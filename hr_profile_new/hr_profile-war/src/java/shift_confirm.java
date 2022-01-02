/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrShiftChangeRequest;
import e.HrShiftRequestDt;
import e.HrUsers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.primefaces.event.RowEditEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class shift_confirm {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrShiftRequestDt> hrShidtRequestDtList = new ArrayList<HrShiftRequestDt>();
    private String usercode;
    private HrEmpInfo hrEmpInfo = new HrEmpInfo();

    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrShidtRequestDtList = sessionBean.getHrShiftRequestDtList(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
    }

    /** Creates a new instance of shift_confirm */
    public shift_confirm() {
    }

    public List<HrShiftRequestDt> getHrShidtRequestDtList() {
        return hrShidtRequestDtList;
    }

    public void setHrShidtRequestDtList(List<HrShiftRequestDt> hrShidtRequestDtList) {
        this.hrShidtRequestDtList = hrShidtRequestDtList;
    }

    public void update(RowEditEvent e) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HrShiftRequestDt hrShiftRequestDt = (HrShiftRequestDt) e.getObject();
        Connection connection=null;
        Session session=null;
        MessageProducer messageProducer=null;
        HrShiftChangeRequest hrShiftChangeRequest = sessionBean.getHrShiftChangeRequestById(hrShiftRequestDt.getReqId());
        hrShiftChangeRequest.setConfirmDate(new Date());
        if (hrShiftRequestDt.getMngConfirm().equals("Y")) {
            hrShiftChangeRequest.setMngNo(hrEmpInfo);
            hrShiftChangeRequest.setMngConfirm("Y");
            sessionBean.merge_shift_request(hrShiftChangeRequest);
            if (CashHandler.getEmpManagers().get(hrShiftChangeRequest.getEmpNo().getEmpNo()) != null && CashHandler.getEmpManagers().get(hrShiftChangeRequest.getEmpNo().getEmpNo()).size() > 0) {
                for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrShiftChangeRequest.getEmpNo().getEmpNo())) {
                    if (CashHandler.getAlerts().get(hrEmpMangers.getMngNo()) != null && CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).size() > 0) {
                        for (HrProfileMsg msg : CashHandler.getAlerts().get(hrEmpMangers.getMngNo())) {
                            if (msg.getMsgId().equals(hrShiftChangeRequest.getId()) && msg.getEntityName().equals("HrShiftChangeRequest")) {
                                msg.setReadDone('Y');
                            }
                        }
                    }
                }
                sessionBean.updateReadDoneMsg("HrShiftChangeRequest", hrShiftChangeRequest.getId(), 'Y', null);
            }

            try {
                Context ctx = new InitialContext();
                ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                connection = connectionFactory.createConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                messageProducer = session.createProducer(queue);
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("HrShiftChangeRequest");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrShiftChangeRequest.getEmpNo().getEmpNo());
                hrProfileMsg.setSenderNo(hrShiftChangeRequest.getMngNo().getEmpNo());
                hrProfileMsg.setMsgId(hrShiftChangeRequest.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove('Y');
                objectMessage.setObject(hrProfileMsg);
                if (CashHandler.getMsgs().containsKey(hrShiftChangeRequest.getEmpNo().getEmpNo())) {
                    CashHandler.getMsgs().get(hrShiftChangeRequest.getEmpNo().getEmpNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getMsgs().put(hrShiftChangeRequest.getEmpNo().getEmpNo(), hrProfileMsgs);
                }
                messageProducer.send(objectMessage);
                System.out.println("message sent");

            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (JMSException x) {
                x.printStackTrace();
            }finally{
            try{
            messageProducer.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }try{
            session.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }try{
            connection.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈⁄ „«œ ÿ·»  €ÌÌ— «·‘Ì› "));
        } else if (hrShiftRequestDt.getMngConfirm().equals("N")) {
            hrShiftChangeRequest.setMngNo(sessionBean.finduserbyid(Long.parseLong(usercode)));
            hrShiftChangeRequest.setMngConfirm("N");
            
            try {
                Context ctx = new InitialContext();
                ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                connection = connectionFactory.createConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                messageProducer = session.createProducer(queue);
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("HrShiftChangeRequest");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrShiftChangeRequest.getEmpNo().getEmpNo());
                hrProfileMsg.setSenderNo(hrShiftChangeRequest.getMngNo().getEmpNo());
                hrProfileMsg.setMsgId(hrShiftChangeRequest.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove('N');
                objectMessage.setObject(hrProfileMsg);
                if (CashHandler.getMsgs().containsKey(hrShiftChangeRequest.getEmpNo().getEmpNo())) {
                    CashHandler.getMsgs().get(hrShiftChangeRequest.getEmpNo().getEmpNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getMsgs().put(hrShiftChangeRequest.getEmpNo().getEmpNo(), hrProfileMsgs);
                }
                messageProducer.send(objectMessage);
                System.out.println("message sent");
            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (JMSException x) {
                x.printStackTrace();
            }finally{
            try{
            messageProducer.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }try{
            session.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }try{
            connection.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
            sessionBean.merge_shift_request(hrShiftChangeRequest);
            if (CashHandler.getEmpManagers().get(hrShiftChangeRequest.getEmpNo().getEmpNo()) != null && CashHandler.getEmpManagers().get(hrShiftChangeRequest.getEmpNo().getEmpNo()).size() > 0) {
                for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrShiftChangeRequest.getEmpNo().getEmpNo())) {
                    if (CashHandler.getAlerts().get(hrEmpMangers.getMngNo()) != null && CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).size() > 0) {
                        for (HrProfileMsg msg : CashHandler.getAlerts().get(hrEmpMangers.getMngNo())) {
                            if (msg.getMsgId().equals(hrShiftChangeRequest.getId()) && msg.getEntityName().equals("HrShiftChangeRequest")) {
                                msg.setReadDone('Y');
                            }
                        }
                    }
                }
                sessionBean.updateReadDoneMsg("HrShiftChangeRequest", hrShiftChangeRequest.getId(), 'Y', null);
            }
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ ÿ·»  €ÌÌ— «·‘Ì› "));
        }

    }
}

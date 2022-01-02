/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrAuthorizeRequest;
import e.HrAuthorizeRequestDt;
import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import sb.SessionBeanLocal;
import sb.SessionBeanRemote;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class authorize_confirm {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrAuthorizeRequestDt> hrAuthorizeRequestList = new ArrayList<HrAuthorizeRequestDt>();
    private String usercode;
    HrEmpInfo hrEmpInfo;

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
        hrAuthorizeRequestList = sessionBean.getAuthorizeRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
    }

    /** Creates a new instance of authorize_confirm */
    public authorize_confirm() {
    }

    public void update(RowEditEvent e) {
       Connection connection=null;
       Session session=null;
       MessageProducer messageProducer=null;
        long start_time=System.currentTimeMillis();
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        HrAuthorizeRequestDt hrAuthorizeRequestDt = (HrAuthorizeRequestDt) e.getObject();
        HrAuthorizeRequest hrAuthorizeRequest = sessionBean.getAuthorizeRequestById(hrAuthorizeRequestDt.getReqId());
        Calendar c = Calendar.getInstance();
        c.setTime(hrAuthorizeRequestDt.getAuthorizeDate());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        System.out.println("time1>>>"+(System.currentTimeMillis()-start_time));
        Long chk_result = sessionBean.chk_authorize_request(hrAuthorizeRequest.getAuthorizeDate(), hrAuthorizeRequest.getEmpNo(), null, hrAuthorizeRequest.getMinutesNo());
        System.out.println("time2>>>"+(System.currentTimeMillis()-start_time));
        if (chk_result == 4L) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ÎØÃ", "Êã ÊÌÇæÒ ÇáÍÏ ÇáÃÞÕì áãÌãæÚ ÚÏÏ ÇáÏÞÇÆÞ");
            fc.addMessage(null, fm);
            hrAuthorizeRequestList = sessionBean.getAuthorizeRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
            return;
        }
        if (hrAuthorizeRequest.getAccepted() != null && hrAuthorizeRequest.getFlag() != null && hrAuthorizeRequest.getFlag().equals("Y") && (hrAuthorizeRequestDt.getAccepted() == null || hrAuthorizeRequestDt.getAccepted().equals("N")) && hrAuthorizeRequest.getAccepted().equals("Y")) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ÎØÃ", "áÇ íãßäß ÑÝÖ ÇáØáÈ .. Êã ÊÝÚíá ÇáÅÐä");
            fc.addMessage(null, fm);
            hrAuthorizeRequestList = sessionBean.getAuthorizeRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
            return;
        }
        if (hrAuthorizeRequestDt.getAccepted() == null) {
            hrAuthorizeRequest.setAccepted(null);
            hrAuthorizeRequest.setMngNo(null);
        } else {
            hrAuthorizeRequest.setAccepted(hrAuthorizeRequestDt.getAccepted());
            hrAuthorizeRequest.setMngNo(hrEmpInfo);
        }
        System.out.println("time3>>>"+(System.currentTimeMillis()-start_time));
        sessionBean.mergeHrAuthorizeRequest(hrAuthorizeRequest);
        if (hrAuthorizeRequestDt.getAccepted() != null && hrAuthorizeRequestDt.getAccepted().equals("Y")) {
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Êã ÇáÃÚÊãÇÏ", "Êã ÃÚÊãÇÏ ÇáÅÐä");
            if (CashHandler.getEmpManagers().get(hrAuthorizeRequest.getEmpNo()) != null && CashHandler.getEmpManagers().get(hrAuthorizeRequest.getEmpNo()).size() > 0) {
                    for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrAuthorizeRequest.getEmpNo())) {
                        System.out.println(hrEmpMangers.getMngName());
                        if (CashHandler.getAlerts().get(hrEmpMangers.getMngNo()) != null && CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).size() > 0) {
                            for (HrProfileMsg msg : CashHandler.getAlerts().get(hrEmpMangers.getMngNo())) {
                                if (msg.getMsgId().equals(hrAuthorizeRequest.getId()) && msg.getEntityName().equals("HrAuthorizeRequest")) {
                                    msg.setReadDone('Y');
                                }
                            }
                        }
                    }
                    sessionBean.updateReadDoneMsg("HrAuthorizeRequest", hrAuthorizeRequest.getId(), 'Y', null);
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
                hrProfileMsg.setEntityName("HrAuthorizeRequest");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrAuthorizeRequest.getEmpNo());
                hrProfileMsg.setSenderNo(Long.parseLong(usercode));
                hrProfileMsg.setMsgId(hrAuthorizeRequest.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove('Y');
                objectMessage.setObject(hrProfileMsg);
                if (CashHandler.getMsgs().containsKey(hrAuthorizeRequest.getEmpNo())) {
                    CashHandler.getMsgs().get(hrAuthorizeRequest.getEmpNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getMsgs().put(hrAuthorizeRequest.getEmpNo(), hrProfileMsgs);
                }
                messageProducer.send(objectMessage);
                System.out.println("message sent");
                 System.out.println("time4>>>"+(System.currentTimeMillis()-start_time));

            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (JMSException x) {
                x.printStackTrace();
            }
            finally{
            try{
            messageProducer.close();
            }catch(Exception exc){
                exc.printStackTrace();
            }try{
            session.close();
            }catch(Exception exc){
                exc.printStackTrace();
            }try{
            connection.close();
            }catch(Exception exc){
                exc.printStackTrace();
            }

        }
        } else if (hrAuthorizeRequestDt.getAccepted() != null && hrAuthorizeRequestDt.getAccepted().equals("N")) {
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Êã ÇáÑÝÖ", "Êã ÑÝÖ ÇáÅÐä");
            if (CashHandler.getEmpManagers().get(hrAuthorizeRequest.getEmpNo()) != null && CashHandler.getEmpManagers().get(hrAuthorizeRequest.getEmpNo()).size() > 0) {
                    for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrAuthorizeRequest.getEmpNo())) {
                        System.out.println(hrEmpMangers.getMngName());
                        if (CashHandler.getAlerts().get(hrEmpMangers.getMngNo()) != null && CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).size() > 0) {
                            for (HrProfileMsg msg : CashHandler.getAlerts().get(hrEmpMangers.getMngNo())) {
                                if (msg.getMsgId().equals(hrAuthorizeRequest.getId()) && msg.getEntityName().equals("HrAuthorizeRequest")) {
                                    msg.setReadDone('Y');
                                }
                            }
                        }
                    }
                    sessionBean.updateReadDoneMsg("HrAuthorizeRequest", hrAuthorizeRequest.getId(), 'Y', null);
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
                hrProfileMsg.setEntityName("HrAuthorizeRequest");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrAuthorizeRequest.getEmpNo());
                hrProfileMsg.setSenderNo(Long.parseLong(usercode));
                hrProfileMsg.setMsgId(hrAuthorizeRequest.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove('N');
                objectMessage.setObject(hrProfileMsg);
                if (CashHandler.getMsgs().containsKey(hrAuthorizeRequest.getEmpNo())) {
                    CashHandler.getMsgs().get(hrAuthorizeRequest.getEmpNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getMsgs().put(hrAuthorizeRequest.getEmpNo(), hrProfileMsgs);
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
        } else {
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Êã ÅáÛÇÁ ÇáÃÚÊãÇÏ", "Êã ÅáÛÇÁ ÇáÃÚÊãÇÏ");
            if (CashHandler.getEmpManagers().get(hrAuthorizeRequest.getEmpNo()) != null && CashHandler.getEmpManagers().get(hrAuthorizeRequest.getEmpNo()).size() > 0) {
                    for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrAuthorizeRequest.getEmpNo())) {
                        System.out.println(hrEmpMangers.getMngName());
                        if (CashHandler.getAlerts().get(hrEmpMangers.getMngNo()) != null && CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).size() > 0) {
                            for (HrProfileMsg msg : CashHandler.getAlerts().get(hrEmpMangers.getMngNo())) {
                                if (msg.getMsgId().equals(hrAuthorizeRequest.getId()) && msg.getEntityName().equals("HrAuthorizeRequest")) {
                                    msg.setReadDone('N');
                                }
                            }
                        }
                    }
                    sessionBean.updateReadDoneMsg("HrAuthorizeRequest", hrAuthorizeRequest.getId(), 'N', null);
                }
        }
        fc.addMessage(null, fm);

    }

    public List<HrAuthorizeRequestDt> getHrAuthorizeRequestList() {
        return hrAuthorizeRequestList;
    }

    public void setHrAuthorizeRequestList(List<HrAuthorizeRequestDt> hrAuthorizeRequestList) {
        this.hrAuthorizeRequestList = hrAuthorizeRequestList;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrBorrowFundRequest;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
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
import org.primefaces.event.RowEditEvent;
import sb.SessionBeanLocal;
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

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class FundBorrowGuaranteeApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrBorrowFundRequest> borrowFundRequestList = new ArrayList<HrBorrowFundRequest>();

    @PostConstruct
    public void init() {
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
        borrowFundRequestList = sessionBean.findAllGuaranteeRequests(hrEmpInfo);
    }

    public void update(RowEditEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HrBorrowFundRequest hrBorrowFundRequest = (HrBorrowFundRequest) event.getObject();
        if (hrBorrowFundRequest.getGuarantee1().getEmpNo() == hrEmpInfo.getEmpNo()) {
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee1", hrBorrowFundRequest.getId(), 'Y', null);
            if (CashHandler.getAlerts().get(hrEmpInfo.getEmpNo()) != null && CashHandler.getAlerts().get(hrEmpInfo.getEmpNo()).size() > 0) {
                for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(hrEmpInfo.getEmpNo())) {
                    if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee1") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                        hrProfileMsg.setReadDone('Y');
                    }
                }
            }
            sendMessages(hrBorrowFundRequest, 1);
        } else if (hrBorrowFundRequest.getGuarantee2().getEmpNo() == hrEmpInfo.getEmpNo()) {
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee2", hrBorrowFundRequest.getId(), 'Y', null);
            if (CashHandler.getAlerts().get(hrEmpInfo.getEmpNo()) != null && CashHandler.getAlerts().get(hrEmpInfo.getEmpNo()).size() > 0) {
                for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(hrEmpInfo.getEmpNo())) {
                    if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee2") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                        hrProfileMsg.setReadDone('Y');
                    }
                }
            }
            sendMessages(hrBorrowFundRequest, 2);
        }
        if(hrBorrowFundRequest.getGuarantee1Confirm()!=null && hrBorrowFundRequest.getGuarantee2Confirm()!=null
                && hrBorrowFundRequest.getGuarantee1Confirm().equals(new Character(('Y'))) && hrBorrowFundRequest.getGuarantee2Confirm().equals(new Character(('Y')))){
            sendMessages(hrBorrowFundRequest, 3);
        }else if(hrBorrowFundRequest.getGuarantee1Confirm()!=null && hrBorrowFundRequest.getGuarantee2Confirm()!=null
                && (hrBorrowFundRequest.getGuarantee1Confirm().equals(new Character('N')) || hrBorrowFundRequest.getGuarantee2Confirm().equals(new Character('N')))){
            cancelMessage(hrBorrowFundRequest);
        }
        sessionBean.mergeFundBorrow(hrBorrowFundRequest);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·—œ ⁄·Ï ÿ·» ÷„«‰ ”·›… »‰Ã«Õ"));
    }

    public void sendMessages(HrBorrowFundRequest hrBorrowFundRequest, int type) {
        Connection connection=null;
        Session session=null;
        MessageProducer messageProducer=null;
        try {
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
            Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            ObjectMessage objectMessage = session.createObjectMessage();
            HrProfileMsg hrProfileMsg = new HrProfileMsg();
            hrProfileMsg.setSendDate(new Date());
            hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
            hrProfileMsg.setMsgId(hrBorrowFundRequest.getId());

            if (type == 1) {
                hrProfileMsg.setMsgApprove(hrBorrowFundRequest.getGuarantee1Confirm());
                hrProfileMsg.setEntityName("HrBorrowFundRequestGuarantee1");
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setEmpNo(hrBorrowFundRequest.getEmpNo().getEmpNo());
            } else if (type == 2) {
                hrProfileMsg.setEntityName("HrBorrowFundRequestGuarantee2");
                hrProfileMsg.setMsgApprove(hrBorrowFundRequest.getGuarantee2Confirm());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setEmpNo(hrBorrowFundRequest.getEmpNo().getEmpNo());
            } else if (type == 3) {
                hrProfileMsg.setEntityName("HrBorrowFundRequestDeptMng");
                hrProfileMsg.setMsgType(1L);
                hrProfileMsg.setEmpNo(hrBorrowFundRequest.getDeptMng().getEmpNo());
            }
            objectMessage.setObject(hrProfileMsg);
            if (type == 1 || type == 2) {
                if (CashHandler.getMsgs().containsKey(hrBorrowFundRequest.getEmpNo().getEmpNo())) {
                    CashHandler.getMsgs().get(hrBorrowFundRequest.getEmpNo().getEmpNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getMsgs().put(hrBorrowFundRequest.getEmpNo().getEmpNo(), hrProfileMsgs);
                }
            }else{
                if (CashHandler.getAlerts().containsKey(hrBorrowFundRequest.getDeptMng().getEmpNo())) {
                    CashHandler.getAlerts().get(hrBorrowFundRequest.getDeptMng().getEmpNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getAlerts().put(hrBorrowFundRequest.getDeptMng().getEmpNo(), hrProfileMsgs);
                }
            }
            messageProducer.send(objectMessage);
            System.out.println("message sent");
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally{
            try{
            messageProducer.close();
            }catch(Exception e){
                e.printStackTrace();
            }try{
            session.close();
            }catch(Exception e){
                e.printStackTrace();
            }try{
            connection.close();
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

     public void cancelMessage(HrBorrowFundRequest hrBorrowFundRequest) {
        for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(hrBorrowFundRequest.getDeptMng().getEmpNo())) {
            if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestDeptMng") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                hrProfileMsg.setReadDone('Y');
                return;
            }
        }
        sessionBean.updateReadDoneMsg("HrBorrowFundRequestDeptMng", hrBorrowFundRequest.getId(), 'Y', null);
    }

    /** Creates a new instance of FundBorrowGuaranteeApprove */
    public FundBorrowGuaranteeApprove() {
    }

    public List<HrBorrowFundRequest> getBorrowFundRequestList() {
        return borrowFundRequestList;
    }

    public void setBorrowFundRequestList(List<HrBorrowFundRequest> borrowFundRequestList) {
        this.borrowFundRequestList = borrowFundRequestList;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }
}

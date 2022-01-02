/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrBorrowFundRequest;
import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrFundAdvanceSetup;
import e.HrFundBorrowSetup;
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
public class FundBorrowDeptMngApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo = new HrEmpInfo();
    private List<HrBorrowFundRequest> deptMngRequestList = new ArrayList<HrBorrowFundRequest>();
    private HrFundBorrowSetup hrFundBorrowSetup = new HrFundBorrowSetup();

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
        deptMngRequestList = sessionBean.findDeptMngRequests(hrEmpInfo);
        hrFundBorrowSetup = sessionBean.findBorrowSetup();
    }

    public void update(RowEditEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HrBorrowFundRequest hrBorrowFundRequest = (HrBorrowFundRequest) event.getObject();
        sessionBean.mergeFundBorrow(hrBorrowFundRequest);

        
//        sessionBean.updateReadDoneMsg("HrBorrowFundRequestDeptMng", hrBorrowFundRequest.getId(), 'Y', null);
//        if (CashHandler.getAlerts().get(hrEmpInfo.getEmpNo()) != null && CashHandler.getAlerts().get(hrEmpInfo.getEmpNo()).size() > 0) {
//            for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(hrEmpInfo.getEmpNo())) {
//                if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestDeptMng") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
//                    hrProfileMsg.setReadDone('Y');
//                }
//            }
//        }


        if (CashHandler.getEmpManagers().get(hrBorrowFundRequest.getEmpNo().getEmpNo()) != null && CashHandler.getEmpManagers().get(hrBorrowFundRequest.getEmpNo().getEmpNo()).size() > 0) {
            for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrBorrowFundRequest.getEmpNo().getEmpNo())) {
                if (CashHandler.getAlerts().get(hrEmpMangers.getMngNo()) != null && CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).size() > 0) {
                    for (HrProfileMsg msg : CashHandler.getAlerts().get(hrEmpMangers.getMngNo())) {
                        if (msg.getMsgId().equals(hrBorrowFundRequest.getId()) && msg.getEntityName().equals("HrBorrowFundRequestDeptMng")) {
                            msg.setReadDone('Y');
                        }
                    }
                }
            }
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestDeptMng", hrBorrowFundRequest.getId(), 'Y', null);
        }


        sendMessages(hrBorrowFundRequest, 1);
        if (hrBorrowFundRequest.getDeptMngConfirm() != null && hrBorrowFundRequest.getDeptMngConfirm().equals(new Character('Y'))) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  «·„Ê«›ﬁ… ⁄·Ï ÿ·» ”·›… „‰ «·’‰œÊﬁ »‰Ã«Õ"));
            if (hrBorrowFundRequest.getDeptMngConfirm() != null && hrBorrowFundRequest.getDeptMngConfirm().equals('Y') && (hrBorrowFundRequest.getCancel() == null || hrBorrowFundRequest.getCancel().equals('N'))) {
                sendMessages(hrBorrowFundRequest, 2);
                hrBorrowFundRequest.setNewReplyFlag('Y');
            }
        } else if (hrBorrowFundRequest.getDeptMngConfirm() != null && hrBorrowFundRequest.getDeptMngConfirm().equals(new Character('N'))) {
            cancelMessage(hrBorrowFundRequest);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ ÿ·» ”·›… „‰ «·’‰œÊﬁ »‰Ã«Õ"));
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈·€«¡ «·≈⁄ „«œ »‰Ã«Õ"));
        }
    }

    public void sendMessages(HrBorrowFundRequest hrBorrowFundRequest, int type) {
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        try {
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
            Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            if (type == 1) {
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
                hrProfileMsg.setMsgId(hrBorrowFundRequest.getId());
                hrProfileMsg.setEmpNo(hrBorrowFundRequest.getEmpNo().getEmpNo());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setEntityName("HrBorrowFundRequestDeptMng");
                if (CashHandler.getMsgs().containsKey(hrBorrowFundRequest.getEmpNo().getEmpNo())) {
                    CashHandler.getMsgs().get(hrBorrowFundRequest.getEmpNo().getEmpNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getMsgs().put(hrBorrowFundRequest.getEmpNo().getEmpNo(), hrProfileMsgs);
                }
                hrProfileMsg.setMsgApprove(hrBorrowFundRequest.getDeptMngConfirm());
                objectMessage.setObject(hrProfileMsg);
                messageProducer.send(objectMessage);
            } else {
                Long[] respArray = {hrFundBorrowSetup.getResponsibleCode(), hrFundBorrowSetup.getResponsible2(), hrFundBorrowSetup.getResponsible3()};
                for (int i = 0; i < respArray.length; i++) {
                    if (respArray[i] == null) {
                        continue;
                    }
                    ObjectMessage objectMessage = session.createObjectMessage();
                    HrProfileMsg hrProfileMsg = new HrProfileMsg();
                    hrProfileMsg.setSendDate(new Date());
                    hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
                    hrProfileMsg.setMsgId(hrBorrowFundRequest.getId());
                    hrProfileMsg.setEmpNo(respArray[i]);
                    hrProfileMsg.setMsgType(1L);
                    hrProfileMsg.setEntityName("HrBorrowFundRequestResponsible");
                    if (CashHandler.getAlerts().containsKey(respArray[i])) {
                        CashHandler.getAlerts().get(respArray[i]).add(hrProfileMsg);
                    } else {
                        List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                        hrProfileMsgs.add(hrProfileMsg);
                        CashHandler.getAlerts().put(respArray[i], hrProfileMsgs);
                    }
                    hrProfileMsg.setMsgApprove(hrBorrowFundRequest.getDeptMngConfirm());
                    objectMessage.setObject(hrProfileMsg);
                    messageProducer.send(objectMessage);
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                messageProducer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void cancelMessage(HrBorrowFundRequest hrBorrowFundRequest) {
        Long[] respArray = {hrFundBorrowSetup.getResponsibleCode(), hrFundBorrowSetup.getResponsible2(), hrFundBorrowSetup.getResponsible3()};
        sessionBean.updateReadDoneMsg("HrBorrowFundRequestResponsible", hrBorrowFundRequest.getId(), 'Y', null);
        for (int i = 0; i < respArray.length; i++) {
            if (respArray[i] == null) {
                continue;
            }
            for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(respArray[i])) {
                if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestResponsible") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                    hrProfileMsg.setReadDone('Y');
                    break;
                }
            }
        }
    }

    /** Creates a new instance of FundBorrowDeptMngApprove */
    public FundBorrowDeptMngApprove() {
    }

    public List<HrBorrowFundRequest> getDeptMngRequestList() {
        return deptMngRequestList;
    }

    public void setDeptMngRequestList(List<HrBorrowFundRequest> deptMngRequestList) {
        this.deptMngRequestList = deptMngRequestList;
    }
}

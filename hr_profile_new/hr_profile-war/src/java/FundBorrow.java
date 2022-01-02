/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrBorrowFundRequest;
import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrFaculty;
import e.HrFundBorrowSetup;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.text.SimpleDateFormat;
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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
public class FundBorrow implements Cloneable {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrBorrowFundRequest hrBorrowFundRequest;
    private Long month;
    private Long year;
    private boolean cancel;
    private HrEmpInfo hrEmpInfo;
    private char editFlag = 'N';
    protected HrBorrowFundRequest copy;
    private List<HrBorrowFundRequest> reqList = new ArrayList<HrBorrowFundRequest>();
    private HrBorrowFundRequest selectedReauest = new HrBorrowFundRequest();
    private String agreementMsg;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
    private HrFundBorrowSetup hrFundBorrowSetup=new HrFundBorrowSetup();

    @PostConstruct
    private void init() {
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
        reqList = sessionBean.findHrBorrowFundRequests(hrEmpInfo);
        if (!reqList.isEmpty()) {
            hrBorrowFundRequest = reqList.get(0);
            try {
                copy = (HrBorrowFundRequest) hrBorrowFundRequest.clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(FundBorrow.class.getName()).log(Level.SEVERE, null, ex);
            }
            month = hrBorrowFundRequest.getReqStart().get(Calendar.MONTH) + 1l;
            year = hrBorrowFundRequest.getReqStart().get(Calendar.YEAR) + 0l;
        } else {
            hrBorrowFundRequest = new HrBorrowFundRequest();
        }

        if (CashHandler.getMsgs().get(hrEmpInfo.getEmpNo()) != null && CashHandler.getMsgs().get(hrEmpInfo.getEmpNo()).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getMsgs().get(hrEmpInfo.getEmpNo())) {
                if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee1") || hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee2")
                        || hrProfileMsg.getEntityName().equals("HrBorrowFundRequestDeptMng") || hrProfileMsg.getEntityName().equals("HrBorrowFundRequestResponsible")) {
                    hrProfileMsg.setReadDone('Y');
                }
            }
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee1", null, 'Y', hrEmpInfo.getEmpNo());
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee2", null, 'Y', hrEmpInfo.getEmpNo());
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestDeptMng", null, 'Y', hrEmpInfo.getEmpNo());
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestResponsible", null, 'Y', hrEmpInfo.getEmpNo());
        }
        hrFundBorrowSetup=sessionBean.findBorrowSetup();
    }

    public void saveFundBorrow(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (hrBorrowFundRequest != null && hrBorrowFundRequest.getId() != null && cancel) {
            if(hrBorrowFundRequest.getMngConfirm()!=null && hrBorrowFundRequest.getMngConfirm().equals('Y')){
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ «·≈·€«¡.. „ ≈⁄ „«œ «·ÿ·»"));
                return;
            }
            hrBorrowFundRequest.setCancel('Y');
            sessionBean.mergeFundBorrow(hrBorrowFundRequest);
            cancelMessage(hrBorrowFundRequest.getGuarantee1().getEmpNo(), 1);
            cancelMessage(hrBorrowFundRequest.getGuarantee2().getEmpNo(), 2);
            cancelMessage(hrBorrowFundRequest.getDeptMng().getEmpNo(), 3);
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee1", null, 'Y', hrEmpInfo.getEmpNo());
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee2", null, 'Y', hrEmpInfo.getEmpNo());
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestDeptMng", null, 'Y', hrEmpInfo.getEmpNo());
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestResponsible", null, 'Y', hrEmpInfo.getEmpNo());
            sessionBean.updateReadDoneMsg("HrBorrowFundRequest", hrBorrowFundRequest.getId(), 'Y', hrEmpInfo.getEmpNo());
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ ≈·€«¡ «·ÿ·»"));
            cancel=false;
            return;
        }

        if (year == null || month == null || hrBorrowFundRequest.getDeptMng() == null || hrBorrowFundRequest.getReqMonths() == null
                || hrBorrowFundRequest.getDeptMng().getEmpName() == null || hrBorrowFundRequest.getReqAmount() == null
                || hrBorrowFundRequest.getGuarantee1() == null || hrBorrowFundRequest.getGuarantee2() == null
                || hrBorrowFundRequest.getGuarantee1().getEmpName() == null
                || hrBorrowFundRequest.getGuarantee2().getEmpName() == null
                || hrBorrowFundRequest.getG1Phone() == null || hrBorrowFundRequest.getG1Phone().isEmpty()
                || hrBorrowFundRequest.getG2Phone() == null || hrBorrowFundRequest.getG2Phone().isEmpty()
                || hrBorrowFundRequest.getDeptMngPhone() == null || hrBorrowFundRequest.getDeptMngPhone().isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
            return;
        }

        List<HrEmpMangers> mngList = CashHandler.getEmpManagers().get(hrEmpInfo.getEmpNo());
        int cnt = 0;
        for (HrEmpMangers hrEmpMangers : mngList) {
            if (hrBorrowFundRequest.getDeptMng().getEmpNo() == hrEmpMangers.getMngNo()) {
                cnt++;
                break;
            }
        }
        if (cnt == 0) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» √‰ ÌﬂÊ‰ —∆Ì” «·ﬁ”„ √Õœ «·„⁄ „œÌ‰ ·ÿ·»«  «·„ÊŸ›"));
            return;
        }

        if (hrBorrowFundRequest.getGuarantee1().getEmpNo() == hrBorrowFundRequest.getGuarantee2().getEmpNo()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·÷«„‰ «·√Ê· ÂÊ ‰›”Â «·÷«„‰ «·À«‰Ï"));
            return;
        }
        if (hrBorrowFundRequest.getGuarantee1().getEmpNo() == hrEmpInfo.getEmpNo() || hrBorrowFundRequest.getGuarantee2().getEmpNo() == hrEmpInfo.getEmpNo()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ··„ÊŸ› √‰ ÌﬂÊ‰ ÷«„‰ ·‰›”Â"));
            return;
        }

        Calendar startDate = Calendar.getInstance();
        startDate.set(year.intValue(), month.intValue() - 1, 1);

        int chk = sessionBean.chkFundBorrow(hrEmpInfo.getEmpNo(), hrBorrowFundRequest.getGuarantee1().getEmpNo(), hrBorrowFundRequest.getGuarantee2().getEmpNo(), hrBorrowFundRequest.getReqAmount(), hrBorrowFundRequest.getReqMonths(), hrEmpInfo.getTotSal(), startDate.getTime());
        if (chk == 1) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ‹‰   ⁄œÏ „œ…  ⁄ÌÌ‰ «·„ÊŸ› ” … √‘Â— ⁄·Ï «·√ﬁ·"));
            return;
        }
        else if (chk == 12) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ÿ·» √ﬂÀ— „‰ ”·›… Œ·«· ‰›” «·‘Â—"));
            return;
        }
        else if (chk == 2) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ «·Õ’Ê· ⁄·Ï ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„· »⁄œ"));
            return;
        } else if (chk == 3) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ «·Õ’Ê· ⁄·Ï ”·›… ·„ Ì„— ⁄·Ï ≈‰ Â«¡ ”œ«œÂ« ”‰…"));
            return;
        } else if (chk == 4) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·÷«„‰ «·√Ê· √Œ– ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„·"));
            return;
        } else if (chk == 5) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·÷«„‰ «·√Ê· ÷„‰ ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„·"));
            return;
        } else if (chk == 6) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·÷«„‰ «·À«‰Ï √Œ– ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„·"));
            return;
        } else if (chk == 7) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·÷«„‰ «·À«‰Ï ÷„‰ ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„·"));
            return;
        } else if (chk == 8) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·”·›…  ⁄œ  √÷⁄«› «·—« »"));
            return;
        } else if (chk == 9) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  ⁄œÏ «·„»·€ «·„”„ÊÕ »Â ·„Ã„Ê⁄ «·”·› Œ·«· › —… «· ⁄ÌÌ‰"));
            return;
        } else if (chk == 10) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» √·« ÌﬂÊ‰ «· «—ÌŒ ﬁ»· »œ«Ì… «·‘Â— «·Õ«·Ï"));
            return;
        } else if (chk == 11) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  Ã«Ê“ «·Õœ «·√ﬁ’Ï ·⁄œœ ‘ÂÊ— «·”œ«œ"));
            return;
        } else {
            hrBorrowFundRequest.setReqStart(startDate);
            hrBorrowFundRequest.setCancel(null);
            hrBorrowFundRequest.setEmpNo(hrEmpInfo);
            if (hrBorrowFundRequest.getId() != null && hrBorrowFundRequest.getId() != 0L) {
                if (copy.getGuarantee1() != null && hrBorrowFundRequest.getGuarantee1() != null && copy.getGuarantee1().getEmpNo() != hrBorrowFundRequest.getGuarantee1().getEmpNo()) {
                    cancelMessage(copy.getGuarantee1().getEmpNo(), 1);
                    sendMessages(hrBorrowFundRequest.getGuarantee1().getEmpNo(), 1);
                }
                if (copy.getGuarantee2() != null && hrBorrowFundRequest.getGuarantee2() != null && copy.getGuarantee2().getEmpNo() != hrBorrowFundRequest.getGuarantee2().getEmpNo()
                        || copy.getDeptMng().getEmpNo() != hrBorrowFundRequest.getDeptMng().getEmpNo()) {
                    cancelMessage(copy.getGuarantee2().getEmpNo(), 2);
                    sendMessages(hrBorrowFundRequest.getGuarantee2().getEmpNo(), 2);
                }
                if (copy.getDeptMng() != null && hrBorrowFundRequest.getDeptMng() != null && copy.getDeptMng().getEmpNo() != hrBorrowFundRequest.getDeptMng().getEmpNo()) {
                    cancelMessage(copy.getDeptMng().getEmpNo(), 3);
                    sendMessages(hrBorrowFundRequest.getGuarantee2().getEmpNo(), 3);

                }
                if (hrBorrowFundRequest.getMngNo() != null) {
                    sendMessages(hrBorrowFundRequest.getMngNo().getEmpNo(), 4);
                    hrBorrowFundRequest.setNewReplyFlag('Y');
                }

                sessionBean.mergeFundBorrow(hrBorrowFundRequest);
                reqList = sessionBean.findHrBorrowFundRequests(hrEmpInfo);
                hrBorrowFundRequest = reqList.get(0);
                try {
                    copy = (HrBorrowFundRequest) hrBorrowFundRequest.clone();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(FundBorrow.class.getName()).log(Level.SEVERE, null, ex);
                }
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „  ⁄œÌ· «·ÿ·» »‰Ã«Õ"));
            } else {

                hrBorrowFundRequest.setReqDate(new Date());
                hrBorrowFundRequest.setSerial(sessionBean.findMaxFundBorrowSerial() + 1L);
                sessionBean.persistFundBorrow(hrBorrowFundRequest);
                reqList = sessionBean.findHrBorrowFundRequests(hrEmpInfo);
                hrBorrowFundRequest = reqList.get(0);
                try {
                    copy = (HrBorrowFundRequest) hrBorrowFundRequest.clone();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(FundBorrow.class.getName()).log(Level.SEVERE, null, ex);
                }
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ Õ›Ÿ «·ÿ·» »‰Ã«Õ"));
                sendMessages(hrBorrowFundRequest.getGuarantee1().getEmpNo(), 1);
                sendMessages(hrBorrowFundRequest.getGuarantee2().getEmpNo(), 2);

            }
            editFlag = 'N';
        }
    }

    public void onSelect() {
        hrBorrowFundRequest = selectedReauest;
        if(selectedReauest.getCancel()!=null && selectedReauest.getCancel().equals('Y'))
            cancel=true;
        month = hrBorrowFundRequest.getReqStart().get(Calendar.MONTH) + 1l;
        year = hrBorrowFundRequest.getReqStart().get(Calendar.YEAR) + 0l;
        editFlag = 'N';
    }

    public void newRequest(ActionEvent ae) {
        hrBorrowFundRequest = new HrBorrowFundRequest();
        try {
            copy = (HrBorrowFundRequest) hrBorrowFundRequest.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FundBorrow.class.getName()).log(Level.SEVERE, null, ex);
        }
        year = null;
        month = null;
        editFlag = 'N';
        cancel = false;
    }

    public String applyEmpResponse(String response) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (response.equals("1")) {
            hrBorrowFundRequest.setEmpConfirm('Y');
            hrBorrowFundRequest.setEmpConfirmDate(new Date());
            sessionBean.mergeFundBorrow(hrBorrowFundRequest);
            if(hrFundBorrowSetup.getResponsibleCode()!=null)
                sendMessages(hrFundBorrowSetup.getResponsibleCode(), 4);
            if(hrFundBorrowSetup.getResponsible2()!=null)
                sendMessages(hrFundBorrowSetup.getResponsible2(), 4);
            if(hrFundBorrowSetup.getResponsible3()!=null)
                sendMessages(hrFundBorrowSetup.getResponsible3(), 4);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „  «·„Ê«›ﬁ… ⁄·Ï  ⁄œÌ·«  „”∆Ê· «·’‰œÊﬁ"));
        } else if (response.equals("2")) {
            hrBorrowFundRequest.setEmpConfirm('N');
            hrBorrowFundRequest.setEmpConfirmDate(new Date());
            sessionBean.mergeFundBorrow(hrBorrowFundRequest);
            hrBorrowFundRequest = new HrBorrowFundRequest();
            month = null;
            year = null;
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ —›÷  ⁄œÌ·«  „”∆Ê· «·’‰œÊﬁ"));
        } else {
            editFlag = 'Y';
        }
        return null;
    }

    public void cancelMessage(long empNo, int type) {
        if (CashHandler.getAlerts().get(empNo) != null && CashHandler.getAlerts().get(empNo).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(empNo)) {
                if (type == 1 && hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee1") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                    hrProfileMsg.setReadDone('Y');

                } else if (type == 2 && hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee2") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                    hrProfileMsg.setReadDone('Y');
                }
            }
        }
        if (CashHandler.getAlerts().get(empNo) != null && CashHandler.getAlerts().get(hrBorrowFundRequest.getDeptMng().getEmpNo()).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(hrBorrowFundRequest.getDeptMng().getEmpNo())) {
                if (type == 3 && hrProfileMsg.getEntityName().equals("HrBorrowFundRequestDeptMng") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                    hrProfileMsg.setReadDone('Y');

                }
            }
        }
        if (type == 1) {
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee1", hrBorrowFundRequest.getId(), 'Y', null);
        } else if (type == 2) {
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee2", hrBorrowFundRequest.getId(), 'Y', null);
        } else if (type == 3) {
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestDeptMng", hrBorrowFundRequest.getId(), 'Y', null);
        }
    }

    public void sendMessages(Long reciever, int type) {
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
            hrProfileMsg.setEmpNo(reciever);
            hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
            hrProfileMsg.setMsgId(hrBorrowFundRequest.getId());

            if (type == 1) {
                hrProfileMsg.setEntityName("HrBorrowFundRequestGuarantee1");
                hrProfileMsg.setMsgType(1L);
            } else if (type == 2) {
                hrProfileMsg.setEntityName("HrBorrowFundRequestGuarantee2");
                hrProfileMsg.setMsgType(1L);
            } else if (type == 3) {
                hrProfileMsg.setEntityName("HrBorrowFundRequestDeptMng");
                hrProfileMsg.setMsgType(1L);
            } else if (type == 4) {
                hrProfileMsg.setEntityName("HrBorrowFundRequest");
                hrProfileMsg.setMsgType(2L);
            }

            objectMessage.setObject(hrProfileMsg);
            if (type == 1 || type == 2) {
                if (CashHandler.getAlerts().containsKey(reciever)) {
                    CashHandler.getAlerts().get(reciever).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getAlerts().put(reciever, hrProfileMsgs);
                }
            } else {
                if (CashHandler.getMsgs().containsKey(reciever)) {
                    CashHandler.getMsgs().get(reciever).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getMsgs().put(reciever, hrProfileMsgs);
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

    public void removeGuarantee1Data() {
        if (hrBorrowFundRequest != null) {
            hrBorrowFundRequest.setG1Phone(null);
            hrBorrowFundRequest.setGuarantee1Confirm(null);
        }
    }

    public void removeGuarantee2Data() {
        if (hrBorrowFundRequest != null) {
            hrBorrowFundRequest.setG2Phone(null);
            hrBorrowFundRequest.setGuarantee2Confirm(null);
        }
    }

    public void removeDeptMngData() {
        hrBorrowFundRequest.setDeptMngPhone(null);
        hrBorrowFundRequest.setDeptMngConfirm(null);
    }

    /** Creates a new instance of FundBorrow */
    public FundBorrow() {
    }

    public HrBorrowFundRequest getHrBorrowFundRequest() {
        return hrBorrowFundRequest;
    }

    public void setHrBorrowFundRequest(HrBorrowFundRequest hrBorrowFundRequest) {
        this.hrBorrowFundRequest = hrBorrowFundRequest;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public char getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(char editFlag) {
        this.editFlag = editFlag;
    }

    public List<HrBorrowFundRequest> getReqList() {
        return reqList;
    }

    public void setReqList(List<HrBorrowFundRequest> reqList) {
        this.reqList = reqList;
    }

    public HrBorrowFundRequest getSelectedReauest() {
        return selectedReauest;
    }

    public void setSelectedReauest(HrBorrowFundRequest selectedReauest) {
        this.selectedReauest = selectedReauest;
    }

    public String getAgreementMsg() {
        if (hrBorrowFundRequest != null && hrBorrowFundRequest.getEmpNo() != null && hrBorrowFundRequest.getResAmount() != null && hrBorrowFundRequest.getResMonths() != null) {
            return " Â· √‰  „Ê«›ﬁ ⁄·Ï ”·›… »ﬁÌ„… " + hrBorrowFundRequest.getResAmount() + " Ì»œ√ ”œ«œÂ« „‰ ‘Â— "
                    + sdf.format(hrBorrowFundRequest.getResStart()) + " »⁄œœ ‘ÂÊ— ”œ«œ  " + hrBorrowFundRequest.getResMonths() + (hrBorrowFundRequest.getResMonths() > 10 ? " ‘Â—«ø " : " √‘Â—ø ");
        }
        return agreementMsg;
    }

    public void setAgreementMsg(String agreementMsg) {
        this.agreementMsg = agreementMsg;
    }
}

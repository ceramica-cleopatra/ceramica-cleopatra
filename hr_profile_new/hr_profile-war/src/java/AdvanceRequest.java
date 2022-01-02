/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrAdvanceRequest;
import e.HrEmpInfo;
import e.HrEmpMangers;
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
import sb.SessionBeanLocal;

/**
 *
 * @author a.abbas
 */
@ManagedBean
@ViewScoped
public class AdvanceRequest {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo = new HrEmpInfo();
    private HrAdvanceRequest hrAdvanceRequest = new HrAdvanceRequest();
    private HrAdvanceRequest selectedRequest = new HrAdvanceRequest();
    private List<HrAdvanceRequest> hrAdvanceRequestList = new ArrayList<HrAdvanceRequest>();
    private boolean cancel;
    protected HrAdvanceRequest copy = new HrAdvanceRequest();

    /** Creates a new instance of AdvanceRequest */
    @PostConstruct
    public void init() {
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        hrAdvanceRequestList = sessionBean.findEmpAdvanceRequests(hrEmpInfo);
        cancel = false;

        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }

        if (CashHandler.getMsgs().get(hrEmpInfo.getEmpNo()) != null && CashHandler.getMsgs().get(hrEmpInfo.getEmpNo()).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getMsgs().get(hrEmpInfo.getEmpNo())) {
                if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestGuarantee") || hrProfileMsg.getEntityName().equals("HrAdvanceRequestDeptMng")
                        || hrProfileMsg.getEntityName().equals("HrAdvanceRequest")) {
                    hrProfileMsg.setReadDone('Y');
                }
            }
            sessionBean.updateReadDoneMsg("HrAdvanceRequestGuarantee", null, 'Y', hrEmpInfo.getEmpNo());
            sessionBean.updateReadDoneMsg("HrAdvanceRequestDeptMng", null, 'Y', hrEmpInfo.getEmpNo());
            sessionBean.updateReadDoneMsg("HrAdvanceRequest", null, 'Y', hrEmpInfo.getEmpNo());
        }
    }

    public void saveRequest(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = null;
        if(cancel){
            if(hrAdvanceRequest.getRespApprove()!=null && hrAdvanceRequest.getRespApprove().equals('Y')){
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ «·≈·€«¡..  „ ≈⁄ „«œ «·⁄ÂœÂ"));
                return;
            }else{
                hrAdvanceRequest.setCancel(new Character('Y'));
                sessionBean.mergeAdvanceRequest(hrAdvanceRequest);
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ ≈·€«¡ «·⁄ÂœÂ »‰Ã«Õ"));
                hrAdvanceRequestList = sessionBean.findEmpAdvanceRequests(hrEmpInfo);
                cancel=false;
                return;
            }
        }
        if (hrAdvanceRequest.getAmount() == null || hrAdvanceRequest.getGuaranteeNo() == null || hrAdvanceRequest.getDeptMngNo() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
            return;
        }

        List<HrEmpMangers> mngList = CashHandler.getEmpManagers().get(hrEmpInfo.getEmpNo());
        int cnt = 0;
        for (HrEmpMangers hrEmpMangers : mngList) {
            if (hrAdvanceRequest.getDeptMngNo().getEmpNo() == hrEmpMangers.getMngNo()) {
                cnt++;
                break;
            }
        }
        if (cnt == 0) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈Œ Ì«— √Õœ «·„⁄ „œÌ‰ ·ÿ·»«  «·„ÊŸ›"));
            return;
        }

        Long chk=sessionBean.chkFundAdvanceRequest(hrEmpInfo.getEmpNo(),hrAdvanceRequest.getId(),hrAdvanceRequest.getGuaranteeNo().getEmpNo(),hrAdvanceRequest.getAmount());

        if(chk==1){
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» √‰ Ì ⁄œÏ «·„ÊŸ› 6 √‘Â— ⁄·Ï «· ⁄ÌÌ‰");
            fc.addMessage(null, fm);
            return;
        }

        if(chk==2){
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ”œ«œ «·⁄ÂœÂ «·”«»ﬁ… ﬁ»· «·Õ’Ê· ⁄·Ï ⁄ÂœÂ ÃœÌœÂ");
            fc.addMessage(null, fm);
            return;
        }

         if(chk==3){
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "«·÷«„‰ Õ’· ⁄·Ï ⁄ÂœÂ Ê·„ Ì”œœÂ« »⁄œ");
            fc.addMessage(null, fm);
            return;
        }

        if(chk==4){
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÿ·» «·⁄Âœ ÌÃ» √‰ ÌﬂÊ‰ ›Ï «·› —… «·„”„ÊÕ »Â« Œ·«· «·‘Â—");
            fc.addMessage(null, fm);
            return;
        }

        if(chk==5){
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", " „  Ã«Ê“ «·„»·€ «·„”„ÊÕ »Â ··⁄ÂœÂ");
            fc.addMessage(null, fm);
            return;
        }

        if(chk==6){
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", " „  Ã«Ê“ «·Õœ «·√ﬁ’Ï ··⁄ÂœÂ »”»» ÊÃÊœ ”·›…");
            fc.addMessage(null, fm);
            return;
        }

        if(chk==7){
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈⁄ „«œ «·⁄ÂœÂ «·”«»ﬁ… √Ê·« ﬁ»· ≈œŒ«· ⁄ÂœÂ ÃœÌœ…");
            fc.addMessage(null, fm);
            return;
        }

        hrAdvanceRequest.setEmpNo(hrEmpInfo);
        hrAdvanceRequest.setTrnsDate(new Date());
        if (cancel) {
            hrAdvanceRequest.setCancel(new Character('Y'));
            cancelMessage(hrAdvanceRequest.getGuaranteeNo().getEmpNo(), 1);
            cancelMessage(hrAdvanceRequest.getDeptMngNo().getEmpNo(), 2);
            cancelMessage(hrAdvanceRequest.getEmpNo().getEmpNo(), 3);
            cancelMessage(hrAdvanceRequest.getFundRespNo().getEmpNo(), 4);
        } else {
            hrAdvanceRequest.setCancel(new Character('N'));
        }
        if (hrAdvanceRequest.getId() == null) {
            sessionBean.persistAdvanceRequest(hrAdvanceRequest);
            sendMessages(hrAdvanceRequest.getGuaranteeNo().getEmpNo(), 1);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «·ÿ·» »‰Ã«Õ");
        } else {
            if (hrAdvanceRequest.getGuaranteeNo().getEmpNo() != copy.getGuaranteeNo().getEmpNo()) {
                hrAdvanceRequest.setGuaranteeApprove(null);
            }
            if (hrAdvanceRequest.getDeptMngNo().getEmpNo() != copy.getDeptMngNo().getEmpNo()) {
                hrAdvanceRequest.setDeptMngApprove(null);
            }
            sessionBean.mergeAdvanceRequest(hrAdvanceRequest);
            if ((copy.getGuaranteeNo() != null && hrAdvanceRequest.getGuaranteeNo() != null && copy.getGuaranteeNo().getEmpNo() != hrAdvanceRequest.getGuaranteeNo().getEmpNo())||
                    (copy.getCancel()!=null && hrAdvanceRequest.getCancel()!=null && !copy.getCancel().equals(hrAdvanceRequest.getCancel()) && hrAdvanceRequest.getCancel().equals(new Character('N')))) {
                cancelMessage(copy.getGuaranteeNo().getEmpNo(), 1);
                sendMessages(hrAdvanceRequest.getGuaranteeNo().getEmpNo(), 1);
            }
            if ((copy.getDeptMngNo() != null && hrAdvanceRequest.getDeptMngNo() != null && copy.getDeptMngNo().getEmpNo() != hrAdvanceRequest.getDeptMngNo().getEmpNo())||
                    (copy.getCancel()!=null && hrAdvanceRequest.getCancel()!=null && !copy.getCancel().equals(hrAdvanceRequest.getCancel()) && hrAdvanceRequest.getCancel().equals(new Character('N')))) {
                cancelMessage(copy.getDeptMngNo().getEmpNo(), 2);
                sendMessages(hrAdvanceRequest.getDeptMngNo().getEmpNo(), 2);
            }
            
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  ⁄œÌ· «·ÿ·» »‰Ã«Õ");
        }

        hrAdvanceRequestList = sessionBean.findEmpAdvanceRequests(hrEmpInfo);
        cancel = false;
        fc.addMessage(null, fm);
    }

    public void cancelMessage(long empNo, int type) {
        if (CashHandler.getAlerts().get(empNo) != null && CashHandler.getAlerts().get(empNo).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(empNo)) {
                if (type == 1 && hrProfileMsg.getEntityName().equals("HrAdvanceRequestGuarantee") && hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId())) {
                    hrProfileMsg.setReadDone('Y');
                    sessionBean.updateReadDoneMsg("HrAdvanceRequestGuarantee", hrAdvanceRequest.getId(), 'Y', null);
                } else if (type == 2 && hrProfileMsg.getEntityName().equals("HrAdvanceRequestDeptMng") && hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId())) {
                    hrProfileMsg.setReadDone('Y');
                    sessionBean.updateReadDoneMsg("HrAdvanceRequestDeptMng", hrAdvanceRequest.getId(), 'Y', null);
                } else if (type == 3 && hrProfileMsg.getEntityName().equals("HrAdvanceRequest") && hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId())) {
                    hrProfileMsg.setReadDone('Y');
                }else if (type == 4 && hrProfileMsg.getEntityName().equals("HrAdvanceRequestResponsible") && hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId())) {
                    hrProfileMsg.setReadDone('Y');
                    sessionBean.updateReadDoneMsg("HrAdvanceRequestResponsible", hrAdvanceRequest.getId(), 'Y', null);
                }
            }
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
            hrProfileMsg.setMsgId(hrAdvanceRequest.getId());

            if (type == 1) {
                hrProfileMsg.setEntityName("HrAdvanceRequestGuarantee");
                hrProfileMsg.setMsgType(1L);
            } else if (type == 2) {
                hrProfileMsg.setEntityName("HrAdvanceRequestDeptMng");
                hrProfileMsg.setMsgType(1L);
            } else if (type == 3) {
                hrProfileMsg.setEntityName("HrAdvanceRequest");
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

    public void rowSelectListner() {
        cancel = selectedRequest.getCancel().equals(new Character('Y'));
        try {
            copy = (HrAdvanceRequest) selectedRequest.clone();
            hrAdvanceRequest = (HrAdvanceRequest) selectedRequest.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AdvanceRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newSearch(ActionEvent ae) {
        hrAdvanceRequest = new HrAdvanceRequest();
    }

    public AdvanceRequest() {
    }

    public HrAdvanceRequest getHrAdvanceRequest() {
        return hrAdvanceRequest;
    }

    public void setHrAdvanceRequest(HrAdvanceRequest hrAdvanceRequest) {
        this.hrAdvanceRequest = hrAdvanceRequest;
        if (hrAdvanceRequest == null) {
            hrAdvanceRequest = new HrAdvanceRequest();
        }
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public List<HrAdvanceRequest> getHrAdvanceRequestList() {
        return hrAdvanceRequestList;
    }

    public void setHrAdvanceRequestList(List<HrAdvanceRequest> hrAdvanceRequestList) {
        this.hrAdvanceRequestList = hrAdvanceRequestList;
    }

    public HrAdvanceRequest getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(HrAdvanceRequest selectedRequest) {
        this.selectedRequest = selectedRequest;
    }
}

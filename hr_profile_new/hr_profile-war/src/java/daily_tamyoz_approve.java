/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrMontlySalaryCalcPeriod;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrTamyozHd;
import e.HrTamyozSecurityTransfer;
import e.HrUsers;
import java.io.Serializable;
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
import javax.faces.model.SelectItem;
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
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class daily_tamyoz_approve implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrTamyozHd> empDailyTamyozApprove = new ArrayList<HrTamyozHd>();
    private HrEmpInfo hrEmpInfo;
    private List<SelectItem> chkApprove1;
    private List<SelectItem> chkApprove2;
    private List<SelectItem> chkApprove3;
    private int flag = 0;
    private String usercode;

    @PostConstruct
    public void init() {
        chkApprove1 = new ArrayList<SelectItem>();
        chkApprove2 = new ArrayList<SelectItem>();
        chkApprove3 = new ArrayList<SelectItem>();
        HrTamyozSecurityTransfer asSecurity = null;
        HrTamyozSecurityTransfer asManager = null;
        HrTamyozSecurityTransfer asTreasury = null;
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        List<HrTamyozSecurityTransfer> hrTamyozSecurityTransfers = sessionBean.findTamyozSecutityTransferPerEmp(Long.parseLong(usercode));
        for (HrTamyozSecurityTransfer hrTamyozSecurityTransfer : hrTamyozSecurityTransfers) {
            if (hrTamyozSecurityTransfer.getApprove().equals(1L)) {
                asSecurity = hrTamyozSecurityTransfer;
            } else if (hrTamyozSecurityTransfer.getApprove().equals(2L)) {
                asManager = hrTamyozSecurityTransfer;
            } else if (hrTamyozSecurityTransfer.getApprove().equals(3L)) {
                asTreasury = hrTamyozSecurityTransfer;
            }
        }
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

        HrMontlySalaryCalcPeriod hscp = CashHandler.hscp;
        Date min_date = hscp.getDateFrom();
        if (hrEmpInfo.getJobId() == 71 || hrEmpInfo.getJobId() == 66) {
            empDailyTamyozApprove = sessionBean.getEmpDailyTamyozApprove1(hrEmpInfo.getLocId(), min_date);
            chkApprove1.add(0, new SelectItem("1"));
        } else if (asSecurity != null) {
            empDailyTamyozApprove = sessionBean.getEmpDailyTamyozApprove1(asSecurity.getLoc().getId(), min_date);
            hrEmpInfo.setLocation(asSecurity.getLoc().getName());
            chkApprove1.add(0, new SelectItem("1"));
        } else if (hrEmpInfo.getJobId() == 14 || hrEmpInfo.getJobId() == 15) {
            empDailyTamyozApprove = sessionBean.getEmpDailyTamyozApprove2(hrEmpInfo.getLocId(), min_date);
            chkApprove2.add(0, new SelectItem("2"));
        } else if (asManager != null) {
            empDailyTamyozApprove = sessionBean.getEmpDailyTamyozApprove2(asManager.getLoc().getId(), min_date);
            hrEmpInfo.setLocation(asManager.getLoc().getName());
            chkApprove2.add(0, new SelectItem("2"));
        } else if (hrEmpInfo.getJobId() == 34 || hrEmpInfo.getJobId() == 18 || hrEmpInfo.getJobId() == 129) {
            empDailyTamyozApprove = sessionBean.getEmpDailyTamyozApprove3(hrEmpInfo.getLocId(), min_date);
            chkApprove3.add(0, new SelectItem("3"));
        } else if (asTreasury != null) {
            empDailyTamyozApprove = sessionBean.getEmpDailyTamyozApprove3(asTreasury.getLoc().getId(), min_date);
            hrEmpInfo.setLocation(asTreasury.getLoc().getName());
            chkApprove3.add(0, new SelectItem("3"));
        }
        
    }

    /** Creates a new instance of daily_tamyoz_approve */
    public daily_tamyoz_approve() {
    }

    public List<HrTamyozHd> getEmpDailyTamyozApprove() {
        return empDailyTamyozApprove;
    }

    public void setEmpDailyTamyozApprove(List<HrTamyozHd> empDailyTamyozApprove) {
        this.empDailyTamyozApprove = empDailyTamyozApprove;
    }

    public void save(ActionEvent ae) {
        if (flag == 0) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("·„ Ì „ «·Õ›Ÿ", "·« ÌÊÃœ  €ÌÌ— ·ﬂÏ ÌÕ›Ÿ"));
            return;
        }
        String selected_row = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx").toString().substring(22, FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx").toString().indexOf(","));
        HrTamyozHd hrTamyozHd = empDailyTamyozApprove.get(Integer.parseInt(selected_row));
        HrTamyozHd hth = sessionBean.getHrTamyozHdById(hrTamyozHd.getId());
        List<String> new_approve = new ArrayList<String>();
        if (hrTamyozHd.getApproved().isEmpty() || (!hth.getApproved().isEmpty() && hrTamyozHd.getApproved().get(0).equals(hth.getApproved().get(0)))) {
            if (!hth.getApproved().isEmpty() && Long.parseLong(hth.getApproved().get(0)) > 1L) {
                List<String> x = new ArrayList<String>();
                x.add(0, String.valueOf(Long.parseLong(hth.getApproved().get(0)) - 1L));
                hrTamyozHd.setApproved(x);
            } else {
                List<String> x = new ArrayList<String>();
                x.add(null);
                hrTamyozHd.setApproved(x);
            }
            if (!hrTamyozHd.getApproved().isEmpty() && Long.parseLong(hrTamyozHd.getApproved().get(0)) == 1L) {
                hrTamyozHd.setManagerApproveDate(null);
                hrTamyozHd.setManagerApproved(null);
            } else if (!hrTamyozHd.getApproved().isEmpty() && Long.parseLong(hrTamyozHd.getApproved().get(0)) == 2L) {
                hrTamyozHd.setTreasuryApproveDate(null);
                hrTamyozHd.setTreasuryApproved(null);
            } else {
                hrTamyozHd.setSecApproveDate(null);
                hrTamyozHd.setSecApproved(null);

            }



            Long y = 0L;
            if (!hth.getApproved().isEmpty() && Long.parseLong(hth.getApproved().get(0)) >= 1L) {
                y = Long.parseLong(hth.getApproved().get(0)) + 1L;
            } else {
                y = 1L;
            }
            if (CashHandler.getTamyozApprove() != null
                    && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()) != null
                    && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(y) != null) {
                for (Long emp : CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(y)) {
                    if (CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(y) != null && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(y).size() > 0) {
                        if (CashHandler.getAlerts().get(emp) != null) {
                            for (HrProfileMsg msg : CashHandler.getAlerts().get(emp)) {
                                if (msg.getMsgId().equals(hrTamyozHd.getId()) && msg.getEntityName().equals("HrTamyozHd")) {
                                    msg.setReadDone('Y');
                                }
                            }
                        }
                    }
                }
                sessionBean.updateReadDoneMsg("HrTamyozHd", hrTamyozHd.getId(), 'Y', null);
            }


        } else {

            if (!hth.getApproved().isEmpty()) {
                new_approve.add(0, String.valueOf(Long.parseLong(hth.getApproved().get(0)) + 1L));
                hrTamyozHd.setApproved(new_approve);
            } else {
                new_approve.add(0, "1");
                hrTamyozHd.setApproved(new_approve);
            }
            if (Long.parseLong(hrTamyozHd.getApproved().get(0)) == 2L) {
                hrTamyozHd.setManagerApproveDate(new Date());
                hrTamyozHd.setManagerApproved(Long.parseLong(usercode));
            } else if (Long.parseLong(hrTamyozHd.getApproved().get(0)) == 3L) {
                hrTamyozHd.setTreasuryApproveDate(new Date());
                hrTamyozHd.setTreasuryApproved(Long.parseLong(usercode));
            } else {
                hrTamyozHd.setSecApproveDate(new Date());
                hrTamyozHd.setSecApproved(Long.parseLong(usercode));
            }



            if (CashHandler.getTamyozApprove() != null
                    && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()) != null
                    && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(Long.parseLong(hrTamyozHd.getApproved().get(0))) != null) {
                for (Long emp : CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(Long.parseLong(hrTamyozHd.getApproved().get(0)))) {
                    if (CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(Long.parseLong(hrTamyozHd.getApproved().get(0))) != null && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(Long.parseLong(hrTamyozHd.getApproved().get(0))).size() > 0) {
                        if (CashHandler.getAlerts().get(emp) != null) {
                            for (HrProfileMsg msg : CashHandler.getAlerts().get(emp)) {
                                if (msg.getMsgId().equals(hrTamyozHd.getId()) && msg.getEntityName().equals("HrTamyozHd")) {
                                    msg.setReadDone('Y');
                                }
                            }
                        }
                    }
                }
                sessionBean.updateReadDoneMsg("HrTamyozHd", hrTamyozHd.getId(), 'Y', null);
            }



            if (CashHandler.getTamyozApprove() != null
                    && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()) != null
                    && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(Long.parseLong(hrTamyozHd.getApproved().get(0)) + 1L) != null
                    && (hrTamyozHd.getApproved().get(0).equals("1") || hrTamyozHd.getApproved().get(0).equals("2"))) {
                System.out.println("inside first if");
                Connection connection=null;
                Session session=null;
                MessageProducer messageProducer=null;
                try {
                    Context ctx = new InitialContext();
                    ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                    Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                    connection = connectionFactory.createConnection();
                    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    List<Long> empApprove = CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(Long.parseLong(hrTamyozHd.getApproved().get(0)) + 1L);
                    System.out.println("empApprove" + empApprove);
                    messageProducer = session.createProducer(queue);
                    for (Long emp : empApprove) {
                        ObjectMessage objectMessage = session.createObjectMessage();
                        HrProfileMsg hrProfileMsg = new HrProfileMsg();
                        hrProfileMsg.setEntityName("HrTamyozHd");
                        hrProfileMsg.setSendDate(new Date());
                        hrProfileMsg.setEmpNo(emp);
                        hrProfileMsg.setSenderNo(Long.parseLong(usercode));
                        hrProfileMsg.setMsgId(hrTamyozHd.getId());
                        hrProfileMsg.setMsgType(1L);
                        hrProfileMsg.setMsgApprove('Y');
                        objectMessage.setObject(hrProfileMsg);
                        messageProducer.send(objectMessage);
                        if (CashHandler.getAlerts().containsKey(emp)) {
                            CashHandler.getAlerts().get(emp).add(hrProfileMsg);
                        } else {
                            List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                            hrProfileMsgs.add(hrProfileMsg);
                            CashHandler.getAlerts().put(emp, hrProfileMsgs);
                        }
                        System.out.println("message sent");
                    }

                } catch (NamingException ex) {
                    ex.printStackTrace();
                } catch (JMSException x) {
                    x.printStackTrace();
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


        }
        sessionBean.mergeHrTamyozHd(hrTamyozHd);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(" „ »‰Ã«Õ", " „ Õ›Ÿ «· „Ì“ »‰Ã«Õ"));
        flag = 0;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

    public List<SelectItem> getChkApprove2() {
        return chkApprove2;
    }

    public void setChkApprove2(List<SelectItem> chkApprove2) {
        this.chkApprove2 = chkApprove2;
    }

    public List<SelectItem> getChkApprove3() {
        return chkApprove3;
    }

    public void setChkApprove3(List<SelectItem> chkApprove3) {
        this.chkApprove3 = chkApprove3;
    }

    public List<SelectItem> getChkApprove1() {
        return chkApprove1;
    }

    public void setChkApprove1(List<SelectItem> chkApprove1) {
        this.chkApprove1 = chkApprove1;
    }
}

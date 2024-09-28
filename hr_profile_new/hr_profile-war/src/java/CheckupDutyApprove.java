/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrCheckupDutyDt;
import e.HrCheckupDutyDt1;
import e.HrCheckupDutyEmp1;
import e.HrCheckupDutyEmployees;
import e.HrCheckupDutyHd;
import e.HrCheckupDutyHd1;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import sb.SessionBeanLocal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.mail.PasswordAuthentication;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class CheckupDutyApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private int mng_summ_flag = 0;
    private int emp_summ_flag = 0;
    private int dept_summ_flag = 0;
    private int positive_summ_flag = 0;
    private int negative_summ_flag = 0;
    private int sugg_summ_flag = 0;
    private String txt;
    private BigDecimal value;
    private Character approve;
    private String inTrns;
    private String outTrns;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm");
    private List<HrCheckupDutyHd1> notApprovedList = new ArrayList<HrCheckupDutyHd1>();
    private HrCheckupDutyHd1 hrCheckupDutyHd = new HrCheckupDutyHd1();
    private HrCheckupDutyDt1 hrCheckupDutyDt = new HrCheckupDutyDt1();
    private String usercode;
    private String rejectReason;

    /** Creates a new instance of checkup_duty_approve */
    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        try {
            notApprovedList = sessionBean.getCheckUpDutiesNotApproved1();
            if (!notApprovedList.isEmpty()) {
                hrCheckupDutyHd = notApprovedList.get(0);
            }
        } catch (NullPointerException e) {
            notApprovedList = new ArrayList<HrCheckupDutyHd1>();
        }

        try {
            value = sessionBean.getCheckUpDutyBonus(hrCheckupDutyHd.getLocId().getId()).getValue();
        } catch (NullPointerException e) {
            value = new BigDecimal(0);
        }
    }

    public String add() {
        /* if (hrCheckupDutyHd.getHrCheckupDutyEmp1List().get(hrCheckupDutyHd.getHrCheckupDutyEmp1List().size() - 1).getEmpId() == null
        || hrCheckupDutyHd.getHrCheckupDutyEmp1List().get(hrCheckupDutyHd.getHrCheckupDutyEmp1List().size() - 1).getEmpId().getEmpNo() == 0L) {
        return null;
        }*/
        HrCheckupDutyEmp1 hrCheckupDutyEmp1 = new HrCheckupDutyEmp1();
        hrCheckupDutyEmp1.setEmpId(new HrEmpInfo());
        hrCheckupDutyEmp1.setHrCheckupDutyHd1(hrCheckupDutyHd);
        System.out.println("is it approved :" + hrCheckupDutyHd.getApproved());
        hrCheckupDutyHd.getHrCheckupDutyEmp1List().add(hrCheckupDutyEmp1);
        return null;
    }

    public List<HrCheckupDutyHd1> getNotApprovedList() {
        return notApprovedList;
    }

    public void setNotApprovedList(List<HrCheckupDutyHd1> notApprovedList) {
        this.notApprovedList = notApprovedList;
    }

    public void save(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (approve != null) {
            hrCheckupDutyHd.setApproved(approve);
            hrCheckupDutyHd.setRejectReason(rejectReason);
            hrCheckupDutyHd.setApproveDate(new Date());
            hrCheckupDutyHd.setMngNo(Long.parseLong(usercode));
            sessionBean.mergeHrCheckUpDutyHd1(hrCheckupDutyHd);
            if (approve.equals(new Character('Y'))) {
                for (HrCheckupDutyEmployees hrCheckupDutyEmployees : hrCheckupDutyHd.getHrCheckupDutyEmployeesList()) {
                    hrCheckupDutyEmployees.setValue(Long.parseLong(value.toString()) / hrCheckupDutyHd.getHrCheckupDutyEmployeesList().size());
                    sessionBean.mergeHrCheckupDutyEmployees(hrCheckupDutyEmployees);
                }
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈⁄ „«œ «· ﬁ—Ì—"));
            } else if (approve.equals(new Character('N'))) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ «· ﬁ—Ì—"));
            }

            notApprovedList = sessionBean.getCheckUpDutiesNotApproved1();

            Long[] mng = {11959L, 11914L};
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();

                for (Long l : mng) {
                    if (jedis.hgetAll("alerts:" + l) != null && !jedis.hgetAll("alerts:" + l).isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + l);
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            try {
                                HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                if (hrProfileMsg.getMsgId().equals(hrCheckupDutyHd.getId()) && hrProfileMsg.getEntityName().equals("HrCheckupDutyHd1")) {
                                    hrProfileMsg.setReadDone('Y');
                                    jedis.hset("alerts:" + l, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + l, Integer.MAX_VALUE);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(checkup_duty_approve.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    sessionBean.updateReadDoneMsg("HrCheckupDutyHd1", hrCheckupDutyHd.getId(), 'Y', null);
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }

            Connection connection = null;
            javax.jms.Session session = null;
            MessageProducer messageProducer = null;
            try {
                Context ctx = new InitialContext();
                ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                connection = connectionFactory.createConnection();
                session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
                messageProducer = session.createProducer(queue);
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("HrCheckupDutyHd1");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrCheckupDutyHd.getEntryNo().getEmpNo());
                hrProfileMsg.setSenderNo(Long.parseLong(usercode));
                hrProfileMsg.setMsgId(hrCheckupDutyHd.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove(hrCheckupDutyHd.getApproved());
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("msgs:" + hrCheckupDutyHd.getEntryNo().getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                        + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("msgs:" + hrCheckupDutyHd.getEntryNo().getEmpNo(), Integer.MAX_VALUE);
                messageProducer.send(objectMessage);
                System.out.println("message sent");
            } catch (IOException ex) {
                Logger.getLogger(CheckupDutyApprove.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(authorize_request.class.getName()).log(Level.SEVERE, null, ex);
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
            approve = null;
            if (!notApprovedList.isEmpty()) {
                hrCheckupDutyHd = notApprovedList.get(0);
            } else {
                hrCheckupDutyHd = new HrCheckupDutyHd1();
            }
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈⁄ „«œ «· ﬁ—Ì— √Ê —›÷Â"));
        }
    }

    public String summary(String s) {

        return null;
    }

    public int getMng_summ_flag() {
        return mng_summ_flag;
    }

    public void setMng_summ_flag(int mng_summ_flag) {
        this.mng_summ_flag = mng_summ_flag;
    }

    public String summarize() {
        hrCheckupDutyDt.setSummaryFlag('Y');
        sessionBean.mergeHrCheckUpDutyDt1(hrCheckupDutyDt);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ ﬂ„·Œ’"));
        return null;
    }

    public String reply() {
        sessionBean.mergeHrCheckUpDutyDt1(hrCheckupDutyDt);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «·—œ"));
        return null;
    }

    public String action() {
        sessionBean.mergeHrCheckUpDutyDt1(hrCheckupDutyDt);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «·„·«ÕŸ…"));
        return null;
    }

    public List<String> complete(String query) {
        List<String> mails = sessionBean.getDeptManagersMails(query);
        System.out.println(txt);
        System.out.println(query);
        txt = null;
        return mails;
    }

    private void send(String smtpHost, int smtpPort,
            String from, String to,
            String subject, String content)
            throws AddressException, MessagingException {

        Authenticator authenticator = new Authenticator();
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, authenticator);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setHeader("Content-Transfer-Encoding", "8Bit");
        System.out.println("to>>>>>" + to);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);
        msg.setContent(content, "text/html; charset=utf-8");


        // Send the message
        Transport.send(msg);
    }

    private class Authenticator extends javax.mail.Authenticator {

        private PasswordAuthentication authentication;

        public Authenticator() {
            String username = "oracle@ccg.com.eg";
            String password = "Or@cle@123";
            authentication = new PasswordAuthentication(username, password);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }

    public String send_mail() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (txt == null || txt.isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈œŒ«· «·≈„Ì·"));
            return null;
        }
        hrCheckupDutyDt.setRedirectFlag('Y');
        hrCheckupDutyDt.setRedirectMail(txt);
        sessionBean.mergeHrCheckUpDutyDt1(hrCheckupDutyDt);
        System.out.println("mail txt>>>>" + txt);
        String e_mail = "<table align='right' style='clear:both;'>";
        e_mail = e_mail + "<tr><td><table align='right'><tr><td style='text-align:right;'>" + hrCheckupDutyHd.getEntryNo().getDeptName()
                + "</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>:</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>«·ﬁ”„ </td></tr>";
        e_mail = e_mail + "<tr><td style='text-align:right;'>" + sdf.format(hrCheckupDutyHd.getTrnsDate()) + "</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>:</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>«· «—ÌŒ </td></tr></table></td></tr>";
        e_mail = e_mail + "<tr><td style='text-align:right'>";
        e_mail = e_mail + hrCheckupDutyDt.getDetails();
        e_mail = e_mail + "</td></tr><tr><td style='text-align:right;color:red;font:bold;font-size:medium;text-decoration:underline'>«·ﬁ«∆„Ì‰ »⁄„· «· ﬁ—Ì—</td></tr>";
        e_mail = e_mail + "<tr><td><table align='right' border='1'><tr><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>«·≈”„</td><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>"
                + "«·ﬂÊœ</td></tr>";
        for (HrCheckupDutyEmployees x : hrCheckupDutyHd.getHrCheckupDutyEmployeesList()) {
            e_mail = e_mail + "<tr><td style='text-align:right'>" + x.getEmpId().getEmpName() + "</td><td style='text-align:right'>" + x.getEmpId().getEmpNo() + "</td></tr>";
        }
        e_mail = e_mail + "</table></td></tr></table>";
        final String a = e_mail;
        final String to = txt;
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", to, " ﬁ—Ì— «·ﬁ”„", a);
                    //  sessionBean.send_mail(body, " ﬁ—Ì— «·ﬁ”„", a);
                } catch (AddressException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·≈—”«· »‰Ã«Õ"));
        txt = null;
        mng_summ_flag = 0;
        emp_summ_flag = 0;
        dept_summ_flag = 0;
        positive_summ_flag = 0;
        negative_summ_flag = 0;
        sugg_summ_flag = 0;
        return null;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public HrCheckupDutyHd1 getHrCheckupDutyHd() {
        if (hrCheckupDutyHd != null && hrCheckupDutyHd.getInTrns() != null && hrCheckupDutyHd.getOutTrns() != null) {
            inTrns = formatTime.format(hrCheckupDutyHd.getInTrns());
            outTrns = formatTime.format(hrCheckupDutyHd.getOutTrns());
            try {
                value = sessionBean.getCheckUpDutyBonus(hrCheckupDutyHd.getLocId().getId()).getValue();
            } catch (NullPointerException e) {
                value = new BigDecimal(0);
            }
        }

        return hrCheckupDutyHd;
    }

    public void setHrCheckupDutyHd(HrCheckupDutyHd1 hrCheckupDutyHd) {

        this.hrCheckupDutyHd = hrCheckupDutyHd;
    }

    public int getEmp_summ_flag() {
        return emp_summ_flag;
    }

    public void setEmp_summ_flag(int emp_summ_flag) {
        this.emp_summ_flag = emp_summ_flag;
    }

    public int getDept_summ_flag() {
        return dept_summ_flag;
    }

    public void setDept_summ_flag(int dept_summ_flag) {
        this.dept_summ_flag = dept_summ_flag;
    }

    public int getNegative_summ_flag() {
        return negative_summ_flag;
    }

    public void setNegative_summ_flag(int negative_summ_flag) {
        this.negative_summ_flag = negative_summ_flag;
    }

    public int getPositive_summ_flag() {
        return positive_summ_flag;
    }

    public void setPositive_summ_flag(int positive_summ_flag) {
        this.positive_summ_flag = positive_summ_flag;
    }

    public int getSugg_summ_flag() {
        return sugg_summ_flag;
    }

    public void setSugg_summ_flag(int sugg_summ_flag) {
        this.sugg_summ_flag = sugg_summ_flag;
    }

    public Character getApprove() {
        return approve;
    }

    public void setApprove(Character approve) {
        this.approve = approve;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getInTrns() {
        return inTrns;
    }

    public void setInTrns(String inTrns) {
        this.inTrns = inTrns;
    }

    public String getOutTrns() {
        return outTrns;
    }

    public void setOutTrns(String outTrns) {
        this.outTrns = outTrns;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public CheckupDutyApprove() {
    }

    public HrCheckupDutyDt1 getHrCheckupDutyDt() {
        return hrCheckupDutyDt;
    }

    public void setHrCheckupDutyDt(HrCheckupDutyDt1 hrCheckupDutyDt) {
        this.hrCheckupDutyDt = hrCheckupDutyDt;
    }
}

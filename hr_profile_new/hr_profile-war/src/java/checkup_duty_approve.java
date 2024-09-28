/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrCheckupDutyDt;
import e.HrCheckupDutyHd;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
import java.math.BigDecimal;
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
import javax.faces.bean.ViewScoped;
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
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class checkup_duty_approve {

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
    private List<HrCheckupDutyHd> notApprovedList = new ArrayList<HrCheckupDutyHd>();
    private HrCheckupDutyHd hrCheckupDutyHd = new HrCheckupDutyHd();
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
            notApprovedList = sessionBean.getCheckUpDutiesNotApproved();
            if (!notApprovedList.isEmpty()) {
                hrCheckupDutyHd = notApprovedList.get(0);
            }
        } catch (NullPointerException e) {
            notApprovedList = new ArrayList<HrCheckupDutyHd>();
        }

        try {
            value = sessionBean.getCheckUpDutyBonus(hrCheckupDutyHd.getLocId().getId()).getValue();
        } catch (NullPointerException e) {
            value = new BigDecimal(0);
        }
    }

    public checkup_duty_approve() {
    }

    public List<HrCheckupDutyHd> getNotApprovedList() {
        return notApprovedList;
    }

    public void setNotApprovedList(List<HrCheckupDutyHd> notApprovedList) {
        this.notApprovedList = notApprovedList;
    }

    public void save(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (approve != null) {
            hrCheckupDutyHd.setApprove(approve);
            hrCheckupDutyHd.setRejectReason(rejectReason);
            sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
            if (approve.equals(new Character('Y'))) {
                for (HrCheckupDutyDt hrCheckupDutyDt : hrCheckupDutyHd.getHrCheckupDutyDtList()) {
                    hrCheckupDutyDt.setValue(Long.parseLong(value.toString()) / hrCheckupDutyHd.getHrCheckupDutyDtList().size());
                    sessionBean.mergeHrCheckupDutyDt(hrCheckupDutyDt);
                }
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈⁄ „«œ «· ﬁ—Ì—"));
            } else if (approve.equals(new Character('N'))) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ «· ﬁ—Ì—"));
            }

            notApprovedList = sessionBean.getCheckUpDutiesNotApproved();
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                Long[] mng = {11959L, 11914L};
                for (Long l : mng) {
                    if (jedis.hgetAll("alerts:" + l) != null && !jedis.hgetAll("alerts:" + l).isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + l);
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            try {
                                HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                if (hrProfileMsg.getMsgId().equals(hrCheckupDutyHd.getId()) && hrProfileMsg.getEntityName().equals("HrCheckupDutyHd")) {
                                    hrProfileMsg.setReadDone('Y');
                                    jedis.hset("alerts:" + l, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + l, Integer.MAX_VALUE);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(checkup_duty_approve.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    sessionBean.updateReadDoneMsg("HrCheckupDutyHd", hrCheckupDutyHd.getId(), 'Y', null);
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
                hrProfileMsg.setEntityName("HrCheckupDutyHd");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrCheckupDutyHd.getEntryId().getEmpNo());
                hrProfileMsg.setSenderNo(Long.parseLong(usercode));
                hrProfileMsg.setMsgId(hrCheckupDutyHd.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove(hrCheckupDutyHd.getApprove());
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("msgs:" + hrCheckupDutyHd.getEntryId().getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                        + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("msgs:" + hrCheckupDutyHd.getEntryId().getEmpNo(), Integer.MAX_VALUE);
                messageProducer.send(objectMessage);
                System.out.println("message sent");
            } catch (IOException ex) {
                Logger.getLogger(checkup_duty_approve.class.getName()).log(Level.SEVERE, null, ex);
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
                hrCheckupDutyHd = new HrCheckupDutyHd();
            }
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈⁄ „«œ «· ﬁ—Ì— √Ê —›÷Â"));
        }
    }

    public String summary(String s) {
        if (s.equals("mng_summ")) {
            hrCheckupDutyHd.setMngMeetSumm(new Character('Y'));
            sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        } else if (s.equals("emp_summ")) {
            hrCheckupDutyHd.setEmpMeetSumm(new Character('Y'));
            sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        } else if (s.equals("dept_summ")) {
            hrCheckupDutyHd.setDeptMeetSumm(new Character('Y'));
            sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        } else if (s.equals("positive_summ")) {
            hrCheckupDutyHd.setPositiveSumm(new Character('Y'));
            sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        } else if (s.equals("negative_summ")) {
            hrCheckupDutyHd.setNegativeSumm(new Character('Y'));
            sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        } else if (s.equals("sugg_summ")) {
            hrCheckupDutyHd.setSuggestionSumm(new Character('Y'));
            sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        }
        return null;
    }

    public String addReply() {
        sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «·—œ"));
        return null;
    }

    public String addNote() {
        sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «·„·«ÕŸ…"));
        return null;
    }

    public int getMng_summ_flag() {
        return mng_summ_flag;
    }

    public void setMng_summ_flag(int mng_summ_flag) {
        this.mng_summ_flag = mng_summ_flag;
    }

    public String redirect(String s) {
        if (s.equals("mng_summ")) {
            if (mng_summ_flag == 0) {
                mng_summ_flag = 1;
            } else {
                mng_summ_flag = 0;
            }
        } else if (s.equals("emp_summ")) {
            if (emp_summ_flag == 0) {
                emp_summ_flag = 1;
            } else {
                emp_summ_flag = 0;
            }
        } else if (s.equals("dept_summ")) {
            if (dept_summ_flag == 0) {
                dept_summ_flag = 1;
            } else {
                dept_summ_flag = 0;
            }
        } else if (s.equals("positive_summ")) {
            if (positive_summ_flag == 0) {
                positive_summ_flag = 1;
            } else {
                positive_summ_flag = 0;
            }
        } else if (s.equals("negative_summ")) {
            if (negative_summ_flag == 0) {
                negative_summ_flag = 1;
            } else {
                negative_summ_flag = 0;
            }
        } else if (s.equals("sugg_summ")) {
            if (sugg_summ_flag == 0) {
                sugg_summ_flag = 1;
            } else {
                sugg_summ_flag = 0;
            }
        }
        txt = null;
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
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setHeader("Content-Transfer-Encoding", "8Bit");
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
            return new javax.mail.PasswordAuthentication("oracle@ccg.com.eg", "Or@cle@123");
        }
    }

    public String send_mail(String s) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (txt == null || txt.isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈œŒ«· «·≈„Ì·"));
            return null;
        }

        String string = "<table align='right' style='clear:both;'>";
        string = string + "<tr><td><table align='right'><tr><td style='text-align:right;'>" + hrCheckupDutyHd.getEntryId().getDeptName()
                + "</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>:</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>«·ﬁ”„ </td></tr>";
        string = string + "<tr><td style='text-align:right;'>" + hrCheckupDutyHd.getLocId().getName() + "</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>:</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>«·„Êﬁ⁄ </td></tr>";
        string = string + "<tr><td style='text-align:right;'>" + sdf.format(hrCheckupDutyHd.getTrnsDate()) + "</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>:</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>«· «—ÌŒ </td></tr></table></td></tr>";
        string = string + "<tr><td style='text-align:right'>";
        if (s.equals("mng_summ")) {
            string = string + hrCheckupDutyHd.getMngMeet();
        } else if (s.equals("emp_summ")) {
            string = hrCheckupDutyHd.getEmpMeet();
        } else if (s.equals("dept_summ")) {
            string = hrCheckupDutyHd.getDeptMeet();
        } else if (s.equals("positive_summ")) {
            string = hrCheckupDutyHd.getPositive();
        } else if (s.equals("negative_summ")) {
            string = hrCheckupDutyHd.getNegative();
        } else if (s.equals("sugg_summ")) {
            string = hrCheckupDutyHd.getSuggestion();
        }
        string = string + "</td></tr><tr><td style='text-align:right;color:red;font:bold;font-size:medium;text-decoration:underline'>«·ﬁ«∆„Ì‰ »«·„√„Ê—Ì…</td></tr>";
        string = string + "<tr><td><table align='right' border='1'><tr><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>«·≈”„</td><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>"
                + "«·ﬂÊœ</td></tr>";
        for (HrCheckupDutyDt x : hrCheckupDutyHd.getHrCheckupDutyDtList()) {
            string = string + "<tr><td style='text-align:right'>" + x.getEmpId().getEmpName() + "</td><td style='text-align:right'>" + x.getEmpId().getEmpNo() + "</td></tr>";
        }
        string = string + "</table></td></tr></table>";
        final String a = string;
        final String to = txt;
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, "oracle@ccg.com.eg", to, " ﬁ—Ì— „—Ê—", a);
//                    sessionBean.send_mail(txt, " ﬁ—Ì— „—Ê—", a);
//                } catch (AddressException ex) {
//                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (MessagingException ex) {
//                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
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

    public HrCheckupDutyHd getHrCheckupDutyHd() {
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

    public void setHrCheckupDutyHd(HrCheckupDutyHd hrCheckupDutyHd) {

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
}

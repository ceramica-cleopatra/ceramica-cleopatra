/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.EmpSuggest;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@RequestScoped
public class complaints {

    @EJB
    private SessionBeanLocal sessionBean;
    private String e;
    private String usercode;
    private HrEmpInfo hrEmpInfo;

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
    }

    /** Creates a new instance of complaints */
    public complaints() {
    }

    private void send(String smtpHost, int smtpPort,
            String from, String to,
            String subject, String content)
            throws AddressException, MessagingException {

        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);
        msg.setContent(content, "text/html; charset=utf-8");


        // Send the message
        Transport.send(msg);
    }

    public String send_mail() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Long id = sessionBean.find_suggest_id();
        EmpSuggest es = new EmpSuggest();
        if (id == null) {
            es.setId(1L);
        } else {
            es.setId(id + 1L);
        }
        es.setEmpNo(Long.parseLong(usercode));
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        es.setSuggestDate(timestamp);
        if (e.length() == 0) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "·„ Ì „ «·≈—”«·", "ÌÃ» ≈œŒ«· «·‘ﬂÊÏ"));
            return null;
        }
        String s = "<div  style='background-color:#FFA500;clear:both;text-align:center;height:3px'></div>";
        s = s + "<div align='left'><h3 color='red'>Sender Details :</h3>";
        s = s + "<h4>" + usercode + "<h4>";
        s = s + "<h4>" + hrEmpInfo.getEmpName() + "</h4>";
        s = s + "<h4>" + hrEmpInfo.getDeptName() + "</h4>";
        s = s + "<h4>" + hrEmpInfo.getJobName() + "</h4></div>";
        es.setSuggestion(e);
        sessionBean.persist_suggestion(es);
        e = e + s;

        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.allam@ccg.com.eg", "‘ﬂÊÏ", e);
                } catch (AddressException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.madbouely@ccg.com.eg", "‘ﬂÊÏ", e);
                } catch (AddressException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "m.dardiry@ccg.com.eg", "‘ﬂÊÏ", e);
                } catch (AddressException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.abbas@ccg.com.eg", "‘ﬂÊÏ", e);
                } catch (AddressException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "mahmoud.mohamed@ccg.com.eg", "‘ﬂÊÏ", e);
                } catch (AddressException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
//        try {
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.allam@ccg.com.eg", "‘ﬂÊÏ", e);
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.madbouely@ccg.com.eg", "‘ﬂÊÏ", e);
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "m.dardiry@ccg.com.eg", "‘ﬂÊÏ", e);
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.abbas@ccg.com.eg", "‘ﬂÊÏ", e);
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "mahmoud.mohamed@ccg.com.eg", "‘ﬂÊÏ", e);
//        } catch (Exception e) {
//        }
        e = null;
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈—”«· «·‘ﬂÊÏ »‰Ã«Õ"));
        return null;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}

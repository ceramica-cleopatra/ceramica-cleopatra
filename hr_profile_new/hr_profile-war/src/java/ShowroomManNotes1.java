/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrManNotesDt;
import e.HrManNotesHd;
import e.HrProfileAccessLog;
import e.HrShowroomManNotes;
import e.HrUsers;
import java.util.ArrayList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.PasswordAuthentication;
import org.primefaces.event.CloseEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class ShowroomManNotes1 {

    @EJB
    private SessionBeanLocal sessionBean;
    private String usercode;
    private HrEmpInfo hrEmpInfo;
    private HrManNotesHd hrManNotesHd;
    private List<HrManNotesHd> hrManNotesHdList = new ArrayList<HrManNotesHd>();
    @ManagedProperty("#{msg}")
    private ResourceBundle bundle;
    private List<Object[]> titleList;
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
        hrManNotesHdList = sessionBean.findHrManNotes(Long.parseLong(usercode));
        hrManNotesHd = new HrManNotesHd();
    }

    public void handleDlgClose(CloseEvent event) {
        System.out.println("dlg closed");
        hrManNotesHd = new HrManNotesHd();
    }

    public void save(ActionEvent ae) {
        if (hrManNotesHd.getId() == null) {
            hrManNotesHd.setTrnsDate(new Date());
            hrManNotesHd.setEmpNo(hrEmpInfo);
            sessionBean.persistHrManNotesHd(hrManNotesHd);
        } else {
            sessionBean.mergeHrManNotesHd(hrManNotesHd);
        }

        hrManNotesHdList = sessionBean.findHrManNotes(Long.parseLong(usercode));
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
    }



    public String sendToApprove() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (hrManNotesHd.getId() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» Õ›Ÿ «· ﬁ—Ì— √Ê·«"));
            return null;
        }
       for(HrManNotesDt hrManNotesDt : hrManNotesHd.getHrManNotesDtList()){
            if(hrManNotesDt.getDetails()==null){
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
                return null;
            }
        }

        
        String s = "<table align='right' style='clear:both;'>";
        s = s + "<tr><td><table align='right'><tr><td style='text-align:right'>" + hrManNotesHd.getEmpNo().getLocation() + "</td><td style='color:darkblue;font:bold;font-size:large;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«·„Êﬁ⁄</td></tr>";
       for(HrManNotesDt hrManNotesDt : hrManNotesHd.getHrManNotesDtList()){
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+hrManNotesDt.getTitle()+"</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrManNotesDt.getDetails() + "</td></tr>";
        }
        s = s + "</table>";
        final String a = s;
/*

        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.allam@ccg.com.eg", " - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " + hrManNotesHd.getEmpNo().getLocation(), a);
                } catch (AddressException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.madbouely@ccg.com.eg"," - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " +  hrManNotesHd.getEmpNo().getLocation(), a);
                } catch (AddressException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "m.dardiry@ccg.com.eg"," - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " +  hrManNotesHd.getEmpNo().getLocation(), a);
                } catch (AddressException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();*/
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.abbas@ccg.com.eg", " - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " +  hrManNotesHd.getEmpNo().getLocation(), a);
                } catch (AddressException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();/*
        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "mahmoud.mohamed@ccg.com.eg", " - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " +  hrManNotesHd.getEmpNo().getLocation(), a);
                } catch (AddressException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();*/

        hrManNotesHd.setDone('Y');
        sessionBean.mergeHrManNotesHd(hrManNotesHd);
        hrManNotesHd = new HrManNotesHd();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈—”«·  ﬁ—Ì— „—«Ã⁄… «·„⁄—÷ »‰Ã«Õ"));


        return null;
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
        Session session = Session.getDefaultInstance(props, authenticator);

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

    public void addNewDutyReport(ActionEvent ae) {
        hrManNotesHd = new HrManNotesHd();
        titleList=sessionBean.findManNotesTitles(hrEmpInfo.getEmpNo());
        List<HrManNotesDt> hrManNotesDts=new ArrayList<HrManNotesDt>();
        for(Object[] title : titleList){
            HrManNotesDt hrManNotesDt=new HrManNotesDt();
            hrManNotesDt.setHrManNotesHd(hrManNotesHd);
            hrManNotesDt.setTitle((String) title[0]);
            hrManNotesDt.setDisplayOrder(Long.parseLong(title[1].toString()));
            hrManNotesDts.add(hrManNotesDt);
        }
        hrManNotesHd.setHrManNotesDtList(hrManNotesDts);
    }

    public HrManNotesHd getHrManNotesHd() {
        return hrManNotesHd;
    }

    public void setHrManNotesHd(HrManNotesHd hrManNotesHd) {
        this.hrManNotesHd = hrManNotesHd;
    }

    public List<HrManNotesHd> getHrManNotesHdList() {
        return hrManNotesHdList;
    }

    public void setHrManNotesHdList(List<HrManNotesHd> hrManNotesHdList) {
        this.hrManNotesHdList = hrManNotesHdList;
    }

    

    public ShowroomManNotes1() {
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    

}

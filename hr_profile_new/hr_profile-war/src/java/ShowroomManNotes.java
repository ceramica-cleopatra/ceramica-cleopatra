/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CloseEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class ShowroomManNotes {

    @EJB
    private SessionBeanLocal sessionBean;
    private String usercode;
    private HrEmpInfo hrEmpInfo;
    private HrShowroomManNotes hrShowroomManNotes;
    private List<HrShowroomManNotes> hrShowroomManNotesList = new ArrayList<HrShowroomManNotes>();
    @ManagedProperty("#{msg}")
    private ResourceBundle bundle;
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
        hrShowroomManNotesList = sessionBean.findHrShowroomManNoteses(Long.parseLong(usercode));
        hrShowroomManNotes = new HrShowroomManNotes();
    }

    public void handleDlgClose(CloseEvent event) {
        System.out.println("dlg closed");
        hrShowroomManNotes = new HrShowroomManNotes();
    }

    public void save(ActionEvent ae) {
        if (hrShowroomManNotes.getId() == null) {
            hrShowroomManNotes.setTrnsDate(new Date());
            hrShowroomManNotes.setManId(hrEmpInfo);
            sessionBean.persistHrShowroomManNotes(hrShowroomManNotes);
        } else {
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        }

        hrShowroomManNotesList = sessionBean.findHrShowroomManNoteses(Long.parseLong(usercode));
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
    }



    public String sendToApprove() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (hrShowroomManNotes.getId() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» Õ›Ÿ «· ﬁ—Ì— √Ê·«"));
            return null;
        }
        if (hrShowroomManNotes.getBasicComponents()==null || hrShowroomManNotes.getCompDept()==null ||
                hrShowroomManNotes.getCheckupDuty()==null || hrShowroomManNotes.getGraphicDept()==null
                || hrShowroomManNotes.getSalesDept()==null || hrShowroomManNotes.getShowDept()==null
                || hrShowroomManNotes.getComplaints()==null 
                || hrShowroomManNotes.getSuggestions()==null || hrShowroomManNotes.getTransDept()==null ||
                hrShowroomManNotes.getBasicComponents().isEmpty() || hrShowroomManNotes.getCompDept().isEmpty() ||
                hrShowroomManNotes.getCheckupDuty().isEmpty() || hrShowroomManNotes.getGraphicDept().isEmpty()
                || hrShowroomManNotes.getSalesDept().isEmpty() || hrShowroomManNotes.getShowDept().isEmpty()
                || hrShowroomManNotes.getComplaints().isEmpty()
                || hrShowroomManNotes.getSuggestions().isEmpty() || hrShowroomManNotes.getTransDept().isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
            return null;
        }

        
        String s = "<table align='right' style='clear:both;'>";
        s = s + "<tr><td><table align='right'><tr><td style='text-align:right'>" + hrShowroomManNotes.getManId().getLocation() + "</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«·„Êﬁ⁄</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+bundle.getString("BasicComponentNotes")+"</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrShowroomManNotes.getBasicComponents() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+bundle.getString("SalesDeptNotes")+"</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrShowroomManNotes.getSalesDept() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+bundle.getString("CompDeptNotes")+"</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrShowroomManNotes.getCompDept() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+bundle.getString("TrnsDeptNotes")+"</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrShowroomManNotes.getTransDept() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+bundle.getString("GraphicDeptNotes")+"</tr>";
        s = s + "<tr><td style='text-align:right'>" + hrShowroomManNotes.getGraphicDept() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+bundle.getString("ShowDeptNotes")+"</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrShowroomManNotes.getShowDept() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+bundle.getString("CheckupDutyNotes")+"</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrShowroomManNotes.getCheckupDuty() + "</td></tr>";
        s = s + "<tr><td style='color:darkgreen;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+bundle.getString("Suggestion")+"</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrShowroomManNotes.getSuggestions() + "</td></tr>";
        s = s + "<tr><td style='color:red;font:bold;font-size:medium;text-align:right;text-decoration:underline'>"+bundle.getString("Complaint")+"</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrShowroomManNotes.getComplaints() + "</td></tr>";
        s = s + "</table>";
        final String a = s;


        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.allam@ccg.com.eg", " - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " + hrShowroomManNotes.getManId().getLocation(), a);
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
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.madbouely@ccg.com.eg"," - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " + hrShowroomManNotes.getManId().getLocation(), a);
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
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "m.dardiry@ccg.com.eg"," - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " + hrShowroomManNotes.getManId().getLocation(), a);
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
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.abbas@ccg.com.eg", " - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " + hrShowroomManNotes.getManId().getLocation(), a);
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
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "mahmoud.mohamed@ccg.com.eg", " - ﬁ—Ì— „—«Ã⁄… «·„⁄—÷- " + hrShowroomManNotes.getManId().getLocation(), a);
                } catch (AddressException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        hrShowroomManNotes.setDone('Y');
        sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        hrShowroomManNotes = new HrShowroomManNotes();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈—”«·  ﬁ—Ì— „—«Ã⁄… «·„⁄—÷ »‰Ã«Õ"));


        return null;
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


    public void addNewDutyReport(ActionEvent ae) {
        hrShowroomManNotes = new HrShowroomManNotes();
    }

    public List<HrShowroomManNotes> getHrShowroomManNotesList() {
        return hrShowroomManNotesList;
    }

    public void setHrShowroomManNotesList(List<HrShowroomManNotes> hrShowroomManNotesList) {
        this.hrShowroomManNotesList = hrShowroomManNotesList;
    }

    public HrShowroomManNotes getHrShowroomManNotes() {
        return hrShowroomManNotes;
    }

    public void setHrShowroomManNotes(HrShowroomManNotes hrShowroomManNotes) {
        this.hrShowroomManNotes = hrShowroomManNotes;
    }

    public ShowroomManNotes() {
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    

}

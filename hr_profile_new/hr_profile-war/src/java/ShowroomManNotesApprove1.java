/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrManNotesDt;
import e.HrManNotesHd;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrShowroomManNotes;
import e.HrUsers;
import java.text.SimpleDateFormat;
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
import sb.SessionBeanLocal;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.mail.PasswordAuthentication;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class ShowroomManNotesApprove1 {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrManNotesHd> hrManNotesHdNotApproved = new ArrayList<HrManNotesHd>();
    private HrManNotesHd hrManNotesHd = new HrManNotesHd();
    private HrManNotesDt hrManNotesDt = new HrManNotesDt();
    private int basic_comp_summ_flag = 0;
    private int sales_dept_summ_flag = 0;
    private int show_dept_summ_flag = 0;
    private int trns_dept_summ_flag = 0;
    private int comp_dept_summ_flag = 0;
    private int graphic_dept_summ_flag = 0;
    private int checkup_summ_flag = 0;
    private int comp_summ_flag = 0;
    private int sugg_summ_flag = 0;
    private volatile String txt;
    private String mail = null;
    private String usercode;
    private String replyTxt;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private HrEmpInfo hrEmpInfo;

    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrManNotesHdNotApproved = sessionBean.findManNotesNotApproved();
        if (!hrManNotesHdNotApproved.isEmpty()) {
            hrManNotesHd = hrManNotesHdNotApproved.get(0);
        }
    }

    public String summarize() {
        hrManNotesDt.setSummaryFlag('Y');
        sessionBean.mergeHrManNotesDt(hrManNotesDt);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ ﬂ„·Œ’"));
        return null;
    }

    public String reply() {
        hrManNotesDt.setReply(replyTxt);
        sessionBean.mergeHrManNotesDt(hrManNotesDt);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «·—œ"));
        replyTxt = null;
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
        Session session = Session.getDefaultInstance(props, authenticator);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
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
        } else {
            mail = txt;
        }

        hrManNotesDt.setRedirectFlag('Y');
        hrManNotesDt.setRedirectMail(txt);
        sessionBean.mergeHrManNotesDt(hrManNotesDt);
        String string = "<table align='right' style='clear:both;'>";
        string = string + "<tr><td><table align='right'><tr><td style='text-align:right;'>" + hrManNotesDt.getHrManNotesHd().getEmpNo().getLocation()
                + "</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>:</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>«·„Êﬁ⁄</td></tr>";
        string = string + "<tr><td style='text-align:right;'>" + sdf.format(hrManNotesDt.getHrManNotesHd().getTrnsDate()) + "</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>:</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>«· «—ÌŒ </td></tr></table></td></tr>";
        string = string + "<tr><td style='text-align:right'>";
        string = string + hrManNotesDt.getDetails();
        string = string + "</td></tr></table>";

        final String a = string;

        new Thread(new Runnable() {

            public void run() {
                try {
                    System.out.println("txt:" + txt);
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", mail, " ﬁ—Ì— „—«Ã⁄… «·„⁄—÷", a);
                } catch (AddressException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·≈—”«· »‰Ã«Õ"));
        basic_comp_summ_flag = 0;
        sales_dept_summ_flag = 0;
        show_dept_summ_flag = 0;
        trns_dept_summ_flag = 0;
        comp_dept_summ_flag = 0;
        graphic_dept_summ_flag = 0;
        checkup_summ_flag = 0;
        comp_summ_flag = 0;
        sugg_summ_flag = 0;
        txt = null;
        return null;
    }

    public void save(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (hrManNotesHd.getApproved() != null) {
            hrManNotesHd.setApproveDate(new Date());
            hrManNotesHd.setMngNo(hrEmpInfo);
            sessionBean.mergeHrManNotesHd(hrManNotesHd);
            if (hrManNotesHd.getApproved().equals(new Character('Y'))) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈⁄ „«œ «· ﬁ—Ì—"));
            } else if (hrManNotesHd.getApproved().equals(new Character('N'))) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ «· ﬁ—Ì—"));
            }
            hrManNotesHdNotApproved = sessionBean.findManNotesNotApproved();

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
                hrProfileMsg.setEntityName("HR_SHOWROOM_MAN_NOTES");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrManNotesHd.getMngNo().getEmpNo());
                hrProfileMsg.setSenderNo(Long.parseLong(usercode));
                hrProfileMsg.setMsgId(hrManNotesHd.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove(hrManNotesHd.getApproved());
                objectMessage.setObject(hrProfileMsg);
                if (CashHandler.getMsgs().containsKey(hrManNotesHd.getMngNo().getEmpNo())) {
                    CashHandler.getMsgs().get(hrManNotesHd.getMngNo().getEmpNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getMsgs().put(hrManNotesHd.getMngNo().getEmpNo(), hrProfileMsgs);
                }
                messageProducer.send(objectMessage);
                System.out.println("message sent");
            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                try {
                    messageProducer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    session.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
            if (!hrManNotesHdNotApproved.isEmpty()) {
                hrManNotesHd = hrManNotesHdNotApproved.get(0);
            } else {
                hrManNotesHd = new HrManNotesHd();
            }
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈⁄ „«œ «· ﬁ—Ì— √Ê —›÷Â"));
        }
    }


    public void handleDlgClose(CloseEvent event) {
        System.out.println("dlg closed");
        replyTxt=null;
        mail = null;
    }

    public int getBasic_comp_summ_flag() {
        return basic_comp_summ_flag;
    }

    public void setBasic_comp_summ_flag(int basic_comp_summ_flag) {
        this.basic_comp_summ_flag = basic_comp_summ_flag;
    }

    public int getCheckup_summ_flag() {
        return checkup_summ_flag;
    }

    public void setCheckup_summ_flag(int checkup_summ_flag) {
        this.checkup_summ_flag = checkup_summ_flag;
    }

    public int getComp_dept_summ_flag() {
        return comp_dept_summ_flag;
    }

    public void setComp_dept_summ_flag(int comp_dept_summ_flag) {
        this.comp_dept_summ_flag = comp_dept_summ_flag;
    }

    public int getComp_summ_flag() {
        return comp_summ_flag;
    }

    public void setComp_summ_flag(int comp_summ_flag) {
        this.comp_summ_flag = comp_summ_flag;
    }

    public int getGraphic_dept_summ_flag() {
        return graphic_dept_summ_flag;
    }

    public void setGraphic_dept_summ_flag(int graphic_dept_summ_flag) {
        this.graphic_dept_summ_flag = graphic_dept_summ_flag;
    }

    public int getSales_dept_summ_flag() {
        return sales_dept_summ_flag;
    }

    public void setSales_dept_summ_flag(int sales_dept_summ_flag) {
        this.sales_dept_summ_flag = sales_dept_summ_flag;
    }

    public int getShow_dept_summ_flag() {
        return show_dept_summ_flag;
    }

    public void setShow_dept_summ_flag(int show_dept_summ_flag) {
        this.show_dept_summ_flag = show_dept_summ_flag;
    }

    public int getSugg_summ_flag() {
        return sugg_summ_flag;
    }

    public void setSugg_summ_flag(int sugg_summ_flag) {
        this.sugg_summ_flag = sugg_summ_flag;
    }

    public int getTrns_dept_summ_flag() {
        return trns_dept_summ_flag;
    }

    public void setTrns_dept_summ_flag(int trns_dept_summ_flag) {
        this.trns_dept_summ_flag = trns_dept_summ_flag;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    /** Creates a new instance of ShowroomManNotesApprove */
    public ShowroomManNotesApprove1() {
    }

    public HrManNotesDt getHrManNotesDt() {
        return hrManNotesDt;
    }

    public void setHrManNotesDt(HrManNotesDt hrManNotesDt) {
        this.hrManNotesDt = hrManNotesDt;
    }

    public HrManNotesHd getHrManNotesHd() {
        return hrManNotesHd;
    }

    public void setHrManNotesHd(HrManNotesHd hrManNotesHd) {
        this.hrManNotesHd = hrManNotesHd;
    }

    public List<HrManNotesHd> getHrManNotesHdNotApproved() {
        return hrManNotesHdNotApproved;
    }

    public void setHrManNotesHdNotApproved(List<HrManNotesHd> hrManNotesHdNotApproved) {
        this.hrManNotesHdNotApproved = hrManNotesHdNotApproved;
    }

    public String getReplyTxt() {
        return replyTxt;
    }

    public void setReplyTxt(String replyTxt) {
        this.replyTxt = replyTxt;
    }


}

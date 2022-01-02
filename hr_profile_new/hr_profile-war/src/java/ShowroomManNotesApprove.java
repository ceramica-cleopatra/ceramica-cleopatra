/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class ShowroomManNotesApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrShowroomManNotes> showroomManNotesNotApproved = new ArrayList<HrShowroomManNotes>();
    private HrShowroomManNotes hrShowroomManNotes = new HrShowroomManNotes();
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
    private String mail=null;
    private String usercode;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @PostConstruct
    public void init() {
        usercode=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        showroomManNotesNotApproved = sessionBean.findHrShowroomManNotesNotApproved();
        if (!showroomManNotesNotApproved.isEmpty()) {
                hrShowroomManNotes = showroomManNotesNotApproved.get(0);
            }
    }

    public String summary(String s) {
        if (s.equals("basicComponent")) {
            hrShowroomManNotes.setSummBasicComponents("Y");
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        } else if (s.equals("salesDept")) {
            hrShowroomManNotes.setSummSalesDept("Y");
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        } else if (s.equals("trnsDept")) {
            hrShowroomManNotes.setSummTrnsDept("Y");
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        } else if (s.equals("showDept")) {
            hrShowroomManNotes.setSummShowDept("Y");
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        } else if (s.equals("checkUpDuty")) {
            hrShowroomManNotes.setSummCheckupDuty("Y");
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        } else if (s.equals("compDept")) {
            hrShowroomManNotes.setSummCompDept("Y");
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        } else if (s.equals("graphicDept")) {
            hrShowroomManNotes.setSummGraphicDept("Y");
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        } else if (s.equals("suggestions")) {
            hrShowroomManNotes.setSummSuggestions("Y");
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        } else if (s.equals("complaints")) {
            hrShowroomManNotes.setSummComplaints("Y");
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        }
        return null;
    }

    public String redirect(String s) {
        if (s.equals("basicComponent")) {
            if (basic_comp_summ_flag == 0) {
                basic_comp_summ_flag = 1;
            } else {
                basic_comp_summ_flag = 0;
            }
        } else if (s.equals("salesDept")) {
            if (sales_dept_summ_flag == 0) {
                sales_dept_summ_flag = 1;
            } else {
                sales_dept_summ_flag = 0;
            }
        } else if (s.equals("trnsDept")) {
            if (trns_dept_summ_flag == 0) {
                trns_dept_summ_flag = 1;
            } else {
                trns_dept_summ_flag = 0;
            }
        } else if (s.equals("showDept")) {
            if (show_dept_summ_flag == 0) {
                show_dept_summ_flag = 1;
            } else {
                show_dept_summ_flag = 0;
            }
        } else if (s.equals("checkUpDuty")) {
            if (checkup_summ_flag == 0) {
                checkup_summ_flag = 1;
            } else {
                checkup_summ_flag = 0;
            }
        } else if (s.equals("compDept")) {
            if (comp_dept_summ_flag == 0) {
                comp_dept_summ_flag = 1;
            } else {
                comp_dept_summ_flag = 0;
            }
        } else if (s.equals("graphicDept")) {
            if (graphic_dept_summ_flag == 0) {
                graphic_dept_summ_flag = 1;
            } else {
                graphic_dept_summ_flag = 0;
            }
        } else if (s.equals("complaints")) {
            if (comp_summ_flag == 0) {
                comp_summ_flag = 1;
            } else {
                comp_summ_flag = 0;
            }
        } else if (s.equals("suggestions")) {
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

    public String addReply(){
        sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈÷«›… «·—œ »‰Ã«Õ"));
        return null;
    }

    public String addNote(){
        sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈÷«›… «·„·«ÕŸ… »‰Ã«Õ"));
        return null;
    }

    private void send(String smtpHost, int smtpPort,
            String from, String to,
            String subject, String content)
            throws AddressException, MessagingException {
        System.out.println("tooooooooo"+to);
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getDefaultInstance(props, null);

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

    public synchronized String send_mail(String s) {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (txt == null || txt.isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈œŒ«· «·≈„Ì·"));
            return null;
        }
        else{System.out.print("rrrrrrr"+txt);
        mail=txt;}
        String string = "<table align='right' style='clear:both;'>";
        string = string + "<tr><td><table align='right'><tr><td style='text-align:right;'>" + hrShowroomManNotes.getManId().getLocation()
                + "</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>:</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>«·„Êﬁ⁄</td></tr>";
        string = string + "<tr><td style='text-align:right;'>" + sdf.format(hrShowroomManNotes.getTrnsDate()) + "</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>:</td><td style='text-align:right;color:red;font:bold;font-size:medium;'>«· «—ÌŒ </td></tr></table></td></tr>";
        string = string + "<tr><td style='text-align:right'>";
        if (s.equals("basicComponent")) {
            string = string + hrShowroomManNotes.getBasicComponents();
        } else if (s.equals("salesDept")) {
            string = hrShowroomManNotes.getSalesDept();
        } else if (s.equals("trnsDept")) {
            string = hrShowroomManNotes.getTransDept();
        } else if (s.equals("showDept")) {
            string = hrShowroomManNotes.getShowDept();
        } else if (s.equals("checkUpDuty")) {
            string = hrShowroomManNotes.getCheckupDuty();
        } else if (s.equals("compDept")) {
            string = hrShowroomManNotes.getCompDept();
        } else if (s.equals("graphicDept")) {
            string = hrShowroomManNotes.getGraphicDept();
        } else if (s.equals("complaints")) {
            string = hrShowroomManNotes.getComplaints();
        } else if (s.equals("suggestions")) {
            string = hrShowroomManNotes.getSuggestions();
        }
        string = string + "</td></tr></table>";

        final String a = string;

        new Thread(new Runnable() {

            public void run() {
                try {System.out.println("txt:"+txt);
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
        if (hrShowroomManNotes.getApproved() != null) {
            sessionBean.mergeHrShowroomManNotes(hrShowroomManNotes);
            if (hrShowroomManNotes.getApproved().equals(new Character('Y'))) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈⁄ „«œ «· ﬁ—Ì—"));
            } else if (hrShowroomManNotes.getApproved().equals(new Character('N'))) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ «· ﬁ—Ì—"));
            }
            showroomManNotesNotApproved = sessionBean.findHrShowroomManNotesNotApproved();

            Connection connection=null;
            javax.jms.Session session=null;
            MessageProducer messageProducer=null;
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
                hrProfileMsg.setEmpNo(hrShowroomManNotes.getManId().getEmpNo());
                hrProfileMsg.setSenderNo(Long.parseLong(usercode));
                hrProfileMsg.setMsgId(hrShowroomManNotes.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove(hrShowroomManNotes.getApproved());
                objectMessage.setObject(hrProfileMsg);
                if (CashHandler.getMsgs().containsKey(hrShowroomManNotes.getManId().getEmpNo())) {
                    CashHandler.getMsgs().get(hrShowroomManNotes.getManId().getEmpNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getMsgs().put(hrShowroomManNotes.getManId().getEmpNo(), hrProfileMsgs);
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
                }catch(Exception ex){
                    ex.printStackTrace();
                }try{
                session.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }try{
                connection.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }
            if (!showroomManNotesNotApproved.isEmpty()) {
                hrShowroomManNotes = showroomManNotesNotApproved.get(0);
            } else {
                hrShowroomManNotes = new HrShowroomManNotes();
            }
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈⁄ „«œ «· ﬁ—Ì— √Ê —›÷Â"));
        }
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
    public ShowroomManNotesApprove() {
    }

    public List<HrShowroomManNotes> getShowroomManNotesNotApproved() {
        return showroomManNotesNotApproved;
    }

    public void setShowroomManNotesNotApproved(List<HrShowroomManNotes> showroomManNotesNotApproved) {
        this.showroomManNotesNotApproved = showroomManNotesNotApproved;
    }

    public HrShowroomManNotes getHrShowroomManNotes() {
        return hrShowroomManNotes;
    }

    public void setHrShowroomManNotes(HrShowroomManNotes hrShowroomManNotes) {
        this.hrShowroomManNotes = hrShowroomManNotes;
    }
}

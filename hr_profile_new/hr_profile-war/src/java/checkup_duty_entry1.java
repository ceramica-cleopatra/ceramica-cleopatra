/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import e.HrCheckupDutyDt;
import e.HrCheckupDutyDt1;
import e.HrCheckupDutyEmployees;
import e.HrCheckupDutyHd;
import e.HrCheckupDutyHd1;
import e.HrEmpInfo;
import e.HrLocation;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.CloseEvent;
import sb.SessionBeanLocal;
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
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class checkup_duty_entry1 {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<SelectItem> duty_locations_list = new ArrayList<SelectItem>();
    private Long brn_id;
    private HrCheckupDutyHd1 hrCheckupDutyHd = new HrCheckupDutyHd1();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private List<HrCheckupDutyHd1> previousCheckpDutyList;
    private String usercode;
    private HrEmpInfo hrEmpInfo;
    private List<Object[]> titleList;
    private Calendar c;

    /** Creates a new instance of checkup_duty_entry */
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
        c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -30);
        previousCheckpDutyList = sessionBean.findCheckUpDutyHdForEmp1(hrEmpInfo, c.getTime());
        for (HrLocation hrLocation : sessionBean.getLocations()) {
            duty_locations_list.add(new SelectItem(hrLocation.getId(), hrLocation.getName()));
        }
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            if (jedis.hgetAll("msgs:" + usercode) != null && !jedis.hgetAll("msgs:" + usercode).isEmpty()) {
                List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("msgs:" + usercode);
                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                    try {
                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                        if (hrProfileMsg.getEntityName().equals("HrCheckupDutyHd1")) {
                            hrProfileMsg.setReadDone('Y');
                            jedis.hset("msgs:" + usercode, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("msgs:" + usercode, Integer.MAX_VALUE);
                            hrProfileMsgs.add(hrProfileMsg);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(checkup_duty_entry1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sessionBean.mergeHrProfileMsgList(hrProfileMsgs);
            }
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
    }

    public String add() {
        if (hrCheckupDutyHd.getHrCheckupDutyEmployeesList().get(hrCheckupDutyHd.getHrCheckupDutyEmployeesList().size() - 1).getEmpId() == null
                || hrCheckupDutyHd.getHrCheckupDutyEmployeesList().get(hrCheckupDutyHd.getHrCheckupDutyEmployeesList().size() - 1).getEmpId().getEmpNo() == 0L) {
            return null;
        }
        HrCheckupDutyEmployees hrCheckupDutyEmployees = new HrCheckupDutyEmployees();
        hrCheckupDutyEmployees.setEmpId(new HrEmpInfo());
        hrCheckupDutyEmployees.setHrCheckupDutyHd1(hrCheckupDutyHd);
        System.out.println("is it approved :" + hrCheckupDutyHd.getApproved());
        hrCheckupDutyHd.getHrCheckupDutyEmployeesList().add(hrCheckupDutyEmployees);
        return null;
    }

    public void onRowSelect() {
        if (hrCheckupDutyHd.getHrCheckupDutyEmployeesList().isEmpty()) {
            System.out.println("empty list");
            HrCheckupDutyEmployees hrCheckupDutyEmployees = new HrCheckupDutyEmployees();
            hrCheckupDutyEmployees.setHrCheckupDutyHd1(hrCheckupDutyHd);
            hrCheckupDutyEmployees.setEmpId(new HrEmpInfo());
            hrCheckupDutyHd.getHrCheckupDutyEmployeesList().add(hrCheckupDutyEmployees);
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        System.out.println("old value:" + event.getOldValue());
        System.out.println("new value:" + event.getNewValue());
        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void handleDlgClose(CloseEvent event) {
        hrCheckupDutyHd = new HrCheckupDutyHd1();
    }

    public void addNewDutyReport(ActionEvent ae) {
        brn_id = null;
        hrCheckupDutyHd = new HrCheckupDutyHd1();
        titleList = sessionBean.findCheckupDutyTitles(hrEmpInfo.getEmpNo());
        List<HrCheckupDutyDt1> hrCheckupDutyDt1s = new ArrayList<HrCheckupDutyDt1>();
        for (Object[] title : titleList) {
            HrCheckupDutyDt1 hrCheckupDutyDt = new HrCheckupDutyDt1();
            hrCheckupDutyDt.setHrCheckupDutyHd1(hrCheckupDutyHd);
            hrCheckupDutyDt.setTitle((String) title[0]);
            hrCheckupDutyDt.setDisplayOrder(Long.parseLong(title[1].toString()));
            hrCheckupDutyDt1s.add(hrCheckupDutyDt);
        }
        hrCheckupDutyHd.setHrCheckupDutyDt1List(hrCheckupDutyDt1s);
        HrCheckupDutyEmployees hrCheckupDutyEmployees = new HrCheckupDutyEmployees();
        hrCheckupDutyEmployees.setEmpId(new HrEmpInfo());
        hrCheckupDutyEmployees.setHrCheckupDutyHd1(hrCheckupDutyHd);
        List<HrCheckupDutyEmployees> hrCheckupDutyEmployeesList = new ArrayList<HrCheckupDutyEmployees>();
        hrCheckupDutyEmployeesList.add(hrCheckupDutyEmployees);
        hrCheckupDutyHd.setHrCheckupDutyEmployeesList(hrCheckupDutyEmployeesList);
    }

    public void save(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        List<HrCheckupDutyEmployees> filteredList = new ArrayList<HrCheckupDutyEmployees>();
        if (hrCheckupDutyHd.getTrnsDate() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «· «—ÌŒ"));
            return;
        }
//        int i = 0;
//        for (HrCheckupDutyEmployees y : filteredList) {
//
//            if (y.getEmpId() == null || y.getEmpId().getEmpNo() == 0L) {
//                //hrCheckupDutyHd.getHrCheckupDutyDtList().remove(y);
//                System.out.println("removed" + y.getEmpId().getEmpName());
//            }
//            System.out.println("filter" + y.getEmpId().getEmpName());
//        }
//        filteredList.clear();
//        List<HrCheckupDutyEmployees> needToRemove = new ArrayList<HrCheckupDutyEmployees>();
//        for (HrCheckupDutyEmployees y : filteredList) {
//            int cnt = 0;
//
//            for (HrCheckupDutyEmployees x : hrCheckupDutyHd.getHrCheckupDutyEmployeesList()) {
//                if (y.getEmpId().getEmpNo() == x.getEmpId().getEmpNo()) {
//                    {
//                        cnt++;
//                        if (cnt > 1) {
//                            needToRemove.add(x);
//                        }
//
//                    }
//                }
//            }
//        }
//        hrCheckupDutyHd.getHrCheckupDutyEmployeesList().removeAll(needToRemove);
//        filteredList.clear();
//        filteredList.addAll(hrCheckupDutyHd.getHrCheckupDutyEmployeesList());
//        for (HrCheckupDutyEmployees x : hrCheckupDutyHd.getHrCheckupDutyEmployeesList()) {
//            if (x.getId() != null && x.getId() != 0L) {
//                sessionBean.removeHrCheckupDutyEmployees(x);
//            }
//        }
//        hrCheckupDutyHd.setHrCheckupDutyEmployeesList(filteredList);

        if (hrCheckupDutyHd.getTrnsDate() == null || hrCheckupDutyHd.getHrCheckupDutyEmployeesList().get(0).getEmpId() == null
                || hrCheckupDutyHd.getHrCheckupDutyDt1List() == null || hrCheckupDutyHd.getHrCheckupDutyDt1List().isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
            return;
        }


        if (hrCheckupDutyHd.getHrCheckupDutyEmployeesList() == null || hrCheckupDutyHd.getHrCheckupDutyEmployeesList().size() == 0
                || hrCheckupDutyHd.getHrCheckupDutyEmployeesList().get(0).getEmpId() == null || hrCheckupDutyHd.getHrCheckupDutyEmployeesList().get(0).getEmpId().getEmpNo() == 0) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· „ÊŸ› Ê«Õœ ⁄·Ï «·√ﬁ·"));
            return;
        }


//        for(HrCheckupDutyDt1 hrCheckupDutyDt : hrCheckupDutyHd.getHrCheckupDutyDt1List()){
//            if(hrCheckupDutyDt.getDetails()==null){
//                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
//                return ;
//            }
//        }

        hrCheckupDutyHd.setEntryNo(hrEmpInfo);
        hrCheckupDutyHd.setEntryDate(new Date());
        if (hrCheckupDutyHd.getId() == null) {
            sessionBean.persistHrCheckUpDutyHd1(hrCheckupDutyHd);
        } else {
            sessionBean.mergeHrCheckUpDutyHd1(hrCheckupDutyHd);
        }

        previousCheckpDutyList = sessionBean.findCheckUpDutyHdForEmp1(hrEmpInfo, c.getTime());
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
    }

    public String sendToApprove() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (hrCheckupDutyHd.getId() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» Õ›Ÿ «· ﬁ—Ì— √Ê·«"));
            return null;
        }
        if (hrCheckupDutyHd.getHrCheckupDutyEmployeesList() == null || hrCheckupDutyHd.getHrCheckupDutyEmployeesList().size() == 0
                || hrCheckupDutyHd.getHrCheckupDutyEmployeesList().get(0).getEmpId() == null || hrCheckupDutyHd.getHrCheckupDutyEmployeesList().get(0).getEmpId().getEmpNo() == 0) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· „ÊŸ› Ê«Õœ ⁄·Ï «·√ﬁ·"));
            return null;
        }

        int i = 0;
        for (HrCheckupDutyDt1 hrCheckupDutyDt : hrCheckupDutyHd.getHrCheckupDutyDt1List()) {
            if (hrCheckupDutyDt.getDetails() == null || hrCheckupDutyDt.getDetails().length() == 0) {
                i++;
                break;
            }
        }
        if (i > 0) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
            return null;
        }


        String s = "<table align='right' style='clear:both;'>";
        s = s + "<tr><td><table align='right'><tr><td style='text-align:right'>" + hrCheckupDutyHd.getEntryNo().getDeptName() + "</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«·ﬁ”„ </td></tr>";
        s = s + "<tr><td style='text-align:right'>" + sdf.format(hrCheckupDutyHd.getTrnsDate()) + "</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«· «—ÌŒ </td></tr></table></td></tr>";
        for (HrCheckupDutyDt1 hrCheckupDutyDt : hrCheckupDutyHd.getHrCheckupDutyDt1List()) {
            s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>" + hrCheckupDutyDt.getTitle() + "</td></tr>";
            s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyDt.getDetails() + "</td></tr>";
        }
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>«·„ÊŸ›Ì‰ «·ﬁ«∆„Ì‰ »«· ﬁ—Ì—</td></tr>";
        s = s + "<tr><td><table align='right' border='1'><tr><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>«·≈”„</td><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>«·ﬂÊœ</td></tr>";
        for (HrCheckupDutyEmployees x : hrCheckupDutyHd.getHrCheckupDutyEmployeesList()) {

            s = s + "<tr><td style='text-align:right'>" + x.getEmpId().getEmpName() + "</td><td style='text-align:right'>" + x.getEmpId().getEmpNo() + "</td></tr>";

        }
        s = s + "</table>";
        final String a = s;

        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.allam@ccg.com.eg", " -  ﬁ—Ì— «·ﬁ”„ - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
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
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.madbouely@ccg.com.eg", " -  ﬁ—Ì— «·ﬁ”„ - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
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
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "m.dardiry@ccg.com.eg", " -  ﬁ—Ì— «·ﬁ”„ - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
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
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.abbas@ccg.com.eg", " -  ﬁ—Ì— «·ﬁ”„ - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
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
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "mahmoud.mohamed@ccg.com.eg", " -  ﬁ—Ì— «·ﬁ”„ - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
                } catch (AddressException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        Connection connection = null;
        javax.jms.Session session = null;
        MessageProducer messageProducer = null;
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
            Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            Long[] mng = {11959L, 11914L};
            for (Long l : mng) {
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("HrCheckupDutyHd1");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(l);
                hrProfileMsg.setSenderNo(hrCheckupDutyHd.getEntryNo().getEmpNo());
                hrProfileMsg.setMsgId(hrCheckupDutyHd.getId());
                hrProfileMsg.setMsgType(1L);
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("alerts:" + l, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("alerts:" + l, Integer.MAX_VALUE);
                messageProducer.send(objectMessage);
            }
            System.out.println("message sent");

        } catch (IOException ex) {
            Logger.getLogger(checkup_duty_entry1.class.getName()).log(Level.SEVERE, null, ex);
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

            if (jedis != null) {
                jedis.close();

            }
        }
        hrCheckupDutyHd.setDone('Y');
        sessionBean.mergeHrCheckUpDutyHd1(hrCheckupDutyHd);
        brn_id = null;
        hrCheckupDutyHd = new HrCheckupDutyHd1();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈—”«·  ﬁ—Ì— «·ﬁ”„  ··≈⁄ „«œ"));


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
        Session session = Session.getInstance(props, authenticator);

        // Construct the message
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        msg.setHeader("Content-Transfer-Encoding", "8Bit");
        msg.setSubject(subject, "UTF-8");
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

    public List<HrCheckupDutyHd1> getPreviousCheckpDutyList() {
        return previousCheckpDutyList;
    }

    public void setPreviousCheckpDutyList(List<HrCheckupDutyHd1> previousCheckpDutyList) {
        this.previousCheckpDutyList = previousCheckpDutyList;
    }

    public checkup_duty_entry1() {
    }

    public Long getBrn_id() {
        return brn_id;
    }

    public void setBrn_id(Long brn_id) {
        this.brn_id = brn_id;
    }

    public List<SelectItem> getDuty_locations_list() {
        return duty_locations_list;
    }

    public void setDuty_locations_list(List<SelectItem> duty_locations_list) {
        this.duty_locations_list = duty_locations_list;
    }

    public HrCheckupDutyHd1 getHrCheckupDutyHd() {
        return hrCheckupDutyHd;
    }

    public void setHrCheckupDutyHd(HrCheckupDutyHd1 hrCheckupDutyHd) {
        if (hrCheckupDutyHd != null && hrCheckupDutyHd.getLocId() != null) {
            brn_id = hrCheckupDutyHd.getLocId().getId();
        }
        this.hrCheckupDutyHd = hrCheckupDutyHd;
    }
}

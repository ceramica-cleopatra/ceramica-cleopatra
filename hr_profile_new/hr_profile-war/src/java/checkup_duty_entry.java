/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrCheckupDutyDt;
import e.HrCheckupDutyHd;
import e.HrEmpInfo;
import e.HrLocation;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
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
import javax.faces.event.BehaviorEvent;
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
public class checkup_duty_entry {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<SelectItem> duty_locations_list = new ArrayList<SelectItem>();
    private Long brn_id;
    private HrCheckupDutyHd hrCheckupDutyHd = new HrCheckupDutyHd();
    //private List<HrCheckupDutyDt> hrCheckupDutyDtList;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private List<HrCheckupDutyHd> previousCheckpDutyList;
    private String usercode;
    private HrEmpInfo hrEmpInfo;
    private Calendar c;
    private List<HrEmpInfo> driversList;
    private HrEmpInfo selectedDriver;
    private HrEmpInfo filteredDrviver;
    private Integer driverRate;

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
        previousCheckpDutyList = sessionBean.findCheckUpDutyHdForEmp(hrEmpInfo, c.getTime());
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
                        if (hrProfileMsg.getEntityName().equals("HrCheckupDutyHd")) {
                            hrProfileMsg.setReadDone('Y');
                            hrProfileMsgs.add(hrProfileMsg);
                            jedis.hset("msgs:" + usercode, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("msgs:" + usercode, Integer.MAX_VALUE);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sessionBean.mergeHrProfileMsgList(hrProfileMsgs);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        driversList = sessionBean.findDriversList(127L);
        filteredDrviver = new HrEmpInfo();
        selectedDriver = new HrEmpInfo();
        driverRate = 0;
    }

    public void onRowSelect() {
        if (hrCheckupDutyHd.getHrCheckupDutyDtList().isEmpty()) {
            System.out.println("empty list");
            HrCheckupDutyDt hrCheckupDutyDt = new HrCheckupDutyDt();
            hrCheckupDutyDt.setHrCheckupDutyHd(hrCheckupDutyHd);
            hrCheckupDutyDt.setEmpId(new HrEmpInfo());
            hrCheckupDutyHd.getHrCheckupDutyDtList().add(hrCheckupDutyDt);
        }
        if (hrCheckupDutyHd.getDriverNo() != null && hrCheckupDutyHd.getDriverNo() != 0) {
            filteredDrviver = sessionBean.finduserbyid(hrCheckupDutyHd.getDriverNo());
            driverRate = hrCheckupDutyHd.getDriverRate();
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
        System.out.println("dlg closed");
        hrCheckupDutyHd = new HrCheckupDutyHd();
    }

    public void addNewDutyReport(ActionEvent ae) {
        brn_id = null;
        hrCheckupDutyHd = new HrCheckupDutyHd();
        filteredDrviver = new HrEmpInfo();
        selectedDriver = new HrEmpInfo();
        HrCheckupDutyDt x = new HrCheckupDutyDt();
        x.setEmpId(new HrEmpInfo());
        x.setHrCheckupDutyHd(hrCheckupDutyHd);
        driverRate = 0;
        List<HrCheckupDutyDt> hrCheckupDutyDtList = new ArrayList<HrCheckupDutyDt>();
        hrCheckupDutyDtList.add(x);
        hrCheckupDutyHd.setHrCheckupDutyDtList(hrCheckupDutyDtList);
    }

    public String add() {
        if (hrCheckupDutyHd.getHrCheckupDutyDtList().get(hrCheckupDutyHd.getHrCheckupDutyDtList().size() - 1).getEmpId() == null
                || hrCheckupDutyHd.getHrCheckupDutyDtList().get(hrCheckupDutyHd.getHrCheckupDutyDtList().size() - 1).getEmpId().getEmpNo() == 0L) {
            return null;
        }
        HrCheckupDutyDt hrCheckupDutyDt = new HrCheckupDutyDt();
        hrCheckupDutyDt.setEmpId(new HrEmpInfo());
        hrCheckupDutyDt.setHrCheckupDutyHd(hrCheckupDutyHd);
        System.out.println("is it approved :" + hrCheckupDutyHd.getApprove());
        hrCheckupDutyHd.getHrCheckupDutyDtList().add(hrCheckupDutyDt);
        return null;
    }

    public void save(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        List<HrCheckupDutyDt> filteredList = new ArrayList<HrCheckupDutyDt>();
        if (hrCheckupDutyHd.getTrnsDate() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «· «—ÌŒ"));
            return;
        }
        filteredList.addAll(hrCheckupDutyHd.getHrCheckupDutyDtList());
        int i = 0;
        for (HrCheckupDutyDt y : filteredList) {

            if (y.getEmpId() == null || y.getEmpId().getEmpNo() == 0L) {
                hrCheckupDutyHd.getHrCheckupDutyDtList().remove(y);
                System.out.println("removed" + y.getEmpId().getEmpName());
            }
            System.out.println("filter" + y.getEmpId().getEmpName());
        }
        filteredList.clear();
        filteredList.addAll(hrCheckupDutyHd.getHrCheckupDutyDtList());
        List<HrCheckupDutyDt> needToRemove = new ArrayList<HrCheckupDutyDt>();
        for (HrCheckupDutyDt y : filteredList) {
            int cnt = 0;

            for (HrCheckupDutyDt x : hrCheckupDutyHd.getHrCheckupDutyDtList()) {
                if (y.getEmpId().getEmpNo() == x.getEmpId().getEmpNo()) {
                    {
                        cnt++;
                        if (cnt > 1) {
                            needToRemove.add(x);
                        }

                    }
                }
            }
        }
        hrCheckupDutyHd.getHrCheckupDutyDtList().removeAll(needToRemove);
        filteredList.clear();
        filteredList.addAll(hrCheckupDutyHd.getHrCheckupDutyDtList());
        for (HrCheckupDutyDt x : hrCheckupDutyHd.getHrCheckupDutyDtList()) {
            if (x.getId() != null && x.getId() != 0L) {
                sessionBean.removeHrCheckDutyDt(x);
            }
        }
        hrCheckupDutyHd.setHrCheckupDutyDtList(filteredList);
        hrCheckupDutyHd.setEntryId(sessionBean.finduserbyid(Long.parseLong(usercode)));
        hrCheckupDutyHd.setEntryDate(new Date());
        hrCheckupDutyHd.setLocId(sessionBean.getLocationById(brn_id));
        if (filteredDrviver != null && filteredDrviver.getEmpNo() != 0) {
            hrCheckupDutyHd.setDriverNo(filteredDrviver.getEmpNo());
        }
        hrCheckupDutyHd.setDriverRate(driverRate);
        if (hrCheckupDutyHd.getId() == null) {
            sessionBean.persist_checkup_duty(hrCheckupDutyHd);
        } else {
            sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        }
        previousCheckpDutyList = sessionBean.findCheckUpDutyHdForEmp(hrEmpInfo, c.getTime());
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
    }

    public String sendToApprove() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (hrCheckupDutyHd.getId() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» Õ›Ÿ «· ﬁ—Ì— √Ê·«"));
            return null;
        }
        if (hrCheckupDutyHd.getTrnsDate() == null || brn_id == null || hrCheckupDutyHd.getDeptMeet().length() == 0 || hrCheckupDutyHd.getEmpMeet().length() == 0
                || hrCheckupDutyHd.getMngMeet().length() == 0 || hrCheckupDutyHd.getNegative().length() == 0
                || hrCheckupDutyHd.getPositive().length() == 0 || hrCheckupDutyHd.getSuggestion().length() == 0
                || hrCheckupDutyHd.getHrCheckupDutyDtList().get(0).getEmpId() == null
                || hrCheckupDutyHd.getHrCheckupDutyDtList().get(0).getEmpId().getEmpNo() == 0L
                || hrCheckupDutyHd.getOutTrns() == null || hrCheckupDutyHd.getInTrns() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
            return null;
        }
        /*
        if (new Date().getTime() - hrCheckupDutyHd.getTrnsDate().getTime() > 345600000) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ≈—”«·  ﬁ—Ì— ··≈⁄ „«œ »⁄œ „—Ê— 4 √Ì«„ ⁄·Ï «·„√„Ê—Ì…"));
        return null;
        }
         */
        Long chk = sessionBean.chkCheckupDuty(hrCheckupDutyHd.getEntryId().getEmpNo(), hrCheckupDutyHd.getTrnsDate(), hrCheckupDutyHd.getId(), hrCheckupDutyHd.getLocId().getId(), hrCheckupDutyHd.getEntryId().getDeptId());

        if (chk == 1) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ≈œŒ«·  ﬁ—Ì— „—Ê— ·Â–« «·„Êﬁ⁄ „‰ ﬁ»· Œ·«· Â–« «·‘Â—"));
            return null;
        }

        if (chk == 2) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ≈œŒ«· √ﬂÀ— „‰ 7 „Ê«ﬁ⁄ œ«Œ·Ì… Ê 7 „Ê«ﬁ⁄ Œ«—ÃÌ… ··ﬁ”„ Œ·«· «·‘Â—"));
            return null;
        }

        if (chk == 3) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  Ã«Ê“ «·Õœ «·√ﬁ’Ï «·„”„ÊÕ »Â ·≈œŒ«·  ﬁ«—Ì— „—Ê— ·Â–« «·‘Â—"));
            return null;
        }

        if (chk == 4) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  ŒÿÏ «·„œ… «·“„‰Ì… «·„”„ÊÕ »Â« ·≈œŒ«·  ﬁ—Ì— «·„—Ê—"));
            return null;
        }

        if (hrCheckupDutyHd.getTrnsType().equals(1L) && (hrCheckupDutyHd.getDriverNo() == null || hrCheckupDutyHd.getDriverNo() == 0 || hrCheckupDutyHd.getDriverRate() == null || hrCheckupDutyHd.getDriverRate() == 0)) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «·”«∆ﬁ Ê ﬁÌÌ„Â"));
            return null;
        }

        String s = "<table align='right' style='clear:both;'>";
        s = s + "<tr><td><table align='right'><tr><td style='text-align:right'>" + hrCheckupDutyHd.getEntryId().getDeptName() + "</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«·ﬁ”„ </td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyHd.getLocId().getName() + "</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«·„Êﬁ⁄ </td></tr>";
        s = s + "<tr><td style='text-align:right'>" + sdf.format(hrCheckupDutyHd.getTrnsDate()) + "</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«· «—ÌŒ </td></tr></table></td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>«·≈Ã „«⁄ „⁄ „œÌ— «·„Êﬁ⁄</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyHd.getMngMeet() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>«·≈Ã „«⁄ „⁄ √Õœ √Ê«∆· «·„Êﬁ⁄</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyHd.getEmpMeet() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>«·≈Ã „«⁄ „⁄ „”∆Ê·Ï ﬁ”„ﬂ</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyHd.getDeptMeet() + "</td></tr>";
        s = s + "<tr><td style='color:darkgreen;font:bold;font-size:medium;text-align:right;text-decoration:underline'>√ÌÃ«»Ì«  œ«Œ· «·„Êﬁ⁄</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyHd.getPositive() + "</td></tr>";
        s = s + "<tr><td style='color:red;font:bold;font-size:medium;text-align:right;text-decoration:underline'>”·»Ì«  œ«Œ· «·„Êﬁ⁄</td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyHd.getNegative() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'> Ê’Ì«  Ê ≈ﬁ —«Õ« </td></tr>";
        s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyHd.getSuggestion() + "</td></tr>";
        s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>«·„ÊŸ›Ì‰ «·ﬁ«∆„Ì‰ »«·„—Ê—</td></tr>";
        s = s + "<tr><td><table align='right' border='1'><tr><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>«·≈”„</td><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>«·ﬂÊœ</td></tr>";
        for (HrCheckupDutyDt x : hrCheckupDutyHd.getHrCheckupDutyDtList()) {

            s = s + "<tr><td style='text-align:right'>" + x.getEmpId().getEmpName() + "</td><td style='text-align:right'>" + x.getEmpId().getEmpNo() + "</td></tr>";

        }
        s = s + "</table>";
        final String a = s;
//        try {
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.allam@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), s);
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.madbouely@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), s);
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "m.dardiry@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), s);
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.abbas@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), s);
//            send("20.1.1.21", 25, " oracle@ccg.com.eg", "mahmoud.mohamed@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), s);

        new Thread(new Runnable() {

            public void run() {
                try {
                    send("20.1.1.21", 25, " oracle@ccg.com.eg", "programming.dep@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), a);
                } catch (AddressException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

//        new Thread(new Runnable() {
//        public void run() {
//        try {
//        send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.madbouely@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), a);
//        } catch (AddressException ex) {
//        ex.printStackTrace();
//        } catch (MessagingException ex) {
//        ex.printStackTrace();
//        }
//        }
//        }).start();
//
//        new Thread(new Runnable() {
//        public void run() {
//        try {
//        send("20.1.1.21", 25, " oracle@ccg.com.eg", "m.dardiry@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), a);
//        } catch (AddressException ex) {
//        ex.printStackTrace();
//        } catch (MessagingException ex) {
//        ex.printStackTrace();
//        }
//        }
//        }).start();
//
//
//        new Thread(new Runnable() {
//
//            public void run() {
//                try {
//                    send("mail.ccg.com.eg", 587, "oracle@ccg.com.eg", "a.abbas@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), a);
//                } catch (AddressException ex) {
//                    ex.printStackTrace();
//                } catch (MessagingException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//        public void run() {
//        try {
//        send("20.1.1.21", 25, " oracle@ccg.com.eg", "mahmoud.mohamed@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryId().getDeptName(), a);
//        } catch (AddressException ex) {
//        ex.printStackTrace();
//        } catch (MessagingException ex) {
//        ex.printStackTrace();
//        }
//        }
//        }).start();

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
                hrProfileMsg.setEntityName("HrCheckupDutyHd");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(l);
                hrProfileMsg.setSenderNo(hrCheckupDutyHd.getEntryId().getEmpNo());
                hrProfileMsg.setMsgId(hrCheckupDutyHd.getId());
                hrProfileMsg.setMsgType(1L);
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("alerts:" + l, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("alerts:" + l, Integer.MAX_VALUE);
                messageProducer.send(objectMessage);
            }
            System.out.println("message sent");

        } catch (IOException ex) {
            Logger.getLogger(checkup_duty_entry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(authorize_request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
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
        hrCheckupDutyHd.setDone('Y');
        sessionBean.mergeHrCheckUpDutyHd(hrCheckupDutyHd);
        brn_id = null;
        hrCheckupDutyHd = new HrCheckupDutyHd();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈—”«·  ﬁ—Ì— «·„—Ê— ··≈⁄ „«œ"));


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

    public List<HrCheckupDutyHd> getPreviousCheckpDutyList() {
        return previousCheckpDutyList;
    }

    public void setPreviousCheckpDutyList(List<HrCheckupDutyHd> previousCheckpDutyList) {
        this.previousCheckpDutyList = previousCheckpDutyList;
    }

    public checkup_duty_entry() {
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

    public HrCheckupDutyHd getHrCheckupDutyHd() {
        return hrCheckupDutyHd;
    }

    public void setHrCheckupDutyHd(HrCheckupDutyHd hrCheckupDutyHd) {
        if (hrCheckupDutyHd != null && hrCheckupDutyHd.getLocId() != null) {
            brn_id = hrCheckupDutyHd.getLocId().getId();
        }
        this.hrCheckupDutyHd = hrCheckupDutyHd;
    }

    public List<HrEmpInfo> getDriversList() {
        return driversList;
    }

    public void setDriversList(List<HrEmpInfo> driversList) {
        this.driversList = driversList;
    }

    public HrEmpInfo getSelectedDriver() {
        return selectedDriver;
    }

    public void setSelectedDriver(HrEmpInfo selectedDriver) {
        this.selectedDriver = selectedDriver;
    }

    public void updateSelectedDrv() {
        filteredDrviver = selectedDriver;
    }

    public HrEmpInfo getFilteredDrviver() {
        return filteredDrviver;
    }

    public void setFilteredDrviver(HrEmpInfo filteredDrviver) {
        this.filteredDrviver = filteredDrviver;
    }

    public Integer getDriverRate() {
        return driverRate;
    }

    public void setDriverRate(Integer driverRate) {
        this.driverRate = driverRate;
    }
}

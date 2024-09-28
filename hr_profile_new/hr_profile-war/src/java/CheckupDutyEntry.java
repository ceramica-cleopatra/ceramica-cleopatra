/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author data
 */
import e.HrCheckupDutyDt2;
import e.HrCheckupDutyEmp2;
import e.HrCheckupDutyEmployees;
import e.HrCheckupDutyHd2;
import e.HrCheckupDutyItems;
import e.HrCheckupDutyLocations;
import e.HrCheckupDutySetupHd;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.codehaus.jackson.JsonGenerationException;
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
public class CheckupDutyEntry {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<SelectItem> dutyLocationsList = new ArrayList<SelectItem>();
    private Long brnId;
    private HrCheckupDutyHd2 hrCheckupDutyHd = new HrCheckupDutyHd2();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private List<HrCheckupDutyHd2> previousCheckpDutyList;
    private String usercode;
    private HrEmpInfo hrEmpInfo;
    private Calendar c;
    private List<HrEmpInfo> driversList;
    private HrEmpInfo selectedDriver;
    private HrEmpInfo filteredDriver;
    private Integer driverRate;
    private long driverNo;
    private boolean successFlag = false;
    private HrCheckupDutySetupHd hrCheckupDutySetupHd;
    private boolean sent = false;

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
        c.add(Calendar.DAY_OF_MONTH, -366);
        previousCheckpDutyList = sessionBean.findCheckUpDutyHdForEmp2(hrEmpInfo, c.getTime());
        hrCheckupDutySetupHd = sessionBean.findCheckupDutySetupHdByEmpNo(hrEmpInfo.getEmpNo());
        if (hrCheckupDutySetupHd != null) {
            for (HrCheckupDutyLocations loc : hrCheckupDutySetupHd.getHrCheckupDutyLocationsList()) {
                if (loc != null && loc.getLocId() != null) {
                    dutyLocationsList.add(new SelectItem(loc.getLocId().getId(), loc.getLocId().getName()));
                }
            }
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
                        if (hrProfileMsg.getEntityName().equals("HrCheckupDutyHd2")) {
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
        driversList = sessionBean.findDriversList(127L);
        filteredDriver = new HrEmpInfo();
        selectedDriver = new HrEmpInfo();
        driverRate = 0;
    }

    public String add() {
        if (hrCheckupDutyHd.getHrCheckupDutyEmp2List().get(hrCheckupDutyHd.getHrCheckupDutyEmp2List().size() - 1).getEmpId() == null
                || hrCheckupDutyHd.getHrCheckupDutyEmp2List().get(hrCheckupDutyHd.getHrCheckupDutyEmp2List().size() - 1).getEmpId().getEmpNo() == 0L) {
            return null;
        }
        HrCheckupDutyEmp2 hrCheckupDutyEmp2 = new HrCheckupDutyEmp2();
        hrCheckupDutyEmp2.setEmpId(new HrEmpInfo());
        hrCheckupDutyEmp2.setHrCheckupDutyHd2(hrCheckupDutyHd);
        System.out.println("is it approved :" + hrCheckupDutyHd.getApproved());
        hrCheckupDutyHd.getHrCheckupDutyEmp2List().add(hrCheckupDutyEmp2);
        return null;
    }

    public void onRowSelect() {
        if (hrCheckupDutyHd.getHrCheckupDutyEmp2List().isEmpty()) {
            System.out.println("empty list");
            HrCheckupDutyEmp2 hrCheckupDutyEmp = new HrCheckupDutyEmp2();
            hrCheckupDutyEmp.setHrCheckupDutyHd2(hrCheckupDutyHd);
            hrCheckupDutyEmp.setEmpId(new HrEmpInfo());
            hrCheckupDutyHd.getHrCheckupDutyEmp2List().add(hrCheckupDutyEmp);
        }
        if (hrCheckupDutyHd.getDriverNo() != null && hrCheckupDutyHd.getDriverNo().getEmpNo() != 0) {
            filteredDriver = hrCheckupDutyHd.getDriverNo();
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
        hrCheckupDutyHd = new HrCheckupDutyHd2();
    }

    public void addNewDutyReport(ActionEvent ae) {
        brnId = null;
        driverRate = 0;
        hrCheckupDutyHd = new HrCheckupDutyHd2();
        filteredDriver = new HrEmpInfo();
        selectedDriver = new HrEmpInfo();
        List<HrCheckupDutyDt2> hrCheckupDutyDt1s = new ArrayList<HrCheckupDutyDt2>();
        if (hrCheckupDutySetupHd == null) {
            return;
        }
        for (HrCheckupDutyItems item : hrCheckupDutySetupHd.getHrCheckupDutyItemsList()) {
            HrCheckupDutyDt2 hrCheckupDutyDt = new HrCheckupDutyDt2();
            hrCheckupDutyDt.setHrCheckupDutyHd2(hrCheckupDutyHd);
            hrCheckupDutyDt.setTitle(item.getTitle());
            hrCheckupDutyDt.setDisplayOrder(item.getDisplayOrder());
            hrCheckupDutyDt1s.add(hrCheckupDutyDt);
        }
        hrCheckupDutyHd.setHrCheckupDutyDt2List(hrCheckupDutyDt1s);
        HrCheckupDutyEmp2 hrCheckupDutyEmp2 = new HrCheckupDutyEmp2();
        hrCheckupDutyEmp2.setEmpId(new HrEmpInfo());
        hrCheckupDutyEmp2.setHrCheckupDutyHd2(hrCheckupDutyHd);
        List<HrCheckupDutyEmp2> hrCheckupDutyEmpList = new ArrayList<HrCheckupDutyEmp2>();
        hrCheckupDutyEmpList.add(hrCheckupDutyEmp2);
        hrCheckupDutyHd.setHrCheckupDutyEmp2List(hrCheckupDutyEmpList);
    }

    public void save(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (hrCheckupDutyHd.getTrnsDate() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «· «—ÌŒ"));
            return;
        }

        if (hrCheckupDutyHd.getTrnsDate() == null || hrCheckupDutyHd.getHrCheckupDutyEmp2List().get(0).getEmpId() == null
                || hrCheckupDutyHd.getHrCheckupDutyDt2List() == null || hrCheckupDutyHd.getHrCheckupDutyDt2List().isEmpty()
                || (brnId == null || brnId == 0) || hrCheckupDutyHd.getInTrns() == null || hrCheckupDutyHd.getOutTrns() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
            return;
        }


        if (hrCheckupDutyHd.getTrnsType() != null && hrCheckupDutyHd.getTrnsType().equals(1L) && (filteredDriver == null || filteredDriver.getEmpNo() == 0 || driverRate == null || driverRate == 0)) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «·”«∆ﬁ Ê ﬁÌÌ„Â"));
            return;
        }



        if (hrCheckupDutyHd.getHrCheckupDutyEmp2List() == null || hrCheckupDutyHd.getHrCheckupDutyEmp2List().size() == 0
                || hrCheckupDutyHd.getHrCheckupDutyEmp2List().get(0).getEmpId() == null || hrCheckupDutyHd.getHrCheckupDutyEmp2List().get(0).getEmpId().getEmpNo() == 0) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· „ÊŸ› Ê«Õœ ⁄·Ï «·√ﬁ·"));
            return;
        }

        for (HrCheckupDutyEmp2 hrCheckupDutyEmp2 : hrCheckupDutyHd.getHrCheckupDutyEmp2List()) {
            Long chk = sessionBean.chkCheckupDuty2(hrCheckupDutyEmp2.getEmpId().getEmpNo(), hrCheckupDutyHd.getTrnsDate(), brnId, hrCheckupDutyHd.getId());

            if (chk == 1) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "  „  Ã«Ê“ «·Õœ «·√ﬁ’Ï «·„”„ÊÕ »Â ·≈œŒ«·  ﬁ«—Ì— „—Ê— " + hrCheckupDutyEmp2.getEmpId().getEmpName()));
                return;
            }

            if (chk == 2) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " «·„œ… «·“„‰Ì… „‰ √Œ— “Ì«—… ·Â–« «·„Êﬁ⁄ ·„   Ã«Ê“ «·› —… «·„œ… «·„ÿ·Ê»… »Ì‰ “Ì«— Ì‰ ·‰›” «·„Êﬁ⁄ " + hrCheckupDutyEmp2.getEmpId().getEmpName()));
                return;
            }
        }

        hrCheckupDutyHd.setEntryNo(hrEmpInfo);
        hrCheckupDutyHd.setLocId(sessionBean.findLocationById(brnId));

        if (filteredDriver != null && filteredDriver.getEmpNo() != 0) {
            hrCheckupDutyHd.setDriverNo(filteredDriver);
            hrCheckupDutyHd.setDriverRate(driverRate);
        }

        if (hrCheckupDutyHd.getTrnsType() != null && hrCheckupDutyHd.getTrnsType() == 2L) {
            hrCheckupDutyHd.setDriverNo(null);
            hrCheckupDutyHd.setDriverRate(null);
        }

        hrCheckupDutyHd.setEntryNo(hrEmpInfo);
        hrCheckupDutyHd.setEntryDate(new Date());
        hrCheckupDutyHd.setLocId(sessionBean.findLocationById(brnId));
        if (hrCheckupDutyHd.getId() == null) {
            sessionBean.persistHrCheckUpDutyHd2(hrCheckupDutyHd);
        } else {
            sessionBean.mergeHrCheckUpDutyHd2(hrCheckupDutyHd);
        }
        successFlag = true;
        previousCheckpDutyList = sessionBean.findCheckUpDutyHdForEmp2(hrEmpInfo, c.getTime());
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
    }

    public String sendToApprove() {
        if (!sent) {
            sent = true;
            FacesContext fc = FacesContext.getCurrentInstance();
            if (hrCheckupDutyHd.getId() == null) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» Õ›Ÿ «· ﬁ—Ì— √Ê·«"));
                return null;
            }
            if (hrCheckupDutyHd.getHrCheckupDutyEmp2List() == null || hrCheckupDutyHd.getHrCheckupDutyEmp2List().size() == 0
                    || hrCheckupDutyHd.getHrCheckupDutyEmp2List().get(0).getEmpId() == null || hrCheckupDutyHd.getHrCheckupDutyEmp2List().get(0).getEmpId().getEmpNo() == 0) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· „ÊŸ› Ê«Õœ ⁄·Ï «·√ﬁ·"));
                return null;
            }

            if (hrCheckupDutyHd.getTrnsType() == null) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ Ê”Ì·… «·≈‰ ﬁ«·"));
                return null;
            }

            int i = 0;
            for (HrCheckupDutyDt2 hrCheckupDutyDt : hrCheckupDutyHd.getHrCheckupDutyDt2List()) {
                if (hrCheckupDutyDt.getDetails() == null || hrCheckupDutyDt.getDetails().length() == 0) {
                    i++;
                    break;
                }
            }
            if (i > 0) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
                return null;
            }

            if (hrCheckupDutyHd.getTrnsType().equals(1L) && (hrCheckupDutyHd.getDriverNo() == null || hrCheckupDutyHd.getDriverNo().getEmpNo() == 0 || hrCheckupDutyHd.getDriverRate() == null || hrCheckupDutyHd.getDriverRate() == 0)) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «·”«∆ﬁ Ê ﬁÌÌ„Â"));
                return null;
            }

            if (hrCheckupDutyHd.getDone() != null && hrCheckupDutyHd.getDone().equals(new Character('Y'))) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ≈—”«· «· ﬁ—Ì— ··≈⁄ „«œ „‰ ﬁ»·"));
                return null;
            }

            hrCheckupDutyHd.setDone('Y');

            String s = "<table align='right' style='clear:both;'>";
            s = s + "<tr><td><table align='right'><tr><td style='text-align:right'>" + hrCheckupDutyHd.getEntryNo().getDeptName() + "</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«·ﬁ”„ </td></tr>";
            s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyHd.getLocId().getName() + "</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«·„Êﬁ⁄ </td></tr>";
            s = s + "<tr><td style='text-align:right'>" + sdf.format(hrCheckupDutyHd.getTrnsDate()) + "</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>:</td><td style='color:darkblue;font:bold;font-size:medium;text-align:right;'>«· «—ÌŒ </td></tr></table></td></tr>";
            for (HrCheckupDutyDt2 hrCheckupDutyDt : hrCheckupDutyHd.getHrCheckupDutyDt2List()) {
                s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>" + hrCheckupDutyDt.getTitle() + "</td></tr>";
                s = s + "<tr><td style='text-align:right'>" + hrCheckupDutyDt.getDetails() + "</td></tr>";
            }
            s = s + "<tr><td style='color:darkblue;font:bold;font-size:medium;text-align:right;text-decoration:underline'>«·„ÊŸ›Ì‰ «·ﬁ«∆„Ì‰ »«· ﬁ—Ì—</td></tr>";
            s = s + "<tr><td><table align='right' border='1'><tr><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>«·≈”„</td><td style='color:white;font:bold;font-size:medium;text-align:center;background:gray'>«·ﬂÊœ</td></tr>";
            for (HrCheckupDutyEmp2 x : hrCheckupDutyHd.getHrCheckupDutyEmp2List()) {

                s = s + "<tr><td style='text-align:right'>" + x.getEmpId().getEmpName() + "</td><td style='text-align:right'>" + x.getEmpId().getEmpNo() + "</td></tr>";

            }
            s = s + "</table>";
            final String a = s;

            new Thread(new Runnable() {

                public void run() {
                    try {
                        send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.allam@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
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
                        send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.madbouely@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
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
                        send("20.1.1.21", 25, " oracle@ccg.com.eg", "m.dardiry@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
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
                        send("20.1.1.21", 25, " oracle@ccg.com.eg", "mahmoud.mohamed@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
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
                        send("20.1.1.21", 25, " oracle@ccg.com.eg", "a.abbas@ccg.com.eg", " -  ﬁ—Ì— „—Ê— - " + hrCheckupDutyHd.getEntryNo().getDeptName(), a);
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
            try {
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
                    hrProfileMsg.setEntityName("HrCheckupDutyHd2");
                    hrProfileMsg.setSendDate(new Date());
                    hrProfileMsg.setEmpNo(l);
                    hrProfileMsg.setSenderNo(hrCheckupDutyHd.getEntryNo().getEmpNo());
                    hrProfileMsg.setMsgId(hrCheckupDutyHd.getId());
                    hrProfileMsg.setMsgType(1L);
                    objectMessage.setObject(hrProfileMsg);
                    Jedis jedis = null;
                    try {
                        jedis = WorkerBean.pool.getResource();

                        jedis.hset("alerts:" + l, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                        jedis.expire("alerts:" + l, Integer.MAX_VALUE);
                    } finally {
                        if (jedis != null) {
                            jedis.close();
                        }
                    }
                    messageProducer.send(objectMessage);
                }
                System.out.println("message sent");

            } catch (IOException ex) {
                Logger.getLogger(CheckupDutyEntry.class.getName()).log(Level.SEVERE, null, ex);
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
            sessionBean.mergeHrCheckUpDutyHd2(hrCheckupDutyHd);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈—”«·  ﬁ—Ì— «·„—Ê— ··≈⁄ „«œ"));
        }
        brnId = null;
        hrCheckupDutyHd = new HrCheckupDutyHd2();
        successFlag = true;
        sent = false;
        previousCheckpDutyList = sessionBean.findCheckUpDutyHdForEmp2(hrEmpInfo, c.getTime());
        return null;
    }

    public void updateSelectedDrv() {
        filteredDriver = selectedDriver;
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
            String password = "L@123456";
            authentication = new PasswordAuthentication(username, password);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }

    public List<HrCheckupDutyHd2> getPreviousCheckpDutyList() {
        return previousCheckpDutyList;
    }

    public void setPreviousCheckpDutyList(List<HrCheckupDutyHd2> previousCheckpDutyList) {
        this.previousCheckpDutyList = previousCheckpDutyList;
    }

    public CheckupDutyEntry() {
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public List<SelectItem> getDutyLocationsList() {
        return dutyLocationsList;
    }

    public void setDutyLocationsList(List<SelectItem> dutyLocationsList) {
        this.dutyLocationsList = dutyLocationsList;
    }

    public HrCheckupDutyHd2 getHrCheckupDutyHd() {
        return hrCheckupDutyHd;
    }

    public void setHrCheckupDutyHd(HrCheckupDutyHd2 hrCheckupDutyHd) {
        if (hrCheckupDutyHd != null && hrCheckupDutyHd.getLocId() != null) {
            brnId = hrCheckupDutyHd.getLocId().getId();
        }
        this.hrCheckupDutyHd = hrCheckupDutyHd;
    }

    public Integer getDriverRate() {
        return driverRate;
    }

    public void setDriverRate(Integer driverRate) {
        this.driverRate = driverRate;
    }

    public List<HrEmpInfo> getDriversList() {
        return driversList;
    }

    public void setDriversList(List<HrEmpInfo> driversList) {
        this.driversList = driversList;
    }

    public HrEmpInfo getFilteredDriver() {
        return filteredDriver;
    }

    public void setFilteredDriver(HrEmpInfo filteredDrviver) {
        this.filteredDriver = filteredDrviver;
    }

    public HrEmpInfo getSelectedDriver() {
        return selectedDriver;
    }

    public void setSelectedDriver(HrEmpInfo selectedDriver) {
        this.selectedDriver = selectedDriver;
    }

    public boolean isSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }
}

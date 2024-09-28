/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrHolidayRequest;
import e.HrHolidayRequestDt;
import e.HrManualEffectionDt;
import e.HrManualEffectionHd;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
import java.math.BigDecimal;
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
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.primefaces.event.RowEditEvent;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class holiday_confirm {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrHolidayRequestDt> holidayRequestDetailList = new ArrayList<HrHolidayRequestDt>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    String usercode;
    HrEmpInfo hrEmpInfo;

    /** Creates a new instance of holiday_confirm */
    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        hrEmpInfo = hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        holidayRequestDetailList = sessionBean.getHolidayRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
    }

    public holiday_confirm() {
    }

    public List<HrHolidayRequestDt> getHolidayRequestDetailList() {
        return holidayRequestDetailList;
    }

    public void setHolidayRequestDetailList(List<HrHolidayRequestDt> holidayRequestDetailList) {
        this.holidayRequestDetailList = holidayRequestDetailList;
    }

    public void update(RowEditEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        HrHolidayRequestDt hrHolidayRequestDt = (HrHolidayRequestDt) event.getObject();
        HrHolidayRequest hrHolidayRequest = sessionBean.findHolidayRequestById(hrHolidayRequestDt.getReqId());
        Calendar c = Calendar.getInstance();
        Long chk_result = sessionBean.chk_holiday_request(hrHolidayRequest.getEmpId(), hrHolidayRequest.getFromDate(), hrHolidayRequest.getToDate(), hrHolidayRequest.getHolidayType().getId(), hrHolidayRequest.getId());
        if (hrHolidayRequestDt.getMngConfirm() != null && hrHolidayRequestDt.getMngConfirm() == 'Y') {
            if (chk_result.equals(1L)) {
                fm.setSummary("Œÿ√");
                fm.setDetail("—’Ìœ «·„ÊŸ› «·Õ«·Ï ·« Ìﬂ›Ï ··Õ’Ê· ⁄·Ï √Ã«“… ·Â–Â «·„œ…");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                fc.addMessage(null, fm);
                holidayRequestDetailList = sessionBean.getHolidayRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
                return;
            }
            Calendar cc = Calendar.getInstance();
            cc.set(Calendar.DAY_OF_MONTH, 25);
            cc.set(Calendar.HOUR_OF_DAY, 23);
            cc.set(Calendar.MINUTE, 59);
            cc.set(Calendar.SECOND, 59);
            Calendar ccc = Calendar.getInstance();
            ccc.set(Calendar.DAY_OF_MONTH, 15);
            ccc.set(Calendar.HOUR_OF_DAY, 23);
            ccc.set(Calendar.MINUTE, 59);
            ccc.set(Calendar.SECOND, 59);

            if (cc.before(Calendar.getInstance()) && hrHolidayRequestDt.getFromDate().before(ccc.getTime())) {
                fm.setSummary("Œÿ√");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                fm.setDetail("·« Ì„ﬂ‰ ≈⁄ „«œ «·ÿ·» »”»» œŒÊ· «·„— »«  ··„—«Ã⁄…");
                fc.addMessage(null, fm);
                holidayRequestDetailList = sessionBean.getHolidayRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
                return;
            }
            if (chk_result.equals(4L)) {
                fm.setSummary("Œÿ√");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                fm.setDetail("› —… «·√Ã«“…  Õ ÊÏ ⁄·Ï €Ì«» »√–‰ √Ê »œÊ‰ √–‰ .. ·« Ì„ﬂ‰  ÿ»Ìﬁ «·√Ã«“…");
                fc.addMessage(null, fm);
                holidayRequestDetailList = sessionBean.getHolidayRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
                return;
            }
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
            SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
            if (hrHolidayRequestDt.getHolidayType() == 10L) {
                if (chk_result.equals(2L)) {
                    fm.setSummary("Œÿ√");
                    fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                    fm.setDetail("·« Ì„ﬂ‰ ··„ÊŸ› «·Õ’Ê· ⁄·Ï √ﬂÀ— „‰ ÌÊ„Ì‰ √Ã«“… ⁄«—÷… Œ·«· ‰›” «·‘Â—");
                    fc.addMessage(null, fm);
                    holidayRequestDetailList = sessionBean.getHolidayRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
                    return;
                }
                if ((((hrHolidayRequestDt.getToDate().getTime() - hrHolidayRequestDt.getFromDate().getTime()) / (1000 * 60 * 60 * 24)) + 1) > 2) {
                    fm.setSummary("Œÿ√");
                    fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                    fm.setDetail("·« Ì„ﬂ‰ﬂ ÿ·» √ﬂÀ— „‰ ÌÊ„Ì‰ √Ã«“… ⁄«—÷… ›Ï ‰›” «·ÿ·»");
                    fc.addMessage(null, fm);
                    holidayRequestDetailList = sessionBean.getHolidayRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
                    return;
                }

            }
            for (int i = 1; i <= hrHolidayRequestDt.getDaysNo(); i++) {
                c.setTime(hrHolidayRequestDt.getFromDate());
                c.add(Calendar.DATE, i - 1);
                Date d = c.getTime();
                if (sessionBean.chkCardExist(hrHolidayRequestDt.getEmpNo(), Long.parseLong(sdf3.format(d)), Long.parseLong(sdf.format(d))) == 0L) {
                    sessionBean.addEmpCard(hrHolidayRequestDt.getEmpNo(), Long.parseLong(sdf3.format(d)), Long.parseLong(sdf.format(d)));
                }
                HrManualEffectionHd hrManualEffectionHd = sessionBean.getHrManualEffectionHd(hrHolidayRequestDt.getEmpNo(), Long.parseLong(sdf3.format(d)), Long.parseLong(sdf.format(d)));
                HrManualEffectionDt hrManualEffectionDt;
                if (sessionBean.chkManualEffectionDt(hrManualEffectionHd.getId(), d) == 0L) {
                    continue;

                }
                hrManualEffectionDt = sessionBean.getHrManualEffectionDt(hrManualEffectionHd.getId(), d);
                hrManualEffectionDt.setNotes(hrHolidayRequestDt.getHolidayName());
                hrManualEffectionDt.setHolType(hrHolidayRequestDt.getHolidayType());
                hrManualEffectionDt.setHoliday("Y");
                if (hrHolidayRequestDt.getHolidayType() == 10L && (hrManualEffectionDt.getInTrns() != null || hrManualEffectionDt.getOutTrns() != null)) {
                    hrManualEffectionDt.setEffectDays(BigDecimal.ZERO);
                    hrManualEffectionDt.setEffectDays(BigDecimal.ZERO);
                    hrManualEffectionDt.setPlusMinuts(BigDecimal.ZERO);
                    hrManualEffectionDt.setPlusDays(BigDecimal.ZERO);
                }
                sessionBean.mergeHrEffectionManualDt(hrManualEffectionDt);

            }
            hrHolidayRequest.setRejectDesc(null);
            hrHolidayRequest.setMngNo(hrEmpInfo);
            hrHolidayRequest.setMngConfirm('Y');
            hrHolidayRequest.setApproveDate(new Date());
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                if (jedis.hgetAll("managers:" + hrHolidayRequest.getEmpId()) != null && !jedis.hgetAll("managers:" + hrHolidayRequest.getEmpId()).isEmpty()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrHolidayRequest.getEmpId());
                    for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                        if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                            Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                            for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                                try {
                                    HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                    if (hrProfileMsg.getMsgId().equals(hrHolidayRequest.getId()) && hrProfileMsg.getEntityName().equals("HrHolidayRequest")) {
                                        hrProfileMsg.setReadDone('Y');
                                        jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                        jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(holiday_confirm.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                    sessionBean.updateReadDoneMsg("HrHolidayRequest", hrHolidayRequest.getId(), 'Y', null);
                }
            } finally {
                if (jedis != null) {
                    jedis.close();

                }
            }
            fm.setSummary(" „ «·√⁄ „«œ");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fm.setDetail(" „ √⁄ „«œ ÿ·» «·√Ã«“…");

            Connection connection = null;
            Session session = null;
            MessageProducer messageProducer = null;
            try {
                Context ctx = new InitialContext();
                ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                connection = connectionFactory.createConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                messageProducer = session.createProducer(queue);
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("HrHolidayRequest");
                hrProfileMsg.setMsgApprove('Y');
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrHolidayRequest.getEmpId());
                hrProfileMsg.setSenderNo(Long.parseLong(usercode));
                hrProfileMsg.setMsgId(hrHolidayRequest.getId());
                hrProfileMsg.setMsgType(2L);
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("msgs:" + hrHolidayRequest.getEmpId(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("msgs:" + hrHolidayRequest.getEmpId(), Integer.MAX_VALUE);
                messageProducer.send(objectMessage);
                System.out.println("message sent");

            } catch (IOException ex) {
                Logger.getLogger(holiday_confirm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (JMSException x) {
                x.printStackTrace();
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

        } else {
            if (hrHolidayRequestDt.getMngConfirm() == null) {
                hrHolidayRequest.setMngNo(null);
                hrHolidayRequest.setMngConfirm(null);
                hrHolidayRequest.setRejectDesc(null);
                hrHolidayRequest.setApproveDate(null);
                fm.setSummary(" „ «· —«Ã⁄");
                fm.setSeverity(FacesMessage.SEVERITY_INFO);
                fm.setDetail(" „ «· —«Ã⁄ ⁄‰ «·√⁄ „«œ");
                Jedis jedis = null;
                try {
                    jedis = WorkerBean.pool.getResource();
                    if (jedis.hgetAll("managers:" + hrHolidayRequest.getEmpId()) != null && !jedis.hgetAll("managers:" + hrHolidayRequest.getEmpId()).isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrHolidayRequest.getEmpId());
                        for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                            if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                                    try {
                                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                        if (hrProfileMsg.getMsgId().equals(hrHolidayRequest.getId()) && hrProfileMsg.getEntityName().equals("HrHolidayRequest")) {
                                            hrProfileMsg.setReadDone('N');
                                            jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                            jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(holiday_confirm.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                        sessionBean.updateReadDoneMsg("HrHolidayRequest", hrHolidayRequest.getId(), 'N', null);
                    }
                } finally {
                    if (jedis != null) {
                        jedis.close();

                    }
                }
            } else {
                if (hrHolidayRequestDt.getHolidayType() == 10L) {
                    fm.setSummary("Œÿ√");
                    fm.setDetail("·« Ì„ﬂ‰ﬂ —ﬁ÷ «·√Ã«“… «·⁄«—÷…");
                    fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                    fc.addMessage(null, fm);
                    holidayRequestDetailList = sessionBean.getHolidayRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
                    return;
                }
                if (hrHolidayRequestDt.getRejectDesc().length() == 0) {
                    fm.setSummary("Œÿ√");
                    fm.setDetail("ÌÃ» ≈œŒ«· ”»» «·—›÷");
                    fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                    fc.addMessage(null, fm);
                    holidayRequestDetailList = sessionBean.getHolidayRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
                    return;
                }

                Jedis jedis = null;
                try {
                    jedis = WorkerBean.pool.getResource();
                    if (jedis.hgetAll("managers:" + hrHolidayRequest.getEmpId()) != null && !jedis.hgetAll("managers:" + hrHolidayRequest.getEmpId()).isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrHolidayRequest.getEmpId());
                        for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                            if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                                    try {
                                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                        if (hrProfileMsg.getMsgId().equals(hrHolidayRequest.getId()) && hrProfileMsg.getEntityName().equals("HrHolidayRequest")) {
                                            hrProfileMsg.setReadDone('Y');
                                            jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                            jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(holiday_confirm.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                        sessionBean.updateReadDoneMsg("HrHolidayRequest", hrHolidayRequest.getId(), 'Y', null);
                    }
                } finally {
                    if (jedis != null) {
                        jedis.close();

                    }
                }
                fm.setSummary(" „ «·—›÷");
                fm.setDetail(" „ —›÷ ÿ·» «·√Ã«“…");
                Connection connection = null;
                Session session = null;
                MessageProducer messageProducer = null;
                try {
                    Context ctx = new InitialContext();
                    ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                    Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                    connection = connectionFactory.createConnection();
                    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    messageProducer = session.createProducer(queue);
                    ObjectMessage objectMessage = session.createObjectMessage();
                    HrProfileMsg hrProfileMsg = new HrProfileMsg();
                    hrProfileMsg.setEntityName("HrHolidayRequest");
                    hrProfileMsg.setMsgApprove('N');
                    hrProfileMsg.setSendDate(new Date());
                    hrProfileMsg.setEmpNo(hrHolidayRequest.getEmpId());
                    hrProfileMsg.setSenderNo(Long.parseLong(usercode));
                    hrProfileMsg.setMsgId(hrHolidayRequest.getId());
                    hrProfileMsg.setMsgType(2L);
                    objectMessage.setObject(hrProfileMsg);
                    jedis.hset("msgs:" + hrHolidayRequest.getEmpId(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                    jedis.expire("msgs:" + hrHolidayRequest.getEmpId(), Integer.MAX_VALUE);
                    messageProducer.send(objectMessage);
                    System.out.println("message sent");
                } catch (IOException ex) {
                    Logger.getLogger(holiday_confirm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    ex.printStackTrace();
                } catch (JMSException x) {
                    x.printStackTrace();
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

                fm.setSeverity(FacesMessage.SEVERITY_INFO);
                hrHolidayRequest.setMngNo(hrEmpInfo);
                hrHolidayRequest.setMngConfirm('N');
                hrHolidayRequest.setApproveDate(new Date());
                hrHolidayRequest.setRejectDesc(hrHolidayRequestDt.getRejectDesc());
            }
            for (HrManualEffectionDt hrManualEffectionDt : sessionBean.getHrManualEffectionDt(hrHolidayRequestDt.getEmpNo(), hrHolidayRequestDt.getFromDate(), hrHolidayRequestDt.getToDate())) {
                hrManualEffectionDt.setHolType(null);
                hrManualEffectionDt.setHoliday(null);
                hrManualEffectionDt.setNotes(null);
                sessionBean.mergeHrEffectionManualDt(hrManualEffectionDt);
            }

        }




        fc.addMessage(null, fm);
        sessionBean.mergeHrHolidayRequest(hrHolidayRequest);
        holidayRequestDetailList = sessionBean.getHolidayRequestDt(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
    }
}

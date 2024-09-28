/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrAuthorization;
import e.HrAuthorizeRequest;
import e.HrMontlySalaryCalcPeriod;
import e.HrProfileAccessLog;
import e.HrProfileMessage;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
public class authorize_request implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private Date authorize_date;
    private Long Type;
    private Long minutes_number;
    private String canceled;
    private String usercode;
    private Date min_date;
    private List<HrAuthorizeRequest> authorize_request_list = new ArrayList<HrAuthorizeRequest>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    Date from_date;
    Date to_date;
    Calendar c = Calendar.getInstance();
    Long x = 0L;
    Long y = 0L;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        usercode = (String) fc.getExternalContext().getSessionMap().get("usercode");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        String hscpAsString = null;
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            hscpAsString = jedis.get("hscp");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        HrMontlySalaryCalcPeriod hscp = null;
        try {
            hscp = new ObjectMapper().readValue(hscpAsString, HrMontlySalaryCalcPeriod.class);
        } catch (IOException ex) {
            Logger.getLogger(authorize_request.class.getName()).log(Level.SEVERE, null, ex);
        }
        min_date = hscp.getDateFrom();
        authorize_request_list = sessionBean.getAuthorizeRequestList(Long.parseLong(usercode), min_date);

        if (jedis.hgetAll("msgs:" + usercode) != null && !jedis.hgetAll("msgs:" + usercode).isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> hrProfileMsgMap = jedis.hgetAll("msgs:" + usercode);
            for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                try {
                    HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                    if (hrProfileMsg.getEntityName().equals("HrAuthorizeRequest")) {
                        hrProfileMsg.setReadDone('Y');
                        jedis.hset("msgs:" + usercode, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                        jedis.expire("msgs:" + usercode, Integer.MAX_VALUE);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(authorize_request.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            sessionBean.updateReadDoneMsg("HrAuthorizeRequest", null, 'Y', Long.parseLong(usercode));
        }

    }

    public void add_new_authorize(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        usercode = (String) fc.getExternalContext().getSessionMap().get("usercode");
        if (authorize_date == null || Type == null || minutes_number == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« ");
            fc.addMessage(null, fm);
            return;
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 25);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Calendar ccc = Calendar.getInstance();
        ccc.set(Calendar.DAY_OF_MONTH, 15);
        ccc.set(Calendar.HOUR_OF_DAY, 23);
        ccc.set(Calendar.MINUTE, 59);
        ccc.set(Calendar.SECOND, 59);
        Long chk_result = sessionBean.chk_authorize_request(authorize_date, Long.parseLong(usercode), null, minutes_number);
        if (c.before(Calendar.getInstance()) && authorize_date.before(ccc.getTime())) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ìﬂ‰ﬂ ≈œŒ«· «·≈–‰ ..  „ œŒÊ· «·„— »«  ··„—«Ã⁄…");
            fc.addMessage(null, fm);
            return;
        }
        if (chk_result.equals(1L)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ≈œŒ«· ≈–‰ ›Ï ‘Â— ﬁœÌ„");
            fc.addMessage(null, fm);
            return;
        }
        c.setTime(authorize_date);
        Calendar cc = Calendar.getInstance();
        cc.add(Calendar.MONTH, -2);
        if (c.before(cc)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ≈œŒ«· ≈–‰ ›Ï ‘Â— ﬁœÌ„");
            fc.addMessage(null, fm);
            return;
        }
        if (chk_result.equals(2L)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ≈œŒ«· ≈–‰ „‰ ﬁ»· Œ·«· ‰›” «·ÌÊ„ ");
            fc.addMessage(null, fm);

            reset_authorize_requets_list();
            return;
        }
        if (chk_result.equals(3L)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ≈œŒ«· ≈–‰ Œ·«· Â–« «·‘Â— .. ·„ Ì „ › Õ ﬂ«— ");
            fc.addMessage(null, fm);
            return;
        }
        if (minutes_number < 15L) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» √·« Ìﬁ· «·≈–‰ ⁄‰ 15 œﬁÌﬁ…");
            fc.addMessage(null, fm);
            return;
        }

        if (chk_result.equals(4L)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  Ã«Ê“ «·Õœ «·√ﬁ’Ï ·„Ã„Ê⁄ ⁄œœ «·œﬁ«∆ﬁ");
            fc.addMessage(null, fm);
            return;
        }
        HrAuthorizeRequest hrAuthprizeRequest = new HrAuthorizeRequest();
        if (sessionBean.getAuthorizeRequestMax() == null) {
            hrAuthprizeRequest.setId(1L);
        } else {
            hrAuthprizeRequest.setId(1L + sessionBean.getAuthorizeRequestMax());
        }
        hrAuthprizeRequest.setEmpNo(Long.parseLong(usercode));
        hrAuthprizeRequest.setMinutesNo(minutes_number);
        hrAuthprizeRequest.setType(Type);
        hrAuthprizeRequest.setAuthorizeDate(authorize_date);
        hrAuthprizeRequest.setTrnsDate(new Date());
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «·ÿ·» »‰Ã«Õ");
        fc.addMessage(null, fm);
        sessionBean.authorizeRequestPersist(hrAuthprizeRequest);
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
            Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrAuthprizeRequest.getEmpNo());
            for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("HrAuthorizeRequest");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(Long.parseLong(hrEmpManagersAsString));
                hrProfileMsg.setSenderNo(hrAuthprizeRequest.getEmpNo());
                hrProfileMsg.setMsgId(hrAuthprizeRequest.getId());
                hrProfileMsg.setMsgType(1L);
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                        + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                messageProducer.send(objectMessage);
            }
            System.out.println("message sent");

        } catch (IOException ex) {
            Logger.getLogger(authorize_request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
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
        authorize_date = null;
        Type = null;
        minutes_number = null;
        reset_authorize_requets_list();
    }

    public Long getType() {
        return Type;
    }

    public void setType(Long Type) {
        this.Type = Type;
    }

    public Date getAuthorize_date() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate") != null) {
            String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate");
            String dd = s.substring(0, s.indexOf('-'));
            String mm = s.substring(s.indexOf('-') + 1, s.lastIndexOf('-'));
            String yyyy = s.substring(s.lastIndexOf('-') + 1);
            System.out.println("");
            Calendar c = Calendar.getInstance(new Locale("ar"));
            c.set(Integer.parseInt(yyyy), Integer.parseInt(mm) - 1, Integer.parseInt(dd));
            authorize_date = c.getTime();
        }
        return authorize_date;
    }

    public void setAuthorize_date(Date authorize_date) {
        this.authorize_date = authorize_date;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public Long getMinutes_number() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("minusMinuts") != null) {
            minutes_number = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("minusMinuts"));
        }
        return minutes_number;
    }

    public void setMinutes_number(Long minutes_number) {
        this.minutes_number = minutes_number;
    }

    /** Creates a new instance of authorize_request */
    public authorize_request() {
    }

    public List<HrAuthorizeRequest> getAuthorize_request_list() {
        return authorize_request_list;
    }

    public void setAuthorize_request_list(List<HrAuthorizeRequest> authorize_request_list) {
        this.authorize_request_list = authorize_request_list;
    }

    public void reset_authorize_requets_list() {
        FacesContext fc = FacesContext.getCurrentInstance();
        usercode = (String) fc.getExternalContext().getSessionMap().get("usercode");
        String hscpAsString = null;
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            hscpAsString = jedis.get("hscp");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        HrMontlySalaryCalcPeriod hscp = null;
        try {
            hscp = new ObjectMapper().readValue(hscpAsString, HrMontlySalaryCalcPeriod.class);
        } catch (IOException ex) {
            Logger.getLogger(authorize_request.class.getName()).log(Level.SEVERE, null, ex);
        }
        min_date = hscp.getDateFrom();
        authorize_request_list = sessionBean.getAuthorizeRequestList(Long.parseLong(usercode), min_date);
    }

    public void populate_authorize_list(ActionEvent ae) {
        reset_authorize_requets_list();
    }

    public void update(RowEditEvent event) {
        HrAuthorizeRequest hrAuthorizeRequest = (HrAuthorizeRequest) event.getObject();
        FacesContext fc = FacesContext.getCurrentInstance();
        usercode = (String) fc.getExternalContext().getSessionMap().get("usercode");
        if (hrAuthorizeRequest.getAuthorizeDate() == null || hrAuthorizeRequest.getType() == null || hrAuthorizeRequest.getMinutesNo() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« ");
            fc.addMessage(null, fm);
            reset_authorize_requets_list();
            return;
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 25);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Calendar ccc = Calendar.getInstance();
        ccc.set(Calendar.DAY_OF_MONTH, 15);
        ccc.set(Calendar.HOUR_OF_DAY, 23);
        ccc.set(Calendar.MINUTE, 59);
        ccc.set(Calendar.SECOND, 59);
        Long chk_result = sessionBean.chk_authorize_request(hrAuthorizeRequest.getAuthorizeDate(), Long.parseLong(usercode), hrAuthorizeRequest.getId(), hrAuthorizeRequest.getMinutesNo());
        if (c.before(Calendar.getInstance()) && hrAuthorizeRequest.getAuthorizeDate().before(ccc.getTime())) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ìﬂ‰ﬂ ≈œŒ«· «·≈–‰ ..  „ œŒÊ· «·„— »«  ··„—«Ã⁄…");
            fc.addMessage(null, fm);
            return;
        }
        if (chk_result.equals(1L)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ≈œŒ«· ≈–‰ ›Ï ‘Â— ﬁœÌ„");
            fc.addMessage(null, fm);
            reset_authorize_requets_list();
            return;
        }
        c.setTime(hrAuthorizeRequest.getAuthorizeDate());
        Calendar cc = Calendar.getInstance();
        cc.add(Calendar.MONTH, -2);
        if (c.before(cc)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ≈œŒ«· ≈–‰ ›Ï ‘Â— ﬁœÌ„");
            fc.addMessage(null, fm);
            reset_authorize_requets_list();
            return;
        }
        if (chk_result.equals(2L)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ≈œŒ«· ≈–‰ „‰ ﬁ»· Œ·«· ‰›” «·ÌÊ„ ");
            fc.addMessage(null, fm);
            reset_authorize_requets_list();
            return;
        }
        if (chk_result.equals(3L)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ≈œŒ«· ≈–‰ Œ·«· Â–« «·‘Â— .. ·„ Ì „ › Õ ﬂ«— ");
            fc.addMessage(null, fm);
            reset_authorize_requets_list();
            return;
        }

        if (hrAuthorizeRequest.getMinutesNo() < 15L) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» √·« Ìﬁ· «·≈–‰ ⁄‰ 15 œﬁÌﬁ…");
            fc.addMessage(null, fm);
            reset_authorize_requets_list();
            return;
        }



        if (chk_result.equals(4L)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  Ã«Ê“ «·Õœ «·√ﬁ’Ï ·„Ã„Ê⁄ ⁄œœ «·œﬁ«∆ﬁ");
            fc.addMessage(null, fm);
            reset_authorize_requets_list();
            return;
        }
        sessionBean.mergeHrAuthorizeRequest(hrAuthorizeRequest);
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  ⁄œÌ· «·≈–‰ »‰Ã«Õ");
        fc.addMessage(null, fm);
        ObjectMapper objectMapper = new ObjectMapper();
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + usercode);
            for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                    Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                    for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                        try {
                            HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                            if (hrProfileMsg.getEntityName().equals("HrAuthorizeRequest") && hrProfileMsg.getMsgId().equals(hrAuthorizeRequest.getId()) && hrAuthorizeRequest.getCancel().equals("Y")) {
                                hrProfileMsg.setReadDone('Y');
                                jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                            } else if (hrProfileMsg.getEntityName().equals("HrAuthorizeRequest") && hrProfileMsg.getMsgId().equals(hrAuthorizeRequest.getId()) && (hrAuthorizeRequest.getCancel().equals("N") || hrAuthorizeRequest.getCancel() == null)) {
                                hrProfileMsg.setReadDone(null);
                                jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(authorize_request.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        if (hrAuthorizeRequest.getCancel().equals("Y")) {
            sessionBean.updateReadDoneMsg("HrAuthorizeRequest", hrAuthorizeRequest.getId(), 'Y', null);
        } else {
            sessionBean.updateReadDoneMsg("HrAuthorizeRequest", hrAuthorizeRequest.getId(), 'N', null);
        }
        reset_authorize_requets_list();
    }
}

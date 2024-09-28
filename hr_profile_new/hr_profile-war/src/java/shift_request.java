/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrMontlySalaryCalcPeriod;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrShift;
import e.HrShiftChangeRequest;
import e.HrShiftDt;
import e.HrUsers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
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
public class shift_request {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<SelectItem> shift_selected_list = new ArrayList<SelectItem>();
    private List<HrShiftChangeRequest> hrShiftChangeRequestList;
    private Date shift_date;
    private Long shift_selected;
    private String usercode;
    private FacesContext fc;
    private Date min_date;

    @PostConstruct
    public void init() {
        fc = FacesContext.getCurrentInstance();
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
        HrEmpInfo hrEmpInfo = (HrEmpInfo) fc.getExternalContext().getSessionMap().get("hrEmpInfo");
        List<HrShiftDt> hrShifts = sessionBean.getShift(hrEmpInfo.getDeptId(), hrEmpInfo.getLocId());
        for (HrShiftDt hrShiftDt : hrShifts) {
            shift_selected_list.add(new SelectItem(hrShiftDt.getHrShift().getId(), hrShiftDt.getHrShift().getName()));
        }
        hrShiftChangeRequestList = new ArrayList<HrShiftChangeRequest>();
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
        hrShiftChangeRequestList = sessionBean.getHrShiftChangeRequestList(Long.parseLong(usercode), min_date);

        if (jedis.hgetAll("msgs:" + usercode) != null && !jedis.hgetAll("msgs:" + usercode).isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> hrProfileMsgMap = jedis.hgetAll("msgs:" + usercode);
            for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                try {
                    HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                    if (hrProfileMsg.getEntityName().equals("HrShiftChangeRequest")) {
                        hrProfileMsg.setReadDone('Y');
                        jedis.hset("msgs:" + usercode, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                        jedis.expire("msgs:" + usercode, Integer.MAX_VALUE);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(shift_request.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            sessionBean.updateReadDoneMsg("HrShiftChangeRequest", null, 'Y', Long.parseLong(usercode));
        }
    }

    /** Creates a new instance of shift_request */
    public shift_request() {
    }

    public Long getShift_selected() {
        return shift_selected;
    }

    public void setShift_selected(Long shift_selected) {
        this.shift_selected = shift_selected;
    }

    /** Creates a new instance of shift_request */
    public Date getShift_date() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate") != null) {
            String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate");
            String dd = s.substring(0, s.indexOf('-'));
            String mm = s.substring(s.indexOf('-') + 1, s.lastIndexOf('-'));
            String yyyy = s.substring(s.lastIndexOf('-') + 1);
            System.out.println("");
            Calendar c = Calendar.getInstance(new Locale("ar"));
            c.set(Integer.parseInt(yyyy), Integer.parseInt(mm) - 1, Integer.parseInt(dd));
            shift_date = c.getTime();
        }
        return shift_date;
    }

    public void setShift_date(Date shift_date) {
        this.shift_date = shift_date;
    }

    public List<SelectItem> getShift_selected_list() {
        return shift_selected_list;
    }

    public void setShift_selected_list(List<SelectItem> shift_selected_list) {
        this.shift_selected_list = shift_selected_list;
    }

    public void save_shift_request(ActionEvent ae) {
        fc = FacesContext.getCurrentInstance();
        if (shift_date == null || shift_selected == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« ");
            fc.addMessage(null, fm);
            return;
        }
        Long chk_value = sessionBean.chk_shift_change_request(Long.parseLong(usercode), shift_date, null);
        System.out.println("chk_value" + chk_value);
        if (chk_value == 1L) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ÿ·»  €ÌÌ— ‘Ì›  ≈·« »⁄œ ≈œŒ«· ÃœÊ· «·‘Ì›  „‰ «·„œÌ— «·„”∆Ê·"));
            return;
        }
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.HOUR, 0);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(shift_date);
        c2.set(Calendar.HOUR, 0);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);

        if (c2.before(c1)) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ÿ·»  €ÌÌ— ‘Ì›   ·ÌÊ„ ”«»ﬁ"));
            return;
        }
        if (chk_value == 2L) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ÿ·»  €ÌÌ— ‘Ì›  √ﬂÀ— „‰ „—… Œ·«· ‰›” «·ÌÊ„"));
            return;
        }
        Long id = sessionBean.getHrShiftRequest();
        if (id == null) {
            id = 1L;
        } else {
            id = id + 1L;
        }
        HrShiftChangeRequest hrShiftChangeRequest = new HrShiftChangeRequest();
        hrShiftChangeRequest.setId(id);
        hrShiftChangeRequest.setShiftDate(shift_date);
        hrShiftChangeRequest.setShiftId(sessionBean.getShiftById(shift_selected));
        hrShiftChangeRequest.setEmpNo(sessionBean.finduserbyid(Long.parseLong(usercode)));
        hrShiftChangeRequest.setTrnsDate(new Date());
        sessionBean.persistHrShiftChangeRequest(hrShiftChangeRequest);
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
            Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrShiftChangeRequest.getEmpNo().getEmpNo());
            for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("HrShiftChangeRequest");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(Long.parseLong(hrEmpManagersAsString));
                hrProfileMsg.setSenderNo(hrShiftChangeRequest.getEmpNo().getEmpNo());
                hrProfileMsg.setMsgId(hrShiftChangeRequest.getId());
                hrProfileMsg.setMsgType(1L);
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                messageProducer.send(objectMessage);

            }
            System.out.println("message sent");

        } catch (IOException ex) {
            Logger.getLogger(shift_request.class.getName()).log(Level.SEVERE, null, ex);
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
        shift_date = null;
        shift_selected = null;
        hrShiftChangeRequestList = sessionBean.getHrShiftChangeRequestList(Long.parseLong(usercode), min_date);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ «·Õ›Ÿ", " „ Õ›Ÿ «·ÿ·» »‰Ã«Õ"));
    }

    public void update(RowEditEvent event) {
        fc = FacesContext.getCurrentInstance();
        HrShiftChangeRequest hrShiftChangeRequest = (HrShiftChangeRequest) event.getObject();
        if (hrShiftChangeRequest.getShiftDate() == null || hrShiftChangeRequest.getShiftId() == null) {
            FacesMessage fm = new FacesMessage("Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« ");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(null, fm);
            return;
        }

        Long chk_value = sessionBean.chk_shift_change_request(Long.parseLong(usercode), hrShiftChangeRequest.getShiftDate(), hrShiftChangeRequest.getId());
        if (chk_value == 1L) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ÿ·»  €ÌÌ— ‘Ì›  ≈·« »⁄œ ≈œŒ«· ÃœÊ· «·‘Ì›  „‰ «·„œÌ— «·„”∆Ê·"));
            hrShiftChangeRequestList = sessionBean.getHrShiftChangeRequestList(Long.parseLong(usercode), min_date);
            return;
        }
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.HOUR, 0);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(hrShiftChangeRequest.getShiftDate());
        c2.set(Calendar.HOUR, 0);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);


        if (c2.before(c1)) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ÿ·»  €ÌÌ— ‘Ì›   ·ÌÊ„ ”«»ﬁ"));
            return;
        }
        if (chk_value == 2L) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ÿ·»  €ÌÌ— ‘Ì›  √ﬂÀ— „‰ „—… Œ·«· ‰›” «·ÌÊ„"));
            hrShiftChangeRequestList = sessionBean.getHrShiftChangeRequestList(Long.parseLong(usercode), min_date);
            return;
        }
        HrShift hrShift = new HrShift();
        hrShift = sessionBean.getShiftById(hrShiftChangeRequest.getShiftId().getId());
        hrShiftChangeRequest.setShiftId(hrShift);
        sessionBean.merge_shift_request(hrShiftChangeRequest);
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + usercode);
            ObjectMapper objectMapper = new ObjectMapper();
            for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                    Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                    for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                        try {
                            HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                            if (hrProfileMsg.getEntityName().equals("HrShiftChangeRequest") && hrProfileMsg.getMsgId().equals(hrShiftChangeRequest.getId()) && hrShiftChangeRequest.getCanceled().equals("Y")) {
                                hrProfileMsg.setReadDone('Y');
                                jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                            } else if (hrProfileMsg.getEntityName().equals("HrShiftChangeRequest") && hrProfileMsg.getMsgId().equals(hrShiftChangeRequest.getId()) && (hrShiftChangeRequest.getCanceled().equals("N") || hrShiftChangeRequest.getCanceled() == null)) {
                                hrProfileMsg.setReadDone(null);
                                jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(shift_request.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    if (hrShiftChangeRequest.getCanceled().equals("Y")) {
            sessionBean.updateReadDoneMsg("HrShiftChangeRequest", hrShiftChangeRequest.getId(), 'Y', null);
    }
    else

    {
        sessionBean.updateReadDoneMsg("HrShiftChangeRequest", hrShiftChangeRequest.getId(), 'N', null);
    }
    hrShiftChangeRequestList  = sessionBean.getHrShiftChangeRequestList(Long.parseLong(usercode), min_date);
    hrShiftChangeRequest  = new HrShiftChangeRequest();
    shift_date  = null;
    shift_selected  = null;

    fc.addMessage (null


, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  ⁄œÌ· «·ÿ·» »‰Ã«Õ"));
    }

    public List<

HrShiftChangeRequest> getHrShiftChangeRequestList() {
        return hrShiftChangeRequestList;


}

    public void

setHrShiftChangeRequestList(List<HrShiftChangeRequest> hrShiftChangeRequestList) {
        this.hrShiftChangeRequestList = hrShiftChangeRequestList;

}
}

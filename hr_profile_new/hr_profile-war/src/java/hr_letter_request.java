/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpMangers;
import e.HrLetterRequest;
import e.HrLetterType;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrRegion;
import e.HrUsers;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;
import sb.SessionBeanRemote;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@SessionScoped
public class hr_letter_request implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrLetterRequest hrLetterRequest = new HrLetterRequest();
    private List<SelectItem> hrLetterReasonList = new ArrayList<SelectItem>();
    private List<HrLetterRequest> hrLetterRequestList = new ArrayList<HrLetterRequest>();
    private Long reason;

    /** Creates a new instance of hr_letter_request */
    public hr_letter_request() {
    }

    @PostConstruct
    public void init() {

        for (HrLetterType hrLetterType : sessionBean.getHrLetterTypes()) {
            hrLetterReasonList.add(new SelectItem(hrLetterType.getId(), hrLetterType.getName()));
        }
        String usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrLetterRequestList = sessionBean.hrLetterRequestList(Long.parseLong(usercode));
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
                        if (hrProfileMsg.getEntityName().equals("HrLetterRequest")) {
                            hrProfileMsg.setReadDone('Y');
                            jedis.hset("msgs:" + usercode, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("msgs:" + usercode, Integer.MAX_VALUE);
                            hrProfileMsgs.add(hrProfileMsg);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(hr_letter_request.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<HrLetterRequest> getHrLetterRequestList() {
        return hrLetterRequestList;
    }

    public void setHrLetterRequestList(List<HrLetterRequest> hrLetterRequestList) {
        this.hrLetterRequestList = hrLetterRequestList;
    }

    public HrLetterRequest getHrLetterRequest() {
        return hrLetterRequest;
    }

    public Long getReason() {
        return reason;
    }

    public void setReason(Long reason) {
        this.reason = reason;
    }

    public List<SelectItem> getHrLetterReasonList() {
        return hrLetterReasonList;
    }

    public void setHrLetterReasonList(List<SelectItem> hrLetterReasonList) {
        this.hrLetterReasonList = hrLetterReasonList;
    }

    public void setHrLetterRequest(HrLetterRequest hrLetterRequest) {
        this.hrLetterRequest = hrLetterRequest;
    }

    public void save(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (hrLetterRequest.getDest().length() == 0 || reason == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« ");
            fc.addMessage(null, fm);
            return;
        }
        String usercode = (String) fc.getExternalContext().getSessionMap().get("usercode");
        if (sessionBean.getHrLetterRequestId() == null) {
            hrLetterRequest.setId(1L);
        } else {
            hrLetterRequest.setId(sessionBean.getHrLetterRequestId() + 1L);
        }
        hrLetterRequest.setTrnsDate(new Date());
        hrLetterRequest.setEmpNo(Long.parseLong(usercode));
        hrLetterRequest.setReason(sessionBean.getHrLetterTypeById(reason));
        sessionBean.persistHrLetterRequest(hrLetterRequest);
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
            ObjectMessage objectMessage = session.createObjectMessage();
            HrProfileMsg hrProfileMsg = new HrProfileMsg();
            hrProfileMsg.setEmpNo(13764L);
            hrProfileMsg.setEntityName("HrLetterRequest");
            hrProfileMsg.setSendDate(new Date());
            hrProfileMsg.setSenderNo(hrLetterRequest.getEmpNo());
            hrProfileMsg.setMsgId(hrLetterRequest.getId());
            hrProfileMsg.setMsgType(1L);
            objectMessage.setObject(hrProfileMsg);
            messageProducer.send(objectMessage);
            jedis.hset("alerts:" + 13764L, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
            jedis.expire("alerts:" + 13764L, Integer.MAX_VALUE);
            System.out.println("message sent");
        } catch (IOException ex) {
            Logger.getLogger(hr_letter_request.class.getName()).log(Level.SEVERE, null, ex);
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

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «·ÿ·» »‰Ã«Õ");
        fc.addMessage(null, fm);
        hrLetterRequestList = sessionBean.hrLetterRequestList(Long.parseLong(usercode));
        hrLetterRequest = new HrLetterRequest();
        reason = null;
    }

    public void update(RowEditEvent event) {
        HrLetterRequest hrLetterRequest1 = (HrLetterRequest) event.getObject();
        FacesContext fc = FacesContext.getCurrentInstance();
        sessionBean.mergeHrLetterRequest(hrLetterRequest1);
        ObjectMapper objectMapper = new ObjectMapper();
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrLetterRequest1.getEmpNo());
            for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                    Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                    for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                        try {
                            HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                            if (hrProfileMsg.getEntityName().equals("HrLetterRequest") && hrProfileMsg.getMsgId().equals(hrLetterRequest1.getId()) && hrLetterRequest1.getCanceled().equals(new Character('Y'))) {
                                hrProfileMsg.setReadDone('Y');
                                jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                            } else if (hrProfileMsg.getEntityName().equals("HrLetterRequest") && hrProfileMsg.getMsgId().equals(hrLetterRequest1.getId()) && (hrLetterRequest1.getCanceled().equals(new Character('N')) || hrLetterRequest1.getCanceled() == null)) {
                                hrProfileMsg.setReadDone(null);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(hr_letter_request.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        if (hrLetterRequest1.getCanceled().equals(new Character('Y'))) {
            sessionBean.updateReadDoneMsg("HrLetterRequest", hrLetterRequest1.getId(), 'Y', null);
        } else {
            sessionBean.updateReadDoneMsg("HrLetterRequest", hrLetterRequest1.getId(), 'N', null);
        }
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «· ⁄œÌ· »‰Ã«Õ");
        fc.addMessage(null, fm);
    }
}

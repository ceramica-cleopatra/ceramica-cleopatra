/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrShiftChangeRequest;
import e.HrShiftRequestDt;
import e.HrUsers;
import java.io.IOException;
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
public class shift_confirm {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrShiftRequestDt> hrShidtRequestDtList = new ArrayList<HrShiftRequestDt>();
    private String usercode;
    private HrEmpInfo hrEmpInfo = new HrEmpInfo();

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
        hrShidtRequestDtList = sessionBean.getHrShiftRequestDtList(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
    }

    /** Creates a new instance of shift_confirm */
    public shift_confirm() {
    }

    public List<HrShiftRequestDt> getHrShidtRequestDtList() {
        return hrShidtRequestDtList;
    }

    public void setHrShidtRequestDtList(List<HrShiftRequestDt> hrShidtRequestDtList) {
        this.hrShidtRequestDtList = hrShidtRequestDtList;
    }

    public void update(RowEditEvent e) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HrShiftRequestDt hrShiftRequestDt = (HrShiftRequestDt) e.getObject();
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        HrShiftChangeRequest hrShiftChangeRequest = sessionBean.getHrShiftChangeRequestById(hrShiftRequestDt.getReqId());
        hrShiftChangeRequest.setConfirmDate(new Date());
        if (hrShiftRequestDt.getMngConfirm().equals("Y")) {
            hrShiftChangeRequest.setMngNo(hrEmpInfo);
            hrShiftChangeRequest.setMngConfirm("Y");
            sessionBean.merge_shift_request(hrShiftChangeRequest);
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                if (jedis.hgetAll("managers:" + hrShiftChangeRequest.getEmpNo().getEmpNo()) != null && !jedis.hgetAll("managers:" + hrShiftChangeRequest.getEmpNo().getEmpNo()).isEmpty()) {
                    Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrShiftChangeRequest.getEmpNo().getEmpNo());
                    ObjectMapper objectMapper = new ObjectMapper();
                    for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                        if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                            Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                            for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                                try {
                                    HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                    if (hrProfileMsg.getMsgId().equals(hrShiftChangeRequest.getId()) && hrProfileMsg.getEntityName().equals("HrShiftChangeRequest")) {
                                        hrProfileMsg.setReadDone('Y');
                                        jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                        jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(shift_confirm.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                    sessionBean.updateReadDoneMsg("HrShiftChangeRequest", hrShiftChangeRequest.getId(), 'Y', null);
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }

            try {
                Context ctx = new InitialContext();
                ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                connection = connectionFactory.createConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                messageProducer = session.createProducer(queue);
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("HrShiftChangeRequest");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrShiftChangeRequest.getEmpNo().getEmpNo());
                hrProfileMsg.setSenderNo(hrShiftChangeRequest.getMngNo().getEmpNo());
                hrProfileMsg.setMsgId(hrShiftChangeRequest.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove('Y');
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("msgs:" + hrShiftChangeRequest.getEmpNo().getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("msgs:" + hrShiftChangeRequest.getEmpNo().getEmpNo(), Integer.MAX_VALUE);
                messageProducer.send(objectMessage);
                System.out.println("message sent");

            } catch (IOException ex) {
                Logger.getLogger(shift_confirm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (JMSException x) {
                x.printStackTrace();
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
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈⁄ „«œ ÿ·»  €ÌÌ— «·‘Ì› "));
        } else if (hrShiftRequestDt.getMngConfirm().equals("N")) {
            hrShiftChangeRequest.setMngNo(sessionBean.finduserbyid(Long.parseLong(usercode)));
            hrShiftChangeRequest.setMngConfirm("N");
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
                hrProfileMsg.setEntityName("HrShiftChangeRequest");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrShiftChangeRequest.getEmpNo().getEmpNo());
                hrProfileMsg.setSenderNo(hrShiftChangeRequest.getMngNo().getEmpNo());
                hrProfileMsg.setMsgId(hrShiftChangeRequest.getId());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setMsgApprove('N');
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("msgs:" + hrShiftChangeRequest.getEmpNo().getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("msgs:" + hrShiftChangeRequest.getEmpNo().getEmpNo(), Integer.MAX_VALUE);
                messageProducer.send(objectMessage);
                System.out.println("message sent");
            } catch (IOException ex) {
                Logger.getLogger(shift_confirm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (JMSException x) {
                x.printStackTrace();
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
            sessionBean.merge_shift_request(hrShiftChangeRequest);
            if (jedis.hgetAll("managers:" + hrShiftChangeRequest.getEmpNo().getEmpNo()) != null && !jedis.hgetAll("managers:" + hrShiftChangeRequest.getEmpNo().getEmpNo()).isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrShiftChangeRequest.getEmpNo().getEmpNo());
                for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                    if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            try {
                                HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                if (hrProfileMsg.getMsgId().equals(hrShiftChangeRequest.getId()) && hrProfileMsg.getEntityName().equals("HrShiftChangeRequest")) {
                                    hrProfileMsg.setReadDone('Y');
                                    jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(shift_confirm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                sessionBean.updateReadDoneMsg("HrShiftChangeRequest", hrShiftChangeRequest.getId(), 'Y', null);
            }
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ ÿ·»  €ÌÌ— «·‘Ì› "));
        }

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrAdvanceRequest;
import e.HrEmpInfo;
import e.HrFundAdvanceSetup;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
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
 * @author a.abbas
 */
@ManagedBean
@ViewScoped
public class AdvanceGuaranteeApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrAdvanceRequest> hrAdvanceRequestList = new ArrayList<HrAdvanceRequest>();
    private HrFundAdvanceSetup hrFundAdvanceSetup = new HrFundAdvanceSetup();

    @PostConstruct
    public void init() {
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
        hrFundAdvanceSetup = sessionBean.findAdvanceSetup();
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("currentDay" + currentDay);
        if (currentDay >= hrFundAdvanceSetup.getFromDay() && currentDay <= hrFundAdvanceSetup.getToDay()) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            hrAdvanceRequestList = sessionBean.findAdvanceRequestNeedGuaranteeApprove(hrEmpInfo, calendar.getTime());
        }
    }

    public void update(RowEditEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HrAdvanceRequest hrAdvanceRequest = (HrAdvanceRequest) event.getObject();
        sessionBean.updateReadDoneMsg("HrAdvanceRequestGuarantee", hrAdvanceRequest.getId(), 'Y', null);
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            if (jedis.hgetAll("alerts:" + hrEmpInfo.getEmpNo()) != null && !jedis.hgetAll("alerts:" + hrEmpInfo.getEmpNo()).isEmpty()) {
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpInfo.getEmpNo());
                ObjectMapper objectMapper = new ObjectMapper();
                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                    try {
                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                        if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestGuarantee") && hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId())) {
                            hrProfileMsg.setReadDone('Y');
                            jedis.hset("alerts:" + hrEmpInfo.getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                                    + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("alerts:" + hrEmpInfo.getEmpNo(), Integer.MAX_VALUE);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(AdvanceGuaranteeApprove.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        sendMessages(hrAdvanceRequest, 1);

        if (hrAdvanceRequest.getGuaranteeApprove() != null && hrAdvanceRequest.getGuaranteeApprove().equals(new Character('Y'))) {
            sendMessages(hrAdvanceRequest, 2);
        } else if (hrAdvanceRequest.getGuaranteeApprove() != null && hrAdvanceRequest.getGuaranteeApprove().equals(new Character('N'))) {
            cancelMessage(hrAdvanceRequest, 1);
            cancelMessage(hrAdvanceRequest, 2);
        }
        sessionBean.mergeAdvanceRequest(hrAdvanceRequest);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·—œ ⁄·Ï ÿ·» ÷„«‰ ”·›… »‰Ã«Õ"));
    }

    public void cancelMessage(HrAdvanceRequest hrAdvanceRequest, int type) {
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();

            if (jedis.hgetAll("alerts:" + hrAdvanceRequest.getEmpNo().getEmpNo()) != null
                    && !jedis.hgetAll("alerts:" + hrAdvanceRequest.getEmpNo().getEmpNo()).isEmpty()) {
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrAdvanceRequest.getEmpNo().getEmpNo());
                ObjectMapper objectMapper = new ObjectMapper();
                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                    HrProfileMsg hrProfileMsg = null;
                    try {
                        hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                        if (type == 1 && hrProfileMsg.getEntityName().equals("HrAdvanceRequestGuarantee") && hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId())) {
                            hrProfileMsg.setReadDone('Y');
                            jedis.hset("alerts:" + hrAdvanceRequest.getEmpNo().getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                                    + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("alerts:" + hrAdvanceRequest.getEmpNo().getEmpNo(), Integer.MAX_VALUE);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(AdvanceGuaranteeApprove.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (jedis.hgetAll("alerts:" + hrAdvanceRequest.getDeptMngNo().getEmpNo()) != null
                    && !jedis.hgetAll("alerts:" + hrAdvanceRequest.getDeptMngNo().getEmpNo()).isEmpty()) {
                {

                    Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrAdvanceRequest.getDeptMngNo().getEmpNo());
                    ObjectMapper objectMapper = new ObjectMapper();
                    for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                        HrProfileMsg hrProfileMsg = null;
                        try {
                            hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                            if (type == 2 && hrProfileMsg.getEntityName().equals("HrAdvanceRequestDeptMng") && hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId())) {
                                hrProfileMsg.setReadDone('Y');
                                jedis.hset("alerts:" + hrAdvanceRequest.getDeptMngNo().getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                                        + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrAdvanceRequest.getDeptMngNo().getEmpNo(), Integer.MAX_VALUE);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(AdvanceGuaranteeApprove.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sessionBean.updateReadDoneMsg("HrAdvanceRequestDeptMng", hrAdvanceRequest.getId(), 'Y', null);
                    }
                }

                if (type == 1) {
                    sessionBean.updateReadDoneMsg("HrAdvanceRequestGuarantee", hrAdvanceRequest.getId(), 'Y', null);
                } else {
                    sessionBean.updateReadDoneMsg("HrAdvanceRequestDeptMng", hrAdvanceRequest.getId(), 'Y', null);
                }
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void sendMessages(HrAdvanceRequest hrAdvanceRequest, int type) {
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
            hrProfileMsg.setSendDate(new Date());
            hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
            hrProfileMsg.setMsgId(hrAdvanceRequest.getId());

            if (type == 1) {
                hrProfileMsg.setMsgApprove(hrAdvanceRequest.getGuaranteeApprove());
                hrProfileMsg.setEntityName("HrAdvanceRequestGuarantee");
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setEmpNo(hrAdvanceRequest.getEmpNo().getEmpNo());
            } else if (type == 2) {
                hrProfileMsg.setEntityName("HrAdvanceRequestDeptMng");
                hrProfileMsg.setMsgType(1L);
                hrProfileMsg.setEmpNo(hrAdvanceRequest.getDeptMngNo().getEmpNo());
            }
            objectMessage.setObject(hrProfileMsg);
            ObjectMapper objectMapper = new ObjectMapper();
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                if (type == 1) {
                    try {
                        jedis.hset("msgs:" + hrAdvanceRequest.getEmpNo().getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                        jedis.expire("msgs:" + hrAdvanceRequest.getEmpNo().getEmpNo(), Integer.MAX_VALUE);
                    } catch (IOException ex) {
                        Logger.getLogger(AdvanceGuaranteeApprove.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        jedis.hset("alerts:" + hrAdvanceRequest.getDeptMngNo().getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                                + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                        jedis.expire("alerts:" + hrAdvanceRequest.getDeptMngNo().getEmpNo(), Integer.MAX_VALUE);
                    } catch (IOException ex) {
                        Logger.getLogger(AdvanceGuaranteeApprove.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
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
    }

    /** Creates a new instance of AdvanceGuaranteeApprove */
    public AdvanceGuaranteeApprove() {
    }

    public List<HrAdvanceRequest> getHrAdvanceRequestList() {
        return hrAdvanceRequestList;
    }

    public void setHrAdvanceRequestList(List<HrAdvanceRequest> hrAdvanceRequestList) {
        this.hrAdvanceRequestList = hrAdvanceRequestList;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }
}

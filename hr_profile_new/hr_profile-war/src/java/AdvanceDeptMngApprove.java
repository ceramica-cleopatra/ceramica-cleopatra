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
import jxl.biff.ContinueRecord;
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
public class AdvanceDeptMngApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private HrFundAdvanceSetup hrFundAdvanceSetup;
    private List<HrAdvanceRequest> hrAdvanceRequestList = new ArrayList<HrAdvanceRequest>();

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


        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("currentDay" + currentDay);
        hrFundAdvanceSetup = sessionBean.findAdvanceSetup();
        if (currentDay >= hrFundAdvanceSetup.getFromDay() && currentDay <= hrFundAdvanceSetup.getToDay()) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            hrAdvanceRequestList = sessionBean.findAdvanceRequestNeedDeptMngApprove(hrEmpInfo, calendar.getTime());
        }
    }

    public void update(RowEditEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HrAdvanceRequest hrAdvanceRequest = (HrAdvanceRequest) event.getObject();
        sessionBean.mergeAdvanceRequest(hrAdvanceRequest);


        Jedis jedis = null;
        try {

            jedis = WorkerBean.pool.getResource();
            if (jedis.hgetAll("managers:" + hrAdvanceRequest.getEmpNo().getEmpNo()) != null && !jedis.hgetAll("managers:" + hrAdvanceRequest.getEmpNo().getEmpNo()).isEmpty()) {
                Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrAdvanceRequest.getEmpNo().getEmpNo());
                ObjectMapper objectMapper = new ObjectMapper();
                for (String hrEmpMangersAsString : hrEmpManagersMap.values()) {
                    if (jedis.hgetAll("alerts:" + hrEmpMangersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpMangersAsString).isEmpty()) {
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpMangersAsString);
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            HrProfileMsg hrProfileMsg = null;
                            try {
                                hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                            } catch (IOException ex) {
                                Logger.getLogger(AdvanceDeptMngApprove.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId()) && hrProfileMsg.getEntityName().equals("HrAdvanceRequestDeptMng")) {
                                hrProfileMsg.setReadDone('Y');
                                try {
                                    jedis.hset("alerts:" + hrEmpMangersAsString, hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType() + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + hrEmpMangersAsString, Integer.MAX_VALUE);
                                } catch (IOException ex) {
                                    Logger.getLogger(AdvanceDeptMngApprove.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        sessionBean.updateReadDoneMsg("HrAdvanceRequestDeptMng", hrAdvanceRequest.getId(), 'Y', null);




        sendMessages(hrAdvanceRequest, 1);
        if (hrAdvanceRequest.getDeptMngApprove() != null && hrAdvanceRequest.getDeptMngApprove().equals(new Character('Y'))) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  «·„Ê«›ﬁ… ⁄·Ï ÿ·» ”·›… „‰ «·’‰œÊﬁ »‰Ã«Õ"));
            if (hrAdvanceRequest.getDeptMngApprove() != null && hrAdvanceRequest.getDeptMngApprove().equals('Y') && (hrAdvanceRequest.getCancel() == null || hrAdvanceRequest.getCancel().equals('N'))) {
                sendMessages(hrAdvanceRequest, 2);
            }
        } else if (hrAdvanceRequest.getDeptMngApprove() != null && hrAdvanceRequest.getDeptMngApprove().equals(new Character('N'))) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ ÿ·» ”·›… „‰ «·’‰œÊﬁ »‰Ã«Õ"));
            cancelMessage(hrAdvanceRequest);
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈·€«¡ «·≈⁄ „«œ »‰Ã«Õ"));
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
            if (type == 1) {
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
                hrProfileMsg.setMsgId(hrAdvanceRequest.getId());

                hrProfileMsg.setEmpNo(hrAdvanceRequest.getEmpNo().getEmpNo());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setEntityName("HrAdvanceRequestDeptMng");
                hrProfileMsg.setMsgApprove(hrAdvanceRequest.getDeptMngApprove());
                ObjectMapper objectMapper = new ObjectMapper();
                Jedis jedis = null;
                try {
                    jedis = WorkerBean.pool.getResource();
                    jedis.hset("msgs:" + hrAdvanceRequest.getEmpNo().getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                            + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                    jedis.expire("msgs:" + hrAdvanceRequest.getEmpNo().getEmpNo(), Integer.MAX_VALUE);
                } catch (IOException ex) {
                    Logger.getLogger(AdvanceDeptMngApprove.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
                objectMessage.setObject(hrProfileMsg);
                messageProducer.send(objectMessage);
            } else {
                Long[] respArray = {hrFundAdvanceSetup.getResponsibleCode(), hrFundAdvanceSetup.getResponsible2(), hrFundAdvanceSetup.getResponsible3()};
                for (int i = 0; i < respArray.length; i++) {
                    if (respArray[i] == null) {
                        continue;
                    }
                    ObjectMessage objectMessage = session.createObjectMessage();
                    HrProfileMsg hrProfileMsg = new HrProfileMsg();
                    hrProfileMsg.setSendDate(new Date());
                    hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
                    hrProfileMsg.setMsgId(hrAdvanceRequest.getId());
                    hrProfileMsg.setEmpNo(respArray[i]);
                    hrProfileMsg.setMsgType(1L);
                    hrProfileMsg.setEntityName("HrAdvanceRequestResponsible");
                    hrProfileMsg.setMsgApprove(hrAdvanceRequest.getDeptMngApprove());
                    ObjectMapper objectMapper = new ObjectMapper();
                    Jedis jedis = null;
                    try {
                        jedis = WorkerBean.pool.getResource();
                        jedis.hset("alerts:" + hrFundAdvanceSetup.getResponsibleCode(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                                + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                        jedis.expire("alerts:" + hrFundAdvanceSetup.getResponsibleCode(), Integer.MAX_VALUE);
                    } catch (IOException ex) {
                        Logger.getLogger(AdvanceDeptMngApprove.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        if (jedis != null) {
                            jedis.close();
                        }
                    }
                    objectMessage.setObject(hrProfileMsg);
                    messageProducer.send(objectMessage);
                }
            }

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

    public void cancelMessage(HrAdvanceRequest hrAdvanceRequest) {
        sessionBean.updateReadDoneMsg("HrAdvanceRequestDeptMng", hrAdvanceRequest.getId(), 'Y', hrAdvanceRequest.getEmpNo().getEmpNo());
        sessionBean.updateReadDoneMsg("HrAdvanceRequestResponsible", hrAdvanceRequest.getId(), 'Y', null);
        sessionBean.updateReadDoneMsg("HrAdvanceRequestDeptMng", hrAdvanceRequest.getId(), 'Y', null);
        Long[] respArray = {hrFundAdvanceSetup.getResponsibleCode(), hrFundAdvanceSetup.getResponsible2(), hrFundAdvanceSetup.getResponsible3()};
        for (int i = 0; i < respArray.length; i++) {
            if (respArray[i] == null) {
                continue;
            }
            Jedis jedis = null;
            try {

                jedis = WorkerBean.pool.getResource();
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + respArray[i]);
                ObjectMapper objectMapper = new ObjectMapper();
                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                    HrProfileMsg hrProfileMsg = null;
                    try {
                        hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                    } catch (IOException ex) {
                        Logger.getLogger(AdvanceDeptMngApprove.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId()) && hrProfileMsg.getEntityName().equals("HrAdvanceRequestResponsible")) {
                        hrProfileMsg.setReadDone('Y');
                        try {
                            jedis.hset("alerts:" + respArray[i], hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                                    + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("alerts:" + respArray[i], Integer.MAX_VALUE);
                        } catch (IOException ex) {
                            Logger.getLogger(AdvanceDeptMngApprove.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }

        }
    }

    public List<HrAdvanceRequest> getHrAdvanceRequestList() {
        return hrAdvanceRequestList;
    }

    public void setHrAdvanceRequestList(List<HrAdvanceRequest> hrAdvanceRequestList) {
        this.hrAdvanceRequestList = hrAdvanceRequestList;
    }

    /** Creates a new instance of AdvanceDeptMngApprove */
    public AdvanceDeptMngApprove() {
    }
}

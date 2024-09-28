/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrBorrowFundRequest;
import e.HrEmpInfo;
import e.HrFundBorrowSetup;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
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
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.primefaces.event.RowEditEvent;
import sb.SessionBeanLocal;
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
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class FundBorrowDeptMngApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo = new HrEmpInfo();
    private List<HrBorrowFundRequest> deptMngRequestList = new ArrayList<HrBorrowFundRequest>();
    private HrFundBorrowSetup hrFundBorrowSetup = new HrFundBorrowSetup();

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
        deptMngRequestList = sessionBean.findDeptMngRequests(hrEmpInfo);
        hrFundBorrowSetup = sessionBean.findBorrowSetup();
    }

    public void update(RowEditEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HrBorrowFundRequest hrBorrowFundRequest = (HrBorrowFundRequest) event.getObject();
        sessionBean.mergeFundBorrow(hrBorrowFundRequest);

        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();

            if (jedis.hgetAll("managers:" + hrBorrowFundRequest.getEmpNo().getEmpNo()) != null && !jedis.hgetAll("managers:" + hrBorrowFundRequest.getEmpNo().getEmpNo()).isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrBorrowFundRequest.getEmpNo().getEmpNo());
                for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                    if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            try {
                                HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                if (hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId()) && hrProfileMsg.getEntityName().equals("HrBorrowFundRequestDeptMng")) {
                                    hrProfileMsg.setReadDone('Y');
                                    jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(FundBorrowDeptMngApprove.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                sessionBean.updateReadDoneMsg("HrBorrowFundRequestDeptMng", hrBorrowFundRequest.getId(), 'Y', null);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        sendMessages(hrBorrowFundRequest, 1);
        if (hrBorrowFundRequest.getDeptMngConfirm() != null && hrBorrowFundRequest.getDeptMngConfirm().equals(new Character('Y'))) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  «·„Ê«›ﬁ… ⁄·Ï ÿ·» ”·›… „‰ «·’‰œÊﬁ »‰Ã«Õ"));
            if (hrBorrowFundRequest.getDeptMngConfirm() != null && hrBorrowFundRequest.getDeptMngConfirm().equals('Y') && (hrBorrowFundRequest.getCancel() == null || hrBorrowFundRequest.getCancel().equals('N'))) {
                sendMessages(hrBorrowFundRequest, 2);
                hrBorrowFundRequest.setNewReplyFlag('Y');
            }
        } else if (hrBorrowFundRequest.getDeptMngConfirm() != null && hrBorrowFundRequest.getDeptMngConfirm().equals(new Character('N'))) {
            cancelMessage(hrBorrowFundRequest);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ ÿ·» ”·›… „‰ «·’‰œÊﬁ »‰Ã«Õ"));
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈·€«¡ «·≈⁄ „«œ »‰Ã«Õ"));
        }
    }

    public void sendMessages(HrBorrowFundRequest hrBorrowFundRequest, int type) {
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
                hrProfileMsg.setMsgId(hrBorrowFundRequest.getId());
                hrProfileMsg.setEmpNo(hrBorrowFundRequest.getEmpNo().getEmpNo());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setEntityName("HrBorrowFundRequestDeptMng");
                hrProfileMsg.setMsgApprove(hrBorrowFundRequest.getDeptMngConfirm());
                Jedis jedis = null;
                try {
                    jedis = WorkerBean.pool.getResource();
                    jedis.hset("msgs:" + hrBorrowFundRequest.getEmpNo().getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                    jedis.expire("msgs:" + hrBorrowFundRequest.getEmpNo().getEmpNo(), Integer.MAX_VALUE);
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
                objectMessage.setObject(hrProfileMsg);
                messageProducer.send(objectMessage);
            } else {
                Long[] respArray = {hrFundBorrowSetup.getResponsibleCode(), hrFundBorrowSetup.getResponsible2(), hrFundBorrowSetup.getResponsible3()};
                for (int i = 0; i < respArray.length; i++) {
                    if (respArray[i] == null) {
                        continue;
                    }
                    ObjectMessage objectMessage = session.createObjectMessage();
                    HrProfileMsg hrProfileMsg = new HrProfileMsg();
                    hrProfileMsg.setSendDate(new Date());
                    hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
                    hrProfileMsg.setMsgId(hrBorrowFundRequest.getId());
                    hrProfileMsg.setEmpNo(respArray[i]);
                    hrProfileMsg.setMsgType(1L);
                    hrProfileMsg.setEntityName("HrBorrowFundRequestResponsible");
                    Jedis jedis = null;
                    try {
                        jedis = WorkerBean.pool.getResource();
                        jedis.hset("alerts:" + respArray[i], hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                        jedis.expire("alerts:" + respArray[i], Integer.MAX_VALUE);
                    } finally {
                        if (jedis != null) {
                            jedis.close();
                        }
                    }
                    hrProfileMsg.setMsgApprove(hrBorrowFundRequest.getDeptMngConfirm());
                    objectMessage.setObject(hrProfileMsg);
                    messageProducer.send(objectMessage);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FundBorrowDeptMngApprove.class.getName()).log(Level.SEVERE, null, ex);
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

    public void cancelMessage(HrBorrowFundRequest hrBorrowFundRequest) {
        Long[] respArray = {hrFundBorrowSetup.getResponsibleCode(), hrFundBorrowSetup.getResponsible2(), hrFundBorrowSetup.getResponsible3()};
        sessionBean.updateReadDoneMsg("HrBorrowFundRequestResponsible", hrBorrowFundRequest.getId(), 'Y', null);
        for (int i = 0; i < respArray.length; i++) {
            if (respArray[i] == null) {
                continue;
            }
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + respArray[i]);
                ObjectMapper objectMapper = new ObjectMapper();
                for (String hrProfileMsgAsSrting : hrProfileMsgMap.values()) {
                    try {
                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsSrting, HrProfileMsg.class);
                        if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestResponsible") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                            hrProfileMsg.setReadDone('Y');
                            jedis.hset("alerts:" + respArray[i], hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("alerts:" + respArray[i], Integer.MAX_VALUE);
                            break;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FundBorrowDeptMngApprove.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }

    /** Creates a new instance of FundBorrowDeptMngApprove */
    public FundBorrowDeptMngApprove() {
    }

    public List<HrBorrowFundRequest> getDeptMngRequestList() {
        return deptMngRequestList;
    }

    public void setDeptMngRequestList(List<HrBorrowFundRequest> deptMngRequestList) {
        this.deptMngRequestList = deptMngRequestList;
    }
}

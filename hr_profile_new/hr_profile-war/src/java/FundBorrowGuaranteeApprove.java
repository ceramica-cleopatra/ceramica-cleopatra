/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrBorrowFundRequest;
import e.HrEmpInfo;
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
public class FundBorrowGuaranteeApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrBorrowFundRequest> borrowFundRequestList = new ArrayList<HrBorrowFundRequest>();

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
        borrowFundRequestList = sessionBean.findAllGuaranteeRequests(hrEmpInfo);
    }

    public void update(RowEditEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HrBorrowFundRequest hrBorrowFundRequest = (HrBorrowFundRequest) event.getObject();
        if (hrBorrowFundRequest.getGuarantee1().getEmpNo() == hrEmpInfo.getEmpNo()) {
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee1", hrBorrowFundRequest.getId(), 'Y', null);
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                if (jedis.hgetAll("alerts:" + hrEmpInfo.getEmpNo()) != null && !jedis.hgetAll("alerts:" + hrEmpInfo.getEmpNo()).isEmpty()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpInfo.getEmpNo());
                    for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                        try {
                            HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                            if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee1") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                                hrProfileMsg.setReadDone('Y');
                                jedis.hset("alerts:" + hrEmpInfo.getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrEmpInfo.getEmpNo(), Integer.MAX_VALUE);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(FundBorrowGuaranteeApprove.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            sendMessages(hrBorrowFundRequest, 1);
        } else if (hrBorrowFundRequest.getGuarantee2().getEmpNo() == hrEmpInfo.getEmpNo()) {
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                sessionBean.updateReadDoneMsg("HrBorrowFundRequestGuarantee2", hrBorrowFundRequest.getId(), 'Y', null);
                if (jedis.hgetAll("alerts" + hrEmpInfo.getEmpNo()) != null && !jedis.hgetAll("alerts" + hrEmpInfo.getEmpNo()).isEmpty()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts" + hrEmpInfo.getEmpNo());
                    for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                        try {
                            HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                            if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee2") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                                hrProfileMsg.setReadDone('Y');
                                jedis.hset("alerts" + hrEmpInfo.getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrEmpInfo.getEmpNo(), Integer.MAX_VALUE);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(FundBorrowGuaranteeApprove.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            sendMessages(hrBorrowFundRequest, 2);
        }
        if (hrBorrowFundRequest.getGuarantee1Confirm() != null && hrBorrowFundRequest.getGuarantee2Confirm() != null
                && hrBorrowFundRequest.getGuarantee1Confirm().equals(new Character(('Y'))) && hrBorrowFundRequest.getGuarantee2Confirm().equals(new Character(('Y')))) {
            sendMessages(hrBorrowFundRequest, 3);
        } else if (hrBorrowFundRequest.getGuarantee1Confirm() != null && hrBorrowFundRequest.getGuarantee2Confirm() != null
                && (hrBorrowFundRequest.getGuarantee1Confirm().equals(new Character('N')) || hrBorrowFundRequest.getGuarantee2Confirm().equals(new Character('N')))) {
            cancelMessage(hrBorrowFundRequest);
        }
        sessionBean.mergeFundBorrow(hrBorrowFundRequest);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·—œ ⁄·Ï ÿ·» ÷„«‰ ”·›… »‰Ã«Õ"));
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
            ObjectMessage objectMessage = session.createObjectMessage();
            HrProfileMsg hrProfileMsg = new HrProfileMsg();
            hrProfileMsg.setSendDate(new Date());
            hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
            hrProfileMsg.setMsgId(hrBorrowFundRequest.getId());

            if (type == 1) {
                hrProfileMsg.setMsgApprove(hrBorrowFundRequest.getGuarantee1Confirm());
                hrProfileMsg.setEntityName("HrBorrowFundRequestGuarantee1");
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setEmpNo(hrBorrowFundRequest.getEmpNo().getEmpNo());
            } else if (type == 2) {
                hrProfileMsg.setEntityName("HrBorrowFundRequestGuarantee2");
                hrProfileMsg.setMsgApprove(hrBorrowFundRequest.getGuarantee2Confirm());
                hrProfileMsg.setMsgType(2L);
                hrProfileMsg.setEmpNo(hrBorrowFundRequest.getEmpNo().getEmpNo());
            } else if (type == 3) {
                hrProfileMsg.setEntityName("HrBorrowFundRequestDeptMng");
                hrProfileMsg.setMsgType(1L);
                hrProfileMsg.setEmpNo(hrBorrowFundRequest.getDeptMng().getEmpNo());
            }
            objectMessage.setObject(hrProfileMsg);
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                if (type == 1 || type == 2) {
                    jedis.hset("msgs:" + hrBorrowFundRequest.getDeptMng().getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                    jedis.expire("alerts:" + hrBorrowFundRequest.getEmpNo().getEmpNo(), Integer.MAX_VALUE);
                } else {
                    jedis.hset("alerts:" + hrBorrowFundRequest.getEmpNo().getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                    jedis.expire("alerts:" + hrBorrowFundRequest.getEmpNo().getEmpNo(), Integer.MAX_VALUE);
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            messageProducer.send(objectMessage);
            System.out.println("message sent");
        } catch (IOException ex) {
            Logger.getLogger(FundBorrowGuaranteeApprove.class.getName()).log(Level.SEVERE, null, ex);
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
        ObjectMapper objectMapper = new ObjectMapper();
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrBorrowFundRequest.getDeptMng().getEmpNo());
            for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                try {
                    HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                    if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestDeptMng") && hrProfileMsg.getMsgId().equals(hrBorrowFundRequest.getId())) {
                        hrProfileMsg.setReadDone('Y');

                        jedis.hset("alerts:" + hrBorrowFundRequest.getDeptMng().getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                        jedis.expire("alerts:" + hrBorrowFundRequest.getDeptMng().getEmpNo(), Integer.MAX_VALUE);

                        return;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FundBorrowGuaranteeApprove.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        sessionBean.updateReadDoneMsg("HrBorrowFundRequestDeptMng", hrBorrowFundRequest.getId(), 'Y', null);
    }

    /** Creates a new instance of FundBorrowGuaranteeApprove */
    public FundBorrowGuaranteeApprove() {
    }

    public List<HrBorrowFundRequest> getBorrowFundRequestList() {
        return borrowFundRequestList;
    }

    public void setBorrowFundRequestList(List<HrBorrowFundRequest> borrowFundRequestList) {
        this.borrowFundRequestList = borrowFundRequestList;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }
}

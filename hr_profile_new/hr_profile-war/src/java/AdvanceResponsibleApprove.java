/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrAdvanceRequest;
import e.HrAdvanceZamalaDt;
import e.HrAdvanceZamalaHd;
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
 * @author a.abbas
 */
@ManagedBean
@ViewScoped
public class AdvanceResponsibleApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrAdvanceRequest> hrAdvanceRequestList = new ArrayList<HrAdvanceRequest>();
    private HrFundAdvanceSetup hrFundAdvanceSetup;
    private Object[] advanceMonthlyBudget;

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
        hrFundAdvanceSetup = sessionBean.findAdvanceSetup();
        advanceMonthlyBudget = sessionBean.findFundAdvanceMonthlyBudget();
        if (currentDay >= hrFundAdvanceSetup.getFromDay() && currentDay <= hrFundAdvanceSetup.getToDay()) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            hrAdvanceRequestList = sessionBean.findAdvanceRequestNeedRespApprove(calendar.getTime());
        }
    }

    public void update(RowEditEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HrAdvanceRequest hrAdvanceRequest = (HrAdvanceRequest) event.getObject();
        hrAdvanceRequest.setFundRespNo(hrEmpInfo);
        sessionBean.updateReadDoneMsg("HrAdvanceRequestResponsible", hrAdvanceRequest.getId(), 'Y', null);
        Long[] respArray = {hrFundAdvanceSetup.getResponsibleCode(), hrFundAdvanceSetup.getResponsible2(), hrFundAdvanceSetup.getResponsible3()};
        for (int i = 0; i < respArray.length; i++) {
            if (respArray[i] == null) {
                continue;
            }
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();

                if (jedis.hgetAll("alerts:" + respArray[i].toString()) != null && !jedis.hgetAll("alerts:" + respArray[i].toString()).isEmpty()) {
                    Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + respArray[i].toString());
                    ObjectMapper objectMapper = new ObjectMapper();
                    for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                        try {
                            HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                            if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestResponsible") && hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId())) {
                                hrProfileMsg.setReadDone('Y');
                                jedis.hset("alerts:" + hrProfileMsg.getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                                        + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrProfileMsg.getEmpNo(), Integer.MAX_VALUE);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(AdvanceResponsibleApprove.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        sendMessages(hrAdvanceRequest);
        sessionBean.mergeAdvanceRequest(hrAdvanceRequest);
        if (hrAdvanceRequest.getRespApprove() != null && hrAdvanceRequest.getRespApprove().equals(new Character('Y'))) {
            HrAdvanceZamalaHd hrAdvanceZamalaHd = new HrAdvanceZamalaHd();
            hrAdvanceZamalaHd.setEmpNo(hrAdvanceRequest.getEmpNo());
            hrAdvanceZamalaHd.setReqId(hrAdvanceRequest.getId());
            hrAdvanceZamalaHd.setTrnsDate(new Date());
            hrAdvanceZamalaHd.setGuaranteeNo(hrAdvanceRequest.getGuaranteeNo().getEmpNo());
            hrAdvanceZamalaHd.setSerial(sessionBean.findSerialOfAdvanceZamalalaHd());
            hrAdvanceZamalaHd.setNote("Created By Profile");
            List<HrAdvanceZamalaDt> dtList = new ArrayList<HrAdvanceZamalaDt>();
            HrAdvanceZamalaDt hrAdvanceZamalaDt = new HrAdvanceZamalaDt();
            hrAdvanceZamalaDt.setAmount(hrAdvanceRequest.getAmount());
            Calendar c = Calendar.getInstance();
            Long payMonth = Long.parseLong((c.get(Calendar.MONTH) + 1) + "");
            Long payYear = Long.parseLong(c.get(Calendar.YEAR) + "");
            hrAdvanceZamalaDt.setPayMonth(payMonth);
            hrAdvanceZamalaDt.setPayYear(payYear);
            dtList.add(hrAdvanceZamalaDt);
            hrAdvanceZamalaDt.setHrAdvanceZamalaHd(hrAdvanceZamalaHd);
            hrAdvanceZamalaHd.setHrAdvanceZamalaDtList(dtList);
            hrAdvanceZamalaHd.setMngNo(hrAdvanceRequest.getDeptMngNo().getEmpNo());
            sessionBean.persistAdvanceZamalaHd(hrAdvanceZamalaHd);
            advanceMonthlyBudget = sessionBean.findFundAdvanceMonthlyBudget();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  «·„Ê«›ﬁ… ⁄·Ï ÿ·» ”·›… „‰ «·’‰œÊﬁ »‰Ã«Õ"));
        } else if (hrAdvanceRequest.getRespApprove() != null && hrAdvanceRequest.getRespApprove().equals(new Character('N'))) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ ÿ·» ”·›… „‰ «·’‰œÊﬁ »‰Ã«Õ"));
        }
    }

    public void updateAll(ActionEvent event) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        hrFundAdvanceSetup = sessionBean.findAdvanceSetup();
        advanceMonthlyBudget = sessionBean.findFundAdvanceMonthlyBudget();
        if (currentDay >= hrFundAdvanceSetup.getFromDay() && currentDay <= hrFundAdvanceSetup.getToDay()) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            hrAdvanceRequestList = sessionBean.findAdvanceRequestNeedRespApprove(calendar.getTime());
        }
        FacesContext fc = FacesContext.getCurrentInstance();

        for (HrAdvanceRequest hrAdvanceRequest : hrAdvanceRequestList) {
            hrAdvanceRequest.setFundRespNo(hrEmpInfo);
            sessionBean.updateReadDoneMsg("HrAdvanceRequestResponsible", hrAdvanceRequest.getId(), 'Y', null);
            Long[] respArray = {hrFundAdvanceSetup.getResponsibleCode(), hrFundAdvanceSetup.getResponsible2(), hrFundAdvanceSetup.getResponsible3()};
            for (int i = 0; i < respArray.length; i++) {
                if (respArray[i] == null) {
                    continue;
                }

                Jedis jedis = null;
                try {
                    jedis = WorkerBean.pool.getResource();

                    if (jedis.hgetAll("alerts:" + respArray[i].toString()) != null && !jedis.hgetAll("alerts:" + respArray[i].toString()).isEmpty()) {
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + respArray[i].toString());
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            try {
                                HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestResponsible") && hrProfileMsg.getMsgId().equals(hrAdvanceRequest.getId())) {
                                    hrProfileMsg.setReadDone('Y');
                                    jedis.hset("alerts:" + hrProfileMsg.getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                                            + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + hrProfileMsg.getEmpNo(), Integer.MAX_VALUE);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(AdvanceResponsibleApprove.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
            }
            sendMessages(hrAdvanceRequest);
            hrAdvanceRequest.setRespApprove('Y');
            sessionBean.mergeAdvanceRequest(hrAdvanceRequest);
            HrAdvanceZamalaHd hrAdvanceZamalaHd = new HrAdvanceZamalaHd();
            hrAdvanceZamalaHd.setEmpNo(hrAdvanceRequest.getEmpNo());
            hrAdvanceZamalaHd.setReqId(hrAdvanceRequest.getId());
            hrAdvanceZamalaHd.setTrnsDate(new Date());
            hrAdvanceZamalaHd.setGuaranteeNo(hrAdvanceRequest.getGuaranteeNo().getEmpNo());
            hrAdvanceZamalaHd.setSerial(sessionBean.findSerialOfAdvanceZamalalaHd());
            List<HrAdvanceZamalaDt> dtList = new ArrayList<HrAdvanceZamalaDt>();
            HrAdvanceZamalaDt hrAdvanceZamalaDt = new HrAdvanceZamalaDt();
            hrAdvanceZamalaDt.setAmount(hrAdvanceRequest.getAmount());
            Calendar c = Calendar.getInstance();
            Long payMonth = Long.parseLong((c.get(Calendar.MONTH) + 1) + "");
            Long payYear = Long.parseLong(c.get(Calendar.YEAR) + "");
            hrAdvanceZamalaDt.setPayMonth(payMonth);
            hrAdvanceZamalaDt.setPayYear(payYear);
            dtList.add(hrAdvanceZamalaDt);
            hrAdvanceZamalaDt.setHrAdvanceZamalaHd(hrAdvanceZamalaHd);
            hrAdvanceZamalaHd.setHrAdvanceZamalaDtList(dtList);
            hrAdvanceZamalaHd.setMngNo(hrAdvanceRequest.getDeptMngNo().getEmpNo());
            sessionBean.persistAdvanceZamalaHd(hrAdvanceZamalaHd);


        }
        advanceMonthlyBudget = sessionBean.findFundAdvanceMonthlyBudget();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  «·„Ê«›ﬁ… ⁄·Ï ÿ·» ”·›… „‰ «·’‰œÊﬁ »‰Ã«Õ"));
    }

    public void sendMessages(HrAdvanceRequest hrAdvanceRequest) {
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
            hrProfileMsg.setEmpNo(hrAdvanceRequest.getEmpNo().getEmpNo());
            hrProfileMsg.setEntityName("HrAdvanceRequest");
            hrProfileMsg.setMsgType(2L);
            hrProfileMsg.setMsgApprove(hrAdvanceRequest.getRespApprove());
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                jedis.hset("msgs:" + hrAdvanceRequest.getEmpNo().getEmpNo(), hrProfileMsg.getEntityName() + hrProfileMsg.getMsgType()
                        + hrProfileMsg.getEmpNo() + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("msgs:" + hrAdvanceRequest.getEmpNo().getEmpNo(), Integer.MAX_VALUE);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            objectMessage.setObject(hrProfileMsg);
            messageProducer.send(objectMessage);
            System.out.println("message sent");
        } catch (IOException ex) {
            Logger.getLogger(AdvanceResponsibleApprove.class.getName()).log(Level.SEVERE, null, ex);
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

    /** Creates a new instance of AdvanceResponsibleApprove */
    public AdvanceResponsibleApprove() {
    }

    public List<HrAdvanceRequest> getHrAdvanceRequestList() {
        return hrAdvanceRequestList;
    }

    public void setHrAdvanceRequestList(List<HrAdvanceRequest> hrAdvanceRequestList) {
        this.hrAdvanceRequestList = hrAdvanceRequestList;
    }

    public Object[] getAdvanceMonthlyBudget() {
        return advanceMonthlyBudget;
    }

    public void setAdvanceMonthlyBudget(Object[] advanceMonthlyBudget) {
        this.advanceMonthlyBudget = advanceMonthlyBudget;
    }
}

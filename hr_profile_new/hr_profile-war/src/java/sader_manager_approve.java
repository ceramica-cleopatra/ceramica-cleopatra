/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkBranch;
import e.CrmkOrdrSader;
import e.CrmkOrdrSaderSetting;
import e.HrEmpCrmkBranch;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.faces.context.FacesContext;
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
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@SessionScoped
public class sader_manager_approve {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<CrmkOrdrSader> crmkOrdrSaderNotApproved = new ArrayList<CrmkOrdrSader>();
    private List<CrmkOrdrSader> crmkOrdrSaderApproved = new ArrayList<CrmkOrdrSader>();
    private List<SelectItem> storeList;
    private HrEmpInfo hrEmpInfo;
    private Long store;
    private String usercode;
    private long locId;
    private List<String> exceptionEmployess;
    private Date trnsDate;

    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        crmkOrdrSaderApproved = sessionBean.getSaderApproved(Long.parseLong(usercode));
        storeList = new ArrayList<SelectItem>();
        for (CrmkBranch crmkBranch : sessionBean.getStore()) {
            storeList.add(new SelectItem(crmkBranch.getId(), crmkBranch.getName()));
        }
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
        exceptionEmployess = Arrays.asList("14944", "14819", "13014", "10475", "12922", "12272", "12894");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -2);
        trnsDate = c.getTime();
        if (exceptionEmployess.contains(hrEmpInfo.getEmpNo() + "")) {
            locId = 124;
        } else {
            locId = hrEmpInfo.getLocId();
        }
        crmkOrdrSaderNotApproved = sessionBean.getSaderNotApproved(locId, trnsDate);
    }

    public Long getStore() {
        return store;
    }

    public void setStore(Long store) {
        if (store != null) {
            this.store = store;
        }
    }

    public List<CrmkOrdrSader> getCrmkOrdrSaderApproved() {
        return crmkOrdrSaderApproved;
    }

    public void setCrmkOrdrSaderApproved(List<CrmkOrdrSader> crmkOrdrSaderApproved) {
        this.crmkOrdrSaderApproved = crmkOrdrSaderApproved;
    }

    public List<SelectItem> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<SelectItem> storeList) {
        this.storeList = storeList;
    }

    /** Creates a new instance of sader_manager_approve */
    public sader_manager_approve() {
    }

    public List<CrmkOrdrSader> getCrmkOrdrSaderNotApproved() {
        return crmkOrdrSaderNotApproved;
    }

    public void approve(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Integer selected_row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ordr").toString());
        CrmkOrdrSader crmkOrdrSader = crmkOrdrSaderNotApproved.get(selected_row);
        if (store == null) {
            fc.addMessage(null, new FacesMessage("Œÿ√", "ÌÃ»  ÕœÌœ «·„Œ“‰"));
            return;
        }
        CrmkOrdrSaderSetting crmkOrdrSaderSetting = sessionBean.getCrmkOrdrSaderSettings(crmkOrdrSader.getBrnId().getId()).get(0);
        Date d1 = new Date();
        d1.setHours(0);
        d1.setMinutes(0);
        d1.setSeconds(0);
        Date d2 = new Date();
        d2.setHours(23);
        d2.setMinutes(59);
        d2.setSeconds(59);
        Long cnt = sessionBean.chkCrmkOrdrSaderCnt(crmkOrdrSader.getBrnId().getId(), d1, d2);
        Long sum = sessionBean.chkCrmkOrdrSaderSum(crmkOrdrSader.getBrnId().getId(), d1, d2);
        if (sum == null) {
            sum = 0L;
        }
        if (sum + crmkOrdrSader.getAmount() > crmkOrdrSaderSetting.getMaxAmount()) {
            fc.addMessage(null, new FacesMessage("Œÿ√", "·ﬁœ  ⁄œÌ  «·Õœ «·ÌÊ„Ï «·„”„ÊÕ »Â ·ﬁÌ„… «·ÿ·»Ì« "));
            return;
        }
        if (cnt + 1L > crmkOrdrSaderSetting.getMaxNo()) {
            fc.addMessage(null, new FacesMessage("Œÿ√", "·ﬁœ  ŒÿÌ  «·Õœ «·ÌÊ„Ï «·„”„ÊÕ »Â ·⁄œœ «·ÿ·»Ì« "));
            return;
        }
        crmkOrdrSader.setManagerApproveDate(new Date());
        crmkOrdrSader.setManagerApprove("Y");
        crmkOrdrSader.setManagerId(Long.parseLong(usercode));
        crmkOrdrSader.setStoreId(sessionBean.getCrmkBranchById(store));
        sessionBean.mergeCrmkOrdrSader(crmkOrdrSader);
        store = null;
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
            for (HrEmpCrmkBranch storeMng : sessionBean.findStoreManagers(crmkOrdrSader.getStoreId().getId())) {
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("CrmkOrdrSader");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(storeMng.getEmpNo());
                hrProfileMsg.setSenderNo(crmkOrdrSader.getManagerId());
                hrProfileMsg.setMsgId(crmkOrdrSader.getId());
                hrProfileMsg.setMsgType(1L);
                objectMessage.setObject(hrProfileMsg);
                jedis.hset("alerts:" + storeMng.getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("alerts:" + storeMng.getEmpNo(), Integer.MAX_VALUE);
                messageProducer.send(objectMessage);
            }
            System.out.println("message sent");
        } catch (IOException ex) {
            Logger.getLogger(sader_manager_approve.class.getName()).log(Level.SEVERE, null, ex);
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
        crmkOrdrSaderApproved = sessionBean.getSaderApproved(Long.parseLong(usercode));
        crmkOrdrSaderNotApproved = sessionBean.getSaderNotApproved(locId, trnsDate);
        fc.addMessage(null, new FacesMessage(" „ »‰Ã«Õ", " „ ≈⁄ „«œ «·ÿ·»Ì… »‰Ã«Õ"));
    }

    public void refuse(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Integer selected_row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ordr").toString());
        CrmkOrdrSader crmkOrdrSader = crmkOrdrSaderApproved.get(selected_row);

        List<HrEmpCrmkBranch> hrEmpCrmkBranchs;
        try {
            hrEmpCrmkBranchs = sessionBean.findStoreManagers(crmkOrdrSader.getStoreId().getId());
        } catch (NullPointerException e) {
            hrEmpCrmkBranchs = null;
        }

        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            if (hrEmpCrmkBranchs != null && hrEmpCrmkBranchs.size() > 0) {
                for (HrEmpCrmkBranch hrEmpCrmkBranch : sessionBean.findStoreManagers(crmkOrdrSader.getStoreId().getId())) {
                    if (jedis.hgetAll("alerts:" + hrEmpCrmkBranch.getEmpNo()) != null && !jedis.hgetAll("alerts:" + hrEmpCrmkBranch.getEmpNo()).isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpCrmkBranch.getEmpNo());
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            try {
                                HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                if (hrProfileMsg.getMsgId().equals(crmkOrdrSader.getId()) && hrProfileMsg.getEntityName().equals("CrmkOrdrSader")) {
                                    hrProfileMsg.setReadDone('Y');
                                    jedis.hset("alerts:" + hrEmpCrmkBranch.getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + hrEmpCrmkBranch.getEmpNo(), Integer.MAX_VALUE);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(sader_manager_approve.class.getName()).log(Level.SEVERE, null, ex);
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
        sessionBean.updateReadDoneMsg("CrmkOrdrSader", crmkOrdrSader.getId(), 'Y', null);

        crmkOrdrSader.setManagerApprove("N");
        crmkOrdrSader.setManagerApproveDate(null);
        crmkOrdrSader.setManagerId(null);
        crmkOrdrSader.setStoreId(null);
        sessionBean.mergeCrmkOrdrSader(crmkOrdrSader);
        store = null;
        crmkOrdrSaderApproved = sessionBean.getSaderApproved(Long.parseLong(usercode));
        crmkOrdrSaderNotApproved = sessionBean.getSaderNotApproved(locId, trnsDate);
        fc.addMessage(null, new FacesMessage(" „ »‰Ã«Õ", " „ ≈·€«¡ ≈⁄ „«œ «·ÿ·»Ì… »‰Ã«Õ"));
    }

    public void setCrmkOrdrSaderNotApproved(List<CrmkOrdrSader> crmkOrdrSaderNotApproved) {
        this.crmkOrdrSaderNotApproved = crmkOrdrSaderNotApproved;
    }
}

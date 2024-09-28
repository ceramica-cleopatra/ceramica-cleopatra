/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrDutyTrnsDt;
import e.HrDutyTrnsHdVu;
import e.HrEmpInfo;
import e.HrProfileMsg;
import java.io.IOException;
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
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@RequestScoped
public class DutyApproval {

    @EJB
    private SessionBeanLocal sessionBean;

    /** Creates a new instance of DutyApproval */
    public DutyApproval() {
    }
    private HrEmpInfo hrEmpInfo;
    private List<HrDutyTrnsHdVu> hrDutyTrnsHdList;
    private boolean mainCheckboxValue;

    @PostConstruct
    public void init() {
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        hrDutyTrnsHdList = sessionBean.findDutyToApproveList(hrEmpInfo.getEmpNo(), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
    }

    public List<HrDutyTrnsHdVu> getHrDutyTrnsHdList() {
        return hrDutyTrnsHdList;
    }

    public void setHrDutyTrnsHdList(List<HrDutyTrnsHdVu> hrDutyTrnsHdList) {
        this.hrDutyTrnsHdList = hrDutyTrnsHdList;
    }

    public String save() {
        String dutyId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("dutyId");
        HrDutyTrnsHdVu hrTrnsDutyHdVu = hrDutyTrnsHdList.get(hrDutyTrnsHdList.indexOf(new HrDutyTrnsHdVu(Long.parseLong(dutyId))));
        for (HrDutyTrnsDt hrDutyTrnsDt : hrTrnsDutyHdVu.getHrDutyTrnsDtList()) {
            if (hrDutyTrnsDt.getApproved().equals(new Character('1')) || hrDutyTrnsDt.getApproved().equals(new Character('2'))) {
                hrDutyTrnsDt.setApproveDate(new Date());
                hrDutyTrnsDt.setMngNo(hrEmpInfo);
                Jedis jedis = null;
                try {
                    jedis = WorkerBean.pool.getResource();

                    if (jedis.hgetAll("managers:" + hrTrnsDutyHdVu.getEmpNo()) != null && !jedis.hgetAll("managers:" + hrTrnsDutyHdVu.getEmpNo()).isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrTrnsDutyHdVu.getEmpNo());
                        for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                            if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                                    try {
                                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                        if (hrProfileMsg.getMsgId().equals(hrDutyTrnsDt.getId()) && hrProfileMsg.getEntityName().equals("HrDutyTrnsDtReq")) {
                                            hrProfileMsg.setReadDone('Y');
                                            jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                            jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(DutyApproval.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                        sessionBean.updateReadDoneMsg("HrDutyTrnsDtReq", hrDutyTrnsDt.getId(), 'Y', null);
                    }
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
            } else {
                hrDutyTrnsDt.setApproveDate(null);
                hrDutyTrnsDt.setApproved(null);
                hrDutyTrnsDt.setMngNo(null);
            }
            sessionBean.updateDutyDt(hrDutyTrnsDt);
        }
        hrDutyTrnsHdList = sessionBean.findDutyToApproveList(hrEmpInfo.getEmpNo(), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));

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
            hrProfileMsg.setEntityName("HrDutyTrnsDtApprove");
            hrProfileMsg.setMsgApprove('Y');
            hrProfileMsg.setSendDate(new Date());
            hrProfileMsg.setEmpNo(hrTrnsDutyHdVu.getEmpNo());
            hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
            hrProfileMsg.setMsgId(hrTrnsDutyHdVu.getId());
            hrProfileMsg.setMsgType(2L);
            objectMessage.setObject(hrProfileMsg);
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();

                jedis.hset("msgs:" + hrTrnsDutyHdVu.getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("msgs:" + hrTrnsDutyHdVu.getEmpNo(), Integer.MAX_VALUE);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }

            }
            messageProducer.send(objectMessage);
            System.out.println("message sent");

        } catch (IOException ex) {
            Logger.getLogger(DutyApproval.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException x) {
            x.printStackTrace();
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

        return null;
    }

    public void updateAllCheckboxes() {
        System.out.println(mainCheckboxValue);
        String dutyId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hdId");
        HrDutyTrnsHdVu hrTrnsDutyHdVu = hrDutyTrnsHdList.get(hrDutyTrnsHdList.indexOf(new HrDutyTrnsHdVu(Long.parseLong(dutyId))));
        for (HrDutyTrnsDt hrDutyTrnsDt : hrTrnsDutyHdVu.getHrDutyTrnsDtList()) {
            if (mainCheckboxValue) {
                hrDutyTrnsDt.setApproved(new Character('1'));
            } else {
                hrDutyTrnsDt.setApproved(null);
            }
        }
    }

    public boolean isMainCheckboxValue() {
        return mainCheckboxValue;
    }

    public void setMainCheckboxValue(boolean mainCheckboxValue) {
        this.mainCheckboxValue = mainCheckboxValue;
    }
}

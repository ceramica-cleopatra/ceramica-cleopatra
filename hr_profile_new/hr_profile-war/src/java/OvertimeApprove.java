/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrManualEffectionDt;
import e.HrProfileMsg;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
@ViewScoped
public class OvertimeApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private boolean approveAll;

    /** Creates a new instance of OvertimeApprove */
    public OvertimeApprove() {
    }
    private List<OvertimeApproveHdDTO> overtimeList;
    private List<Object[]> list;
    private HrEmpInfo hrEmpInfo;
    private Map<Long, Map<String, String>> overtimeMap;

    @PostConstruct
    public void init() {
        overtimeMap = new HashMap<Long, Map<String, String>>();
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        list = sessionBean.getOvertimeToApprove(c.getTime(), new Date(), hrEmpInfo.getEmpNo(), c1.getTime());
        overtimeList = new ArrayList<OvertimeApproveHdDTO>();
        for (Object[] emp : list) {

            OvertimeApproveHdDTO overtimeApproveHdDTO = new OvertimeApproveHdDTO();
            overtimeApproveHdDTO.setEmpNo(Long.parseLong(emp[0].toString()));
            OvertimeApproveDtDTO overtimeApproveDtDTO = new OvertimeApproveDtDTO();
            overtimeApproveDtDTO.setPlusMinuts(Long.parseLong(emp[3].toString()));
            overtimeApproveDtDTO.setTrnsDate((Date) emp[4]);
            overtimeApproveDtDTO.setInTrns(emp[5] != null ? emp[5].toString() : null);
            overtimeApproveDtDTO.setOutTrns(emp[6] != null ? emp[6].toString() : null);
            overtimeApproveDtDTO.setId(Long.parseLong(emp[8].toString()));
            overtimeApproveDtDTO.setApproveAsString(emp[7] != null ? "true" : "false");
            Map<String, String> map = new HashMap<String, String>();
            if (emp[7] != null && emp[7].toString().equals("Y")) {
                map.put(emp[8].toString(), "true");
            } else {
                map.put(emp[8].toString(), "false");
            }
            if (overtimeMap.containsKey(Long.parseLong(emp[0].toString()))) {
                overtimeMap.get(Long.parseLong(emp[0].toString())).put(emp[8].toString(), map.get(emp[8].toString()));
            } else {
                overtimeMap.put(Long.parseLong(emp[0].toString()), map);
            }


            overtimeApproveDtDTO.setOvertimeApproveHdDTO(overtimeApproveHdDTO);
            if (overtimeList.contains(overtimeApproveHdDTO)) {
                overtimeApproveHdDTO = overtimeList.get(overtimeList.indexOf(overtimeApproveHdDTO));
                overtimeApproveHdDTO.getOvertimeApproveDtDTOList().add(overtimeApproveDtDTO);
                overtimeApproveHdDTO.setTotalPlusMinuts(Long.parseLong(emp[3].toString()) + overtimeApproveHdDTO.getTotalPlusMinuts());
            } else {
                List<OvertimeApproveDtDTO> overtimeApproveDtDTOs = new ArrayList<OvertimeApproveDtDTO>();
                overtimeApproveHdDTO.setEmpName(emp[1].toString());
                overtimeApproveHdDTO.setLocation(emp[2].toString());
                overtimeApproveHdDTO.setTotalPlusMinuts(Long.parseLong(emp[3].toString()));
                overtimeApproveDtDTOs.add(overtimeApproveDtDTO);
                overtimeApproveHdDTO.setOvertimeApproveDtDTOList(overtimeApproveDtDTOs);
                overtimeList.add(overtimeApproveHdDTO);
            }

        }
        updateApproveAllFlag();

    }

    void populateOvertimeList(Long empId) {
        for (Long empNo : overtimeMap.keySet()) {
            if (empNo.equals(empId)) {
                continue;
            }
            OvertimeApproveHdDTO overtimeApproveHdDTO = new OvertimeApproveHdDTO();
            overtimeApproveHdDTO.setEmpNo(empNo);
            OvertimeApproveHdDTO actualOvertimeHd = overtimeList.get(overtimeList.indexOf(overtimeApproveHdDTO));
            for (OvertimeApproveDtDTO dt1 : actualOvertimeHd.getOvertimeApproveDtDTOList()) {
                dt1.setApproveAsString(overtimeMap.get(empNo).get(dt1.getId().toString()));
            }
        }

    }

    public void updateApproveAllFlag() {
        for (OvertimeApproveHdDTO hd : overtimeList) {
            hd.setApproveAll(true);
            for (OvertimeApproveDtDTO dt : hd.getOvertimeApproveDtDTOList()) {
                if (dt.getApproveAsString().equals("false")) {
                    hd.setApproveAll(false);
                    break;
                }
            }
        }
    }

    public void save() {
        String empNo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empNo");
        OvertimeApproveHdDTO overtimeApproveHdDTO = new OvertimeApproveHdDTO();
        overtimeApproveHdDTO.setEmpNo(Long.parseLong(empNo));
        OvertimeApproveHdDTO actualOvertimeHd = overtimeList.get(overtimeList.indexOf(overtimeApproveHdDTO));
        for (OvertimeApproveDtDTO dt1 : actualOvertimeHd.getOvertimeApproveDtDTOList()) {
            HrManualEffectionDt hrManualEffectionDt = sessionBean.getManaEffectionDtById(dt1.getId());
            if (dt1.getApproveAsString() != null && dt1.getApproveAsString().equals("true")) {
                hrManualEffectionDt.setOvertimeApprove("Y");
                hrManualEffectionDt.setOvertimeApprove_Date(new Date());
                overtimeMap.get(Long.parseLong(empNo)).put(dt1.getId().toString(), "true");
            } else {
                hrManualEffectionDt.setOvertimeApprove(null);
                hrManualEffectionDt.setOvertimeApprove_Date(null);
                overtimeMap.get(Long.parseLong(empNo)).put(dt1.getId().toString(), "false");
            }
            sessionBean.mergeHrManaualEffectionDt(hrManualEffectionDt);
            if (dt1.getApproveAsString() != null) {
                Jedis jedis = null;
                try {
                    jedis = WorkerBean.pool.getResource();
                    if (jedis.hgetAll("managers:" + hrManualEffectionDt.getHrManualEffectionHd().getEmpId()) != null && !jedis.hgetAll("managers:" + hrManualEffectionDt.getHrManualEffectionHd().getEmpId()).isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrManualEffectionDt.getHrManualEffectionHd().getEmpId());
                        for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                            if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                                    try {
                                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                        if (hrProfileMsg.getMsgId().equals(hrManualEffectionDt.getId()) && hrProfileMsg.getEntityName().equals("HrOvertimeSubReq")) {
                                            hrProfileMsg.setReadDone(dt1.getApproveAsString().equals("true") ? 'Y' : 'N');
                                            jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                            jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(OvertimeApprove.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                        sessionBean.updateReadDoneMsg("HrOvertimeSubReq", hrManualEffectionDt.getId(), dt1.getApproveAsString().equals("true") ? 'Y' : 'N', null);
                    }

                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }

                if (dt1.getApproveAsString().equals("true")) {
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
                        hrProfileMsg.setEntityName("HrOvertimeSubApprove");
                        hrProfileMsg.setMsgApprove('Y');
                        hrProfileMsg.setSendDate(new Date());
                        hrProfileMsg.setEmpNo(hrManualEffectionDt.getHrManualEffectionHd().getEmpId());
                        hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
                        hrProfileMsg.setMsgId(hrManualEffectionDt.getId());
                        hrProfileMsg.setMsgType(2L);
                        objectMessage.setObject(hrProfileMsg);
                        jedis.hset("msgs:" + hrManualEffectionDt.getHrManualEffectionHd().getEmpId(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                        jedis.expire("msgs:" + hrManualEffectionDt.getHrManualEffectionHd().getEmpId(), Integer.MAX_VALUE);
                        messageProducer.send(objectMessage);
                        System.out.println("message sent");
                    } catch (IOException ex) {
                        Logger.getLogger(OvertimeApprove.class.getName()).log(Level.SEVERE, null, ex);
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
                }
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "�� �����", "�� ����� �����"));
        updateApproveAllFlag();
    }

    public void markAllAsApproved() {
        String empNo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empNo");
        OvertimeApproveHdDTO overtimeApproveHdDTO = new OvertimeApproveHdDTO();
        overtimeApproveHdDTO.setEmpNo(Long.parseLong(empNo));
        OvertimeApproveHdDTO actualOvertimeHd = overtimeList.get(overtimeList.indexOf(overtimeApproveHdDTO));
        if (actualOvertimeHd.isApproveAll()) {
            for (OvertimeApproveDtDTO dt : actualOvertimeHd.getOvertimeApproveDtDTOList()) {
                dt.setApproveAsString("true");
            }
        } else {
            for (OvertimeApproveDtDTO dt : actualOvertimeHd.getOvertimeApproveDtDTOList()) {
                dt.setApproveAsString("false");
            }
        }
    }

    public List<OvertimeApproveHdDTO> getOvertimeList() {
        String empNo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empNo");
        if (empNo != null) {
            populateOvertimeList(Long.parseLong(empNo));
        }
        return overtimeList;
    }

    public void setOvertimeList(List<OvertimeApproveHdDTO> overtimeList) {
        this.overtimeList = overtimeList;
    }

    public boolean isApproveAll() {
        return approveAll;
    }

    public void setApproveAll(boolean approveAll) {
        this.approveAll = approveAll;
    }
}

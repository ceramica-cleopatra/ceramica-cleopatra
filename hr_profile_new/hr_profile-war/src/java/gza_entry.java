/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrGzaDt;
import e.HrGzaEmpMngDt;
import e.HrGzaHd;
import e.HrGzaReason;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class gza_entry implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<SelectItem> gza_reason_list = new ArrayList<SelectItem>();
    private FacesMessage fm = new FacesMessage();
    private FacesContext fc = FacesContext.getCurrentInstance();
    private RequestContext context = RequestContext.getCurrentInstance();
    private String usercode;
    private String txt;
    private HrGzaEmpMngDt selected_row;
    private String emp_name;
    private Long tot_sal;
    private Long gza_reason_id;
    private String notes;
    private Long gza_value;
    private Double gza_days;
    private HrEmpInfo hrEmpInfo;
    private List<HrGzaEmpMngDt> hrGzaEmpMngDtList;

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
        List<HrGzaReason> hrGzaReasons = sessionBean.getGzaReasons();
        for (HrGzaReason hrGzaReason : hrGzaReasons) {
            gza_reason_list.add(new SelectItem(hrGzaReason.getId(), hrGzaReason.getName()));
        }
//        hrGzaEmpMngDtList = sessionBean.getGzaEmpMngDt(hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId(), txt);
        hrGzaEmpMngDtList = sessionBean.getGzaEmpMngDtForMng(hrEmpInfo.getEmpNo(), txt);
    }

    public Double getGza_days() {
        return gza_days;
    }

    public void setGza_days(Double gza_days) {
        this.gza_days = gza_days;
    }

    public Long getGza_value() {
        return gza_value;
    }

    public void setGza_value(Long gza_value) {
        this.gza_value = gza_value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getGza_reason_id() {
        return gza_reason_id;
    }

    public void setGza_reason_id(Long gza_reason_id) {
        this.gza_reason_id = gza_reason_id;
    }

    public List<SelectItem> getGza_reason_list() {
        return gza_reason_list;
    }

    public void setGza_reason_list(List<SelectItem> gza_reason_list) {
        this.gza_reason_list = gza_reason_list;
    }

    /** Creates a new instance of gza_entry */
    public gza_entry() {
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Long getTot_sal() {
        if (selected_row != null) {
            tot_sal = selected_row.getTotSal();
        }
        return tot_sal;
    }

    public void setTot_sal(Long tot_sal) {
        this.tot_sal = tot_sal;
    }

    public List<String> complete(String query) {
//        List<String> results = sessionBean.getEmpByEmpNameSubstr(hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId(), query);
        List<String> results=sessionBean.getGzaEmpByEmpNameSubstrAndMngNo(hrEmpInfo.getEmpNo(),query);
        txt = null;
        return results;
    }

    public void save(ActionEvent ae) {
        fc = FacesContext.getCurrentInstance();
        context = RequestContext.getCurrentInstance();
        if (gza_reason_id == null) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» ≈œŒ«· ”»» «·Ã“«¡");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(null, fm);

            context.addCallbackParam("isValid", false);
            return;
        }
        if (gza_days == null) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» ≈œŒ«· ⁄œœ √Ì«„ «·Ã“«");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(null, fm);
            context.addCallbackParam("isValid", false);
            return;
        }
        if (notes.length() == 0) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» ≈œŒ«·  ⁄·Ìﬁ ⁄·Ï «·Ã“«");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(null, fm);
            context.addCallbackParam("isValid", false);
            return;
        }
        Long hd_id = 0L;
        Long dt_id = 0L;
        if (sessionBean.getGzaHdMax() == null) {
            hd_id = 1L;
        } else {
            hd_id = sessionBean.getGzaHdMax() + 1L;
        }
        if (sessionBean.getGzaDtMax() == null) {
            dt_id = 1L;
        } else {
            dt_id = sessionBean.getGzaDtMax() + 1L;
        }
        HrGzaHd hrGzaHd = new HrGzaHd();
        hrGzaHd.setTrnsDate(new Date());
        HrGzaReason hrGzaReason = new HrGzaReason();
        hrGzaReason.setId(gza_reason_id);
        hrGzaHd.setGzaType(hrGzaReason);
        hrGzaHd.setId(hd_id);
        hrGzaHd.setMngNo(Long.parseLong(usercode));
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        hrGzaHd.setTrnsMonth(Long.parseLong(sdf1.format(new Date())));
        hrGzaHd.setTrnsYear(Long.parseLong(sdf2.format(new Date())));
        sessionBean.persistGzaHd(hrGzaHd);
        HrGzaDt hrGzaDt = new HrGzaDt();
        hrGzaDt.setId(dt_id);
        hrGzaDt.setHrGzaHd(hrGzaHd);
        hrGzaDt.setEmpNo(sessionBean.finduserbyid(selected_row.getEmpNo()));
        hrGzaDt.setGzaDays(gza_days);
        hrGzaDt.setGzaValue(gza_value);
        hrGzaDt.setLastSalary(tot_sal);
        hrGzaDt.setNotes(notes);
        sessionBean.persistGzaDt(hrGzaDt);
        fm.setSummary(" „ «·Õ›Ÿ");
        fm.setDetail(" „ Õ›Ÿ «·Ã“« »‰Ã«Õ");
        Connection connection=null;
        Session session=null;
        MessageProducer messageProducer=null;
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
            hrProfileMsg.setEntityName("HrGzaHd");
            hrProfileMsg.setSendDate(new Date());
            hrProfileMsg.setEmpNo(hrGzaDt.getEmpNo().getEmpNo());
            hrProfileMsg.setSenderNo(Long.parseLong(usercode));
            hrProfileMsg.setMsgId(hrGzaHd.getId());
            hrProfileMsg.setMsgType(1L);
            objectMessage.setObject(hrProfileMsg);
            jedis.hset("alerts:"+hrGzaDt.getEmpNo().getEmpNo(),hrProfileMsg.getEntityName()+ "" + hrProfileMsg.getMsgType() +""+ hrProfileMsg.getEmpNo() +""+ hrProfileMsg.getMsgId()
                    ,new ObjectMapper().writeValueAsString(hrProfileMsg));
            jedis.expire("alerts:"+hrGzaDt.getEmpNo().getEmpNo(),Integer.MAX_VALUE);
            messageProducer.send(objectMessage);

            System.out.println("message sent");

        } catch (IOException ex) {
            Logger.getLogger(gza_entry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally{
             if (jedis != null) {
                jedis.close();
            }
            try{
            messageProducer.close();
            }catch(Exception e){
                e.printStackTrace();
            }try{
            session.close();
            }catch(Exception e){
                e.printStackTrace();
            }try{
            connection.close();
            }catch(Exception e){
                e.printStackTrace();
            }

        }

        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fc.addMessage(null, fm);
        context.addCallbackParam("isValid", true);
        gza_reason_id = null;
        emp_name = null;
        tot_sal = null;
        notes = null;
        gza_days = null;
        gza_value = null;
    }

    public void search(ActionEvent ae) {
//        hrGzaEmpMngDtList = sessionBean.getGzaEmpMngDt(hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId(), txt);
        hrGzaEmpMngDtList = sessionBean.getGzaEmpMngDtForMng(hrEmpInfo.getEmpNo(), txt);
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void onClose(CloseEvent ce) {
        gza_reason_id = null;
        notes = null;
        gza_days = null;
        gza_value = null;
        tot_sal = null;
    }

    public List<HrGzaEmpMngDt> getHrGzaEmpMngDtList() {
        return hrGzaEmpMngDtList;
    }

    public void setHrGzaEmpMngDtList(List<HrGzaEmpMngDt> hrGzaEmpMngDtList) {
        this.hrGzaEmpMngDtList = hrGzaEmpMngDtList;
    }

    public HrGzaEmpMngDt getSelected_row() {
        return selected_row;
    }

    public void setSelected_row(HrGzaEmpMngDt selected_row) {
        this.selected_row = selected_row;
    }
}

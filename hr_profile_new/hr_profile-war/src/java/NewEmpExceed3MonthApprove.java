/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrNewEmpExceed3Months;
import e.HrNewEmpExceed3monthDt;
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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.primefaces.event.RowEditEvent;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class NewEmpExceed3MonthApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrNewEmpExceed3monthDt> hrNewEmpExceed3monthDtList;

    @PostConstruct
    public void init() {
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        hrNewEmpExceed3monthDtList = sessionBean.getNewEmpExceed3MonthDt(hrEmpInfo.getEmpNo(), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId());
    }

    /** Creates a new instance of NewEmpExceed3MonthApprove */
    public NewEmpExceed3MonthApprove() {
    }

    public List<HrNewEmpExceed3monthDt> getHrNewEmpExceed3monthDtList() {
        return hrNewEmpExceed3monthDtList;
    }

    public void setHrNewEmpExceed3monthDtList(List<HrNewEmpExceed3monthDt> hrNewEmpExceed3monthDtList) {
        this.hrNewEmpExceed3monthDtList = hrNewEmpExceed3monthDtList;
    }

    public void update(RowEditEvent event) {
        HrNewEmpExceed3monthDt hrNewEmpExceed3monthDt = (HrNewEmpExceed3monthDt) event.getObject();
        HrNewEmpExceed3Months hrNewEmpExceed3Months = sessionBean.findEmpExceed3MonthsById(hrNewEmpExceed3monthDt.getRowId());
        hrNewEmpExceed3Months.setMngNo(hrEmpInfo.getEmpNo());
        hrNewEmpExceed3Months.setApproved(hrNewEmpExceed3monthDt.getApproved());
        hrNewEmpExceed3Months.setApproveDate(new Date());
        sessionBean.mergeHrNewEmpExceed3Months(hrNewEmpExceed3Months);
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            if (jedis.hgetAll("managers:" + hrNewEmpExceed3monthDt.getEmpNo()) != null && !jedis.hgetAll("managers:" + hrNewEmpExceed3monthDt.getEmpNo()).isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> hrEmpManagersMap = jedis.hgetAll("managers:" + hrNewEmpExceed3monthDt.getEmpNo());
                for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
                    if (jedis.hgetAll("alerts:" + hrEmpManagersAsString) != null && !jedis.hgetAll("alerts:" + hrEmpManagersAsString).isEmpty()) {
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpManagersAsString);
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            try {
                                HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                if (hrProfileMsg.getMsgId().equals(hrNewEmpExceed3monthDt.getRowId()) && hrProfileMsg.getEntityName().equals("HR_NEW_EMP_EXCEED_3_MONTHS")) {
                                    hrProfileMsg.setReadDone('Y');
                                    jedis.hset("alerts:" + hrEmpManagersAsString, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + hrEmpManagersAsString, Integer.MAX_VALUE);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(NewEmpExceed3MonthApprove.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                sessionBean.updateReadDoneMsg("HR_NEW_EMP_EXCEED_3_MONTHS", hrNewEmpExceed3monthDt.getRowId(), 'Y', null);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈⁄ „«œ «·’·«ÕÌ… »‰Ã«Õ"));
    }
}

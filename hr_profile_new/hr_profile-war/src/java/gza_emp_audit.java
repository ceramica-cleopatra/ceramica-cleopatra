/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrGzaDt;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;
import sb.SessionBeanRemote;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class gza_emp_audit {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<Object[]> HrGzaDtForEmp = new ArrayList<Object[]>();
    private String usercode;
    private Object[] selectedGza;

    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        HrGzaDtForEmp = sessionBean.getHrGzaDtForEmp(Long.parseLong(usercode));
        List<HrGzaDt> hrGzaDts = sessionBean.getEmpNotReadGza(Long.parseLong(usercode));
        for (HrGzaDt hrGzaDt : hrGzaDts) {
            hrGzaDt.setReadDone("Y");
            sessionBean.mergeHrGzaDt(hrGzaDt);
        }

        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            if (jedis.hgetAll("alerts:" + usercode) != null && !jedis.hgetAll("alerts:" + usercode).isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + usercode);
                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                    try {
                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                        if (hrProfileMsg.getEntityName().equals("HrGzaHd")) {
                            hrProfileMsg.setReadDone('Y');
                            jedis.hset("alerts:" + usercode, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("alerts:" + usercode, Integer.MAX_VALUE);
                            sessionBean.updateReadDoneMsg("HrGzaHd", hrProfileMsg.getMsgId(), 'Y', null);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(gza_emp_audit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
    }

    public List<Object[]> getHrGzaDtForEmp() {
        return HrGzaDtForEmp;
    }

    public void setHrGzaDtForEmp(List<Object[]> HrGzaDtForEmp) {
        this.HrGzaDtForEmp = HrGzaDtForEmp;
    }

    public Object[] getSelectedGza() {
        return selectedGza;
    }

    public void setSelectedGza(Object[] selectedGza) {
        this.selectedGza = selectedGza;
    }

    /** Creates a new instance of gza_emp_audit */
    public gza_emp_audit() {
    }
}

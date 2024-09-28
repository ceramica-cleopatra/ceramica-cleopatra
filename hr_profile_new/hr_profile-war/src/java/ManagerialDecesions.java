/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrMangaerialDecisions;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
@ViewScoped
public class ManagerialDecesions {

    @EJB
    private SessionBeanLocal sessionBean;
    private String usercode;

    /** Creates a new instance of ManagerialDecesions */
    public ManagerialDecesions() {
    }
    List<HrMangaerialDecisions> md_list;
    HrMangaerialDecisions md;

    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        md_list = sessionBean.getManagerialDecisions();

        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            if (jedis.hgetAll("decisions:" + usercode) != null && !jedis.hgetAll("decisions:" + usercode).isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("decisions:" + usercode);
                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                    try {
                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                        hrProfileMsg.setReadDone('Y');
                        jedis.hset("decisions:" + usercode, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                        jedis.expire("decisions:" + usercode, Integer.MAX_VALUE);
                    } catch (IOException ex) {
                        Logger.getLogger(ManagerialDecesions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sessionBean.updateReadDoneMsg(null, null, 'Y', Long.parseLong(usercode));
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void setMd_list(List md_list) {
        this.md_list = md_list;
    }

    public List getMd_list() {
        return md_list;
    }

    public void setMd(HrMangaerialDecisions md) {
        this.md = md;
    }

    public HrMangaerialDecisions getMd() {
        return md;
    }

    public void findDecisionDetails() {
        Integer selected_row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx").toString());
        md = md_list.get(selected_row);
    }
}

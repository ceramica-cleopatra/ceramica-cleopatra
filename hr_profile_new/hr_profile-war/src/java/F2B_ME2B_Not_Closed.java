/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkF2bMe2bNotClosed;
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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class F2B_ME2B_Not_Closed {

    @EJB
    private SessionBeanLocal sessionBean;
    private String usercode;

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
        notClosedF2BOrMe2B = sessionBean.findNotClosedF2BOrME2BForEmp(Long.parseLong(usercode));

        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();

            if (jedis.hgetAll("alerts:" + usercode) != null && !jedis.hgetAll("alerts:" + usercode).isEmpty()) {
                List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + usercode);
                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                    try {
                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                        if (hrProfileMsg.getEntityName().equals("CrmkF2bMe2bNotClosed")) {
                            hrProfileMsg.setReadDone('Y');
                            jedis.hset("alerts:" + usercode, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("alerts:" + usercode, Integer.MAX_VALUE);
                            hrProfileMsgs.add(hrProfileMsg);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(F2B_ME2B_Not_Closed.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sessionBean.mergeHrProfileMsgList(hrProfileMsgs);
            }
        } finally {
            jedis.close();
        }
    }

    /** Creates a new instance of F2B_ME2B_Not_Closed */
    public F2B_ME2B_Not_Closed() {
    }
    private List<CrmkF2bMe2bNotClosed> notClosedF2BOrMe2B = new ArrayList<CrmkF2bMe2bNotClosed>();

    public List<CrmkF2bMe2bNotClosed> getNotClosedF2BOrMe2B() {
        return notClosedF2BOrMe2B;
    }

    public void setNotClosedF2BOrMe2B(List<CrmkF2bMe2bNotClosed> notClosedF2BOrMe2B) {
        this.notClosedF2BOrMe2B = notClosedF2BOrMe2B;
    }
}

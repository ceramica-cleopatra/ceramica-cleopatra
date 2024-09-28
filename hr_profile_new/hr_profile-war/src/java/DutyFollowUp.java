/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrDutyTrnsHd;
import e.HrDutyTrnsHdVu;
import e.HrEmpInfo;
import e.HrProfileMsg;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
public class DutyFollowUp {

    /** Creates a new instance of DutyFollowUp */
    public DutyFollowUp() {
    }
    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrDutyTrnsHd> hrDutyTrnsHdList;

    @PostConstruct
    public void init() {
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 0);
        c.add(Calendar.MONTH, -1);
        System.out.println(c);
        hrDutyTrnsHdList = sessionBean.findDutyToFollowUp(hrEmpInfo, c.getTime());
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();

            if (jedis.hgetAll("msgs:" + hrEmpInfo.getEmpNo()) != null && !jedis.hgetAll("msgs:" + hrEmpInfo.getEmpNo()).isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("msgs:" + hrEmpInfo.getEmpNo());
                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                    try {
                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                        if (hrProfileMsg.getEntityName().equals("HrDutyTrnsDtApprove")) {
                            hrProfileMsg.setReadDone('Y');
                            jedis.hset("msgs:" + hrEmpInfo.getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("msgs:" + hrEmpInfo.getEmpNo(), Integer.MAX_VALUE);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(DutyFollowUp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sessionBean.updateReadDoneMsg("HrDutyTrnsDtApprove", null, 'Y', hrEmpInfo.getEmpNo());
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<HrDutyTrnsHd> getHrDutyTrnsHdList() {
        return hrDutyTrnsHdList;
    }
}

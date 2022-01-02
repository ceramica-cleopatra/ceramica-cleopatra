/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkF2bMe2bNotClosed;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
         if(CashHandler.getAlerts().get(Long.parseLong(usercode))!=null && CashHandler.getAlerts().get(Long.parseLong(usercode)).size()>0)
        {   List<HrProfileMsg> hrProfileMsgs=new ArrayList<HrProfileMsg>();
            for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(Long.parseLong(usercode)))
            {
                if(hrProfileMsg.getEntityName().equals("CrmkF2bMe2bNotClosed"))
                {
                    hrProfileMsg.setReadDone('Y');
                    hrProfileMsgs.add(hrProfileMsg);
                }
            }
            sessionBean.mergeHrProfileMsgList(hrProfileMsgs);
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

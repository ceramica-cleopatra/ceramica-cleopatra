/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrMangaerialDecisions;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
        usercode=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
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

        if(CashHandler.getDecisions().get(Long.parseLong(usercode))!=null && CashHandler.getDecisions().get(Long.parseLong(usercode)).size()>0)
        {
            for (HrProfileMsg hrProfileMsg : CashHandler.getDecisions().get(Long.parseLong(usercode)))
            {
                    System.out.println("Desisions"+hrProfileMsg.getId());
                    hrProfileMsg.setReadDone('Y');
            }
            sessionBean.updateReadDoneMsg(null, null, 'Y',Long.parseLong(usercode));
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

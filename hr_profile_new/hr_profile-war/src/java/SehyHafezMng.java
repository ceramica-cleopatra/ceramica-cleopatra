/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrHafezSehyMngDt;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class SehyHafezMng {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrHafezSehyMngDt> hrHafezSehyDtList = new ArrayList<HrHafezSehyMngDt>();
    private HrHafezSehyMngDt hrHafezSehyDt = new HrHafezSehyMngDt();
    private HrEmpInfo hrEmpInfo;
    private Long totalHafez=0L;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String usercode = (String) fc.getExternalContext().getSessionMap().get("usercode");
        hrEmpInfo = (HrEmpInfo) fc.getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrHafezSehyDtList = sessionBean.getHafezSehyMng(Long.parseLong(usercode));

         for(HrHafezSehyMngDt hrHafezSehyDt: hrHafezSehyDtList){
            totalHafez+=hrHafezSehyDt.getValue();
        }
        if (hrHafezSehyDtList.size() > 0) {
            hrHafezSehyDt = hrHafezSehyDtList.get(0);
        }
   
    }

    public HrHafezSehyMngDt getHrHafezSehyDt() {
        return hrHafezSehyDt;
    }

    public void setHrHafezSehyDt(HrHafezSehyMngDt hrHafezSehyDt) {
        this.hrHafezSehyDt = hrHafezSehyDt;
    }

    /** Creates a new instance of PoundHafez */
    public SehyHafezMng() {
    }


    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

    public Long getTotalHafez() {
        return totalHafez;
    }

    public void setTotalHafez(Long totalHafez) {
        this.totalHafez = totalHafez;
    }

    public List<HrHafezSehyMngDt> getHrHafezSehyDtList() {
        return hrHafezSehyDtList;
    }

    public void setHrHafezSehyDtList(List<HrHafezSehyMngDt> hrHafezSehyDtList) {
        this.hrHafezSehyDtList = hrHafezSehyDtList;
    }

    
    

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean(name="gza_mng_audit")
@ViewScoped
public class gza_mng_audit implements Serializable {
    @EJB
    private SessionBeanLocal sessionBean;
    private List<Object[]> hrrGzaHdList = new ArrayList<Object[]>();
    private String usercode;
    private String txt;
    private Object[] selectedGza;
    private HrEmpInfo hrEmpInfo;
    @PostConstruct
    public void init(){
    hrEmpInfo=(HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
    HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
    usercode=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
    hrrGzaHdList = sessionBean.getHrGzaHds(Long.parseLong(usercode), txt);
    }
    public List<String> complete(String query) {
        List<String> results = sessionBean.getEmpByEmpNameSubstr(hrEmpInfo.getDeptId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getJobId(), query);
        txt = null;
        return results;
    }
    FacesContext fc;

    /** Creates a new instance of gza_mng_audit */
    public gza_mng_audit() {
    }

    public List<Object[]> getHrrGzaHdList() {
        return hrrGzaHdList;
    }

    public void search(ActionEvent ae) {
        hrrGzaHdList = sessionBean.getHrGzaHds(Long.parseLong(usercode), txt);
    }

    public void setHrrGzaHdList(List<Object[]> hrrGzaHdList) {
        this.hrrGzaHdList = hrrGzaHdList;
    }

    public Object[] getSelectedGza() {
        return selectedGza;
    }

    public void setSelectedGza(Object[] selectedGza) {
        this.selectedGza = selectedGza;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }


}

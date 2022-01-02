/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrAdvanceZamalaDt;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
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
 * @author data
 */
@ManagedBean
@ViewScoped
public class AdvanceGuaranteeFollowUp {
    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrAdvanceZamalaDt> guaranteeAdvanceDtList;
    @PostConstruct
    public void init(){
        HrEmpInfo hrEmpInfo=(HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        guaranteeAdvanceDtList=sessionBean.findGuaranteeAdvanceZamalaDt(hrEmpInfo.getEmpNo());
    }
    /** Creates a new instance of AdvanceGuaranteeFollowUp */
    public AdvanceGuaranteeFollowUp() {
    }

    public List<HrAdvanceZamalaDt> getGuaranteeAdvanceDtList() {
        return guaranteeAdvanceDtList;
    }

    public void setGuaranteeAdvanceDtList(List<HrAdvanceZamalaDt> guaranteeAdvanceDtList) {
        this.guaranteeAdvanceDtList = guaranteeAdvanceDtList;
    }

    

}

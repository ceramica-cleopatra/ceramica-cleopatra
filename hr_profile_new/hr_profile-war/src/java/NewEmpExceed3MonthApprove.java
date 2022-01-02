/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrNewEmpExceed3Months;
import e.HrNewEmpExceed3monthDt;
import e.HrProfileMsg;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
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
    public void init(){
        hrEmpInfo=(HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        hrNewEmpExceed3monthDtList=sessionBean.getNewEmpExceed3MonthDt(hrEmpInfo.getEmpNo(),hrEmpInfo.getDeptId(),hrEmpInfo.getLocId(),hrEmpInfo.getJobGrpId(),hrEmpInfo.getJobId());
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
        HrNewEmpExceed3monthDt hrNewEmpExceed3monthDt=(HrNewEmpExceed3monthDt) event.getObject();
        HrNewEmpExceed3Months hrNewEmpExceed3Months=sessionBean.findEmpExceed3MonthsById(hrNewEmpExceed3monthDt.getRowId());
        hrNewEmpExceed3Months.setMngNo(hrEmpInfo.getEmpNo());
        hrNewEmpExceed3Months.setApproved(hrNewEmpExceed3monthDt.getApproved());
        hrNewEmpExceed3Months.setApproveDate(new Date());
        sessionBean.mergeHrNewEmpExceed3Months(hrNewEmpExceed3Months);
        if (CashHandler.getEmpManagers().get(hrNewEmpExceed3monthDt.getEmpNo()) != null && CashHandler.getEmpManagers().get(hrNewEmpExceed3monthDt.getEmpNo()).size() > 0) {
                    for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrNewEmpExceed3monthDt.getEmpNo())) {
                        System.out.println(hrEmpMangers.getMngName());
                        if (CashHandler.getAlerts().get(hrEmpMangers.getMngNo()) != null && CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).size() > 0) {
                            for (HrProfileMsg msg : CashHandler.getAlerts().get(hrEmpMangers.getMngNo())) {
                                if (msg.getMsgId().equals(hrNewEmpExceed3monthDt.getRowId()) && msg.getEntityName().equals("HR_NEW_EMP_EXCEED_3_MONTHS")) {
                                    msg.setReadDone('Y');
                                }
                            }
                        }
                    }
                    sessionBean.updateReadDoneMsg("HR_NEW_EMP_EXCEED_3_MONTHS", hrNewEmpExceed3monthDt.getRowId(), 'Y', null);
                }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈⁄ „«œ «·’·«ÕÌ… »‰Ã«Õ"));
    }
    
}

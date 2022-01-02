/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkActivatePreviousDocReq;
import e.CrmkRsrvDt;
import e.HrEmpInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
public class ActivatePreviousDoc {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<CrmkActivatePreviousDocReq> crmkActivatePreviousDocReqList = new ArrayList<CrmkActivatePreviousDocReq>();
    private HrEmpInfo hrEmpInfo;

    @PostConstruct
    public void init() {
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        crmkActivatePreviousDocReqList = sessionBean.getPreviousDocReqToActivate(hrEmpInfo.getLocId());
    }

    public void update(RowEditEvent event) {
        CrmkActivatePreviousDocReq crmkActivatePreviousDocReq = (CrmkActivatePreviousDocReq) event.getObject();
        crmkActivatePreviousDocReq.setActivateDate(new Date());
        crmkActivatePreviousDocReq.setEmpActivateNo(hrEmpInfo.getEmpNo());
        sessionBean.mergeCrmkActivatePreviousDocReq(crmkActivatePreviousDocReq);
        if (crmkActivatePreviousDocReq.getActivate().equals('Y')) {
            CrmkRsrvDt crmkRsrvDt=sessionBean.getCrmkRsrvDt(crmkActivatePreviousDocReq.getRsrvId());
            sessionBean.activatePreviousDoc(crmkRsrvDt);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  ›⁄Ì· «·„” ‰œ »‰Ã«Õ"));
        } else if (crmkActivatePreviousDocReq.getActivate().equals('N')) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷  ›⁄Ì· «·„” ‰œ »‰Ã«Õ"));
        }
    }

    /** Creates a new instance of ActivatePreviousDoc */
    public ActivatePreviousDoc() {
    }

    public List<CrmkActivatePreviousDocReq> getCrmkActivatePreviousDocReqList() {
        return crmkActivatePreviousDocReqList;
    }

    public void setCrmkActivatePreviousDocReqList(List<CrmkActivatePreviousDocReq> crmkActivatePreviousDocReqList) {
        this.crmkActivatePreviousDocReqList = crmkActivatePreviousDocReqList;
    }
}

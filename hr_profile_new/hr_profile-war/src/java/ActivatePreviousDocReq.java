/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkActivatePreviousDocReq;
import e.CrmkActivatePreviousDocVu;
import e.CrmkBranch;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class ActivatePreviousDocReq {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<CrmkActivatePreviousDocReq> crmkActivatePreviousDocReqList = new ArrayList<CrmkActivatePreviousDocReq>();
    private CrmkActivatePreviousDocVu crmkActivatePreviousDocVu;
    private CrmkActivatePreviousDocVu selectedRow;
    private List<CrmkActivatePreviousDocVu> crmkActivatePreviousDocVus = new ArrayList<CrmkActivatePreviousDocVu>();
    private List<SelectItem> branchList = new ArrayList<SelectItem>();
    private CrmkBranch currentCrmkBranch;
    private List<SelectItem> storeList = new ArrayList<SelectItem>();
    private Calendar calendar;
    /** Creates a new instance of ActivatePreviousDoc */
    public ActivatePreviousDocReq() {
    }

    @PostConstruct
    public void init() {
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        crmkActivatePreviousDocReqList = sessionBean.getActivatePreviousDocReqs(hrEmpInfo, calendar.getTime());
        currentCrmkBranch = sessionBean.getCrmkBranchById(sessionBean.getLocationById(hrEmpInfo.getLocId()).getCrmkId());
        crmkActivatePreviousDocVu = new CrmkActivatePreviousDocVu();
        crmkActivatePreviousDocVu.setDocBrnId(currentCrmkBranch);
        crmkActivatePreviousDocVu.setDocType(11L);
        SelectItem selectItem = new SelectItem(currentCrmkBranch.getId(), currentCrmkBranch.getName());
        branchList.add(selectItem);
        List<CrmkBranch> crmkBranckList = sessionBean.getStore();
        for (CrmkBranch crmkBranch : crmkBranckList) {
            SelectItem si = new SelectItem(crmkBranch.getId(), crmkBranch.getName());
            storeList.add(si);
        }
        selectedRow = new CrmkActivatePreviousDocVu();
    }

    public void onListChanged(ValueChangeEvent e) {
        branchList = new ArrayList<SelectItem>();
        if (e.getNewValue().toString().equals("11")) {
            SelectItem selectItem = new SelectItem(currentCrmkBranch.getId(), currentCrmkBranch.getName());
            branchList.add(selectItem);
        } else if (e.getNewValue().toString().equals("5")) {
            branchList = storeList;
        }
    }

    public void saveRequest(ActionEvent e) {
        if (crmkActivatePreviousDocVu.getDocType() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈œŒ«· ‰Ê⁄ «·„” ‰œ"));
            return;
        }
        if (crmkActivatePreviousDocVu.getCrmkSehy() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈œŒ«· ‰Ê⁄ «·„” ‰œ"));
            return;
        }
        if (crmkActivatePreviousDocVu.getDocType() == 5 && crmkActivatePreviousDocVu.getDocBrnId() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈œŒ«· «·„Œ“‰"));
            return;
        }
        if (crmkActivatePreviousDocVu.getDocNo() == null && (crmkActivatePreviousDocVu.getDocHandNo() == null || crmkActivatePreviousDocVu.getDocHandNo().equals(""))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈œŒ«· «·—ﬁ„ √Ê «·—ﬁ„ «·ÌœÊÏ ··„” ‰œ"));
            return;
        }
        crmkActivatePreviousDocVus = sessionBean.getActivatePreviousDocVus(crmkActivatePreviousDocVu.getDocType(), crmkActivatePreviousDocVu.getDocBrnId().getId(), crmkActivatePreviousDocVu.getCrmkSehy().toString(), crmkActivatePreviousDocVu.getDocNo(), crmkActivatePreviousDocVu.getDocHandNo(), crmkActivatePreviousDocVu.getDocPrdId());
        if (crmkActivatePreviousDocVus.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "Â–« «·„” ‰œ €Ì— „ÊÃÊœ"));
        } else if (crmkActivatePreviousDocVus.size() == 1) {
            CrmkActivatePreviousDocReq crmkActivatePreviousDocReq = new CrmkActivatePreviousDocReq();
            crmkActivatePreviousDocReq.setEmpReqNo(hrEmpInfo);
            crmkActivatePreviousDocReq.setTrnsDate(new Date());
            crmkActivatePreviousDocReq.setCrmkSehy(crmkActivatePreviousDocVus.get(0).getCrmkSehy());
            crmkActivatePreviousDocReq.setDocId(crmkActivatePreviousDocVus.get(0).getDocId());
            crmkActivatePreviousDocReq.setDocType(crmkActivatePreviousDocVus.get(0).getDocType());
            crmkActivatePreviousDocReq.setRsrvId(crmkActivatePreviousDocVus.get(0).getRsrvId());
            crmkActivatePreviousDocReq.setBrnId(crmkActivatePreviousDocVus.get(0).getDocBrnId());
            crmkActivatePreviousDocReq.setDocNo(crmkActivatePreviousDocVus.get(0).getDocNo());
            crmkActivatePreviousDocReq.setDocHandNo(crmkActivatePreviousDocVus.get(0).getDocHandNo());
            crmkActivatePreviousDocReq.setDocPrdId(crmkActivatePreviousDocVus.get(0).getDocPrdId());
            crmkActivatePreviousDocReq.setHrLocId(hrEmpInfo.getLocId());
            sessionBean.saveCrmkActivatePreviousDocReq(crmkActivatePreviousDocReq);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ «·Õ›Ÿ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
            crmkActivatePreviousDocReqList = sessionBean.getActivatePreviousDocReqs(hrEmpInfo, calendar.getTime());
            crmkActivatePreviousDocVu = new CrmkActivatePreviousDocVu();
            crmkActivatePreviousDocVu.setDocBrnId(currentCrmkBranch);
            crmkActivatePreviousDocVu.setDocType(11L);
        } else {
            RequestContext.getCurrentInstance().execute("PF('activateDlg').show();");
        }

    }

    public void updateDocActivateRequest() {
        crmkActivatePreviousDocVu.setCrmkSehy(selectedRow.getCrmkSehy());
        crmkActivatePreviousDocVu.setDocBrnId(selectedRow.getDocBrnId());
        crmkActivatePreviousDocVu.setDocHandNo(selectedRow.getDocHandNo());
        crmkActivatePreviousDocVu.setDocNo(selectedRow.getDocNo());
        crmkActivatePreviousDocVu.setDocPrdId(selectedRow.getDocPrdId());
        crmkActivatePreviousDocVu.setDocType(selectedRow.getDocType());
    }

    public CrmkActivatePreviousDocVu getCrmkActivatePreviousDocVu() {
        return crmkActivatePreviousDocVu;
    }

    public void setCrmkActivatePreviousDocVu(CrmkActivatePreviousDocVu crmkActivatePreviousDocVu) {
        this.crmkActivatePreviousDocVu = crmkActivatePreviousDocVu;
    }

    public List<CrmkActivatePreviousDocReq> getCrmkActivatePreviousDocReqList() {
        return crmkActivatePreviousDocReqList;
    }

    public void setCrmkActivatePreviousDocReqList(List<CrmkActivatePreviousDocReq> crmkActivatePreviousDocReqList) {
        this.crmkActivatePreviousDocReqList = crmkActivatePreviousDocReqList;
    }

    public List<CrmkActivatePreviousDocVu> getCrmkActivatePreviousDocVus() {
        return crmkActivatePreviousDocVus;
    }

    public void setCrmkActivatePreviousDocVus(List<CrmkActivatePreviousDocVu> crmkActivatePreviousDocVus) {
        this.crmkActivatePreviousDocVus = crmkActivatePreviousDocVus;
    }

    public List<SelectItem> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<SelectItem> branchList) {
        this.branchList = branchList;
    }

    public CrmkActivatePreviousDocVu getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(CrmkActivatePreviousDocVu selectedRow) {
        this.selectedRow = selectedRow;
    }
}

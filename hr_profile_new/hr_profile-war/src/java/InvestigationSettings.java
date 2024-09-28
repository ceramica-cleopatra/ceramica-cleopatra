/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrJobGrp;
import e.HrJobs;
import e.HrLocation;
import e.HrLocationInvestSetting;
import e.HrManagement;
import e.HrMenuTable;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import sb.SessionBeanLocal;

/**
 *
 * @author a.abbas
 */
@ManagedBean
@ViewScoped
public class InvestigationSettings {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrLocationInvestSetting> hrLocationInvestSettings = new ArrayList<HrLocationInvestSetting>();
    private HrLocationInvestSetting selectedRow;
    private List<HrJobGrp> jobGrpList;
    private List<HrManagement> deptList;
    private List<HrEmpInfo> empList;
    private List<HrJobs> jobList;
    private List<HrLocation> locList;
    private HrJobGrp selectedGrp;
    private HrJobs selectedJob;
    private HrManagement selectedDept;
    private HrEmpInfo selectedEmp;
    private HrLocation selectedLoc;
    private HrJobGrp filteredGrp;
    private HrJobs filteredJob;
    private HrManagement filteredDept;
    private HrEmpInfo filteredEmp;
    private HrLocation filteredLoc;
    private String[] selectedOrderList;
    private List<SelectItem> orderList;

    @PostConstruct
    public void init() {
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
        hrLocationInvestSettings = sessionBean.findAllLocationInvestSetting(null, null, null, null, null, null);
        jobGrpList = sessionBean.findJobGrpList();
        deptList = sessionBean.findDeptList();
        empList = sessionBean.findEmpList();
        jobList = sessionBean.findJobList();
        locList = sessionBean.findLocationList();
        selectedGrp = new HrJobGrp();
        selectedJob = new HrJobs();
        selectedDept = new HrManagement();
        selectedEmp = new HrEmpInfo();
        selectedLoc = new HrLocation();
        filteredGrp = new HrJobGrp();
        filteredJob = new HrJobs();
        filteredDept = new HrManagement();
        filteredEmp = new HrEmpInfo();
        filteredLoc = new HrLocation();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Locale locale = facesContext.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages_ar", locale);
        orderList = new ArrayList<SelectItem>();
        orderList.add(new SelectItem("g.name", bundle.getString("JobGrpName")));
        orderList.add(new SelectItem("j.name", bundle.getString("JobName")));
        orderList.add(new SelectItem("l.name", bundle.getString("LocationName")));
        orderList.add(new SelectItem("d.name", bundle.getString("DeptName")));
        orderList.add(new SelectItem("e.emp_name", bundle.getString("EmployeeName")));
    }

    public void serach(ActionEvent ae) {
       String orderByList="";
        for(int i=0;i<selectedOrderList.length;i++){
            orderByList+=selectedOrderList[i]+(i==selectedOrderList.length-1? "" : ",");
        }
        hrLocationInvestSettings = sessionBean.findAllLocationInvestSetting(filteredGrp.getId(), filteredJob.getId(), filteredLoc.getId(), filteredDept.getId(), filteredEmp == null ? null : filteredEmp.getEmpNo(),orderByList);
    }

    public void resetValues() {
        hrLocationInvestSettings = new ArrayList<HrLocationInvestSetting>();
        selectedGrp = new HrJobGrp();
        selectedJob = new HrJobs();
        selectedDept = new HrManagement();
        selectedEmp = new HrEmpInfo();
        selectedLoc = new HrLocation();
        filteredGrp = new HrJobGrp();
        filteredJob = new HrJobs();
        filteredDept = new HrManagement();
        filteredEmp = new HrEmpInfo();
        filteredLoc = new HrLocation();
    }

    public void newSearch(ActionEvent ae) {
        resetValues();
    }

    public void addNew(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();

        if ((filteredDept==null || filteredDept.getId() == null) && (filteredEmp==null || filteredEmp.getEmpNo() == 0) && (filteredGrp==null || filteredGrp.getId() == null) && (filteredJob==null || filteredJob.getId() == null) && (filteredLoc==null || filteredLoc.getId() == null)) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈Œ Ì«— ≈œŒ«· «·›∆… «·„”„ÊÕ ·Â« »Â–« «·≈” Àﬁ’«¡"));
            return;
        }

//        Long cnt = sessionBean.checkSettingsExistance(filteredGrp!=null ? filteredGrp.getId() : null, filteredJob!=null ? filteredJob.getId():null,filteredLoc!=null ? filteredLoc.getId() : null,filteredDept!=null ? filteredDept.getId() : null,filteredEmp!=null ? filteredEmp.getEmpNo() : null);
//        if (cnt == 0) {
//            HrLocationInvestSetting hrLocationInvestSetting = new HrLocationInvestSetting();
//            Long id = sessionBean.findHrLocationInvestigationSettingsNextId();
//            hrLocationInvestSetting.setId((id == null ? 0 : id) + 1);
//            hrLocationInvestSetting.setDeptId(filteredDept!=null && filteredDept.getId() != null ? filteredDept : null);
//            hrLocationInvestSetting.setEmpId(filteredEmp!=null && filteredEmp.getEmpNo() != 0 ? filteredEmp : null);
//            hrLocationInvestSetting.setGrpId(filteredGrp!=null && filteredGrp.getId() != null ? filteredGrp : null);
//            hrLocationInvestSetting.setJobId(filteredJob!=null && filteredJob.getId() != null ? filteredJob : null);
//            hrLocationInvestSetting.setLocId(filteredLoc!=null && filteredLoc.getId() != null ? filteredLoc : null);
//            sessionBean.persistHrLocationInvestigationSettings(hrLocationInvestSetting);
//        }

        HrLocationInvestSetting hrLocationInvestSetting = new HrLocationInvestSetting();
        Long id = sessionBean.findHrLocationInvestigationSettingsNextId();
        hrLocationInvestSetting.setId((id == null ? 0 : id) + 1);
        hrLocationInvestSetting.setDeptId(filteredDept!=null && filteredDept.getId() != null ? filteredDept : null);
        hrLocationInvestSetting.setEmpId(filteredEmp!=null && filteredEmp.getEmpNo() != 0 ? filteredEmp : null);
        hrLocationInvestSetting.setGrpId(filteredGrp!=null && filteredGrp.getId() != null ? filteredGrp : null);
        hrLocationInvestSetting.setJobId(filteredJob!=null && filteredJob.getId() != null ? filteredJob : null);
        hrLocationInvestSetting.setLocId(filteredLoc!=null && filteredLoc.getId() != null ? filteredLoc : null);
        sessionBean.persistHrLocationInvestigationSettings(hrLocationInvestSetting);
        hrLocationInvestSettings = sessionBean.findAllLocationInvestSetting(filteredGrp!=null ? filteredGrp.getId() : null, filteredJob!=null ? filteredJob.getId() : null,filteredLoc!=null ? filteredLoc.getId() : null,filteredDept!=null ?  filteredDept.getId() : null, filteredEmp!=null ? filteredEmp.getEmpNo() : null,null);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ ≈œŒ«· «·”„«ÕÌ… »‰Ã«Õ"));
    }

    public void delete(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(fc.getExternalContext().getRequestParameterMap().get("id").toString());
        HrLocationInvestSetting hrLocationInvestSetting = new HrLocationInvestSetting();
        hrLocationInvestSetting.setId(id);
        sessionBean.deleteHrLocationInvestSetting(hrLocationInvestSetting);
        hrLocationInvestSettings = sessionBean.findAllLocationInvestSetting(filteredGrp!=null ? filteredGrp.getId() : null, filteredJob!=null ? filteredJob.getId() : null,filteredLoc!=null ? filteredLoc.getId() : null,filteredDept!=null ?  filteredDept.getId() : null, filteredEmp!=null ? filteredEmp.getEmpNo() : null,null);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ–› «·”„«ÕÌ… »‰Ã«Õ"));
    }

    public void resetInvestigation(ActionEvent ae){
        FacesContext fc = FacesContext.getCurrentInstance();
       // sessionBean.removeAllHrEmpLocInvest();
        sessionBean.removeAllHrLocationInvestSetting();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ–› Ã„Ì⁄ «·»Ì«‰«  «·Œ«’… »«·≈” ﬁ’«¡ «·”«»ﬁ"));
    }

    /** Creates a new instance of ProfilePrivilages */
    public InvestigationSettings() {
    }

    public List<HrLocationInvestSetting> getHrLocationInvestSettings() {
        return hrLocationInvestSettings;
    }

    public void setHrLocationInvestSettings(List<HrLocationInvestSetting> hrLocationInvestSettings) {
        this.hrLocationInvestSettings = hrLocationInvestSettings;
    }



    public void updateSelectedGrp() {
        filteredGrp = selectedGrp;
    }

    public void updateSelectedJob() {
        filteredJob = selectedJob;
    }

    public void updateSelectedDept() {
        filteredDept = selectedDept;
    }

    public void updateSelectedLoc() {
        filteredLoc = selectedLoc;
    }

    public void updateSelectedEmp() {
        filteredEmp = selectedEmp;
    }

  

    public HrLocationInvestSetting getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(HrLocationInvestSetting selectedRow) {
        this.selectedRow = selectedRow;
    }

    public List<HrManagement> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<HrManagement> deptList) {
        this.deptList = deptList;
    }

    public List<HrEmpInfo> getEmpList() {
        return empList;
    }

    public void setEmpList(List<HrEmpInfo> empList) {
        this.empList = empList;
    }

    public List<HrJobGrp> getJobGrpList() {
        return jobGrpList;
    }

    public void setJobGrpList(List<HrJobGrp> jobGrpList) {
        this.jobGrpList = jobGrpList;
    }

    public List<HrJobs> getJobList() {
        return jobList;
    }

    public void setJobList(List<HrJobs> jobList) {
        this.jobList = jobList;
    }

    public List<HrLocation> getLocList() {
        return locList;
    }

    public void setLocList(List<HrLocation> locList) {
        this.locList = locList;
    }

    public HrManagement getFilteredDept() {
        return filteredDept;
    }

    public void setFilteredDept(HrManagement filteredDept) {
        this.filteredDept = filteredDept;
    }

    public HrEmpInfo getFilteredEmp() {
        return filteredEmp;
    }

    public void setFilteredEmp(HrEmpInfo filteredEmp) {
        this.filteredEmp = filteredEmp;
    }

    public HrJobGrp getFilteredGrp() {
        return filteredGrp;
    }

    public void setFilteredGrp(HrJobGrp filteredGrp) {
        this.filteredGrp = filteredGrp;
    }

    public HrJobs getFilteredJob() {
        return filteredJob;
    }

    public void setFilteredJob(HrJobs filteredJob) {
        this.filteredJob = filteredJob;
    }

    public HrLocation getFilteredLoc() {
        return filteredLoc;
    }

    public void setFilteredLoc(HrLocation filteredLoc) {
        this.filteredLoc = filteredLoc;
    }

    public HrManagement getSelectedDept() {
        return selectedDept;
    }

    public void setSelectedDept(HrManagement selectedDept) {
        this.selectedDept = selectedDept;
    }

    public HrEmpInfo getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(HrEmpInfo selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public HrJobGrp getSelectedGrp() {
        return selectedGrp;
    }

    public void setSelectedGrp(HrJobGrp selectedGrp) {
        this.selectedGrp = selectedGrp;
    }

    public HrJobs getSelectedJob() {
        return selectedJob;
    }

    public void setSelectedJob(HrJobs selectedJob) {
        this.selectedJob = selectedJob;
    }

    public HrLocation getSelectedLoc() {
        return selectedLoc;
    }

    public void setSelectedLoc(HrLocation selectedLoc) {
        this.selectedLoc = selectedLoc;
    }

    public List<SelectItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<SelectItem> orderList) {
        this.orderList = orderList;
    }

    public String[] getSelectedOrderList() {
        return selectedOrderList;
    }

    public void setSelectedOrderList(String[] selectedOrderList) {
        this.selectedOrderList = selectedOrderList;
    }

    
}

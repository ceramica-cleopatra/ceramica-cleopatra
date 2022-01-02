/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrJobGrp;
import e.HrJobs;
import e.HrLocation;
import e.HrManagement;
import e.HrMenuTable;
import e.HrProfileAccessLog;
import e.HrProfilePrivilage;
import e.HrUsers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import sb.SessionBeanLocal;

/**
 *
 * @author a.abbas
 */
@ManagedBean
@ViewScoped
public class ProfilePrivilagesReport {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<Object[]> resultList = new ArrayList<Object[]>();
    private List<HrJobGrp> jobGrpList;
    private List<HrManagement> deptList;
    private List<HrEmpInfo> empList;
    private List<HrJobs> jobList;
    private List<HrLocation> locList;
    private List<HrMenuTable> menuList;
    private HrJobGrp filteredGrp;
    private HrJobs filteredJob;
    private HrManagement filteredDept;
    private HrEmpInfo filteredEmp;
    private HrLocation filteredLoc;
    private HrMenuTable filteredMen;
    private String[] selectedOrderList;
    private List<SelectItem> orderList;
    private HrJobGrp selectedGrp;
    private HrJobs selectedJob;
    private HrManagement selectedDept;
    private HrEmpInfo selectedEmp;
    private HrLocation selectedLoc;
    private HrMenuTable selectedMen;
    private MenuModel model;
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
//        resultList=sessionBean.profilePrivilageReport(null,null,null,null,null,null, null);
        jobGrpList = sessionBean.findJobGrpList();
        deptList = sessionBean.findDeptList();
        empList = sessionBean.findEmpList();
        jobList = sessionBean.findJobList();
        locList = sessionBean.findLocationList();
        menuList = sessionBean.findAllMenuList();
        selectedGrp = new HrJobGrp();
        selectedJob = new HrJobs();
        selectedDept = new HrManagement();
        selectedEmp = new HrEmpInfo();
        selectedLoc = new HrLocation();
        selectedMen = new HrMenuTable();
        filteredGrp = new HrJobGrp();
        filteredJob = new HrJobs();
        filteredDept = new HrManagement();
        filteredEmp = new HrEmpInfo();
        filteredLoc = new HrLocation();
        filteredMen = new HrMenuTable();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Locale locale = facesContext.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages_ar", locale);
        orderList = new ArrayList<SelectItem>();
        orderList.add(new SelectItem("info.job_grp_name", bundle.getString("JobGrpName")));
        orderList.add(new SelectItem("info.job_name", bundle.getString("JobName")));
        orderList.add(new SelectItem("info.location", bundle.getString("LocationName")));
        orderList.add(new SelectItem("info.dept_name", bundle.getString("DeptName")));
        orderList.add(new SelectItem("info.emp_name", bundle.getString("EmployeeName")));
        orderList.add(new SelectItem("m.arabic_name", bundle.getString("PageName")));
    }

    public void serach(ActionEvent ae) {
        String orderByList = "";
        for (int i = 0; i < selectedOrderList.length; i++) {
            orderByList += selectedOrderList[i] + (i == selectedOrderList.length - 1 ? "" : ",");
        }
        resultList = sessionBean.profilePrivilageReport(filteredGrp.getId(), filteredJob.getId(), filteredLoc.getId(), filteredDept.getId(), filteredEmp == null ? null : filteredEmp.getEmpNo(), filteredMen.getId(), orderByList);
    }

    public void resetValues() {
        resultList = new ArrayList<Object[]>();
        filteredGrp = new HrJobGrp();
        filteredJob = new HrJobs();
        filteredDept = new HrManagement();
        filteredEmp = new HrEmpInfo();
        filteredLoc = new HrLocation();
        filteredMen = new HrMenuTable();
    }

    public void constructMenu(ActionEvent ae) {
        model = new DefaultMenuModel();
        List<Object[]> list=sessionBean.findMenuToConstruct(filteredGrp.getId(), filteredJob.getId(), filteredLoc.getId(), filteredDept.getId(), filteredEmp == null ? null : filteredEmp.getEmpNo(), filteredMen.getId());
        for(Object[] o1 : list) {
            if (o1[2] == null) {
                DefaultSubMenu submenu = new DefaultSubMenu((String) o1[0]);
                List<HrMenuTable> childList = new ArrayList<HrMenuTable>();
                for(Object[] o2 : list) {
                    if (o2[2] != null && o1[1].equals(o2[2])) {
                        DefaultMenuItem item = new DefaultMenuItem(o2[0]);
                        submenu.addElement(item);
                    }
                }
                model.addElement(submenu);
            }
        }

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

    public void updateSelectedMenu() {
        filteredMen = selectedMen;
    }

    public void newSearch(ActionEvent ae) {
        resetValues();
    }

    /** Creates a new instance of ProfilePrivilagesReport */
    public ProfilePrivilagesReport() {
    }

    public List<Object[]> getResultList() {
        return resultList;
    }

    public void setResultList(List<Object[]> resultList) {
        this.resultList = resultList;
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

    public HrMenuTable getFilteredMen() {
        return filteredMen;
    }

    public void setFilteredMen(HrMenuTable filteredMen) {
        this.filteredMen = filteredMen;
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

    public List<HrMenuTable> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<HrMenuTable> menuList) {
        this.menuList = menuList;
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

    public HrMenuTable getSelectedMen() {
        return selectedMen;
    }

    public void setSelectedMen(HrMenuTable selectedMen) {
        this.selectedMen = selectedMen;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    
}

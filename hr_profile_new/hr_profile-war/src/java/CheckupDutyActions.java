/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrCheckupDutyHd1;
import e.HrEmpInfo;
import e.HrJobGrp;
import e.HrLocation;
import e.HrManagement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class CheckupDutyActions {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrManagement filteredDept;
    private HrEmpInfo filteredEmp;
    private HrLocation filteredLoc;
    private List<HrManagement> deptList;
    private List<HrEmpInfo> empList;
    private List<HrLocation> locList;
    private HrManagement selectedDept;
    private HrEmpInfo selectedEmp;
    private HrLocation selectedLoc;
    private Date fromDate;
    private Date toDate;
    private Date tempFromDate;
    private Date tempToDate;
    private int approvedFlag;
    private List<HrCheckupDutyHd1> checkupDutyList;
    private HrCheckupDutyHd1 selectedDuty;

    @PostConstruct
    public void init() {
        deptList = sessionBean.findDeptList();
        empList = sessionBean.findEmpList();
        locList = sessionBean.findLocationList();
        selectedDept = new HrManagement();
        selectedEmp = new HrEmpInfo();
        selectedLoc = new HrLocation();
        filteredDept = new HrManagement();
        filteredEmp = new HrEmpInfo();
        filteredLoc = new HrLocation();
        checkupDutyList = new ArrayList<HrCheckupDutyHd1>();
    }

    public String resetValues() {
        checkupDutyList = new ArrayList<HrCheckupDutyHd1>();
        selectedDept = new HrManagement();
        selectedEmp = new HrEmpInfo();
        selectedLoc = new HrLocation();
        filteredDept = new HrManagement();
        filteredEmp = new HrEmpInfo();
        filteredLoc = new HrLocation();
        approvedFlag=3;
        tempFromDate=null;
        tempToDate=null;
        return null;
    }

    public String search() {
        if (fromDate == null) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, 1000);
            tempFromDate = c.getTime();
        }else{
            tempFromDate=fromDate;
        }

        if (toDate == null) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, 3000);
            tempToDate = c.getTime();
        }else{
            tempToDate=toDate;
        }
        checkupDutyList = sessionBean.findCheckupDutyForAddAction(filteredEmp==null || filteredEmp.getEmpName() == null ? null : filteredEmp, filteredLoc==null || filteredLoc.getName() == null ? null : filteredLoc,filteredDept==null ||  filteredDept.getName() == null ? null : filteredDept.getId(), tempFromDate, tempToDate, approvedFlag);
        return null;
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

    /** Creates a new instance of CheckupDutyActions */
    public CheckupDutyActions() {
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

    public HrLocation getFilteredLoc() {
        return filteredLoc;
    }

    public void setFilteredLoc(HrLocation filteredLoc) {
        this.filteredLoc = filteredLoc;
    }

    public List<HrLocation> getLocList() {
        return locList;
    }

    public void setLocList(List<HrLocation> locList) {
        this.locList = locList;
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

    public HrLocation getSelectedLoc() {
        return selectedLoc;
    }

    public void setSelectedLoc(HrLocation selectedLoc) {
        this.selectedLoc = selectedLoc;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getApprovedFlag() {
        return approvedFlag;
    }

    public void setApprovedFlag(int approvedFlag) {
        this.approvedFlag = approvedFlag;
    }

    public List<HrCheckupDutyHd1> getCheckupDutyList() {
        return checkupDutyList;
    }

    public void setCheckupDutyList(List<HrCheckupDutyHd1> checkupDutyList) {
        this.checkupDutyList = checkupDutyList;
    }

    public HrCheckupDutyHd1 getSelectedDuty() {
        return selectedDuty;
    }

    public void setSelectedDuty(HrCheckupDutyHd1 selectedDuty) {
        this.selectedDuty = selectedDuty;
    }
}

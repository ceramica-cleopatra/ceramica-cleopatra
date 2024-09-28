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
import e.HrProfilePrifilage;
import e.HrProfilePrivilage;
import e.HrUsers;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.codehaus.jackson.map.ObjectMapper;
import org.primefaces.context.RequestContext;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;

/**
 *
 * @author a.abbas
 */
@ManagedBean
@ViewScoped
public class ProfilePrivilages {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrProfilePrifilage> hrProfilePrifilageList = new ArrayList<HrProfilePrifilage>();
    private HrProfilePrifilage selectedRow;
    private List<HrJobGrp> jobGrpList;
    private List<HrManagement> deptList;
    private List<HrEmpInfo> empList;
    private List<HrJobs> jobList;
    private List<HrLocation> locList;
    private List<HrMenuTable> menuList;
    private HrJobGrp selectedGrp;
    private HrJobs selectedJob;
    private HrManagement selectedDept;
    private HrEmpInfo selectedEmp;
    private HrLocation selectedLoc;
    private HrMenuTable selectedMen;
    private HrJobGrp filteredGrp;
    private HrJobs filteredJob;
    private HrManagement filteredDept;
    private HrEmpInfo filteredEmp;
    private HrLocation filteredLoc;
    private HrMenuTable filteredMen;
    private String[] selectedOrderList;
    private List<SelectItem> orderList;
    private List<HrProfilePrivilage> hrProfilePrivilageList;

    @PostConstruct
    public void init() {
        HrEmpInfo hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrProfilePrifilageList = sessionBean.findAllPrivilages(null, null, null, null, null, null, null);
        jobGrpList = sessionBean.findJobGrpList();
        deptList = sessionBean.findDeptList();
        empList = sessionBean.findEmpList();
        jobList = sessionBean.findJobList();
        locList = sessionBean.findLocationList();
        menuList = sessionBean.findMenuList();
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
        orderList.add(new SelectItem("g.name", bundle.getString("JobGrpName")));
        orderList.add(new SelectItem("j.name", bundle.getString("JobName")));
        orderList.add(new SelectItem("l.name", bundle.getString("LocationName")));
        orderList.add(new SelectItem("d.name", bundle.getString("DeptName")));
        orderList.add(new SelectItem("e.emp_name", bundle.getString("EmployeeName")));
        orderList.add(new SelectItem("m.arabic_name", bundle.getString("PageName")));
    }

    public void serach(ActionEvent ae) {
        String orderByList = "";
        for (int i = 0; i < selectedOrderList.length; i++) {
            orderByList += selectedOrderList[i] + (i == selectedOrderList.length - 1 ? "" : ",");
        }
        hrProfilePrifilageList = sessionBean.findAllPrivilages(filteredGrp.getId(), filteredJob.getId(), filteredLoc.getId(), filteredDept.getId(), filteredEmp == null ? null : filteredEmp.getEmpNo(), filteredMen.getId(), orderByList);
    }

    public void resetValues() {
        hrProfilePrifilageList = new ArrayList<HrProfilePrifilage>();
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
    }

    public void newSearch(ActionEvent ae) {
        resetValues();
    }

    public void addNew(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (filteredMen == null || filteredMen.getId() == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈Œ Ì«— «·‘«‘…"));
            return;
        }
        if ((filteredDept == null || filteredDept.getId() == null) && (filteredEmp == null || filteredEmp.getEmpNo() == 0) && (filteredGrp == null || filteredGrp.getId() == null) && (filteredJob == null || filteredJob.getId() == null) && (filteredLoc == null || filteredLoc.getId() == null)) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈Œ Ì«— ≈œŒ«· «·›∆… «·„”„ÊÕ ·Â« »Â–Â «·‘«‘…"));
            return;
        }

        Long cnt = sessionBean.checkParentPrivilages(filteredGrp != null ? filteredGrp.getId() : null, filteredJob != null ? filteredJob.getId() : null, filteredLoc != null ? filteredLoc.getId() : null, filteredDept != null ? filteredDept.getId() : null, filteredEmp != null ? filteredEmp.getEmpNo() : null, filteredMen != null ? filteredMen.getParentId() : null);
        HrProfilePrifilage parentProfilePrivilage = null;
        if (cnt == 0) {
            parentProfilePrivilage = new HrProfilePrifilage();
            Long id = sessionBean.findHrProfilePrifilageNextId();
            parentProfilePrivilage.setId((id == null ? 0 : id) + 1);
            parentProfilePrivilage.setDeptId(filteredDept != null && filteredDept.getId() != null ? filteredDept : null);
            parentProfilePrivilage.setEmpId(filteredEmp != null && filteredEmp.getEmpNo() != 0 ? filteredEmp : null);
            parentProfilePrivilage.setGrpId(filteredGrp != null && filteredGrp.getId() != null ? filteredGrp : null);
            parentProfilePrivilage.setJobId(filteredJob != null && filteredJob.getId() != null ? filteredJob : null);
            parentProfilePrivilage.setLocId(filteredLoc != null && filteredLoc.getId() != null ? filteredLoc : null);
            HrMenuTable parentMenu = sessionBean.findMenuById(filteredMen.getParentId());
            parentProfilePrivilage.setNewMenuId(parentMenu);
            sessionBean.persistProfilePrivilage(parentProfilePrivilage);

            List<Object[]> privilageList = sessionBean.findAllPrivilagesByEmpNo(filteredGrp != null ? filteredGrp.getId() : null, filteredJob != null ? filteredJob.getId() : null, filteredLoc != null ? filteredLoc.getId() : null, filteredDept != null ? filteredDept.getId() : null, filteredEmp != null ? filteredEmp.getEmpNo() : null, filteredMen != null ? filteredMen.getParentId() : null);
            ObjectMapper objectMapper = new ObjectMapper();
            for (Object[] privilage : privilageList) {
                HrProfilePrivilageDTO hrProfilePrivilageDTO = new HrProfilePrivilageDTO(Long.parseLong(String.valueOf(privilage[0])), Long.parseLong(String.valueOf(privilage[1])),
                        new HrMenuTableDTO(filteredMen.getParentId(), parentMenu.getMenuName(), parentMenu.getArabicName(), parentMenu.getIcon(), null, parentMenu.getMenuOrder()), parentMenu.getArabicName());
                Jedis jedis = null;
                try {
                    String hrProfilePrivilageAsString = objectMapper.writeValueAsString(hrProfilePrivilageDTO);
                    jedis = WorkerBean.pool.getResource();
                    if (jedis.hgetAll("menu:" + hrProfilePrivilageDTO.getEmpNo()) != null && !jedis.hgetAll("menu:" + hrProfilePrivilageDTO.getEmpNo()).isEmpty()) {
                        jedis.hset("menu:" + hrProfilePrivilageDTO.getEmpNo(), hrProfilePrivilageDTO.getId().toString(), hrProfilePrivilageAsString);
                        jedis.expire("menu:" + hrProfilePrivilageDTO.getEmpNo(), Integer.MAX_VALUE);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CashHandler.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }

            }
        }

        HrProfilePrifilage hrProfilePrifilage = new HrProfilePrifilage();
        Long id = sessionBean.findHrProfilePrifilageNextId();
        hrProfilePrifilage.setId((id == null ? 0 : id) + 1);
        hrProfilePrifilage.setDeptId(filteredDept != null && filteredDept.getId() != null ? filteredDept : null);
        hrProfilePrifilage.setEmpId(filteredEmp != null && filteredEmp.getEmpNo() != 0 ? filteredEmp : null);
        hrProfilePrifilage.setGrpId(filteredGrp != null && filteredGrp.getId() != null ? filteredGrp : null);
        hrProfilePrifilage.setJobId(filteredJob != null && filteredJob.getId() != null ? filteredJob : null);
        hrProfilePrifilage.setLocId(filteredLoc != null && filteredLoc.getId() != null ? filteredLoc : null);
        hrProfilePrifilage.setNewMenuId(filteredMen);
        sessionBean.persistProfilePrivilage(hrProfilePrifilage);
        List<Object[]> privilageList = sessionBean.findAllPrivilagesByEmpNo(filteredGrp != null ? filteredGrp.getId() : null, filteredJob != null ? filteredJob.getId() : null, filteredLoc != null ? filteredLoc.getId() : null, filteredDept != null ? filteredDept.getId() : null, filteredEmp != null ? filteredEmp.getEmpNo() : null, filteredMen != null ? filteredMen.getId() : null);
        ObjectMapper objectMapper = new ObjectMapper();
        for (Object[] privilage : privilageList) {
            HrProfilePrivilageDTO hrProfilePrivilageDTO = new HrProfilePrivilageDTO(Long.parseLong(String.valueOf(privilage[0])), Long.parseLong(String.valueOf(privilage[1])),
                    new HrMenuTableDTO(filteredMen.getId(), filteredMen.getMenuName(), filteredMen.getArabicName(), filteredMen.getIcon(), filteredMen.getParentId(), filteredMen.getMenuOrder()), filteredMen.getArabicName());
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                String hrProfilePrivilageAsString = objectMapper.writeValueAsString(hrProfilePrivilageDTO);
                if (jedis.hgetAll("menu:" + hrProfilePrivilageDTO.getEmpNo()) != null && !jedis.hgetAll("menu:" + hrProfilePrivilageDTO.getEmpNo()).isEmpty()) {
                    jedis.hset("menu:" + hrProfilePrivilageDTO.getEmpNo(), hrProfilePrivilageDTO.getId().toString(), hrProfilePrivilageAsString);
                    jedis.expire("menu:" + hrProfilePrivilageDTO.getEmpNo(), Integer.MAX_VALUE);
                }
            } catch (IOException ex) {
                Logger.getLogger(CashHandler.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ ≈œŒ«· «·”„«ÕÌ… »‰Ã«Õ"));
        hrProfilePrifilageList = sessionBean.findAllPrivilages(filteredGrp != null ? filteredGrp.getId() : null, filteredJob != null ? filteredJob.getId() : null, filteredLoc != null ? filteredLoc.getId() : null, filteredDept != null ? filteredDept.getId() : null, filteredEmp != null ? filteredEmp.getEmpNo() : null, filteredMen != null ? filteredMen.getId() : null, null);
    }

    public void delete(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(fc.getExternalContext().getRequestParameterMap().get("id").toString());
        HrProfilePrifilage hrProfilePrifilage = sessionBean.findPrivilageById(id);
        List<Object[]> privilageList = sessionBean.findAllPrivilagesByEmpNo(hrProfilePrifilage.getGrpId() != null ? hrProfilePrifilage.getGrpId().getId() : null,
                hrProfilePrifilage.getJobId() != null ? hrProfilePrifilage.getJobId().getId() : null, hrProfilePrifilage.getLocId() != null ? hrProfilePrifilage.getLocId().getId() : null,
                hrProfilePrifilage.getDeptId() != null ? hrProfilePrifilage.getDeptId().getId() : null, hrProfilePrifilage.getEmpId() != null ? hrProfilePrifilage.getEmpId().getEmpNo()
                : null, hrProfilePrifilage.getNewMenuId() != null ? hrProfilePrifilage.getNewMenuId().getId() : null);
        sessionBean.deleteProfilePrifilage(hrProfilePrifilage);
        for (Object[] privilage : privilageList) {
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                jedis.hdel("menu:" + privilage[1], new String[]{privilage[0].toString()});
            } finally {
                if (jedis != null) {
                    jedis.close();

                }
            }

        }
        hrProfilePrifilageList = sessionBean.findAllPrivilages(filteredGrp != null ? filteredGrp.getId() : null, filteredJob != null ? filteredJob.getId() : null, filteredLoc != null ? filteredLoc.getId() : null, filteredDept != null ? filteredDept.getId() : null, filteredEmp != null ? filteredEmp.getEmpNo() : null, filteredMen != null ? filteredMen.getId() : null, null);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ–› «·”„«ÕÌ… »‰Ã«Õ"));
    }

    /** Creates a new instance of ProfilePrivilages */
    public ProfilePrivilages() {
    }

    public List<HrProfilePrifilage> getHrProfilePrifilageList() {
        return hrProfilePrifilageList;
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

    public void setHrProfilePrifilageList(List<HrProfilePrifilage> hrProfilePrifilageList) {
        this.hrProfilePrifilageList = hrProfilePrifilageList;
    }

    public HrProfilePrifilage getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(HrProfilePrifilage selectedRow) {
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

    public List<HrMenuTable> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<HrMenuTable> menuList) {
        this.menuList = menuList;
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

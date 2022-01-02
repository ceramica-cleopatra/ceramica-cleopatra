/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrAllShowroomTrgt;
import e.HrEmpInfo;
import e.HrJobGrp;
import e.HrJobs;
import e.HrLocation;
import e.HrManagement;
import e.HrProfileAccessLog;
import e.HrProfileAlertHd;
import e.HrProfileAlertReceiver;
import e.HrUsers;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.primefaces.component.editor.Editor;
import org.primefaces.event.SelectEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class add_alert {

    @EJB
    private SessionBeanLocal sessionBean;
    private String alert;
    private Character active;
    private String bgColor;
    boolean active_boolean;
    private HrProfileAlertHd hrProfileAlertHd;
    private List<HrJobGrp> jobGrpList;
    private List<HrManagement> deptList;
    private List<HrJobs> jobList;
    private List<HrLocation> locList;
    private HrJobGrp selectedGrp;
    private HrJobs selectedJob;
    private HrManagement selectedDept;
    private HrLocation selectedLoc;
    private HrJobGrp filteredGrp;
    private HrJobs filteredJob;
    private HrManagement filteredDept;
    private HrLocation filteredLoc;
    private Editor editor = new Editor();
    private List<HrProfileAlertHd> list = new ArrayList<HrProfileAlertHd>();
    private List<HrProfileAlertReceiver> hrProfileAlertReceiverList;

    @PostConstruct
    public void init() {
        list = sessionBean.hrProfileAlertHdList();
        hrProfileAlertHd = new HrProfileAlertHd();
        jobGrpList = sessionBean.findJobGrpList();
        deptList = sessionBean.findDeptList();
        jobList = sessionBean.findJobList();
        locList = sessionBean.findLocationList();
        hrProfileAlertReceiverList = new ArrayList<HrProfileAlertReceiver>();
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
    }

    public add_alert() {
    }

    public String save_alert() {
        if (hrProfileAlertHd == null) {
            hrProfileAlertHd = new HrProfileAlertHd();
        }
        hrProfileAlertHd.setAlert(alert);
        hrProfileAlertHd.setBgColor(bgColor);
        if (active_boolean) {
          /*  for (HrProfileAlertHd x : list) {
                if (x.getActive().equals(new Character(('Y'))) && (hrProfileAlertHd.getId() == null || !x.getId().equals(hrProfileAlertHd.getId()))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰  ›⁄Ì· √ﬂÀ— „‰  ‰»ÌÂ"));
                    return "";
                }
            }*/
            hrProfileAlertHd.setActive(new Character(('Y')));
        } else {
            hrProfileAlertHd.setActive(new Character('N'));
        }
        if (hrProfileAlertHd.getId() == null) {
            Long id = sessionBean.getAlertHdMaxId() + 1;
            hrProfileAlertHd.setId(id);
            hrProfileAlertHd.setTrnsDate(new Date());
            sessionBean.persistAlertHd(hrProfileAlertHd);
           for (HrProfileAlertReceiver reciver : hrProfileAlertReceiverList) {
                reciver.setHdId(hrProfileAlertHd);
                sessionBean.saveHrProfileAlertRecivers(reciver);
            }
        } else {
            sessionBean.deleteAllReciversForAlert(hrProfileAlertHd);
            for (HrProfileAlertReceiver reciver : hrProfileAlertReceiverList) {
                reciver.setHdId(hrProfileAlertHd);
                sessionBean.saveHrProfileAlertRecivers(reciver);
            }
            hrProfileAlertHd.setHrProfileAlertReceivers(hrProfileAlertReceiverList);
            sessionBean.mergeAlertHd(hrProfileAlertHd);
        }

        hrProfileAlertHd = new HrProfileAlertHd();
        hrProfileAlertReceiverList=new ArrayList<HrProfileAlertReceiver>();
        list = sessionBean.hrProfileAlertHdList();
        active_boolean = false;
        bgColor = null;
        alert = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ Õ›Ÿ «· ‰»ÌÂ »‰Ã«Õ", ""));
        return null;
    }

    public String addNew() {
        active = null;
        alert = null;
        active_boolean = false;
        bgColor = null;
        hrProfileAlertHd = new HrProfileAlertHd();
        hrProfileAlertReceiverList=new ArrayList<HrProfileAlertReceiver>();
        return null;
    }

    public void row_select(SelectEvent e) {
        if (hrProfileAlertHd != null) {
            active = hrProfileAlertHd.getActive();
            if (active.equals('Y')) {
                active_boolean = true;
            } else {
                active_boolean = false;
            }
            alert = hrProfileAlertHd.getAlert();
            bgColor = hrProfileAlertHd.getBgColor();
            hrProfileAlertReceiverList=hrProfileAlertHd.getHrProfileAlertReceivers();
            System.out.println("select " + alert);
        }
    }

    public void addReceiver(ActionEvent e) {
        if((selectedDept==null || selectedDept.getId()==null) &&
           (selectedGrp==null || selectedGrp.getId()==null) &&
           (selectedJob==null || selectedJob.getId()==null) &&
           (selectedLoc==null || selectedLoc.getId()==null)){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","ÌÃ»  ÕœÌœ «·ÊŸ«∆›, «·„Ê«ﬁ⁄ , «·√ﬁ”«„ √Ê «·„Ã„Ê⁄… «·ÊŸÌ›Ì…"));
           return;
        }
        HrProfileAlertReceiver hrProfileAlertReceiver=new HrProfileAlertReceiver();
        hrProfileAlertReceiver.setDeptId(selectedDept);
        hrProfileAlertReceiver.setJobGrpId(selectedGrp);
        hrProfileAlertReceiver.setJobId(selectedJob);
        hrProfileAlertReceiver.setLocId(selectedLoc);
        hrProfileAlertReceiverList.add(hrProfileAlertReceiver);
        hrProfileAlertReceiver = new HrProfileAlertReceiver();
        filteredDept=new HrManagement();
        filteredGrp=new HrJobGrp();
        filteredJob=new HrJobs();
        filteredLoc=new HrLocation();
    }

    public String deleteReceiver() {
        String receiver=FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap().get("receiverId");
        System.out.println("receiver"+receiver);
        HrProfileAlertReceiver hrProfileAlertReceiver=new HrProfileAlertReceiver();
        hrProfileAlertReceiver.setId(new BigDecimal(receiver));
        sessionBean.deleteAlertReciver(hrProfileAlertReceiver);
        list = sessionBean.hrProfileAlertHdList();
        hrProfileAlertReceiverList=hrProfileAlertHd.getHrProfileAlertReceivers();
        hrProfileAlertHd=new HrProfileAlertHd();
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR," „ »‰Ã«Õ"," „ «·Õ–› »‰Ã«Õ"));

        return null;
    }

    public Character getActive() {
        return active;
    }

    public void setActive(Character active) {
        this.active = active;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public boolean isActive_boolean() {
        return active_boolean;
    }

    public void setActive_boolean(boolean active_boolean) {
        this.active_boolean = active_boolean;
    }

    public List<HrProfileAlertHd> getList() {
        return list;
    }

    public void setList(List<HrProfileAlertHd> list) {
        this.list = list;
    }

    public HrProfileAlertHd getHrProfileAlertHd() {
        return hrProfileAlertHd;
    }

    public void setHrProfileAlertHd(HrProfileAlertHd hrProfileAlertHd) {
        this.hrProfileAlertHd = hrProfileAlertHd;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public List<HrManagement> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<HrManagement> deptList) {
        this.deptList = deptList;
    }

    public HrManagement getFilteredDept() {
        return filteredDept;
    }

    public void setFilteredDept(HrManagement filteredDept) {
        this.filteredDept = filteredDept;
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

    public HrManagement getSelectedDept() {
        return selectedDept;
    }

    public void setSelectedDept(HrManagement selectedDept) {
        this.selectedDept = selectedDept;
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
        System.out.println("filteredLoc>>" + filteredLoc);
    }

    public List<HrProfileAlertReceiver> getHrProfileAlertReceiverList() {
        return hrProfileAlertReceiverList;
    }

    public void setHrProfileAlertReceiverList(List<HrProfileAlertReceiver> hrProfileAlertReceiverList) {
        this.hrProfileAlertReceiverList = hrProfileAlertReceiverList;
    }

    
}

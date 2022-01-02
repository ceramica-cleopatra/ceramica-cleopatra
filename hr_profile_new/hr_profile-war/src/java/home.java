/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrActiveAlert;
import e.HrArea;
import e.HrDynAlertDt;
import e.HrDynAlertHd;
import e.HrDynAlertTemplateDt;
import e.HrDynAlertTemplateHd;
import e.HrEmpHd;
import e.HrEmpInfo;
import e.HrEmpLocInvest;
import e.HrLocation;
import e.HrProfileAccessLog;
import e.HrProfileAlertDt;
import e.HrProfileAlertHd;
import e.HrProfileCustomVote;
import e.HrProfileMessage;
import e.HrProfileMsg;
import e.HrRegion;
import e.HrUsers;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean(name = "home")
@ViewScoped
public class home implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrProfileMessage> publicMsgs = new ArrayList<HrProfileMessage>();
    private HrActiveAlert hrActiveAlert;
    private String displayAlert = "N";
    private String usercode;
    private Long chkInvest = 1L;
    private HrEmpLocInvest hrEmpLocInvest = new HrEmpLocInvest();
    private List<HrArea> allArea;
    private List<HrRegion> allRegion;
    private HrArea selectedArea = new HrArea();
    private HrRegion selectedRegion = new HrRegion();
    private boolean otherArea = false;
    private HrLocation choose1Loc;
    private HrLocation choose2Loc;
    private HrLocation choose3Loc;
    private List<HrLocation> allLocations = new ArrayList<HrLocation>();
    private HrEmpInfo hrEmpInfo;
    private HrProfileCustomVote hrProfileCustomVote;
    private HrDynAlertTemplateHd hrDynAlertTemplateHd;

    @PostConstruct
    public void init() {
        long t1 = System.currentTimeMillis();
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        List<Object[]> activeAlerts = sessionBean.getEmpActiveAlerts(hrEmpInfo.getDeptId(), hrEmpInfo.getJobId(), hrEmpInfo.getLocId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getEmpNo());
        if (!activeAlerts.isEmpty()) {
            hrActiveAlert = new HrActiveAlert();
            hrActiveAlert.setAlertId(Long.parseLong(activeAlerts.get(0)[0].toString()));
            hrActiveAlert.setAlertTxt((String) activeAlerts.get(0)[1]);
            hrActiveAlert.setBgColor((String) activeAlerts.get(0)[2]);
            displayAlert = "Y";
        } else {
            displayAlert = "N";
        }
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        //publicMsgs = sessionBean.find_messages();

        allArea=sessionBean.findAllArea();
        allRegion=sessionBean.findAllRegion();
        //allLocations=sessionBean.findLocationList();
        List<HrLocation> showRoomList = sessionBean.findAllShowRooms();
        allLocations=sessionBean.findSpecificLocationList(hrEmpInfo.getLocId());
        boolean showInvest=false;
        for(HrLocation hrLocation : showRoomList){
            if(hrLocation.getId().equals(hrEmpInfo.getLocId()))
                showInvest = true;
        }
        if(showInvest){
            chkInvest=sessionBean.chkLocInvest(Long.parseLong(usercode));
            hrEmpLocInvest.setTrnsFlag('Y');
            RequestContext.getCurrentInstance().execute("PF('investDlg').show();");
        }

//         List<HrDynAlertTemplateHd> alertList= sessionBean.findAlertsNotApplied(hrEmpInfo.getEmpNo());
//         hrDynAlertTemplateHd =null;
//         for(HrDynAlertTemplateHd currentAlert : alertList){
//             if((currentAlert.getDeptId()==null || (currentAlert.getDeptId()!=null && currentAlert.getDeptId().getId().equals(hrEmpInfo.getDeptId()))) &&
//                (currentAlert.getJobGrpId()==null || (currentAlert.getJobGrpId()!=null && currentAlert.getJobGrpId().getId().equals(hrEmpInfo.getJobGrpId()))) &&
//                (currentAlert.getJobId()==null || (currentAlert.getJobId()!=null && currentAlert.getJobId().getId().equals(hrEmpInfo.getJobId()))) &&
//                (currentAlert.getLocId()==null || (currentAlert.getLocId()!=null && currentAlert.getLocId().getId().equals(hrEmpInfo.getLocId())))){
//                hrDynAlertTemplateHd=currentAlert;
//                break;
//             }
//         }
        if (hrDynAlertTemplateHd != null) {
            RequestContext.getCurrentInstance().execute("PF('dynAlertDlg').show();");
            hrProfileCustomVote = new HrProfileCustomVote();
        }
        System.out.println("home>>>>>" + (System.currentTimeMillis() - t1));
    }

    public void readAlertDone(ActionEvent ae) {
        HrProfileAlertDt hrProfileAlertDt = new HrProfileAlertDt();
        hrProfileAlertDt.setId(sessionBean.getAlertDtMaxId() + 1);
        FacesContext fc = FacesContext.getCurrentInstance();
        hrProfileAlertDt.setEmpNo(Long.parseLong(usercode));
        HrProfileAlertHd hrProfileAlertHd = new HrProfileAlertHd();
        hrProfileAlertHd.setId(hrActiveAlert.getAlertId());
        hrProfileAlertDt.setHrProfileAlertHd(hrProfileAlertHd);
        sessionBean.persistAlertDt(hrProfileAlertDt);
        hrActiveAlert = null;
        displayAlert = "N";
    }
    /*
    public void saveWizard(ActionEvent ae){
    if((hrEmpInfo.getJobId().equals(49L) ||hrEmpLocInvest.getTrnsFlag().equals('Y')) && (choose1Loc==null || choose1Loc.getName()==null || choose2Loc==null || choose2Loc.getName()==null || choose3Loc==null || choose3Loc.getName()==null)){
    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"ÌÃ»  ÕœÌœ «·„Ê«ﬁ⁄ «· Ï  —€» ›Ï «·≈‰ ﬁ«· ≈·ÌÂ« »«· — Ì»","ÌÃ»  ÕœÌœ «·„Ê«ﬁ⁄ «· Ï  —€» ›Ï «·≈‰ ﬁ«· ≈·ÌÂ« »«· — Ì»"));
    return;
    }
    hrEmpLocInvest.setEmpId(Long.parseLong(usercode));
    hrEmpLocInvest.setGovId(selectedArea!=null ? selectedArea.getId() : null);
    hrEmpLocInvest.setLocId1(choose1Loc!=null ? choose1Loc.getId() : null);
    hrEmpLocInvest.setLocId2(choose2Loc !=null ? choose2Loc.getId() : null);
    hrEmpLocInvest.setLocId3(choose3Loc !=null ? choose3Loc.getId() : null);
    hrEmpLocInvest.setRegionId(selectedRegion!=null ? selectedRegion.getId() : null);
    hrEmpLocInvest.setId(sessionBean.findHrEmpLocInvestId()+1L);
    chkInvest=1L;
    RequestContext.getCurrentInstance().execute("PF('investDlg').hide();");
    if(hrEmpInfo.getJobId().equals(49L))
    hrEmpLocInvest.setTrnsFlag(new Character('Y'));
    sessionBean.persistHrEmpInvestLoc(hrEmpLocInvest);
    }
     */

    public void saveWizard(ActionEvent ae) {
        if (choose1Loc == null || choose1Loc.getName() == null || choose2Loc == null || choose2Loc.getName() == null || choose3Loc == null || choose3Loc.getName() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ÌÃ»  ÕœÌœ «·„Ê«ﬁ⁄ «· Ï  —€» ›Ï «·≈‰ ﬁ«· ≈·ÌÂ« »«· — Ì»", "ÌÃ»  ÕœÌœ «·„Ê«ﬁ⁄ «· Ï  —€» ›Ï «·≈‰ ﬁ«· ≈·ÌÂ« »«· — Ì»"));
            return;
        }
        if (choose1Loc.equals(choose2Loc) || choose2Loc.equals(choose3Loc) || choose1Loc.equals(choose3Loc)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ÌÃ» ≈Œ Ì«— „Ê«ﬁ⁄ „Œ ·›…", "ÌÃ» ≈Œ Ì«— „Ê«ﬁ⁄ „Œ ·›…"));
            return;
        }
        hrEmpLocInvest.setEmpId(Long.parseLong(usercode));
        hrEmpLocInvest.setGovId(selectedArea != null ? selectedArea.getId() : null);
        hrEmpLocInvest.setLocId1(choose1Loc != null ? choose1Loc.getId() : null);
        hrEmpLocInvest.setLocId2(choose2Loc != null ? choose2Loc.getId() : null);
        hrEmpLocInvest.setLocId3(choose3Loc != null ? choose3Loc.getId() : null);
        hrEmpLocInvest.setRegionId(selectedRegion != null ? selectedRegion.getId() : null);
        hrEmpLocInvest.setId(sessionBean.findHrEmpLocInvestId() + 1L);
        chkInvest = 1L;
        RequestContext.getCurrentInstance().execute("PF('investDlg').hide();");
        sessionBean.persistHrEmpInvestLoc(hrEmpLocInvest);
    }

    public String saveDynAlert() {
        HrDynAlertHd hrDynAlertHd = new HrDynAlertHd();
        hrDynAlertHd.setEmpNo(hrEmpInfo.getEmpNo());
        hrDynAlertHd.setTemplateId(hrDynAlertTemplateHd);
        hrDynAlertHd.setTrnsDate(new Date());
        List<HrDynAlertDt> hrDynAlertDts = new ArrayList<HrDynAlertDt>();
        for (HrDynAlertTemplateDt hrDynAlertTemplateDt : hrDynAlertTemplateHd.getHrDynAlertTemplateDtList()) {
            if (hrDynAlertTemplateDt.getCompId().equals(6L) || hrDynAlertTemplateDt.getCompId().equals(7L)) {
                continue;
            }
            HrDynAlertDt hrDynAlertDt = new HrDynAlertDt();
            hrDynAlertDt.setTxt(hrDynAlertTemplateDt.getTxt());
            hrDynAlertDt.setValue(hrDynAlertTemplateDt.getValue());
            hrDynAlertDt.setHrDynAlertHd(hrDynAlertHd);
            hrDynAlertDt.setCompId(hrDynAlertTemplateDt.getCompId());
            if (hrDynAlertTemplateDt.getBooleanValue() != null) {
                hrDynAlertDt.setBooleanValue(hrDynAlertTemplateDt.getBooleanValue() ? 1L : 0L);
            }
            hrDynAlertDt.setCommentLabel(hrDynAlertTemplateDt.getCommentLabel());
            hrDynAlertDt.setCommentValue(hrDynAlertTemplateDt.getCommentValue());
            hrDynAlertDts.add(hrDynAlertDt);
        }
        hrDynAlertHd.setHrDynAlertDtList(hrDynAlertDts);
        sessionBean.persistHrDynAlertHd(hrDynAlertHd);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «· ’ÊÌ  »‰Ã«Õ"));
        RequestContext.getCurrentInstance().execute("PF('dynAlertDlg').hide();");
        return null;
    }

    public home() {
    }

    public void updateAmra(ActionEvent ae) {
    }

    public HrActiveAlert getHrActiveAlert() {
        return hrActiveAlert;
    }

    public void setHrActiveAlert(HrActiveAlert hrActiveAlert) {
        this.hrActiveAlert = hrActiveAlert;
    }

    public List<HrProfileMessage> getPublicMsgs() {
        return publicMsgs;
    }

    public void setPublicMsgs(List<HrProfileMessage> publicMsgs) {
        this.publicMsgs = publicMsgs;
    }

    public String getDisplayAlert() {
        return displayAlert;
    }

    public void setDisplayAlert(String displayAlert) {
        this.displayAlert = displayAlert;
    }

    public Long getChkInvest() {
        if (chkInvest.equals(0L)) {
            RequestContext.getCurrentInstance().execute("PF('investDlg').show();");
        }
        return chkInvest;
    }

    public void setChkInvest(Long chkInvest) {
        this.chkInvest = chkInvest;
    }

    public String onFlowProcess(FlowEvent event) {
        if (!event.getOldStep().equals("alert") && (selectedRegion == null || selectedRegion.getId() == null || ((selectedArea == null || selectedArea.getId() == null) && (hrEmpLocInvest.getOtherRegion() == null || hrEmpLocInvest.getOtherRegion().isEmpty())))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ÌÃ» ≈œŒ«· «·„œÌ‰… Ê«·„‰ÿﬁ…", "ÌÃ» ≈œŒ«· «·„œÌ‰… Ê«·„‰ÿﬁ…"));
            return "address";
        }
        if (otherArea && (hrEmpLocInvest.getOtherRegion() == null || hrEmpLocInvest.getOtherRegion().isEmpty())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ÌÃ» ≈œŒ«· «·„‰ÿﬁ… «·√Œ—Ï", "ÌÃ» ≈œŒ«· «·„‰ÿﬁ… «·√Œ—Ï"));
            return "address";
        }
        return event.getNewStep();
    }

    public void onLoc1Selected() {
        System.out.println("selectedLoc1" + choose1Loc.getName());
    }

    public void onLoc2Selected() {
        System.out.println("selectedLoc2" + choose2Loc.getName());
    }

    public void onLoc3Selected() {
        System.out.println("selectedLoc3" + choose1Loc.getName());
    }

    public void onSelectRadioButton() {
        System.out.println("choose" + hrEmpLocInvest.getTrnsFlag());
    }

    public HrEmpLocInvest getHrEmpLocInvest() {
        return hrEmpLocInvest;
    }

    public void setHrEmpLocInvest(HrEmpLocInvest hrEmpLocInvest) {
        this.hrEmpLocInvest = hrEmpLocInvest;
    }

    public void onAreaSelected() {
        System.out.println("seelcted Area>>>" + selectedArea.getName());
    }

    public void onRegionSelected() {
        System.out.println("seelcted Region>>>" + selectedRegion.getName());
    }

    public void onSelectOtherArea() {
        System.out.println("otheArea>>>" + otherArea);
    }

    public List<HrArea> getAllArea() {
        return allArea;
    }

    public void setAllArea(List<HrArea> allArea) {
        this.allArea = allArea;
    }

    public List<HrRegion> getAllRegion() {
        return allRegion;
    }

    public void setAllRegion(List<HrRegion> allRegion) {
        this.allRegion = allRegion;
    }

    public HrArea getSelectedArea() {
        return selectedArea;
    }

    public void setSelectedArea(HrArea selectedArea) {
        this.selectedArea = selectedArea;
    }

    public HrRegion getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(HrRegion selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

    public boolean isOtherArea() {
        return otherArea;
    }

    public void setOtherArea(boolean otherArea) {
        this.otherArea = otherArea;
    }

    public HrLocation getChoose1Loc() {
        return choose1Loc;
    }

    public void setChoose1Loc(HrLocation choose1Loc) {
        this.choose1Loc = choose1Loc;
    }

    public HrLocation getChoose2Loc() {
        return choose2Loc;
    }

    public void setChoose2Loc(HrLocation choose2Loc) {
        this.choose2Loc = choose2Loc;
    }

    public HrLocation getChoose3Loc() {
        return choose3Loc;
    }

    public void setChoose3Loc(HrLocation choose3Loc) {
        this.choose3Loc = choose3Loc;
    }

    public List<HrLocation> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(List<HrLocation> allLocations) {
        this.allLocations = allLocations;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

    public HrProfileCustomVote getHrProfileCustomVote() {
        return hrProfileCustomVote;
    }

    public void setHrProfileCustomVote(HrProfileCustomVote hrProfileCustomVote) {
        this.hrProfileCustomVote = hrProfileCustomVote;
    }

    public HrDynAlertTemplateHd getHrDynAlertTemplateHd() {
        return hrDynAlertTemplateHd;
    }

    public void setHrDynAlertTemplateHd(HrDynAlertTemplateHd hrDynAlertTemplateHd) {
        this.hrDynAlertTemplateHd = hrDynAlertTemplateHd;
    }
}

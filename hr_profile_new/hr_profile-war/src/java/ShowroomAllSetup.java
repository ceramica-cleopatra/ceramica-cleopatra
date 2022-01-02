/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrLocation;
import e.HrMainTrgtLevelsDt;
import e.HrMainTrgtLevelsHd;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.model.DualListModel;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class ShowroomAllSetup {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrMainTrgtLevelsHd> hrMainTrgtLevelList;
    private List<HrLocation> sourceLocationList;
    private List<HrLocation> destinationLocationList;
    private DualListModel<HrLocation> allLocations;
    private HrEmpInfo filteredEmp;
    private List<HrEmpInfo> empList;
    private HrEmpInfo selectedEmp;
    private List<HrLocation> abstractList;
    private HrMainTrgtLevelsHd selectedLevelHd;
    private boolean updateFlag;

    /** Creates a new instance of ShowroomAllSetup */
    public ShowroomAllSetup() {
    }

    @PostConstruct
    public void init() {
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        hrMainTrgtLevelList = sessionBean.getMainTrgtLevelList();
        abstractList = sessionBean.findAllShowRooms();
        sourceLocationList = new ArrayList<HrLocation>();
        sourceLocationList.addAll(abstractList);
        destinationLocationList = new ArrayList<HrLocation>();
        selectedEmp = new HrEmpInfo();
        empList = sessionBean.findEmpList();
        allLocations = new DualListModel<HrLocation>(sourceLocationList, destinationLocationList);
    }

    public void saveChanges() {

        if (allLocations.getTarget().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ «·„Ê«ﬁ⁄"));
            return;
        }
        if (updateFlag) {
            for (HrMainTrgtLevelsDt hrMainTrgtLevelsDt : selectedLevelHd.getHrMainTrgtLevelsDtList()) {
                sessionBean.removeHrMainTrgtLevelDt(hrMainTrgtLevelsDt);
            }
            List<HrMainTrgtLevelsDt> hrMainTrgtLevelsDts = new ArrayList<HrMainTrgtLevelsDt>();
            for (HrLocation hrLocation : allLocations.getTarget()) {
                HrMainTrgtLevelsDt hrMainTrgtLevelsDt = new HrMainTrgtLevelsDt();
                hrMainTrgtLevelsDt.setBrnId(hrLocation);
                hrMainTrgtLevelsDt.setHrMainTrgtLevelsHd(selectedLevelHd);
                sessionBean.saveHrMainTrgtLevelDt(hrMainTrgtLevelsDt);
                hrMainTrgtLevelsDts.add(hrMainTrgtLevelsDt);
            }
            selectedLevelHd.setHrMainTrgtLevelsDtList(hrMainTrgtLevelsDts);
        } else {
            if (selectedEmp == null || selectedEmp.getEmpName() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈Œ Ì«— «·„ÊŸ›"));
                return;
            }
            HrMainTrgtLevelsHd hrMainTrgtLevelsHd = new HrMainTrgtLevelsHd();
            List<HrMainTrgtLevelsDt> hrMainTrgtLevelsDts = new ArrayList<HrMainTrgtLevelsDt>();
            hrMainTrgtLevelsHd.setEmpNo(selectedEmp);
            hrMainTrgtLevelsHd.setHrMainTrgtLevelsDtList(hrMainTrgtLevelsDts);
            sessionBean.saveHrMainTrgtLevelHd(hrMainTrgtLevelsHd);
            for (HrLocation hrLocation : allLocations.getTarget()) {
                HrMainTrgtLevelsDt hrMainTrgtLevelsDt = new HrMainTrgtLevelsDt();
                hrMainTrgtLevelsDt.setBrnId(hrLocation);
                hrMainTrgtLevelsDt.setHrMainTrgtLevelsHd(hrMainTrgtLevelsHd);
                hrMainTrgtLevelsDts.add(hrMainTrgtLevelsDt);
                sessionBean.saveHrMainTrgtLevelDt(hrMainTrgtLevelsDt);
            }
            hrMainTrgtLevelsHd.setHrMainTrgtLevelsDtList(hrMainTrgtLevelsDts);
            hrMainTrgtLevelList.add(hrMainTrgtLevelsHd);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
    }

    public void deleteTrgtLevelHd(ActionEvent e) {
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        HrMainTrgtLevelsHd hrMainTrgtLevelsHd = hrMainTrgtLevelList.get(Integer.parseInt(param));
//        for (HrMainTrgtLevelsDt hrMainTrgtLevelsDt : hrMainTrgtLevelsHd.getHrMainTrgtLevelsDtList()) {
//            sessionBean.removeHrMainTrgtLevelDt(hrMainTrgtLevelsDt);
//        }
        sessionBean.removeHrMainTrgtLevelHd(hrMainTrgtLevelsHd);
        hrMainTrgtLevelList.remove(Integer.parseInt(param));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ–› »‰Ã«Õ"));
    }

    public void updateSelectedLevel() {
        updateFlag = true;
        selectedEmp = filteredEmp = selectedLevelHd.getEmpNo();
        destinationLocationList = new ArrayList<HrLocation>();
        for (HrMainTrgtLevelsDt hrMainTrgtLevelsDt : selectedLevelHd.getHrMainTrgtLevelsDtList()) {
            destinationLocationList.add(hrMainTrgtLevelsDt.getBrnId());
        }
        sourceLocationList = new ArrayList<HrLocation>();
        sourceLocationList.addAll(abstractList);
        sourceLocationList.removeAll(destinationLocationList);
        allLocations = new DualListModel<HrLocation>(sourceLocationList, destinationLocationList);
    }

    public void updateLocations() {
        updateFlag = false;
        filteredEmp = new HrEmpInfo();
        selectedEmp = new HrEmpInfo();
        sourceLocationList = new ArrayList<HrLocation>();
        sourceLocationList.addAll(abstractList);
        destinationLocationList = new ArrayList<HrLocation>();
        allLocations = new DualListModel<HrLocation>(sourceLocationList, destinationLocationList);
    }

    public void updateSelectedEmp() {
        filteredEmp = selectedEmp;
    }

    public List<HrMainTrgtLevelsHd> getHrMainTrgtLevelList() {
        return hrMainTrgtLevelList;
    }

    public void setHrMainTrgtLevelList(List<HrMainTrgtLevelsHd> hrMainTrgtLevelList) {
        this.hrMainTrgtLevelList = hrMainTrgtLevelList;
    }

    public List<HrLocation> getDestinationLocationList() {
        return destinationLocationList;
    }

    public void setDestinationLocationList(List<HrLocation> destinationLocationList) {
        this.destinationLocationList = destinationLocationList;
    }

    public List<HrLocation> getSourceLocationList() {
        return sourceLocationList;
    }

    public void setSourceLocationList(List<HrLocation> sourceLocationList) {
        this.sourceLocationList = sourceLocationList;
    }

    public DualListModel<HrLocation> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(DualListModel<HrLocation> allLocations) {
        this.allLocations = allLocations;
    }

    public HrEmpInfo getFilteredEmp() {
        return filteredEmp;
    }

    public void setFilteredEmp(HrEmpInfo filteredEmp) {
        this.filteredEmp = filteredEmp;
    }

    public List<HrEmpInfo> getEmpList() {
        return empList;
    }

    public void setEmpList(List<HrEmpInfo> empList) {
        this.empList = empList;
    }

    public HrEmpInfo getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(HrEmpInfo selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public HrMainTrgtLevelsHd getSelectedLevelHd() {
        return selectedLevelHd;
    }

    public void setSelectedLevelHd(HrMainTrgtLevelsHd selectedLevelHd) {
        this.selectedLevelHd = selectedLevelHd;
    }

    public boolean isUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(boolean updateFlag) {
        this.updateFlag = updateFlag;
    }
}

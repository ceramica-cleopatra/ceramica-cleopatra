/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrDynAlertTemplateCompDt;
import e.HrDynAlertTemplateDt;
import e.HrDynAlertTemplateHd;
import e.HrJobGrp;
import e.HrJobs;
import e.HrLocation;
import e.HrManagement;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class DynamicAlert {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrDynAlertTemplateHd hrDynAlertTemplateHd;
    private List<DynamicAlertInputItemsDTO> inputItemList = new ArrayList<DynamicAlertInputItemsDTO>();
    private List<HrDynAlertTemplateDt> selectedList = new ArrayList<HrDynAlertTemplateDt>();
    private HrDynAlertTemplateHd templateHd;
    private HrDynAlertTemplateDt templateDt;
    private String txt;
    private Boolean required;
    private Boolean addComment;
    private String commentLabel;
    private Long width;
    private Long height;
    private Long dialogWidth;
    private Long dialogHeight;
    private String title;
    private Boolean active;
    private String itemValue;
    private String itemLabel;
    private List<HrDynAlertTemplateCompDt> compValues = new ArrayList<HrDynAlertTemplateCompDt>();
    private Map<Integer, List<SelectItem>> manyItemValuesMap = new HashMap<Integer, List<SelectItem>>();
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
    private HrDynAlertTemplateDt selectedComp;
    private HrDynAlertTemplateDt copyComp;
    private List<HrDynAlertTemplateHd> alertList = new ArrayList<HrDynAlertTemplateHd>();
    private HrDynAlertTemplateHd selectedAlert = new HrDynAlertTemplateHd();
    private Integer compIndx;

    @PostConstruct
    public void init() {
        hrDynAlertTemplateHd = sessionBean.findActiveAlert();
        inputItemList.add(new DynamicAlertInputItemsDTO(1L, "InputText"));
        inputItemList.add(new DynamicAlertInputItemsDTO(2L, "SelectOneMenu"));
        inputItemList.add(new DynamicAlertInputItemsDTO(3L, "RadioButton"));
        inputItemList.add(new DynamicAlertInputItemsDTO(4L, "InputTextarea"));
        inputItemList.add(new DynamicAlertInputItemsDTO(5L, "SelectBooleanCheckbox"));
        inputItemList.add(new DynamicAlertInputItemsDTO(6L, "Spacer"));
        inputItemList.add(new DynamicAlertInputItemsDTO(7L, "TEXT"));

        jobGrpList = sessionBean.findJobGrpList();
        deptList = sessionBean.findDeptList();
        jobList = sessionBean.findJobList();
        locList = sessionBean.findLocationList();
        selectedGrp = new HrJobGrp();
        selectedJob = new HrJobs();
        selectedDept = new HrManagement();
        selectedLoc = new HrLocation();
        filteredGrp = new HrJobGrp();
        filteredJob = new HrJobs();
        filteredDept = new HrManagement();
        filteredLoc = new HrLocation();
        alertList = sessionBean.findAlertList();
    }

    public void onDrop(DragDropEvent ddEvent) {
        DynamicAlertInputItemsDTO item = ((DynamicAlertInputItemsDTO) ddEvent.getData());
        templateDt = new HrDynAlertTemplateDt();
        templateDt.setCompId(item.getItemId());

        if (item.getItemId().equals(1L) || item.getItemId().equals(4L) || item.getItemId().equals(5L)) {
            RequestContext.getCurrentInstance().execute("PF('inputTxtDlg').show();");
        } else if (item.getItemId().equals(2L) || item.getItemId().equals(3L)) {
            RequestContext.getCurrentInstance().execute("PF('selectOneMenuDlg').show();");
        } else if (item.getItemId().equals(6L)) {
            RequestContext.getCurrentInstance().execute("PF('spacerDlg').show();");
        } else if (item.getItemId().equals(7L)) {
            RequestContext.getCurrentInstance().execute("PF('txtDlg').show();");
        }
    }

    public String addComponent() {
        if (templateDt.getCompId().equals(2L) || templateDt.getCompId().equals(3L)) {
            if (compValues == null || compValues.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «·≈Œ Ì«—« "));
                return null;
            }
            templateDt.setHrDynAlertTemplateCompDtList(compValues);
            List<SelectItem> selectItems = new ArrayList<SelectItem>();
            for (HrDynAlertTemplateCompDt compDt : compValues) {
                selectItems.add(new SelectItem(compDt.getItemValue(), compDt.getItemLabel()));
            }
            manyItemValuesMap.put(selectedList.size(), selectItems);
        }
        templateDt.setTxt(txt);
        templateDt.setAddComment(addComment);
        templateDt.setCommentLabel(commentLabel);
        templateDt.setRequired(required);
        templateDt.setCompWidth(width);
        templateDt.setCompHeight(height);
        templateDt.setHrDynAlertTemplateCompDtList(compValues);
        selectedList.add(templateDt);
        resetValues();
        return null;
    }

    public void resetValues() {
        txt = null;
        required = false;
        addComment = false;
        commentLabel = null;
        width = null;
        height = null;
        itemValue = null;
        itemLabel = null;
        compValues = new ArrayList<HrDynAlertTemplateCompDt>();
        selectedComp=null;
        compIndx=null;
    }

    public String deleteComp() {
        String indx = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx");
        selectedList.remove(Integer.parseInt(indx));
        return null;
    }

    public String showDlgToUpdateComp(){
      try{
        copyComp=(HrDynAlertTemplateDt) selectedComp.clone();
       } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AdvanceRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (selectedComp.getCompId().equals(1L) || selectedComp.getCompId().equals(4L) || selectedComp.getCompId().equals(5L)) {
            RequestContext.getCurrentInstance().execute("PF('inputTxtDlgUpdate').show();");
        } else if (selectedComp.getCompId().equals(2L) || selectedComp.getCompId().equals(3L)) {
            compIndx=0;
            for(HrDynAlertTemplateDt tempId:selectedList){
                if(tempId==selectedComp){
                    break;
                }
                compIndx++;
            }
            RequestContext.getCurrentInstance().execute("PF('selectOneMenuDlgUpdate').show();");
        } else if (selectedComp.getCompId().equals(6L)) {
            RequestContext.getCurrentInstance().execute("PF('spacerDlgUpdate').show();");
        } else if (selectedComp.getCompId().equals(7L)) {
            RequestContext.getCurrentInstance().execute("PF('txtDlgUpdate').show();");
        }
        return null;
    }

    public String cancelUpdateComp(){
        selectedComp=copyComp;
        return null;
    }

    public String addItem() {
        if (itemLabel == null || itemValue == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " ÌÃ» ≈œŒ«· «·ﬁÌ„… Ê «·‰’ ··≈Œ Ì«—"));
            return null;
        }
        HrDynAlertTemplateCompDt hrDynAlertTemplateCompDt = new HrDynAlertTemplateCompDt();
        hrDynAlertTemplateCompDt.setItemLabel(itemLabel);
        hrDynAlertTemplateCompDt.setItemValue(itemValue);
        if(selectedComp!=null && selectedComp.getCompId()!=null){
            hrDynAlertTemplateCompDt.setHrDynAlertTemplateDt(selectedComp);
            manyItemValuesMap.get(compIndx).add(new SelectItem(itemValue,itemLabel));
            selectedComp.getHrDynAlertTemplateCompDtList().add(hrDynAlertTemplateCompDt);
        }
        else{
            hrDynAlertTemplateCompDt.setHrDynAlertTemplateDt(templateDt);
            compValues.add(hrDynAlertTemplateCompDt);
        }
        itemLabel = null;
        itemValue = null;
        compIndx=null;
        return null;
    }

    public String saveAlert() {
        HrDynAlertTemplateHd hrDynAlertTemplateHd = null;
        if (selectedAlert != null && selectedAlert.getId() != null) {
            hrDynAlertTemplateHd = selectedAlert;
            for (HrDynAlertTemplateDt hrDynAlertTemplateDt : selectedAlert.getHrDynAlertTemplateDtList()) {
                sessionBean.removeAlertDT(hrDynAlertTemplateDt);
            }
        } else {
            hrDynAlertTemplateHd = new HrDynAlertTemplateHd();
        }
        hrDynAlertTemplateHd.setActive(active ? 'Y' : 'N');
        hrDynAlertTemplateHd.setDeptId(selectedDept);
        hrDynAlertTemplateHd.setJobGrpId(selectedGrp);
        hrDynAlertTemplateHd.setJobId(selectedJob);
        hrDynAlertTemplateHd.setLocId(selectedLoc);
        hrDynAlertTemplateHd.setTitle(title);
        hrDynAlertTemplateHd.setWidth(dialogWidth);
        hrDynAlertTemplateHd.setHeight(dialogHeight);
        hrDynAlertTemplateHd.setTrnsDate(new Date());
        hrDynAlertTemplateHd.setHrDynAlertTemplateDtList(selectedList);
        for (HrDynAlertTemplateDt hrDynAlertTemplateDt : selectedList) {
            hrDynAlertTemplateDt.setHrDynAlertTemplateHd(hrDynAlertTemplateHd);
        }

        if (selectedAlert != null && selectedAlert.getId() != null) {
            for (HrDynAlertTemplateDt hrDynAlertTemplateDt : selectedList) {
                sessionBean.persistHrDynAlertTemplateDt(hrDynAlertTemplateDt);
            }
            sessionBean.updateAlertHD(hrDynAlertTemplateHd);
        } else {
            sessionBean.persistHrDynAlertTemplateHd(hrDynAlertTemplateHd);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
        resetValues();
        selectedAlert = null;
        title = null;
        dialogHeight=null;
        dialogWidth=null;
        active = null;
        selectedList=new ArrayList<HrDynAlertTemplateDt>();
        manyItemValuesMap = new HashMap<Integer, List<SelectItem>>();
        filteredDept = selectedDept = null;
        filteredGrp = selectedGrp = null;
        filteredJob = selectedJob = null;
        filteredLoc = selectedLoc = null;
        return null;
    }

    public String findPreviousAlertList() {
        alertList = sessionBean.findAlertList();
        return null;
    }

    public String cancelComponent() {
        resetValues();
        return null;
    }

    public String newAlert() {
        resetValues();
        selectedComp=new HrDynAlertTemplateDt();
        selectedAlert = null;
        title = null;
        dialogHeight=null;
        dialogWidth=null;
        active = null;
        selectedList=new ArrayList<HrDynAlertTemplateDt>();
        manyItemValuesMap = new HashMap<Integer, List<SelectItem>>();
        filteredDept = selectedDept = null;
        filteredGrp = selectedGrp = null;
        filteredJob = selectedJob = null;
        filteredLoc = selectedLoc = null;
        return null;
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

    public void updateSelectedAlert() {
        active = selectedAlert.getActive().equals('Y') ? true : false;
        filteredDept = selectedDept = selectedAlert.getDeptId();
        filteredGrp = selectedGrp = selectedAlert.getJobGrpId();
        filteredJob = selectedJob = selectedAlert.getJobId();
        filteredLoc = selectedLoc = selectedAlert.getLocId();
        active = selectedAlert.getActive().equals('Y') ? true : false;
        title = selectedAlert.getTitle();
        dialogWidth = selectedAlert.getWidth();
        dialogHeight= selectedAlert.getHeight();
        Collections.sort(selectedAlert.getHrDynAlertTemplateDtList(), new DynamicAlertTemplateComparator());
        manyItemValuesMap = new HashMap<Integer, List<SelectItem>>();
        selectedList = new ArrayList<HrDynAlertTemplateDt>();
        for (HrDynAlertTemplateDt hrDynAlertTemplateDt : selectedAlert.getHrDynAlertTemplateDtList()) {
            selectedList.add(hrDynAlertTemplateDt);
            List<SelectItem> selectItems = new ArrayList<SelectItem>();
            for (HrDynAlertTemplateCompDt compDt : hrDynAlertTemplateDt.getHrDynAlertTemplateCompDtList()) {
                selectItems.add(new SelectItem(compDt.getItemValue(), compDt.getItemLabel()));
            }
            manyItemValuesMap.put(selectedList.size() - 1, selectItems);
        }
    }

    /** Creates a new instance of DynamicAlert */
    public DynamicAlert() {
    }

    public HrDynAlertTemplateHd getHrDynAlertTemplateHd() {
        return hrDynAlertTemplateHd;


    }

    public void setHrDynAlertTemplateHd(HrDynAlertTemplateHd hrDynAlertTemplateHd) {
        this.hrDynAlertTemplateHd = hrDynAlertTemplateHd;


    }

    public List<DynamicAlertInputItemsDTO> getInputItemList() {
        return inputItemList;


    }

    public void setInputItemList(List<DynamicAlertInputItemsDTO> inputItemList) {
        this.inputItemList = inputItemList;


    }

    public List<HrDynAlertTemplateDt> getSelectedList() {
        return selectedList;


    }

    public void setSelectedList(List<HrDynAlertTemplateDt> selectedList) {
        this.selectedList = selectedList;


    }

    public HrDynAlertTemplateDt getTemplateDt() {
        return templateDt;


    }

    public void setTemplateDt(HrDynAlertTemplateDt templateDt) {
        this.templateDt = templateDt;


    }

    public HrDynAlertTemplateHd getTemplateHd() {
        return templateHd;


    }

    public void setTemplateHd(HrDynAlertTemplateHd templateHd) {
        this.templateHd = templateHd;


    }

    public String getTxt() {
        return txt;


    }

    public void setTxt(String txt) {
        this.txt = txt;


    }

    public Boolean getAddComment() {
        return addComment;


    }

    public void setAddComment(Boolean addComment) {
        this.addComment = addComment;


    }

    public String getCommentLabel() {
        return commentLabel;


    }

    public void setCommentLabel(String commentLabel) {
        this.commentLabel = commentLabel;


    }

    public Long getHeight() {
        return height;


    }

    public void setHeight(Long height) {
        this.height = height;


    }

    public Boolean getRequired() {
        return required;


    }

    public void setRequired(Boolean required) {
        this.required = required;


    }

    public Long getWidth() {
        return width;


    }

    public void setWidth(Long width) {
        this.width = width;


    }

    public List<HrDynAlertTemplateCompDt> getCompValues() {
        return compValues;


    }

    public void setCompValues(List<HrDynAlertTemplateCompDt> compValues) {
        this.compValues = compValues;


    }

    public String getItemLabel() {
        return itemLabel;


    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;


    }

    public String getItemValue() {
        return itemValue;


    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;


    }

    public Map<Integer, List<SelectItem>> getManyItemValuesMap() {
        return manyItemValuesMap;


    }

    public void setManyItemValuesMap(Map<Integer, List<SelectItem>> manyItemValuesMap) {
        this.manyItemValuesMap = manyItemValuesMap;


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

    public Boolean getActive() {
        return active;


    }

    public void setActive(Boolean active) {
        this.active = active;


    }

    public String getTitle() {
        return title;


    }

    public void setTitle(String title) {
        this.title = title;

    }

    public List<HrDynAlertTemplateHd> getAlertList() {
        alertList = sessionBean.findAlertList();
        return alertList;
    }

    public void setAlertList(List<HrDynAlertTemplateHd> alertList) {
        this.alertList = alertList;
    }

    public HrDynAlertTemplateHd getSelectedAlert() {
        return selectedAlert;
    }

    public void setSelectedAlert(HrDynAlertTemplateHd selectedAlert) {
        this.selectedAlert = selectedAlert;
    }

    public Long getDialogHeight() {
        return dialogHeight;
    }

    public void setDialogHeight(Long dialogHeight) {
        this.dialogHeight = dialogHeight;
    }

    public Long getDialogWidth() {
        return dialogWidth;
    }

    public void setDialogWidth(Long dialogWidth) {
        this.dialogWidth = dialogWidth;
    }

    public HrDynAlertTemplateDt getSelectedComp() {
        return selectedComp;
    }

    public void setSelectedComp(HrDynAlertTemplateDt selectedComp) {
        this.selectedComp = selectedComp;
    }

    
}

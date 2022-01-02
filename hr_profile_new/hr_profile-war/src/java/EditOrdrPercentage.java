/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkBranch;
import e.CrmkCOrdrEmp;
import e.CrmkDOrdrEmp;
import e.CrmkEmpHstry;
import e.CrmkEmployee;
import e.CrmkOrdrEmpLog;
import e.CrmkOrdrHd;
import e.CrmkSOrdrEmp;
import e.HrEmpInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.context.RequestContext;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class EditOrdrPercentage implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    List<CrmkBranch> showRoomList;
    String showroomName;
    Long showroomNo;
    CrmkBranch showroomSelected;
    Long currentShowroomBrnId;
    String crmkDcre;
    Long ordrNo;
    Date ordrDate;
    String customerName;
    CrmkOrdrHd selectedOrdr;
    CrmkCOrdrEmp selectedCordrEmp = new CrmkCOrdrEmp();
    CrmkDOrdrEmp selectedDordrEmp = new CrmkDOrdrEmp();
    CrmkSOrdrEmp selectedSordrEmp = new CrmkSOrdrEmp();
    CrmkEmpHstry selectedEmployee;
    List<CrmkEmpHstry> empList;
    List<CrmkOrdrHd> crmkOrdrHdList;
    private List<SelectItem> groupedEmployees;
    private List<SelectItem> group1;
    private List<SelectItem> group2;
    private String[] selectedEmployees;
    private CrmkBranch currentShowroom;
    private Map<Long, CrmkEmpHstry> employeeMap;
    private HrEmpInfo hrEmpInfo;
    private CrmkEmpHstry singleSelectedEmployee;
    private String singleSelectedEmployeeName;
    private Long singleSelectedEmployeeId;
    private boolean confirmationFlag = false;
    private boolean allConfirmationFlag = false;

    /** Creates a new instance of EditOrdrPercentage */
    public EditOrdrPercentage() {
    }

    @PostConstruct
    public void init() {
        showRoomList = sessionBean.getShow();
        selectedOrdr = new CrmkOrdrHd();
        empList = sessionBean.findAllCrmkEmployees();
        groupedEmployees = new ArrayList<SelectItem>();
        group1 = new ArrayList<SelectItem>();
        employeeMap = new HashMap<Long, CrmkEmpHstry>();
        for (CrmkEmpHstry emp : empList) {
            group1.add(new SelectItem(emp.getHdId().getId(), emp.getHdId().getName()));
            employeeMap.put(emp.getHdId().getId(), emp);
        }
        SelectItemGroup allEmployees = new SelectItemGroup("Ã„Ì⁄ «·„ÊŸ›Ì‰");
        allEmployees.setSelectItems(group1.toArray(new SelectItem[group1.size()]));
        groupedEmployees.addAll(group1);
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
    }

    public void updateShowroomName() {
        showroomName = showroomSelected.getName();
        showroomNo = showroomSelected.getNo();
        currentShowroomBrnId = showroomSelected.getId();
        currentShowroom = showroomSelected;
    }

    public void updateOrdr() {
        customerName = selectedOrdr.getClntName();
        ordrDate = selectedOrdr.getTrnsDate();
    }

    public void findShowroomByNo() {
        showroomSelected = sessionBean.findShowroomByNo(showroomNo);
        if (showroomNo != null && showroomSelected != null) {
            showroomName = showroomSelected.getName();
            currentShowroomBrnId = showroomSelected.getId();
            currentShowroom = showroomSelected;
        } else {
            showroomName = null;
            currentShowroomBrnId = null;
            currentShowroom = null;
        }
    }

    public void findCrmkEmployeeById() {
        if(singleSelectedEmployeeId==null){
            singleSelectedEmployeeName = null;
            singleSelectedEmployee=null;
            return;
        }
        if (selectedOrdr == null || selectedOrdr.getId() == null || crmkDcre == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈Œ Ì«— «·ÿ·»Ì… √Ê·«"));
            return;
        }
        singleSelectedEmployee = sessionBean.findCrmkEmployeeBtId(singleSelectedEmployeeId);
        if (singleSelectedEmployeeId != null && singleSelectedEmployee != null && singleSelectedEmployee.getHdId().getHrId() != null) {
            singleSelectedEmployeeName = singleSelectedEmployee.getHdId().getName();
            System.out.println(singleSelectedEmployeeName);
            confirmationFlag = (singleSelectedEmployee.getBrnId().getId().equals(selectedOrdr.getCrmkBranch().getId()));
        } else {
            singleSelectedEmployeeName = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "·« ÌÊÃœ „ÊŸ› »Â–« «·ﬂÊœ"));
        }
    }

    public String searchOrdr() {
        if (ordrNo == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈œŒ«· —ﬁ„ «·ÿ·»Ì…"));
            return null;
        }
        if (crmkDcre == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈œŒ«· ‰Ê⁄ «·ÿ·»Ì…"));
            return null;
        }
        if (currentShowroomBrnId == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈œŒ«· „⁄—÷ «·ÿ·»Ì…"));
            return null;
        }
        crmkOrdrHdList = sessionBean.findForOrdrPercentage(ordrNo, crmkDcre.charAt(0), currentShowroomBrnId, ordrDate, customerName);
        if (crmkOrdrHdList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "·«  ÊÃœ ÿ·»Ì… »Â–Â «·»Ì«‰« "));
            return null;
        } else if (crmkOrdrHdList.size() > 1) {
            RequestContext.getCurrentInstance().execute("PF('ordrDlg').show();");
            return null;
        } else {
            selectedOrdr = crmkOrdrHdList.get(0);
            customerName = selectedOrdr.getClntName();
            ordrDate = selectedOrdr.getTrnsDate();
            group1 = new ArrayList<SelectItem>();
            group2 = new ArrayList<SelectItem>();
            for (CrmkEmpHstry emp : empList) {
                if (selectedOrdr.getCrmkBranch().getId() != null && selectedOrdr.getCrmkBranch().getId().equals(emp.getBrnId().getId())) {
                    group1.add(new SelectItem(emp.getHdId().getId(), emp.getHdId().getName()));
                } else {
                    group2.add(new SelectItem(emp.getHdId().getId(), emp.getHdId().getName()));
                }
            }
            groupedEmployees = new ArrayList<SelectItem>();
            groupedEmployees.addAll(group1);
            groupedEmployees.addAll(group2);
        }
        return null;
    }

    public String newSearch() {
        showroomName = null;
        showroomNo = null;
        showroomSelected = new CrmkBranch();
        currentShowroomBrnId = null;
        crmkDcre = null;
        ordrNo = null;
        ordrDate = null;
        customerName = null;
        selectedOrdr = new CrmkOrdrHd();
        selectedEmployees = null;
        selectedEmployee = null;
        confirmationFlag = false;
        allConfirmationFlag=false;
        singleSelectedEmployee=null;
        singleSelectedEmployeeId=null;
        singleSelectedEmployeeName=null;
        return null;
    }

    public void deleteEmp(ActionEvent e) {
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (crmkDcre.equals("S")) {
            selectedOrdr.getCrmkSOrdrEmps().remove(Integer.parseInt(param));
        } else if (crmkDcre.equals("C")) {
            selectedOrdr.getCrmkCOrdrEmps().remove(Integer.parseInt(param));
        } else if (crmkDcre.equals("D")) {
            selectedOrdr.getCrmkDOrdrEmps().remove(Integer.parseInt(param));
        }
    }

    public String chooseEmployee() {
        String indx = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx");
        if (crmkDcre.equals("S")) {
            selectedSordrEmp = selectedOrdr.getCrmkSOrdrEmps().get(Integer.parseInt(indx));
        } else if (crmkDcre.equals("C")) {
            selectedCordrEmp = selectedOrdr.getCrmkCOrdrEmps().get(Integer.parseInt(indx));
        } else if (crmkDcre.equals("D")) {
            selectedDordrEmp = selectedOrdr.getCrmkDOrdrEmps().get(Integer.parseInt(indx));
        }
        return null;
    }

    public String addSelectedEmployees() {
        if (selectedOrdr == null || selectedOrdr.getId() == null || crmkDcre == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈Œ Ì«— «·ÿ·»Ì… √Ê·«"));
            return null;
        }
        if (selectedEmployees == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "·„ Ì „ ≈Œ Ì«— „ÊŸ›Ì‰"));
            return null;
        }
        for (int i = 0; i < selectedEmployees.length; i++) {
            if (!employeeMap.get(Long.parseLong(selectedEmployees[i])).getBrnId().getId().equals(selectedOrdr.getCrmkBranch().getId())) {
                allConfirmationFlag = true;
                break;
            }
        }
        /*if (flag) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("confirmation.show()");
        } else {*/
            fillSelectedEmployees();
        //}
            allConfirmationFlag=false;
        return null;
    }

    public void fillSelectedEmployees() {
        for (int i = 0; i < selectedEmployees.length; i++) {
            if (crmkDcre.equals("C")) {
                CrmkCOrdrEmp crmkCOrdrEmp = new CrmkCOrdrEmp();
                crmkCOrdrEmp.setEmpId(employeeMap.get(Long.parseLong(selectedEmployees[i])).getHdId());
                selectedOrdr.getCrmkCOrdrEmps().add(crmkCOrdrEmp);
            } else if (crmkDcre.equals("D")) {
                CrmkDOrdrEmp crmkDOrdrEmp = new CrmkDOrdrEmp();
                crmkDOrdrEmp.setEmpId(employeeMap.get(Long.parseLong(selectedEmployees[i])).getHdId());
                selectedOrdr.getCrmkDOrdrEmps().add(crmkDOrdrEmp);
            } else if (crmkDcre.equals("S")) {
                CrmkSOrdrEmp crmkSOrdrEmp = new CrmkSOrdrEmp();
                crmkSOrdrEmp.setEmpId(employeeMap.get(Long.parseLong(selectedEmployees[i])).getHdId());
                selectedOrdr.getCrmkSOrdrEmps().add(crmkSOrdrEmp);
            }
        }
    }

    public String addSingleSelectedEmployee() {
        if (selectedOrdr == null || selectedOrdr.getId() == null || crmkDcre == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈Œ Ì«— «·ÿ·»Ì… √Ê·«"));
            return null;
        }
        if (singleSelectedEmployee == null || singleSelectedEmployee.getHdId().getHrId() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "·„ Ì „ ≈Œ Ì«— „ÊŸ›Ì‰"));
            return null;
        }
        System.out.println(singleSelectedEmployee.getBrnId().getId());
        System.out.println(selectedOrdr.getCrmkBranch().getId());
        if (crmkDcre.equals("C")) {
            CrmkCOrdrEmp crmkCOrdrEmp = new CrmkCOrdrEmp();
            crmkCOrdrEmp.setEmpId(singleSelectedEmployee.getHdId());
            selectedOrdr.getCrmkCOrdrEmps().add(crmkCOrdrEmp);
        } else if (crmkDcre.equals("D")) {
            CrmkDOrdrEmp crmkDOrdrEmp = new CrmkDOrdrEmp();
            crmkDOrdrEmp.setEmpId(singleSelectedEmployee.getHdId());
            selectedOrdr.getCrmkDOrdrEmps().add(crmkDOrdrEmp);
        } else if (crmkDcre.equals("S")) {
            CrmkSOrdrEmp crmkSOrdrEmp = new CrmkSOrdrEmp();
            crmkSOrdrEmp.setEmpId(singleSelectedEmployee.getHdId());
            selectedOrdr.getCrmkSOrdrEmps().add(crmkSOrdrEmp);
        }
        confirmationFlag = false;
        return null;
    }

    public String save() {
        if (selectedOrdr == null || selectedOrdr.getId() == null || crmkDcre == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈Œ Ì«— «·ÿ·»Ì… √Ê·«"));
            return null;
        }

        CrmkOrdrHd crmkOrdrHd = sessionBean.findCrmkOrdrHdById(selectedOrdr.getId());
        Double totalPercentage = 0D;
        Set<String> distinctEmp = new HashSet<String>();
        if (crmkDcre.equals("C")) {
            for (CrmkCOrdrEmp crmkCOrdrEmp : selectedOrdr.getCrmkCOrdrEmps()) {
                if (crmkCOrdrEmp.getPercent() == null || crmkCOrdrEmp.getPercent() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈œŒ«· ‰”»… ·Ã„Ì⁄ «·„ÊŸ›Ì‰"));
                    return null;
                }
                totalPercentage = totalPercentage + crmkCOrdrEmp.getPercent();
                distinctEmp.add(crmkCOrdrEmp.getEmpId().getName());
            }

            if (Math.round(totalPercentage) != 100) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» √‰ ÌﬂÊ‰ „Ã„Ê⁄ «·‰”» 100"));
                return null;
            }

            if (distinctEmp.size() != selectedOrdr.getCrmkCOrdrEmps().size()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÊÃœ  ﬂ—«— ›Ï «·„ÊŸ›Ì‰"));
                return null;
            }
            /*for (CrmkCOrdrEmp crmkCOrdrEmp : crmkOrdrHd.getCrmkCOrdrEmps()) {
            crmkCOrdrEmp.setUserId(1L);
            sessionBean.deleteCrmkCOrderEmp(crmkCOrdrEmp);
            }*/

            sessionBean.deleteCrmkOrdrEmp(crmkOrdrHd.getId(), crmkDcre);

            for (CrmkCOrdrEmp crmkCOrdrEmp : selectedOrdr.getCrmkCOrdrEmps()) {
                crmkCOrdrEmp.setBrnId(crmkOrdrHd.getCrmkBranch().getId());
                crmkCOrdrEmp.setCrmkOrdrHd(crmkOrdrHd);
                crmkCOrdrEmp.setId(sessionBean.getCrmkOrderEmpId("C", crmkOrdrHd.getCrmkBranch().getId()));
                sessionBean.saveCrmkCOrderEmp(crmkCOrdrEmp);
            }

        } else if (crmkDcre.equals("D")) {
            for (CrmkDOrdrEmp crmkDOrdrEmp : selectedOrdr.getCrmkDOrdrEmps()) {
                if (crmkDOrdrEmp.getPercent() == null || crmkDOrdrEmp.getPercent() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈œŒ«· ‰”»… ·Ã„Ì⁄ «·„ÊŸ›Ì‰"));
                    return null;
                }
                totalPercentage = totalPercentage + crmkDOrdrEmp.getPercent();
                distinctEmp.add(crmkDOrdrEmp.getEmpId().getName());
            }

            if (Math.round(totalPercentage) != 100) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» √‰ ÌﬂÊ‰ „Ã„Ê⁄ «·‰”» 100"));
                return null;
            }

            if (distinctEmp.size() != selectedOrdr.getCrmkDOrdrEmps().size()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÊÃœ  ﬂ—«— ›Ï «·„ÊŸ›Ì‰"));
                return null;
            }

            /*for (CrmkDOrdrEmp crmkDOrdrEmp : crmkOrdrHd.getCrmkDOrdrEmps()) {
            crmkDOrdrEmp.setUserId(1L);
            sessionBean.deleteCrmkDOrderEmp(crmkDOrdrEmp);
            }*/

            sessionBean.deleteCrmkOrdrEmp(crmkOrdrHd.getId(), crmkDcre);

            for (CrmkDOrdrEmp crmkDOrdrEmp : selectedOrdr.getCrmkDOrdrEmps()) {
                crmkDOrdrEmp.setBrnId(crmkOrdrHd.getCrmkBranch().getId());
                crmkDOrdrEmp.setCrmkOrdrHd(crmkOrdrHd);
                crmkDOrdrEmp.setId(sessionBean.getCrmkOrderEmpId("D", crmkOrdrHd.getCrmkBranch().getId()));
                sessionBean.saveCrmkDOrderEmp(crmkDOrdrEmp);
            }
        } else if (crmkDcre.equals("S")) {
            for (CrmkSOrdrEmp crmkSOrdrEmp : selectedOrdr.getCrmkSOrdrEmps()) {
                if (crmkSOrdrEmp.getPercent() == null || crmkSOrdrEmp.getPercent() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈œŒ«· ‰”»… ·Ã„Ì⁄ «·„ÊŸ›Ì‰"));
                    return null;
                }
                totalPercentage = totalPercentage + crmkSOrdrEmp.getPercent();
                distinctEmp.add(crmkSOrdrEmp.getEmpId().getName());
            }

            if (Math.round(totalPercentage) != 100) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» √‰ ÌﬂÊ‰ „Ã„Ê⁄ «·‰”» 100"));
                return null;
            }

            if (distinctEmp.size() != selectedOrdr.getCrmkSOrdrEmps().size()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÊÃœ  ﬂ—«— ›Ï «·„ÊŸ›Ì‰"));
                return null;
            }

            /*for (CrmkSOrdrEmp crmkSOrdrEmp : crmkOrdrHd.getCrmkSOrdrEmps()) {
            crmkSOrdrEmp.setUserId(1L);
            sessionBean.deleteCrmkSOrderEmp(crmkSOrdrEmp);
            }*/

            sessionBean.deleteCrmkOrdrEmp(crmkOrdrHd.getId(), crmkDcre);

            for (CrmkSOrdrEmp crmkSOrdrEmp : selectedOrdr.getCrmkSOrdrEmps()) {
                crmkSOrdrEmp.setBrnId(crmkOrdrHd.getCrmkBranch().getId());
                crmkSOrdrEmp.setCrmkOrdrHd(crmkOrdrHd);
                crmkSOrdrEmp.setId(sessionBean.getCrmkOrderEmpId("S", crmkOrdrHd.getCrmkBranch().getId()));
                sessionBean.saveCrmkSOrderEmp(crmkSOrdrEmp);
            }
        }
        CrmkOrdrEmpLog crmkOrdrEmpLog = new CrmkOrdrEmpLog();
        crmkOrdrEmpLog.setChangeDate(new Date());
        crmkOrdrEmpLog.setOrdrId(selectedOrdr.getId());
        crmkOrdrEmpLog.setUserId(hrEmpInfo.getEmpNo());
        sessionBean.saveCrmkOrdrEmpLog(crmkOrdrEmpLog);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
        return null;
    }

    public String equalDistibute() {
        double temp = 0d;
        if (crmkDcre.equals("C")) {
            for (CrmkCOrdrEmp crmkCOrdrEmp : selectedOrdr.getCrmkCOrdrEmps()) {
                temp = 100d / selectedOrdr.getCrmkCOrdrEmps().size();
                temp = (double) Math.round(temp * 100) / 100;
                crmkCOrdrEmp.setPercent(new Double(temp));
            }
        } else if (crmkDcre.equals("D")) {
            for (CrmkDOrdrEmp crmkDOrdrEmp : selectedOrdr.getCrmkDOrdrEmps()) {
                temp = 100d / selectedOrdr.getCrmkDOrdrEmps().size();
                temp = (double) Math.round(temp * 100) / 100;
                crmkDOrdrEmp.setPercent(new Double(temp));
            }
        } else if (crmkDcre.equals("S")) {
            for (CrmkSOrdrEmp crmkSOrdrEmp : selectedOrdr.getCrmkSOrdrEmps()) {
                temp = 100d / selectedOrdr.getCrmkSOrdrEmps().size();
                temp = (double) Math.round(temp * 100) / 100;
                crmkSOrdrEmp.setPercent(new Double(temp));
            }
        }
        return null;
    }

    public String importShowroomEmployees() {
        if (crmkDcre.equals("C")) {
            selectedOrdr.setCrmkCOrdrEmps(new ArrayList<CrmkCOrdrEmp>());
        } else if (crmkDcre.equals("D")) {
            selectedOrdr.setCrmkDOrdrEmps(new ArrayList<CrmkDOrdrEmp>());
        } else if (crmkDcre.equals("S")) {
            selectedOrdr.setCrmkSOrdrEmps(new ArrayList<CrmkSOrdrEmp>());
        }
        for (CrmkEmpHstry emp : empList) {
            if (selectedOrdr.getCrmkBranch().getId().equals(emp.getBrnId().getId())) {
                if (crmkDcre.equals("C")) {
                    CrmkCOrdrEmp crmkCOrdrEmp = new CrmkCOrdrEmp();
                    crmkCOrdrEmp.setEmpId(emp.getHdId());
                    selectedOrdr.getCrmkCOrdrEmps().add(crmkCOrdrEmp);
                } else if (crmkDcre.equals("D")) {
                    CrmkDOrdrEmp crmkDOrdrEmp = new CrmkDOrdrEmp();
                    crmkDOrdrEmp.setEmpId(emp.getHdId());
                    selectedOrdr.getCrmkDOrdrEmps().add(crmkDOrdrEmp);
                } else if (crmkDcre.equals("S")) {
                    CrmkSOrdrEmp crmkSOrdrEmp = new CrmkSOrdrEmp();
                    crmkSOrdrEmp.setEmpId(emp.getHdId());
                    selectedOrdr.getCrmkSOrdrEmps().add(crmkSOrdrEmp);
                }
            }
        }
        return null;
    }

    public void addEmployee() {
        if (crmkDcre.equals("C")) {
            selectedCordrEmp.setEmpId(selectedEmployee.getHdId());
        } else if (crmkDcre.equals("D")) {
            selectedDordrEmp.setEmpId(selectedEmployee.getHdId());
        } else if (crmkDcre.equals("S")) {
            selectedSordrEmp.setEmpId(selectedEmployee.getHdId());
        }
    }

    public List<CrmkBranch> getShowRoomList() {
        return showRoomList;
    }

    public void setShowRoomList(List<CrmkBranch> showRoomList) {
        this.showRoomList = showRoomList;
    }

    public String getShowroomName() {
        return showroomName;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }

    public CrmkBranch getShowroomSelected() {
        return showroomSelected;
    }

    public void setShowroomSelected(CrmkBranch showroomSelected) {
        this.showroomSelected = showroomSelected;
    }

    public Long getShowroomNo() {
        return showroomNo;
    }

    public void setShowroomNo(Long showroomNo) {
        this.showroomNo = showroomNo;
    }

    public String getCrmkDcre() {
        return crmkDcre;
    }

    public void setCrmkDcre(String crmkDcre) {
        this.crmkDcre = crmkDcre;
    }

    public Long getOrdrNo() {
        return ordrNo;
    }

    public void setOrdrNo(Long ordrNo) {
        this.ordrNo = ordrNo;
    }

    public Date getOrdrDate() {
        return ordrDate;
    }

    public void setOrdrDate(Date ordrDate) {
        this.ordrDate = ordrDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CrmkOrdrHd getSelectedOrdr() {
        return selectedOrdr;
    }

    public void setSelectedOrdr(CrmkOrdrHd selectedOrdr) {
        this.selectedOrdr = selectedOrdr;
    }

    public List<CrmkOrdrHd> getCrmkOrdrHdList() {
        return crmkOrdrHdList;
    }

    public void setCrmkOrdrHdList(List<CrmkOrdrHd> crmkOrdrHdList) {
        this.crmkOrdrHdList = crmkOrdrHdList;
    }

    public CrmkCOrdrEmp getSelectedCordrEmp() {
        return selectedCordrEmp;
    }

    public void setSelectedCordrEmp(CrmkCOrdrEmp selectedCordrEmp) {
        this.selectedCordrEmp = selectedCordrEmp;
    }

    public CrmkDOrdrEmp getSelectedDordrEmp() {
        return selectedDordrEmp;
    }

    public void setSelectedDordrEmp(CrmkDOrdrEmp selectedDordrEmp) {
        this.selectedDordrEmp = selectedDordrEmp;
    }

    public CrmkSOrdrEmp getSelectedSordrEmp() {
        return selectedSordrEmp;
    }

    public void setSelectedSordrEmp(CrmkSOrdrEmp selectedSordrEmp) {
        this.selectedSordrEmp = selectedSordrEmp;
    }

    public CrmkEmpHstry getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(CrmkEmpHstry selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<CrmkEmpHstry> getEmpList() {
        return empList;
    }

    public void setEmpList(List<CrmkEmpHstry> empList) {
        this.empList = empList;
    }

    public List<SelectItem> getGroupedEmployees() {
        return groupedEmployees;
    }

    public void setGroupedEmployees(List<SelectItem> groupedEmployees) {
        this.groupedEmployees = groupedEmployees;
    }

    public String[] getSelectedEmployees() {
        return selectedEmployees;
    }

    public void setSelectedEmployees(String[] selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }

    public CrmkEmpHstry getSingleSelectedEmployee() {
        return singleSelectedEmployee;
    }

    public void setSingleSelectedEmployee(CrmkEmpHstry singleSelectedEmployee) {
        this.singleSelectedEmployee = singleSelectedEmployee;
    }

    public String getSingleSelectedEmployeeName() {
        return singleSelectedEmployeeName;
    }

    public void setSingleSelectedEmployeeName(String singleSelectedEmployeeName) {
        this.singleSelectedEmployeeName = singleSelectedEmployeeName;
    }

    public Long getSingleSelectedEmployeeId() {
        return singleSelectedEmployeeId;
    }

    public void setSingleSelectedEmployeeId(Long singleSelectedEmployeeId) {
        this.singleSelectedEmployeeId = singleSelectedEmployeeId;
    }

    public boolean isConfirmationFlag() {
        return confirmationFlag;
    }

    public void setConfirmationFlag(boolean confirmationFlag) {
        this.confirmationFlag = confirmationFlag;
    }

    public boolean isAllConfirmationFlag() {
        return allConfirmationFlag;
    }

    public void setAllConfirmationFlag(boolean allConfirmationFlag) {
        this.allConfirmationFlag = allConfirmationFlag;
    }

    public Map<Long, CrmkEmpHstry> getEmployeeMap() {
        return employeeMap;
    }

    public void setEmployeeMap(Map<Long, CrmkEmpHstry> employeeMap) {
        this.employeeMap = employeeMap;
    }

    
}

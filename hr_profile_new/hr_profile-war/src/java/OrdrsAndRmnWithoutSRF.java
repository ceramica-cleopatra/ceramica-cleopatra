/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkBranch;
import e.HrEmpInfo;
import e.HrLocation;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sb.SessionBeanLocal;
import org.primefaces.context.RequestContext;
/**
 *
 * @author Administrator
 */
@ManagedBean(name = "ordrsAndRmnWithoutSRF", eager = true)
@SessionScoped
public class OrdrsAndRmnWithoutSRF implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    @ManagedProperty("#{ordrAndRemainMB}")
    private OrdrAndRemainMB ordrAndRemainMB;
    private LazyDataModel<String> clntLazyModel;
    private List<CrmkOrdrAndRmnWithoutSrfDTO> list;
    private LazyDataModel<CrmkOrdrAndRmnWithoutSrfDTO> model;
    private int rowCount;
    private Date fromDate;
    private Date toDate;
    private Long trnsNo;
    private Double actQty;
    private String empName;
    private String empNameSelected;
    private String driverName;
    private String driverNameSelected;
    private Long ordrNo;
    private String clntName;
    private String clntNameSelected;
    private String phone;
    private Long rmnFlag=0L;
    private String crmkSehy;
    private String rmnNo;
    private Long ordrType;
    private Map<String, Object> searchCriteria;
    private Long actualLocation;
    private List<String> empList;
    private List<String> clntList;
    private List<String> drvList;
    private OrdrDtDTO selectedItem;
    private List<String[]> qtyDetailResult;
    private String[] qtyDetailHd;
    private List<ColumnModel> columns;
    private boolean requestFlag;
    private List<OrdrHdDTO> rmnSelectedList;
    private List<CrmkOrdrAndRmnWithoutSrfDTO> backupList;
    private List<SelectItem> storeList;
    private Map<String, String> storeMap;
    private String shadow;
    private OrdrHdDTO selectedOrdr;
    private Long govern;
    private HrEmpInfo hrEmpInfo;
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
        storeList = new ArrayList<SelectItem>();
        storeMap = new HashMap<String, String>();
        rmnSelectedList = new ArrayList<OrdrHdDTO>();
        backupList = new ArrayList<CrmkOrdrAndRmnWithoutSrfDTO>();
        FacesContext fc = FacesContext.getCurrentInstance();
        Object[] arr=sessionBean.findBrnAndGovern(hrEmpInfo.getLocId());
        actualLocation=Long.parseLong(arr[0]+"");
        govern=Long.parseLong(arr[1]+"");
        List<Object[]> stores=new ArrayList<Object[]>();
        if(hrEmpInfo.getDeptId().equals(1L))
            stores = sessionBean.getStoreByGovern(govern,"'T','W'");
        else if(hrEmpInfo.getDeptId().equals(10L))
           stores = sessionBean.getStoreByGovern(govern,"'T'");
        for (Object[] branch : stores) {
            storeList.add(new SelectItem(branch[0], branch[1] + ""));
            storeMap.put(branch[0] + "", branch[1] + "");
        }
        empList = sessionBean.findOrdrEmployeeNames(actualLocation);
        drvList = sessionBean.findOrdrDriverNames(actualLocation);
        clntList = sessionBean.findOrdrClntNames(actualLocation);
        selectedOrdr=new OrdrHdDTO();
    }

    public String newSearch(){
        this.model=null;
        fromDate=null;
        toDate=null;
        trnsNo=null;
        actQty=null;
        crmkSehy=null;
        ordrNo=null;
        clntName=null;
        driverName=null;
        empName=null;
        phone=null;
        rmnFlag=0L;
        rmnNo=null;
        ordrType=null;
        shadow=null;
        return null;
    }

    public String search() {
        searchCriteria = new HashMap<String, Object>();
        searchCriteria.put("brnId", actualLocation);
        if (fromDate != null) {
            searchCriteria.put("fromDate", fromDate);
        }
        if (toDate != null) {
            searchCriteria.put("toDate", toDate);
        }
        if (trnsNo != null) {
            searchCriteria.put("trnsNo", trnsNo);
        }
        if (actQty != null) {
            searchCriteria.put("actQty", actQty);
        }
        if (crmkSehy != null) {
            searchCriteria.put("crmkSehy", crmkSehy);
        }
        if (ordrNo != null) {
            searchCriteria.put("no", ordrNo);
        }
        if (clntName != null && !clntName.isEmpty()) {
            searchCriteria.put("clntName", clntName);
        }
        if (driverName != null && !driverName.isEmpty()) {
            searchCriteria.put("name", driverName);
        }
        if (empName != null && !empName.isEmpty()) {
            searchCriteria.put("empName", empName);
        }

        if (rmnFlag != null && !rmnFlag.equals(0L)) {
            searchCriteria.put("rmnFlag", rmnFlag);
        }else{
            searchCriteria.put("rmnFlag", 0L);
        }
        if (phone != null) {
            searchCriteria.put("phone", phone);
        }
        if(rmnNo != null){
            searchCriteria.put("rmnNo",rmnNo);
        }
        if(ordrType !=null){
            searchCriteria.put("ordrType",ordrType);
        }
        if(shadow !=null){
            searchCriteria.put("shadow",shadow);
        }
        rowCount = sessionBean.countOrdrAndRemainWithoutSrfPerEmp(searchCriteria);
        this.model = new LazyDataModel<CrmkOrdrAndRmnWithoutSrfDTO>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<CrmkOrdrAndRmnWithoutSrfDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                filters = searchCriteria;
                List<CrmkOrdrAndRmnWithoutSrfDTO> result = ordrAndRemainMB.getFormattedResults(first, pageSize, filters,1,rowCount);
                backupList.clear();
                backupList.addAll(result);
                model.setRowCount(rowCount);
                if (!rmnSelectedList.isEmpty()) {
                    for (CrmkOrdrAndRmnWithoutSrfDTO crmkOrdrAndRmnWithoutSrfDTO : result) {
                        for (OrdrHdDTO ordrHdDTO : crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList()) {
                            if (rmnSelectedList.contains(ordrHdDTO)) {
                                ordrHdDTO.setRequestFlag(Boolean.TRUE);
                                ordrHdDTO.setTrgtBrnId(rmnSelectedList.get(rmnSelectedList.indexOf(ordrHdDTO)).getTrgtBrnId());
                                for (OrdrDtDTO otd : rmnSelectedList.get(rmnSelectedList.indexOf(ordrHdDTO)).getOrdrDtDTOList()) {
                                    if (otd.isSelectedItem()) {
                                        ordrHdDTO.getOrdrDtDTOList().get(ordrHdDTO.getOrdrDtDTOList().indexOf(otd)).setSelectedItem(true);
                                    } else {
                                        ordrHdDTO.getOrdrDtDTOList().get(ordrHdDTO.getOrdrDtDTOList().indexOf(otd)).setSelectedItem(false);
                                    }
                                }
                            }
                        }
                    }
                }

                return result;
            }
        };
        return null;
    }

    public void addRmnToRequest() {
        String ordrId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ordrId");
//        String requestFlag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("requestFlag");
        OrdrHdDTO ohd = new OrdrHdDTO();
        for (CrmkOrdrAndRmnWithoutSrfDTO crmkOrdrAndRmnWithoutSrfDTO : backupList) {
            for (OrdrHdDTO ordrHdDTO : crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList()) {
                if (ordrHdDTO.getOrdrId().equals(Long.parseLong(ordrId))) {
                    ohd = ordrHdDTO;
                    break;
                }
            }
        }
        if (!rmnSelectedList.contains(ohd) && ohd.getRequestFlag()) {
           ohd.setRequestFlag(Boolean.TRUE);
            if (ohd.getTrgtBrnId() != null && ohd.getTrgtBrnId() != 0) {
                rmnSelectedList.add(ohd);
            }
            
            for (OrdrDtDTO ordrDtDTO : ohd.getOrdrDtDTOList()) {
                ordrDtDTO.setSelectedItem(true);
            }
        } else if (!ohd.getRequestFlag()) {
            rmnSelectedList.remove(ohd);
            for (OrdrDtDTO ordrDtDTO : ohd.getOrdrDtDTOList()) {
                ordrDtDTO.setSelectedItem(false);
            }
        }
        System.out.println(rmnSelectedList);
    }

    public void changeStore() {
        String ordrId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ordrId");
        OrdrHdDTO ohd = new OrdrHdDTO();
        for (CrmkOrdrAndRmnWithoutSrfDTO crmkOrdrAndRmnWithoutSrfDTO : backupList) {
            for (OrdrHdDTO ordrHdDTO : crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList()) {
                if (ordrHdDTO.getOrdrId().equals(Long.parseLong(ordrId))) {
                    ohd = ordrHdDTO;
                    break;
                }
            }
        }
        if (rmnSelectedList.contains(ohd)) {
            rmnSelectedList.remove(ohd);
        }
        if (ohd.getRequestFlag()!=null && ohd.getRequestFlag().equals(Boolean.TRUE) && ohd.getTrgtBrnId()!=null && ohd.getTrgtBrnId()!=0)  {
            rmnSelectedList.add(ohd);
        }

//        if (rmnSelectedList.contains(ohd)) {
//            rmnSelectedList.remove(ohd);
//            rmnSelectedList.add(ohd);
//        }
    }

    public String createSrfRequest() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (rmnSelectedList.isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈Œ Ì«— »«ﬁÏ ÿ—›‰« √Ê √ﬂÀ—"));
            return null;
        }
        for (OrdrHdDTO ordrHdDTO : rmnSelectedList) {
            ordrHdDTO.setTrgtBrnName(storeMap.get(ordrHdDTO.getTrgtBrnId().toString()));
        }
        this.model = new LazyDataModel<CrmkOrdrAndRmnWithoutSrfDTO>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<CrmkOrdrAndRmnWithoutSrfDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<CrmkOrdrAndRmnWithoutSrfDTO> result = new ArrayList<CrmkOrdrAndRmnWithoutSrfDTO>();
                model.setRowCount(0);
                return result;
            }
        };
        return "rmn_srf_request";
    }

    public String getIpAddress() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String reqIp = httpServletRequest.getRemoteAddr();
        String ipSec = reqIp.substring(4).toString();
        int counter = ipSec.lastIndexOf(".");
        ipSec = ipSec.substring(1, counter).toString();
        return ipSec;
    }

    public String findQtyDetailsPerGovern() {
        String itemCode = selectedItem.getItemCode();
        List<Object[]> qtyDetails = sessionBean.findActualQtyDetailsPerGovern(itemCode.replaceAll("\"", ""), govern, selectedItem.getCrmkSehy());

        Set<String> stores = new LinkedHashSet<String>();
        Set<String> cTone = new LinkedHashSet<String>();
        String[][] result = null;
        for (Object[] row : qtyDetails) {
            stores.add((String) row[1]);
            cTone.add(row[3] + "-sep-" + row[4]);
        }

        result = new String[cTone.size() + 1][stores.size() + 2];
        result[0][0] = "C";
        result[0][1] = "Tone";
        int i = 2;
        for (String store : stores) {
            result[0][i] = store;
            i++;
        }
        i = 1;
        for (String ct : cTone) {
            result[i][0] = ct.split("-sep-")[0].equals("null") ? "" : ct.split("-sep-")[0];
            result[i][1] = ct.split("-sep-")[1].equals("null") ? "" : ct.split("-sep-")[1];
            i++;
        }

        for (Object[] row : qtyDetails) {
            for (int k = 1; k < cTone.size() + 1; k++) {
                for (int j = 2; j < stores.size() + 2; j++) {
                    if (row[1].equals(result[0][j]) && ((row[3] == null && result[k][0].isEmpty()) || (row[3] != null && row[3].equals(result[k][0]))) && ((row[4] == null && result[k][1].isEmpty()) || (row[4] != null && row[4].equals(result[k][1])))) {
                        result[k][j] = row[0].toString();
                    }
                }
            }
        }
        qtyDetailHd = result[0];
        qtyDetailResult = new ArrayList<String[]>();
        for (int n = 0; n < cTone.size(); n++) {
            qtyDetailResult.add(result[n + 1]);
        }
        columns = new ArrayList<ColumnModel>();
        i = 0;
        for (String columnKey : qtyDetailHd) {
            columns.add(new ColumnModel(columnKey, i + ""));
            i++;
        }
        return null;
    }

    public void showSelectedOrdrDT(){
        if(selectedOrdr!=null && selectedOrdr.getOrdrId()!=null && selectedOrdr.getRmnId()!=null){
//             RequestContext.getCurrentInstance().execute("PF('ordrDT').show()");
        }
    }

    static public class ColumnModel implements Serializable {

        private String header;
        private String property;

        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }
    }

    public void updateEmpName() {
        empName = empNameSelected;
    }

    public void updateClntName() {
        clntName = clntNameSelected;
    }

    public void updateDrvName() {
        driverName = driverNameSelected;
    }

    public OrdrsAndRmnWithoutSRF() {
    }

    public OrdrAndRemainMB getOrdrAndRemainMB() {
        return ordrAndRemainMB;
    }

    public void setOrdrAndRemainMB(OrdrAndRemainMB ordrAndRemainMB) {
        this.ordrAndRemainMB = ordrAndRemainMB;
    }

    public List<CrmkOrdrAndRmnWithoutSrfDTO> getList() {
        return list;
    }

    public void setList(List<CrmkOrdrAndRmnWithoutSrfDTO> list) {
        this.list = list;
    }

    public LazyDataModel<CrmkOrdrAndRmnWithoutSrfDTO> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<CrmkOrdrAndRmnWithoutSrfDTO> model) {
        this.model = model;
    }

    public Double getActQty() {
        return actQty;
    }

    public void setActQty(Double actQty) {
        this.actQty = actQty;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public String getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(String crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Long getOrdrNo() {
        return ordrNo;
    }

    public void setOrdrNo(Long ordrNo) {
        this.ordrNo = ordrNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getTrnsNo() {
        return trnsNo;
    }

    public void setTrnsNo(Long trnsNo) {
        this.trnsNo = trnsNo;
    }

    public List<String> getEmpList() {
        return empList;
    }

    public void setEmpList(List<String> empList) {
        this.empList = empList;
    }

    public LazyDataModel<String> getClntLazyModel() {
        return clntLazyModel;
    }

    public void setClntLazyModel(LazyDataModel<String> clntLazyModel) {
        this.clntLazyModel = clntLazyModel;
    }

    public List<String> getClntList() {
        return clntList;
    }

    public void setClntList(List<String> clntList) {
        this.clntList = clntList;
    }

    public List<String> getDrvList() {
        return drvList;
    }

    public void setDrvList(List<String> drvList) {
        this.drvList = drvList;
    }

    public String getClntNameSelected() {
        return clntNameSelected;
    }

    public void setClntNameSelected(String clntNameSelected) {

        this.clntNameSelected = clntNameSelected;
    }

    public String getDriverNameSelected() {
        return driverNameSelected;
    }

    public void setDriverNameSelected(String driverNameSelected) {

        this.driverNameSelected = driverNameSelected;
    }

    public String getEmpNameSelected() {
        return empNameSelected;
    }

    public void setEmpNameSelected(String empNameSelected) {

        this.empNameSelected = empNameSelected;
    }

    public OrdrDtDTO getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(OrdrDtDTO selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<String[]> getQtyDetailResult() {
        return qtyDetailResult;
    }

    public void setQtyDetailResult(List<String[]> qtyDetailResult) {
        this.qtyDetailResult = qtyDetailResult;
    }

    public boolean isRequestFlag() {
        return requestFlag;
    }

    public void setRequestFlag(boolean requestFlag) {
        this.requestFlag = requestFlag;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public List<OrdrHdDTO> getRmnSelectedList() {
        return rmnSelectedList;
    }

    public void setRmnSelectedList(List<OrdrHdDTO> rmnSelectedList) {
        this.rmnSelectedList = rmnSelectedList;
    }

    public List<SelectItem> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<SelectItem> storeList) {
        this.storeList = storeList;
    }

    public Long getOrdrType() {
        return ordrType;
    }

    public void setOrdrType(Long ordrType) {
        this.ordrType = ordrType;
    }

    public String getRmnNo() {
        return rmnNo;
    }

    public void setRmnNo(String rmnNo) {
        this.rmnNo = rmnNo;
    }

    public String getShadow() {
        return shadow;
    }

    public void setShadow(String shadow) {
        this.shadow = shadow;
    }

    public OrdrHdDTO getSelectedOrdr() {
        return selectedOrdr;
    }

    public void setSelectedOrdr(OrdrHdDTO selectedOrdr) {
        this.selectedOrdr = selectedOrdr;
    }

    public Long getRmnFlag() {
        return rmnFlag;
    }

    public void setRmnFlag(Long rmnFlag) {
        this.rmnFlag = rmnFlag;
    }


    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkOrdrAndRmnWithoutSrf;
import e.CrmkRmnPrintRequest;
import e.HrEmpInfo;
import e.HrLocation;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class RmnSrfPrint {

    @EJB
    private SessionBeanLocal sessionBean;
    @ManagedProperty("#{ordrAndRemainMB}")
    private OrdrAndRemainMB ordrAndRemainMB;
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
    private String showroomName;
    private String driverNameSelected;
    private String showroomSelected;
    private Long ordrNo;
    private String clntName;
    private String clntNameSelected;
    private String clntPhone;
    private String driverPhone;
    private String crmkSehy;
    private String rmnNo;
    private Long ordrType;
    private Map<String, Object> searchCriteria;
    private Long actualLocation;
    private List<String> empList;
    private List<String> clntList;
    private List<String> drvList;
    private List<String> showRoomList;
    private OrdrDtDTO selectedItem;
    private List<String[]> qtyDetailResult;
    private String[] qtyDetailHd;
    private List<ColumnModel> columns;
    private boolean requestFlag;
    private List<OrdrHdDTO> rmnSelectedList;
    private List<CrmkOrdrAndRmnWithoutSrfDTO> backupList;
    private List<SelectItem> storeList;
    private Long brnId;
    private List<Object[]> remainDt;
    private CrmkOrdrAndRmnWithoutSrf crmkOrdrAndRmnWithoutSrf;
    private String printFlag = "N";
    private Double totalQty = 0D;
    private String totalQtyAsString;
    private HrEmpInfo hrEmpInfo;
    private List<Object[]> reqCntPerBrn;
    private Long reqCount = 0L;
    private boolean brnSelected;
    private String srfCaution;
    private Date today;
    private java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
    private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
    private Long govern;

    @PostConstruct
    public void init() {
        storeList = new ArrayList<SelectItem>();
        rmnSelectedList = new ArrayList<OrdrHdDTO>();
        backupList = new ArrayList<CrmkOrdrAndRmnWithoutSrfDTO>();
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

        FacesContext fc = FacesContext.getCurrentInstance();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        today = c.getTime();
        Object[] arr = sessionBean.findBrnAndGovern(hrEmpInfo.getLocId());
        actualLocation = Long.parseLong(arr[0] + "");
        govern = Long.parseLong(arr[1] + "");
        empList = sessionBean.findEmpRequestedNameList(actualLocation);
        drvList = sessionBean.findTrgtDriverNameList(actualLocation);
        clntList = sessionBean.findTrgtClntNameList(actualLocation);
        showRoomList=sessionBean.findShowNameNameList(actualLocation);
    }


    public String newSearch() {
        this.model = null;
        brnId = null;
        crmkSehy = null;
        clntName = null;
        driverName = null;
        showroomName=null;
        empName = null;
        clntPhone = null;
        driverPhone = null;
        rmnNo = null;
        searchCriteria = null;
        rowCount = 0;
        ordrNo = null;
        empList = sessionBean.findEmpRequestedNameList(actualLocation);
        drvList = sessionBean.findTrgtDriverNameList(actualLocation);
        clntList = sessionBean.findTrgtClntNameList(actualLocation);
        showRoomList=sessionBean.findShowNameNameList(actualLocation);
        return null;
    }

    public String search() {
        System.out.println("search started");
        //refreshBrnReqCnt();

        searchCriteria = new HashMap<String, Object>();
        searchCriteria.put("trgtBrnId", actualLocation);
        boolean filterExist=false;
        if (brnId != null) {
            searchCriteria.put("brnId", brnId);
            filterExist=true;
        }
        if (crmkSehy != null && !crmkSehy.isEmpty()) {
            searchCriteria.put("crmkSehy", crmkSehy);
            filterExist=true;
        }
        if (clntName != null && !clntName.isEmpty()) {
            searchCriteria.put("clntName", clntName);
            filterExist=true;
        }
        if (driverName != null && !driverName.isEmpty()) {
            searchCriteria.put("driverName", driverName);
            filterExist=true;
        }
        if (empName != null && !empName.isEmpty()) {
            searchCriteria.put("empName", empName);
            filterExist=true;
        }
        if (clntPhone != null && !clntPhone.isEmpty()) {
            searchCriteria.put("clntPhone", clntPhone);
            filterExist=true;
        }
        if (driverPhone != null && !driverPhone.isEmpty()) {
            searchCriteria.put("driverPhone", driverPhone);
            filterExist=true;
        }
        if (showroomName != null && !showroomName.isEmpty()) {
            searchCriteria.put("showroomName", showroomName);
            filterExist=true;
        }
        if (rmnNo != null && !rmnNo.isEmpty()) {
            searchCriteria.put("rmnNo", rmnNo);
            filterExist=true;
        }

        if (ordrNo != null ) {
            searchCriteria.put("ordrNo", ordrNo);
            filterExist=true;
            System.out.println("000000000000");
        }
        if(!filterExist){
            FacesContext fc=FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» «·»ÕÀ »≈” Œœ«„ √Õœ «·›·« — ⁄·Ï «·√ﬁ·"));
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        searchCriteria.put("load_date", date);
        rowCount = sessionBean.findCountOfRequestsPerBrn(searchCriteria);
        this.model = new LazyDataModel<CrmkOrdrAndRmnWithoutSrfDTO>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<CrmkOrdrAndRmnWithoutSrfDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                filters = searchCriteria;
                List<CrmkOrdrAndRmnWithoutSrfDTO> result = ordrAndRemainMB.getFormattedResults(first, pageSize, filters, 2, rowCount);
                backupList.clear();
                backupList.addAll(result);
                model.setRowCount(rowCount);
                System.out.println("search result"+result.size());
                return result;
            }
        };

        empList = sessionBean.findEmpRequestedNameList(actualLocation);
        drvList = sessionBean.findTrgtDriverNameList(actualLocation);
        clntList = sessionBean.findTrgtClntNameList(actualLocation);
        showRoomList=sessionBean.findShowNameNameList(actualLocation);

        
        return null;
    }

    public String print() {
        printFlag = "Y";
        String reqId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("reqId");
        crmkOrdrAndRmnWithoutSrf = sessionBean.findCrmkOrdrAndRmnWithoutSrf(Long.parseLong(reqId)).get(0);
        if (crmkOrdrAndRmnWithoutSrf != null && crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest() != null && !crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().getTargetBrnId().equals(actualLocation)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ≈⁄«œ…  ÊÃÌÂ ÿ»«⁄… «·≈–‰ ·„Êﬁ⁄ √Œ—"));
            return null;
        }
        remainDt = sessionBean.findRemainDt(crmkOrdrAndRmnWithoutSrf.getRemainId(), crmkOrdrAndRmnWithoutSrf.getCrmkSehy());
        totalQty = 0D;
        for (Object[] o : remainDt) {
            if (o[8] != null) {
                totalQty += Double.valueOf(o[8].toString());
            }
        }
        totalQty = (double) Math.round(totalQty * 100) / 100;
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setPrintDate(new Date());
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setPrinted(new Character('Y'));
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setEmpPrintedId(hrEmpInfo.getEmpNo());
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setPrintCanceledBy(null);
        sessionBean.mergeCrmkRmnPrintRequest(crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest());
        totalQtyAsString = sessionBean.findQtyAsString(totalQty);
        srfCaution = "Â–« «·„” ‰œ ’«·Õ ··’—› „‰ „Œ“‰ " + crmkOrdrAndRmnWithoutSrf.getTrgtBrnName() + " Œ·«· «·ÌÊ„ " + format.format(new Date());
        //refreshBrnReqCnt();
        return null;
    }

    public String cancelPrint() {
        String reqId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("reqId");
        crmkOrdrAndRmnWithoutSrf = sessionBean.findCrmkOrdrAndRmnWithoutSrf(Long.parseLong(reqId)).get(0);
        if (crmkOrdrAndRmnWithoutSrf == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ’—› Â–« «·„” ‰œ"));
            return null;
        }
        Long cnt = sessionBean.chkRmnDispatch(crmkOrdrAndRmnWithoutSrf.getCrmkSehy(), crmkOrdrAndRmnWithoutSrf.getRemainId());
        if (cnt > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ’—› Â–« «·„” ‰œ"));
            return null;
        }
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setPrintDate(null);
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setPrinted(null);
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setEmpPrintedId(null);
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setTargetBrnId(null);
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setTrgtBrnName(null);
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setDriverName(null);
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setClntName(null);
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setClntPhone(null);
        crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest().setPrintCanceledBy(hrEmpInfo.getEmpNo());
        sessionBean.mergeCrmkRmnPrintRequest(crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest());
        model = null;
        rowCount = 0;
        return search();
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

        Set<String> stores = new HashSet<String>();
        Set<String> cTone = new HashSet<String>();
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

    public void updateShowroomName() {
        showroomName = showroomSelected;
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

    public String getClntPhone() {
        return clntPhone;
    }

    public void setClntPhone(String clntPhone) {
        this.clntPhone = clntPhone;
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

    /** Creates a new instance of RmnSrfPrint */
    public RmnSrfPrint() {
    }

    public List<Object[]> getRemainDt() {
        return remainDt;
    }

    public void setRemainDt(List<Object[]> remainDt) {
        this.remainDt = remainDt;
    }

    public CrmkOrdrAndRmnWithoutSrf getCrmkOrdrAndRmnWithoutSrf() {
        return crmkOrdrAndRmnWithoutSrf;
    }

    public void setCrmkOrdrAndRmnWithoutSrf(CrmkOrdrAndRmnWithoutSrf crmkOrdrAndRmnWithoutSrf) {
        this.crmkOrdrAndRmnWithoutSrf = crmkOrdrAndRmnWithoutSrf;
    }

    public String getPrintFlag() {
        if (printFlag.equals("Y")) {
            printFlag = "N";
            return "Y";
        } else {
            return "N";
        }
    }

    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

    public Double getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Double totalQty) {
        this.totalQty = totalQty;
    }

    public String getTotalQtyAsString() {
        return totalQtyAsString;
    }

    public void setTotalQtyAsString(String totalQtyAsString) {
        this.totalQtyAsString = totalQtyAsString;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public List<Object[]> getReqCntPerBrn() {
        return reqCntPerBrn;
    }

    public void setReqCntPerBrn(List<Object[]> reqCntPerBrn) {
        this.reqCntPerBrn = reqCntPerBrn;
    }

    public Long getReqCount() {
        return reqCount;
    }

    public void setReqCount(Long reqCount) {
        this.reqCount = reqCount;
    }

    public boolean isBrnSelected() {
        return brnSelected;
    }

    public void setBrnSelected(boolean brnSelected) {
        this.brnSelected = brnSelected;
    }

    public String getSrfCaution() {
        return srfCaution;
    }

    public void setSrfCaution(String srfCaution) {
        this.srfCaution = srfCaution;
    }

    public List<String> getShowRoomList() {
        return showRoomList;
    }

    public void setShowRoomList(List<String> showRoomList) {
        this.showRoomList = showRoomList;
    }

    public String getShowroomSelected() {
        return showroomSelected;
    }

    public void setShowroomSelected(String showroomSelected) {
        this.showroomSelected = showroomSelected;
    }

    public String getShowroomName() {
        return showroomName;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }
    
}

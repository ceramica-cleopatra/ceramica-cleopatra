/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkRmnPrintRequest;
import e.HrEmpInfo;
import e.HrLocation;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class RmnSrfRequest implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    @ManagedProperty("#{ordrsAndRmnWithoutSRF}")
    private OrdrsAndRmnWithoutSRF ordrsAndRmnWithoutSRF;
    private List<CrmkRmnPrintRequest> rmnListNeedToPersist;
    private List<CrmkRmnPrintRequest> rmnListNeedToMerge;
    private List<RmnListRequestedToPrintDTO> rmnListRequestedToPrintDTOs;
    private Map<Long, Set<String>> previousDriversMap;
    private List<String> previousDrivers;
    private Object[] selectedDriver;
    private List<Object[]> brnDrivers;
    private RmnListRequestedToPrintDTO currentRow = new RmnListRequestedToPrintDTO();
    private HrEmpInfo hrEmpInfo;
    private Long actualLocation;
    private Long govern;

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


        Object[] arr = sessionBean.findBrnAndGovern(hrEmpInfo.getLocId());
        actualLocation = Long.parseLong(arr[0] + "");
        govern = Long.parseLong(arr[1] + "");
        brnDrivers = sessionBean.findDriversForBrn(actualLocation);
        previousDrivers = new ArrayList<String>();
        previousDriversMap = new HashMap<Long, Set<String>>();
        rmnListRequestedToPrintDTOs = new ArrayList<RmnListRequestedToPrintDTO>();
        if (!ordrsAndRmnWithoutSRF.getRmnSelectedList().isEmpty()) {
            for (OrdrHdDTO ordrHdDTO : ordrsAndRmnWithoutSRF.getRmnSelectedList()) {
                previousDriversMap.put(ordrHdDTO.getRmnId(), ordrHdDTO.getDrivers());
                RmnListRequestedToPrintDTO rmnListRequestedToPrintDTO = new RmnListRequestedToPrintDTO();
                rmnListRequestedToPrintDTO.setRmnId(ordrHdDTO.getRmnId());
                rmnListRequestedToPrintDTO.setRmnNo(Long.parseLong(ordrHdDTO.getRmnNo()));
                rmnListRequestedToPrintDTO.setRmnHandNo(ordrHdDTO.getRmnHandNo() == null ? null : Long.parseLong(ordrHdDTO.getRmnHandNo()));
                rmnListRequestedToPrintDTO.setAddress(ordrHdDTO.getAddress());
                rmnListRequestedToPrintDTO.setClntArea(ordrHdDTO.getArea());
                rmnListRequestedToPrintDTO.setClntCity(ordrHdDTO.getCity());
                rmnListRequestedToPrintDTO.setClntName(ordrHdDTO.getClntName());
                rmnListRequestedToPrintDTO.setClntPhone(ordrHdDTO.getClntPhone());
                rmnListRequestedToPrintDTO.setDrivers(ordrHdDTO.getDrivers());
                rmnListRequestedToPrintDTO.setPrdId(ordrHdDTO.getRmnPrdId());
                rmnListRequestedToPrintDTO.setStoreId(ordrHdDTO.getTrgtBrnId());
                rmnListRequestedToPrintDTO.setStoreName(ordrHdDTO.getTrgtBrnName());
                rmnListRequestedToPrintDTO.setCrmkSehy(ordrHdDTO.getCRMK_SEHY().charAt(0));
                rmnListRequestedToPrintDTO.setReqId(ordrHdDTO.getReqId());
                Double totQty = 0D;
                for (OrdrDtDTO ordrDtDTO : ordrHdDTO.getOrdrDtDTOList()) {
                    if (ordrDtDTO.isSelectedItem()) {
                        totQty += ordrDtDTO.getRmnQty();
                    }
                }
                rmnListRequestedToPrintDTO.setTotalQty(totQty);
                rmnListRequestedToPrintDTO.setTrnsDate(ordrHdDTO.getRmnTrnsDate());
                rmnListRequestedToPrintDTOs.add(rmnListRequestedToPrintDTO);
            }
            Collections.sort(rmnListRequestedToPrintDTOs, new CrmkRmnPrintRequestComparator());
            currentRow = rmnListRequestedToPrintDTOs.get(0);
        }
    }

    public String getIpAddress() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String reqIp = httpServletRequest.getHeader("x-forwarded-for");
        if (reqIp != null) {
            String[] ipArray = reqIp.split(",");
            for (int i = 0; i < ipArray.length; i++) {
                if (ipArray[i] != null && !ipArray[i].isEmpty() && !ipArray[i].trim().contains("20.1.1.46")) {
                    reqIp = ipArray[i];
                    break;
                }
            }
        }
        if (reqIp == null || "".equals(reqIp)) {
            reqIp = httpServletRequest.getRemoteAddr();
        }
        String ipSec = reqIp.substring(5, reqIp.length()).toString();
        int counter = ipSec.indexOf(".");
        ipSec = ipSec.substring(0, counter).toString();
        return ipSec;
    }

    public void saveRequests() {
        rmnListNeedToPersist = new ArrayList<CrmkRmnPrintRequest>();
        rmnListNeedToMerge = new ArrayList<CrmkRmnPrintRequest>();
        FacesContext fc = FacesContext.getCurrentInstance();
        for (RmnListRequestedToPrintDTO rmnListRequestedToPrintDTO : rmnListRequestedToPrintDTOs) {
            System.out.print("driverName>>>>>>" + rmnListRequestedToPrintDTO.getDriverName());
            if (rmnListRequestedToPrintDTO.getDriverName() == null || rmnListRequestedToPrintDTO.getDriverName().isEmpty()) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ ≈”„ «·⁄„Ì· √Ê ≈”„ «·”«∆ﬁ"));
                return;
            } else if (rmnListRequestedToPrintDTO.getDriverPhone() == null || rmnListRequestedToPrintDTO.getDriverPhone().isEmpty()) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«·  ·Ì›Ê‰ «·⁄„Ì· √Ê «·”«∆ﬁ"));
                return;
            } else if (rmnListRequestedToPrintDTO.getLoadDate() == null) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«·  «—ÌŒ «· Õ„Ì·"));
                return;
            }
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date date = cal.getTime();
            Calendar datePlus7Days = Calendar.getInstance();
            datePlus7Days.add(Calendar.DAY_OF_MONTH, 7);
            System.out.println("datePlus7Days"+datePlus7Days.toString());

            if (rmnListRequestedToPrintDTO.getLoadDate().before(date) || rmnListRequestedToPrintDTO.getLoadDate().after(datePlus7Days.getTime())) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«·  «—ÌŒ «· Õ„Ì· »‘ﬂ· ’ÕÌÕ"));
                return;
            }
            if (rmnListRequestedToPrintDTO.getReqId() == null) {
                CrmkRmnPrintRequest crmkRmnPrintRequest = new CrmkRmnPrintRequest();
                crmkRmnPrintRequest.setClntName(rmnListRequestedToPrintDTO.getClntName());
                crmkRmnPrintRequest.setClntPhone(rmnListRequestedToPrintDTO.getClntPhone());
                crmkRmnPrintRequest.setCrmkSehy(rmnListRequestedToPrintDTO.getCrmkSehy());
                crmkRmnPrintRequest.setDriverName(rmnListRequestedToPrintDTO.getDriverName());
                crmkRmnPrintRequest.setDriverPhone(rmnListRequestedToPrintDTO.getDriverPhone());
                crmkRmnPrintRequest.setEmpRequestedId(hrEmpInfo.getEmpNo());
                crmkRmnPrintRequest.setPrdId(rmnListRequestedToPrintDTO.getPrdId());
                crmkRmnPrintRequest.setRmnHandNo(rmnListRequestedToPrintDTO.getRmnHandNo());
                crmkRmnPrintRequest.setRmnId(rmnListRequestedToPrintDTO.getRmnId());
                crmkRmnPrintRequest.setRmnNo(rmnListRequestedToPrintDTO.getRmnNo());
                crmkRmnPrintRequest.setRmnTrnsDate(rmnListRequestedToPrintDTO.getTrnsDate());
                crmkRmnPrintRequest.setTargetBrnId(rmnListRequestedToPrintDTO.getStoreId());
                crmkRmnPrintRequest.setTrnsDate(new Date());
                crmkRmnPrintRequest.setDriverId(rmnListRequestedToPrintDTO.getDriverId());
                crmkRmnPrintRequest.setLoadDate(rmnListRequestedToPrintDTO.getLoadDate());
                crmkRmnPrintRequest.setBrnRequestedId(actualLocation);
                crmkRmnPrintRequest.setBrnRequestedName("");
                crmkRmnPrintRequest.setTrgtBrnName(rmnListRequestedToPrintDTO.getStoreName());
                crmkRmnPrintRequest.setEmpRequestedName(hrEmpInfo.getEmpName());
                rmnListNeedToPersist.add(crmkRmnPrintRequest);
            } else {
                CrmkRmnPrintRequest crmkRmnPrintRequest = sessionBean.findCrmkRmnPrintRequestById(rmnListRequestedToPrintDTO.getReqId().getId());
                if (rmnListRequestedToPrintDTO.getClntName() != null) {
                    crmkRmnPrintRequest.setClntName(rmnListRequestedToPrintDTO.getClntName());
                }
                if (rmnListRequestedToPrintDTO.getClntPhone() != null) {
                    crmkRmnPrintRequest.setClntPhone(rmnListRequestedToPrintDTO.getClntPhone());
                }
                if (rmnListRequestedToPrintDTO.getDriverName() != null) {
                    crmkRmnPrintRequest.setDriverName(rmnListRequestedToPrintDTO.getDriverName());
                }
                if (rmnListRequestedToPrintDTO.getDriverPhone() != null) {
                    crmkRmnPrintRequest.setDriverPhone(rmnListRequestedToPrintDTO.getDriverPhone());
                }
                crmkRmnPrintRequest.setLoadDate(rmnListRequestedToPrintDTO.getLoadDate());
                crmkRmnPrintRequest.setEmpRequestedId(hrEmpInfo.getEmpNo());
                crmkRmnPrintRequest.setEmpRequestedName(hrEmpInfo.getEmpName());
                crmkRmnPrintRequest.setTrgtBrnName(rmnListRequestedToPrintDTO.getStoreName());
                crmkRmnPrintRequest.setTargetBrnId(rmnListRequestedToPrintDTO.getStoreId());
                crmkRmnPrintRequest.setTrnsDate(new Date());
                crmkRmnPrintRequest.setEmpPrintedId(null);
                crmkRmnPrintRequest.setPrintCanceledBy(null);
                crmkRmnPrintRequest.setPrintDate(null);
                crmkRmnPrintRequest.setPrinted(null);
                if (rmnListRequestedToPrintDTO.getDriverId() != null) {
                    crmkRmnPrintRequest.setDriverId(rmnListRequestedToPrintDTO.getDriverId());
                }
                rmnListNeedToMerge.add(crmkRmnPrintRequest);
            }
        }
        if (!rmnListNeedToPersist.isEmpty()) {
            sessionBean.persistCrmkRmnPrintRequest(rmnListNeedToPersist);
        }
        if (!rmnListNeedToMerge.isEmpty()) {
            sessionBean.mergeCrmkRmnPrintRequest(rmnListNeedToMerge);
        }
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
        rmnListNeedToPersist = new ArrayList<CrmkRmnPrintRequest>();
        rmnListNeedToMerge = new ArrayList<CrmkRmnPrintRequest>();
        rmnListRequestedToPrintDTOs = new ArrayList<RmnListRequestedToPrintDTO>();
        new Thread(new Runnable() {

            public void run() {
                sessionBean.refreshCrmkRmnWithoutSrfMv();
            }
        }).start();
    }

    public String back() {
        ordrsAndRmnWithoutSRF.setRmnSelectedList(new ArrayList<OrdrHdDTO>());
        return "crmk_ordr_and_rmn_without_srf";
    }

    public void updateDriver() {
        currentRow.setDriverName(selectedDriver[1].toString());
        currentRow.setDriverId(Long.parseLong(selectedDriver[0].toString()));
        currentRow.setDriverPhone(selectedDriver[2].toString());
    }

    public List<String> getPreviousDrivers() {
        return previousDrivers;
    }

    public void setPreviousDrivers(List<String> previousDrivers) {
        this.previousDrivers = previousDrivers;
    }

    /** Creates a new instance of RmnSrfRequest */
    public RmnSrfRequest() {
    }

    public OrdrsAndRmnWithoutSRF getOrdrsAndRmnWithoutSRF() {
        return ordrsAndRmnWithoutSRF;
    }

    public void setOrdrsAndRmnWithoutSRF(OrdrsAndRmnWithoutSRF ordrsAndRmnWithoutSRF) {
        this.ordrsAndRmnWithoutSRF = ordrsAndRmnWithoutSRF;
    }

    public List<CrmkRmnPrintRequest> getRmnListNeedToPersist() {
        return rmnListNeedToPersist;
    }

    public void setRmnListNeedToPersist(List<CrmkRmnPrintRequest> rmnListNeedToPersist) {
        this.rmnListNeedToPersist = rmnListNeedToPersist;
    }

    public List<RmnListRequestedToPrintDTO> getRmnListRequestedToPrintDTOs() {
        return rmnListRequestedToPrintDTOs;
    }

    public void setRmnListRequestedToPrintDTOs(List<RmnListRequestedToPrintDTO> rmnListRequestedToPrintDTOs) {
        this.rmnListRequestedToPrintDTOs = rmnListRequestedToPrintDTOs;
    }

    public List<Object[]> getBrnDrivers() {
        return brnDrivers;
    }

    public void setBrnDrivers(List<Object[]> brnDrivers) {
        this.brnDrivers = brnDrivers;
    }

    public Object[] getSelectedDriver() {
        return selectedDriver;
    }

    public void setSelectedDriver(Object[] selectedDriver) {
        this.selectedDriver = selectedDriver;
    }

    public RmnListRequestedToPrintDTO getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(RmnListRequestedToPrintDTO currentRow) {
        this.currentRow = currentRow;
    }
}

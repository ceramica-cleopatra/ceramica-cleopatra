
import e.CrmkRmnPrintRequest;
import java.util.Date;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class RmnListRequestedToPrintDTO {

    private Long rmnId;
    private Long rmnNo;
    private Long rmnHandNo;
    private Long prdId;
    private Date trnsDate;
    private String clntName;
    private String clntPhone;
    private String clntCity;
    private String clntArea;
    private String address;
    private String driverName;
    private Long driverId;
    private String driverPhone;
    private Double totalQty;
    private Set<String> drivers;
    private String storeName;
    private Long storeId;
    private Character crmkSehy;
    private CrmkRmnPrintRequest reqId;
    private Date loadDate;
    public RmnListRequestedToPrintDTO() {
    }

    public Long getPrdId() {
        return prdId;
    }

    public void setPrdId(Long prdId) {
        this.prdId = prdId;
    }

    public Long getRmnId() {
        return rmnId;
    }

    public void setRmnId(Long rmnId) {
        this.rmnId = rmnId;
    }

    public Long getRmnNo() {
        return rmnNo;
    }

    public void setRmnNo(Long rmnNo) {
        this.rmnNo = rmnNo;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClntArea() {
        return clntArea;
    }

    public void setClntArea(String clntArea) {
        this.clntArea = clntArea;
    }

    public String getClntCity() {
        return clntCity;
    }

    public void setClntCity(String clntCity) {
        this.clntCity = clntCity;
    }

    public String getClntPhone() {
        return clntPhone;
    }

    public void setClntPhone(String clntPhone) {
        this.clntPhone = clntPhone;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Set<String> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<String> drivers) {
        this.drivers = drivers;
    }

    public Long getRmnHandNo() {
        return rmnHandNo;
    }

    public void setRmnHandNo(Long rmnHandNo) {
        this.rmnHandNo = rmnHandNo;
    }

    public Double getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Double totalQty) {
        this.totalQty = totalQty;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Character getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(Character crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public CrmkRmnPrintRequest getReqId() {
        return reqId;
    }

    public void setReqId(CrmkRmnPrintRequest reqId) {
        this.reqId = reqId;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    
    
 

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RmnListRequestedToPrintDTO other = (RmnListRequestedToPrintDTO) obj;
        if (this.rmnId != other.rmnId && (this.rmnId == null || !this.rmnId.equals(other.rmnId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.rmnId != null ? this.rmnId.hashCode() : 0);
        return hash;
    }


    

}

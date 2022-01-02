
import e.CrmkRmnPrintRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class OrdrHdDTO {
private Long ordrId;
private Long ordrNo;
private String CRMK_SEHY;
private String HAND_NO;
private Date trnsDate;
private String showName;
private String grpName;
private String empName;
private Long prdId;
private Long rmnPrdId;
private Date rmnTrnsDate;
private Long handNo;
private String storeName;
private Long rmnId;
private String rmnNo;
private String rmnHandNo;
private List<OrdrDtDTO> ordrDtDTOList;
private Boolean requestFlag;
private Long trgtBrnId;
private String trgtBrnName;
private Long trnsNo;
private String address;
private String city;
private String area;
private Set<String> drivers;
private String clntName;
private String clntPhone;
private CrmkRmnPrintRequest reqId;
private String trgtClntName;
private String trgtDriverName;
private String trgtClntPhone;
private String trgtDriverPhone;
private String empRequestedName;
private Long empRequestedId;
private String brnRequestedName;
private Long trgtDriverId;

    public OrdrHdDTO() {
    }

    public String getCRMK_SEHY() {
        return CRMK_SEHY;
    }

    public void setCRMK_SEHY(String CRMK_SEHY) {
        this.CRMK_SEHY = CRMK_SEHY;
    }

    public String getHAND_NO() {
        return HAND_NO;
    }

    public void setHAND_NO(String HAND_NO) {
        this.HAND_NO = HAND_NO;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public List<OrdrDtDTO> getOrdrDtDTOList() {
        return ordrDtDTOList;
    }

    public void setOrdrDtDTOList(List<OrdrDtDTO> ordrDtDTOList) {
        this.ordrDtDTOList = ordrDtDTOList;
    }

    public Long getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(Long ordrId) {
        this.ordrId = ordrId;
    }

    public Long getOrdrNo() {
        return ordrNo;
    }

    public void setOrdrNo(Long ordrNo) {
        this.ordrNo = ordrNo;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }


      public Long getHandNo() {
        return handNo;
    }

    public void setHandNo(Long handNo) {
        this.handNo = handNo;
    }


    

    public Long getRmnId() {
        return rmnId;
    }

    public void setRmnId(Long rmnId) {
        this.rmnId = rmnId;
    }

    public Long getPrdId() {
        return prdId;
    }

    public void setPrdId(Long prdId) {
        this.prdId = prdId;
    }

    public Long getRmnPrdId() {
        return rmnPrdId;
    }

    public void setRmnPrdId(Long rmnPrdId) {
        this.rmnPrdId = rmnPrdId;
    }

   

    public Date getRmnTrnsDate() {
        return rmnTrnsDate;
    }

    public void setRmnTrnsDate(Date rmnTrnsDate) {
        this.rmnTrnsDate = rmnTrnsDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getRmnNo() {
        return rmnNo;
    }

    public void setRmnNo(String rmnNo) {
        this.rmnNo = rmnNo;
    }

    public Boolean getRequestFlag() {
        return requestFlag;
    }

    public void setRequestFlag(Boolean requestFlag) {
        this.requestFlag = requestFlag;
    }

    public Long getTrgtBrnId() {
        return trgtBrnId;
    }

    public void setTrgtBrnId(Long trgtBrnId) {
        this.trgtBrnId = trgtBrnId;
    }

    public String getTrgtBrnName() {
        return trgtBrnName;
    }

    public void setTrgtBrnName(String trgtBrnName) {
        this.trgtBrnName = trgtBrnName;
    }

    

    public String getRmnHandNo() {
        return rmnHandNo;
    }

    public void setRmnHandNo(String rmnHandNo) {
        this.rmnHandNo = rmnHandNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<String> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<String> drivers) {
        this.drivers = drivers;
    }

    public Long getTrnsNo() {
        return trnsNo;
    }

    public void setTrnsNo(Long trnsNo) {
        this.trnsNo = trnsNo;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public String getClntPhone() {
        return clntPhone;
    }

    public void setClntPhone(String clntPhone) {
        this.clntPhone = clntPhone;
    }

    public CrmkRmnPrintRequest getReqId() {
        return reqId;
    }

    public void setReqId(CrmkRmnPrintRequest reqId) {
        this.reqId = reqId;
    }

   


    
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdrHdDTO other = (OrdrHdDTO) obj;
        if (this.ordrId != other.ordrId && (this.ordrId == null || !this.ordrId.equals(other.ordrId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.ordrId != null ? this.ordrId.hashCode() : 0);
        return hash;
    }

    public String getBrnRequestedName() {
        return brnRequestedName;
    }

    public void setBrnRequestedName(String brnRequestedName) {
        this.brnRequestedName = brnRequestedName;
    }

    public Long getEmpRequestedId() {
        return empRequestedId;
    }

    public void setEmpRequestedId(Long empRequestedId) {
        this.empRequestedId = empRequestedId;
    }

    public String getEmpRequestedName() {
        return empRequestedName;
    }

    public void setEmpRequestedName(String empRequestedName) {
        this.empRequestedName = empRequestedName;
    }

    public String getTrgtClntName() {
        return trgtClntName;
    }

    public void setTrgtClntName(String trgtClntName) {
        this.trgtClntName = trgtClntName;
    }

    public Long getTrgtDriverId() {
        return trgtDriverId;
    }

    public void setTrgtDriverId(Long trgtDriverId) {
        this.trgtDriverId = trgtDriverId;
    }

    public String getTrgtDriverName() {
        return trgtDriverName;
    }

    public void setTrgtDriverName(String trgtDriverName) {
        this.trgtDriverName = trgtDriverName;
    }

    public String getTrgtClntPhone() {
        return trgtClntPhone;
    }

    public void setTrgtClntPhone(String trgtClntPhone) {
        this.trgtClntPhone = trgtClntPhone;
    }

    public String getTrgtDriverPhone() {
        return trgtDriverPhone;
    }

    public void setTrgtDriverPhone(String trgtDriverPhone) {
        this.trgtDriverPhone = trgtDriverPhone;
    }




}

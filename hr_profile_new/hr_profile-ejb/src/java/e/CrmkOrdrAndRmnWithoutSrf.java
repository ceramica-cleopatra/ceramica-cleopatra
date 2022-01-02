/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "CRMK_RMN_WITHOUT_SRF_MV", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findAll", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c where (c.trnsDate>=:fromDate or :fromDate is null)"
    + "and (c.trnsDate<=:toDate or :toDate is null) and (c.trnsNo=:trnsNo or :trnsNo is null) and (c.actQty>:actQty or :actQty is null) and (c.empName like :empName or :empName is null) "
    + "and (c.name like :name or :name is null) and (c.no=:no or :no is null) and (c.clntName like :clntName or :clntName is null) and (c.showName like :showroomName or :showroomName is null) and (c.phone=:phone or :phone is null) "
    + "and (c.crmkSehy=:crmkSehy or :crmkSehy is null) and (c.rmnHandNo=:rmnNo or :rmnNo is null) and ((c.remainId is not null and :ordrType=1) or (c.remainId is null and :ordrType=2) or :ordrType=3) "
    + " and (((c.actQty<c.remain or c.actQty is null) and :shadow=1) or (c.actQty>=c.remain and :shadow=2) or :shadow=3)  and ((c.trgtBrnId is null and :rmnFlag=1) or (c.trgtBrnId is not null and :rmnFlag=2) or :rmnFlag=0) and c.brnId=:brnId order by c.clntName"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findCount", query = "SELECT count(c) FROM CrmkOrdrAndRmnWithoutSrf c where (c.trnsDate>=:fromDate or :fromDate is null) "
    + "and (c.trnsDate<=:toDate or :toDate is null) and (c.trnsNo=:trnsNo or :trnsNo is null) and (c.actQty>:actQty or :actQty is null) and (c.empName like :empName or :empName is null) "
    + "and (c.name like :name or :name is null) and (c.no=:no or :no is null) and (c.clntName like :clntName or :clntName is null) and (c.showName like :showroomName or :showroomName is null) and (c.phone=:phone or :phone is null) "
    + "and (c.crmkSehy=:crmkSehy or :crmkSehy is null) and (c.rmnHandNo=:rmnNo or :rmnNo is null) and ((c.remainId is not null and :ordrType=1) or (c.remainId is null and :ordrType=2) or :ordrType=3) "
    + " and (((c.actQty<c.remain or c.actQty is null) and :shadow=1) or (c.actQty>=c.remain and :shadow=2) or :shadow=3) and ((c.trgtBrnId is null and :rmnFlag=1) or (c.trgtBrnId is not null and :rmnFlag=2) or :rmnFlag=0) and c.brnId=:brnId"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findAllClntName", query = "SELECT distinct c.clntName FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.brnId = :brnId and c.clntName is not null"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findAllEmpName", query = "SELECT distinct c.empName FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.brnId = :brnId and c.empName is not null"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findAllDrvName", query = "SELECT distinct c.name FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.brnId = :brnId and c.name is not null"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findAllRequestsPerBrn", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c JOIN c.crmkRmnPrintRequest r WHERE r.targetBrnId = :trgtBrnId and "
    +" (c.brnRequestedId=:brnId or :brnId is null) and (c.empRequestedName like :empName or :empName is null) "
    + " and (c.trgtDriverName like :driverName or :driverName is null) and (c.showName like :showroomName or :showroomName is null) and (c.trgtClntName like :clntName or :clntName is null) and (c.trgtClntPhone=:clntPhone or :clntPhone is null) "
    + " and (c.crmkSehy=:crmkSehy or :crmkSehy is null) and (c.rmnHandNo=:rmnNo or :rmnNo is null) and (c.no=:ordrNo or :ordrNo is null) "
    + " and (c.trgtDriverPhone=:driverPhone or :driverPhone is null) and (c.loadDate=:load_date or c.crmkRmnPrintRequest.printed='Y') order by c.clntName"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findCntOfRequestsPerBrn", query = "SELECT count(c) FROM CrmkOrdrAndRmnWithoutSrf c JOIN c.crmkRmnPrintRequest r WHERE r.targetBrnId = :trgtBrnId and "
    +" (c.brnRequestedId=:brnId or :brnId is null) and (c.empRequestedName like :empName or :empName is null) "
    + " and (c.trgtDriverName like :driverName or :driverName is null) and (c.showName like :showroomName or :showroomName is null) and (c.trgtClntName like :clntName or :clntName is null) and (c.trgtClntPhone=:clntPhone or :clntPhone is null) "
    + " and (c.crmkSehy=:crmkSehy or :crmkSehy is null) and (c.rmnHandNo=:rmnNo or :rmnNo is null)  and (c.no=:ordrNo or :ordrNo is null)"
    + " and (c.trgtDriverPhone=:driverPhone or :driverPhone is null)  and (c.loadDate=:load_date or c.crmkRmnPrintRequest.printed='Y')"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByNo", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.no = :no"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findAllTrgtClntName", query = "SELECT distinct c.trgtClntName FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.trgtBrnId = :brnId and c.trgtClntName is not null"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findAllShowNameName", query = "SELECT distinct c.showName FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.trgtBrnId = :brnId and c.showName is not null"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findAllEmpRequestedName", query = "SELECT distinct c.empRequestedName FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.trgtBrnId = :brnId and c.empRequestedName is not null"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findAllDriverRequestedName", query = "SELECT distinct c.trgtDriverName FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.trgtBrnId = :brnId and c.trgtDriverName is not null"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByReqId", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.crmkRmnPrintRequest.id = :reqId"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByCrmkSehy", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.crmkSehy = :crmkSehy"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByHandNo", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.handNo = :handNo"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByTrnsDate", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByShowName", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.showName = :showName"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByClntName", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.clntName = :clntName"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByPhone", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.phone = :phone"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByGrpName", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.grpName = :grpName"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByNoCTone", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.noCTone = :noCTone"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByFactoryNo", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.factoryNo = :factoryNo"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByFrz", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.frz = :frz"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByQty", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.qty = :qty"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByOutQty", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.outQty = :outQty"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByCanQty", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.canQty = :canQty"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByRemain", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.remain = :remain"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByRmNo", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.rmnNo = :rmnNo"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByStr", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.str = :str"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByTrnsNo", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.trnsNo = :trnsNo"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByName", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByEmpName", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.empName = :empName"),
    @NamedQuery(name = "CrmkOrdrAndRmnWithoutSrf.findByActQty", query = "SELECT c FROM CrmkOrdrAndRmnWithoutSrf c WHERE c.actQty = :actQty")})
public class CrmkOrdrAndRmnWithoutSrf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "ORDR_ID")
    private Long ordrId;
    @Column(name = "BRN_ID")
    private Long brnId;
    @Column(name = "NO")
    private Long no;
    @Column(name = "CRMK_SEHY")
    private String crmkSehy;
    @Column(name = "HAND_NO")
    private String handNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "SHOW_NAME")
    private String showName;
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "GRP_NAME")
    private String grpName;
    @Column(name = "NO_C_TONE")
    private String noCTone;
    @Column(name = "FACTORY_NO")
    private String factoryNo;
    @Column(name = "FRZ")
    private Long frz;
    @Column(name = "QTY")
    private Double qty;
    @Column(name = "OUT_QTY")
    private Double outQty;
    @Column(name = "CAN_QTY")
    private Double canQty;
    @Column(name = "REMAIN")
    private Double remain;
    @Column(name = "RMN_NO")
    private String rmnNo;
    @Column(name = "STR")
    private String str;
    @Column(name = "TRNS_NO")
    private Long trnsNo;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "ACT_QTY")
    private Double actQty;
    @Column(name = "REMAIN_ID")
    private Long remainId;
    @Column(name = "PRD_ID")
    private Long prdId;
    @Column(name = "RMN_PRD_ID")
    private Long rmnPrdId;
    @Column(name = "RMN_TRNS_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rmnTrnsDate;
    @Column(name = "ITEMCODE")
    private String itemcode;
    @Column(name = "RMN_HAND_NO")
    private String rmnHandNo;
    @Column(name = "LOAD_DATE")
    @Temporal(TemporalType.DATE)
    private Date loadDate;
    @Column(name = "CITY")
    private String city;
    @Column(name = "AREA")
    private String area;
    @Column(name = "ADDRESS")
    private String address;
    @JoinColumn(name = "REQ_ID", referencedColumnName = "ID")
    @ManyToOne
    private CrmkRmnPrintRequest crmkRmnPrintRequest;
    @Column(name="TRGT_CLNT_NAME")
    private String trgtClntName;
    @Column(name="TRGT_CLNT_PHONE")
    private String trgtClntPhone;
    @Column(name="TRGT_DRIVER_NAME")
    private String trgtDriverName;
    @Column(name="TRGT_DRIVER_PHONE")
    private String trgtDriverPhone;
    @Column(name="EMP_REQUESTED_NAME")
    private String empRequestedName;
    @Column(name="EMP_REQUESTED_ID")
    private Long empRequestedId;
    @Column(name="BRN_REQUESTED_NAME")
    private String brnRequestedName;
    @Column(name="BRN_REQUESTED_ID")
    private Long brnRequestedId;
    @Column(name="TRGT_BRN_ID")
    private Long trgtBrnId;
    @Column(name="TARGET_BRN_NAME")
    private String trgtBrnName;
    @Column(name="TRGT_DRIVER_ID")
    private Long trgtDriverId;
    @Column(name="PREV_RMN_NO")
    private Long prevRmnNo;
    @Column(name="PREV_RMN_HAND_NO")
    private Long prevRmnHandNo;
    @Column(name="PREV_RMN_BRN_NAME")
    private String prevRmnBrnName;
    @Column(name="NOTES")
    private String notes;
    public CrmkOrdrAndRmnWithoutSrf() {
    }

    public Long getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(Long ordrId) {
        this.ordrId = ordrId;
    }

    public String getHandNo() {
        return handNo;
    }

    public void setHandNo(String handNo) {
        this.handNo = handNo;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public String getNoCTone() {
        return noCTone;
    }

    public void setNoCTone(String noCTone) {
        this.noCTone = noCTone;
    }

    public String getFactoryNo() {
        return factoryNo;
    }

    public void setFactoryNo(String factoryNo) {
        this.factoryNo = factoryNo;
    }

    public String getRmnNo() {
        return rmnNo;
    }

    public void setRmnNo(String rmnNo) {
        this.rmnNo = rmnNo;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Long getTrnsNo() {
        return trnsNo;
    }

    public void setTrnsNo(Long trnsNo) {
        this.trnsNo = trnsNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getActQty() {
        return actQty;
    }

    public void setActQty(Double actQty) {
        this.actQty = actQty;
    }

    public Double getCanQty() {
        return canQty;
    }

    public void setCanQty(Double canQty) {
        this.canQty = canQty;
    }

    public String getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(String crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public Long getFrz() {
        return frz;
    }

    public void setFrz(Long frz) {
        this.frz = frz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Double getOutQty() {
        return outQty;
    }

    public void setOutQty(Double outQty) {
        this.outQty = outQty;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getRemain() {
        return remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    public Long getRemainId() {
        return remainId;
    }

    public void setRemainId(Long remainId) {
        this.remainId = remainId;
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
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

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
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

    public String getRmnHandNo() {
        return rmnHandNo;
    }

    public void setRmnHandNo(String rmnHandNo) {
        this.rmnHandNo = rmnHandNo;
    }

    public CrmkRmnPrintRequest getCrmkRmnPrintRequest() {
        return crmkRmnPrintRequest;
    }

    public void setCrmkRmnPrintRequest(CrmkRmnPrintRequest crmkRmnPrintRequest) {
        this.crmkRmnPrintRequest = crmkRmnPrintRequest;
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

    public Long getTrgtBrnId() {
        return trgtBrnId;
    }

    public void setTrgtBrnId(Long trgtBrnId) {
        this.trgtBrnId = trgtBrnId;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrevRmnBrnName() {
        return prevRmnBrnName;
    }

    public void setPrevRmnBrnName(String prevRmnBrnName) {
        this.prevRmnBrnName = prevRmnBrnName;
    }

    public Long getPrevRmnHandNo() {
        return prevRmnHandNo;
    }

    public void setPrevRmnHandNo(Long prevRmnHandNo) {
        this.prevRmnHandNo = prevRmnHandNo;
    }

    public Long getPrevRmnNo() {
        return prevRmnNo;
    }

    public void setPrevRmnNo(Long prevRmnNo) {
        this.prevRmnNo = prevRmnNo;
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

    public Long getBrnRequestedId() {
        return brnRequestedId;
    }

    public void setBrnRequestedId(Long brnRequestedId) {
        this.brnRequestedId = brnRequestedId;
    }

    public String getTrgtBrnName() {
        return trgtBrnName;
    }

    public void setTrgtBrnName(String trgtBrnName) {
        this.trgtBrnName = trgtBrnName;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    
}

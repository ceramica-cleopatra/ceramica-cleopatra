/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Cacheable;
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
 * @author ahmed abbas
 */

@Entity
@Table(name = "CRMK_ORDERS_NOT_DELIVERED", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkOrdersNotDelivered.findAll", query = "SELECT c FROM CrmkOrdersNotDelivered c where c.trnsDate between :from_date and :to_date and (c.hrId.empNo=:emp or :emp is null) and (c.locId=:brn or :brn is null) order by c.ordrNo,c.clntName"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByOrdrId", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.ordrId = :ordrId"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByOrdrNo", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.ordrNo = :ordrNo"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findAllByBrnId", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE (c.brnId = :brnId or :brnId is null) and c.trnsDate between :from_date and :to_date order by c.ordrNo,c.clntName"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByTrnsDate", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByCrmkSehy", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.crmkSehy = :crmkSehy"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByClntName", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.clntName = :clntName"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByPhone1", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.phone1 = :phone1"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByEmpId", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.empId = :empId"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByPercent", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.percent = :percent"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByNoCTone", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.noCTone = :noCTone"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByGrpId", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.grpId = :grpId"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByFrz", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.frz = :frz"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByHrId", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.hrId = :hrId"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByQty", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.qty = :qty"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByTrnsNo", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.trnsNo = :trnsNo"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByName", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByMobile", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByDeliveryDate", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByBranchName", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.branchName = :branchName"),
    @NamedQuery(name = "CrmkOrdersNotDelivered.findByLocId", query = "SELECT c FROM CrmkOrdersNotDelivered c WHERE c.locId = :locId")})
public class CrmkOrdersNotDelivered implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    private Long id;
    @Column(name = "ORDR_ID")
    private Long ordrId;
    @Column(name = "ORDR_NO")
    private Long ordrNo;
    @Column(name = "BRN_ID")
    private Long brnId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "CRMK_SEHY")
    private String crmkSehy;
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Column(name = "PHONE1")
    private String phone1;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "PERCENT")
    private Long percent;
    @Column(name = "NO_C_TONE")
    private String noCTone;
    @Column(name = "GRP_ID")
    private Long grpId;
    @Column(name = "FRZ")
    private Long frz;
    @ManyToOne
    @JoinColumn(name = "HR_ID")
    private HrEmpInfo hrId;
    @Column(name = "QTY")
    private Double qty;
    @Column(name = "TRNS_NO")
    private Long trnsNo;
    @Column(name = "NAME")
    private String name;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "DELIVERY_DATE")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    @Column(name = "BRANCH_NAME")
    private String branchName;
    @Column(name = "LOC_ID")
    private Long locId;

    public CrmkOrdersNotDelivered() {
    }

    public Long getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(Long ordrId) {
        this.ordrId = ordrId;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

   

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

  

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

   

    public String getNoCTone() {
        return noCTone;
    }

    public void setNoCTone(String noCTone) {
        this.noCTone = noCTone;
    }

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
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

    public HrEmpInfo getHrId() {
        return hrId;
    }

    public void setHrId(HrEmpInfo hrId) {
        this.hrId = hrId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdrNo() {
        return ordrNo;
    }

    public void setOrdrNo(Long ordrNo) {
        this.ordrNo = ordrNo;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

}

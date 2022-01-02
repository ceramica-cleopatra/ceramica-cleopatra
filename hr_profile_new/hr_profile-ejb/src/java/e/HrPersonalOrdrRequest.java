/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_PERSONAL_ORDR_REQUEST", catalog = "", schema = "HR")

@NamedQueries({
    @NamedQuery(name = "HrPersonalOrdrRequest.findAll", query = "SELECT h FROM HrPersonalOrdrRequest h"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findById", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.id = :id"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByEmpNo", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.empNo.id = :empNo"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByOrdrId", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.ordrId = :ordrId"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByTrnsDate", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByApprove", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.approve = :approve"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByEmpApproved", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.empApproved = :empApproved"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByBrnId", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.brnId = :brnId"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByOrdrTrnsDate", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.ordrTrnsDate = :ordrTrnsDate"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByOrdrNo", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.ordrNo = :ordrNo"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByOrdrHandNo", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.ordrHandNo = :ordrHandNo"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByPrdrPrdId", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.prdrPrdId = :prdrPrdId"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByCrmkSehy", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.crmkSehy = :crmkSehy"),
    @NamedQuery(name = "HrPersonalOrdrRequest.findByQty", query = "SELECT h FROM HrPersonalOrdrRequest h WHERE h.qty = :qty"),
    @NamedQuery(name = "HrPersonalOrdrRequest.chkOrdrExist", query ="SELECT COUNT(c) FROM CrmkOrdrHd c WHERE c.no= :ordr_no and c.crmkBranch.id= :brn_id and c.prdId= :prd_id and c.crmkSehy= :crmk_sehy and (c.canceled='N' or c.canceled is null)"),
    @NamedQuery(name = "HrPersonalOrdrRequest.getPersonalOrdr", query ="SELECT c FROM CrmkOrdrHd c WHERE c.no= :ordr_no and c.crmkBranch.id= :brn_id and c.prdId= :prd_id and c.crmkSehy= :crmk_sehy"),
    @NamedQuery(name = "HrPersonalOrdrRequest.chkOrdrIdExist", query ="SELECT count(c) FROM HrPersonalOrdrRequest c WHERE c.ordrId= :ordr_id"),
    @NamedQuery(name = "HrPersonalOrdrRequest.getPersonalOrdrToConfirm", query ="SELECT c FROM HrPersonalOrdrRequest c WHERE c.trnsDate between :from_date and :to_date order by c.trnsDate desc")
    })
public class HrPersonalOrdrRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="SEQ" ,initialValue=1,sequenceName="HRPERSONALORDRSEQUENCE",allocationSize=1)
    @Id
    @GeneratedValue(generator="SEQ",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "EMP_NO", referencedColumnName = "ID")
    @ManyToOne
    private HrEmpHd empNo;
    @Column(name = "ORDR_ID")
    private Long ordrId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "APPROVE")
    private Character approve;
    @JoinColumn(name = "EMP_APPROVED", referencedColumnName = "ID")
    @ManyToOne
    private HrEmpHd empApproved;
    @JoinColumn(name = "BRN_ID",referencedColumnName="ID")
    @ManyToOne
    private CrmkBranch brnId;
    @Column(name = "ORDR_TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordrTrnsDate;
    @Column(name = "ORDR_NO")
    private Long ordrNo;
    @Column(name = "ORDR_HAND_NO")
    private String ordrHandNo;
    @Column(name = "PRDR_PRD_ID")
    private Long prdrPrdId;
    @Column(name = "CRMK_SEHY")
    private Character crmkSehy;
    @Column(name = "QTY")
    private Double qty;
    @Column(name = "TOT_VAL")
    private BigDecimal totVal;
    @Column(name = "DSCNT")
    private Long dscnt;
    public HrPersonalOrdrRequest() {
    }

    public Long getDscnt() {
        return dscnt;
    }

    public void setDscnt(Long dscnt) {
        this.dscnt = dscnt;
    }

    public BigDecimal getTotVal() {
        return totVal;
    }

    public void setTotVal(BigDecimal totVal) {
        this.totVal = totVal;
    }

   

   

   

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Character getApprove() {
        return approve;
    }

    public void setApprove(Character approve) {
        this.approve = approve;
    }

   

    public Date getOrdrTrnsDate() {
        return ordrTrnsDate;
    }

    public void setOrdrTrnsDate(Date ordrTrnsDate) {
        this.ordrTrnsDate = ordrTrnsDate;
    }


    public Character getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(Character crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public CrmkBranch getBrnId() {
        return brnId;
    }

    public void setBrnId(CrmkBranch brnId) {
        this.brnId = brnId;
    }

   

    public HrEmpHd getEmpApproved() {
        return empApproved;
    }

    public void setEmpApproved(HrEmpHd empApproved) {
        this.empApproved = empApproved;
    }

    public HrEmpHd getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpHd empNo) {
        this.empNo = empNo;
    }

   

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdrHandNo() {
        return ordrHandNo;
    }

    public void setOrdrHandNo(String ordrHandNo) {
        this.ordrHandNo = ordrHandNo;
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

    public Long getPrdrPrdId() {
        return prdrPrdId;
    }

    public void setPrdrPrdId(Long prdrPrdId) {
        this.prdrPrdId = prdrPrdId;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

   

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrPersonalOrdrRequest)) {
            return false;
        }
        HrPersonalOrdrRequest other = (HrPersonalOrdrRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrPersonalOrdrRequest[id=" + id + "]";
    }

}

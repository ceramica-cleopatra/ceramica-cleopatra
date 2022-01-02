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
 * @author a.abbas
 */
@Entity
@Table(name = "HR_ADVANCE_REQUEST", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrAdvanceRequest.findAll", query = "SELECT h FROM HrAdvanceRequest h"),
    @NamedQuery(name = "HrAdvanceRequest.findById", query = "SELECT h FROM HrAdvanceRequest h WHERE h.id = :id"),
    @NamedQuery(name = "HrAdvanceRequest.findByEmpNo", query = "SELECT h FROM HrAdvanceRequest h WHERE h.empNo = :empNo order by h.trnsDate desc"),
    @NamedQuery(name = "HrAdvanceRequest.findByTrnsDate", query = "SELECT h FROM HrAdvanceRequest h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrAdvanceRequest.findByGuaranteeNo", query = "SELECT h FROM HrAdvanceRequest h WHERE h.guaranteeNo = :guaranteeNo and (h.cancel='N' or h.cancel is null) and (h.guaranteeApprove is null or h.guaranteeApprove='N') and h.deptMngApprove is null and h.trnsDate>=:from_date"),
    @NamedQuery(name = "HrAdvanceRequest.findByRespNo", query = "SELECT h FROM HrAdvanceRequest h WHERE (h.cancel='N' or h.cancel is null) and h.deptMngApprove='Y' and (h.respApprove is null or h.respApprove='N') and h.trnsDate>=:from_date order by h.id"),
    @NamedQuery(name = "HrAdvanceRequest.findByDeptMngNo", query = "SELECT h FROM HrAdvanceRequest h WHERE h.deptMngNo = :deptMngNo and (h.cancel='N' or h.cancel is null) and h.guaranteeApprove='Y' and (h.deptMngApprove is null or h.deptMngApprove='N') and h.trnsDate>=:from_date"),
    @NamedQuery(name = "HrAdvanceRequest.findByGuaranteeApprove", query = "SELECT h FROM HrAdvanceRequest h WHERE h.guaranteeApprove = :guaranteeApprove"),
    @NamedQuery(name = "HrAdvanceRequest.findByDeptMngApprove", query = "SELECT h FROM HrAdvanceRequest h WHERE h.deptMngApprove = :deptMngApprove"),
    @NamedQuery(name = "HrAdvanceRequest.findByFundRespNo", query = "SELECT h FROM HrAdvanceRequest h WHERE h.fundRespNo = :fundRespNo"),
    @NamedQuery(name = "HrAdvanceRequest.findByRespApprove", query = "SELECT h FROM HrAdvanceRequest h WHERE h.respApprove = :respApprove"),
    @NamedQuery(name = "HrAdvanceRequest.findByAmount", query = "SELECT h FROM HrAdvanceRequest h WHERE h.amount = :amount")})
public class HrAdvanceRequest implements Serializable,Cloneable{
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_ADVANCE_REQUEST_SEQ", sequenceName="HR_ADVANCE_REQUEST_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_ADVANCE_REQUEST_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="EMP_NO")
    private HrEmpInfo empNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @ManyToOne
    @JoinColumn(name="GUARANTEE_NO")
    private HrEmpInfo guaranteeNo;
    @ManyToOne
    @JoinColumn(name="DEPT_MNG_NO")
    private HrEmpInfo deptMngNo;
    @Column(name = "GUARANTEE_APPROVE")
    private Character guaranteeApprove;
    @Column(name = "DEPT_MNG_APPROVE")
    private Character deptMngApprove;
    @ManyToOne
    @JoinColumn(name="FUND_RESP_NO")
    private HrEmpInfo fundRespNo;
    @Column(name = "RESP_APPROVE")
    private Character respApprove;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "CANCEL")
    private Character cancel;
    public HrAdvanceRequest() {
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Character getGuaranteeApprove() {
        return guaranteeApprove;
    }

    public void setGuaranteeApprove(Character guaranteeApprove) {
        this.guaranteeApprove = guaranteeApprove;
    }

    public Character getDeptMngApprove() {
        return deptMngApprove;
    }

    public void setDeptMngApprove(Character deptMngApprove) {
        this.deptMngApprove = deptMngApprove;
    }

    public Character getRespApprove() {
        return respApprove;
    }

    public void setRespApprove(Character respApprove) {
        this.respApprove = respApprove;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public HrEmpInfo getDeptMngNo() {
        return deptMngNo;
    }

    public void setDeptMngNo(HrEmpInfo deptMngNo) {
        this.deptMngNo = deptMngNo;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    public HrEmpInfo getFundRespNo() {
        return fundRespNo;
    }

    public void setFundRespNo(HrEmpInfo fundRespNo) {
        this.fundRespNo = fundRespNo;
    }

    public HrEmpInfo getGuaranteeNo() {
        return guaranteeNo;
    }

    public void setGuaranteeNo(HrEmpInfo guaranteeNo) {
        this.guaranteeNo = guaranteeNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getCancel() {
        return cancel;
    }

    public void setCancel(Character cancel) {
        this.cancel = cancel;
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
        if (!(object instanceof HrAdvanceRequest)) {
            return false;
        }
        HrAdvanceRequest other = (HrAdvanceRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "e.HrAdvanceRequest[id=" + id + "]";
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
      return super.clone();
    }

}

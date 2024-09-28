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
@Table(name = "HR_HOLIDAY_REQUEST", catalog = "", schema = "HR")
@NamedQueries({
   // @NamedQuery(name = "HrHolidayRequest.findAll1", query = "select o.id,o.empId,o.fromDate,o.toDate,o.trnsDate,o.daysNo,o.canceled,o.mngConfirm,t.name,i.empName,t.id,o,o.rejectDesc,ae.empName from HrHolidayRequest o JOIN o.holidayType t LEFT JOIN o.hrEmpInfo i  JOIN o.alternativeEmp ae where o.empId=:emp_id and o.fromDate>=:from_date"),
    @NamedQuery(name = "HrHolidayRequest.findAll", query = "select o from HrHolidayRequest o where o.empId=:emp_id and o.fromDate>=:from_date"),
    @NamedQuery(name = "HrHolidayRequest.chkHolidayEntry", query = "select count(o) from HrHolidayRequest o where o.empId=:emp_id and ((:from_date between o.fromDate and o.toDate) or (:to_date between o.fromDate and o.toDate) or (o.fromDate between :from_date and :to_date) or (o.toDate between :from_date and :to_date)) and (o.canceled='N' or o.canceled is null) and o.id<>:id"),
    @NamedQuery(name = "HrHolidayRequest.getMax", query = "select max(o.id) from HrHolidayRequest o"),
    @NamedQuery(name = "HrHolidayRequest.findById", query = "SELECT h FROM HrHolidayRequest h WHERE h.id = :id"),
    @NamedQuery(name = "HrHolidayRequest.findByEmpId", query = "SELECT h FROM HrHolidayRequest h WHERE h.empId = :empId"),
    @NamedQuery(name = "HrHolidayRequest.findByHolidayType", query = "SELECT h FROM HrHolidayRequest h WHERE h.holidayType = :holidayType"),
    @NamedQuery(name = "HrHolidayRequest.findByDaysNo", query = "SELECT h FROM HrHolidayRequest h WHERE h.daysNo = :daysNo"),
    @NamedQuery(name = "HrHolidayRequest.findByFromDate", query = "SELECT h FROM HrHolidayRequest h WHERE h.fromDate = :fromDate"),
    @NamedQuery(name = "HrHolidayRequest.findByToDate", query = "SELECT h FROM HrHolidayRequest h WHERE h.toDate = :toDate"),
    @NamedQuery(name = "HrHolidayRequest.findByAlternativeEmp", query = "SELECT h FROM HrHolidayRequest h WHERE h.alternativeEmp = :alternativeEmp"),
    @NamedQuery(name = "HrHolidayRequest.findByMngConfirm", query = "SELECT h FROM HrHolidayRequest h WHERE h.mngConfirm = :mngConfirm"),
    @NamedQuery(name = "HrHolidayRequest.findByTrnsDate", query = "SELECT h FROM HrHolidayRequest h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrHolidayRequest.findByRequestDesc", query = "SELECT h FROM HrHolidayRequest h WHERE h.requestDesc = :requestDesc"),
    @NamedQuery(name = "HrHolidayRequest.findByRejectDesc", query = "SELECT h FROM HrHolidayRequest h WHERE h.rejectDesc = :rejectDesc"),
    @NamedQuery(name = "HrHolidayRequest.findByCanceled", query = "SELECT h FROM HrHolidayRequest h WHERE h.canceled = :canceled")})
public class HrHolidayRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_ID")
    private Long empId;
    @ManyToOne
    @JoinColumn(name = "HOLIDAY_TYPE")
    private HrHolidayType holidayType;
    @Column(name = "DAYS_NO")
    private Long daysNo;
    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @ManyToOne
    @JoinColumn(name = "ALTERNATIVE_EMP")
    private HrEmpInfo alternativeEmp;
    @Column(name = "MNG_CONFIRM")
    private Character mngConfirm;
    @ManyToOne
    @JoinColumn(name = "MNG_NO")
    private HrEmpInfo mngNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "REQUEST_DESC")
    private String requestDesc;
    @Column(name = "REJECT_DESC")
    private String rejectDesc;
    @Column(name = "CANCELED")
    private Character canceled;
    @Column(name = "APPROVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date approveDate;

    public HrHolidayRequest() {
    }

    
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

   

    public Character getMngConfirm() {
        return mngConfirm;
    }

    public void setMngConfirm(Character mngConfirm) {
        this.mngConfirm = mngConfirm;
    }

   

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }

    public String getRejectDesc() {
        return rejectDesc;
    }

    public void setRejectDesc(String rejectDesc) {
        this.rejectDesc = rejectDesc;
    }

    public Character getCanceled() {
        return canceled;
    }

    public void setCanceled(Character canceled) {
        this.canceled = canceled;
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
        if (!(object instanceof HrHolidayRequest)) {
            return false;
        }
        HrHolidayRequest other = (HrHolidayRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public HrEmpInfo getAlternativeEmp() {
        return alternativeEmp;
    }

    public void setAlternativeEmp(HrEmpInfo alternativeEmp) {
        this.alternativeEmp = alternativeEmp;
    }

  

    public Long getDaysNo() {
        return daysNo;
    }

    public void setDaysNo(Long daysNo) {
        this.daysNo = daysNo;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public HrHolidayType getHrHoldayType() {
        return holidayType;
    }

    public void setHrHoldayType(HrHolidayType hrHoldayType) {
        this.holidayType = hrHoldayType;
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrHolidayType getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HrHolidayType holidayType) {
        this.holidayType = holidayType;
    }

    public HrEmpInfo getMngNo() {
        return mngNo;
    }

    public void setMngNo(HrEmpInfo mngNo) {
        this.mngNo = mngNo;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }
  

    @Override
    public String toString() {
        return "e.HrHolidayRequest[id=" + id + "]";
    }

}

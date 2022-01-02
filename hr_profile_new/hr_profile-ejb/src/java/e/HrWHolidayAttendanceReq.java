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
 * @author Administrator
 */

@Entity
@Table(name = "HR_W_HOLIDAY_ATTENDANCE_REQ", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrWHolidayAttendanceReq.findAll", query = "SELECT h FROM HrWHolidayAttendanceReq h"),
    @NamedQuery(name = "HrWHolidayAttendanceReq.findHist", query = "SELECT h FROM HrWHolidayAttendanceReq h where h.trnsDate>=:from_date and h.empNo=:emp_no"),
    @NamedQuery(name = "HrWHolidayAttendanceReq.findMaxId", query = "SELECT max(h.id) FROM HrWHolidayAttendanceReq h"),
    @NamedQuery(name = "HrWHolidayAttendanceReq.findById", query = "SELECT h FROM HrWHolidayAttendanceReq h WHERE h.id = :id"),
    @NamedQuery(name = "HrWHolidayAttendanceReq.findByEmpNo", query = "SELECT h FROM HrWHolidayAttendanceReq h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrWHolidayAttendanceReq.findByWeeklyHolidayDate", query = "SELECT h FROM HrWHolidayAttendanceReq h WHERE h.weeklyHolidayDate = :weeklyHolidayDate"),
    @NamedQuery(name = "HrWHolidayAttendanceReq.findByApproved", query = "SELECT h FROM HrWHolidayAttendanceReq h WHERE h.approved = :approved"),
    @NamedQuery(name = "HrWHolidayAttendanceReq.findByMngNo", query = "SELECT h FROM HrWHolidayAttendanceReq h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrWHolidayAttendanceReq.findByTrnsDate", query = "SELECT h FROM HrWHolidayAttendanceReq h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrWHolidayAttendanceReq.findByCanceled", query = "SELECT h FROM HrWHolidayAttendanceReq h WHERE h.canceled = :canceled")})
public class HrWHolidayAttendanceReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "WEEKLY_HOLIDAY_DATE")
    @Temporal(TemporalType.DATE)
    private Date weeklyHolidayDate;
    @Column(name = "APPROVED")
    private String approved;
    @ManyToOne
    @JoinColumn(name = "MNG_NO")
    private HrEmpInfo mngNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "CANCELED")
    private String canceled;

    public HrWHolidayAttendanceReq() {
    }

    
    public Date getWeeklyHolidayDate() {
        return weeklyHolidayDate;
    }

    public void setWeeklyHolidayDate(Date weeklyHolidayDate) {
        this.weeklyHolidayDate = weeklyHolidayDate;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrEmpInfo getMngNo() {
        return mngNo;
    }

    public void setMngNo(HrEmpInfo mngNo) {
        this.mngNo = mngNo;
    }

   

   

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
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
        if (!(object instanceof HrWHolidayAttendanceReq)) {
            return false;
        }
        HrWHolidayAttendanceReq other = (HrWHolidayAttendanceReq) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrWHolidayAttendanceReq[id=" + id + "]";
    }

}

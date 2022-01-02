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
 * @author data
 */
@Entity
@Table(name = "HR_DUTY_TRNS_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDutyTrnsDt.findAll", query = "SELECT h FROM HrDutyTrnsDt h"),
    @NamedQuery(name = "HrDutyTrnsDt.findById", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrDutyTrnsDt.findByFromLoc", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.fromLoc = :fromLoc"),
    @NamedQuery(name = "HrDutyTrnsDt.findByToLoc", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.toLoc = :toLoc"),
    @NamedQuery(name = "HrDutyTrnsDt.findByFromTime", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.fromTime = :fromTime"),
    @NamedQuery(name = "HrDutyTrnsDt.findByToTime", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.toTime = :toTime"),
    @NamedQuery(name = "HrDutyTrnsDt.findByActualTime", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.actualTime = :actualTime"),
    @NamedQuery(name = "HrDutyTrnsDt.findByExpectedTime", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.expectedTime = :expectedTime"),
    @NamedQuery(name = "HrDutyTrnsDt.findByMngNo", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrDutyTrnsDt.findByApproved", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.approved = :approved"),
    @NamedQuery(name = "HrDutyTrnsDt.findByApproveDate", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.approveDate = :approveDate"),
    @NamedQuery(name = "HrDutyTrnsDt.findByLocId", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrDutyTrnsDt.findByCalculatedTime", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.calculatedTime = :calculatedTime"),
    @NamedQuery(name = "HrDutyTrnsDt.findByEditByHr", query = "SELECT h FROM HrDutyTrnsDt h WHERE h.editByHr = :editByHr")})
public class HrDutyTrnsDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "FROM_LOC")
    private HrLocation fromLoc;
    @ManyToOne
    @JoinColumn(name = "TO_LOC")
    private HrLocation toLoc;
    @Column(name = "FROM_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromTime;
    @Column(name = "TO_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toTime;
    @Column(name = "ACTUAL_TIME")
    private Long actualTime;
    @Column(name = "EXPECTED_TIME")
    private Long expectedTime;
    @ManyToOne
    @JoinColumn(name = "MNG_NO")
    private HrEmpInfo mngNo;
    @Column(name = "APPROVED")
    private Character approved;
    @Column(name = "APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveDate;
    @JoinColumn(name = "LOC_ID")
    @ManyToOne
    private HrLocation locId;
    @Column(name = "CALCULATED_TIME")
    private Long calculatedTime;
    @Column(name = "EDIT_BY_HR")
    private Character editByHr;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrDutyTrnsHdVu hrDutyTrnsHdVu;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID",insertable=false,updatable=false)
    @ManyToOne
    private HrDutyTrnsHd hrDutyTrnsHd;

    public HrDutyTrnsDt() {
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Character getApproved() {
        return approved;
    }

    public void setApproved(Character approved) {
        this.approved = approved;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Long getActualTime() {
        return actualTime;
    }

    public void setActualTime(Long actualTime) {
        this.actualTime = actualTime;
    }

    public Long getCalculatedTime() {
        return calculatedTime;
    }

    public void setCalculatedTime(Long calculatedTime) {
        this.calculatedTime = calculatedTime;
    }

    public Long getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Long expectedTime) {
        this.expectedTime = expectedTime;
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

    public Character getEditByHr() {
        return editByHr;
    }

    public void setEditByHr(Character editByHr) {
        this.editByHr = editByHr;
    }

    public HrDutyTrnsHdVu getHrDutyTrnsHdVu() {
        return hrDutyTrnsHdVu;
    }

    public void setHrDutyTrnsHdVu(HrDutyTrnsHdVu hrDutyTrnsHdVu) {
        this.hrDutyTrnsHdVu = hrDutyTrnsHdVu;
    }

    public HrLocation getFromLoc() {
        return fromLoc;
    }

    public void setFromLoc(HrLocation fromLoc) {
        this.fromLoc = fromLoc;
    }

    public HrLocation getLocId() {
        return locId;
    }

    public void setLocId(HrLocation locId) {
        this.locId = locId;
    }

    public HrLocation getToLoc() {
        return toLoc;
    }

    public void setToLoc(HrLocation toLoc) {
        this.toLoc = toLoc;
    }

    public HrDutyTrnsHd getHrDutyTrnsHd() {
        return hrDutyTrnsHd;
    }

    public void setHrDutyTrnsHd(HrDutyTrnsHd hrDutyTrnsHd) {
        this.hrDutyTrnsHd = hrDutyTrnsHd;
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
        if (!(object instanceof HrDutyTrnsDt)) {
            return false;
        }
        HrDutyTrnsDt other = (HrDutyTrnsDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDutyTrnsDt[id=" + id + "]";
    }

}

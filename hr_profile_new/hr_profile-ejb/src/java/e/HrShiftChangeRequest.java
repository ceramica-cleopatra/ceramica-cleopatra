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
@Table(name = "HR_SHIFT_CHANGE_REQUEST", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrShiftChangeRequest.findAll", query = "SELECT h FROM HrShiftChangeRequest h where h.empNo.empNo=:emp and h.shiftDate>:from_date order by h.shiftDate"),
    @NamedQuery(name = "HrShiftChangeRequest.chkShiftExist", query = "SELECT count(h.id) FROM HrShiftChangeRequest h where h.empNo.empNo=:emp and h.shiftDate=:from_date and (h.canceled is null or h.canceled='N') and (h.mngConfirm<>'N' or h.mngConfirm is null) and (h.id<>:id or :id is null)"),
    @NamedQuery(name = "HrShiftChangeRequest.findMax", query = "SELECT max(h.id) FROM HrShiftChangeRequest h"),
    @NamedQuery(name = "HrShiftChangeRequest.findById", query = "SELECT h FROM HrShiftChangeRequest h WHERE h.id = :id"),
    @NamedQuery(name = "HrShiftChangeRequest.findByTrnsDate", query = "SELECT h FROM HrShiftChangeRequest h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrShiftChangeRequest.findByShiftDate", query = "SELECT h FROM HrShiftChangeRequest h WHERE h.shiftDate = :shiftDate"),
    @NamedQuery(name = "HrShiftChangeRequest.findByEmpNo", query = "SELECT h FROM HrShiftChangeRequest h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrShiftChangeRequest.findByShiftId", query = "SELECT h FROM HrShiftChangeRequest h WHERE h.shiftId = :shiftId"),
    @NamedQuery(name = "HrShiftChangeRequest.findByMngNo", query = "SELECT h FROM HrShiftChangeRequest h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrShiftChangeRequest.findByMngConfirm", query = "SELECT h FROM HrShiftChangeRequest h WHERE h.mngConfirm = :mngConfirm"),
    @NamedQuery(name = "HrShiftChangeRequest.findByCanceled", query = "SELECT h FROM HrShiftChangeRequest h WHERE h.canceled = :canceled")})
public class HrShiftChangeRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "SHIFT_DATE")
    @Temporal(TemporalType.DATE)
    private Date shiftDate;
    @ManyToOne
    @JoinColumn(name = "EMP_NO")
    private HrEmpInfo empNo;
    @ManyToOne
    @JoinColumn(name = "SHIFT_ID")
    private HrShift shiftId;
    @ManyToOne
    @JoinColumn(name = "MNG_NO")
    private HrEmpInfo mngNo;
    @Column(name = "MNG_CONFIRM")
    private String mngConfirm;
    @Column(name = "CANCELED")
    private String canceled;
    @Column(name = "EFF_RECALCULATED")
    private String effRecalculated;
    @Column(name = "CONFIRM_DATE")
    @Temporal(TemporalType.DATE)
    private Date confirmDate;
    public HrShiftChangeRequest() {
    }

   

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMngConfirm() {
        return mngConfirm;
    }

    public void setMngConfirm(String mngConfirm) {
        this.mngConfirm = mngConfirm;
    }

    public HrEmpInfo getMngNo() {
        return mngNo;
    }

    public void setMngNo(HrEmpInfo mngNo) {
        this.mngNo = mngNo;
    }

    public HrShift getShiftId() {
        return shiftId;
    }

    public void setShiftId(HrShift shiftId) {
        this.shiftId = shiftId;
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
        if (!(object instanceof HrShiftChangeRequest)) {
            return false;
        }
        HrShiftChangeRequest other = (HrShiftChangeRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrShiftChangeRequest[id=" + id + "]";
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

 

    public String getEffRecalculated() {
        return effRecalculated;
    }

    public void setEffRecalculated(String effRecalculated) {
        this.effRecalculated = effRecalculated;
    }

}

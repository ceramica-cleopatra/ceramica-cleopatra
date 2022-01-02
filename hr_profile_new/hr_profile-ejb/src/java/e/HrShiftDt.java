/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_SHIFT_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrShiftDt.findAll", query = "SELECT h FROM HrShiftDt h where h.profile='Y' and h.deptId=:deptId and (h.locId=:locId or h.locId is null)"),
    @NamedQuery(name = "HrShiftDt.findByDeptId", query = "SELECT h FROM HrShiftDt h WHERE h.profile='Y' and h.deptId = :deptId"),
    @NamedQuery(name = "HrShiftDt.findById", query = "SELECT h FROM HrShiftDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrShiftDt.findByProfile", query = "SELECT h FROM HrShiftDt h WHERE h.profile = :profile"),
    @NamedQuery(name = "HrShiftDt.findByNotes", query = "SELECT h FROM HrShiftDt h WHERE h.notes = :notes")})
public class HrShiftDt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrShift HrShift;
    @Column(name = "PROFILE")
    private Character profile;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "LOC_ID")
    private Long locId;
    @Column(name = "NOTES")
    private String notes;

    public HrShiftDt() {
    }

    public HrShiftDt(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Character getProfile() {
        return profile;
    }

    public void setProfile(Character profile) {
        this.profile = profile;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        if (!(object instanceof HrShiftDt)) {
            return false;
        }
        HrShiftDt other = (HrShiftDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public HrShift getHrShift() {
        return HrShift;
    }

    public void setHrShift(HrShift HrShift) {
        this.HrShift = HrShift;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    

    @Override
    public String toString() {
        return "e.HrShiftDt[id=" + id + "]";
    }
}

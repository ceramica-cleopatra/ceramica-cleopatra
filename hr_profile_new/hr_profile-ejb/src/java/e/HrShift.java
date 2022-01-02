/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_SHIFT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrShift.findAll", query = "SELECT h FROM HrShift h where h.deptId=:dept and h.profile='Y'"),
    @NamedQuery(name = "HrShift.findById", query = "SELECT h FROM HrShift h WHERE h.id = :id"),
    @NamedQuery(name = "HrShift.findByName", query = "SELECT h FROM HrShift h WHERE h.name = :name"),
    @NamedQuery(name = "HrShift.findByFrm", query = "SELECT h FROM HrShift h WHERE h.frm = :frm"),
    @NamedQuery(name = "HrShift.findByToo", query = "SELECT h FROM HrShift h WHERE h.too = :too"),
    @NamedQuery(name = "HrShift.findByPeriod", query = "SELECT h FROM HrShift h WHERE h.period = :period"),
    @NamedQuery(name = "HrShift.findByNotes", query = "SELECT h FROM HrShift h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrShift.findByProfile", query = "SELECT h FROM HrShift h WHERE h.profile = :profile"),
    @NamedQuery(name = "HrShift.findByDeptId", query = "SELECT h FROM HrShift h WHERE h.deptId = :deptId")})
public class HrShift implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FRM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frm;
    @Column(name = "TOO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date too;
    @Column(name = "PERIOD")
    private Long period;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "PROFILE")
    private String profile;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @OneToMany(mappedBy="shiftId")
    private List<HrEmpTime> hrEmpTimeList;
    @OneToMany(mappedBy="shiftId")
    private List<HrShiftChangeRequest> hrShiftChangeRequestList;
    @OneToMany(mappedBy="hrShift")
    private List<HrEmpEffectionVu> hrEmpEffectionVuList;
    public HrShift() {
    }

    public HrShift(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFrm() {
        return frm;
    }

    public void setFrm(Date frm) {
        this.frm = frm;
    }

    public Date getToo() {
        return too;
    }

    public void setToo(Date too) {
        this.too = too;
    }

   

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }



    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<HrEmpEffectionVu> getHrEmpEffectionVuList() {
        return hrEmpEffectionVuList;
    }

    public void setHrEmpEffectionVuList(List<HrEmpEffectionVu> hrEmpEffectionVuList) {
        this.hrEmpEffectionVuList = hrEmpEffectionVuList;
    }

    public List<HrEmpTime> getHrEmpTimeList() {
        return hrEmpTimeList;
    }

    public void setHrEmpTimeList(List<HrEmpTime> hrEmpTimeList) {
        this.hrEmpTimeList = hrEmpTimeList;
    }

    public List<HrShiftChangeRequest> getHrShiftChangeRequestList() {
        return hrShiftChangeRequestList;
    }

    public void setHrShiftChangeRequestList(List<HrShiftChangeRequest> hrShiftChangeRequestList) {
        this.hrShiftChangeRequestList = hrShiftChangeRequestList;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
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
        if (!(object instanceof HrShift)) {
            return false;
        }
        HrShift other = (HrShift) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrShift[id=" + id + "]";
    }

}

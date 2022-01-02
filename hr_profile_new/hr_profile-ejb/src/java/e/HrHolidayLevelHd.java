/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_HOLIDAY_LEVEL_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHolidayLevelHd.findAll", query = "SELECT h FROM HrHolidayLevelHd h"),
    @NamedQuery(name = "HrHolidayLevelHd.findById", query = "SELECT h FROM HrHolidayLevelHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrHolidayLevelHd.findByGrpId", query = "SELECT h FROM HrHolidayLevelHd h WHERE h.grpId = :grpId"),
    @NamedQuery(name = "HrHolidayLevelHd.findByJobId", query = "SELECT h FROM HrHolidayLevelHd h WHERE h.jobId = :jobId"),
    @NamedQuery(name = "HrHolidayLevelHd.findByLocId", query = "SELECT h FROM HrHolidayLevelHd h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrHolidayLevelHd.findByDeptId", query = "SELECT h FROM HrHolidayLevelHd h WHERE h.deptId = :deptId")})
public class HrHolidayLevelHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "GRP_ID")
    private BigInteger grpId;
    @Column(name = "JOB_ID")
    private BigInteger jobId;
    @Column(name = "LOC_ID")
    private BigInteger locId;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @OneToMany(mappedBy = "hrHolidayLevelHd")
    private List<HrHolidayLevelDt> hrHolidayLevelDtList;

    public HrHolidayLevelHd() {
    }

    public HrHolidayLevelHd(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getGrpId() {
        return grpId;
    }

    public void setGrpId(BigInteger grpId) {
        this.grpId = grpId;
    }

    public BigInteger getJobId() {
        return jobId;
    }

    public void setJobId(BigInteger jobId) {
        this.jobId = jobId;
    }

    public BigInteger getLocId() {
        return locId;
    }

    public void setLocId(BigInteger locId) {
        this.locId = locId;
    }

    public BigInteger getDeptId() {
        return deptId;
    }

    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }

    public List<HrHolidayLevelDt> getHrHolidayLevelDtList() {
        return hrHolidayLevelDtList;
    }

    public void setHrHolidayLevelDtList(List<HrHolidayLevelDt> hrHolidayLevelDtList) {
        this.hrHolidayLevelDtList = hrHolidayLevelDtList;
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
        if (!(object instanceof HrHolidayLevelHd)) {
            return false;
        }
        HrHolidayLevelHd other = (HrHolidayLevelHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrHolidayLevelHd[id=" + id + "]";
    }

}

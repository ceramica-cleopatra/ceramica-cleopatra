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
 * @author data
 */
@Entity
@Table(name = "HR_MAN_NOTES_TITLES_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManNotesTitlesHd.findAll", query = "SELECT h FROM HrManNotesTitlesHd h"),
    @NamedQuery(name = "HrManNotesTitlesHd.findById", query = "SELECT h FROM HrManNotesTitlesHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrManNotesTitlesHd.findByEmpNo", query = "SELECT h FROM HrManNotesTitlesHd h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrManNotesTitlesHd.findByDeptId", query = "SELECT h FROM HrManNotesTitlesHd h WHERE h.deptId = :deptId"),
    @NamedQuery(name = "HrManNotesTitlesHd.findByJobId", query = "SELECT h FROM HrManNotesTitlesHd h WHERE h.jobId = :jobId"),
    @NamedQuery(name = "HrManNotesTitlesHd.findByJobGrpId", query = "SELECT h FROM HrManNotesTitlesHd h WHERE h.jobGrpId = :jobGrpId")})
public class HrManNotesTitlesHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "EMP_NO")
    private BigInteger empNo;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "JOB_ID")
    private BigInteger jobId;
    @Column(name = "JOB_GRP_ID")
    private BigInteger jobGrpId;
    @OneToMany(mappedBy = "hrManNotesTitlesHd")
    private List<HrManNotesTitlesDt> hrManNotesTitlesDtList;

    public HrManNotesTitlesHd() {
    }

    public HrManNotesTitlesHd(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getEmpNo() {
        return empNo;
    }

    public void setEmpNo(BigInteger empNo) {
        this.empNo = empNo;
    }

    public BigInteger getDeptId() {
        return deptId;
    }

    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }

    public BigInteger getJobId() {
        return jobId;
    }

    public void setJobId(BigInteger jobId) {
        this.jobId = jobId;
    }

    public BigInteger getJobGrpId() {
        return jobGrpId;
    }

    public void setJobGrpId(BigInteger jobGrpId) {
        this.jobGrpId = jobGrpId;
    }

    public List<HrManNotesTitlesDt> getHrManNotesTitlesDtList() {
        return hrManNotesTitlesDtList;
    }

    public void setHrManNotesTitlesDtList(List<HrManNotesTitlesDt> hrManNotesTitlesDtList) {
        this.hrManNotesTitlesDtList = hrManNotesTitlesDtList;
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
        if (!(object instanceof HrManNotesTitlesHd)) {
            return false;
        }
        HrManNotesTitlesHd other = (HrManNotesTitlesHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrManNotesTitlesHd[id=" + id + "]";
    }

}

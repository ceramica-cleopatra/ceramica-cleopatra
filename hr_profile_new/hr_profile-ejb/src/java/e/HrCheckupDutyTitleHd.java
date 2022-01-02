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
@Table(name = "HR_CHECKUP_DUTY_TITLE_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyTitleHd.findAll", query = "SELECT h FROM HrCheckupDutyTitleHd h"),
    @NamedQuery(name = "HrCheckupDutyTitleHd.findById", query = "SELECT h FROM HrCheckupDutyTitleHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyTitleHd.findByEmpNo", query = "SELECT h FROM HrCheckupDutyTitleHd h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrCheckupDutyTitleHd.findByDeptId", query = "SELECT h FROM HrCheckupDutyTitleHd h WHERE h.deptId = :deptId"),
    @NamedQuery(name = "HrCheckupDutyTitleHd.findByJobId", query = "SELECT h FROM HrCheckupDutyTitleHd h WHERE h.jobId = :jobId"),
    @NamedQuery(name = "HrCheckupDutyTitleHd.findByJobGrpId", query = "SELECT h FROM HrCheckupDutyTitleHd h WHERE h.jobGrpId = :jobGrpId")})
public class HrCheckupDutyTitleHd implements Serializable {
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
    @OneToMany(mappedBy = "hrCheckupDutyTitleHd")
    private List<HrCheckupDutyTitleDt> hrCheckupDutyTitleDtList;

    public HrCheckupDutyTitleHd() {
    }

    public HrCheckupDutyTitleHd(BigDecimal id) {
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

    public List<HrCheckupDutyTitleDt> getHrCheckupDutyTitleDtList() {
        return hrCheckupDutyTitleDtList;
    }

    public void setHrCheckupDutyTitleDtList(List<HrCheckupDutyTitleDt> hrCheckupDutyTitleDtList) {
        this.hrCheckupDutyTitleDtList = hrCheckupDutyTitleDtList;
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
        if (!(object instanceof HrCheckupDutyTitleHd)) {
            return false;
        }
        HrCheckupDutyTitleHd other = (HrCheckupDutyTitleHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyTitleHd[id=" + id + "]";
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DEV
 */
@Entity
@Table(name = "HR_LOCATION_INVEST_SETTING", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrLocationInvestSetting.findAll", query = "SELECT h FROM HrLocationInvestSetting h"),
    @NamedQuery(name = "HrLocationInvestSetting.findMaxId", query = "SELECT max(h.id) FROM HrLocationInvestSetting h"),
    @NamedQuery(name = "HrLocationInvestSetting.findById", query = "SELECT h FROM HrLocationInvestSetting h WHERE h.id = :id"),
    @NamedQuery(name = "HrLocationInvestSetting.findByLocId", query = "SELECT h FROM HrLocationInvestSetting h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrLocationInvestSetting.findByGrpId", query = "SELECT h FROM HrLocationInvestSetting h WHERE h.grpId = :grpId"),
    @NamedQuery(name = "HrLocationInvestSetting.findByJobId", query = "SELECT h FROM HrLocationInvestSetting h WHERE h.jobId = :jobId"),
    @NamedQuery(name = "HrLocationInvestSetting.findByDeptId", query = "SELECT h FROM HrLocationInvestSetting h WHERE h.deptId = :deptId"),
    @NamedQuery(name = "HrLocationInvestSetting.findByEmpId", query = "SELECT h FROM HrLocationInvestSetting h WHERE h.empId = :empId")})
public class HrLocationInvestSetting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "LOC_ID",referencedColumnName="ID")
    @ManyToOne
    private HrLocation locId;
    @JoinColumn(name = "GRP_ID",referencedColumnName="ID")
    @ManyToOne
    private HrJobGrp grpId;
    @JoinColumn(name = "JOB_ID",referencedColumnName="ID")
    @ManyToOne
    private HrJobs jobId;
    @JoinColumn(name = "DEPT_ID",referencedColumnName="ID")
    @ManyToOne
    private HrManagement deptId;
    @JoinColumn(name = "EMP_ID",referencedColumnName="EMP_NO")
    @ManyToOne
    private HrEmpInfo empId;

    public HrLocationInvestSetting() {
    }

    public HrManagement getDeptId() {
        return deptId;
    }

    public void setDeptId(HrManagement deptId) {
        this.deptId = deptId;
    }

    public HrEmpInfo getEmpId() {
        return empId;
    }

    public void setEmpId(HrEmpInfo empId) {
        this.empId = empId;
    }

    public HrJobGrp getGrpId() {
        return grpId;
    }

    public void setGrpId(HrJobGrp grpId) {
        this.grpId = grpId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrJobs getJobId() {
        return jobId;
    }

    public void setJobId(HrJobs jobId) {
        this.jobId = jobId;
    }

    public HrLocation getLocId() {
        return locId;
    }

    public void setLocId(HrLocation locId) {
        this.locId = locId;
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
        if (!(object instanceof HrLocationInvestSetting)) {
            return false;
        }
        HrLocationInvestSetting other = (HrLocationInvestSetting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrLocationInvestSetting[id=" + id + "]";
    }

}

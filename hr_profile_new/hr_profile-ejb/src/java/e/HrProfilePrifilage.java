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
 * @author a.abbas
 */
@Entity
@Table(name = "HR_PROFILE_PRIFILAGE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrProfilePrifilage.findAll", query = "SELECT h FROM HrProfilePrifilage h LEFT JOIN h.grpId g where h.newMenuId.parentId is not null"
            + " and ((h.grpId.id is not null and h.grpId.id=:grp_id) or :grp_id is null) and (:loc_id is null and :dept_id is null and :loc_id is null and :emp_no is null and :job_id  is null and :menu_id is null) "
//            + " and ((h.jobId.id is not null and h.jobId.id=:job_id) or :job_id is null) and ((h.deptId.id is not null and h.deptId.id=:dept_id) or :dept_id is null)"
//            + " and ((h.empId.empNo is not null and h.empId.empNo=:emp_no) or :emp_no is null) and (h.newMenuId.id=:menu_id or :menu_id is null)"
            ),
    @NamedQuery(name = "HrProfilePrifilage.findMaxId", query = "SELECT max(h.id) FROM HrProfilePrifilage h"),
    @NamedQuery(name = "HrProfilePrifilage.findByLocId", query = "SELECT h FROM HrProfilePrifilage h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrProfilePrifilage.findByGrpId", query = "SELECT h FROM HrProfilePrifilage h WHERE h.grpId = :grpId"),
    @NamedQuery(name = "HrProfilePrifilage.findByJobId", query = "SELECT h FROM HrProfilePrifilage h WHERE h.jobId = :jobId"),
    @NamedQuery(name = "HrProfilePrifilage.findByDeptId", query = "SELECT h FROM HrProfilePrifilage h WHERE h.deptId = :deptId"),
    @NamedQuery(name = "HrProfilePrifilage.findByEmpId", query = "SELECT h FROM HrProfilePrifilage h WHERE h.empId = :empId"),
    @NamedQuery(name = "HrProfilePrifilage.findByNewMenuId", query = "SELECT h FROM HrProfilePrifilage h WHERE h.newMenuId = :newMenuId")})
public class HrProfilePrifilage implements Serializable {
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
    @JoinColumn(name = "NEW_MENU_ID",referencedColumnName="ID")
    @ManyToOne
    private HrMenuTable newMenuId;

    public HrProfilePrifilage() {
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

    public HrMenuTable getNewMenuId() {
        return newMenuId;
    }

    public void setNewMenuId(HrMenuTable newMenuId) {
        this.newMenuId = newMenuId;
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
        if (!(object instanceof HrProfilePrifilage)) {
            return false;
        }
        HrProfilePrifilage other = (HrProfilePrifilage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrProfilePrifilage[id=" + id + "]";
    }

}

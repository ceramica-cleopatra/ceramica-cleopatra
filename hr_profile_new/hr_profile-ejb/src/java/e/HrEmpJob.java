/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_EMP_JOB", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpJob.findAllById", query = "select l.name,m.name,j.name,jg.name,d.name,o.trnsDate,j.id from HrEmpJob o LEFT JOIN o.hrLocation l LEFT JOIN o.hrJobs j LEFT JOIN o.hrDepartment d LEFT JOIN o.hrManagement m LEFT JOIN o.hrJobGrp jg where o.hdId=:emp_id"),
    @NamedQuery(name = "HrEmpJob.findAll", query = "SELECT h FROM HrEmpJob h"),
    @NamedQuery(name = "HrEmpJob.findById", query = "SELECT h FROM HrEmpJob h WHERE h.id = :id"),
    @NamedQuery(name = "HrEmpJob.findByTrnsDate", query = "SELECT h FROM HrEmpJob h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrEmpJob.findByNotes", query = "SELECT h FROM HrEmpJob h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrEmpJob.findByUserName", query = "SELECT h FROM HrEmpJob h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrEmpJob.findByMachineName", query = "SELECT h FROM HrEmpJob h WHERE h.machineName = :machineName"),
    @NamedQuery(name = "HrEmpJob.findByCreateBy", query = "SELECT h FROM HrEmpJob h WHERE h.createBy = :createBy"),
    @NamedQuery(name = "HrEmpJob.findByCreateAt", query = "SELECT h FROM HrEmpJob h WHERE h.createAt = :createAt"),
    @NamedQuery(name = "HrEmpJob.findByModifyBy", query = "SELECT h FROM HrEmpJob h WHERE h.modifyBy = :modifyBy"),
    @NamedQuery(name = "HrEmpJob.findByModifyAt", query = "SELECT h FROM HrEmpJob h WHERE h.modifyAt = :modifyAt")})
public class HrEmpJob implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name="HD_ID", unique = true)
    private Long hdId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @ManyToOne
    @JoinColumn(name="LOC_ID", unique = true)
    private HrLocation hrLocation;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MACHINE_NAME")
    private String machineName;
    @Column(name = "CREATE_BY")
    private BigInteger createBy;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "MODIFY_BY")
    private BigInteger modifyBy;
    @Column(name = "MODIFY_AT")
    @Temporal(TemporalType.DATE)
    private Date modifyAt;
    @JoinColumn(name = "REG_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrRegion hrRegion;
    @JoinColumn(name = "MANAG_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrManagement hrManagement;
    @JoinColumn(name = "JOB_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrJobs hrJobs;
    @JoinColumn(name = "GRP_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrJobGrp hrJobGrp;
    @JoinColumn(name = "DEPT_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrDepartment hrDepartment;


    public HrEmpJob() {
    }

    public HrEmpJob(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public Long getHdId() {
        return hdId;
    }

    public void setHdId(Long hdId) {
        this.hdId = hdId;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public HrLocation getHrLocation() {
        return hrLocation;
    }

    public void setHrLocation(HrLocation hrLocation) {
        this.hrLocation = hrLocation;
    }

 

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public BigInteger getCreateBy() {
        return createBy;
    }

    public void setCreateBy(BigInteger createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public BigInteger getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(BigInteger modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public HrRegion getHrRegion() {
        return hrRegion;
    }

    public void setHrRegion(HrRegion hrRegion) {
        this.hrRegion = hrRegion;
    }

    public HrManagement getHrManagement() {
        return hrManagement;
    }

    public void setHrManagement(HrManagement hrManagement) {
        this.hrManagement = hrManagement;
    }

    public HrJobs getHrJobs() {
        return hrJobs;
    }

    public void setHrJobs(HrJobs hrJobs) {
        this.hrJobs = hrJobs;
    }

    public HrJobGrp getHrJobGrp() {
        return hrJobGrp;
    }

    public void setHrJobGrp(HrJobGrp hrJobGrp) {
        this.hrJobGrp = hrJobGrp;
    }


    public HrDepartment getHrDepartment() {
        return hrDepartment;
    }

    public void setHrDepartment(HrDepartment hrDepartment) {
        this.hrDepartment = hrDepartment;
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
        if (!(object instanceof HrEmpJob)) {
            return false;
        }
        HrEmpJob other = (HrEmpJob) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrEmpJob[id=" + id + "]";
    }

}

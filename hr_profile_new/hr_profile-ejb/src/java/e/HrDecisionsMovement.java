/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
 * @author Administrator
 */
@Entity
@Table(name = "HR_DECISIONS_MOVEMENT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDecisionsMovement.findAll", query = "SELECT h FROM HrDecisionsMovement h"),
    @NamedQuery(name = "HrDecisionsMovement.findById", query = "SELECT h FROM HrDecisionsMovement h WHERE h.id = :id"),
    @NamedQuery(name = "HrDecisionsMovement.findByFromJob", query = "SELECT h FROM HrDecisionsMovement h WHERE h.fromJob = :fromJob"),
    @NamedQuery(name = "HrDecisionsMovement.findByToJob", query = "SELECT h FROM HrDecisionsMovement h WHERE h.toJob = :toJob")})
public class HrDecisionsMovement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrMangaerialDecisions hrMangaerialDecisions;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_NO")
    @ManyToOne
    private HrEmpInfo hrEmpInfo;
    @JoinColumn(name = "FROM_LOCATION", referencedColumnName = "ID")
    @ManyToOne
    private HrLocation fromLocation;
    @JoinColumn(name = "TO_LOCATION", referencedColumnName = "ID")
    @ManyToOne
    private HrLocation toLocation;
    @JoinColumn(name = "FROM_JOB", referencedColumnName = "ID")
    @ManyToOne
    private HrJobs fromJob;
    @JoinColumn(name = "TO_JOB", referencedColumnName = "ID")
    @ManyToOne
    private HrJobs toJob;
    @JoinColumn(name = "TO_DEPT", referencedColumnName = "ID")
    @ManyToOne
    private HrManagement toDept;
    @JoinColumn(name = "TO_GRP", referencedColumnName = "ID")
    @ManyToOne
    private HrJobGrp toGrp;
    @JoinColumn(name = "FROM_DEPT", referencedColumnName = "ID")
    @ManyToOne
    private HrManagement fromDept;
    @JoinColumn(name = "FROM_GRP", referencedColumnName = "ID")
    @ManyToOne
    private HrJobGrp fromGrp;

    public HrDecisionsMovement() {
    }

    public HrJobs getFromJob() {
        return fromJob;
    }

    public void setFromJob(HrJobs fromJob) {
        this.fromJob = fromJob;
    }

    public HrLocation getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(HrLocation fromLocation) {
        this.fromLocation = fromLocation;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

    public HrMangaerialDecisions getHrMangaerialDecisions() {
        return hrMangaerialDecisions;
    }

    public void setHrMangaerialDecisions(HrMangaerialDecisions hrMangaerialDecisions) {
        this.hrMangaerialDecisions = hrMangaerialDecisions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrJobs getToJob() {
        return toJob;
    }

    public void setToJob(HrJobs toJob) {
        this.toJob = toJob;
    }

    public HrLocation getToLocation() {
        return toLocation;
    }

    public void setToLocation(HrLocation toLocation) {
        this.toLocation = toLocation;
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
        if (!(object instanceof HrDecisionsMovement)) {
            return false;
        }
        HrDecisionsMovement other = (HrDecisionsMovement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDecisionsMovement[id=" + id + "]";
    }

    public HrManagement getToDept() {
        return toDept;
    }

    public void setToDept(HrManagement toDept) {
        this.toDept = toDept;
    }

    public HrJobGrp getToGrp() {
        return toGrp;
    }

    public void setToGrp(HrJobGrp toGrp) {
        this.toGrp = toGrp;
    }

    public HrManagement getFromDept() {
        return fromDept;
    }

    public void setFromDept(HrManagement fromDept) {
        this.fromDept = fromDept;
    }

    public HrJobGrp getFromGrp() {
        return fromGrp;
    }

    public void setFromGrp(HrJobGrp fromGrp) {
        this.fromGrp = fromGrp;
    }
}

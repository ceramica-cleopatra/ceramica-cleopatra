/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_PROFILE_ALERT_RECEIVER", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrProfileAlertReceiver.findAll", query = "SELECT h FROM HrProfileAlertReceiver h"),
    @NamedQuery(name = "HrProfileAlertReceiver.findById", query = "SELECT h FROM HrProfileAlertReceiver h WHERE h.id = :id"),
    @NamedQuery(name = "HrProfileAlertReceiver.findByDeptId", query = "SELECT h FROM HrProfileAlertReceiver h WHERE h.deptId = :deptId"),
    @NamedQuery(name = "HrProfileAlertReceiver.findByJobId", query = "SELECT h FROM HrProfileAlertReceiver h WHERE h.jobId = :jobId"),
    @NamedQuery(name = "HrProfileAlertReceiver.findByLocId", query = "SELECT h FROM HrProfileAlertReceiver h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrProfileAlertReceiver.findByJobGrpId", query = "SELECT h FROM HrProfileAlertReceiver h WHERE h.jobGrpId = :jobGrpId")})
public class HrProfileAlertReceiver implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(schema="HR",name="HR_PROFILE_ALERT_RECEIVER_SEQ",sequenceName="HR_PROFILE_ALERT_RECEIVER_SEQ", initialValue=1, allocationSize=1)
    @GeneratedValue(generator="HR_PROFILE_ALERT_RECEIVER_SEQ",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "DEPT_ID")
    private HrManagement deptId;
    @JoinColumn(name = "JOB_ID")
    private HrJobs jobId;
    @JoinColumn(name = "LOC_ID")
    private HrLocation locId;
    @JoinColumn(name = "JOB_GRP_ID")
    private HrJobGrp jobGrpId;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrProfileAlertHd hdId;

    public HrProfileAlertReceiver() {
    }

    public HrProfileAlertReceiver(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public HrManagement getDeptId() {
        return deptId;
    }

    public void setDeptId(HrManagement deptId) {
        this.deptId = deptId;
    }

    public HrJobGrp getJobGrpId() {
        return jobGrpId;
    }

    public void setJobGrpId(HrJobGrp jobGrpId) {
        this.jobGrpId = jobGrpId;
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

   
    public HrProfileAlertHd getHdId() {
        return hdId;
    }

    public void setHdId(HrProfileAlertHd hdId) {
        this.hdId = hdId;
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
        if (!(object instanceof HrProfileAlertReceiver)) {
            return false;
        }
        HrProfileAlertReceiver other = (HrProfileAlertReceiver) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrProfileAlertReceiver[id=" + id + "]";
    }

}

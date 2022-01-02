/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
 * @author data
 */
@Entity
@Table(name = "HR_DUTY_TRNS_HD_VU", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDutyTrnsHdVu.findAll", query = "SELECT h FROM HrDutyTrnsHdVu h"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findNeedToApprove", query = "SELECT h FROM HrDutyTrnsHdVu h where h.empNo<>:mngNo and h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job order by h.trnsDate asc"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findById", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.id = :id"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findByEmpNo", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findByEmpName", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findByTrnsDate", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findByJobName", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findByLocation", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.location = :location"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findByMngDept", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.mngDept = :mngDept"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findByMngLoc", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.mngLoc = :mngLoc"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findByMngGrp", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.mngGrp = :mngGrp"),
    @NamedQuery(name = "HrDutyTrnsHdVu.findByMngJob", query = "SELECT h FROM HrDutyTrnsHdVu h WHERE h.mngJob = :mngJob")})
public class HrDutyTrnsHdVu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "MNG_DEPT")
    private BigInteger mngDept;
    @Column(name = "MNG_LOC")
    private BigInteger mngLoc;
    @Column(name = "MNG_GRP")
    private BigInteger mngGrp;
    @Column(name = "MNG_JOB")
    private BigInteger mngJob;
    @OneToMany(mappedBy = "hrDutyTrnsHdVu")
    private List<HrDutyTrnsDt> hrDutyTrnsDtList;

    public HrDutyTrnsHdVu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigInteger getMngDept() {
        return mngDept;
    }

    public void setMngDept(BigInteger mngDept) {
        this.mngDept = mngDept;
    }

    public BigInteger getMngLoc() {
        return mngLoc;
    }

    public void setMngLoc(BigInteger mngLoc) {
        this.mngLoc = mngLoc;
    }

    public BigInteger getMngGrp() {
        return mngGrp;
    }

    public void setMngGrp(BigInteger mngGrp) {
        this.mngGrp = mngGrp;
    }

    public BigInteger getMngJob() {
        return mngJob;
    }

    public void setMngJob(BigInteger mngJob) {
        this.mngJob = mngJob;
    }

    public List<HrDutyTrnsDt> getHrDutyTrnsDtList() {
        return hrDutyTrnsDtList;
    }

    public void setHrDutyTrnsDtList(List<HrDutyTrnsDt> hrDutyTrnsDtList) {
        this.hrDutyTrnsDtList = hrDutyTrnsDtList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HrDutyTrnsHdVu other = (HrDutyTrnsHdVu) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public HrDutyTrnsHdVu(Long id) {
        this.id = id;
    }

}

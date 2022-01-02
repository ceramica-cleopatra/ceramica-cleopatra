/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_DUTY_EMP_MNG_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDutyEmpMngDt.findAll", query = "SELECT h FROM HrGzaEmpMngDt h where h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job and (h.empName=:emp_name or :emp_name is null)"),
    @NamedQuery(name = "HrDutyEmpMngDt.findByEmpSubstr", query = "SELECT h.empName FROM HrGzaEmpMngDt h where h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job and h.empName like :emp_name"),
    @NamedQuery(name = "HrDutyEmpMngDt.findById", query = "SELECT h FROM HrDutyEmpMngDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrDutyEmpMngDt.findByEmpNo", query = "SELECT h FROM HrDutyEmpMngDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrDutyEmpMngDt.findByEmpName", query = "SELECT h FROM HrDutyEmpMngDt h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrDutyEmpMngDt.findByJobName", query = "SELECT h FROM HrDutyEmpMngDt h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrDutyEmpMngDt.findByDeptName", query = "SELECT h FROM HrDutyEmpMngDt h WHERE h.deptName = :deptName"),
    @NamedQuery(name = "HrDutyEmpMngDt.findByMngDept", query = "SELECT h FROM HrDutyEmpMngDt h WHERE h.mngDept = :mngDept"),
    @NamedQuery(name = "HrDutyEmpMngDt.findByMngLoc", query = "SELECT h FROM HrDutyEmpMngDt h WHERE h.mngLoc = :mngLoc"),
    @NamedQuery(name = "HrDutyEmpMngDt.findByMngGrp", query = "SELECT h FROM HrDutyEmpMngDt h WHERE h.mngGrp = :mngGrp"),
    @NamedQuery(name = "HrDutyEmpMngDt.findByMngJob", query = "SELECT h FROM HrDutyEmpMngDt h WHERE h.mngJob = :mngJob")})
public class HrDutyEmpMngDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private BigInteger id;
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private long empNo;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "MNG_DEPT")
    private BigInteger mngDept;
    @Column(name = "MNG_LOC")
    private BigInteger mngLoc;
    @Column(name = "MNG_GRP")
    private BigInteger mngGrp;
    @Column(name = "MNG_JOB")
    private BigInteger mngJob;

    public HrDutyEmpMngDt() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(long empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

}

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_GZA_EMP_MNG_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrGzaEmpMngDt.findAll", query = "SELECT h FROM HrGzaEmpMngDt h where h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job and (h.empName=:emp_name or :emp_name is null)"),
    @NamedQuery(name = "HrGzaEmpMngDt.findAllEmpUnderMng", query = "SELECT h FROM HrGzaEmpMngDt h where h.mngNo=:mng_no and (h.empName=:emp_name or :emp_name is null)"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByEmpSubstr", query = "SELECT h.empName FROM HrGzaEmpMngDt h where h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job and h.empName like :emp_name"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByEmpSubstrAndMngNo", query = "SELECT h.empName FROM HrGzaEmpMngDt h where h.mngNo=:mng_no and h.empName like :emp_name"),
    @NamedQuery(name = "HrGzaEmpMngDt.findById", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByEmpNo", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByEmpName", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByTotSal", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.totSal = :totSal"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByJobName", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByDeptName", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.deptName = :deptName"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByMngDept", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.mngDept = :mngDept"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByMngLoc", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.mngLoc = :mngLoc"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByMngGrp", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.mngGrp = :mngGrp"),
    @NamedQuery(name = "HrGzaEmpMngDt.findByMngJob", query = "SELECT h FROM HrGzaEmpMngDt h WHERE h.mngJob = :mngJob")})
     @NamedQuery(name = "HrGzaEmpMngDt.findAllGiza", query = "SELECT h FROM HrGzaEmpMngDt h")
public class HrGzaEmpMngDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private long empNo;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "TOT_SAL")
    private Long totSal;
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "MNG_DEPT")
    private Long mngDept;
    @Column(name = "MNG_LOC")
    private Long mngLoc;
    @Column(name = "MNG_GRP")
    private Long mngGrp;
    @Column(name = "MNG_JOB")
    private Long mngJob;
    @Column(name = "MNG_NO")
    private Long mngNo;
    @Column(name = "MNG_NAME")
    private String mngName;
    public HrGzaEmpMngDt() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMngDept() {
        return mngDept;
    }

    public void setMngDept(Long mngDept) {
        this.mngDept = mngDept;
    }

    public Long getMngGrp() {
        return mngGrp;
    }

    public void setMngGrp(Long mngGrp) {
        this.mngGrp = mngGrp;
    }

    public Long getMngJob() {
        return mngJob;
    }

    public void setMngJob(Long mngJob) {
        this.mngJob = mngJob;
    }

    public Long getMngLoc() {
        return mngLoc;
    }

    public void setMngLoc(Long mngLoc) {
        this.mngLoc = mngLoc;
    }

    public Long getTotSal() {
        return totSal;
    }

    public void setTotSal(Long totSal) {
        this.totSal = totSal;
    }

    public String getMngName() {
        return mngName;
    }

    public void setMngName(String mngName) {
        this.mngName = mngName;
    }

   

    public Long getMngNo() {
        return mngNo;
    }

    public void setMngNo(Long mngNo) {
        this.mngNo = mngNo;
    }

 
 

}

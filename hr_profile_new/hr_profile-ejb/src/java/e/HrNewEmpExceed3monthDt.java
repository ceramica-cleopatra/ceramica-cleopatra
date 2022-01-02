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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_NEW_EMP_EXCEED_3MONTH_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findAll", query = "SELECT h FROM HrNewEmpExceed3monthDt h  where h.empNo<>:mng and h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job order by h.empNo"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findById", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByEmpNo", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByEmpName", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByJobName", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByMngDept", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.mngDept = :mngDept"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByMngLoc", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.mngLoc = :mngLoc"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByMngGrp", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.mngGrp = :mngGrp"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByMngJob", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.mngJob = :mngJob"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByBasicSalary", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.basicSalary = :basicSalary"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByVarSalary", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.varSalary = :varSalary"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByTotalSalary", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.totalSalary = :totalSalary"),
    @NamedQuery(name = "HrNewEmpExceed3monthDt.findByRowId", query = "SELECT h FROM HrNewEmpExceed3monthDt h WHERE h.rowId = :rowId")})
public class HrNewEmpExceed3monthDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ID")
    @Id
    private Long id;
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private long empNo;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "MNG_DEPT")
    private Long mngDept;
    @Column(name = "MNG_LOC")
    private Long mngLoc;
    @Column(name = "MNG_GRP")
    private Long mngGrp;
    @Column(name = "MNG_JOB")
    private Long mngJob;
    @Column(name = "BASIC_SALARY")
    private Long basicSalary;
    @Column(name = "VAR_SALARY")
    private Long varSalary;
    @Column(name = "TOTAL_SALARY")
    private Long totalSalary;
    @Column(name = "HIRE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date hireDate;
    @Column(name = "APPROVED")
    private Character approved;
    @Basic(optional = false)
    @Column(name = "ROW_ID")
    private Long rowId;

    public HrNewEmpExceed3monthDt() {
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

    public Long getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Long basicSalary) {
        this.basicSalary = basicSalary;
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

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Long getVarSalary() {
        return varSalary;
    }

    public void setVarSalary(Long varSalary) {
        this.varSalary = varSalary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Character getApproved() {
        return approved;
    }

    public void setApproved(Character approved) {
        this.approved = approved;
    }

    
  
}

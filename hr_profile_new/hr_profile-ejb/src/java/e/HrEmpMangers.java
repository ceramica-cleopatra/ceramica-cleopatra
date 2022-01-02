/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collections;
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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_EMP_MANGERS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpMangers.findEmpByEmpSubstr", query = "SELECT h.empName FROM HrEmpMangers h where h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job and h.empName like :emp_name"),
    @NamedQuery(name = "HrEmpMangers.findMngEmp", query = "SELECT h FROM HrEmpMangers h where h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job order by h.location,h.jobName"),
    @NamedQuery(name = "HrEmpMangers.findById", query = "SELECT h FROM HrEmpMangers h WHERE h.id = :id"),
    @NamedQuery(name = "HrEmpMangers.findAll", query = "SELECT h FROM HrEmpMangers h order by h.location,h.jobName"),
    @NamedQuery(name = "HrEmpMangers.findByEmpNo", query = "SELECT h FROM HrEmpMangers h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrEmpMangers.findByEmpName", query = "SELECT h FROM HrEmpMangers h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrEmpMangers.findByJobName", query = "SELECT h FROM HrEmpMangers h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrEmpMangers.findByMngDept", query = "SELECT h FROM HrEmpMangers h WHERE h.mngDept = :mngDept"),
    @NamedQuery(name = "HrEmpMangers.findByMngLoc", query = "SELECT h FROM HrEmpMangers h WHERE h.mngLoc = :mngLoc"),
    @NamedQuery(name = "HrEmpMangers.findByMngGrp", query = "SELECT h FROM HrEmpMangers h WHERE h.mngGrp = :mngGrp"),
    @NamedQuery(name = "HrEmpMangers.findByMngJob", query = "SELECT h FROM HrEmpMangers h WHERE h.mngJob = :mngJob")})
public class HrEmpMangers implements Serializable {
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
    @Column(name = "MNG_NO")
    private Long mngNo;
    @Column(name = "MNG_NAME")
    private String mngName;
    @OneToMany(mappedBy = "hdId" )
    private List<HrEmpTime> hrEmpTimeList;
    @OneToMany(mappedBy = "empNo")
    private List<NextHrEmpTime> nextHrEmpTimeList;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    
    public List<NextHrEmpTime> getNextHrEmpTimeList()
    {
        Collections.sort(nextHrEmpTimeList, new hrEmpTimeComparator());
        return nextHrEmpTimeList;
    }

    public void setNextHrEmpTimeList(List<NextHrEmpTime> nextHrEmpTimeList) {
        this.nextHrEmpTimeList = nextHrEmpTimeList;
    }


    public List<HrEmpTime> getHrEmpTimeList() { 
        return hrEmpTimeList;
    }

    public void setHrEmpTimeList(List<HrEmpTime> hrEmpTimeList) {
        this.hrEmpTimeList = hrEmpTimeList;
    }


    
    public HrEmpMangers() {
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

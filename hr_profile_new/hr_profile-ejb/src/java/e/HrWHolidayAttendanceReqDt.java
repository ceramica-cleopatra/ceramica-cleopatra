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
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_W_HOLIDAY_ATTENDANCE_REQ_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findAll", query = "SELECT h FROM HrWHolidayAttendanceReqDt h where (h.mngNo=:mng or h.mngNo is null) and h.empNo<>:mng and h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findById", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByEmpNo", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByEmpName", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByJobName", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByMngDept", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.mngDept = :mngDept"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByMngLoc", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.mngLoc = :mngLoc"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByMngGrp", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.mngGrp = :mngGrp"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByMngJob", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.mngJob = :mngJob"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByWeeklyHolidayDate", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.weeklyHolidayDate = :weeklyHolidayDate"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByReqId", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.reqId = :reqId"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByMngNo", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByApproved", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.approved = :approved"),
    @NamedQuery(name = "HrWHolidayAttendanceReqDt.findByTrnsDate", query = "SELECT h FROM HrWHolidayAttendanceReqDt h WHERE h.trnsDate = :trnsDate")})
public class HrWHolidayAttendanceReqDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
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
    @Column(name = "WEEKLY_HOLIDAY_DATE")
    @Temporal(TemporalType.DATE)
    private Date weeklyHolidayDate;
    @Basic(optional = false)
    @Column(name = "REQ_ID")
    private Long reqId;
    @Column(name = "MNG_NO")
    private Long mngNo;
    @Column(name = "APPROVED")
    private String approved;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;

    public HrWHolidayAttendanceReqDt() {
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

    public Date getWeeklyHolidayDate() {
        return weeklyHolidayDate;
    }

    public void setWeeklyHolidayDate(Date weeklyHolidayDate) {
        this.weeklyHolidayDate = weeklyHolidayDate;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
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

    public Long getMngNo() {
        return mngNo;
    }

    public void setMngNo(Long mngNo) {
        this.mngNo = mngNo;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

 
    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

}

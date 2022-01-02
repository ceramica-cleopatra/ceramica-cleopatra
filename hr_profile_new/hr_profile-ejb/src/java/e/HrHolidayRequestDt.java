/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_HOLIDAY_REQUEST_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHolidayRequestDt.findAll", query = "SELECT h FROM HrHolidayRequestDt h where (h.mngNo=:mng or h.mngNo is null) and h.empNo<>:mng and h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job order by h.empNo"),
    @NamedQuery(name = "HrHolidayRequestDt.chkHolidaysNotConfirmed", query = "SELECT count(h.id) FROM HrHolidayRequestDt h WHERE h.empNo<>:mng and h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job and h.mngConfirm is null"),
    @NamedQuery(name = "HrHolidayRequestDt.findByEmpNo", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrHolidayRequestDt.findByEmpName", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrHolidayRequestDt.findByJobName", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrHolidayRequestDt.findByMngDept", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.mngDept = :mngDept"),
    @NamedQuery(name = "HrHolidayRequestDt.findByMngLoc", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.mngLoc = :mngLoc"),
    @NamedQuery(name = "HrHolidayRequestDt.findByMngGrp", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.mngGrp = :mngGrp"),
    @NamedQuery(name = "HrHolidayRequestDt.findByMngJob", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.mngJob = :mngJob"),
    @NamedQuery(name = "HrHolidayRequestDt.findByReqId", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.reqId = :reqId"),
    @NamedQuery(name = "HrHolidayRequestDt.findByFromDate", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.fromDate = :fromDate"),
    @NamedQuery(name = "HrHolidayRequestDt.findByToDate", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.toDate = :toDate"),
    @NamedQuery(name = "HrHolidayRequestDt.findByDaysNo", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.daysNo = :daysNo"),
    @NamedQuery(name = "HrHolidayRequestDt.findByHolidayType", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.holidayType = :holidayType"),
    @NamedQuery(name = "HrHolidayRequestDt.findByMngConfirm", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.mngConfirm = :mngConfirm"),
    @NamedQuery(name = "HrHolidayRequestDt.findByMngNo", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrHolidayRequestDt.findByRejectDesc", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.rejectDesc = :rejectDesc"),
    @NamedQuery(name = "HrHolidayRequestDt.findByRequestDesc", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.requestDesc = :requestDesc"),
    @NamedQuery(name = "HrHolidayRequestDt.findByHolidayName", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.holidayName = :holidayName"),
    @NamedQuery(name = "HrHolidayRequestDt.findByAltEmpName", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.altEmpName = :altEmpName"),
    @NamedQuery(name = "HrHolidayRequestDt.findByAltEmpNo", query = "SELECT h FROM HrHolidayRequestDt h WHERE h.altEmpNo = :altEmpNo")})
public class HrHolidayRequestDt implements Serializable {
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
    @Basic(optional = false)
    @Column(name = "REQ_ID")
    private Long reqId;
    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Column(name = "DAYS_NO")
    private Long daysNo;
    @Column(name = "HOLIDAY_TYPE")
    private Long holidayType;
    @Column(name = "MNG_CONFIRM")
    private Character mngConfirm;
    @Column(name = "MNG_NO")
    private Long mngNo;
    @Column(name = "REJECT_DESC")
    private String rejectDesc;
    @Column(name = "REQUEST_DESC")
    private String requestDesc;
    @Column(name = "HOLIDAY_NAME")
    private String holidayName;
    @Column(name = "ALT_EMP_NAME")
    private String altEmpName;
    @Basic(optional = false)
    @Column(name = "ALT_EMP_NO")
    private long altEmpNo;
    @Column(name = "HOLIDAY_BALANCE")
    private long holidayBalance;

    public HrHolidayRequestDt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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



    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getDaysNo() {
        return daysNo;
    }

    public void setDaysNo(Long daysNo) {
        this.daysNo = daysNo;
    }

    public Long getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(Long holidayType) {
        this.holidayType = holidayType;
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

   

    public Character getMngConfirm() {
        return mngConfirm;
    }

    public void setMngConfirm(Character mngConfirm) {
        this.mngConfirm = mngConfirm;
    }

  

    public String getRejectDesc() {
        return rejectDesc;
    }

    public void setRejectDesc(String rejectDesc) {
        this.rejectDesc = rejectDesc;
    }

    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getAltEmpName() {
        return altEmpName;
    }

    public void setAltEmpName(String altEmpName) {
        this.altEmpName = altEmpName;
    }

    public long getAltEmpNo() {
        return altEmpNo;
    }

    public void setAltEmpNo(long altEmpNo) {
        this.altEmpNo = altEmpNo;
    }

    public long getHolidayBalance() {
        return holidayBalance;
    }

    public void setHolidayBalance(long holidayBalance) {
        this.holidayBalance = holidayBalance;
    }

    
}

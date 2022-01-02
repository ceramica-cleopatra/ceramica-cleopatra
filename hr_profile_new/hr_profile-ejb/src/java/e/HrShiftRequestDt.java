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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_SHIFT_REQUEST_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrShiftRequestDt.findAll", query = "SELECT h FROM HrShiftRequestDt h where (h.mngNo=:mng or h.mngNo is null) and h.empNo<>:mng and h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job"),
    @NamedQuery(name = "HrShiftRequestDt.chkShiftNotConfirmed", query = "SELECT count(h.id) FROM HrShiftRequestDt h where h.empNo<>:mng and h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job and h.mngConfirm is null"),
    @NamedQuery(name = "HrShiftRequestDt.findById", query = "SELECT h FROM HrShiftRequestDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrShiftRequestDt.findByEmpNo", query = "SELECT h FROM HrShiftRequestDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrShiftRequestDt.findByEmpName", query = "SELECT h FROM HrShiftRequestDt h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrShiftRequestDt.findByJobName", query = "SELECT h FROM HrShiftRequestDt h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrShiftRequestDt.findByMngDept", query = "SELECT h FROM HrShiftRequestDt h WHERE h.mngDept = :mngDept"),
    @NamedQuery(name = "HrShiftRequestDt.findByMngLoc", query = "SELECT h FROM HrShiftRequestDt h WHERE h.mngLoc = :mngLoc"),
    @NamedQuery(name = "HrShiftRequestDt.findByMngGrp", query = "SELECT h FROM HrShiftRequestDt h WHERE h.mngGrp = :mngGrp"),
    @NamedQuery(name = "HrShiftRequestDt.findByMngJob", query = "SELECT h FROM HrShiftRequestDt h WHERE h.mngJob = :mngJob"),
    @NamedQuery(name = "HrShiftRequestDt.findByShiftDate", query = "SELECT h FROM HrShiftRequestDt h WHERE h.shiftDate = :shiftDate"),
    @NamedQuery(name = "HrShiftRequestDt.findByReqId", query = "SELECT h FROM HrShiftRequestDt h WHERE h.reqId = :reqId"),
    @NamedQuery(name = "HrShiftRequestDt.findByShiftId", query = "SELECT h FROM HrShiftRequestDt h WHERE h.shiftId = :shiftId"),
    @NamedQuery(name = "HrShiftRequestDt.findByMngNo", query = "SELECT h FROM HrShiftRequestDt h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrShiftRequestDt.findByShiftName", query = "SELECT h FROM HrShiftRequestDt h WHERE h.shiftName = :shiftName"),
    @NamedQuery(name = "HrShiftRequestDt.findByTrnsDate", query = "SELECT h FROM HrShiftRequestDt h WHERE h.trnsDate = :trnsDate")})
public class HrShiftRequestDt implements Serializable {
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
    @Column(name = "SHIFT_DATE")
    @Temporal(TemporalType.DATE)
    private Date shiftDate;
    @Basic(optional = false)
    @Column(name = "REQ_ID")
    private Long reqId;
    @Column(name = "SHIFT_ID")
    private Long shiftId;
    @Column(name = "MNG_NO")
    private Long mngNo;
    @Basic(optional = false)
    @Column(name = "MNG_CONFIRM")
    private String mngConfirm;
    @Column(name = "SHIFT_NAME")
    private String shiftName;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name="FRM")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date frm;
    @Column(name="TOO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date too;

    public Date getFrm() {
        return frm;
    }

    public void setFrm(Date frm) {
        this.frm = frm;
    }

    public Date getToo() {
        return too;
    }

    public void setToo(Date too) {
        this.too = too;
    }

    
    public HrShiftRequestDt() {
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

  

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
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

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Long getMngNo() {
        return mngNo;
    }

    public void setMngNo(Long mngNo) {
        this.mngNo = mngNo;
    }

  

    public String getMngConfirm() {
        return mngConfirm;
    }

    public void setMngConfirm(String mngConfirm) {
        this.mngConfirm = mngConfirm;
    }

  

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

}

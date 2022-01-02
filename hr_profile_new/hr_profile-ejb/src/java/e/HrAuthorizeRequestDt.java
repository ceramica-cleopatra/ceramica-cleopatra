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
@Table(name = "HR_AUTHORIZE_REQUEST_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrAuthorizeRequestDt.findAll", query = "SELECT h FROM HrAuthorizeRequestDt h where (h.mngNo=:mng or h.mngNo is null) and h.empNo<>:mng and h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job"),
    @NamedQuery(name = "HrAuthorizeRequestDt.chkAuthorizeNotConfirmed", query = "SELECT count(h.id) FROM HrAuthorizeRequestDt h where h.empNo<>:mng and h.mngDept=:dept and h.mngLoc=:loc and h.mngGrp=:grp and h.mngJob=:job and h.accepted is null"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findById", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByEmpNo", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByEmpName", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByJobName", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByMngDept", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.mngDept = :mngDept"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByMngLoc", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.mngLoc = :mngLoc"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByMngGrp", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.mngGrp = :mngGrp"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByMngJob", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.mngJob = :mngJob"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByAuthorizeDate", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.authorizeDate = :authorizeDate"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByReqId", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.reqId = :reqId"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByMinutesNo", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.minutesNo = :minutesNo"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByMngNo", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByType", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.type = :type"),
    @NamedQuery(name = "HrAuthorizeRequestDt.findByTrnsDate", query = "SELECT h FROM HrAuthorizeRequestDt h WHERE h.trnsDate = :trnsDate")})
public class HrAuthorizeRequestDt implements Serializable {
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
    @Column(name = "AUTHORIZE_DATE")
    @Temporal(TemporalType.DATE)
    private Date authorizeDate;
    @Basic(optional = false)
    @Column(name = "REQ_ID")
    private Long reqId;
    @Column(name = "MINUTES_NO")
    private Long minutesNo;
    @Column(name = "MNG_NO")
    private Long mngNo;
    @Column(name="ACCEPTED")
    private String accepted;
    @Column(name = "TYPE")
    private Long type;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public HrAuthorizeRequestDt() {
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

   


    public Date getAuthorizeDate() {
        return authorizeDate;
    }

    public void setAuthorizeDate(Date authorizeDate) {
        this.authorizeDate = authorizeDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMinutesNo() {
        return minutesNo;
    }

    public void setMinutesNo(Long minutesNo) {
        this.minutesNo = minutesNo;
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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

  

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

}

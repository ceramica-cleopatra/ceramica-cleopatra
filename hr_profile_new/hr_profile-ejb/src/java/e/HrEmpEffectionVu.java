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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "HR_EMP_EFFECTION_DETAILS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpEffectionVu.findAll", query = "SELECT h FROM HrEmpEffectionVu h"),
    @NamedQuery(name = "HrEmpEffectionVu.findAnnualHolidays", query = "SELECT count(h.id) FROM HrEmpEffectionVu h where h.empId=:emp and h.trnsYear=:year and h.holiday='Y' and h.holType=2"),
    @NamedQuery(name = "HrEmpEffectionVu.findOpposeHolidays", query = "SELECT count(h.id) FROM HrEmpEffectionVu h where h.empId=:emp and h.trnsYear=:year and h.holiday='Y' and h.holType=10"),
    @NamedQuery(name = "HrEmpEffectionVu.findNormalHolidays", query = "SELECT count(h.id) FROM HrEmpEffectionVu h where h.empId=:emp and h.trnsYear=:year and h.holiday='Y' and h.holType=1"),
    @NamedQuery(name = "HrEmpEffectionVu.findInsteadHolidays", query = "SELECT count(h.id) FROM HrEmpEffectionVu h where h.empId=:emp and h.trnsYear=:year and h.holiday='Y' and h.holType=6"),
    @NamedQuery(name = "HrEmpEffectionVu.findById", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.id = :id"),
    @NamedQuery(name = "HrEmpEffectionVu.findByEmpId", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.empId = :empId"),
    @NamedQuery(name = "HrEmpEffectionVu.findByTrnsMonth", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrEmpEffectionVu.findByTrnsYear", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrEmpEffectionVu.findByTrnsDate", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrEmpEffectionVu.findByEffectMinuts", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.effectMinuts = :effectMinuts"),
    @NamedQuery(name = "HrEmpEffectionVu.findByEffectDays", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.effectDays = :effectDays"),
    @NamedQuery(name = "HrEmpEffectionVu.findByNotes", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrEmpEffectionVu.findByAuthorizedDay", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.authorizedDay = :authorizedDay"),
    @NamedQuery(name = "HrEmpEffectionVu.findByNotAuthorizedDay", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.notAuthorizedDay = :notAuthorizedDay"),
    @NamedQuery(name = "HrEmpEffectionVu.findByInTrns", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.inTrns = :inTrns"),
    @NamedQuery(name = "HrEmpEffectionVu.findByOutTrns", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.outTrns = :outTrns"),
    @NamedQuery(name = "HrEmpEffectionVu.findByPlusMinuts", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.plusMinuts = :plusMinuts"),
    @NamedQuery(name = "HrEmpEffectionVu.findByPlusDays", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.plusDays = :plusDays"),
    @NamedQuery(name = "HrEmpEffectionVu.findByHoliday", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.holiday = :holiday"),
    @NamedQuery(name = "HrEmpEffectionVu.findByDuty", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.duty = :duty"),
    @NamedQuery(name = "HrEmpEffectionVu.findByAuthorization", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.authorization = :authorization"),
    @NamedQuery(name = "HrEmpEffectionVu.findByHolType", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.holType = :holType"),
    @NamedQuery(name = "HrEmpEffectionVu.findByAuthorizationId", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.authorizationId = :authorizationId"),
    @NamedQuery(name = "HrEmpEffectionVu.findByDutyId", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.dutyId = :dutyId"),
    @NamedQuery(name = "HrEmpEffectionVu.findByManualDtId", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.manualDtId = :manualDtId"),
    @NamedQuery(name = "HrEmpEffectionVu.findByManualHdId", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.manualHdId = :manualHdId"),
    @NamedQuery(name = "HrEmpEffectionVu.findByVacation", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.vacation = :vacation"),
    @NamedQuery(name = "HrEmpEffectionVu.findByWeeklyHoliday", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.weeklyHoliday = :weeklyHoliday"),
    @NamedQuery(name = "HrEmpEffectionVu.findByClosed", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.closed = :closed"),
    @NamedQuery(name = "HrEmpEffectionVu.findByTs", query = "SELECT h FROM HrEmpEffectionVu h WHERE h.ts = :ts"),
    @NamedQuery(name = "HrEmpEffectionVu.chkCard", query ="SELECT count(h.id) FROM HrEmpEffectionVu h WHERE h.empId=:emp and h.trnsDate=:authorize_date"),
    @NamedQuery(name = "HrEmpEffectionVu.findAllById", query = "SELECT o.trnsDate,o.inTrns,o.outTrns,o.effectMinuts,o.effectDays,o.plusMinuts,o.plusDays" +
",o.holiday,o.vacation,o.weeklyHoliday,o.holType,o.authorization,o.ts,o.duty,o.authorizedDay,o.notAuthorizedDay,o.notes,o.hrShift.name,o.hrShift.frm,o.hrShift.too,o.dutyTypeName,o.dutyStart,o.dutyEnd,o.dutyLocationName,o.workDone,o.dutyPunishMinuts,o.dutyPunishDays,o.dutyPlusMinuts,o.dutyPlusDays,o.overtimeApprove FROM HrEmpEffectionVu o where o.trnsDate BETWEEN :date_from AND :date_to  AND o.empId=:emp_id ORDER BY o.trnsDate")})
public class HrEmpEffectionVu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ID")
    @Id
    private BigInteger id;
    @Column(name = "EMP_ID")
    private BigInteger empId;
    @Column(name = "TRNS_MONTH")
    private Long trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Short trnsYear;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "EFFECT_MINUTS")
    private BigInteger effectMinuts;
    @Column(name = "EFFECT_DAYS")
    private Double effectDays;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "AUTHORIZED_DAY")
    private String authorizedDay;
    @Column(name = "NOT_AUTHORIZED_DAY")
    private String notAuthorizedDay;
    @Column(name = "IN_TRNS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inTrns;
    @Column(name = "OUT_TRNS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date outTrns;
    @Column(name = "PLUS_MINUTS")
    private BigInteger plusMinuts;
    @Column(name = "PLUS_DAYS")
    private BigInteger plusDays;
    @Column(name = "HOLIDAY")
    private String holiday;
    @Column(name = "DUTY")
    private String duty;
    @Column(name = "AUTHORIZATION")
    private String authorization;
    @Column(name = "HOL_TYPE")
    private Long holType;
    @Column(name = "AUTHORIZATION_ID")
    private Long authorizationId;
    @Column(name = "DUTY_ID")
    private Long dutyId;
    @Basic(optional = false)
    @Column(name = "MANUAL_DT_ID")
    private long manualDtId;
    @Basic(optional = false)
    @Column(name = "MANUAL_HD_ID")
    private long manualHdId;
    @Column(name = "VACATION")
    private String vacation;
    @Column(name = "WEEKLY_HOLIDAY")
    private String weeklyHoliday;
    @Column(name = "CLOSED")
    private String closed;
    @Column(name = "TS")
    private String ts;
    @Column(name="DUTY_TYPE_NAME")
    private String dutyTypeName;
    @Column(name="DUTY_START")
    private String dutyStart;
    @Column(name="DUTY_END")
    private String dutyEnd;
    @Column(name="DUTY_LOCATION_NAME")
    private String dutyLocationName;
    @Column(name="WORK_DONE")
    private String workDone;
    @Column(name="DUTY_PUNISH_MINUTS")
    private String dutyPunishMinuts;
    @Column(name="DUTY_PUNISH_DAYS")
    private String dutyPunishDays;
    @Column(name="DUTY_PLUS_MINUTS")
    private String dutyPlusMinuts;
    @Column(name="DUTY_PLUS_DAYS")
    private String dutyPlusDays;
    @Column(name="OVERTIME_APPROVE")
    private String overtimeApprove;
    @ManyToOne
    @JoinColumn(name="SHIFT_ID")
    private HrShift hrShift;

    public HrShift getHrShift() {
        return hrShift;
    }

    public void setHrShift(HrShift hrShift) {
        this.hrShift = hrShift;
    }

    

    public HrEmpEffectionVu() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getEmpId() {
        return empId;
    }

    public void setEmpId(BigInteger empId) {
        this.empId = empId;
    }

    public Long getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(Long trnsMonth) {
        this.trnsMonth = trnsMonth;
    }

    public Short getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Short trnsYear) {
        this.trnsYear = trnsYear;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public BigInteger getEffectMinuts() {
        return effectMinuts;
    }

    public void setEffectMinuts(BigInteger effectMinuts) {
        this.effectMinuts = effectMinuts;
    }

    public Double getEffectDays() {
        return effectDays;
    }

    public void setEffectDays(Double effectDays) {
        this.effectDays = effectDays;
    }

  

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAuthorizedDay() {
        return authorizedDay;
    }

    public void setAuthorizedDay(String authorizedDay) {
        this.authorizedDay = authorizedDay;
    }

    public String getNotAuthorizedDay() {
        return notAuthorizedDay;
    }

    public void setNotAuthorizedDay(String notAuthorizedDay) {
        this.notAuthorizedDay = notAuthorizedDay;
    }

    public Date getInTrns() {
        return inTrns;
    }

    public void setInTrns(Date inTrns) {
        this.inTrns = inTrns;
    }

    public Date getOutTrns() {
        return outTrns;
    }

    public void setOutTrns(Date outTrns) {
        this.outTrns = outTrns;
    }

    public BigInteger getPlusMinuts() {
        return plusMinuts;
    }

    public void setPlusMinuts(BigInteger plusMinuts) {
        this.plusMinuts = plusMinuts;
    }

    public BigInteger getPlusDays() {
        return plusDays;
    }

    public void setPlusDays(BigInteger plusDays) {
        this.plusDays = plusDays;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public Long getHolType() {
        return holType;
    }

    public void setHolType(Long holType) {
        this.holType = holType;
    }

    public Long getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(Long authorizationId) {
        this.authorizationId = authorizationId;
    }

    public Long getDutyId() {
        return dutyId;
    }

    public void setDutyId(Long dutyId) {
        this.dutyId = dutyId;
    }

    public long getManualDtId() {
        return manualDtId;
    }

    public void setManualDtId(long manualDtId) {
        this.manualDtId = manualDtId;
    }

    public long getManualHdId() {
        return manualHdId;
    }

    public void setManualHdId(long manualHdId) {
        this.manualHdId = manualHdId;
    }

    public String getVacation() {
        return vacation;
    }

    public void setVacation(String vacation) {
        this.vacation = vacation;
    }

    public String getWeeklyHoliday() {
        return weeklyHoliday;
    }

    public void setWeeklyHoliday(String weeklyHoliday) {
        this.weeklyHoliday = weeklyHoliday;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getDutyEnd() {
        return dutyEnd;
    }

    public void setDutyEnd(String dutyEnd) {
        this.dutyEnd = dutyEnd;
    }

    public String getDutyLocationName() {
        return dutyLocationName;
    }

    public void setDutyLocationName(String dutyLocationName) {
        this.dutyLocationName = dutyLocationName;
    }

    public String getDutyStart() {
        return dutyStart;
    }

    public void setDutyStart(String dutyStart) {
        this.dutyStart = dutyStart;
    }

    public String getDutyTypeName() {
        return dutyTypeName;
    }

    public void setDutyTypeName(String dutyTypeName) {
        this.dutyTypeName = dutyTypeName;
    }

    public String getWorkDone() {
        return workDone;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }

    public String getDutyPlusDays() {
        return dutyPlusDays;
    }

    public void setDutyPlusDays(String dutyPlusDays) {
        this.dutyPlusDays = dutyPlusDays;
    }

    public String getDutyPlusMinuts() {
        return dutyPlusMinuts;
    }

    public void setDutyPlusMinuts(String dutyPlusMinuts) {
        this.dutyPlusMinuts = dutyPlusMinuts;
    }

    public String getDutyPunishDays() {
        return dutyPunishDays;
    }

    public void setDutyPunishDays(String dutyPunishDays) {
        this.dutyPunishDays = dutyPunishDays;
    }

    public String getDutyPunishMinuts() {
        return dutyPunishMinuts;
    }

    public void setDutyPunishMinuts(String dutyPunishMinuts) {
        this.dutyPunishMinuts = dutyPunishMinuts;
    }

    public String getOvertimeApprove() {
        return overtimeApprove;
    }

    public void setOvertimeApprove(String overtimeApprove) {
        this.overtimeApprove = overtimeApprove;
    }

    

}

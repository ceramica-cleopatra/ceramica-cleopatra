/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "HR_MANUAL_EFFECTION_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManualEffectionDt.findAll", query = "SELECT h FROM HrManualEffectionDt h"),
    @NamedQuery(name = "HrManualEffectionDt.chkUpdateHoliday", query = "SELECT count(h.id) FROM HrManualEffectionDt h WHERE (h.vacation='N' or h.vacation is null) and (h.holiday='N' or h.holiday is null) and (h.weeklyHoliday='N' or h.weeklyHoliday is null) and h.trnsDate=:trns_date and h.hrManualEffectionHd.id=:hd_id"),
    @NamedQuery(name = "HrManualEffectionDt.updateHoliday", query = "SELECT h FROM HrManualEffectionDt h WHERE (h.vacation='N' or h.vacation is null) and (h.holiday='N' or h.holiday is null) and (h.weeklyHoliday='N' or h.weeklyHoliday is null) and h.trnsDate=:trns_date and h.hrManualEffectionHd.id=:hd_id"),
    @NamedQuery(name = "HrManualEffectionDt.updateHolidays", query = "SELECT h FROM HrManualEffectionDt h WHERE (h.vacation='N' or h.vacation is null) and h.holiday='Y' and (h.weeklyHoliday='N' or h.weeklyHoliday is null) and h.trnsDate between :from_date and :to_date and :emp=(select o.empId from HrManualEffectionHd o where o.id=h.hrManualEffectionHd.id)"),
    @NamedQuery(name = "HrManualEffectionDt.findById", query = "SELECT h FROM HrManualEffectionDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrManualEffectionDt.findByTrnsDate", query = "SELECT h FROM HrManualEffectionDt h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrManualEffectionDt.findByEffectMinuts", query = "SELECT h FROM HrManualEffectionDt h WHERE h.effectMinuts = :effectMinuts"),
    @NamedQuery(name = "HrManualEffectionDt.findByEffectDays", query = "SELECT h FROM HrManualEffectionDt h WHERE h.effectDays = :effectDays"),
    @NamedQuery(name = "HrManualEffectionDt.findByNotes", query = "SELECT h FROM HrManualEffectionDt h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrManualEffectionDt.findByAuthorizedDay", query = "SELECT h FROM HrManualEffectionDt h WHERE h.authorizedDay = :authorizedDay"),
    @NamedQuery(name = "HrManualEffectionDt.findByNotAuthorizedDay", query = "SELECT h FROM HrManualEffectionDt h WHERE h.notAuthorizedDay = :notAuthorizedDay"),
    @NamedQuery(name = "HrManualEffectionDt.findByInTrns", query = "SELECT h FROM HrManualEffectionDt h WHERE h.inTrns = :inTrns"),
    @NamedQuery(name = "HrManualEffectionDt.findByOutTrns", query = "SELECT h FROM HrManualEffectionDt h WHERE h.outTrns = :outTrns"),
    @NamedQuery(name = "HrManualEffectionDt.findByPlusMinuts", query = "SELECT h FROM HrManualEffectionDt h WHERE h.plusMinuts = :plusMinuts"),
    @NamedQuery(name = "HrManualEffectionDt.findByPlusDays", query = "SELECT h FROM HrManualEffectionDt h WHERE h.plusDays = :plusDays"),
    @NamedQuery(name = "HrManualEffectionDt.findByHoliday", query = "SELECT h FROM HrManualEffectionDt h WHERE h.holiday = :holiday"),
    @NamedQuery(name = "HrManualEffectionDt.findByDuty", query = "SELECT h FROM HrManualEffectionDt h WHERE h.duty = :duty"),
    @NamedQuery(name = "HrManualEffectionDt.findByAuthorization", query = "SELECT h FROM HrManualEffectionDt h WHERE h.authorization = :authorization"),
    @NamedQuery(name = "HrManualEffectionDt.findByHolType", query = "SELECT h FROM HrManualEffectionDt h WHERE h.holType = :holType"),
    @NamedQuery(name = "HrManualEffectionDt.findByAuthorizationId", query = "SELECT h FROM HrManualEffectionDt h WHERE h.authorizationId = :authorizationId"),
    @NamedQuery(name = "HrManualEffectionDt.findByDutyId", query = "SELECT h FROM HrManualEffectionDt h WHERE h.dutyId = :dutyId"),
    @NamedQuery(name = "HrManualEffectionDt.findByHolidayDays", query = "SELECT h FROM HrManualEffectionDt h WHERE h.holidayDays = :holidayDays"),
    @NamedQuery(name = "HrManualEffectionDt.findByVacation", query = "SELECT h FROM HrManualEffectionDt h WHERE h.vacation = :vacation"),
    @NamedQuery(name = "HrManualEffectionDt.findByPlusDayClose", query = "SELECT h FROM HrManualEffectionDt h WHERE h.plusDayClose = :plusDayClose"),
    @NamedQuery(name = "HrManualEffectionDt.findByWeeklyHoliday", query = "SELECT h FROM HrManualEffectionDt h WHERE h.weeklyHoliday = :weeklyHoliday"),
    @NamedQuery(name = "HrManualEffectionDt.findByShiftId", query = "SELECT h FROM HrManualEffectionDt h WHERE h.shiftId = :shiftId"),
    @NamedQuery(name = "HrManualEffectionDt.findByStrtLaw", query = "SELECT h FROM HrManualEffectionDt h WHERE h.strtLaw = :strtLaw"),
    @NamedQuery(name = "HrManualEffectionDt.findByPeriodLaw", query = "SELECT h FROM HrManualEffectionDt h WHERE h.periodLaw = :periodLaw"),
    @NamedQuery(name = "HrManualEffectionDt.findByEndLaw", query = "SELECT h FROM HrManualEffectionDt h WHERE h.endLaw = :endLaw"),
    @NamedQuery(name = "HrManualEffectionDt.findByClosed", query = "SELECT h FROM HrManualEffectionDt h WHERE h.closed = :closed"),
    @NamedQuery(name = "HrManualEffectionDt.findByTs", query = "SELECT h FROM HrManualEffectionDt h WHERE h.ts = :ts"),
    @NamedQuery(name = "HrManualEffectionDt.findByOutEffection", query = "SELECT h FROM HrManualEffectionDt h WHERE h.outEffection = :outEffection"),
    @NamedQuery(name = "HrManualEffectionDt.findByClockTrns", query = "SELECT h FROM HrManualEffectionDt h WHERE h.clockTrns = :clockTrns"),
    @NamedQuery(name = "HrManualEffectionDt.findByUserId", query = "SELECT h FROM HrManualEffectionDt h WHERE h.userId = :userId"),
    @NamedQuery(name = "HrManualEffectionDt.findByCounterInOut", query = "SELECT h FROM HrManualEffectionDt h WHERE h.counterInOut = :counterInOut"),
    @NamedQuery(name = "HrManualEffectionDt.cntOfHolidays10", query = "select count(dt) from HrManualEffectionDt dt JOIN dt.hrManualEffectionHd hd" +
                      " where dt.holType=10 and hd.empId=:emp_id and hd.trnsMonth=:m and hd.trnsYear=:y"),
    @NamedQuery(name = "HrManualEffectionDt.cntOfAuth", query = "select count(dt) from HrManualEffectionDt dt JOIN dt.hrManualEffectionHd hd" +
                      " where hd.empId=:emp_id and dt.trnsDate between :from_date and :to_date and (dt.notAuthorizedDay='Y' or dt.authorizedDay='Y')"),
    @NamedQuery(name = "HrManualEffectionDt.chkTrnsEntry", query = "select count(dt) from HrManualEffectionDt dt JOIN dt.hrManualEffectionHd hd where hd.empId=:emp_id and dt.trnsDate between :from_date and :to_date and (dt.inTrns is not null or dt.outTrns is not null)")})
public class HrManualEffectionDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "EFFECT_MINUTS")
    private Long effectMinuts;
    @Column(name = "EFFECT_DAYS")
    private BigDecimal effectDays;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "AUTHORIZED_DAY")
    private String authorizedDay;
    @Column(name = "NOT_AUTHORIZED_DAY")
    private String notAuthorizedDay;
    @Column(name = "IN_TRNS")
    @Temporal(TemporalType.DATE)
    private Date inTrns;
    @Column(name = "OUT_TRNS")
    @Temporal(TemporalType.DATE)
    private Date outTrns;
    @Column(name = "PLUS_MINUTS")
    private BigDecimal plusMinuts;
    @Column(name = "PLUS_DAYS")
    private BigDecimal plusDays;
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
    @Column(name = "HOLIDAY_DAYS")
    private BigInteger holidayDays;
    @Column(name = "VACATION")
    private String vacation;
    @Column(name = "PLUS_DAY_CLOSE")
    private String plusDayClose;
    @Column(name = "WEEKLY_HOLIDAY")
    private String weeklyHoliday;
    @Column(name = "SHIFT_ID")
    private Long shiftId;
    @Column(name = "STRT_LAW")
    private Long strtLaw;
    @Column(name = "PERIOD_LAW")
    private Long periodLaw;
    @Column(name = "END_LAW")
    private Long endLaw;
    @Column(name = "CLOSED")
    private String closed;
    @Column(name = "TS")
    private String ts;
    @Column(name = "OUT_EFFECTION")
    private String outEffection;
    @Column(name = "CLOCK_TRNS")
    private String clockTrns;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "COUNTER_IN_OUT")
    private Short counterInOut;
    @Column(name = "OVERTIME_APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date overtimeApprove_Date;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrManualEffectionHd hrManualEffectionHd;
    @Column(name = "OVERTIME_APPROVE")
    private String overtimeApprove;
    public HrManualEffectionDt() {
    }

    public HrManualEffectionDt(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Long getEffectMinuts() {
        return effectMinuts;
    }

    public void setEffectMinuts(Long effectMinuts) {
        this.effectMinuts = effectMinuts;
    }

    public BigDecimal getEffectDays() {
        return effectDays;
    }

    public void setEffectDays(BigDecimal effectDays) {
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

    public BigDecimal getPlusMinuts() {
        return plusMinuts;
    }

    public void setPlusMinuts(BigDecimal plusMinuts) {
        this.plusMinuts = plusMinuts;
    }

    public BigDecimal getPlusDays() {
        return plusDays;
    }

    public void setPlusDays(BigDecimal plusDays) {
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

    public BigInteger getHolidayDays() {
        return holidayDays;
    }

    public void setHolidayDays(BigInteger holidayDays) {
        this.holidayDays = holidayDays;
    }

    public String getVacation() {
        return vacation;
    }

    public void setVacation(String vacation) {
        this.vacation = vacation;
    }

    public String getPlusDayClose() {
        return plusDayClose;
    }

    public void setPlusDayClose(String plusDayClose) {
        this.plusDayClose = plusDayClose;
    }

    public String getWeeklyHoliday() {
        return weeklyHoliday;
    }

    public void setWeeklyHoliday(String weeklyHoliday) {
        this.weeklyHoliday = weeklyHoliday;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Long getStrtLaw() {
        return strtLaw;
    }

    public void setStrtLaw(Long strtLaw) {
        this.strtLaw = strtLaw;
    }

    public Long getPeriodLaw() {
        return periodLaw;
    }

    public void setPeriodLaw(Long periodLaw) {
        this.periodLaw = periodLaw;
    }

    public Long getEndLaw() {
        return endLaw;
    }

    public void setEndLaw(Long endLaw) {
        this.endLaw = endLaw;
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

    public String getOutEffection() {
        return outEffection;
    }

    public void setOutEffection(String outEffection) {
        this.outEffection = outEffection;
    }

    public String getClockTrns() {
        return clockTrns;
    }

    public void setClockTrns(String clockTrns) {
        this.clockTrns = clockTrns;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Short getCounterInOut() {
        return counterInOut;
    }

    public void setCounterInOut(Short counterInOut) {
        this.counterInOut = counterInOut;
    }

    public HrManualEffectionHd getHrManualEffectionHd() {
        return hrManualEffectionHd;
    }

    public void setHrManualEffectionHd(HrManualEffectionHd hrManualEffectionHd) {
        this.hrManualEffectionHd = hrManualEffectionHd;
    }

    public String getOvertimeApprove() {
        return overtimeApprove;
    }

    public void setOvertimeApprove(String overtimeApprove) {
        this.overtimeApprove = overtimeApprove;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrManualEffectionDt)) {
            return false;
        }
        HrManualEffectionDt other = (HrManualEffectionDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Date getOvertimeApprove_Date() {
        return overtimeApprove_Date;
    }

    public void setOvertimeApprove_Date(Date overtimeApprove_Date) {
        this.overtimeApprove_Date = overtimeApprove_Date;
    }

   

    @Override
    public String toString() {
        return "e.HrManualEffectionDt[id=" + id + "]";
    }

}

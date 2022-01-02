/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_HAFEZ_HISTORY", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHafezHistory.findAll", query = "SELECT sum(h.finalAmount) FROM HrHafezHistory h where h.monthCalc=:m and h.trnsYear=:y and h.empId=:emp group by h.empId"),
    @NamedQuery(name = "HrHafezHistory.findByEmpId", query = "SELECT h FROM HrHafezHistory h WHERE h.empId = :empId"),
    @NamedQuery(name = "HrHafezHistory.findByMonthCalc", query = "SELECT h FROM HrHafezHistory h WHERE h.monthCalc = :monthCalc"),
    @NamedQuery(name = "HrHafezHistory.findByTrnsYear", query = "SELECT h FROM HrHafezHistory h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrHafezHistory.findByHolidayDays", query = "SELECT h FROM HrHafezHistory h WHERE h.holidayDays = :holidayDays"),
    @NamedQuery(name = "HrHafezHistory.findByAuthorizedDays", query = "SELECT h FROM HrHafezHistory h WHERE h.authorizedDays = :authorizedDays"),
    @NamedQuery(name = "HrHafezHistory.findByNotAuthorizedDays", query = "SELECT h FROM HrHafezHistory h WHERE h.notAuthorizedDays = :notAuthorizedDays"),
    @NamedQuery(name = "HrHafezHistory.findByEsabaDays", query = "SELECT h FROM HrHafezHistory h WHERE h.esabaDays = :esabaDays"),
    @NamedQuery(name = "HrHafezHistory.findByMaradyDays", query = "SELECT h FROM HrHafezHistory h WHERE h.maradyDays = :maradyDays"),
    @NamedQuery(name = "HrHafezHistory.findByAutoAmount", query = "SELECT h FROM HrHafezHistory h WHERE h.autoAmount = :autoAmount"),
    @NamedQuery(name = "HrHafezHistory.findByHolidayAmount", query = "SELECT h FROM HrHafezHistory h WHERE h.holidayAmount = :holidayAmount"),
    @NamedQuery(name = "HrHafezHistory.findByAuthorizedAmount", query = "SELECT h FROM HrHafezHistory h WHERE h.authorizedAmount = :authorizedAmount"),
    @NamedQuery(name = "HrHafezHistory.findByNotAuthorizedAmount", query = "SELECT h FROM HrHafezHistory h WHERE h.notAuthorizedAmount = :notAuthorizedAmount"),
    @NamedQuery(name = "HrHafezHistory.findByEsabaAmount", query = "SELECT h FROM HrHafezHistory h WHERE h.esabaAmount = :esabaAmount"),
    @NamedQuery(name = "HrHafezHistory.findByMaradyAmount", query = "SELECT h FROM HrHafezHistory h WHERE h.maradyAmount = :maradyAmount"),
    @NamedQuery(name = "HrHafezHistory.findByTotAmount", query = "SELECT h FROM HrHafezHistory h WHERE h.totAmount = :totAmount"),
    @NamedQuery(name = "HrHafezHistory.findByFinalAmount", query = "SELECT h FROM HrHafezHistory h WHERE h.finalAmount = :finalAmount"),
    @NamedQuery(name = "HrHafezHistory.findByExceptions", query = "SELECT h FROM HrHafezHistory h WHERE h.exceptions = :exceptions"),
    @NamedQuery(name = "HrHafezHistory.findByListId", query = "SELECT h FROM HrHafezHistory h WHERE h.listId = :listId")})
public class HrHafezHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HrHafezHistoryPK hrHafezHistoryPK;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "MONTH_CALC")
    private Double monthCalc;
    @Column(name = "TRNS_YEAR")
    private Long trnsYear;
    @Column(name = "HOLIDAY_DAYS")
    private Long holidayDays;
    @Column(name = "AUTHORIZED_DAYS")
    private Long authorizedDays;
    @Column(name = "NOT_AUTHORIZED_DAYS")
    private Long notAuthorizedDays;
    @Column(name = "ESABA_DAYS")
    private Long esabaDays;
    @Column(name = "MARADY_DAYS")
    private Long maradyDays;
    @Column(name = "AUTO_AMOUNT")
    private Long autoAmount;
    @Column(name = "HOLIDAY_AMOUNT")
    private Double holidayAmount;
    @Column(name = "AUTHORIZED_AMOUNT")
    private Double authorizedAmount;
    @Column(name = "NOT_AUTHORIZED_AMOUNT")
    private Double notAuthorizedAmount;
    @Column(name = "ESABA_AMOUNT")
    private Double esabaAmount;
    @Column(name = "MARADY_AMOUNT")
    private Double maradyAmount;
    @Column(name = "TOT_AMOUNT")
    private Double totAmount;
    @Column(name = "FINAL_AMOUNT")
    private Double finalAmount;
    @Column(name = "EXCEPTIONS")
    private String exceptions;
    @Column(name = "LIST_ID")
    private Long listId;

    public HrHafezHistory() {
    }

    public HrHafezHistory(HrHafezHistoryPK hrHafezHistoryPK) {
        this.hrHafezHistoryPK = hrHafezHistoryPK;
    }

    public HrHafezHistoryPK getHrHafezHistoryPK() {
        return hrHafezHistoryPK;
    }

    public void setHrHafezHistoryPK(HrHafezHistoryPK hrHafezHistoryPK) {
        this.hrHafezHistoryPK = hrHafezHistoryPK;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

  

    public Long getHolidayDays() {
        return holidayDays;
    }

    public void setHolidayDays(Long holidayDays) {
        this.holidayDays = holidayDays;
    }

    public Double getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(Double authorizedAmount) {
        this.authorizedAmount = authorizedAmount;
    }

    public Long getAuthorizedDays() {
        return authorizedDays;
    }

    public void setAuthorizedDays(Long authorizedDays) {
        this.authorizedDays = authorizedDays;
    }

    public Long getAutoAmount() {
        return autoAmount;
    }

    public void setAutoAmount(Long autoAmount) {
        this.autoAmount = autoAmount;
    }

    public Double getEsabaAmount() {
        return esabaAmount;
    }

    public void setEsabaAmount(Double esabaAmount) {
        this.esabaAmount = esabaAmount;
    }

    public Long getEsabaDays() {
        return esabaDays;
    }

    public void setEsabaDays(Long esabaDays) {
        this.esabaDays = esabaDays;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Double getHolidayAmount() {
        return holidayAmount;
    }

    public void setHolidayAmount(Double holidayAmount) {
        this.holidayAmount = holidayAmount;
    }

    public Double getMaradyAmount() {
        return maradyAmount;
    }

    public void setMaradyAmount(Double maradyAmount) {
        this.maradyAmount = maradyAmount;
    }

    public Long getMaradyDays() {
        return maradyDays;
    }

    public void setMaradyDays(Long maradyDays) {
        this.maradyDays = maradyDays;
    }

    public Double getMonthCalc() {
        return monthCalc;
    }

    public void setMonthCalc(Double monthCalc) {
        this.monthCalc = monthCalc;
    }

    public Double getNotAuthorizedAmount() {
        return notAuthorizedAmount;
    }

    public void setNotAuthorizedAmount(Double notAuthorizedAmount) {
        this.notAuthorizedAmount = notAuthorizedAmount;
    }

    public Long getNotAuthorizedDays() {
        return notAuthorizedDays;
    }

    public void setNotAuthorizedDays(Long notAuthorizedDays) {
        this.notAuthorizedDays = notAuthorizedDays;
    }

    public Double getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(Double totAmount) {
        this.totAmount = totAmount;
    }

    public Long getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Long trnsYear) {
        this.trnsYear = trnsYear;
    }

  
    public String getExceptions() {
        return exceptions;
    }

    public void setExceptions(String exceptions) {
        this.exceptions = exceptions;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hrHafezHistoryPK != null ? hrHafezHistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrHafezHistory)) {
            return false;
        }
        HrHafezHistory other = (HrHafezHistory) object;
        if ((this.hrHafezHistoryPK == null && other.hrHafezHistoryPK != null) || (this.hrHafezHistoryPK != null && !this.hrHafezHistoryPK.equals(other.hrHafezHistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrHafezHistory[hrHafezHistoryPK=" + hrHafezHistoryPK + "]";
    }

}

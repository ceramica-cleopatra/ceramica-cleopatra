/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Cacheable(true)
@Entity
@Table(name = "HR_MONTLY_SALARY_CALC_PERIOD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrMontlySalaryCalcPeriod.findAll", query = "select o from HrMontlySalaryCalcPeriod o where :date between o.dateFrom and o.dateTo"),
    @NamedQuery(name = "HrMontlySalaryCalcPeriod.findByTrnsMonth", query = "SELECT h FROM HrMontlySalaryCalcPeriod h WHERE h.hrMontlySalaryCalcPeriodPK.trnsMonth = :trnsMonth and h.trnsYear=:trnsYear"),
    @NamedQuery(name = "HrMontlySalaryCalcPeriod.findByDateFrom", query = "SELECT h FROM HrMontlySalaryCalcPeriod h WHERE h.dateFrom = :dateFrom"),
    @NamedQuery(name = "HrMontlySalaryCalcPeriod.findByDateTo", query = "SELECT h FROM HrMontlySalaryCalcPeriod h WHERE h.dateTo = :dateTo"),
    @NamedQuery(name = "HrMontlySalaryCalcPeriod.findByNotes", query = "SELECT h FROM HrMontlySalaryCalcPeriod h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrMontlySalaryCalcPeriod.findByTrnsYear", query = "SELECT h FROM HrMontlySalaryCalcPeriod h WHERE h.hrMontlySalaryCalcPeriodPK.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrMontlySalaryCalcPeriod.findByHafez", query = "SELECT h FROM HrMontlySalaryCalcPeriod h WHERE h.hafez = :hafez"),
    @NamedQuery(name = "HrMontlySalaryCalcPeriod.findByHafezYear", query = "SELECT h FROM HrMontlySalaryCalcPeriod h WHERE h.hafezYear = :hafezYear")})
public class HrMontlySalaryCalcPeriod implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HrMontlySalaryCalcPeriodPK hrMontlySalaryCalcPeriodPK;
    @Column(name = "DATE_FROM")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Column(name = "DATE_TO")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "HAFEZ")
    private Long hafez;
    @Column(name = "HAFEZ_YEAR")
    private Short hafezYear;
    @Column(name = "TRNS_MONTH")
    private Long trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Long trnsYear;

    public Long getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(Long trnsMonth) {
        this.trnsMonth = trnsMonth;
    }

    public Long getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Long trnsYear) {
        this.trnsYear = trnsYear;
    }
    
    public HrMontlySalaryCalcPeriod() {
    }

    public HrMontlySalaryCalcPeriod(HrMontlySalaryCalcPeriodPK hrMontlySalaryCalcPeriodPK) {
        this.hrMontlySalaryCalcPeriodPK = hrMontlySalaryCalcPeriodPK;
    }

    public HrMontlySalaryCalcPeriod(long trnsMonth, short trnsYear) {
        this.hrMontlySalaryCalcPeriodPK = new HrMontlySalaryCalcPeriodPK(trnsMonth, trnsYear);
    }

    public HrMontlySalaryCalcPeriodPK getHrMontlySalaryCalcPeriodPK() {
        return hrMontlySalaryCalcPeriodPK;
    }

    public void setHrMontlySalaryCalcPeriodPK(HrMontlySalaryCalcPeriodPK hrMontlySalaryCalcPeriodPK) {
        this.hrMontlySalaryCalcPeriodPK = hrMontlySalaryCalcPeriodPK;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getHafez() {
        return hafez;
    }

    public void setHafez(Long hafez) {
        this.hafez = hafez;
    }

    public Short getHafezYear() {
        return hafezYear;
    }

    public void setHafezYear(Short hafezYear) {
        this.hafezYear = hafezYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hrMontlySalaryCalcPeriodPK != null ? hrMontlySalaryCalcPeriodPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrMontlySalaryCalcPeriod)) {
            return false;
        }
        HrMontlySalaryCalcPeriod other = (HrMontlySalaryCalcPeriod) object;
        if ((this.hrMontlySalaryCalcPeriodPK == null && other.hrMontlySalaryCalcPeriodPK != null) || (this.hrMontlySalaryCalcPeriodPK != null && !this.hrMontlySalaryCalcPeriodPK.equals(other.hrMontlySalaryCalcPeriodPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrMontlySalaryCalcPeriod[hrMontlySalaryCalcPeriodPK=" + hrMontlySalaryCalcPeriodPK + "]";
    }

}

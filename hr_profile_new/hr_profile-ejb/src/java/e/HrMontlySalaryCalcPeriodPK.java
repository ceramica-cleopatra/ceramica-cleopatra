/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ahmed abbas
 */
@Embeddable
public class HrMontlySalaryCalcPeriodPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "TRNS_MONTH")
    private long trnsMonth;
    @Basic(optional = false)
    @Column(name = "TRNS_YEAR")
    private short trnsYear;

    public HrMontlySalaryCalcPeriodPK() {
    }

    public HrMontlySalaryCalcPeriodPK(long trnsMonth, short trnsYear) {
        this.trnsMonth = trnsMonth;
        this.trnsYear = trnsYear;
    }

    public long getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(long trnsMonth) {
        this.trnsMonth = trnsMonth;
    }

    public short getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(short trnsYear) {
        this.trnsYear = trnsYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) trnsMonth;
        hash += (int) trnsYear;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrMontlySalaryCalcPeriodPK)) {
            return false;
        }
        HrMontlySalaryCalcPeriodPK other = (HrMontlySalaryCalcPeriodPK) object;
        if (this.trnsMonth != other.trnsMonth) {
            return false;
        }
        if (this.trnsYear != other.trnsYear) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrMontlySalaryCalcPeriodPK[trnsMonth=" + trnsMonth + ", trnsYear=" + trnsYear + "]";
    }

}

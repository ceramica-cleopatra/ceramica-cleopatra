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
@Table(name = "HR_PRAY_TIMES", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrPrayTimes.findAll", query = "SELECT h FROM HrPrayTimes h"),
    @NamedQuery(name = "HrPrayTimes.findByTrnsDate", query = "SELECT h FROM HrPrayTimes h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrPrayTimes.findByFajr", query = "SELECT h FROM HrPrayTimes h WHERE h.fajr = :fajr"),
    @NamedQuery(name = "HrPrayTimes.findByShorok", query = "SELECT h FROM HrPrayTimes h WHERE h.shorok = :shorok"),
    @NamedQuery(name = "HrPrayTimes.findByZohr", query = "SELECT h FROM HrPrayTimes h WHERE h.zohr = :zohr"),
    @NamedQuery(name = "HrPrayTimes.findByAsr", query = "SELECT h FROM HrPrayTimes h WHERE h.asr = :asr"),
    @NamedQuery(name = "HrPrayTimes.findByMaghreb", query = "SELECT h FROM HrPrayTimes h WHERE h.maghreb = :maghreb"),
    @NamedQuery(name = "HrPrayTimes.findByAsha", query = "SELECT h FROM HrPrayTimes h WHERE h.asha = :asha")})
public class HrPrayTimes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "FAJR")
    private String fajr;
    @Column(name = "SHOROK")
    private String shorok;
    @Column(name = "ZOHR")
    private String zohr;
    @Column(name = "ASR")
    private String asr;
    @Column(name = "MAGHREB")
    private String maghreb;
    @Column(name = "ASHA")
    private String asha;

    public HrPrayTimes() {
    }

    public HrPrayTimes(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getShorok() {
        return shorok;
    }

    public void setShorok(String shorok) {
        this.shorok = shorok;
    }

    public String getZohr() {
        return zohr;
    }

    public void setZohr(String zohr) {
        this.zohr = zohr;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getMaghreb() {
        return maghreb;
    }

    public void setMaghreb(String maghreb) {
        this.maghreb = maghreb;
    }

    public String getAsha() {
        return asha;
    }

    public void setAsha(String asha) {
        this.asha = asha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trnsDate != null ? trnsDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrPrayTimes)) {
            return false;
        }
        HrPrayTimes other = (HrPrayTimes) object;
        if ((this.trnsDate == null && other.trnsDate != null) || (this.trnsDate != null && !this.trnsDate.equals(other.trnsDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrPrayTimes[trnsDate=" + trnsDate + "]";
    }

}

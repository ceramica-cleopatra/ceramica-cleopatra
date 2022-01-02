/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_MANUAL_TRNS_LEVEL_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManualTrnsLevelDt.findAll", query = "SELECT h FROM HrManualTrnsLevelDt h where h.locId=:loc and h.hrManualTrnsLevelHd.empId=:user"),
    @NamedQuery(name = "HrManualTrnsLevelDt.findById", query = "SELECT h FROM HrManualTrnsLevelDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrManualTrnsLevelDt.findByLocId", query = "SELECT h FROM HrManualTrnsLevelDt h WHERE h.locId = :locId")})
public class HrManualTrnsLevelDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "LOC_ID")
    private Long locId;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrManualTrnsLevelHd hrManualTrnsLevelHd;

    public HrManualTrnsLevelDt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

   


    public HrManualTrnsLevelHd getHrManualTrnsLevelHd() {
        return hrManualTrnsLevelHd;
    }

    public void setHrManualTrnsLevelHd(HrManualTrnsLevelHd hrManualTrnsLevelHd) {
        this.hrManualTrnsLevelHd = hrManualTrnsLevelHd;
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
        if (!(object instanceof HrManualTrnsLevelDt)) {
            return false;
        }
        HrManualTrnsLevelDt other = (HrManualTrnsLevelDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrManualTrnsLevelDt[id=" + id + "]";
    }

}

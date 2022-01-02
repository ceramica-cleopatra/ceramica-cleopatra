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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_CHECKUP_DUTY_BONUS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyBonus.findAll", query = "SELECT h FROM HrCheckupDutyBonus h"),
    @NamedQuery(name = "HrCheckupDutyBonus.findById", query = "SELECT h FROM HrCheckupDutyBonus h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyBonus.findByLocId", query = "SELECT h FROM HrCheckupDutyBonus h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrCheckupDutyBonus.findByValue", query = "SELECT h FROM HrCheckupDutyBonus h WHERE h.value = :value")})
public class HrCheckupDutyBonus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "LOC_ID")
    private Long locId;
    @Column(name = "VALUE")
    private BigDecimal value;

    public HrCheckupDutyBonus() {
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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
        if (!(object instanceof HrCheckupDutyBonus)) {
            return false;
        }
        HrCheckupDutyBonus other = (HrCheckupDutyBonus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyBonus[id=" + id + "]";
    }

}

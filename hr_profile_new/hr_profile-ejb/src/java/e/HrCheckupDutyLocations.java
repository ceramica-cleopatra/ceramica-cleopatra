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
 * @author data
 */
@Entity
@Table(name = "HR_CHECKUP_DUTY_LOCATIONS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyLocations.findAll", query = "SELECT h FROM HrCheckupDutyLocations h"),
    @NamedQuery(name = "HrCheckupDutyLocations.findById", query = "SELECT h FROM HrCheckupDutyLocations h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyLocations.findByLocId", query = "SELECT h FROM HrCheckupDutyLocations h WHERE h.locId = :locId and h.hrCheckupDutySetupHd.empNo=:empNo"),
    @NamedQuery(name = "HrCheckupDutyLocations.findByLocIdAndDeptId", query = "SELECT h FROM HrCheckupDutyLocations h WHERE h.locId = :locId and h.hrCheckupDutySetupHd.deptId=:dept_id and h.hrCheckupDutySetupHd.empNo is null"),
    @NamedQuery(name = "HrCheckupDutyLocations.findByAmount", query = "SELECT h FROM HrCheckupDutyLocations h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrCheckupDutyLocations.findByMaxEntryPeriod", query = "SELECT h FROM HrCheckupDutyLocations h WHERE h.maxEntryPeriod = :maxEntryPeriod"),
    @NamedQuery(name = "HrCheckupDutyLocations.findByVisitRepeatPeriod", query = "SELECT h FROM HrCheckupDutyLocations h WHERE h.visitRepeatPeriod = :visitRepeatPeriod")})
public class HrCheckupDutyLocations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "LOC_ID")
    private HrLocation locId;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "MAX_ENTRY_PERIOD")
    private Long maxEntryPeriod;
    @Column(name = "VISIT_REPEAT_PERIOD")
    private Long visitRepeatPeriod;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCheckupDutySetupHd hrCheckupDutySetupHd;

    public HrCheckupDutyLocations() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrLocation getLocId() {
        return locId;
    }

    public void setLocId(HrLocation locId) {
        this.locId = locId;
    }

    public Long getMaxEntryPeriod() {
        return maxEntryPeriod;
    }

    public void setMaxEntryPeriod(Long maxEntryPeriod) {
        this.maxEntryPeriod = maxEntryPeriod;
    }

    public Long getVisitRepeatPeriod() {
        return visitRepeatPeriod;
    }

    public void setVisitRepeatPeriod(Long visitRepeatPeriod) {
        this.visitRepeatPeriod = visitRepeatPeriod;
    }

    
    public HrCheckupDutySetupHd getHrCheckupDutySetupHd() {
        return hrCheckupDutySetupHd;
    }

    public void setHrCheckupDutySetupHd(HrCheckupDutySetupHd hrCheckupDutySetupHd) {
        this.hrCheckupDutySetupHd = hrCheckupDutySetupHd;
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
        if (!(object instanceof HrCheckupDutyLocations)) {
            return false;
        }
        HrCheckupDutyLocations other = (HrCheckupDutyLocations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyLocations[id=" + id + "]";
    }

}

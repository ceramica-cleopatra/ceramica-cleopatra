/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_OPEN_DUTY_EXPECTED_LOC_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrOpenDutyExpectedLocDt.findAll", query = "SELECT h FROM HrOpenDutyExpectedLocDt h"),
    @NamedQuery(name = "HrOpenDutyExpectedLocDt.findExpectedEmployees", query = "SELECT h FROM HrOpenDutyExpectedLocDt h WHERE (h.hrLocation.id = :loc_id or (h.hrLocation.id=-1 and h.hrOpenDutyHd.empId.locId in (select l.id from HrLocation l where l.ipAddress=:ip))) and (:trns_date between h.hrOpenDutyHd.fromDate and h.hrOpenDutyHd.toDate)"),
    @NamedQuery(name = "HrOpenDutyExpectedLocDt.findByLocId", query = "SELECT h FROM HrOpenDutyExpectedLocDt h WHERE h.hrLocation.id = :loc_id")
    })
public class HrOpenDutyExpectedLocDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_OPEN_DUTY_EXPECTED_DT_SEQ", sequenceName="HR_OPEN_DUTY_EXPECTED_DT_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_OPEN_DUTY_EXPECTED_DT_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "LOC_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private HrLocation hrLocation;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private HrOpenDutyHd hrOpenDutyHd;

    public HrOpenDutyExpectedLocDt() {
    }

    public HrOpenDutyExpectedLocDt(Long id) {
        this.id = id;
    }

    public HrOpenDutyHd getHrOpenDutyHd() {
        return hrOpenDutyHd;
    }

    public void setHrOpenDutyHd(HrOpenDutyHd hrOpenDutyHd) {
        this.hrOpenDutyHd = hrOpenDutyHd;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrLocation getHrLocation() {
        return hrLocation;
    }

    public void setHrLocation(HrLocation hrLocation) {
        this.hrLocation = hrLocation;
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
        if (!(object instanceof HrOpenDutyExpectedLocDt)) {
            return false;
        }
        HrOpenDutyExpectedLocDt other = (HrOpenDutyExpectedLocDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrOpenDutyExpectedLocDt[id=" + id + "]";
    }

}

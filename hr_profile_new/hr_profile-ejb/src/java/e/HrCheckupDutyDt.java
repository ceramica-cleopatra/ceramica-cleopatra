/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
@Table(name = "HR_CHECKUP_DUTY_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyDt.findAll", query = "SELECT h FROM HrCheckupDutyDt h"),
    @NamedQuery(name = "HrCheckupDutyDt.findById", query = "SELECT h FROM HrCheckupDutyDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyDt.findByEmpId", query = "SELECT h FROM HrCheckupDutyDt h WHERE h.empId = :empId"),
    @NamedQuery(name = "HrCheckupDutyDt.findByValue", query = "SELECT h FROM HrCheckupDutyDt h WHERE h.value = :value")})
public class HrCheckupDutyDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_CHECKUP_DUTY_DT_SEQ", sequenceName="HR_CHECKUP_DUTY_DT_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_CHECKUP_DUTY_DT_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="EMP_Id")
    private HrEmpInfo empId;
    @Column(name = "VALUE")
    private Long value;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCheckupDutyHd hrCheckupDutyHd;

    public HrCheckupDutyDt() {
    }

    public HrEmpInfo getEmpId() {
        return empId;
    }

    public void setEmpId(HrEmpInfo empId) {
        this.empId = empId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

  
    public HrCheckupDutyHd getHrCheckupDutyHd() {
        return hrCheckupDutyHd;
    }

    public void setHrCheckupDutyHd(HrCheckupDutyHd hrCheckupDutyHd) {
        this.hrCheckupDutyHd = hrCheckupDutyHd;
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
        if (!(object instanceof HrCheckupDutyDt)) {
            return false;
        }
        HrCheckupDutyDt other = (HrCheckupDutyDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyDt[id=" + id + "]";
    }

}

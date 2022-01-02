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
 * @author data
 */
@Entity
@Table(name = "HR_CHECKUP_DUTY_EMP1")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyEmp1.findAll", query = "SELECT h FROM HrCheckupDutyEmp1 h"),
    @NamedQuery(name = "HrCheckupDutyEmp1.findById", query = "SELECT h FROM HrCheckupDutyEmp1 h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyEmp1.findByValue", query = "SELECT h FROM HrCheckupDutyEmp1 h WHERE h.value = :value")})
public class HrCheckupDutyEmp1 implements Serializable {
    @SequenceGenerator(name="HR_CHECKUP_DUTY_EMPLOYEES_SEQ", sequenceName="HR_CHECKUP_DUTY_EMPLOYEES_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_CHECKUP_DUTY_EMPLOYEES_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private HrEmpInfo empId;
    @Column(name = "VALUE")
    private Long value;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCheckupDutyHd1 hrCheckupDutyHd1;

    public HrCheckupDutyEmp1() {
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

   

    public HrCheckupDutyHd1 getHrCheckupDutyHd1() {
        return hrCheckupDutyHd1;
    }

    public void setHrCheckupDutyHd1(HrCheckupDutyHd1 hrCheckupDutyHd1) {
        this.hrCheckupDutyHd1 = hrCheckupDutyHd1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public HrEmpInfo getEmpId() {
        return empId;
    }

    public void setEmpId(HrEmpInfo empId) {
        this.empId = empId;
    }




    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrCheckupDutyEmp1)) {
            return false;
        }
        HrCheckupDutyEmp1 other = (HrCheckupDutyEmp1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyEmp1[id=" + id + "]";
    }

}

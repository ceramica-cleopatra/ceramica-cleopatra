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
@Table(name = "HR_CHECKUP_DUTY_EMP2", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyEmp2.findAll", query = "SELECT h FROM HrCheckupDutyEmp2 h"),
    @NamedQuery(name = "HrCheckupDutyEmp2.findById", query = "SELECT h FROM HrCheckupDutyEmp2 h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyEmp2.findByValue", query = "SELECT h FROM HrCheckupDutyEmp2 h WHERE h.value = :value"),
    @NamedQuery(name = "HrCheckupDutyEmp2.findByEmpId", query = "SELECT h FROM HrCheckupDutyEmp2 h WHERE h.empId = :empId")})
public class HrCheckupDutyEmp2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_CHECKUP_DUTY_EMPLOYEES_SEQ", sequenceName="HR_CHECKUP_DUTY_EMPLOYEES_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_CHECKUP_DUTY_EMPLOYEES_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "VALUE")
    private Long value;
    @JoinColumn(name = "EMP_ID")
    private HrEmpInfo empId;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCheckupDutyHd2 hrCheckupDutyHd2;

    public HrCheckupDutyEmp2() {
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

    
    public HrCheckupDutyHd2 getHrCheckupDutyHd2() {
        return hrCheckupDutyHd2;
    }

    public void setHrCheckupDutyHd2(HrCheckupDutyHd2 hrCheckupDutyHd2) {
        this.hrCheckupDutyHd2 = hrCheckupDutyHd2;
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
        if (!(object instanceof HrCheckupDutyEmp2)) {
            return false;
        }
        HrCheckupDutyEmp2 other = (HrCheckupDutyEmp2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyEmp2[id=" + id + "]";
    }

}

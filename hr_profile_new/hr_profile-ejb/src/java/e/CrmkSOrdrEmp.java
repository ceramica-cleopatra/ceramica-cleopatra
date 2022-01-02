/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CRMK_S_ORDR_EMP", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkSOrdrEmp.findAll", query = "SELECT c FROM CrmkSOrdrEmp c"),
    @NamedQuery(name = "CrmkSOrdrEmp.findById", query = "SELECT c FROM CrmkSOrdrEmp c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkSOrdrEmp.findByNotes", query = "SELECT c FROM CrmkSOrdrEmp c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkSOrdrEmp.findByPercent", query = "SELECT c FROM CrmkSOrdrEmp c WHERE c.percent = :percent")})
public class CrmkSOrdrEmp implements Serializable {
    private static final long serialVersionUID = 1L;
     @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "BRN_ID")
    private Long brnId;
    @JoinColumn(name = "HD_ID",referencedColumnName="ID")
    @ManyToOne
    private CrmkOrdrHd crmkOrdrHd;
    @JoinColumn(name = "EMP_ID",referencedColumnName="ID")
    @ManyToOne
    private CrmkEmployee empId;
    @Column(name = "PERCENT")
    private Double percent;
    @Column(name = "NOTES")
    private String notes;

    public CrmkSOrdrEmp() {
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public CrmkEmployee getEmpId() {
        return empId;
    }

    public void setEmpId(CrmkEmployee empId) {
        this.empId = empId;
    }

    public CrmkOrdrHd getCrmkOrdrHd() {
        return crmkOrdrHd;
    }

    public void setCrmkOrdrHd(CrmkOrdrHd crmkOrdrHd) {
        this.crmkOrdrHd = crmkOrdrHd;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        if (!(object instanceof CrmkSOrdrEmp)) {
            return false;
        }
        CrmkSOrdrEmp other = (CrmkSOrdrEmp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkSOrdrEmp[id=" + id + "]";
    }

}

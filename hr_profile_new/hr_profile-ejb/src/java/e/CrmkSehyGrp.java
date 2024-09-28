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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DEV
 */
@Entity
@Table(name = "CRMK_SEHY_GRP", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkSehyGrp.findAll", query = "SELECT c FROM CrmkSehyGrp c"),
    @NamedQuery(name = "CrmkSehyGrp.findById", query = "SELECT c FROM CrmkSehyGrp c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkSehyGrp.findByName", query = "SELECT c FROM CrmkSehyGrp c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkSehyGrp.findByNotes", query = "SELECT c FROM CrmkSehyGrp c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkSehyGrp.findByWeight", query = "SELECT c FROM CrmkSehyGrp c WHERE c.weight = :weight")})
public class CrmkSehyGrp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "WEIGHT")
    private Long weight;

    public CrmkSehyGrp() {
    }

    public CrmkSehyGrp(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
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
        if (!(object instanceof CrmkSehyGrp)) {
            return false;
        }
        CrmkSehyGrp other = (CrmkSehyGrp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkSehyGrp[id=" + id + "]";
    }

}

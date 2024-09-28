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
@Table(name = "CRMK_SEHY_NAME", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkSehyName.findAll", query = "SELECT c FROM CrmkSehyName c"),
    @NamedQuery(name = "CrmkSehyName.findById", query = "SELECT c FROM CrmkSehyName c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkSehyName.findByName", query = "SELECT c FROM CrmkSehyName c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkSehyName.findByTakm", query = "SELECT c FROM CrmkSehyName c WHERE c.takm = :takm"),
    @NamedQuery(name = "CrmkSehyName.findByNotes", query = "SELECT c FROM CrmkSehyName c WHERE c.notes = :notes")})
public class CrmkSehyName implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TAKM")
    private Character takm;
    @Column(name = "NOTES")
    private String notes;

    public CrmkSehyName() {
    }

    public CrmkSehyName(BigDecimal id) {
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

    public Character getTakm() {
        return takm;
    }

    public void setTakm(Character takm) {
        this.takm = takm;
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
        if (!(object instanceof CrmkSehyName)) {
            return false;
        }
        CrmkSehyName other = (CrmkSehyName) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkSehyName[id=" + id + "]";
    }

}

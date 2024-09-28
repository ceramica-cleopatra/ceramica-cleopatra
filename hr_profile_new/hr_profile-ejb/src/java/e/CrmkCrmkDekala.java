/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "CRMK_CRMK_DEKALA" ,schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkCrmkDekala.findAll", query = "SELECT c FROM CrmkCrmkDekala c"),
    @NamedQuery(name = "CrmkCrmkDekala.findById", query = "SELECT c FROM CrmkCrmkDekala c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkCrmkDekala.findByName", query = "SELECT c FROM CrmkCrmkDekala c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkCrmkDekala.findByNotes", query = "SELECT c FROM CrmkCrmkDekala c WHERE c.notes = :notes")})
public class CrmkCrmkDekala implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;

    public CrmkCrmkDekala() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmkCrmkDekala)) {
            return false;
        }
        CrmkCrmkDekala other = (CrmkCrmkDekala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkCrmkDekala[id=" + id + "]";
    }

}

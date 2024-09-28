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
@Table(name = "CRMK_DCRE_DEKALA",schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkDcreDekala.findAll", query = "SELECT c FROM CrmkDcreDekala c"),
    @NamedQuery(name = "CrmkDcreDekala.findById", query = "SELECT c FROM CrmkDcreDekala c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkDcreDekala.findByName", query = "SELECT c FROM CrmkDcreDekala c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkDcreDekala.findByNotes", query = "SELECT c FROM CrmkDcreDekala c WHERE c.notes = :notes")})
public class CrmkDcreDekala implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;

    public CrmkDcreDekala() {
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
        if (!(object instanceof CrmkDcreDekala)) {
            return false;
        }
        CrmkDcreDekala other = (CrmkDcreDekala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkDcreDekala[id=" + id + "]";
    }

}

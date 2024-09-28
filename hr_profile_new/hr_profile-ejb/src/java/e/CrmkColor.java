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
@Table(name = "CRMK_COLOR" ,schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkColor.findAll", query = "SELECT c FROM CrmkColor c"),
    @NamedQuery(name = "CrmkColor.findById", query = "SELECT c FROM CrmkColor c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkColor.findByName", query = "SELECT c FROM CrmkColor c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkColor.findByDegree", query = "SELECT c FROM CrmkColor c WHERE c.degree = :degree"),
    @NamedQuery(name = "CrmkColor.findBySpecial", query = "SELECT c FROM CrmkColor c WHERE c.special = :special"),
    @NamedQuery(name = "CrmkColor.findByNotes", query = "SELECT c FROM CrmkColor c WHERE c.notes = :notes")})
public class CrmkColor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DEGREE")
    private Long degree;
    @Column(name = "SPECIAL")
    private Character special;
    @Column(name = "NOTES")
    private String notes;

    public CrmkColor() {
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

    public Long getDegree() {
        return degree;
    }

    public void setDegree(Long degree) {
        this.degree = degree;
    }

    public Character getSpecial() {
        return special;
    }

    public void setSpecial(Character special) {
        this.special = special;
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
        if (!(object instanceof CrmkColor)) {
            return false;
        }
        CrmkColor other = (CrmkColor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkColor[id=" + id + "]";
    }

}

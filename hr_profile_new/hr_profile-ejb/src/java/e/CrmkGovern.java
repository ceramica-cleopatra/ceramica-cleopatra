/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "CRMK_GOVERN", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkGovern.findAll", query = "SELECT c FROM CrmkGovern c"),
    @NamedQuery(name = "CrmkGovern.findById", query = "SELECT c FROM CrmkGovern c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkGovern.findByName", query = "SELECT c.name FROM CrmkGovern c WHERE c.name like :name"),
    @NamedQuery(name = "CrmkGovern.findByNotes", query = "SELECT c FROM CrmkGovern c WHERE c.notes = :notes")})
public class CrmkGovern implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;

    public CrmkGovern() {
    }

    public CrmkGovern(Long id) {
        this.id = id;
    }

    public CrmkGovern(Long id, String name) {
        this.id = id;
        this.name = name;
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
        if (!(object instanceof CrmkGovern)) {
            return false;
        }
        CrmkGovern other = (CrmkGovern) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkGovern[id=" + id + "]";
    }

}

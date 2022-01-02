/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_HAFEZ_SEHY_TYPES", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHafezSehyTypes.findAll", query = "SELECT h FROM HrHafezSehyTypes h"),
    @NamedQuery(name = "HrHafezSehyTypes.findById", query = "SELECT h FROM HrHafezSehyTypes h WHERE h.id = :id"),
    @NamedQuery(name = "HrHafezSehyTypes.findByName", query = "SELECT h FROM HrHafezSehyTypes h WHERE h.name = :name")})
public class HrHafezSehyTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NAME")
    private String name;

    public HrHafezSehyTypes() {
    }

    public HrHafezSehyTypes(BigDecimal id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrHafezSehyTypes)) {
            return false;
        }
        HrHafezSehyTypes other = (HrHafezSehyTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrHafezSehyTypes[id=" + id + "]";
    }

}

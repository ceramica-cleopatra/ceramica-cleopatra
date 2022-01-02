/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_BADL_TYPE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrBadlType.findAll", query = "SELECT h FROM HrBadlType h"),
    @NamedQuery(name = "HrBadlType.findById", query = "SELECT h FROM HrBadlType h WHERE h.id = :id"),
    @NamedQuery(name = "HrBadlType.findByName", query = "SELECT h FROM HrBadlType h WHERE h.name = :name"),
    @NamedQuery(name = "HrBadlType.findByNotes", query = "SELECT h FROM HrBadlType h WHERE h.notes = :notes")})
public class HrBadlType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @OneToMany(mappedBy = "typeId")
    private List<HrBadlHistory> hrBadlHistoryList;
    public HrBadlType() {
    }

    public List<HrBadlHistory> getHrBadlHistoryList() {
        return hrBadlHistoryList;
    }

    public void setHrBadlHistoryList(List<HrBadlHistory> hrBadlHistoryList) {
        this.hrBadlHistoryList = hrBadlHistoryList;
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
        if (!(object instanceof HrBadlType)) {
            return false;
        }
        HrBadlType other = (HrBadlType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrBadlType[id=" + id + "]";
    }

}

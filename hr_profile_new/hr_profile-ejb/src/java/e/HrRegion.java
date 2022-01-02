/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_REGION", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrRegion.findAll", query = "SELECT h FROM HrRegion h"),
    @NamedQuery(name = "HrRegion.findById", query = "SELECT h FROM HrRegion h WHERE h.id = :id"),
    @NamedQuery(name = "HrRegion.findByName", query = "SELECT h FROM HrRegion h WHERE h.name = :name"),
    @NamedQuery(name = "HrRegion.findByNotes", query = "SELECT h FROM HrRegion h WHERE h.notes = :notes")})
public class HrRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @OneToMany(mappedBy = "hrRegion")
    private List<HrArea> hrAreaList;
    @OneToMany(mappedBy = "hrRegion")
    private List<HrLocation> hrLocationList;

    public HrRegion() {
    }

    public HrRegion(Long id) {
        this.id = id;
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

    public List<HrArea> getHrAreaList() {
        return hrAreaList;
    }

    public void setHrAreaList(List<HrArea> hrAreaList) {
        this.hrAreaList = hrAreaList;
    }

    public List<HrLocation> getHrLocationList() {
        return hrLocationList;
    }

    public void setHrLocationList(List<HrLocation> hrLocationList) {
        this.hrLocationList = hrLocationList;
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
        if (!(object instanceof HrRegion)) {
            return false;
        }
        HrRegion other = (HrRegion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrRegion[id=" + id + "]";
    }

}

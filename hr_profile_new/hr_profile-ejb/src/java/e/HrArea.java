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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_AREA", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrArea.findAll", query = "SELECT h FROM HrArea h"),
    @NamedQuery(name = "HrArea.findById", query = "SELECT h FROM HrArea h WHERE h.id = :id"),
    @NamedQuery(name = "HrArea.findByName", query = "SELECT h FROM HrArea h WHERE h.name = :name"),
    @NamedQuery(name = "HrArea.findByNotes", query = "SELECT h FROM HrArea h WHERE h.notes = :notes")})
public class HrArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrRegion hrRegion;
    @OneToMany(mappedBy = "hrArea")
    private List<HrLocation> hrLocationList;

    public HrArea() {
    }

    public HrArea(Long id) {
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

    public HrRegion getHrRegion() {
        return hrRegion;
    }

    public void setHrRegion(HrRegion hrRegion) {
        this.hrRegion = hrRegion;
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
        if (!(object instanceof HrArea)) {
            return false;
        }
        HrArea other = (HrArea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrArea[id=" + id + "]";
    }

}

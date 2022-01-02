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
@Table(name = "HR_DEGREE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDegree.findAll", query = "SELECT h FROM HrDegree h"),
    @NamedQuery(name = "HrDegree.findById", query = "SELECT h FROM HrDegree h WHERE h.id = :id"),
    @NamedQuery(name = "HrDegree.findByName", query = "SELECT h FROM HrDegree h WHERE h.name = :name"),
    @NamedQuery(name = "HrDegree.findByNotes", query = "SELECT h FROM HrDegree h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrDegree.findByDegCatId", query = "SELECT h FROM HrDegree h WHERE h.degCatId = :degCatId")})
public class HrDegree implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "DEG_CAT_ID")
    private String degCatId;
    @OneToMany(mappedBy = "hrdegree")
    private List<HrEmpEducation> hrEmpEducationList;
    public HrDegree() {
    }

    public HrDegree(Long id) {
        this.id = id;
    }

    public List<HrEmpEducation> getHrEmpEducationList() {
        return hrEmpEducationList;
    }

    public void setHrEmpEducationList(List<HrEmpEducation> hrEmpEducationList) {
        this.hrEmpEducationList = hrEmpEducationList;
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

    public String getDegCatId() {
        return degCatId;
    }

    public void setDegCatId(String degCatId) {
        this.degCatId = degCatId;
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
        if (!(object instanceof HrDegree)) {
            return false;
        }
        HrDegree other = (HrDegree) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDegree[id=" + id + "]";
    }

}

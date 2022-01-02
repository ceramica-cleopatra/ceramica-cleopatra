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
@Table(name = "HR_MANAGEMENT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManagement.findAll", query = "SELECT h FROM HrManagement h"),
    @NamedQuery(name = "HrManagement.findAllDeptNames", query = "SELECT h FROM HrManagement h"),
    @NamedQuery(name = "HrManagement.findById", query = "SELECT h FROM HrManagement h WHERE h.id = :id"),
    @NamedQuery(name = "HrManagement.findByName", query = "SELECT h FROM HrManagement h WHERE h.name = :name"),
    @NamedQuery(name = "HrManagement.findByNotes", query = "SELECT h FROM HrManagement h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrManagement.findByShift", query = "SELECT h FROM HrManagement h WHERE h.shift = :shift")})
public class HrManagement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "SHIFT")
    private Character shift;
    @OneToMany(mappedBy = "hrManagement")
    private List<HrEmpJob> hrEmpJobList;
    @JoinColumn(name = "MANGEMENT_GROUP", referencedColumnName = "ID")
    @ManyToOne
    private HrMangementGroup hrMangementGroup;

    public HrManagement() {
    }

    public HrManagement(Long id) {
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

    public Character getShift() {
        return shift;
    }

    public void setShift(Character shift) {
        this.shift = shift;
    }

    public List<HrEmpJob> getHrEmpJobList() {
        return hrEmpJobList;
    }

    public void setHrEmpJobList(List<HrEmpJob> hrEmpJobList) {
        this.hrEmpJobList = hrEmpJobList;
    }

    public HrMangementGroup getHrMangementGroup() {
        return hrMangementGroup;
    }

    public void setHrMangementGroup(HrMangementGroup hrMangementGroup) {
        this.hrMangementGroup = hrMangementGroup;
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
        if (!(object instanceof HrManagement)) {
            return false;
        }
        HrManagement other = (HrManagement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrManagement[id=" + id + "]";
    }

}

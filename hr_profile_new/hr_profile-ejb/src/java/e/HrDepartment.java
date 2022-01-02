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
@Table(name = "HR_DEPARTMENT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDepartment.findAll", query = "SELECT h FROM HrDepartment h"),
    @NamedQuery(name = "HrDepartment.findById", query = "SELECT h FROM HrDepartment h WHERE h.id = :id"),
    @NamedQuery(name = "HrDepartment.findByName", query = "SELECT h FROM HrDepartment h WHERE h.name = :name"),
    @NamedQuery(name = "HrDepartment.findByNotes", query = "SELECT h FROM HrDepartment h WHERE h.notes = :notes")})
public class HrDepartment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @OneToMany(mappedBy = "hrDepartment")
    private List<HrEmpJob> hrEmpJobList;

    public HrDepartment() {
    }

    public HrDepartment(Long id) {
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

    public List<HrEmpJob> getHrEmpJobList() {
        return hrEmpJobList;
    }

    public void setHrEmpJobList(List<HrEmpJob> hrEmpJobList) {
        this.hrEmpJobList = hrEmpJobList;
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
        if (!(object instanceof HrDepartment)) {
            return false;
        }
        HrDepartment other = (HrDepartment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDepartment[id=" + id + "]";
    }

}

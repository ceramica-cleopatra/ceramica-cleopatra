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
@Table(name = "HR_JOBS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrJobs.findAll", query = "SELECT h FROM HrJobs h"),
    @NamedQuery(name = "HrJobs.findJobNames", query = "SELECT h FROM HrJobs h"),
    @NamedQuery(name = "HrJobs.findById", query = "SELECT h FROM HrJobs h WHERE h.id = :id"),
    @NamedQuery(name = "HrJobs.findByName", query = "SELECT h FROM HrJobs h WHERE h.name = :name"),
    @NamedQuery(name = "HrJobs.findByNotes", query = "SELECT h FROM HrJobs h WHERE h.notes = :notes")})
public class HrJobs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @OneToMany(mappedBy = "hrJobs")
    private List<HrEmpJob> hrEmpJobList;
    @OneToMany(mappedBy = "hrJobs")
    private List<HrEmpHd> hrEmpHdList;

    public HrJobs() {
    }

    public HrJobs(Long id) {
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

    public List<HrEmpHd> getHrEmpHdList() {
        return hrEmpHdList;
    }

    public void setHrEmpHdList(List<HrEmpHd> hrEmpHdList) {
        this.hrEmpHdList = hrEmpHdList;
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
        if (!(object instanceof HrJobs)) {
            return false;
        }
        HrJobs other = (HrJobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrJobs[id=" + id + "]";
    }

}

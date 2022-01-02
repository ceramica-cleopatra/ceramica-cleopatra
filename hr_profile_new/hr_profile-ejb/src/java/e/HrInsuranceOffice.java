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
@Table(name = "HR_INSURANCE_OFFICE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrInsuranceOffice.findAll", query = "SELECT h FROM HrInsuranceOffice h where h.id=:id"),
    @NamedQuery(name = "HrInsuranceOffice.findById", query = "SELECT h FROM HrInsuranceOffice h WHERE h.id = :id"),
    @NamedQuery(name = "HrInsuranceOffice.findByName", query = "SELECT h FROM HrInsuranceOffice h WHERE h.name = :name"),
    @NamedQuery(name = "HrInsuranceOffice.findByNotes", query = "SELECT h FROM HrInsuranceOffice h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrInsuranceOffice.findByOfficeNo", query = "SELECT h FROM HrInsuranceOffice h WHERE h.officeNo = :officeNo")})
public class HrInsuranceOffice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "OFFICE_NO")
    private String officeNo;
    @OneToMany(mappedBy = "hrInsuranceOffice")
    private List<HrEmpHd> hrEmpHdList;

    public HrInsuranceOffice() {
    }

    public HrInsuranceOffice(Long id) {
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

    public String getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
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
        if (!(object instanceof HrInsuranceOffice)) {
            return false;
        }
        HrInsuranceOffice other = (HrInsuranceOffice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrInsuranceOffice[id=" + id + "]";
    }

}

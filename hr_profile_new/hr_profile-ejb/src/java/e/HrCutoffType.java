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
@Table(name = "HR_CUTOFF_TYPE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCutoffType.findAll", query = "SELECT h FROM HrCutoffType h"),
    @NamedQuery(name = "HrCutoffType.findById", query = "SELECT h FROM HrCutoffType h WHERE h.id = :id"),
    @NamedQuery(name = "HrCutoffType.findByName", query = "SELECT h FROM HrCutoffType h WHERE h.name = :name"),
    @NamedQuery(name = "HrCutoffType.findByNotes", query = "SELECT h FROM HrCutoffType h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrCutoffType.findByRearrange", query = "SELECT h FROM HrCutoffType h WHERE h.rearrange = :rearrange"),
    @NamedQuery(name = "HrCutoffType.findByGza", query = "SELECT h FROM HrCutoffType h WHERE h.gza = :gza"),
    @NamedQuery(name = "HrCutoffType.findByEmpBox", query = "SELECT h FROM HrCutoffType h WHERE h.empBox = :empBox"),
    @NamedQuery(name = "HrCutoffType.findByClinic", query = "SELECT h FROM HrCutoffType h WHERE h.clinic = :clinic")})
public class HrCutoffType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "REARRANGE")
    private String rearrange;
    @Column(name = "GZA")
    private Character gza;
    @Column(name = "EMP_BOX")
    private Character empBox;
    @Column(name = "CLINIC")
    private Character clinic;
    @OneToMany(mappedBy = "hrCutoffType")
    private List<HrCutoffVu> hrCutoffVuList;
    @OneToMany(mappedBy = "hrCutoffType")
    private List<HrBorrowHd> hrBorrowHdList;

    public HrCutoffType() {
    }

    public HrCutoffType(Long id) {
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

    public String getRearrange() {
        return rearrange;
    }

    public void setRearrange(String rearrange) {
        this.rearrange = rearrange;
    }

    public Character getGza() {
        return gza;
    }

    public void setGza(Character gza) {
        this.gza = gza;
    }

    public Character getEmpBox() {
        return empBox;
    }

    public void setEmpBox(Character empBox) {
        this.empBox = empBox;
    }

    public Character getClinic() {
        return clinic;
    }

    public void setClinic(Character clinic) {
        this.clinic = clinic;
    }

    public List<HrCutoffVu> getHrCutoffVuList() {
        return hrCutoffVuList;
    }

    public void setHrCutoffVuList(List<HrCutoffVu> hrCutoffVuList) {
        this.hrCutoffVuList = hrCutoffVuList;
    }

    public List<HrBorrowHd> getHrBorrowHdList() {
        return hrBorrowHdList;
    }

    public void setHrBorrowHdList(List<HrBorrowHd> hrBorrowHdList) {
        this.hrBorrowHdList = hrBorrowHdList;
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
        if (!(object instanceof HrCutoffType)) {
            return false;
        }
        HrCutoffType other = (HrCutoffType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCutoffType[id=" + id + "]";
    }

}

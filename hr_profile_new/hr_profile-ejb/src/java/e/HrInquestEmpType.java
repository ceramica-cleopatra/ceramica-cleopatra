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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_INQUEST_EMP_TYPE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrInquestEmpType.findAll", query = "SELECT h FROM HrInquestEmpType h"),
    @NamedQuery(name = "HrInquestEmpType.findById", query = "SELECT h FROM HrInquestEmpType h WHERE h.id = :id"),
    @NamedQuery(name = "HrInquestEmpType.findByTypeName", query = "SELECT h FROM HrInquestEmpType h WHERE h.typeName = :typeName"),
    @NamedQuery(name = "HrInquestEmpType.findByNotes", query = "SELECT h FROM HrInquestEmpType h WHERE h.notes = :notes")})
public class HrInquestEmpType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TYPE_NAME")
    private String typeName;
    @Column(name = "NOTES")
    private String notes;
    @OneToMany(mappedBy = "hrInquestEmpType")
    private List<HrInquestDt> hrInquestDtList;

    public HrInquestEmpType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<HrInquestDt> getHrInquestDtList() {
        return hrInquestDtList;
    }

    public void setHrInquestDtList(List<HrInquestDt> hrInquestDtList) {
        this.hrInquestDtList = hrInquestDtList;
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
        if (!(object instanceof HrInquestEmpType)) {
            return false;
        }
        HrInquestEmpType other = (HrInquestEmpType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrInquestEmpType[id=" + id + "]";
    }

}

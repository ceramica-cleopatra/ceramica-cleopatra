/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "HR_MANUAL_TRNS_LEVEL_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManualTrnsLevelHd.findAll", query = "SELECT h FROM HrManualTrnsLevelHd h"),
    @NamedQuery(name = "HrManualTrnsLevelHd.findById", query = "SELECT h FROM HrManualTrnsLevelHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrManualTrnsLevelHd.findByEmpId", query = "SELECT h FROM HrManualTrnsLevelHd h WHERE h.empId = :empId")})
public class HrManualTrnsLevelHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_ID")
    private Long empId;
    @OneToMany(mappedBy = "hrManualTrnsLevelHd")
    private List<HrManualTrnsLevelDt> hrManualTrnsLevelDtList;

    public HrManualTrnsLevelHd() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public List<HrManualTrnsLevelDt> getHrManualTrnsLevelDtList() {
        return hrManualTrnsLevelDtList;
    }

    public void setHrManualTrnsLevelDtList(List<HrManualTrnsLevelDt> hrManualTrnsLevelDtList) {
        this.hrManualTrnsLevelDtList = hrManualTrnsLevelDtList;
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
        if (!(object instanceof HrManualTrnsLevelHd)) {
            return false;
        }
        HrManualTrnsLevelHd other = (HrManualTrnsLevelHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrManualTrnsLevelHd[id=" + id + "]";
    }

}

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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_BANK_BRANCH", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrBankBranch.findAll", query = "SELECT h FROM HrBankBranch h"),
    @NamedQuery(name = "HrBankBranch.findById", query = "SELECT h FROM HrBankBranch h WHERE h.id = :id"),
    @NamedQuery(name = "HrBankBranch.findByName", query = "SELECT h FROM HrBankBranch h WHERE h.name = :name")})
public class HrBankBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy="hrBankBranch")
    private List<HrEmpHd> hrEmpHdList;
    public HrBankBranch() {
    }

    public List<HrEmpHd> getHrEmpHdList() {
        return hrEmpHdList;
    }

    public void setHrEmpHdList(List<HrEmpHd> hrEmpHdList) {
        this.hrEmpHdList = hrEmpHdList;
    }

    public HrBankBranch(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof HrBankBranch)) {
            return false;
        }
        HrBankBranch other = (HrBankBranch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrBankBranch[id=" + id + "]";
    }

}

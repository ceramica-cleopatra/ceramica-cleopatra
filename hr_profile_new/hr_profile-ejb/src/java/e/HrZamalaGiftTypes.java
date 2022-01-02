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
@Table(name = "HR_ZAMALA_GIFT_TYPES", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrZamalaGiftTypes.findAll", query = "SELECT h FROM HrZamalaGiftTypes h"),
    @NamedQuery(name = "HrZamalaGiftTypes.findById", query = "SELECT h FROM HrZamalaGiftTypes h WHERE h.id = :id"),
    @NamedQuery(name = "HrZamalaGiftTypes.findByName", query = "SELECT h FROM HrZamalaGiftTypes h WHERE h.name = :name"),
    @NamedQuery(name = "HrZamalaGiftTypes.findByAmount", query = "SELECT h FROM HrZamalaGiftTypes h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrZamalaGiftTypes.findByEmpStatus", query = "SELECT h FROM HrZamalaGiftTypes h WHERE h.empStatus = :empStatus"),
    @NamedQuery(name = "HrZamalaGiftTypes.findByStatus", query = "SELECT h FROM HrZamalaGiftTypes h WHERE h.status = :status")})
public class HrZamalaGiftTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "EMP_STATUS")
    private Character empStatus;
    @Column(name = "STATUS")
    private Character status;
    @OneToMany(mappedBy = "hrZamalaGiftTypes")
    private List<HrZamalaGiftSrfHd> hrZamalaGiftSrfHdList;

    public HrZamalaGiftTypes() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Character getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(Character empStatus) {
        this.empStatus = empStatus;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public List<HrZamalaGiftSrfHd> getHrZamalaGiftSrfHdList() {
        return hrZamalaGiftSrfHdList;
    }

    public void setHrZamalaGiftSrfHdList(List<HrZamalaGiftSrfHd> hrZamalaGiftSrfHdList) {
        this.hrZamalaGiftSrfHdList = hrZamalaGiftSrfHdList;
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
        if (!(object instanceof HrZamalaGiftTypes)) {
            return false;
        }
        HrZamalaGiftTypes other = (HrZamalaGiftTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrZamalaGiftTypes[id=" + id + "]";
    }

}

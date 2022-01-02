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
@Table(name = "HR_PLUS_PAYMENT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrPlusPayment.findAll", query = "SELECT h FROM HrPlusPayment h"),
    @NamedQuery(name = "HrPlusPayment.findById", query = "SELECT h FROM HrPlusPayment h WHERE h.id = :id"),
    @NamedQuery(name = "HrPlusPayment.findByTrnsMonth", query = "SELECT h FROM HrPlusPayment h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrPlusPayment.findByTrnsYear", query = "SELECT h FROM HrPlusPayment h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrPlusPayment.findByNotes", query = "SELECT h FROM HrPlusPayment h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrPlusPayment.findByName", query = "SELECT h FROM HrPlusPayment h WHERE h.name = :name")})
public class HrPlusPayment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_MONTH")
    private Short trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Short trnsYear;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "hrPlusPayment")
    private List<HrPlusPaymentDt> hrPlusPaymentDtList;

    public HrPlusPayment() {
    }

    public HrPlusPayment(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(Short trnsMonth) {
        this.trnsMonth = trnsMonth;
    }

    public Short getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Short trnsYear) {
        this.trnsYear = trnsYear;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HrPlusPaymentDt> getHrPlusPaymentDtList() {
        return hrPlusPaymentDtList;
    }

    public void setHrPlusPaymentDtList(List<HrPlusPaymentDt> hrPlusPaymentDtList) {
        this.hrPlusPaymentDtList = hrPlusPaymentDtList;
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
        if (!(object instanceof HrPlusPayment)) {
            return false;
        }
        HrPlusPayment other = (HrPlusPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrPlusPayment[id=" + id + "]";
    }

}

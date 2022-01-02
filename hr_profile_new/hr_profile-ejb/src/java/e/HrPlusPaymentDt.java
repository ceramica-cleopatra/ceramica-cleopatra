/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_PLUS_PAYMENT_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrPlusPaymentDt.findAll", query = "SELECT h FROM HrPlusPaymentDt h"),
    @NamedQuery(name = "HrPlusPaymentDt.findById", query = "SELECT h FROM HrPlusPaymentDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrPlusPaymentDt.findByTotSal", query = "SELECT h FROM HrPlusPaymentDt h WHERE h.totSal = :totSal"),
    @NamedQuery(name = "HrPlusPaymentDt.findByTotPay", query = "SELECT h FROM HrPlusPaymentDt h WHERE h.totPay = :totPay"),
    @NamedQuery(name = "HrPlusPaymentDt.findByNotes", query = "SELECT h FROM HrPlusPaymentDt h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrPlusPaymentDt.findByTotHours", query = "SELECT h FROM HrPlusPaymentDt h WHERE h.totHours = :totHours")})
public class HrPlusPaymentDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TOT_SAL")
    private BigDecimal totSal;
    @Column(name = "TOT_PAY")
    private BigDecimal totPay;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "TOT_HOURS")
    private BigDecimal totHours;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrPlusPayment hrPlusPayment;

    public HrPlusPaymentDt() {
    }

    public HrPlusPaymentDt(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotSal() {
        return totSal;
    }

    public void setTotSal(BigDecimal totSal) {
        this.totSal = totSal;
    }

    public BigDecimal getTotPay() {
        return totPay;
    }

    public void setTotPay(BigDecimal totPay) {
        this.totPay = totPay;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getTotHours() {
        return totHours;
    }

    public void setTotHours(BigDecimal totHours) {
        this.totHours = totHours;
    }

    public HrPlusPayment getHrPlusPayment() {
        return hrPlusPayment;
    }

    public void setHrPlusPayment(HrPlusPayment hrPlusPayment) {
        this.hrPlusPayment = hrPlusPayment;
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
        if (!(object instanceof HrPlusPaymentDt)) {
            return false;
        }
        HrPlusPaymentDt other = (HrPlusPaymentDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrPlusPaymentDt[id=" + id + "]";
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author a.abbas
 */
@Entity
@Table(name = "HR_ADVANCE_ZAMALA_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrAdvanceZamalaDt.findAll", query = "SELECT h FROM HrAdvanceZamalaDt h where (h.hrAdvanceZamalaHd.canceled='N' or h.hrAdvanceZamalaHd.canceled is null) "
    + " and h.hrAdvanceZamalaHd.empNo=:emp_no order by h.id"),
    @NamedQuery(name = "HrAdvanceZamalaDt.findAllForGuarantee", query = "SELECT h FROM HrAdvanceZamalaDt h where (h.hrAdvanceZamalaHd.canceled='N' or h.hrAdvanceZamalaHd.canceled is null) "
    + " and h.hrAdvanceZamalaHd.guaranteeNo=:emp_no and (h.payDone='N' or h.payDone is null) order by h.id"),
    @NamedQuery(name = "HrAdvanceZamalaDt.findById", query = "SELECT h FROM HrAdvanceZamalaDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrAdvanceZamalaDt.findByAmount", query = "SELECT h FROM HrAdvanceZamalaDt h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrAdvanceZamalaDt.findByPayMonth", query = "SELECT h FROM HrAdvanceZamalaDt h WHERE h.payMonth = :payMonth"),
    @NamedQuery(name = "HrAdvanceZamalaDt.findByPayYear", query = "SELECT h FROM HrAdvanceZamalaDt h WHERE h.payYear = :payYear"),
    @NamedQuery(name = "HrAdvanceZamalaDt.findByPayDone", query = "SELECT h FROM HrAdvanceZamalaDt h WHERE h.payDone = :payDone"),
    @NamedQuery(name = "HrAdvanceZamalaDt.findByDelay", query = "SELECT h FROM HrAdvanceZamalaDt h WHERE h.delay = :delay"),
    @NamedQuery(name = "HrAdvanceZamalaDt.findByCash", query = "SELECT h FROM HrAdvanceZamalaDt h WHERE h.cash = :cash"),
    @NamedQuery(name = "HrAdvanceZamalaDt.findByCashAmount", query = "SELECT h FROM HrAdvanceZamalaDt h WHERE h.cashAmount = :cashAmount")})
public class HrAdvanceZamalaDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(generator="HR_ADVANCE_ZAMALA_DT_SEQ",strategy=GenerationType.SEQUENCE)
    @Id
    @SequenceGenerator(name="HR_ADVANCE_ZAMALA_DT_SEQ",sequenceName="HR_ADVANCE_ZAMALA_DT_SEQ",initialValue=1,allocationSize=1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "PAY_MONTH")
    private Long payMonth;
    @Column(name = "PAY_YEAR")
    private Long payYear;
    @Column(name = "PAY_DONE")
    private Character payDone;
    @Column(name = "DELAY")
    private Character delay;
    @Column(name = "CASH")
    private Character cash;
    @Column(name = "CASH_AMOUNT")
    private Long cashAmount;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrAdvanceZamalaHd hrAdvanceZamalaHd;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Long cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(Long payMonth) {
        this.payMonth = payMonth;
    }

    public Long getPayYear() {
        return payYear;
    }

    public void setPayYear(Long payYear) {
        this.payYear = payYear;
    }

    
    public HrAdvanceZamalaDt() {
    }

    public Character getPayDone() {
        return payDone;
    }

    public void setPayDone(Character payDone) {
        this.payDone = payDone;
    }

    public Character getDelay() {
        return delay;
    }

    public void setDelay(Character delay) {
        this.delay = delay;
    }

    public Character getCash() {
        return cash;
    }

    public void setCash(Character cash) {
        this.cash = cash;
    }

    public HrAdvanceZamalaHd getHrAdvanceZamalaHd() {
        return hrAdvanceZamalaHd;
    }

    public void setHrAdvanceZamalaHd(HrAdvanceZamalaHd hrAdvanceZamalaHd) {
        this.hrAdvanceZamalaHd = hrAdvanceZamalaHd;
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
        if (!(object instanceof HrAdvanceZamalaDt)) {
            return false;
        }
        HrAdvanceZamalaDt other = (HrAdvanceZamalaDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrAdvanceZamalaDt[id=" + id + "]";
    }

}

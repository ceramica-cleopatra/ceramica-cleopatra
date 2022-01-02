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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_BORROW_ZAMALA_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrBorrowZamalaDt.findAll", query = "SELECT h FROM HrBorrowZamalaDt h"),
    @NamedQuery(name = "HrBorrowZamalaDt.findById", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByPayMonth", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.payMonth = :payMonth"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByPayYear", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.payYear = :payYear"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByNotes", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByPayAmount", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.payAmount = :payAmount"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByPayDone", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.payDone = :payDone"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByDelay", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.delay = :delay"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByGeneralDelay", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.generalDelay = :generalDelay"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByMonetary", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.monetary = :monetary"),
    @NamedQuery(name = "HrBorrowZamalaDt.findBySalary", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.salary = :salary"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByReward", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.reward = :reward"),
    @NamedQuery(name = "HrBorrowZamalaDt.findByReturned", query = "SELECT h FROM HrBorrowZamalaDt h WHERE h.returned = :returned")})
public class HrBorrowZamalaDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PAY_MONTH")
    private Long payMonth;
    @Column(name = "PAY_YEAR")
    private Long payYear;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "PAY_AMOUNT")
    private Long payAmount;
    @Column(name = "PAY_DONE")
    private Character payDone;
    @Column(name = "DELAY")
    private Character delay;
    @Column(name = "GENERAL_DELAY")
    private Character generalDelay;
    @Column(name = "MONETARY")
    private Character monetary;
    @Column(name = "SALARY")
    private Character salary;
    @Column(name = "REWARD")
    private Character reward;
    @Column(name = "RETURNED")
    private Character returned;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrBorrowZamalaHd hrBorrowZamalaHd;

    public HrBorrowZamalaDt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
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
   

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Character getGeneralDelay() {
        return generalDelay;
    }

    public void setGeneralDelay(Character generalDelay) {
        this.generalDelay = generalDelay;
    }

    public Character getMonetary() {
        return monetary;
    }

    public void setMonetary(Character monetary) {
        this.monetary = monetary;
    }

    public Character getSalary() {
        return salary;
    }

    public void setSalary(Character salary) {
        this.salary = salary;
    }

    public Character getReward() {
        return reward;
    }

    public void setReward(Character reward) {
        this.reward = reward;
    }

    public Character getReturned() {
        return returned;
    }

    public void setReturned(Character returned) {
        this.returned = returned;
    }

    public HrBorrowZamalaHd getHrBorrowZamalaHd() {
        return hrBorrowZamalaHd;
    }

    public void setHrBorrowZamalaHd(HrBorrowZamalaHd hrBorrowZamalaHd) {
        this.hrBorrowZamalaHd = hrBorrowZamalaHd;
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
        if (!(object instanceof HrBorrowZamalaDt)) {
            return false;
        }
        HrBorrowZamalaDt other = (HrBorrowZamalaDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrBorrowZamalaDt[id=" + id + "]";
    }

}

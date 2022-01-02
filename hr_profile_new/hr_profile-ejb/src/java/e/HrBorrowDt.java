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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_BORROW_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrBorrowDt.findAll", query = "SELECT o FROM HrBorrowDt o where o.payAmount<>0 order by o.payYear,o.payMonth"),
    @NamedQuery(name = "HrBorrowDt.findById", query = "SELECT h FROM HrBorrowDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrBorrowDt.findByPayMonth", query = "SELECT h FROM HrBorrowDt h WHERE h.payMonth = :payMonth"),
    @NamedQuery(name = "HrBorrowDt.findByPayYear", query = "SELECT h FROM HrBorrowDt h WHERE h.payYear = :payYear"),
    @NamedQuery(name = "HrBorrowDt.findByNotes", query = "SELECT h FROM HrBorrowDt h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrBorrowDt.findByPayAmount", query = "SELECT h FROM HrBorrowDt h WHERE h.payAmount = :payAmount"),
    @NamedQuery(name = "HrBorrowDt.findByPayDone", query = "SELECT h FROM HrBorrowDt h WHERE h.payDone = :payDone"),
    @NamedQuery(name = "HrBorrowDt.findByDelay", query = "SELECT h FROM HrBorrowDt h WHERE h.delay = :delay"),
    @NamedQuery(name = "HrBorrowDt.findByGeneralDelay", query = "SELECT h FROM HrBorrowDt h WHERE h.generalDelay = :generalDelay"),
    @NamedQuery(name = "HrBorrowDt.findByMonetary", query = "SELECT h FROM HrBorrowDt h WHERE h.monetary = :monetary"),
    @NamedQuery(name = "HrBorrowDt.findBySalary", query = "SELECT h FROM HrBorrowDt h WHERE h.salary = :salary"),
    @NamedQuery(name = "HrBorrowDt.findByReward", query = "SELECT h FROM HrBorrowDt h WHERE h.reward = :reward")})
public class HrBorrowDt implements Serializable {
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
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrBorrowHd hrBorrowHd;

    public HrBorrowDt() {
    }

    public HrBorrowDt(Long id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
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

    public HrBorrowHd getHrBorrowHd() {
        return hrBorrowHd;
    }

    public void setHrBorrowHd(HrBorrowHd hrBorrowHd) {
        this.hrBorrowHd = hrBorrowHd;
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
        if (!(object instanceof HrBorrowDt)) {
            return false;
        }
        HrBorrowDt other = (HrBorrowDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrBorrowDt[id=" + id + "]";
    }

}

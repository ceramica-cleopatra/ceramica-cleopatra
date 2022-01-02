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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_FUND_BORROW_SETUP", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrFundBorrowSetup.findAll", query = "SELECT h FROM HrFundBorrowSetup h"),
    @NamedQuery(name = "HrFundBorrowSetup.findByResponsibleCode", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.responsibleCode = :responsibleCode"),
    @NamedQuery(name = "HrFundBorrowSetup.findByMaxMonths", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.maxMonths = :maxMonths"),
    @NamedQuery(name = "HrFundBorrowSetup.findByEmpHirePeriod", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.empHirePeriod = :empHirePeriod"),
    @NamedQuery(name = "HrFundBorrowSetup.findByBorrowNotPaied", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.borrowNotPaied = :borrowNotPaied"),
    @NamedQuery(name = "HrFundBorrowSetup.findByLastPaiedBorrowBeforeYear", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.lastPaiedBorrowBeforeYear = :lastPaiedBorrowBeforeYear"),
    @NamedQuery(name = "HrFundBorrowSetup.findByBorrowNotPaiedG1", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.borrowNotPaiedG1 = :borrowNotPaiedG1"),
    @NamedQuery(name = "HrFundBorrowSetup.findByBorrowGuaranteeG1", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.borrowGuaranteeG1 = :borrowGuaranteeG1"),
    @NamedQuery(name = "HrFundBorrowSetup.findByBorrowNotPaiedG2", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.borrowNotPaiedG2 = :borrowNotPaiedG2"),
    @NamedQuery(name = "HrFundBorrowSetup.findByBorrowGuaranteeG2", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.borrowGuaranteeG2 = :borrowGuaranteeG2"),
    @NamedQuery(name = "HrFundBorrowSetup.findByMaxTotalBorrowsLimit", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.maxTotalBorrowsLimit = :maxTotalBorrowsLimit"),
    @NamedQuery(name = "HrFundBorrowSetup.findByBorrowAmountLimit", query = "SELECT h FROM HrFundBorrowSetup h WHERE h.borrowAmountLimit = :borrowAmountLimit")})
public class HrFundBorrowSetup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RESPONSIBLE_CODE")
    private Long responsibleCode;
    @Column(name = "MAX_MONTHS")
    private Character maxMonths;
    @Column(name = "EMP_HIRE_PERIOD")
    private Character empHirePeriod;
    @Column(name = "BORROW_NOT_PAIED")
    private Character borrowNotPaied;
    @Column(name = "LAST_PAIED_BORROW_BEFORE_YEAR")
    private Character lastPaiedBorrowBeforeYear;
    @Column(name = "BORROW_NOT_PAIED_G1")
    private Character borrowNotPaiedG1;
    @Column(name = "BORROW_GUARANTEE_G1")
    private Character borrowGuaranteeG1;
    @Column(name = "BORROW_NOT_PAIED_G2")
    private Character borrowNotPaiedG2;
    @Column(name = "BORROW_GUARANTEE_G2")
    private Character borrowGuaranteeG2;
    @Column(name = "MAX_TOTAL_BORROWS_LIMIT")
    private Character maxTotalBorrowsLimit;
    @Column(name = "BORROW_AMOUNT_LIMIT")
    private Character borrowAmountLimit;
    @Column(name = "RESPONSIBLE2")
    private Long responsible2;
    @Column(name = "RESPONSIBLE3")
    private Long responsible3;
    public HrFundBorrowSetup() {
    }

    public Long getResponsibleCode() {
        return responsibleCode;
    }

    public void setResponsibleCode(Long responsibleCode) {
        this.responsibleCode = responsibleCode;
    }

    public Character getMaxMonths() {
        return maxMonths;
    }

    public void setMaxMonths(Character maxMonths) {
        this.maxMonths = maxMonths;
    }

    public Character getEmpHirePeriod() {
        return empHirePeriod;
    }

    public void setEmpHirePeriod(Character empHirePeriod) {
        this.empHirePeriod = empHirePeriod;
    }

    public Character getBorrowNotPaied() {
        return borrowNotPaied;
    }

    public void setBorrowNotPaied(Character borrowNotPaied) {
        this.borrowNotPaied = borrowNotPaied;
    }

    public Character getLastPaiedBorrowBeforeYear() {
        return lastPaiedBorrowBeforeYear;
    }

    public void setLastPaiedBorrowBeforeYear(Character lastPaiedBorrowBeforeYear) {
        this.lastPaiedBorrowBeforeYear = lastPaiedBorrowBeforeYear;
    }

    public Character getBorrowNotPaiedG1() {
        return borrowNotPaiedG1;
    }

    public void setBorrowNotPaiedG1(Character borrowNotPaiedG1) {
        this.borrowNotPaiedG1 = borrowNotPaiedG1;
    }

    public Character getBorrowGuaranteeG1() {
        return borrowGuaranteeG1;
    }

    public void setBorrowGuaranteeG1(Character borrowGuaranteeG1) {
        this.borrowGuaranteeG1 = borrowGuaranteeG1;
    }

    public Character getBorrowNotPaiedG2() {
        return borrowNotPaiedG2;
    }

    public void setBorrowNotPaiedG2(Character borrowNotPaiedG2) {
        this.borrowNotPaiedG2 = borrowNotPaiedG2;
    }

    public Character getBorrowGuaranteeG2() {
        return borrowGuaranteeG2;
    }

    public void setBorrowGuaranteeG2(Character borrowGuaranteeG2) {
        this.borrowGuaranteeG2 = borrowGuaranteeG2;
    }

    public Character getMaxTotalBorrowsLimit() {
        return maxTotalBorrowsLimit;
    }

    public void setMaxTotalBorrowsLimit(Character maxTotalBorrowsLimit) {
        this.maxTotalBorrowsLimit = maxTotalBorrowsLimit;
    }

    public Character getBorrowAmountLimit() {
        return borrowAmountLimit;
    }

    public void setBorrowAmountLimit(Character borrowAmountLimit) {
        this.borrowAmountLimit = borrowAmountLimit;
    }

    public Long getResponsible2() {
        return responsible2;
    }

    public void setResponsible2(Long responsible2) {
        this.responsible2 = responsible2;
    }

    public Long getResponsible3() {
        return responsible3;
    }

    public void setResponsible3(Long responsible3) {
        this.responsible3 = responsible3;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responsibleCode != null ? responsibleCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrFundBorrowSetup)) {
            return false;
        }
        HrFundBorrowSetup other = (HrFundBorrowSetup) object;
        if ((this.responsibleCode == null && other.responsibleCode != null) || (this.responsibleCode != null && !this.responsibleCode.equals(other.responsibleCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrFundBorrowSetup[responsibleCode=" + responsibleCode + "]";
    }

}

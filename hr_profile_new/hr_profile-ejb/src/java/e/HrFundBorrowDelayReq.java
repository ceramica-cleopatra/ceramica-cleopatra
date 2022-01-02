/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed
 */
@Entity
@Table(name = "HR_FUND_BORROW_DELAY_REQ", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrFundBorrowDelayReq.findAll", query = "SELECT h FROM HrFundBorrowDelayReq h"),
    @NamedQuery(name = "HrFundBorrowDelayReq.findById", query = "SELECT h FROM HrFundBorrowDelayReq h WHERE h.id = :id"),
    @NamedQuery(name = "HrFundBorrowDelayReq.findByBorrowNo", query = "SELECT h FROM HrFundBorrowDelayReq h WHERE h.borrowNo = :borrowNo"),
    @NamedQuery(name = "HrFundBorrowDelayReq.findByDelayMonth", query = "SELECT h FROM HrFundBorrowDelayReq h WHERE h.delayMonth = :delayMonth"),
    @NamedQuery(name = "HrFundBorrowDelayReq.findByDelayYear", query = "SELECT h FROM HrFundBorrowDelayReq h WHERE h.delayYear = :delayYear"),
    @NamedQuery(name = "HrFundBorrowDelayReq.findByTrnsDate", query = "SELECT h FROM HrFundBorrowDelayReq h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrFundBorrowDelayReq.findByEmpNo", query = "SELECT h FROM HrFundBorrowDelayReq h WHERE h.empNo = :empNo")})
public class HrFundBorrowDelayReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_BORROW_FUND_REQUEST_SEQ", sequenceName="HR_BORROW_FUND_REQUEST_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_BORROW_FUND_REQUEST_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "BORROW_NO")
    private Long borrowNo;
    @Column(name = "DELAY_MONTH")
    private Long delayMonth;
    @Column(name = "DELAY_YEAR")
    private Long delayYear;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "EMP_NO")
    private Long empNo;

    public HrFundBorrowDelayReq() {
    }

    
    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Long getBorrowNo() {
        return borrowNo;
    }

    public void setBorrowNo(Long borrowNo) {
        this.borrowNo = borrowNo;
    }

    public Long getDelayMonth() {
        return delayMonth;
    }

    public void setDelayMonth(Long delayMonth) {
        this.delayMonth = delayMonth;
    }

    public Long getDelayYear() {
        return delayYear;
    }

    public void setDelayYear(Long delayYear) {
        this.delayYear = delayYear;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof HrFundBorrowDelayReq)) {
            return false;
        }
        HrFundBorrowDelayReq other = (HrFundBorrowDelayReq) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrFundBorrowDelayReq[id=" + id + "]";
    }

}

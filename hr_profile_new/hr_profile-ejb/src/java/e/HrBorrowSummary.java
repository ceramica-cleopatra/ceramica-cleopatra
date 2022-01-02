/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_BORROW_SUMMARY", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrBorrowSummary.findAll", query = "SELECT h FROM HrBorrowSummary h"),
    @NamedQuery(name = "HrBorrowSummary.findByRownum", query = "SELECT h FROM HrBorrowSummary h WHERE h.rownum = :rownum"),
    @NamedQuery(name = "HrBorrowSummary.findByCntPaydone", query = "SELECT h FROM HrBorrowSummary h WHERE h.cntPaydone = :cntPaydone"),
    @NamedQuery(name = "HrBorrowSummary.findByCntMonthes", query = "SELECT h FROM HrBorrowSummary h WHERE h.cntMonthes = :cntMonthes"),
    @NamedQuery(name = "HrBorrowSummary.findBySumNotpayed", query = "SELECT h FROM HrBorrowSummary h WHERE h.sumNotpayed = :sumNotpayed"),
    @NamedQuery(name = "HrBorrowSummary.findBySumPayamount", query = "SELECT h FROM HrBorrowSummary h WHERE h.sumPayamount = :sumPayamount")})
public class HrBorrowSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ROWNUM")
    private Long rownum;
    @Basic(optional = false)
    
    @Column(name = "CNT_PAYDONE")
    private BigInteger cntPaydone;
    @Column(name = "CNT_MONTHES")
    private BigInteger cntMonthes;
    @Column(name = "SUM_NOTPAYED")
    private BigInteger sumNotpayed;
    @Column(name = "SUM_PAYAMOUNT")
    private BigInteger sumPayamount;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrBorrowHd x;
    public HrBorrowSummary() {
    }

    public HrBorrowHd getX() {
        return x;
    }

    public void setX(HrBorrowHd x) {
        this.x = x;
    }


    public Long getRownum() {
        return rownum;
    }

    public void setRownum(Long rownum) {
        this.rownum = rownum;
    }

    public BigInteger getCntPaydone() {
        return cntPaydone;
    }

    public void setCntPaydone(BigInteger cntPaydone) {
        this.cntPaydone = cntPaydone;
    }

    public BigInteger getCntMonthes() {
        return cntMonthes;
    }

    public void setCntMonthes(BigInteger cntMonthes) {
        this.cntMonthes = cntMonthes;
    }

    public BigInteger getSumNotpayed() {
        return sumNotpayed;
    }

    public void setSumNotpayed(BigInteger sumNotpayed) {
        this.sumNotpayed = sumNotpayed;
    }

    public BigInteger getSumPayamount() {
        return sumPayamount;
    }

    public void setSumPayamount(BigInteger sumPayamount) {
        this.sumPayamount = sumPayamount;
    }

}

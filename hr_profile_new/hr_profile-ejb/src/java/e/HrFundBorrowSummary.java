/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_FUND_BORROW_SUMMARY")
@NamedQueries({
    @NamedQuery(name = "HrFundBorrowSummary.findAll", query = "SELECT h FROM HrFundBorrowSummary h"),
    @NamedQuery(name = "HrFundBorrowSummary.findByHdId", query = "SELECT h FROM HrFundBorrowSummary h where h.x=:hd_id"),
    @NamedQuery(name = "HrFundBorrowSummary.findByRownum", query = "SELECT h FROM HrFundBorrowSummary h WHERE h.rownum = :rownum"),
    @NamedQuery(name = "HrFundBorrowSummary.findByCntPaydone", query = "SELECT h FROM HrFundBorrowSummary h WHERE h.cntPaydone = :cntPaydone"),
    @NamedQuery(name = "HrFundBorrowSummary.findByCntMonthes", query = "SELECT h FROM HrFundBorrowSummary h WHERE h.cntMonthes = :cntMonthes"),
    @NamedQuery(name = "HrFundBorrowSummary.findBySumNotpayed", query = "SELECT h FROM HrFundBorrowSummary h WHERE h.sumNotpayed = :sumNotpayed"),
    @NamedQuery(name = "HrFundBorrowSummary.findBySumPayamount", query = "SELECT h FROM HrFundBorrowSummary h WHERE h.sumPayamount = :sumPayamount")})
public class HrFundBorrowSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ROWNUM")
    private Long rownum;
    @Column(name = "CNT_PAYDONE")
    private Long cntPaydone;
    @Column(name = "CNT_MONTHES")
    private Long cntMonthes;
    @Column(name = "SUM_NOTPAYED")
    private Long sumNotpayed;
    @Column(name = "SUM_PAYAMOUNT")
    private Long sumPayamount;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrBorrowZamalaHd x;
    public HrFundBorrowSummary() {
    }

    public Long getCntMonthes() {
        return cntMonthes;
    }

    public void setCntMonthes(Long cntMonthes) {
        this.cntMonthes = cntMonthes;
    }

    public Long getCntPaydone() {
        return cntPaydone;
    }

    public void setCntPaydone(Long cntPaydone) {
        this.cntPaydone = cntPaydone;
    }

    public Long getRownum() {
        return rownum;
    }

    public void setRownum(Long rownum) {
        this.rownum = rownum;
    }

    public Long getSumNotpayed() {
        return sumNotpayed;
    }

    public void setSumNotpayed(Long sumNotpayed) {
        this.sumNotpayed = sumNotpayed;
    }

    public Long getSumPayamount() {
        return sumPayamount;
    }

    public void setSumPayamount(Long sumPayamount) {
        this.sumPayamount = sumPayamount;
    }

    public HrBorrowZamalaHd getX() {
        return x;
    }

    public void setX(HrBorrowZamalaHd x) {
        this.x = x;
    }
    
    
    
}

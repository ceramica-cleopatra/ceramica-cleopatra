/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "HR_AUTH_AND_HOL_BALANCE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrAuthAndHolBalance.findAll", query = "SELECT h FROM HrAuthAndHolBalance h"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByEmpNo", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByInsteadHolRemind", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.insteadHolRemind = :insteadHolRemind"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByNormalHolRemind", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.normalHolRemind = :normalHolRemind"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByInsteadHolUsed", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.insteadHolUsed = :insteadHolUsed"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByOpposedHolUsed", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.opposedHolUsed = :opposedHolUsed"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByTotalNormalHol", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.totalNormalHol = :totalNormalHol"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByNormalHolUsed", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.normalHolUsed = :normalHolUsed"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByAnnualHolUsed", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.annualHolUsed = :annualHolUsed"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByTotAuthUsed", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.totAuthUsed = :totAuthUsed"),
    @NamedQuery(name = "HrAuthAndHolBalance.findByTotBindingAuth", query = "SELECT h FROM HrAuthAndHolBalance h WHERE h.totBindingAuth = :totBindingAuth")})
public class HrAuthAndHolBalance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private long empNo;
    @Column(name = "INSTEAD_HOL_REMIND")
    private Long insteadHolRemind;
    @Column(name = "NORMAL_HOL_REMIND")
    private Long normalHolRemind;
    @Column(name = "INSTEAD_HOL_USED")
    private Long insteadHolUsed;
    @Column(name = "OPPOSED_HOL_USED")
    private Long opposedHolUsed;
    @Column(name = "TOTAL_NORMAL_HOL")
    private Long totalNormalHol;
    @Column(name = "EXCEPTIONAL_HOL")
    private Long totalExceptionalHol;
    @Column(name = "EXCEPTIONAL_HOL_USED")
    private Long exceptionalHolUsed;
    @Column(name = "NORMAL_HOL_USED")
    private Long normalHolUsed;
    @Column(name = "ANNUAL_HOL_USED")
    private Long annualHolUsed;
    @Column(name = "TOT_AUTH_USED")
    private Long totAuthUsed;
    @Column(name = "TOT_BINDING_AUTH")
    private Long totBindingAuth;
    @Column(name="PREV_MONTH_AUTH_USED_CNT")
    private Long PrevMonthAuthUsedCnt;
    @Column(name="PREV_MONTH_BINDING_AUTH_CNT")
    private Long prevMonthBindingAuthCnt;
    public HrAuthAndHolBalance() {
    }

    public long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(long empNo) {
        this.empNo = empNo;
    }

    public Long getAnnualHolUsed() {
        return annualHolUsed;
    }

    public void setAnnualHolUsed(Long annualHolUsed) {
        this.annualHolUsed = annualHolUsed;
    }

    public Long getInsteadHolRemind() {
        return insteadHolRemind;
    }

    public void setInsteadHolRemind(Long insteadHolRemind) {
        this.insteadHolRemind = insteadHolRemind;
    }

    public Long getInsteadHolUsed() {
        return insteadHolUsed;
    }

    public void setInsteadHolUsed(Long insteadHolUsed) {
        this.insteadHolUsed = insteadHolUsed;
    }

    public Long getNormalHolRemind() {
        return normalHolRemind;
    }

    public void setNormalHolRemind(Long normalHolRemind) {
        this.normalHolRemind = normalHolRemind;
    }

    public Long getNormalHolUsed() {
        return normalHolUsed;
    }

    public void setNormalHolUsed(Long normalHolUsed) {
        this.normalHolUsed = normalHolUsed;
    }

    public Long getOpposedHolUsed() {
        return opposedHolUsed;
    }

    public void setOpposedHolUsed(Long opposedHolUsed) {
        this.opposedHolUsed = opposedHolUsed;
    }

    public Long getTotAuthUsed() {
        return totAuthUsed;
    }

    public void setTotAuthUsed(Long totAuthUsed) {
        this.totAuthUsed = totAuthUsed;
    }

    public Long getTotBindingAuth() {
        return totBindingAuth;
    }

    public void setTotBindingAuth(Long totBindingAuth) {
        this.totBindingAuth = totBindingAuth;
    }

    public Long getTotalNormalHol() {
        return totalNormalHol;
    }

    public void setTotalNormalHol(Long totalNormalHol) {
        this.totalNormalHol = totalNormalHol;
    }

    public Long getPrevMonthAuthUsedCnt() {
        return PrevMonthAuthUsedCnt;
    }

    public void setPrevMonthAuthUsedCnt(Long PrevMonthAuthUsedCnt) {
        this.PrevMonthAuthUsedCnt = PrevMonthAuthUsedCnt;
    }

    public Long getPrevMonthBindingAuthCnt() {
        return prevMonthBindingAuthCnt;
    }

    public void setPrevMonthBindingAuthCnt(Long prevMonthBindingAuthCnt) {
        this.prevMonthBindingAuthCnt = prevMonthBindingAuthCnt;
    }

    public Long getExceptionalHolUsed() {
        return exceptionalHolUsed;
    }

    public void setExceptionalHolUsed(Long exceptionalHolUsed) {
        this.exceptionalHolUsed = exceptionalHolUsed;
    }

    public Long getTotalExceptionalHol() {
        return totalExceptionalHol;
    }

    public void setTotalExceptionalHol(Long totalExceptionalHol) {
        this.totalExceptionalHol = totalExceptionalHol;
    }

}

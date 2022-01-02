/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_BORROW_ZAMALA_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrBorrowZamalaHd.findAll", query = "SELECT o FROM HrBorrowZamalaHd o where o.empNo=:emp_no and 1<=(select count(dt) from HrBorrowZamalaDt dt JOIN dt.hrBorrowZamalaHd hd where hd.empNo=:emp_no and dt.hrBorrowZamalaHd.id=o.id and (dt.payDone='N' or dt.payDone is null) and (dt.delay='N' or dt.delay is null) and (dt.generalDelay='N' or dt.generalDelay is null)) and (o.canceled='N' or o.canceled is null)"),
    @NamedQuery(name = "HrBorrowZamalaHd.findAllForGuarantee", query = "SELECT o FROM HrBorrowZamalaHd o where (o.guarantee1=:emp_no or o.guarantee2=:emp_no) and 1<=(select count(dt) from HrBorrowZamalaDt dt JOIN dt.hrBorrowZamalaHd hd where dt.hrBorrowZamalaHd.id=o.id and (dt.payDone='N' or dt.payDone is null) and (dt.delay='N' or dt.delay is null) and (dt.generalDelay='N' or dt.generalDelay is null)) and (o.canceled='N' or o.canceled is null)"),
    @NamedQuery(name = "HrBorrowZamalaHd.findById", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByNo", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.no = :no"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByTrnsDate", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByEmpNo", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.empNo = :empNo and h.canceled!='Y' order by h.no"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByGuarantee1", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.guarantee1 = :guarantee1"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByGuarantee2", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.guarantee2 = :guarantee2"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByBorrowAmount", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.borrowAmount = :borrowAmount"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByPayMonths", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.payMonths = :payMonths"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByStartDate", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.startDate = :startDate"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByNotes", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByLastSalary", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.lastSalary = :lastSalary"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByCanceled", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.canceled = :canceled"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByPrinted", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.printed = :printed"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByManager", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.manager = :manager"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByG1Phone", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.g1Phone = :g1Phone"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByG2Phone", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.g2Phone = :g2Phone"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByMPhone", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.mPhone = :mPhone"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByException", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.exception = :exception"),
    @NamedQuery(name = "HrBorrowZamalaHd.findByTyp", query = "SELECT h FROM HrBorrowZamalaHd h WHERE h.typ = :typ")})
public class HrBorrowZamalaHd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NO")
    private Long no;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @JoinColumn(name = "EMP_NO",referencedColumnName="EMP_NO")
    @ManyToOne
    private HrEmpInfo empNo;
    @ManyToOne
    @JoinColumn(name = "GUARANTEE1")
    private HrEmpInfo guarantee1;
    @ManyToOne
    @JoinColumn(name = "GUARANTEE2")
    private HrEmpInfo guarantee2;
    @Column(name = "BORROW_AMOUNT")
    private Long borrowAmount;
    @Column(name = "PAY_MONTHS")
    private Long payMonths;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "LAST_SALARY")
    private Long lastSalary;
    @Column(name = "CANCELED")
    private Character canceled;
    @Column(name = "PRINTED")
    private Character printed;
    @Column(name = "MANAGER")
    private BigInteger manager;
    @Column(name = "G1_PHONE")
    private String g1Phone;
    @Column(name = "G2_PHONE")
    private String g2Phone;
    @Column(name = "M_PHONE")
    private String mPhone;
    @Column(name = "EXCEPTION")
    private Character exception;
    @Column(name = "TYP")
    private BigInteger typ;
    @OneToMany(mappedBy = "hrBorrowZamalaHd")
    private List<HrBorrowZamalaDt> hrBorrowZamalaDtList;
    @OneToMany(mappedBy = "x")
    private List<HrFundBorrowSummary> listHrFundBorrowSummary;

    public HrBorrowZamalaHd() {
    }

    public Long getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(Long borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    public HrEmpInfo getGuarantee1() {
        return guarantee1;
    }

    public void setGuarantee1(HrEmpInfo guarantee1) {
        this.guarantee1 = guarantee1;
    }

    public HrEmpInfo getGuarantee2() {
        return guarantee2;
    }

    public void setGuarantee2(HrEmpInfo guarantee2) {
        this.guarantee2 = guarantee2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastSalary() {
        return lastSalary;
    }

    public void setLastSalary(Long lastSalary) {
        this.lastSalary = lastSalary;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getPayMonths() {
        return payMonths;
    }

    public void setPayMonths(Long payMonths) {
        this.payMonths = payMonths;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Character getCanceled() {
        return canceled;
    }

    public void setCanceled(Character canceled) {
        this.canceled = canceled;
    }

    public Character getPrinted() {
        return printed;
    }

    public void setPrinted(Character printed) {
        this.printed = printed;
    }

    public BigInteger getManager() {
        return manager;
    }

    public void setManager(BigInteger manager) {
        this.manager = manager;
    }

    public String getG1Phone() {
        return g1Phone;
    }

    public void setG1Phone(String g1Phone) {
        this.g1Phone = g1Phone;
    }

    public String getG2Phone() {
        return g2Phone;
    }

    public void setG2Phone(String g2Phone) {
        this.g2Phone = g2Phone;
    }

    public String getMPhone() {
        return mPhone;
    }

    public void setMPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public Character getException() {
        return exception;
    }

    public void setException(Character exception) {
        this.exception = exception;
    }

    public BigInteger getTyp() {
        return typ;
    }

    public void setTyp(BigInteger typ) {
        this.typ = typ;
    }

    public List<HrBorrowZamalaDt> getHrBorrowZamalaDtList() {
        Iterator<HrBorrowZamalaDt> l = hrBorrowZamalaDtList.iterator();
        while (l.hasNext()) {
            HrBorrowZamalaDt hbd = l.next();
            if (hbd.getPayAmount() == 0L) {
                l.remove();
            }
        }
        Collections.sort(hrBorrowZamalaDtList, new hrBorrowComerator());
        return hrBorrowZamalaDtList;
    }

    public void setHrBorrowZamalaDtList(List<HrBorrowZamalaDt> hrBorrowZamalaDtList) {
        this.hrBorrowZamalaDtList = hrBorrowZamalaDtList;
    }

    public List<HrFundBorrowSummary> getListHrFundBorrowSummary() {
        return listHrFundBorrowSummary;
    }

    public void setListHrFundBorrowSummary(List<HrFundBorrowSummary> listHrFundBorrowSummary) {
        this.listHrFundBorrowSummary = listHrFundBorrowSummary;
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
        if (!(object instanceof HrBorrowZamalaHd)) {
            return false;
        }
        HrBorrowZamalaHd other = (HrBorrowZamalaHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrBorrowZamalaHd[id=" + id + "]";
    }
}

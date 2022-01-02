/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_BORROW_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrBorrowHd.findAll", query = "SELECT o FROM HrBorrowHd o where o.empNo=:emp_no and 1<=(select count(dt) from HrBorrowDt dt JOIN dt.hrBorrowHd hd where hd.empNo=:emp_no and dt.hrBorrowHd=o and (dt.payDone='N' or dt.payDone is null) and (dt.delay='N' or dt.delay is null) and (dt.generalDelay='N' or dt.generalDelay is null)) and (o.canceled='N' or o.canceled is null)"),
    @NamedQuery(name = "HrBorrowHd.findById", query = "SELECT h FROM HrBorrowHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrBorrowHd.findByNo", query = "SELECT h FROM HrBorrowHd h WHERE h.no = :no"),
    @NamedQuery(name = "HrBorrowHd.findByTrnsDate", query = "SELECT h FROM HrBorrowHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrBorrowHd.findByEmpNo", query = "SELECT h FROM HrBorrowHd h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrBorrowHd.findByGuarantee1", query = "SELECT h FROM HrBorrowHd h WHERE h.guarantee1 = :guarantee1"),
    @NamedQuery(name = "HrBorrowHd.findByGuarantee2", query = "SELECT h FROM HrBorrowHd h WHERE h.guarantee2 = :guarantee2"),
    @NamedQuery(name = "HrBorrowHd.findByBorrowAmount", query = "SELECT h FROM HrBorrowHd h WHERE h.borrowAmount = :borrowAmount"),
    @NamedQuery(name = "HrBorrowHd.findByPayMonths", query = "SELECT h FROM HrBorrowHd h WHERE h.payMonths = :payMonths"),
    @NamedQuery(name = "HrBorrowHd.findByStartDate", query = "SELECT h FROM HrBorrowHd h WHERE h.startDate = :startDate"),
    @NamedQuery(name = "HrBorrowHd.findByNotes", query = "SELECT h FROM HrBorrowHd h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrBorrowHd.findByLastSalary", query = "SELECT h FROM HrBorrowHd h WHERE h.lastSalary = :lastSalary"),
    @NamedQuery(name = "HrBorrowHd.findByCanceled", query = "SELECT h FROM HrBorrowHd h WHERE h.canceled = :canceled"),
    @NamedQuery(name = "HrBorrowHd.findByPrinted", query = "SELECT h FROM HrBorrowHd h WHERE h.printed = :printed"),
    @NamedQuery(name = "HrBorrowHd.findByManager", query = "SELECT h FROM HrBorrowHd h WHERE h.manager = :manager"),
    @NamedQuery(name = "HrBorrowHd.findByG1Phone", query = "SELECT h FROM HrBorrowHd h WHERE h.g1Phone = :g1Phone"),
    @NamedQuery(name = "HrBorrowHd.findByG2Phone", query = "SELECT h FROM HrBorrowHd h WHERE h.g2Phone = :g2Phone"),
    @NamedQuery(name = "HrBorrowHd.findByMPhone", query = "SELECT h FROM HrBorrowHd h WHERE h.mPhone = :mPhone"),
    @NamedQuery(name = "HrBorrowHd.findByException", query = "SELECT h FROM HrBorrowHd h WHERE h.exception = :exception")})
public class HrBorrowHd implements Serializable {
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
    @Column(name = "EMP_NO")
    private Long empNo;
    @ManyToOne
    @JoinColumn(name="Guarantee1")
    private HrEmpInfo guarantee1;
    @ManyToOne
    @JoinColumn(name="Guarantee2")
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
    private Long manager;
    @Column(name = "G1_PHONE")
    private String g1Phone;
    @Column(name = "G2_PHONE")
    private String g2Phone;
    @Column(name = "M_PHONE")
    private String mPhone;
    @Column(name = "EXCEPTION")
    private Character exception;
    @OneToMany(mappedBy = "hrBorrowHd")
    private List<HrBorrowDt> hrBorrowDtList;
    @JoinColumn(name = "TYP", referencedColumnName = "ID")
    @ManyToOne
    private HrCutoffType hrCutoffType;
    @OneToMany(mappedBy = "x")
    private List<HrBorrowSummary> listHrBorrowSummary;
    public HrBorrowHd() {
    }

    public List<HrBorrowSummary> getListHrBorrowSummary() {
        return listHrBorrowSummary;
    }

    public void setListHrBorrowSummary(List<HrBorrowSummary> listHrBorrowSummary) {
        this.listHrBorrowSummary = listHrBorrowSummary;
    }


    public HrBorrowHd(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
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

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    

    public Long getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(Long borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public Long getPayMonths() {
        return payMonths;
    }

    public void setPayMonths(Long payMonths) {
        this.payMonths = payMonths;
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

    public List<HrBorrowDt> getHrBorrowDtList()
    {
    Iterator<HrBorrowDt> l=hrBorrowDtList.iterator();
    while(l.hasNext())
    {
        HrBorrowDt hbd=l.next();
        if(hbd.getPayAmount()==0L)
        {
         l.remove();
        }
    }
    Collections.sort(hrBorrowDtList, new hrBorrowComerator());
  return hrBorrowDtList;
 }

    public void setHrBorrowDtList(List<HrBorrowDt> hrBorrowDtList) {
        this.hrBorrowDtList = hrBorrowDtList;
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
        if (!(object instanceof HrBorrowHd)) {
            return false;
        }
        HrBorrowHd other = (HrBorrowHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public HrCutoffType getHrCutoffType() {
        return hrCutoffType;
    }

    public void setHrCutoffType(HrCutoffType hrCutoffType) {
        this.hrCutoffType = hrCutoffType;
    }

    @Override
    public String toString() {
        return "e.HrBorrowHd[id=" + id + "]";
    }

}

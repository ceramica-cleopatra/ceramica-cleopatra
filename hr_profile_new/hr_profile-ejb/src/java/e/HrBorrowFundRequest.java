/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */ 

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_BORROW_FUND_REQUEST", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrBorrowFundRequest.findAll", query = "SELECT h FROM HrBorrowFundRequest h"),
    @NamedQuery(name = "HrBorrowFundRequest.maxSerial", query = "SELECT max(h.serial) FROM HrBorrowFundRequest h"),
    @NamedQuery(name = "HrBorrowFundRequest.findById", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.id = :id"),
    @NamedQuery(name = "HrBorrowFundRequest.findRequestsNeedConfirm", query = "SELECT h FROM HrBorrowFundRequest h WHERE (h.cancel='N' or h.cancel is null) and h.deptMngConfirm='Y' and h.mngConfirm is null  order by h.serial asc"),
    @NamedQuery(name = "HrBorrowFundRequest.findByEmpNo", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.empNo = :empNo and (h.cancel='N' or h.cancel is null) and (h.empConfirm='Y' or h.empConfirm is null) order by h.reqDate desc"),
    @NamedQuery(name = "HrBorrowFundRequest.findByReqAmount", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.reqAmount = :reqAmount"),
    @NamedQuery(name = "HrBorrowFundRequest.findByReqMonths", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.reqMonths = :reqMonths"),
    @NamedQuery(name = "HrBorrowFundRequest.findByReqStart", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.reqStart = :reqStart"),
    @NamedQuery(name = "HrBorrowFundRequest.findByEmpConfirm", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.empConfirm = :empConfirm"),
    @NamedQuery(name = "HrBorrowFundRequest.findByReqDate", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.reqDate = :reqDate"),
    @NamedQuery(name = "HrBorrowFundRequest.findByEmpConfirmDate", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.empConfirmDate = :empConfirmDate"),
    @NamedQuery(name = "HrBorrowFundRequest.findByResAmount", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.resAmount = :resAmount"),
    @NamedQuery(name = "HrBorrowFundRequest.findByResMonths", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.resMonths = :resMonths"),
    @NamedQuery(name = "HrBorrowFundRequest.findByResStart", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.resStart = :resStart"),
    @NamedQuery(name = "HrBorrowFundRequest.findByMngNo", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrBorrowFundRequest.findByMngConfirm", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.mngConfirm = :mngConfirm"),
    @NamedQuery(name = "HrBorrowFundRequest.findByMngConfirmDate", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.mngConfirmDate = :mngConfirmDate"),
    @NamedQuery(name = "HrBorrowFundRequest.findByMngNotes", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.mngNotes = :mngNotes"),
    @NamedQuery(name = "HrBorrowFundRequest.findByGuarantee1", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.guarantee1 = :guarantee1"),
    @NamedQuery(name = "HrBorrowFundRequest.findByAllGuarantee", query = "SELECT h FROM HrBorrowFundRequest h WHERE (h.guarantee1 = :guarantee or h.guarantee2=:guarantee) and (h.cancel='N' or h.cancel is null)"),
    @NamedQuery(name = "HrBorrowFundRequest.findByGuarantee1Confirm", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.guarantee1Confirm = :guarantee1Confirm"),
    @NamedQuery(name = "HrBorrowFundRequest.findByGuarantee2", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.guarantee2 = :guarantee2"),
    @NamedQuery(name = "HrBorrowFundRequest.findByGuarantee2Confirm", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.guarantee2Confirm = :guarantee2Confirm"),
    @NamedQuery(name = "HrBorrowFundRequest.findByDeptMng", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.deptMng = :deptMng and (h.cancel='N' or h.cancel is null) and h.guarantee1Confirm='Y' and h.guarantee2Confirm='Y'"),
    @NamedQuery(name = "HrBorrowFundRequest.findByDeptMngConfirm", query = "SELECT h FROM HrBorrowFundRequest h WHERE h.deptMngConfirm = :deptMngConfirm")})
public class HrBorrowFundRequest implements Serializable,Cloneable{
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_BORROW_FUND_REQUEST_SEQ", sequenceName="HR_BORROW_FUND_REQUEST_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_BORROW_FUND_REQUEST_SEQ")

    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SERIAL")
    private Long serial;
    @ManyToOne
    @JoinColumn(name="EMP_NO")
    private HrEmpInfo empNo;
    @Column(name = "REQ_AMOUNT")
    private Long reqAmount;
    @Column(name = "REQ_MONTHS")
    private Long reqMonths;
    @Column(name = "REQ_START")
    @Temporal(TemporalType.DATE)
    private Calendar reqStart;
    @Column(name = "EMP_CONFIRM")
    private Character empConfirm;
    @Column(name = "REQ_DATE")
    @Temporal(TemporalType.DATE)
    private Date reqDate;
    @Column(name = "EMP_CONFIRM_DATE")
    @Temporal(TemporalType.DATE)
    private Date empConfirmDate;
    @Column(name = "RES_AMOUNT")
    private Long resAmount;
    @Column(name = "RES_MONTHS")
    private Long resMonths;
    @Column(name = "RES_START")
    @Temporal(TemporalType.DATE)
    private Date resStart;
    @ManyToOne
    @JoinColumn(name="MNG_NO")
    private HrEmpInfo mngNo;
    @Column(name = "MNG_CONFIRM")
    private Character mngConfirm;
    @Column(name = "MNG_CONFIRM_DATE")
    @Temporal(TemporalType.DATE)
    private Date mngConfirmDate;
    @Column(name = "MNG_NOTES")
    private String mngNotes;
    @ManyToOne
    @JoinColumn(name="Guarantee1")
    private HrEmpInfo guarantee1;
    @Column(name = "GUARANTEE1_CONFIRM")
    private Character guarantee1Confirm;
    @ManyToOne
    @JoinColumn(name="Guarantee2")
    private HrEmpInfo guarantee2;
    @Column(name = "GUARANTEE2_CONFIRM")
    private Character guarantee2Confirm;
    @ManyToOne
    @JoinColumn(name="DEPT_MNG")
    private HrEmpInfo deptMng;
    @Column(name = "DEPT_MNG_CONFIRM")
    private Character deptMngConfirm;
    @Column(name = "G1_PHONE")
    private String g1Phone;
    @Column(name = "G2_PHONE")
    private String g2Phone;
    @Column(name = "DEPT_MNG_PHONE")
    private String deptMngPhone;
    @Column(name = "CANCEL")
    private Character cancel;
    @Column(name = "NEW_REPLY_FLAG")
    private Character newReplyFlag;
    public HrBorrowFundRequest() {
    }

    public Calendar getReqStart() {
        return reqStart;
    }

    public void setReqStart(Calendar reqStart) {
        this.reqStart = reqStart;
    }

    


    public Character getEmpConfirm() {
        return empConfirm;
    }

    public void setEmpConfirm(Character empConfirm) {
        this.empConfirm = empConfirm;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public Date getEmpConfirmDate() {
        return empConfirmDate;
    }

    public void setEmpConfirmDate(Date empConfirmDate) {
        this.empConfirmDate = empConfirmDate;
    }

    public Date getResStart() {
        return resStart;
    }

    public void setResStart(Date resStart) {
        this.resStart = resStart;
    }

   

   
    public Character getMngConfirm() {
        return mngConfirm;
    }

    public void setMngConfirm(Character mngConfirm) {
        this.mngConfirm = mngConfirm;
    }

    public Date getMngConfirmDate() {
        return mngConfirmDate;
    }

    public void setMngConfirmDate(Date mngConfirmDate) {
        this.mngConfirmDate = mngConfirmDate;
    }

    public String getMngNotes() {
        return mngNotes;
    }

    public void setMngNotes(String mngNotes) {
        this.mngNotes = mngNotes;
    }

    

    public Character getGuarantee1Confirm() {
        return guarantee1Confirm;
    }

    public void setGuarantee1Confirm(Character guarantee1Confirm) {
        this.guarantee1Confirm = guarantee1Confirm;
    }

   

    public Character getGuarantee2Confirm() {
        return guarantee2Confirm;
    }

    public void setGuarantee2Confirm(Character guarantee2Confirm) {
        this.guarantee2Confirm = guarantee2Confirm;
    }

    

    public Character getDeptMngConfirm() {
        return deptMngConfirm;
    }

    public void setDeptMngConfirm(Character deptMngConfirm) {
        this.deptMngConfirm = deptMngConfirm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public HrEmpInfo getDeptMng() {
        return deptMng;
    }

    public void setDeptMng(HrEmpInfo deptMng) {
        this.deptMng = deptMng;
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

    public HrEmpInfo getMngNo() {
        return mngNo;
    }

    public void setMngNo(HrEmpInfo mngNo) {
        this.mngNo = mngNo;
    }

    public Long getReqAmount() {
        return reqAmount;
    }

    public void setReqAmount(Long reqAmount) {
        this.reqAmount = reqAmount;
    }

    public Long getReqMonths() {
        return reqMonths;
    }

    public void setReqMonths(Long reqMonths) {
        this.reqMonths = reqMonths;
    }

    public Long getResAmount() {
        return resAmount;
    }

    public void setResAmount(Long resAmount) {
        this.resAmount = resAmount;
    }

    public Long getResMonths() {
        return resMonths;
    }

    public void setResMonths(Long resMonths) {
        this.resMonths = resMonths;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrBorrowFundRequest)) {
            return false;
        }
        HrBorrowFundRequest other = (HrBorrowFundRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrBorrowFundRequest[id=" + id + "]";
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

    public String getDeptMngPhone() {
        return deptMngPhone;
    }

    public void setDeptMngPhone(String deptMngPhone) {
        this.deptMngPhone = deptMngPhone;
    }

    public Character getCancel() {
        return cancel;
    }

    public void setCancel(Character cancel) {
        this.cancel = cancel;
    }

    public Character getNewReplyFlag() {
        return newReplyFlag;
    }

    public void setNewReplyFlag(Character newReplyFlag) {
        this.newReplyFlag = newReplyFlag;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }


    
 @Override
    public Object clone() throws CloneNotSupportedException{
      return super.clone();
    }

}

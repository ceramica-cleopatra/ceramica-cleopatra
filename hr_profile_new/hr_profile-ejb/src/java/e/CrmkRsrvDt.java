/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "CRMK_RSRV_DT", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkRsrvDt.findAll", query = "SELECT c FROM CrmkRsrvDt c"),
    @NamedQuery(name = "CrmkRsrvDt.findById", query = "SELECT c FROM CrmkRsrvDt c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkRsrvDt.findByPrevYears", query = "SELECT c FROM CrmkRsrvDt c WHERE c.prevYears = :prevYears"),
    @NamedQuery(name = "CrmkRsrvDt.findByChkNo", query = "SELECT c FROM CrmkRsrvDt c WHERE c.chkNo = :chkNo"),
    @NamedQuery(name = "CrmkRsrvDt.findByDueDate", query = "SELECT c FROM CrmkRsrvDt c WHERE c.dueDate = :dueDate"),
    @NamedQuery(name = "CrmkRsrvDt.findByBankName", query = "SELECT c FROM CrmkRsrvDt c WHERE c.bankName = :bankName"),
    @NamedQuery(name = "CrmkRsrvDt.findByNameRtn", query = "SELECT c FROM CrmkRsrvDt c WHERE c.nameRtn = :nameRtn"),
    @NamedQuery(name = "CrmkRsrvDt.findByDocHandNo", query = "SELECT c FROM CrmkRsrvDt c WHERE c.docHandNo = :docHandNo"),
    @NamedQuery(name = "CrmkRsrvDt.findByAmount", query = "SELECT c FROM CrmkRsrvDt c WHERE c.amount = :amount"),
    @NamedQuery(name = "CrmkRsrvDt.findByNotes", query = "SELECT c FROM CrmkRsrvDt c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkRsrvDt.findBySrfOrdrNo", query = "SELECT c FROM CrmkRsrvDt c WHERE c.srfOrdrNo = :srfOrdrNo"),
    @NamedQuery(name = "CrmkRsrvDt.findByBankBrn", query = "SELECT c FROM CrmkRsrvDt c WHERE c.bankBrn = :bankBrn")})
public class CrmkRsrvDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PREV_YEARS")
    private Character prevYears;
    @Column(name = "CHK_NO")
    private String chkNo;
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Column(name = "BANK_NAME")
    private String bankName;
    @Column(name = "NAME_RTN")
    private String nameRtn;
    @Column(name = "DOC_HAND_NO")
    private String docHandNo;
    @Basic(optional = false)
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "SRF_ORDR_NO")
    private Long srfOrdrNo;
    @Column(name = "BANK_BRN")
    private String bankBrn;

    public CrmkRsrvDt() {
    }

    public CrmkRsrvDt(Long id) {
        this.id = id;
    }

    public CrmkRsrvDt(Long id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getPrevYears() {
        return prevYears;
    }

    public void setPrevYears(Character prevYears) {
        this.prevYears = prevYears;
    }

    public String getChkNo() {
        return chkNo;
    }

    public void setChkNo(String chkNo) {
        this.chkNo = chkNo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNameRtn() {
        return nameRtn;
    }

    public void setNameRtn(String nameRtn) {
        this.nameRtn = nameRtn;
    }

    public String getDocHandNo() {
        return docHandNo;
    }

    public void setDocHandNo(String docHandNo) {
        this.docHandNo = docHandNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getSrfOrdrNo() {
        return srfOrdrNo;
    }

    public void setSrfOrdrNo(Long srfOrdrNo) {
        this.srfOrdrNo = srfOrdrNo;
    }

    public String getBankBrn() {
        return bankBrn;
    }

    public void setBankBrn(String bankBrn) {
        this.bankBrn = bankBrn;
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
        if (!(object instanceof CrmkRsrvDt)) {
            return false;
        }
        CrmkRsrvDt other = (CrmkRsrvDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkRsrvDt[id=" + id + "]";
    }

}

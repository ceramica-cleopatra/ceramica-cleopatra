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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "CRMK_ORDR_SADER", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkOrdrSader.findNotApproved", query = "SELECT c FROM CrmkOrdrSader c where c.empApprove='Y' and (c.managerApprove is null or c.managerApprove='N') and c.brnId.id in (select l.crmkId from HrLocation l where l.id=:loc_id) and (c.canceled is null or c.canceled='N') and c.trnsDate>:trnsDate"),
    @NamedQuery(name = "CrmkOrdrSader.findNotPrinted", query = "SELECT c FROM CrmkOrdrSader c where c.empApprove='Y' and c.managerApprove='Y' and c.storeId.id in (select l.crmkId from HrLocation l where l.id=:loc_id) and (c.canceled is null or c.canceled='N') and (c.printed is null or c.printed='N')"),
    @NamedQuery(name = "CrmkOrdrSader.findNotPrintedWithSearch", query = "SELECT c FROM CrmkOrdrSader c where c.empApprove='Y' and c.managerApprove='Y' and c.storeId.id in (select l.crmkId from HrLocation l where l.id=:loc_id) and (c.canceled is null or c.canceled='N') and (c.printed is null or c.printed='N') "
    + " and (c.ordrId.no=:ordrNo or :ordrNo is null) and (c.ordrId.crmkSehy=:crmkSehy or :crmkSehy is null) and (c.brnId.id=:brnId or :brnId is null)"),
    @NamedQuery(name = "CrmkOrdrSader.chkCnt", query = "SELECT count(c.id) FROM CrmkOrdrSader c where c.empApprove='Y' and c.managerApprove='Y' and c.brnId.id=:brn_id and (c.canceled is null or c.canceled='N') and c.trnsDate between :from_date and :to_date"),
    @NamedQuery(name = "CrmkOrdrSader.chkSum", query = "SELECT sum(c.amount) FROM CrmkOrdrSader c where c.empApprove='Y' and c.managerApprove='Y' and c.brnId.id=:brn_id and (c.canceled is null or c.canceled='N') and c.trnsDate between :from_date and :to_date and c.amount is not null"),
    @NamedQuery(name = "CrmkOrdrSader.findApproved", query = "SELECT c FROM CrmkOrdrSader c WHERE c.managerId = :mng_id and (c.printed is null or c.printed='N') and (c.canceled is null or c.canceled='N')"),
    @NamedQuery(name = "CrmkOrdrSader.findPrinted", query = "SELECT c FROM CrmkOrdrSader c WHERE c.printEmpId = :emp_id and c.printed='Y' and (c.canceled is null or c.canceled='N') and c.printDate>=:frm_date"),
    @NamedQuery(name = "CrmkOrdrSader.findById", query = "SELECT c FROM CrmkOrdrSader c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkOrdrSader.findByOrdrId", query = "SELECT c FROM CrmkOrdrSader c WHERE c.ordrId = :ordrId"),
    @NamedQuery(name = "CrmkOrdrSader.findByManagerId", query = "SELECT c FROM CrmkOrdrSader c WHERE c.managerId = :managerId"),
    @NamedQuery(name = "CrmkOrdrSader.findByTrnsDate", query = "SELECT c FROM CrmkOrdrSader c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkOrdrSader.findByStoreId", query = "SELECT c FROM CrmkOrdrSader c WHERE c.storeId = :storeId"),
    @NamedQuery(name = "CrmkOrdrSader.findByAmount", query = "SELECT c FROM CrmkOrdrSader c WHERE c.amount = :amount"),
    @NamedQuery(name = "CrmkOrdrSader.findByEmpApprove", query = "SELECT c FROM CrmkOrdrSader c WHERE c.empApprove = :empApprove"),
    @NamedQuery(name = "CrmkOrdrSader.findByNotes", query = "SELECT c FROM CrmkOrdrSader c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkOrdrSader.findByBrnId", query = "SELECT c FROM CrmkOrdrSader c WHERE c.brnId.id = :brnId"),
    @NamedQuery(name = "CrmkOrdrSader.findByPrdId", query = "SELECT c FROM CrmkOrdrSader c WHERE c.prdId = :prdId"),
    @NamedQuery(name = "CrmkOrdrSader.findByPrinted", query = "SELECT c FROM CrmkOrdrSader c WHERE c.printed = :printed"),
    @NamedQuery(name = "CrmkOrdrSader.findByEmpId", query = "SELECT c FROM CrmkOrdrSader c WHERE c.empId = :empId"),
    @NamedQuery(name = "CrmkOrdrSader.findByManagerApprove", query = "SELECT c FROM CrmkOrdrSader c WHERE c.managerApprove = :managerApprove"),
    @NamedQuery(name = "CrmkOrdrSader.findByPrintEmpId", query = "SELECT c FROM CrmkOrdrSader c WHERE c.printEmpId = :printEmpId"),
    @NamedQuery(name = "CrmkOrdrSader.findByManagerApproveDate", query = "SELECT c FROM CrmkOrdrSader c WHERE c.managerApproveDate = :managerApproveDate"),
    @NamedQuery(name = "CrmkOrdrSader.findByEmpApproveDate", query = "SELECT c FROM CrmkOrdrSader c WHERE c.empApproveDate = :empApproveDate"),
    @NamedQuery(name = "CrmkOrdrSader.findByPrintDate", query = "SELECT c FROM CrmkOrdrSader c WHERE c.printDate = :printDate"),
    @NamedQuery(name = "CrmkOrdrSader.findByCanceled", query = "SELECT c FROM CrmkOrdrSader c WHERE c.canceled = :canceled"),
    @NamedQuery(name = "CrmkOrdrSader.findByCreationEmp", query = "SELECT c FROM CrmkOrdrSader c WHERE c.creationEmp = :creationEmp")})
public class CrmkOrdrSader implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ORDR_ID")
    private CrmkOrdrHd ordrId;
    @Column(name = "MANAGER_ID")
    private Long managerId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private CrmkBranch storeId;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "EMP_APPROVE")
    private String empApprove;
    @Column(name = "NOTES")
    private String notes;
    @ManyToOne
    @JoinColumn(name = "BRN_ID")
    private CrmkBranch brnId;
    @Column(name = "PRD_ID")
    private Long prdId;
    @Column(name = "PRINTED")
    private String printed;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "MANAGER_APPROVE")
    private String managerApprove;
    @Column(name = "PRINT_EMP_ID")
    private String printEmpId;
    @Column(name = "MANAGER_APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date managerApproveDate;
    @Column(name = "EMP_APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date empApproveDate;
    @Column(name = "PRINT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date printDate;
    @Column(name = "CANCELED")
    private String canceled;
    @Column(name = "CREATION_EMP")
    private Long creationEmp;

    public CrmkOrdrSader() {
    }

    public CrmkOrdrSader(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CrmkOrdrHd getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(CrmkOrdrHd ordrId) {
        this.ordrId = ordrId;
    }

   

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public CrmkBranch getStoreId() {
        return storeId;
    }

    public void setStoreId(CrmkBranch storeId) {
        this.storeId = storeId;
    }

  

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

   

    public String getEmpApprove() {
        return empApprove;
    }

    public void setEmpApprove(String empApprove) {
        this.empApprove = empApprove;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public CrmkBranch getBrnId() {
        return brnId;
    }

    public void setBrnId(CrmkBranch brnId) {
        this.brnId = brnId;
    }

   
    public Long getPrdId() {
        return prdId;
    }

    public void setPrdId(Long prdId) {
        this.prdId = prdId;
    }

    public String getPrinted() {
        return printed;
    }

    public void setPrinted(String printed) {
        this.printed = printed;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getManagerApprove() {
        return managerApprove;
    }

    public void setManagerApprove(String managerApprove) {
        this.managerApprove = managerApprove;
    }

    public String getPrintEmpId() {
        return printEmpId;
    }

    public void setPrintEmpId(String printEmpId) {
        this.printEmpId = printEmpId;
    }

    public Date getManagerApproveDate() {
        return managerApproveDate;
    }

    public void setManagerApproveDate(Date managerApproveDate) {
        this.managerApproveDate = managerApproveDate;
    }

    public Date getEmpApproveDate() {
        return empApproveDate;
    }

    public void setEmpApproveDate(Date empApproveDate) {
        this.empApproveDate = empApproveDate;
    }

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public Long getCreationEmp() {
        return creationEmp;
    }

    public void setCreationEmp(Long creationEmp) {
        this.creationEmp = creationEmp;
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
        if (!(object instanceof CrmkOrdrSader)) {
            return false;
        }
        CrmkOrdrSader other = (CrmkOrdrSader) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkOrdrSader[id=" + id + "]";
    }

}

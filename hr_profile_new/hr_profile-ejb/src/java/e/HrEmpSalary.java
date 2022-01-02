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
import javax.persistence.Id;
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
@Table(name = "HR_EMP_SALARY", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpSalary.findLast", query = "SELECT h FROM HrEmpSalary h where h.trnsDate=(select max(x.trnsDate) FROM HrEmpSalary x where x.hdId=h.hdId) and h.hdId=:emp"),
    @NamedQuery(name = "HrEmpSalary.findPrevious", query = "SELECT h FROM HrEmpSalary h where h.trnsDate=(select max(x.trnsDate) FROM HrEmpSalary x where x.hdId=h.hdId and x.trnsDate<=:date) and h.hdId=:emp"),
    @NamedQuery(name = "HrEmpSalary.findById", query = "SELECT h FROM HrEmpSalary h WHERE h.id = :id"),
    @NamedQuery(name = "HrEmpSalary.findByTrnsDate", query = "SELECT h FROM HrEmpSalary h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrEmpSalary.findByTotSal", query = "SELECT h FROM HrEmpSalary h WHERE h.totSal = :totSal"),
    @NamedQuery(name = "HrEmpSalary.findByBasicSal", query = "SELECT h FROM HrEmpSalary h WHERE h.basicSal = :basicSal"),
    @NamedQuery(name = "HrEmpSalary.findByVarSal", query = "SELECT h FROM HrEmpSalary h WHERE h.varSal = :varSal"),
    @NamedQuery(name = "HrEmpSalary.findByNotes", query = "SELECT h FROM HrEmpSalary h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrEmpSalary.findByOthers", query = "SELECT h FROM HrEmpSalary h WHERE h.others = :others"),
    @NamedQuery(name = "HrEmpSalary.findByUserName", query = "SELECT h FROM HrEmpSalary h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrEmpSalary.findByMachineName", query = "SELECT h FROM HrEmpSalary h WHERE h.machineName = :machineName"),
    @NamedQuery(name = "HrEmpSalary.findByTax", query = "SELECT h FROM HrEmpSalary h WHERE h.tax = :tax"),
    @NamedQuery(name = "HrEmpSalary.findByCreateBy", query = "SELECT h FROM HrEmpSalary h WHERE h.createBy = :createBy"),
    @NamedQuery(name = "HrEmpSalary.findByCreateAt", query = "SELECT h FROM HrEmpSalary h WHERE h.createAt = :createAt"),
    @NamedQuery(name = "HrEmpSalary.findByModifyBy", query = "SELECT h FROM HrEmpSalary h WHERE h.modifyBy = :modifyBy"),
    @NamedQuery(name = "HrEmpSalary.findByModifyAt", query = "SELECT h FROM HrEmpSalary h WHERE h.modifyAt = :modifyAt")})
public class HrEmpSalary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "HD_ID")
    private Long hdId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "TOT_SAL")
    private Double totSal;
    @Column(name = "BASIC_SAL")
    private Double basicSal;
    @Column(name = "VAR_SAL")
    private Double varSal;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "OTHERS")
    private BigDecimal others;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MACHINE_NAME")
    private String machineName;
    @Column(name = "TAX")
    private Double tax;
    @Column(name = "CREATE_BY")
    private BigInteger createBy;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "MODIFY_BY")
    private BigInteger modifyBy;
    @Column(name = "MODIFY_AT")
    @Temporal(TemporalType.DATE)
    private Date modifyAt;

    public Long getHdId() {
        return hdId;
    }

    public void setHdId(Long hdId) {
        this.hdId = hdId;
    }

    

    public HrEmpSalary() {
    }

    public HrEmpSalary(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Double getTotSal() {
        return totSal;
    }

    public void setTotSal(Double totSal) {
        this.totSal = totSal;
    }

    public Double getBasicSal() {
        return basicSal;
    }

    public void setBasicSal(Double basicSal) {
        this.basicSal = basicSal;
    }

    public Double getVarSal() {
        return varSal;
    }

    public void setVarSal(Double varSal) {
        this.varSal = varSal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getOthers() {
        return others;
    }

    public void setOthers(BigDecimal others) {
        this.others = others;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public BigInteger getCreateBy() {
        return createBy;
    }

    public void setCreateBy(BigInteger createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public BigInteger getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(BigInteger modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
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
        if (!(object instanceof HrEmpSalary)) {
            return false;
        }
        HrEmpSalary other = (HrEmpSalary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrEmpSalary[id=" + id + "]";
    }

}

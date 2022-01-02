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
 * @author data
 */
@Entity
@Table(name = "HR_NEW_EMP_EXCEED_3_MONTHS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrNewEmpExceed3Months.findAll", query = "SELECT h FROM HrNewEmpExceed3Months h"),
    @NamedQuery(name = "HrNewEmpExceed3Months.findById", query = "SELECT h FROM HrNewEmpExceed3Months h WHERE h.id = :id"),
    @NamedQuery(name = "HrNewEmpExceed3Months.findByEmpNo", query = "SELECT h FROM HrNewEmpExceed3Months h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrNewEmpExceed3Months.findByTotalSalary", query = "SELECT h FROM HrNewEmpExceed3Months h WHERE h.totalSalary = :totalSalary"),
    @NamedQuery(name = "HrNewEmpExceed3Months.findByBasicSalary", query = "SELECT h FROM HrNewEmpExceed3Months h WHERE h.basicSalary = :basicSalary"),
    @NamedQuery(name = "HrNewEmpExceed3Months.findByVarSalary", query = "SELECT h FROM HrNewEmpExceed3Months h WHERE h.varSalary = :varSalary"),
    @NamedQuery(name = "HrNewEmpExceed3Months.findByCreationDate", query = "SELECT h FROM HrNewEmpExceed3Months h WHERE h.creationDate = :creationDate"),
    @NamedQuery(name = "HrNewEmpExceed3Months.findByApproved", query = "SELECT h FROM HrNewEmpExceed3Months h WHERE h.approved = :approved"),
    @NamedQuery(name = "HrNewEmpExceed3Months.findByApproveDate", query = "SELECT h FROM HrNewEmpExceed3Months h WHERE h.approveDate = :approveDate")})
public class HrNewEmpExceed3Months implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_NEW_EMP_EXCEED_3_MONTH_SEQ", sequenceName="HR_NEW_EMP_EXCEED_3_MONTH_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_NEW_EMP_EXCEED_3_MONTH_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "EMP_NO",referencedColumnName="EMP_NO")
    private HrEmpInfo empNo;
    @Column(name = "TOTAL_SALARY")
    private Long totalSalary;
    @Column(name = "BASIC_SALARY")
    private Long basicSalary;
    @Column(name = "VAR_SALARY")
    private Long varSalary;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "APPROVED")
    private Character approved;
    @Column(name = "APPROVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date approveDate;
    @Column(name = "MNG_NO")
    private Long mngNo;
    public HrNewEmpExceed3Months() {
    }

    public Long getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Long basicSalary) {
        this.basicSalary = basicSalary;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Long getVarSalary() {
        return varSalary;
    }

    public void setVarSalary(Long varSalary) {
        this.varSalary = varSalary;
    }

    
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Character getApproved() {
        return approved;
    }

    public void setApproved(Character approved) {
        this.approved = approved;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Long getMngNo() {
        return mngNo;
    }

    public void setMngNo(Long mngNo) {
        this.mngNo = mngNo;
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
        if (!(object instanceof HrNewEmpExceed3Months)) {
            return false;
        }
        HrNewEmpExceed3Months other = (HrNewEmpExceed3Months) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrNewEmpExceed3Months[id=" + id + "]";
    }

}

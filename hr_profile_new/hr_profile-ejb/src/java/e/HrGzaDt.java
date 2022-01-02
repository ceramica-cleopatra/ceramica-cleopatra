/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */

@Entity
@Table(name = "HR_GZA_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrGzaDt.findAll", query = "SELECT h.trnsDate,h.gzaType.name,dt.empNo.empName,dt.gzaDays,dt.gzaValue,dt.mngGzaDays,dt.mngGzaValue,dt.approved,dt.notes FROM HrGzaDt dt LEFT JOIN dt.hrGzaHd h where h.mngNo=:mng and (dt.empNo.empName like :emp or :emp is null) order by h.trnsDate desc"),
    @NamedQuery(name = "HrGzaDt.findAllForEmp", query = "SELECT h.trnsDate,h.gzaType.name,dt.empNo.empName,dt.gzaDays,dt.gzaValue,dt.mngGzaDays,dt.mngGzaValue,dt.approved,dt.notes FROM HrGzaDt dt LEFT JOIN dt.hrGzaHd h where dt.empNo.empNo=:emp order by h.trnsDate desc"),
    @NamedQuery(name = "HrGzaDt.chkEmpReadGza", query = "SELECT count(h.id) FROM HrGzaDt h WHERE h.empNo.empNo = :empNo and h.readDone is null"),
    @NamedQuery(name = "HrGzaDt.getEmpNotReadGza", query = "SELECT h FROM HrGzaDt h WHERE h.empNo.empNo = :emp and h.readDone is null"),
    @NamedQuery(name = "HrGzaDt.chkEmpReadApprovedGza", query = "SELECT count(h.id) FROM HrGzaDt h WHERE h.empNo.empNo = :empNo and h.readApprovedDone is null and h.readDone='Y' and h.approved='Y'"),
    @NamedQuery(name = "HrGzaDt.getEmpReadApprovedGza", query = "SELECT h FROM HrGzaDt h WHERE h.empNo.empNo = :emp and h.readApprovedDone is null and h.readDone='Y' and h.approved='Y'"),
    @NamedQuery(name = "HrGzaDt.findMax", query = "SELECT max(h.id) FROM HrGzaDt h"),
    @NamedQuery(name = "HrGzaDt.findById", query = "SELECT h FROM HrGzaDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrGzaDt.findByEmpNo", query = "SELECT h FROM HrGzaDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrGzaDt.findByLastSalary", query = "SELECT h FROM HrGzaDt h WHERE h.lastSalary = :lastSalary"),
    @NamedQuery(name = "HrGzaDt.findByGzaDays", query = "SELECT h FROM HrGzaDt h WHERE h.gzaDays = :gzaDays"),
    @NamedQuery(name = "HrGzaDt.findByGzaValue", query = "SELECT h FROM HrGzaDt h WHERE h.gzaValue = :gzaValue"),
    @NamedQuery(name = "HrGzaDt.findByNotes", query = "SELECT h FROM HrGzaDt h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrGzaDt.findByApprovedBy", query = "SELECT h FROM HrGzaDt h WHERE h.approvedBy = :approvedBy"),
    @NamedQuery(name = "HrGzaDt.findByApproved", query = "SELECT h FROM HrGzaDt h WHERE h.approved = :approved"),
    @NamedQuery(name = "HrGzaDt.findByReadDone", query = "SELECT h FROM HrGzaDt h WHERE h.readDone = :readDone"),
    @NamedQuery(name = "HrGzaDt.findByMngGzaDays", query = "SELECT h FROM HrGzaDt h WHERE h.mngGzaDays = :mngGzaDays"),
    @NamedQuery(name = "HrGzaDt.findByMngGzaValue", query = "SELECT h FROM HrGzaDt h WHERE h.mngGzaValue = :mngGzaValue"),
    @NamedQuery(name = "HrGzaDt.findByReadApprovedDone", query = "SELECT h FROM HrGzaDt h WHERE h.readApprovedDone = :readApprovedDone")})
public class HrGzaDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "EMP_NO")
    @ManyToOne
    private HrEmpInfo empNo;
    @Column(name = "LAST_SALARY")
    private Long lastSalary;
    @Column(name = "GZA_DAYS")
    private Double gzaDays;
    @Column(name = "GZA_VALUE")
    private Long gzaValue;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "APPROVED_BY")
    private String approvedBy;
    @Column(name = "APPROVED")
    private String approved;
    @Column(name = "READ_DONE")
    private String readDone;
    @Column(name = "MNG_GZA_DAYS")
    private Long mngGzaDays;
    @Column(name = "MNG_GZA_VALUE")
    private Long mngGzaValue;
    @Column(name = "READ_APPROVED_DONE")
    private String readApprovedDone;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrGzaHd hrGzaHd;

    public HrGzaDt() {
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    public Double getGzaDays() {
        return gzaDays;
    }

    public void setGzaDays(Double gzaDays) {
        this.gzaDays = gzaDays;
    }

   
   

  

   

   

    public Long getGzaValue() {
        return gzaValue;
    }

    public void setGzaValue(Long gzaValue) {
        this.gzaValue = gzaValue;
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

    public Long getMngGzaDays() {
        return mngGzaDays;
    }

    public void setMngGzaDays(Long mngGzaDays) {
        this.mngGzaDays = mngGzaDays;
    }

    public Long getMngGzaValue() {
        return mngGzaValue;
    }

    public void setMngGzaValue(Long mngGzaValue) {
        this.mngGzaValue = mngGzaValue;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReadApprovedDone() {
        return readApprovedDone;
    }

    public void setReadApprovedDone(String readApprovedDone) {
        this.readApprovedDone = readApprovedDone;
    }

    public String getReadDone() {
        return readDone;
    }

    public void setReadDone(String readDone) {
        this.readDone = readDone;
    }

   
    public HrGzaHd getHrGzaHd() {
        return hrGzaHd;
    }

    public void setHrGzaHd(HrGzaHd hrGzaHd) {
        this.hrGzaHd = hrGzaHd;
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
        if (!(object instanceof HrGzaDt)) {
            return false;
        }
        HrGzaDt other = (HrGzaDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrGzaDt[id=" + id + "]";
    }

}

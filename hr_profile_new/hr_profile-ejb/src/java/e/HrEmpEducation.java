/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "HR_EMP_EDUCATION", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpEducation.findAll", query = "SELECT h FROM HrEmpEducation h"),
    @NamedQuery(name = "HrEmpEducation.findAllById", query = "select o.branch,o.gyear,d.name,f.name from HrEmpEducation o LEFT JOIN o.hrdegree d LEFT JOIN o.hrfaculty f where o.hdId=:emp_id"),
    @NamedQuery(name = "HrEmpEducation.findById", query = "SELECT h FROM HrEmpEducation h WHERE h.id = :id"),
    @NamedQuery(name = "HrEmpEducation.findByGyear", query = "SELECT h FROM HrEmpEducation h WHERE h.gyear = :gyear"),
    @NamedQuery(name = "HrEmpEducation.findByBranch", query = "SELECT h FROM HrEmpEducation h WHERE h.branch = :branch"),
    @NamedQuery(name = "HrEmpEducation.findByNotes", query = "SELECT h FROM HrEmpEducation h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrEmpEducation.findByTrnsDate", query = "SELECT h FROM HrEmpEducation h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrEmpEducation.findByUserName", query = "SELECT h FROM HrEmpEducation h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrEmpEducation.findByMachineName", query = "SELECT h FROM HrEmpEducation h WHERE h.machineName = :machineName"),
    @NamedQuery(name = "HrEmpEducation.findByCreateBy", query = "SELECT h FROM HrEmpEducation h WHERE h.createBy = :createBy"),
    @NamedQuery(name = "HrEmpEducation.findByCreateAt", query = "SELECT h FROM HrEmpEducation h WHERE h.createAt = :createAt"),
    @NamedQuery(name = "HrEmpEducation.findByModifyBy", query = "SELECT h FROM HrEmpEducation h WHERE h.modifyBy = :modifyBy"),
    @NamedQuery(name = "HrEmpEducation.findByModifyAt", query = "SELECT h FROM HrEmpEducation h WHERE h.modifyAt = :modifyAt")})
public class HrEmpEducation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name="HD_ID", nullable = false)
    private Long hdId;
    @Column(name = "GYEAR")
    private Short gyear;
    @Column(name = "BRANCH")
    private String branch;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MACHINE_NAME")
    private String machineName;
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
    @ManyToOne
    @JoinColumn(name="DEGREE_ID")
    private HrDegree hrdegree;
    @ManyToOne
    @JoinColumn(name="FACULTY_ID")
    private HrFaculty hrfaculty;

    public Long getHdId() {
        return hdId;
    }
    public void setHdId(Long hdId) {
        this.hdId = hdId;
    }
    public HrEmpEducation() {
    }

    public HrDegree getHrdegree() {
        return hrdegree;
    }

    public void setHrdegree(HrDegree hrdegree) {
        this.hrdegree = hrdegree;
    }

    public HrFaculty getHrfaculty() {
        return hrfaculty;
    }

    public void setHrfaculty(HrFaculty hrfaculty) {
        this.hrfaculty = hrfaculty;
    }

    public HrEmpEducation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getGyear() {
        return gyear;
    }

    public void setGyear(Short gyear) {
        this.gyear = gyear;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
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
        if (!(object instanceof HrEmpEducation)) {
            return false;
        }
        HrEmpEducation other = (HrEmpEducation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrEmpEducation[id=" + id + "]";
    }

}

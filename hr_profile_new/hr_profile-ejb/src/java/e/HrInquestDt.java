/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
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
 * @author data
 */
@Entity
@Table(name = "HR_INQUEST_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrInquestDt.findAll", query = "SELECT h FROM HrInquestDt h"),
    @NamedQuery(name = "HrInquestDt.findById", query = "SELECT h FROM HrInquestDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrInquestDt.findByEmpName", query = "SELECT h FROM HrInquestDt h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrInquestDt.findByInqRec", query = "SELECT h FROM HrInquestDt h WHERE h.inqRec = :inqRec"),
    @NamedQuery(name = "HrInquestDt.findByNotes", query = "SELECT h FROM HrInquestDt h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrInquestDt.findByInqCon", query = "SELECT h FROM HrInquestDt h WHERE h.inqCon = :inqCon"),
    @NamedQuery(name = "HrInquestDt.findByInqCoApprove", query = "SELECT h FROM HrInquestDt h WHERE h.inqCoApprove = :inqCoApprove")})
public class HrInquestDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "INQ_REC")
    private String inqRec;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "INQ_CON")
    private Character inqCon;
    @Column(name = "INQ_CON_MNG")
    private Character inqConMng;
    @Column(name = "INQ_CO_APPROVE")
    private String inqCoApprove;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrInquestHd hrInquestHd;
    @JoinColumn(name = "EMP_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private HrInquestEmpType hrInquestEmpType;

    public HrInquestDt() {
    }

    public HrInquestDt(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getInqRec() {
        return inqRec;
    }

    public void setInqRec(String inqRec) {
        this.inqRec = inqRec;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Character getInqCon() {
        return inqCon;
    }

    public void setInqCon(Character inqCon) {
        this.inqCon = inqCon;
    }

    public String getInqCoApprove() {
        return inqCoApprove;
    }

    public void setInqCoApprove(String inqCoApprove) {
        this.inqCoApprove = inqCoApprove;
    }

    public HrInquestHd getHrInquestHd() {
        return hrInquestHd;
    }

    public void setHrInquestHd(HrInquestHd hrInquestHd) {
        this.hrInquestHd = hrInquestHd;
    }

    public HrInquestEmpType getHrInquestEmpType() {
        return hrInquestEmpType;
    }

    public void setHrInquestEmpType(HrInquestEmpType hrInquestEmpType) {
        this.hrInquestEmpType = hrInquestEmpType;
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
        if (!(object instanceof HrInquestDt)) {
            return false;
        }
        HrInquestDt other = (HrInquestDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrInquestDt[id=" + id + "]";
    }

    public Character getInqConMng() {
        return inqConMng;
    }

    public void setInqConMng(Character inqConMng) {
        this.inqConMng = inqConMng;
    }

    

}

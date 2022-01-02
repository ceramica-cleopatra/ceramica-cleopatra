/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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

/**
 *
 * @author ahmed abbas
 */

@Entity
@Table(name = "HR_TAMYOZ_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrTamyozDt.personalyTamyoz", query = "SELECT h FROM HrTamyozDt h where h.hrTamyozHd.rewardType=6 and h.hrTamyozHd.trnsDate between :from_date and :to_date and h.empId.empNo=:emp order by h.hrTamyozHd.trnsDate"),
    @NamedQuery(name = "HrTamyozDt.findById", query = "SELECT h FROM HrTamyozDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrTamyozDt.findEmpsByHdId", query = "SELECT h.empId.empNo FROM HrTamyozDt h WHERE h.hrTamyozHd.id = :id"),
    @NamedQuery(name = "HrTamyozDt.chkEmpDailyTamyoz", query = "SELECT count(h.id) FROM HrTamyozDt h WHERE h.empId.empNo = :emp and h.hrTamyozHd.rewardType=7 and h.hrTamyozHd.trnsDate=:date"),
    @NamedQuery(name = "HrTamyozDt.findByAmount", query = "SELECT h FROM HrTamyozDt h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrTamyozDt.findByNotes", query = "SELECT h FROM HrTamyozDt h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrTamyozDt.findByRatio", query = "SELECT h FROM HrTamyozDt h WHERE h.ratio = :ratio")})
public class HrTamyozDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_TAMYOZ_DT_SEQ", sequenceName="HR_TAMYOZ_DT_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_TAMYOZ_DT_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "AMOUNT")
    private Long amount;
    @JoinColumn(name = "EMP_ID" ,referencedColumnName = "EMP_NO")
    @ManyToOne
    private HrEmpInfo empId;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "RATIO")
    private Long ratio;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrTamyozHd hrTamyozHd;

    public HrTamyozDt() {
    }

    public HrTamyozDt(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public HrEmpInfo getEmpId() {
        return empId;
    }

    public void setEmpId(HrEmpInfo empId) {
        this.empId = empId;
    }

  

  

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getRatio() {
        return ratio;
    }

    public void setRatio(Long ratio) {
        this.ratio = ratio;
    }

    public HrTamyozHd getHrTamyozHd() {
        return hrTamyozHd;
    }

    public void setHrTamyozHd(HrTamyozHd hrTamyozHd) {
        this.hrTamyozHd = hrTamyozHd;
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
        if (!(object instanceof HrTamyozDt)) {
            return false;
        }
        HrTamyozDt other = (HrTamyozDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrTamyozDt[id=" + id + "]";
    }

}

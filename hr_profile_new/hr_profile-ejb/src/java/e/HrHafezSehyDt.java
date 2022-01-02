/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "HR_HAFEZ_SEHY_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHafezSehyDt.findAll", query = "SELECT h FROM HrHafezSehyDt h where h.hrHafezSehyHd.dateFrom=(select max(o.dateFrom) from HrHafezSehyHd o) and h.empNo=:emp_no"),
    @NamedQuery(name = "HrHafezSehyDt.findById", query = "SELECT h FROM HrHafezSehyDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrHafezSehyDt.findByEmpNo", query = "SELECT h FROM HrHafezSehyDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrHafezSehyDt.findByTypeId", query = "SELECT h FROM HrHafezSehyDt h WHERE h.typeId = :typeId"),
    @NamedQuery(name = "HrHafezSehyDt.findByQty", query = "SELECT h FROM HrHafezSehyDt h WHERE h.qty = :qty"),
    @NamedQuery(name = "HrHafezSehyDt.findByValue", query = "SELECT h FROM HrHafezSehyDt h WHERE h.value = :value")})
public class HrHafezSehyDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrHafezSehyTypes typeId;
    @Column(name = "QTY")
    private Long qty;
    @Column(name = "VALUE")
    private Long value;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrHafezSehyHd hrHafezSehyHd;

    public HrHafezSehyDt() {
    }

    public HrHafezSehyDt(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public HrHafezSehyTypes getTypeId() {
        return typeId;
    }

    public void setTypeId(HrHafezSehyTypes typeId) {
        this.typeId = typeId;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

   
    public HrHafezSehyHd getHrHafezSehyHd() {
        return hrHafezSehyHd;
    }

    public void setHrHafezSehyHd(HrHafezSehyHd hrHafezSehyHd) {
        this.hrHafezSehyHd = hrHafezSehyHd;
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
        if (!(object instanceof HrHafezSehyDt)) {
            return false;
        }
        HrHafezSehyDt other = (HrHafezSehyDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrHafezSehyDt[id=" + id + "]";
    }

}

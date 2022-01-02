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
@Table(name = "HR_HAFEZ_SEHY_MNG_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHafezSehyMngDt.findAll", query = "SELECT h FROM HrHafezSehyMngDt h where h.hrHafezSehyMngHd.dateFrom=(select max(o.dateFrom) from HrHafezSehyMngHd o) and h.empNo=:emp_no"),
    @NamedQuery(name = "HrHafezSehyMngDt.findById", query = "SELECT h FROM HrHafezSehyMngDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrHafezSehyMngDt.findByEmpNo", query = "SELECT h FROM HrHafezSehyMngDt h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrHafezSehyMngDt.findByTypeId", query = "SELECT h FROM HrHafezSehyMngDt h WHERE h.typeId = :typeId"),
    @NamedQuery(name = "HrHafezSehyMngDt.findByQty", query = "SELECT h FROM HrHafezSehyMngDt h WHERE h.qty = :qty"),
    @NamedQuery(name = "HrHafezSehyMngDt.findByValue", query = "SELECT h FROM HrHafezSehyMngDt h WHERE h.value = :value")})
public class HrHafezSehyMngDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrHafezSehyTypes typeId;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrHafezSehyMngHd hrHafezSehyMngHd;
    @Column(name = "QTY")
    private Long qty;
    @Column(name = "VALUE")
    private Long value;

    public HrHafezSehyMngDt() {
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public HrHafezSehyMngHd getHrHafezSehyMngHd() {
        return hrHafezSehyMngHd;
    }

    public void setHrHafezSehyMngHd(HrHafezSehyMngHd hrHafezSehyMngHd) {
        this.hrHafezSehyMngHd = hrHafezSehyMngHd;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrHafezSehyMngDt)) {
            return false;
        }
        HrHafezSehyMngDt other = (HrHafezSehyMngDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrHafezSehyMngDt[id=" + id + "]";
    }

}

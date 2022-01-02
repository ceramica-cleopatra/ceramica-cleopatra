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
 * @author Administrator
 */
@Entity
@Table(name = "CRMK_C_ME2ORDR_DT", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkCMe2ordrDt.findAll", query = "SELECT c FROM CrmkCMe2ordrDt c"),
    @NamedQuery(name = "CrmkCMe2ordrDt.findById", query = "SELECT c FROM CrmkCMe2ordrDt c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkCMe2ordrDt.findByQty", query = "SELECT c FROM CrmkCMe2ordrDt c WHERE c.qty = :qty"),
    @NamedQuery(name = "CrmkCMe2ordrDt.findByNotes", query = "SELECT c FROM CrmkCMe2ordrDt c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkCMe2ordrDt.findByActMins", query = "SELECT c FROM CrmkCMe2ordrDt c WHERE c.actMins = :actMins"),
    @NamedQuery(name = "CrmkCMe2ordrDt.findByShowRecQty", query = "SELECT c FROM CrmkCMe2ordrDt c WHERE c.showRecQty = :showRecQty")})
public class CrmkCMe2ordrDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "QTY")
    private BigDecimal qty;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "ACT_MINS")
    private BigDecimal actMins;
    @Column(name = "SHOW_REC_QTY")
    private BigDecimal showRecQty;
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CrmkCrmkItem crmkCrmkItem;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CrmkCMe2ordrHd crmkCMe2ordrHd;

    public CrmkCMe2ordrDt() {
    }

    public CrmkCMe2ordrDt(Long id) {
        this.id = id;
    }

    public CrmkCMe2ordrDt(Long id, BigDecimal qty) {
        this.id = id;
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getActMins() {
        return actMins;
    }

    public void setActMins(BigDecimal actMins) {
        this.actMins = actMins;
    }

    public BigDecimal getShowRecQty() {
        return showRecQty;
    }

    public void setShowRecQty(BigDecimal showRecQty) {
        this.showRecQty = showRecQty;
    }

    public CrmkCrmkItem getCrmkCrmkItem() {
        return crmkCrmkItem;
    }

    public void setCrmkCrmkItem(CrmkCrmkItem crmkCrmkItem) {
        this.crmkCrmkItem = crmkCrmkItem;
    }

    public CrmkCMe2ordrHd getCrmkCMe2ordrHd() {
        return crmkCMe2ordrHd;
    }

    public void setCrmkCMe2ordrHd(CrmkCMe2ordrHd crmkCMe2ordrHd) {
        this.crmkCMe2ordrHd = crmkCMe2ordrHd;
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
        if (!(object instanceof CrmkCMe2ordrDt)) {
            return false;
        }
        CrmkCMe2ordrDt other = (CrmkCMe2ordrDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkCMe2ordrDt[id=" + id + "]";
    }

}

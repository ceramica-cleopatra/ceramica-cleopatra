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
 * @author ahmed abbas
 */
@Entity
@Table(name = "CRMK_S_ORDR_DT", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkSOrdrDt.findAll", query = "SELECT c FROM CrmkSOrdrDt c"),
    @NamedQuery(name = "CrmkSOrdrDt.findSum", query = "SELECT sum(c.qty) FROM CrmkSOrdrDt c where c.crmkOrdrHd.id=:ordr_id"),
    @NamedQuery(name = "CrmkSOrdrDt.findById", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkSOrdrDt.findByQty", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.qty = :qty"),
    @NamedQuery(name = "CrmkSOrdrDt.findByUprice", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.uprice = :uprice"),
    @NamedQuery(name = "CrmkSOrdrDt.findByTotDscnt", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.totDscnt = :totDscnt"),
    @NamedQuery(name = "CrmkSOrdrDt.findByDscntPrcnt", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.dscntPrcnt = :dscntPrcnt"),
    @NamedQuery(name = "CrmkSOrdrDt.findByDscntVal", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.dscntVal = :dscntVal"),
    @NamedQuery(name = "CrmkSOrdrDt.findByDscntTotP", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.dscntTotP = :dscntTotP"),
    @NamedQuery(name = "CrmkSOrdrDt.findByDscntTotV", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.dscntTotV = :dscntTotV"),
    @NamedQuery(name = "CrmkSOrdrDt.findByNotes", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkSOrdrDt.findBySrfOrdrNo", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.srfOrdrNo = :srfOrdrNo"),
    @NamedQuery(name = "CrmkSOrdrDt.findByDscntSubP", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.dscntSubP = :dscntSubP"),
    @NamedQuery(name = "CrmkSOrdrDt.findByDscntSubV", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.dscntSubV = :dscntSubV"),
    @NamedQuery(name = "CrmkSOrdrDt.findByFree", query = "SELECT c FROM CrmkSOrdrDt c WHERE c.free = :free")})
public class CrmkSOrdrDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "QTY")
    private Double qty;
    @Basic(optional = false)
    @Column(name = "UPRICE")
    private BigDecimal uprice;
    @Column(name = "TOT_DSCNT")
    private Character totDscnt;
    @Column(name = "DSCNT_PRCNT")
    private BigDecimal dscntPrcnt;
    @Column(name = "DSCNT_VAL")
    private BigDecimal dscntVal;
    @Column(name = "DSCNT_TOT_P")
    private BigDecimal dscntTotP;
    @Column(name = "DSCNT_TOT_V")
    private BigDecimal dscntTotV;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "SRF_ORDR_NO")
    private Long srfOrdrNo;
    @Column(name = "DSCNT_SUB_P")
    private BigDecimal dscntSubP;
    @Column(name = "DSCNT_SUB_V")
    private BigDecimal dscntSubV;
    @Column(name = "FREE")
    private BigDecimal free;
    @ManyToOne
    @JoinColumn(name="HD_ID")
    private CrmkOrdrHd crmkOrdrHd;
    public CrmkSOrdrDt() {
    }

    public CrmkSOrdrDt(Long id) {
        this.id = id;
    }

    public CrmkOrdrHd getCrmkOrdrHd() {
        return crmkOrdrHd;
    }

    public void setCrmkOrdrHd(CrmkOrdrHd crmkOrdrHd) {
        this.crmkOrdrHd = crmkOrdrHd;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

  

    public BigDecimal getUprice() {
        return uprice;
    }

    public void setUprice(BigDecimal uprice) {
        this.uprice = uprice;
    }

    public Character getTotDscnt() {
        return totDscnt;
    }

    public void setTotDscnt(Character totDscnt) {
        this.totDscnt = totDscnt;
    }

    public BigDecimal getDscntPrcnt() {
        return dscntPrcnt;
    }

    public void setDscntPrcnt(BigDecimal dscntPrcnt) {
        this.dscntPrcnt = dscntPrcnt;
    }

    public BigDecimal getDscntVal() {
        return dscntVal;
    }

    public void setDscntVal(BigDecimal dscntVal) {
        this.dscntVal = dscntVal;
    }

    public BigDecimal getDscntTotP() {
        return dscntTotP;
    }

    public void setDscntTotP(BigDecimal dscntTotP) {
        this.dscntTotP = dscntTotP;
    }

    public BigDecimal getDscntTotV() {
        return dscntTotV;
    }

    public void setDscntTotV(BigDecimal dscntTotV) {
        this.dscntTotV = dscntTotV;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getSrfOrdrNo() {
        return srfOrdrNo;
    }

    public void setSrfOrdrNo(Long srfOrdrNo) {
        this.srfOrdrNo = srfOrdrNo;
    }

    public BigDecimal getDscntSubP() {
        return dscntSubP;
    }

    public void setDscntSubP(BigDecimal dscntSubP) {
        this.dscntSubP = dscntSubP;
    }

    public BigDecimal getDscntSubV() {
        return dscntSubV;
    }

    public void setDscntSubV(BigDecimal dscntSubV) {
        this.dscntSubV = dscntSubV;
    }

    public BigDecimal getFree() {
        return free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
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
        if (!(object instanceof CrmkSOrdrDt)) {
            return false;
        }
        CrmkSOrdrDt other = (CrmkSOrdrDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkSOrdrDt[id=" + id + "]";
    }

}

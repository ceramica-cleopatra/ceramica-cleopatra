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
@Table(name = "CRMK_D_ORDR_DT", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkDOrdrDt.findAll", query = "SELECT c FROM CrmkDOrdrDt c"),
    @NamedQuery(name = "CrmkDOrdrDt.findSum", query = "SELECT sum(c.qty) FROM CrmkDOrdrDt c where c.crmkOrdrHd.id=:ordr_id"),
    @NamedQuery(name = "CrmkDOrdrDt.findById", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkDOrdrDt.findByQty", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.qty = :qty"),
    @NamedQuery(name = "CrmkDOrdrDt.findByUprice", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.uprice = :uprice"),
    @NamedQuery(name = "CrmkDOrdrDt.findByUextra", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.uextra = :uextra"),
    @NamedQuery(name = "CrmkDOrdrDt.findByTotDscnt", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.totDscnt = :totDscnt"),
    @NamedQuery(name = "CrmkDOrdrDt.findByNotes", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkDOrdrDt.findByDscntPrcnt", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.dscntPrcnt = :dscntPrcnt"),
    @NamedQuery(name = "CrmkDOrdrDt.findByDscntVal", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.dscntVal = :dscntVal"),
    @NamedQuery(name = "CrmkDOrdrDt.findByDscntTotP", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.dscntTotP = :dscntTotP"),
    @NamedQuery(name = "CrmkDOrdrDt.findByDscntTotV", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.dscntTotV = :dscntTotV"),
    @NamedQuery(name = "CrmkDOrdrDt.findBySrfOrdrNo", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.srfOrdrNo = :srfOrdrNo"),
    @NamedQuery(name = "CrmkDOrdrDt.findByDscntSubP", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.dscntSubP = :dscntSubP"),
    @NamedQuery(name = "CrmkDOrdrDt.findByDscntSubV", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.dscntSubV = :dscntSubV"),
    @NamedQuery(name = "CrmkDOrdrDt.findByFree", query = "SELECT c FROM CrmkDOrdrDt c WHERE c.free = :free")})
public class CrmkDOrdrDt implements Serializable {
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
    @Column(name = "UEXTRA")
    private BigDecimal uextra;
    @Column(name = "TOT_DSCNT")
    private Character totDscnt;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "DSCNT_PRCNT")
    private BigDecimal dscntPrcnt;
    @Column(name = "DSCNT_VAL")
    private BigDecimal dscntVal;
    @Column(name = "DSCNT_TOT_P")
    private BigDecimal dscntTotP;
    @Column(name = "DSCNT_TOT_V")
    private BigDecimal dscntTotV;
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
    public CrmkDOrdrDt() {
    }

    public CrmkDOrdrDt(Long id) {
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

    public BigDecimal getUextra() {
        return uextra;
    }

    public void setUextra(BigDecimal uextra) {
        this.uextra = uextra;
    }

    public Character getTotDscnt() {
        return totDscnt;
    }

    public void setTotDscnt(Character totDscnt) {
        this.totDscnt = totDscnt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        if (!(object instanceof CrmkDOrdrDt)) {
            return false;
        }
        CrmkDOrdrDt other = (CrmkDOrdrDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkDOrdrDt[id=" + id + "]";
    }

}

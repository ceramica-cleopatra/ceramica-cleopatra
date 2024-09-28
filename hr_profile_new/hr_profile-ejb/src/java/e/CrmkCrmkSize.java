/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DEV
 */
@Entity
@Table(name = "CRMK_CRMK_SIZE", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkCrmkSize.findAll", query = "SELECT c FROM CrmkCrmkSize c"),
    @NamedQuery(name = "CrmkCrmkSize.findById", query = "SELECT c FROM CrmkCrmkSize c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkCrmkSize.findByWidth", query = "SELECT c FROM CrmkCrmkSize c WHERE c.width = :width"),
    @NamedQuery(name = "CrmkCrmkSize.findByName", query = "SELECT c FROM CrmkCrmkSize c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkCrmkSize.findByCrtnPcs", query = "SELECT c FROM CrmkCrmkSize c WHERE c.crtnPcs = :crtnPcs"),
    @NamedQuery(name = "CrmkCrmkSize.findByCrtnMtr", query = "SELECT c FROM CrmkCrmkSize c WHERE c.crtnMtr = :crtnMtr"),
    @NamedQuery(name = "CrmkCrmkSize.findByCrtnKg", query = "SELECT c FROM CrmkCrmkSize c WHERE c.crtnKg = :crtnKg"),
    @NamedQuery(name = "CrmkCrmkSize.findByPltCrtn", query = "SELECT c FROM CrmkCrmkSize c WHERE c.pltCrtn = :pltCrtn"),
    @NamedQuery(name = "CrmkCrmkSize.findByNotes", query = "SELECT c FROM CrmkCrmkSize c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkCrmkSize.findByPltMtr", query = "SELECT c FROM CrmkCrmkSize c WHERE c.pltMtr = :pltMtr"),
    @NamedQuery(name = "CrmkCrmkSize.findByPltKg", query = "SELECT c FROM CrmkCrmkSize c WHERE c.pltKg = :pltKg")})
public class CrmkCrmkSize implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "WIDTH")
    private BigDecimal width;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CRTN_PCS")
    private Long crtnPcs;
    @Column(name = "CRTN_MTR")
    private BigDecimal crtnMtr;
    @Column(name = "CRTN_KG")
    private BigDecimal crtnKg;
    @Column(name = "PLT_CRTN")
    private Long pltCrtn;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "PLT_MTR")
    private BigDecimal pltMtr;
    @Column(name = "PLT_KG")
    private BigDecimal pltKg;

    public CrmkCrmkSize() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCrtnPcs() {
        return crtnPcs;
    }

    public void setCrtnPcs(Long crtnPcs) {
        this.crtnPcs = crtnPcs;
    }

    public BigDecimal getCrtnMtr() {
        return crtnMtr;
    }

    public void setCrtnMtr(BigDecimal crtnMtr) {
        this.crtnMtr = crtnMtr;
    }

    public BigDecimal getCrtnKg() {
        return crtnKg;
    }

    public void setCrtnKg(BigDecimal crtnKg) {
        this.crtnKg = crtnKg;
    }

    public Long getPltCrtn() {
        return pltCrtn;
    }

    public void setPltCrtn(Long pltCrtn) {
        this.pltCrtn = pltCrtn;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getPltMtr() {
        return pltMtr;
    }

    public void setPltMtr(BigDecimal pltMtr) {
        this.pltMtr = pltMtr;
    }

    public BigDecimal getPltKg() {
        return pltKg;
    }

    public void setPltKg(BigDecimal pltKg) {
        this.pltKg = pltKg;
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
        if (!(object instanceof CrmkCrmkSize)) {
            return false;
        }
        CrmkCrmkSize other = (CrmkCrmkSize) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkCrmkSize[id=" + id + "]";
    }

}

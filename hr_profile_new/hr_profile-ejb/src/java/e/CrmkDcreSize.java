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
@Table(name = "CRMK_DCRE_SIZE", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkDcreSize.findAll", query = "SELECT c FROM CrmkDcreSize c"),
    @NamedQuery(name = "CrmkDcreSize.findById", query = "SELECT c FROM CrmkDcreSize c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkDcreSize.findByWidth", query = "SELECT c FROM CrmkDcreSize c WHERE c.width = :width"),
    @NamedQuery(name = "CrmkDcreSize.findByName", query = "SELECT c FROM CrmkDcreSize c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkDcreSize.findByCrtnPcs", query = "SELECT c FROM CrmkDcreSize c WHERE c.crtnPcs = :crtnPcs"),
    @NamedQuery(name = "CrmkDcreSize.findByCrtnMtr", query = "SELECT c FROM CrmkDcreSize c WHERE c.crtnMtr = :crtnMtr"),
    @NamedQuery(name = "CrmkDcreSize.findByCrtnKg", query = "SELECT c FROM CrmkDcreSize c WHERE c.crtnKg = :crtnKg"),
    @NamedQuery(name = "CrmkDcreSize.findByPltCrtn", query = "SELECT c FROM CrmkDcreSize c WHERE c.pltCrtn = :pltCrtn"),
    @NamedQuery(name = "CrmkDcreSize.findByNotes", query = "SELECT c FROM CrmkDcreSize c WHERE c.notes = :notes")})
public class CrmkDcreSize implements Serializable {
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

    public CrmkDcreSize() {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmkDcreSize)) {
            return false;
        }
        CrmkDcreSize other = (CrmkDcreSize) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkDcreSize[id=" + id + "]";
    }

}

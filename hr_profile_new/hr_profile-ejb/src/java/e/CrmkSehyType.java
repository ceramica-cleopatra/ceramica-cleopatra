/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
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
@Table(name = "CRMK_SEHY_TYPE", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkSehyType.findAll", query = "SELECT c FROM CrmkSehyType c"),
    @NamedQuery(name = "CrmkSehyType.findById", query = "SELECT c FROM CrmkSehyType c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkSehyType.findByName", query = "SELECT c FROM CrmkSehyType c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkSehyType.findByTakm", query = "SELECT c FROM CrmkSehyType c WHERE c.takm = :takm"),
    @NamedQuery(name = "CrmkSehyType.findByNotes", query = "SELECT c FROM CrmkSehyType c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkSehyType.findByColor", query = "SELECT c FROM CrmkSehyType c WHERE c.color = :color"),
    @NamedQuery(name = "CrmkSehyType.findByDekala", query = "SELECT c FROM CrmkSehyType c WHERE c.dekala = :dekala"),
    @NamedQuery(name = "CrmkSehyType.findByFrz", query = "SELECT c FROM CrmkSehyType c WHERE c.frz = :frz"),
    @NamedQuery(name = "CrmkSehyType.findByCompanyId", query = "SELECT c FROM CrmkSehyType c WHERE c.companyId = :companyId")})
public class CrmkSehyType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TAKM")
    private Character takm;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "COLOR")
    private Character color;
    @Column(name = "DEKALA")
    private Character dekala;
    @Column(name = "FRZ")
    private Character frz;
    @Column(name = "COMPANY_ID")
    private Long companyId;

    public CrmkSehyType() {
    }

    public CrmkSehyType(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getTakm() {
        return takm;
    }

    public void setTakm(Character takm) {
        this.takm = takm;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Character getColor() {
        return color;
    }

    public void setColor(Character color) {
        this.color = color;
    }

    public Character getDekala() {
        return dekala;
    }

    public void setDekala(Character dekala) {
        this.dekala = dekala;
    }

    public Character getFrz() {
        return frz;
    }

    public void setFrz(Character frz) {
        this.frz = frz;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
        if (!(object instanceof CrmkSehyType)) {
            return false;
        }
        CrmkSehyType other = (CrmkSehyType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkSehyType[id=" + id + "]";
    }

}

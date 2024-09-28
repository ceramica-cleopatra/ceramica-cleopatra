/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
@Table(name = "CRMK_DCRE_TYPE", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkDcreType.findAll", query = "SELECT c FROM CrmkDcreType c"),
    @NamedQuery(name = "CrmkDcreType.findById", query = "SELECT c FROM CrmkDcreType c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkDcreType.findByName", query = "SELECT c FROM CrmkDcreType c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkDcreType.findByGrpId", query = "SELECT c FROM CrmkDcreType c WHERE c.grpId = :grpId"),
    @NamedQuery(name = "CrmkDcreType.findByDfltUnit", query = "SELECT c FROM CrmkDcreType c WHERE c.dfltUnit = :dfltUnit"),
    @NamedQuery(name = "CrmkDcreType.findByGrdUnit", query = "SELECT c FROM CrmkDcreType c WHERE c.grdUnit = :grdUnit"),
    @NamedQuery(name = "CrmkDcreType.findByFactoryNo", query = "SELECT c FROM CrmkDcreType c WHERE c.factoryNo = :factoryNo"),
    @NamedQuery(name = "CrmkDcreType.findByDekala", query = "SELECT c FROM CrmkDcreType c WHERE c.dekala = :dekala"),
    @NamedQuery(name = "CrmkDcreType.findByTablow", query = "SELECT c FROM CrmkDcreType c WHERE c.tablow = :tablow"),
    @NamedQuery(name = "CrmkDcreType.findByC", query = "SELECT c FROM CrmkDcreType c WHERE c.c = :c"),
    @NamedQuery(name = "CrmkDcreType.findByTone", query = "SELECT c FROM CrmkDcreType c WHERE c.tone = :tone"),
    @NamedQuery(name = "CrmkDcreType.findBySizeId", query = "SELECT c FROM CrmkDcreType c WHERE c.sizeId = :sizeId"),
    @NamedQuery(name = "CrmkDcreType.findByFrz", query = "SELECT c FROM CrmkDcreType c WHERE c.frz = :frz"),
    @NamedQuery(name = "CrmkDcreType.findByNotes", query = "SELECT c FROM CrmkDcreType c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkDcreType.findByCompanyId", query = "SELECT c FROM CrmkDcreType c WHERE c.companyId = :companyId")})
public class CrmkDcreType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "GRP_ID")
    private Long grpId;
    @Column(name = "DFLT_UNIT")
    private Long dfltUnit;
    @Column(name = "GRD_UNIT")
    private Long grdUnit;
    @Column(name = "FACTORY_NO")
    private Character factoryNo;
    @Column(name = "DEKALA")
    private Character dekala;
    @Column(name = "TABLOW")
    private Character tablow;
    @Column(name = "C")
    private Character c;
    @Column(name = "TONE")
    private Character tone;
    @Column(name = "SIZE_ID")
    private Character sizeId;
    @Column(name = "FRZ")
    private Character frz;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "COMPANY_ID")
    private Long companyId;

    public CrmkDcreType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public Long getDfltUnit() {
        return dfltUnit;
    }

    public void setDfltUnit(Long dfltUnit) {
        this.dfltUnit = dfltUnit;
    }

    public Long getGrdUnit() {
        return grdUnit;
    }

    public void setGrdUnit(Long grdUnit) {
        this.grdUnit = grdUnit;
    }

    public Character getFactoryNo() {
        return factoryNo;
    }

    public void setFactoryNo(Character factoryNo) {
        this.factoryNo = factoryNo;
    }

    public Character getDekala() {
        return dekala;
    }

    public void setDekala(Character dekala) {
        this.dekala = dekala;
    }

    public Character getTablow() {
        return tablow;
    }

    public void setTablow(Character tablow) {
        this.tablow = tablow;
    }

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public Character getTone() {
        return tone;
    }

    public void setTone(Character tone) {
        this.tone = tone;
    }

    public Character getSizeId() {
        return sizeId;
    }

    public void setSizeId(Character sizeId) {
        this.sizeId = sizeId;
    }

    public Character getFrz() {
        return frz;
    }

    public void setFrz(Character frz) {
        this.frz = frz;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        if (!(object instanceof CrmkDcreType)) {
            return false;
        }
        CrmkDcreType other = (CrmkDcreType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkDcreType[id=" + id + "]";
    }

}

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
@Table(name = "CRMK_CRMK_TYPE", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkCrmkType.findAll", query = "SELECT c FROM CrmkCrmkType c"),
    @NamedQuery(name = "CrmkCrmkType.findById", query = "SELECT c FROM CrmkCrmkType c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkCrmkType.findByName", query = "SELECT c FROM CrmkCrmkType c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkCrmkType.findByDfltUnit", query = "SELECT c FROM CrmkCrmkType c WHERE c.dfltUnit = :dfltUnit"),
    @NamedQuery(name = "CrmkCrmkType.findByGrdUnit", query = "SELECT c FROM CrmkCrmkType c WHERE c.grdUnit = :grdUnit"),
    @NamedQuery(name = "CrmkCrmkType.findByNotes", query = "SELECT c FROM CrmkCrmkType c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkCrmkType.findByFactoryNo", query = "SELECT c FROM CrmkCrmkType c WHERE c.factoryNo = :factoryNo"),
    @NamedQuery(name = "CrmkCrmkType.findByDekala", query = "SELECT c FROM CrmkCrmkType c WHERE c.dekala = :dekala"),
    @NamedQuery(name = "CrmkCrmkType.findByTablow", query = "SELECT c FROM CrmkCrmkType c WHERE c.tablow = :tablow"),
    @NamedQuery(name = "CrmkCrmkType.findByC", query = "SELECT c FROM CrmkCrmkType c WHERE c.c = :c"),
    @NamedQuery(name = "CrmkCrmkType.findByTone", query = "SELECT c FROM CrmkCrmkType c WHERE c.tone = :tone"),
    @NamedQuery(name = "CrmkCrmkType.findByGrpId", query = "SELECT c FROM CrmkCrmkType c WHERE c.grpId = :grpId"),
    @NamedQuery(name = "CrmkCrmkType.findBySizeId", query = "SELECT c FROM CrmkCrmkType c WHERE c.sizeId = :sizeId"),
    @NamedQuery(name = "CrmkCrmkType.findByFrz", query = "SELECT c FROM CrmkCrmkType c WHERE c.frz = :frz"),
    @NamedQuery(name = "CrmkCrmkType.findByCompanyId", query = "SELECT c FROM CrmkCrmkType c WHERE c.companyId = :companyId")})
public class CrmkCrmkType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DFLT_UNIT")
    private Long dfltUnit;
    @Column(name = "GRD_UNIT")
    private Long grdUnit;
    @Column(name = "NOTES")
    private String notes;
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
    @Column(name = "GRP_ID")
    private Long grpId;
    @Column(name = "SIZE_ID")
    private Character sizeId;
    @Column(name = "FRZ")
    private Character frz;
    @Column(name = "COMPANY_ID")
    private Long companyId;

    public CrmkCrmkType() {
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
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
        if (!(object instanceof CrmkCrmkType)) {
            return false;
        }
        CrmkCrmkType other = (CrmkCrmkType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkCrmkType[id=" + id + "]";
    }

}

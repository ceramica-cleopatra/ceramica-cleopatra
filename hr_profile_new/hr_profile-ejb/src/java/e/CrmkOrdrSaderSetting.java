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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "CRMK_ORDR_SADER_SETTING", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkOrdrSaderSetting.findAll", query = "SELECT c FROM CrmkOrdrSaderSetting c where c.brnId=:brn_id"),
    @NamedQuery(name = "CrmkOrdrSaderSetting.findById", query = "SELECT c FROM CrmkOrdrSaderSetting c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkOrdrSaderSetting.findByBrnId", query = "SELECT c FROM CrmkOrdrSaderSetting c WHERE c.brnId = :brnId"),
    @NamedQuery(name = "CrmkOrdrSaderSetting.findByMaxAmount", query = "SELECT c FROM CrmkOrdrSaderSetting c WHERE c.maxAmount = :maxAmount"),
    @NamedQuery(name = "CrmkOrdrSaderSetting.findByMaxNo", query = "SELECT c FROM CrmkOrdrSaderSetting c WHERE c.maxNo = :maxNo"),
    @NamedQuery(name = "CrmkOrdrSaderSetting.findByHouresPrivilage", query = "SELECT c FROM CrmkOrdrSaderSetting c WHERE c.houresPrivilage = :houresPrivilage"),
    @NamedQuery(name = "CrmkOrdrSaderSetting.findByNotes", query = "SELECT c FROM CrmkOrdrSaderSetting c WHERE c.notes = :notes")})
public class CrmkOrdrSaderSetting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "BRN_ID")
    private Long brnId;
    @Column(name = "MAX_AMOUNT")
    private Long maxAmount;
    @Column(name = "MAX_NO")
    private Long maxNo;
    @Column(name = "HOURES_PRIVILAGE")
    private Long houresPrivilage;
    @Column(name = "NOTES")
    private String notes;

    public CrmkOrdrSaderSetting() {
    }

    public CrmkOrdrSaderSetting(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public Long getHouresPrivilage() {
        return houresPrivilage;
    }

    public void setHouresPrivilage(Long houresPrivilage) {
        this.houresPrivilage = houresPrivilage;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    

    public Long getMaxNo() {
        return maxNo;
    }

    public void setMaxNo(Long maxNo) {
        this.maxNo = maxNo;
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
        if (!(object instanceof CrmkOrdrSaderSetting)) {
            return false;
        }
        CrmkOrdrSaderSetting other = (CrmkOrdrSaderSetting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkOrdrSaderSetting[id=" + id + "]";
    }

}

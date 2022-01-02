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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_DYN_ALERT_TEMPLATE_COMP_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDynAlertTemplateCompDt.findAll", query = "SELECT h FROM HrDynAlertTemplateCompDt h"),
    @NamedQuery(name = "HrDynAlertTemplateCompDt.findById", query = "SELECT h FROM HrDynAlertTemplateCompDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrDynAlertTemplateCompDt.findByItemLabel", query = "SELECT h FROM HrDynAlertTemplateCompDt h WHERE h.itemLabel = :itemLabel"),
    @NamedQuery(name = "HrDynAlertTemplateCompDt.findByItemValue", query = "SELECT h FROM HrDynAlertTemplateCompDt h WHERE h.itemValue = :itemValue")})
public class HrDynAlertTemplateCompDt  implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_DYN_ALERT_TEMPLATE_COMP_SEQ", sequenceName="HR_DYN_ALERT_TEMPLATE_COMP_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_DYN_ALERT_TEMPLATE_COMP_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ITEM_LABEL")
    private String itemLabel;
    @Column(name = "ITEM_VALUE")
    private String itemValue;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrDynAlertTemplateDt hrDynAlertTemplateDt;

    public HrDynAlertTemplateCompDt() {
    }

    public HrDynAlertTemplateCompDt(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public HrDynAlertTemplateDt getHrDynAlertTemplateDt() {
        return hrDynAlertTemplateDt;
    }

    public void setHrDynAlertTemplateDt(HrDynAlertTemplateDt hrDynAlertTemplateDt) {
        this.hrDynAlertTemplateDt = hrDynAlertTemplateDt;
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
        if (!(object instanceof HrDynAlertTemplateCompDt)) {
            return false;
        }
        HrDynAlertTemplateCompDt other = (HrDynAlertTemplateCompDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDynAlertTemplateCompDt[id=" + id + "]";
    }

}

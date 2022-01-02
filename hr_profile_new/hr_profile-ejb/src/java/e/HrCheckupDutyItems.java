/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author data
 */
@Entity
@Table(name = "HR_CHECKUP_DUTY_ITEMS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyItems.findAll", query = "SELECT h FROM HrCheckupDutyItems h"),
    @NamedQuery(name = "HrCheckupDutyItems.findById", query = "SELECT h FROM HrCheckupDutyItems h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyItems.findByTitle", query = "SELECT h FROM HrCheckupDutyItems h WHERE h.title = :title"),
    @NamedQuery(name = "HrCheckupDutyItems.findByDisplayOrder", query = "SELECT h FROM HrCheckupDutyItems h WHERE h.displayOrder = :displayOrder")})
public class HrCheckupDutyItems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DISPLAY_ORDER")
    private Long displayOrder;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCheckupDutySetupHd hrCheckupDutySetupHd;

    public HrCheckupDutyItems() {
    }

    public HrCheckupDutyItems(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public HrCheckupDutySetupHd getHrCheckupDutySetupHd() {
        return hrCheckupDutySetupHd;
    }

    public void setHrCheckupDutySetupHd(HrCheckupDutySetupHd hrCheckupDutySetupHd) {
        this.hrCheckupDutySetupHd = hrCheckupDutySetupHd;
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
        if (!(object instanceof HrCheckupDutyItems)) {
            return false;
        }
        HrCheckupDutyItems other = (HrCheckupDutyItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyItems[id=" + id + "]";
    }

}

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
 * @author data
 */
@Entity
@Table(name = "HR_CHECKUP_DUTY_TITLE_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyTitleDt.findAll", query = "SELECT h FROM HrCheckupDutyTitleDt h"),
    @NamedQuery(name = "HrCheckupDutyTitleDt.findById", query = "SELECT h FROM HrCheckupDutyTitleDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyTitleDt.findByTitle", query = "SELECT h FROM HrCheckupDutyTitleDt h WHERE h.title = :title")})
public class HrCheckupDutyTitleDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "TITLE")
    private String title;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCheckupDutyTitleHd hrCheckupDutyTitleHd;

    public HrCheckupDutyTitleDt() {
    }

    public HrCheckupDutyTitleDt(BigDecimal id) {
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

    public HrCheckupDutyTitleHd getHrCheckupDutyTitleHd() {
        return hrCheckupDutyTitleHd;
    }

    public void setHrCheckupDutyTitleHd(HrCheckupDutyTitleHd hrCheckupDutyTitleHd) {
        this.hrCheckupDutyTitleHd = hrCheckupDutyTitleHd;
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
        if (!(object instanceof HrCheckupDutyTitleDt)) {
            return false;
        }
        HrCheckupDutyTitleDt other = (HrCheckupDutyTitleDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyTitleDt[id=" + id + "]";
    }

}

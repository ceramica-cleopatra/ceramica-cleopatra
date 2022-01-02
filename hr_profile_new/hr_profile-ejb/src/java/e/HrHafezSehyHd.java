/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_HAFEZ_SEHY_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHafezSehyHd.findAll", query = "SELECT h FROM HrHafezSehyHd h"),
    @NamedQuery(name = "HrHafezSehyHd.findById", query = "SELECT h FROM HrHafezSehyHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrHafezSehyHd.findByDateFrom", query = "SELECT h FROM HrHafezSehyHd h WHERE h.dateFrom = :dateFrom"),
    @NamedQuery(name = "HrHafezSehyHd.findByDateTo", query = "SELECT h FROM HrHafezSehyHd h WHERE h.dateTo = :dateTo")})
public class HrHafezSehyHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "DATE_FROM")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Column(name = "DATE_TO")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @OneToMany(mappedBy = "hrHafezSehyHd")
    private List<HrHafezSehyDt> hrHafezSehyDtList;

    public HrHafezSehyHd() {
    }

    public HrHafezSehyHd(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public List<HrHafezSehyDt> getHrHafezSehyDtList() {
        return hrHafezSehyDtList;
    }

    public void setHrHafezSehyDtList(List<HrHafezSehyDt> hrHafezSehyDtList) {
        this.hrHafezSehyDtList = hrHafezSehyDtList;
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
        if (!(object instanceof HrHafezSehyHd)) {
            return false;
        }
        HrHafezSehyHd other = (HrHafezSehyHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrHafezSehyHd[id=" + id + "]";
    }

}

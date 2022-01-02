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
@Table(name = "HR_HAFEZ_SEHY_MNG_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHafezSehyMngHd.findAll", query = "SELECT h FROM HrHafezSehyMngHd h"),
    @NamedQuery(name = "HrHafezSehyMngHd.findById", query = "SELECT h FROM HrHafezSehyMngHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrHafezSehyMngHd.findByDateFrom", query = "SELECT h FROM HrHafezSehyMngHd h WHERE h.dateFrom = :dateFrom"),
    @NamedQuery(name = "HrHafezSehyMngHd.findByDateTo", query = "SELECT h FROM HrHafezSehyMngHd h WHERE h.dateTo = :dateTo")})
public class HrHafezSehyMngHd implements Serializable {
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
    @OneToMany(mappedBy = "hrHafezSehyMngHd")
    private List<HrHafezSehyMngDt> hrHafezSehyMngDtList;

    public HrHafezSehyMngHd() {
    }

    public HrHafezSehyMngHd(BigDecimal id) {
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

    public List<HrHafezSehyMngDt> getHrHafezSehyMngDtList() {
        return hrHafezSehyMngDtList;
    }

    public void setHrHafezSehyMngDtList(List<HrHafezSehyMngDt> hrHafezSehyMngDtList) {
        this.hrHafezSehyMngDtList = hrHafezSehyMngDtList;
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
        if (!(object instanceof HrHafezSehyMngHd)) {
            return false;
        }
        HrHafezSehyMngHd other = (HrHafezSehyMngHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrHafezSehyMngHd[id=" + id + "]";
    }

}

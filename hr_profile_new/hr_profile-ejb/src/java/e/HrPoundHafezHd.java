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
 * @author Administrator
 */
@Entity
@Table(name = "HR_POUND_HAFEZ_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrPoundHafezHd.findAll", query = "SELECT h FROM HrPoundHafezHd h"),
    @NamedQuery(name = "HrPoundHafezHd.findById", query = "SELECT h FROM HrPoundHafezHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrPoundHafezHd.findByFromDate", query = "SELECT h FROM HrPoundHafezHd h WHERE h.fromDate = :fromDate"),
    @NamedQuery(name = "HrPoundHafezHd.findByToDate", query = "SELECT h FROM HrPoundHafezHd h WHERE h.toDate = :toDate")})
public class HrPoundHafezHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @OneToMany(mappedBy = "hrPoundHafezHd")
    private List<HrPoundHafezDt> hrPoundHafezDtList;

    public HrPoundHafezHd() {
    }

    public HrPoundHafezHd(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public List<HrPoundHafezDt> getHrPoundHafezDtList() {
        return hrPoundHafezDtList;
    }

    public void setHrPoundHafezDtList(List<HrPoundHafezDt> hrPoundHafezDtList) {
        this.hrPoundHafezDtList = hrPoundHafezDtList;
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
        if (!(object instanceof HrPoundHafezHd)) {
            return false;
        }
        HrPoundHafezHd other = (HrPoundHafezHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrPoundHafezHd[id=" + id + "]";
    }

}

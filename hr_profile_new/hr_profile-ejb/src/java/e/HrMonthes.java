/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_MONTHES", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrMonthes.findAll", query = "SELECT h FROM HrMonthes h"),
    @NamedQuery(name = "HrMonthes.findById", query = "SELECT h FROM HrMonthes h WHERE h.id = :id"),
    @NamedQuery(name = "HrMonthes.findByName", query = "SELECT h FROM HrMonthes h WHERE h.name = :name")})
public class HrMonthes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "trnsMonth")
    private List<HrTrgtHist> hrtrgttList;

    public List<HrTrgtHist> getHrtrgttList() {
        return hrtrgttList;
    }

    public void setHrtrgttList(List<HrTrgtHist> hrtrgttList) {
        this.hrtrgttList = hrtrgttList;
    }
    public HrMonthes() {
    }

    public HrMonthes(BigDecimal id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrMonthes)) {
            return false;
        }
        HrMonthes other = (HrMonthes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrMonthes[id=" + id + "]";
    }

}

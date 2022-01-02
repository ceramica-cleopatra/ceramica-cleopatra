/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_GZA_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrGzaHd.findAll", query = "SELECT h FROM HrGzaHd h where h.mngNo=:mng order by h.trnsDate desc"),
    @NamedQuery(name = "HrGzaHd.findMax", query = "SELECT max(h.id) FROM HrGzaHd h"),
    @NamedQuery(name = "HrGzaHd.findById", query = "SELECT h FROM HrGzaHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrGzaHd.findByTrnsDate", query = "SELECT h FROM HrGzaHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrGzaHd.findByTrnsMonth", query = "SELECT h FROM HrGzaHd h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrGzaHd.findByTrnsYear", query = "SELECT h FROM HrGzaHd h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrGzaHd.findByGzaType", query = "SELECT h FROM HrGzaHd h WHERE h.gzaType = :gzaType"),
    @NamedQuery(name = "HrGzaHd.findByMngNo", query = "SELECT h FROM HrGzaHd h WHERE h.mngNo = :mngNo")})
public class HrGzaHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "TRNS_MONTH")
    private Long trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Long trnsYear;
    @JoinColumn(name = "GZA_TYPE")
    @ManyToOne
    private HrGzaReason gzaType;
    @Column(name = "MNG_NO")
    private Long mngNo;
    @OneToMany(mappedBy = "hrGzaHd")
    private List<HrGzaDt> hrGzaDtList;

    public HrGzaHd() {
    }

    public HrGzaReason getGzaType() {
        return gzaType;
    }

    public void setGzaType(HrGzaReason gzaType) {
        this.gzaType = gzaType;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMngNo() {
        return mngNo;
    }

    public void setMngNo(Long mngNo) {
        this.mngNo = mngNo;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Long getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(Long trnsMonth) {
        this.trnsMonth = trnsMonth;
    }

    public Long getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Long trnsYear) {
        this.trnsYear = trnsYear;
    }

   

    public List<HrGzaDt> getHrGzaDtList() {
        return hrGzaDtList;
    }

    public void setHrGzaDtList(List<HrGzaDt> hrGzaDtList) {
        this.hrGzaDtList = hrGzaDtList;
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
        if (!(object instanceof HrGzaHd)) {
            return false;
        }
        HrGzaHd other = (HrGzaHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrGzaHd[id=" + id + "]";
    }

}

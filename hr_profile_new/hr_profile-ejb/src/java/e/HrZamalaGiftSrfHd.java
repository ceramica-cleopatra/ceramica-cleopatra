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
 * @author data
 */
@Entity
@Table(name = "HR_ZAMALA_GIFT_SRF_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrZamalaGiftSrfHd.findAll", query = "SELECT h FROM HrZamalaGiftSrfHd h"),
    @NamedQuery(name = "HrZamalaGiftSrfHd.findById", query = "SELECT h FROM HrZamalaGiftSrfHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrZamalaGiftSrfHd.findByTrnsDate", query = "SELECT h FROM HrZamalaGiftSrfHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrZamalaGiftSrfHd.findByNo", query = "SELECT h FROM HrZamalaGiftSrfHd h WHERE h.serial = :no"),
    @NamedQuery(name = "HrZamalaGiftSrfHd.findByClosed", query = "SELECT h FROM HrZamalaGiftSrfHd h WHERE h.closed = :closed"),
    @NamedQuery(name = "HrZamalaGiftSrfHd.findByCanceled", query = "SELECT h FROM HrZamalaGiftSrfHd h WHERE h.canceled = :canceled")})
public class HrZamalaGiftSrfHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Basic(optional = false)
    @Column(name = "NO")
    private Long serial;
    @Column(name = "CLOSED")
    private Character closed;
    @Column(name = "CANCELED")
    private Character canceled;
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrZamalaGiftTypes hrZamalaGiftTypes;
    @OneToMany(mappedBy = "hrZamalaGiftSrfHd")
    private List<HrZamalaGiftSrfDt> hrZamalaGiftSrfDtList;

    public HrZamalaGiftSrfHd() {
    }

    public HrZamalaGiftSrfHd(Long id) {
        this.id = id;
    }

    public HrZamalaGiftSrfHd(Long id, Date trnsDate, Long no) {
        this.id = id;
        this.trnsDate = trnsDate;
        this.serial = no;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

   

    public Character getClosed() {
        return closed;
    }

    public void setClosed(Character closed) {
        this.closed = closed;
    }

    public Character getCanceled() {
        return canceled;
    }

    public void setCanceled(Character canceled) {
        this.canceled = canceled;
    }

    public HrZamalaGiftTypes getHrZamalaGiftTypes() {
        return hrZamalaGiftTypes;
    }

    public void setHrZamalaGiftTypes(HrZamalaGiftTypes hrZamalaGiftTypes) {
        this.hrZamalaGiftTypes = hrZamalaGiftTypes;
    }

    public List<HrZamalaGiftSrfDt> getHrZamalaGiftSrfDtList() {
        return hrZamalaGiftSrfDtList;
    }

    public void setHrZamalaGiftSrfDtList(List<HrZamalaGiftSrfDt> hrZamalaGiftSrfDtList) {
        this.hrZamalaGiftSrfDtList = hrZamalaGiftSrfDtList;
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
        if (!(object instanceof HrZamalaGiftSrfHd)) {
            return false;
        }
        HrZamalaGiftSrfHd other = (HrZamalaGiftSrfHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrZamalaGiftSrfHd[id=" + id + "]";
    }

}

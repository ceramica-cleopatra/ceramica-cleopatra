/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_SCHEDULE_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrScheduleDt.findAll", query = "SELECT h FROM HrScheduleDt h"),
    @NamedQuery(name = "HrScheduleDt.getMax", query = "SELECT max(h.id) FROM HrScheduleDt h"),
    @NamedQuery(name = "HrScheduleDt.findLastStatus", query = "SELECT h FROM HrScheduleDt h WHERE h.trnsDate = (select max(o.trnsDate) from HrScheduleDt o where o.hrSchedule=h.hrSchedule) and h.hrSchedule.id=:id"),
    @NamedQuery(name = "HrScheduleDt.findById", query = "SELECT h FROM HrScheduleDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrScheduleDt.findByStatus", query = "SELECT h FROM HrScheduleDt h WHERE h.status = :status"),
    @NamedQuery(name = "HrScheduleDt.findByTrnsDate", query = "SELECT h FROM HrScheduleDt h WHERE h.trnsDate = :trnsDate")})
public class HrScheduleDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "STATUS")
    private Long status;
    @ManyToOne
    @JoinColumn(name = "HD_ID")
    private HrSchedule hrSchedule;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;

    public HrScheduleDt() {
    }

    public HrSchedule getHrSchedule() {
        return hrSchedule;
    }

    public void setHrSchedule(HrSchedule hrSchedule) {
        this.hrSchedule = hrSchedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

  

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
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
        if (!(object instanceof HrScheduleDt)) {
            return false;
        }
        HrScheduleDt other = (HrScheduleDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrScheduleDt[id=" + id + "]";
    }

}

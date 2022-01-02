/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author a.abbas
 */
@Entity
@Table(name = "HR_EVENT_BACKGROUND", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEventBackground.findAll", query = "SELECT h FROM HrEventBackground h"),
    @NamedQuery(name = "HrEventBackground.findById", query = "SELECT h FROM HrEventBackground h WHERE h.id = :id"),
    @NamedQuery(name = "HrEventBackground.findByEvent", query = "SELECT h FROM HrEventBackground h WHERE h.event = :event"),
    @NamedQuery(name = "HrEventBackground.findByDateFrom", query = "SELECT h FROM HrEventBackground h WHERE h.dateFrom = :dateFrom"),
    @NamedQuery(name = "HrEventBackground.findByDateTo", query = "SELECT h FROM HrEventBackground h WHERE h.dateTo = :dateTo"),
    @NamedQuery(name = "HrEventBackground.findByImageName", query = "SELECT h FROM HrEventBackground h WHERE h.imageName = :imageName")})
public class HrEventBackground implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "EVENT")
    private String event;
    @Column(name = "DATE_FROM")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Column(name = "DATE_TO")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @Column(name = "IMAGE_NAME")
    private String imageName;

    public HrEventBackground() {
    }

    public HrEventBackground(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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
        if (!(object instanceof HrEventBackground)) {
            return false;
        }
        HrEventBackground other = (HrEventBackground) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrEventBackground[id=" + id + "]";
    }

}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_ZAMALA_GIFT_SRF_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrZamalaGiftSrfDt.findAll", query = "SELECT h FROM HrZamalaGiftSrfDt h where h.empId=:emp_id order by h.eventDate desc"),
    @NamedQuery(name = "HrZamalaGiftSrfDt.findById", query = "SELECT h FROM HrZamalaGiftSrfDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrZamalaGiftSrfDt.findByAmount", query = "SELECT h FROM HrZamalaGiftSrfDt h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrZamalaGiftSrfDt.findByEventDate", query = "SELECT h FROM HrZamalaGiftSrfDt h WHERE h.eventDate = :eventDate"),
    @NamedQuery(name = "HrZamalaGiftSrfDt.findByNotes", query = "SELECT h FROM HrZamalaGiftSrfDt h WHERE h.notes = :notes")})
public class HrZamalaGiftSrfDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "EMP_ID",referencedColumnName = "EMP_NO")
    @ManyToOne
    HrEmpInfo empId;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "EVENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date eventDate;
    @Column(name = "NOTES")
    private String notes;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrZamalaGiftSrfHd hrZamalaGiftSrfHd;

    public HrZamalaGiftSrfDt() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public HrZamalaGiftSrfHd getHrZamalaGiftSrfHd() {
        return hrZamalaGiftSrfHd;
    }

    public void setHrZamalaGiftSrfHd(HrZamalaGiftSrfHd hrZamalaGiftSrfHd) {
        this.hrZamalaGiftSrfHd = hrZamalaGiftSrfHd;
    }

    public HrEmpInfo getEmpId() {
        return empId;
    }

    public void setEmpId(HrEmpInfo empId) {
        this.empId = empId;
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
        if (!(object instanceof HrZamalaGiftSrfDt)) {
            return false;
        }
        HrZamalaGiftSrfDt other = (HrZamalaGiftSrfDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrZamalaGiftSrfDt[id=" + id + "]";
    }

}

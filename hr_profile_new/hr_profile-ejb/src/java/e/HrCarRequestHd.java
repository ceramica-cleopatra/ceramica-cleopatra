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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_CAR_REQUEST_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCarRequestHd.findAll", query = "SELECT h FROM HrCarRequestHd h"),
    @NamedQuery(name = "HrCarRequestHd.findById", query = "SELECT h FROM HrCarRequestHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrCarRequestHd.findByTrnsDate", query = "SELECT h FROM HrCarRequestHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrCarRequestHd.findByCheckoutDate", query = "SELECT h FROM HrCarRequestHd h WHERE h.checkoutDate = :checkoutDate"),
    @NamedQuery(name = "HrCarRequestHd.findByPhoneNo", query = "SELECT h FROM HrCarRequestHd h WHERE h.phoneNo = :phoneNo"),
    @NamedQuery(name = "HrCarRequestHd.findByEmpNo", query = "SELECT h FROM HrCarRequestHd h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrCarRequestHd.findByCheckoutDays", query = "SELECT h FROM HrCarRequestHd h WHERE h.checkoutDays = :checkoutDays"),
    @NamedQuery(name = "HrCarRequestHd.findByNotes", query = "SELECT h FROM HrCarRequestHd h WHERE h.notes = :notes")})
public class HrCarRequestHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_CAR_REQUEST_HD_SEQ", sequenceName="HR_CAR_REQUEST_HD_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_CAR_REQUEST_HD_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "CHECKOUT_DATE")
    @Temporal(TemporalType.DATE)
    private Date checkoutDate;
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Column(name = "EMP_NO")
    private BigInteger empNo;
    @Column(name = "CHECKOUT_DAYS")
    private BigInteger checkoutDays;
    @Column(name = "NOTES")
    private String notes;
    @OneToMany(mappedBy = "hrCarRequestHd")
    private List<HrCarRequestMembers> hrCarRequestMembersList;
    @OneToMany(mappedBy = "hrCarRequestHd")
    private List<HrCarRequestLocations> hrCarRequestLocationsList;

    public HrCarRequestHd() {
    }

    public HrCarRequestHd(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public BigInteger getEmpNo() {
        return empNo;
    }

    public void setEmpNo(BigInteger empNo) {
        this.empNo = empNo;
    }

    public BigInteger getCheckoutDays() {
        return checkoutDays;
    }

    public void setCheckoutDays(BigInteger checkoutDays) {
        this.checkoutDays = checkoutDays;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<HrCarRequestMembers> getHrCarRequestMembersList() {
        return hrCarRequestMembersList;
    }

    public void setHrCarRequestMembersList(List<HrCarRequestMembers> hrCarRequestMembersList) {
        this.hrCarRequestMembersList = hrCarRequestMembersList;
    }

    public List<HrCarRequestLocations> getHrCarRequestLocationsList() {
        return hrCarRequestLocationsList;
    }

    public void setHrCarRequestLocationsList(List<HrCarRequestLocations> hrCarRequestLocationsList) {
        this.hrCarRequestLocationsList = hrCarRequestLocationsList;
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
        if (!(object instanceof HrCarRequestHd)) {
            return false;
        }
        HrCarRequestHd other = (HrCarRequestHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCarRequestHd[id=" + id + "]";
    }

}

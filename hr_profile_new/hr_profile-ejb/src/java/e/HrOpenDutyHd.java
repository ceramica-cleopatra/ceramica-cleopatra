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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_OPEN_DUTY_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrOpenDutyHd.findExpectedLocations", query = "SELECT h.hrOpenDutyExpectedLocDtList FROM HrOpenDutyHd h JOIN FETCH h.hrOpenDutyExpectedLocDtList where h=:open_hd"),
    @NamedQuery(name = "HrOpenDutyHd.findById", query = "SELECT h FROM HrOpenDutyHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrOpenDutyHd.findByTrnsDate", query = "SELECT h FROM HrOpenDutyHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrOpenDutyHd.findByMngId", query = "SELECT h FROM HrOpenDutyHd h WHERE h.mngId = :mngId"),
    @NamedQuery(name = "HrOpenDutyHd.findByEmpId", query = "SELECT h FROM HrOpenDutyHd h WHERE h.empId = :empId"),
    @NamedQuery(name = "HrOpenDutyHd.findByFromDate", query = "SELECT h FROM HrOpenDutyHd h WHERE h.fromDate = :fromDate"),
    @NamedQuery(name = "HrOpenDutyHd.findByToDate", query = "SELECT h FROM HrOpenDutyHd h WHERE h.toDate = :toDate"),
    @NamedQuery(name = "HrOpenDutyHd.findByNotes", query = "SELECT h FROM HrOpenDutyHd h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrOpenDutyHd.chkDutyForSpecificEmp", query = "SELECT count(h.id) FROM HrOpenDutyHd h WHERE h.empId = :emp_id and :trns_date between h.fromDate and h.toDate"),
    @NamedQuery(name = "HrOpenDutyHd.findDutyForSpecificEmp", query = "SELECT h FROM HrOpenDutyHd h WHERE h.empId = :emp_id and :trns_date<=h.fromDate")
})
public class HrOpenDutyHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_OPEN_DUTY_HD_SEQ", sequenceName="HR_OPEN_DUTY_HD_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_OPEN_DUTY_HD_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @ManyToOne
    @JoinColumn(name = "MNG_ID")
    private HrEmpInfo mngId;
    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private HrEmpInfo empId;
    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @Column(name = "NOTES")
    private String notes;
    @Column(name="CANCEL")
    private Character cancel;
    @OneToMany(mappedBy="hrOpenDutyHd" ,cascade=CascadeType.ALL)
    private List<HrOpenDutyDt> hrOpenDutyHdList;
    @OneToMany(mappedBy="hrOpenDutyHd",cascade=CascadeType.ALL)
    private List<HrOpenDutyExpectedLocDt> hrOpenDutyExpectedLocDtList;
    public HrOpenDutyHd() {
    }

  

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public HrEmpInfo getEmpId() {
        return empId;
    }

    public void setEmpId(HrEmpInfo empId) {
        this.empId = empId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrEmpInfo getMngId() {
        return mngId;
    }

    public void setMngId(HrEmpInfo mngId) {
        this.mngId = mngId;
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

   
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        if (!(object instanceof HrOpenDutyHd)) {
            return false;
        }
        HrOpenDutyHd other = (HrOpenDutyHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Character getCancel() {
        return cancel;
    }

    public void setCancel(Character cancel) {
        this.cancel = cancel;
    }

    @Override
    public String toString() {
        return "e.HrOpenDutyHd[id=" + id + "]";
    }

    public List<HrOpenDutyExpectedLocDt> getHrOpenDutyExpectedLocDtList() {
        return hrOpenDutyExpectedLocDtList;
    }

    public void setHrOpenDutyExpectedLocDtList(List<HrOpenDutyExpectedLocDt> hrOpenDutyExpectedLocDtList) {
        this.hrOpenDutyExpectedLocDtList = hrOpenDutyExpectedLocDtList;
    }

    public List<HrOpenDutyDt> getHrOpenDutyHdList() {
        return hrOpenDutyHdList;
    }

    public void setHrOpenDutyHdList(List<HrOpenDutyDt> hrOpenDutyHdList) {
        this.hrOpenDutyHdList = hrOpenDutyHdList;
    }

 

}

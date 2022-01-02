/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
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
 * @author data
 */
@Entity
@Table(name = "HR_CHECKUP_DUTY_HD1", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyHd1.findAll", query = "SELECT h FROM HrCheckupDutyHd1 h"),
    @NamedQuery(name = "HrCheckupDutyHd1.findById", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByTrnsDate", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByEntryDate", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.entryDate = :entryDate"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByEntryNo", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.entryNo = :entryNo and h.trnsDate>=:trnsDate"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByLocId", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByInTrns", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.inTrns = :inTrns"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByOutTrns", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.outTrns = :outTrns"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByDone", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.done = :done"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByApproved", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.approved = :approved"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByApproveDate", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.approveDate = :approveDate"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByMngNo", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrCheckupDutyHd1.findByRejectReason", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.rejectReason = :rejectReason"),
    @NamedQuery(name = "HrCheckupDutyHd1.findNotApproved", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE h.approved is null and h.done='Y' order by h.trnsDate"),
    @NamedQuery(name = "HrCheckupDutyHd1.searchForActions", query = "SELECT h FROM HrCheckupDutyHd1 h WHERE ((h.approved is null and :approved=2) or (h.approved is not null and :approved=1) or :approved=3) and h.done='Y'"
    + " and h.trnsDate between :fromDate and :toDate and (h.entryNo=:emp or :emp is null) and (h.locId=:loc or :loc is null) and (h.entryNo.deptId=:dept_id or :dept_id is null) order by h.trnsDate")})
public class HrCheckupDutyHd1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_CHECKUP_DUTY_HD_SEQ", sequenceName="HR_CHECKUP_DUTY_HD_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_CHECKUP_DUTY_HD_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "ENTRY_DATE")
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    @ManyToOne
    @JoinColumn(name = "ENTRY_NO")
    private HrEmpInfo entryNo;
    @ManyToOne
    @JoinColumn(name = "LOC_ID")
    private HrLocation locId;
    @Column(name = "IN_TRNS")
    @Temporal(TemporalType.DATE)
    private Date inTrns;
    @Column(name = "OUT_TRNS")
    @Temporal(TemporalType.DATE)
    private Date outTrns;
    @Column(name = "DONE")
    private Character done;
    @Column(name = "APPROVED")
    private Character approved;
    @Column(name = "APPROVE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approveDate;
    @Column(name = "MNG_NO")
    private Long mngNo;
    @Column(name = "REJECT_REASON")
    private String rejectReason;
    @OneToMany(mappedBy = "hrCheckupDutyHd1",cascade=CascadeType.ALL)
    private List<HrCheckupDutyEmployees> hrCheckupDutyEmployeesList;
    @OneToMany(mappedBy = "hrCheckupDutyHd1",cascade=CascadeType.ALL)
    private List<HrCheckupDutyEmp1> hrCheckupDutyEmp1List;
    @OneToMany(mappedBy = "hrCheckupDutyHd1",cascade=CascadeType.ALL)
    private List<HrCheckupDutyDt1> hrCheckupDutyDt1List;
    public HrCheckupDutyHd1() {
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

 

    public HrEmpInfo getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(HrEmpInfo entryNo) {
        this.entryNo = entryNo;
    }

    public HrLocation getLocId() {
        return locId;
    }

    public void setLocId(HrLocation locId) {
        this.locId = locId;
    }

    

    public Date getInTrns() {
        return inTrns;
    }

    public void setInTrns(Date inTrns) {
        this.inTrns = inTrns;
    }

    public Date getOutTrns() {
        return outTrns;
    }

    public void setOutTrns(Date outTrns) {
        this.outTrns = outTrns;
    }

    public Character getDone() {
        return done;
    }

    public void setDone(Character done) {
        this.done = done;
    }

    public Character getApproved() {
        return approved;
    }

    public void setApproved(Character approved) {
        this.approved = approved;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Long getMngNo() {
        return mngNo;
    }

    public void setMngNo(Long mngNo) {
        this.mngNo = mngNo;
    }

    

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public List<HrCheckupDutyEmployees> getHrCheckupDutyEmployeesList() {
        return hrCheckupDutyEmployeesList;
    }

    public void setHrCheckupDutyEmployeesList(List<HrCheckupDutyEmployees> hrCheckupDutyEmployeesList) {
        this.hrCheckupDutyEmployeesList = hrCheckupDutyEmployeesList;
    }

    public List<HrCheckupDutyDt1> getHrCheckupDutyDt1List() {
        if(hrCheckupDutyDt1List!=null)
            Collections.sort(hrCheckupDutyDt1List, new HrCheckupDutyDtComparator());
        return hrCheckupDutyDt1List;
    }

    public void setHrCheckupDutyDt1List(List<HrCheckupDutyDt1> hrCheckupDutyDt1List) {
        this.hrCheckupDutyDt1List = hrCheckupDutyDt1List;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public List<HrCheckupDutyEmp1> getHrCheckupDutyEmp1List() {
        return hrCheckupDutyEmp1List;
    }

    public void setHrCheckupDutyEmp1List(List<HrCheckupDutyEmp1> hrCheckupDutyEmp1List) {
        this.hrCheckupDutyEmp1List = hrCheckupDutyEmp1List;
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
        if (!(object instanceof HrCheckupDutyHd1)) {
            return false;
        }
        HrCheckupDutyHd1 other = (HrCheckupDutyHd1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyHd1[id=" + id + "]";
    }

}

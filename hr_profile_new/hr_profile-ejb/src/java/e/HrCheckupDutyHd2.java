/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
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
@Table(name = "HR_CHECKUP_DUTY_HD2", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyHd2.findAll", query = "SELECT h FROM HrCheckupDutyHd2 h"),
    @NamedQuery(name = "HrCheckupDutyHd2.findById", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByTrnsDate", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByEntryDate", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.entryDate = :entryDate"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByEntryNo", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.entryNo = :entryNo and h.trnsDate>=:trnsDate order by h.trnsDate desc"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByLocId", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByInTrns", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.inTrns = :inTrns"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByOutTrns", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.outTrns = :outTrns"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByDone", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.done = :done"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByApproved", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.approved = :approved"),
    @NamedQuery(name = "HrCheckupDutyHd2.findNotApproved", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.approved is null and h.done='Y' order by h.trnsDate"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByApproveDate", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.approveDate = :approveDate"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByMngNo", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByRejectReason", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.rejectReason = :rejectReason"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByTrnsType", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.trnsType = :trnsType"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByDriverNo", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.driverNo = :driverNo"),
    @NamedQuery(name = "HrCheckupDutyHd2.findByDriverRate", query = "SELECT h FROM HrCheckupDutyHd2 h WHERE h.driverRate = :driverRate")})
public class HrCheckupDutyHd2 implements Serializable {
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
    @JoinColumn(name = "ENTRY_NO")
    private HrEmpInfo entryNo;
    @JoinColumn(name = "LOC_ID")
    private HrLocation locId;
    @Column(name = "IN_TRNS")
    @Temporal(TemporalType.TIME)
    private Date inTrns;
    @Column(name = "OUT_TRNS")
    @Temporal(TemporalType.TIME)
    private Date outTrns;
    @Column(name = "DONE")
    private Character done;
    @Column(name = "APPROVED")
    private Character approved;
    @Column(name = "APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveDate;
    @Column(name = "MNG_NO")
    private Long mngNo;
    @Column(name = "REJECT_REASON")
    private String rejectReason;
    @Column(name = "TRNS_TYPE")
    private Long trnsType;
    @JoinColumn(name = "DRIVER_NO")
    private HrEmpInfo driverNo;
    @Column(name = "DRIVER_RATE")
    private Integer driverRate;
    @Column(name = "DRIVER_Amount")
    private Integer driverAmount;
    @OneToMany(mappedBy = "hrCheckupDutyHd2",cascade=CascadeType.ALL)
    private List<HrCheckupDutyEmp2> hrCheckupDutyEmp2List;
    @OneToMany(mappedBy = "hrCheckupDutyHd2",cascade=CascadeType.ALL)
    private List<HrCheckupDutyDt2> hrCheckupDutyDt2List;

    public HrCheckupDutyHd2() {
    }

   
    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
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

    
    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public HrEmpInfo getDriverNo() {
        return driverNo;
    }

    public void setDriverNo(HrEmpInfo driverNo) {
        this.driverNo = driverNo;
    }

    public Integer getDriverRate() {
        return driverRate;
    }

    public void setDriverRate(Integer driverRate) {
        this.driverRate = driverRate;
    }

    public HrEmpInfo getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(HrEmpInfo entryNo) {
        this.entryNo = entryNo;
    }

    public List<HrCheckupDutyDt2> getHrCheckupDutyDt2List() {
        if(hrCheckupDutyDt2List==null || hrCheckupDutyDt2List.isEmpty())
            return null;
        Collections.sort(hrCheckupDutyDt2List,new HrCheckupDutyDt2Comparator());
        return hrCheckupDutyDt2List;
    }

    public void setHrCheckupDutyDt2List(List<HrCheckupDutyDt2> hrCheckupDutyDt2List) {
        this.hrCheckupDutyDt2List = hrCheckupDutyDt2List;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrLocation getLocId() {
        return locId;
    }

    public void setLocId(HrLocation locId) {
        this.locId = locId;
    }

    public Long getMngNo() {
        return mngNo;
    }

    public void setMngNo(Long mngNo) {
        this.mngNo = mngNo;
    }

    public Long getTrnsType() {
        return trnsType;
    }

    public void setTrnsType(Long trnsType) {
        this.trnsType = trnsType;
    }

    public List<HrCheckupDutyEmp2> getHrCheckupDutyEmp2List() {
        return hrCheckupDutyEmp2List;
    }

    public void setHrCheckupDutyEmp2List(List<HrCheckupDutyEmp2> hrCheckupDutyEmp2List) {
        this.hrCheckupDutyEmp2List = hrCheckupDutyEmp2List;
    }

    public Integer getDriverAmount() {
        return driverAmount;
    }

    public void setDriverAmount(Integer driverAmount) {
        this.driverAmount = driverAmount;
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
        if (!(object instanceof HrCheckupDutyHd2)) {
            return false;
        }
        HrCheckupDutyHd2 other = (HrCheckupDutyHd2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyHd2[id=" + id + "]";
    }

}

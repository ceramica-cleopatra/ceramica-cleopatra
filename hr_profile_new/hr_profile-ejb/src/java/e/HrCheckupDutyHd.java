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
import javax.persistence.Cacheable;
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
@Table(name = "HR_CHECKUP_DUTY_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyHd.findAll", query = "SELECT h FROM HrCheckupDutyHd h"),
    @NamedQuery(name = "HrCheckupDutyHd.findById", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyHd.findByTrnsDate", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrCheckupDutyHd.findByEntryId", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.entryId = :entryId and h.trnsDate>=:trnsDate"),
    @NamedQuery(name = "HrCheckupDutyHd.findByEntryDate", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.entryDate = :entryDate"),
    @NamedQuery(name = "HrCheckupDutyHd.findByLocId", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrCheckupDutyHd.findByMngMeet", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.mngMeet = :mngMeet"),
    @NamedQuery(name = "HrCheckupDutyHd.findByMngMeetSumm", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.mngMeetSumm = :mngMeetSumm"),
    @NamedQuery(name = "HrCheckupDutyHd.findByEmpMeet", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.empMeet = :empMeet"),
    @NamedQuery(name = "HrCheckupDutyHd.findByEmpMeetSumm", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.empMeetSumm = :empMeetSumm"),
    @NamedQuery(name = "HrCheckupDutyHd.findByDeptMeet", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.deptMeet = :deptMeet"),
    @NamedQuery(name = "HrCheckupDutyHd.findByDeptMeetSumm", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.deptMeetSumm = :deptMeetSumm"),
    @NamedQuery(name = "HrCheckupDutyHd.findByPositive", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.positive = :positive"),
    @NamedQuery(name = "HrCheckupDutyHd.findByPositiveSumm", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.positiveSumm = :positiveSumm"),
    @NamedQuery(name = "HrCheckupDutyHd.findByNegative", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.negative = :negative"),
    @NamedQuery(name = "HrCheckupDutyHd.findByNegativeSumm", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.negativeSumm = :negativeSumm"),
    @NamedQuery(name = "HrCheckupDutyHd.findBySuggestion", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.suggestion = :suggestion"),
    @NamedQuery(name = "HrCheckupDutyHd.findBySuggestionSumm", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.suggestionSumm = :suggestionSumm"),
    @NamedQuery(name = "HrCheckupDutyHd.findByApprove", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.approve = :approve"),
    @NamedQuery(name = "HrCheckupDutyHd.checkExistance", query = "SELECT count(h) FROM HrCheckupDutyHd h WHERE h.entryId.empNo = :empNo and h.trnsDate=:trnsDate and h.locId.id=:locId and h.id<>:id"),
    @NamedQuery(name = "HrCheckupDutyHd.findNotApproved", query = "SELECT h FROM HrCheckupDutyHd h WHERE h.approve is null and h.done='Y' order by h.trnsDate")})
public class HrCheckupDutyHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_CHECKUP_DUTY_HD_SEQ", sequenceName="HR_CHECKUP_DUTY_HD_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_CHECKUP_DUTY_HD_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @ManyToOne
    @JoinColumn(name = "ENTRY_ID")
    private HrEmpInfo entryId;
    @Column(name = "ENTRY_DATE")
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    @ManyToOne
    @JoinColumn(name = "LOC_ID")
    private HrLocation locId;
    @Column(name = "MNG_MEET")
    private String mngMeet;
    @Column(name = "MNG_MEET_SUMM")
    private Character mngMeetSumm;
    @Column(name = "EMP_MEET")
    private String empMeet;
    @Column(name = "EMP_MEET_SUMM")
    private Character empMeetSumm;
    @Column(name = "DEPT_MEET")
    private String deptMeet;
    @Column(name = "DEPT_MEET_SUMM")
    private Character deptMeetSumm;
    @Column(name = "POSITIVE")
    private String positive;
    @Column(name = "POSITIVE_SUMM")
    private Character positiveSumm;
    @Column(name = "NEGATIVE")
    private String negative;
    @Column(name = "NEGATIVE_SUMM")
    private Character negativeSumm;
    @Column(name = "SUGGESTION")
    private String suggestion;
    @Column(name = "SUGGESTION_SUMM")
    private Character suggestionSumm;
    @Column(name = "APPROVE")
    private Character approve;
    @Column(name = "IN_TRNS")
    @Temporal(TemporalType.TIME)
    private Date inTrns;
    @Column(name = "OUT_TRNS")
    @Temporal(TemporalType.TIME)
    private Date outTrns;
    @Column(name = "Done")
    private Character done;
    @Column(name = "REJECT_REASON")
    private String rejectReason;
    @OneToMany(mappedBy = "hrCheckupDutyHd",cascade=CascadeType.ALL)
    private List<HrCheckupDutyDt> hrCheckupDutyDtList;
    @Column(name = "MNG_MEET_REPLY")
    private String mngMeetReply;
    @Column(name = "MNG_MEET_NOTES")
    private String mngMeetNotes;
    @Column(name = "EMP_MEET_REPLY")
    private String empMeetReply;
    @Column(name = "EMP_MEET_NOTES")
    private String empMeetNotes;
    @Column(name = "DEPT_MEET_REPLY")
    private String deptMeetReply;
    @Column(name = "DEPT_MEET_NOTES")
    private String deptMeetNotes;
    @Column(name = "POSITIVE_REPLY")
    private String positiveReply;
    @Column(name = "POSITIVE_NOTES")
    private String positiveNotes;
    @Column(name = "NEGATIVE_REPLY")
    private String negativeReply;
    @Column(name = "NEGATIVE_NOTES")
    private String negativeNotes;
    @Column(name = "SUGGESTION_REPLY")
    private String suggestionReply;
    @Column(name = "SUGGESTION_NOTES")
    private String suggestionNotes;
    @Column(name = "TRNS_TYPE")
    private Long trnsType;
    @Column(name = "DRIVER_NO")
    private Long driverNo;
    @Column(name = "DRIVER_RATE")
    private Integer driverRate;

    public HrCheckupDutyHd() {
    }

    public HrEmpInfo getEntryId() {
        return entryId;
    }

    public void setEntryId(HrEmpInfo entryId) {
        this.entryId = entryId;
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

   

    public String getMngMeet() {
        return mngMeet;
    }

    public void setMngMeet(String mngMeet) {
        this.mngMeet = mngMeet;
    }

    public Character getMngMeetSumm() {
        return mngMeetSumm;
    }

    public void setMngMeetSumm(Character mngMeetSumm) {
        this.mngMeetSumm = mngMeetSumm;
    }

    public String getEmpMeet() {
        return empMeet;
    }

    public void setEmpMeet(String empMeet) {
        this.empMeet = empMeet;
    }

    public Character getEmpMeetSumm() {
        return empMeetSumm;
    }

    public void setEmpMeetSumm(Character empMeetSumm) {
        this.empMeetSumm = empMeetSumm;
    }

    public String getDeptMeet() {
        return deptMeet;
    }

    public void setDeptMeet(String deptMeet) {
        this.deptMeet = deptMeet;
    }

    public Character getDeptMeetSumm() {
        return deptMeetSumm;
    }

    public void setDeptMeetSumm(Character deptMeetSumm) {
        this.deptMeetSumm = deptMeetSumm;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public Character getPositiveSumm() {
        return positiveSumm;
    }

    public void setPositiveSumm(Character positiveSumm) {
        this.positiveSumm = positiveSumm;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public Character getNegativeSumm() {
        return negativeSumm;
    }

    public void setNegativeSumm(Character negativeSumm) {
        this.negativeSumm = negativeSumm;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Character getSuggestionSumm() {
        return suggestionSumm;
    }

    public void setSuggestionSumm(Character suggestionSumm) {
        this.suggestionSumm = suggestionSumm;
    }

    public Character getApprove() {
        return approve;
    }

    public void setApprove(Character approve) {
        this.approve = approve;
    }

    public List<HrCheckupDutyDt> getHrCheckupDutyDtList() {
        return hrCheckupDutyDtList;
    }

    public void setHrCheckupDutyDtList(List<HrCheckupDutyDt> hrCheckupDutyDtList) {
        this.hrCheckupDutyDtList = hrCheckupDutyDtList;
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

    public String getDeptMeetNotes() {
        return deptMeetNotes;
    }

    public void setDeptMeetNotes(String deptMeetNotes) {
        this.deptMeetNotes = deptMeetNotes;
    }

    public String getDeptMeetReply() {
        return deptMeetReply;
    }

    public void setDeptMeetReply(String deptMeetReply) {
        this.deptMeetReply = deptMeetReply;
    }

    public String getEmpMeetNotes() {
        return empMeetNotes;
    }

    public void setEmpMeetNotes(String empMeetNotes) {
        this.empMeetNotes = empMeetNotes;
    }

    public String getEmpMeetReply() {
        return empMeetReply;
    }

    public void setEmpMeetReply(String empMeetReply) {
        this.empMeetReply = empMeetReply;
    }

    public String getMngMeetNotes() {
        return mngMeetNotes;
    }

    public void setMngMeetNotes(String mngMeetNotes) {
        this.mngMeetNotes = mngMeetNotes;
    }

    public String getMngMeetReply() {
        return mngMeetReply;
    }

    public void setMngMeetReply(String mngMeetReply) {
        this.mngMeetReply = mngMeetReply;
    }

    public String getNegativeNotes() {
        return negativeNotes;
    }

    public void setNegativeNotes(String negativeNotes) {
        this.negativeNotes = negativeNotes;
    }

    public String getNegativeReply() {
        return negativeReply;
    }

    public void setNegativeReply(String negativeReply) {
        this.negativeReply = negativeReply;
    }

    public String getPositiveNotes() {
        return positiveNotes;
    }

    public void setPositiveNotes(String positiveNotes) {
        this.positiveNotes = positiveNotes;
    }

    public String getPositiveReply() {
        return positiveReply;
    }

    public void setPositiveReply(String positiveReply) {
        this.positiveReply = positiveReply;
    }

    public String getSuggestionNotes() {
        return suggestionNotes;
    }

    public void setSuggestionNotes(String suggestionNotes) {
        this.suggestionNotes = suggestionNotes;
    }

    public String getSuggestionReply() {
        return suggestionReply;
    }

    public void setSuggestionReply(String suggestionReply) {
        this.suggestionReply = suggestionReply;
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
        if (!(object instanceof HrCheckupDutyHd)) {
            return false;
        }
        HrCheckupDutyHd other = (HrCheckupDutyHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Character getDone() {
        return done;
    }

    public void setDone(Character done) {
        this.done = done;
    }

    

    @Override
    public String toString() {
        return "e.HrCheckupDutyHd[id=" + id + "]";
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Long getDriverNo() {
        return driverNo;
    }

    public void setDriverNo(Long driverNo) {
        this.driverNo = driverNo;
    }

    public Integer getDriverRate() {
        return driverRate;
    }

    public void setDriverRate(Integer driverRate) {
        this.driverRate = driverRate;
    }

    public Long getTrnsType() {
        return trnsType;
    }

    public void setTrnsType(Long trnsType) {
        this.trnsType = trnsType;
    }

    

}

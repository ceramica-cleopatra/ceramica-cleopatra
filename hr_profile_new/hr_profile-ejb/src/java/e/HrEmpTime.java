/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_EMP_TIME", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpTime.findAll", query = "SELECT h FROM HrEmpTime h where h.trnsDate>=:from_date and h.hdId.empNo=:emp"),
    @NamedQuery(name = "HrEmpTime.lastStatus", query = "SELECT h FROM HrEmpTime h where h.hdId.empNo=:emp and h.trnsDate=(select max(o.trnsDate) from HrEmpTime o where o.hdId.empNo=:emp)"),
    @NamedQuery(name = "HrEmpTime.chkcnt", query = "SELECT count(h.id) FROM HrEmpTime h where h.trnsDate>=:from_date and h.hdId.empNo=:emp"),
    @NamedQuery(name = "HrEmpTime.chkShiftEntry", query = "SELECT count(distinct h.id) FROM HrEmpTime h where h.trnsDate between :from_date and :to_date and h.hdId.empNo=:emp"),
    @NamedQuery(name = "HrEmpTime.chkcntOfShiftAtDay", query = "SELECT count(h.id) FROM HrEmpTime h where h.trnsDate=:from_date and h.hdId.empNo=:emp"),
    @NamedQuery(name = "HrEmpTime.findById", query = "SELECT h FROM HrEmpTime h WHERE h.id = :id"),
    @NamedQuery(name = "HrEmpTime.findByTrnsDateAndEmpNo", query = "SELECT distinct h FROM HrEmpTime h WHERE h.trnsDate = :trnsDate and h.hdId.empNo=:emp"),
    @NamedQuery(name = "HrEmpTime.findByNotes", query = "SELECT h FROM HrEmpTime h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrEmpTime.findByUserName", query = "SELECT h FROM HrEmpTime h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrEmpTime.findByMachineName", query = "SELECT h FROM HrEmpTime h WHERE h.machineName = :machineName"),
    @NamedQuery(name = "HrEmpTime.findByCreateBy", query = "SELECT h FROM HrEmpTime h WHERE h.createBy = :createBy"),
    @NamedQuery(name = "HrEmpTime.findByCreateAt", query = "SELECT h FROM HrEmpTime h WHERE h.createAt = :createAt"),
    @NamedQuery(name = "HrEmpTime.findByModifyBy", query = "SELECT h FROM HrEmpTime h WHERE h.modifyBy = :modifyBy"),
    @NamedQuery(name = "HrEmpTime.findByModifyAt", query = "SELECT h FROM HrEmpTime h WHERE h.modifyAt = :modifyAt"),
    @NamedQuery(name = "HrEmpTime.findByByuser", query = "SELECT h FROM HrEmpTime h WHERE h.byuser = :byuser"),
    @NamedQuery(name = "HrEmpTime.findByReset", query = "SELECT h FROM HrEmpTime h WHERE h.reset = :reset")})
public class HrEmpTime implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_EMP_TIME_seq", sequenceName="HR_EMP_TIME_seq", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_EMP_TIME_seq")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MACHINE_NAME")
    private String machineName;
    @Column(name = "CREATE_BY")
    private BigInteger createBy;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "MODIFY_BY")
    private BigInteger modifyBy;
    @Column(name = "MODIFY_AT")
    @Temporal(TemporalType.DATE)
    private Date modifyAt;
    @Column(name = "BYUSER")
    private Character byuser;
    @Column(name = "RESET")
    private Character reset;
    @ManyToOne
    @JoinColumn(name = "SHIFT_ID")
    private HrShift shiftId;
    @Column(name = "STRT_LAW")
    private Long strtLaw;
    @Column(name = "END_LAW")
    private Long endLaw;
    @Column(name = "PERIOD_LAW")
    private Long periodLaw;
    @JoinColumn(name = "HD_ID" ,referencedColumnName="EMP_NO")
    @ManyToOne
    private HrEmpMangers hdId;

    public Long getEndLaw() {
        return endLaw;
    }

    public void setEndLaw(Long endLaw) {
        this.endLaw = endLaw;
    }

    public Long getPeriodLaw() {
        return periodLaw;
    }

    public void setPeriodLaw(Long periodLaw) {
        this.periodLaw = periodLaw;
    }

    public HrShift getShiftId() {
        return shiftId;
    }

    public void setShiftId(HrShift shiftId) {
        this.shiftId = shiftId;
    }

   

    public Long getStrtLaw() {
        return strtLaw;
    }

    public void setStrtLaw(Long strtLaw) {
        this.strtLaw = strtLaw;
    }

    public HrEmpMangers getHdId() {
        return hdId;
    }

    public void setHdId(HrEmpMangers hdId) {
        this.hdId = hdId;
    }

    

   
    

    public HrEmpTime() {
    }

    public HrEmpTime(Long id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public BigInteger getCreateBy() {
        return createBy;
    }

    public void setCreateBy(BigInteger createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public BigInteger getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(BigInteger modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public Character getByuser() {
        return byuser;
    }

    public void setByuser(Character byuser) {
        this.byuser = byuser;
    }

    public Character getReset() {
        return reset;
    }

    public void setReset(Character reset) {
        this.reset = reset;
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
        if (!(object instanceof HrEmpTime)) {
            return false;
        }
        HrEmpTime other = (HrEmpTime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrEmpTime[id=" + id + "]";
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_TAMYOZ_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrTamyozHd.findAll", query = "SELECT h FROM HrTamyozHd h"),
    @NamedQuery(name = "HrTamyozHd.getEmpDailyTamyozApprove1", query = "SELECT h FROM HrTamyozHd h WHERE h.locationId = :loc and (h.approved is null or h.approved=1) and h.rewardType=7 and h.trnsDate>=:d"),
    @NamedQuery(name = "HrTamyozHd.getEmpDailyTamyozApprove2", query = "SELECT h FROM HrTamyozHd h WHERE h.locationId = :loc and (h.approved=1 or h.approved=2) and h.rewardType=7 and h.trnsDate>=:d"),
    @NamedQuery(name = "HrTamyozHd.getEmpDailyTamyozApprove3", query = "SELECT h FROM HrTamyozHd h WHERE h.locationId = :loc and (h.approved=2 or h.approved=3) and h.rewardType=7 and h.trnsDate>=:d"),
    @NamedQuery(name = "HrTamyozHd.getEmpDailyTamyozEntry", query = "SELECT h FROM HrTamyozHd h WHERE h.locationId = :loc and (h.approved is null or h.approved=0) and h.rewardType=7 and h.trnsDate>=:d"),
    @NamedQuery(name = "HrTamyozHd.findPersist", query = "SELECT h FROM HrTamyozHd h where h.trnsDate=:date and h.locationId=:loc and h.rewardType=7 and h.tamyozEntry=:emp"),
    @NamedQuery(name = "HrTamyozHd.findById", query = "SELECT h FROM HrTamyozHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrTamyozHd.findByName", query = "SELECT h FROM HrTamyozHd h WHERE h.name = :name"),
    @NamedQuery(name = "HrTamyozHd.findByMonth", query = "SELECT h FROM HrTamyozHd h WHERE h.month = :month"),
    @NamedQuery(name = "HrTamyozHd.findByTrnsDate", query = "SELECT h FROM HrTamyozHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrTamyozHd.findByNotes", query = "SELECT h FROM HrTamyozHd h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrTamyozHd.findByUserName", query = "SELECT h FROM HrTamyozHd h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrTamyozHd.findByMachineName", query = "SELECT h FROM HrTamyozHd h WHERE h.machineName = :machineName"),
    @NamedQuery(name = "HrTamyozHd.findByApprovedBy", query = "SELECT h FROM HrTamyozHd h WHERE h.approvedBy = :approvedBy"),
    @NamedQuery(name = "HrTamyozHd.findByTrnsYear", query = "SELECT h FROM HrTamyozHd h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrTamyozHd.findByApproved", query = "SELECT h FROM HrTamyozHd h WHERE h.approved = :approved"),
    @NamedQuery(name = "HrTamyozHd.findBySecApproved", query = "SELECT h FROM HrTamyozHd h WHERE h.secApproved = :secApproved"),
    @NamedQuery(name = "HrTamyozHd.findByTreasuryApproved", query = "SELECT h FROM HrTamyozHd h WHERE h.treasuryApproved = :treasuryApproved"),
    @NamedQuery(name = "HrTamyozHd.findByManagerApproved", query = "SELECT h FROM HrTamyozHd h WHERE h.managerApproved = :managerApproved"),
    @NamedQuery(name = "HrTamyozHd.findByTamyozEntry", query = "SELECT h FROM HrTamyozHd h WHERE h.tamyozEntry = :tamyozEntry"),
    @NamedQuery(name = "HrTamyozHd.findBySecApproveDate", query = "SELECT h FROM HrTamyozHd h WHERE h.secApproveDate = :secApproveDate"),
    @NamedQuery(name = "HrTamyozHd.findByTreasuryApproveDate", query = "SELECT h FROM HrTamyozHd h WHERE h.treasuryApproveDate = :treasuryApproveDate"),
    @NamedQuery(name = "HrTamyozHd.findByManagerApproveDate", query = "SELECT h FROM HrTamyozHd h WHERE h.managerApproveDate = :managerApproveDate")})
public class HrTamyozHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_TAMYOZ_HD_SEQ", sequenceName="HR_TAMYOZ_HD_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_TAMYOZ_HD_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "MONTH")
    private Long month;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MACHINE_NAME")
    private String machineName;
    @Column(name = "APPROVED_BY")
    private String approvedBy;
    @Column(name = "TRNS_YEAR")
    private Long trnsYear;
    @Column(name = "APPROVED")
    private String approved;
    @Column(name = "SEC_APPROVED")
    private Long secApproved;
    @Column(name = "TREASURY_APPROVED")
    private Long treasuryApproved;
    @Column(name = "MANAGER_APPROVED")
    private Long managerApproved;
    @Column(name = "TAMYOZ_ENTRY")
    private Long tamyozEntry;
    @Column(name = "SEC_APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date secApproveDate;
    @Column(name = "TREASURY_APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date treasuryApproveDate;
    @Column(name = "MANAGER_APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date managerApproveDate;
    @Column(name="REWARD_TYPE")
    private Long rewardType;
    @Column(name="LOCATION_ID")
    private Long locationId;
    @Column(name="MANAGE_ID")
    private Long manageId;
    @OneToMany(mappedBy = "hrTamyozHd" ,fetch=FetchType.EAGER)
    private List<HrTamyozDt> hrTamyozDtList;

    public Long getManageId() {
        return manageId;
    }

    public void setManageId(Long manageId) {
        this.manageId = manageId;
    }

    

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }


    
    public Long getRewardType() {
        return rewardType;
    }

    public void setRewardType(Long rewardType) {
        this.rewardType = rewardType;
    }

    public HrTamyozHd() {
    }

    public HrTamyozHd(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
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

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Long getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Long trnsYear) {
        this.trnsYear = trnsYear;
    }

    public List<String> getApproved() {
        List<String> x=new ArrayList<String>();
        if(approved!=null)
        {
        x.add(approved);
        }
        return x;
    }

    public void setApproved(List<String> approve) {
       try{
        this.approved=approve.get(0);
        }catch(Exception e){}
    }

    public Long getManagerApproved() {
        return managerApproved;
    }

    public void setManagerApproved(Long managerApproved) {
        this.managerApproved = managerApproved;
    }

    public Long getSecApproved() {
        return secApproved;
    }

    public void setSecApproved(Long secApproved) {
        this.secApproved = secApproved;
    }

    public Long getTamyozEntry() {
        return tamyozEntry;
    }

    public void setTamyozEntry(Long tamyozEntry) {
        this.tamyozEntry = tamyozEntry;
    }

    public Long getTreasuryApproved() {
        return treasuryApproved;
    }

    public void setTreasuryApproved(Long treasuryApproved) {
        this.treasuryApproved = treasuryApproved;
    }

   
    public Date getSecApproveDate() {
        return secApproveDate;
    }

    public void setSecApproveDate(Date secApproveDate) {
        this.secApproveDate = secApproveDate;
    }

    public Date getTreasuryApproveDate() {
        return treasuryApproveDate;
    }

    public void setTreasuryApproveDate(Date treasuryApproveDate) {
        this.treasuryApproveDate = treasuryApproveDate;
    }

    public Date getManagerApproveDate() {
        return managerApproveDate;
    }

    public void setManagerApproveDate(Date managerApproveDate) {
        this.managerApproveDate = managerApproveDate;
    }

    public List<HrTamyozDt> getHrTamyozDtList() {
        return hrTamyozDtList;
    }

    public void setHrTamyozDtList(List<HrTamyozDt> hrTamyozDtList) {
        this.hrTamyozDtList = hrTamyozDtList;
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
        if (!(object instanceof HrTamyozHd)) {
            return false;
        }
        HrTamyozHd other = (HrTamyozHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrTamyozHd[id=" + id + "]";
    }

}

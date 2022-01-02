/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_EMP_LOC_INVEST", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpLocInvest.findAll", query = "SELECT h FROM HrEmpLocInvest h"),
    @NamedQuery(name = "HrEmpLocInvest.findMaxId", query = "SELECT max(h.id) FROM HrEmpLocInvest h"),
    @NamedQuery(name = "HrEmpLocInvest.findByEmpId", query = "SELECT h FROM HrEmpLocInvest h WHERE h.empId = :empId"),
    @NamedQuery(name = "HrEmpLocInvest.chkInvest", query = "SELECT count(h) FROM HrEmpLocInvest h WHERE h.empId = :emp_id"),
    @NamedQuery(name = "HrEmpLocInvest.findByHomeNo", query = "SELECT h FROM HrEmpLocInvest h WHERE h.homeNo = :homeNo"),
    @NamedQuery(name = "HrEmpLocInvest.findByStreet", query = "SELECT h FROM HrEmpLocInvest h WHERE h.street = :street"),
    @NamedQuery(name = "HrEmpLocInvest.findByRegionId", query = "SELECT h FROM HrEmpLocInvest h WHERE h.regionId = :regionId"),
    @NamedQuery(name = "HrEmpLocInvest.findByOtherRegion", query = "SELECT h FROM HrEmpLocInvest h WHERE h.otherRegion = :otherRegion"),
    @NamedQuery(name = "HrEmpLocInvest.findBySignInPlace", query = "SELECT h FROM HrEmpLocInvest h WHERE h.signInPlace = :signInPlace"),
    @NamedQuery(name = "HrEmpLocInvest.findByGovId", query = "SELECT h FROM HrEmpLocInvest h WHERE h.govId = :govId"),
    @NamedQuery(name = "HrEmpLocInvest.findByArrivalAvg", query = "SELECT h FROM HrEmpLocInvest h WHERE h.arrivalAvg = :arrivalAvg"),
    @NamedQuery(name = "HrEmpLocInvest.findByLeaveAvg", query = "SELECT h FROM HrEmpLocInvest h WHERE h.leaveAvg = :leaveAvg"),
    @NamedQuery(name = "HrEmpLocInvest.findByTrnsFlag", query = "SELECT h FROM HrEmpLocInvest h WHERE h.trnsFlag = :trnsFlag"),
    @NamedQuery(name = "HrEmpLocInvest.findByReason", query = "SELECT h FROM HrEmpLocInvest h WHERE h.reason = :reason"),
    @NamedQuery(name = "HrEmpLocInvest.findByLocId1", query = "SELECT h FROM HrEmpLocInvest h WHERE h.locId1 = :locId1"),
    @NamedQuery(name = "HrEmpLocInvest.findByLocId2", query = "SELECT h FROM HrEmpLocInvest h WHERE h.locId2 = :locId2"),
    @NamedQuery(name = "HrEmpLocInvest.findByLocId3", query = "SELECT h FROM HrEmpLocInvest h WHERE h.locId3 = :locId3")})
public class HrEmpLocInvest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "HOME_NO")
    private String homeNo;
    @Column(name = "STREET")
    private String street;
    @Column(name = "REGION_ID")
    private Long regionId;
    @Column(name = "OTHER_REGION")
    private String otherRegion;
    @Column(name = "SIGN_IN_PLACE")
    private String signInPlace;
    @Column(name = "GOV_ID")
    private Long govId;
    @Column(name = "ARRIVAL_AVG")
    private String arrivalAvg;
    @Column(name = "LEAVE_AVG")
    private String leaveAvg;
    @Column(name = "TRNS_FLAG")
    private Character trnsFlag;
    @Column(name = "REASON")
    private String reason;
    @Column(name = "LOC_ID1")
    private Long locId1;
    @Column(name = "LOC_ID2")
    private Long locId2;
    @Column(name = "LOC_ID3")
    private Long locId3;
    @Column(name = "PHONE_NO")
    private String phoneNo;

    public HrEmpLocInvest() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getGovId() {
        return govId;
    }

    public void setGovId(Long govId) {
        this.govId = govId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocId1() {
        return locId1;
    }

    public void setLocId1(Long locId1) {
        this.locId1 = locId1;
    }

    public Long getLocId2() {
        return locId2;
    }

    public void setLocId2(Long locId2) {
        this.locId2 = locId2;
    }

    public Long getLocId3() {
        return locId3;
    }

    public void setLocId3(Long locId3) {
        this.locId3 = locId3;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

   
    public String getHomeNo() {
        return homeNo;
    }

    public void setHomeNo(String homeNo) {
        this.homeNo = homeNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public String getOtherRegion() {
        return otherRegion;
    }

    public void setOtherRegion(String otherRegion) {
        this.otherRegion = otherRegion;
    }

    public String getSignInPlace() {
        return signInPlace;
    }

    public void setSignInPlace(String signInPlace) {
        this.signInPlace = signInPlace;
    }

    public String getArrivalAvg() {
        return arrivalAvg;
    }

    public void setArrivalAvg(String arrivalAvg) {
        this.arrivalAvg = arrivalAvg;
    }

    public String getLeaveAvg() {
        return leaveAvg;
    }

    public void setLeaveAvg(String leaveAvg) {
        this.leaveAvg = leaveAvg;
    }

    public Character getTrnsFlag() {
        return trnsFlag;
    }

    public void setTrnsFlag(Character trnsFlag) {
        this.trnsFlag = trnsFlag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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
        if (!(object instanceof HrEmpLocInvest)) {
            return false;
        }
        HrEmpLocInvest other = (HrEmpLocInvest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrEmpLocInvest[id=" + id + "]";
    }

}

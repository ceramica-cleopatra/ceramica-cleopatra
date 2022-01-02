/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_EMP_HOLIDAYS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpHolidays.findAll", query = "SELECT h FROM HrEmpHolidays h"),
    @NamedQuery(name = "HrEmpHolidays.findNormalHolidays", query = "SELECT h FROM HrEmpHolidays h "
    + "where h.empNo=:emp and h.holType=1 and h.trnsDate"
            + "=(select max(o.trnsDate) from HrEmpHolidays o "
            + "where o.empNo=h.empNo and o.holType=1 "
            + "and o.days is not null "
            + "and o.days<>0)"),
    @NamedQuery(name = "HrEmpHolidays.findById", query = "SELECT h FROM HrEmpHolidays h WHERE h.id = :id"),
    @NamedQuery(name = "HrEmpHolidays.findByEmpNo", query = "SELECT h FROM HrEmpHolidays h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrEmpHolidays.findByHolType", query = "SELECT h FROM HrEmpHolidays h WHERE h.holType = :holType"),
    @NamedQuery(name = "HrEmpHolidays.findByDays", query = "SELECT h FROM HrEmpHolidays h WHERE h.days = :days"),
    @NamedQuery(name = "HrEmpHolidays.findByDayOff", query = "SELECT h FROM HrEmpHolidays h WHERE h.dayOff = :dayOff"),
    @NamedQuery(name = "HrEmpHolidays.findByTrnsDate", query = "SELECT h FROM HrEmpHolidays h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrEmpHolidays.findByUserName", query = "SELECT h FROM HrEmpHolidays h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrEmpHolidays.findByMachineName", query = "SELECT h FROM HrEmpHolidays h WHERE h.machineName = :machineName"),
    @NamedQuery(name = "HrEmpHolidays.findByNotes", query = "SELECT h FROM HrEmpHolidays h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrEmpHolidays.findByCreateBy", query = "SELECT h FROM HrEmpHolidays h WHERE h.createBy = :createBy"),
    @NamedQuery(name = "HrEmpHolidays.findByCreateAt", query = "SELECT h FROM HrEmpHolidays h WHERE h.createAt = :createAt"),
    @NamedQuery(name = "HrEmpHolidays.findByModifyBy", query = "SELECT h FROM HrEmpHolidays h WHERE h.modifyBy = :modifyBy"),
    @NamedQuery(name = "HrEmpHolidays.findByModifyAt", query = "SELECT h FROM HrEmpHolidays h WHERE h.modifyAt = :modifyAt")})
public class HrEmpHolidays implements Serializable {
    
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "HOL_TYPE")
    private Long holType;
    @Column(name = "DAYS")
    private Long days;
    @Column(name = "DAY_OFF")
    private Long dayOff;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MACHINE_NAME")
    private String machineName;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "CREATE_BY")
    private Long createBy;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "MODIFY_BY")
    private Long modifyBy;
    @Column(name = "MODIFY_AT")
    @Temporal(TemporalType.DATE)
    private Date modifyAt;

    public HrEmpHolidays() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getHolType() {
        return holType;
    }

    public void setHolType(Long holType) {
        this.holType = holType;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }


  
    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getDayOff() {
        return dayOff;
    }

    public void setDayOff(Long dayOff) {
        this.dayOff = dayOff;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

  

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

   

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

   

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "HR_AUTHORIZATION", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrAuthorization.calcAuthorizeSum", query = "SELECT h FROM HrAuthorization h where h.trnsDate between :from_date and :to_date and h.empId=:emp"),
    @NamedQuery(name = "HrAuthorization.findById", query = "SELECT h FROM HrAuthorization h WHERE h.id = :id"),
    @NamedQuery(name = "HrAuthorization.findByTrnsDate", query = "SELECT h FROM HrAuthorization h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrAuthorization.findByAuthIn", query = "SELECT h FROM HrAuthorization h WHERE h.authIn = :authIn"),
    @NamedQuery(name = "HrAuthorization.findByAuthOut", query = "SELECT h FROM HrAuthorization h WHERE h.authOut = :authOut"),
    @NamedQuery(name = "HrAuthorization.findByPunishMinuts", query = "SELECT h FROM HrAuthorization h WHERE h.punishMinuts = :punishMinuts"),
    @NamedQuery(name = "HrAuthorization.findByPunishSalary", query = "SELECT h FROM HrAuthorization h WHERE h.punishSalary = :punishSalary"),
    @NamedQuery(name = "HrAuthorization.findByNotes", query = "SELECT h FROM HrAuthorization h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrAuthorization.findByPluseMinuts", query = "SELECT h FROM HrAuthorization h WHERE h.pluseMinuts = :pluseMinuts"),
    @NamedQuery(name = "HrAuthorization.findByPlusDays", query = "SELECT h FROM HrAuthorization h WHERE h.plusDays = :plusDays"),
    @NamedQuery(name = "HrAuthorization.findByAuthorizeMinutes", query = "SELECT h FROM HrAuthorization h WHERE h.authorizeMinutes = :authorizeMinutes")})
public class HrAuthorization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "AUTH_IN")
    @Temporal(TemporalType.TIME)
    private Date authIn;
    @Column(name = "AUTH_OUT")
    @Temporal(TemporalType.TIME)
    private Date authOut;
    @Column(name = "PUNISH_MINUTS")
    private Long punishMinuts;
    @Column(name = "PUNISH_SALARY")
    private Long punishSalary;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "PLUSE_MINUTS")
    private Long pluseMinuts;
    @Column(name = "PLUS_DAYS")
    private Long plusDays;
    @Column(name = "AUTHORIZE_MINUTES")
    private Long authorizeMinutes;
    @Column(name = "EMP_ID")
    private Long empId;
    public HrAuthorization() {
    }

    public HrAuthorization(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorizeMinutes() {
        return authorizeMinutes;
    }

    public void setAuthorizeMinutes(Long authorizeMinutes) {
        this.authorizeMinutes = authorizeMinutes;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getPlusDays() {
        return plusDays;
    }

    public void setPlusDays(Long plusDays) {
        this.plusDays = plusDays;
    }

    public Long getPluseMinuts() {
        return pluseMinuts;
    }

    public void setPluseMinuts(Long pluseMinuts) {
        this.pluseMinuts = pluseMinuts;
    }

    public Long getPunishMinuts() {
        return punishMinuts;
    }

    public void setPunishMinuts(Long punishMinuts) {
        this.punishMinuts = punishMinuts;
    }

    public Long getPunishSalary() {
        return punishSalary;
    }

    public void setPunishSalary(Long punishSalary) {
        this.punishSalary = punishSalary;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Date getAuthIn() {
        return authIn;
    }

    public void setAuthIn(Date authIn) {
        this.authIn = authIn;
    }

    public Date getAuthOut() {
        return authOut;
    }

    public void setAuthOut(Date authOut) {
        this.authOut = authOut;
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
        if (!(object instanceof HrAuthorization)) {
            return false;
        }
        HrAuthorization other = (HrAuthorization) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrAuthorization[id=" + id + "]";
    }

}

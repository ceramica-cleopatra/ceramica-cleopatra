/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_MANUAL_EFFECTION_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManualEffectionHd.findAll", query = "SELECT h FROM HrManualEffectionHd h where h.empId=:emp and h.trnsMonth=:m and h.trnsYear=:y"),
    @NamedQuery(name = "HrManualEffectionHd.chkCardExist", query = "SELECT count(h.id) FROM HrManualEffectionHd h where h.empId=:emp and h.trnsMonth=:m and h.trnsYear=:y"),
    @NamedQuery(name = "HrManualEffectionHd.findById", query = "SELECT h FROM HrManualEffectionHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrManualEffectionHd.findByTrnsMonth", query = "SELECT h FROM HrManualEffectionHd h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrManualEffectionHd.findByTrnsYear", query = "SELECT h FROM HrManualEffectionHd h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrManualEffectionHd.findByNotes", query = "SELECT h FROM HrManualEffectionHd h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrManualEffectionHd.findByUserName", query = "SELECT h FROM HrManualEffectionHd h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrManualEffectionHd.findByMachinName", query = "SELECT h FROM HrManualEffectionHd h WHERE h.machinName = :machinName")})
public class HrManualEffectionHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_ID")
    private Long empId;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }
    @Column(name = "TRNS_MONTH")
    private Long trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Short trnsYear;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MACHIN_NAME")
    private String machinName;

    public HrManualEffectionHd() {
    }

    public HrManualEffectionHd(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(Long trnsMonth) {
        this.trnsMonth = trnsMonth;
    }

    public Short getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Short trnsYear) {
        this.trnsYear = trnsYear;
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

    public String getMachinName() {
        return machinName;
    }

    public void setMachinName(String machinName) {
        this.machinName = machinName;
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
        if (!(object instanceof HrManualEffectionHd)) {
            return false;
        }
        HrManualEffectionHd other = (HrManualEffectionHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrManualEffectionHd[id=" + id + "]";
    }

}

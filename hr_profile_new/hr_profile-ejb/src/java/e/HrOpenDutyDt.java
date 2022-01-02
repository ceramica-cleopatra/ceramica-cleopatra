/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Administrator
 */
@Entity
@Table(name = "HR_OPEN_DUTY_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrOpenDutyDt.findLastEmpTransaction", query = "SELECT h FROM HrOpenDutyDt h WHERE h.trnsTime=(select max(o.trnsTime) from HrOpenDutyDt o where o.hrOpenDutyHd.empId.empNo=:emp_no and o.trnsTime between :from_date and :to_date and o.locId.id=:loc_id)"),
    @NamedQuery(name = "HrOpenDutyDt.findById", query = "SELECT h FROM HrOpenDutyDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrOpenDutyDt.findToday'sTransactions", query = "SELECT h FROM HrOpenDutyDt h WHERE h.trnsTime between :from_date and :to_date"),
    @NamedQuery(name = "HrOpenDutyDt.findBySecId", query = "SELECT h FROM HrOpenDutyDt h WHERE h.secId = :secId")})
public class HrOpenDutyDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_OPEN_DUTY_DT_SEQ", sequenceName="HR_OPEN_DUTY_DT_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_OPEN_DUTY_DT_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "LOC_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private HrLocation locId;
    @Column(name = "TRNS_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsTime;
    @Column(name = "TRNS_TYPE")
    private String trnsType;
    @Column(name = "SEC_ID")
    private Long secId;
    @Column(name = "FLAG")
    private Character flag;
    @Column(name="EXTERNAL")
    private Character external;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private HrOpenDutyHd hrOpenDutyHd;
    public HrOpenDutyDt() {
    }

    public HrOpenDutyDt(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public HrLocation getLocId() {
        return locId;
    }

    public void setLocId(HrLocation locId) {
        this.locId = locId;
    }

    

    public Date getTrnsTime() {
        return trnsTime;
    }

    public void setTrnsTime(Date trnsTime) {
        this.trnsTime = trnsTime;
    }

    public String getTrnsType() {
        return trnsType;
    }

    public void setTrnsType(String trnsType) {
        this.trnsType = trnsType;
    }

   

    
    

    public Long getSecId() {
        return secId;
    }

    public void setSecId(Long secId) {
        this.secId = secId;
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
        if (!(object instanceof HrOpenDutyDt)) {
            return false;
        }
        HrOpenDutyDt other = (HrOpenDutyDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public HrOpenDutyHd getHrOpenDutyHd() {
        return hrOpenDutyHd;
    }

    public void setHrOpenDutyHd(HrOpenDutyHd hrOpenDutyHd) {
        this.hrOpenDutyHd = hrOpenDutyHd;
    }

    @Override
    public String toString() {
        return "e.HrOpenDutyDt[id=" + id + "]";
    }

    public Character getFlag() {
        return flag;
    }

    public void setFlag(Character flag) {
        this.flag = flag;
    }

    public Character getExternal() {
        return external;
    }

    public void setExternal(Character external) {
        this.external = external;
    }

}

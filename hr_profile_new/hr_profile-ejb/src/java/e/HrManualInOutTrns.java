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
@Table(name = "HR_MANUAL_IN_OUT_TRNS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManualInOutTrns.findAll", query = "SELECT h FROM HrManualInOutTrns h where h.trnsDate between :frm_date and :to_date and h.locId in (SELECT x.hrLocationIpMappingPK.locId FROM HrLocationIpMapping x WHERE x.hrLocationIpMappingPK.ip = :ip) order by h.trnsDate desc"),
    @NamedQuery(name = "HrManualInOutTrns.findId", query = "SELECT max(h.id) FROM HrManualInOutTrns h"),
    @NamedQuery(name = "HrManualInOutTrns.findIn", query = "SELECT h FROM HrManualInOutTrns h where h.trnsDate between :frm_date and :to_date and h.userId=:user and h.trnsType='A1' order by h.trnsDate desc"),
    @NamedQuery(name = "HrManualInOutTrns.chkLastInOut", query = "SELECT h FROM HrManualInOutTrns h where h.trnsDate=(select max(x.trnsDate) from HrManualInOutTrns x where x.hrEmpHd.id=h.hrEmpHd.id) and h.hrEmpHd.id=:emp"),
    @NamedQuery(name = "HrManualInOutTrns.findOut", query = "SELECT h FROM HrManualInOutTrns h where h.trnsDate between :frm_date and :to_date and h.userId=:user and h.trnsType='A2' order by h.trnsDate desc"),
    @NamedQuery(name = "HrManualInOutTrns.findByTrnsType", query = "SELECT h FROM HrManualInOutTrns h WHERE h.trnsType = :trnsType"),
    @NamedQuery(name = "HrManualInOutTrns.findByTrnsDate", query = "SELECT h FROM HrManualInOutTrns h WHERE h.trnsDate = :trnsDate")})
public class HrManualInOutTrns implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_MANUAL_IN_OUT_TRNS_SEQ", sequenceName="HR_MANUAL_IN_OUT_TRNS_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_MANUAL_IN_OUT_TRNS_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name= "USER_ID")
    private Long userId;
    @ManyToOne
    @JoinColumn(name= "EMP_ID")
    private HrEmpHd hrEmpHd;
    @Column(name = "TRNS_TYPE")
    private String trnsType;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "LOC_ID")
    private Long locId;
    public HrEmpHd getHrEmpHd() {
        return hrEmpHd;
    }

    public void setHrEmpHd(HrEmpHd hrEmpHd) {
        this.hrEmpHd = hrEmpHd;
    }

   

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public HrManualInOutTrns() {
    }

    public HrManualInOutTrns(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrnsType() {
        return trnsType;
    }

    public void setTrnsType(String trnsType) {
        this.trnsType = trnsType;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
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
        if (!(object instanceof HrManualInOutTrns)) {
            return false;
        }
        HrManualInOutTrns other = (HrManualInOutTrns) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrManualInOutTrns[id=" + id + "]";
    }

}

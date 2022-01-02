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
 * @author data
 */
@Entity
@Table(name = "HR_DYN_ALERT_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDynAlertHd.findAll", query = "SELECT h FROM HrDynAlertHd h"),
    @NamedQuery(name = "HrDynAlertHd.findById", query = "SELECT h FROM HrDynAlertHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrDynAlertHd.findByTrnsDate", query = "SELECT h FROM HrDynAlertHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrDynAlertHd.findByTemplateId", query = "SELECT h FROM HrDynAlertHd h WHERE h.templateId = :templateId"),
    @NamedQuery(name = "HrDynAlertHd.findByEmpNo", query = "SELECT h FROM HrDynAlertHd h WHERE h.empNo = :empNo")})
public class HrDynAlertHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_DYN_ALERT_HD_SEQ", sequenceName="HR_DYN_ALERT_HD_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_DYN_ALERT_HD_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @JoinColumn(name = "TEMPLATE_ID",referencedColumnName="ID")
    @ManyToOne
    private HrDynAlertTemplateHd templateId;
    @Column(name = "EMP_NO")
    private Long empNo;
    @OneToMany(mappedBy = "hrDynAlertHd",cascade=CascadeType.PERSIST)
    private List<HrDynAlertDt> hrDynAlertDtList;

    public HrDynAlertHd() {
    }

    public HrDynAlertHd(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public HrDynAlertTemplateHd getTemplateId() {
        return templateId;
    }

    public void setTemplateId(HrDynAlertTemplateHd templateId) {
        this.templateId = templateId;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public List<HrDynAlertDt> getHrDynAlertDtList() {
        return hrDynAlertDtList;
    }

    public void setHrDynAlertDtList(List<HrDynAlertDt> hrDynAlertDtList) {
        this.hrDynAlertDtList = hrDynAlertDtList;
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
        if (!(object instanceof HrDynAlertHd)) {
            return false;
        }
        HrDynAlertHd other = (HrDynAlertHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDynAlertHd[id=" + id + "]";
    }

}

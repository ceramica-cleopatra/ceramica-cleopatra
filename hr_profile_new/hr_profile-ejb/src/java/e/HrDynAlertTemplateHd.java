/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
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
@Table(name = "HR_DYN_ALERT_TEMPLATE_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDynAlertTemplateHd.findAll", query = "SELECT h FROM HrDynAlertTemplateHd h order by h.trnsDate desc"),
    @NamedQuery(name = "HrDynAlertTemplateHd.findById", query = "SELECT h FROM HrDynAlertTemplateHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrDynAlertTemplateHd.findByTrnsDate", query = "SELECT h FROM HrDynAlertTemplateHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrDynAlertTemplateHd.findByTitle", query = "SELECT h FROM HrDynAlertTemplateHd h WHERE h.title = :title"),
    @NamedQuery(name = "HrDynAlertTemplateHd.findByActive", query = "SELECT h FROM HrDynAlertTemplateHd h WHERE h.active = :active"),
    @NamedQuery(name = "HrDynAlertTemplateHd.findNotApplied", query = "SELECT h FROM HrDynAlertTemplateHd h where (SELECT count(j) from HrDynAlertHd j"
    + " where j.templateId.id=h.id and j.empNo=:emp_no)=0 and h.active = 'Y'")})
public class HrDynAlertTemplateHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_DYN_ALERT_TEMPLATE_HD_SEQ", sequenceName="HR_DYN_ALERT_TEMPLATE_HD_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_DYN_ALERT_TEMPLATE_HD_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "ACTIVE")
    private Character active;
    @Column(name = "WIDTH")
    private Long width;
    @Column(name = "HEIGHT")
    private Long height;
    @ManyToOne
    @JoinColumn(name = "DEPT_ID",referencedColumnName="ID")
    private HrManagement deptId;
    @ManyToOne
    @JoinColumn(name = "JOB_GRP_ID",referencedColumnName="ID")
    private HrJobGrp jobGrpId;
    @ManyToOne
    @JoinColumn(name = "JOB_ID",referencedColumnName="ID")
    private HrJobs jobId;
    @ManyToOne
    @JoinColumn(name = "LOC_ID",referencedColumnName="ID")
    private HrLocation locId;
    @OneToMany(mappedBy = "hrDynAlertTemplateHd",cascade=CascadeType.ALL)
    private List<HrDynAlertTemplateDt> hrDynAlertTemplateDtList;

    public HrDynAlertTemplateHd() {
    }

    public HrDynAlertTemplateHd(BigDecimal id) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Character getActive() {
        return active;
    }

    public void setActive(Character active) {
        this.active = active;
    }

    public HrManagement getDeptId() {
        return deptId;
    }

    public void setDeptId(HrManagement deptId) {
        this.deptId = deptId;
    }

    public HrJobGrp getJobGrpId() {
        return jobGrpId;
    }

    public void setJobGrpId(HrJobGrp jobGrpId) {
        this.jobGrpId = jobGrpId;
    }

    public HrJobs getJobId() {
        return jobId;
    }

    public void setJobId(HrJobs jobId) {
        this.jobId = jobId;
    }

    public HrLocation getLocId() {
        return locId;
    }

    public void setLocId(HrLocation locId) {
        this.locId = locId;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    

    
    public List<HrDynAlertTemplateDt> getHrDynAlertTemplateDtList() {
        Collections.sort( hrDynAlertTemplateDtList,new DynamicAlertTemplateComparator());
        return hrDynAlertTemplateDtList;
    }

    public void setHrDynAlertTemplateDtList(List<HrDynAlertTemplateDt> hrDynAlertTemplateDtList) {
        this.hrDynAlertTemplateDtList = hrDynAlertTemplateDtList;
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
        if (!(object instanceof HrDynAlertTemplateHd)) {
            return false;
        }
        HrDynAlertTemplateHd other = (HrDynAlertTemplateHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDynAlertTemplateHd[id=" + id + "]";
    }

}

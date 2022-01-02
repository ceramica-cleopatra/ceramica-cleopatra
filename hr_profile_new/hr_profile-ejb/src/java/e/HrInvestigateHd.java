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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_INVESTIGATE_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrInvestigateHd.findAll", query = "SELECT h FROM HrInvestigateHd h "
    + "where :current_date between h.fromDate and h.toDate "
    + "and (h.deptId=:dept or h.deptId is null) "
    + "and (h.jobId=:job or h.jobId is null) "
    + "and (h.grpId=:grp or h.grpId is null) "
    + "and (h.locId=:loc or h.locId is null)"),
     @NamedQuery(name = "HrInvestigateHd.chkInvestigation", query = "SELECT count(h) FROM HrInvestigateHd h "
    + "where :current_date between h.fromDate and h.toDate "
    + "and (h.deptId=:dept or h.deptId is null) "
    + "and (h.jobId=:job or h.jobId is null) "
    + "and (h.grpId=:grp or h.grpId is null) "
    + "and (h.locId=:loc or h.locId is null)"),
    @NamedQuery(name = "HrInvestigateHd.findById", query = "SELECT h FROM HrInvestigateHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrInvestigateHd.findByName", query = "SELECT h FROM HrInvestigateHd h WHERE h.name = :name"),
    @NamedQuery(name = "HrInvestigateHd.findByTarget", query = "SELECT h FROM HrInvestigateHd h WHERE h.target = :target"),
    @NamedQuery(name = "HrInvestigateHd.findByFromDate", query = "SELECT h FROM HrInvestigateHd h WHERE h.fromDate = :fromDate"),
    @NamedQuery(name = "HrInvestigateHd.findByToDate", query = "SELECT h FROM HrInvestigateHd h WHERE h.toDate = :toDate"),
    @NamedQuery(name = "HrInvestigateHd.findByDeptId", query = "SELECT h FROM HrInvestigateHd h WHERE h.deptId = :deptId"),
    @NamedQuery(name = "HrInvestigateHd.findByJobId", query = "SELECT h FROM HrInvestigateHd h WHERE h.jobId = :jobId"),
    @NamedQuery(name = "HrInvestigateHd.findByGrpId", query = "SELECT h FROM HrInvestigateHd h WHERE h.grpId = :grpId"),
    @NamedQuery(name = "HrInvestigateHd.findByLocId", query = "SELECT h FROM HrInvestigateHd h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrInvestigateHd.findByType", query = "SELECT h FROM HrInvestigateHd h WHERE h.type = :type"),
    @NamedQuery(name = "HrInvestigateHd.findByQuestion", query = "SELECT h FROM HrInvestigateHd h WHERE h.question = :question")})
public class HrInvestigateHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TARGET")
    private String target;
    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "JOB_ID")
    private Long jobId;
    @Column(name = "GRP_ID")
    private Long grpId;
    @Column(name = "LOC_ID")
    private Long locId;
    @Column(name = "TYPE")
    private Long type;
    @Column(name = "QUESTION")
    private String question;
    @OneToMany(mappedBy = "hrInvestigateHd")
    private List<HrInvestigateDt> hrInvestigateDtList;

    public HrInvestigateHd() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

   

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<HrInvestigateDt> getHrInvestigateDtList() {
        return hrInvestigateDtList;
    }

    public void setHrInvestigateDtList(List<HrInvestigateDt> hrInvestigateDtList) {
        this.hrInvestigateDtList = hrInvestigateDtList;
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
        if (!(object instanceof HrInvestigateHd)) {
            return false;
        }
        HrInvestigateHd other = (HrInvestigateHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrInvestigateHd[id=" + id + "]";
    }

}

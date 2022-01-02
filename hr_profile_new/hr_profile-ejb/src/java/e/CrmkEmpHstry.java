/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "CRMK_EMP_HSTRY", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkEmpHstry.findAll", query = "SELECT c FROM CrmkEmpHstry c where c.jobId=6 and c.startDate=(select max(x.startDate) FROM CrmkEmpHstry x where x.hdId.id=c.hdId.id) and c.hdId.hrId is not null and c.hdId.hrId.empStatus='R'"),
    @NamedQuery(name = "CrmkEmpHstry.findById", query = "SELECT c FROM CrmkEmpHstry c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkEmpHstry.findByStartDate", query = "SELECT c FROM CrmkEmpHstry c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "CrmkEmpHstry.findByJobDesc", query = "SELECT c FROM CrmkEmpHstry c WHERE c.jobDesc = :jobDesc"),
    @NamedQuery(name = "CrmkEmpHstry.findByHdId", query = "SELECT c FROM CrmkEmpHstry c WHERE c.jobId=6 and c.startDate=(select max(x.startDate) FROM CrmkEmpHstry x where x.hdId.id=c.hdId.id) and c.hdId.id=:emp_id"),
    @NamedQuery(name = "CrmkEmpHstry.findByNotes", query = "SELECT c FROM CrmkEmpHstry c WHERE c.notes = :notes")})
public class CrmkEmpHstry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HD_ID",referencedColumnName="ID")
    private CrmkEmployee hdId;
    @ManyToOne
    @JoinColumn(name = "BRN_ID",referencedColumnName="ID")
    private CrmkBranch brnId;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "JOB_DESC")
    private String jobDesc;
    @Column(name = "JOB_ID")
    private String jobId;
    @Column(name = "NOTES")
    private String notes;

    public CrmkEmpHstry() {
    }

    public CrmkBranch getBrnId() {
        return brnId;
    }

    public void setBrnId(CrmkBranch brnId) {
        this.brnId = brnId;
    }

    public CrmkEmployee getHdId() {
        return hdId;
    }

    public void setHdId(CrmkEmployee hdId) {
        this.hdId = hdId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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
        if (!(object instanceof CrmkEmpHstry)) {
            return false;
        }
        CrmkEmpHstry other = (CrmkEmpHstry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkEmpHstry[id=" + id + "]";
    }

}

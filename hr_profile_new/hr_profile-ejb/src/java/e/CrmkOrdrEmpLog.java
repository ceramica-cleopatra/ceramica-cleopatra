/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CRMK_ORDR_EMP_LOG", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkOrdrEmpLog.findAll", query = "SELECT c FROM CrmkOrdrEmpLog c"),
    @NamedQuery(name = "CrmkOrdrEmpLog.findById", query = "SELECT c FROM CrmkOrdrEmpLog c WHERE c.id = :id")
})
public class CrmkOrdrEmpLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(schema="CRMK",name="CRMK_ORDR_EMP_LOG_SEQ", sequenceName="CRMK_ORDR_EMP_LOG_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRMK_ORDR_EMP_LOG_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "CHANGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeDate;
    @Column(name = "ORDR_ID")
    private Long ordrId;

    public CrmkOrdrEmpLog() {
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(Long ordrId) {
        this.ordrId = ordrId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        if (!(object instanceof CrmkOrdrEmpLog)) {
            return false;
        }
        CrmkOrdrEmpLog other = (CrmkOrdrEmpLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkOrdrEmpLog[id=" + id + "]";
    }

}

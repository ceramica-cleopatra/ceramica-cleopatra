/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_EGADA_SETUP", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEgadaSetup.findAll", query = "SELECT h FROM HrEgadaSetup h"),
    @NamedQuery(name = "HrEgadaSetup.findById", query = "SELECT h FROM HrEgadaSetup h WHERE h.id = :id"),
    @NamedQuery(name = "HrEgadaSetup.findByJobGrpId", query = "SELECT h FROM HrEgadaSetup h WHERE h.jobGrpId = :jobGrpId"),
    @NamedQuery(name = "HrEgadaSetup.findByValue", query = "SELECT h FROM HrEgadaSetup h WHERE h.value = :value")})
public class HrEgadaSetup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "JOB_GRP_ID")
    private Long jobGrpId;
    @Column(name = "VALUE")
    private Double value;

    public HrEgadaSetup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobGrpId() {
        return jobGrpId;
    }

    public void setJobGrpId(Long jobGrpId) {
        this.jobGrpId = jobGrpId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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
        if (!(object instanceof HrEgadaSetup)) {
            return false;
        }
        HrEgadaSetup other = (HrEgadaSetup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrEgadaSetup[id=" + id + "]";
    }

}

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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_CAR_REQUEST_MEMBERS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCarRequestMembers.findAll", query = "SELECT h FROM HrCarRequestMembers h"),
    @NamedQuery(name = "HrCarRequestMembers.findById", query = "SELECT h FROM HrCarRequestMembers h WHERE h.id = :id"),
    @NamedQuery(name = "HrCarRequestMembers.findByEmpNo", query = "SELECT h FROM HrCarRequestMembers h WHERE h.empNo = :empNo")})
public class HrCarRequestMembers implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_CAR_REQUEST_MEMBERS_SEQ", sequenceName="HR_CAR_REQUEST_MEMBERS_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_CAR_REQUEST_MEMBERS_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "EMP_NO")
    private BigInteger empNo;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCarRequestHd hrCarRequestHd;

    public HrCarRequestMembers() {
    }

    public HrCarRequestMembers(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getEmpNo() {
        return empNo;
    }

    public void setEmpNo(BigInteger empNo) {
        this.empNo = empNo;
    }

    public HrCarRequestHd getHrCarRequestHd() {
        return hrCarRequestHd;
    }

    public void setHrCarRequestHd(HrCarRequestHd hrCarRequestHd) {
        this.hrCarRequestHd = hrCarRequestHd;
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
        if (!(object instanceof HrCarRequestMembers)) {
            return false;
        }
        HrCarRequestMembers other = (HrCarRequestMembers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCarRequestMembers[id=" + id + "]";
    }

}

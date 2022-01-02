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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_PROFILE_ACCESS_LOG", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrProfileAccessLog.findAll", query = "SELECT h FROM HrProfileAccessLog h"),
    @NamedQuery(name = "HrProfileAccessLog.findById", query = "SELECT h FROM HrProfileAccessLog h WHERE h.id = :id"),
    @NamedQuery(name = "HrProfileAccessLog.findByUserName", query = "SELECT h FROM HrProfileAccessLog h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrProfileAccessLog.findByEmpNo", query = "SELECT h FROM HrProfileAccessLog h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrProfileAccessLog.findByTrnsDate", query = "SELECT h FROM HrProfileAccessLog h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrProfileAccessLog.findByPageName", query = "SELECT h FROM HrProfileAccessLog h WHERE h.pageName = :pageName")})
public class HrProfileAccessLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_PROFILE_ACCESS_LOG_SEQ", sequenceName="HR_PROFILE_ACCESS_LOG_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_PROFILE_ACCESS_LOG_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "PAGE_NAME")
    private String pageName;

    public HrProfileAccessLog() {
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
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
        if (!(object instanceof HrProfileAccessLog)) {
            return false;
        }
        HrProfileAccessLog other = (HrProfileAccessLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrProfileAccessLog[id=" + id + "]";
    }

}

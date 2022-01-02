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
@Table(name = "HR_DUTY_TRNS_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDutyTrnsHd.findAll", query = "SELECT h FROM HrDutyTrnsHd h where h.empNo=:empNo and h.trnsDate>=:trnsDate order by h.trnsDate asc"),
    @NamedQuery(name = "HrDutyTrnsHd.findById", query = "SELECT h FROM HrDutyTrnsHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrDutyTrnsHd.findByEmpNo", query = "SELECT h FROM HrDutyTrnsHd h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrDutyTrnsHd.findByTrnsDate", query = "SELECT h FROM HrDutyTrnsHd h WHERE h.trnsDate = :trnsDate")})
public class HrDutyTrnsHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "EMP_NO")
    private HrEmpInfo empNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @OneToMany(mappedBy = "hrDutyTrnsHd")
    private List<HrDutyTrnsDt> hrDutyTrnsDtList;

    public HrDutyTrnsHd() {
    }

    public HrDutyTrnsHd(Long id) {
        this.id = id;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
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
        if (!(object instanceof HrDutyTrnsHd)) {
            return false;
        }
        HrDutyTrnsHd other = (HrDutyTrnsHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDutyTrnsHd[id=" + id + "]";
    }

    public List<HrDutyTrnsDt> getHrDutyTrnsDtList() {
        return hrDutyTrnsDtList;
    }

    public void setHrDutyTrnsDtList(List<HrDutyTrnsDt> hrDutyTrnsDtList) {
        this.hrDutyTrnsDtList = hrDutyTrnsDtList;
    }

}

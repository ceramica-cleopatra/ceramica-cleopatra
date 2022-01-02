/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_MAIN_TRGT_LEVELS_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrMainTrgtLevelsHd.findAll", query = "SELECT h FROM HrMainTrgtLevelsHd h"),
    @NamedQuery(name = "HrMainTrgtLevelsHd.findById", query = "SELECT h FROM HrMainTrgtLevelsHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrMainTrgtLevelsHd.findByEmpNo", query = "SELECT h FROM HrMainTrgtLevelsHd h WHERE h.empNo = :empNo")})
public class HrMainTrgtLevelsHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_MAIN_TRGT_LEVELS_SEQ", sequenceName="HR_MAIN_TRGT_LEVELS_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_MAIN_TRGT_LEVELS_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "EMP_NO")
    private HrEmpInfo empNo;
    @OneToMany(mappedBy = "hrMainTrgtLevelsHd",cascade=CascadeType.REMOVE)
    private List<HrMainTrgtLevelsDt> hrMainTrgtLevelsDtList;

    public HrMainTrgtLevelsHd() {
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

    public List<HrMainTrgtLevelsDt> getHrMainTrgtLevelsDtList() {
        return hrMainTrgtLevelsDtList;
    }

    public void setHrMainTrgtLevelsDtList(List<HrMainTrgtLevelsDt> hrMainTrgtLevelsDtList) {
        this.hrMainTrgtLevelsDtList = hrMainTrgtLevelsDtList;
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
        if (!(object instanceof HrMainTrgtLevelsHd)) {
            return false;
        }
        HrMainTrgtLevelsHd other = (HrMainTrgtLevelsHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrMainTrgtLevelsHd[id=" + id + "]";
    }

}

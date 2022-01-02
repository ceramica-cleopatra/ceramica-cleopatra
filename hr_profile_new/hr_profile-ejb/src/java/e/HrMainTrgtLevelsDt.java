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
@Table(name = "HR_MAIN_TRGT_LEVELS_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrMainTrgtLevelsDt.findAll", query = "SELECT h FROM HrMainTrgtLevelsDt h"),
    @NamedQuery(name = "HrMainTrgtLevelsDt.findById", query = "SELECT h FROM HrMainTrgtLevelsDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrMainTrgtLevelsDt.findByBrnId", query = "SELECT h FROM HrMainTrgtLevelsDt h WHERE h.brnId = :brnId")})
public class HrMainTrgtLevelsDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_MAIN_TRGT_LEVELS_SEQ", sequenceName="HR_MAIN_TRGT_LEVELS_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_MAIN_TRGT_LEVELS_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @ManyToOne
    @JoinColumn(name = "BRN_ID")
    private HrLocation brnId;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrMainTrgtLevelsHd hrMainTrgtLevelsHd;

    public HrMainTrgtLevelsDt() {
    }

    public HrMainTrgtLevelsDt(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public HrLocation getBrnId() {
        return brnId;
    }

    public void setBrnId(HrLocation brnId) {
        this.brnId = brnId;
    }

    public HrMainTrgtLevelsHd getHrMainTrgtLevelsHd() {
        return hrMainTrgtLevelsHd;
    }

    public void setHrMainTrgtLevelsHd(HrMainTrgtLevelsHd hrMainTrgtLevelsHd) {
        this.hrMainTrgtLevelsHd = hrMainTrgtLevelsHd;
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
        if (!(object instanceof HrMainTrgtLevelsDt)) {
            return false;
        }
        HrMainTrgtLevelsDt other = (HrMainTrgtLevelsDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrMainTrgtLevelsDt[id=" + id + "]";
    }

}

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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_CHECKUP_DUTY_SETUP_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutySetupHd.findAll", query = "SELECT h FROM HrCheckupDutySetupHd h"),
    @NamedQuery(name = "HrCheckupDutySetupHd.findById", query = "SELECT h FROM HrCheckupDutySetupHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutySetupHd.findByEmpNo", query = "SELECT h FROM HrCheckupDutySetupHd h , HrEmpInfo i WHERE (i.deptId=h.deptId or h.deptId is null)"+
                        " and (i.empNo=h.empNo or h.empNo is null)"+
                        " and i.empNo=:empNo")})
public class HrCheckupDutySetupHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @OneToMany(mappedBy = "hrCheckupDutySetupHd")
    private List<HrCheckupDutyLocations> hrCheckupDutyLocationsList;
    @OneToMany(mappedBy = "hrCheckupDutySetupHd")
    private List<HrCheckupDutyItems> hrCheckupDutyItemsList;

    public HrCheckupDutySetupHd() {
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

    
    public List<HrCheckupDutyLocations> getHrCheckupDutyLocationsList() {
        return hrCheckupDutyLocationsList;
    }

    public void setHrCheckupDutyLocationsList(List<HrCheckupDutyLocations> hrCheckupDutyLocationsList) {
        this.hrCheckupDutyLocationsList = hrCheckupDutyLocationsList;
    }

    public List<HrCheckupDutyItems> getHrCheckupDutyItemsList() {
        return hrCheckupDutyItemsList;
    }

    public void setHrCheckupDutyItemsList(List<HrCheckupDutyItems> hrCheckupDutyItemsList) {
        this.hrCheckupDutyItemsList = hrCheckupDutyItemsList;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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
        if (!(object instanceof HrCheckupDutySetupHd)) {
            return false;
        }
        HrCheckupDutySetupHd other = (HrCheckupDutySetupHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutySetupHd[id=" + id + "]";
    }

}

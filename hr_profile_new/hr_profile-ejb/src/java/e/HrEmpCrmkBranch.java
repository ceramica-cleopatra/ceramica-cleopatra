/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_EMP_CRMK_BRANCH", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpCrmkBranch.findAll", query = "SELECT h FROM HrEmpCrmkBranch h"),
    @NamedQuery(name = "HrEmpCrmkBranch.findByEmpNo", query = "SELECT h FROM HrEmpCrmkBranch h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrEmpCrmkBranch.findByCrmkId", query = "SELECT h FROM HrEmpCrmkBranch h WHERE h.crmkId = :crmkId"),
    @NamedQuery(name = "HrEmpCrmkBranch.findByLocId", query = "SELECT h FROM HrEmpCrmkBranch h WHERE h.locId = :locId")})
public class HrEmpCrmkBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private long empNo;
    @Column(name = "CRMK_ID")
    private Long crmkId;
    @Column(name = "LOC_ID")
    private Long locId;

    public HrEmpCrmkBranch() {
    }

    public long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(long empNo) {
        this.empNo = empNo;
    }

    public Long getCrmkId() {
        return crmkId;
    }

    public void setCrmkId(Long crmkId) {
        this.crmkId = crmkId;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

}

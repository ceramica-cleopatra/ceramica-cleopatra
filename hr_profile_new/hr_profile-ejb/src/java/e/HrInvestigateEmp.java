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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_INVESTIGATE_EMP", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrInvestigateEmp.findAll", query = "SELECT h FROM HrInvestigateEmp h"),
    @NamedQuery(name = "HrInvestigateEmp.findTotalRadioCount", query = "SELECT count(distinct h.empNo) FROM HrInvestigateEmp h where h.hrInvestigateDt.hrInvestigateHd=:hd_id"),
    @NamedQuery(name = "HrInvestigateEmp.findMax", query = "SELECT max(h.id) FROM HrInvestigateEmp h"),
    @NamedQuery(name = "HrInvestigateEmp.chkcnt", query = "SELECT count(h.id) FROM HrInvestigateEmp h where h.hrInvestigateDt.hrInvestigateHd.id=:investigate_id and h.empNo=:emp"),
    @NamedQuery(name = "HrInvestigateEmp.findById", query = "SELECT h FROM HrInvestigateEmp h WHERE h.id = :id"),
    @NamedQuery(name = "HrInvestigateEmp.findByEmpNo", query = "SELECT h FROM HrInvestigateEmp h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrInvestigateEmp.findByAnswer", query = "SELECT h FROM HrInvestigateEmp h WHERE h.answer = :answer")})
public class HrInvestigateEmp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "ANSWER")
    private Long answer;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrInvestigateDt hrInvestigateDt;

    public HrInvestigateEmp() {
    }

    public Long getAnswer() {
        return answer;
    }

    public void setAnswer(Long answer) {
        this.answer = answer;
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

   
    public HrInvestigateDt getHrInvestigateDt() {
        return hrInvestigateDt;
    }

    public void setHrInvestigateDt(HrInvestigateDt hrInvestigateDt) {
        this.hrInvestigateDt = hrInvestigateDt;
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
        if (!(object instanceof HrInvestigateEmp)) {
            return false;
        }
        HrInvestigateEmp other = (HrInvestigateEmp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrInvestigateEmp[id=" + id + "]";
    }

}

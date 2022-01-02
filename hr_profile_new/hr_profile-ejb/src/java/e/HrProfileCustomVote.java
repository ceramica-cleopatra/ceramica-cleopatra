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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_PROFILE_CUSTOM_VOTE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrProfileCustomVote.findAll", query = "SELECT h FROM HrProfileCustomVote h"),
    @NamedQuery(name = "HrProfileCustomVote.findById", query = "SELECT h FROM HrProfileCustomVote h WHERE h.id = :id"),
    @NamedQuery(name = "HrProfileCustomVote.findByEmpNo", query = "SELECT h FROM HrProfileCustomVote h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrProfileCustomVote.findByVoteResult", query = "SELECT h FROM HrProfileCustomVote h WHERE h.voteResult = :voteResult"),
    @NamedQuery(name = "HrProfileCustomVote.chk", query = "SELECT count(h.id) FROM HrProfileCustomVote h where h.empNo=:emp_no")})
public class HrProfileCustomVote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(schema="HR",name="HR_PROFILE_CUSTOM_VOTE_SEQ",sequenceName="HR_PROFILE_CUSTOM_VOTE_SEQ", initialValue=1, allocationSize=1)
    @GeneratedValue(generator="HR_PROFILE_CUSTOM_VOTE_SEQ",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "VOTE_RESULT")
    private Long voteResult;

    public HrProfileCustomVote() {
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

    public Long getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(Long voteResult) {
        this.voteResult = voteResult;
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
        if (!(object instanceof HrProfileCustomVote)) {
            return false;
        }
        HrProfileCustomVote other = (HrProfileCustomVote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrProfileCustomVote[id=" + id + "]";
    }

}

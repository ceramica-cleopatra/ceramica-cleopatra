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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "EMP_SUGGEST", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "EmpSuggest.findAll", query = "SELECT e FROM EmpSuggest e"),
    @NamedQuery(name = "EmpSuggest.findmax", query = "select max(o.id) from EmpSuggest o"),
    @NamedQuery(name = "EmpSuggest.findById", query = "SELECT e FROM EmpSuggest e WHERE e.id = :id"),
    @NamedQuery(name = "EmpSuggest.findBySuggestDate", query = "SELECT e FROM EmpSuggest e WHERE e.suggestDate = :suggestDate"),
    @NamedQuery(name = "EmpSuggest.findBySuggestion", query = "SELECT e FROM EmpSuggest e WHERE e.suggestion = :suggestion"),
    @NamedQuery(name = "EmpSuggest.findByEmpNo", query = "SELECT e FROM EmpSuggest e WHERE e.empNo = :empNo")})
public class EmpSuggest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SUGGEST_DATE")
    @Temporal(TemporalType.DATE)
    private Date suggestDate;
    @Column(name = "SUGGESTION")
    private String suggestion;
    @Column(name = "EMP_NO")
    private Long empNo;

    public EmpSuggest() {
    }

  

    public Date getSuggestDate() {
        return suggestDate;
    }

    public void setSuggestDate(Date suggestDate) {
        this.suggestDate = suggestDate;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
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

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpSuggest)) {
            return false;
        }
        EmpSuggest other = (EmpSuggest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.EmpSuggest[id=" + id + "]";
    }

}

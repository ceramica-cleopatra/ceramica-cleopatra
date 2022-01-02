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
@Table(name = "HR_TAMYOZ_SECURITY_TRANSFER", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrTamyozSecurityTransfer.findAll", query = "SELECT h FROM HrTamyozSecurityTransfer h"),
    @NamedQuery(name = "HrTamyozSecurityTransfer.chkSecurityTransfer1", query = "SELECT h FROM HrTamyozSecurityTransfer h where h.approve=1 and h.empNo=:emp"),
    @NamedQuery(name = "HrTamyozSecurityTransfer.chkSecurityTransfer2", query = "SELECT h FROM HrTamyozSecurityTransfer h where h.approve=2 and h.empNo=:emp"),
    @NamedQuery(name = "HrTamyozSecurityTransfer.chkSecurityTransfer3", query = "SELECT h FROM HrTamyozSecurityTransfer h where h.approve=3 and h.empNo=:emp"),
    @NamedQuery(name = "HrTamyozSecurityTransfer.findById", query = "SELECT h FROM HrTamyozSecurityTransfer h WHERE h.id = :id"),
    @NamedQuery(name = "HrTamyozSecurityTransfer.findByEmpNo", query = "SELECT h FROM HrTamyozSecurityTransfer h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrTamyozSecurityTransfer.findByLoc", query = "SELECT h FROM HrTamyozSecurityTransfer h WHERE h.loc = :loc"),
    @NamedQuery(name = "HrTamyozSecurityTransfer.findByApprove", query = "SELECT h FROM HrTamyozSecurityTransfer h WHERE h.approve = :approve")})
public class HrTamyozSecurityTransfer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @ManyToOne
    @JoinColumn(name = "LOC",referencedColumnName = "ID")
    private HrLocation loc;
    @Column(name = "APPROVE")
    private Long approve;

    public HrTamyozSecurityTransfer() {
    }

    public Long getApprove() {
        return approve;
    }

    public void setApprove(Long approve) {
        this.approve = approve;
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

    public HrLocation getLoc() {
        return loc;
    }

    public void setLoc(HrLocation loc) {
        this.loc = loc;
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
        if (!(object instanceof HrTamyozSecurityTransfer)) {
            return false;
        }
        HrTamyozSecurityTransfer other = (HrTamyozSecurityTransfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrTamyozSecurityTransfer[id=" + id + "]";
    }

}

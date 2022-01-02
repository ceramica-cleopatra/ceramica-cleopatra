/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "TAX_DESC", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "TaxDesc.findAll", query = "SELECT t FROM TaxDesc t"),
    @NamedQuery(name = "TaxDesc.findByEmpNo", query = "SELECT t FROM TaxDesc t WHERE t.empNo = :empNo"),
    @NamedQuery(name = "TaxDesc.findByAmount", query = "SELECT t FROM TaxDesc t WHERE t.amount = :amount")})
public class TaxDesc implements Serializable {
  
   @Id
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public TaxDesc() {
    }

   

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

  


}

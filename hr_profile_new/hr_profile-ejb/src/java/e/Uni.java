/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "UNI", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "Uni.findAll", query = "SELECT u FROM Uni u"),
    @NamedQuery(name = "Uni.findByEmpId", query = "SELECT u FROM Uni u WHERE u.empId = :empId"),
    @NamedQuery(name = "Uni.findByAmount", query = "SELECT u FROM Uni u WHERE u.amount = :amount")})
public class Uni implements Serializable {
    private static final long serialVersionUID = 1L;
   
   @Id
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "AMOUNT")
    private Double amount;

    public Uni() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

   
    

    


}

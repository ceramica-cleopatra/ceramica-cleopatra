/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "CRMK_CURRENT_BRN_TRGT", catalog = "", schema = "CRMK")
@NamedQueries({@NamedQuery(name = "CrmkCurrentBrnTrgt.findAll", query = "SELECT c FROM CrmkCurrentBrnTrgt c where c.brnId=:brn_id"),
               @NamedQuery(name = "CrmkCurrentBrnTrgt.findBrnOrder", query = "SELECT count(c) FROM CrmkCurrentBrnTrgt c where c.percent>:percent")})
public class CrmkCurrentBrnTrgt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="BRN_ID")
    private Long brnId;
    @Column(name="AMOUNT")
    private Long amount;
    @Column(name="T_TARGET")
    private Long trgt;
    @Column(name="PERCENT")
    private Double percent;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Long getTrgt() {
        return trgt;
    }

    public void setTrgt(Long trgt) {
        this.trgt = trgt;
    }

    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brnId != null ? brnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmkCurrentBrnTrgt)) {
            return false;
        }
        CrmkCurrentBrnTrgt other = (CrmkCurrentBrnTrgt) object;
        if ((this.brnId == null && other.brnId != null) || (this.brnId != null && !this.brnId.equals(other.brnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkCurrentBrnTrgt[id=" + brnId + "]";
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_SHOWROOM_TRGT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrShowroomTrgt.findAll", query = "SELECT h FROM HrShowroomTrgt h order by h.percent desc"),
    @NamedQuery(name = "HrShowroomTrgt.findByBrnId", query = "SELECT h FROM HrShowroomTrgt h WHERE h.brnId.id = :brnId"),
    @NamedQuery(name = "HrShowroomTrgt.findByAmount", query = "SELECT h FROM HrShowroomTrgt h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrShowroomTrgt.findByTTarget", query = "SELECT h FROM HrShowroomTrgt h WHERE h.tTarget = :tTarget"),
    @NamedQuery(name = "HrShowroomTrgt.findByPercent", query = "SELECT h FROM HrShowroomTrgt h WHERE h.percent = :percent"),
    @NamedQuery(name = "HrShowroomTrgt.findBrnOrder", query = "SELECT count(h) FROM HrShowroomTrgt h where h.percent>:percent")})
public class HrShowroomTrgt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "BRN_ID",referencedColumnName="ID")
    private HrLocation brnId;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "T_TARGET")
    private Long tTarget;
    @Column(name = "PERCENT")
    private Double percent;

    public HrShowroomTrgt() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public HrLocation getBrnId() {
        return brnId;
    }

    public void setBrnId(HrLocation brnId) {
        this.brnId = brnId;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Long gettTarget() {
        return tTarget;
    }

    public void settTarget(Long tTarget) {
        this.tTarget = tTarget;
    }
}

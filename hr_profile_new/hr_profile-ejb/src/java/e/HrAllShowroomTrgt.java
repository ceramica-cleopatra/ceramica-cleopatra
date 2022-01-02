/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "HR_ALL_SHOWROOM_TRGT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrAllShowroomTrgt.findAll", query = "SELECT h FROM HrAllShowroomTrgt h"),
    @NamedQuery(name = "HrAllShowroomTrgt.findById", query = "SELECT h FROM HrAllShowroomTrgt h WHERE h.id = :id"),
    @NamedQuery(name = "HrAllShowroomTrgt.findByBrnId", query = "SELECT h FROM HrAllShowroomTrgt h WHERE h.brnId = :brnId"),
    @NamedQuery(name = "HrAllShowroomTrgt.findByAmount", query = "SELECT h FROM HrAllShowroomTrgt h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrAllShowroomTrgt.findByTTarget", query = "SELECT h FROM HrAllShowroomTrgt h WHERE h.tTarget = :tTarget"),
    @NamedQuery(name = "HrAllShowroomTrgt.findByPercent", query = "SELECT h FROM HrAllShowroomTrgt h WHERE h.percent = :percent"),
    @NamedQuery(name = "HrAllShowroomTrgt.findByMonths", query = "SELECT h FROM HrAllShowroomTrgt h WHERE h.months = :months")})
public class HrAllShowroomTrgt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "BRN_ID")
    private Long brnId;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "T_TARGET")
    private Long tTarget;
    @Column(name = "PERCENT")
    private Double percent;
    @Column(name = "MONTHS")
    private String months;

    public HrAllShowroomTrgt() {
    }

    

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

}

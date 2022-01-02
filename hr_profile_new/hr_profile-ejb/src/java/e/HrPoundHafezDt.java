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
 * @author Administrator
 */
@Entity
@Table(name = "HR_POUND_HAFEZ_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrPoundHafezDt.findAll", query = "SELECT h FROM HrPoundHafezDt h where h.hrPoundHafezHd.fromDate=(select max(o.fromDate) from HrPoundHafezHd o) and h.empId=:emp_id"),
    @NamedQuery(name = "HrPoundHafezDt.findById", query = "SELECT h FROM HrPoundHafezDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrPoundHafezDt.findByTrgt", query = "SELECT h FROM HrPoundHafezDt h WHERE h.trgt = :trgt"),
    @NamedQuery(name = "HrPoundHafezDt.findByQty", query = "SELECT h FROM HrPoundHafezDt h WHERE h.qty = :qty"),
    @NamedQuery(name = "HrPoundHafezDt.findByVar", query = "SELECT h FROM HrPoundHafezDt h WHERE h.var = :var"),
    @NamedQuery(name = "HrPoundHafezDt.findByBasic", query = "SELECT h FROM HrPoundHafezDt h WHERE h.basic = :basic"),
    @NamedQuery(name = "HrPoundHafezDt.findByCheckupDuty", query = "SELECT h FROM HrPoundHafezDt h WHERE h.checkupDuty = :checkupDuty"),
    @NamedQuery(name = "HrPoundHafezDt.findByGza", query = "SELECT h FROM HrPoundHafezDt h WHERE h.gza = :gza"),
    @NamedQuery(name = "HrPoundHafezDt.findByEffections", query = "SELECT h FROM HrPoundHafezDt h WHERE h.effections = :effections"),
    @NamedQuery(name = "HrPoundHafezDt.findByNet", query = "SELECT h FROM HrPoundHafezDt h WHERE h.net = :net"),
    @NamedQuery(name = "HrPoundHafezDt.findByEmpId", query = "SELECT h FROM HrPoundHafezDt h WHERE h.empId = :empId")})
public class HrPoundHafezDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "TRGT")
    private BigInteger trgt;
    @Column(name = "QTY")
    private BigInteger qty;
    @Column(name = "VAR")
    private BigInteger var;
    @Column(name = "BASIC")
    private BigInteger basic;
    @Column(name = "CHECKUP_DUTY")
    private BigInteger checkupDuty;
    @Column(name = "RAISE_BASIC_2018")
    private BigInteger raiseBasic2018;
    @Column(name = "RAISE_VAR_2018")
    private BigInteger raiseVar2018;
    @Column(name = "GZA")
    private BigInteger gza;
    @Column(name = "EFFECTIONS")
    private BigInteger effections;
    @Column(name = "NET")
    private BigInteger net;
    @Column(name = "EMP_ID")
    private BigInteger empId;
    @Column(name="STORE_ISSUES")
    private BigInteger storeIssues;
    @Column(name="LOAD_BONUS")
    private BigInteger loadBonus;
    @Column(name="DRIVERS_BONUS")
    private BigInteger driversBonus;
    @Column(name="TRNS_BONUS")
    private BigInteger trnsBonus;
    @Column(name="OTHER")
    private BigInteger other;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrPoundHafezHd hrPoundHafezHd;


    public HrPoundHafezDt() {
    }

    public HrPoundHafezDt(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getTrgt() {
        return trgt;
    }

    public void setTrgt(BigInteger trgt) {
        this.trgt = trgt;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public BigInteger getVar() {
        return var;
    }

    public void setVar(BigInteger var) {
        this.var = var;
    }

    public BigInteger getBasic() {
        return basic;
    }

    public void setBasic(BigInteger basic) {
        this.basic = basic;
    }

    public BigInteger getCheckupDuty() {
        return checkupDuty;
    }

    public void setCheckupDuty(BigInteger checkupDuty) {
        this.checkupDuty = checkupDuty;
    }

    public BigInteger getGza() {
        return gza;
    }

    public void setGza(BigInteger gza) {
        this.gza = gza;
    }

    public BigInteger getEffections() {
        return effections;
    }

    public void setEffections(BigInteger effections) {
        this.effections = effections;
    }

    public BigInteger getNet() {
        return net;
    }

    public void setNet(BigInteger net) {
        this.net = net;
    }

    public BigInteger getEmpId() {
        return empId;
    }

    public void setEmpId(BigInteger empId) {
        this.empId = empId;
    }

    public HrPoundHafezHd getHrPoundHafezHd() {
        return hrPoundHafezHd;
    }

    public void setHrPoundHafezHd(HrPoundHafezHd hrPoundHafezHd) {
        this.hrPoundHafezHd = hrPoundHafezHd;
    }

    public BigInteger getStoreIssues() {
        return storeIssues;
    }

    public void setStoreIssues(BigInteger storeIssues) {
        this.storeIssues = storeIssues;
    }

    public BigInteger getRaiseBasic2018() {
        return raiseBasic2018;
    }

    public void setRaiseBasic2018(BigInteger raiseBasic2018) {
        this.raiseBasic2018 = raiseBasic2018;
    }

    public BigInteger getRaiseVar2018() {
        return raiseVar2018;
    }

    public void setRaiseVar2018(BigInteger raiseVar2018) {
        this.raiseVar2018 = raiseVar2018;
    }

    public BigInteger getLoadBonus() {
        return loadBonus;
    }

    public void setLoadBonus(BigInteger loadBonus) {
        this.loadBonus = loadBonus;
    }

    public BigInteger getDriversBonus() {
        return driversBonus;
    }

    public void setDriversBonus(BigInteger driversBonus) {
        this.driversBonus = driversBonus;
    }

    public BigInteger getOther() {
        return other;
    }

    public void setOther(BigInteger other) {
        this.other = other;
    }

    public BigInteger getTrnsBonus() {
        return trnsBonus;
    }

    public void setTrnsBonus(BigInteger trnsBonus) {
        this.trnsBonus = trnsBonus;
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
        if (!(object instanceof HrPoundHafezDt)) {
            return false;
        }
        HrPoundHafezDt other = (HrPoundHafezDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrPoundHafezDt[id=" + id + "]";
    }

}

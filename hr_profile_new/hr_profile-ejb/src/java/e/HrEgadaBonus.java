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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_EGADA_BONUS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEgadaBonus.findAll", query = "SELECT h FROM HrEgadaBonus h"),
    @NamedQuery(name = "HrEgadaBonus.findByEmpNo", query = "SELECT h FROM HrEgadaBonus h WHERE h.empNo = :empNo and h.trnsMonth = :trnsMonth and h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrEgadaBonus.findByValue", query = "SELECT h FROM HrEgadaBonus h WHERE h.value = :value"),
    @NamedQuery(name = "HrEgadaBonus.findByEffection", query = "SELECT h FROM HrEgadaBonus h WHERE h.effection = :effection"),
    @NamedQuery(name = "HrEgadaBonus.findByTax", query = "SELECT h FROM HrEgadaBonus h WHERE h.tax = :tax"),
    @NamedQuery(name = "HrEgadaBonus.findByNet", query = "SELECT h FROM HrEgadaBonus h WHERE h.net = :net"),
    @NamedQuery(name = "HrEgadaBonus.findByTrnsMonth", query = "SELECT h FROM HrEgadaBonus h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrEgadaBonus.findByTrnsYear", query = "SELECT h FROM HrEgadaBonus h WHERE h.trnsYear = :trnsYear")})
public class HrEgadaBonus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name= "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "VALUE")
    private Double value;
    @Column(name = "EFFECTION")
    private Double effection;
    @Column(name = "TAX")
    private Double tax;
    @Column(name = "MARADY")
    private Double marady;
    @Column(name = "ESABA")
    private Double esaba;
    @Column(name = "MINUS_MINUTES")
    private Double minusMinutes;
    @Column(name = "MINUS_DAYS")
    private Double minusDays;
    @Column(name = "AUTH")
    private Double auth;
    @Column(name = "NOT_AUTH")
    private Double notAuth;
    @Column(name = "LIST_ID")
    private Long listId;
    @Column(name = "LIST_NAME")
    private String listName;
    @Column(name = "NET")
    private Double net;
    @Column(name = "TRNS_MONTH")
    private Long trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Long trnsYear;

    public HrEgadaBonus() {
    }

    public Double getEffection() {
        return effection;
    }

    public void setEffection(Double effection) {
        this.effection = effection;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Long getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(Long trnsMonth) {
        this.trnsMonth = trnsMonth;
    }

    public Long getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Long trnsYear) {
        this.trnsYear = trnsYear;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getAuth() {
        return auth;
    }

    public void setAuth(Double auth) {
        this.auth = auth;
    }

    public Double getEsaba() {
        return esaba;
    }

    public void setEsaba(Double esaba) {
        this.esaba = esaba;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Double getMarady() {
        return marady;
    }

    public void setMarady(Double marady) {
        this.marady = marady;
    }

    public Double getMinusDays() {
        return minusDays;
    }

    public void setMinusDays(Double minusDays) {
        this.minusDays = minusDays;
    }

    public Double getMinusMinutes() {
        return minusMinutes;
    }

    public void setMinusMinutes(Double minusMinutes) {
        this.minusMinutes = minusMinutes;
    }

    public Double getNotAuth() {
        return notAuth;
    }

    public void setNotAuth(Double notAuth) {
        this.notAuth = notAuth;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empNo != null ? empNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrEgadaBonus)) {
            return false;
        }
        HrEgadaBonus other = (HrEgadaBonus) object;
        if ((this.empNo == null && other.empNo != null) || (this.empNo != null && !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrEgadaBonus[empNo=" + empNo + "]";
    }

}

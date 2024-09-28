/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
@Table(name = "HR_SAL_DETAIL", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrSalDetail.findAll", query = "SELECT h FROM HrSalDetail h"),
    @NamedQuery(name = "HrSalDetail.findByEmpNo", query = "SELECT h FROM HrSalDetail h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrSalDetail.findByTotSal", query = "SELECT h FROM HrSalDetail h WHERE h.totSal = :totSal"),
    @NamedQuery(name = "HrSalDetail.findByBasicSal", query = "SELECT h FROM HrSalDetail h WHERE h.basicSal = :basicSal"),
    @NamedQuery(name = "HrSalDetail.findByAuthVal", query = "SELECT h FROM HrSalDetail h WHERE h.authVal = :authVal"),
    @NamedQuery(name = "HrSalDetail.findByNotAuthVal", query = "SELECT h FROM HrSalDetail h WHERE h.notAuthVal = :notAuthVal"),
    @NamedQuery(name = "HrSalDetail.findByMinusMinutsVal", query = "SELECT h FROM HrSalDetail h WHERE h.minusMinutsVal = :minusMinutsVal"),
    @NamedQuery(name = "HrSalDetail.findByMinusDaysVal", query = "SELECT h FROM HrSalDetail h WHERE h.minusDaysVal = :minusDaysVal"),
    @NamedQuery(name = "HrSalDetail.findByMaradyVal", query = "SELECT h FROM HrSalDetail h WHERE h.maradyVal = :maradyVal"),
    @NamedQuery(name = "HrSalDetail.findByEsabaVal", query = "SELECT h FROM HrSalDetail h WHERE h.esabaVal = :esabaVal"),
    @NamedQuery(name = "HrSalDetail.findByTax", query = "SELECT h FROM HrSalDetail h WHERE h.tax = :tax"),
    @NamedQuery(name = "HrSalDetail.findByWorkerPay", query = "SELECT h FROM HrSalDetail h WHERE h.workerPay = :workerPay"),
    @NamedQuery(name = "HrSalDetail.findByPlusValue", query = "SELECT h FROM HrSalDetail h WHERE h.plusValue = :plusValue"),
    @NamedQuery(name = "HrSalDetail.findByHafezTot", query = "SELECT h FROM HrSalDetail h WHERE h.hafezTot = :hafezTot"),
    @NamedQuery(name = "HrSalDetail.findByCutoffTot", query = "SELECT h FROM HrSalDetail h WHERE h.cutoffTot = :cutoffTot"),
    @NamedQuery(name = "HrSalDetail.findBySalesTamyozTot", query = "SELECT h FROM HrSalDetail h WHERE h.salesTamyozTot = :salesTamyozTot"),
    @NamedQuery(name = "HrSalDetail.findByTamyozTot", query = "SELECT h FROM HrSalDetail h WHERE h.tamyozTot = :tamyozTot")})
public class HrSalDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "TOT_SAL")
    private Double totSal;
    @Column(name = "BASIC_SAL")
    private Double basicSal;
    @Column(name = "AUTH_VAL")
    private Double authVal;
    @Column(name = "NOT_AUTH_VAL")
    private Double notAuthVal;
    @Column(name = "MINUS_MINUTS_VAL")
    private Double minusMinutsVal;
    @Column(name = "MINUS_DAYS_VAL")
    private Double minusDaysVal;
    @Column(name = "MARADY_VAL")
    private Double maradyVal;
    @Column(name = "ESABA_VAL")
    private Double esabaVal;
    @Column(name = "TAX")
    private Double tax;
    @Column(name = "WORKER_PAY")
    private Double workerPay;
    @Column(name = "PLUS_VALUE")
    private Double plusValue;
    @Column(name = "HAFEZ_TOT")
    private Double hafezTot;
    @Column(name = "CUTOFF_TOT")
    private Double cutoffTot;
    @Column(name = "SALES_TAMYOZ_TOT")
    private Double salesTamyozTot;
    @Column(name = "TAMYOZ_TOT")
    private Double tamyozTot;
    @Column(name = "TRNS_MONTH")
    private Long trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Long trnsYear;

    public HrSalDetail() {
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Double getTotSal() {
        return totSal;
    }

    public void setTotSal(Double totSal) {
        this.totSal = totSal;
    }

    public Double getBasicSal() {
        return basicSal;
    }

    public void setBasicSal(Double basicSal) {
        this.basicSal = basicSal;
    }

    public Double getAuthVal() {
        return authVal;
    }

    public void setAuthVal(Double authVal) {
        this.authVal = authVal;
    }

    public Double getNotAuthVal() {
        return notAuthVal;
    }

    public void setNotAuthVal(Double notAuthVal) {
        this.notAuthVal = notAuthVal;
    }

    public Double getMinusMinutsVal() {
        return minusMinutsVal;
    }

    public void setMinusMinutsVal(Double minusMinutsVal) {
        this.minusMinutsVal = minusMinutsVal;
    }

    public Double getMinusDaysVal() {
        return minusDaysVal;
    }

    public void setMinusDaysVal(Double minusDaysVal) {
        this.minusDaysVal = minusDaysVal;
    }

    public Double getMaradyVal() {
        return maradyVal;
    }

    public void setMaradyVal(Double maradyVal) {
        this.maradyVal = maradyVal;
    }

    public Double getEsabaVal() {
        return esabaVal;
    }

    public void setEsabaVal(Double esabaVal) {
        this.esabaVal = esabaVal;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getWorkerPay() {
        return workerPay;
    }

    public void setWorkerPay(Double workerPay) {
        this.workerPay = workerPay;
    }

    public Double getPlusValue() {
        return plusValue;
    }

    public void setPlusValue(Double plusValue) {
        this.plusValue = plusValue;
    }

    public Double getHafezTot() {
        return hafezTot;
    }

    public void setHafezTot(Double hafezTot) {
        this.hafezTot = hafezTot;
    }

    public Double getCutoffTot() {
        return cutoffTot;
    }

    public void setCutoffTot(Double cutoffTot) {
        this.cutoffTot = cutoffTot;
    }

    public Double getSalesTamyozTot() {
        return salesTamyozTot;
    }

    public void setSalesTamyozTot(Double salesTamyozTot) {
        this.salesTamyozTot = salesTamyozTot;
    }

    public Double getTamyozTot() {
        return tamyozTot;
    }

    public void setTamyozTot(Double tamyozTot) {
        this.tamyozTot = tamyozTot;
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


}

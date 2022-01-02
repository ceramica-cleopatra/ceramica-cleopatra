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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_SAL_HISTORY", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrSalHistory.findAll", query = "SELECT h FROM HrSalHistory h where h.empNo=:emp and h.trnsMonth=:m and h.trnsYear=:y"),
    @NamedQuery(name = "HrSalHistory.maxYear", query = "SELECT max(h.trnsYear) FROM HrSalHistory h"),
    @NamedQuery(name = "HrSalHistory.maxMonth", query = "SELECT max(h.trnsMonth) FROM HrSalHistory h where h.trnsYear=:y"),
    @NamedQuery(name = "HrSalHistory.chkOldDate", query = "select count(o.empNo) from HrSalHistory o where o.trnsMonth in (select y.trnsMonth from HrMontlySalaryCalcPeriod y where :from_date between y.dateFrom and y.dateTo or :to_date between y.dateFrom and y.dateTo) and o.trnsYear in (select x.trnsYear from HrMontlySalaryCalcPeriod x where :from_date between x.dateFrom and x.dateTo or :to_date between x.dateFrom and x.dateTo)"),
    @NamedQuery(name = "HrSalHistory.chkOldDateForAuthorize", query = "select count(o.empNo) from HrSalHistory o where o.trnsMonth in (select y.trnsMonth from HrMontlySalaryCalcPeriod y where :authorize_date between y.dateFrom and y.dateTo) and o.trnsYear in (select x.trnsYear from HrMontlySalaryCalcPeriod x where :authorize_date between x.dateFrom and x.dateTo)"),
    @NamedQuery(name = "HrSalHistory.findByEmpNo", query = "SELECT h FROM HrSalHistory h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrSalHistory.findByTrnsMonth", query = "SELECT h FROM HrSalHistory h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrSalHistory.findByTrnsYear", query = "SELECT h FROM HrSalHistory h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrSalHistory.findByBasicSal", query = "SELECT h FROM HrSalHistory h WHERE h.basicSal = :basicSal"),
    @NamedQuery(name = "HrSalHistory.findByVarSal", query = "SELECT h FROM HrSalHistory h WHERE h.varSal = :varSal"),
    @NamedQuery(name = "HrSalHistory.findByTotSal", query = "SELECT h FROM HrSalHistory h WHERE h.totSal = :totSal"),
    @NamedQuery(name = "HrSalHistory.findByTax", query = "SELECT h FROM HrSalHistory h WHERE h.tax = :tax"),
    @NamedQuery(name = "HrSalHistory.findByCutOff", query = "SELECT h FROM HrSalHistory h WHERE h.cutOff = :cutOff"),
    @NamedQuery(name = "HrSalHistory.findByEffectMinusVal", query = "SELECT h FROM HrSalHistory h WHERE h.effectMinusVal = :effectMinusVal"),
    @NamedQuery(name = "HrSalHistory.findByWorkerPay", query = "SELECT h FROM HrSalHistory h WHERE h.workerPay = :workerPay"),
    @NamedQuery(name = "HrSalHistory.findByCompanyPay", query = "SELECT h FROM HrSalHistory h WHERE h.companyPay = :companyPay"),
    @NamedQuery(name = "HrSalHistory.findByPlusValue", query = "SELECT h FROM HrSalHistory h WHERE h.plusValue = :plusValue"),
    @NamedQuery(name = "HrSalHistory.findByAuthVal", query = "SELECT h FROM HrSalHistory h WHERE h.authVal = :authVal"),
    @NamedQuery(name = "HrSalHistory.findByNotAuthVal", query = "SELECT h FROM HrSalHistory h WHERE h.notAuthVal = :notAuthVal"),
    @NamedQuery(name = "HrSalHistory.findByMinusMinutsVal", query = "SELECT h FROM HrSalHistory h WHERE h.minusMinutsVal = :minusMinutsVal"),
    @NamedQuery(name = "HrSalHistory.findByMinusDaysVal", query = "SELECT h FROM HrSalHistory h WHERE h.minusDaysVal = :minusDaysVal"),
    @NamedQuery(name = "HrSalHistory.findByEsaba", query = "SELECT h FROM HrSalHistory h WHERE h.esaba = :esaba"),
    @NamedQuery(name = "HrSalHistory.findByEsabaRatio", query = "SELECT h FROM HrSalHistory h WHERE h.esabaRatio = :esabaRatio"),
    @NamedQuery(name = "HrSalHistory.findByEsabaVal", query = "SELECT h FROM HrSalHistory h WHERE h.esabaVal = :esabaVal"),
    @NamedQuery(name = "HrSalHistory.findByMarady", query = "SELECT h FROM HrSalHistory h WHERE h.marady = :marady"),
    @NamedQuery(name = "HrSalHistory.findByMaradyRatio", query = "SELECT h FROM HrSalHistory h WHERE h.maradyRatio = :maradyRatio"),
    @NamedQuery(name = "HrSalHistory.findByMaradyVal", query = "SELECT h FROM HrSalHistory h WHERE h.maradyVal = :maradyVal"),
    @NamedQuery(name = "HrSalHistory.findByListId", query = "SELECT h FROM HrSalHistory h WHERE h.listId = :listId")})
public class HrSalHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "TRNS_MONTH")
    private Long trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Long trnsYear;
    @Column(name = "BASIC_SAL")
    private Double basicSal;
    @Column(name = "VAR_SAL")
    private Double varSal;
    @Column(name = "TOT_SAL")
    private Double totSal;
    @Column(name = "TAX")
    private Double tax;
    @Column(name = "CUT_OFF")
    private Double cutOff;
    @Column(name = "EFFECT_MINUS_VAL")
    private Double effectMinusVal;
    @Column(name = "WORKER_PAY")
    private Double workerPay;
    @Column(name = "COMPANY_PAY")
    private Double companyPay;
    @Column(name = "PLUS_VALUE")
    private Double plusValue;
    @Column(name = "AUTH_VAL")
    private Double authVal;
    @Column(name = "NOT_AUTH_VAL")
    private Double notAuthVal;
    @Column(name = "MINUS_MINUTS_VAL")
    private Double minusMinutsVal;
    @Column(name = "MINUS_DAYS_VAL")
    private Double minusDaysVal;
    @Column(name = "ESABA")
    private Double esaba;
    @Column(name = "ESABA_RATIO")
    private Double esabaRatio;
    @Column(name = "ESABA_VAL")
    private Double esabaVal;
    @Column(name = "MARADY")
    private Double marady;
    @Column(name = "MARADY_RATIO")
    private Double maradyRatio;
    @Column(name = "MARADY_VAL")
    private Double maradyVal;
    @Column(name = "LIST_ID")
    private Long listId;

    public HrSalHistory() {
    }


    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
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

    public Double getAuthVal() {
        return authVal;
    }

    public void setAuthVal(Double authVal) {
        this.authVal = authVal;
    }

    public Double getBasicSal() {
        return basicSal;
    }

    public void setBasicSal(Double basicSal) {
        this.basicSal = basicSal;
    }

    public Double getCompanyPay() {
        return companyPay;
    }

    public void setCompanyPay(Double companyPay) {
        this.companyPay = companyPay;
    }

    public Double getCutOff() {
        return cutOff;
    }

    public void setCutOff(Double cutOff) {
        this.cutOff = cutOff;
    }

    public Double getEffectMinusVal() {
        return effectMinusVal;
    }

    public void setEffectMinusVal(Double effectMinusVal) {
        this.effectMinusVal = effectMinusVal;
    }

    public Double getEsaba() {
        return esaba;
    }

    public void setEsaba(Double esaba) {
        this.esaba = esaba;
    }

    public Double getEsabaRatio() {
        return esabaRatio;
    }

    public void setEsabaRatio(Double esabaRatio) {
        this.esabaRatio = esabaRatio;
    }

    public Double getEsabaVal() {
        return esabaVal;
    }

    public void setEsabaVal(Double esabaVal) {
        this.esabaVal = esabaVal;
    }

    public Double getMarady() {
        return marady;
    }

    public void setMarady(Double marady) {
        this.marady = marady;
    }

    public Double getMaradyRatio() {
        return maradyRatio;
    }

    public void setMaradyRatio(Double maradyRatio) {
        this.maradyRatio = maradyRatio;
    }

    public Double getMaradyVal() {
        return maradyVal;
    }

    public void setMaradyVal(Double maradyVal) {
        this.maradyVal = maradyVal;
    }

    public Double getMinusDaysVal() {
        return minusDaysVal;
    }

    public void setMinusDaysVal(Double minusDaysVal) {
        this.minusDaysVal = minusDaysVal;
    }

    public Double getMinusMinutsVal() {
        return minusMinutsVal;
    }

    public void setMinusMinutsVal(Double minusMinutsVal) {
        this.minusMinutsVal = minusMinutsVal;
    }

    public Double getNotAuthVal() {
        return notAuthVal;
    }

    public void setNotAuthVal(Double notAuthVal) {
        this.notAuthVal = notAuthVal;
    }

    public Double getPlusValue() {
        return plusValue;
    }

    public void setPlusValue(Double plusValue) {
        this.plusValue = plusValue;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotSal() {
        return totSal;
    }

    public void setTotSal(Double totSal) {
        this.totSal = totSal;
    }

    public Double getVarSal() {
        return varSal;
    }

    public void setVarSal(Double varSal) {
        this.varSal = varSal;
    }

    public Double getWorkerPay() {
        return workerPay;
    }

    public void setWorkerPay(Double workerPay) {
        this.workerPay = workerPay;
    }

  
    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

  
}

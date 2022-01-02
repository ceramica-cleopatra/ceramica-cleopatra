/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_SAL_CALC", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name="HrSalCalc.chkReview",query="select count(o.empNo) from HrSalCalc o where o.trnsMonth in (select y.trnsMonth from HrMontlySalaryCalcPeriod y where :from_date between y.dateFrom and y.dateTo or :to_date between y.dateFrom and y.dateTo) and o.trnsYear in (select x.trnsYear from HrMontlySalaryCalcPeriod x where :from_date between x.dateFrom and x.dateTo or :to_date between x.dateFrom and x.dateTo)"),
    @NamedQuery(name="HrSalCalc.chkReviewForAuthorize",query="select count(o.empNo) from HrSalCalc o where o.trnsMonth in (select y.trnsMonth from HrMontlySalaryCalcPeriod y where :authorize_date between y.dateFrom and y.dateTo) and o.trnsYear in (select x.trnsYear from HrMontlySalaryCalcPeriod x where :authorize_date between x.dateFrom and x.dateTo)"),
    @NamedQuery(name = "HrSalCalc.findAll", query = "SELECT h FROM HrSalCalc h"),
    @NamedQuery(name = "HrSalCalc.findByEmpNo", query = "SELECT h FROM HrSalCalc h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrSalCalc.findByTrnsMonth", query = "SELECT h FROM HrSalCalc h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrSalCalc.findByTrnsYear", query = "SELECT h FROM HrSalCalc h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrSalCalc.findByTotMinusMinuts", query = "SELECT h FROM HrSalCalc h WHERE h.totMinusMinuts = :totMinusMinuts"),
    @NamedQuery(name = "HrSalCalc.findByTotMinusDays", query = "SELECT h FROM HrSalCalc h WHERE h.totMinusDays = :totMinusDays"),
    @NamedQuery(name = "HrSalCalc.findByTotPlusMinuts", query = "SELECT h FROM HrSalCalc h WHERE h.totPlusMinuts = :totPlusMinuts"),
    @NamedQuery(name = "HrSalCalc.findByTotPlusDays", query = "SELECT h FROM HrSalCalc h WHERE h.totPlusDays = :totPlusDays"),
    @NamedQuery(name = "HrSalCalc.findByTotSal", query = "SELECT h FROM HrSalCalc h WHERE h.totSal = :totSal"),
    @NamedQuery(name = "HrSalCalc.findByBasicSal", query = "SELECT h FROM HrSalCalc h WHERE h.basicSal = :basicSal"),
    @NamedQuery(name = "HrSalCalc.findByVarSal", query = "SELECT h FROM HrSalCalc h WHERE h.varSal = :varSal"),
    @NamedQuery(name = "HrSalCalc.findByOthers", query = "SELECT h FROM HrSalCalc h WHERE h.others = :others"),
    @NamedQuery(name = "HrSalCalc.findByTax", query = "SELECT h FROM HrSalCalc h WHERE h.tax = :tax"),
    @NamedQuery(name = "HrSalCalc.findByAuth", query = "SELECT h FROM HrSalCalc h WHERE h.auth = :auth"),
    @NamedQuery(name = "HrSalCalc.findByNotAuth", query = "SELECT h FROM HrSalCalc h WHERE h.notAuth = :notAuth"),
    @NamedQuery(name = "HrSalCalc.findByMinusValue", query = "SELECT h FROM HrSalCalc h WHERE h.minusValue = :minusValue"),
    @NamedQuery(name = "HrSalCalc.findByPlusValue", query = "SELECT h FROM HrSalCalc h WHERE h.plusValue = :plusValue"),
    @NamedQuery(name = "HrSalCalc.findByAuthValue", query = "SELECT h FROM HrSalCalc h WHERE h.authValue = :authValue"),
    @NamedQuery(name = "HrSalCalc.findByNotAuthValue", query = "SELECT h FROM HrSalCalc h WHERE h.notAuthValue = :notAuthValue"),
    @NamedQuery(name = "HrSalCalc.findByClosed", query = "SELECT h FROM HrSalCalc h WHERE h.closed = :closed"),
    @NamedQuery(name = "HrSalCalc.findByUserName", query = "SELECT h FROM HrSalCalc h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrSalCalc.findByMachineName", query = "SELECT h FROM HrSalCalc h WHERE h.machineName = :machineName"),
    @NamedQuery(name = "HrSalCalc.findByMinusMinutsVal", query = "SELECT h FROM HrSalCalc h WHERE h.minusMinutsVal = :minusMinutsVal"),
    @NamedQuery(name = "HrSalCalc.findByMinusDaysVal", query = "SELECT h FROM HrSalCalc h WHERE h.minusDaysVal = :minusDaysVal"),
    @NamedQuery(name = "HrSalCalc.findByMarady", query = "SELECT h FROM HrSalCalc h WHERE h.marady = :marady"),
    @NamedQuery(name = "HrSalCalc.findByMaradyRatio", query = "SELECT h FROM HrSalCalc h WHERE h.maradyRatio = :maradyRatio"),
    @NamedQuery(name = "HrSalCalc.findByMaradyVal", query = "SELECT h FROM HrSalCalc h WHERE h.maradyVal = :maradyVal"),
    @NamedQuery(name = "HrSalCalc.findByEsaba", query = "SELECT h FROM HrSalCalc h WHERE h.esaba = :esaba"),
    @NamedQuery(name = "HrSalCalc.findByEsabaRatio", query = "SELECT h FROM HrSalCalc h WHERE h.esabaRatio = :esabaRatio"),
    @NamedQuery(name = "HrSalCalc.findByEsabaVal", query = "SELECT h FROM HrSalCalc h WHERE h.esabaVal = :esabaVal"),
    @NamedQuery(name = "HrSalCalc.findByListId", query = "SELECT h FROM HrSalCalc h WHERE h.listId = :listId"),
    @NamedQuery(name = "HrSalCalc.findByWorkerPay", query = "SELECT h FROM HrSalCalc h WHERE h.workerPay = :workerPay"),
    @NamedQuery(name = "HrSalCalc.findByCompanyPay", query = "SELECT h FROM HrSalCalc h WHERE h.companyPay = :companyPay")})
public class HrSalCalc implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Id
    @Column(name = "EMP_NO")
    private BigInteger empNo;
    @Column(name = "TRNS_MONTH")
    private Long trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Short trnsYear;
    @Column(name = "TOT_MINUS_MINUTS")
    private BigInteger totMinusMinuts;
    @Column(name = "TOT_MINUS_DAYS")
    private BigInteger totMinusDays;
    @Column(name = "TOT_PLUS_MINUTS")
    private BigInteger totPlusMinuts;
    @Column(name = "TOT_PLUS_DAYS")
    private BigInteger totPlusDays;
    @Column(name = "TOT_SAL")
    private BigDecimal totSal;
    @Column(name = "BASIC_SAL")
    private BigDecimal basicSal;
    @Column(name = "VAR_SAL")
    private BigDecimal varSal;
    @Column(name = "OTHERS")
    private BigDecimal others;
    @Column(name = "TAX")
    private BigDecimal tax;
    @Column(name = "AUTH")
    private BigInteger auth;
    @Column(name = "NOT_AUTH")
    private BigInteger notAuth;
    @Column(name = "MINUS_VALUE")
    private BigDecimal minusValue;
    @Column(name = "PLUS_VALUE")
    private BigDecimal plusValue;
    @Column(name = "AUTH_VALUE")
    private BigDecimal authValue;
    @Column(name = "NOT_AUTH_VALUE")
    private BigDecimal notAuthValue;
    @Column(name = "CLOSED")
    private String closed;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MACHINE_NAME")
    private String machineName;
    @Column(name = "MINUS_MINUTS_VAL")
    private BigDecimal minusMinutsVal;
    @Column(name = "MINUS_DAYS_VAL")
    private BigDecimal minusDaysVal;
    @Column(name = "MARADY")
    private Long marady;
    @Column(name = "MARADY_RATIO")
    private BigDecimal maradyRatio;
    @Column(name = "MARADY_VAL")
    private BigDecimal maradyVal;
    @Column(name = "ESABA")
    private Long esaba;
    @Column(name = "ESABA_RATIO")
    private BigDecimal esabaRatio;
    @Column(name = "ESABA_VAL")
    private BigDecimal esabaVal;
    @Column(name = "LIST_ID")
    private Long listId;
    @Column(name = "WORKER_PAY")
    private BigDecimal workerPay;
    @Column(name = "COMPANY_PAY")
    private BigDecimal companyPay;

    public HrSalCalc() {
    }

    public BigInteger getEmpNo() {
        return empNo;
    }

    public void setEmpNo(BigInteger empNo) {
        this.empNo = empNo;
    }

    public Long getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(Long trnsMonth) {
        this.trnsMonth = trnsMonth;
    }

    public Short getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Short trnsYear) {
        this.trnsYear = trnsYear;
    }

    public BigInteger getTotMinusMinuts() {
        return totMinusMinuts;
    }

    public void setTotMinusMinuts(BigInteger totMinusMinuts) {
        this.totMinusMinuts = totMinusMinuts;
    }

    public BigInteger getTotMinusDays() {
        return totMinusDays;
    }

    public void setTotMinusDays(BigInteger totMinusDays) {
        this.totMinusDays = totMinusDays;
    }

    public BigInteger getTotPlusMinuts() {
        return totPlusMinuts;
    }

    public void setTotPlusMinuts(BigInteger totPlusMinuts) {
        this.totPlusMinuts = totPlusMinuts;
    }

    public BigInteger getTotPlusDays() {
        return totPlusDays;
    }

    public void setTotPlusDays(BigInteger totPlusDays) {
        this.totPlusDays = totPlusDays;
    }

    public BigDecimal getTotSal() {
        return totSal;
    }

    public void setTotSal(BigDecimal totSal) {
        this.totSal = totSal;
    }

    public BigDecimal getBasicSal() {
        return basicSal;
    }

    public void setBasicSal(BigDecimal basicSal) {
        this.basicSal = basicSal;
    }

    public BigDecimal getVarSal() {
        return varSal;
    }

    public void setVarSal(BigDecimal varSal) {
        this.varSal = varSal;
    }

    public BigDecimal getOthers() {
        return others;
    }

    public void setOthers(BigDecimal others) {
        this.others = others;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigInteger getAuth() {
        return auth;
    }

    public void setAuth(BigInteger auth) {
        this.auth = auth;
    }

    public BigInteger getNotAuth() {
        return notAuth;
    }

    public void setNotAuth(BigInteger notAuth) {
        this.notAuth = notAuth;
    }

    public BigDecimal getMinusValue() {
        return minusValue;
    }

    public void setMinusValue(BigDecimal minusValue) {
        this.minusValue = minusValue;
    }

    public BigDecimal getPlusValue() {
        return plusValue;
    }

    public void setPlusValue(BigDecimal plusValue) {
        this.plusValue = plusValue;
    }

    public BigDecimal getAuthValue() {
        return authValue;
    }

    public void setAuthValue(BigDecimal authValue) {
        this.authValue = authValue;
    }

    public BigDecimal getNotAuthValue() {
        return notAuthValue;
    }

    public void setNotAuthValue(BigDecimal notAuthValue) {
        this.notAuthValue = notAuthValue;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public BigDecimal getMinusMinutsVal() {
        return minusMinutsVal;
    }

    public void setMinusMinutsVal(BigDecimal minusMinutsVal) {
        this.minusMinutsVal = minusMinutsVal;
    }

    public BigDecimal getMinusDaysVal() {
        return minusDaysVal;
    }

    public void setMinusDaysVal(BigDecimal minusDaysVal) {
        this.minusDaysVal = minusDaysVal;
    }

    public Long getMarady() {
        return marady;
    }

    public void setMarady(Long marady) {
        this.marady = marady;
    }

    public BigDecimal getMaradyRatio() {
        return maradyRatio;
    }

    public void setMaradyRatio(BigDecimal maradyRatio) {
        this.maradyRatio = maradyRatio;
    }

    public BigDecimal getMaradyVal() {
        return maradyVal;
    }

    public void setMaradyVal(BigDecimal maradyVal) {
        this.maradyVal = maradyVal;
    }

    public Long getEsaba() {
        return esaba;
    }

    public void setEsaba(Long esaba) {
        this.esaba = esaba;
    }

    public BigDecimal getEsabaRatio() {
        return esabaRatio;
    }

    public void setEsabaRatio(BigDecimal esabaRatio) {
        this.esabaRatio = esabaRatio;
    }

    public BigDecimal getEsabaVal() {
        return esabaVal;
    }

    public void setEsabaVal(BigDecimal esabaVal) {
        this.esabaVal = esabaVal;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public BigDecimal getWorkerPay() {
        return workerPay;
    }

    public void setWorkerPay(BigDecimal workerPay) {
        this.workerPay = workerPay;
    }

    public BigDecimal getCompanyPay() {
        return companyPay;
    }

    public void setCompanyPay(BigDecimal companyPay) {
        this.companyPay = companyPay;
    }

  
}

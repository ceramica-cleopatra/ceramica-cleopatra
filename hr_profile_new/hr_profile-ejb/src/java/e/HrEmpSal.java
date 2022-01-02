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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_EMP_SAL", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpSal.findAll", query = "SELECT h FROM HrEmpSal h where h.empNo=:emp"),
    @NamedQuery(name = "HrEmpSal.findById", query = "SELECT h FROM HrEmpSal h WHERE h.id = :id"),
    @NamedQuery(name = "HrEmpSal.findByListId", query = "SELECT h FROM HrEmpSal h WHERE h.listId = :listId"),
    @NamedQuery(name = "HrEmpSal.findByEmpNo", query = "SELECT h FROM HrEmpSal h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrEmpSal.findByEmpName", query = "SELECT h FROM HrEmpSal h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrEmpSal.findByBasicSal", query = "SELECT h FROM HrEmpSal h WHERE h.basicSal = :basicSal"),
    @NamedQuery(name = "HrEmpSal.findByVarSal", query = "SELECT h FROM HrEmpSal h WHERE h.varSal = :varSal"),
    @NamedQuery(name = "HrEmpSal.findByTotSal", query = "SELECT h FROM HrEmpSal h WHERE h.totSal = :totSal"),
    @NamedQuery(name = "HrEmpSal.findByWorkerPay", query = "SELECT h FROM HrEmpSal h WHERE h.workerPay = :workerPay"),
    @NamedQuery(name = "HrEmpSal.findByTax", query = "SELECT h FROM HrEmpSal h WHERE h.tax = :tax"),
    @NamedQuery(name = "HrEmpSal.findByPlusValue", query = "SELECT h FROM HrEmpSal h WHERE h.plusValue = :plusValue"),
    @NamedQuery(name = "HrEmpSal.findByCutOff", query = "SELECT h FROM HrEmpSal h WHERE h.cutOff = :cutOff"),
    @NamedQuery(name = "HrEmpSal.findByMCutOff", query = "SELECT h FROM HrEmpSal h WHERE h.mCutOff = :mCutOff"),
    @NamedQuery(name = "HrEmpSal.findByTotOutCome", query = "SELECT h FROM HrEmpSal h WHERE h.totOutCome = :totOutCome"),
    @NamedQuery(name = "HrEmpSal.findByJobName", query = "SELECT h FROM HrEmpSal h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrEmpSal.findByNetValue", query = "SELECT h FROM HrEmpSal h WHERE h.netValue = :netValue"),
    @NamedQuery(name = "HrEmpSal.findByMinusMinutsVal", query = "SELECT h FROM HrEmpSal h WHERE h.minusMinutsVal = :minusMinutsVal"),
    @NamedQuery(name = "HrEmpSal.findByMinusDaysVal", query = "SELECT h FROM HrEmpSal h WHERE h.minusDaysVal = :minusDaysVal"),
    @NamedQuery(name = "HrEmpSal.findByAuthVal", query = "SELECT h FROM HrEmpSal h WHERE h.authVal = :authVal"),
    @NamedQuery(name = "HrEmpSal.findByNotAuthVal", query = "SELECT h FROM HrEmpSal h WHERE h.notAuthVal = :notAuthVal"),
    @NamedQuery(name = "HrEmpSal.findByMaradyVal", query = "SELECT h FROM HrEmpSal h WHERE h.maradyVal = :maradyVal"),
    @NamedQuery(name = "HrEmpSal.findByEsabaVal", query = "SELECT h FROM HrEmpSal h WHERE h.esabaVal = :esabaVal"),
    @NamedQuery(name = "HrEmpSal.findByTotIncome", query = "SELECT h FROM HrEmpSal h WHERE h.totIncome = :totIncome"),
    @NamedQuery(name = "HrEmpSal.findByHafez", query = "SELECT h FROM HrEmpSal h WHERE h.hafez = :hafez"),
    @NamedQuery(name = "HrEmpSal.findBySalesTamyoz", query = "SELECT h FROM HrEmpSal h WHERE h.salesTamyoz = :salesTamyoz"),
    @NamedQuery(name = "HrEmpSal.findByTamyoz", query = "SELECT h FROM HrEmpSal h WHERE h.tamyoz = :tamyoz")})
public class HrEmpSal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "LIST_ID")
    private long listId;
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private long empNo;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "BASIC_SAL")
    private Double basicSal;
    @Column(name = "VAR_SAL")
    private Double varSal;
    @Column(name = "TOT_SAL")
    private Double totSal;
    @Column(name = "WORKER_PAY")
    private Double workerPay;
    @Column(name = "TAX")
    private Double tax;
    @Column(name = "PLUS_VALUE")
    private Double plusValue;
    @Column(name = "CUT_OFF")
    private Double cutOff;
    @Column(name = "M_CUT_OFF")
    private Double mCutOff;
    @Column(name = "TOT_OUT_COME")
    private Double totOutCome;
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "NET_VALUE")
    private Double netValue;
    @Column(name = "MINUS_MINUTS_VAL")
    private Double minusMinutsVal;
    @Column(name = "MINUS_DAYS_VAL")
    private Double minusDaysVal;
    @Column(name = "AUTH_VAL")
    private Double authVal;
    @Column(name = "NOT_AUTH_VAL")
    private Double notAuthVal;
    @Column(name = "MARADY_VAL")
    private Double maradyVal;
    @Column(name = "ESABA_VAL")
    private Double esabaVal;
    @Column(name = "TOT_INCOME")
    private Double totIncome;
    @Column(name = "HAFEZ")
    private Double hafez;
    @Column(name = "SALES_TAMYOZ")
    private Double salesTamyoz;
    @Column(name = "TAMYOZ")
    private Double tamyoz;

    public HrEmpSal() {
    }

    
    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(long empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

   
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getCutOff() {
        return cutOff;
    }

    public void setCutOff(Double cutOff) {
        this.cutOff = cutOff;
    }

    public Double getEsabaVal() {
        return esabaVal;
    }

    public void setEsabaVal(Double esabaVal) {
        this.esabaVal = esabaVal;
    }

    public Double getHafez() {
        return hafez;
    }

    public void setHafez(Double hafez) {
        this.hafez = hafez;
    }

    public Double getmCutOff() {
        return mCutOff;
    }

    public void setmCutOff(Double mCutOff) {
        this.mCutOff = mCutOff;
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

    public Double getNetValue() {
        return netValue;
    }

    public void setNetValue(Double netValue) {
        this.netValue = netValue;
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

    public Double getSalesTamyoz() {
        return salesTamyoz;
    }

    public void setSalesTamyoz(Double salesTamyoz) {
        this.salesTamyoz = salesTamyoz;
    }

    public Double getTamyoz() {
        return tamyoz;
    }

    public void setTamyoz(Double tamyoz) {
        this.tamyoz = tamyoz;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotIncome() {
        return totIncome;
    }

    public void setTotIncome(Double totIncome) {
        this.totIncome = totIncome;
    }

    public Double getTotOutCome() {
        return totOutCome;
    }

    public void setTotOutCome(Double totOutCome) {
        this.totOutCome = totOutCome;
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

   

}

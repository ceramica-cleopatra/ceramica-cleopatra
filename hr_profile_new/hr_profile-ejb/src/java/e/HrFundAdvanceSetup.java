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
 * @author a.abbas
 */
@Entity
@Table(name = "HR_FUND_ADVANCE_SETUP", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrFundAdvanceSetup.findAll", query = "SELECT h FROM HrFundAdvanceSetup h"),
    @NamedQuery(name = "HrFundAdvanceSetup.findByResponsibleCode", query = "SELECT h FROM HrFundAdvanceSetup h WHERE h.responsibleCode = :responsibleCode"),
    @NamedQuery(name = "HrFundAdvanceSetup.findByMaxAmount", query = "SELECT h FROM HrFundAdvanceSetup h WHERE h.maxAmount = :maxAmount"),
    @NamedQuery(name = "HrFundAdvanceSetup.findByFromDay", query = "SELECT h FROM HrFundAdvanceSetup h WHERE h.fromDay = :fromDay"),
    @NamedQuery(name = "HrFundAdvanceSetup.findByToDay", query = "SELECT h FROM HrFundAdvanceSetup h WHERE h.toDay = :toDay"),
    @NamedQuery(name = "HrFundAdvanceSetup.findByChkMaxAmount", query = "SELECT h FROM HrFundAdvanceSetup h WHERE h.chkMaxAmount = :chkMaxAmount"),
    @NamedQuery(name = "HrFundAdvanceSetup.findByChkEmpHirePeriod", query = "SELECT h FROM HrFundAdvanceSetup h WHERE h.chkEmpHirePeriod = :chkEmpHirePeriod"),
    @NamedQuery(name = "HrFundAdvanceSetup.findByChkAdvanceNotPaied", query = "SELECT h FROM HrFundAdvanceSetup h WHERE h.chkAdvanceNotPaied = :chkAdvanceNotPaied"),
    @NamedQuery(name = "HrFundAdvanceSetup.findByChkAdvanceNotPaiedG", query = "SELECT h FROM HrFundAdvanceSetup h WHERE h.chkAdvanceNotPaiedG = :chkAdvanceNotPaiedG")})
public class HrFundAdvanceSetup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "RESPONSIBLE_CODE")
    private Long responsibleCode;
    @Column(name = "MAX_AMOUNT")
    private Long maxAmount;
    @Column(name = "FROM_DAY")
    private Long fromDay;
    @Column(name = "TO_DAY")
    private Long toDay;
    @Column(name = "CHK_MAX_AMOUNT")
    private Character chkMaxAmount;
    @Column(name = "CHK_EMP_HIRE_PERIOD")
    private Character chkEmpHirePeriod;
    @Column(name = "CHK_ADVANCE_NOT_PAIED")
    private Character chkAdvanceNotPaied;
    @Column(name = "CHK_ADVANCE_NOT_PAIED_G")
    private Character chkAdvanceNotPaiedG;
    @Column(name = "RESPONSIBLE2")
    private Long responsible2;
    @Column(name = "RESPONSIBLE3")
    private Long responsible3;
    public HrFundAdvanceSetup() {
    }

    public Long getFromDay() {
        return fromDay;
    }

    public void setFromDay(Long fromDay) {
        this.fromDay = fromDay;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Long getResponsibleCode() {
        return responsibleCode;
    }

    public void setResponsibleCode(Long responsibleCode) {
        this.responsibleCode = responsibleCode;
    }

    public Long getToDay() {
        return toDay;
    }

    public void setToDay(Long toDay) {
        this.toDay = toDay;
    }

   

    public Character getChkMaxAmount() {
        return chkMaxAmount;
    }

    public void setChkMaxAmount(Character chkMaxAmount) {
        this.chkMaxAmount = chkMaxAmount;
    }

    public Character getChkEmpHirePeriod() {
        return chkEmpHirePeriod;
    }

    public void setChkEmpHirePeriod(Character chkEmpHirePeriod) {
        this.chkEmpHirePeriod = chkEmpHirePeriod;
    }

    public Character getChkAdvanceNotPaied() {
        return chkAdvanceNotPaied;
    }

    public void setChkAdvanceNotPaied(Character chkAdvanceNotPaied) {
        this.chkAdvanceNotPaied = chkAdvanceNotPaied;
    }

    public Character getChkAdvanceNotPaiedG() {
        return chkAdvanceNotPaiedG;
    }

    public void setChkAdvanceNotPaiedG(Character chkAdvanceNotPaiedG) {
        this.chkAdvanceNotPaiedG = chkAdvanceNotPaiedG;
    }

    public Long getResponsible2() {
        return responsible2;
    }

    public void setResponsible2(Long responsible2) {
        this.responsible2 = responsible2;
    }

    public Long getResponsible3() {
        return responsible3;
    }

    public void setResponsible3(Long responsible3) {
        this.responsible3 = responsible3;
    }

    

}

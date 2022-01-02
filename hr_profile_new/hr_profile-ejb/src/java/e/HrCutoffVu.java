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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_CUTOFF_VU", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCutoffVu.findAll", query = "SELECT sum(h.amount) FROM HrCutoffVu h where h.month=:m and h.year=:y and h.empId=:emp"),
    @NamedQuery(name = "HrCutoffVu.findAllCutoff", query = "SELECT h FROM HrCutoffVu h where h.month=:m and h.year=:y and h.empId=:emp"),
    @NamedQuery(name = "HrCutoffVu.findById", query = "SELECT h FROM HrCutoffVu h WHERE h.id = :id"),
    @NamedQuery(name = "HrCutoffVu.findByAmount", query = "SELECT h FROM HrCutoffVu h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrCutoffVu.findByMonth", query = "SELECT h FROM HrCutoffVu h WHERE h.month = :month"),
    @NamedQuery(name = "HrCutoffVu.findByYear", query = "SELECT h FROM HrCutoffVu h WHERE h.year = :year")})
public class HrCutoffVu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "MONTH")
    private Long month;
    @Column(name = "YEAR")
    private Long year;
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCutoffType hrCutoffType;

    public HrCutoffVu() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

   
    public HrCutoffType getHrCutoffType() {
        return hrCutoffType;
    }

    public void setHrCutoffType(HrCutoffType hrCutoffType) {
        this.hrCutoffType = hrCutoffType;
    }

}

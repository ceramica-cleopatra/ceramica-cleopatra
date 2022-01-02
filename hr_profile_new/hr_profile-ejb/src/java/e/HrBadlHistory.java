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
@Table(name = "HR_BADL_HISTORY", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrBadlHistory.findAll", query = "SELECT h FROM HrBadlHistory h where h.trnsMonth=:m and h.trnsYear=:y and h.amount>0 and h.empId=:emp"),
    @NamedQuery(name = "HrBadlHistory.findByEmpId", query = "SELECT h FROM HrBadlHistory h WHERE h.empId = :empId"),
    @NamedQuery(name = "HrBadlHistory.findByTrnsMonth", query = "SELECT h FROM HrBadlHistory h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrBadlHistory.findByTrnsYear", query = "SELECT h FROM HrBadlHistory h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrBadlHistory.findByAmount", query = "SELECT h FROM HrBadlHistory h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrBadlHistory.findByTypeId", query = "SELECT h FROM HrBadlHistory h WHERE h.typeId = :typeId"),
    @NamedQuery(name = "HrBadlHistory.findByListId", query = "SELECT h FROM HrBadlHistory h WHERE h.listId = :listId")})
public class HrBadlHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Id
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "TRNS_MONTH")
    private Long trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Long trnsYear;
    @Column(name = "AMOUNT")
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private HrBadlType typeId;
    @Column(name = "LIST_ID")
    private Long listId;

    public HrBadlHistory() {
    }

   

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public HrBadlType getTypeId() {
        return typeId;
    }

    public void setTypeId(HrBadlType typeId) {
        this.typeId = typeId;
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

   
    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

   

  
}

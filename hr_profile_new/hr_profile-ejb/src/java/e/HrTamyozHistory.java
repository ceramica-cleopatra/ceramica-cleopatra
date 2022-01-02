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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_TAMYOZ_HISTORY", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrTamyozHistory.findAll", query = "SELECT sum(h.amount) FROM HrTamyozHistory h where h.trnsMonth=:m and h.trnsYear=:y and h.empId=:emp group by h.empId"),
    @NamedQuery(name = "HrTamyozHistory.salesTamyoz", query = "SELECT sum(h.amount) FROM HrTamyozHistory h where h.typeId=4 and  h.trnsMonth=:m and h.trnsYear=:y and h.empId=:emp group by h.empId"),
    @NamedQuery(name = "HrTamyozHistory.otherTamyoz", query = "SELECT sum(h.amount) FROM HrTamyozHistory h where h.typeId<>4 and  h.trnsMonth=:m and h.trnsYear=:y and h.empId=:emp group by h.empId"),
    @NamedQuery(name = "HrTamyozHistory.findById", query = "SELECT h FROM HrTamyozHistory h WHERE h.id = :id"),
    @NamedQuery(name = "HrTamyozHistory.findByTypeId", query = "SELECT h FROM HrTamyozHistory h WHERE h.typeId = :typeId"),
    @NamedQuery(name = "HrTamyozHistory.findByEmpId", query = "SELECT h FROM HrTamyozHistory h WHERE h.empId = :empId"),
    @NamedQuery(name = "HrTamyozHistory.findByTrnsMonth", query = "SELECT h FROM HrTamyozHistory h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrTamyozHistory.findByTrnsYear", query = "SELECT h FROM HrTamyozHistory h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrTamyozHistory.findByAmount", query = "SELECT h FROM HrTamyozHistory h WHERE h.amount = :amount"),
    @NamedQuery(name = "HrTamyozHistory.findByListId", query = "SELECT h FROM HrTamyozHistory h WHERE h.listId = :listId")})
public class HrTamyozHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HrTamyozHistoryPK hrTamyozHistoryPK;
    @Column(name = "ID")
    private Long id;
    @Column(name = "TYPE_ID")
    private Long typeId;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "TRNS_MONTH")
    private Short trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Short trnsYear;
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "LIST_ID")
    private Long listId;

    public HrTamyozHistory() {
    }

    public HrTamyozHistory(HrTamyozHistoryPK hrTamyozHistoryPK) {
        this.hrTamyozHistoryPK = hrTamyozHistoryPK;
    }

    public HrTamyozHistoryPK getHrTamyozHistoryPK() {
        return hrTamyozHistoryPK;
    }

    public void setHrTamyozHistoryPK(HrTamyozHistoryPK hrTamyozHistoryPK) {
        this.hrTamyozHistoryPK = hrTamyozHistoryPK;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Short getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(Short trnsMonth) {
        this.trnsMonth = trnsMonth;
    }

    public Short getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Short trnsYear) {
        this.trnsYear = trnsYear;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }



    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hrTamyozHistoryPK != null ? hrTamyozHistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrTamyozHistory)) {
            return false;
        }
        HrTamyozHistory other = (HrTamyozHistory) object;
        if ((this.hrTamyozHistoryPK == null && other.hrTamyozHistoryPK != null) || (this.hrTamyozHistoryPK != null && !this.hrTamyozHistoryPK.equals(other.hrTamyozHistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrTamyozHistory[hrTamyozHistoryPK=" + hrTamyozHistoryPK + "]";
    }

}

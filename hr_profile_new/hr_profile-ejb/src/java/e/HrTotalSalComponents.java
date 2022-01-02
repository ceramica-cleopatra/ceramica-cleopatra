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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_TOTAL_SAL_COMPONENTS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrTotalSalComponents.findAll", query = "SELECT h FROM HrTotalSalComponents h"),
    @NamedQuery(name = "HrTotalSalComponents.findById", query = "SELECT h FROM HrTotalSalComponents h WHERE h.id = :id"),
    @NamedQuery(name = "HrTotalSalComponents.findByEmpId", query = "SELECT h FROM HrTotalSalComponents h WHERE h.empId = :empId and h.oldType<>'حافز ثابت'"),
    @NamedQuery(name = "HrTotalSalComponents.findFixedHafezByEmpId", query = "SELECT h FROM HrTotalSalComponents h WHERE h.empId = :empId and h.oldType='حافز ثابت'"),
    @NamedQuery(name = "HrTotalSalComponents.findByOldSal", query = "SELECT h FROM HrTotalSalComponents h WHERE h.oldSal = :oldSal"),
    @NamedQuery(name = "HrTotalSalComponents.findByOldType", query = "SELECT h FROM HrTotalSalComponents h WHERE h.oldType = :oldType")})
public class HrTotalSalComponents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "OLD_SAL")
    private Double oldSal;
    @Column(name = "OLD_TYPE")
    private String oldType;

    public HrTotalSalComponents() {
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

    public Double getOldSal() {
        return oldSal;
    }

    public void setOldSal(Double oldSal) {
        this.oldSal = oldSal;
    }

   
    public String getOldType() {
        return oldType;
    }

    public void setOldType(String oldType) {
        this.oldType = oldType;
    }

}

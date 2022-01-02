/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
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
 * @author a.abbas
 */
@Entity
@Table(name = "EMP_QTY_TRGET_YEAR_MV", catalog = "", schema = "CRMK")
 @NamedQueries({
    @NamedQuery(name = "EmpQtyTrgetYearMv.findAll", query = "SELECT e FROM EmpQtyTrgetYearMv e"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findAllSalesMenInBrn", query = "SELECT e FROM EmpQtyTrgetYearMv e  WHERE e.brnId.id=:brn_id and e.months=:month order by e.net desc"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findEmpTarget", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.empNo.empNo = :emp_no and e.months=:months ORDER BY e.net DESC"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByHrId", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.empNo = :hrId"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByTarget", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.target = :target"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByNetC", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.netC = :netC"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByQtyC", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.qtyC = :qtyC"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByNetD", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.netD = :netD"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByQtyD", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.qtyD = :qtyD"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByNetS", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.netS = :netS"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByQtyS", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.qtyS = :qtyS"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByNet", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.net = :net"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByQty", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.qty = :qty"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByBrnTarget", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.brnTarget = :brnTarget"),
    @NamedQuery(name = "EmpQtyTrgetYearMv.findByMonths", query = "SELECT e FROM EmpQtyTrgetYearMv e WHERE e.months = :months")})
public class EmpQtyTrgetYearMv implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "EMP_NO",name = "HR_ID")
    private HrEmpInfo empNo;
    @Column(name = "MONTHS")
    private String months;
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID",name = "BRN_ID")
    private CrmkBranch brnId;
    @Column(name = "TARGET")
    private Long target;
    @Column(name = "NET_C")
    private Long netC;
    @Column(name = "NET_D")
    private Long netD;
    @Column(name = "NET_S")
    private Long netS;
    @Column(name = "QTY_C")
    private Long qtyC;
    @Column(name = "QTY_D")
    private Long qtyD;
    @Column(name = "QTY_S")
    private Long qtyS;
    @Column(name = "NET")
    private Long net;
    @Column(name = "QTY")
    private Long qty;
    @Basic(optional = false)
    @Column(name = "BRN_TARGET")
    private long brnTarget;

    public EmpQtyTrgetYearMv() {
    }

   
   
    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public CrmkBranch getBrnId() {
        return brnId;
    }

    public void setBrnId(CrmkBranch brnId) {
        this.brnId = brnId;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    public Long getNet() {
        return net;
    }

    public void setNet(Long net) {
        this.net = net;
    }

    public Long getNetC() {
        return netC;
    }

    public void setNetC(Long netC) {
        this.netC = netC;
    }

    public Long getNetD() {
        return netD;
    }

    public void setNetD(Long netD) {
        this.netD = netD;
    }

    public Long getNetS() {
        return netS;
    }

    public void setNetS(Long netS) {
        this.netS = netS;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getQtyC() {
        return qtyC;
    }

    public void setQtyC(Long qtyC) {
        this.qtyC = qtyC;
    }

    public Long getQtyD() {
        return qtyD;
    }

    public void setQtyD(Long qtyD) {
        this.qtyD = qtyD;
    }

    public Long getQtyS() {
        return qtyS;
    }

    public void setQtyS(Long qtyS) {
        this.qtyS = qtyS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

 

    public long getBrnTarget() {
        return brnTarget;
    }

    public void setBrnTarget(long brnTarget) {
        this.brnTarget = brnTarget;
    }


}

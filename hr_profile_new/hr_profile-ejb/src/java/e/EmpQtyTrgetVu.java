/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "EMP_QTY_TRGET_MV" ,schema = "CRMK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpQtyTrgetVu.findOthersInBrn", query = "select count(o) from EmpQtyTrgetVu o where o.empNo.empNo<>:emp_no and o.trgtPercent >:x and o.brnId.id=:brn_id "),
    @NamedQuery(name = "EmpQtyTrgetVu.findEmp", query = "select o from EmpQtyTrgetVu o where o.empNo=:emp_no"),
    @NamedQuery(name = "EmpQtyTrgetVu.findOthers", query = "SELECT COUNT(o) from EmpQtyTrgetVu o where o.trgtPercent >(select t.trgtPercent from EmpQtyTrgetVu t WHERE t.empNo.empNo=:emp_no and t.brnId.id=:brn_id)"),
    @NamedQuery(name = "EmpQtyTrgetVu.findAllInBrn", query = "select count(o) from EmpQtyTrgetVu o where o.brnId.id=:brn_id"),
    @NamedQuery(name = "EmpQtyTrgetVu.findAll", query = "SELECT h FROM EmpQtyTrgetVu h where h.brnId.id=:brn_id order by h.net desc"),
    @NamedQuery(name = "EmpQtyTrgetVu.findById", query = "SELECT h FROM EmpQtyTrgetVu h WHERE h.id = :id"),
    @NamedQuery(name = "EmpQtyTrgetVu.findByBrnId", query = "SELECT h FROM EmpQtyTrgetVu h WHERE h.brnId = :brnId"),
    @NamedQuery(name = "EmpQtyTrgetVu.findByNet", query = "SELECT h FROM EmpQtyTrgetVu h WHERE h.net = :net"),
    @NamedQuery(name = "EmpQtyTrgetVu.findByTrgt", query = "SELECT h FROM EmpQtyTrgetVu h WHERE h.trgt = :trgt"),
    @NamedQuery(name = "EmpQtyTrgetVu.findByEmpNo", query = "SELECT h FROM EmpQtyTrgetVu h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "EmpQtyTrgetVu.findByTrgtPercent", query = "SELECT h FROM EmpQtyTrgetVu h WHERE h.trgtPercent = :trgtPercent")})
public class EmpQtyTrgetVu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "EMP_NO",name = "HR_ID")
    private HrEmpInfo empNo;
    @Column(name = "TARGET")
    private Long trgt;
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID",name = "BRN_ID")
    private CrmkBranch brnId;
    @Column(name = "LOC_ID")
    private Long locId;
    @Column(name = "NET_C")
    private Long netC;
    @Column(name = "QTY_C")
    private Long qtyC;
    @Column(name = "NET_D")
    private Long netD;
    @Column(name = "QTY_D")
    private Long qtyD;
    @Column(name = "NET_S")
    private Long netS;
    @Column(name = "QTY_S")
    private Long qtyS;
    @Column(name = "NET")
    private Long net;
    @Column(name = "QTY")
    private Long qty;
    @Column(name = "TRGT_PERCENT")
    private Long trgtPercent;

    public EmpQtyTrgetVu() {
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    public CrmkBranch getBrnId() {
        return brnId;
    }

    public void setBrnId(CrmkBranch brnId) {
        this.brnId = brnId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTrgt() {
        return trgt;
    }

    public void setTrgt(Long trgt) {
        this.trgt = trgt;
    }

    public Long getTrgtPercent() {
        return trgtPercent;
    }

    public void setTrgtPercent(Long trgtPercent) {
        this.trgtPercent = trgtPercent;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }



}

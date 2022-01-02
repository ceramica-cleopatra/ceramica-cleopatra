/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "CRMK_F2B_ME2B_NOT_CLOSED", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findAll", query = "SELECT c FROM CrmkF2bMe2bNotClosed c"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.chk", query = "SELECT count(c) FROM CrmkF2bMe2bNotClosed c where c.empNo=:emp_no"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findById", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findByF2meId", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.f2meId = :f2meId"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findByTyp", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.typ = :typ"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findByNo", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.no = :no"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findByBrnId", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.brnId = :brnId"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findByTrnsDate", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findByFactoryStore", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.factoryStore = :factoryStore"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findByQty", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.qty = :qty"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findByEmpNo", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.empNo = :emp_no"),
    @NamedQuery(name = "CrmkF2bMe2bNotClosed.findByEmpName", query = "SELECT c FROM CrmkF2bMe2bNotClosed c WHERE c.empName = :empName")})
public class CrmkF2bMe2bNotClosed implements Serializable {
    @Id
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "F2ME_ID")
    private Long f2meId;
    @Column(name = "TYP")
    private Character typ;
    @Column(name = "NO")
    private String no;
    @Column(name = "BRN_ID")
    private Long brnId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "FACTORY_STORE")
    private String factoryStore;
    @Column(name = "QTY")
    private BigInteger qty;
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private long empNo;
    @Column(name = "EMP_NAME")
    private String empName;

    public CrmkF2bMe2bNotClosed() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Long getF2meId() {
        return f2meId;
    }

    public void setF2meId(Long f2meId) {
        this.f2meId = f2meId;
    }

    public Character getTyp() {
        return typ;
    }

    public void setTyp(Character typ) {
        this.typ = typ;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getFactoryStore() {
        return factoryStore;
    }

    public void setFactoryStore(String factoryStore) {
        this.factoryStore = factoryStore;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
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

}

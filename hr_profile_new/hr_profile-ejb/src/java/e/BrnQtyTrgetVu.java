/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
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
 * @author ahmed
 */
@Entity
@Table(name = "BRN_QTY_TRGET_MV", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "BrnQtyTrgetVu.findAll", query = "SELECT b FROM BrnQtyTrgetVu b order by b.percent desc"),
    @NamedQuery(name = "BrnQtyTrgetVu.findById", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.id = :id"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByBrnId", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.brnId = :brn_id"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByLocId", query = "SELECT b FROM BrnQtyTrgetVu b,HrLocation loc WHERE b.brnId=loc.crmkId and loc.hrLocationType.id =1 and loc.id = :brn_id"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByNetC", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.netC = :netC"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByNetD", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.netD = :netD"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByNetS", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.netS = :netS"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByQtyC", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.qtyC = :qtyC"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByQtyD", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.qtyD = :qtyD"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByQtyS", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.qtyS = :qtyS"),
    @NamedQuery(name = "BrnQtyTrgetVu.findOrdr", query = "SELECT count(b) FROM BrnQtyTrgetVu b WHERE b.percent>:percent"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByQty", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.qty = :qty"),
    @NamedQuery(name = "BrnQtyTrgetVu.findByNet", query = "SELECT b FROM BrnQtyTrgetVu b WHERE b.net = :net")})
public class BrnQtyTrgetVu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "BRN_TARGET")
    private long brnTarget;
    @Column(name = "BRN_ID")
    private Long brnId;
    @ManyToOne
    @JoinColumn(referencedColumnName="ID",name = "LOC_ID")
    private HrLocation locId;
    @Column(name = "NET_C")
    private Long netC;
    @Column(name = "TRGT_PERCENT")
    private Long percent;
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
    @Column(name = "QTY")
    private Long qty;
    @Column(name = "NET")
    private Long net;

    public BrnQtyTrgetVu() {
    }

    public long getBrnTarget() {
        return brnTarget;
    }

    public void setBrnTarget(long brnTarget) {
        this.brnTarget = brnTarget;
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

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public HrLocation getLocId() {
        return locId;
    }

    public void setLocId(HrLocation locId) {
        this.locId = locId;
    }

   
}

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
@Table(name = "BRN_QTY_TRGET_YEAR_MV", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "BrnQtyTrgetYearVu.findAll", query = "SELECT b FROM BrnQtyTrgetYearVu b"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByBrnIdAndMonths", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.locId.id = :brn_id and b.months=:months and b.years=:year"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findOrdr", query = "SELECT count(b) FROM BrnQtyTrgetYearVu b WHERE b.trgtPercent > :trgt_percent and b.months=:month and  b.years=:year"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByBrnTarget", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.brnTarget = :brnTarget"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByNetC", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.netC = :netC"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByNetD", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.netD = :netD"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByNetS", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.netS = :netS"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByQtyC", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.qtyC = :qtyC"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByQtyD", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.qtyD = :qtyD"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByQtyS", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.qtyS = :qtyS"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByQty", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.qty = :qty"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByNet", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.net = :net"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByTrgtPercent", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.trgtPercent = :trgtPercent"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByMonths", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.months = :month and b.years=:year order by b.trgtPercent desc"),
    @NamedQuery(name = "BrnQtyTrgetYearVu.findByLocId", query = "SELECT b FROM BrnQtyTrgetYearVu b WHERE b.locId = :locId")})
public class BrnQtyTrgetYearVu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "BRN_ID")
    private Long brnId;
    @Column(name = "BRN_TARGET")
    private Long brnTarget;
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
    @Column(name = "QTY")
    private Long qty;
    @Column(name = "NET")
    private Long net;
    @Column(name = "TRGT_PERCENT")
    private Long trgtPercent;
    @Column(name = "MONTHS")
    private String months;
    @Column(name = "YEARS")
    private String years;
    @ManyToOne
    @JoinColumn(referencedColumnName="ID",name = "LOC_ID")
    private HrLocation locId;

    public BrnQtyTrgetYearVu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrLocation getLocId() {
        return locId;
    }

    public void setLocId(HrLocation locId) {
        this.locId = locId;
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

    public Long getTrgtPercent() {
        return trgtPercent;
    }

    public void setTrgtPercent(Long trgtPercent) {
        this.trgtPercent = trgtPercent;
    }

    public Long getBrnTarget() {
        return brnTarget;
    }

    public void setBrnTarget(Long brnTarget) {
        this.brnTarget = brnTarget;
    }


    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }



}

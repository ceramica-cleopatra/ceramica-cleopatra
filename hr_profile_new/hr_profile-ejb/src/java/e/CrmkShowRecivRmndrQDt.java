/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CRMK_SHOW_RECIV_RMNDR_Q_DT", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findAll", query = "SELECT c FROM CrmkShowRecivRmndrQDt c where c.brnIp=:ip and (c.recivEmp is null or c.rmndrEmp is null)"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByHdId", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.hdId.id = :hdId"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByDtId", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.dtId = :dtId"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByTrnsDate", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByNo", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.no = :no"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByHandNo", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.handNo = :handNo"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByStore", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.store = :store"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByStoreId", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.storeId = :storeId"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByOrdrNo", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.ordrNo = :ordrNo"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByOrdrHandNo", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.ordrHandNo = :ordrHandNo"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByRecivEmp", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.recivEmp = :recivEmp"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByRmndrEmp", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.rmndrEmp = :rmndrEmp"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByItemCode", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.itemCode = :itemCode"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByMe2ordrQty", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.me2ordrQty = :me2ordrQty"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByRecivQty", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.recivQty = :recivQty"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByRmndrQty", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.rmndrQty = :rmndrQty"),
    @NamedQuery(name = "CrmkShowRecivRmndrQDt.findByCrmkSehy", query = "SELECT c FROM CrmkShowRecivRmndrQDt c WHERE c.crmkSehy = :crmkSehy")})
public class CrmkShowRecivRmndrQDt implements Serializable {
    
    @Id
    @Column(name = "DT_ID")
    private Long dtId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "NO")
    private String no;
    @Column(name = "HAND_NO")
    private String handNo;
    @Column(name = "STORE")
    private String store;
    @Column(name = "STORE_ID")
    private Long storeId;
    @Column(name = "ORDR_NO")
    private BigInteger ordrNo;
    @Column(name = "ORDR_HAND_NO")
    private String ordrHandNo;
    @Column(name = "RECIV_EMP")
    private BigInteger recivEmp;
    @Column(name = "RMNDR_EMP")
    private BigInteger rmndrEmp;
    @Column(name = "ITEM_CODE")
    private String itemCode;
    @Column(name = "ME2ORDR_QTY")
    private Double me2ordrQty;
    @Column(name = "RECIV_QTY")
    private Double recivQty;
    @Column(name = "RMNDR_QTY")
    private Double rmndrQty;
    @Column(name = "CRMK_SEHY")
    private Character crmkSehy;
    @Column(name = "BRN_IP")
    private Character brnIp;
    @ManyToOne
    @JoinColumn(name = "HD_ID")
    private CrmkShowRecivRmndrQHd hdId;
    public CrmkShowRecivRmndrQDt() {
    }

    public CrmkShowRecivRmndrQHd getHdId() {
        return hdId;
    }

    public void setHdId(CrmkShowRecivRmndrQHd hdId) {
        this.hdId = hdId;
    }

    

    public Long getDtId() {
        return dtId;
    }

    public void setDtId(Long dtId) {
        this.dtId = dtId;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getHandNo() {
        return handNo;
    }

    public void setHandNo(String handNo) {
        this.handNo = handNo;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public BigInteger getOrdrNo() {
        return ordrNo;
    }

    public void setOrdrNo(BigInteger ordrNo) {
        this.ordrNo = ordrNo;
    }

    public String getOrdrHandNo() {
        return ordrHandNo;
    }

    public void setOrdrHandNo(String ordrHandNo) {
        this.ordrHandNo = ordrHandNo;
    }

    public BigInteger getRecivEmp() {
        return recivEmp;
    }

    public void setRecivEmp(BigInteger recivEmp) {
        this.recivEmp = recivEmp;
    }

    public BigInteger getRmndrEmp() {
        return rmndrEmp;
    }

    public void setRmndrEmp(BigInteger rmndrEmp) {
        this.rmndrEmp = rmndrEmp;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getMe2ordrQty() {
        return me2ordrQty;
    }

    public void setMe2ordrQty(Double me2ordrQty) {
        this.me2ordrQty = me2ordrQty;
    }

    public Double getRecivQty() {
        
        return recivQty;
    }

    public void setRecivQty(Double recivQty) {
        this.recivQty = recivQty;
    }

    public Double getRmndrQty() {
        return rmndrQty;
    }

    public void setRmndrQty(Double rmndrQty) {
        this.rmndrQty = rmndrQty;
    }

    public Character getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(Character crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public Character getBrnIp() {
        return brnIp;
    }

    public void setBrnIp(Character brnIp) {
        this.brnIp = brnIp;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "CRMK_SHOW_RECIV_RMNDR_Q_HD", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findAllReciev", query = "SELECT c FROM CrmkShowRecivRmndrQHd c where c.brnIp=:ip and exists (select x from c.crmkShowRecivRmndrQDtList x where x.recivQty is null)"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findAllRmndr", query = "SELECT c FROM CrmkShowRecivRmndrQHd c where c.brnIp=:ip and exists (select x from c.crmkShowRecivRmndrQDtList x where x.rmndrQty is null)"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findById", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByTrnsDate", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByNo", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.no = :no"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByHandNo", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.handNo = :handNo"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByStore", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.store = :store"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByStoreId", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.storeId = :storeId"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByOrdrNo", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.ordrNo = :ordrNo"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByOrdrHandNo", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.ordrHandNo = :ordrHandNo"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByRecivEmp", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.recivEmp = :recivEmp"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByRmndrEmp", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.rmndrEmp = :rmndrEmp"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByCrmkSehy", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.crmkSehy = :crmkSehy"),
    @NamedQuery(name = "CrmkShowRecivRmndrQHd.findByBrnIp", query = "SELECT c FROM CrmkShowRecivRmndrQHd c WHERE c.brnIp = :brnIp")})
public class CrmkShowRecivRmndrQHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
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
    @Column(name = "CRMK_SEHY")
    private Character crmkSehy;
    @Column(name = "BRN_IP")
    private String brnIp;
    @OneToMany(fetch=FetchType.EAGER,mappedBy="hdId")
    private List<CrmkShowRecivRmndrQDt> crmkShowRecivRmndrQDtList;

    public List<CrmkShowRecivRmndrQDt> getCrmkShowRecivRmndrQDtList() {
        return crmkShowRecivRmndrQDtList;
    }

    public void setCrmkShowRecivRmndrQDtList(List<CrmkShowRecivRmndrQDt> crmkShowRecivRmndrQDtList) {
        this.crmkShowRecivRmndrQDtList = crmkShowRecivRmndrQDtList;
    }

    

    public CrmkShowRecivRmndrQHd() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Character getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(Character crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public String getBrnIp() {
        return brnIp;
    }

    public void setBrnIp(String brnIp) {
        this.brnIp = brnIp;
    }

}

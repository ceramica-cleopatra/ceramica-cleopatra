/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
@Table(name = "DMS_TRNS_ORDR_DT", catalog = "", schema = "DMS")
@NamedQueries({
    @NamedQuery(name = "DmsTrnsOrdrDt.findAll", query = "SELECT d FROM DmsTrnsOrdrDt d where d.brnId=:brn_id and d.ordrNo=:ordr_no "
    + "and (d.governName=:govern_name or :govern_name is null) and (d.regionName=:region_name or :region_name is null) and (d.mobile=:mobile or :mobile is null)"
    + " and (d.tel1=:tel1 or :tel1 is null) and (d.clntName like :clnt_name or :clnt_name is null) and (d.address like :address or :address is null)"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findById", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.id = :id"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByBrnId", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.brnId = :brnId"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByOrdrNo", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.ordrNo = :ordrNo"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByClntName", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.clntName = :clntName"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByAddress", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.address = :address"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByCityId", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.cityId = :cityId"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByGovernName", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.governName = :governName"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByAreaId", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.areaId = :areaId"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByRegionName", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.regionName = :regionName"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByMobile", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.mobile = :mobile"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByTel1", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.tel1 = :tel1"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByListPrice", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.listPrice = :listPrice"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findBySQty", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.sQty = :sQty"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByPQty", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.pQty = :pQty"),
    @NamedQuery(name = "DmsTrnsOrdrDt.findByMQty", query = "SELECT d FROM DmsTrnsOrdrDt d WHERE d.mQty = :mQty")})
public class DmsTrnsOrdrDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "BRN_ID")
    private Long brnId;
    @Basic(optional = false)
    @Column(name = "ORDR_NO")
    private Long ordrNo;
    @Basic(optional = false)
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @Column(name = "CITY_ID")
    private Long cityId;
    @Column(name = "GOVERN_NAME")
    private String governName;
    @Basic(optional = false)
    @Column(name = "AREA_ID")
    private Long areaId;
    @Column(name = "REGION_NAME")
    private String regionName;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "TEL1")
    private String tel1;
    @Column(name = "LIST_PRICE")
    private Double listPrice;
    @Column(name = "S_QTY")
    private Double sQty;
    @Column(name = "P_QTY")
    private Double pQty;
    @Column(name = "M_QTY")
    private Double mQty;
    @Column(name = "HD_ID")
    private Long hdId;
    @Column(name="RMN_DISCOUNT")
    private Double rmnDiscount;
    @Column(name = "BRINGS")
    private String brings;
    @Column(name = "MIN_PRICE")
    private Long minPrice;
    @Column(name = "PRICE_PAYED")
    private Double pricePayed;
    @Column(name = "DISCOUNT")
    private Double discount;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    public DmsTrnsOrdrDt() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public String getGovernName() {
        return governName;
    }

    public void setGovernName(String governName) {
        this.governName = governName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

   

    public Double getmQty() {
        return mQty;
    }

    public void setmQty(Double mQty) {
        this.mQty = mQty;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getOrdrNo() {
        return ordrNo;
    }

    public void setOrdrNo(Long ordrNo) {
        this.ordrNo = ordrNo;
    }

    public Double getpQty() {
        return pQty;
    }

    public void setpQty(Double pQty) {
        this.pQty = pQty;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Double getsQty() {
        return sQty;
    }

    public void setsQty(Double sQty) {
        this.sQty = sQty;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public Long getHdId() {
        return hdId;
    }

    public void setHdId(Long hdId) {
        this.hdId = hdId;
    }

    public Double getRmnDiscount() {
        return rmnDiscount;
    }

    public void setRmnDiscount(Double rmnDiscount) {
        this.rmnDiscount = rmnDiscount;
    }

    public String getBrings() {
        return brings;
    }

    public void setBrings(String brings) {
        this.brings = brings;
    }

    public Double getPricePayed() {
        return pricePayed;
    }

    public void setPricePayed(Double pricePayed) {
        this.pricePayed = pricePayed;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

}

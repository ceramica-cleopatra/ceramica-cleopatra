/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "DMS_TRANSPORT_ORDR_HD", catalog = "", schema = "DMS")
@NamedQueries({
    @NamedQuery(name = "DmsTransportOrdrHd.findAll", query = "SELECT d FROM DmsTransportOrdrHd d where d.managerId=:manager_id and d.receiptVoucher='Y'"),
    @NamedQuery(name = "DmsTransportOrdrHd.findById", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.id = :id"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByBrnId", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.brnId = :brnId"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByOrdrNo", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.ordrNo = :ordrNo"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByClntName", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.clntName = :clntName"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByCityId", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.cityId = :cityId"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByAreaId", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.areaId = :areaId"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByTel1", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.tel1 = :tel1"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByTel2", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.tel2 = :tel2"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByMobile", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.mobile = :mobile"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByPrice", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.price = :price"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByPricePayed", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.pricePayed = :pricePayed"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByPriceRmn", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.priceRmn = :priceRmn"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByNotes", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.notes = :notes"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByTrnsDate", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.trnsDate = :trnsDate"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByPrdId", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.prdId = :prdId"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByAddress", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.address = :address"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByDeliveryDate", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByFamilyName", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.familyName = :familyName"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByPhoneCode", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.phoneCode = :phoneCode"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByCanceled", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.canceled = :canceled"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByListPrice", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.listPrice = :listPrice"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByTimeFrom", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.timeFrom = :timeFrom"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByTimeTo", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.timeTo = :timeTo"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByPrinted", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.printed = :printed"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByBackNo", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.backNo = :backNo"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByLoadPlace", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.loadPlace = :loadPlace"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByBrings", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.brings = :brings"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByPlanTrnsDate", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.planTrnsDate = :planTrnsDate"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByRltdOrdrId", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.rltdOrdrId = :rltdOrdrId"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByDiscountEmp", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.discountEmp = :discountEmp"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByDiscount", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.discount = :discount"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByNewOrdr", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.newOrdr = :newOrdr"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByDiscountNotes", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.discountNotes = :discountNotes"),
    @NamedQuery(name = "DmsTransportOrdrHd.findByClientPayed", query = "SELECT d FROM DmsTransportOrdrHd d WHERE d.clientPayed = :clientPayed")})
public class DmsTransportOrdrHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "BRN_ID")
    private long brnId;
    @Basic(optional = false)
    @Column(name = "ORDR_NO")
    private long ordrNo;
    @Basic(optional = false)
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Basic(optional = false)
    @Column(name = "CITY_ID")
    private long cityId;
    @Basic(optional = false)
    @Column(name = "AREA_ID")
    private long areaId;
    @Column(name = "TEL1")
    private String tel1;
    @Column(name = "TEL2")
    private String tel2;
    @Column(name = "MOBILE")
    private String mobile;
    @Basic(optional = false)
    @Column(name = "PRICE")
    private Double price;
    @Basic(optional = false)
    @Column(name = "PRICE_PAYED")
    private Double pricePayed;
    @Column(name = "PRICE_RMN")
    private Double priceRmn;
    @Column(name = "NOTES")
    private String notes;
    @Basic(optional = false)
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Basic(optional = false)
    @Column(name = "PRD_ID")
    private short prdId;
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @Column(name = "DELIVERY_DATE")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    @Column(name = "FAMILY_NAME")
    private String familyName;
    @Column(name = "PHONE_CODE")
    private String phoneCode;
    @Column(name = "CANCELED")
    private String canceled;
    @Column(name = "LIST_PRICE")
    private BigDecimal listPrice;
    @Column(name = "TIME_FROM")
    @Temporal(TemporalType.DATE)
    private Date timeFrom;
    @Column(name = "TIME_TO")
    @Temporal(TemporalType.DATE)
    private Date timeTo;
    @Column(name = "PRINTED")
    private String printed;
    @Column(name = "BACK_NO")
    private BigInteger backNo;
    @Column(name = "LOAD_PLACE")
    private String loadPlace;
    @Column(name = "BRINGS")
    private String brings;
    @Column(name = "PLAN_TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date planTrnsDate;
    @Column(name = "RLTD_ORDR_ID")
    private BigInteger rltdOrdrId;
    @ManyToOne
    @JoinColumn(name = "DISCOUNT_EMP",referencedColumnName="EMP_NO")
    private HrEmpInfo discountEmp;
    @Column(name = "DISCOUNT")
    private Double discount;
    @Column(name = "NEW_ORDR")
    private String newOrdr;
    @Column(name = "DISCOUNT_NOTES")
    private String discountNotes;
    @Column(name = "CLIENT_PAYED")
    private String clientPayed;
    @Column(name = "HD_ID")
    private Long hdId;
    @Column(name = "MANAGER_ID")
    private Long managerId;
    @Column(name="RMN_DISCOUNT")
    private Double rmnDiscount;
    @Column(name="RECEIPT_VOUCHER")
    private String receiptVoucher;
    @Column(name="PROFILE_CONFIRM")
    private String profileConfirm;
    @Column(name="DISCOUNT_TIMES")
    private Long discountTimes;
    @ManyToOne
    @JoinColumn(name="USER_ID",referencedColumnName="ID")
    private DmsUsers userId;
    public DmsTransportOrdrHd() {
    }

    public DmsTransportOrdrHd(Long id) {
        this.id = id;
    }

    public DmsTransportOrdrHd(Long id, long brnId, long ordrNo, String clntName, long cityId, long areaId, Double price, Double pricePayed, Date trnsDate, short prdId, Date deliveryDate) {
        this.id = id;
        this.brnId = brnId;
        this.ordrNo = ordrNo;
        this.clntName = clntName;
        this.cityId = cityId;
        this.areaId = areaId;
        this.price = price;
        this.pricePayed = pricePayed;
        this.trnsDate = trnsDate;
        this.prdId = prdId;
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getBrnId() {
        return brnId;
    }

    public void setBrnId(long brnId) {
        this.brnId = brnId;
    }

    public long getOrdrNo() {
        return ordrNo;
    }

    public void setOrdrNo(long ordrNo) {
        this.ordrNo = ordrNo;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPricePayed() {
        return pricePayed;
    }

    public void setPricePayed(Double pricePayed) {
        this.pricePayed = pricePayed;
    }

    public Double getPriceRmn() {
        return priceRmn;
    }

    public void setPriceRmn(Double priceRmn) {
        this.priceRmn = priceRmn;
    }

    


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public short getPrdId() {
        return prdId;
    }

    public void setPrdId(short prdId) {
        this.prdId = prdId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public String getPrinted() {
        return printed;
    }

    public void setPrinted(String printed) {
        this.printed = printed;
    }

    public BigInteger getBackNo() {
        return backNo;
    }

    public void setBackNo(BigInteger backNo) {
        this.backNo = backNo;
    }

    public String getLoadPlace() {
        return loadPlace;
    }

    public void setLoadPlace(String loadPlace) {
        this.loadPlace = loadPlace;
    }

    public String getBrings() {
        return brings;
    }

    public void setBrings(String brings) {
        this.brings = brings;
    }

    public Date getPlanTrnsDate() {
        return planTrnsDate;
    }

    public void setPlanTrnsDate(Date planTrnsDate) {
        this.planTrnsDate = planTrnsDate;
    }

    public BigInteger getRltdOrdrId() {
        return rltdOrdrId;
    }

    public void setRltdOrdrId(BigInteger rltdOrdrId) {
        this.rltdOrdrId = rltdOrdrId;
    }

    public HrEmpInfo getDiscountEmp() {
        return discountEmp;
    }

    public void setDiscountEmp(HrEmpInfo discountEmp) {
        this.discountEmp = discountEmp;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    


    public String getNewOrdr() {
        return newOrdr;
    }

    public void setNewOrdr(String newOrdr) {
        this.newOrdr = newOrdr;
    }

    public String getDiscountNotes() {
        return discountNotes;
    }

    public void setDiscountNotes(String discountNotes) {
        this.discountNotes = discountNotes;
    }

    public String getClientPayed() {
        return clientPayed;
    }

    public void setClientPayed(String clientPayed) {
        this.clientPayed = clientPayed;
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

    public String getReceiptVoucher() {
        return receiptVoucher;
    }

    public void setReceiptVoucher(String receiptVoucher) {
        this.receiptVoucher = receiptVoucher;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getProfileConfirm() {
        return profileConfirm;
    }

    public void setProfileConfirm(String profileConfirm) {
        this.profileConfirm = profileConfirm;
    }

    public DmsUsers getUserId() {
        return userId;
    }

    public void setUserId(DmsUsers userId) {
        this.userId = userId;
    }

    public Long getDiscountTimes() {
        return discountTimes;
    }

    public void setDiscountTimes(Long discountTimes) {
        this.discountTimes = discountTimes;
    }


    



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmsTransportOrdrHd)) {
            return false;
        }
        DmsTransportOrdrHd other = (DmsTransportOrdrHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "e.DmsTransportOrdrHd[id=" + id + "]";
    }

}

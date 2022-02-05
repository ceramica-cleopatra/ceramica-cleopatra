package com.dms.driverTracking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DMS_DRIVERS_UNDER_SRF_PRICE",schema="DMS")
public class DmsDriversUnderSrfPriceVW {
	@Id
	@Column(name="ROW_ID")
	private Long rowId;
	@Column(name="BRN_ID")
	private Long brnId;
	@Column(name="TRNS_ORDR_DATE")
	@Temporal(TemporalType.DATE)
	private Date trnsOrdrDate;
	@Column(name="ORD_BRN")
	private String showroom;
	@Column(name="ORDR_NO")
	private Long trnsOrdrNo;
	@Column(name="CRMK_SEHY")
	private String crmkSehy;
	@Column(name="ME2ORDR_DATE")
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;
	@Column(name="SRF_NO")
	private Long deliveryOrdrNo;
	@Column(name="STORE_ID")
	private Long storeId;
	@Column(name="SRF_BRN")
	private String store;
	@Column(name="price")
	private Double balance;
	@Column(name="DRIVER_ID")
	private Long driverId;
	public Long getRowId() {
		return rowId;
	}
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
	public Long getBrnId() {
		return brnId;
	}
	public void setBrnId(Long brnId) {
		this.brnId = brnId;
	}
	public Date getTrnsOrdrDate() {
		return trnsOrdrDate;
	}
	public void setTrnsOrdrDate(Date trnsOrdrDate) {
		this.trnsOrdrDate = trnsOrdrDate;
	}
	public String getShowroom() {
		return showroom;
	}
	public void setShowroom(String showroom) {
		this.showroom = showroom;
	}
	public Long getTrnsOrdrNo() {
		return trnsOrdrNo;
	}
	public void setTrnsOrdrNo(Long trnsOrdrNo) {
		this.trnsOrdrNo = trnsOrdrNo;
	}
	public String getCrmkSehy() {
		return crmkSehy;
	}
	public void setCrmkSehy(String crmkSehy) {
		this.crmkSehy = crmkSehy;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Long getDeliveryOrdrNo() {
		return deliveryOrdrNo;
	}
	public void setDeliveryOrdrNo(Long deliveryOrdrNo) {
		this.deliveryOrdrNo = deliveryOrdrNo;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
}

package com.dms.driverTracking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DMS_DRIVER_TRANS_VW",schema="DMS")
public class DmsDriverTrnsVW {
	@Id
    private Long id;
	@Column(name="DRIVER_ID")
	private Long driverId;
	@Column(name="DRIVER_NAME")
	private String driverName;
	@Column(name="STORE_ID")
	private Long storeId;
	@Column(name="STORE_NAME")
	private String storeName;
	@Column(name="TRNS_DATE_IN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date trnsDateIn;
	@Column(name="TRNS_DATE_OUT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date trnsDateOut;
	@Column(name="LICENSE")
	private String license;
	@Column(name="MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getTrnsDateIn() {
		return trnsDateIn;
	}
	public void setTrnsDateIn(Date trnsDateIn) {
		this.trnsDateIn = trnsDateIn;
	}
	public Date getTrnsDateOut() {
		return trnsDateOut;
	}
	public void setTrnsDateOut(Date trnsDateOut) {
		this.trnsDateOut = trnsDateOut;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
}

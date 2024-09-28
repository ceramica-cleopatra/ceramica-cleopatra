package com.dms.driverTracking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DMS_PLAN_LIVE_VW",schema="DMS")
public class DmsPlanLiveVW {

	@Id
    private Long id;
	@Column(name="PLAN_ID")
	private Long planId;
	@Column(name="PLAN_NAME")
	private String planName;
	@Temporal(TemporalType.DATE)
	@Column(name="TRNS_DATE")
	private Date trnsDate;
	@Column(name="ORDR_NO")
	private Long ordrNo;
	@Column(name="BRN_ID")
	private Long brnId;
	@Column(name="BRN_NAME")
	private String brnName;
	@Column(name="PLAN_BRN_ID")
	private Long planBrnId;
	@Column(name="CLNT_NAME")
	private String clntName;
	@Column(name="TEL1")
	private String tel1;
	@Column(name="TEL2")
	private String tel2;
	@Column(name="MOBILE")
	private String mobile;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="TIME_FROM")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeFrom;
	@Column(name="TIME_TO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeTo;
	@Column(name="AREA_NAME")
	private String areaName;
	@Column(name="CITY_NAME")
	private String cityName;
	@Column(name="TOTAL_QTY")
	private Double totalQty;
	@Column(name="DRIVER_ID")
	private Long driverId;
	@Column(name="DRIVER_NAME")
	private String driverName;
	@Column(name="TYPE_ID")
	private Long typeId;
	@Column(name="TYPE_NAME")
	private String typeName;
	@Column(name="CAR_TYPE")
	private String carType;
	@Column(name="STATUS_ID")
	private Long statusId;
	@Column(name="STATUS")
	private String status;
	@Column(name="LATITUDE")
	private String latitude;
	@Column(name="LONGITUDE")
	private String longitude;
	@Column(name="DRIVER_MOBILE")
	private String driverMobile;
	@Column(name="MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Long getBrnId() {
		return brnId;
	}
	public void setBrnId(Long brnId) {
		this.brnId = brnId;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	
	public Long getPlanBrnId() {
		return planBrnId;
	}
	public void setPlanBrnId(Long planBrnId) {
		this.planBrnId = planBrnId;
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
	public void setDrivername(String driverName) {
		this.driverName = driverName;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getClntName() {
		return clntName;
	}
	public void setClntName(String clntName) {
		this.clntName = clntName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Date getTrnsDate() {
		return trnsDate;
	}
	public void setTrnsDate(Date trnsDate) {
		this.trnsDate = trnsDate;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public Long getOrdrNo() {
		return ordrNo;
	}
	public void setOrdrNo(Long ordrNo) {
		this.ordrNo = ordrNo;
	}
	public String getDriverMobile() {
		return driverMobile;
	}
	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
	
}

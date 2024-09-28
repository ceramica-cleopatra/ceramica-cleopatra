package com.query.generation.qr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name=" CRMK_SEHY_QR_ITEMS",schema="CRMK")
public class SehyQRItems {
	@Id
	private String id;
	@Column(name="QR_STRING")
	private String qrString;
	@Column(name="CODE")
	private String code;
	@Column(name="TYPE_ID")
	private Long typeId;
	@Column(name="NAME_ID")
	private Long nameId;
	@Column(name="DEKALA_ID")
	private Long dekalaId;
	@Column(name="COLOR_ID")
	private Long colorId;
	@Column(name="GRP_ID")
	private Long grpId;
	@Column(name="SEHY_NAME")
	private String sehyName;
	@Column(name="PRICE")
	private String price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQrString() {
		return qrString;
	}
	public void setQrString(String qrString) {
		this.qrString = qrString;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getNameId() {
		return nameId;
	}
	public void setNameId(Long nameId) {
		this.nameId = nameId;
	}
	public Long getDekalaId() {
		return dekalaId;
	}
	public void setDekalaId(Long dekalaId) {
		this.dekalaId = dekalaId;
	}
	public Long getColorId() {
		return colorId;
	}
	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}
	public Long getGrpId() {
		return grpId;
	}
	public void setGrpId(Long grpId) {
		this.grpId = grpId;
	}
	public String getSehyName() {
		return sehyName;
	}
	public void setSehyName(String sehyName) {
		this.sehyName = sehyName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	
}

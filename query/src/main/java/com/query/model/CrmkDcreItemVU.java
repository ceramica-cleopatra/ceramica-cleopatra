package com.query.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CRMK_DCRE_DISTINCT_VU",schema="CRMK")
public class CrmkDcreItemVU {
	@Id
	@Column(name="VU_ID")
	private String id;
	@Column(name="TYPE_ID")
	private Long typeId;
	@Column(name="TYPE_NAME")
	private String typeName;
	@Column(name="SIZE_ID")
	private Long sizeId;
	@Column(name="SIZE_NAME")
	private String sizeName;
	@Column(name="DEKALA_ID")
	private Long dekalaId;
	@Column(name="DEKALA_NAME")
	private String dekalaName;
	@Column(name="FACTORY_NO")
	private String factoryNo;
	@Column(name="FRZ")
	private Long frz;
	@Column(name="COLOUR_ID")
	private Long colourId;
	@Column(name="COLOUR_NAME")
	private String colourName;
	@Column(name="TABLOW")
	private Long tablow;
	@Column(name="ITEM_ID")
	private Long itemId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Long getSizeId() {
		return sizeId;
	}
	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public Long getDekalaId() {
		return dekalaId;
	}
	public void setDekalaId(Long dekalaId) {
		this.dekalaId = dekalaId;
	}
	public String getDekalaName() {
		return dekalaName;
	}
	public void setDekalaName(String dekalaName) {
		this.dekalaName = dekalaName;
	}
	public String getFactoryNo() {
		return factoryNo;
	}
	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}
	public Long getFrz() {
		return frz;
	}
	public void setFrz(Long frz) {
		this.frz = frz;
	}
	public Long getColourId() {
		return colourId;
	}
	public void setColourId(Long colourId) {
		this.colourId = colourId;
	}
	public String getColourName() {
		return colourName;
	}
	public void setColourName(String colourName) {
		this.colourName = colourName;
	}
	public Long getTablow() {
		return tablow;
	}
	public void setTablow(Long tablow) {
		this.tablow = tablow;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}

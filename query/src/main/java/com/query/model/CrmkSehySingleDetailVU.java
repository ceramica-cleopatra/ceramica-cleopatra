package com.query.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CRMK_SEHY_SINGLE_DETAIL_VU",schema="CRMK")
public class CrmkSehySingleDetailVU {
@Id
@Column(name="ID")
private Long id;
@Column(name="ITEM_ID")
private Long itemId;
@Column(name="CODE")
private String code;
@Column(name="COLOR_ID")
private Long colorId;
@Column(name="DEKALA_ID")
private Long dekalaId;
@Column(name="NAME_ID")
private Long nameId;	
@Column(name="FRZ")
private Long frz;
@Column(name="TYPE_ID")
private Long typeId;
@Column(name="NAME")
private String name;
@Column(name="DEKALA")
private String dekala;
@Column(name="COLOR")
private String color;
@Column(name="TYPE_NAME")
private String typeName;
@Column(name="STORE_ID")
private Long storeId;
@Column(name="QTY")
private Long qty;
@Column(name="RSRV")
private Long rsrv;
@Column(name="FREE")
private Long free;
@Column(name="GOVERN_ID")
private Long governId;
@Column(name="GOVERN_NAME")
private String governName;
@Column(name="STORE_NAME")
private String storeName;
@Column(name="PRICE")
private Double price;


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getColorId() {
	return colorId;
}
public void setColorId(Long colorId) {
	this.colorId = colorId;
}
public Long getDekalaId() {
	return dekalaId;
}
public void setDekalaId(Long dekalaId) {
	this.dekalaId = dekalaId;
}
public Long getNameId() {
	return nameId;
}
public void setNameId(Long nameId) {
	this.nameId = nameId;
}
public Long getFrz() {
	return frz;
}
public void setFrz(Long frz) {
	this.frz = frz;
}
public Long getTypeId() {
	return typeId;
}
public void setTypeId(Long typeId) {
	this.typeId = typeId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Long getStoreId() {
	return storeId;
}
public void setStoreId(Long storeId) {
	this.storeId = storeId;
}
public Long getQty() {
	return qty;
}
public void setQty(Long qty) {
	this.qty = qty;
}
public Long getRsrv() {
	return rsrv;
}
public void setRsrv(Long rsrv) {
	this.rsrv = rsrv;
}
public Long getFree() {
	return free;
}
public void setFree(Long free) {
	this.free = free;
}
public Long getGovernId() {
	return governId;
}
public void setGovernId(Long governId) {
	this.governId = governId;
}
public String getGovernName() {
	return governName;
}
public void setGovernName(String governName) {
	this.governName = governName;
}
public String getStoreName() {
	return storeName;
}
public void setStoreName(String storeName) {
	this.storeName = storeName;
}
public Long getItemId() {
	return itemId;
}
public void setItemId(Long itemId) {
	this.itemId = itemId;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getDekala() {
	return dekala;
}
public void setDekala(String dekala) {
	this.dekala = dekala;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public String getTypeName() {
	return typeName;
}
public void setTypeName(String typeName) {
	this.typeName = typeName;
}

}

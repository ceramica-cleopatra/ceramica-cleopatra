package com.query.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CRMK_SEHY_TAKM_VU",schema="CRMK")
public class CrmkSehyTakmVU {
@Id
@Column(name="ID")
private Long id;
@Column(name="CODE")
private String code;
@Column(name="TYPE_ID")
private Long typeId;
@Column(name="TYPE_NAME")
private String typeName;
@Column(name="COLOR_ID")
private Long colorId;
@Column(name="DEKALA_ID")
private Long dekalaId;
@Column(name="NAME_ID")
private Long nameId;	
@Column(name="FRZ")
private Long frz;
@Column(name="COLOR")
private String color;
@Column(name="DEKALA")
private String dekala;
@Column(name="NAME")
private String name;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
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
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public String getDekala() {
	return dekala;
}
public void setDekala(String dekala) {
	this.dekala = dekala;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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


}

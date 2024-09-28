package com.query.dto;

public class GroupDetailsDTO {
	public GroupDetailsDTO(String itemId,String crmkDcre ,String typeId, String typeName, String sizeId, String sizeName, String factoryNo,
			String dekalaId, String dekalaName,String degree,String bathName,String bathColor,String bathId, String free1,String free2,String colorId,String colorName,String tablow) {
		super();
		this.itemId = itemId;
		this.crmkDcre = crmkDcre;
		this.typeId = typeId;
		this.typeName = typeName;
		this.sizeId = sizeId;
		this.sizeName = sizeName;
		this.factoryNo = factoryNo;
		this.dekalaId = dekalaId;
		this.dekalaName = dekalaName;
		this.degree = degree;
		this.bathName = bathName;
		this.bathColor = bathColor;
		this.bathId = bathId;
		this.free1 = free1;
		this.free2 = free2;
		this.colorId = colorId;
		this.colorName = colorName;
		this.tablow = tablow;
	}
	private String itemId;
	private String crmkDcre;
	private String typeId;
	private String typeName;
	private String sizeId;
	private String sizeName;
	private String factoryNo;
	private String dekalaId;
	private String dekalaName;
	private String colorId;
	private String colorName;
	private String tablow;
	private String free1;
	private String free2;
	private String degree;
	private String bathName;
	private String bathColor;
	private String bathId;
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getFactoryNo() {
		return factoryNo;
	}
	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}
	public String getDekalaId() {
		return dekalaId;
	}
	public void setDekalaId(String dekalaId) {
		this.dekalaId = dekalaId;
	}
	public String getDekalaName() {
		return dekalaName;
	}
	public void setDekalaName(String dekalaName) {
		this.dekalaName = dekalaName;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getTablow() {
		return tablow;
	}
	public void setTablow(String tablow) {
		this.tablow = tablow;
	}

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getCrmkDcre() {
		return crmkDcre;
	}
	public void setCrmkDcre(String crmkDcre) {
		this.crmkDcre = crmkDcre;
	}
	public String getFree1() {
		return free1;
	}
	public void setFree1(String free1) {
		this.free1 = free1;
	}
	public String getFree2() {
		return free2;
	}
	public void setFree2(String free2) {
		this.free2 = free2;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getBathName() {
		return bathName;
	}
	public void setBathName(String bathName) {
		this.bathName = bathName;
	}
	public String getBathColor() {
		return bathColor;
	}
	public void setBathColor(String bathColor) {
		this.bathColor = bathColor;
	}
	public String getBathId() {
		return bathId;
	}
	public void setBathId(String bathId) {
		this.bathId = bathId;
	}

}

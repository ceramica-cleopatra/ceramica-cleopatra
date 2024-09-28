package com.query.dto;

public class DcreSearchResultDTO {
	public DcreSearchResultDTO(String typeId, String typeName, String sizeId, String sizeName, String factoryNo,
			String dekalaId, String dekalaName, String frz, String colorId, String colorName,String tablow) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.sizeId = sizeId;
		this.sizeName = sizeName;
		this.factoryNo = factoryNo;
		this.dekalaId = dekalaId;
		this.dekalaName = dekalaName;
		this.frz = frz;
		this.colorId = colorId;
		this.colorName = colorName;
		this.tablow = tablow;
	}

	private String typeId;
	private String typeName;
	private String sizeId;
	private String sizeName;
	private String factoryNo;
	private String dekalaId;
	private String dekalaName;
	private String frz;
	private String colorId;
	private String colorName;
	private String tablow;
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
	public String getFrz() {
		return frz;
	}
	public void setFrz(String frz) {
		this.frz = frz;
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

}

package com.query.dto;

public class CrmkSearchResultDTO {
	
	public CrmkSearchResultDTO(String typeId, String typeName, String sizeId, String sizeName, String factoryNo,
			String dekalaId, String dekalaName, String frz) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.sizeId = sizeId;
		this.sizeName = sizeName;
		this.factoryNo = factoryNo;
		this.dekalaId = dekalaId;
		this.dekalaName = dekalaName;
		this.frz = frz;
	}

	private String typeId;
	private String typeName;
	private String sizeId;
	private String sizeName;
	private String factoryNo;
	private String dekalaId;
	private String dekalaName;
	private String frz;
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
	

}

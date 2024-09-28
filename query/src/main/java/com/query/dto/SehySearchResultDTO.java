package com.query.dto;

public class SehySearchResultDTO {
	public SehySearchResultDTO(String typeId, String typeName, String nameId, String name, String dekalaId, String dekalaName ,String colorId, String colorName, String frz,String takmId) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.nameId = nameId;
		this.name = name;
		this.dekalaId = dekalaId;
		this.dekalaName = dekalaName;
		this.colorId = colorId;
		this.colorName = colorName;
		this.frz = frz;
		this.takmId = takmId;
	}

	private String typeId;
	private String typeName;
	private String nameId;
	private String name;
	private String dekalaId;
	private String dekalaName;
	private String colorId;
	private String colorName;
	private String frz;
	private String takmId;
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
	
	public String getNameId() {
		return nameId;
	}
	public void setNameId(String nameId) {
		this.nameId = nameId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTakmId() {
		return takmId;
	}
	public void setTakmId(String takmId) {
		this.takmId = takmId;
	}

	
}

package com.query.generation.qr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name=" CRMK_DCRE_QR_ITEMS_MV",schema="CRMK")
public class DcreQRItems {
	@Id
	private String id;
	@Column(name="QR_STRING")
	private String qrString;
	@Column(name="NO_C_TONE")
	private String noCTone;
	@Column(name="TYPE_ID")
	private Long typeId;
	@Column(name="SIZE_ID")
	private Long sizeId;
	@Column(name="DEKALA_ID")
	private Long dekalaId;
	@Column(name="FACTORY_NO")
	private Long factoryNo;
	@Column(name="COLOR_ID")
	private Long colorId;
	@Column(name="TABLOW")
	private Long tablow;
	@Column(name="ENGLISH_NAME")
	private String englishName;
	@Column(name="PRICE")
	private Double price;
	@Column(name="SHOW_TYPE")
	private String showType;
	@Column(name="GRP_ID")
	private Long grpId;
	@Column(name="FLOOR")
	private Long floor;
	@Column(name="BRN_ID")
	private Long brnId;
	@Column(name="STAND_NO")
	private String standNo;
	@Column(name="HOW2SHOW")
	private Long how2show;
	
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
	public String getNoCTone() {
		return noCTone;
	}
	public void setNoCTone(String noCTone) {
		this.noCTone = noCTone;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getSizeId() {
		return sizeId;
	}
	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}
	public Long getDekalaId() {
		return dekalaId;
	}
	public void setDekalaId(Long dekalaId) {
		this.dekalaId = dekalaId;
	}
	public Long getFactoryNo() {
		return factoryNo;
	}
	public void setFactoryNo(Long factoryNo) {
		this.factoryNo = factoryNo;
	}
	public Long getColorId() {
		return colorId;
	}
	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}
	public Long getTablow() {
		return tablow;
	}
	public void setTablow(Long tablow) {
		this.tablow = tablow;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public Long getGrpId() {
		return grpId;
	}
	public void setGrpId(Long grpId) {
		this.grpId = grpId;
	}
	public Long getFloor() {
		return floor;
	}
	public void setFloor(Long floor) {
		this.floor = floor;
	}
	public Long getBrnId() {
		return brnId;
	}
	public void setBrnId(Long brnId) {
		this.brnId = brnId;
	}
	public String getStandNo() {
		return standNo;
	}
	public void setStandNo(String standNo) {
		this.standNo = standNo;
	}
	public Long getHow2show() {
		return how2show;
	}
	public void setHow2show(Long how2show) {
		this.how2show = how2show;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colorId == null) ? 0 : colorId.hashCode());
		result = prime * result + ((dekalaId == null) ? 0 : dekalaId.hashCode());
		result = prime * result + ((factoryNo == null) ? 0 : factoryNo.hashCode());
		result = prime * result + ((sizeId == null) ? 0 : sizeId.hashCode());
		result = prime * result + ((tablow == null) ? 0 : tablow.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DcreQRItems other = (DcreQRItems) obj;
		if (colorId == null) {
			if (other.colorId != null)
				return false;
		} else if (!colorId.equals(other.colorId))
			return false;
		if (dekalaId == null) {
			if (other.dekalaId != null)
				return false;
		} else if (!dekalaId.equals(other.dekalaId))
			return false;
		if (factoryNo == null) {
			if (other.factoryNo != null)
				return false;
		} else if (!factoryNo.equals(other.factoryNo))
			return false;
		if (sizeId == null) {
			if (other.sizeId != null)
				return false;
		} else if (!sizeId.equals(other.sizeId))
			return false;
		if (tablow == null) {
			if (other.tablow != null)
				return false;
		} else if (!tablow.equals(other.tablow))
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}
	
	
	
}

package com.query.dto;

import java.util.List;

public class SehyItemDetailDTO {
private Long itemId;
private String itemName;
private String freeQty;
private String realQty;
private String rsrvQty;
private String price;


public Long getItemId() {
	return itemId;
}
public void setItemId(Long itemId) {
	this.itemId = itemId;
}
public String getFreeQty() {
	return freeQty;
}
public void setFreeQty(String freeQty) {
	this.freeQty = freeQty;
}

public String getRealQty() {
	return realQty;
}
public void setRealQty(String realQty) {
	this.realQty = realQty;
}

public String getRsrvQty() {
	return rsrvQty;
}
public void setRsrvQty(String rsrvQty) {
	this.rsrvQty = rsrvQty;
}

public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}


public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public SehyItemDetailDTO() {
	super();
}
public SehyItemDetailDTO(Long itemId, String itemName, String price) {
	super();
	this.itemId = itemId;
	this.itemName = itemName;
	this.price = price;
	this.freeQty = "0";
	this.realQty = "0";
	this.rsrvQty = "0";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
	SehyItemDetailDTO other = (SehyItemDetailDTO) obj;
	if (itemId == null) {
		if (other.itemId != null)
			return false;
	} else if (!itemId.equals(other.itemId))
		return false;
	return true;
}



}

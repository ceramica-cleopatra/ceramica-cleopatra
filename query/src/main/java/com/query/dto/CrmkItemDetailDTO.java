package com.query.dto;

import java.util.List;

public class CrmkItemDetailDTO {
private Integer itemId;
private String c;
private String tone;
private String freeQty;
private String realQty;
private String rsrvQty;

public Integer getItemId() {
	return itemId;
}
public void setItemId(Integer itemId) {
	this.itemId = itemId;
}
public String getC() {
	return c;
}
public void setC(String c) {
	this.c = c;
}
public String getTone() {
	return tone;
}
public void setTone(String tone) {
	this.tone = tone;
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
public CrmkItemDetailDTO() {
	super();
}
public CrmkItemDetailDTO(Integer itemId, String c, String tone) {
	super();
	this.itemId = itemId;
	this.c = c;
	this.tone = tone;
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
	CrmkItemDetailDTO other = (CrmkItemDetailDTO) obj;
	if (itemId == null) {
		if (other.itemId != null)
			return false;
	} else if (!itemId.equals(other.itemId))
		return false;
	return true;
}



}

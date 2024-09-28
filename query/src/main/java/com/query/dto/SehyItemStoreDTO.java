package com.query.dto;

import java.util.List;

public class SehyItemStoreDTO {
private String storeId;
private String storeName;
private String governName;
private String totalFree;
public String getTotalFree() {
	return totalFree;
}

public void setTotalFree(String totalFree) {
	this.totalFree = totalFree;
}

public String getStoreId() {
	return storeId;
}

public void setStoreId(String storeId) {
	this.storeId = storeId;
}



private List<SehyItemDetailDTO> itemDteailList;
public String getStoreName() {
	return storeName;
}
public void setStoreName(String storeName) {
	this.storeName = storeName;
}
public List<SehyItemDetailDTO> getItemDteailList() {
	return itemDteailList;
}
public void setItemDteailList(List<SehyItemDetailDTO> itemDteailList) {
	this.itemDteailList = itemDteailList;
}


public String getGovernName() {
	return governName;
}

public void setGovernName(String governName) {
	this.governName = governName;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
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
	SehyItemStoreDTO other = (SehyItemStoreDTO) obj;
	if (storeName == null) {
		if (other.storeName != null)
			return false;
	} else if (!storeName.equals(other.storeName))
		return false;
	return true;
}


}

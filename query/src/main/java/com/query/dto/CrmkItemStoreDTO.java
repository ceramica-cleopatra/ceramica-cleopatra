package com.query.dto;

import java.util.List;

public class CrmkItemStoreDTO {
private String storeName;
private String totalFree;
public String getTotalFree() {
	return totalFree;
}

public void setTotalFree(String totalFree) {
	this.totalFree = totalFree;
}

private List<CrmkItemDetailDTO> itemDteailList;
public String getStoreName() {
	return storeName;
}
public void setStoreName(String storeName) {
	this.storeName = storeName;
}
public List<CrmkItemDetailDTO> getItemDteailList() {
	return itemDteailList;
}
public void setItemDteailList(List<CrmkItemDetailDTO> itemDteailList) {
	this.itemDteailList = itemDteailList;
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
	CrmkItemStoreDTO other = (CrmkItemStoreDTO) obj;
	if (storeName == null) {
		if (other.storeName != null)
			return false;
	} else if (!storeName.equals(other.storeName))
		return false;
	return true;
}


}
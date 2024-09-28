package com.query.dto;

import java.util.List;

public class SehyItemGovDTO {
     private String productName;
	 private List<SehyItemStoreDTO> storeList;
	 private List<SehyItemDetailDTO> distinctItems;
	
	public List<SehyItemStoreDTO> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<SehyItemStoreDTO> storeList) {
		this.storeList = storeList;
	}
	
	public List<SehyItemDetailDTO> getDistinctItems() {
		return distinctItems;
	}
	public void setDistinctItems(List<SehyItemDetailDTO> distinctItems) {
		this.distinctItems = distinctItems;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	 
}

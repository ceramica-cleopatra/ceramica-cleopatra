package com.query.dto;

import java.util.List;

public class CrmkItemGovDTO {
	 private String govName;
	 private String totalFree;
	 private String totalReal;
	 private String totalRsrv;
	 private String price;
	 private String priceType;
	 private String pList;
	 private String dList;
	 private String productName;
	 private String packageSize;
	 private List<CrmkItemStoreDTO> storeList;
	 private List<CrmkItemDetailDTO> distinctItems;
	public String getGovName() {
		return govName;
	}
	public void setGovName(String govName) {
		this.govName = govName;
	}
	public List<CrmkItemStoreDTO> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<CrmkItemStoreDTO> storeList) {
		this.storeList = storeList;
	}
	
	public String getTotalFree() {
		return totalFree;
	}
	public void setTotalFree(String totalFree) {
		this.totalFree = totalFree;
	}
	public String getTotalReal() {
		return totalReal;
	}
	public void setTotalReal(String totalReal) {
		this.totalReal = totalReal;
	}
	public List<CrmkItemDetailDTO> getDistinctItems() {
		return distinctItems;
	}
	public void setDistinctItems(List<CrmkItemDetailDTO> distinctItems) {
		this.distinctItems = distinctItems;
	}
	
	public String getTotalRsrv() {
		return totalRsrv;
	}
	public void setTotalRsrv(String totalRsrv) {
		this.totalRsrv = totalRsrv;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}
	
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((govName == null) ? 0 : govName.hashCode());
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
		CrmkItemGovDTO other = (CrmkItemGovDTO) obj;
		if (govName == null) {
			if (other.govName != null)
				return false;
		} else if (!govName.equals(other.govName))
			return false;
		return true;
	}
	public String getpList() {
		return pList;
	}
	public void setpList(String pList) {
		this.pList = pList;
	}
	public String getdList() {
		return dList;
	}
	public void setdList(String dList) {
		this.dList = dList;
	}
	
	
	 
}

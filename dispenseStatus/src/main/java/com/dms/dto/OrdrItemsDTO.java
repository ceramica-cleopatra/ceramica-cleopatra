package com.dms.dto;

public class OrdrItemsDTO {
	private String itemName;
	private Double totalQty;
	private Double remainQty;
	private Double dispensedQty;
	private Double canceledQty;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}
	public Double getRemainQty() {
		return remainQty;
	}
	public void setRemainQty(Double remainQty) {
		this.remainQty = remainQty;
	}
	public Double getCanceledQty() {
		return canceledQty;
	}
	public void setCanceledQty(Double canceledQty) {
		this.canceledQty = canceledQty;
	}
	public Double getDispensedQty() {
		return dispensedQty;
	}
	public void setDispensedQty(Double dispensedQty) {
		this.dispensedQty = dispensedQty;
	}
	
}

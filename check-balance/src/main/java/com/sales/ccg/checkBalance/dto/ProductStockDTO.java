package com.sales.ccg.checkBalance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ProductStockDTO {
	private Double quantity;
	private Long sku;
	private String type;
	
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}

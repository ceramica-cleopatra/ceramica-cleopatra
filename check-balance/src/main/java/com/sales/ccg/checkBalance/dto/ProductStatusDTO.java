package com.sales.ccg.checkBalance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProductStatusDTO {
	private Long sku;
	private boolean hasStock;
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public boolean isHasStock() {
		return hasStock;
	}
	public void setHasStock(boolean hasStock) {
		this.hasStock = hasStock;
	}
	
	
}

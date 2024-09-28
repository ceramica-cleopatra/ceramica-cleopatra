package com.sales.ccg.checkBalance.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class PayloadDTO {
	private List<ProductStatusDTO> productList;

	public List<ProductStatusDTO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductStatusDTO> productList) {
		this.productList = productList;
	}
	
}

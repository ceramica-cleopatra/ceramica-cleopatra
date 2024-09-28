package com.sales.ccg.checkBalance.dto;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class OrderBalanceRequestDTO {
	private Long governorate;
	private List<ProductStockDTO> products;
	public Long getGovernorate() {
		return governorate;
	}
	public void setGovernorate(Long governorate) {
		this.governorate = governorate;
	}
	public List<ProductStockDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductStockDTO> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return "OrderBalanceRequestDTO [governorate=" + governorate + ", products=" + mapper.writeValueAsString(products) + "]";
		} catch (JsonProcessingException e) {
			   e.printStackTrace();
			   return null;
		}
	}

}

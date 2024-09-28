package com.sales.ccg.updatebalance.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProductDTO implements Serializable {
	private Long sku;
	private Double qty;
	private Integer governorateId;
	private Date modificationDate;
	
	

	public ProductDTO() {
		super();
	}



	public Long getSku() {
		return sku;
	}



	public void setSku(Long sku) {
		this.sku = sku;
	}



	public Double getQty() {
		return qty;
	}



	public void setQty(Double qty) {
		this.qty = qty;
	}



	public Integer getGovernorateId() {
		return governorateId;
	}



	public void setGovernorateId(Integer governorateId) {
		this.governorateId = governorateId;
	}



	public Date getModificationDate() {
		return modificationDate;
	}



	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}



	public ProductDTO(Long sku,Double qty,Integer governorateId,Date modificationDate){
		this.sku = sku;
		this.qty = qty;
		this.governorateId = governorateId;
		this.modificationDate = modificationDate;
	}
}

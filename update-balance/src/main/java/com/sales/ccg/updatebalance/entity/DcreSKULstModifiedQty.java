package com.sales.ccg.updatebalance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CRMK_D_SKU_LST_MODIFIED_QTY",schema="CRMK")
public class DcreSKULstModifiedQty {
	@Id
	private Long id;
	@Column(name="SKU")
	private Long sku;
	@Column(name = "GOVERN_ID")
	private Integer governId;
	@Column(name = "FREE_QTY")
	private Double freeQty;
	@Column(name = "LAST_MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public Integer getGovernId() {
		return governId;
	}
	public void setGovernId(Integer governId) {
		this.governId = governId;
	}
	public Double getFreeQty() {
		return freeQty;
	}
	public void setFreeQty(Double freeQty) {
		this.freeQty = freeQty;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}

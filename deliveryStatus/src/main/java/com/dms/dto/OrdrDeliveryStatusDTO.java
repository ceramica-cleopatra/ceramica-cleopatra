package com.dms.dto;

import java.io.Serializable;
import java.util.Date;

public class OrdrDeliveryStatusDTO implements Serializable{
	private String ordrId;
	private String deliveryDate;
	private String status;
	
	public String getOrdrId() {
		return ordrId;
	}
	public void setOrdrId(String ordrId) {
		this.ordrId = ordrId;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

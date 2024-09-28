package com.sales.ccg.checkBalance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OrderBalanceResponseDTO {
	private StatusDTO status;
	private PayloadDTO payload;
	public StatusDTO getStatus() {
		return status;
	}
	public void setStatus(StatusDTO status) {
		this.status = status;
	}
	public PayloadDTO getPayload() {
		return payload;
	}
	public void setPayload(PayloadDTO payload) {
		this.payload = payload;
	}
	
	
}

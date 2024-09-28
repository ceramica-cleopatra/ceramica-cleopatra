package com.sales.ccg.checkBalance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StatusDTO {
	private String statusMessage;
	private long responseTime;
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	
	

}

package com.sales.ccg.updatebalance.exception;

import com.sales.ccg.updatebalance.model.StatusDTO;

public class InvalidDateFormatException extends RuntimeException {
	private StatusDTO status;

	public InvalidDateFormatException(StatusDTO status) {
		this.status = status;
		System.out.println("Invalid Date Format Exception");
	}
	
	public StatusDTO getStatus() {
		return status;
	}

}

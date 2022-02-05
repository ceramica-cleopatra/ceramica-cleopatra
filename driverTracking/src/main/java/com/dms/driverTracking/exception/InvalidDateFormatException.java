package com.dms.driverTracking.exception;

import com.dms.driverTracking.dto.StatusDTO;

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

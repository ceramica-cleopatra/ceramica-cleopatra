package com.dms.driverTracking.exception;

import com.dms.driverTracking.dto.StatusDTO;

public class LessThanMinPageSizeException extends RuntimeException {
	private StatusDTO status;

	public LessThanMinPageSizeException(StatusDTO status) {
		this.status = status;
		System.out.println("Less Than Min Page Size Exception");
	}

	public StatusDTO getStatus() {
		return status;
	}

}

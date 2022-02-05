package com.dms.driverTracking.exception;

import com.dms.driverTracking.dto.StatusDTO;

public class InvalidNumberException extends RuntimeException {
	private StatusDTO status;

	public InvalidNumberException(StatusDTO status) {
		this.status = status;
		System.out.println("Invalid Number Exception");
	}

	public StatusDTO getStatus() {
		return status;
	}

}

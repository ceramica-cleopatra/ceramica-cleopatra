package com.dms.driverTracking.exception;

import com.dms.driverTracking.dto.StatusDTO;

public class LessThanMinPageNumberException extends RuntimeException {
	private StatusDTO status;

	public LessThanMinPageNumberException(StatusDTO status) {
		this.status = status;
	}

	public StatusDTO getStatus() {
		return status;
	}

}

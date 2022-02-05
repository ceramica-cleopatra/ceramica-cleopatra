package com.dms.driverTracking.exception;

import com.dms.driverTracking.dto.StatusDTO;

public class GreaterThanMaxPageNumberException extends RuntimeException {
	private StatusDTO status;

	public GreaterThanMaxPageNumberException(StatusDTO status) {
		this.status = status;
	}

	public StatusDTO getStatus() {
		return status;
	}

}

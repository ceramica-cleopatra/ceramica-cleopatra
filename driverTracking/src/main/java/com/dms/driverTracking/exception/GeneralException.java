package com.dms.driverTracking.exception;

import com.dms.driverTracking.dto.StatusDTO;

public class GeneralException extends RuntimeException {
	private StatusDTO status;

	public GeneralException(StatusDTO status) {
		this.status = status;
	}

	public StatusDTO getStatus() {
		return status;
	}

}

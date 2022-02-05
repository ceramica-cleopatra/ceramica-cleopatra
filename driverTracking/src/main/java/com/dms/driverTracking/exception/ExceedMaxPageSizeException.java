package com.dms.driverTracking.exception;

import com.dms.driverTracking.dto.StatusDTO;

public class ExceedMaxPageSizeException extends RuntimeException {
	private StatusDTO status;

	public ExceedMaxPageSizeException(StatusDTO status) {
		this.status = status;
		System.out.println("Exceed Maximum Page Size Exception");
	}

	public StatusDTO getStatus() {
		return status;
	}

}

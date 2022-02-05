package com.dms.exception;

import com.dms.dto.StatusDTO;

public class GeneralException extends RuntimeException {
	private StatusDTO status;

	public GeneralException(StatusDTO status) {
		this.status = status;
	}

	public StatusDTO getStatus() {
		return status;
	}

}

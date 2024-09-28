package com.query.exception;

import com.query.dto.StatusDTO;

public class GeneralException extends RuntimeException {
	private StatusDTO status;

	public GeneralException(StatusDTO status) {
		this.status = status;
	}

	public StatusDTO getStatus() {
		return status;
	}

}

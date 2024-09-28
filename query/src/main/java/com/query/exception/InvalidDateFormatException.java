package com.query.exception;

import com.query.dto.StatusDTO;

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

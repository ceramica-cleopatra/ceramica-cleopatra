package com.query.exception;

import com.query.dto.StatusDTO;

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

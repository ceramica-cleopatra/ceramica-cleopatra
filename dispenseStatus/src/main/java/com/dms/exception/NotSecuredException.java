package com.dms.exception;

import com.dms.dto.StatusDTO;

public class NotSecuredException extends RuntimeException{
	private StatusDTO status;

	public NotSecuredException(StatusDTO status) {
		this.status = status;
		System.out.println("Not Secured Request Exception");
	}

	public StatusDTO getStatus() {
		return status;
	}
}

package com.query.exception;

import com.query.dto.StatusDTO;

public class RequiredException extends RuntimeException{
	private StatusDTO status;
	public RequiredException(StatusDTO status){
		this.status=status;
	}
	
	public StatusDTO getStatus() {
		return status;
	}
}

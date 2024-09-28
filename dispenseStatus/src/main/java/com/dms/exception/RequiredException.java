package com.dms.exception;

import com.dms.dto.StatusDTO;

public class RequiredException extends RuntimeException{
	private StatusDTO statusDTO;
	public RequiredException(StatusDTO status){
		this.statusDTO=status;
	}
	public StatusDTO getStatusDTO() {
		return statusDTO;
	}
	public void setStatusDTO(StatusDTO statusDTO) {
		this.statusDTO = statusDTO;
	}
	
	
}

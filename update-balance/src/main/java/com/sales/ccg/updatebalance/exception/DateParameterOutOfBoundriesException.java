package com.sales.ccg.updatebalance.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sales.ccg.updatebalance.model.StatusDTO;

public class DateParameterOutOfBoundriesException extends RuntimeException{
	StatusDTO statusDTO ;

	public DateParameterOutOfBoundriesException(StatusDTO statusDTO) {
		super();
		this.statusDTO = statusDTO;
	}

	public StatusDTO getStatusDTO() {
		return statusDTO;
	}

	public void setStatusDTO(StatusDTO statusDTO) {
		this.statusDTO = statusDTO;
	}

}

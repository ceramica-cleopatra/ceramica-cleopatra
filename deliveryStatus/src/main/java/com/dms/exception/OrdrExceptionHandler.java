package com.dms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dms.dto.StatusDTO;
import com.dms.exception.InvalidDateFormatException;
import com.dms.exception.InvalidNumberException;
import com.dms.utility.StatusEnum;

@ControllerAdvice
public class OrdrExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidNumberException.class)
	public ResponseEntity<StatusDTO> handleInvalidNumberExcption(InvalidNumberException e, WebRequest webRequest) {
		return new ResponseEntity<StatusDTO>(e.getStatus(), HttpStatus.OK);
	}

	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<StatusDTO> handleInvalidDateFormatException(InvalidDateFormatException e,
			WebRequest webRequest) {
		return new ResponseEntity<StatusDTO>(e.getStatus(), HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StatusDTO> handleGeneralException(RuntimeException e,
			WebRequest webRequest) {
		StatusDTO status = new StatusDTO();
		e.printStackTrace();
		status.setCode(StatusEnum.Exception.getStatusCode());
		status.setMessage(StatusEnum.Exception.getStatusMessage());
		return new ResponseEntity<StatusDTO>(status, HttpStatus.OK);
	}
}

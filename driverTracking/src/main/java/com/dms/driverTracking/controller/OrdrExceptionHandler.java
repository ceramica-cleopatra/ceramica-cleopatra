package com.dms.driverTracking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dms.driverTracking.dto.ResponseDTO;
import com.dms.driverTracking.dto.StatusDTO;
import com.dms.driverTracking.exception.ExceedMaxPageSizeException;
import com.dms.driverTracking.exception.InvalidDateFormatException;
import com.dms.driverTracking.exception.InvalidNumberException;
import com.dms.driverTracking.utility.StatusEnum;

@ControllerAdvice
public class OrdrExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidNumberException.class)
	public ResponseEntity<ResponseDTO> handleInvalidNumberExcption(InvalidNumberException e, WebRequest webRequest) {
		ResponseDTO ordrPlan = new ResponseDTO();
		ordrPlan.setStatus(e.getStatus());
		return new ResponseEntity<ResponseDTO>(ordrPlan, HttpStatus.OK);
	}

	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<ResponseDTO> handleInvalidDateFormatException(InvalidDateFormatException e,
			WebRequest webRequest) {
		ResponseDTO ordrPlan = new ResponseDTO();
		ordrPlan.setStatus(e.getStatus());
		return new ResponseEntity<ResponseDTO>(ordrPlan, HttpStatus.OK);
	}
	
	@ExceptionHandler(ExceedMaxPageSizeException.class)
	public ResponseEntity<ResponseDTO> handleExceedMaxPageSizeException(ExceedMaxPageSizeException e,
			WebRequest webRequest) {
		ResponseDTO ordrPlan = new ResponseDTO();
		ordrPlan.setStatus(e.getStatus());
		return new ResponseEntity<ResponseDTO>(ordrPlan, HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseDTO> handleGeneralException(RuntimeException e,
			WebRequest webRequest) {
		ResponseDTO ordrPlan = new ResponseDTO();
		StatusDTO status = new StatusDTO();
		e.printStackTrace();
		status.setCode(StatusEnum.Exception.getStatusCode());
		status.setMessage(StatusEnum.Exception.getStatusMessage());
		ordrPlan.setStatus(status);
		return new ResponseEntity<ResponseDTO>(ordrPlan, HttpStatus.OK);
	}
}

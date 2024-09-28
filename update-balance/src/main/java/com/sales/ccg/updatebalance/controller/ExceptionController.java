package com.sales.ccg.updatebalance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sales.ccg.updatebalance.exception.DateParameterOutOfBoundriesException;
import com.sales.ccg.updatebalance.exception.InvalidDateFormatException;
import com.sales.ccg.updatebalance.model.ResponseDTO;


@ControllerAdvice
public class ExceptionController  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<ResponseDTO> handleInvalidDateFormatException(InvalidDateFormatException e) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(e.getStatus());
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@ExceptionHandler(DateParameterOutOfBoundriesException.class)
	public ResponseEntity<ResponseDTO> handleDateParameterOutOfBoundriesException(DateParameterOutOfBoundriesException e) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(e.getStatusDTO());
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
}
package com.dms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dms.dto.ResponseDTO;
import com.dms.dto.StatusDTO;
import com.dms.exception.InvalidDateFormatException;
import com.dms.exception.InvalidNumberException;
import com.dms.util.StatusEnum;

@ControllerAdvice
public class DispenseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidNumberException.class)
	public ResponseEntity<ResponseDTO> handleInvalidNumberExcption(InvalidNumberException e, WebRequest webRequest) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(e.getStatus());
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}

	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<ResponseDTO> handleInvalidDateFormatException(InvalidDateFormatException e,
			WebRequest webRequest) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(e.getStatus());
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@ExceptionHandler(RequiredException.class)
	public ResponseEntity<ResponseDTO> handleRequiredParameterException(RuntimeException e,
			WebRequest webRequest) {
		ResponseDTO responseDTO = new ResponseDTO();
		StatusDTO status = new StatusDTO();
		e.printStackTrace();
		status.setCode(StatusEnum.Required.getStatusCode());
		status.setMessage(StatusEnum.Required.getStatusMessage());
		responseDTO.setStatus(status);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@ExceptionHandler(NotSecuredException.class)
	public ResponseEntity<ResponseDTO> handleNotSecuredException(RuntimeException e,
			WebRequest webRequest) {
		ResponseDTO responseDTO = new ResponseDTO();
		StatusDTO status = new StatusDTO();
		e.printStackTrace();
		status.setCode(StatusEnum.NotSecured.getStatusCode());
		status.setMessage(StatusEnum.NotSecured.getStatusMessage());
		responseDTO.setStatus(status);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseDTO> handleGeneralException(RuntimeException e,
			WebRequest webRequest) {
		ResponseDTO responseDTO = new ResponseDTO();
		StatusDTO status = new StatusDTO();
		e.printStackTrace();
		status.setCode(StatusEnum.Exception.getStatusCode());
		status.setMessage(StatusEnum.Exception.getStatusMessage());
		responseDTO.setStatus(status);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
}

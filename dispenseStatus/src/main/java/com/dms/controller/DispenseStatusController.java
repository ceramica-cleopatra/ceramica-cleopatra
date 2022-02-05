package com.dms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dms.dto.OrdrDispenseStatusDTO;
import com.dms.dto.ResponseDTO;
import com.dms.dto.StatusDTO;
import com.dms.dto.TrnsOrdrDTO;
import com.dms.exception.GeneralException;
import com.dms.service.DispenseStatusService;
import com.dms.util.StatusEnum;
import com.dms.validation.DispenseStatusValidation;

@RestController
public class DispenseStatusController {
	
	@Autowired
	private DispenseStatusService dispenseStatusService;
	
	@Autowired
	private DispenseStatusValidation dispenseStatusValidation;
	
	@RequestMapping(value = "/ordrDispenseStatus", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getOrdrDispenseStatus(@RequestParam String trnsOrdrId,@RequestHeader("secured") String secured) {
		ResponseDTO responseDTO = new ResponseDTO();
		Long startTime =System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		dispenseStatusValidation.isSecured(status, secured, startTime);
		dispenseStatusValidation.validateNumber(status, trnsOrdrId, startTime);
		TrnsOrdrDTO trnsOrdrDTO = null;
		try{
			trnsOrdrDTO =dispenseStatusService.getOrdrDispenseStatus(Long.parseLong(trnsOrdrId));
			if(trnsOrdrDTO == null || trnsOrdrDTO.getDispenseStatus() == null){
				status.setCode(StatusEnum.NoResultFound.getStatusCode());
				status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
				status.setResponseTime(System.currentTimeMillis() - startTime);
				status.setResultCount(1L);
				responseDTO.setPayload(null);
				responseDTO.setStatus(status);
				return new ResponseEntity<>(responseDTO,HttpStatus.OK);
			}
		}catch(Exception e){
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			status.setResultCount(0L);
			e.printStackTrace();
			throw new GeneralException(status);
		}
		status.setCode(StatusEnum.Ok.getStatusCode());
		status.setMessage(StatusEnum.Ok.getStatusMessage());
		status.setResponseTime(System.currentTimeMillis() - startTime);
		status.setResultCount(1L);
		responseDTO.setPayload(trnsOrdrDTO);
		responseDTO.setStatus(status);
		return new ResponseEntity<>(responseDTO,HttpStatus.OK);
	}
	
}

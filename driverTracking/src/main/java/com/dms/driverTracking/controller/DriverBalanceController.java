package com.dms.driverTracking.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dms.driverTracking.dto.IntermediateDTO;
import com.dms.driverTracking.dto.ResponseDTO;
import com.dms.driverTracking.dto.StatusDTO;
import com.dms.driverTracking.service.driverBalance.DriverBalanceService;
import com.dms.driverTracking.service.driverTrans.DriverTrnsService;
import com.dms.driverTracking.utility.StatusEnum;
import com.dms.driverTracking.validation.ordr.RequestParameterValidation;

@RestController
@RequestMapping(value = "/driverBalance")
public class DriverBalanceController {
	@Autowired
	RequestParameterValidation requestParameterValidation;

	@Autowired
	DriverBalanceService driverBalanceService;

	@GetMapping
	public ResponseEntity<ResponseDTO> getDriverBalance(@RequestParam(required = true) String driverId,
			@RequestHeader("secured") String secured) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
		
		ResponseDTO driverBalance = new ResponseDTO();
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		requestParameterValidation.isSecured(status, secured, startTime);
		requestParameterValidation.validateNumber(status, driverId, startTime);
		IntermediateDTO intermediateDTO = driverBalanceService.getDriverBalance(Long.parseLong(driverId));
		if (intermediateDTO.getPayload() == null) {
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
			status.setResultCount(0L);
		} else {
			driverBalance.setPayload(intermediateDTO.getPayload());
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
			status.setResultCount(intermediateDTO.getResultCount());
		}
		status.setResponseTime(System.currentTimeMillis() - startTime);
		driverBalance.setStatus(status);
		return new ResponseEntity<>(driverBalance, HttpStatus.OK);
	}

}

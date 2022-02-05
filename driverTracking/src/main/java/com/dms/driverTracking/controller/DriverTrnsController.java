package com.dms.driverTracking.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dms.driverTracking.dto.IntermediateDTO;
import com.dms.driverTracking.dto.ResponseDTO;
import com.dms.driverTracking.dto.StatusDTO;
import com.dms.driverTracking.service.driverTrans.DriverTrnsService;
import com.dms.driverTracking.utility.StatusEnum;
import com.dms.driverTracking.validation.ordr.RequestParameterValidation;

@RestController
@RequestMapping(value = "/driverTrns")
public class DriverTrnsController {
	@Autowired
	RequestParameterValidation requestParameterValidation;

	@Autowired
	DriverTrnsService driverTrnsService;

	@GetMapping
	public ResponseEntity<ResponseDTO> getDriverTrns(@RequestParam(defaultValue = "1") String pageNo,
			@RequestParam(defaultValue = "25") String pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(required = false) String updateDate, @RequestParam(required = false) String driverId,
			@RequestHeader("secured") String secured) {
		ResponseDTO driverTrns = new ResponseDTO();
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		requestParameterValidation.isSecured(status, secured, startTime);
		requestParameterValidation.validateNumber(status, pageNo, startTime);
		requestParameterValidation.validateNumber(status, pageSize, startTime);
		requestParameterValidation.validateNumber(status, driverId, startTime);
		requestParameterValidation.validatePageSize(status, pageSize, startTime);
		Date date = requestParameterValidation.validateDateFormat(status, updateDate, startTime);
		IntermediateDTO intermediateDTO = driverTrnsService.findDriversTrnsList(Integer.parseInt(pageNo) - 1,
				Integer.parseInt(pageSize), sortBy, date, driverId != null ? Integer.parseInt(driverId) : null);
		if (intermediateDTO.getResultCount() != null && intermediateDTO.getResultCount() > 0L) {
			requestParameterValidation.validatePageNumber(status, Long.parseLong(pageNo), Long.parseLong(pageSize),
					intermediateDTO.getResultCount(), startTime);
		}

		if (intermediateDTO.getPayload() == null || ((List) intermediateDTO.getPayload()).isEmpty()) {
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
			status.setResultCount(0L);
		} else {
			driverTrns.setPayload((List) intermediateDTO.getPayload());
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
			status.setResultCount(intermediateDTO.getResultCount());
		}
		status.setResponseTime(System.currentTimeMillis() - startTime);
		driverTrns.setStatus(status);
		return new ResponseEntity<>(driverTrns, HttpStatus.OK);
	}

}

package com.dms.driverTracking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.dms.driverTracking.dto.OrdrDTO;
import com.dms.driverTracking.dto.IntermediateDTO;
import com.dms.driverTracking.dto.ResponseDTO;
import com.dms.driverTracking.dto.StatusDTO;
import com.dms.driverTracking.service.ordr.OrdrService;
import com.dms.driverTracking.utility.StatusEnum;
import com.dms.driverTracking.validation.ordr.RequestParameterValidation;

@RestController
@RequestMapping(value = "/ordrPlan")
public class OrdrController {
	@Autowired
	private OrdrService ordrService;

	@Autowired
	private RequestParameterValidation requestParameterValidation;

	@GetMapping
	public ResponseEntity<ResponseDTO> getOrdrPlan(@RequestParam(defaultValue = "1") String pageNo,
			@RequestParam(defaultValue = "25") String pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(required = false) String updateDate,@RequestHeader("secured") String secured) {
		ResponseDTO ordrPlanDTO =new ResponseDTO();
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		requestParameterValidation.isSecured(status, secured, startTime);
		requestParameterValidation.validateNumber(status, pageNo, startTime);
		requestParameterValidation.validateNumber(status, pageSize, startTime);
		requestParameterValidation.validatePageSize(status, pageSize, startTime);
		Date date = requestParameterValidation.validateDateFormat(status, updateDate, startTime);
		IntermediateDTO intermediateDTO = ordrService.findOrdrList(Integer.parseInt(pageNo) - 1,
				Integer.parseInt(pageSize), sortBy, date);
		if (intermediateDTO.getResultCount() != null && intermediateDTO.getResultCount() > 0L) {
			requestParameterValidation.validatePageNumber(status, Long.parseLong(pageNo), Long.parseLong(pageSize),
					intermediateDTO.getResultCount(), startTime);
		}
		
		if (intermediateDTO.getPayload() == null || ((List) intermediateDTO.getPayload()).isEmpty()) {
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
			status.setResultCount(0L);
		} else {
			ordrPlanDTO.setPayload((List)intermediateDTO.getPayload());
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
			status.setResultCount(intermediateDTO.getResultCount());
		}
		status.setResponseTime(System.currentTimeMillis() - startTime);
		ordrPlanDTO.setStatus(status);
		return new ResponseEntity<>(ordrPlanDTO, HttpStatus.OK);
	}

}

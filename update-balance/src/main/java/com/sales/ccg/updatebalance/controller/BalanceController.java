package com.sales.ccg.updatebalance.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sales.ccg.updatebalance.model.ProductDTO;
import com.sales.ccg.updatebalance.model.ResponseDTO;
import com.sales.ccg.updatebalance.model.StatusDTO;
import com.sales.ccg.updatebalance.model.StatusEnum;
import com.sales.ccg.updatebalance.service.ModifiedBalanceService;
import com.sales.ccg.updatebalance.validation.RequestParameterValidation;

@RestController
public class BalanceController {
	Logger logger = LoggerFactory.getLogger(BalanceController.class);
	@Autowired
	RequestParameterValidation requestParameterValidation;
	
	@Autowired
	private ModifiedBalanceService modifiedBalanceService;

	@GetMapping("/updatedProducts")
	public ResponseEntity<ResponseDTO> findUpdatedProducts(//@RequestHeader("secured") String secured,
			@RequestParam String modificationDate) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		ResponseDTO result = new ResponseDTO();
		requestParameterValidation.requiredParameter(status, modificationDate, startTime);
		Date date = requestParameterValidation.validateDateFormat(status, modificationDate, startTime);
		requestParameterValidation.validateDateValue(status, date, startTime);
		List<ProductDTO> resultList = modifiedBalanceService.findUpdatedProducts(date);

		if (resultList.isEmpty()) {
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
			status.setResultCount(0L);
		} else {
			result.setPayload(resultList);
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
			status.setResultCount(Long.parseLong(resultList.size()+""));
		}
		status.setResponseTime(System.currentTimeMillis() - startTime);
		result.setStatus(status);
		logger.info("Request updatedProducts ::: {} ::: RsponseStatus:{} ::: ResultCount:{} ::: ResponseTime:{}" , modificationDate ,status.getMessage(),status.getResultCount(),status.getResponseTime());
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
}

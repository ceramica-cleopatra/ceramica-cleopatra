package com.dms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dms.dto.OrdrDeliveryStatusDTO;
import com.dms.dto.StatusDTO;
import com.dms.exception.GeneralException;
import com.dms.service.DeliveryStatusUpdateService;
import com.dms.utility.StatusEnum;
import com.dms.validation.OrdrDeliveryStatusValidation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DeliveryStatusController {
	@Autowired
	private DeliveryStatusUpdateService deliveryStatusUpdateService;

	@Autowired
	private OrdrDeliveryStatusValidation ordrDeliveryStatusValidation;

	@RequestMapping(value = "/updateOrdrStatus", method = RequestMethod.GET)
    public ResponseEntity<StatusDTO> updateDeliveryOrdrStatus(@RequestParam String ordrDeliveryStatus,@RequestHeader("secured") String secured) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		ObjectMapper mapper = new ObjectMapper();
		OrdrDeliveryStatusDTO ordrDeliveryStatusDTO = null;
		try {
			ordrDeliveryStatusDTO = mapper.readValue(ordrDeliveryStatus, OrdrDeliveryStatusDTO.class);
			deliveryStatusUpdateService.updateOrdrDeliveryStatus(ordrDeliveryStatusDTO);
			ordrDeliveryStatusValidation.isSecured(status, secured, startTime);
			ordrDeliveryStatusValidation.validateRequired(status, ordrDeliveryStatusDTO.getDeliveryDate(), startTime, "deliveryDate");
			ordrDeliveryStatusValidation.validateRequired(status, ordrDeliveryStatusDTO.getOrdrId(), startTime, "ordrId");
			ordrDeliveryStatusValidation.validateRequired(status, ordrDeliveryStatusDTO.getStatus(), startTime, "status");
			ordrDeliveryStatusValidation.validateDateFormat(status, ordrDeliveryStatusDTO.getDeliveryDate(), startTime);
			ordrDeliveryStatusValidation.validateNumber(status, ordrDeliveryStatusDTO.getOrdrId(), startTime);
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		status.setCode(StatusEnum.Ok.getStatusCode());
		status.setMessage(StatusEnum.Ok.getStatusMessage());
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(0L);
        return new ResponseEntity<StatusDTO>(status,HttpStatus.OK);
    }
}

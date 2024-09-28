package com.sales.ccg.checkBalance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sales.ccg.checkBalance.dto.OrderBalanceRequestDTO;
import com.sales.ccg.checkBalance.dto.OrderBalanceResponseDTO;
import com.sales.ccg.checkBalance.service.BalanceService;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class BalanceController {
	Logger logger = LoggerFactory.getLogger(BalanceController.class);
	
	@Autowired
	private BalanceService balanceService;

	@PostMapping("/checkOrderBalance")
	public ResponseEntity<OrderBalanceResponseDTO> checkOrderBalance(@RequestHeader("secured") String secured,
			@RequestBody OrderBalanceRequestDTO ordr,@RequestParam(required = false,defaultValue="1") String version) {
		logger.info("Request checkOrderBalance ::: " + ordr.toString());
		return new ResponseEntity<OrderBalanceResponseDTO>(balanceService.checkOrderBalance(ordr,version), HttpStatus.OK);
	}
}

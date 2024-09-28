package com.sales.ccg.checkBalance.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sales.ccg.checkBalance.dto.OrderBalanceRequestDTO;
import com.sales.ccg.checkBalance.dto.OrderBalanceResponseDTO;
import com.sales.ccg.checkBalance.dto.PayloadDTO;
import com.sales.ccg.checkBalance.dto.ProductStatusDTO;
import com.sales.ccg.checkBalance.dto.StatusDTO;
import com.sales.ccg.checkBalance.repository.BalanceRepository;

@Component
public class BalanceService {
	@Autowired
	private BalanceRepository balanceRepository;

	public OrderBalanceResponseDTO checkOrderBalance(OrderBalanceRequestDTO order,String version){
		long startTime =System.currentTimeMillis();
		Integer counter[] ={0} ;
		String statusMsg[] = {"OK"};
		List<ProductStatusDTO> productList = new ArrayList<>();
		order.getProducts().forEach(item ->{
			BigDecimal result = null;
			if(version.equals("2")){
				result = balanceRepository.checkOrderBalance(item.getSku(), item.getQuantity(), order.getGovernorate(),item.getType());
			}else{
				result = balanceRepository.checkOrderBalanceV1(item.getSku(), item.getQuantity(), order.getGovernorate());
			}
			
			if(result == null){
				statusMsg[0] = "Invalid Inputs";
			}
			else if(item.getQuantity()<0){
				statusMsg[0] = "Quantity should be greater than Zero";
			}
			else if(result.equals(new BigDecimal("1"))){
				ProductStatusDTO productStatusDTO = new ProductStatusDTO();
				productStatusDTO.setSku(item.getSku());
				productStatusDTO.setHasStock(true);
				productList.add(productStatusDTO);
			}else{
				ProductStatusDTO productStatusDTO = new ProductStatusDTO();
				productStatusDTO.setSku(item.getSku());
				productStatusDTO.setHasStock(false);
				productList.add(productStatusDTO);
			}
			});
		
		return constructResponse(productList,statusMsg[0],(System.currentTimeMillis() - startTime));
	}
	
	private OrderBalanceResponseDTO constructResponse(List<ProductStatusDTO> productList,String statusMsg,long timeElapsed){
		
		OrderBalanceResponseDTO orderBalanceResponseDTO = new OrderBalanceResponseDTO();
		StatusDTO status = new StatusDTO();
		PayloadDTO payload= null;
		if(statusMsg.equals("OK")){
			payload = new PayloadDTO();
			payload.setProductList(productList);
			orderBalanceResponseDTO.setPayload(payload);
		}
		status.setStatusMessage(statusMsg);
		status.setResponseTime(timeElapsed);
		orderBalanceResponseDTO.setStatus(status);
		
		return orderBalanceResponseDTO;
	}

}

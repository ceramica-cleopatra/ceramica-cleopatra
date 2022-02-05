package com.dms.driverTracking.service.driverBalance.driverBalanceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.driverTracking.dao.DriverBalanceRepository;
import com.dms.driverTracking.dto.DriverBalanceDTO;
import com.dms.driverTracking.dto.DriverBalanceDetailDTO;
import com.dms.driverTracking.dto.IntermediateDTO;
import com.dms.driverTracking.model.DmsDriversUnderSrfPriceVW;
import com.dms.driverTracking.service.driverBalance.DriverBalanceService;

@Service("driverBalanceService")
public class DriverBalanceServiceImpl implements DriverBalanceService{

	@Autowired
	private DriverBalanceRepository driverBalanceRepository;
	
	private List<DmsDriversUnderSrfPriceVW> getDriverBalanceDetails(Long driverId){
		return driverBalanceRepository.findBalanceDetailsByDriverId(driverId);
	}
	
	private Double getDriverBalanceTotalValue(Long driverId){
		return driverBalanceRepository.findBalanceByDriverId(driverId);
	}

	public IntermediateDTO getDriverBalance(Long driverId){
		IntermediateDTO intermediateDTO = new IntermediateDTO();
		List<DriverBalanceDetailDTO> driverBalanceDetailsList = new ArrayList<DriverBalanceDetailDTO>();
		List<DmsDriversUnderSrfPriceVW> dmsDriversUnderSrfPriceVWs = getDriverBalanceDetails(driverId);
		for(DmsDriversUnderSrfPriceVW dmsDriversUnderSrfPriceVW : dmsDriversUnderSrfPriceVWs){
			DriverBalanceDetailDTO driverBalanceDetailDTO = new DriverBalanceDetailDTO.Builder()
					.setCrmkSehy(dmsDriversUnderSrfPriceVW.getCrmkSehy())
					.setOrdrDate(dmsDriversUnderSrfPriceVW.getTrnsOrdrDate())
					.setOrdrNo(dmsDriversUnderSrfPriceVW.getTrnsOrdrNo())
					.setShowroom(dmsDriversUnderSrfPriceVW.getShowroom())
					.setSrfDate(dmsDriversUnderSrfPriceVW.getDeliveryDate())
					.setSrfOrdrNo(dmsDriversUnderSrfPriceVW.getDeliveryOrdrNo())
					.setStore(dmsDriversUnderSrfPriceVW.getShowroom())
					.setPrice(dmsDriversUnderSrfPriceVW.getBalance())
					.build();
			driverBalanceDetailsList.add(driverBalanceDetailDTO);
		}
		
		DriverBalanceDTO driverBalanceDTO = new DriverBalanceDTO.Builder()
				.setBalance(getDriverBalanceTotalValue(driverId))
				.setDriverBalanceDetails(driverBalanceDetailsList)
				.setDriverId(driverId)
				.build();	
		intermediateDTO.setPayload(driverBalanceDTO);	
		intermediateDTO.setResultCount(1L);
		return intermediateDTO;
	}
	
}

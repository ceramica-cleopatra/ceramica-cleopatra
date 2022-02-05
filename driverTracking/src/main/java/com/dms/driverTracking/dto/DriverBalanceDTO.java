package com.dms.driverTracking.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class DriverBalanceDTO {
	private Long driverId;
	private Double balance;
	private List<DriverBalanceDetailDTO> driverBalanceDetailDTO;
	
	private DriverBalanceDTO(Builder builder) {
		this.driverId = builder.driverId;
		this.balance = builder.balance;
		this.driverBalanceDetailDTO = builder.driverBalanceDetailDTO;
	}
	
	public static class Builder{
		private Long driverId;
		private Double balance;
		private List<DriverBalanceDetailDTO> driverBalanceDetailDTO;
		
		public Builder setDriverId(Long driverId){
			this.driverId = driverId;
			return this;
		}
		
		public Builder setBalance(Double balance){
			this.balance = balance;
			return this;
		}
		
		public Builder setDriverBalanceDetails(List<DriverBalanceDetailDTO> driverBalanceDetailDTO){
			this.driverBalanceDetailDTO = driverBalanceDetailDTO;
			return this;
		}
		
		public DriverBalanceDTO build(){
			return new DriverBalanceDTO(this);
		}
	}
	
}

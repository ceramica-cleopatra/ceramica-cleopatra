package com.dms.driverTracking.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class DriverDTO {
	private Long driverId;
	private String driverName;
	private String driverType;
	private String status;
	private String carType;
	private String phoneNo;
	
	private DriverDTO(Builder builder){
		this.driverId=builder.driverId;
		this.driverName=builder.driverName;
		this.driverType=builder.driverType;
		this.status=builder.status;
		this.carType=builder.carType;
		this.phoneNo=builder.phoneNo;
	}
	
	public static class Builder{
		private Long driverId;
		private String driverName;
		private String driverType;
		private String status;
		private String carType;
		private String phoneNo;
		
		public DriverDTO build(){
			return new DriverDTO(this);
		}
		public Builder setDriverId(Long driverId){
			this.driverId=driverId;
			return this;
		}
		
		public Builder setDriverName(String driverName){
			this.driverName=driverName;
			return this;
		}
		
		public Builder setDriverType(String driverType){
			this.driverType=driverType;
			return this;
		}
		
		public Builder setStatus(String status){
			this.status=status;
			return this;
		}
		
		public Builder setPhoneNo(String phoneNo){
			this.phoneNo=phoneNo;
			return this;
		}
		
		public Builder setCarType(String carType){
			this.carType = carType;
			return this;
		}
	}
}

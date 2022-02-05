package com.dms.driverTracking.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class DriverBalanceDetailDTO {
	private Long ordrNo;
	private String showroom;
	private Date ordrDate;
	private String crmkSehy;
	private Date srfDate;
	private Long srfOrdrNo;
	private String store;
	private Double price;
	
	private DriverBalanceDetailDTO(Builder builder) {
		this.ordrNo = builder.ordrNo;
		this.showroom = builder.showroom;
		this.ordrDate = builder.ordrDate;
		this.crmkSehy = builder.crmkSehy;
		this.srfDate = builder.srfDate;
		this.srfOrdrNo = builder.srfOrdrNo;
		this.store = builder.store;
		this.price = builder.price;
	}
	
	public static class Builder{
		private Long ordrNo;
		private String showroom;
		private Date ordrDate;
		private String crmkSehy;
		private Date srfDate;
		private Long srfOrdrNo;
		private String store;
		private Double price;
		
		public Builder setOrdrNo(Long ordrNo){
			this.ordrNo = ordrNo;
			return this;
		}
		
		public Builder setShowroom(String showroom){
			this.showroom = showroom;
			return this;
		}
		
		public Builder setOrdrDate(Date ordrDate){
			this.ordrDate = ordrDate;
			return this;
		}
		
		public Builder setCrmkSehy(String crmkSehy){
			this.crmkSehy = crmkSehy;
			return this;
		}
		
		public Builder setSrfDate(Date srfDate){
			this.srfDate = srfDate;
			return this;
		}
		
		public Builder setSrfOrdrNo(Long srfOrdrNo){
			this.srfOrdrNo = srfOrdrNo;
			return this;
		}
		
		public Builder setStore(String store){
			this.store = store;
			return this;
		}
		
		public Builder setPrice(Double price){
			this.price = price;
			return this;
		}
		
		public DriverBalanceDetailDTO build(){
			return new DriverBalanceDetailDTO(this); 
		}
	} 
}

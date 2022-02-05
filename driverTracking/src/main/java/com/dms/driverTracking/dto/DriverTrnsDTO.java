package com.dms.driverTracking.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class DriverTrnsDTO {
	private Long id;
	private Long driverId;
	private String driverName;
	private String license;
	private String inTrns;
	private String outTrns;
	private Long storeId;
	private String storeName;
	private String modificationDate;
	
	private DriverTrnsDTO(Builder builder){
		this.id = builder.id;
		this.driverId = builder.driverId;
		this.driverName = builder.driverName;
		this.inTrns = builder.inTrns;
		this.license = builder.license;
		this.modificationDate = builder.modificationDate;
		this.outTrns = builder.outTrns;
		this.storeId = builder.storeId;
		this.storeName = builder.storeName;
	}
	
	public static class Builder{
		private Long id;
		private Long driverId;
		private String driverName;
		private String license;
		private String inTrns;
		private String outTrns;
		private Long storeId;
		private String storeName;
		private String modificationDate;
		
		public Builder setId(Long id){
			this.id = id;
			return this;
		}
		
		public Builder setDriverId(Long driverId){
			this.driverId = driverId;
			return this;
		}
		
		public Builder setDriverName(String driverName){
			this.driverName = driverName;
			return this;
		}
		
		public Builder setLicense(String license){
			this.license = license;
			return this;
		}
		
		public Builder setInTrns(String inTrns){
			this.inTrns = inTrns;
			return this;
		}
		
		public Builder setOutTrns(String outTrns){
			this.outTrns = outTrns;
			return this;
		}
		
		public Builder setStoreId(Long storeId){
			this.storeId = storeId;
			return this;
		}
		
		public Builder setModificationDate(String modificationDate){
			this.modificationDate = modificationDate;
			return this;
		}
		
		public Builder setStoreName(String storeName){
			this.storeName = storeName;
			return this;
		}
		
		public DriverTrnsDTO build(){
			return new DriverTrnsDTO(this);
		}
	}

}

package com.dms.driverTracking.dto;

import java.io.Serializable;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class OrdrDTO implements Serializable{
	private Long planId;
	private String planName;
	private Date trnsDate;
	private Long brnId;
	private String brnName;
	private Long ordrId;
	private DriverDTO driver;
	private OrdrDetailDTO ordrDetail;
	private String modificationDate;
	
	private OrdrDTO(Builder builder){
		this.ordrId=builder.ordrId;
		this.brnName=builder.brnName;
		this.planId=builder.planId;
		this.planName=builder.planName;
		this.modificationDate=builder.modificationDate;
		this.brnId=builder.brnId;
		this.driver=builder.driver;
		this.ordrDetail=builder.ordrDetail;
		this.trnsDate=builder.trnsDate;
	}
	
	public static class Builder{
		private Long id;
		private Long planId;
		private String planName;
		private Date trnsDate;
		private Long brnId;
		private String brnName;
		private Long ordrId;
		private DriverDTO driver;
		private OrdrDetailDTO ordrDetail;
		private String modificationDate;
		
		public Builder setPlanId(Long planId){
			this.planId=planId;
			return this;
		}
		
		public Builder setPlanName(String planName){
			this.planName=planName;
			return this;
		}
		
		public Builder setBrnId(Long brnId){
			this.brnId=brnId;
			return this;
		}
		
		public Builder setBrnName(String brnName){
			this.brnName=brnName;
			return this;
		}
		
		public Builder setOrdrId(Long ordrId){
			this.ordrId=ordrId;
			return this;
		}
		
		public Builder setDriver(DriverDTO driver){
			this.driver=driver;
			return this;
		}
		
		public Builder setTrnsDate(Date trnsDate){
			this.trnsDate=trnsDate;
			return this;
		}
		
		public Builder setModificationDate(String modificationDate){
			this.modificationDate=modificationDate;
			return this;
		}
		
		public Builder setOrdrDetail(OrdrDetailDTO ordrDetail){
			this.ordrDetail=ordrDetail;
			return this;
		}
		
		public OrdrDTO build(){
			return new OrdrDTO(this);
		}
	}
}

package com.dms.driverTracking.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class OrdrDetailDTO {
	private Long ordrNo;
	private String clientName;
	private String address;
	private String tel1;
	private String tel2;
	private String mobile;
	private String city;
	private String area;
	private Double totalQty;
	private String   delieveryFrom;
	private String   delieveryTo;
	private String longitude;
	private String latitude;
	
	private OrdrDetailDTO(Builder builder){
		this.ordrNo=builder.ordrNo;
		this.address=builder.address;
		this.area=builder.area;
		this.city=builder.city;
		this.clientName=builder.clientName;
		this.mobile=builder.mobile;
		this.tel1=builder.tel1;
		this.tel2=builder.tel2;
		this.totalQty=builder.totalQty;
		this.delieveryFrom=builder.delieveryFrom;
		this.delieveryTo=builder.delieveryTo;
		this.longitude=builder.longitude;
		this.latitude=builder.latitude;
	}
	
	public static class Builder{
		private Long ordrNo;
		private String clientName;
		private String address;
		private String tel1;
		private String tel2;
		private String mobile;
		private String city;
		private String area;
		private Double totalQty;
		private String   delieveryFrom;
		private String   delieveryTo;
		private String longitude;
		private String latitude;
		
		public Builder setClientName(String clientName){
			this.clientName=clientName;
			return this;
		}
		
		public Builder setAddress(String address){
			this.address=address;
			return this;
		}
		
		public Builder setTel1(String tel1){
			this.tel1=tel1;
			return this;
		}
		
		public Builder setTel2(String tel2){
			this.tel2=tel2;
			return this;
		}
		
		public Builder setMobile(String mobile){
			this.mobile=mobile;
			return this;
		}
		
		public Builder setCity(String city){
			this.city=city;
			return this;
		}
		
		public Builder setOrdrNo(Long ordrNo){
			this.ordrNo=ordrNo;
			return this;
		}
		
		public Builder setArea(String area){
			this.area=area;
			return this;
		}
		
		public Builder setTotalQty(Double totalQty){
			this.totalQty=totalQty;
			return this;
		}
		
		public Builder setDelieveryFrom(String delieveryFrom){
			this.delieveryFrom=delieveryFrom;
			return this;
		}
		
		public Builder setDelieveryTo(String delieveryTo){
			this.delieveryTo=delieveryTo;
			return this;
		}
		
		public Builder setLongitude(String longitude){
			this.longitude=longitude;
			return this;
		}
		
		public Builder setLatitude(String latitude){
			this.latitude=latitude;
			return this;
		}
		
		public OrdrDetailDTO build(){
			return new OrdrDetailDTO(this);
		}
	}
}

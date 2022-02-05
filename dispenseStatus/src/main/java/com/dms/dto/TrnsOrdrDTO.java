package com.dms.dto;

import java.util.List;

public class TrnsOrdrDTO {
	private Long trnsOrdrId;
	private String dispenseStatus;
	private List<OrdrDTO> ordrList;
	public Long getTrnsOrdrId() {
		return trnsOrdrId;
	}
	public void setTrnsOrdrId(Long trnsOrdrId) {
		this.trnsOrdrId = trnsOrdrId;
	}
	public String getDispenseStatus() {
		return dispenseStatus;
	}
	public void setDispenseStatus(String dispenseStatus) {
		this.dispenseStatus = dispenseStatus;
	}
	public List<OrdrDTO> getOrdrList() {
		return ordrList;
	}
	public void setOrdrList(List<OrdrDTO> ordrList) {
		this.ordrList = ordrList;
	}
	
}

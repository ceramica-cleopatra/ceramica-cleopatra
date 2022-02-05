package com.dms.dto;

import java.util.List;

public class OrdrDTO {
	private Long ordrId;
	private Long ordrNo;
	private String branchName;
	private String prdId;
	private String ordrType;
	private String clntName;
	private String trnsDate;
	private String dispenseStatus;
	private List<OrdrItemsDTO> itemList;
	public Long getOrdrId() {
		return ordrId;
	}
	public void setOrdrId(Long ordrId) {
		this.ordrId = ordrId;
	}
	public Long getOrdrNo() {
		return ordrNo;
	}
	public void setOrdrNo(Long ordrNo) {
		this.ordrNo = ordrNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getPrdId() {
		return prdId;
	}
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}
	public String getOrdrType() {
		return ordrType;
	}
	public void setOrdrType(String ordrType) {
		this.ordrType = ordrType;
	}
	public String getDispenseStatus() {
		return dispenseStatus;
	}
	public void setDispenseStatus(String dispenseStatus) {
		this.dispenseStatus = dispenseStatus;
	}
	public List<OrdrItemsDTO> getItemList() {
		return itemList;
	}
	public void setItemList(List<OrdrItemsDTO> itemList) {
		this.itemList = itemList;
	}
	public String getClntName() {
		return clntName;
	}
	public void setClntName(String clntName) {
		this.clntName = clntName;
	}
	public String getTrnsDate() {
		return trnsDate;
	}
	public void setTrnsDate(String trnsDate) {
		this.trnsDate = trnsDate;
	}
	
}

package com.dms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DMS_TRNS_ORDR_SRF_STATUS",schema="DMS")
public class DmsTrnsOrdrSrfStatus {
	@Id
	private Long id;
	@Column(name="TRNS_ORDR_ID")
	private Long trnsOrdrId;
	@Column(name="ORDR_ID")
	private Long ordrId;
	@Column(name="NO")
	private Long no;
	@Column(name="TRNS_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date trnsDate;
	@Column(name="BRANCH_NAME")
	private String branchName;
	@Column(name="CLNT_NAME")
	private String clntName;
	@Column(name="CRMK_SEHY")
	private String crmkSehy;
	@Column(name="PRD_ID")
	private String prdId;
	@Column(name="NO_C_TONE")
	private String noCTone;
	@Column(name="QTY")
	private Double qty;
	@Column(name="OUT_QTY")
	private Double outQty;
	@Column(name="CAN_QTY")
	private Double canQty;
	@Column(name="REMAIN")
	private Double remain;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTrnsOrdrId() {
		return trnsOrdrId;
	}
	public void setTrnsOrdrId(Long trnsOrdrId) {
		this.trnsOrdrId = trnsOrdrId;
	}
	public Long getOrdrId() {
		return ordrId;
	}
	public void setOrdrId(Long ordrId) {
		this.ordrId = ordrId;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCrmkSehy() {
		return crmkSehy;
	}
	public void setCrmkSehy(String crmkSehy) {
		this.crmkSehy = crmkSehy;
	}
	public String getPrdId() {
		return prdId;
	}
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}
	public String getNoCTone() {
		return noCTone;
	}
	public void setNoCTone(String noCTone) {
		this.noCTone = noCTone;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getRemain() {
		return remain;
	}
	public void setRemain(Double remain) {
		this.remain = remain;
	}
	public Date getTrnsDate() {
		return trnsDate;
	}
	public void setTrnsDate(Date trnsDate) {
		this.trnsDate = trnsDate;
	}
	public String getClntName() {
		return clntName;
	}
	public void setClntName(String clntName) {
		this.clntName = clntName;
	}
	public Double getOutQty() {
		return outQty;
	}
	public void setOutQty(Double outQty) {
		this.outQty = outQty;
	}
	public Double getCanQty() {
		return canQty;
	}
	public void setCanQty(Double canQty) {
		this.canQty = canQty;
	}
	
}

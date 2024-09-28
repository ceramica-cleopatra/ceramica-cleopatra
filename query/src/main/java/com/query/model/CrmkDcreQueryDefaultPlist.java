package com.query.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CRMK_DCRE_QUERY_DEFAULT_PLIST",schema="CRMK")
public class CrmkDcreQueryDefaultPlist {

	@Id
	@Column(name="No")
	private Long no;
	
	@Column(name="ID")
	private Long id;
	
	@Column(name="TRNS_DATE")
	private String trnsDate;
	
	@Column(name="NOTES")
	private String notes;
	
	@Column(name="DEFAULT_VALUE")
	private String defaultValue;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTrnsDate() {
		return trnsDate;
	}

	public void setTrnsDate(String trnsDate) {
		this.trnsDate = trnsDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}

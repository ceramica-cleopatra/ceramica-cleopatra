package com.query.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HR_QUERY_APP_LOG",schema="HR")
public class HrQueryAppLog {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HR_QUERY_APP_LOG_SEQ")
	@SequenceGenerator(schema="HR" ,name = "HR_QUERY_APP_LOG_SEQ",sequenceName = "HR_QUERY_APP_LOG_SEQ",allocationSize = 1)  
@Id
private Long id;
@Column(name="EMP_NO")
private Long empNo;
@Column(name="TRNS_DATE")
@Temporal(TemporalType.TIMESTAMP)
private Date trnsDate;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getEmpNo() {
	return empNo;
}
public void setEmpNo(Long empNo) {
	this.empNo = empNo;
}
public Date getTrnsDate() {
	return trnsDate;
}
public void setTrnsDate(Date trnsDate) {
	this.trnsDate = trnsDate;
}

}

package com.dms.model;

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
@Table(name="DMS_ORDR_DELIVERY_STATUS",schema="DMS")
@SequenceGenerator(initialValue=1,sequenceName="DMS_ORDR_DELIVERY_STATUS_SEQ",schema="DMS", name = "DMS_ORDR_DELIVERY_STATUS_SEQ", allocationSize = 1)
public class DmsOrdrDeliveryStatus {
@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="DMS_ORDR_DELIVERY_STATUS_SEQ")
@Id
private Long id;
@Column(name="ORDR_ID")
private Long ordrId;
@Temporal(TemporalType.TIMESTAMP)
@Column(name="DELIVERY_DATE")
private Date deliveryDate;
@Column(name="STATUS")
private String status;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getOrdrId() {
	return ordrId;
}
public void setOrdrId(Long ordrId) {
	this.ordrId = ordrId;
}
public Date getDeliveryDate() {
	return deliveryDate;
}
public void setDeliveryDate(Date deliveryDate) {
	this.deliveryDate = deliveryDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}

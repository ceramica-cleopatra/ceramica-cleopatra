package com.dms.dto;

import java.util.List;

public class ResponseDTO {
private StatusDTO status;
private Object payload;
public StatusDTO getStatus() {
	return status;
}
public void setStatus(StatusDTO status) {
	this.status = status;
}
public Object getPayload() {
	return payload;
}
public void setPayload(Object payload) {
	this.payload = payload;
}

}

package com.sales.ccg.updatebalance.model;

public class StatusDTO {
private Long code;
private String message;
private Long responseTime;
private Long resultCount;
public Long getCode() {
	return code;
}
public void setCode(Long code) {
	this.code = code;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Long getResponseTime() {
	return responseTime;
}
public void setResponseTime(Long responseTime) {
	this.responseTime = responseTime;
}
public Long getResultCount() {
	return resultCount;
}
public void setResultCount(Long resultCount) {
	this.resultCount = resultCount;
}

}

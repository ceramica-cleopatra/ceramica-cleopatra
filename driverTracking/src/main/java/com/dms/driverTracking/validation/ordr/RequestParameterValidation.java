package com.dms.driverTracking.validation.ordr;

import java.util.Date;

import com.dms.driverTracking.dto.StatusDTO;

public interface RequestParameterValidation {
	public Date validateDateFormat(StatusDTO status,String date,Long startTime);
	public void validatePageNumber(StatusDTO status,Long pageNumber,Long pageSize,Long resultCount,Long startTime);
	public void validatePageSize(StatusDTO status,String pageSize,Long startTime);
	public void validateNumber(StatusDTO status,String value,Long startTime);
	public void isSecured(StatusDTO status, String value, Long startTime);
}

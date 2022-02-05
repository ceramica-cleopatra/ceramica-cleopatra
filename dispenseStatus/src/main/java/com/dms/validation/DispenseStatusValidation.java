package com.dms.validation;

import com.dms.dto.StatusDTO;

public interface DispenseStatusValidation {
	public void validateDateFormat(StatusDTO status,String date,Long startTime);
	public void validateNumber(StatusDTO status,String value,Long startTime);
	public void validateRequired(StatusDTO status,Object value,Long startTime,String field);
	public void isSecured(StatusDTO status, String value, Long startTime);
}

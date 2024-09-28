package com.query.validation;

import java.util.Date;
import com.query.dto.StatusDTO;

public interface QueryValidation {
	public void validateDateFormat(StatusDTO status,String date,Long startTime);
	public void validateNumber(StatusDTO status,String value,Long startTime);
	public void validateRequired(StatusDTO status,Object value,Long startTime,String field);
	public void isSecured(StatusDTO status, String value, Long startTime);
}

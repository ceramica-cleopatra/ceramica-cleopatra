package com.sales.ccg.updatebalance.validation;

import java.util.Date;

import com.sales.ccg.updatebalance.model.StatusDTO;



public interface RequestParameterValidation {
	public Date validateDateFormat(StatusDTO status,String date,Long startTime);
	public void validateDateValue(StatusDTO status, Date date, Long startTime);
	public void requiredParameter(StatusDTO status, String date, Long startTime);

}

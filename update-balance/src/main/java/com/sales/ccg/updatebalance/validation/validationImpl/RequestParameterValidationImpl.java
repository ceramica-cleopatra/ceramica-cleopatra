package com.sales.ccg.updatebalance.validation.validationImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sales.ccg.updatebalance.exception.DateParameterOutOfBoundriesException;
import com.sales.ccg.updatebalance.exception.InvalidDateFormatException;
import com.sales.ccg.updatebalance.exception.RequiredException;
import com.sales.ccg.updatebalance.model.StatusDTO;
import com.sales.ccg.updatebalance.model.StatusEnum;
import com.sales.ccg.updatebalance.validation.RequestParameterValidation;


@Component("requestParameterValidation")
public class RequestParameterValidationImpl implements RequestParameterValidation {
	
	Logger logger = LoggerFactory.getLogger(RequestParameterValidationImpl.class);

	@Override
	public Date validateDateFormat(StatusDTO status, String date, Long startTime) {
		if (date == null) {
			return null;
		}
		Date updateDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			updateDate = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			status.setCode(StatusEnum.InvalidDateFormat.getStatusCode());
			status.setMessage(StatusEnum.InvalidDateFormat.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			logger.error("Invalid Date Format ::: {}" , updateDate);
			throw new InvalidDateFormatException(status);
		}

		return updateDate;
	}

	
	@Override
	public void validateDateValue(StatusDTO status, Date date, Long startTime) {
		Date currentDate = new Date();
		if(currentDate.getTime() - date.getTime()>(3*60*60*1000)){
			status.setCode(StatusEnum.DateOutOfBoundaries.getStatusCode());
			status.setMessage(StatusEnum.DateOutOfBoundaries.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			logger.error("Date Parameter Out Of Boundries ::: {}" , date);
			throw new DateParameterOutOfBoundriesException(status);
		}
	}
	
	
	@Override
	public void requiredParameter(StatusDTO status, String date, Long startTime) {
		if(date==null || date.isEmpty()){
			status.setCode(StatusEnum.RequiredParameter.getStatusCode());
			status.setMessage(StatusEnum.RequiredParameter.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			logger.error("Missing required parameter" , date);
			throw new RequiredException(status);
		}
	}

}

package com.query.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.query.exception.InvalidDateFormatException;
import com.query.exception.InvalidNumberException;
import com.query.utility.StatusEnum;
import com.query.exception.NotSecuredException;
import com.query.exception.RequiredException;
import com.query.dto.StatusDTO;

@Component("queryValidation")
public class QueryValidationImpl implements QueryValidation{

	@Override
	public void validateDateFormat(StatusDTO status, String date, Long startTime) {
		Date deliveryDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			deliveryDate = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			status.setCode(StatusEnum.InvalidDateFormat.getStatusCode());
			status.setMessage(StatusEnum.InvalidDateFormat.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			throw new InvalidDateFormatException(status);
		}
		
	}

	@Override
	public void validateNumber(StatusDTO status, String value, Long startTime) {
		if (value != null) {
			Pattern pattern = Pattern.compile("[0-9]+");
			Matcher matcher = pattern.matcher(value);
			if (!matcher.matches()) {
				status.setCode(StatusEnum.InvalidNumber.getStatusCode());
				status.setMessage(StatusEnum.InvalidNumber.getStatusMessage());
				status.setResponseTime(System.currentTimeMillis() - startTime);
				throw new InvalidNumberException(status);
			}
		}
	}

	@Override
	public void isSecured(StatusDTO status, String value, Long startTime) {
		if (!value.equals("true")) {
			status.setCode(StatusEnum.NotSecured.getStatusCode());
			status.setMessage(StatusEnum.NotSecured.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			throw new NotSecuredException(status);
		}
		
	}

	@Override
	public void validateRequired(StatusDTO status, Object value, Long startTime,String field) {
		if(value==null || value.toString().equals("")){
			status.setCode(StatusEnum.Required.getStatusCode());
			status.setMessage(field+StatusEnum.Required.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			throw new RequiredException(status);
		}
		
	}

}

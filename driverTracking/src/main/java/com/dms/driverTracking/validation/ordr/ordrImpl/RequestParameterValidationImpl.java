package com.dms.driverTracking.validation.ordr.ordrImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.dms.driverTracking.dto.StatusDTO;
import com.dms.driverTracking.exception.ExceedMaxPageSizeException;
import com.dms.driverTracking.exception.GreaterThanMaxPageNumberException;
import com.dms.driverTracking.exception.InvalidDateFormatException;
import com.dms.driverTracking.exception.InvalidNumberException;
import com.dms.driverTracking.exception.LessThanMinPageNumberException;
import com.dms.driverTracking.exception.LessThanMinPageSizeException;
import com.dms.driverTracking.exception.NotSecuredException;
import com.dms.driverTracking.utility.StatusEnum;
import com.dms.driverTracking.validation.ordr.RequestParameterValidation;

@Component("requestParameterValidation")
public class RequestParameterValidationImpl implements RequestParameterValidation {

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
			throw new InvalidDateFormatException(status);
		}

		return updateDate;
	}

	@Override
	public void validatePageNumber(StatusDTO status, Long pageNumber, Long pageSize, Long resultCount, Long startTime) {
		if (pageNumber < 1) {
			status.setCode(StatusEnum.LessThanMinPageNumber.getStatusCode());
			status.setMessage(StatusEnum.LessThanMinPageNumber.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			System.out.println("Less Than Min PageSize Exception");
			throw new LessThanMinPageNumberException(status);
		}

		if (resultCount > pageSize && pageNumber > (resultCount / pageSize) + 1) {
			status.setCode(StatusEnum.GreaterThanMaxPageNumber.getStatusCode());
			status.setMessage(StatusEnum.GreaterThanMaxPageNumber.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			System.out.println("Greater Than Max Page Number Exception");
			throw new GreaterThanMaxPageNumberException(status);
		}
	}

	@Override
	public void validatePageSize(StatusDTO status, String pageSize, Long startTime) {
		if (Integer.parseInt(pageSize) > 100) {
			status.setCode(StatusEnum.MaxPageSizeExceeded.getStatusCode());
			status.setMessage(StatusEnum.MaxPageSizeExceeded.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			throw new ExceedMaxPageSizeException(status);
		}

		if (Integer.parseInt(pageSize) < 1) {
			status.setCode(StatusEnum.LessThanMinSize.getStatusCode());
			status.setMessage(StatusEnum.LessThanMinSize.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis() - startTime);
			throw new LessThanMinPageSizeException(status);
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

}

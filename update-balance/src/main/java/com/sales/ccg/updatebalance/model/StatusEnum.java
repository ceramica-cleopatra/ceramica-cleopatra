package com.sales.ccg.updatebalance.model;

public enum StatusEnum {
	Ok(100L, "Ok"), InvalidNumber(101L, "Invalid Number"), InvalidDateFormat(102L,
			"Invalid Date Format; Valid Format is dd-mm-yyyy hh24:mi"), Exception(103L,
					"Exception"), MaxPageSizeExceeded(104L,
							"Maximum Page Size (100 row) has been exceeded"), LessThanMinSize(105L,
									"Less Than Minimum Page Size(1) limit"), LessThanMinPageNumber(106L,
											"Less Than Min Page Number limit"), GreaterThanMaxPageNumber(107L,
													"Greater Than Max Page Number"), NoResultFound(404L,
															"No Result Found"),NotSecured(108L,"Request is not Secured"),DateOutOfBoundaries(109L,"Date Parameter Out Of Boundries Exception")
		,RequiredParameter(110L,"Missing Required Parameter");
	private final Long statusCode;
	private final String statusMessage;

	private StatusEnum(Long statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public Long getStatusCode() {
		return this.statusCode;
	}

	public String getStatusMessage() {
		return this.statusMessage;
	}

}
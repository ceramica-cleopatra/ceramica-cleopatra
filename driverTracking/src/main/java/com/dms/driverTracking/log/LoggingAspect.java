package com.dms.driverTracking.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dms.driverTracking.dto.ResponseDTO;

@Aspect
@Configuration
public class LoggingAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Around(value = "execution(* com.dms.driverTracking.controller.*.get*(..)) || execution(* com.dms.driverTracking.controller.*.handle*(..))")
	public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
	                .currentRequestAttributes())
	                .getRequest();
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			String statusMsg = null;
			Long statusCode = 0L;
			Long responseTime = 0L;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			if(value!=null){
				statusCode = ((ResponseEntity<ResponseDTO>)value).getBody().getStatus().getCode();
				statusMsg = ((ResponseEntity<ResponseDTO>)value).getBody().getStatus().getMessage();
				responseTime = ((ResponseEntity<ResponseDTO>)value).getBody().getStatus().getResponseTime();
			}

			String requestParametrers = "";
			for(Entry<String, String[]> entry: request.getParameterMap().entrySet()){
				requestParametrers += "Parameter:"+entry.getKey()+" value:"+((String[])entry.getValue())[0]+"$$";
			}
			logger.trace(
                "{}:::{}:::{}:::{}:::{}:::{}:::{}",
                dateFormat.format(new Date()),
                request.getRequestURI(),
                requestParametrers,
                request.getRemoteAddr(),
                statusCode,
                statusMsg,
                responseTime);
				
		}
		return value;
	}
	
}

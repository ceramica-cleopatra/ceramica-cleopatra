package com.dms.driverTracking;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dms.driverTracking.ccgOracleSync.TrnsOrdrStatusSync;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DriverTrackingApplication extends SpringBootServletInitializer {

	@PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("EET"));
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DriverTrackingApplication.class, args);
		//TrnsOrdrStatusSync x = new TrnsOrdrStatusSync();
		//x.syncTrnsOrdrStatus();
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		TimeZone.setDefault(TimeZone.getTimeZone("EET"));
		return application.sources(DriverTrackingApplication.class);
	}

}

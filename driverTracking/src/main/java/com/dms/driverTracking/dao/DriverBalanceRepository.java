package com.dms.driverTracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dms.driverTracking.model.DmsDriversUnderSrfPriceVW;

public interface DriverBalanceRepository extends CrudRepository<DmsDriversUnderSrfPriceVW, Long>{
	@Query("SELECT e FROM DmsDriversUnderSrfPriceVW e WHERE e.driverId=:driverId AND e.balance>0")
	List<DmsDriversUnderSrfPriceVW> findBalanceDetailsByDriverId(@Param("driverId") Long driverId);
	
	@Query(value="SELECT DMS.DMS_DRIVER_BAL_CALC (:driverId ,NULL ,SYSDATE-60 ,NULL ) FROM DUAL",nativeQuery=true)
	Double findBalanceByDriverId(@Param("driverId") Long driverId);
}

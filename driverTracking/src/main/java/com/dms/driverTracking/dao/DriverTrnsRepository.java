package com.dms.driverTracking.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.dms.driverTracking.model.DmsDriverTrnsVW;
import com.dms.driverTracking.model.DmsPlanLiveVW;

public interface DriverTrnsRepository extends PagingAndSortingRepository<DmsDriverTrnsVW, Long> {
	@Query("SELECT u FROM DmsDriverTrnsVW u WHERE (u.modificationDate>=:updateDate OR :updateDate IS NULL) AND (u.driverId=:drvId OR :drvId IS NULL)")    
	Page<DmsDriverTrnsVW> findDriverTrnsList(Pageable pageRequest,@Param("updateDate") Date updateDate,@Param("drvId") Integer driverId);
}

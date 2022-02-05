package com.dms.driverTracking.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.dms.driverTracking.model.DmsPlanLiveVW;

public interface OrdrRepository extends PagingAndSortingRepository<DmsPlanLiveVW, Long> {
	@Query("SELECT u FROM DmsPlanLiveVW u WHERE (u.modificationDate>=:updateDate or :updateDate IS NULL)")    
	Page<DmsPlanLiveVW> findOrdrList(Pageable pageRequest,@Param("updateDate") Date updateDate);
}

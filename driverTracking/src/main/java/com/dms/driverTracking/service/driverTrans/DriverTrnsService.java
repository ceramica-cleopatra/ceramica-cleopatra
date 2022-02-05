package com.dms.driverTracking.service.driverTrans;

import java.util.Date;

import com.dms.driverTracking.dto.IntermediateDTO;

public interface DriverTrnsService {
	public IntermediateDTO findDriversTrnsList(Integer pageNo, Integer pageSize, String sortBy,Date updateDate, Integer driverId);
}

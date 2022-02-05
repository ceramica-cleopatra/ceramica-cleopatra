package com.dms.driverTracking.service.driverBalance;

import com.dms.driverTracking.dto.IntermediateDTO;

public interface DriverBalanceService {
	public IntermediateDTO getDriverBalance(Long driverId);
}

package com.dms.driverTracking.service.ordr;

import java.util.Date;
import com.dms.driverTracking.dto.IntermediateDTO;

public interface OrdrService {
	IntermediateDTO findOrdrList(Integer pageNo, Integer pageSize, String sortBy,Date updateDate);
}

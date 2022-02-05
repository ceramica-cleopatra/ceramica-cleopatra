package com.dms.service;

import com.dms.dto.TrnsOrdrDTO;

public interface DispenseStatusService {
	public TrnsOrdrDTO getOrdrDispenseStatus(Long trnsOrdrId);
}

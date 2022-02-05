package com.dms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dms.model.DmsTrnsOrdrSrfStatus;

public interface DispenseStatusDao extends CrudRepository<DmsTrnsOrdrSrfStatus, Long> {
	@Query("SELECT d FROM DmsTrnsOrdrSrfStatus d WHERE d.trnsOrdrId=:trnsOrdrId")
	public List<DmsTrnsOrdrSrfStatus> getOrdrDispenseStatus(@Param("trnsOrdrId") Long trnsOrdrId);
	@Query(nativeQuery = true, value = "SELECT CRMK.GET_ORDR_SRF_STATUS(:ordrId) FROM dual")
    public Long getOrdrSrfStatus(@Param("ordrId") Long ordrId);
}

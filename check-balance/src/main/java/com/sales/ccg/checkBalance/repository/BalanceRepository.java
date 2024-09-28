package com.sales.ccg.checkBalance.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sales.ccg.checkBalance.model.CrmkOrdrHd;

@Repository
public interface BalanceRepository extends JpaRepository<CrmkOrdrHd, Long>{
	@Query(value="SELECT CRMK.GET_C_WEB_FREE_V2(:sku,:qty,:govId,:type) FROM DUAL",nativeQuery=true)
	public BigDecimal checkOrderBalance(@Param(value = "sku") Long sku,@Param(value="qty") Double qty,@Param(value="govId")Long govId,@Param(value="type") String type);
	
	@Query(value="SELECT CRMK.GET_C_WEB_FREE(:sku,:qty,:govId) FROM DUAL",nativeQuery=true)
	public BigDecimal checkOrderBalanceV1(@Param(value = "sku") Long sku,@Param(value="qty") Double qty,@Param(value="govId")Long govId);
}

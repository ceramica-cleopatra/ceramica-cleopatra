package com.sales.ccg.updatebalance.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sales.ccg.updatebalance.entity.CrmkSKULstModifiedQty;

@Repository
public interface CrmkRepository extends JpaRepository<CrmkSKULstModifiedQty, Long> {
	@Query("SELECT e FROM CrmkSKULstModifiedQty e WHERE e.lastModifiedDate>=:modificationDate")
	public List<CrmkSKULstModifiedQty> findProductQtyUpdates(@Param("modificationDate") Date modificationDate);
}

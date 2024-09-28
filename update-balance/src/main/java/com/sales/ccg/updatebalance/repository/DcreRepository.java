package com.sales.ccg.updatebalance.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sales.ccg.updatebalance.entity.DcreSKULstModifiedQty;

@Repository
public interface DcreRepository extends JpaRepository<DcreSKULstModifiedQty, Long> {
	@Query("SELECT e FROM DcreSKULstModifiedQty e WHERE e.lastModifiedDate>=:modificationDate")
	public List<DcreSKULstModifiedQty> findProductQtyUpdates(@Param("modificationDate") Date modificationDate);
}

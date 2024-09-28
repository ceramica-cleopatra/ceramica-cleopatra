package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkCrmkSize;

public interface CrmkSizeRepository extends CrudRepository<CrmkCrmkSize, Long>{
	@Query("select c from CrmkCrmkSize c where (c.id=:sizeId or :sizeId is null) and (c.name like :sizeName or :sizeName is null)")
	List<CrmkCrmkSize> getCrmkSize(@Param("sizeId") Long id,@Param("sizeName") String name);
}

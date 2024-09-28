package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkDcreSize;

public interface DcreSizeRepository extends CrudRepository<CrmkDcreSize, Long>{
	@Query("select c from CrmkDcreSize c where (c.id=:sizeId or :sizeId is null) and (c.name like :sizeName or :sizeName is null)")
	List<CrmkDcreSize> getDcreSize(@Param("sizeId") Long id,@Param("sizeName") String name);
}

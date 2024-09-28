package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkDcreType;

public interface DcreTypeRepository extends CrudRepository<CrmkDcreType, Long>{
	@Query("select c from CrmkDcreType c where (c.id=:typeId or :typeId is null) and (c.name like :typeName or :typeName is null)")
	List<CrmkDcreType> getDcreTypes(@Param("typeId") Long id,@Param("typeName") String name);
}

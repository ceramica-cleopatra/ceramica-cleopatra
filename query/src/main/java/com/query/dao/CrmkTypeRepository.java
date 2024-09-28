package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkCrmkType;

public interface CrmkTypeRepository extends CrudRepository<CrmkCrmkType, Long>{
	@Query("select c from CrmkCrmkType c where (c.id=:typeId or :typeId is null) and (c.name like :typeName or :typeName is null)")
	List<CrmkCrmkType> getCrmkTypes(@Param("typeId") Long id,@Param("typeName") String name);
}

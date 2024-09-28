package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkCrmkType;
import com.query.model.CrmkSehyType;

public interface SehyTypeRepository extends CrudRepository<CrmkSehyType, Long>{
	@Query("select c from CrmkSehyType c where (c.id=:typeId or :typeId is null) and (c.name like :typeName or :typeName is null) order by c.id")
	List<CrmkSehyType> getSehyTypes(@Param("typeId") Long id,@Param("typeName") String name);
}

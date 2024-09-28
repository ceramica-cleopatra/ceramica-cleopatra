package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkCrmkType;
import com.query.model.CrmkSehyName;
import com.query.model.CrmkSehyType;

public interface SehyNameRepository extends CrudRepository<CrmkSehyName, Long>{
	@Query("select c from CrmkSehyName c where (c.id=:typeId or :typeId is null) and (c.name like :typeName or :typeName is null) order by c.id")
	List<CrmkSehyName> getSehyNames(@Param("typeId") Long id,@Param("typeName") String name);
}

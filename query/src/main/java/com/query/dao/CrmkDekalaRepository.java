package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkCrmkDekala;

public interface CrmkDekalaRepository extends CrudRepository<CrmkCrmkDekala, Long>{
	@Query("select c from CrmkCrmkDekala c where (c.id=:dekalaId or :dekalaId is null) and (c.name like :dekalaName or :dekalaName is null)")
	List<CrmkCrmkDekala> getCrmkDekalas(@Param("dekalaId") Long id,@Param("dekalaName") String name);
}

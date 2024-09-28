package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkCrmkDekala;
import com.query.model.CrmkSehyDekala;

public interface SehyDekalaRepository extends CrudRepository<CrmkSehyDekala, Long>{
	@Query("select c from CrmkSehyDekala c where (c.id=:dekalaId or :dekalaId is null) and (c.name like :dekalaName or :dekalaName is null)")
	List<CrmkSehyDekala> getSehyDekalas(@Param("dekalaId") Long id,@Param("dekalaName") String name);
}

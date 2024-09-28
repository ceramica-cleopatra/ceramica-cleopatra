package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkDcreDekala;

public interface DcreDekalaRepository extends CrudRepository<CrmkDcreDekala, Long>{
	@Query("select c from CrmkDcreDekala c where (c.id=:dekalaId or :dekalaId is null) and (c.name like :dekalaName or :dekalaName is null)")
	List<CrmkDcreDekala> getDcreDekalas(@Param("dekalaId") Long id,@Param("dekalaName") String name);
}

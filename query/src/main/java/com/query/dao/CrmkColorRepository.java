package com.query.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkColor;

public interface CrmkColorRepository extends CrudRepository<CrmkColor, Long>{
	@Query("select c from CrmkColor c where (c.id=:colorId or :colorId is null) and (c.name like :colorName or :colorName is null)")
	List<CrmkColor> getCrmkColors(@Param("colorId") Long id,@Param("colorName") String name);
}

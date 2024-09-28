package com.query.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkSehySingleDetailVU;
import com.query.model.CrmkSehyTakmDetailVU;
import com.query.model.CrmkSehyTakmVU;

public interface SehySingleDetailSearchRepository extends CrudRepository<CrmkSehySingleDetailVU, Long>{
	@Query("select c from CrmkSehySingleDetailVU c where (c.typeId=:typeId or :typeId is null) and (c.nameId=:nameId or :nameId is null) and (c.dekalaId=:dekalaId or :dekalaId is null) "
			+ "and (c.colorId=:colorId or :colorId is null) and (c.frz=:frz or :frz is null) order by c.governId")
	List<CrmkSehySingleDetailVU> getSehyTakmSearchResult(@Param("typeId") Long typeId,@Param("nameId") Long nameId,@Param("dekalaId") Long dekalaId,@Param("colorId") Long colorId,
			@Param("frz") Long frz);

}

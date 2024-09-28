package com.query.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkSehySingleVU;
import com.query.model.CrmkSehyTakmVU;

public interface SehySingleSearchRepository extends PagingAndSortingRepository<CrmkSehySingleVU, Long>{
	@Query("select c from CrmkSehySingleVU c where (c.typeId=:typeId or :typeId is null) and (c.typeName like :typeName or :typeName is null) and "
			+ "(c.nameId=:nameId or :nameId is null) and (c.name like :name or :name is null) and "
			+ "(c.dekalaId=:dekalaId or :dekalaId is null) and (c.dekala like :dekalaName or :dekalaName is null) and "
			+ "(c.colorId=:colorId or :colorId is null) and (c.color like :colorName or :colorName is null) and "
			+ "(c.frz = :frz or :frz is null) "
			+ "order by c.id")
	Page<CrmkSehySingleVU> getSehySingleSearchResult(Pageable pageable,@Param("typeId") Long typeId,@Param("typeName") String typeName,@Param("nameId") Long nameId,@Param("name") String name,
			@Param("dekalaId") Long dekalaId,@Param("dekalaName") String dekalaName,@Param("colorId") Long colorId,@Param("colorName") String colorName,@Param("frz") Long frz);
	
	@Query("select c from CrmkSehySingleVU c where c.typeId=:typeId and (c.nameId=:nameId or :nameId is null) and "
			+ "(c.dekalaId=:dekalaId or :dekalaId is null) and (c.colorId=:colorId or :colorId is null) and "
			+ "(c.frz = :frz or :frz is null) ")
	List<CrmkSehySingleVU> getSehySingleName(@Param("typeId") Long typeId,@Param("nameId") Long nameId,@Param("dekalaId") Long dekalaId,
			@Param("colorId") Long colorId,@Param("frz") Long frz);

}

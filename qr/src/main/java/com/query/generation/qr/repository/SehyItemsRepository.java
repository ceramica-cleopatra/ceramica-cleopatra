package com.query.generation.qr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.generation.qr.model.DcreQRItems;
import com.query.generation.qr.model.SehyQRItems;

public interface SehyItemsRepository extends CrudRepository<SehyQRItems,Long> {
	@Query("select e from SehyQRItems e where (e.typeId=:typeId or :typeId is null) and (e.nameId=:nameId or :nameId is null) and (e.dekalaId=:dekalaId or :dekalaId is null) "
			+ "and (e.colorId=:colorId or :colorId is null) and (e.grpId=:sehyGrpId or :sehyGrpId is null)")
	public List<SehyQRItems> getSehyItems(@Param("typeId") Long typeId,@Param("nameId") Long sizeId,@Param("dekalaId") Long dekalaId,@Param("colorId") Long colorId,@Param("sehyGrpId") Long sehyGrpId);
}

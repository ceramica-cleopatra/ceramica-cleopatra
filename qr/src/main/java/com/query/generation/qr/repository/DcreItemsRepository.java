package com.query.generation.qr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.generation.qr.model.DcreQRItems;

public interface DcreItemsRepository extends CrudRepository<DcreQRItems,Long> {
	@Query("select e from DcreQRItems e where (e.typeId=:typeId or :typeId is null) and (e.sizeId=:sizeId or :sizeId is null) and (e.dekalaId=:dekalaId or :dekalaId is null) "
			+ "and (e.factoryNo=:factoryNo or :factoryNo is null) and (e.colorId=:colorId or :colorId is null) and (e.tablow=:tablow or :tablow is null) and "
			+ "(e.showType=:showType or :showType is null) and (e.grpId=:grpId or :grpId is null) and (e.floor=:floor or :floor is null) and (e.brnId=:brnId or :brnId is null) "
			+ "and (e.standNo=:standNo or :standNo is null) and (e.how2show=:how2show or :how2show is null)")
	public List<DcreQRItems> getDcreItems(@Param("typeId") Long typeId,@Param("sizeId") Long sizeId,@Param("dekalaId") Long dekalaId,@Param("factoryNo") Long factoryNo,@Param("colorId") Long colorId
			,@Param("tablow") Long tablow,@Param("showType") String showType,@Param("grpId") Long grpId,@Param("floor") Long floor,@Param("brnId") Long brnId,@Param("standNo") String standNo
			,@Param("how2show") Long how2show);
}

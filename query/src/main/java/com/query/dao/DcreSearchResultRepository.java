package com.query.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.query.model.CrmkCrmkItemVU;
import com.query.model.CrmkDcreItemVU;

public interface DcreSearchResultRepository extends PagingAndSortingRepository<CrmkDcreItemVU, Integer>{
	@Query("select distinct c from CrmkDcreItemVU c where (c.typeId=:typeId or :typeId is null) and (c.typeName like :typeName or :typeName is null) and (c.sizeId = :sizeId or :sizeId is null) "
			+ "and (c.sizeName like :sizeName or :sizeName is null) and (c.dekalaId = :dekalaId or :dekalaId is null) and (c.factoryNo = :factoryNo or :factoryNo is null) "
			+ "and (c.dekalaName like :dekalaName or :dekalaName is null) and (c.frz = :frz or :frz is null) and (c.colourId = :colourId or :colourId is null) "
			+ "and (c.colourName = :colourName or :colourName is null) "
			+ "and (c.tablow = :tablow or :tablow is null)")
	Page<CrmkDcreItemVU> getDcreSearchResult(Pageable pageable,@Param("typeId") Long typeId,@Param("typeName") String typeName,@Param("sizeId") Long sizeId,@Param("sizeName") String sizeName,
			@Param("dekalaId") Long dekalaId,@Param("dekalaName") String dekalaName,@Param("factoryNo") String factoryNo
			,@Param("frz") Long frz,@Param("colourId") Long colourId,@Param("colourName") String colourName,@Param("tablow") Long tablow);
	
	@Query(nativeQuery=true,value="select item_id,c,tone,store_id,b.name store_name,g.name gov_name, sum (qty) qty,no_c_tone,crtn_mtr "+
			"from (select store_id, item.id item_id,item.c,item.tone,item.no_c_tone,siz.crtn_mtr, -sum (rsrv) qty "+
                 "from crmk_d_rsrv_mv rsrv, "+
                      "crmk_dcre_item item, "+
                      "crmk_brn_govern brn_govern, "+
                      "crmk_dcre_size siz "+
                      "where item.id = rsrv.item_id "+
                      "and siz.id=item.size_id "+
                      "and rsrv.show_id = brn_govern.brn_id "+
                      "and nvl(brn_govern.govern_id,0) = :gov_id "+
                      "and (nvl(item.type_id,0) = :typeId or :typeId is null) "+
                      "and (nvl(item.size_id,0) = :sizeId or :sizeId is null) "+
                      "and (nvl(factory_no, 0) =:factoryNo or :factoryNo is null) "+
                      "and (nvl(dekala_id, 0) =:dekalaId or :dekalaId is null)"+
                      "and (nvl(frz,0) = :frz or :frz is null) "+ 
                      "and (nvl(color_id,0) = :colorId or :colorId is null) "+
                      "and (nvl(tablow,0) = :tablow or :tablow is null) "+
                      "group by item.id,item.c,item.tone, store_id,item.no_c_tone,siz.crtn_mtr "+
                      "having round (sum (rsrv)) > 0 "+
                      "union all "+
                      "select store_id, item.id,item.c,item.tone,item.no_c_tone,siz.crtn_mtr, sum (qty) qty "+
                      "from crmk_d_free_mv act, "+
                      "crmk_dcre_item item, "+
                      "crmk_brn_govern brn_govern, "+
                      "crmk_branch brn, "+
                      "crmk_dcre_size siz "+
                      "where item.id = act.item_id "+
                      "and siz.id=item.size_id "+
                      "and act.store_id = brn_govern.brn_id "+
                      "and brn_govern.brn_id = brn.id "+
                      "and brn.store_show = 'T' "+
                      "and nvl(brn_govern.govern_id,0) = :gov_id "+
                      "and (nvl(item.type_id,0) = :typeId or :typeId is null) "+
                      "and (nvl(item.size_id,0) = :sizeId or :sizeId is null) "+
                      "and (nvl(factory_no, 0) =:factoryNo or :factoryNo is null) "+
                      "and (nvl(dekala_id, 0) =:dekalaId or :dekalaId is null)"+
                      "and (nvl(frz,0) = :frz or :frz is null) "+ 
                      "and (nvl(color_id,0) = :colorId or :colorId is null) "+
                      "and (nvl(tablow,0) = :tablow or :tablow is null) "+
                      "group by item.id,item.c,item.tone, store_id,item.no_c_tone,siz.crtn_mtr "+
                      "having round (sum (qty)) <> 0),crmk.crmk_branch b,crmk.crmk_govern g,crmk.crmk_brn_govern bg "+
                      "where store_id=b.id(+) "+
                      "and b.id=bg.brn_id(+) "+
                      "and bg.govern_id=g.id(+) "+
                      "group by item_id,c,tone,store_id,b.name,g.name,no_c_tone,crtn_mtr")
			List<Object[]> getDcreResultDetails(@Param("typeId") Long typeId,@Param("sizeId") Long sizeId,
			@Param("dekalaId") Long dekalaId,@Param("factoryNo") Long factoryNo,@Param("frz") Long frz,
			@Param("gov_id") Long govId,@Param("colorId") Long colorId,@Param("tablow") Long tablow);
	
	
	@Query(nativeQuery=true,value="SELECT CRMK.GET_DCRE_QUERY_PRICE_WITH_TYPE(:itemId,:pList,:dList) FROM DUAL")
	public String getDcreDefaultPrice(@Param("itemId") Long itemId, @Param("pList") Long pList, @Param("dList") Long dList);
	
	
	@Query(nativeQuery=true,value="SELECT i.id,i.no_c_tone,siz.crtn_mtr FROM CRMK.CRMK_DCRE_ITEM i,CRMK.CRMK_DCRE_SIZE siz "
			+ "WHERE i.size_id=siz.id and i.type_id=:typeId and i.size_id=:sizeId and (nvl(i.dekala_id,0)=:dekalaId or :dekalaId is null) "
			+ "and (factory_no=:factoryNo or :factoryNo is null) and frz=:frz and (color_id=:colorId or :colorId is null) "
			+ "and  (tablow=:tablow or :tablow is null) and rownum<2")
	public List<Object[]> getDcreItemDetails(@Param("typeId") Long typeId,@Param("sizeId") Long sizeId,
			@Param("dekalaId") Long dekalaId,@Param("factoryNo") Long factoryNo,@Param("frz") Long frz,
			@Param("colorId") Long colorId,@Param("tablow") Long tablow);
	
	@Query("select distinct c from CrmkDcreItemVU c where (c.typeId=:typeId or :typeId is null) and (c.sizeId = :sizeId or :sizeId is null) "
			+ "and (c.dekalaId = :dekalaId or :dekalaId is null) and (c.factoryNo = :factoryNo or :factoryNo is null) and (c.frz = :frz or :frz is null) and (colourId=:colorId or :colorId is null) "
			+ "and  (tablow=:tablow or :tablow is null)")
	List<CrmkDcreItemVU> getDcreSearchResult(@Param("typeId") Long typeId,@Param("sizeId") Long sizeId,
			@Param("dekalaId") Long dekalaId,@Param("factoryNo") String factoryNo
			,@Param("frz") Long frz,@Param("colorId") Long colorId,@Param("tablow") Long tablow);
	
	
	@Query(nativeQuery=true,value="SELECT DISTINCT hd.name BATH_NAME,show_color.name BATH_COLOR,hd.id BATH_ID"
		      + " FROM crmk_dcre_size siz,crmk_dcre_dekala dekala,crmk_dcre_type TYPE,show.show_bath_hd hd,show.show_bath_dt dt,show.show_dcre_item show_item,show.show_bath_color show_color,"
		      + " crmk_dcre_item item,crmk_color color "
		      + " WHERE show_item.type_id = TYPE.id"
		      + " AND show_item.size_id = siz.id(+)"
		      + " AND show_item.dekala_id = dekala.id(+)"
		      + " AND show_item.color_id = color.id(+)"
		      + " AND hd.id=dt.hd_id" 
		      + " AND show_item.id=dt.item_id"
		      + " AND dt.crmk_dcre='D'"
		      + " AND hd.color=show_color.id"
		      + " AND item.TYPE_ID = show_item.type_id"
		      + " and item.SIZE_ID=show_item.size_id"
		      + " and nvl(item.dekala_id,0)=nvl(show_item.dekala_id,0)"
		      + " and nvl(item.factory_no,0)=nvl(show_item.factory_no,0)"
		      + " and nvl(item.color_id,0)=nvl(show_item.color_id,0)"
		      + " and (nvl(item.type_id,0) = :typeId or :typeId is null)"
            + " and (nvl(item.size_id,0) = :sizeId or :sizeId is null)"
            + " and (item.factory_no =:factoryNo or :factoryNo is null)"
            + " and (nvl(item.dekala_id, 0) =:dekalaId or :dekalaId is null)"
            + " and (nvl(item.frz,0) = :frz or :frz is null)" 
//            + " and (item.color_id = :colorId or :colorId is null)" 
//            + " and (item.tablow = :tablow or :tablow is null)"
		      )
	List<Object[]> getDcreGroups(@Param("typeId") Long typeId,@Param("sizeId") Long sizeId,
			@Param("dekalaId") Long dekalaId,@Param("factoryNo") String factoryNo,@Param("frz") Long frz);
	
}


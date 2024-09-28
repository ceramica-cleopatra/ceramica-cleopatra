package com.query.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.query.model.CrmkCrmkItemVU;

public interface CrmkSearchResultRepository extends PagingAndSortingRepository<CrmkCrmkItemVU, Integer>{
	@Query("select distinct c from CrmkCrmkItemVU c where (c.typeId=:typeId or :typeId is null) and (c.typeName like :typeName or :typeName is null) and (c.sizeId = :sizeId or :sizeId is null) "
			+ "and (c.sizeName like :sizeName or :sizeName is null) and (c.dekalaName like :dekalaName or :dekalaName is null) "
			+ "and (c.dekalaId = :dekalaId or :dekalaId is null) and (c.factoryNo = :factoryNo or :factoryNo is null) and (c.frz = :frz or :frz is null)")
	Page<CrmkCrmkItemVU> getCrmkSearchResult(Pageable pageable,@Param("typeId") Long typeId,@Param("typeName") String typeName,@Param("sizeId") Long sizeId,@Param("sizeName") String sizeName,
			@Param("dekalaId") Long dekalaId,@Param("dekalaName") String dekalaName,@Param("factoryNo") String factoryNo
			,@Param("frz") Long frz);
	
	@Query(nativeQuery=true,value="select item_id,c,tone,store_id,b.name store_name,g.name gov_name, sum (qty) qty,no_c_tone,crtn_mtr "+
			"from (select store_id, item.id item_id,item.c,item.tone,item.no_c_tone,siz.crtn_mtr, -sum (rsrv) qty "+
                 "from crmk_c_rsrv_mv rsrv, "+
                      "crmk_crmk_item item, "+
                      "crmk_brn_govern brn_govern, "+
                      "crmk_crmk_size siz "+
                      "where item.id = rsrv.item_id(+) "+
                      "and siz.id=item.size_id "+
                      "and rsrv.show_id = brn_govern.brn_id(+) "+
                      "and nvl(brn_govern.govern_id,0) = :gov_id "+
                      "and (nvl(item.type_id,0) = :typeId or :typeId is null) "+
                      "and (nvl(item.size_id,0) = :sizeId or :sizeId is null) "+
                      "and (nvl(factory_no, 0) =:factoryNo or :factoryNo is null) "+
                      "and (nvl(dekala_id, 0) =:dekalaId or :dekalaId is null)"+
                      "and (nvl(frz,0) = :frz or :frz is null) "+ 
                      "group by item.id,item.c,item.tone, store_id,item.no_c_tone,siz.crtn_mtr "+
                      "having round (sum (rsrv)) > 0 "+
                      "union all "+
                      "select store_id, item.id,item.c,item.tone,item.no_c_tone,siz.crtn_mtr, sum (qty) qty "+
                      "from crmk_c_free_mv act, "+
                      "crmk_crmk_item item, "+
                      "crmk_brn_govern brn_govern, "+
                      "crmk_branch brn, "+
                      "crmk_crmk_size siz "+
                      "where item.id = act.item_id(+) "+
                      "and siz.id=item.size_id "+
                      "and act.store_id = brn_govern.brn_id(+) "+
                      "and brn_govern.brn_id = brn.id(+) "+
                      "and brn.store_show = 'T' "+
                      "and nvl(brn_govern.govern_id,0) = :gov_id "+
                      "and (nvl(item.type_id,0) = :typeId or :typeId is null) "+
                      "and (nvl(item.size_id,0) = :sizeId or :sizeId is null) "+
                      "and (nvl(factory_no, 0) =:factoryNo or :factoryNo is null) "+
                      "and (nvl(dekala_id, 0) =:dekalaId or :dekalaId is null)"+
                      "and (nvl(frz,0) = :frz or :frz is null) "+ 
                      "group by item.id,item.c,item.tone, store_id,item.no_c_tone,siz.crtn_mtr "+
                      "having round (sum (qty)) <> 0"+
                       "),crmk.crmk_branch b,crmk.crmk_govern g,crmk.crmk_brn_govern bg "+
                      "where store_id=b.id(+) "+
                      "and b.id=bg.brn_id(+) "+
                      "and bg.govern_id=g.id(+) "+
                      "group by item_id,c,tone,store_id,b.name,g.name,no_c_tone,crtn_mtr")
	List<Object[]> getCrmkResultDetails(@Param("typeId") Long typeId,@Param("sizeId") Long sizeId,
			@Param("dekalaId") Long dekalaId,@Param("factoryNo") Long factoryNo,@Param("frz") Long frz,@Param("gov_id") Long govId);
	
	
	@Query(nativeQuery=true,value="SELECT CRMK.GET_CRMK_QUERY_PRICE_WITH_TYPE(:itemId,:pList,:dList) FROM DUAL")
	public String getCrmkDefaultPrice(@Param("itemId") Long itemId, @Param("pList") Long pList, @Param("dList") Long dList);
	
	@Query(nativeQuery=true,value="SELECT i.id,i.no_c_tone,siz.crtn_mtr FROM CRMK.CRMK_CRMK_ITEM i,CRMK.CRMK_CRMK_SIZE siz WHERE i.size_id=siz.id and i.type_id=:typeId and i.size_id=:sizeId and (nvl(i.dekala_id,0)=:dekalaId or :dekalaId is null) and (factory_no=:factoryNo or :factoryNo is null) and frz=:frz and rownum<2")
	public List<Object[]> getCrmkItemDetails(@Param("typeId") Long typeId,@Param("sizeId") Long sizeId,@Param("dekalaId") Long dekalaId,@Param("factoryNo") Long factoryNo
			,@Param("frz") Long frz);
	
	@Query("select distinct c from CrmkCrmkItemVU c where (c.typeId=:typeId or :typeId is null) and (c.sizeId = :sizeId or :sizeId is null) "
			+ "and (c.dekalaId = :dekalaId or :dekalaId is null) and (c.factoryNo = :factoryNo or :factoryNo is null) and (c.frz = :frz or :frz is null)")
	List<CrmkCrmkItemVU> getCrmkSearchResult(@Param("typeId") Long typeId,@Param("sizeId") Long sizeId,
			@Param("dekalaId") Long dekalaId,@Param("factoryNo") String factoryNo
			,@Param("frz") Long frz);
	
	@Query(nativeQuery=true,value="SELECT DISTINCT hd.name BATH_NAME,show_color.name BATH_COLOR,hd.id BATH_ID"
		      + " FROM crmk_crmk_size siz,crmk_crmk_dekala dekala,crmk_crmk_type TYPE,show.show_bath_hd hd,show.show_bath_dt dt,show.show_crmk_item show_item,show.show_bath_color show_color,"
		      + " crmk_crmk_item item "
		      + " WHERE show_item.type_id = TYPE.id"
		      + " AND show_item.size_id = siz.id(+)"
		      + " AND show_item.dekala_id = dekala.id(+)"
		      + " AND hd.id=dt.hd_id" 
		      + " AND show_item.id=dt.item_id"
		      + " AND dt.crmk_dcre='C'"
		      + " AND hd.color=show_color.id"
		      + " AND item.TYPE_ID = show_item.type_id"
		      + " and item.SIZE_ID=show_item.size_id"
		      + " and nvl(item.dekala_id,0)=nvl(show_item.dekala_id,0)"
		      + " and nvl(item.factory_no,0)=nvl(show_item.factory_no,0)"
		      + " and nvl(item.color_id,0)=nvl(show_item.color_id,0)"
		      + " and (nvl(item.type_id,0) = :typeId or :typeId is null)"
              + " and (nvl(item.size_id,0) = :sizeId or :sizeId is null)"
              + " and (nvl(item.factory_no, 0) =:factoryNo or :factoryNo is null)"
              + " and (nvl(item.dekala_id, 0) =:dekalaId or :dekalaId is null)"
              + " and (nvl(item.frz,0) = :frz or :frz is null)" 
		      )
	List<Object[]> getCrmkGroups(@Param("typeId") Long typeId,@Param("sizeId") Long sizeId,
			@Param("dekalaId") Long dekalaId,@Param("factoryNo") String factoryNo,@Param("frz") Long frz);
	
	@Query(nativeQuery=true,value="SELECT ITEM_ID,CRMK_DCRE,TYPE_ID,TYPE_NAME,SIZE_ID,SIZE_NAME,FACTORY_NO,DEKALA_ID,"
      + " DEKALA_NAME,DEGREE,BATH_NAME,BATH_COLOR,BATH_ID,TO_NUMBER (GET_C_FREE (ITEM_ID,:gov_id,1)) FREE_1,TO_NUMBER(GET_C_FREE (ITEM_ID,:gov_id,2)) FREE_2,null colorId,null colorName,null tablow"
      + " FROM(SELECT 'C' crmk_dcre,show_item.type_id,TYPE.name type_name,show_item.size_id,siz.name size_name,show_item.factory_no,show_item.dekala_id,"
      + " dekala.name dekala_name,hd.name bath_name,hd.id bath_id,show_color.name bath_color,DECODE (dt.COLOR_DEGREE,1,'وسط',3,'فاتح',2, 'غامق') DEGREE,MAX(item.ID) ITEM_ID"
      + " FROM crmk_crmk_size siz,crmk_crmk_dekala dekala,crmk_crmk_type TYPE,show.show_bath_hd hd,show.show_bath_dt dt,show.show_crmk_item show_item,show.show_bath_color show_color,"
      + " crmk_crmk_item item "
      + " WHERE show_item.type_id = TYPE.id"
      + " AND show_item.size_id = siz.id(+)"
      + " AND show_item.dekala_id = dekala.id(+)"
      + " AND hd.id=dt.hd_id" 
      + " AND show_item.id=dt.item_id"
      + " AND dt.crmk_dcre='C'"
      + " AND hd.color=show_color.id"
      +  " AND item.TYPE_ID = show_item.type_id"
      +  " and item.SIZE_ID=show_item.size_id"
      +  " and nvl(item.dekala_id,0)=nvl(show_item.dekala_id,0)"
      +  " and nvl(item.factory_no,0)=nvl(show_item.factory_no,0)"
      +  " and nvl(item.color_id,0)=nvl(show_item.color_id,0)"
      +  " group by 'C',show_item.type_id,TYPE.name,show_item.size_id,siz.name,show_item.factory_no,show_item.dekala_id,dekala.name,hd.name,show_color.name,DT.COLOR_DEGREE,hd.id"
      +  " ) where BATH_ID=:bathId"
      +  " union all"
      + " SELECT ITEM_ID,CRMK_DCRE,TYPE_ID,TYPE_NAME,SIZE_ID,SIZE_NAME,FACTORY_NO,DEKALA_ID,DEKALA_NAME,DEGREE,BATH_NAME,BATH_COLOR,BATH_ID,TO_NUMBER (GET_D_FREE (ITEM_ID,:gov_id,1)) FREE_1,"
      + " TO_NUMBER(GET_D_FREE (ITEM_ID,:gov_id,2)) FREE_2,colorId,colorName,tablow"
      + " FROM(SELECT 'D' crmk_dcre,show_item.type_id,TYPE.name type_name,show_item.size_id,siz.name size_name,show_item.factory_no,show_item.dekala_id,dekala.name dekala_name,hd.name bath_name,"
      + " hd.id bath_id,show_color.name bath_color,DECODE (DT.COLOR_DEGREE,1, 'وسط',3, 'فاتح',2, 'غامق') DEGREE,MAX(item.ID) ITEM_ID,color.id colorId,color.name colorName,item.tablow"
      + " FROM crmk_dcre_size siz,crmk_dcre_dekala dekala,crmk_dcre_type TYPE,show.show_bath_hd hd,show.show_bath_dt dt,show.show_dcre_item show_item,show.show_bath_color show_color,crmk_dcre_item item"
      + ",crmk_color color"
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
      + " group by 'D',show_item.type_id,TYPE.name,show_item.size_id,siz.name,show_item.factory_no,show_item.dekala_id,dekala.name,hd.name,show_color.name,DT.COLOR_DEGREE,hd.id,color.id,color.name,item.tablow)"
      + " where BATH_ID=:bathId")
	List<Object[]> getGroupDetails(@Param("bathId") Long bathId,@Param("gov_id") Long govId);
}


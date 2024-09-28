package com.query.service;

import java.util.List;

import com.query.dto.CrmkDcreQueryDefaultDlistDTO;
import com.query.dto.CrmkDcreQueryDefaultPlistDTO;
import com.query.dto.CrmkDekalaDTO;
import com.query.dto.CrmkItemGovDTO;
import com.query.dto.CrmkSearchResultDTO;
import com.query.dto.CrmkSizeDTO;
import com.query.dto.CrmkTypeDTO;
import com.query.dto.DcreDekalaDTO;
import com.query.dto.DcreItemGovDTO;
import com.query.dto.DcreSearchResultDTO;
import com.query.dto.DcreSizeDTO;
import com.query.dto.DcreTypeDTO;
import com.query.dto.GroupDTO;
import com.query.dto.PriceDTO;

public interface DcreQueryService {
	
	List<DcreTypeDTO> getDcreTypes(Long id,String name);

	List<DcreDekalaDTO> getDcreDekala(Long id, String name);
	
	List<DcreSizeDTO> getDcreSize(Long id, String name);
	
	List<DcreSearchResultDTO> getDcreSearchResult(Long typeId,String typeName,Long sizeId
			,String sizeName,String factoryNo,Long dekalaId,String dekalaName,Long frz,Long colorId,
			String colorName,Long tablow,String page,String perPage);
	
	List<DcreItemGovDTO> getDcreSearchResultDetails(Long typeId,Long sizeId,Long factoryNo,Long dekalaId,Long frz,Long govId,Long colorId,Long tablow, Long pList,Long dList);
	
	List<CrmkDcreQueryDefaultPlistDTO> getDcreQueryDefaultPlist();
	
	PriceDTO getDcreItemPrice(Long typeId,Long sizeId,String factoryNo,Long dekalaId,Long frz,Long colorId,Long tablow,Long pList,Long dList);
	
	List<CrmkDcreQueryDefaultDlistDTO> getDcreQueryDefaultDlist();
	
	List<GroupDTO> getDcreGroups(Long typeId,Long sizeId,Long dekalaId,String factoryNo,Long frz,String tablow,String colorId);
}

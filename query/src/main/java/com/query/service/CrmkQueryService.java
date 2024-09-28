package com.query.service;

import java.util.List;

import com.query.dto.CrmkColorDTO;
import com.query.dto.CrmkCrmkQueryDefaultDlistDTO;
import com.query.dto.CrmkCrmkQueryDefaultPlistDTO;
import com.query.dto.CrmkDekalaDTO;
import com.query.dto.CrmkItemGovDTO;
import com.query.dto.CrmkSearchResultDTO;
import com.query.dto.CrmkSizeDTO;
import com.query.dto.CrmkTypeDTO;
import com.query.dto.GroupDTO;
import com.query.dto.GroupDetailsDTO;
import com.query.dto.PriceDTO;
import com.query.model.CrmkCrmkQueryDefaultPlist;

public interface CrmkQueryService {
	
	List<CrmkTypeDTO> getCrmkTypes(Long id,String name);

	List<CrmkDekalaDTO> getCrmkDekala(Long id, String name);
	
	List<CrmkSizeDTO> getCrmkSize(Long id, String name);
	
	List<CrmkColorDTO> getCrmkColor(Long id, String name);
	
	List<CrmkSearchResultDTO> getCrmkSearchResult(Long typeId,String typeName,Long sizeId,String sizeName,String factoryNo,Long dekalaId,String dekalaName,Long frz,String page,String perPage);
	
	List<CrmkItemGovDTO> getCrmkSearchResultDetails(Long typeId,Long sizeId,Long factoryNo,Long dekalaId,Long frz,Long govId, Long pList,Long dList);
	
	List<CrmkCrmkQueryDefaultPlistDTO> getCrmkQueryDefaultPlist();
	
	List<CrmkCrmkQueryDefaultDlistDTO> getCrmkQueryDefaultDlist();
	
	PriceDTO getCrmkItemPrice(Long typeId,Long sizeId,String factoryNo,Long dekalaId,Long frz,Long pList,Long dList);
	
	List<GroupDTO> getCrmkGroups(Long typeId,Long sizeId,Long dekalaId,String factoryNo,Long frz);
	
	List<GroupDetailsDTO> getGroupDetails(Long bathId, Long govId);
}

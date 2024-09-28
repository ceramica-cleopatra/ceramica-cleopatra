package com.query.service;

import java.util.List;

import com.query.dto.CrmkColorDTO;
import com.query.dto.CrmkDekalaDTO;
import com.query.dto.CrmkItemGovDTO;
import com.query.dto.CrmkSearchResultDTO;
import com.query.dto.CrmkSizeDTO;
import com.query.dto.CrmkTypeDTO;
import com.query.dto.SehyDekalaDTO;
import com.query.dto.SehyItemGovDTO;
import com.query.dto.SehyNameDTO;
import com.query.dto.SehySearchResultDTO;
import com.query.dto.SehyTypeDTO;

public interface SehyQueryService {
	
	List<SehyTypeDTO> getSehyTypes(Long id,String name);
	
	List<SehyNameDTO> getSehyNames(Long id,String name);

	List<SehyDekalaDTO> getSehyDekala(Long id, String name);
	
	List<CrmkColorDTO> getCrmkColor(Long id, String name);
	
	public List<SehySearchResultDTO> getSehySearchResult(Long typeId,String typeName,Long nameId,String name,Long dekalaId,String dekalaName,Long colorId,
			String colorName,Long frz,String page,String perPage);
	
	SehyItemGovDTO getSehyTakmSearchDetails(Long typeId,Long nameId,Long dekalaId,Long colorId,Long frz);
	
	SehyItemGovDTO getSehySingleSearchDetails(Long typeId,Long nameId,Long dekalaId,Long colorId,Long frz);
}

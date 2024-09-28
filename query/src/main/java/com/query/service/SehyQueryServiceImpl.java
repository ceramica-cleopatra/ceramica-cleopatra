package com.query.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.query.dao.CrmkColorRepository;
import com.query.dao.CrmkDekalaRepository;
import com.query.dao.CrmkSearchResultRepository;
import com.query.dao.CrmkSizeRepository;
import com.query.dao.CrmkTypeRepository;
import com.query.dao.SehyDekalaRepository;
import com.query.dao.SehyNameRepository;
import com.query.dao.SehySingleDetailSearchRepository;
import com.query.dao.SehySingleSearchRepository;
import com.query.dao.SehyTakmDetailSearchRepository;
import com.query.dao.SehyTakmSearchRepository;
import com.query.dao.SehyTypeRepository;
import com.query.dto.CrmkColorDTO;
import com.query.dto.CrmkDekalaDTO;
import com.query.dto.CrmkItemDetailDTO;
import com.query.dto.CrmkSearchResultDTO;
import com.query.dto.CrmkSizeDTO;
import com.query.dto.CrmkTypeDTO;
import com.query.dto.SehyDekalaDTO;
import com.query.dto.SehyItemDetailDTO;
import com.query.dto.SehyItemGovDTO;
import com.query.dto.SehyItemStoreDTO;
import com.query.dto.SehyNameDTO;
import com.query.dto.SehySearchResultDTO;
import com.query.dto.SehyTypeDTO;
import com.query.dto.CrmkItemGovDTO;
import com.query.dto.CrmkItemStoreDTO;
import com.query.model.CrmkColor;
import com.query.model.CrmkCrmkDekala;
import com.query.model.CrmkCrmkItemVU;
import com.query.model.CrmkCrmkSize;
import com.query.model.CrmkCrmkType;
import com.query.model.CrmkSehyDekala;
import com.query.model.CrmkSehyName;
import com.query.model.CrmkSehySingleDetailVU;
import com.query.model.CrmkSehySingleVU;
import com.query.model.CrmkSehyTakmDetailVU;
import com.query.model.CrmkSehyTakmVU;
import com.query.model.CrmkSehyType;

@Service("sehyQueryService")
public class SehyQueryServiceImpl implements SehyQueryService {
	@Autowired
	SehyDekalaRepository sehyDekalaRepository;

	@Autowired
	CrmkTypeRepository crmkTypeRepository;

	@Autowired
	SehyTypeRepository sehyTypeRepository;

	@Autowired
	SehyNameRepository sehyNameRepository;

	@Autowired
	SehyTakmSearchRepository sehyTakmSearchRepository;

	@Autowired
	SehySingleSearchRepository sehySingleSearchRepository;

	@Autowired
	SehyTakmDetailSearchRepository sehyTakmDetailSerachRepository;

	@Autowired
	SehySingleDetailSearchRepository sehysingleDetailSerachRepository;

	@Autowired
	CrmkColorRepository crmkColorRepository;

	@Override
	public List<SehyTypeDTO> getSehyTypes(Long id, String name) {
		List<CrmkSehyType> crmkSehyTypes = sehyTypeRepository.getSehyTypes(id, name);
		List<SehyTypeDTO> sehyTypeDTOs = new ArrayList<SehyTypeDTO>();
		for (CrmkSehyType crmkSehyType : crmkSehyTypes) {
			sehyTypeDTOs.add(new SehyTypeDTO(crmkSehyType.getId().toString(), crmkSehyType.getName()));
		}
		return sehyTypeDTOs;
	}

	@Override
	public List<SehyNameDTO> getSehyNames(Long id, String name) {
		List<CrmkSehyName> crmkSehyNames = sehyNameRepository.getSehyNames(id, name);
		List<SehyNameDTO> sehyNameDTOs = new ArrayList<SehyNameDTO>();
		for (CrmkSehyName CrmkSehyName : crmkSehyNames) {
			sehyNameDTOs.add(new SehyNameDTO(CrmkSehyName.getId().toString(), CrmkSehyName.getName()));
		}
		return sehyNameDTOs;
	}

	@Override
	public List<SehyDekalaDTO> getSehyDekala(Long id, String name) {
		List<CrmkSehyDekala> CrmkSehyDekalas = sehyDekalaRepository.getSehyDekalas(id, name);
		List<SehyDekalaDTO> sehyDekalaDTOs = new ArrayList<SehyDekalaDTO>();
		for (CrmkSehyDekala crmkSehyDekala : CrmkSehyDekalas) {
			sehyDekalaDTOs.add(new SehyDekalaDTO(crmkSehyDekala.getId().toString(), crmkSehyDekala.getName()));
		}
		return sehyDekalaDTOs;
	}

	@Override
	public List<CrmkColorDTO> getCrmkColor(Long id, String name) {
		List<CrmkColor> crmkColors = crmkColorRepository.getCrmkColors(id, name);
		List<CrmkColorDTO> crmkColorDTOs = new ArrayList<CrmkColorDTO>();
		for (CrmkColor crmkColor : crmkColors) {
			crmkColorDTOs.add(new CrmkColorDTO(crmkColor.getId().toString(), crmkColor.getName()));
		}
		return crmkColorDTOs;
	}

	@Override
	public List<SehySearchResultDTO> getSehySearchResult(Long typeId, String typeName, Long nameId, String name,
			Long dekalaId, String dekalaName, Long colorId, String colorName, Long frz, String page, String perPage) {
		List<SehySearchResultDTO> searchResultDTOs = new ArrayList<SehySearchResultDTO>();
		if (typeId >= 1 && typeId <= 40) {
			Pageable selectedPage = PageRequest.of(Integer.parseInt(page), Integer.parseInt(perPage));
			Page<CrmkSehyTakmVU> pageData = sehyTakmSearchRepository.getSehyTakmSearchResult(selectedPage, typeId,
					typeName != null ? "%" + typeName + "%" : null, nameId, name != null ? "%" + name + "%" : null,
					dekalaId, dekalaName != null ? "%" + dekalaName + "%" : null, colorId,
					colorName != null ? "%" + colorName + "%" : null, frz);
			List<CrmkSehyTakmVU> crmkSehyTakmVUs = pageData.getContent();

			for (CrmkSehyTakmVU crmkSehyTakmVU : crmkSehyTakmVUs) {
				searchResultDTOs.add(new SehySearchResultDTO(crmkSehyTakmVU.getTypeId().toString(),
						crmkSehyTakmVU.getTypeName() == null ? "" : crmkSehyTakmVU.getTypeName(),
						crmkSehyTakmVU.getNameId() == null ? "" : crmkSehyTakmVU.getNameId().toString(),
						crmkSehyTakmVU.getName() == null ? "" : crmkSehyTakmVU.getName(),
						crmkSehyTakmVU.getDekalaId() == null ? "" : crmkSehyTakmVU.getDekalaId().toString(),
						crmkSehyTakmVU.getDekala() == null ? "" : crmkSehyTakmVU.getDekala(),
						crmkSehyTakmVU.getColorId() == null ? "" : crmkSehyTakmVU.getColorId().toString(),
						crmkSehyTakmVU.getColor() == null ? "" : crmkSehyTakmVU.getColor(),
						crmkSehyTakmVU.getFrz() == null ? "" : crmkSehyTakmVU.getFrz().toString(),
						crmkSehyTakmVU.getId() == null ? "" : crmkSehyTakmVU.getId().toString()));
			}
		} else {
			Pageable selectedPage = PageRequest.of(Integer.parseInt(page), Integer.parseInt(perPage));
			Page<CrmkSehySingleVU> pageData = sehySingleSearchRepository.getSehySingleSearchResult(selectedPage, typeId,
					typeName != null ? "%" + typeName + "%" : null, nameId, name != null ? "%" + name + "%" : null,
					dekalaId, dekalaName != null ? "%" + dekalaName + "%" : null, colorId,
					colorName != null ? "%" + colorName + "%" : null, frz);
			List<CrmkSehySingleVU> crmkSehySingleVUs = pageData.getContent();

			for (CrmkSehySingleVU crmkSehySingleVU : crmkSehySingleVUs) {
				searchResultDTOs.add(new SehySearchResultDTO(crmkSehySingleVU.getTypeId().toString(),
						crmkSehySingleVU.getTypeName() == null ? "" : crmkSehySingleVU.getTypeName(),
						crmkSehySingleVU.getNameId() == null ? "" : crmkSehySingleVU.getNameId().toString(),
						crmkSehySingleVU.getName() == null ? "" : crmkSehySingleVU.getName(),
						crmkSehySingleVU.getDekalaId() == null ? "" : crmkSehySingleVU.getDekalaId().toString(),
						crmkSehySingleVU.getDekala() == null ? "" : crmkSehySingleVU.getDekala(),
						crmkSehySingleVU.getColorId() == null ? "" : crmkSehySingleVU.getColorId().toString(),
						crmkSehySingleVU.getColor() == null ? "" : crmkSehySingleVU.getColor(),
						crmkSehySingleVU.getFrz() == null ? "" : crmkSehySingleVU.getFrz().toString(),
						crmkSehySingleVU.getId() == null ? "" : crmkSehySingleVU.getId().toString()));
			}
		}
		return searchResultDTOs;
	}

	@Override
	public SehyItemGovDTO getSehyTakmSearchDetails(Long typeId, Long nameId, Long dekalaId, Long colorId, Long frz) {
		List<CrmkSehyTakmDetailVU> itemDetails = sehyTakmDetailSerachRepository.getSehyTakmSearchResult(typeId, nameId,
				dekalaId, colorId, frz);
		List<SehyItemDetailDTO> itemTemplate = new ArrayList<>();

		for (CrmkSehyTakmDetailVU crmkSehyTakmDetailVU : itemDetails) {
			SehyItemDetailDTO sehyItemDetailDTO = new SehyItemDetailDTO();
			sehyItemDetailDTO.setItemId(crmkSehyTakmDetailVU.getItemId());
			if (itemTemplate.contains(sehyItemDetailDTO))
				continue;
			sehyItemDetailDTO.setItemName(crmkSehyTakmDetailVU.getName() == null ? "" : crmkSehyTakmDetailVU.getName());
			sehyItemDetailDTO.setFreeQty("0");
			sehyItemDetailDTO.setRealQty("0");
			sehyItemDetailDTO.setPrice(
					crmkSehyTakmDetailVU.getPrice() != null ? crmkSehyTakmDetailVU.getPrice().toString() : "0");
			itemTemplate.add(sehyItemDetailDTO);
		}

		SehyItemGovDTO sehyItemGovDTO = new SehyItemGovDTO();
		sehyItemGovDTO.setDistinctItems(new ArrayList<SehyItemDetailDTO>());
		sehyItemGovDTO.setStoreList(new ArrayList<SehyItemStoreDTO>());
		for (CrmkSehyTakmDetailVU crmkSehyTakmDetailVU : itemDetails) {
			SehyItemDetailDTO sehyItemDetailDTO = new SehyItemDetailDTO();
			sehyItemDetailDTO.setItemId(crmkSehyTakmDetailVU.getItemId());
			if (sehyItemGovDTO.getDistinctItems().contains(sehyItemDetailDTO)) {
				sehyItemDetailDTO = sehyItemGovDTO.getDistinctItems()
						.get(sehyItemGovDTO.getDistinctItems().indexOf(sehyItemDetailDTO));
				sehyItemDetailDTO.setFreeQty((Math
						.round(Double.parseDouble(
								sehyItemDetailDTO.getFreeQty() != null ? sehyItemDetailDTO.getFreeQty() : "0"))
						+ (crmkSehyTakmDetailVU.getFree() != null
								&& !crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
										? Math.round(Double.parseDouble(crmkSehyTakmDetailVU.getFree().toString()))
										: 0))
						+ "");
				sehyItemDetailDTO.setRealQty((Math
						.round(Double.parseDouble(
								sehyItemDetailDTO.getRealQty() != null ? sehyItemDetailDTO.getRealQty() : "0"))
						+ (crmkSehyTakmDetailVU.getQty() != null
								? Math.round(Double.parseDouble(crmkSehyTakmDetailVU.getQty().toString())) : 0))
						+ "");
				sehyItemDetailDTO.setRsrvQty((Math.round(Double
						.parseDouble(sehyItemDetailDTO.getRsrvQty() != null ? sehyItemDetailDTO.getRsrvQty() : "0")
						+ (crmkSehyTakmDetailVU.getRsrv() != null
								&& crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
										? Math.round(Double.parseDouble(crmkSehyTakmDetailVU.getRsrv().toString()))
										: 0)))
						+ "");
				SehyItemStoreDTO sehyItemStoreDTO = new SehyItemStoreDTO();
				sehyItemStoreDTO.setStoreName(crmkSehyTakmDetailVU.getStoreName().replace("}", ""));
				if (sehyItemGovDTO.getStoreList().contains(sehyItemStoreDTO)) {
					sehyItemStoreDTO = sehyItemGovDTO.getStoreList()
							.get(sehyItemGovDTO.getStoreList().indexOf(sehyItemStoreDTO));
					sehyItemStoreDTO.setTotalFree((Math
							.round(Double.parseDouble(
									sehyItemStoreDTO.getTotalFree() != null ? sehyItemStoreDTO.getTotalFree() : "0"))
							+ (crmkSehyTakmDetailVU.getFree() != null
									&& !crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
											? Math.round(Double.parseDouble(crmkSehyTakmDetailVU.getFree().toString()))
											: 0))
							+ "");
					SehyItemDetailDTO sehyItemDetailDTO2 = new SehyItemDetailDTO();
					sehyItemDetailDTO2.setItemId(crmkSehyTakmDetailVU.getItemId());
					sehyItemDetailDTO2 = sehyItemStoreDTO.getItemDteailList()
							.get(sehyItemStoreDTO.getItemDteailList().indexOf(sehyItemDetailDTO2));
					sehyItemDetailDTO2.setFreeQty(crmkSehyTakmDetailVU.getFree() != null
							&& !crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
									? crmkSehyTakmDetailVU.getFree().toString() : "0");
					sehyItemDetailDTO2.setRealQty(
							crmkSehyTakmDetailVU.getQty() != null ? crmkSehyTakmDetailVU.getQty().toString() : "0");
					sehyItemDetailDTO2.setRsrvQty(crmkSehyTakmDetailVU.getRsrv() != null
							&& crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
									? crmkSehyTakmDetailVU.getRsrv().toString() : "0");
				} else {
					List<SehyItemDetailDTO> storeDistinctItems = new ArrayList<SehyItemDetailDTO>();
					for (SehyItemDetailDTO temp : itemTemplate) {
						storeDistinctItems
								.add(new SehyItemDetailDTO(temp.getItemId(), temp.getItemName(), temp.getPrice()));
					}
					sehyItemStoreDTO.setItemDteailList(storeDistinctItems);
					sehyItemStoreDTO.setGovernName(crmkSehyTakmDetailVU.getGovernName());
					sehyItemStoreDTO.setStoreId(crmkSehyTakmDetailVU.getStoreId().toString());
					sehyItemStoreDTO.setTotalFree((crmkSehyTakmDetailVU.getFree() != null
							&& !crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
									? Math.round(Double.parseDouble(crmkSehyTakmDetailVU.getFree().toString())) : 0)
							+ "");
					SehyItemDetailDTO sehyItemDetailDTO2 = new SehyItemDetailDTO();
					sehyItemDetailDTO2.setItemId(crmkSehyTakmDetailVU.getItemId());
					sehyItemDetailDTO2 = storeDistinctItems.get(storeDistinctItems.indexOf(sehyItemDetailDTO2));
					sehyItemDetailDTO2.setFreeQty(crmkSehyTakmDetailVU.getFree() != null
							&& !crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
									? crmkSehyTakmDetailVU.getFree().toString() : "0");
					sehyItemDetailDTO2.setRealQty(
							crmkSehyTakmDetailVU.getFree() != null ? crmkSehyTakmDetailVU.getFree().toString() : "0");
					sehyItemDetailDTO2.setRsrvQty(crmkSehyTakmDetailVU.getFree() != null
							&& crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
									? crmkSehyTakmDetailVU.getFree().toString() : "0");
					sehyItemGovDTO.getStoreList().add(sehyItemStoreDTO);
				}

			} else {
				List<SehyItemDetailDTO> distinctItems = new ArrayList<SehyItemDetailDTO>();
				for (SehyItemDetailDTO temp : itemTemplate) {
					distinctItems.add(new SehyItemDetailDTO(temp.getItemId(), temp.getItemName(), temp.getPrice()));
				}
				sehyItemGovDTO.setDistinctItems(distinctItems);
				sehyItemDetailDTO = sehyItemGovDTO.getDistinctItems()
						.get(sehyItemGovDTO.getDistinctItems().indexOf(sehyItemDetailDTO));
				sehyItemDetailDTO.setFreeQty(crmkSehyTakmDetailVU.getFree() != null
						&& !crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
								? crmkSehyTakmDetailVU.getFree().toString() : "0");
				sehyItemDetailDTO.setRealQty(
						crmkSehyTakmDetailVU.getFree() != null ? crmkSehyTakmDetailVU.getFree().toString() : "0");
				sehyItemDetailDTO.setRsrvQty(crmkSehyTakmDetailVU.getFree() != null
						&& crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
								? crmkSehyTakmDetailVU.getFree().toString() : "0");

				SehyItemStoreDTO sehyItemStoreDTO = new SehyItemStoreDTO();
				sehyItemStoreDTO.setStoreName(crmkSehyTakmDetailVU.getStoreName().replace("}", ""));
				sehyItemStoreDTO.setTotalFree(crmkSehyTakmDetailVU.getFree() != null
						&& !crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
								? crmkSehyTakmDetailVU.getFree().toString() : "0");
				sehyItemStoreDTO.setGovernName(crmkSehyTakmDetailVU.getGovernName());
				sehyItemStoreDTO.setStoreId(crmkSehyTakmDetailVU.getStoreId().toString());
				SehyItemDetailDTO sehyItemDetailDTO2 = new SehyItemDetailDTO();
				sehyItemDetailDTO2.setItemId(crmkSehyTakmDetailVU.getItemId());

				List<SehyItemDetailDTO> storeDistinctItems = new ArrayList<SehyItemDetailDTO>();
				for (SehyItemDetailDTO temp : itemTemplate) {
					storeDistinctItems
							.add(new SehyItemDetailDTO(temp.getItemId(), temp.getItemName(), temp.getPrice()));
				}

				sehyItemDetailDTO2 = storeDistinctItems.get(storeDistinctItems.indexOf(sehyItemDetailDTO2));

				sehyItemDetailDTO2.setFreeQty(crmkSehyTakmDetailVU.getFree() != null
						&& !crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
								? crmkSehyTakmDetailVU.getFree().toString() : "0");
				sehyItemDetailDTO2.setRealQty(
						crmkSehyTakmDetailVU.getFree() != null ? crmkSehyTakmDetailVU.getFree().toString() : "0");
				sehyItemDetailDTO2.setRsrvQty(crmkSehyTakmDetailVU.getFree() != null
						&& crmkSehyTakmDetailVU.getStoreId().toString().equals("81")
								? crmkSehyTakmDetailVU.getFree().toString() : "0");
				sehyItemStoreDTO.setItemDteailList(storeDistinctItems);
				sehyItemGovDTO.getStoreList().add(sehyItemStoreDTO);
			}
		}
		List<CrmkSehyTakmVU> takmList = sehyTakmSearchRepository.getSehyTakmName(typeId, nameId, dekalaId, colorId,
				frz);
		if (!takmList.isEmpty()) {
			sehyItemGovDTO.setProductName(takmList.get(0).getTypeName() + " / " + (takmList.get(0).getName() == null ? "" : takmList.get(0).getName()) + " / "
					+ (takmList.get(0).getDekala() == null ? "" : takmList.get(0).getDekala()) + " / " + (takmList.get(0).getColor() == null ? "" : takmList.get(0).getColor()) + " / "
					+ takmList.get(0).getFrz());
		}
		return sehyItemGovDTO;
	}

	@Override
	public SehyItemGovDTO getSehySingleSearchDetails(Long typeId, Long nameId, Long dekalaId, Long colorId, Long frz) {
		List<CrmkSehySingleDetailVU> itemDetails = sehysingleDetailSerachRepository.getSehyTakmSearchResult(typeId,
				nameId, dekalaId, colorId, frz);
		List<SehyItemDetailDTO> itemTemplate = new ArrayList<>();

		for (CrmkSehySingleDetailVU crmkSehySingleDetailVU : itemDetails) {
			SehyItemDetailDTO sehyItemDetailDTO = new SehyItemDetailDTO();
			sehyItemDetailDTO.setItemId(crmkSehySingleDetailVU.getItemId());
			if (itemTemplate.contains(sehyItemDetailDTO))
				continue;
			sehyItemDetailDTO
					.setItemName(crmkSehySingleDetailVU.getName() == null ? "" : crmkSehySingleDetailVU.getName());
			sehyItemDetailDTO.setFreeQty("0");
			sehyItemDetailDTO.setRealQty("0");
			sehyItemDetailDTO.setPrice(
					crmkSehySingleDetailVU.getPrice() != null ? crmkSehySingleDetailVU.getPrice().toString() : "0");
			itemTemplate.add(sehyItemDetailDTO);
		}

		SehyItemGovDTO sehyItemGovDTO = new SehyItemGovDTO();
		sehyItemGovDTO.setDistinctItems(new ArrayList<SehyItemDetailDTO>());
		sehyItemGovDTO.setStoreList(new ArrayList<SehyItemStoreDTO>());
		for (CrmkSehySingleDetailVU crmkSehySingleDetailVU : itemDetails) {
			SehyItemDetailDTO sehyItemDetailDTO = new SehyItemDetailDTO();
			sehyItemDetailDTO.setItemId(crmkSehySingleDetailVU.getItemId());
			if (sehyItemGovDTO.getDistinctItems().contains(sehyItemDetailDTO)) {
				sehyItemDetailDTO = sehyItemGovDTO.getDistinctItems()
						.get(sehyItemGovDTO.getDistinctItems().indexOf(sehyItemDetailDTO));
				sehyItemDetailDTO.setFreeQty((Math
						.round(Double.parseDouble(
								sehyItemDetailDTO.getFreeQty() != null ? sehyItemDetailDTO.getFreeQty() : "0"))
						+ (crmkSehySingleDetailVU.getFree() != null
								&& !crmkSehySingleDetailVU.getStoreId().toString().equals("81")
										? Math.round(Double.parseDouble(crmkSehySingleDetailVU.getFree().toString()))
										: 0))
						+ "");
				sehyItemDetailDTO.setRealQty((Math
						.round(Double.parseDouble(
								sehyItemDetailDTO.getRealQty() != null ? sehyItemDetailDTO.getRealQty() : "0"))
						+ (crmkSehySingleDetailVU.getQty() != null
								? Math.round(Double.parseDouble(crmkSehySingleDetailVU.getQty().toString())) : 0))
						+ "");
				sehyItemDetailDTO.setRsrvQty((Math.round(Double
						.parseDouble(sehyItemDetailDTO.getRsrvQty() != null ? sehyItemDetailDTO.getRsrvQty() : "0")
						+ (crmkSehySingleDetailVU.getRsrv() != null
								&& crmkSehySingleDetailVU.getStoreId().toString().equals("81")
										? Math.round(Double.parseDouble(crmkSehySingleDetailVU.getRsrv().toString()))
										: 0)))
						+ "");
				SehyItemStoreDTO sehyItemStoreDTO = new SehyItemStoreDTO();
				sehyItemStoreDTO.setStoreName(crmkSehySingleDetailVU.getStoreName().replace("}", ""));
				if (sehyItemGovDTO.getStoreList().contains(sehyItemStoreDTO)) {
					sehyItemStoreDTO = sehyItemGovDTO.getStoreList()
							.get(sehyItemGovDTO.getStoreList().indexOf(sehyItemStoreDTO));
					sehyItemStoreDTO.setTotalFree((Math
							.round(Double.parseDouble(
									sehyItemStoreDTO.getTotalFree() != null ? sehyItemStoreDTO.getTotalFree() : "0"))
							+ (crmkSehySingleDetailVU.getFree() != null
									&& !crmkSehySingleDetailVU.getStoreId().toString().equals("81")
											? Math.round(
													Double.parseDouble(crmkSehySingleDetailVU.getFree().toString()))
											: 0))
							+ "");
					SehyItemDetailDTO sehyItemDetailDTO2 = new SehyItemDetailDTO();
					sehyItemDetailDTO2.setItemId(crmkSehySingleDetailVU.getItemId());
					sehyItemDetailDTO2 = sehyItemStoreDTO.getItemDteailList()
							.get(sehyItemStoreDTO.getItemDteailList().indexOf(sehyItemDetailDTO2));
					sehyItemDetailDTO2.setFreeQty(crmkSehySingleDetailVU.getFree() != null
							&& !crmkSehySingleDetailVU.getStoreId().toString().equals("81")
									? crmkSehySingleDetailVU.getFree().toString() : "0");
					sehyItemDetailDTO2.setRealQty(
							crmkSehySingleDetailVU.getQty() != null ? crmkSehySingleDetailVU.getQty().toString() : "0");
					sehyItemDetailDTO2.setRsrvQty(crmkSehySingleDetailVU.getRsrv() != null
							&& crmkSehySingleDetailVU.getStoreId().toString().equals("81")
									? crmkSehySingleDetailVU.getRsrv().toString() : "0");
				} else {
					List<SehyItemDetailDTO> storeDistinctItems = new ArrayList<SehyItemDetailDTO>();
					for (SehyItemDetailDTO temp : itemTemplate) {
						storeDistinctItems
								.add(new SehyItemDetailDTO(temp.getItemId(), temp.getItemName(), temp.getPrice()));
					}
					sehyItemStoreDTO.setItemDteailList(storeDistinctItems);
					sehyItemStoreDTO.setGovernName(crmkSehySingleDetailVU.getGovernName());
					sehyItemStoreDTO.setStoreId(crmkSehySingleDetailVU.getStoreId().toString());
					sehyItemStoreDTO.setTotalFree((crmkSehySingleDetailVU.getFree() != null
							&& !crmkSehySingleDetailVU.getStoreId().toString().equals("81")
									? Math.round(Double.parseDouble(crmkSehySingleDetailVU.getFree().toString())) : 0)
							+ "");
					SehyItemDetailDTO sehyItemDetailDTO2 = new SehyItemDetailDTO();
					sehyItemDetailDTO2.setItemId(crmkSehySingleDetailVU.getItemId());
					sehyItemDetailDTO2 = storeDistinctItems.get(storeDistinctItems.indexOf(sehyItemDetailDTO2));
					sehyItemDetailDTO2.setFreeQty(crmkSehySingleDetailVU.getFree() != null
							&& !crmkSehySingleDetailVU.getStoreId().toString().equals("81")
									? crmkSehySingleDetailVU.getFree().toString() : "0");
					sehyItemDetailDTO2.setRealQty(crmkSehySingleDetailVU.getFree() != null
							? crmkSehySingleDetailVU.getFree().toString() : "0");
					sehyItemDetailDTO2.setRsrvQty(crmkSehySingleDetailVU.getFree() != null
							&& crmkSehySingleDetailVU.getStoreId().toString().equals("81")
									? crmkSehySingleDetailVU.getFree().toString() : "0");
					sehyItemGovDTO.getStoreList().add(sehyItemStoreDTO);
				}

			} else {
				List<SehyItemDetailDTO> distinctItems = new ArrayList<SehyItemDetailDTO>();
				for (SehyItemDetailDTO temp : itemTemplate) {
					distinctItems.add(new SehyItemDetailDTO(temp.getItemId(), temp.getItemName(), temp.getPrice()));
				}
				sehyItemGovDTO.setDistinctItems(distinctItems);
				sehyItemDetailDTO = sehyItemGovDTO.getDistinctItems()
						.get(sehyItemGovDTO.getDistinctItems().indexOf(sehyItemDetailDTO));
				sehyItemDetailDTO.setFreeQty(crmkSehySingleDetailVU.getFree() != null
						&& !crmkSehySingleDetailVU.getStoreId().toString().equals("81")
								? crmkSehySingleDetailVU.getFree().toString() : "0");
				sehyItemDetailDTO.setRealQty(
						crmkSehySingleDetailVU.getFree() != null ? crmkSehySingleDetailVU.getFree().toString() : "0");
				sehyItemDetailDTO.setRsrvQty(crmkSehySingleDetailVU.getFree() != null
						&& crmkSehySingleDetailVU.getStoreId().toString().equals("81")
								? crmkSehySingleDetailVU.getFree().toString() : "0");

				SehyItemStoreDTO sehyItemStoreDTO = new SehyItemStoreDTO();
				sehyItemStoreDTO.setStoreName(crmkSehySingleDetailVU.getStoreName().replace("}", ""));
				sehyItemStoreDTO.setTotalFree(crmkSehySingleDetailVU.getFree() != null
						&& !crmkSehySingleDetailVU.getStoreId().toString().equals("81")
								? crmkSehySingleDetailVU.getFree().toString() : "0");
				sehyItemStoreDTO.setGovernName(crmkSehySingleDetailVU.getGovernName());
				sehyItemStoreDTO.setStoreId(crmkSehySingleDetailVU.getStoreId().toString());
				SehyItemDetailDTO sehyItemDetailDTO2 = new SehyItemDetailDTO();
				sehyItemDetailDTO2.setItemId(crmkSehySingleDetailVU.getItemId());

				List<SehyItemDetailDTO> storeDistinctItems = new ArrayList<SehyItemDetailDTO>();
				for (SehyItemDetailDTO temp : itemTemplate) {
					storeDistinctItems
							.add(new SehyItemDetailDTO(temp.getItemId(), temp.getItemName(), temp.getPrice()));
				}

				sehyItemDetailDTO2 = storeDistinctItems.get(storeDistinctItems.indexOf(sehyItemDetailDTO2));

				sehyItemDetailDTO2.setFreeQty(crmkSehySingleDetailVU.getFree() != null
						&& !crmkSehySingleDetailVU.getStoreId().toString().equals("81")
								? crmkSehySingleDetailVU.getFree().toString() : "0");
				sehyItemDetailDTO2.setRealQty(
						crmkSehySingleDetailVU.getFree() != null ? crmkSehySingleDetailVU.getFree().toString() : "0");
				sehyItemDetailDTO2.setRsrvQty(crmkSehySingleDetailVU.getFree() != null
						&& crmkSehySingleDetailVU.getStoreId().toString().equals("81")
								? crmkSehySingleDetailVU.getFree().toString() : "0");
				sehyItemStoreDTO.setItemDteailList(storeDistinctItems);
				sehyItemGovDTO.getStoreList().add(sehyItemStoreDTO);
			}
		}
		List<CrmkSehySingleVU> singleList = sehySingleSearchRepository.getSehySingleName(typeId, nameId, dekalaId, colorId, frz);
		if(!singleList.isEmpty()){
			sehyItemGovDTO.setProductName(singleList.get(0).getTypeName() + " / " + (singleList.get(0).getName() == null ? "" : singleList.get(0).getName()) + " / "
					+ (singleList.get(0).getDekala() == null ? "" : singleList.get(0).getDekala()) + " / " + (singleList.get(0).getColor() == null ? "" : singleList.get(0).getColor()) + " / "
					+ singleList.get(0).getFrz());
		}
		return sehyItemGovDTO;
	}

}

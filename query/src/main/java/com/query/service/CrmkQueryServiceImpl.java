package com.query.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.query.dao.CrmkColorRepository;
import com.query.dao.CrmkCrmkQueryDefaultDlistRepository;
import com.query.dao.CrmkCrmkQueryDefaultPlistRepository;
import com.query.dao.CrmkDekalaRepository;
import com.query.dao.CrmkSearchResultRepository;
import com.query.dao.CrmkSizeRepository;
import com.query.dao.CrmkTypeRepository;
import com.query.dto.CrmkColorDTO;
import com.query.dto.CrmkCrmkQueryDefaultDlistDTO;
import com.query.dto.CrmkCrmkQueryDefaultPlistDTO;
import com.query.dto.CrmkDekalaDTO;
import com.query.dto.CrmkItemDetailDTO;
import com.query.dto.CrmkSearchResultDTO;
import com.query.dto.CrmkSizeDTO;
import com.query.dto.CrmkTypeDTO;
import com.query.dto.GroupDTO;
import com.query.dto.GroupDetailsDTO;
import com.query.dto.PriceDTO;
import com.query.dto.CrmkItemGovDTO;
import com.query.dto.CrmkItemStoreDTO;
import com.query.model.CrmkColor;
import com.query.model.CrmkCrmkDekala;
import com.query.model.CrmkCrmkItemVU;
import com.query.model.CrmkCrmkQueryDefaultDlist;
import com.query.model.CrmkCrmkQueryDefaultPlist;
import com.query.model.CrmkCrmkSize;
import com.query.model.CrmkCrmkType;

@Service("crmkQueryService")
public class CrmkQueryServiceImpl implements CrmkQueryService {
	@Autowired
	CrmkDekalaRepository crmkDekalaRepository;

	@Autowired
	CrmkTypeRepository crmkTypeRepository;

	@Autowired
	CrmkSizeRepository crmkSizeRepository;

	@Autowired
	CrmkSearchResultRepository crmkSearchResultRepository;

	@Autowired
	CrmkColorRepository crmkColorRepository;

	@Autowired
	CrmkCrmkQueryDefaultPlistRepository crmkCrmkQueryDefaultPlistRepository;

	@Autowired
	CrmkCrmkQueryDefaultDlistRepository crmkCrmkQueryDefaultDlistRepository;

	@Override
	public List<CrmkTypeDTO> getCrmkTypes(Long id, String name) {
		List<CrmkCrmkType> crmkCrmkTypes = crmkTypeRepository.getCrmkTypes(id, name);
		List<CrmkTypeDTO> crmkTypeDTOs = new ArrayList<CrmkTypeDTO>();
		for (CrmkCrmkType crmkCrmkType : crmkCrmkTypes) {
			crmkTypeDTOs.add(new CrmkTypeDTO(crmkCrmkType.getId().toString(), crmkCrmkType.getName()));
		}
		return crmkTypeDTOs;
	}

	@Override
	public List<CrmkDekalaDTO> getCrmkDekala(Long id, String name) {
		List<CrmkCrmkDekala> crmkCrmkDekalas = crmkDekalaRepository.getCrmkDekalas(id, name);
		List<CrmkDekalaDTO> crmkDekalaDTOs = new ArrayList<CrmkDekalaDTO>();
		for (CrmkCrmkDekala crmkCrmkDekala : crmkCrmkDekalas) {
			crmkDekalaDTOs.add(new CrmkDekalaDTO(crmkCrmkDekala.getId().toString(), crmkCrmkDekala.getName()));
		}
		return crmkDekalaDTOs;
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
	public List<CrmkSizeDTO> getCrmkSize(Long id, String name) {
		List<CrmkCrmkSize> crmkCrmkSizes = crmkSizeRepository.getCrmkSize(id, name);
		List<CrmkSizeDTO> crmkSizeDTOs = new ArrayList<CrmkSizeDTO>();
		for (CrmkCrmkSize crmkCrmkSize : crmkCrmkSizes) {
			crmkSizeDTOs.add(new CrmkSizeDTO(crmkCrmkSize.getId().toString(), crmkCrmkSize.getName()));
		}
		return crmkSizeDTOs;
	}

	@Override
	public List<CrmkSearchResultDTO> getCrmkSearchResult(Long typeId, String typeName, Long sizeId, String sizeName,
			String factoryNo, Long dekalaId, String dekalaName, Long frz, String page, String perPage) {
		Pageable selectedPage = PageRequest.of(Integer.parseInt(page), Integer.parseInt(perPage));
		Page<CrmkCrmkItemVU> pageData = crmkSearchResultRepository.getCrmkSearchResult(selectedPage, typeId,
				typeName != null ? "%" + typeName + "%" : null, sizeId, sizeName != null ? "%" + sizeName + "%" : null,
				dekalaId, dekalaName != null ? "%" + dekalaName + "%" : null, factoryNo, frz);
		List<CrmkCrmkItemVU> crmkCrmkItemVUs = pageData.getContent();
		List<CrmkSearchResultDTO> crmkSearchResultDTOs = new ArrayList<CrmkSearchResultDTO>();
		for (CrmkCrmkItemVU crmkCrmkItemVU : crmkCrmkItemVUs) {
			crmkSearchResultDTOs.add(new CrmkSearchResultDTO(crmkCrmkItemVU.getTypeId().toString(),
					crmkCrmkItemVU.getTypeName() == null ? "" : crmkCrmkItemVU.getTypeName(),
					crmkCrmkItemVU.getSizeId() == null ? "" : crmkCrmkItemVU.getSizeId().toString(),
					crmkCrmkItemVU.getSizeName() == null ? "" : crmkCrmkItemVU.getSizeName(),
					crmkCrmkItemVU.getFactoryNo() == null ? "" : crmkCrmkItemVU.getFactoryNo().toString(),
					crmkCrmkItemVU.getDekalaId() == null ? "" : crmkCrmkItemVU.getDekalaId().toString(),
					crmkCrmkItemVU.getDekalaName() == null ? "" : crmkCrmkItemVU.getDekalaName(),
					crmkCrmkItemVU.getFrz() == null ? "" : crmkCrmkItemVU.getFrz().toString()));
		}
		return crmkSearchResultDTOs;
	}

	@Override
	public List<CrmkItemGovDTO> getCrmkSearchResultDetails(Long typeId, Long sizeId, Long factoryNo, Long dekalaId,
			Long frz, Long govId, Long pList, Long dList) {

		List<Object[]> itemDetails = crmkSearchResultRepository.getCrmkResultDetails(typeId, sizeId, dekalaId,
				factoryNo, frz, govId);
		List<CrmkItemGovDTO> govList = new ArrayList<>();
		List<CrmkItemDetailDTO> itemTemplate = new ArrayList<>();

		for (Object[] object : itemDetails) {
			CrmkItemDetailDTO crmkItemDetailDTO = new CrmkItemDetailDTO();
			crmkItemDetailDTO.setItemId(Integer.parseInt(object[0].toString()));
			if (itemTemplate.contains(crmkItemDetailDTO))
				continue;
			crmkItemDetailDTO.setC(object[1] == null || object[1].equals("") ? "" : object[1].toString());
			crmkItemDetailDTO.setTone(object[2] == null ? "" : object[2].toString());
			crmkItemDetailDTO.setFreeQty("0");
			crmkItemDetailDTO.setRealQty("0");
			crmkItemDetailDTO.setRsrvQty("0");
			itemTemplate.add(crmkItemDetailDTO);
		}

		for (Object[] object : itemDetails) {
			CrmkItemGovDTO crmkItemGovDTO = new CrmkItemGovDTO();
			crmkItemGovDTO.setGovName("");
			crmkItemGovDTO.setProductName(object[7].toString().replaceAll("\\\\", " / "));
			crmkItemGovDTO.setProductName(crmkItemGovDTO.getProductName().replace("/  /", "/"));
			crmkItemGovDTO.setProductName(crmkItemGovDTO.getProductName().replace("/  /", "/"));
			crmkItemGovDTO.setProductName(crmkItemGovDTO.getProductName().replace("/  /", "/"));
			crmkItemGovDTO.setPackageSize(object[8].toString());
			CrmkItemDetailDTO crmkItemDetailDTO = new CrmkItemDetailDTO();
			crmkItemDetailDTO.setItemId(Integer.parseInt(object[0].toString()));
			if (govList.contains(crmkItemGovDTO)) {
				crmkItemGovDTO = govList.get(govList.indexOf(crmkItemGovDTO));
				crmkItemGovDTO.setTotalReal(Math.round(
						Double.parseDouble(crmkItemGovDTO.getTotalReal() != null ? crmkItemGovDTO.getTotalReal() : "0")
								+ (object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? Math.round(Double.parseDouble(object[6].toString())) : 0))
						+ "");
				crmkItemGovDTO.setTotalRsrv((Math.round(
						Double.parseDouble(crmkItemGovDTO.getTotalRsrv() != null ? crmkItemGovDTO.getTotalRsrv() : "0")
								+ (object[6] != null && object[6] != "" && object[3].toString().equals("81")
										? Math.round(Double.parseDouble(object[6].toString())) : 0)))
						+ "");
				crmkItemGovDTO.setTotalFree((Math.round(
						Double.parseDouble(crmkItemGovDTO.getTotalFree() != null ? crmkItemGovDTO.getTotalFree() : "0"))
						+ (object[6] != null && object[6] != "" ? Math.round(Double.parseDouble(object[6].toString()))
								: 0))
						+ "");
				crmkItemDetailDTO = crmkItemGovDTO.getDistinctItems()
						.get(crmkItemGovDTO.getDistinctItems().indexOf(crmkItemDetailDTO));
				crmkItemDetailDTO.setRealQty((Math
						.round(Double.parseDouble(
								crmkItemDetailDTO.getRealQty() != null ? crmkItemDetailDTO.getRealQty() : "0"))
						+ (object[6] != null && object[6] != "" && !object[3].toString().equals("81")
								? Math.round(Double.parseDouble(object[6].toString())) : 0))
						+ "");
				crmkItemDetailDTO.setFreeQty((Math
						.round(Double.parseDouble(
								crmkItemDetailDTO.getFreeQty() != null ? crmkItemDetailDTO.getFreeQty() : "0"))
						+ (object[6] != null && object[6] != "" ? Math.round(Double.parseDouble(object[6].toString()))
								: 0))
						+ "");
				crmkItemDetailDTO.setRsrvQty((Math.round(Double
						.parseDouble(crmkItemDetailDTO.getRsrvQty() != null ? crmkItemDetailDTO.getRsrvQty() : "0")
						+ (object[6] != null && object[6] != "" && object[3].toString().equals("81")
								? Math.round(Double.parseDouble(object[6].toString())) : 0)))
						+ "");
				if (!object[3].toString().equals("81")) {
					CrmkItemStoreDTO crmkItemStoreDTO = new CrmkItemStoreDTO();
					crmkItemStoreDTO.setStoreName(object[4].toString().replace("}", ""));
					if (crmkItemGovDTO.getStoreList().contains(crmkItemStoreDTO)) {
						crmkItemStoreDTO = crmkItemGovDTO.getStoreList()
								.get(crmkItemGovDTO.getStoreList().indexOf(crmkItemStoreDTO));
						crmkItemStoreDTO.setTotalFree((Math
								.round(Double.parseDouble(crmkItemStoreDTO.getTotalFree() != null
										? crmkItemStoreDTO.getTotalFree() : "0"))
								+ (object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? Math.round(Double.parseDouble(object[6].toString())) : 0))
								+ "");
						CrmkItemDetailDTO crmkItemDetailDTO2 = new CrmkItemDetailDTO();
						crmkItemDetailDTO2.setItemId(Integer.parseInt(object[0].toString()));
						crmkItemDetailDTO2 = crmkItemStoreDTO.getItemDteailList()
								.get(crmkItemStoreDTO.getItemDteailList().indexOf(crmkItemDetailDTO2));
						crmkItemDetailDTO2
								.setFreeQty(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? object[6].toString() : "0");
						crmkItemDetailDTO2
								.setRealQty(object[6] != null && object[6] != "" ? object[6].toString() : "0");
						crmkItemDetailDTO2
								.setRsrvQty(object[6] != null && object[6] != "" && object[3].toString().equals("81")
										? object[6].toString() : "0");
					} else {
						crmkItemStoreDTO
								.setTotalFree(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? object[6].toString() : "0");

						List<CrmkItemDetailDTO> storeDistinctItems = new ArrayList<CrmkItemDetailDTO>();
						for (CrmkItemDetailDTO temp : itemTemplate) {
							storeDistinctItems
									.add(new CrmkItemDetailDTO(temp.getItemId(), temp.getC(), temp.getTone()));
						}
						crmkItemStoreDTO.setItemDteailList(storeDistinctItems);
						CrmkItemDetailDTO crmkItemDetailDTO2 = new CrmkItemDetailDTO();
						crmkItemDetailDTO2.setItemId(Integer.parseInt(object[0].toString()));
						crmkItemDetailDTO2 = storeDistinctItems.get(storeDistinctItems.indexOf(crmkItemDetailDTO2));
						crmkItemDetailDTO2
								.setFreeQty(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? object[6].toString() : "0");
						crmkItemDetailDTO2
								.setRealQty(object[6] != null && object[6] != "" ? object[6].toString() : "0");
						crmkItemDetailDTO2
								.setRsrvQty(object[6] != null && object[6] != "" && object[3].toString().equals("81")
										? object[6].toString() : "0");
						crmkItemGovDTO.getStoreList().add(crmkItemStoreDTO);
					}
				}
			} else {
				crmkItemGovDTO.setTotalReal(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
						? object[6].toString() : "0");
				crmkItemGovDTO.setTotalFree(object[6] != null && object[6] != "" ? object[6].toString() : "0");
				crmkItemGovDTO.setTotalRsrv(object[6] != null && object[6] != "" && object[3].toString().equals("81")
						? object[6].toString() : "0");
				List<CrmkItemDetailDTO> distinctItems = new ArrayList<CrmkItemDetailDTO>();
				for (CrmkItemDetailDTO temp : itemTemplate) {
					distinctItems.add(new CrmkItemDetailDTO(temp.getItemId(), temp.getC(), temp.getTone()));
				}
				crmkItemGovDTO.setDistinctItems(distinctItems);
				crmkItemDetailDTO = crmkItemGovDTO.getDistinctItems()
						.get(crmkItemGovDTO.getDistinctItems().indexOf(crmkItemDetailDTO));
				crmkItemDetailDTO.setRealQty(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
						? object[6].toString() : "0");
				crmkItemDetailDTO.setFreeQty(object[6] != null && object[6] != "" ? object[6].toString() : "0");
				crmkItemDetailDTO.setRsrvQty(object[6] != null && object[6] != "" && object[3].toString().equals("81")
						? object[6].toString() : "0");
				crmkItemGovDTO.setStoreList(new ArrayList<>());
				
				if (!object[3].toString().equals("81")) {
					CrmkItemStoreDTO crmkItemStoreDTO = new CrmkItemStoreDTO();
					crmkItemStoreDTO.setStoreName(object[4].toString().replace("}", ""));
					crmkItemStoreDTO
							.setTotalFree(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
									? object[6].toString() : "0");

					CrmkItemDetailDTO crmkItemDetailDTO2 = new CrmkItemDetailDTO();
					crmkItemDetailDTO2.setItemId(Integer.parseInt(object[0].toString()));

					List<CrmkItemDetailDTO> storeDistinctItems = new ArrayList<CrmkItemDetailDTO>();
					for (CrmkItemDetailDTO temp : itemTemplate) {
						storeDistinctItems.add(new CrmkItemDetailDTO(temp.getItemId(), temp.getC(), temp.getTone()));
					}

					crmkItemDetailDTO2 = storeDistinctItems.get(storeDistinctItems.indexOf(crmkItemDetailDTO2));

					crmkItemDetailDTO2
							.setFreeQty(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
									? object[6].toString() : "0");
					crmkItemDetailDTO2.setRealQty(object[6] != null && object[6] != "" ? object[6].toString() : "0");
					crmkItemDetailDTO2
							.setRsrvQty(object[6] != null && object[6] != "" && object[3].toString().equals("81")
									? object[6].toString() : "0");
					crmkItemStoreDTO.setItemDteailList(storeDistinctItems);
					crmkItemGovDTO.getStoreList().add(crmkItemStoreDTO);
				}
				String price = crmkSearchResultRepository.getCrmkDefaultPrice(Long.parseLong(object[0].toString()),
						pList, dList);
				crmkItemGovDTO.setPrice(price.split(",")[0]);
				crmkItemGovDTO.setPriceType(price.indexOf(",") == -1 ? "" : price.split(",")[1]);
				crmkItemGovDTO.setpList(price.indexOf(",") == -1 ? "" : price.split(",")[2]);
				crmkItemGovDTO.setdList(price.indexOf(",") == -1 ? "" : price.split(",")[3]);
				govList.add(crmkItemGovDTO);
			}
		}

		if (govList.isEmpty()) {
			List<Object[]> itemList = crmkSearchResultRepository.getCrmkItemDetails(typeId, sizeId, dekalaId,
					factoryNo == null ? null : factoryNo, frz);
			if (itemList.isEmpty()) {
				return govList;
			}
			if (itemList.get(0) != null && itemList.get(0).length == 3) {
				CrmkItemGovDTO crmkItemGovDTO = new CrmkItemGovDTO();
				crmkItemGovDTO.setGovName("");
				crmkItemGovDTO.setTotalFree("");
				crmkItemGovDTO.setTotalReal("");
				crmkItemGovDTO.setTotalRsrv("");
				crmkItemGovDTO.setPackageSize(itemList.get(0)[2].toString());
				crmkItemGovDTO.setProductName(itemList.get(0)[1].toString().replaceAll("\\\\", " / "));
				crmkItemGovDTO.setProductName(crmkItemGovDTO.getProductName().replace("/  /", "/"));
				crmkItemGovDTO.setProductName(crmkItemGovDTO.getProductName().replace("/  /", "/"));
				crmkItemGovDTO.setProductName(crmkItemGovDTO.getProductName().replace("/  /", "/"));
				String price = crmkSearchResultRepository.getCrmkDefaultPrice(Long.parseLong(itemList.get(0)[0] + ""),
						pList, dList);
				crmkItemGovDTO.setPrice(price.split(",")[0]);
				crmkItemGovDTO.setPriceType(price.indexOf(",") == -1 ? "" : price.split(",")[1]);
				crmkItemGovDTO.setpList(price.indexOf(",") == -1 ? "" : price.split(",")[2]);
				crmkItemGovDTO.setdList(price.indexOf(",") == -1 ? "" : price.split(",")[3]);
				crmkItemGovDTO.setDistinctItems(new ArrayList<CrmkItemDetailDTO>());
				crmkItemGovDTO.setStoreList(new ArrayList<CrmkItemStoreDTO>());
				govList.add(crmkItemGovDTO);
			}
		}

		return govList;
	}

	@Override
	public List<CrmkCrmkQueryDefaultPlistDTO> getCrmkQueryDefaultPlist() {
		List<CrmkCrmkQueryDefaultPlistDTO> CrmkCrmkQueryDefaultPlistDTOList = new ArrayList<>();
		crmkCrmkQueryDefaultPlistRepository.findAll()
				.forEach(x -> CrmkCrmkQueryDefaultPlistDTOList.add(constructCrmkQueryDefaultPlistModel(x)));
		return CrmkCrmkQueryDefaultPlistDTOList;
	}

	private CrmkCrmkQueryDefaultPlistDTO constructCrmkQueryDefaultPlistModel(
			CrmkCrmkQueryDefaultPlist crmkCrmkQueryDefaultPlist) {
		CrmkCrmkQueryDefaultPlistDTO crmkCrmkQueryDefaultPlistDTO = new CrmkCrmkQueryDefaultPlistDTO();
		crmkCrmkQueryDefaultPlistDTO.setDefaultValue(crmkCrmkQueryDefaultPlist.getDefaultValue());
		crmkCrmkQueryDefaultPlistDTO.setNo(crmkCrmkQueryDefaultPlist.getNo());
		crmkCrmkQueryDefaultPlistDTO
				.setNotes(crmkCrmkQueryDefaultPlist.getNotes() == null ? "" : crmkCrmkQueryDefaultPlist.getNotes());
		crmkCrmkQueryDefaultPlistDTO.setTrnsDate(crmkCrmkQueryDefaultPlist.getTrnsDate());
		crmkCrmkQueryDefaultPlistDTO.setId(crmkCrmkQueryDefaultPlist.getId());
		return crmkCrmkQueryDefaultPlistDTO;
	}

	@Override
	public PriceDTO getCrmkItemPrice(Long typeId, Long sizeId, String factoryNo, Long dekalaId, Long frz, Long pList,
			Long dList) {
		List<CrmkCrmkItemVU> crmkist = crmkSearchResultRepository.getCrmkSearchResult(typeId, sizeId, dekalaId,
				factoryNo, frz);
		String priceDetails = crmkSearchResultRepository.getCrmkDefaultPrice(crmkist.get(0).getItemId(), pList, dList);
		PriceDTO priceDTO = new PriceDTO();
		priceDTO.setPrice(priceDetails.split(",")[0]);
		priceDTO.setPriceType(priceDetails.indexOf(",") == -1 ? "" : priceDetails.split(",")[1]);
		return priceDTO;
	}

	@Override
	public List<CrmkCrmkQueryDefaultDlistDTO> getCrmkQueryDefaultDlist() {
		List<CrmkCrmkQueryDefaultDlistDTO> CrmkCrmkQueryDefaultDlistDTOList = new ArrayList<>();
		crmkCrmkQueryDefaultDlistRepository.findAll()
				.forEach(x -> CrmkCrmkQueryDefaultDlistDTOList.add(constructCrmkQueryDefaultDlistModel(x)));
		return CrmkCrmkQueryDefaultDlistDTOList;
	}

	private CrmkCrmkQueryDefaultDlistDTO constructCrmkQueryDefaultDlistModel(
			CrmkCrmkQueryDefaultDlist crmkCrmkQueryDefaultDlist) {
		CrmkCrmkQueryDefaultDlistDTO crmkCrmkQueryDefaultDlistDTO = new CrmkCrmkQueryDefaultDlistDTO();
		crmkCrmkQueryDefaultDlistDTO.setDefaultValue(crmkCrmkQueryDefaultDlist.getDefaultValue());
		crmkCrmkQueryDefaultDlistDTO.setNo(crmkCrmkQueryDefaultDlist.getNo());
		crmkCrmkQueryDefaultDlistDTO
				.setNotes(crmkCrmkQueryDefaultDlist.getNotes() == null ? "" : crmkCrmkQueryDefaultDlist.getNotes());
		crmkCrmkQueryDefaultDlistDTO.setTrnsDate(crmkCrmkQueryDefaultDlist.getTrnsDate());
		crmkCrmkQueryDefaultDlistDTO.setId(crmkCrmkQueryDefaultDlist.getId());
		return crmkCrmkQueryDefaultDlistDTO;
	}
	
	@Override
	public List<GroupDTO> getCrmkGroups(Long typeId,Long sizeId,Long dekalaId,String factoryNo,Long frz){
		List<Object[]> crmkGroups = crmkSearchResultRepository.getCrmkGroups(typeId, sizeId, dekalaId, factoryNo, frz);
		List<GroupDTO> groupList = new ArrayList<>();
		crmkGroups.forEach(g -> {
			GroupDTO group = new GroupDTO();
			group.setBathName(g[0] == null ? "" : g[0].toString());
			group.setBathColor(g[1] == null ? "" : g[1].toString());
			group.setBathId(Long.parseLong(g[2].toString()));
			groupList.add(group);
		});
		return groupList;
	}
	
	@Override
	public List<GroupDetailsDTO> getGroupDetails(Long bathId, Long govId) {
		List<Object[]> crmkGroups = crmkSearchResultRepository.getGroupDetails(bathId, govId);
		List<GroupDetailsDTO> groupDetailsDTOs = new ArrayList<GroupDetailsDTO>();
		for (Object[] groupDetails : crmkGroups) {
			groupDetailsDTOs.add(new GroupDetailsDTO(
					groupDetails[0] == null ? "" : groupDetails[0].toString(),
					groupDetails[1] == null ? "" : groupDetails[1].toString(),
					groupDetails[2] == null ? "" : groupDetails[2].toString(),
					groupDetails[3] == null ? "" : groupDetails[3].toString(),
					groupDetails[4] == null ? "" : groupDetails[4].toString(),
					groupDetails[5] == null ? "" : groupDetails[5].toString(),
					groupDetails[6] == null ? "" : groupDetails[6].toString(),
					groupDetails[7] == null ? "" : groupDetails[7].toString(),
					groupDetails[8] == null ? "" : groupDetails[8].toString(),
					groupDetails[9] == null ? "" : groupDetails[9].toString(),
					groupDetails[10] == null ? "" : groupDetails[10].toString(),
					groupDetails[11] == null ? "" : groupDetails[11].toString(),
					groupDetails[12] == null ? "" : groupDetails[12].toString(),
					groupDetails[13] == null ? "" : groupDetails[13].toString(),
					groupDetails[14] == null ? "" : groupDetails[14].toString(),
					groupDetails[15] == null ? "" : groupDetails[15].toString(),
					groupDetails[16] == null ? "" : groupDetails[16].toString(),
					groupDetails[17] == null ? "" : groupDetails[17].toString()
					));
		}
		return groupDetailsDTOs;
	}


}

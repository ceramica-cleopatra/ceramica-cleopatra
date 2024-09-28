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

import com.query.dao.CrmkCrmkQueryDefaultDlistRepository;
import com.query.dao.CrmkCrmkQueryDefaultPlistRepository;
import com.query.dao.CrmkDcreQueryDefaultDlistRepository;
import com.query.dao.CrmkDcreQueryDefaultPlistRepository;
import com.query.dao.CrmkDekalaRepository;
import com.query.dao.CrmkSearchResultRepository;
import com.query.dao.CrmkSizeRepository;
import com.query.dao.CrmkTypeRepository;
import com.query.dao.DcreDekalaRepository;
import com.query.dao.DcreSearchResultRepository;
import com.query.dao.DcreSizeRepository;
import com.query.dao.DcreTypeRepository;
import com.query.dto.CrmkCrmkQueryDefaultDlistDTO;
import com.query.dto.CrmkCrmkQueryDefaultPlistDTO;
import com.query.dto.CrmkDcreQueryDefaultDlistDTO;
import com.query.dto.CrmkDcreQueryDefaultPlistDTO;
import com.query.dto.CrmkDekalaDTO;
import com.query.dto.CrmkItemDetailDTO;
import com.query.dto.CrmkSearchResultDTO;
import com.query.dto.CrmkSizeDTO;
import com.query.dto.CrmkTypeDTO;
import com.query.dto.DcreDekalaDTO;
import com.query.dto.DcreItemDetailDTO;
import com.query.dto.DcreItemGovDTO;
import com.query.dto.DcreItemStoreDTO;
import com.query.dto.DcreSearchResultDTO;
import com.query.dto.DcreSizeDTO;
import com.query.dto.DcreTypeDTO;
import com.query.dto.GroupDTO;
import com.query.dto.PriceDTO;
import com.query.dto.CrmkItemGovDTO;
import com.query.dto.CrmkItemStoreDTO;
import com.query.model.CrmkCrmkDekala;
import com.query.model.CrmkCrmkItemVU;
import com.query.model.CrmkCrmkQueryDefaultDlist;
import com.query.model.CrmkCrmkQueryDefaultPlist;
import com.query.model.CrmkCrmkSize;
import com.query.model.CrmkCrmkType;
import com.query.model.CrmkDcreDekala;
import com.query.model.CrmkDcreItemVU;
import com.query.model.CrmkDcreQueryDefaultDlist;
import com.query.model.CrmkDcreQueryDefaultPlist;
import com.query.model.CrmkDcreSize;
import com.query.model.CrmkDcreType;

@Service("dcreQueryService")
public class DcreQueryServiceImpl implements DcreQueryService {
	@Autowired
	DcreDekalaRepository dcreDekalaRepository;

	@Autowired
	DcreTypeRepository dcreTypeRepository;

	@Autowired
	DcreSizeRepository dcreSizeRepository;

	@Autowired
	DcreSearchResultRepository dcreSearchResultRepository;

	@Autowired
	CrmkDcreQueryDefaultPlistRepository crmkDcreQueryDefaultPlistRepository;

	@Autowired
	CrmkDcreQueryDefaultDlistRepository crmkDcreQueryDefaultDlistRepository;

	@Override
	public List<DcreTypeDTO> getDcreTypes(Long id, String name) {
		List<CrmkDcreType> crmkDcreTypes = dcreTypeRepository.getDcreTypes(id, name);
		List<DcreTypeDTO> dcreTypeDTOs = new ArrayList<DcreTypeDTO>();
		for (CrmkDcreType crmkDcreType : crmkDcreTypes) {
			dcreTypeDTOs.add(new DcreTypeDTO(crmkDcreType.getId().toString(), crmkDcreType.getName()));
		}
		return dcreTypeDTOs;
	}

	@Override
	public List<DcreDekalaDTO> getDcreDekala(Long id, String name) {
		List<CrmkDcreDekala> crmkDcreDekalas = dcreDekalaRepository.getDcreDekalas(id, name);
		List<DcreDekalaDTO> dcreDekalaDTOs = new ArrayList<DcreDekalaDTO>();
		for (CrmkDcreDekala crmkDcreDekala : crmkDcreDekalas) {
			dcreDekalaDTOs.add(new DcreDekalaDTO(crmkDcreDekala.getId().toString(), crmkDcreDekala.getName()));
		}
		return dcreDekalaDTOs;
	}

	@Override
	public List<DcreSizeDTO> getDcreSize(Long id, String name) {
		List<CrmkDcreSize> crmkDcreSizes = dcreSizeRepository.getDcreSize(id, name);
		List<DcreSizeDTO> dcreSizeDTOs = new ArrayList<DcreSizeDTO>();
		for (CrmkDcreSize crmkDcreSize : crmkDcreSizes) {
			dcreSizeDTOs.add(new DcreSizeDTO(crmkDcreSize.getId().toString(), crmkDcreSize.getName()));
		}
		return dcreSizeDTOs;
	}

	@Override
	public List<DcreSearchResultDTO> getDcreSearchResult(Long typeId, String typeName, Long sizeId, String sizeName,
			String factoryNo, Long dekalaId, String dekalaName, Long frz, Long colorId, String colorName, Long tablow,
			String page, String perPage) {
		Pageable selectedPage = PageRequest.of(Integer.parseInt(page), Integer.parseInt(perPage));
		Page<CrmkDcreItemVU> pageData = dcreSearchResultRepository.getDcreSearchResult(selectedPage, typeId,
				typeName != null ? "%" + typeName + "%" : null, sizeId, sizeName != null ? "%" + sizeName + "%" : null,
				dekalaId, dekalaName != null ? "%" + dekalaName + "%" : null, factoryNo, frz, colorId,
				colorName != null ? "%" + colorName + "%" : null, tablow);
		List<CrmkDcreItemVU> crmkDcreItemVUs = pageData.getContent();
		List<DcreSearchResultDTO> dcreSearchResultDTOs = new ArrayList<DcreSearchResultDTO>();
		for (CrmkDcreItemVU crmkDcreItemVU : crmkDcreItemVUs) {
			dcreSearchResultDTOs.add(new DcreSearchResultDTO(crmkDcreItemVU.getTypeId().toString(),
					crmkDcreItemVU.getTypeName() == null ? "" : crmkDcreItemVU.getTypeName(),
					crmkDcreItemVU.getSizeId() == null ? "" : crmkDcreItemVU.getSizeId().toString(),
					crmkDcreItemVU.getSizeName() == null ? "" : crmkDcreItemVU.getSizeName(),
					crmkDcreItemVU.getFactoryNo() == null ? "" : crmkDcreItemVU.getFactoryNo().toString(),
					crmkDcreItemVU.getDekalaId() == null ? "" : crmkDcreItemVU.getDekalaId().toString(),
					crmkDcreItemVU.getDekalaName() == null ? "" : crmkDcreItemVU.getDekalaName(),
					crmkDcreItemVU.getFrz() == null ? "" : crmkDcreItemVU.getFrz().toString(),
					crmkDcreItemVU.getColourId() == null ? "" : crmkDcreItemVU.getColourId().toString(),
					crmkDcreItemVU.getColourName() == null ? "" : crmkDcreItemVU.getColourName().toString(),
					crmkDcreItemVU.getTablow() == null ? "" : crmkDcreItemVU.getTablow().toString()));
		}
		return dcreSearchResultDTOs;
	}

	@Override
	public List<DcreItemGovDTO> getDcreSearchResultDetails(Long typeId, Long sizeId, Long factoryNo, Long dekalaId,
			Long frz, Long govId, Long colorId, Long tablow, Long pList, Long dList) {

		List<Object[]> itemDetails = dcreSearchResultRepository.getDcreResultDetails(typeId, sizeId, dekalaId,
				factoryNo, frz, govId, colorId, tablow);
		List<DcreItemGovDTO> govList = new ArrayList<>();
		List<DcreItemDetailDTO> itemTemplate = new ArrayList<>();

		for (Object[] object : itemDetails) {
			DcreItemDetailDTO dcreItemDetailDTO = new DcreItemDetailDTO();
			dcreItemDetailDTO.setItemId(Integer.parseInt(object[0].toString()));
			if (itemTemplate.contains(dcreItemDetailDTO))
				continue;
			dcreItemDetailDTO.setC(object[1] == null || object[1].equals("") ? "" : object[1].toString());
			dcreItemDetailDTO.setTone(object[2] == null ? "" : object[2].toString());
			dcreItemDetailDTO.setFreeQty("0");
			dcreItemDetailDTO.setRealQty("0");
			itemTemplate.add(dcreItemDetailDTO);
		}

		for (Object[] object : itemDetails) {
			DcreItemGovDTO dcreItemGovDTO = new DcreItemGovDTO();
			dcreItemGovDTO.setGovName("");
			dcreItemGovDTO.setProductName(object[7].toString().replaceAll("\\\\", "/"));
			dcreItemGovDTO.setPackageSize(object[8].toString());
			DcreItemDetailDTO dcreItemDetailDTO = new DcreItemDetailDTO();
			dcreItemDetailDTO.setItemId(Integer.parseInt(object[0].toString()));
			if (govList.contains(dcreItemGovDTO)) {
				dcreItemGovDTO = govList.get(govList.indexOf(dcreItemGovDTO));
				dcreItemGovDTO.setTotalReal(Math.round(
						Double.parseDouble(dcreItemGovDTO.getTotalReal() != null ? dcreItemGovDTO.getTotalReal() : "0")
								+ (object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? Math.round(Double.parseDouble(object[6].toString())) : 0))
						+ "");
				dcreItemGovDTO.setTotalRsrv((Math.round(
						Double.parseDouble(dcreItemGovDTO.getTotalRsrv() != null ? dcreItemGovDTO.getTotalRsrv() : "0")
								+ (object[6] != null && object[6] != "" && object[3].toString().equals("81")
										? Math.round(Double.parseDouble(object[6].toString())) : 0)))
						+ "");
				dcreItemGovDTO.setTotalFree((Math.round(
						Double.parseDouble(dcreItemGovDTO.getTotalFree() != null ? dcreItemGovDTO.getTotalFree() : "0"))
						+ (object[6] != null && object[6] != "" ? Math.round(Double.parseDouble(object[6].toString()))
								: 0))
						+ "");
				dcreItemDetailDTO = dcreItemGovDTO.getDistinctItems()
						.get(dcreItemGovDTO.getDistinctItems().indexOf(dcreItemDetailDTO));
				dcreItemDetailDTO.setRealQty((Math
						.round(Double.parseDouble(
								dcreItemDetailDTO.getRealQty() != null ? dcreItemDetailDTO.getRealQty() : "0"))
						+ (object[6] != null && object[6] != "" && !object[3].toString().equals("81")
								? Math.round(Double.parseDouble(object[6].toString())) : 0))
						+ "");
				dcreItemDetailDTO.setFreeQty((Math
						.round(Double.parseDouble(
								dcreItemDetailDTO.getFreeQty() != null ? dcreItemDetailDTO.getFreeQty() : "0"))
						+ (object[6] != null && object[6] != "" ? Math.round(Double.parseDouble(object[6].toString()))
								: 0))
						+ "");
				dcreItemDetailDTO.setRsrvQty((Math.round(Double
						.parseDouble(dcreItemDetailDTO.getRsrvQty() != null ? dcreItemDetailDTO.getRsrvQty() : "0")
						+ (object[6] != null && object[6] != "" && object[3].toString().equals("81")
								? Math.round(Double.parseDouble(object[6].toString())) : 0)))
						+ "");
				if (!object[3].toString().equals("81")) {
					DcreItemStoreDTO dcreItemStoreDTO = new DcreItemStoreDTO();
					dcreItemStoreDTO.setStoreName(object[4].toString().replace("}", ""));

					if (dcreItemGovDTO.getStoreList().contains(dcreItemStoreDTO)) {
						dcreItemStoreDTO = dcreItemGovDTO.getStoreList()
								.get(dcreItemGovDTO.getStoreList().indexOf(dcreItemStoreDTO));
						dcreItemStoreDTO.setTotalFree((Math
								.round(Double.parseDouble(dcreItemStoreDTO.getTotalFree() != null
										? dcreItemStoreDTO.getTotalFree() : "0"))
								+ (object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? Math.round(Double.parseDouble(object[6].toString())) : 0))
								+ "");
						DcreItemDetailDTO dcreItemDetailDTO2 = new DcreItemDetailDTO();
						dcreItemDetailDTO2.setItemId(Integer.parseInt(object[0].toString()));
						dcreItemDetailDTO2 = dcreItemStoreDTO.getItemDteailList()
								.get(dcreItemStoreDTO.getItemDteailList().indexOf(dcreItemDetailDTO2));
						dcreItemDetailDTO2
								.setFreeQty(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? object[6].toString() : "0");
						dcreItemDetailDTO2
								.setRealQty(object[6] != null && object[6] != "" ? object[6].toString() : "0");
						dcreItemDetailDTO2
								.setRsrvQty(object[6] != null && object[6] != "" && object[3].toString().equals("81")
										? object[6].toString() : "0");
					} else {
						dcreItemStoreDTO
								.setTotalFree(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? object[6].toString() : "0");

						List<DcreItemDetailDTO> storeDistinctItems = new ArrayList<DcreItemDetailDTO>();
						for (DcreItemDetailDTO temp : itemTemplate) {
							storeDistinctItems
									.add(new DcreItemDetailDTO(temp.getItemId(), temp.getC(), temp.getTone()));
						}
						dcreItemStoreDTO.setItemDteailList(storeDistinctItems);
						DcreItemDetailDTO dcreItemDetailDTO2 = new DcreItemDetailDTO();
						dcreItemDetailDTO2.setItemId(Integer.parseInt(object[0].toString()));
						dcreItemDetailDTO2 = storeDistinctItems.get(storeDistinctItems.indexOf(dcreItemDetailDTO2));
						dcreItemDetailDTO2
								.setFreeQty(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
										? object[6].toString() : "0");
						dcreItemDetailDTO2
								.setRealQty(object[6] != null && object[6] != "" ? object[6].toString() : "0");
						dcreItemDetailDTO2
								.setRsrvQty(object[6] != null && object[6] != "" && object[3].toString().equals("81")
										? object[6].toString() : "0");
						dcreItemGovDTO.getStoreList().add(dcreItemStoreDTO);
					}
				}
			} else {
				dcreItemGovDTO.setTotalReal(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
						? object[6].toString() : "0");
				dcreItemGovDTO.setTotalFree(object[6] != null && object[6] != "" ? object[6].toString() : "0");
				dcreItemGovDTO.setTotalRsrv(object[6] != null && object[6] != "" && object[3].toString().equals("81")
						? object[6].toString() : "0");
				List<DcreItemDetailDTO> distinctItems = new ArrayList<DcreItemDetailDTO>();
				for (DcreItemDetailDTO temp : itemTemplate) {
					distinctItems.add(new DcreItemDetailDTO(temp.getItemId(), temp.getC(), temp.getTone()));
				}
				dcreItemGovDTO.setDistinctItems(distinctItems);
				dcreItemDetailDTO = dcreItemGovDTO.getDistinctItems()
						.get(dcreItemGovDTO.getDistinctItems().indexOf(dcreItemDetailDTO));
				dcreItemDetailDTO.setRealQty(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
						? object[6].toString() : "0");
				dcreItemDetailDTO.setFreeQty(object[6] != null && object[6] != "" ? object[6].toString() : "0");
				dcreItemDetailDTO.setRsrvQty(object[6] != null && object[6] != "" && object[3].toString().equals("81")
						? object[6].toString() : "0");
				dcreItemGovDTO.setStoreList(new ArrayList<>());
				
				if (!object[3].toString().equals("81")) {
					DcreItemStoreDTO dcreItemStoreDTO = new DcreItemStoreDTO();
					dcreItemStoreDTO.setStoreName(object[4].toString().replace("}", ""));
					dcreItemStoreDTO
							.setTotalFree(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
									? object[6].toString() : "0");

					DcreItemDetailDTO dcreItemDetailDTO2 = new DcreItemDetailDTO();
					dcreItemDetailDTO2.setItemId(Integer.parseInt(object[0].toString()));

					List<DcreItemDetailDTO> storeDistinctItems = new ArrayList<DcreItemDetailDTO>();
					for (DcreItemDetailDTO temp : itemTemplate) {
						storeDistinctItems.add(new DcreItemDetailDTO(temp.getItemId(), temp.getC(), temp.getTone()));
					}

					dcreItemDetailDTO2 = storeDistinctItems.get(storeDistinctItems.indexOf(dcreItemDetailDTO2));

					dcreItemDetailDTO2
							.setFreeQty(object[6] != null && object[6] != "" && !object[3].toString().equals("81")
									? object[6].toString() : "0");
					dcreItemDetailDTO2.setRealQty(object[6] != null && object[6] != "" ? object[6].toString() : "0");
					dcreItemDetailDTO2
							.setRsrvQty(object[6] != null && object[6] != "" && object[3].toString().equals("81")
									? object[6].toString() : "0");
					dcreItemStoreDTO.setItemDteailList(storeDistinctItems);
					dcreItemGovDTO.getStoreList().add(dcreItemStoreDTO);
				}
				String price = dcreSearchResultRepository.getDcreDefaultPrice(Long.parseLong(object[0].toString()),
						pList, dList);
				dcreItemGovDTO.setPrice(price.split(",")[0]);
				dcreItemGovDTO.setPriceType(price.indexOf(",") == -1 ? "" : price.split(",")[1]);
				dcreItemGovDTO.setpList(price.indexOf(",") == -1 ? "" : price.split(",")[2]);
				dcreItemGovDTO.setdList(price.indexOf(",") == -1 ? "" : price.split(",")[3]);
				govList.add(dcreItemGovDTO);
			}
		}

		if (govList.isEmpty()) {
			List<Object[]> itemList = dcreSearchResultRepository.getDcreItemDetails(typeId, sizeId, dekalaId,
					factoryNo == null ? null : factoryNo, frz, colorId, tablow);
			if (itemList.isEmpty()) {
				return govList;
			}
			if (itemList.get(0) != null && itemList.get(0).length == 3) {
				DcreItemGovDTO dcreItemGovDTO = new DcreItemGovDTO();
				dcreItemGovDTO.setGovName("");
				dcreItemGovDTO.setTotalFree("");
				dcreItemGovDTO.setTotalReal("");
				dcreItemGovDTO.setTotalRsrv("");
				dcreItemGovDTO.setPackageSize(itemList.get(0)[2].toString());
				dcreItemGovDTO.setProductName(itemList.get(0)[1].toString().replaceAll("\\\\", "/"));
				String price = dcreSearchResultRepository.getDcreDefaultPrice(Long.parseLong(itemList.get(0)[0] + ""),
						pList, dList);
				dcreItemGovDTO.setPrice(price.split(",")[0]);
				dcreItemGovDTO.setPriceType(price.indexOf(",") == -1 ? "" : price.split(",")[1]);
				dcreItemGovDTO.setpList(price.indexOf(",") == -1 ? "" : price.split(",")[2]);
				dcreItemGovDTO.setdList(price.indexOf(",") == -1 ? "" : price.split(",")[3]);
				dcreItemGovDTO.setDistinctItems(new ArrayList<DcreItemDetailDTO>());
				dcreItemGovDTO.setStoreList(new ArrayList<DcreItemStoreDTO>());
				govList.add(dcreItemGovDTO);
			}
		}

		return govList;
	}

	@Override
	public List<CrmkDcreQueryDefaultPlistDTO> getDcreQueryDefaultPlist() {
		List<CrmkDcreQueryDefaultPlistDTO> CrmkDcreQueryDefaultPlistDTOList = new ArrayList<>();
		crmkDcreQueryDefaultPlistRepository.findAll()
				.forEach(x -> CrmkDcreQueryDefaultPlistDTOList.add(constructDcreQueryDefaultPlistModel(x)));
		return CrmkDcreQueryDefaultPlistDTOList;
	}

	private CrmkDcreQueryDefaultPlistDTO constructDcreQueryDefaultPlistModel(
			CrmkDcreQueryDefaultPlist crmkDcreQueryDefaultPlist) {
		CrmkDcreQueryDefaultPlistDTO crmkDcreQueryDefaultPlistDTO = new CrmkDcreQueryDefaultPlistDTO();
		crmkDcreQueryDefaultPlistDTO.setDefaultValue(crmkDcreQueryDefaultPlist.getDefaultValue());
		crmkDcreQueryDefaultPlistDTO.setNo(crmkDcreQueryDefaultPlist.getNo());
		crmkDcreQueryDefaultPlistDTO
				.setNotes(crmkDcreQueryDefaultPlist.getNotes() == null ? "" : crmkDcreQueryDefaultPlist.getNotes());
		crmkDcreQueryDefaultPlistDTO.setTrnsDate(crmkDcreQueryDefaultPlist.getTrnsDate());
		crmkDcreQueryDefaultPlistDTO.setId(crmkDcreQueryDefaultPlist.getId());
		return crmkDcreQueryDefaultPlistDTO;
	}

	@Override
	public PriceDTO getDcreItemPrice(Long typeId, Long sizeId, String factoryNo, Long dekalaId, Long frz, Long colorId,
			Long tablow, Long pList, Long dList) {
		System.out.println("typeId>>" + typeId + "sizeId>>" + sizeId + "factoryNo>>" + factoryNo + "dekalaId>>"
				+ dekalaId + "frz>>" + frz + "colorId>>" + colorId + "tablow>>" + tablow + "pList>>" + pList + "dList>>"
				+ dList);
		List<CrmkDcreItemVU> dcreList = dcreSearchResultRepository.getDcreSearchResult(typeId, sizeId, dekalaId,
				factoryNo, frz, colorId, tablow);
		String priceDetails = dcreSearchResultRepository.getDcreDefaultPrice(dcreList.get(0).getItemId(), pList, dList);
		PriceDTO priceDTO = new PriceDTO();
		priceDTO.setPrice(priceDetails.split(",")[0]);
		priceDTO.setPriceType(priceDetails.indexOf(",") == -1 ? "" : priceDetails.split(",")[1]);
		return priceDTO;
	}

	@Override
	public List<CrmkDcreQueryDefaultDlistDTO> getDcreQueryDefaultDlist() {
		List<CrmkDcreQueryDefaultDlistDTO> CrmkDcreQueryDefaultDlistDTOList = new ArrayList<>();
		crmkDcreQueryDefaultDlistRepository.findAll()
				.forEach(x -> CrmkDcreQueryDefaultDlistDTOList.add(constructDcreQueryDefaultDlistModel(x)));
		return CrmkDcreQueryDefaultDlistDTOList;
	}

	private CrmkDcreQueryDefaultDlistDTO constructDcreQueryDefaultDlistModel(
			CrmkDcreQueryDefaultDlist crmkDcreQueryDefaultDlist) {
		CrmkDcreQueryDefaultDlistDTO crmkDcreQueryDefaultDlistDTO = new CrmkDcreQueryDefaultDlistDTO();
		crmkDcreQueryDefaultDlistDTO.setDefaultValue(crmkDcreQueryDefaultDlist.getDefaultValue());
		crmkDcreQueryDefaultDlistDTO.setNo(crmkDcreQueryDefaultDlist.getNo());
		crmkDcreQueryDefaultDlistDTO
				.setNotes(crmkDcreQueryDefaultDlist.getNotes() == null ? "" : crmkDcreQueryDefaultDlist.getNotes());
		crmkDcreQueryDefaultDlistDTO.setTrnsDate(crmkDcreQueryDefaultDlist.getTrnsDate());
		crmkDcreQueryDefaultDlistDTO.setId(crmkDcreQueryDefaultDlist.getId());
		return crmkDcreQueryDefaultDlistDTO;
	}
	
	@Override
	public List<GroupDTO> getDcreGroups(Long typeId,Long sizeId,Long dekalaId,String factoryNo,Long frz,String tablow,String colorId){
		List<Object[]> crmkGroups = dcreSearchResultRepository.getDcreGroups(typeId, sizeId, dekalaId, factoryNo, frz);
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

}

package com.dms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;

import com.dms.dao.DispenseStatusDao;
import com.dms.dto.OrdrDTO;
import com.dms.dto.OrdrItemsDTO;
import com.dms.dto.TrnsOrdrDTO;
import com.dms.model.DmsTrnsOrdrSrfStatus;;

@Service("dispenseStatusService")
public class DispenseStatusServiceImpl implements DispenseStatusService {

	@Autowired
	private DispenseStatusDao dispenseStatusDao;

	@Override
	public TrnsOrdrDTO getOrdrDispenseStatus(Long trnsOrdrId) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		TrnsOrdrDTO trnsOrdrDTO = new TrnsOrdrDTO();
		List<DmsTrnsOrdrSrfStatus> dmsTrnsOrdrSrfStatus = dispenseStatusDao.getOrdrDispenseStatus(trnsOrdrId);
		trnsOrdrDTO.setTrnsOrdrId(trnsOrdrId);
		trnsOrdrDTO.setOrdrList(new ArrayList<OrdrDTO>());
		/*
		 * if (dmsTrnsOrdrSrfStatus.stream().filter(item ->
		 * item.getSrfStatus().equals(1L)).collect(Collectors.toList()) .size()
		 * == dmsTrnsOrdrSrfStatus.size()) {
		 * trnsOrdrDTO.setDispenseStatus("Dispatched"); } else if
		 * (dmsTrnsOrdrSrfStatus.stream().filter(item ->
		 * item.getSrfStatus().equals(2L)) .collect(Collectors.toList()).size()
		 * == dmsTrnsOrdrSrfStatus.size()) {
		 * trnsOrdrDTO.setDispenseStatus("NotDispatched"); } else {
		 * trnsOrdrDTO.setDispenseStatus("PartiallyDispatched"); }
		 */
		Map<Long, List<DmsTrnsOrdrSrfStatus>> ordrMap = dmsTrnsOrdrSrfStatus.stream()
				.collect(Collectors.groupingBy(DmsTrnsOrdrSrfStatus::getOrdrId));
		boolean isDispatched = false;
		boolean isNotDispatched = false;
		boolean isPartiallyDispatched = false;
		ordrMap.forEach((k, v) -> {
			if (v != null & v.get(0)!=null && v.get(0).getCrmkSehy()!=null) {
				OrdrDTO ordrDTO = new OrdrDTO();
				ordrDTO.setOrdrId(k);
				ordrDTO.setBranchName(v.get(0).getBranchName());
				ordrDTO.setDispenseStatus(dispenseStatusDao.getOrdrSrfStatus(k).equals(1L) ? "Dispatched"
						: (dispenseStatusDao.getOrdrSrfStatus(k).equals(2L) ? "NotDispatched" : "PartiallyDispatched"));
				
				ordrDTO.setOrdrNo(v.get(0).getNo());
				ordrDTO.setOrdrType(v.get(0).getCrmkSehy().equals("C") ? "سيراميك"
						: (v.get(0).getCrmkSehy().equals("D") ? "ديكور" : "صحى"));
				ordrDTO.setPrdId(v.get(0).getPrdId());
				ordrDTO.setClntName(v.get(0).getClntName());
				ordrDTO.setTrnsDate(simpleDateFormat.format(v.get(0).getTrnsDate()));
				ordrDTO.setItemList(new ArrayList<>());
				v.forEach(item -> {
					OrdrItemsDTO ordrItemsDTO = new OrdrItemsDTO();
					ordrItemsDTO.setItemName(item.getNoCTone());
					ordrItemsDTO.setRemainQty(item.getRemain());
					ordrItemsDTO.setTotalQty(item.getQty());
					ordrItemsDTO.setDispensedQty(item.getOutQty());
					ordrItemsDTO.setCanceledQty(item.getCanQty());
					ordrDTO.getItemList().add(ordrItemsDTO);
				});
				trnsOrdrDTO.getOrdrList().add(ordrDTO);
			}
		});
		for(OrdrDTO ordrDTO : trnsOrdrDTO.getOrdrList()){
			if(ordrDTO.getDispenseStatus().equals("Dispatched"))
				isDispatched = true;
			else if(ordrDTO.getDispenseStatus().equals("NotDispatched"))
				isNotDispatched = true;
			else isPartiallyDispatched = true;
		}
		if(isPartiallyDispatched || (isDispatched && isNotDispatched))
			trnsOrdrDTO.setDispenseStatus("PartiallyDispatched");
		else if(isDispatched && !isNotDispatched)
			trnsOrdrDTO.setDispenseStatus("Dispatched");
		else if (isNotDispatched) 
			trnsOrdrDTO.setDispenseStatus("NotDispatched");
		return trnsOrdrDTO;
	}

}

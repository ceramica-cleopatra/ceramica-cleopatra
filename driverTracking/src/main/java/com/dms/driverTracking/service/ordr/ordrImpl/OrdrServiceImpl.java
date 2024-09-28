package com.dms.driverTracking.service.ordr.ordrImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dms.driverTracking.dao.OrdrRepository;
import com.dms.driverTracking.dto.DriverDTO;
import com.dms.driverTracking.dto.OrdrDTO;
import com.dms.driverTracking.dto.OrdrDetailDTO;
import com.dms.driverTracking.dto.IntermediateDTO;
import com.dms.driverTracking.model.DmsPlanLiveVW;
import com.dms.driverTracking.service.ordr.OrdrService;

@Service("ordrService")
public class OrdrServiceImpl implements OrdrService {
	@Autowired
	private OrdrRepository ordrRepository;
	
	private SimpleDateFormat simpleDateFormat;

	public IntermediateDTO findOrdrList(Integer pageNo, Integer pageSize, String sortBy,Date updateDate) {
		List<Object> ordrList = new ArrayList<Object>();
		IntermediateDTO intermediateDTO = new IntermediateDTO();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		SimpleDateFormat modificationDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Page<DmsPlanLiveVW> page = ordrRepository.findOrdrList(paging,updateDate);
		List<DmsPlanLiveVW> ordrDataList = page.getContent();
		intermediateDTO.setResultCount(page.getTotalElements());
		simpleDateFormat=new SimpleDateFormat("hh:mm");
		ordrDataList.forEach(ordr -> {
			DriverDTO driver=new DriverDTO.Builder()
					.setDriverId(ordr.getDriverId())
					.setDriverName(ordr.getDriverName())
					.setStatus(ordr.getStatus())
					.setDriverType(ordr.getTypeName())
					.setCarType(ordr.getCarType())
					.setPhoneNo(ordr.getDriverMobile())
					.build();
			OrdrDetailDTO ordrDetail=new OrdrDetailDTO.Builder()
					.setOrdrNo(ordr.getOrdrNo())
					.setAddress(ordr.getAddress())
					.setArea(ordr.getAreaName())
					.setCity(ordr.getCityName())
					.setClientName(ordr.getClntName())
					.setMobile(ordr.getMobile())
					.setTel1(ordr.getTel1())
					.setTel2(ordr.getTel2())
					.setTotalQty(ordr.getTotalQty())
					.setDelieveryFrom(ordr.getTimeFrom()==null ? null : simpleDateFormat.format(ordr.getTimeFrom()))
					.setDelieveryTo(ordr.getTimeTo()==null ? null : simpleDateFormat.format(ordr.getTimeTo()))
					.setLongitude(ordr.getLongitude())
					.setLatitude(ordr.getLatitude())
					.build();
			OrdrDTO ordrDTO = new OrdrDTO.Builder()
					.setBrnId(ordr.getBrnId())
					.setDriver(driver)
					.setOrdrDetail(ordrDetail)
					.setModificationDate(modificationDateFormat.format(ordr.getModificationDate()))
					.setOrdrId(ordr.getId())
					.setBrnName(ordr.getBrnName())
					.setPlanId(ordr.getPlanId())
					.setPlanName(ordr.getPlanName())
					.setTrnsDate(ordr.getTrnsDate())
					.build();
			ordrList.add(ordrDTO);
		});
		intermediateDTO.setPayload(ordrList);
		return intermediateDTO;
	}
}

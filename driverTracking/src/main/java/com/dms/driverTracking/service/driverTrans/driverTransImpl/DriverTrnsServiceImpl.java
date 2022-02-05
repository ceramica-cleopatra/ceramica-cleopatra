package com.dms.driverTracking.service.driverTrans.driverTransImpl;

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

import com.dms.driverTracking.dao.DriverTrnsRepository;
import com.dms.driverTracking.dto.DriverTrnsDTO;
import com.dms.driverTracking.dto.IntermediateDTO;
import com.dms.driverTracking.model.DmsDriverTrnsVW;
import com.dms.driverTracking.service.driverTrans.DriverTrnsService;

@Service("driverTrnsService")
public class DriverTrnsServiceImpl implements DriverTrnsService {
	@Autowired
	DriverTrnsRepository driverTrnsRepository;
	
	@Override
	public IntermediateDTO findDriversTrnsList(Integer pageNo, Integer pageSize, String sortBy, Date updateDate, Integer driverId) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		IntermediateDTO intermediateDTO = new IntermediateDTO();
		List<Object> driverTrnsDTOList = new ArrayList<Object>();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm");
		SimpleDateFormat modificationDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Page<DmsDriverTrnsVW> page = driverTrnsRepository.findDriverTrnsList(paging, updateDate ,driverId);
		intermediateDTO.setResultCount(page.getTotalElements());
		List<DmsDriverTrnsVW> driverTrnsList = page.getContent();
		driverTrnsList.forEach(driverTrns -> {
			DriverTrnsDTO driverTrnsDTO = new DriverTrnsDTO.Builder()
											  .setDriverId(driverTrns.getDriverId())
											  .setDriverName(driverTrns.getDriverName())
											  .setId(driverTrns.getId())
											  .setInTrns(simpleDateFormat.format(driverTrns.getTrnsDateIn()))
											  .setLicense(driverTrns.getLicense())
											  .setModificationDate(modificationDateFormat.format(driverTrns.getModificationDate()))
											  .setOutTrns(simpleDateFormat.format(driverTrns.getTrnsDateOut()))
											  .setStoreId(driverTrns.getStoreId())
											  .setStoreName(driverTrns.getStoreName())
											  .build();
			driverTrnsDTOList.add(driverTrnsDTO);
		});
		
		intermediateDTO.setPayload(driverTrnsDTOList);
		return intermediateDTO;
	}
	
}

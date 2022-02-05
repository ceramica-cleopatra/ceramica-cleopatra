package com.dms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.OrdrDeliveryStatusRepository;
import com.dms.dto.OrdrDeliveryStatusDTO;
import com.dms.model.DmsOrdrDeliveryStatus;

@Service("deliveryStatusUpdateService")
public class DeliveryStatusUpdateServiceImpl implements DeliveryStatusUpdateService{
	@Autowired
	OrdrDeliveryStatusRepository ordrDeliveryStatusRepository;
	@Override
	public void updateOrdrDeliveryStatus(OrdrDeliveryStatusDTO ordrDeliveryStatusDTO) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		DmsOrdrDeliveryStatus dmsOrdrDeliveryStatus = new DmsOrdrDeliveryStatus();
		dmsOrdrDeliveryStatus.setOrdrId(Long.parseLong(ordrDeliveryStatusDTO.getOrdrId()));
		try {
			dmsOrdrDeliveryStatus.setDeliveryDate(simpleDateFormat.parse(ordrDeliveryStatusDTO.getDeliveryDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dmsOrdrDeliveryStatus.setStatus(ordrDeliveryStatusDTO.getStatus());
		ordrDeliveryStatusRepository.save(dmsOrdrDeliveryStatus);
	}

}

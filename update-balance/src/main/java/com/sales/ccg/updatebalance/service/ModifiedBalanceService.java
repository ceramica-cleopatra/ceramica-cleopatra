package com.sales.ccg.updatebalance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.ccg.updatebalance.entity.CrmkSKULstModifiedQty;
import com.sales.ccg.updatebalance.entity.DcreSKULstModifiedQty;
import com.sales.ccg.updatebalance.model.ProductDTO;
import com.sales.ccg.updatebalance.repository.CrmkRepository;
import com.sales.ccg.updatebalance.repository.DcreRepository;

@Service
public class ModifiedBalanceService {
	
	@Autowired
	CrmkRepository crmkRepository;
	
	@Autowired
	DcreRepository dcreRepository;
	
	public List<ProductDTO> findUpdatedProducts(Date modificationDate){
		List<ProductDTO> productModels = new ArrayList<>();
		List<CrmkSKULstModifiedQty> crmkList = crmkRepository.findProductQtyUpdates(modificationDate);
		crmkList.forEach(e -> productModels.add(new ProductDTO(e.getSku(), e.getFreeQty(), e.getGovernId(), e.getLastModifiedDate())));
		List<DcreSKULstModifiedQty> dcreList = dcreRepository.findProductQtyUpdates(modificationDate);
		dcreList.forEach(e -> productModels.add(new ProductDTO(e.getSku(), e.getFreeQty(), e.getGovernId(), e.getLastModifiedDate())));
		return productModels;
	}
}

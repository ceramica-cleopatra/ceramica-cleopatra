package com.sales.ccg.checkBalance.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="CRMK_ORDR_HD")
public class CrmkOrdrHd {
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}

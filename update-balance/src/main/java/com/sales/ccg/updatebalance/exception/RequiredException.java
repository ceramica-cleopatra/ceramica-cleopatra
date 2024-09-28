package com.sales.ccg.updatebalance.exception;

import com.sales.ccg.updatebalance.model.StatusDTO;

public class RequiredException extends RuntimeException{
	private StatusDTO statusDTO;
	public RequiredException(StatusDTO status){
		this.statusDTO=status;
		System.out.println("Required Parameter Exception");
	}
	
	
}

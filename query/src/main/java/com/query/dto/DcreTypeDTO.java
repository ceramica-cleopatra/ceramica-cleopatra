package com.query.dto;

import java.io.Serializable;
import java.util.Date;

public class DcreTypeDTO implements Serializable{
	private String id;
	private String name;
	
	public DcreTypeDTO(String id,String name){
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

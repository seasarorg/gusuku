package org.seasar.gusuku.dto;

import java.io.Serializable;


public class SelectValue implements Serializable {
	private String value;
	private String name;
	
	public SelectValue(String value,String name){
		this.value = value;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}

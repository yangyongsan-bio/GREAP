package com.lyy.util;

import java.io.Serializable;

public class List_change implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String list_change;
	
	
	public String getList_change() {
		return list_change;
	}

	public void setList_change(String list_change) {
		this.list_change = list_change;
	}

	

	@Override
	public String toString() {
		return "[list_change=" + list_change + "]";
	}

}

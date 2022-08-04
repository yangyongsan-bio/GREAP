package com.lyy.Ranalyse;

import java.io.Serializable;

public class Qipao_data implements Serializable, Comparable<Qipao_data>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String data_type;
	
	private String freq;
	
	private String p_value;
	
	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public String getP_value() {
		return p_value;
	}

	public void setP_value(String P_value) {
		p_value = P_value;
	}


	
	@Override
	public String toString() {
		return "[" + p_value  + ",'" + data_type + "'," + freq + "]";
	}

	@Override
	public int compareTo(Qipao_data o) {
		return (int) (Double.valueOf(this.p_value.replaceAll(" ", "")) - Double.valueOf(o.p_value.replaceAll(" ", "")));
	}

}

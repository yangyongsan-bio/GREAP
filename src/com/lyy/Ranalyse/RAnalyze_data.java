package com.lyy.Ranalyse;

import java.io.Serializable;

public class RAnalyze_data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String data_type;
	
	private String freq;
	
	private String number;
	
	private String p_value;
	
	private String bh;
	
	private String bonferroni;
	
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String Nnumber) {
		number = Nnumber;
	}

	public String getP_value() {
		return p_value;
	}

	public void setP_value(String P_value) {
		p_value = P_value;
	}

	public String getBH() {
		return bh;
	}

	public void setBH(String BH) {
		bh = BH;
	}
	
	public String getBonferroni() {
		return bonferroni;
	}

	public void setBonferroni(String Bonferroni) {
		bonferroni = Bonferroni;
	}
	

	
	@Override
	public String toString() {
		return "RAnalyze [data_type=" + data_type + ", freq=" + freq + ", number=" + number + ", p_value="
				+ p_value + ", bh=" + bh + ", bonferroni=" + bonferroni + "]";
	}

	


	
}

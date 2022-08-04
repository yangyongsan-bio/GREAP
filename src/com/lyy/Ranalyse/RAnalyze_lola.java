package com.lyy.Ranalyse;

import java.io.Serializable;

public class RAnalyze_lola implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userSet;
	
	private String dbSet;
	
	private String collection;
	
	private String pValueLog;
	
	private String oddsRatio;
	
	private String support;
	
	private String rnkPV;
	
	private String rnkOR;
	
	private String rnkSup;
	
	private String maxRnk;
	
	private String meanRnk;
	
	private String b;
	
	private String c;
	
	private String d;
	
	private String description;
	
	private String cellType;
	
	private String tissue;
	
	private String antibody;
	
	private String treatment;
	
	private String dataSource;
	
	private String filename;
	
	private String size;
	
	public String getUserSet() {
		return userSet;
	}



	public void setUserSet(String userSet) {
		this.userSet = userSet;
	}



	public String getDbSet() {
		return dbSet;
	}



	public void setDbSet(String dbSet) {
		this.dbSet = dbSet;
	}



	public String getCollection() {
		return collection;
	}



	public void setCollection(String collection) {
		this.collection = collection;
	}



	public String getpValueLog() {
		return pValueLog;
	}



	public void setpValueLog(String pValueLog) {
		this.pValueLog = pValueLog;
	}



	public String getOddsRatio() {
		return oddsRatio;
	}



	public void setOddsRatio(String oddsRatio) {
		this.oddsRatio = oddsRatio;
	}



	public String getSupport() {
		return support;
	}



	public void setSupport(String support) {
		this.support = support;
	}



	public String getRnkPV() {
		return rnkPV;
	}



	public void setRnkPV(String rnkPV) {
		this.rnkPV = rnkPV;
	}



	public String getRnkOR() {
		return rnkOR;
	}



	public void setRnkOR(String rnkOR) {
		this.rnkOR = rnkOR;
	}



	public String getRnkSup() {
		return rnkSup;
	}



	public void setRnkSup(String rnkSup) {
		this.rnkSup = rnkSup;
	}



	public String getMaxRnk() {
		return maxRnk;
	}



	public void setMaxRnk(String maxRnk) {
		this.maxRnk = maxRnk;
	}



	public String getMeanRnk() {
		return meanRnk;
	}



	public void setMeanRnk(String meanRnk) {
		this.meanRnk = meanRnk;
	}



	public String getB() {
		return b;
	}



	public void setB(String b) {
		this.b = b;
	}



	public String getC() {
		return c;
	}



	public void setC(String c) {
		this.c = c;
	}



	public String getD() {
		return d;
	}



	public void setD(String d) {
		this.d = d;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getCellType() {
		return cellType;
	}



	public void setCellType(String cellType) {
		this.cellType = cellType;
	}



	public String getTissue() {
		return tissue;
	}



	public void setTissue(String tissue) {
		this.tissue = tissue;
	}



	public String getAntibody() {
		return antibody;
	}



	public void setAntibody(String antibody) {
		this.antibody = antibody;
	}



	public String getTreatment() {
		return treatment;
	}



	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}



	public String getDataSource() {
		return dataSource;
	}



	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "RAnalyze [userSet =" + userSet  + ", dbSet =" + dbSet  + ", collection=" + collection + ", pValueLog="
				+ pValueLog + ", oddsRatio=" + oddsRatio + ", support=" + support + ", rnkPV=" + rnkPV +  ", rnkOR=" + rnkOR +
				", rnkSup=" + rnkSup + ", maxRnk=" + maxRnk + ", meanRnk=" +  meanRnk + ", b=" + b + ", c=" + c + 
				", d=" + d + ", description=" + description + ", cellType=" + cellType + ", tissue=" + tissue + ", antibody=" + antibody + 
				", treatment=" + treatment + ", dataSource=" + dataSource + ", filename=" + filename + ", size=" + size + "]";
	}





	


	
}

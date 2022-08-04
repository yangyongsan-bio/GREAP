package com.lyy.Ranalyse;

import java.io.Serializable;

public class RAnalyze implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pathwayID;
	
	private String pathwayName;
	
	private String Source;
	
	private String Species;
	
	private String GeneID_Type;
	
	private String AnnGene;
	
	private String GeneNumber;
	
	private String PValue;
	
	private String FDR;

	public String getPathwayID() {
		return pathwayID;
	}

	public void setPathwayID(String pathwayID) {
		this.pathwayID = pathwayID;
	}

	public String getPathwayName() {
		return pathwayName;
	}

	public void setPathwayName(String pathwayName) {
		this.pathwayName = pathwayName;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getSpecies() {
		return Species;
	}

	public void setSpecies(String species) {
		Species = species;
	}

	public String getGeneID_Type() {
		return GeneID_Type;
	}

	public void setGeneID_Type(String geneID_Type) {
		GeneID_Type = geneID_Type;
	}

	public String getAnnGene() {
		return AnnGene;
	}

	public void setAnnGene(String annGene) {
		AnnGene = annGene;
	}

	public String getGeneNumber() {
		return GeneNumber;
	}

	public void setGeneNumber(String geneNumber) {
		GeneNumber = geneNumber;
	}

	public String getPValue() {
		return PValue;
	}

	public void setPValue(String pValue) {
		PValue = pValue;
	}

	public String getFDR() {
		return FDR;
	}

	public void setFDR(String fDR) {
		FDR = fDR;
	}

	@Override
	public String toString() {
		return "RAnalyze [pathwayID=" + pathwayID + ", pathwayName=" + pathwayName + ", Source=" + Source + ", Species="
				+ Species + ", GeneID_Type=" + GeneID_Type + ", AnnGene=" + AnnGene + ", GeneNumber=" + GeneNumber
				+ ", PValue=" + PValue + ", FDR=" + FDR + "]";
	}

}

package com.lyy.model;

import java.io.Serializable;
import java.util.Date;

public class UserEmail implements Serializable {
	private Integer fId;

	private String fUserId;

	private String fEmail;

	private String fPath;

	private String fBedFile;

	private String fHg;

	private String fMethod;

	private String fMin;

	private String fMax;

	private String fIntPValue;

    private String fDatatype;

    private String fSubset;

    private String fLineLength;

	private Date fTime;

	private static final long serialVersionUID = 1L;

	public UserEmail(String userId, String email, String path, Date time) {
		this.fUserId = userId;
		this.fEmail = email;
		this.fPath = path;
		this.fTime = time;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public String getfUserId() {
		return fUserId;
	}

	public void setfUserId(String fUserId) {
		this.fUserId = fUserId;
	}

	public String getfEmail() {
		return fEmail;
	}

	public void setfEmail(String fEmail) {
		this.fEmail = fEmail;
	}

	public String getfPath() {
		return fPath;
	}

	public void setfPath(String fPath) {
		this.fPath = fPath;
	}

	public Date getfTime() {
		return fTime;
	}

	public void setfTime(Date fTime) {
		this.fTime = fTime;
	}

	public String getfBedFile() {
		return fBedFile;
	}

	public void setfBedFile(String fBedFile) {
		this.fBedFile = fBedFile;
	}

	public String getfHg() {
		return fHg;
	}

	public void setfHg(String fHg) {
		this.fHg = fHg;
	}

	public String getfMethod() {
		return fMethod;
	}

	public void setfMethod(String fMethod) {
		this.fMethod = fMethod;
	}

	public String getfMin() {
		return fMin;
	}

	public void setfMin(String fMin) {
		this.fMin = fMin;
	}

	public String getfMax() {
		return fMax;
	}

	public void setfMax(String fMax) {
		this.fMax = fMax;
	}

	public String getfIntPValue() {
		return fIntPValue;
	}

	public void setfIntPValue(String fIntPValue) {
		this.fIntPValue = fIntPValue;
	}

	public String getfDatatype() {
		return fDatatype;
	}

	public void setfDatatype(String fDatatype) {
		this.fDatatype = fDatatype;
	}

	public String getfSubset() {
		return fSubset;
	}

	public void setfSubset(String fSubset) {
		this.fSubset = fSubset;
	}

	public String getfLineLength() {
		return fLineLength;
	}

	public void setfLineLength(String fLineLength) {
		this.fLineLength = fLineLength;
	}

	@Override
	public String toString() {
		return "UserEmail [fId=" + fId + ", fUserId=" + fUserId + ", fEmail=" + fEmail + ", fPath=" + fPath
				+ ", fBedFile=" + fBedFile + ", fHg=" + fHg + ", fMethod=" + fMethod + ", fMin=" + fMin + ", fMax="
				+ fMax + ", fIntPValue=" + fIntPValue + ", fDatatype=" + fDatatype + ", fSubset=" + fSubset
				+ ", fLineLength=" + fLineLength + ", fTime=" + fTime + "]";
	}

}
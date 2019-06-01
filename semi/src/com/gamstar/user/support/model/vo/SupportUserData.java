package com.gamstar.user.support.model.vo;

public class SupportUserData {
	private int userNo;
	private String supportTitle;
	private String supportContent;
	private String supportMediaOri;
	private String supportMediaRe;
	private int supportMediaType;
	
	public SupportUserData() {
		
	}


	public SupportUserData(int userNo, String supportTitle, String supportContent, String supportMediaOri,
			String supportMediaRe, int supportMediaType) {
		super();
		this.userNo = userNo;
		this.supportTitle = supportTitle;
		this.supportContent = supportContent;
		this.supportMediaOri = supportMediaOri;
		this.supportMediaRe = supportMediaRe;
		this.supportMediaType = supportMediaType;
	}

	public String getSupportTitle() {
		return supportTitle;
	}

	public void setSupportTitle(String supportTitle) {
		this.supportTitle = supportTitle;
	}

	public String getSupportContent() {
		return supportContent;
	}

	public void setSupportContent(String supportContent) {
		this.supportContent = supportContent;
	}

	public String getSupportMediaOri() {
		return supportMediaOri;
	}

	public void setSupportMediaOri(String supportMediaOri) {
		this.supportMediaOri = supportMediaOri;
	}

	public String getSupportMediaRe() {
		return supportMediaRe;
	}

	public void setSupportMediaRe(String supportMediaRe) {
		this.supportMediaRe = supportMediaRe;
	}

	public int getSupportMediaType() {
		return supportMediaType;
	}

	public void setSupportMediaType(int supportMediaType) {
		this.supportMediaType = supportMediaType;
	}
	
	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	@Override
	public String toString() {
		return "SupportUserData [supportTitle=" + supportTitle + ", supportContent=" + supportContent
				+ ", supportMediaOri=" + supportMediaOri + ", supportMediaRe=" + supportMediaRe + ", supportMediaType="
				+ supportMediaType + "]";
	}
	
}

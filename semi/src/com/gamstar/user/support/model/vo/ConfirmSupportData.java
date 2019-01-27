package com.gamstar.user.support.model.vo;

import java.util.Date;

public class ConfirmSupportData {
	private int supportNo;
	private int supportState;
	private String supportTitle;
	private Date supportDate;
	
	public ConfirmSupportData() {
		
	}
	public ConfirmSupportData(int supportNo, int supportState, String supportTitle, Date supportDate) {
		super();
		this.supportNo = supportNo;
		this.supportState = supportState;
		this.supportTitle = supportTitle;
		this.supportDate = supportDate;
	}
	
	
	public int getSupportNo() {
		return supportNo;
	}
	public void setSupportNo(int supportNo) {
		this.supportNo = supportNo;
	}
	public int getSupportState() {
		return supportState;
	}
	public void setSupportState(int supportState) {
		this.supportState = supportState;
	}
	public String getSupportTitle() {
		return supportTitle;
	}
	public void setSupportTitle(String supportTitle) {
		this.supportTitle = supportTitle;
	}
	public Date getSupportDate() {
		return supportDate;
	}
	public void setSupportDate(Date supportDate) {
		this.supportDate = supportDate;
	}
	@Override
	public String toString() {
		return "ConfirmSupportData [supportNo=" + supportNo + ", supportState=" + supportState + ", supportTitle="
				+ supportTitle + ", supportDate=" + supportDate + "]";
	}
	
	
}

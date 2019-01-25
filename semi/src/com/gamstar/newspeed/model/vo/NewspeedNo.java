package com.gamstar.newspeed.model.vo;

public class NewspeedNo {
	
	private String newspeedNo;
	
	public NewspeedNo(String newspeedNo) {
		super();
		this.newspeedNo = newspeedNo;
	}

	public String getNewspeedNo() {
		return newspeedNo;
	}

	public void setNewspeedNo(String newspeedNo) {
		this.newspeedNo = newspeedNo;
	}

	@Override
	public String toString() {
		return "NewspeedNo [newspeedNo=" + newspeedNo + "]";
	}
}

package com.gamstar.model.vo;

public class NewspeedMediaTag {

	private int mediaIndex;
	private int newspeedNo;
	private int userNo;
	private double x;
	private double y;

	public int getMediaIndex() {
		return mediaIndex;
	}

	public void setMediaIndex(int mediaIndex) {
		this.mediaIndex = mediaIndex;
	}

	public int getNewspeedNo() {
		return newspeedNo;
	}

	public void setNewspeedNo(int newspeedNo) {
		this.newspeedNo = newspeedNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "NewspeedMediaTag [mediaIndex=" + mediaIndex + ", newspeedNo=" + newspeedNo + ", userNo=" + userNo
				+ ", x=" + x + ", y=" + y + ", getMediaIndex()=" + getMediaIndex() + ", getNewspeedNo()="
				+ getNewspeedNo() + ", getUserNo()=" + getUserNo() + ", getX()=" + getX() + ", getY()=" + getY() + "]";
	}

}

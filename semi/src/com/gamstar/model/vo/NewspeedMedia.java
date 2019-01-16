package com.gamstar.model.vo;

public class NewspeedMedia {
	public static final int MEDIA_TYPE_IMAGE = 0;
	public static final int MEDIA_TYPE_VIDEO = 1;
	
	private int index;
	private int newspeedNo;
	private int type;
	private String path;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getNewspeedNo() {
		return newspeedNo;
	}

	public void setNewspeedNo(int newspeedNo) {
		this.newspeedNo = newspeedNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

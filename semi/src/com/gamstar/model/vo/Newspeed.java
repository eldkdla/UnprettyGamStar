package com.gamstar.model.vo;

import java.util.Date;

public class Newspeed {
	public static int ENABLE_TRUE = 1;
	public static int ENABLE_FALSE = 0;
	
	private int no;
	private String content;
	private Date date;
	private boolean enable;
	private int userNo;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWiteDate() {
		return date;
	}

	public void setWiteDate(Date witeDate) {
		this.date = witeDate;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

}

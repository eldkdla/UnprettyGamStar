package com.gamstar.newspeed.model.vo;

import java.util.Date;

public class Newspeed {
	public static int ENABLE_TRUE = 1;
	public static int ENABLE_FALSE = 0;
	
	private int no;
	private int userNo;
	private String content;
	private boolean enable;
	private Date date;
	private String beforeDay;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Newspeed [no=" + no + ", userNo=" + userNo + ", content=" + content + ", enable=" + enable + ", date="
				+ date + ", getNo()=" + getNo() + ", getUserNo()=" + getUserNo() + ", getContent()=" + getContent()
				+ ", isEnable()=" + isEnable() + ", getDate()=" + getDate() + "]";
	}

	public String getBeforeDay() {
		return beforeDay;
	}

	public void setBeforeDay(String beforeDay) {
		this.beforeDay = beforeDay;
	}

}

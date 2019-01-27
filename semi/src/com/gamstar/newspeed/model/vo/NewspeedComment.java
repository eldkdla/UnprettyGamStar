package com.gamstar.newspeed.model.vo;

import java.util.Date;

public class NewspeedComment {
	private int newspeedNo;
	private int no;
	private int rootNo;
	private String content;
	private int userNo;
	private Date date;
	private int enable;
	private String userName;
	private int level;
	private String beforeDay;
	
	public int getNewspeedNo() {
		return newspeedNo;
	}

	public void setNewspeedNo(int newspeedNo) {
		this.newspeedNo = newspeedNo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getRootNo() {
		return rootNo;
	}

	public void setRootNo(int rootNo) {
		this.rootNo = rootNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "NewspeedComment [newspeedNo=" + newspeedNo + ", no=" + no + ", rootNo=" + rootNo + ", content="
				+ content + ", userNo=" + userNo + ", date=" + date + ", enable=" + enable + ", userName=" + userName
				+ ", getNewspeedNo()=" + getNewspeedNo() + ", getNo()=" + getNo() + ", getRootNo()=" + getRootNo()
				+ ", getContent()=" + getContent() + ", getUserNo()=" + getUserNo() + ", getDate()=" + getDate()
				+ ", getEnable()=" + getEnable() + ", getUserName()=" + getUserName() + "]";
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getBeforeDay() {
		return beforeDay;
	}

	public void setBeforeDay(String beforeDay) {
		this.beforeDay = beforeDay;
	}

}

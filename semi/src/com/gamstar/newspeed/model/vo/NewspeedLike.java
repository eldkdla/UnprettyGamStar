package com.gamstar.newspeed.model.vo;

import java.util.Date;

public class NewspeedLike {
	
	private int no;
	private int userNo;
	private Date likeDate;
	
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
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
	@Override
	public String toString() {
		return "NewspeedLike [no=" + no + ", userNo=" + userNo + ", likeDate=" + likeDate + "]";
	}
	
}

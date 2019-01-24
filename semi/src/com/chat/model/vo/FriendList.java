package com.chat.model.vo;

import java.util.Date;

public class FriendList {
	private int followerUserNo;
	private int followUserNo;
	private Date followDate;
	
	public FriendList() {}

	public FriendList(int followerUserNo, int followUserNo, Date followDate) {
		super();
		this.followerUserNo = followerUserNo;
		this.followUserNo = followUserNo;
		this.followDate = followDate;
	}

	public int getFollowerUserNo() {
		return followerUserNo;
	}

	public void setFollowerUserNo(int followerUserNo) {
		this.followerUserNo = followerUserNo;
	}

	public int getFollowUserNo() {
		return followUserNo;
	}

	public void setFollowUserNo(int followUserNo) {
		this.followUserNo = followUserNo;
	}

	public Date getFollowDate() {
		return followDate;
	}

	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}

	@Override
	public String toString() {
		return "FriendList [followerUserNo=" + followerUserNo + ", followUserNo=" + followUserNo + ", followDate="
				+ followDate + "]";
	}
	
	
}

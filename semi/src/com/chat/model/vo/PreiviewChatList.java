package com.chat.model.vo;

import java.sql.Timestamp;
import java.util.Date;

public class PreiviewChatList implements Comparable<PreiviewChatList>{
	private int myNo;
	private int otherNo;
	private int chatroomNo;
	private int messageUserNo;
	private String userName;
	private String userProfilePhoto;
	private String chatPreview;
	private Timestamp messageTime;
	private int readState;
	
	public PreiviewChatList() {
		
	}

	public PreiviewChatList(int myNo, int otherNo, int chatroomNo, int messageUserNo, String userName,
			String userProfilePhoto, String chatPreview, Timestamp messageTime, int readState) {
		super();
		this.myNo = myNo;
		this.otherNo = otherNo;
		this.chatroomNo = chatroomNo;
		this.messageUserNo = messageUserNo;
		this.userName = userName;
		this.userProfilePhoto = userProfilePhoto;
		this.chatPreview = chatPreview;
		this.messageTime = messageTime;
		this.readState=readState;
	}

	public int getMyNo() {
		return myNo;
	}

	public void setMyNo(int myNo) {
		this.myNo = myNo;
	}

	public int getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(int otherNo) {
		this.otherNo = otherNo;
	}

	public int getChatroomNo() {
		return chatroomNo;
	}

	public void setChatroomNo(int chatroomNo) {
		this.chatroomNo = chatroomNo;
	}

	public int getMessageUserNo() {
		return messageUserNo;
	}

	public void setMessageUserNo(int messageUserNo) {
		this.messageUserNo = messageUserNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}

	public void setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
	}

	public String getChatPreview() {
		return chatPreview;
	}

	public void setChatPreview(String chatPreview) {
		this.chatPreview = chatPreview;
	}

	public Timestamp getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Timestamp messageTime) {
		this.messageTime = messageTime;
	}
	
	public int getReadState() {
		return readState;
	}

	public void setReadState(int readState) {
		this.readState = readState;
	}

	@Override
	  public int compareTo(PreiviewChatList o) {
	    return o.getMessageTime().compareTo(getMessageTime());
	  }

	
	
}
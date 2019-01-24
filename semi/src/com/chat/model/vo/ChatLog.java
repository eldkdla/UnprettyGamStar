package com.chat.model.vo;

import java.sql.Timestamp;
import java.util.Date;

public class ChatLog {
	private int chatNo;
	private int userNo;
	private String chatroomMessage;
	private int myNo;
	private Timestamp send_date;
	
	public ChatLog() {
		
	}

	public ChatLog(int chatNo, int userNo, String chatroomMessage, int myNo, Timestamp send_date) {
		super();
		this.chatNo = chatNo;
		this.userNo = userNo;
		this.chatroomMessage = chatroomMessage;
		this.myNo = myNo;
		this.send_date = send_date;
	}

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getChatroomMessage() {
		return chatroomMessage;
	}

	public void setChatroomMessage(String chatroomMessage) {
		this.chatroomMessage = chatroomMessage;
	}

	public int getMyNo() {
		return myNo;
	}

	public void setMyNo(int myNo) {
		this.myNo = myNo;
	}

	public Timestamp getSend_date() {
		return send_date;
	}

	public void setSend_date(Timestamp send_date) {
		this.send_date = send_date;
	}

	@Override
	public String toString() {
		return "ChatLog [chatNo=" + chatNo + ", userNo=" + userNo + ", chatroomMessage=" + chatroomMessage + ", myNo="
				+ myNo + ", send_date=" + send_date + "]";
	}

	
	
}

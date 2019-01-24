package com.gamstar.user.model.vo;

import java.util.Date;

public class User {
	public static final int STATE_NORMAL = 0;
	public static final int STATE_BANNED = 1;
	public static final int STATE_OUT = 100;

	private int no;
	private String id;
	private String pw;


	public static int getStateNormal() {
		return STATE_NORMAL;
	}

	public static int getStateBanned() {
		return STATE_BANNED;
	}

	public static int getStateOut() {
		return STATE_OUT;
	}

	private String name;
	private int linkType;
	private String gender;
	private int state;
	private String profilePhoto;
	private String profileBackgroundPhoto;
	private String email;
	private String phone;
	private Date enrollDate;
	private int remainingDay;
	private int disclosure;
	private int iswatch;
	
	public int getIswatch() {
		return iswatch;
	}

	public void setIswatch(int iswatch) {
		this.iswatch = iswatch;
	}
	public int getDisclosure() {
		return disclosure;
	}

	public void setDisclosure(int disclosure) {
		this.disclosure = disclosure;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLinkType() {
		return linkType;
	}

	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getProfileBackgroundPhoto() {
		return profileBackgroundPhoto;
	}

	public void setProfileBackgroundPhoto(String profileBackgroundPhoto) {
		this.profileBackgroundPhoto = profileBackgroundPhoto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public int getRemainingDay() {
		return remainingDay;
	}

	public void setRemainingDay(int remainingDay) {
		this.remainingDay = remainingDay;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}

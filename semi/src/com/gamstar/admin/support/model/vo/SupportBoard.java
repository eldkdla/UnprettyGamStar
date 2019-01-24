package com.gamstar.admin.support.model.vo;

import java.sql.Date;

public class SupportBoard {
	
	private int supportBoardNo;
	private String supportBoardTitle;
	private int supportBoardWriterNo;
	private String supportBoardWriterName;
	private String supportBoardWriterId;
	private String supportBoardContent;
	private Date supportBoardDate;
	private int supportBoardRootNo;
	
	public SupportBoard() {}

	public SupportBoard(int supportBoardNo, String supportBoardTitle, int supportBoardWriterNo,
			String supportBoardWriterName, String supportBoardWriterId, String supportBoardContent,
			Date supportBoardDate, int supportBoardRootNo) {
		super();
		this.supportBoardNo = supportBoardNo;
		this.supportBoardTitle = supportBoardTitle;
		this.supportBoardWriterNo = supportBoardWriterNo;
		this.supportBoardWriterName = supportBoardWriterName;
		this.supportBoardWriterId = supportBoardWriterId;
		this.supportBoardContent = supportBoardContent;
		this.supportBoardDate = supportBoardDate;
		this.supportBoardRootNo = supportBoardRootNo;
	}

	public int getSupportBoardNo() {
		return supportBoardNo;
	}

	public void setSupportBoardNo(int supportBoardNo) {
		this.supportBoardNo = supportBoardNo;
	}

	public String getSupportBoardTitle() {
		return supportBoardTitle;
	}

	public void setSupportBoardTitle(String supportBoardTitle) {
		this.supportBoardTitle = supportBoardTitle;
	}

	public int getSupportBoardWriterNo() {
		return supportBoardWriterNo;
	}

	public void setSupportBoardWriterNo(int supportBoardWriterNo) {
		this.supportBoardWriterNo = supportBoardWriterNo;
	}

	public String getSupportBoardWriterName() {
		return supportBoardWriterName;
	}

	public void setSupportBoardWriterName(String supportBoardWriterName) {
		this.supportBoardWriterName = supportBoardWriterName;
	}

	public String getSupportBoardWriterId() {
		return supportBoardWriterId;
	}

	public void setSupportBoardWriterId(String supportBoardWriterId) {
		this.supportBoardWriterId = supportBoardWriterId;
	}

	public String getSupportBoardContent() {
		return supportBoardContent;
	}

	public void setSupportBoardContent(String supportBoardContent) {
		this.supportBoardContent = supportBoardContent;
	}

	public Date getSupportBoardDate() {
		return supportBoardDate;
	}

	public void setSupportBoardDate(Date supportBoardDate) {
		this.supportBoardDate = supportBoardDate;
	}

	public int getSupportBoardRootNo() {
		return supportBoardRootNo;
	}

	public void setSupportBoardRootNo(int supportBoardRootNo) {
		this.supportBoardRootNo = supportBoardRootNo;
	}

	@Override
	public String toString() {
		return "SupportBoard [supportBoardNo=" + supportBoardNo + ", supportBoardTitle=" + supportBoardTitle
				+ ", supportBoardWriterNo=" + supportBoardWriterNo + ", supportBoardWriterName="
				+ supportBoardWriterName + ", supportBoardWriterId=" + supportBoardWriterId + ", supportBoardContent="
				+ supportBoardContent + ", supportBoardDate=" + supportBoardDate + ", supportBoardRootNo="
				+ supportBoardRootNo + "]";
	}
}

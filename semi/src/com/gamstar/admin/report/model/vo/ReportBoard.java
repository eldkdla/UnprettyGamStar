package com.gamstar.admin.report.model.vo;

import java.sql.Date;


public class ReportBoard {
	public static int TYPE_USER = 0;
	public static int TYPE_NEWSPEED = 1;
	public static int TYPE_COMMENT = 2;
	public static int TYPE_CHATTING = 3;
	
	private int reportBoardNo;
	private int reportBoardType;
	private int reportBoardLink;
	private int reportBoardWriterNo;
	private int reportBoardTargetNo;
	private String reportBoardTargetId;
	private String reportBoardContent;
	private Date reportBoardDate;
	private int reportEndResult;
	
	public ReportBoard() {}

	public ReportBoard(int reportBoardNo, int reportBoardType, int reportBoardLink, int reportBoardWriterNo,
			int reportBoardTargetNo, String reportBoardTargetId, String reportBoardContent, Date reportBoardDate,
			int reportEndResult) {
		super();
		this.reportBoardNo = reportBoardNo;
		this.reportBoardType = reportBoardType;
		this.reportBoardLink = reportBoardLink;
		this.reportBoardWriterNo = reportBoardWriterNo;
		this.reportBoardTargetNo = reportBoardTargetNo;
		this.reportBoardTargetId = reportBoardTargetId;
		this.reportBoardContent = reportBoardContent;
		this.reportBoardDate = reportBoardDate;
		this.reportEndResult = reportEndResult;
	}

	public int getReportBoardNo() {
		return reportBoardNo;
	}

	public void setReportBoardNo(int reportBoardNo) {
		this.reportBoardNo = reportBoardNo;
	}

	public int getReportBoardType() {
		return reportBoardType;
	}

	public void setReportBoardType(int reportBoardType) {
		this.reportBoardType = reportBoardType;
	}

	public int getReportBoardLink() {
		return reportBoardLink;
	}

	public void setReportBoardLink(int reportBoardLink) {
		this.reportBoardLink = reportBoardLink;
	}

	public int getReportBoardWriterNo() {
		return reportBoardWriterNo;
	}

	public void setReportBoardWriterNo(int reportBoardWriterNo) {
		this.reportBoardWriterNo = reportBoardWriterNo;
	}

	public int getReportBoardTargetNo() {
		return reportBoardTargetNo;
	}

	public void setReportBoardTargetNo(int reportBoardTargetNo) {
		this.reportBoardTargetNo = reportBoardTargetNo;
	}

	public String getReportBoardTargetId() {
		return reportBoardTargetId;
	}

	public void setReportBoardTargetId(String reportBoardTargetId) {
		this.reportBoardTargetId = reportBoardTargetId;
	}

	public String getReportBoardContent() {
		return reportBoardContent;
	}

	public void setReportBoardContent(String reportBoardContent) {
		this.reportBoardContent = reportBoardContent;
	}

	public Date getReportBoardDate() {
		return reportBoardDate;
	}

	public void setReportBoardDate(Date reportBoardDate) {
		this.reportBoardDate = reportBoardDate;
	}

	public int getReportEndResult() {
		return reportEndResult;
	}

	public void setReportEndResult(int reportEndResult) {
		this.reportEndResult = reportEndResult;
	}

	@Override
	public String toString() {
		return "ReportBoard [reportBoardNo=" + reportBoardNo + ", reportBoardType=" + reportBoardType
				+ ", reportBoardLink=" + reportBoardLink + ", reportBoardWriterNo=" + reportBoardWriterNo
				+ ", reportBoardTargetNo=" + reportBoardTargetNo + ", reportBoardTargetId=" + reportBoardTargetId
				+ ", reportBoardContent=" + reportBoardContent + ", reportBoardDate=" + reportBoardDate
				+ ", reportEndResult=" + reportEndResult + "]";
	}
}

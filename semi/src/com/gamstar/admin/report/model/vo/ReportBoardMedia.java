package com.gamstar.admin.report.model.vo;

public class ReportBoardMedia {
	
	private int ReportBoardNo;
	private int ReportBoardMediaIndex;
	private String ReportBoardMediaPathOri;
	private String ReportBoardMediaPathRe;
	
	public ReportBoardMedia() {}
	
	public ReportBoardMedia(int reportBoardNo, int reportBoardMediaIndex, String reportBoardMediaPathOri,
			String reportBoardMediaPathRe) {
		super();
		ReportBoardNo = reportBoardNo;
		ReportBoardMediaIndex = reportBoardMediaIndex;
		ReportBoardMediaPathOri = reportBoardMediaPathOri;
		ReportBoardMediaPathRe = reportBoardMediaPathRe;
	}

	public int getReportBoardNo() {
		return ReportBoardNo;
	}

	public void setReportBoardNo(int reportBoardNo) {
		ReportBoardNo = reportBoardNo;
	}

	public int getReportBoardMediaIndex() {
		return ReportBoardMediaIndex;
	}

	public void setReportBoardMediaIndex(int reportBoardMediaIndex) {
		ReportBoardMediaIndex = reportBoardMediaIndex;
	}

	public String getReportBoardMediaPathOri() {
		return ReportBoardMediaPathOri;
	}

	public void setReportBoardMediaPathOri(String reportBoardMediaPathOri) {
		ReportBoardMediaPathOri = reportBoardMediaPathOri;
	}

	public String getReportBoardMediaPathRe() {
		return ReportBoardMediaPathRe;
	}

	public void setReportBoardMediaPathRe(String reportBoardMediaPathRe) {
		ReportBoardMediaPathRe = reportBoardMediaPathRe;
	}

	@Override
	public String toString() {
		return "ReportBoardMedia [ReportBoardNo=" + ReportBoardNo + ", ReportBoardMediaIndex=" + ReportBoardMediaIndex
				+ ", ReportBoardMediaPathOri=" + ReportBoardMediaPathOri + ", ReportBoardMediaPathRe="
				+ ReportBoardMediaPathRe + "]";
	}
}

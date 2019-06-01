package com.gamstar.admin.support.model.vo;

public class SupportBoardMedia {
	
	private int SupportBoardMediaNo;
	private int SupportBoardMediaType;
	private int SupportBoardMediaIndex;
	private String SupportBoardMediaPathOri;
	private String SupportBoardMediaPathRe;
	
	public SupportBoardMedia() {}

	public SupportBoardMedia(int supportBoardMediaNo, int supportBoardMediaType, int supportBoardMediaIndex,
			String supportBoardMediaPathOri, String supportBoardMediaPathRe) {
		super();
		SupportBoardMediaNo = supportBoardMediaNo;
		SupportBoardMediaType = supportBoardMediaType;
		SupportBoardMediaIndex = supportBoardMediaIndex;
		SupportBoardMediaPathOri = supportBoardMediaPathOri;
		SupportBoardMediaPathRe = supportBoardMediaPathRe;
	}

	public int getSupportBoardMediaNo() {
		return SupportBoardMediaNo;
	}

	public void setSupportBoardMediaNo(int supportBoardMediaNo) {
		SupportBoardMediaNo = supportBoardMediaNo;
	}

	public int getSupportBoardMediaType() {
		return SupportBoardMediaType;
	}

	public void setSupportBoardMediaType(int supportBoardMediaType) {
		SupportBoardMediaType = supportBoardMediaType;
	}

	public int getSupportBoardMediaIndex() {
		return SupportBoardMediaIndex;
	}

	public void setSupportBoardMediaIndex(int supportBoardMediaIndex) {
		SupportBoardMediaIndex = supportBoardMediaIndex;
	}

	public String getSupportBoardMediaPathOri() {
		return SupportBoardMediaPathOri;
	}

	public void setSupportBoardMediaPathOri(String supportBoardMediaPathOri) {
		SupportBoardMediaPathOri = supportBoardMediaPathOri;
	}

	public String getSupportBoardMediaPathRe() {
		return SupportBoardMediaPathRe;
	}

	public void setSupportBoardMediaPathRe(String supportBoardMediaPathRe) {
		SupportBoardMediaPathRe = supportBoardMediaPathRe;
	}

	@Override
	public String toString() {
		return "SupportBoardMedia [SupportBoardMediaNo=" + SupportBoardMediaNo + ", SupportBoardMediaType="
				+ SupportBoardMediaType + ", SupportBoardMediaIndex=" + SupportBoardMediaIndex
				+ ", SupportBoardMediaPathOri=" + SupportBoardMediaPathOri + ", SupportBoardMediaPathRe="
				+ SupportBoardMediaPathRe + "]";
	}
}


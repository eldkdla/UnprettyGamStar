package com.gamstar.user.support.model.vo;

import java.util.Date;

public class AnswerSupportData {
	private String qContent;
	private String aContent;
	private Date aDate;
	private Date qDate;
	private String title;
	
	public AnswerSupportData() {
		
	}

	public AnswerSupportData(String qContent, String aContent, Date aDate, Date qDate, String title) {
		super();
		this.qContent = qContent;
		this.aContent = aContent;
		this.aDate = aDate;
		this.qDate = qDate;
		this.title = title;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public String getaContent() {
		return aContent;
	}

	public void setaContent(String aContent) {
		this.aContent = aContent;
	}

	public Date getaDate() {
		return aDate;
	}

	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}

	public Date getqDate() {
		return qDate;
	}

	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "AnswerSupportData [qContent=" + qContent + ", aContent=" + aContent + ", aDate=" + aDate + ", qDate="
				+ qDate + ", title=" + title + "]";
	}

	





	
}

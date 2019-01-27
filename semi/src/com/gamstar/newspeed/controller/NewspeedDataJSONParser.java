package com.gamstar.newspeed.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gamstar.newspeed.model.vo.NewspeedComment;

import common.HtmlSpecialChar;

public class NewspeedDataJSONParser {
	
	
	public JSONArray getNewspeedCommentListJSONArray(List<NewspeedComment> commentList, int userNo) {
		JSONArray commentListJSONArray = new JSONArray();
		JSONArray recommentJSONArray = new JSONArray();
		JSONObject preJSONObject = new JSONObject();
		int preRootNo = -1;

		for (int i = 0; i < commentList.size(); i++) {		
			JSONObject json = new JSONObject();
			
			NewspeedComment newspeedComment = commentList.get(i);
			System.out.println(i + "번째" + newspeedComment + "잘나오냠?");
			
			json.put("userNo", newspeedComment.getUserNo());
			json.put("userName", newspeedComment.getUserName());
			json.put("commentContent", HtmlSpecialChar.getHtmlStr(newspeedComment.getContent()));
			json.put("commentNo", newspeedComment.getNo());
			json.put("rootCommentNo", newspeedComment.getRootNo());
			
			String before = parseToDate(newspeedComment.getBeforeDay()).replaceAll(".0", "");
			System.out.println(before + "언제 달았니?");
			
			json.put("beforeTime", before);
				
			if (userNo == newspeedComment.getUserNo()) {
				json.put("isMine", true);
			} else {
				json.put("isMine", false);
			}
				
			if (newspeedComment.getNo() == newspeedComment.getRootNo()) {
				recommentJSONArray = new JSONArray();
	
				json.put("recommentList", recommentJSONArray);			
				commentListJSONArray.add(json);
				preJSONObject = json;
				
			} else if (newspeedComment.getNo() != newspeedComment.getRootNo()) {
				
				recommentJSONArray.add(json);

				continue;
				
			}

			preRootNo = newspeedComment.getRootNo();

		}
		

		return commentListJSONArray;
	}
	
	public String parseToDate(String before) {
		double beforeNum = Double.parseDouble(before);
		String result = "";
		
		if (Math.floor((beforeNum/0.00069)) < 1) {
			return "방금";
		} else if (Math.floor((beforeNum/0.00069)) >= 1 && Math.floor((beforeNum/0.0414)) < 1) {
			return Math.floor((beforeNum/0.00069)) + "분전";
		} else if (Math.floor((beforeNum/0.0414)) >= 1 && Math.floor((beforeNum/0.0414)) < 24) {
			return Math.floor((beforeNum/0.0414)) + "시간전";
		} else if (beforeNum < 31 && Math.floor((beforeNum/0.0414)) >= 24) {
			return Math.floor(beforeNum) + "일전";
		} else if (beforeNum < 366 && beforeNum >= 31) {
			return Math.floor(beforeNum/30) + "개월전";
		}
		
		
		return Math.floor(beforeNum/365) + "년전";
			
	}

}

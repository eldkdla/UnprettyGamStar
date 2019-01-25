package com.gamstar.newspeed.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gamstar.newspeed.model.vo.NewspeedComment;

public class NewspeedDataJSONParser {
	
	
	public JSONArray getNewspeedCommentListJSONArray(List<NewspeedComment> newspeedCommentList, int userNo) {
		JSONArray commentListJSONArray = new JSONArray();
		
		
		for (int i = 0; i < newspeedCommentList.size(); i++) {
			JSONObject commentJSON = new JSONObject();
			NewspeedComment newspeedComment = newspeedCommentList.get(i);
			commentJSON.put("userNo", newspeedComment.getUserNo());
			commentJSON.put("commentContent", newspeedComment.getContent());
			commentJSON.put("userName", newspeedComment.getUserName());
			commentJSON.put("rootCommentNo", newspeedComment.getRootNo());
			commentJSON.put("commentNo", newspeedComment.getNo());
			
			if (userNo == newspeedComment.getUserNo()) {
				commentJSON.put("isMine", true);
			} else {
				commentJSON.put("isMine", false);
			}
			
			commentListJSONArray.add(commentJSON);
		}
		
		return commentListJSONArray;
	}

}

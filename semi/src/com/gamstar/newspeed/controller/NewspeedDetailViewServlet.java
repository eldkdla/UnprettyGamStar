package com.gamstar.newspeed.controller;

import static common.JDBCTemplate.getConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gamstar.newspeed.model.service.NewspeedService;
import com.gamstar.newspeed.model.vo.Newspeed;
import com.gamstar.newspeed.model.vo.NewspeedComment;
import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.newspeed.model.vo.NewspeedMediaTag;
import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;

import common.HtmlSpecialChar;

/**
 * Servlet implementation class NewspeedDetailViewServlet
 */
@WebServlet("/newspeed/newspeedview")
public class NewspeedDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewspeedDataJSONParser parser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewspeedDetailViewServlet() {
		super();
		parser = new NewspeedDataJSONParser();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if (isError(request)) {
			response.sendRedirect(request.getContextPath());
		}
		
		System.out.println(request.getServletContext().getRealPath("/") +"절대");

		int newspeedNo = Integer.parseInt(request.getParameter("newspeedNo"));
		int userNo = (int)request.getSession().getAttribute("userNo");
		NewspeedService nService = new NewspeedService();
		UserService uService = new UserService();
		
		Newspeed newspeed = nService.selectNewspeed(newspeedNo);
		
		if (newspeed == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		List<NewspeedComment> newspeedCommentList = nService.selectNewspeedCommentList(newspeedNo, userNo);
		List<NewspeedMedia>newspeedMediaList = nService.selectNewspeedMediaList(newspeedNo);
		List<NewspeedMediaTag>newspeedMediaTagList = nService.selectNespeedMediaTagList(newspeedNo);
		boolean isLike = nService.isLiked(userNo, newspeedNo);
		boolean isStore = nService.isStored(userNo, newspeedNo);
		User writer = new User();
		writer.setNo(newspeed.getUserNo());
		writer = uService.selectUser(writer);
		JSONObject newspeedJSON = parseNewspeedToJSON(newspeed,writer,newspeedMediaList, newspeedMediaTagList, newspeedCommentList, userNo);
		newspeedJSON.put("isLike", isLike);
		newspeedJSON.put("isStore", isStore);
		
		
		System.out.println(newspeedJSON.toJSONString());
		
		
		response.getWriter().println(newspeedJSON.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean isError(HttpServletRequest request) {
		return request.getParameter("newspeedNo") == null || request.getSession().getAttribute("userNo") == null;
	}

	private JSONObject parseNewspeedToJSON(Newspeed newspeed, User writer, List<NewspeedMedia> newspeedMediaList,
			List<NewspeedMediaTag> newspeedMediaTagList, List<NewspeedComment> commentList, int userNo) {
		JSONObject json = new JSONObject();
		JSONArray commentListJSONArray = null;
		JSONObject fileListJSON = null;
		
		json = getFileListJSON(newspeed, newspeedMediaList, newspeedMediaTagList);
		inputNewspeedWriterInJSON(writer, json);
		commentListJSONArray = getCommentListJSONArray(commentList, userNo);
		
		
		json.put("commentList", commentListJSONArray);
		
		return json;
	}

	private void inputNewspeedWriterInJSON(User writer, JSONObject newspeedJSON) {
		newspeedJSON.put("userNo", writer.getNo());
		newspeedJSON.put("userName", writer.getName());
		newspeedJSON.put("profilephoto", writer.getProfilePhoto());

		System.out.println(writer.getProfilePhoto() + "프사인데 있음?");
	}

	private JSONArray getCommentListJSONArray(List<NewspeedComment> commentList, int userNo) {
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
			
			String before = parser.parseToDate(newspeedComment.getBeforeDay());
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
	

	private JSONObject getFileListJSON(Newspeed newspeed, List<NewspeedMedia> mediaList,
			List<NewspeedMediaTag> mediaTagList) {
		JSONObject newspeedJSONObject = new JSONObject();
		JSONArray mediaListJSONArray = new JSONArray();

		newspeedJSONObject.put("content", HtmlSpecialChar.getHtmlStr(newspeed.getContent()));
		newspeedJSONObject.put("userNo", newspeed.getUserNo());
		newspeedJSONObject.put("newspeedNo", newspeed.getNo());
		
		String before = parser.parseToDate(newspeed.getBeforeDay()).replaceAll(".0", "");
		System.out.println(before + "언제 달았니?");
		
		newspeedJSONObject.put("beforeTime", before);

		for (int i = 0; i < mediaList.size(); i++) {
			mediaListJSONArray.add(getMediaInTagList(mediaList.get(i), mediaTagList));
		}

		newspeedJSONObject.put("fileList", mediaListJSONArray);
		
		System.out.println("야야양ㅁ"+newspeedJSONObject);

		return newspeedJSONObject;
	}

	private JSONObject getMediaInTagList(NewspeedMedia newspeedMedia, List<NewspeedMediaTag> tagList) {
		JSONObject json = new JSONObject();
		JSONArray tagListJSONArray = new JSONArray();
		int index = newspeedMedia.getIndex();

		for (int i = 0; i < tagList.size(); i++) {
			NewspeedMediaTag tag = tagList.get(i);
			JSONObject tagJSON = new JSONObject();
			if (index == tag.getMediaIndex()) {
				tagJSON.put("mediaIndex", index);
				tagJSON.put("newspeedNo", tag.getNewspeedNo());
				tagJSON.put("X", tag.getX());
				tagJSON.put("Y", tag.getY());
				tagJSON.put("userNo", tag.getUserNo());
				tagJSON.put("userName", tag.getUserName());
				tagListJSONArray.add(tagJSON);
			}
		}
		// "fileList":[{"mediaIndex":1,"fileName":"btn_add_media.png","tagList":[{"tagIndex":0,"mediaIndex":1,"x":"0.03350970017636689","y":"0.4192790451074912","userNo":"6",
		// "userName":"반가워"}]},
		json.put("mediaIndex", index);
		json.put("fileName", newspeedMedia.getPath());
		json.put("mediaType", newspeedMedia.getType());
		json.put("tagList", tagListJSONArray);

		return json;
	}

}

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

/**
 * Servlet implementation class NewspeedDetailViewServlet
 */
@WebServlet("/newspeed/newspeedview")
public class NewspeedDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewspeedDetailViewServlet() {
		super();
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

		}

		int newspeedNo = Integer.parseInt(request.getParameter("newspeedNo"));
		NewspeedService nService = new NewspeedService();
		UserService uService = new UserService();
		
		Newspeed newspeed = nService.selectNewspeed(newspeedNo);
		
		if (newspeed == null) {
			System.out.println("게시글을 불러올 수 없습니다.");
			return;
		}
		
		List<NewspeedComment> newspeedCommentList = nService.selectNewspeedCommentList(newspeedNo);
		List<NewspeedMedia>newspeedMediaList = nService.selectNewspeedMediaList(newspeedNo);
		List<NewspeedMediaTag>newspeedMediaTagList = nService.selectNespeedMediaTagList(newspeedNo);
		User writer = new User();
		writer.setNo(newspeed.getUserNo());
		writer = uService.selectUser(writer);
		JSONObject newspeedJSON = parseNewspeedToJSON(newspeed,writer,newspeedMediaList, newspeedMediaTagList, newspeedCommentList);
		
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
		if (request.getParameter("newspeedNo") == null || request.getSession().getAttribute("userNo") == null) {
			System.out.println("로그인이 안되어있거나 게시글번호가 없거나");
			return true;
		}

		return false;
	}

	private JSONObject parseNewspeedToJSON(Newspeed newspeed, User writer, List<NewspeedMedia> newspeedMediaList,
			List<NewspeedMediaTag> newspeedMediaTagList, List<NewspeedComment> commentList) {
		JSONObject json = new JSONObject();
		JSONArray commentListJSONArray = null;
		JSONObject fileListJSON = null;

		inputNewspeedWriterInJSON(writer, json);
		commentListJSONArray = getCommentListJSONArray(commentList);
		fileListJSON = getFileListJSON(newspeed, newspeedMediaList, newspeedMediaTagList);
		
		json.put("commentList", commentListJSONArray);
		json.put("fileList", fileListJSON);
		
		return json;
	}

	private void inputNewspeedWriterInJSON(User writer, JSONObject newspeedJSON) {
		newspeedJSON.put("userNo", writer.getNo());
		newspeedJSON.put("userName", writer.getName());
		newspeedJSON.put("profilephoto", writer.getProfilePhoto());

		System.out.println(writer.getProfilePhoto() + "프사인데 있음?");
	}

	private JSONArray getCommentListJSONArray(List<NewspeedComment> commentList) {
		JSONArray commentListJSONArray = new JSONArray();

		for (int i = 0; i < commentList.size(); i++) {
			JSONObject json = new JSONObject();
			NewspeedComment newspeedComment = commentList.get(i);
			json.put("userNo", newspeedComment.getUserNo());
			json.put("userName", newspeedComment.getUserName());
			json.put("commentContent", newspeedComment.getContent());

			commentListJSONArray.add(json);
		}

		return commentListJSONArray;
	}

	private JSONObject getFileListJSON(Newspeed newspeed, List<NewspeedMedia> mediaList,
			List<NewspeedMediaTag> mediaTagList) {
		JSONObject newspeedJSONObject = new JSONObject();
		JSONArray mediaListJSONArray = new JSONArray();

		newspeedJSONObject.put("content", newspeed.getContent());
		newspeedJSONObject.put("userNo", newspeed.getUserNo());
		newspeedJSONObject.put("newspeedNo", newspeed.getNo());

		for (int i = 0; i < mediaList.size(); i++) {
			mediaListJSONArray.add(getMediaInTagList(mediaList.get(i), mediaTagList));
		}

		newspeedJSONObject.put("fileList", mediaListJSONArray);

		return newspeedJSONObject;
	}

	private JSONObject getMediaInTagList(NewspeedMedia newspeedMedia, List<NewspeedMediaTag> tagList) {
		JSONObject json = new JSONObject();
		JSONArray tagListJSONArray = new JSONArray();
		int index = newspeedMedia.getIndex();

		for (int i = 0; i < tagList.size(); i++) {
			NewspeedMediaTag tag = tagList.get(i);
			if (index == tag.getNewspeedNo()) {
				tagListJSONArray.add(tag);
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

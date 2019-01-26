package com.gamstar.newspeed.controller;

import java.io.IOException;

import java.util.ArrayList;
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
import com.gamstar.newspeed.model.vo.NewspeedLike;
import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.newspeed.model.vo.NewspeedMediaTag;
import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class NewspeedServlet
 */
@WebServlet("/mainnewspeedservlet.do")
public class MainpageNewspeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainpageNewspeedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
//		String userNo = request.getParameter("userNo"); //나중에 세션으로 받아야함
//		if(userNo == null) {
//			response.sendRedirect("/경로"); //세션 못받아오면 로그인 페이지 강제 이동
//		}
		String userNo = "1";
		User user = new User();
		//user.setNo(Integer.parseInt(request.getParameter("userNo")));

		user.setNo(1);
		
		int limite = Integer.parseInt(request.getParameter("limite"));
		
		System.out.println("리밋!! : "+limite);
	

		List<String> peedNo = new NewspeedService().selectNewspeedNo(userNo, limite); //피드번호 불러오기
		
//		List<User> user = new NewspeedService().selectNewspeed(userNo);
		List<Newspeed> contentList = new NewspeedService().selectContent(userNo,limite); //콘텐트 불러오기
		List<NewspeedComment> commentList = new NewspeedService().selectComment(userNo,limite); //코멘트 불러오기
		List<NewspeedMedia> mediaList = new NewspeedService().selectMedia(userNo,limite); //미디어 불러오기
		List<NewspeedLike> likeList = new NewspeedService().selectLike(userNo,limite); //좋아요 불러오기
		List<NewspeedMediaTag> tagList = new NewspeedService().selectMediaTag(userNo,limite); //이미지 태그 불러오기
		List<User> userList = new UserService().selectFeedUser(peedNo); //유저 불러오기
		
		System.out.println("미디어 불러오기 : "+mediaList.size());
		System.out.println("좋아요 불러오기 : "+likeList.size());
		System.out.println("코멘트 불러오기 : "+commentList.size());
		
		System.out.println(userList.size());
		System.out.println(userList.get(0).getName());
		System.out.println(userList.get(0).getProfilePhoto());
		
		JSONArray jsonArr = new JSONArray();
		JSONObject content = new JSONObject();
		JSONObject comment = new JSONObject();
		JSONObject media = new JSONObject();
		JSONObject like = new JSONObject();
		JSONObject tag = new JSONObject();
		JSONObject feedUser = new JSONObject();
		
		for(int i=0;i<contentList.size();i++) {
			
			JSONObject data2 = new JSONObject();
			data2.put("newspeedNo", contentList.get(i).getNo());
			data2.put("userNo", contentList.get(i).getUserNo());
			data2.put("content", contentList.get(i).getContent());
			data2.put("enable", contentList.get(i).isEnable());
			data2.put("date", contentList.get(i).getDate());
			
			content.put("content"+i, data2);
		}

		for(int i=0;i<commentList.size();i++) {
			
			JSONObject data2 = new JSONObject();
			data2.put("newspeedNo", commentList.get(i).getNewspeedNo());
			data2.put("commentNo", commentList.get(i).getNo());
			data2.put("content", commentList.get(i).getContent());
			data2.put("userNo", commentList.get(i).getUserNo());
			data2.put("date", commentList.get(i).getDate());
			data2.put("enable", commentList.get(i).getEnable());
			data2.put("userName", commentList.get(i).getUserName());
			
			comment.put("comment"+i, data2);
		}

		for(int i=0;i<mediaList.size();i++) {
			
			JSONObject data2 = new JSONObject();
			data2.put("mediaIndex", mediaList.get(i).getIndex());
			data2.put("newspeedNo", mediaList.get(i).getNewspeedNo());
			data2.put("mediaType", mediaList.get(i).getType());
			data2.put("mediaPath", mediaList.get(i).getPath());

			media.put("media"+i, data2);
		}

		for(int i=0;i<likeList.size();i++) {
			
			JSONObject data2 = new JSONObject();
			data2.put("newspeedNo", likeList.get(i).getNo());
			data2.put("userNo", likeList.get(i).getUserNo());

			like.put("like"+i, data2);
		}
		
		for(int i=0;i<tagList.size();i++) {

			JSONObject data2 = new JSONObject();
			data2.put("mediaIndex", tagList.get(i).getMediaIndex());
			data2.put("newspeedNo", tagList.get(i).getNewspeedNo());
			data2.put("userNo", tagList.get(i).getUserNo());
			data2.put("X", tagList.get(i).getX());
			data2.put("Y", tagList.get(i).getY());
			data2.put("userName", tagList.get(i).getUserName());
			
			tag.put("tag"+i, data2);
		}
		
		for(int i=0; i<userList.size(); i++) {
			
			JSONObject data2 = new JSONObject();
			data2.put("userName", userList.get(i).getName());
			data2.put("profilePhoto", userList.get(i).getProfilePhoto());
			
			feedUser.put("feedUser"+i, data2);
		}
		
		System.out.println("배열이당 : "+content.size());
		System.out.println("배열이당 : "+comment.size());
		System.out.println("배열이당 : "+media.size());
		System.out.println("배열이당 : "+like.size());
		System.out.println("배열이당 : "+tag.size());
		System.out.println("배열이당 : "+feedUser.size());
		
		jsonArr.add(content);
		jsonArr.add(comment);
		jsonArr.add(media);
		jsonArr.add(like);
		jsonArr.add(tag);
		jsonArr.add(feedUser);
		
		System.out.println("제이슨 배열 이당 : "+jsonArr);
	
		
//		NewspeedSet data = new NewspeedSet();
//		List<NewspeedSet> peedSet = new ArrayList(); 
//		for(int i =0; i<contentList.size();i++) {
//			
//			data.setOne(contentList.get(i).getNo());
//			data.setTwo(contentList.get(i).getUserNo());
//			data.setThree(contentList.get(i).getContent());
//			data.setFour(contentList.get(i).isEnable());
//			data.setFive(contentList.get(i).getDate());
//			data.setSix(commentList.get(i).getNewspeedNo());
//			data.setSeven(commentList.get(i).getNo());
//			data.setEight(commentList.get(i).getRootNo());
//			data.setNine(commentList.get(i).getContent());
//			data.setTen(commentList.get(i).getUserNo());
//			data.setEleven(commentList.get(i).getDate());
//			data.setTwelve(commentList.get(i).getEnable());
//			data.setThirteen(likeList.get(i).getNo());
//			data.setFourteen(likeList.get(i).getUserNo());
//			data.setSixteen(mediaList.get(i).getIndex());
//			data.setSeventeen(mediaList.get(i).getNewspeedNo());
//			data.setEightteen(mediaList.get(i).getType());
//			data.setNineteen(mediaList.get(i).getPath());
//			data.setTwenty(tagList.get(i).getMediaIndex());
//			data.setTwentyOne(tagList.get(i).getNewspeedNo());
//			data.setTwentyTwo(tagList.get(i).getUserNo());
//			data.setTwentyThree(tagList.get(i).getX());
//			data.setTwentyFour(tagList.get(i).getY());
//			data.setTwentyFive(tagList.get(i).getUserName());
//			
//			peedSet.add(data);
//		}
		
//		System.out.println("콘텐 사이즈 : "+contentList.size());
////		System.out.println("콘텐 겟 : "+contentList.get(0));
//		System.out.println("           ------------              ");
////		System.out.println("뉴스피드 코멘트 : "+commentList);
//		System.out.println("콘텐 사이즈 : "+commentList.size());
////		System.out.println("콘텐 겟 : "+commentList.get(0));
//		System.out.println("           ------------              ");
//		System.out.println("뉴스피드 미디어 : "+mediaList);
//		System.out.println("콘텐 사이즈 : "+mediaList.size());
//		System.out.println("콘텐 겟 : "+mediaList.get(0));
//		System.out.println("           ------------              ");
//		System.out.println("콘텐 사이즈 : "+likeList.size());
////		System.out.println("콘텐 겟 : "+likeList.get(0));
//		System.out.println("           ------------              ");
//		System.out.println("콘텐 사이즈 : "+tagList.size());
////		System.out.println("콘텐 겟 : "+tagList.get(0));
//		System.out.println("           ------------              ");
//		System.out.println("서블릿 통신");
//		
//		request.setAttribute("content", contentList);
//		request.setAttribute("comment", commentList);
//		RequestDispatcher dispatcher request.getRequestDispatcher("")
		response.setContentType("application/json;Charset=UTF-8");
		new Gson().toJson(jsonArr,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

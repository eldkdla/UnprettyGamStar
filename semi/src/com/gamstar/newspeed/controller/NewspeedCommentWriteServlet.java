package com.gamstar.newspeed.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gamstar.newspeed.model.service.NewspeedService;
import com.gamstar.newspeed.model.vo.NewspeedComment;

/**
 * Servlet implementation class NewspeedCommentInsertServlet
 */
@WebServlet("/newspeed/newspeedcommentwrite")
public class NewspeedCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewspeedCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if (request.getSession().getAttribute("userNo") == null) {
			response.sendRedirect(request.getContextPath());
		}
		
		
		int newspeedNo = Integer.parseInt(request.getParameter("newspeedNo"));
		int userNo = (int)request.getSession().getAttribute("userNo");
		String content = request.getParameter("commentContent");
		
		NewspeedComment newspeedComment = new NewspeedComment();
		newspeedComment.setUserNo(userNo);
		newspeedComment.setContent(content);
		newspeedComment.setNewspeedNo(newspeedNo);
		
		NewspeedService nService = new NewspeedService();
		
		System.out.println("안녕?");
		System.out.println(content);
		System.out.println(newspeedComment);
		
		int result = nService.insertNewspeedComment(newspeedComment);
		List<NewspeedComment> newspeedCommentList = nService.selectNewspeedCommentList(newspeedNo, userNo);
		JSONArray commentListJSONArray = new NewspeedDataJSONParser().getNewspeedCommentListJSONArray(newspeedCommentList, userNo);
		
		response.getWriter().println(commentListJSONArray.toJSONString());
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	


}

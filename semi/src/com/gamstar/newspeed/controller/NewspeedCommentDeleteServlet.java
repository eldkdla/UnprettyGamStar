package com.gamstar.newspeed.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gamstar.newspeed.model.service.NewspeedService;

/**
 * Servlet implementation class NewspeedCommentDeleteServlet
 */
@WebServlet("/newspeed/newspeedcommentdelete")
public class NewspeedCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewspeedCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (request.getSession().getAttribute("userNo") == null) {
			response.sendRedirect(request.getContextPath());
		}
		
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int newspeedNo = Integer.parseInt(request.getParameter("newspeedNo"));
		int userNo = (int)(request.getSession().getAttribute("userNo"));
		
		NewspeedService nService = new NewspeedService();
		NewspeedDataJSONParser parser = new NewspeedDataJSONParser();
		int result = nService.deleteNewspeedComment(commentNo);
		
		if (result > 0) {
			JSONArray commentJSONObject = parser.getNewspeedCommentListJSONArray(nService.selectNewspeedCommentList(newspeedNo, userNo), userNo);
			response.getWriter().println(commentJSONObject.toJSONString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

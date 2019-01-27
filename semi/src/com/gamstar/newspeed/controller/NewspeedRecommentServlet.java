package com.gamstar.newspeed.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.newspeed.model.service.NewspeedService;
import com.gamstar.newspeed.model.vo.NewspeedComment;

/**
 * Servlet implementation class NewspeedRecommentServlet
 */
@WebServlet("/newspeed/newspeedrecomment")
public class NewspeedRecommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewspeedRecommentServlet() {
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
		int rootNo = Integer.parseInt(request.getParameter("rootCommentNo"));
		
		NewspeedComment newspeedComment = new NewspeedComment();
		newspeedComment.setUserNo(userNo);
		newspeedComment.setContent(content);
		newspeedComment.setNewspeedNo(newspeedNo);
		newspeedComment.setRootNo(rootNo);
		
		NewspeedService nService = new NewspeedService();
		
		System.out.println("안녕?");
		System.out.println(content);
		System.out.println(newspeedComment);
		
		nService.insertNewspeedRecomment(newspeedComment);
		NewspeedDataJSONParser parser = new NewspeedDataJSONParser();
		String data = parser.getNewspeedCommentListJSONArray(nService.selectNewspeedCommentList(newspeedNo, userNo), userNo).toJSONString();
		
		System.out.println("왜그랭?");
		System.out.println(data);
		
		response.getWriter().print(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

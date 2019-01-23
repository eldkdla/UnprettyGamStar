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
 * Servlet implementation class NewspeedCommentInsertServlet
 */
@WebServlet("/newspeed/newspeedcommentinsert")
public class NewspeedCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewspeedCommentInsertServlet() {
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
			return;
		}
		
		
		int newspeedNo = Integer.parseInt(request.getParameter("newspeedNo"));
		int userNo = (int)request.getSession().getAttribute("userNo");
		String content = request.getParameter("content");
		
		NewspeedComment newspeedComment = new NewspeedComment();
		newspeedComment.setUserNo(userNo);
		newspeedComment.setContent(content);
		newspeedComment.setNewspeedNo(newspeedNo);
		
		NewspeedService nService = new NewspeedService();
		
		int result = nService.insertNewspeedComment(newspeedComment);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

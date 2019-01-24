package com.gamstar.newspeed.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.newspeed.model.service.NewspeedService;

/**
 * Servlet implementation class NewspeedLikeServlet
 */
@WebServlet("/newspeed/newspeedlike")
public class NewspeedLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewspeedLikeServlet() {
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
		
		int userNo = (int)request.getSession().getAttribute("userNo");
		int newspeedNo = Integer.parseInt(request.getParameter("newspeedNo"));
		NewspeedService nService = new NewspeedService();
		int result = nService.insertLike(userNo, newspeedNo);
		String msg = getMsgFromResult(result);
		
		response.getWriter().println(msg);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String getMsgFromResult(int result) {
		switch(result) {
		case NewspeedService.NEWSPEED_LIKE_INSERT_OK :
			return "newspeed_like_active_icon_wrapper";
		case NewspeedService.NEWSPEED_LIKE_DELETE_OK :
			return "newspeed_like_icon_wrapper";
		}
		
		return "error";
	}

}

package com.gamstar.newspeed.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.newspeed.model.service.NewspeedService;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		if(isError(request)) {
			
		}
		
		int newspeedNo = Integer.parseInt(request.getParameter("newspeedNo"));
		
		NewspeedService nService = new NewspeedService();
		nService.selectNewspeedJSON(newspeedNo);
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public boolean isError(HttpServletRequest request) {
		if (request.getParameter("newspeedNo") == null || request.getSession().getAttribute("userNo") == null) {
			System.out.println("로그인이 안되어있거나 게시글번호가 없거나");
			return true;
		}
		
		return false;
	}

}

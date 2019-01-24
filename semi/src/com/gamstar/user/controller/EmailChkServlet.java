package com.gamstar.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailChkServlet
 */
@WebServlet("/emailchk")
public class EmailChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailChkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("userEmail");
		String authNum = "";
		
	/*	authNum = RandomNum();
		
		SendEmail(eMail, authNum);
		
		<script type="text/javascript" src="https://cdn.emailjs.com/sdk/2.3.2/email.min.js"></script>
		<script type="text/javascript">
		   (function(){
		      emailjs.init("user_S5X6G49xTFneHj6uLRJWP");
		   })();
		</script>*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

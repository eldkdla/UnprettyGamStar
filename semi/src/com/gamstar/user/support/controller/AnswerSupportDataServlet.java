package com.gamstar.user.support.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gamstar.user.support.model.vo.AnswerSupportData;
import com.gamstar.user.support.service.AnswerSupportDataService;

/**
 * Servlet implementation class AnswerSupportDataServlet
 */
@WebServlet("/answerSupportData")
public class AnswerSupportDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerSupportDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int supportNo=Integer.parseInt(request.getParameter("supportNo"));
		System.out.println(supportNo);
		AnswerSupportData as=new AnswerSupportData();
		as=new AnswerSupportDataService().callAnswerSupportData(supportNo);
		SimpleDateFormat datefm=new SimpleDateFormat("yyyy-MM-dd");
		
				
				JSONObject asObj=new JSONObject();
				asObj.put("question", as.getqContent());
				asObj.put("answer", as.getaContent());
				asObj.put("answerDate", datefm.format(as.getaDate()));
				asObj.put("questionDate", datefm.format(as.getqDate()));
				asObj.put("title", as.getTitle());

				
					System.out.println(asObj.get("question"));
					System.out.println(asObj.get("answer"));
					System.out.println(asObj.get("answerDate"));


			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(asObj);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

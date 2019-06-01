package com.gamstar.user.support.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gamstar.user.support.model.vo.ConfirmSupportData;
import com.gamstar.user.support.service.ConfirmSupportDataService;

/**
 * Servlet implementation class ConfirmSupportDataServlet
 */
@WebServlet("/confirmsupport")
public class ConfirmSupportDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmSupportDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int myNo=(int)request.getSession().getAttribute("userNo");
		ArrayList<ConfirmSupportData> csArr=new ArrayList<ConfirmSupportData>();
		csArr=new ConfirmSupportDataService().callConfirmSupportData(myNo);
		SimpleDateFormat datefm=new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<csArr.size();i++) {
			System.out.println(csArr.get(i));
			}
			JSONArray confirmArr=new JSONArray();
			
			for (int j = 0; j < csArr.size(); j++) {
				
				JSONObject csObj=new JSONObject();
				csObj.put("supportNo", csArr.get(j).getSupportNo());
				csObj.put("title", csArr.get(j).getSupportTitle());
				csObj.put("date", datefm.format(csArr.get(j).getSupportDate()));
				csObj.put("state", csArr.get(j).getSupportState());

				confirmArr.add(csObj);
			}
			
			for(int i=0;i<confirmArr.size();i++) {
				System.out.println(confirmArr.get(i));
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(confirmArr);
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

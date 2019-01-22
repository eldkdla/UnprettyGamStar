package com.gamstar.admin.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.report.model.service.ReportService;

/**
 * Servlet implementation class ReportUserBanServlet
 */
@WebServlet("/admin/report/selectUserBan")
public class ReportUserBanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportUserBanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//no<0 관리자 아니면 못들어옴
		if(request.getSession().getAttribute("userNo")==null||(Integer)request.getSession().getAttribute("userNo")>0)
		{
			request.setAttribute("msg", "잘못된 접근입니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
			return;
		}
		else
		{
			String reportedTargetNo=(String)request.getParameter("reportedId").trim();
			String reportedTargetId=(String)request.getParameter("reportedName").trim();
			String reportBoardNo=(String)request.getParameter("reportBoardNo").trim();
			
			String stopType=request.getParameter("stoptype");
			
			String[] targetNoStrs=reportedTargetNo.split(" ");
			String[] targetIdStrs=reportedTargetId.split(" ");
			String[] boardNoStrs=reportBoardNo.split(" ");
			
			int[] targetNos=new int[targetNoStrs.length];
			int[] boardNos=new int[boardNoStrs.length];
			
			
			for(int i=0; i<targetNos.length; i++)
			{
				targetNos[i]=Integer.parseInt(targetNoStrs[i]);
				boardNos[i]=Integer.parseInt(boardNoStrs[i]);
			}
			
			
			int stopdays=999999999;
			
			if(stopType.equals("temp"))
			{
				stopdays=Integer.parseInt(request.getParameter("stopdays"));			
			}
			
			
			int result=0;
			
			result=new ReportService().setSelectUserStop(targetNos,stopdays,boardNos);
			
			String view="/view/common/msg.jsp";
			String msg="";
			String loc="";
			
			if(result>0)
			{
				if(targetIdStrs.length<3)
				{
					String name="";
					for(String s : targetIdStrs)
					{
						name+=s+" ";
					}
					msg=name.trim()+" 회원을"+stopdays+"일 정지시켰습니다.";
				}
				else
				{
					msg=targetIdStrs.length+"명의 회원을"+stopdays+"일 정지시켰습니다.";
				}
				loc="/admin/reportList";
			}
			else
			{
				msg="처리에 실패하였습니다.";
				loc="/admin/reportList";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
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

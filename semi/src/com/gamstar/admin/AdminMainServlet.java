package com.gamstar.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.report.model.service.ReportService;
import com.gamstar.admin.report.model.vo.ReportBoard;
import com.gamstar.admin.support.model.service.SupportService;
import com.gamstar.admin.support.model.vo.SupportBoard;

/**
 * Servlet implementation class AdminMainServlet
 */
@WebServlet("/admin/goAdminMain")
public class AdminMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMainServlet() {
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
			int cPage=1;
			int numPerPage=5;
			
			List<ReportBoard> mainReportList = new ReportService().selectReportList(cPage,numPerPage);
			int uncheckedReport=new ReportService().selectUnckReportNum();
			
			List<SupportBoard> mainSupportList=new SupportService().selectSupportList(cPage, numPerPage);
			int uncheckedSupport=new SupportService().selectUnckSupportCount();
			
			request.setAttribute("reportList", mainReportList);
			request.setAttribute("reportUnck", uncheckedReport);
			request.setAttribute("supportList", mainSupportList);
			request.setAttribute("supportUnck", uncheckedSupport);
			
			request.getRequestDispatcher("/view/admin/adminMainPage.jsp").forward(request, response);
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

package com.gamstar.admin.report.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.report.model.service.ReportService;
import com.gamstar.admin.report.model.vo.ReportBoard;
import com.gamstar.admin.report.model.vo.ReportBoardMedia;

/**
 * Servlet implementation class ReportListServlet
 */
@WebServlet("/admin/reportList")
public class ReportListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportListServlet() {
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
		{//페이징 처리
			int cPage;
			try {
				cPage=Integer.parseInt(request.getParameter("cPage"));
			}
			catch(NumberFormatException e)
			{
				cPage=1;
			}
			
			int numPerPage;
			try {
				numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
			}
			catch(NumberFormatException e)
			{
				numPerPage=10;
			}
			
			List<ReportBoard> list=new ReportService().selectReportList(cPage,numPerPage);
			Map<Integer,List<ReportBoardMedia>> mediaMap=new HashMap<Integer,List<ReportBoardMedia>>();
			
			for(ReportBoard rb : list)
			{
					int reportNo=rb.getReportBoardNo();
					List<ReportBoardMedia> mediaList = new ReportService().selectReportMediaList(reportNo);
					mediaMap.put(reportNo,mediaList);
			}
			
			int totalReport=new ReportService().selectReportCount();
			int totalPage=(int)Math.ceil((double)totalReport/numPerPage);
			String pageBar="";
			
			int pageBarSize=5;
			int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
			int pageEnd=pageNo+pageBarSize-1;
			
			
			if(pageNo==1)
			{
				pageBar+="<button id='back' disabled='disabled' href='#'><</button>"; 
			}
			else
			{
				pageBar+="<button id='back' href='"+request.getContextPath()+"/admin/reportList?cPage="+(pageNo-1)
						+"&numPerPage="+numPerPage+"'><</button>";
			}
			
			while(!(pageNo>pageEnd||pageNo>totalPage))
			{
				if(cPage==pageNo)
				{
					pageBar+="<small><span class='cPage'>"+pageNo+"</span></small>";
				}
				else
				{
					pageBar+="<small><a href='"+request.getContextPath()+"/admin/reportList?cPage="+(pageNo)
							+"&numPerPage="+numPerPage+"'>"+pageNo+"</a></small>";
				}
				pageNo++;
			}
			
			if(pageNo>totalPage)
			{
				pageBar+="<button id='next' disabled='disabled href='"+request.getContextPath()+"/admin/reportList?cPage="+pageNo
						+"&numPerPage="+numPerPage+"'>></button>";
			}
			
			request.setAttribute("mediaList", mediaMap);
			request.setAttribute("list", list);
			request.setAttribute("cPage", cPage);
			request.setAttribute("numPerPage", numPerPage);
			request.setAttribute("pageBar", pageBar);
			request.getRequestDispatcher("/view/admin/report/reportListAdmin.jsp").forward(request, response);
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

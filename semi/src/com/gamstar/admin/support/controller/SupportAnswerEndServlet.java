package com.gamstar.admin.support.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.gamstar.admin.support.model.service.SupportService;
import com.gamstar.admin.support.model.vo.SupportBoard;
import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;

/**
 * Servlet implementation class SupportAnswerEditServlet
 */
@WebServlet("/admin/supportAnswerEnd")
public class SupportAnswerEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportAnswerEndServlet() {
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
		
			if(!ServletFileUpload.isMultipartContent(request))
			{
				request.setAttribute("msg", "답변 등록 실패");
				request.setAttribute("loc", "/admin/supportList");
				request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
				return;
			}
			
			//파일 기본경로
			String root=getServletContext().getRealPath("/");
			String saveDir=root+"upload/support/";
			
			int maxSize=1024*1024*10;
			
			
			MultipartRequest mr=new MultipartRequest(request, saveDir, maxSize, "UTF-8", new MyFileRenamePolicy());
			SupportBoard supportAnswer=new SupportBoard();
			supportAnswer.setSupportBoardNo(Integer.parseInt(mr.getParameter("oriSupportBoardNo"))*(-1));
			supportAnswer.setSupportBoardContent(mr.getParameter("supportContent"));
			supportAnswer.setSupportBoardRootNo(Integer.parseInt(mr.getParameter("oriSupportBoardNo")));
			System.out.println("SupportAnswerEditServlet supportAnswer: "+supportAnswer);
			int result=new SupportService().insertAnswer(supportAnswer);
			
			String view="";
			String msg="";
			String loc="";
			
			if(result>0)
			{
				view="/admin/supportView?no="+supportAnswer.getSupportBoardRootNo();
			}
			else
			{
				view="/view/common/msg.jsp";
				msg="답변 등록 실패";
				loc="/view/admin/supportView?no="+supportAnswer.getSupportBoardRootNo();
				request.setAttribute("msg", msg);
			}
			request.setAttribute("view", view);
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

package com.gamstar.admin.support.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.support.model.service.SupportService;
import com.gamstar.admin.support.model.vo.SupportBoard;
import com.gamstar.admin.support.model.vo.SupportBoardMedia;

/**
 * Servlet implementation class SupportViewServlet
 */
@WebServlet("/admin/supportView")
public class SupportViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportViewServlet() {
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
			
			int no=Integer.parseInt(request.getParameter("no"));

			//이전/다음글 번호
			int nextNo=0;
			int prevNo=0;
			
			if(no<0)
			{
				no=-1*no;
			}
			SupportBoard s=new SupportService().selectSupportOne(no);
			
			String view="";
			if(s!=null)
			{
				view="/view/admin/support/supportView.jsp";
				request.setAttribute("supportBoard", s);
				
				
				//이전/다음글 번호 구하기
				nextNo=new SupportService().selectSupportNextNo(s.getSupportBoardNo());
				prevNo=new SupportService().selectSupportPrevNo(s.getSupportBoardNo());
			
				
				
				List<SupportBoardMedia> sMedias=new SupportService().selectSupportOneMedia(s.getSupportBoardNo());
				if(!sMedias.isEmpty())
				{
					request.setAttribute("supportBoardMedia", sMedias);
				}
				
				if(s.getSupportBoardRootNo()!=0)
				{
					SupportBoard answer=new SupportService().selectSupportOne(s.getSupportBoardRootNo());
					if(answer!=null)
					{
						request.setAttribute("supportBoardAnswer", answer);
					}
				}
				
			}
			else
			{
				view="/view/common/msg.jsp";
				request.setAttribute("loc", "/admin/supportList");
				request.setAttribute("msg", "게시물이 없습니다");
			}
			
			int cPage=Integer.parseInt(request.getParameter("cPage"));
			request.setAttribute("cPage",cPage);
			request.setAttribute("nextNo", nextNo);
			request.setAttribute("prevNo", prevNo);
			System.out.println(view);
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

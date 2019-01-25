package com.gamstar.help.support.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.support.model.service.SupportService;
import com.gamstar.admin.support.model.vo.SupportBoard;

/**
 * Servlet implementation class GoUserSupportServlet
 */
@WebServlet("/help/support")
public class GoUserSupportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoUserSupportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
				//로그인 안하면 못씀
				if(request.getSession().getAttribute("userNo")==null)
				{
					request.setAttribute("msg", "로그인해주세요");
					request.setAttribute("loc", "/");
					request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
					return;
				}
				//관리자는 관리자 페이지로
				else if((Integer)request.getSession().getAttribute("userNo")<0)
				{
					request.setAttribute("msg", "관리자 페이지에서 접근해주세요");
					request.setAttribute("loc", "/admin/supportList");
					request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
					return;
				}
				else
				{
				
					//페이징처리
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
					
					List<SupportBoard> list=new SupportService().selectSupportList(cPage,numPerPage);
					
					int totalSupport=new SupportService().selectSupportCount();
					int totalPage=(int)Math.ceil((double)totalSupport/numPerPage);
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
						pageBar+="<button id='back' href='"+request.getContextPath()+"/admin/supportList?cPage="+(pageNo-1)
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
							pageBar+="<small><a href='"+request.getContextPath()+"/admin/supportList?cPage="+pageNo
									+"&numPerPage="+numPerPage+"'>"+pageNo+"</a></small>";
						}
						pageNo++;
					}
					
					if(pageNo>totalPage)
					{
						pageBar+="<button id='next' disabled='disabled' href='"+request.getContextPath()+"/admin/supportList?cPage="+pageNo
								+"&numPerPage="+numPerPage+"'>></button>";
					}
					
					request.setAttribute("list", list);
					request.setAttribute("cPage", cPage);
					request.setAttribute("numPerPage", numPerPage);
					request.setAttribute("pageBar", pageBar);
					request.getRequestDispatcher("/view/admin/support/supportListAdmin.jsp").forward(request, response);
				}
					request.getRequestDispatcher("/view/userHelpService.jsp").forward(request, response);;
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

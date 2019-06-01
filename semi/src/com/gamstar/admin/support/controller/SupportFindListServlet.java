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

/**
 * Servlet implementation class SupportFindListServlet
 */
@WebServlet("/admin/support/search")
public class SupportFindListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportFindListServlet() {
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
		
			String searchType=request.getParameter("type");
			String searchKeyword=request.getParameter("keyword");
			
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
					
					List<SupportBoard> list=null;
					int totalSupport=0;
					switch(searchType)
					{
						case "id" : list=new SupportService().searchIdList(cPage,numPerPage,searchKeyword);
												 totalSupport=new SupportService().selectSupportCountId(searchKeyword);break;
						case "title" : list=new SupportService().searchTitleList(cPage,numPerPage,searchKeyword);
						 						    totalSupport=new SupportService().selectSupportCountTitle(searchKeyword);break;
						case "content" : list=new SupportService().searchContentList(cPage,numPerPage,searchKeyword);
													  totalSupport=new SupportService().selectSupportCountContent(searchKeyword);break;
						case "sAll" : list=new SupportService().searchAllList(cPage,numPerPage,searchKeyword);
												  totalSupport=new SupportService().selectSupportCountAll(searchKeyword);break;
					}
		
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
						pageBar+="<button id='back' href='"+request.getContextPath()+"/admin/support/search?cPage="+(pageNo-1)
								+"&type="+searchType+"&keyword="+searchKeyword+"'><</button>";
					}
					if(totalPage==0)
					{
						pageBar+="<small><span class='cPage'><b>1</b></span></small>";
					}
					
					while(!(pageNo>pageEnd||pageNo>totalPage))
					{
						if(cPage==pageNo)
						{
							pageBar+="<small><span class='cPage'><b>"+pageNo+"</b></span></small>";
						}
						else
						{
							pageBar+="<small><a href='"+request.getContextPath()+"/admin/support/search?cPage="+pageNo
									+"&type="+searchType+"&keyword="+searchKeyword+"'>"+pageNo+"</a></small>";
						}
						pageNo++;
					}
					
					if(pageNo>totalPage)
					{
						pageBar+="<button id='next' disabled='disabled' href='"+request.getContextPath()+"/admin/supportFind?cPage="+pageNo
								+"'>></button>";
					}
					
					request.setAttribute("searchType", searchType);
					request.setAttribute("searchKeyword", searchKeyword);
					request.setAttribute("list", list);
					request.setAttribute("cPage", cPage);
					request.setAttribute("numPerPage", numPerPage);
					request.setAttribute("pageBar", pageBar);
					request.getRequestDispatcher("/view/admin/support/supportListFind.jsp").forward(request, response);
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

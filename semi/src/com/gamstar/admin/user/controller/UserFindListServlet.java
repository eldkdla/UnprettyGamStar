package com.gamstar.admin.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.user.model.service.AdminUserService;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class UserFindListServlet
 */
@WebServlet("/admin/user/search")
public class UserFindListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFindListServlet() {
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
			
			
			List<User> list=null;
			int totalMember=0;
			
			switch(searchType)
			{
				case "id" : list=new AdminUserService().searchIdList(cPage,numPerPage,searchKeyword);
				totalMember=new AdminUserService().selectMemberCountId(searchKeyword);break;
				case "name" : list=new AdminUserService().searchNameList(cPage,numPerPage,searchKeyword);
				totalMember=new AdminUserService().selectMemberCountName(searchKeyword);break;
				case "email" : list=new AdminUserService().searchEmailList(cPage,numPerPage,searchKeyword);
				totalMember=new AdminUserService().selectMemberCountEmail(searchKeyword);break;
				case "phone" : list=new AdminUserService().searchPhoneList(cPage,numPerPage,searchKeyword);System.out.println("servlet,검색 들어감");
				totalMember=new AdminUserService().selectMemberCountPhone(searchKeyword);break;
			}
			
			int totalPage=(int)Math.ceil((double)totalMember/numPerPage);
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
				pageBar+="<button id='back' href='"+request.getContextPath()+"/admin/user/search?cPage="+(pageNo-1)
						+"&numPerPage="+numPerPage+"&type="+searchType+"&keyword="+searchKeyword+"'><</button>";
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
					pageBar+="<small><a href='"+request.getContextPath()+"/admin/user/search?cPage="+(pageNo)
							+"&numPerPage="+numPerPage+"&type="+searchType+"&keyword="+searchKeyword+"'>"+pageNo+"</a></small>";
				}
				pageNo++;
			}
			
			if(pageNo>totalPage)
			{
				pageBar+="<button id='next' disabled='disabled href='"+request.getContextPath()+"/admin/userFindList?cPage="+pageNo
						+"&numPerPage="+numPerPage+"'>></button>";
			}
			
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("list", list);
			request.setAttribute("cPage", cPage);
			request.setAttribute("numPerPage", numPerPage);
			request.setAttribute("pageBar", pageBar);
			request.getRequestDispatcher("/view/admin/user/adminUserListFind.jsp").forward(request, response);
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

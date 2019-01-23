package com.gamstar.user.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
/**
 * Servlet implementation class StoryModifyServlet
 */
@WebServlet("/view/storymodify")
public class StoryModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(request.getSession().getAttribute("userNo")!=null&&request.getParameter("uu")==null){

			String msg="";
			String loc="";
			
			String root=getServletContext().getRealPath("/");
			String path=root+"upload/story";
			System.out.println("path: "+path);
			
			if(ServletFileUpload.isMultipartContent(request)){
				Connection conn=getConnection();
				
				int maxSize=1024*1024*100;//100MB
				MultipartRequest multi = new MultipartRequest(request, path,maxSize,"utf-8", new DefaultFileRenamePolicy());
			
				User user=new User();
				user.setNo((int)request.getSession().getAttribute("userNo"));			
				
				int result=0;
				NewspeedMedia newUserStoy=new NewspeedMedia();
				NewspeedMedia oldUserStory=new UserService().selectStory(conn,user);
				
				if(multi.getFilesystemName("uploadProfileStory")!=null){
					newUserStoy.setPath("upload/story/"+multi.getFilesystemName("uploadProfileStory"));
					if(oldUserStory.getPath()==""){
						result=new UserService().insertStory(conn,newUserStoy,user);
					}
					else{
						result=new UserService().updateStory(conn,newUserStoy,user);
					}
				}
				
				if(result!=0){
					File file=new File(request.getSession().getServletContext().getRealPath("/")+oldUserStory.getPath());
						if(file.delete()){
							System.out.println("삭제성공");
						}else{
							System.out.println("삭제실패");
						}
					msg="스토리 변경 성공";
					loc="/view/profile";
				}
				else{
					msg="스토리 변경 실패";
					loc="/view/profile";
				}
				close(conn);
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
			}
			else{
				System.out.println("사진 multipart로 안보냈음");
				response.sendRedirect("profile");
			}
			
		}
		else{
			request.setAttribute("msg", "잘못된 접근");
			request.setAttribute("loc", "");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
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

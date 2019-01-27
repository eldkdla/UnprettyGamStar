package com.gamstar.user.support.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import com.gamstar.filecontroller.*;
import com.gamstar.user.support.model.vo.SupportUserData;
import com.gamstar.user.support.service.SupportUserDataService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
/**
 * Servlet implementation class UserSupportDataServlet
 */
@WebServlet("/userSupportData")
public class SupportUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportUserDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//System.out.println("들어옴?");

	//if(request.getSession().getAttribute("userNo")!=null&&request.getParameter("uu")==null){
			SupportUserData userData=new SupportUserData();
			
			String root=getServletContext().getRealPath("/");
			String path=root+"upload/support";
	//		String path = request.getRealPath("/upload");
			System.out.println("path:"+ path);
	
		if (ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;// 10MB
			MultipartRequest multi = new MultipartRequest(request, path, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			//파일 오리지널 이름
			List<String> fileOriNamelist = new FileController().getFileOriNameList(multi, "supportFileName[]");

			for (int i = 0; i < fileOriNamelist.size(); i++) {
				System.out.println("파일 오리지널 이름 : " + fileOriNamelist.get(i));
			}
			
			//파일 바뀐 이름
			List<String> fileNamelist = new FileController().getFileNameList(multi, "supportFileName[]");

			for (int i = 0; i < fileNamelist.size(); i++) {
				System.out.println("파일 이름 : " + fileNamelist.get(i));
			}
			int[] type=new int[1000];
			//파일 타입
			List<String> fileTypelist = new FileController().getFileTypeList(multi, "supportFileName[]");

			for (int i = 0; i < fileTypelist.size(); i++) {
				int pathpoint = fileTypelist.get(i).lastIndexOf('/');
            	String filepoint = fileTypelist.get(i).substring(pathpoint+1,fileTypelist.get(i).length());
            	String filetype = filepoint.toLowerCase();
            	
            	if(filetype.equals("jpg") || filetype.equals("gif") || filetype.equals("png")|| filetype.equals("jpeg") || filetype.equals("bmp")) {
            		type[i]=0;
            		System.out.println("이미지임");;
            		
            	}
            	else {
            		type[i]=1;
            	}
				System.out.println("파일 타입 : " + fileTypelist.get(i));
			}
			
			//userData.setUserNo((int)request.getSession().getAttribute("userNo"));
			userData.setUserNo(1);
			userData.setSupportTitle(multi.getParameter("title"));
			userData.setSupportContent(multi.getParameter("content"));
			
			
			new SupportUserDataService().insertSupportUserData(userData,fileNamelist,fileOriNamelist,type);
			
			if (multi.getFilesystemName("supportFileName") != null) {
				// 프로필 사진 수정한것
				// System.out.println(("upload/support/"+multi.getFilesystemName("supportFileName")));
				// result=new UserService().updateProfilePhoto(user);*/
			}
		} else {
			System.out.println("사진 multipart로 안보냈음");
			// response.sendRedirect("profile");
		}

		//}
	/*else{
			request.setAttribute("msg", "잘못된 접근");
			request.setAttribute("loc", "");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
		}*/

		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

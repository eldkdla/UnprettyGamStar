package com.gamstar.report.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.report.model.vo.ReportBoard;
import com.gamstar.admin.report.model.vo.ReportBoardMedia;
import com.gamstar.filecontroller.FileController;
import com.gamstar.filecontroller.NewspeedMediaReNamePolicy;
import com.gamstar.report.model.service.ReportService;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReportUserServlet
 */
@WebServlet("/view/reportuser")
public class ReportUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
				if(request.getSession().getAttribute("userNo")!=null){
				
				String root = getServletContext().getRealPath("/") + "upload/report/";
				FileController fileCont = new FileController();
			
				int userNo = ((int)request.getSession().getAttribute("userNo"));
				
				System.out.println("경로 : "+root);
				
				MultipartRequest mr = new MultipartRequest(request, root, 21000000, "UTF-8", new NewspeedMediaReNamePolicy());
				String content = mr.getParameter("content");
				int targetUserNo = Integer.parseInt(mr.getParameter("targetUserNo"));
				int targetNewspeedNo = Integer.parseInt(mr.getParameter("targetNewspeedNo"));

				System.out.println(content + "내용");
				System.out.println(targetUserNo +   "  "  + targetNewspeedNo);
				
				ReportBoard report = new ReportBoard();
				report.setReportBoardType(ReportBoard.TYPE_USER);
				report.setReportBoardTargetNo(targetUserNo);
				report.setReportBoardWriterNo(userNo);
				report.setReportBoardContent(content);
				
				List<ReportBoardMedia> reportBoardMediaList = new ArrayList<ReportBoardMedia>();
				List<String> fileNameList = fileCont.getFileNameList(mr, "reportmedia[]");
				
				while(fileNameList.size() > 0) {
					ReportBoardMedia rBM = new ReportBoardMedia();
					rBM.setReportBoardMediaPathRe("upload/report/" + fileNameList.get(fileNameList.size() - 1));
					rBM.setReportBoardMediaPathOri("upload/report/" + fileNameList.remove(fileNameList.size() - 1));
					rBM.setReportBoardMediaIndex(reportBoardMediaList.size());
					
					reportBoardMediaList.add(rBM);
				}
				
				int result = new ReportService().insertReportBoardTypeUser(report, reportBoardMediaList);
						
				response.getWriter().println("success");
				
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

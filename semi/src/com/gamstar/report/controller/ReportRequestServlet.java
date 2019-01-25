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
import com.gamstar.filecontroller.NewspeedMediaReNamePolicy;
import com.gamstar.newspeed.model.vo.Newspeed;
import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.newspeed.model.vo.NewspeedMediaTag;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReportRequestServlet
 */
@WebServlet("/report/reportrequest")
public class ReportRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("userNo") == null) {
			System.out.println("오잉?");
		}
		
		String root = getServletContext().getRealPath("/") + "upload/report/";
		
	
		int userNo = Integer.parseInt(request.getSession().getAttribute("userNo").toString());
		int result = -1;
		
		System.out.println(root + "경로야!");
		
		MultipartRequest mr = new MultipartRequest(request, root, 21000000, "UTF-8", new NewspeedMediaReNamePolicy());
		String content = mr.getParameter("content");
		int targetUserNo = Integer.parseInt(mr.getParameter("targetUserNo").toString());
		int targetNewspeedNo = Integer.parseInt(mr.getParameter("targetNewspeedNo").toString());

		System.out.println(content + "반가워");
		System.out.println(targetUserNo +   "  "  + targetNewspeedNo);
		
		ReportBoard report = new ReportBoard();
		report.setReportBoardNo(targetNewspeedNo);
		report.setReportBoardType(ReportBoard.TYPE_NEWSPEED);
		report.setReportBoardTargetNo(targetUserNo);
		report.setReportBoardWriterNo(userNo);
		
		response.getWriter().println("success");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

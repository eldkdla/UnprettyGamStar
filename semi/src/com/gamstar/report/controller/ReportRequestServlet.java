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
import com.gamstar.newspeed.model.vo.Newspeed;
import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.newspeed.model.vo.NewspeedMediaTag;
import com.gamstar.report.model.service.ReportService;
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
		FileController fileCont = new FileController();
	
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
		report.setReportBoardLink(targetNewspeedNo);
		report.setReportBoardType(ReportBoard.TYPE_NEWSPEED);
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
		
		ReportService reportService = new ReportService();
		
		reportService.insertReportBoardTypeNewspeed(report, reportBoardMediaList);
				
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

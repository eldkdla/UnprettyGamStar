<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.gamstar.admin.report.model.vo.ReportBoard, com.gamstar.admin.support.model.vo.SupportBoard" %>
<%
		List<ReportBoard> rList=(List)request.getAttribute("reportList");
		int rCount=(Integer)request.getAttribute("reportUnck");
		List<SupportBoard> sList=(List)request.getAttribute("supportList");
		int sCount=(Integer)request.getAttribute("supportUnck");
%>
<style>
/* 위치/크기 설정용 */
	section{
		width:100%;
		height:100%;
	}
	section div{
		display:inline-block;
	}
	div#adminFunctionBtn-container{
	display:none;
		width : 100%;
		height:25%;
		vertical-align: middle;
		text-align: center;
	}
	div#adminFunctionBtn-container div{
		width : 20%;
		text-align: center;
	}
	div#adminFunctionBtn-container div img{
		display:block;
	}
	div#adminMainContent-container{
		width:100%;
		height:25%;
	}
	div#reportList-container,div#supportList-container{
		width:48%;
		margin-right:auto;
		margin-left:auto;
		height:100%;
		text-align:center;
	}
	div#reportList-container{
		float:left;
	}
	div#supportList-container{
		float:right;
	}
	div#reportList-container table,div#supportList-container table{
		width:100%;
		height:100%;
		text-align:center;
		cellspacing:0;
		border-collapse: collapse;
	}
	div#reportList-container table th,div#supportList-container table th{
		margin:0;
		padding:0;
		border-bottom: 1px solid rgba(0,0,0,0.7);
		color:rgba(0,0,0,0.8);
	}
	div#reportList-container table td,div#supportList-container table td{
		margin:0;
		padding:0;
		border-bottom: 1px solid rgba(0,0,0,0.4);
		color:rgba(0,0,0,0.7);
	}
	div#reportList-container table a,div#supportList-container table a{
		text-decoration: none;
		color:rgba(0,0,0,0.7);
	}

@media all and (min-width:1067px){}
@media all and (min-width:601px) and (max-width:1066px){
	div#adminFunctionBtn-container {
		display:none;
	}
	br{
		display:none;
	}
}
@media all and (max-width:600px){
	div#adminFunctionBtn-container {
		display:none;
	}
	br{
		display:none;
	}
	div#reportList-container,div#supportList-container{
		display:block;
		width:100%;
		margin-right:auto;
		margin-left:auto;
	}
	div#reportList-container{
		margin-top:5%;
	}
	div#supportList-container{
		margin-top:5%;
	}
	
}
</style>
<%@ include file="/view/admin/common/header.jsp" %>
<!-- 여기서부터 본문 -->
<section>
	<br/><br/><br/><br/><br/><br/><br/><br/>
	<div id='adminFunctionBtn-container'>
		<div id='userBtn'>
			<img src='<%=request.getContextPath() %>/img/adminImg/member.png' />
			<label>회원관리</label>
		</div>
		<div id='supportBtn'>
			<img src='<%=request.getContextPath() %>/img/adminImg/send.png'/>
			<label>문의확인</label>
		</div>
		<div id='reportBtn'>
			<img src='<%=request.getContextPath() %>/img/adminImg/stop.png'/>
			<label>신고처리</label>
		</div>
		<div id='managerBtn'>
			<img src='<%=request.getContextPath() %>/img/adminImg/admin.png' />
			<label>관리자</label>
		</div>
	</div>
	<div id='adminMainContent-container'>
		<div id='reportList-container'>
			<table>
				<tr>
					<th colspan="3">Report</th>
				</tr>
				<tr>
					<th>User</th>
					<th>Link</th>
					<th>Date</th>
				</tr>
		<%for(ReportBoard r : rList) {%>
				<tr>
					<td><%=r.getReportBoardTargetId() %></td>
					<td>go</td>
					<td><%=r.getReportBoardDate() %></td>
				</tr>
			<%}
			if(rList.size()<5) {
			   		for(int i=0; i<5-rList.size(); i++) {%>
			   <tr>
			   		<td>&nbsp;</td>
			   		<td></td>
			   		<td></td>
			   </tr>
			<%		}
		   	  }%>   	
			</table>
		</div>
		<div id='supportList-container'>
			<table>
				<tr>
					<th colspan="3">Support</th>
				</tr>
				<tr>
					<th>User</th>
					<th>Title</th>
					<th>Date</th>
				</tr>
			<%for(SupportBoard s : sList) {%>
				<tr>
					<td><%=s.getSupportBoardWriterId() %></td>
					<td><a href="<%=request.getContextPath()%>/admin/supportView?no=<%=s.getSupportBoardNo() %>">
					<%if (s.getSupportBoardTitle().length()>10) { %><%=s.getSupportBoardTitle().substring(9) %>..<%}
						else {%><%=s.getSupportBoardTitle() %><%} %></a></td>
					<td><%=s.getSupportBoardDate() %></td>
				</tr>
			<%}
			   if(sList.size()<5) {
			   		for(int i=0; i<5-sList.size(); i++) {%>
			   <tr>
			   		<td>&nbsp;</td>
			   		<td></td>
			   		<td></td>
			   </tr>
			<%		}
		   	  }%>   	
			</table>
		</div>
	</div>
</section>


</body>
</html>
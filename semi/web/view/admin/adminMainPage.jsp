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
	body{
		width:100%;
		height:100%;
		text-align:center;
	}
	section{
		width:100%;
		height:100%;
		text-align:center;
		float:none;
	}
	section div{
		display:inline-block;
		text-align:center;
	}
	div#adminMainContent-container{
		text-align:center;
		width:100%;
		margin:0 auto;
	}
	div#adminBoardContent-container{
		text-align:center;
		width:100%;
	}
	div#adminFunctionBtn-container{
		width : 100%;
		height:25%;
		vertical-align: middle;
		text-align: center;
		display:inline-block;
	}
	div#adminFunctionBtn-container div{
		width : 20%;
		height:50%;
		text-align: center;
		cursor:pointer;
	}
	div#adminFunctionBtn-container div label{
		display:block;
		width:100%;
		height:100%;
		text-align:center;
	}
	div#adminFunctionBtn-container div:hover img{
		opacity: 1.5;
	}
	div#adminFunctionBtn-container div img{
		display:block;
		margin:auto;
		text-align: center;
		opacity:0.5;
	}
	div#adminBoardContent-container{
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
		border-bottom-left-radius: 25px;
		border-bottom-right-radius: 25px;
		border-top-left-radius: 25px;
		border-top-right-radius: 25px;
	}
	div#reportList-container table th,div#supportList-container table th{
		margin:0;
		padding:0;
		border-bottom: 1px solid rgba(0,0,0,0.7);
		color:rgba(0,0,0,0.8);
	}
	div#reportList-container table td:nth-of-type(2n),
	div#supportList-container table td:nth-of-type(2n){
		margin:0;
		padding:0;
		border-bottom: 1px solid rgba(0,0,0,0.4);
		color:rgba(0,0,0,0.7);
		background-color:1px solid rgba(0,0,0,0.2);
	}
	div#reportList-container table td:nth-of-type(2n-1),
	div#supportList-container table td:nth-of-type(2n-1){
		margin:0;
		padding:0;
		border-bottom: 1px solid rgba(0,0,0,0.4);
		color:rgba(0,0,0,0.7);
	}
	
	div#reportList-container table a,div#supportList-container table a{
		text-decoration: none;
		color:rgba(0,0,0,0.7);
	}
	.reportTr{
		cursor:pointer;
	}
	

@media all and (min-width:1067px){
	nav {
		display:none;
	}
	div#wrapper{
		width:100%;
	}
	section{
		margin:auto;
	}
}
@media all and (min-width:601px) and (max-width:1066px){
	div#adminFunctionBtn-container {
		display:none;
	}
	br{
		display:none;
	}
}
@media all and (max-width:600px){
	session{
		margin:0;
		padding:0;
	}
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
	div#supportList-container{
		margin-top:5%;
	}
}
</style>
<%@ include file="/view/admin/common/header.jsp" %>
<style>
	section{
			width:100%;
			height:100%;
			text-align:center;
			float:none;
		}
</style>
<!-- 여기서부터 본문 -->
<section>
	<div id='adminMainContent-container'>
		<br/>
		<br/>
		<br/>
		<div id='adminFunctionBtn-container'>
			<div id='userBtn'>
				<img src='<%=request.getContextPath() %>/img/adminImg/member.png' onclick="location.href='<%=request.getContextPath()%>/admin/userList'";/>
				<label>회원관리</label>
			</div>
			<div id='supportBtn'>
				<img src='<%=request.getContextPath() %>/img/adminImg/send.png' onclick="location.href='<%=request.getContextPath()%>/admin/supportList'";/>
				<label>문의확인</label>
			</div>
			<div id='reportBtn'>
				<img src='<%=request.getContextPath() %>/img/adminImg/stop.png' onclick="location.href='<%=request.getContextPath()%>/admin/reportList'";/>
				<label>신고처리</label>
			</div>
			<div id='managerBtn'>
				<img src='<%=request.getContextPath() %>/img/adminImg/admin.png'  onclick="location.href='<%=request.getContextPath()%>/admin/manager/adminList'";/>
				<label>관리자</label>
			</div>
		</div>
		<br/><br/>
		<div id='adminBoardContent-container'>
			<div id='reportList-container'>
				<table>
					<tr>
						<th colspan="4">Report</th>
					</tr>
					<tr>
						<th>User</th>
						<th>Type</th>
						<th>Content</th>
						<th>Date</th>
					</tr>
			<%if (rList==null||rList.size()==0) {%>
					<tr>
						<td colspan="4"></td>
					</tr>
			<%}else{
				
				for(ReportBoard r : rList) {%>
					<tr class='reportTr' onclick="location.href='<%=request.getContextPath()%>/admin/reportList';">
						<td><%=r.getReportBoardTargetId() %></td>
						<td><%if (r.getReportBoardType()==0){%>USER<%}else if(r.getReportBoardType()==1) {%>NEWSPEED<%} %>
						</td>
						<td><%if (r.getReportBoardContent().length()>8) {%><%=r.getReportBoardContent().substring(0, 7) %>..<%}
								else{%><%=r.getReportBoardContent() %><%} %>
						</td>
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
			   	  }
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
						<td><%=s.getSupportBoardWriterName() %>(<%=s.getSupportBoardWriterId() %>)</td>
						<td><a href="<%=request.getContextPath()%>/admin/supportView?no=<%=s.getSupportBoardNo() %>&cPage=1">
						<%if (s.getSupportBoardTitle().length()>8) { %><%=s.getSupportBoardTitle().substring(0,7) %>..<%}
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
	</div>
</section>
<script>
	function goReport(no){
		location.href='<%=request.getContextPath()%>/admin/reportView?show='+no;
	}
</script>
</body>
</html>
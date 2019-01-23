<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/profileAlert.css">
	


</head>	
<body>
<script>

<%-- if(<%=request.getSession().getAttribute("userNo")%>==null)
	{
	alert('로긴해~');
	} --%>
	
</script>

<%
String login="";
if(request.getSession().getAttribute("userNo") == null)
{
	response.sendRedirect(request.getContextPath()+"/view/login.jsp");
}else
{
	login = (int)request.getSession().getAttribute("userNo") + "";
}
%>
 
<a href="<%=request.getContextPath()%>/view/profile">내정보</a>   <!-- /view/myprofile -->
<a href="<%=request.getContextPath()%>/view/profilemodifyStart">내정보수정</a>
<a href="<%=request.getContextPath()%>/view/login.jsp">로그인</a>
<p><%=login%>가 로그인 한 상태입니다.</p>
<a href="<%=request.getContextPath()%>/logout">로그아웃</a>
<% if(request.getSession().getAttribute("userNo")!=null&&(Integer)request.getSession().getAttribute("userNo")<0) {%>
	<%@ include file="view/admin/common/header.jsp" %>
<%} %>
</body>

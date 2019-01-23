<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int userNo=0;
	if(request.getSession().getAttribute("userNo")!=null)
	{
		userNo=(Integer)request.getSession().getAttribute("userNo");
	}
	
	if(userNo<0){
%>
<%@ include file="view/admin/common/header.jsp" %>
<%} else{%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/informationAlert.css">

</head>
<body>
<%} %>

<form id='limsiloginFrm' action='<%=request.getContextPath() %>/temp/limsiLogin' method='post'>
	<%if (request.getSession().getAttribute("userNo")==null) {%>
	<input type='number' id='userNo' name='userNo'/>
	<button type='submit'>session부여</button>
	<%} %>
</form>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="view/admin/common/header.jsp" %>
<form id='limsiloginFrm' action='<%=request.getContextPath() %>/temp/limsiLogin' method='post'>
	<%if (request.getSession().getAttribute("userNo")==null) {%>
	<input type='number' id='userNo' name='userNo'/>
	<button type='submit'>session부여</button>
	<%} %>
</form>

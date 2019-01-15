<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
	String script=(String)request.getAttribute("script");
%>
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	//서블릿에서 작성한 메세지 호출
	alert('<%= msg%>');
	//새창을 닫아주는 기능 self.close()호출~!
	<%=script!=null?script:""%>
	//호출후 페이지 메인으로 이동
	location.href='<%=request.getContextPath()+loc%>';	
</script>

</head>
<body>

</body>
</html>







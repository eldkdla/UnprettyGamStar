<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
%>
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/informationAlert.css">

</head>
<body>

   <div id="myModal" class="modal">
      <div class="modal-content">
            </br>
            <p><%=msg %></p>
            <div onClick="close_pop();">확인</div>
      </div>
    </div>
 
    <script>
        function close_pop() {
             location.href='<%=request.getContextPath()+loc%>';
        };    
	</script>
	
</body>
</html>







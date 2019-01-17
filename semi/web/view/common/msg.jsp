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
            <div class="alert_close_btn" onClick="close_pop();"><p style="margin:0;">&#10005</p></div>
            <div id='ee' onClick="close_pop();">확인</div>
      </div>
    </div>
 
    <script>
        function close_pop() {
             location.href='<%=request.getContextPath()+loc%>';
        };    
	</script>
	
</body>
</html>







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
            <p><%=msg %></p>
            <div onClick="close_pop();">확인</div>
      </div>
    </div>
 
    <script>
        function close_pop() {
             location.href='<%=request.getContextPath()+loc%>';
        };   
       <%--  $(function(){
        	<%if(msg.equals("비밀번호 변경성공")){
        		request.getSession().removeAttribute("userNo");
        	}
        	else if(msg.equals("회원탈퇴 성공")){
        		request.getSession().removeAttribute("userNo");
        	}%>
        }); --%>
	</script>
	
</body>
</html>







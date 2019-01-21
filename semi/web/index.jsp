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
<a href="<%=request.getContextPath()%>/view/profile">내정보</a>   <!-- /view/myprofile -->
<a href="<%=request.getContextPath()%>/view/profilemodifyStart">내정보수정</a>
<%String sg="efafaef"; %>
<button onclick='aa("<%=sg%>");'>eeeee</button>

    
</body>
<script>
function aa(String msg){
	$('#myModal').remove();
	$('body').append($('<div/>',{
		id:'myModal',
		class:'modal'
	}));
	$('#myModal').append($('<div/>',{
		class:'modal-content'
	}));
	$('.modal-content').append($('<p/>',{
		text:msg
	}));
	$('.modal-content').append($('<div/>',{
		onclick:'close_pop()',
		text:'확인'
	}));
	
}
function close_pop(){
	$('#myModal').css("display","none");
}

	</script>

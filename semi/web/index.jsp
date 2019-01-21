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

<button>eeeee</button>

<div id="myModal" class="modal">
      <div class="modal-content">
            </br>
            <p>dddddd</p>
            <div onClick="close_pop();">확인</div>
      </div>
    </div>
    
</body>
<script>
function aa(){
	
	$('body').append($('<div/>',{
		id:'myModal',
		class:'modal'
	}));
	$('#myModal').append($('<div/>',{
		class:modal-con
	}));
	
}

	</script>

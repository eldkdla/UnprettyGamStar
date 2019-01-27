<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
		<link href="<%=request.getContextPath()%>/css/header.css" rel="stylesheet" type="Text/css">

</head>
<body>

     <nav id='navi'>
        <div id="naviWrap">
           <div id="gamstarLogo"> <p id="gamstarTitle">GamStar</p> </div>

            <div id="naviIconWrap">
                <div id="myHomIcon"><img id="myHomIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/myPage.png"></div>
                <div id="alramIcon"></div>
                <div id="slideIcon"><img id="slideIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/setting.png"></div>
                <div id="slideBox">
                        <div id="infoChange"><div class="infoChangeText"><a href="<%=request.getContextPath()%>/view/profilemodifyStart">개인정보설정</a></div></div>
                        <hr class="slideHr">
                        <div id="chatting" onclick="chat()"><div class="chattingText" ><a href="#">채팅</a></div></div>
                        <hr class="slideHr">
                        <div id="contactBoard" onclick="supportBtn()"><div class="contactBoardText"><a href="#">문의하기</a></div></div>
                        <hr class="slideHr">
                        <div id="logout"><div class="logoutText"><a href="#">로그아웃</a></div></div>
                </div>
            </div>
            <div class="iconBack"><img id ="iconBackImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/logoBack.png"></div> 
        </div>
    </nav>

</body>
</html>

<script>
	$('#slideIcon').click(function slideBoxClick(){ //수정아이콘 클릭 이벤트
       $('#slideBox').slideToggle('slow');
   });
   
	$('#myHomIcon').click(function(){
		location.href='<%=request.getContextPath()%>/view/profile';
	});

</script>
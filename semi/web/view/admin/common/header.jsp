<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin</title>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminMain.css" type="text/css"/>
    <script src='http://code.jquery.com/jquery-latest.min.js'></script>
</head>
<body>
    <div id="wrapper">
        <!-- 헤더(메뉴, 로고) -->
        <header>
            <!-- 메인 상단바랑 어드민 네비랑 충돌있음.  -->
<%--             <nav id='navi'>
        <div id="naviWrap">
        	<div id="gamstarLogo"> <p id="gamstarTitle">GamStar</p> </div>
            <div id='searchBody'>
                <input type="search" id="search" value="" results=5 placeholder="search..."/>
                <img src="<%=request.getContextPath()%>/img/newspeeddetailview/searchView.png" id="searchView">
                <img src="<%=request.getContextPath()%>/img/newspeeddetailview/searchDelete.png" id="searchDelete">
            </div>
            <div id="infoBox"> <div id="infoSentence">Search Something :)</div> </div>
            <div id="naviIconWrap">
                <div id="myHomIcon"><img id="myHomIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/myPage.png"></div>
                <div id="alramIcon"><img id="alramIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/alramBlue.png"></div>
                <div id="slideIcon"><img id="slideIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/setting.png"></div>
                <div id="slideBox">
                        <div id="infoChange"><div class="infoChangeText"><a href="#">개인정보설정</a></div></div>
                        <hr class="slideHr">
                        <div id="chatting" onclick="chat()"><div class="chattingText">채팅</div></div>
                        <hr class="slideHr">
                        <div id="contactBoard" onclick="supportBtn()"><div class="contactBoardText">문의하기</div></div>
                        <hr class="slideHr">
                        <div id="logout"><div class="logoutText"><a href="#">로그아웃</a></div></div>
                </div>
            </div>
            <div class="iconBack"><img id ="iconBackImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/logoBack.png"></div>
            <div class="naviIcon"><img id ="naviIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/logo.png"></div>    
        </div>
    </nav> --%>
        </header>
        <!-- 모바일용 서브 메뉴버튼 -->
        <div id='openMenuDiv'>
            <button id='openMenu' onclick='openAdminMobileMenu();'><img src='<%=request.getContextPath() %>/img/adminImg/menu.png'></button>
        </div>
        <!-- 서브메뉴 -->
        <nav id='adminNav'>
            <br/><br/><br/><br/>
            <p onclick='goMain();' style='cursor:pointer;'>
                <img src='<%=request.getContextPath() %>/img/adminImg/admin.png'>
                <br/>
                <label><small>관리자메뉴</small></label>
            </p>
            <ul>
                <%-- <li>
                    <input type='text' style="width:75%;" placeholder="search"/>
                    <button class='searchBtn'>
                        <img src='<%=request.getContextPath() %>/img/adminImg/search.png'/>
                    </button>
                </li> --%>
                <li>
                	<a class='sideBtn' href='<%=request.getContextPath()%>/admin/userList'>
                		<img src='<%=request.getContextPath() %>/img/adminImg/member.png' />
                		회원관리
               		</a>
            	</li>
                <li>
                	<a class='sideBtn' href='<%=request.getContextPath()%>/admin/supportList'>
                		<img src='<%=request.getContextPath() %>/img/adminImg/send.png'/>
                		문의확인
               		</a>
              	</li>
                <li>
                	<a class='sideBtn' href='<%=request.getContextPath()%>/admin/reportList'>
                		<img src='<%=request.getContextPath() %>/img/adminImg/stop.png'/>
                		신고처리
                	</a>
               	</li>
               	 <li>
                	<a class='sideBtn' href='<%=request.getContextPath()%>/admin/manager/adminList'>
                		<img src='<%=request.getContextPath() %>/img/adminImg/admin.png' />
                		 관리자설정
              		</a>
            	</li>
            </ul>
        </nav>
<script>
	//관리자 버튼 누르면 메인으로 이동
	function goMain(){
		location.href='<%=request.getContextPath() %>/admin/goAdminMain';
	}
	// 모바일 메뉴 열기 위한 함수
    function openAdminMobileMenu(){
        $('#adminNav').toggle();
    }
</script>
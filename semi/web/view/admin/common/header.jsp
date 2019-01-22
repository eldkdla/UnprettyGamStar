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
            <p>메인메뉴</p>
        </header>
        <!-- 모바일용 서브 메뉴버튼 -->
        <div id='openMenuDiv'>
            <button id='openMenu' onclick='openAdminMobileMenu();'><img src='<%=request.getContextPath() %>/img/adminImg/menu.png'></button>
        </div>
        <!-- 서브메뉴 -->
        <nav>
            <br/><br/><br/><br/>
            <p>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>굄성 - UnprettyGamStar - Login</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<script src="<%=request.getContextPath()%>/js/naveridlogin_js_sdk_2.0.0.js"></script>
<link href="https://fonts.googleapis.com/css?family=Jua|Song+Myung|Stylish|Yeon+Sung|Gothic+A1&amp;subset=korean" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/profileAlert.css">
</head>
<body>
<h3>로그인 페이지다요.</h3>
<section id="login-container">
	<div id="loginLayout">
		<div id="xxx">
			<img id="welcome" src="<%=request.getContextPath()%>/img/login/login.png" width="200px" height="200px">
			<h1 class="loginLogo">언프리티 감스타</h1>

			<form id="loginFrm" action="<%=request.getContextPath() %>/login" method="POST">
				<table id="loginTable">
					<tr>
						<td><label for="id" class="loginLabel">ID : </label></td>
						<td><input type="text" class="loginInput" autofocus name="id" id="id" maxlength="30" placeholder=" ID/Email/Phone??" required onkeypress="javascript:capsLock(this);" /></td>
						<td>
						<input type="submit" class='loginButton' value="로그인" onclick="<%=request.getContextPath()%>/login"></td>
					</tr>
					<tr>
						<td><label for="pw" class="loginLabel">PW : </label></td>
						<td><input type="password" class="loginInput" name="pw" id="pw" onkeypress="javascript:capsLock(this);" />
							<p id="capslock" style="color: red; font-weight: bold; display: none">Caps Lock키가 눌려있습니다.</p>
							</td>
					</tr>
					<tr>
						<td colspan="3">
						<a class="loginLabel" href="aaa.html" style="margin-right: 50px">아이디/비밀번호 찾기</a> <a class="loginLabel" href="<%=request.getContextPath()%>/view/userEnroll.jsp">회원가입</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		 
		 <!-- 네이버아이디로로그인 버튼 노출 영역 -->
<div id="naverIdLogin"></div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->
		 <!-- 네이버아이디로로그인 버튼 노출 영역 -->
		 <%-- <a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a> --%>
		<!-- //네이버아이디로로그인 버튼 노출 영역 -->
		<!-- 네이버아이디로로그인 버튼 노출 영역 -->
		<%-- <a href="<%=apiURL%>" target="_blank"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a> --%>
		<!-- //네이버아이디로로그인 버튼 노출 영역 -->
		<div id="plus">
			<a id="gnbLogin" href="#">Login</a>
			<input type="button" value="네이버연동" id="111"> 
			<input type="button" value="페이스북" id="222">
			<input type="button" value="다음" id="333">
			<input type="button" value="구글" id="444">
		</div>
	</div>
</section>

<script>
	/*      var pwInput = document.getElementById("pw");
	 var capslockAlert = document.getElementById("capslock");
	 pwInput.addEventListener("keyup", function (event) {

	 if (!event.getModifierState("CapsLock")) {
	 capslockAlert.style.display = "none"
	 } else {
	 capslockAlert.style.display = "block";
	 }
	 });*/

	function capsLock(e) {
		console.log(event.getModifierState("CapsLock"));
		var capslockAlert = document.getElementById("capslock");
		if (event.getModifierState("CapsLock")) {
			capslockAlert.style.display = "block";
		} else {
			capslockAlert.style.display = "none"
		}
	}

	/* function capsLock(e){
		  var keyCode = 0;
		  var shiftKey=false;
		  keyCode=e.keyCode;
		  shiftKey=e.shiftKey;
		  if (((keyCode >= 65 && keyCode <= 90)&& !shiftKey)||((keyCode >= 97 && keyCode <= 122)&& shiftKey))
		  {
		    alert("CapsLock이 켜져 있습니다");
		    return;
		  }
		} */
		
		//공용 Alert 모달창
        function profileAlert(alertMsg){
           $('#myModal').remove();
           $('body').append($('<div/>',{
              id:'myModal',
              class:'modal'
           }));
           $('#myModal').append($('<div/>',{
              class:'modal-content'
           }));
           $('.modal-content').append($('<p/>',{
              text:alertMsg
           }));
           $('.modal-content').append($('<div/>',{
              onclick:'close_pop()',
              text:'확인'
           }));
        }
        
		//모달창 닫기
        function close_pop(){
           $('#myModal').css("display","none");
        }
        
        
        //네아로 연동
    	var naverLogin = new naver.LoginWithNaverId(
    			{
    				clientId: "fqOHJi8WFN9_xpysEVQG",
    				callbackUrl: "http://localhost:9090/GamStar/view/ncallback.jsp",
    				isPopup: true, /* 팝업을 통한 연동처리 여부 */
    				callbackHandle: true,
    				loginButton: {color: "green", type: 2, height: 30 } /* 로그인 버튼의 타입을 지정 */
    				
    			}
    		);
    		//http://localhost:9090/njst/views/user/nlogin.jsp
    		/* 설정정보를 초기화하고 연동을 준비 */
    		naverLogin.init();
        //팝업창 처리 메소드
    	function setNaverData(naverId, email, winObj) {
    		//$('#token').val(token);
    		
    		console.log(naverId);
    		console.log(email);
    		//console.log(winObj);
    		console.log("어떻게 찍히니?")
    		winObj.close();
    		//var form = $('#nLoginFrm')
    		var form = document.nLoginFrm;

    		
    		$('#naverId').val(naverId);
    		$('#email').val(email);
    		
    		$('#nLoginFrm').submit();
    	};
        
        //값체크용 펑션
    	function test(num) {
    		//$('#token').val(token);

        console.log(num);

    	};
</script>

<!-- 네이버 처리용 필드 -->
<form id="nLoginFrm" method="post" action="<%=request.getContextPath()%>/nlogin">
<input type="hidden" id="naverId" name="naverId" value="">
<input type="hidden" id="email" name="email" value="">
<input type="submit">
</form>

</body>
</html>
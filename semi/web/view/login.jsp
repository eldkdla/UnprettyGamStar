<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>굄성 - UnprettyGamStar - Login</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<script src="<%=request.getContextPath()%>/js/naveridlogin_js_sdk_2.0.0.js"></script>
<link href="https://fonts.googleapis.com/css?family=Jua|Song+Myung|Stylish|Yeon+Sung|Gothic+A1&amp;subset=korean" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
<% if(request.getSession().getAttribute("userNo")!=null)
{
	response.sendRedirect(request.getContextPath()+"/");
}
%>
<h3>로그인 페이지다요.</h3>
<section id="login-container">
	<div id="loginLayout">
		<div id="xxx">
			<img id="welcome" src="<%=request.getContextPath()%>/img/login/login.png" width="200px" height="200px">
			<h1 class="loginLogo">\ 굄-성 /<br>- UnprettyGamStar -</h1>

			<form id="loginFrm" action="<%=request.getContextPath() %>/login" method="POST">
				<table id="loginTable">
					<tr>
						<td><label for="id" class="loginLabel">ID : </label><br>
						<label for="pw" class="loginLabel">PW : </label>
						</td>
						
						<td><input type="text" class="loginInput" autofocus name="id" id="id" maxlength="30" placeholder=" ID" required onkeypress="javascript:capsLock(this);" />
						<input type="password" class="loginInput" style="margin-top: 5px" name="pw" id="pw" placeholder=" Password" onkeypress="javascript:capsLock(this);" />
<!-- 						<p id="capslock" style='color: red; display: none; font-weight: bold;'>Caps Lock키가 눌려있습니다.</p> -->
						
						
						</td>
						<td>
						<input type="submit" class='loginButton' value="로그인" onclick="<%=request.getContextPath()%>/login">
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
						<a class="loginLabel" href="<%=request.getContextPath()%>/view/lostUser.jsp" style="margin-right: 10px">아이디/비밀번호 찾기</a> <a class="loginLabel" href="<%=request.getContextPath()%>/view/userEnroll.jsp">회원가입</a>
						</td>
						<td><div id="naverIdLogin" style ="display: inline-block;"></div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		
		
		<div id="plus_login">
<!-- 네이버아이디로로그인 버튼 노출 영역 -->

<!-- //네이버아이디로로그인 버튼 노출 영역 -->
		<%-- <div id="plus">
			<a id="gnbLogin" href="<%=request.getContextPath()%>">HOME-INDEX</a> 
			<!-- <input type="button" value="페이스북" id="222">
			<input type="button" value="다음" id="333">
			<input type="button" value="구글" id="444"> --></div> --%>
		</div>
	</div>
	
</section>

<script>
	//Caps
	/* $('#pw').on('change keyup paste', (function () {
    if (!event.getModifierState("CapsLock")) {
    	//$("#caps").text('');
        
    }
    else {
    	//capslockAlert.style.display = "block"
    	//$("#caps").text('대문자?').css({'color' : 'red', 'fontWeight': 'bold'});
        //$('#email_check').text('입력한 이메일 주소를 확인해주세요');
        //$('#email_check').css('color', 'red');
        //inval_Arr[3] = false;
    }
	})); */


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
		console.log(event.getModifierState("Capslock"));
		var capslockAlert = document.getElementById("capslock");
		if (event.getModifierState("Capslock")) {
			capslockAlert.style.display = "block";
		} else {
			capslockAlert.style.display = "none"
		}
	}

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
        
    		naverLogin.init();
    		
        //팝업창 처리 메소드
    	function setNaverData(naverId, email, name, winObj) {
    		winObj.close();
    		//var form = $('#nLoginFrm')
    		//var form = document.nLoginFrm;

    		$('#naverId').val(naverId);
    		$('#email').val(email);
    		$('#name').val(name);
    		
    		//alert(name);
    		$('#nLoginFrm').submit();
    		
    	};
        
        
    	function test(num) {
    	console.log(num);
    	};
    	
</script>

<!-- 네이버 처리용 필드 -->
<form id="nLoginFrm" method="post" action="<%=request.getContextPath()%>/nlogin">
<input type="hidden" id="naverId" name="naverId" value="">
<input type="hidden" id="email" name="email" value="">
<input type="hidden" id="name" name="name" value="">
</form>
<!-- 네이버 처리용 필드 -->

</body>
</html>
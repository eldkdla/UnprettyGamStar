<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script	src="<%=request.getContextPath()%>/js/naveridlogin_js_sdk_2.0.0.js"></script>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

로그인 처리중입니다.
<%-- <form method="get" id="testa" action="<%=request.getContextPath()%>/views/user/aaa.jsp">
<input type="hidden" id="userInfo" name="userInfo" value=""> 
</form>
 --%>	<!-- (2) LoginWithNaverId Javscript 설정 정보 및 초기화 -->
	<script>
		var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: "fqOHJi8WFN9_xpysEVQG",
				callbackUrl: "http://localhost:9090/GamStar/view/ncallback.jsp",
				 //callbackUrl: "http://192.168.20.28:9090/GamStar/view/ncallback.jsp",
				 //callbackUrl: "http://192.168.43.213:9090/GamStar/view/ncallback.jsp",
				<%-- callbackUrl: "<%=request.getContextPath()%>/view/ncallback.jsp", --%>
				isPopup: true,
				callbackHandle: true	/* callback 페이지가 분리되었을 경우에 callback 페이지에서는 callback처리를 해줄수 있도록 설정합니다. */
				
			}
		);

		/* (3) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();

		/* (4) Callback의 처리. 정상적으로 Callback 처리가 완료될 경우 main page로 redirect(또는 Popup close) */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					var email = naverLogin.user.getEmail();/* 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
					var name = naverLogin.user.getName();
					
					if( (email == undefined || email == null) || (name == undefined || name == null)) {
						alert("필수정보제공을 동의해주세요.");
						
						naverLogin.reprompt();		/* 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
						
						return;
					}
	
					
					if (status) {
					var email = naverLogin.user.getEmail();
					var name = naverLogin.user.getName();
					var profileImage = naverLogin.user.getProfileImage();
					var birthday = naverLogin.user.getBirthday();
					var uniqId = naverLogin.user.getId();
					var age = naverLogin.user.getAge();
					
					//console.log("테스트부분");
					
					//var token = naverLogin.accessToken();
					//console.log("토큰 : "+token);
					var aaa = 1234;
					opener.test(aaa);
					
					//alert("한박자쉬고");
					opener.setNaverData(uniqId, email, name, window);
					//console.log("자식창은 언제 빠이빠이?")
					//console.log("정보 : "+email+" : "+name+" : "+profileImage+" : "+birthday+" : "+uniqId+" : "+age+"끗");
					//console.log(email);
					
					}  /*프로필 파싱 부분*/
					//console.log(naverLogin.user);
					//var userInfo = naverLogin.user;
										
					/* $(document).ready(function() {
						$('#userInfo').html(userInfo);
				    });
					console.log($('#userInfo').val()); */
					
					/* $('#userInfo').val(userInfo);
					 console.log($('#userInfo').val(userInfo)); */
					 //$("#userInfo").val(JSON.stringify(userInfo));
					 //console.log($("#userInfo").val());
					 //$('#testa').submit();
					 //JSON.stringify(userInfo)
					//alert("미쳤네?");
					
					
					//top.opener.location="/njst/views/user/aaa.jsp?userInfo="+JSON.stringify(userInfo);
					//top.opener.location="/njst/views/user/aaa.jsp";//그나마...
                    window.close();
					
					//window.opener.location.reload(true);
					//window.opener.location.href="/njst/views/user/aaa.jsp";
					//window.close(); 
				
					//window.location.replace("http://" + window.location.hostname + ( (location.port==""||location.port==undefined)?"":":" + location.port) + "/njst/views/user/nlogin.jsp");		//처리후에 되돌아갈 곳
				} else {
					//console.log("callback 처리에 실패하였습니다.");
				}
				window.close();
			});
			
		});
		
	</script>

</body>
</html>
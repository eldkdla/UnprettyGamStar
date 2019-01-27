<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>굄성 - UnprettyGamStar - 가입페이지다요/</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/profileAlert.css">
<link href="https://fonts.googleapis.com/css?family=Jua|Song+Myung|Stylish|Yeon+Sung|Gothic+A1&amp;subset=korean" rel="stylesheet">
</head>

<style>
    *{
        font-size: 15px;
        font-weight: bold;
        font-family: 'Gothic A1', sans-serif;
    }
    
    div#mainDiv{
    /* border : 1px dotted red; */
    
    width : 410px;
    min-width: 310px;
    margin : 0 auto;
}

.enrollBtn {
	position: relative;
	outline: none;
	border: none;
	border-radius: 5px;
	background-color: gray;
	color: white;
	font-weight: bolder;
	/* //width: 70px; */
	height: 25px;
	font-size: 15px;
	cursor: pointer;
	font-family: 'Gothic A1', sans-serif;
}

.reg_button {
margin-top : 10px;
text-align: center;
}

div .userRegFrm {
    /*
    border : 1px solid orange;
    display: flex;
    flex-direction: row;
    justify-content: center;
    width:300px;
    min-width: 300px;
    border: 1px solid red;
    display: flex;
    flex-direction: column;
    align-items: center; */
        
    }
    </style>

<body>
<div id=mainDiv>
        <fieldset>
            <legend>&nbsp; - UnprettyGamStar - &nbsp;</legend>
            <p>안뇽? 가입을 환영한다요./</p>
            <form action="<%=request.getContextPath()%>/userenroll" method="post">
                <!-- 아이디 -->
                <div class="userRegFrm">
                    <label for="userId">아이디</label><br>
                    <input type="text" class="regControl" id="userId" name="userId" placeholder="  아이디 입력" maxlength='20'
                        autofocus size="49" required>
                    <div class="checkMsg" id="id_check"></div>
                </div>
                <!-- 비밀번호 -->
                <div class="userRegFrm">
                    <label for="userPw">비밀번호</label>
                    <sub></sub><br>
                    <input type="password" class="regControl" id="userPw" name="userPw" placeholder="  비밀번호 " maxlength='20'
                        autofocus size="49" required>
                    <div class="checkMsg" id="pw_check"></div>
                </div>
                <!-- 비밀번호 재확인 -->
                <div class="userRegFrm">
                    <label for="userPw2">비밀번호 확인</label><br>
                    <input type="password" class="regControl" id="userPw2" name="userPw2" placeholder="  비밀번호 확인"
                        maxlength='20' autofocus size="49" required>
                    <div class="checkMsg" id="pw2_check"></div>
                </div>
                <!-- 유저 이름 -->
                <div class="userRegFrm">
                    <label for="userName">이름</label><br>
                    <input type="text" class="regControl" id="userName" name="userName" placeholder="  이름 입력 (한글 2-6자)"
                        maxlength='20' autofocus size="49" required>
                    <div class="checkMsg" id="name_check"></div>
                </div>
                <!-- 본인확인 이메일 -->
                <div class="userRegFrm">
                    <label for="userEmail">이메일</label><br>
                    <input type="text" class="regControl" name="userEmail" id="userEmail" placeholder="  E-mail"
                        autofocus size="30" required><button type="button" class="enrollBtn" id="emailSendBtn" name="emailSendBtn" onclick="sendEmail();" disabled  style="margin-left: 7px">인증 메일보내기</button>
                    <div class="checkMsg" id="email_check"></div>
                </div>
                <!-- 이메일 인증하기 -->
                <div class="userRegFrm" id="userAuth" style="display:none;">
                    <label for="userAuth">이메일 : 인증번호</label><br>
                    <input type="text" class="regControl" name="userAuthCode" id="userAuthCode" placeholder="  인증번호를 입력하세요."
                        autofocus size="36" required> <button type="button" class="enrollBtn" id="authChkBtn" name="authChkBtn" onclick="authChk();"  style="margin-left: 7px">인증하기</button>
                    <div class="checkMsg" id="auth_check"></div>
                </div>
                <!-- 휴대전화 -->
                <div class="userRegFrm">
                    <label for="userPhone">휴대전화 ('-' 없이 번호만 입력해주세요.)</label><br>
                    <input type="text" class="regControl" id="userPhone" name="userPhone" placeholder="  휴대전화 번호"
                        maxlength='11' autofocus size="49" required>
                    <div class="checkMsg" id="phone_check"></div>
                </div>

                <div class="reg_button">
                    <input type="reset" class="enrollBtn" onclick="pageBack();"value="뒤로가기" />&emsp;&emsp;
                    <input type="submit" class="enrollBtn" id="reg_submit" value="가입하기" />

          
                </div>
            </form>
        </fieldset>
    </div>

</body>


<script>

	//검증파트
	var inval_Arr = new Array(7).fill(false);

	//정규식 파트
    var regExp_emp = /\s/g;                 //빈칸
    var regExp_id = /^[a-z0-9]{1,20}$/;  //a-z,0-9로 시작하는 1~20자리 아이디
    var regExp_pw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9])(?=.*[A-Z]).{8,20}$/ //대소문자 +숫자 8~20 패스 
    var regExp_email = /^[\w\-]+@(?:(?:[\w\-]{2,}\.)+[a-zA-Z]{2,})$/;
    var regExp_phone = /^(01[016789]{1})([0-9]{3,4})([0-9]{4})$/;
    var regExp_name = /^[가-힣]{2,6}$/;//한글 2~6자

    //아이디 정규식 -ajax와 통합
   /*  $('#userId').on('change keyup paste', (function () {
        if (regExp_id.test($(this).val())) {
            console.log('id참참');
            $("#id_check").text('');
            inval_Arr[0] = true;
        }
        else {
            $('#id_check').text('아이디를 확인해주세요');
            $('#id_check').css('color', 'red');
            inval_Arr[0] = false;
        }
    })); */
    
  //ajax 아이디 중복여부 아이디 정규식
    $('#userId').on('change keyup paste',(function(){
    	if($('#userId').val()==""){//아이디 빈칸일때
            $('#id_check').html("");
         }else{
        	 
        	 if (regExp_id.test($(this).val())) {
                 //console.log('id참참');
                 //$("#id_check").text('');
                 inval_Arr[0] = true;
             
            $.ajax({
               url:'<%=request.getContextPath()%>/useridChk',
               type:"POST",
               data:{"inputId":$('#userId').val()},
               success:function(data){
                  if(data=="true"){
                     $('#id_check').html("사용가능한 아이디입니다.").css('color', 'green');
                     inval_Arr[1] = true;
                  }
                  else if(data=="false"){
                 	 $('#id_check').html("사용 불가능한 아이디입니다.").css('color', 'red');
                 	 inval_Arr[1] = false;
                  }
               },
               error:function(xhr,status){
                  alert(xhr+" : "+status);
       		}
   			
    		});
        	 }
        	 
        	 //틀리아이디의경우
        	 else {
            $('#id_check').html('아이디를 확인해주세요<br>- 영어 대.소문자 와 숫자사용가능 (1~20자리)');
            $('#id_check').css('color', 'red');
            inval_Arr[0] = false;
        }
         
 	}}));

    //비밀번호 정규식
    $('#userPw').on('change keyup paste', (function () {
        if (regExp_pw.test($(this).val())) {
        	//if (1) {
            //console.log('pw참참')
            $("#pw_check").text('');
        }
        else {
            //$('#pw_check').text('비밀번호를 확인해주세요');
            $('#pw_check').css('color', 'red');
            $('#pw_check').html('비밀번호를 확인해주세요.<br><sub>- 영어 대,소문자 그리고 숫자, 특수기호를 <br>- 각각 한글자이상 포함하여 8 ~ 20 글자 </sub>');
            
        }

    }));

    //비밀번호 재확인
    $('#userPw2').on('change keyup paste', (function () {
        if ($('#userPw').val() != $(this).val())
        {
            $('#pw2_check').text('입력하신 비밀번호가 서로 다릅니다.');
            $('#pw2_check').css('color', 'red');
            inval_Arr[2] = false;
        }
        else {
            $('#pw2_check').text('');
            inval_Arr[2] = true;
        }
    }));
    
  //유저이름 정규식
    $('#userName').on('change keyup paste', (function () {
        if (regExp_name.test($(this).val())) {
            //console.log('name참참');
            $("#name_check").text('');
            inval_Arr[3] = true;
        }
        else {
            $('#name_check').text('이름을 확인해주세요');
            $('#name_check').css('color', 'red');
            inval_Arr[3] = false;
        }
    }));

    //이메일 정규식
    $('#userEmail').on('change keyup paste', (function (e) {
        if (regExp_email.test($(this).val())) {
            //console.log('email참참')
            $("#email_check").text('');
            $('#emailSendBtn').attr('disabled', false);
            inval_Arr[4] = true;
        }
        else {
        	
            $('#email_check').text('입력한 이메일 주소를 확인해주세요');
            $('#email_check').css('color', 'red');
            $('#userAuth').css("display", "none");
        	$('#emailSendBtn').attr('disabled', true);      	
            inval_Arr[4] = false;
        }
    }));

    //핸드폰 정규식
    $('#userPhone').on('change keyup paste', (function () {
        if (regExp_phone.test($(this).val())) {
            //console.log('phone참참')
            $("#phone_check").text('');
            inval_Arr[5] = true;
        }
        else {
            $('#phone_check').text('입력한 핸드폰 번호를 확인해주세요');
            $('#phone_check').css('color', 'red');
            inval_Arr[5] = false;
        }
    }));
    
    	
    	
    
        
    
    //전송 체크
    $('#reg_submit').click(function(){
    	var validAll = true;
		for(var i = 0; i < inval_Arr.length; i++){
			
			if(inval_Arr[i] == false){
				validAll = false;
				//console.log(i+": false");
			}
		}
		
		if(validAll){ // 유효성 모두 통과
			//profileAlert("");
    
			return true;
			
		} else{
			profileAlert("입력한 정보들을 다시 한번 확인해주세요 :)");
    return false;
		}
	});    
	
  
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
    };
    
    function pageBack(){
        location.href="<%=request.getContextPath()%>/view/login.jsp";
        history.back();
    };
    
    
    //이메일인증테스트
     		   	var authCode = "";
    			//이메일 전송 부분
    			function sendEmail(){
    		    	//console.log('이메일전송옹');
    		    	$('#userEmail').attr("readonly", "readonly");
    		    	$('#userEmail').css("background-color", "rgb(207,207,207)");
    			    $('#emailSendBtn').attr('disabled', true);
    			    $('#emailSendBtn').css('cursor', 'not-allowed');
    			    $('#emailSendBtn').css("background-color", "rgb(207,207,207)");
    		    	var email = $('#userEmail').val();
    		    	
    				if($('#userEmail').val()==""){//이메일 빈칸일때
    			           $('#email_check').html("email을 입력하세요.");
    			        }else{
    			          
    			        $.ajax({
    			              url:'<%=request.getContextPath()%>/mailSend',
    			              type:"POST",
    			              data:{"receiver":email},
    			              success:function(data){
    			                 authCode = data;
    			            	  
    			                 if(data>0){
    			                    $('#email_check').html("인증메일이 발송 되었습니다.").css('color', 'green');
        		           			$('#userAuth').css("display", "block");
    			           			//console.log('성공했답니다');
    			                 }                                                                                                                                                                                                                                                                                                                                                                                                    
    			                 else if(data==falses){
    			                	 $('#email_check').html("메일발송에 실패하였습니다. 관리자에게 문의하세요").css('color', 'red');
    			           				//comsole.log('실패랍니다');
    			                 }
    			              },
    			              error:function(xhr,status){
    			                 alert(xhr+" : "+status);
    			      		}
    			   		});
    				}};
		
    				
    				function authChk(){
    				
    					var code = $('#userAuthCode').val();
    					
    					if(Number(code) == Number(authCode)) {
    						//console.log("둘이 같아요");
    						$('#auth_check').html("인증이 완료 되었습니다.").css('color', 'green');
    						$('#authChkBtn').attr('disabled', true);
    						$('#authChkBtn').css('cursor', 'not-allowed');
    						$('#userAuthCode').attr("readonly", "readonly");
    	    		    	$('#userAuthCode').css("background-color", "rgb(207,207,207)");
    	    		    	$('#authChkBtn').css("background-color", "rgb(207,207,207)");
    						inval_Arr[6] = true;
    						}
    					else{
    						
    						//console.log("값이 달라요");
    						$('#auth_check').html("인증번호를 확인해 주세요.").css('color', 'red');
    						inval_Arr[6] = false;
    					}
    		
    				};
				

</script>
</html>
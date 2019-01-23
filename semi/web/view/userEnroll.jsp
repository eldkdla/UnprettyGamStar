<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>굄성 - UnprettyGamStar - 가입페이지다요/</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
</head>

<style>
asdasdasdiv{
/* justify-content: center;
width: 600px;
min-width: 300px;
border: 1px solid red;
display: flex;
flex-direction: column;
align-items: center; */

}

</style>


<body>

    <p>닝겐의 가입을 환영한다요</p>
    <form action="<%=request.getContextPath()%>/userenroll" method="post">
        <!-- 아이디 -->
        <div class="userRegFrm">
            <label for="userId">아이디</label><br>
            <input type="text" class="regControl" id="userId" name="userId" placeholder="아이디 입력" maxlength='20' required>
            <div class="checkMsg" id="id_check"></div>
        </div>
        <!-- 비밀번호 -->
        <div class="userRegFrm">
            <label for="userPw">비밀번호</label>
            <sub></sub><br>
            <input type="password" class="regControl" id="userPw" name="userPw" placeholder="비밀번호 " maxlength='20' required>
            <div class="checkMsg" id="pw_check"></div>
        </div>
        <!-- 비밀번호 재확인 -->
        <div class="userRegFrm">
            <label for="userPw2">비밀번호 확인</label><br>
            <input type="password" class="regControl" id="userPw2" name="userPw2" placeholder="비밀번호 확인" maxlength='20' required>
            <div class="checkMsg" id="pw2_check"></div>
        </div>
        <!-- 유저 이름 -->
        <div class="userRegFrm">
            <label for="userName">이름</label><br>
            <input type="text" class="regControl" id="userName" name="userName" placeholder="이름 입력 (한글 2-6자)" maxlength='20' required>
            <div class="checkMsg" id="name_check"></div>
        </div>        
        <!-- 본인확인 이메일 -->
        <div class="userRegFrm">
            <label for="userEmail">이메일</label><br>
            <input type="text" class="regControl" name="userEmail" id="userEmail" placeholder="E-mail" required>
            <!-- <input type="text" style="margin-top: 5px;"class="email_form" name="email_confirm" id="email_confirm" placeholder="인증번호를 입력해주세요!" required>
                            <button type="button" class="btn btn-outline-danger btn-sm px-3" onclick="confirm_email()">
                                <i class="fa fa-envelope"></i>&nbsp;인증
                            </button>&nbsp;
                            <button type="button" class="btn btn-outline-info btn-sm px-3">
                                <i class="fa fa-envelope"></i>&nbsp;확인
                            </button>&nbsp; -->
            <div class="checkMsg" id="email_check"></div>
        </div>
        <!-- 휴대전화 -->
        <div class="userRegFrm">
            <label for="userPhone">휴대전화 ('-' 없이 번호만 입력해주세요)</label><br>
            <input type="text" class="regControl" id="userPhone" name="userPhone" placeholder="휴대전화 번호"  maxlength='11' required>
            <div class="checkMsg" id="phone_check"></div>
        </div>
        <div class="reg_button">
			<input type="reset" value="취소"/>&emsp;&emsp;
        <input type="submit" value="가입"/>
            <%-- <a class="btn btn-danger px-3" href="${pageContext.request.contextPath}">
                <i class="fa fa-rotate-right pr-2" aria-hidden="true"></i>취소하기
            </a>&emsp;&emsp;
            <button class="btn btn-primary px-3" id="reg_submit">
                <i class="fa fa-heart pr-2" aria-hidden="true"></i>가입하기</button> --%>
        </div>
    </form>
</body>


<script>

	//검증파트
	var inval_Arr = new Array(6).fill(false);

	//정규식 파트
    var regExp_emp = /\s/g;                 //빈칸
    var regExp_id = /^[a-z0-9]{1,20}$/;  //a-z,0-9로 시작하는 1~20자리 아이디
    var regExp_pw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9])(?=.*[A-Z]).{8,20}$/ //대소문자 +숫자 8~20 패스 
    var regExp_email = /^[\w\-]+@(?:(?:[\w\-]{2,}\.)+[a-zA-Z]{2,})$/;
    var regExp_phone = /^(01[016789]{1})([0-9]{3,4})([0-9]{4})$/;
    var regExp_name = /^[가-힣]{2,6}$/;//한글 2~6자

    //아이디 정규식
    $('#userId').on('change keyup paste', (function () {
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
    }));

    //비밀번호 정규식
    $('#userPw').on('change keyup paste', (function () {
        //if (regExp_pw.test($(this).val())) {
        	if (1) {
            console.log('pw참참')
            $("#pw_check").text('');
        }
        else {
            //$('#pw_check').text('비밀번호를 확인해주세요');
            $('#pw_check').css('color', 'red');
            $('#pw_check').html('비밀번호를 확인해주세요<br><sub>( 알파벳 소,대문자 그리고 숫자, 특수기호가 각각 한글자이상 포함하여 8~20자 )</sub>');
            
        }

    }));

    //비밀번호 재확인
    $('#userPw2').on('change keyup paste', (function () {
        if ($('#userPw').val() != $(this).val())
        {
            $('#pw2_check').text('입력하신 비밀번호가 서로 다릅니다.');
            $('#pw2_check').css('color', 'red');
            inval_Arr[1] = false;
        }
        else {
            $('#pw2_check').text('');
            inval_Arr[1] = true;
        }
    }));
    
  //유저이름 정규식
    $('#userName').on('change keyup paste', (function () {
        if (regExp_name.test($(this).val())) {
            console.log('name참참');
            $("#name_check").text('');
            inval_Arr[2] = true;
        }
        else {
            $('#name_check').text('이름을 확인해주세요');
            $('#name_check').css('color', 'red');
            inval_Arr[2] = false;
        }
    }));

    //이메일 정규식
    $('#userEmail').on('change keyup paste', (function () {
        if (regExp_email.test($(this).val())) {
            console.log('pw참참')
            $("#email_check").text('');
            inval_Arr[3] = true;
        }
        else {
            $('#email_check').text('입력한 이메일 주소를 확인해주세요');
            $('#email_check').css('color', 'red');
            inval_Arr[3] = false;
        }
    }));

    //핸드폰 정규식
    $('#userPhone').on('change keyup paste', (function () {
        if (regExp_phone.test($(this).val())) {
            console.log('pw참참')
            $("#phone_check").text('');
            inval_Arr[4] = true;
        }
        else {
            $('#phone_check').text('입력한 핸드폰 번호를 확인해주세요');
            $('#phone_check').css('color', 'red');
            inval_Arr[4] = false;
        }
    }));
    
    //ajax 아이디 중복여부
    $('#userId').on('change keyup paste',(function(){
        if($('#userId').val()==""){//아이디 빈칸일때
           $('#id_check').html("");
        }else{
           $.ajax({
              url:'<%=request.getContextPath()%>/useridChk',
              type:"POST",
              data:{"inputId":$('#userId').val()},
              success:function(data){
                 if(data=="true"){
                    $('#id_check').html("사용가능한 아이디입니다.").css('color', 'green');
                    inval_Arr[5] = true;
                 }
                 else if(data=="false"){
                	 $('#id_check').html("사용 불가능한 아이디입니다.").css('color', 'red');
                	 inval_Arr[5] = false;
                 }
              },
              error:function(xhr,status){
                 alert(xhr+" : "+status);
      		}
  			
   		});
		}
	}));
    
    //전송 체크
    $('#reg_submit').click(function(){
    	var validAll = true;
		for(var i = 0; i < inval_Arr.length; i++){
			
			if(inval_Arr[i] == false){
				validAll = false;
				console.log(i+": false래");
			}
		}
		
		if(validAll){ // 유효성 모두 통과
			alert('감스타 가입을 환영합니다.');
			
		} else{
			alert('입력한 정보들을 다시 한번 확인해주세요 :)')
			
		}
	});    

</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>굄성 - UnprettyGamStar - Find User Information</title>
    <link href="https://fonts.googleapis.com/css?family=Jua|Song+Myung|Stylish|Yeon+Sung|Gothic+A1&amp;subset=korean" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/profileAlert.css">

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
    
    .findBtn {
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
    
    .find_button {
    margin-top : 10px;
    text-align: center;
    }
</style>

<body>
        <div id=mainDiv>
                <fieldset>
                    <legend>&nbsp; - UnprettyGamStar - &nbsp;</legend>
                    <h3>아이디 찾기/비밀번호 재설정</h3>
					<p>인증된 이메일만 정보 찾기가 가능합니다.</p>
                    
                    <div style="margin-bottom: 10px;">
                            <input type="radio" class="findSelector" id="search_1" name="search_total" onclick="search_check(1)" checked="checked">
                            <label for="search_1">아이디 찾기</label>
                            <input type="radio" class="findSelector" id="search_2" name="search_total" onclick="search_check(2)"> 
                            <label for="search_2">비밀번호 재설정</label>
                        </div>
                    
                        <form method="POST" >
                        <div id="searchI" style="">
                                <div class="findFrm">
                                    <label for="inputName_1">이름</label>
                                    <div>
                                        <input type="text" id="inputName_1" name="inputName_1" placeholder="  이름 입력 (한글 2-6자)">
                                        <div class="checkMsg" id="name_check"></div>
                                    </div>
                                </div>
            
                                <div class="findFrm">
                                    <label for="inputEmail_1">이메일</label>
                                    <div>
                                        <input type="email" id="inputEmail_1" name="inputEmail_1" placeholder=" 이메일을 입력하세요.">
                                        <div class="checkMsg" id="email_check"></div>
                                    </div>
                                </div>

                                <div class="find_button">
                                        <input type="reset" class="findBtn" onclick="pageBack();"value="취소" />&emsp;&emsp;
                                        <button type="button" class="findBtn" id="find_button" onclick="findId();" >아이디 찾기</button>
                                        <!-- <button type="button" class="enrollBtn" id="authChkBtn" name="authChkBtn" onclick="authChk();"  style="margin-left: 7px">인증하기</button> -->
                                       <!--  <input type="submit" class="findBtn" id="find_submit" value="아이디찾기" /> -->
                                </div>
                            </div>
                        </form>
            
            
            
            
                        <form method="POST" action="<%=request.getContextPath()%>/resetPassword">
                            <div id="searchP" style="display: none; ">
                                <!-- 본인확인 이메일 -->
                <div class="hideCntr">
                    <label for="userEmail" class="hideCntr">이메일</label><br>
                    <input type="text" class="hideCntr" name="userEmail" id="userEmail" placeholder="  E-mail"
                        autofocus size="30" required><button type="button" class="hideCntr" id="emailSendBtn" name="emailSendBtn" onclick="sendEmail();" disabled style="margin-left: 7px">인증 메일보내기</button>
                    <div class="checkMsg" id="email2_check"></div>
                </div>
                <!-- 이메일 인증하기 -->
                <div class="hideCntr2" id="userAuth" style="display:none;">
                    <label for="userAuth" class="hideCntr">이메일 : 인증번호</label><br>
                    <input type="text" class="hideCntr" name="userAuthCode" id="userAuthCode" placeholder="  인증번호를 입력하세요."
                        autofocus size="36" required> <button type="button" class="hideCntr" id="authChkBtn" name="authChkBtn" onclick="authChk();"  style="margin-left: 7px">인증하기</button>
                    <div class="checkMsg" id="auth_check"></div>
                </div>
                <!-- 비밀번호 -->
                <div class="userRegFrm" id="resetPwdiv1" style="display:none;">
                    <label for="userPw">새로운 비밀번호</label>
                    <sub></sub><br>
                    <input type="password" class="regControl" id="userPw" name="userPw" placeholder="  비밀번호 " maxlength='20'
                        autofocus size="49" required>
                    <div class="checkMsg" id="pw_check"></div>
                </div>
                <!-- 비밀번호 재확인 -->
                <div class="userRegFrm" id="resetPwdiv2" style="display:none;">
                    <label for="userPw2">새로운 비밀번호 확인</label><br>
                    <input type="password" class="regControl" id="userPw2" name="userPw2" placeholder="  비밀번호 확인"
                        maxlength='20' autofocus size="49" required>
                    <div class="checkMsg" id="pw2_check"></div>
                </div>
                
                                <div class="find_button">
                                <input type="reset" class="findBtn" onclick="pageBack();"value="취소" />&emsp;&emsp;
                                <input type="submit" class="findBtn" id="find_submit2" value="비밀번호 변경" />
                                </div>
						<input type="hidden" id="no" name="no" value=""/>
                            </div>
                        </form>
				</fieldset>
            </div>    
                        
</body>

<script>
//체크 버튼에 따라  기능이 변경
function search_check(num) {
		if (num == '1') {
            $('#searchP').css('display', 'none');
            $('#searchI').css('display', '');
			
        
		} else {
			$('#searchI').css('display', 'none');
            $('#searchP').css('display', '');
		}
	};
	
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
          html:alertMsg
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
	
	
	function findId(){
    	//console.log('아이디 찾기');
    	var name = $('#inputName_1').val();
    	var email = $('#inputEmail_1').val();
		if($('#inputName_1').val()==""){
			profileAlert("이름을 입력하세요.");
	        }else{
	          
	        $.ajax({
	              url:'<%=request.getContextPath()%>/findId?inputName_1='+name+'&inputEmail_1='+email,
	              type:"POST",
	   
	              success:function(data){
	            	  //console.log(typeof(data));
	            
	            	  var asd = 0.0;
	            	  
	            	  if(data == asd ){
	            		  profileAlert("일치하는<br>아이디가 없습니다.");
	            	  }
	            	  else{
	            	  profileAlert("아이디는<br>[ "+data+" ] 입니다.");
	            	  }                                                                                                                                                                                                                                                                                                                                                                                               
	              },
	              error:function(xhr,status){
	                 alert(xhr+" : "+status);
	      		}
	   		});
		}};
	
		
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
    		    	$('#resetPwdiv1').css("display", "block");
    		    	$('#resetPwdiv2').css("display", "block");
    		    	$('.hideCntr').css("display", "none");
    		    	//$('#userEmail').attr("type", "hidden");
					//inval_Arr[6] = true;
					}
				else{
					
					//console.log("값이 달라요");
					$('#auth_check').html("인증번호를 확인해 주세요.").css('color', 'red');
					//inval_Arr[6] = false;
				}
	
			};
			
			var userNo = 0;
			//이메일 중복확인+한글+@를뺀 특수문자 입력안되게
	        $('#userEmail').on('keyup',(function (event) {
	        	//console.log("들어ㅇ니?");
	        	reg =/^[\w\-]+@(?:(?:[\w\-]{2,}\.)+[a-zA-Z]{2,})$/;
	 
	            
	        	//if (reg.test($(this).val())) {
		           // $(this).val($(this).val().replace(reg, ''));
		            //$(this).focus();
	            //}
	            //else{
		        	if($('#userEmail').val()==""){
		        		$('#email_check').html("");
		        	}else{
		        		//console.log("아작아작");
		            	$.ajax({
		            		url:'<%=request.getContextPath()%>/findUserNo',
		            		type:"POST",
		            		data:{"chkEmail":$('#userEmail').val()},
		            		success:function(data){
		            			//console.log(data);
		            			if(data=='false'){
		            				//console.log("없냐?");
		            				$('#email2_check').html("입력한 이메일 주소를 확인해주세요.").css('color', 'red');		                            
		                            $('#emailSendBtn').attr('disabled', true);
		                            $('#no').val("");
		            			}
		            			else{
		            				//console.log("있냐??");
		            				$('#email2_check').html("이메일 인증이 가능합니다.").css('color', 'green');
		            	            $('#emailSendBtn').attr('disabled', false);
		            	            $('#no').val(Number(data));
		            	            
		            	            //console.log(Number(data));
		            	            //console.log($('#no').val());
		            	            
		            			}
		            		},
		            		error:function(xhr,status){
		            			alert(xhr+" : "+status);
		            		}
		            	});
		        	}
	            //}
	            
	        }));
			
			
	        var inval_Arr = new Array(2).fill(false);
			//정규식패턴
			var chk1=0;
			var chk2=0;
			var regExp_name = /^[가-힣]{2,6}$/;//한글 2~6자
			var regExp_email = /^[\w\-]+@(?:(?:[\w\-]{2,}\.)+[a-zA-Z]{2,})$/;
			var regExp_pw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9])(?=.*[A-Z]).{8,20}$/ //대소문자 +숫자 8~20 패스
			
			 $('#inputName_1').on('change keyup paste', (function () {
			        if (regExp_name.test($(this).val())) {
			            //console.log('name참참');
			            $("#name_check").text('');
			            chk1=1;
			            if(chk1==chk2){
			            $('#find_button').attr('disabled', false);
			            }
			        }
			        else {
			        	chk1=0;
			            $('#name_check').text('이름을 확인해주세요');
			            $('#name_check').css('color', 'red');
			            $('#find_button').attr('disabled', true);
			            
			        }
			    }));
			
			 
			 //이메일 정규식
			    $('#inputEmail_1').on('change keyup paste', (function (e) {
			        if (regExp_email.test($(this).val())) {
			            //console.log('email참참')
			            $("#email_check").text('');
			            chk2=1;
			            if(chk1==chk2){
			            $('#find_button').attr('disabled', false);
			            }
			        }
			        else {
			        	chk2=0;
			            $('#email_check').text('입력한 이메일 주소를 확인해주세요');
			            $('#email_check').css('color', 'red');
			            //$('#userAuth').css("display", "none");
			        	$('#find_button').attr('disabled', true);      	
			           
			        }
			    }));
			 
			
			 
			 
			  //이메일 정규식
			    $('#userEmail').on('change keyup paste', (function (e) {
			        if (regExp_email.test($(this).val())) {
			            //console.log('email참참')
			            //$("#email2_check").text('');
			            //$('#emailSendBtn').attr('disabled', false);
			            inval_Arr[0] = true;
			        }
			        else {
			        	
			            //$('#email2_check').text('입력한 이메일 주소를 확인해주세요');
			            $('#email2_check').css('color', 'red');
			            $('#userAuth').css("display", "none");
			        	$('#emailSendBtn').attr('disabled', true);      	
			            inval_Arr[0] = false;
			        }
			    }));
			 
			 
			 
			    //전송 체크
			    $('#find_submit2').click(function(){
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
			            inval_Arr[1] = false;
			        }
			        else {
			            $('#pw2_check').text('');
			            inval_Arr[1] = true;
			        }
			    }));
			    
			    function pageBack(){
			    	self.close();
			    };
			    
			 
	
</script>
</html>
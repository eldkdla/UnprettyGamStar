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
                    <h3>아이디/비밀번호 찾기</h3>
					<p>인증된 이메일만 정보 찾기가 가능! 가느응!!!</p>
                    
                    <div style="margin-bottom: 10px; border: 1px solid turquoise">
                            <input type="radio" class="findSelector" id="search_1" name="search_total" onclick="search_check(1)" checked="checked">
                            <label for="search_1">아이디 찾기</label>
                            <input type="radio" class="findSelector" id="search_2" name="search_total" onclick="search_check(2)"> 
                            <label for="search_2">비밀번호 찾기</label>
                        </div>
                    
                        <form method="POST" >
                        <div id="searchI" style="border : 1px solid red">
                                <div class="findFrm">
                                    <label for="inputName_1">이름</label>
                                    <div>
                                        <input type="text" id="inputName_1" name="inputName_1" placeholder="  이름 입력">
                                    </div>
                                </div>
            
                                <div class="findFrm">
                                    <label for="inputEmail_1">이메일</label>
                                    <div>
                                        <input type="email" id="inputEmail_1" name="inputEmail_1" placeholder=" 이메일을 입력하세요.">
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
            
            
            
            
                        <form method="POST" action="##">
                            <div id="searchP" style="display: none; border: 1px dotted blue">
                                <!-- 본인확인 이메일 -->
                <div class="hideCntr">
                    <label for="userEmail" class="hideCntr">이메일</label><br>
                    <input type="text" class="hideCntr" name="userEmail" id="userEmail" placeholder="  E-mail"
                        autofocus size="30" required><button type="button" class="hideCntr" id="emailSendBtn" name="emailSendBtn" onclick="sendEmail();"  style="margin-left: 7px">인증 메일보내기</button>
                    <div class="checkMsg" id="email_check"></div>
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
                                <input type="submit" class="findBtn" id="find_submit" value="비밀번호 변경" />
                                </div>
						<input type="hidden" id="no" name="no" value=""/>
                            </div>
                        </form>
				</fieldset>
            </div>    
                        
</body>

<script>
//체크 버튼에 따라 아이디/비밀번호 기능이 달라진다
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
    	console.log('아이디 찾기');
    	var name = $('#inputName_1').val();
    	var email = $('#inputEmail_1').val();
		if($('#inputName_1').val()==""){//
			profileAlert("이름을 입력하세요.");
	        }else{
	          
	        $.ajax({
	              url:'<%=request.getContextPath()%>/findId?inputName_1='+name+'&inputEmail_1='+email,
	              type:"POST",
	   
	              success:function(data){
	            	  console.log(typeof(data));
	            
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
	    	console.log('이메일전송옹');
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
					console.log("둘이 같아요");
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
					
					console.log("값이 달라요");
					$('#auth_check').html("인증번호를 확인해 주세요.").css('color', 'red');
					//inval_Arr[6] = false;
				}
	
			};
			
			var userNo = 0;
			//이메일 중복확인+한글+@를뺀 특수문자 입력안되게
	        $('#userEmail').on('keyup',(function (event) {
	        	console.log("들어ㅇ니?");
	        	reg =/^[\w\-]+@(?:(?:[\w\-]{2,}\.)+[a-zA-Z]{2,})$/;
	 
	            
	        	//if (reg.test($(this).val())) {
		           // $(this).val($(this).val().replace(reg, ''));
		            //$(this).focus();
	            //}
	            //else{
		        	if($('#userEmail').val()==""){
		        		$('#email_check').html("");
		        	}else{
		        		console.log("아작아작");
		            	$.ajax({
		            		url:'<%=request.getContextPath()%>/findUserNo',
		            		type:"POST",
		            		data:{"chkEmail":$('#userEmail').val()},
		            		success:function(data){
		            			console.log(data);
		            			if(data=='false'){
		            				console.log("없냐?");
		            				$('#email_check').html("없어요").css('color', 'red');		                            
		                            $('#emailSendBtn').attr('disabled', true);
		                            $('#no').val("");
		            			}
		            			else{
		            				console.log("있냐??");
		            				$('#email_check').html("있어요").css('color', 'green');
		            	            $('#emailSendBtn').attr('disabled', false);
		            	            $('#no').val(data);
		            	            
		            			}
		            		},
		            		error:function(xhr,status){
		            			alert(xhr+" : "+status);
		            		}
		            	});
		        	}
	            //}
	            
	        }));
	
</script>
</html>
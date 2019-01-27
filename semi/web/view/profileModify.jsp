<%@page import="com.gamstar.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 정보 수정창</title>

	<script src="http://code.jquery.com/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Jua|Song+Myung|Stylish|Yeon+Sung|Gothic+A1&amp;subset=korean"
        rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/alertBox.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/alertBox.js"></script>

</head>
<body>

<% User user=(User)request.getAttribute("userData");%>

		<div class="fullScreen">
            <div id='modifyScreenMenu'>
                <ul id='modifyScreenMenuUl'>
                    <li class='modifyScreenMenuLi'>
                        <input type="radio" name="modifyScreenMenuRbt" id="modifyScreenMenuRbt1" checked>
                        <label for="modifyScreenMenuRbt1" class="modifyScreenMenuLb"><br>프로필 편집</label>
                    </li>
                    <li class='modifyScreenMenuLi'>
                        <input type="radio" name="modifyScreenMenuRbt" id="modifyScreenMenuRbt2">
                        <label for="modifyScreenMenuRbt2" class="modifyScreenMenuLb"><br>비밀번호 변경</label>
                    </li>
                    <li class='modifyScreenMenuLi'>
                        <input type="radio" name="modifyScreenMenuRbt" id="modifyScreenMenuRbt3">
                        <label for="modifyScreenMenuRbt3" class="modifyScreenMenuLb"><br>회원 탈퇴</label>
                    </li>
                </ul>
            </div>

            <div id="modifyScreenMain">

                <form method='POST' action="<%=request.getContextPath()%>/view/modifyEnd" id="form1" enctype="multipart/form-data">
                    <div class="modifyScreenMainTop">
                        <div class="modifyScreenMainPhoto">
                            <button id='modifyScreenMainPhotoBt' onclick="return changePhoto();"><img id='modifyScreenMainPhoto'
                                    src="<%=request.getContextPath()%>/<%=user.getProfilePhoto()%>" alt="사진이 안나와요ㅠㅜ"></button>
                        </div>
                        <div class="modifyScreenMainContent">
                            <label id="modifyScreenIdLb"><%=user.getName() %></label><br>
                            <label id="modifyScreenPhotoLb" onclick="changePhoto();">프로필사진 변경</label>
                        </div>
                    </div>

                    <div class="modifyScreenMainTop">
                        <div class="modifyInputDiv">
                            <label class='modifyInputLb'>이름</label>
                            <input type="text" class="modifyInput" id=modifyName name="name" value='<%=user.getName() %>' minlength=2 maxlength=6 placeholder=" 이름 입력 (한글 2-6자)" required />
                            <div class="chkEmailPhonePw" id="chkName"></div>
                        </div>
                        <div class="modifyInputDiv">
                            <label class='modifyInputLb'>이메일</label>
                            <input type="email" class="modifyInput" id='modifyEmail' name="email" maxlength="50" value="<%=user.getEmail() %>" style="ime-mode:disabled;background-color:rgb(207,207,207);outline:none;border:none;" readonly="readonly" required />
                            <!-- <div class="chkEmailPhonePw" id="chkEmail"></div> -->
                        </div>
                        <div class="modifyInputDiv">
                            <label class='modifyInputLb'>전화번호</label>
                            <input type="text" class="modifyInput" id='modifyPhone' name="phone" minlength="10" maxlength="11" value="<%=user.getPhone()%>" placeholder=" '-' 없이 번호만 입력해주세요" style='ime-mode:disabled;' required/>
                             <div class="chkEmailPhonePw" id="chkPhone"></div>
                        </div>
                        <div class="modifyInputDiv">
                            <label class='modifyInputLb'>성별</label>
                            <select name="gender" id="gender" required>
                                <option value=10 hidden disabled="disabled" >성별</option>
                                <option value="M" <%=user.getGender().equals("M")?"selected":""%>>남자</option>
                                <option value="W" <%=user.getGender().equals("W")?"selected":""%>>여자</option>                      
                            </select>
                        </div>
                        <div class="modifyInputDiv">
                            <label class='modifyInputLb'>비공개</label>
                            <select name="disclosure" id="disclosure" required>
                                <option value=10 hidden disabled="disabled" >공개여부</option>
                                <option value="1"  <%=user.getDisclosure()==1?"selected":""%> >공개</option>
                                <option value="0"  <%=user.getDisclosure()==0?"selected":""%> >비공개</option>                      
                            </select>
                        </div>
                        
                        <input type="file" name="uploadPhoto" id="uploadPhoto" accept="image/*" this.select();>
                        <br>

                        <input type="submit" id='modifyButton' value="변 경" disabled='true' >

                    </div>
                </form>

                <form method='POST' action="<%=request.getContextPath()%>/view/passwordModify" id="form2">

                    <div class="modifyScreenMainTop">
                        <div class="modifyInputDiv2">
                            <label class='modifyInputLb2'>이전 비밀번호</label>
                            <input type="password" class="modifyInput" name="beforePw" id="beforePw"  minlength="8" maxlength="20" required />
                            <div class="chkEmailPhonePw" id="chkBeforePw"></div>
                        </div>
                        <div class="modifyInputDiv2">
                            <label class='modifyInputLb2'>새 비밀번호</label>
                            <input type="password" class="modifyInput" name="newPw" id="newPw" minlength="8" maxlength="20" placeholder=' 대소문자 +숫자+특수문자 8~20자 입력해주세요' required />
                        	<div class="chkEmailPhonePw" id="chkConditionPw"></div>
                        </div>
                        <div class="modifyInputDiv2">
                            <label class='modifyInputLb2'>새 비밀번호 확인</label>
                            <input type="password" class="modifyInput" name="newPwchk" id="newPwchk" minlength="8" maxlength="20" placeholder=' 대소문자 +숫자+특수문자 8~20자 입력해주세요' required>
                            <div class="chkEmailPhonePw" id="chkPwDiv"></div>
                        </div>

                        <br>

                        <input type="button" id='psModifyBt' value="비밀번호 변경" disabled='true'  />


                    </div>
                </form>
                
                <form method='POST' action="<%=request.getContextPath()%>/view/unregister" id="form3">

                    <div class="modifyScreenMainTop">
                        <div class="modifyInputDiv3">
                            <label class='modifyInputLb3'>비밀번호 확인</label>
                            <input type="password" class="modifyInput" name="chkPw" id="chkPw" minlength="8" maxlength="15" required />
                            <div class="chkEmailPhonePw" id="chkPwDv"></div>
                        </div>
                        
                        <br>
                        
                        <button type="button" id='unregister' disabled='true'>회원탈퇴</button>

                    </div>
                </form>

            </div>
        </div>

    <script>
    
        //모바일인지 웹인지 확인해서 css 적용
        $(document).ready(function(){
        var filter = "win16|win32|win64|mac|macintel"; 
        
        if ( navigator.platform ) {
            if ( filter.indexOf( navigator.platform.toLowerCase() ) < 0 ) {
                  //모바일접속
                $('head').append($('<link/>',{
                    rel:'stylesheet',
                    type:'text/css',
                    href:'<%=request.getContextPath()%>/css/profilePageModifyMobile.css'
                }));
            } 
            else {
                  //PC접속
                $('head').append($('<link/>',{
                    rel:'stylesheet',
                    type:'text/css',
                    href:'<%=request.getContextPath()%>/css/profilePageModify.css'
                }));
            } 
        }
        
        <%-- <%if(user.getLinkType()!=0){%> //네이버같은 연동로그인이면 이메일 변경 막기
			$('#modifyEmail').attr('readonly',true);
			$('#modifyEmail').css({"background-color":"rgb(207,207,207)","outline":"none","border":"none"});
			$('#modifyEmail').off('keyup');
			$('#chkEmail').css('display','none');
		<%}%> --%>

        });
    </script>
    
    <script>
        //프로필변경,비밀번호변경 메뉴선택에 따른 컨텐츠 보이게하기
        $('#modifyScreenMenuRbt1').click(function () {
            $('#form1').css('display', 'block');
            $('#form2').css('display', 'none');
            $('#form3').css('display', 'none');
        });
        $('#modifyScreenMenuRbt2').click(function () {
            $('#form1').css('display', 'none');
            $('#form2').css('display', 'block');
            $('#form3').css('display', 'none');
        });
        $('#modifyScreenMenuRbt3').click(function () {
            $('#form1').css('display', 'none');
            $('#form2').css('display', 'none');
            $('#form3').css('display', 'block');
        });

    </script>

    <script>
        //프로필사진 바꾸기
        function changePhoto() {
            $('#uploadPhoto').click();  //프로필사진 클릭시 숨겨둔 input file 실행  
            return false;
        }

        function readURL(input) { //들어온파일 가져와서 e.taget.result로 이미지 받아서 #modifyScreenMainPhoto 사진속성 바꿔주기
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    console.log(e.target.result);
                    $('#modifyScreenMainPhoto').attr('src', e.target.result);
                }
                console.log(input.files[0].name);
                reader.readAsDataURL(input.files[0]); //url읽어드린다
	
            }
        }

        $('#uploadPhoto').change(function () { //숨겨둔 input file에서 그림 선택하면 실행되는 함수 
            console.log(this.files[0]);
            readURL(this);
        });
    </script>

    <script>
    	//이름입력시 변경 활성화
    	$('#modifyName').on('keyup',(function(){
    		reg = /([^가-힣ㄱ-ㅎㅏ-ㅣ\x20])/i;
    		
    		if(reg.test($(this).val())){
    			$(this).val($(this).val().replace(reg,''));
    			$(this).focus();
    		}
    		else{
	    		if($('#modifyName').val()!=""){
	    			$('#chkName').html("이름 적합").css('color', 'green');
	    			if($('#modifyPhone').val()!=""&&($('#chkPhone').html()==""||$('#chkPhone').html()=="전화번호 가능")){
		    			$('#modifyButton').css('background-color', 'cornflowerblue');
		            	$('#modifyButton').removeProp('disabled');
	    			}
	    		}
	    		else{
	    			$('#chkName').html("");
	    			$('#modifyButton').css('background-color', 'gray');
	                $('#modifyButton').prop('disabled', 'true');
	    		}
    		}
    		
    	}));
    	
        //이메일 중복확인+한글+@를뺀 특수문자 입력안되게
        <%-- $('#modifyEmail').on('keyup',(function (event) {
        	reg =/[^a-zA-Z0-9|@|.]/gi;
 
            if (reg.test($(this).val())) {
	            $(this).val($(this).val().replace(reg, ''));
	            $(this).focus();
            }
            else{
	        	if($('#modifyEmail').val()==""){
	        		$('#chkEmail').html("");
	        	}else{
	            	$.ajax({
	            		url:'<%=request.getContextPath()%>/view/chkPhone',
	            		type:"POST",
	            		data:{"chkEmail":$('#modifyEmail').val()},
	            		success:function(data){
	            			if(data=="true"){
	            				$('#chkEmail').html("이메일 중복").css('color', 'red');
	            				$('#modifyButton').css('background-color', 'gray');
	                            $('#modifyButton').prop('disabled', 'true');
	            			}
	            			else if(data=="false"){
	            				$('#chkEmail').html("이메일 가능").css('color', 'green');
	            				if($('#chkPhone').html()=="전화번호 가능"||$('#chkPhone').html()==""){
	            					$('#modifyButton').css('background-color', 'cornflowerblue');
	            	                $('#modifyButton').removeProp('disabled');
	            				}
	            			}
	            		},
	            		error:function(xhr,status){
	            			alert(xhr+" : "+status);
	            		}
	            	});
	        	}
            }
            
        })); --%>
        
     
        //전화번호 숫자체크+중복확인
        $("#modifyPhone").on('keyup',(function(e) {
        	reg = /[^0-9]/gi;
            v = $(this).val();
                  
            if (reg.test(v)) {
	            $(this).val(v.replace(reg, ''));
	            $(this).focus();
            }
            else{
	            if($('#modifyPhone').val()==""){
	        		$('#chkPhone').html("");
	        	}
	            else{
	                $.ajax({
	                	url:'<%=request.getContextPath()%>/view/chkPhone',
	                	type:"POST",
	                	data:{"chkPhone":$('#modifyPhone').val()},
	                	success:function(data){
	                		if(data=="true"){
	                			$('#chkPhone').html("전화번호 중복").css('color', 'red');
	                			$('#modifyButton').css('background-color', 'gray');
	                            $('#modifyButton').prop('disabled', 'true');
	                		}
	                		else if(data=="false"){
	                			$('#chkPhone').html("전화번호 가능").css('color', 'green');
	                			/* if($('#chkEmail').html()=="이메일 가능"||$('#chkEmail').html()==""){ */
	                				if($('#modifyName').val()!=""&&($('#chkName').html()==""||$('#chkName').html("이름 적합"))){
		                				$('#modifyButton').css('background-color', 'cornflowerblue');
		            	                $('#modifyButton').removeProp('disabled');
	                				}
	                			/* } */
	                		}
	                	},
	                	error:function(xhr,status){
	                		alert(xhr+" : "+status);
	                	}
	                });	
	            }
            }
            
        }));


        //성별 바뀌면 버튼 활성화
        $('#gender').change(function () {
            if ($('#gender>option:selected').val() != 10) {
            	if(($('#modifyName').val()!=""&&$('#modifyName').val()!="")&&(( $('#chkName').html()=="이름 적합" &&  $('#chkPhone').html()=="전화번호 가능")||( $('#chkName').html()=="" &&  $('#chkPhone').html()=="")||($('#chkName').html()=="" &&  $('#chkPhone').html()=="전화번호 가능")||($('#chkName').html()=="이름 적합" &&  $('#chkPhone').html()==""))){
			        $('#modifyButton').css('background-color', 'cornflowerblue');
			        $('#modifyButton').removeProp('disabled');
            	}
            }
            else {
                $('#modifyButton').css('background-color', 'gray');
                $('#modifyButton').prop('disabled', 'true');
            }
        });
        
        //공개 비공개 변경시 버튼 활성화
        $('#disclosure').change(function (){
        	if($('#disclosure>option:selected').val() !=10){
        		if(($('#modifyName').val()!=""&&$('#modifyName').val()!="")&&(( $('#chkName').html()=="이름 적합" &&  $('#chkPhone').html()=="전화번호 가능")||( $('#chkName').html()=="" &&  $('#chkPhone').html()=="")||($('#chkName').html()=="" &&  $('#chkPhone').html()=="전화번호 가능")||($('#chkName').html()=="이름 적합" &&  $('#chkPhone').html()==""))){
			        $('#modifyButton').css('background-color', 'cornflowerblue');
			        $('#modifyButton').removeProp('disabled');
            	}
        	}
        	else{
        		 $('#modifyButton').css('background-color', 'gray');
                 $('#modifyButton').prop('disabled', 'true');
        	}
        });
        
    </script>

    <script>
    	//이전비밀번호 맞는지 알려주기
    	$('#beforePw').focusout(function(){
    		if($('#beforePw').val()==""){
    			$('#chkBeforePw').html("");
    		}else{
	    		$.ajax({
	    			url:'<%=request.getContextPath()%>/view/chkBeforePw',
	    			type:"POST",
	    			data:{"beforePw":$('#beforePw').val()},
	    			success:function(data){
	    				if(data=="true"){
	    					$('#chkBeforePw').html("비밀번호 일치").css('color', 'green');
	    					if($('#chkPwDiv').html()=="비밀번호 일치"&&$('#chkConditionPw').html()=="비밀번호 사용가능"){
	    						$('#psModifyBt').css('background-color', 'cornflowerblue');                        
	                        	$('#psModifyBt').removeProp('disabled');
	    					}
	    					else{
	    						$('#psModifyBt').css('background-color', 'gray');
	                            $('#psModifyBt').prop('disabled', 'true');
	    					}
	    				}
	    				else if(data=="false"){
	    					$('#chkBeforePw').html("비밀번호 불일치").css('color', 'red');
	    					$('#psModifyBt').css('background-color', 'gray');
                            $('#psModifyBt').prop('disabled', 'true');
	    				}
	    				
	    			},
	    			error:function(xhr,status){
	    				alert(xhr+" : "+status);
	    			}
	    			
	    		});
    		}
    	});
			//새로운 비밀번호 체크 
            $('#newPwchk,#newPw').on('keyup',(function () {
					
                if ($('#newPwchk').val() != '' && $('#newPw').val() != '') {

                    if ($('#newPw').val() != $('#newPwchk').val()) {
                        $('#chkPwDiv').html("비밀번호 불일치").css('color', 'red');
                        $('#psModifyBt').css('background-color', 'gray');
                        $('#psModifyBt').prop('disabled', 'true');
                    }
                    else {
                        $('#chkPwDiv').html("비밀번호 일치").css('color', 'green');
                        if($('#chkBeforePw').html()=="비밀번호 일치"&&$('#chkConditionPw').html()=="비밀번호 사용가능"){
                       		$('#psModifyBt').css('background-color', 'cornflowerblue');                        
                        	$('#psModifyBt').removeProp('disabled');
                        }
                        else{
                        	$('#psModifyBt').css('background-color', 'gray');
                            $('#psModifyBt').prop('disabled', 'true');
                        }
                    }
                }
                else {
                    $('#chkPwDiv').html("");
                    $('#psModifyBt').css('background-color', 'gray');
                    $('#psModifyBt').prop('disabled', 'true');
                }
            }));
			
            $('#newPw').on('keyup',(function () {
            	reg=/^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9])(?=.*[A-Z]).{8,20}$/ //대소문자 +숫자 8~20 패스;
					
            		if ($('#newPw').val() == '') {
            			$('#chkConditionPw').html("");
            		}
            		else if (reg.test($(this).val())) {
    			         $('#chkConditionPw').html("비밀번호 사용가능").css('color','green');
    			    }
    			    else {
    			    	$('#chkConditionPw').html("비밀번호 부적합").css('color','red');
    			    }		
            }));
            
            $('#psModifyBt').click(function(){
        		$('body').alertBox({
    			        title: '비밀번호 변경 하시겠습니까?',
    			        lTxt: '아니요',
    			        lCallback: function(){},
    			        rTxt: '네',
    			        rCallback: function(){
    						 $('#form2').submit(); 
    			        }
    			      });
        	});
        
      //회원탈퇴시 비밀번호 맞는지 알려주기
    	$('#chkPw').on('keyup',(function(e){
    		if(e.key=='Enter'){
    			e.preventDefault();
    		}
    		if($('#chkPw').val()==""){
    			$('#chkPwDv').html("");
    		}else{
    			console.log("eee");
	    		$.ajax({
	    			url:'<%=request.getContextPath()%>/view/chkBeforePw',
	    			type:"POST",
	    			data:{"beforePw":$('#chkPw').val()},
	    			success:function(data){
	    				console.log("파방");
	    				if(data=="true"){
	    					$('#chkPwDv').html("비밀번호 일치").css('color', 'green');
	    					$('#unregister').css('background-color', 'cornflowerblue');                        
	                        $('#unregister').removeProp('disabled');
	    				}
	    				else if(data=="false"){
	    					$('#chkPwDv').html("비밀번호 불일치").css('color', 'red');
	    					$('#unregister').css('background-color', 'gray');
                            $('#unregister').prop('disabled', 'true');
	    				}
	    				
	    			},
	    			error:function(xhr,status){
	    				alert(xhr+" : "+status);
	    			}
	    			
	    		});
    		}
    	}));
      
    	$('#unregister').click(function(){
    		$('body').alertBox({
			        title: '회원탈퇴 하시겠습니까?',
			        lTxt: '아니요',
			        lCallback: function(){},
			        rTxt: '네',
			        rCallback: function(){
						 $('#form3').submit();
			        }
			      });
    	});

    </script>



</body>
</html>
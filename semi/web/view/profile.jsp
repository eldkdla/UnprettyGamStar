<%@page import="com.gamstar.newspeed.model.vo.NewspeedMedia"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.gamstar.user.model.vo.User" %>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사람정보창</title>

	<!-- <script src="http://code.jquery.com/jquery.min.js"></script>  -->
	<script src="../js/jquery-3.3.1.js"></script>
	
    <link href="https://fonts.googleapis.com/css?family=Jua|Song+Myung|Stylish|Yeon+Sung|Gothic+A1&amp;subset=korean"
        rel="stylesheet">
       
	<link href="<%=request.getContextPath()%>/css/alertBox.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/profileAlert.css">
	<link href="<%=request.getContextPath()%>/css/newspeedwrite.css" rel="stylesheet" type="Text/css">
	<link href="<%=request.getContextPath()%>/css/newspeedDetailView.css" rel="stylesheet" type="Text/css">
	<link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/alertBox.js"></script>
	<script src="../js/newspeedwrite.js"></script>
	<script src="../js/newspeedRequest.js"></script>
	<script src="../js/report.js"></script>
</head>
<body>
	
	<% User user=(User)request.getAttribute("userData");
	   NewspeedMedia userStory=(NewspeedMedia)request.getAttribute("userStory");
	   ArrayList<User> followerDataArray=(ArrayList<User>)request.getAttribute("followerDataArray");
	   ArrayList<User> followDataArray=(ArrayList<User>)request.getAttribute("followDataArray");
	   ArrayList<User> blockDataArray=(ArrayList<User>)request.getAttribute("blockDataArray");
	   ArrayList<User> requestfollowDataArray=(ArrayList<User>)request.getAttribute("requestfollowDataArray");
	   ArrayList<NewspeedMedia> content1DataArray=(ArrayList<NewspeedMedia>)request.getAttribute("content1DataArray");
	   ArrayList<NewspeedMedia> storageContentDataArray=(ArrayList<NewspeedMedia>)request.getAttribute("storageContentDataArray");
	   ArrayList<NewspeedMedia> tagContentDataArray=(ArrayList<NewspeedMedia>)request.getAttribute("tagContentDataArray");
	   ArrayList<User> isRequestFollowDataArray=(ArrayList<User>)request.getAttribute("isRequestFollowDataArray");
	   boolean isFollowed=(boolean)request.getAttribute("isFollowed");
	   boolean isRequestFollow=(boolean)request.getAttribute("isRequestFollow");
	%>
	
	<canvas class="dummy_canvas" style="display:none;" id="original_size_canvas"></canvas>
	<canvas class="dummy_canvas" style="display:none;" id="normal"></canvas>
	<canvas class="dummy_canvas" style="display:none;"id="grayscale"></canvas>
	<canvas class="dummy_canvas" style="display:none;"id="brightness"></canvas>
	
	<div id="newspeedview_btn_wrapper">

        <div id="container-left-btnnnn"></div>
        <div id="newspeedview_container">

            <div id="newspeedview_list_wrapper">
                <div class="newspeedview_list_select">
                    <input type="hidden" class="newspeedNo" value="1">

                    <div class="newspeedview_media_list_wrapper">
                        <div class="medialist_left_btn"></div>

                        <div class="newspeedview_media_list">
                            <div class="newspeedview_media_select">
                                <div class="newspeedview_media_content">
                                    <div class="balloons">임태완
                                        <input type="hidden" class="userNo" value="01">
                                    </div>
                                </div>
                            </div>
                            <div class="newspeedview_media">
                                <div class="newspeedview_media_content">
                                    <div class="balloons">임태완
                                        <input type="hidden" class="tagUserNo" value="01">
                                    </div>
                                </div>
                            </div>
                            <div class="newspeedview_media">
                                <div class="newspeedview_media_content">
                                    <div class="balloons">임태완
                                        <input type="hidden" class="tagUserNo" value="01">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="medialist_right_btn"></div>

                    </div>



                    <div class="newspeedview_all_content_wrapper">
                        <div class="newspeedview_writer_wrapper">
                            <div class="newspeedview_writer_content_wrapper">
                                <div class="newspeedview_profilephoto">
                                    <input type="hidden" class="userNo" value="1">
                                </div>
                                <div class="newspeedview_writername">
                                    <h4>임태완</h4>
                                    <input type="hidden" class="userNo" value="1">
                                </div>
                            </div>
                        </div>

                        <hr class="newspeed_content_hr">

                        <div class="newspeedview_content_wrapper">
                            <div>
                                <div class="newspeed_username">임태완</div>
                                오늘 미세먼지가 짱 많대요. 다들 마스크 쓰시고 감기 조심하세요. 미세먼지 언제 없어지려나 ㅠㅠ 다들 사랑해요!!
                                <input type="hidden" class="userNo" value="1">
                            </div>
                        </div>

                        <hr class="newspeed_content_hr">

                        <div class="newspeedview_comment_wrapper">
                            <div>
                                <div class="newspeed_comment_user">임태완</div>
                                오늘 미세먼지가 짱 많대요. 다들 마스크 쓰시고 감기 조심하세요. 미세먼지 언제 없어지려나 ㅠㅠ 다들 사랑해요!!
                                <input type="hidden" class="userNo" value="1">
                            </div>
                            <div>
                                <h4>임태완</h4>
                                오늘 미세먼지가 짱 많대요. 다들 마스크 쓰시고 감기 조심하세요. 미세먼지 언제 없어지려나 ㅠㅠ 다들 사랑해요!!
                                ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ
                                <input type="hidden" class="userNo" value="1">
                            </div>
                            <div>
                                <h4>임태완</h4>
                                오늘 미세먼지가 짱 많대요. 다들 마스크 쓰시고 감기 조심하세요. 미세먼지 언제 없어지려나 ㅠㅠ 다들 사랑해요!!
                                ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ
                                <input type="hidden" class="userNo" value="1">
                            </div>
                            <div>
                                <h4>임태완</h4>
                                오늘 미세먼지가 짱 많대요. 다들 마스크 쓰시고 감기 조심하세요. 미세먼지 언제 없어지려나 ㅠㅠ 다들 사랑해요!!
                                ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ
                                <input type="hidden" class="userNo" value="1">
                            </div>

                        </div>


                        <div class="newspeedview_icon_wrapper">
                            <div class="newspeed_like_icon_wrapper">

                            </div>
                            <div class="newspeed_store_icon_wrapper">

                            </div>

                        </div>
                        <hr class="newspeed_content_hr">
                        <div class="newspeedview_commentwrite_wrapper">
                            <input type="text" class="newspeedview_comment_write">
                        </div>
                    </div>

                </div>

                <div class="newspeedview_list">
                    <div class="newspeedview_media_list_wrapper">
                        <input type="button" value="방가워" class="medialist_left_btn">
                        <div class="newspeedview_media_list">
                            <div class="newspeedview_media_select">
                                반가워
                            </div>
                            <div class="newspeedview_media">
                                나도
                            </div>
                            <div class="newspeedview_media">
                                응 ㅇㅅㅇ
                            </div>
                        </div>
                        <input type="button" value="방가워" class="medialist_right_btn">
                    </div>
                </div>

                <div class="newspeedview_list">
                    <div class="newspeedview_media_list_wrapper">
                        <input type="button" value="방가워" class="medialist_left_btn">
                        <div class="newspeedview_media_list">
                            <div class="newspeedview_media_select">
                                반가워
                            </div>
                            <div class="newspeedview_media">
                                나도
                            </div>
                            <div class="newspeedview_media">
                                응 ㅇㅅㅇ
                            </div>
                        </div>
                        <input type="button" value="방가워" class="medialist_right_btn">
                    </div>

                </div>
                <div class="newspeedview_list">
                    <p>3</p>

                </div>

                <div class="newspeedview_list">
                    <p>3</p>

                </div>

                <div class="newspeedview_list">
                    <p>3</p>

                </div>
            </div>

        </div>
        <div id="container-right-btnnnn"></div>
    </div>

	 <div class='fullScreen'>
        <div class="profileTop">
           <div id='BackgroundPhotoIconDv' onclick="changeBackgroundPhoto();"><img src='<%=request.getContextPath()%>/img/camera20.png'><label>배경 사진 업데이트</label></div>
           <img src="<%=request.getContextPath()%>/<%=user.getProfileBackgroundPhoto() %>" alt="" id="profileBackgroundPhoto" onclick="changeBackgroundPhoto();">
            <div class="profileTopPhoto">
                <button id='profilePhotoBt'>
                    <div id="profilePhotoHover"><img src="<%=request.getContextPath()%>/img/camera20.png" alt="" ><label >업데이트</label></div>
                    <img id='profilePhoto' src="<%=request.getContextPath()%>/<%=user.getProfilePhoto()%>" alt="사진이 안나와요ㅠㅜ" >
                </button>
            </div>
            <div class="profileTopContent">
                <label id="profileName" onclick="location.href='profile?uu=<%=user.getNo()%>'"><%=user.getName()%></label>
                <button class="profileModify" id='profileModify' onclick="location.href='<%=request.getContextPath()%>/view/profilemodifyStart'"><img src="<%=request.getContextPath()%>/img/modify.png"><label for="profileModify">프로필 편집</label></button>
                <button class="profileModify" id='profileWrite' onclick="showPostingWhole();"><img src="<%=request.getContextPath()%>/img/write.png"><label for="profileWrite">글 작성하기</label></button>
                <button class="profileModify" id='profileBlockBt' onclick="blockClick();"><img src="<%=request.getContextPath()%>/img/blockBtOff.png"><label for="profileBlockBt">차단하기</label></button>
				<button class="profileModify" id='profileFollowBt' onclick="followClick();"><img src="<%=request.getContextPath()%>/img/followOff.png"><label for="profileFollowBt">팔로우</label></button>
			
            </div>
        </div>
 		
 		<div class="updatePhoto" id="updatePhoto">
 			<div class="updatePhotoContent" id="updatePhotoContent">
 				<button>프로필사진 변경</button>
 				<button>스토리 변경</button>
 				<button>스토리 보기</button>
 			</div>
 		</div>
 		
 		<div class="updatePhoto" id="userPhotoMenu">
 			<div class="updatePhotoContent" id="userPhotoMenuContent">
 				<button>스토리 보기</button>
 				<button>신고하기</button>
 			</div>
 		</div>
 		
 		<form action="" method="post" enctype="multipart/form-data">
 		<div class="reportBgk" id="reportBgk">
 			<div class="reportContent" id="reportContent">
 				<textarea name="reportTextarea" placeholder="신고내용을 입력해주세요" wrap="hard" autofocus required></textarea>
 				<input type="file" multiple="multiple"><button type="button">신고하기</button>
 			</div>
 		</div>	
 		</form>
 		
 		<div id="storyContent">
			<div>
			<div id="storyProgressBarBKG">
				<div id="storyProgressBar"> </div>
			</div>
			<img alt="" src="<%=request.getContextPath() %>/img/soundOff.png">
			<video src="<%=request.getContextPath()%>/<%=userStory.getPath()%>" muted autoplay id="storyVideo"></video>
			</div>
		</div>

 		<form action="<%=request.getContextPath()%>/view/photoModify" method="post" id="modifyPhoto" enctype="multipart/form-data">
	        <input type="file" class="hiddenInput" name="uploadProfilePhoto1" id="uploadProfilePhoto1" accept="image/*" this.select();>
	        <input type="file" class="hiddenInput" name="uploadProfilePhoto2" id="uploadProfilePhoto2" accept="image/*" this.select();>
		</form>
		<form action="<%=request.getContextPath()%>/view/storymodify" method="post" id="modifyStory" enctype="multipart/form-data">
	        <input type="file" class="hiddenInput" name="uploadProfileStory" id="uploadProfileStory" accept="video/mp4" this.select();>
	        <input type="test" class="hiddenInput" name="storyUserNo" id="storyUserNo" value="<%=user.getNo() %>">
		</form>
		
        <hr id="dividingLine">

        <div id='profileMenuDiv'>
            <ul id='profileMenuUl'>
                <li class="profileMenuLi">
                    <input type="radio" name="profileMenuRbt" id="profileMenuRbt1" onclick='profileMenuChange();' checked>
                    <label for="profileMenuRbt1" class="profileMenuLb">게시글</label>
                </li>
                
                <li class="profileMenuLi">
                    <input type="radio" name="profileMenuRbt" id="profileMenuRbt3" onclick='profileMenuChange();'>
                    <label for="profileMenuRbt3" class="profileMenuLb">저장됨</label>
                </li>
                <li class="profileMenuLi">
                    <input type="radio" name="profileMenuRbt" id="profileMenuRbt4" onclick='profileMenuChange();'>
                    <label for="profileMenuRbt4" class="profileMenuLb">태그됨</label>
                </li>
                <li class="profileMenuLi">
                    <input type="radio" name="profileMenuRbt" id="profileMenuRbt5" onclick='profileMenuChange();'>
                    <label for="profileMenuRbt5" class="profileMenuLb">팔로워</label>
                 </li>
                <li class="profileMenuLi">
                    <input type="radio" name="profileMenuRbt" id="profileMenuRbt6" onclick='profileMenuChange();'>
                    <label for="profileMenuRbt6" class="profileMenuLb">팔로우</label>
                </li>
                <li class="profileMenuLi" id="blockMenu">
                    <input type="radio" name="profileMenuRbt" id="profileMenuRbt2" onclick='profileMenuChange();'>
                    <label for="profileMenuRbt2" class="profileMenuLb">차단됨</label>
                </li>
                <li class="profileMenuLi" id="requestFollow">
                    <input type="radio" name="profileMenuRbt" id="profileMenuRbt7" onclick='profileMenuChange();'>
                    <label for="profileMenuRbt7" class="profileMenuLb">요청됨</label>
                </li>
            </ul>
        </div>
       	<div id=disclosure>
       	<div id="disclosureContent">
       		<label>비공개 계정입니다</label><br><br>
       		<label>게시글을 보려면 팔로우하세요</label>
       	</div>
       	</div>
        <div id="profileContent1" class="profileContent"></div>  			<!-- 게시글 메뉴 컨텐츠 부모 (게시글들 감싸는애) -->  
        <div id="profileContent2"></div> 			<!-- 저장됨 메뉴 컨텐츠 부모 (게시글들 감싸는애) -->
        <div id="profileContent3" class="profileContent"></div> 			<!-- 태그됨 메뉴 컨텐츠 부모 (게시글들 감싸는애) -->
        <div id="profileContent4" class="profileContent"></div>		 	<!-- 팔로워 메뉴 컨텐츠 부모 (게시글들 감싸는애) -->
        <form action="mypage.jsp" method="post">
        	<div id="profileContent5"></div>		<!-- 팔로우 메뉴 컨텐츠 부모 (게시글들 감싸는애) -->
        	<input type="hidden" name="followerId" value="followerId"/>
        </form> 
        <form action="mypage.jsp" method="post">
        	<div id="profileContent6"></div>		<!-- 차단됨 메뉴 컨텐츠 부모 (게시글들 감싸는애) -->
        	<input type="hidden" name="followId" value="followId"/>
        </form>  
        <div id="profileContent7" class="profileContent"></div>

    </div>
    
    
      
   

    <script>
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
    
            //모바일인지 웹인지 확인해서 css 적용
            $(document).ready(function(){
            	
            var filter = "win16|win32|win64|mac|macintel"; 
           
            if ( navigator.platform ) {
                if ( filter.indexOf( navigator.platform.toLowerCase() ) < 0 ) {
                      //모바일접속
                    $('head').append($('<link/>',{
                        rel:'stylesheet',
                        type:'text/css',
                        href:'<%=request.getContextPath()%>/css/profilePageMobile.css'
                    }));
                } 
                else {
                      //PC접속
                    $('head').append($('<link/>',{
                        rel:'stylesheet',
                        type:'text/css',
                        href:'<%=request.getContextPath()%>/css/profilePage.css'
                    }));
                }
            }
    
            });
            
             //내정보창에 들어온게 나인지 다른사람인지 확인하고 ui수정
           
             <% if(user.getNo()==(int)request.getSession().getAttribute("userNo")){  //내 페이지
            	System.out.println("아이디 같네");
            	%>
            	 $('#profileFollowBt').css("display","none");
            	 $('#profileBlockBt').css("display","none");
            	 $('#profilePhotoBt').click(function(){
            		 showProfilePhotoMenu();
            	 });
            	 <%if(isRequestFollowDataArray.size()!=0){
            		 for(int i=0;i<isRequestFollowDataArray.size();i++){
            		 %>
            		 $('body').alertBox({
            		        title: "'<%=isRequestFollowDataArray.get(i).getName()%>'님이 팔로우 요청했습니다",
            		        lTxt: '보류',
            		        lCallback: function(){
            		        	$.ajax({
            	            		url:'<%=request.getContextPath()%>/view/reserverequestfollow',
            	            		type:"POST",
            	            		data:{"requestFollowUserNo":<%=isRequestFollowDataArray.get(i).getNo()%>},
            	            		success:function(data){},
            	            		error:function(xhr,status){
            	            			alert(xhr+" : "+status);	
            	            		}
            	            		});
            		        },
            		        rTxt: '수락',
            		        rCallback: function(){
	 					        $.ajax({
	 					            url:'<%=request.getContextPath()%>/view/allowrequestfollowuser',
	 					            type:'POST',
	 					            data:{'requestFollowUserNo':<%=isRequestFollowDataArray.get(i).getNo()%>},
	 					            success:function(data){},
            	            		error:function(xhr,status){
            	            			alert(xhr+" : "+status);	
            	            		}
            	            	});
            		        }
            		      });
            		 <%}
            		 }%>
         	<%}
         	else{//다른사람 페이지
         		System.out.println("다른사람 페이지네");
         		%>
         		
         		$('#profileModify').css("display","none");
         		$('#profileWrite').css("display","none");
         		$('#BackgroundPhotoIconDv').css("display","none");
         		$('#profilePhotoHover').css("display","none");
         		$('#profileBackgroundPhoto').removeAttr("onclick");
         		$('#profileBackgroundPhoto').css("cursor","default");
         		$('#blockMenu').css("display","none");
         		$('#requestFollow').css("display","none");
         		
         		<%if(isFollowed){%>
	         		$('#profileFollowBt>label').text("팔로우됨");
	        		$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOn.png");
	        		$('#profileFollowBt').css("background-color","rgb(103,153,255)");  
	        		$('#profileFollowBt').css("color","white"); 
        		
	        		$("#userPhotoMenuContent>button:nth-child(1)").click(function(){
	        			 <%if(userStory.getPath()!=""){%> //스토리가 있으면 스토리 열리게 
	 		        			 $("#storyContent").fadeIn();
	 		     				updateBar=setInterval(update,500);
	 		     				if(!$('#storyProgressBar').width()==0){
	 		     					myMovie.currentTime=0;
	 		     					progressBar.style.width='0px';
	 		     				    window.clearInterval(updateBar);
	 		     				}
	 		     				if(myMovie.paused){
	 		     					myMovie.play();
	 		     				}
	 		            	
	 	        		<%}
	 	        		else{%> //스토리 없으면
	 	        			profileAlert("스토리가 없습니다");
	 	        		<%}%> 
       				 });
        		<%}
         		else if(isRequestFollow){%>
	         		$('#profileFollowBt>label').text("팔로우요청");
	      			$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOn.png");
	        		$('#profileFollowBt').css("background-color","rgba(255,223,36)");  
	        		$('#profileFollowBt').css("color","white");
	        		<%if(user.getDisclosure()==0){
		            	System.out.println("비공개임");
		            	%>
		            	$('#profileMenuDiv').css("display","none");
		            	$('#disclosure').css("display","block");            		 
	            	<%}%>
	            		
	            	$("#userPhotoMenuContent>button:nth-child(1)").css("background-color","rgba(50,50,50,0.2)");
	           		$("#userPhotoMenuContent>button:nth-child(1)").attr("disabled","disalbed");
         		<%}
         		else{%>
        			$('#profileFollowBt>label').text("팔로우");
            		$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOff.png");
            		$('#profileFollowBt').css("background-color","#F6F6F6"); 
            		$('#profileFollowBt').css("color","black");
            		<%if(user.getDisclosure()==0){
            		System.out.println("비공개임");
            		%>
            		$('#profileMenuDiv').css("display","none");
            		$('#disclosure').css("display","block");            		 
            		<%}%>
            		
            		$("#userPhotoMenuContent>button:nth-child(1)").css("background-color","rgba(50,50,50,0.2)");
           			$("#userPhotoMenuContent>button:nth-child(1)").attr("disabled","disalbed");
           			
        		<%}%>
        		
	        		 $('#profilePhotoBt').click(function(){
	        			 $("#userPhotoMenu").fadeIn();
	     				
	            	 });
	        		 $('#userPhotoMenu').click(function(){
	        			$('#userPhotoMenu').fadeOut(); 
	        		 });	 
	            	 
	            	 $("#userPhotoMenuContent>button:nth-child(2)").click(function(){
	            		 $('#reportBgk').fadeIn();
	            	 });
	            	 $('#reportBgk').click(function(){
	            		$('#reportBgk').fadeOut(); 
	            	 });
	            	 $('#reportContent').click(function(e){
	            		 e.stopPropagation();
	            	 });
	            	 
	            	 //신고창에서 신고보내기
	            	 $('#reportContent>button').click(function(){
	      				if($('#reportContent>textarea').val()!=""){
	      					/* $.ajax({
	      						url='',
	      						type='POST'
	      						
	      					}); */
	      				}
	     	 			else{
	     	 				profileAlert("신고내용 입력");
	     	 			}
	      			});
         		
         	<%}%>
         	
    </script>
    <script>
            $(function(){
                $('#profileBackgroundPhoto').hover(function(){
                    $('#BackgroundPhotoIconDv > img').css('transform','scale(0.9)');
                    $('#BackgroundPhotoIconDv').css('border','1px solid rgb(193,193,193)');
                    $('#BackgroundPhotoIconDv > label').css('color','rgba(193,193,193)');
                },function(){
                    $('#BackgroundPhotoIconDv > img').css('transform','scale(1)');
                    $('#BackgroundPhotoIconDv > label').css('color','rgba(213,213,213,0)');
                    $('#BackgroundPhotoIconDv').css('border','none');

                });
            });

            //프로필사진 바꾸기
 
            function readURL1(input) { //들어온파일 가져와서 e.taget.result로 이미지 받아서 #modifyScreenMainPhoto 사진속성 바꿔주기
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        /* console.log(e.target.result); */
                        $('#profilePhoto').attr('src', e.target.result);
                    }
                    /* console.log(input.files[0].name); */
                    reader.readAsDataURL(input.files[0]); //url읽어드린다

                    $('#modifyPhoto').submit(); 
                }
            }

            $('#uploadProfilePhoto1').change(function () { //숨겨둔 input file에서 그림 선택하면 실행되는 함수 
                pathpoint = this.value.lastIndexOf('.');
            	filepoint = this.value.substring(pathpoint+1,this.length);
            	filetype = filepoint.toLowerCase();
            	
            	if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp') {

            		readURL1(this);
            		
            	} else {
            		profileAlert('이미지 선택해주세요');
            		$('#uploadProfilePhoto1').val("");
            	}
                
            });

         	//배경사진 바꾸기
            function changeBackgroundPhoto() {
                $('#uploadProfilePhoto2').click();  //프로필사진 클릭시 숨겨둔 input file 실행                   
            }

            function readURL2(input) { //들어온파일 가져와서 e.taget.result로 이미지 받아서 #modifyScreenMainPhoto 사진속성 바꿔주기
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                       /*  console.log(e.target.result); */
                        $('#profileBackgroundPhoto').attr('src', e.target.result);
                    }
                    /* console.log(input.files[0].name); */
                    reader.readAsDataURL(input.files[0]); //url읽어드린다
        
                     $('#modifyPhoto').submit(); 
                    
                }
            }

            $('#uploadProfilePhoto2').change(function () { //숨겨둔 input file에서 그림 선택하면 실행되는 함수 
            	pathpoint = this.value.lastIndexOf('.');
             	filepoint = this.value.substring(pathpoint+1,this.length);
             	filetype = filepoint.toLowerCase();
             	
             	if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp') {

             		readURL2(this);
             		
             	} else {
             		profileAlert('이미지 선택해주세요');
             		$('#uploadProfilePhoto2').val("");
             	}
                
            });
            
            //스토리 바꾸기
            /* function changePhoto() {
                $('#uploadProfileStory').click(); 	//hidden 인풋 실행      
            }  */
         
            function readURL3(input) { //들어온파일 가져와서 e.taget.result로 이미지 받아서 #modifyScreenMainPhoto 사진속성 바꿔주기
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        /* console.log(e.target.result); */
                    }
                    /* console.log(input.files[0].name); */
                    reader.readAsDataURL(input.files[0]); //url읽어드린다

                    $('#modifyStory').submit(); 
                }
            }

            $('#uploadProfileStory').change(function () { //숨겨둔 input file에서 그림 선택하면 실행되는 함수 
               
            	pathpoint = this.value.lastIndexOf('.');
             	filepoint = this.value.substring(pathpoint+1,this.length);
             	filetype = filepoint.toLowerCase();
             	
             	if(filetype=='mp4') {

             		readURL3(this);
             		
             	} else {
             		profileAlert('MP4만 올릴 수 있어요');
             		$('#uploadProfileStory').val("");
             	}
                
            });
            
    </script>
    
    <script>  //팔로우,차단버튼 이벤트 
	function blockClick(){
    	/* 		$('#profileFollowBt').removeAttr("onclick"); //버튼눌릴때 다른버튼 비활성화
		$('#profileName').removeAttr("onclick"); */
		$('body').alertBox({
	        title: '차단 하시겠습니까?',
	        lTxt: '아니요',
	        lCallback: function(){},
	        rTxt: '네',
	        rCallback: function(){
	        	if(($('#profileBlockBt>label').text())==("차단하기")){
	        		$('#profileBlockBt>label').text("차단됨");
	        		$('#profileBlockBt>img').attr("src","<%=request.getContextPath()%>/img/blockBtOn.png");
	        		$('#profileBlockBt').css("background-color","rgb(241,95,95)"); 
	        		$('#profileBlockBt').css("color","white");
	        	}
			  	 <%-- else{
	        		$('#profileBlockBt>label').text("차단하기");
	        		$('#profileBlockBt>img').attr("src","<%=request.getContextPath()%>/img/blockBtOff.png");
	        		$('#profileBlockBt').css("background-color","#F6F6F6"); 
	        		$('#profileBlockBt').css("color","black");
	        	}  --%>
	        	location.href='<%=request.getContextPath()%>/view/updatefollowblock?block='+$('#profileBlockBt>label').text()+'&uu=<%=user.getNo()%>'; 
	        }
	      });
    	
	}
	function followClick(){
/* 		$('#profileBlockBt').removeAttr("onclick"); //버튼눌릴때 다른버튼 비활성화
		$('#profileName').removeAttr("onclick"); */
		var beforeFollowBtLabel="";
		if(($('#profileFollowBt>label').text())==("팔로우")){
			beforefollowBtLabel="팔로우";
			<%if(user.getDisclosure()==0){%>  //비공개
					$('#profileFollowBt>label').text("팔로우요청");
          			$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOn.png");
            		$('#profileFollowBt').css("background-color","rgba(255,223,36)");  
            		$('#profileFollowBt').css("color","white");
          	<%}
          	else{%>
			$('#profileFollowBt>label').text("팔로우됨");
    		$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOn.png");
    		$('#profileFollowBt').css("background-color","rgb(103,153,255)");  
    		$('#profileFollowBt').css("color","white");
    		
    		$("#userPhotoMenuContent>button:nth-child(1)").css({"background-color":"white","color":"black"});
   			$("#userPhotoMenuContent>button:nth-child(1)").attr("disabled",false);
   			$("#userPhotoMenuContent>button:nth-child(1)").hover(function(){
   			$("#userPhotoMenuContent>button:nth-child(1)").css({"background-color":"rgb(193,193,193)","color":"white"});
   			},function(){
   				$("#userPhotoMenuContent>button:nth-child(1)").css({"background-color":"white","color":"black"});
   			});
   			$("#userPhotoMenuContent>button:nth-child(1)").click(function(){
   			 <%if(userStory.getPath()!=""){%> //스토리가 있으면 스토리 열리게 
	        			 $("#storyContent").fadeIn();
	     				updateBar=setInterval(update,500);
	     				if(!$('#storyProgressBar').width()==0){
	     					myMovie.currentTime=0;
	     					progressBar.style.width='0px';
	     				    window.clearInterval(updateBar);
	     				}
	     				if(myMovie.paused){
	     					myMovie.play();
	     				}
	            	
        		<%}
        		else{%> //스토리 없으면
        			profileAlert("스토리가 없습니다");
        		<%}%> 
				 });
   			<%}%>
    	}
    	else{
    		if(($('#profileFollowBt>label').text())==("팔로우요청")){
    			beforeFollowBtLabel="팔로우요청";
    		}
    		else if(($('#profileFollowBt>label').text())==("팔로우됨")){
    			beforeFollowBtLabel="팔로우됨";
    		}
    		
    		
    		$('#profileFollowBt>label').text("팔로우");
    		$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOff.png");
    		$('#profileFollowBt').css("background-color","#F6F6F6"); 
    		$('#profileFollowBt').css("color","black");
    		
    		$("#userPhotoMenuContent>button:nth-child(1)").css({"background-color":"rgba(50,50,50,0.2)","color":"gray"});
   			$("#userPhotoMenuContent>button:nth-child(1)").attr("disabled","disalbed");
   		
    	}
		
		$.ajax({ //팔로우,팔로워 목록에 추가
    		url:'<%=request.getContextPath()%>/view/updatefollowblock',
    		type:"POST",
    		data:{"follow":$('#profileFollowBt>label').text(),"uu":<%=user.getNo()%>,"beforeFollowBtLabel":beforeFollowBtLabel},
    		success:function(data){
    			
    			<%if(user.getDisclosure()==0){%>
    			$.ajax({//비공개 계정일때 팔로우 확인
    				url:"<%=request.getContextPath()%>/view/chkFollow",
    				type:"POST",
    				data:{"userNo":<%=user.getNo()%>},
    				success:function(data){
    					if(data=="true"){
    						$('#profileMenuDiv').css("display","block");
    	            		$('#disclosure').css("display","none");
    	            		
    					}
    					else if(data=="false"){
    						$('#profileMenuDiv').css("display","none");
    	            		$('#disclosure').css("display","block");
    					}
    				},
    				error:function(xhr,status){
    					alert(xhr+" : "+status);
    				}
    			});
    			<%}%>
    			
    		}
    	});
		
		$.ajax({ //팔로워 목록 갱신
    		url:"<%=request.getContextPath()%>/view/selectfollow",
    		type:"POST",
    		data:{"userNo":<%=user.getNo()%>,"isfollow":"follower"},
    		success:function(data){
    			
    			$('#profileContent5>*').remove();
    			
    			 for(var i=0;i<data.length;i++){							
							var no=data[i]["no"];
							var name=data[i]["name"];
							var profilePhoto=data[i]["profilePhoto"];
							
							$('#profileContent5').append($('<div/>',{
				                class:'profileFollowDv',
				                id:no
				             }));
				             
				             $('#profileContent5>#'+no).on("click",function(){
				            	location.href='<%=request.getContextPath()%>/view/profile?uu='+$(this).attr("id");
				             });
				             
				             $('#profileContent5>#'+no).append($('<img/>',{
				                src: '<%=request.getContextPath()%>/'+profilePhoto
				             }));
				            
				             $('#profileContent5>#'+no).append($('<label/>',{
				                
				             }));
				             $('#profileContent5>#'+no+'>label').text(name);            	
					}	
    		},
    		error:function(xhr,status){
    			alert(xhr+" : "+status);
    		}
    	});
		
	}
	 
	</script>

    <script> //게시글,게시글,저장도미,태그됨 메뉴,팔로워,팔로우 이미지 바뀌는거 + 컨텐츠 출력
           
            $(document).ready(profileMenuChange());
            // $('input[name=profileMenuRbt]').change(function(){
            //     profileMenuChange(this);
            // });

            function profileMenuChange(){
               // console.log($('#profileMenuRbt1').is(':checked'));
               //console.log($(event).get(0)==$('#profileMenuRbt1').get(0));
               
  
                
                 if($('#profileMenuRbt1:checked').val()){
                    $('#profileMenuRbt1').parents('li').css('borderTop',"3px solid black");
                    $('#profileMenuRbt1').css('background','url(<%=request.getContextPath()%>/img/profileMenu1.png) no-repeat');
                    $('#profileContent1').css('display','block');
                    selectedTab = 0;
                    console.log($('.profileContent:eq(' + selectedTab + ')'));
                 }
                 else if(!($('#profileMenuRbt1:checked').val())){
                    $('#profileMenuRbt1').parents('li').css('borderTop',"none");
                    $('#profileMenuRbt1').css('background','url(<%=request.getContextPath()%>/img/profileMenu11.png) no-repeat');
                    $('#profileContent1').css('display','none');  
                }
                 if($('#profileMenuRbt2:checked').val()){
                    $('#profileMenuRbt2').parents('li').css('borderTop',"3px solid black");
                    $('#profileMenuRbt2').css('background','url(<%=request.getContextPath()%>/img/profileMenu2.png) no-repeat');
                    $('#profileContent2').css('display','block'); 
                   
                 }
                 else if(!($('#profileMenuRbt2:checked').val())){
                    $('#profileMenuRbt2').parents('li').css('borderTop',"none");
                    $('#profileMenuRbt2').css('background','url(<%=request.getContextPath()%>/img/profileMenu22.png) no-repeat');
                    $('#profileContent2').css('display','none');
                    
                }
                 if($('#profileMenuRbt3:checked').val()){
                    $('#profileMenuRbt3').parents('li').css('borderTop',"3px solid black");  
                    $('#profileMenuRbt3').css('background','url(<%=request.getContextPath()%>/img/profileMenu3.png) no-repeat');
                    $('#profileContent3').css('display','block');  
                    selectedTab = 1;
                    console.log($('.profileContent:eq(' + selectedTab + ')'));
                 }
                 else if(!($('#profileMenuRbt3:checked').val())){
                    $('#profileMenuRbt3').parents('li').css('borderTop',"none");
                    $('#profileMenuRbt3').css('background','url(<%=request.getContextPath()%>/img/profileMenu33.png) no-repeat');
                    $('#profileContent3').css('display','none');
                }
                 if($('#profileMenuRbt4:checked').val()){
                    $('#profileMenuRbt4').parents('li').css('borderTop',"3px solid black");
                    $('#profileMenuRbt4').css('background','url(<%=request.getContextPath()%>/img/profileMenu4.png) no-repeat');
                    $('#profileContent4').css('display','block');   
                    selectedTab = 2;
                    console.log($('.profileContent:eq(' + selectedTab + ')'));
                 }
                 else if(!($('#profileMenuRbt4:checked').val())){
                    $('#profileMenuRbt4').parents('li').css('borderTop',"none");
                    $('#profileMenuRbt4').css('background','url(<%=request.getContextPath()%>/img/profileMenu44.png) no-repeat');
                    $('#profileContent4').css('display','none');   
                }
                 if($('#profileMenuRbt5:checked').val()){
                    $('#profileMenuRbt5').parents('li').css('borderTop',"3px solid black");   
                    $('#profileMenuRbt5').css('background','url(<%=request.getContextPath()%>/img/profileMenu5.png) no-repeat'); 
                    $('#profileContent5').css('display','block'); 
                 }
                 else if(!($('#profileMenuRbt5:checked').val())){
                    $('#profileMenuRbt5').parents('li').css('borderTop',"none");
                    $('#profileMenuRbt5').css('background','url(<%=request.getContextPath()%>/img/profileMenu55.png) no-repeat'); 
                    $('#profileContent5').css('display','none');
                }
                if($('#profileMenuRbt6:checked').val()){
                    $('#profileMenuRbt6').parents('li').css('borderTop',"3px solid black");
                    $('#profileMenuRbt6').css('background','url(<%=request.getContextPath()%>/img/profileMenu6.png) no-repeat');
                    $('#profileContent6').css('display','block'); 
                 }
                 else if(!($('#profileMenuRbt6:checked').val())){
                    $('#profileMenuRbt6').parents('li').css('borderTop',"none");
                    $('#profileMenuRbt6').css('background','url(<%=request.getContextPath()%>/img/profileMenu66.png) no-repeat');
                    $('#profileContent6').css('display','none'); 
                }
                if($('#profileMenuRbt7:checked').val()){
                    $('#profileMenuRbt7').parents('li').css('borderTop',"3px solid black");
                    $('#profileMenuRbt7').css('background','url(<%=request.getContextPath()%>/img/profileMenu6.png) no-repeat');
                    $('#profileContent7').css('display','block'); 
                 }
                 else if(!($('#profileMenuRbt7:checked').val())){
                    $('#profileMenuRbt7').parents('li').css('borderTop',"none");
                    $('#profileMenuRbt7').css('background','url(<%=request.getContextPath()%>/img/profileMenu66.png) no-repeat');
                    $('#profileContent7').css('display','none'); 
                }
            }
            
    </script>

 <script>
        $(document).ready(function(){ //컨텐츠 만들기

           /* var photoArr=new Array('img/01.jpg','img/02.jpg','img/03.jpg',
           'img/04.jpg','img/05.jpg','img/06.jpg','img/07.jpg','img/08.jpg',
           'img/09.jpg','img/10.jpg','img/11.jpg','img/12.jpg','img/13.jpg'); */
            
            //1.게시글 컨텐츠
            
             <%for(int i=0;i<content1DataArray.size();i++){%>
             	
            	 $('#profileContent1').append($('<div/>',{
	                 class:'profileContent134Photo',
	                 value:'<%=content1DataArray.get(i).getNewspeedNo()%>',
                	 <%if(content1DataArray.get(i).getType()==0){%>
                		 style:"background-image: url('<%=request.getContextPath()%>/<%=content1DataArray.get(i).getPath()%>')"
               		 <%}
                	 else{%>
                  		  style:"background-image: url('<%=request.getContextPath()%>/upload/newspeed/videoContent.png')"
              	    <%}%>
                }));
            	 
            	 <%if((user.getNo()==(int)request.getSession().getAttribute("userNo"))||(user.getNo()<0)){%>
            	 $('#profileContent1>div:nth-child(<%=i+1%>)').append($('<button/>',{
  					class:'contentCancelBt',
  					style:"background-image: url('<%=request.getContextPath()%>/img/cancel3.png')"
  				 }));
            	 
            	 $(('#profileContent1>div:nth-child(<%=i+1%>)')+'>button').css("display","none");
            	 $(('#profileContent1>div:nth-child(<%=i+1%>)')+'>button').css("width","20px");
            	 $(('#profileContent1>div:nth-child(<%=i+1%>)')+'>button').css("height","20.5px");
            	 
  				 $(('#profileContent1>div:nth-child(<%=i+1%>)')+'>button').on("click",function(e){
  				 e.stopPropagation(); //부모 이벤트 실행 안되게
  				 var deleteNewspeedDiv1=$(this).parent();
  				 var storedNewspeedNo1=$(this).parent().attr("value");
  				 $('body').alertBox({
  				        title:'게시물을 지우시겠습니까?',
  				        lTxt: '아니요',
  				        lCallback: function(){}, 
  				        rTxt: '네',
  				        rCallback: function(){
  							 $.ajax({
  								url:'<%=request.getContextPath()%>/view/deletenewspeed',
  								type:"POST",
  								data:{"storedNewspeedNo1":storedNewspeedNo1},
  								success:function(){
  									deleteNewspeedDiv1.remove();
  									location.reload();
  								}
  							}); 
  				        }
  				      });
  				 });
  				 
  				$('#profileContent1>:eq(<%=i%>)').hover(function(){
  					 $(('#profileContent1>div:nth-child(<%=i+1%>)')+'>button').css("display","block");
  				},function(){
  					$(('#profileContent1>div:nth-child(<%=i+1%>)')+'>button').css("display","none");
  				});
  				<%}%>
  				
            	
            	 $('#profileContent1>:eq(<%=i%>)').on('click',function(){
         		    var maxLength = $('#profileContent3 .profileContent134Photo').length;
        		    var selectIndex = $(this).prevAll().length;
            		 requestNewspeedInfo($(this).attr("value"), selectIndex, maxLength, $(this)); 
            		 
            		 
            	 });

             <%}%>  

           //3.저장됨 컨텐츠
             <%for(int i=0;i<storageContentDataArray.size();i++){%>
            	 $('#profileContent3').append($('<div/>',{
                 class:'profileContent134Photo',
                 value:'<%=storageContentDataArray.get(i).getNewspeedNo()%>',
                 <%if(storageContentDataArray.get(i).getType()==0){%>
                	 style:"background-image: url('<%=request.getContextPath()%>/<%=storageContentDataArray.get(i).getPath()%>')"
                 <%}
            	 else{%>
            		 style:"background-image: url('<%=request.getContextPath()%>/upload/newspeed/videoContent.png')"
               	 <%}%>  	 
             }));
            	 
            	 <%if(user.getNo()==(int)request.getSession().getAttribute("userNo")){%>
            	 $('#profileContent3>div:nth-child(<%=i+1%>)').append($('<button/>',{
  					class:'contentCancelBt',
  					style:"background-image: url('<%=request.getContextPath()%>/img/cancel3.png')"
  				 }));
            	 
            	 $(('#profileContent3>div:nth-child(<%=i+1%>)')+'>button').css({"display":"none","width":"20px","height":"20.5px"});
            	
  				 $(('#profileContent3>div:nth-child(<%=i+1%>)')+'>button').on("click",function(e){
  				 e.stopPropagation(); //부모 이벤트 실행 안되게
  				 var deleteNewspeedDiv=$(this).parent();
  				 var storedNewspeedNo=$(this).parent().attr("value");
  				 $('body').alertBox({
  				        title: '저장된 게시물을 지우시겠습니까?',
  				        lTxt: '아니요',
  				        lCallback: function(){},
  				        rTxt: '네',
  				        rCallback: function(){
  							 $.ajax({
  								url:'<%=request.getContextPath()%>/view/deleteStoredNewspeed',
  								type:"POST",
  								data:{"storedNewspeedNo":storedNewspeedNo},
  								success:function(){
  									deleteNewspeedDiv.remove();
  									location.reload();
  								}
  							}); 
  				        }
  				      });
  				 });
  				 
  				$('#profileContent3>:eq(<%=i%>)').hover(function(){
  					 $(('#profileContent3>div:nth-child(<%=i+1%>)')+'>button').css("display","");
  				},function(){
  					$(('#profileContent3>div:nth-child(<%=i+1%>)')+'>button').css("display","none");
  				});
  				<%}%>
            	 
            	 $('#profileContent3>:eq(<%=i%>)').on('click',function(){
            		 
         		    var maxLength = $('#profileContent3 .profileContent134Photo').length;
        		    var selectIndex = $(this).prevAll().length;
            		 requestNewspeedInfo($(this).attr("value"), selectIndex, maxLength, $(this)); 

            	
             	 });
            	 
             <%}%> 
                  
            //4.태그됨 컨텐츠
	         <%for(int i=0;i<tagContentDataArray.size();i++){%>
            	 $('#profileContent4').append($('<div/>',{
                 class:'profileContent134Photo',
                 value:'<%=tagContentDataArray.get(i).getNewspeedNo()%>',
                 <%if(tagContentDataArray.get(i).getType()==0){%>
                 	style:"background-image: url('<%=request.getContextPath()%>/<%=tagContentDataArray.get(i).getPath()%>')"
                 <%}
            	 else{%>
            	 	style:"background-image: url('<%=request.getContextPath()%>/upload/newspeed/videoContent.png')"
                 <%}%> 
            	 
             	}));
            	 
            	 $('#profileContent4>:eq(<%=i%>)').on('click',function(){
         		    var maxLength = $('#profileContent3 .profileContent134Photo').length;
        		    var selectIndex = $(this).prevAll().length;
            		 requestNewspeedInfo($(this).attr("value"), selectIndex, maxLength , $(this));  
              	 });
            	 
             <%}%>  
             
            //5.팔로워 컨텐츠
            $('#profileMenuRbt5').on("click",function(){
            	$.ajax({
            		url:"<%=request.getContextPath()%>/view/selectfollow",
            		type:"POST",
            		data:{"userNo":<%=user.getNo()%>,"isfollow":"follower"},
            		success:function(data){
            			
            			$('#profileContent5>*').remove();
            			
            			 for(var i=0;i<data.length;i++){							
 								var no=data[i]["no"];
 								var name=data[i]["name"];
 								var profilePhoto=data[i]["profilePhoto"];
 								
 								$('#profileContent5').append($('<div/>',{
 					                class:'profileFollowDv',
 					                id:no
 					             }));
 					             
 					             $('#profileContent5>#'+no).on("click",function(){
 					            	location.href='<%=request.getContextPath()%>/view/profile?uu='+$(this).attr("id");
 					             });
 					             
 					             $('#profileContent5>#'+no).append($('<img/>',{
 					                src: '<%=request.getContextPath()%>/'+profilePhoto
 					             }));
 					            
 					             $('#profileContent5>#'+no).append($('<label/>',{
 					                
 					             }));
 					             $('#profileContent5>#'+no+'>label').text(name);            	
 						}	
            		},
            		error:function(xhr,status){
            			alert(xhr+" : "+status);
            		}
            	});
            });
            //페이지 로딩시 팔로워 가져오기
            <%for(int i=0;i<followerDataArray.size();i++){%> 	
            $('#profileContent5').append($('<div/>',{
                class:'profileFollowDv',
                id:'<%=followerDataArray.get(i).getNo()%>'
             }));
             
             $('#profileContent5>#<%=followerDataArray.get(i).getNo()%>').on("click",function(){
            	 console.log($(this).attr("id"));
            	location.href='<%=request.getContextPath()%>/view/profile?uu='+$(this).attr("id");
             });
             
             $('#profileContent5>#<%=followerDataArray.get(i).getNo()%>').append($('<img/>',{
                src: '<%=request.getContextPath()%>/<%=followerDataArray.get(i).getProfilePhoto()%>'
             }));
            
             $('#profileContent5>#<%=followerDataArray.get(i).getNo()%>').append($('<label/>',{
                
             }));
             $('#profileContent5>#<%=followerDataArray.get(i).getNo()%>>label').text('<%=followerDataArray.get(i).getName()%>');
            <%}%>

             //6.팔로우 컨텐츠
             $('#profileMenuRbt6').on("click",function(){
	            $.ajax({
	            		url:"<%=request.getContextPath()%>/view/selectfollow",
	            		type:"POST",
	            		data:{"userNo":<%=user.getNo()%>,"isfollow":"follow"},
	            		success:function(data){
	            			$('#profileContent6>*').remove();
	            			
	            			 for(var i=0;i<data.length;i++){							
	 								var no=data[i]["no"];
	 								var name=data[i]["name"];
	 								var profilePhoto=data[i]["profilePhoto"];
	 								
	 								$('#profileContent6').append($('<div/>',{
	 					                class:'profileFollowDv',
	 					                id:no
	 					             }));
	 					             
	 					             $('#profileContent6>#'+no).on("click",function(){
	 					            	location.href='<%=request.getContextPath()%>/view/profile?uu='+$(this).attr("id");
	 					             });
	 					             
	 					             $('#profileContent6>#'+no).append($('<img/>',{
	 					                src: '<%=request.getContextPath()%>/'+profilePhoto
	 					             }));
	 					            
	 					             $('#profileContent6>#'+no).append($('<label/>',{
	 					                
	 					             }));
	 					             $('#profileContent6>#'+no+'>label').text(name);            	
	 						}	
	            		},
	            		error:function(xhr,status){
	            			alert(xhr+" : "+status);
	            		}
	            	});
             	});
          	    //페이지 로딩시 팔로우 가져오기
	            <%for(int i=0;i<followDataArray.size();i++){%>
	            $('#profileContent6').append($('<div/>',{
	               class:'profileFollowDv',
	               id:'<%=followDataArray.get(i).getNo()%>'
	            }));
	            
	            $('#profileContent6>#<%=followDataArray.get(i).getNo()%>').append($('<img/>',{
	               src: '<%=request.getContextPath()%>/<%=followDataArray.get(i).getProfilePhoto()%>'
	            }));
	           
	            $('#profileContent6>#<%=followDataArray.get(i).getNo()%>').on("click",function(){
	            	console.log($(this).attr("id"));
	            	location.href='<%=request.getContextPath()%>/view/profile?uu='+$(this).attr("id");
	            });
	            
	            $('#profileContent6>#<%=followDataArray.get(i).getNo()%>').append($('<label/>',{
	               
	            }));
	            $('#profileContent6>#<%=followDataArray.get(i).getNo()%>>label').text('<%=followDataArray.get(i).getName()%>');
	           <%}%> 


			 //2.차단됨 컨텐츠
			<%for(int i=0;i<blockDataArray.size();i++){%>
			 $('#profileContent2').append($('<div/>',{
			    class:'profileFollowDv',
			    id:'<%=blockDataArray.get(i).getNo()%>'
			 }));
			 
			 $('#profileContent2>#<%=blockDataArray.get(i).getNo()%>').append($('<img/>',{
			    src: '<%=request.getContextPath()%>/<%=blockDataArray.get(i).getProfilePhoto()%>'
			 }));
			
			 $('#profileContent2>#<%=blockDataArray.get(i).getNo()%>').append($('<label/>',{
				text:'<%=blockDataArray.get(i).getName()%>'
			 }));
			 
			 $('#profileContent2>#<%=blockDataArray.get(i).getNo()%>').append($('<button/>',{
				class:'contentCancelBt'
			 }));
			 
			 $('#profileContent2>#<%=blockDataArray.get(i).getNo()%>').on("click",function(){
				 var deleteBlockId=$(this).attr("id");
				 $('body').alertBox({
				        title: '　　　　차단된 이용자 입니다　　　　차단 해제 하시겠습니까?',
				        lTxt: '아니요',
				        lCallback: function(){},
				        rTxt: '네',
				        rCallback: function(){
							location.href='<%=request.getContextPath()%>/view/updatefollowblock?block=차단하기&uu='+deleteBlockId;
				        }
				 }); 
				
			}); 
			 
			 $(('#profileContent2>#<%=blockDataArray.get(i).getNo()%>')+'>button').on("click",function(e){
				 e.stopPropagation(); //부모 이벤트 실행 안되게
				 var deleteBlockId=$(this).parent().attr("id");
				 $('body').alertBox({
				        title: '차단 해제 하시겠습니까?',
				        lTxt: '아니요',
				        lCallback: function(){},
				        rTxt: '네',
				        rCallback: function(){
							location.href='<%=request.getContextPath()%>/view/updatefollowblock?block=차단하기&uu='+deleteBlockId;
				        }
				      });
			 });
			 <%}%> 
			 
			//7.팔로우요청 컨텐츠
             $('#profileMenuRbt7').on("click",function(){
	              $.ajax({
	            		url:"<%=request.getContextPath()%>/view/selectrequestfollow",
	            		type:"POST",
	            		data:{"userNo":<%=user.getNo()%>},
	            		success:function(data){
	            			
	            			$('#profileContent7>*').remove();
	            			
	            			 for(var i=0;i<data.length;i++){ 		
	 							    var no=data[i]["no"];
	 								var name=data[i]["name"];
	 								var profilePhoto=data[i]["profilePhoto"]; 
	 								if(data[i]["iswatch"]==1){
	 								$('#profileContent7').append($('<div/>',{
	 					                class:'profileFollowDv',
	 					                id:no
	 					             }));
	 					             $('#profileContent7>#'+no).on("click",function(){
	 					            	location.href='<%=request.getContextPath()%>/view/profile?uu='+$(this).attr("id");
	 					             });
	 					             
	 					             $('#profileContent7>#'+no).append($('<img/>',{
	 					            	 src: '<%=request.getContextPath()%>/'+profilePhoto
	 					             }));
	 					            
	 					             $('#profileContent7>#'+no).append($('<label/>',{
	 					                text:name
	 					             }));
	 					             
	 					             $('#profileContent7>#'+no).append($('<button/>',{
	 					            	text:'거절'
	 					             }));
	 					           
	 					             $('#profileContent7>#'+no).append($('<button/>',{
	 					            	text:'수락'
	 					             }));
	 					            $('#profileContent7>#'+no+'>button:eq(0)').on("click",function(e){
	 					            	e.stopPropagation();
	 					            	var requestFollowUserNoDiv=$(this).parent();
	 					            	var requestFollowUserNo=$(this).parent().attr("id");
	 					            	$.ajax({
	 					            		url:'<%=request.getContextPath()%>/view/deleterequestfollowuser',
	 					            		type:'POST',
	 					            		data:{'requestFollowUserNo':requestFollowUserNo},
	 					            		success:function(data){
	 					            			requestFollowUserNoDiv.remove();
	 					            		},
	 					            		error:function(xhr,status){
	 					            			alert(xhr+" : "+status);
	 					            		}
	 					            		
	 					            	});
	 					            });
	 					           $('#profileContent7>#'+no+'>button:eq(1)').on("click",function(e){
	 					            	e.stopPropagation();
	 					            	var requestFollowUserNoDiv=$(this).parent();
	 					            	var requestFollowUserNo=$(this).parent().attr("id");
	 					            	$.ajax({
	 					            		url:'<%=request.getContextPath()%>/view/allowrequestfollowuser',
	 					            		type:'POST',
	 					            		data:{'requestFollowUserNo':requestFollowUserNo},
	 					            		success:function(data){
	 					            			requestFollowUserNoDiv.remove();
	 					            		},
	 					            		error:function(xhr,status){
	 					            			alert(xhr+" : "+status);
	 					            		}
	 					            		
	 					            	});
	 					            });
	 					            $('#profileContent7>#'+no+'>button:eq(0)').css({'margin-right':'8px','background-color':'#FF5A5A'});
	 					            $('#profileContent7>#'+no+'>button:eq(1)').css({'background-color':'#59DA50'}); 
	 				 		}
	 						}
	            		},
	            		error:function(xhr,status){
	            			alert(xhr+" : "+status);
	            		}
	            	});  
             	});
				
        });
        
      //페이지 로딩시 팔로우요청 가져오기
        <%for(int i=0;i<requestfollowDataArray.size();i++){
        	if(requestfollowDataArray.get(i).getIswatch()==1){%>
        $('#profileContent7').append($('<div/>',{
           class:'profileFollowDv',
           id:'<%=requestfollowDataArray.get(i).getNo()%>'
        }));
        
        $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>').append($('<img/>',{
           src: '<%=request.getContextPath()%>/<%=requestfollowDataArray.get(i).getProfilePhoto()%>'
        }));
       
        $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>').on("click",function(){
        	location.href='<%=request.getContextPath()%>/view/profile?uu='+$(this).attr("id");
        });
        
        $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>').append($('<label/>',{
           
        }));
        $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>>label').text('<%=requestfollowDataArray.get(i).getName()%>');
        
        $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>').append($('<button/>',{
         	text:'거절'
          }));
        
          $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>').append($('<button/>',{
         	text:'수락'
          }));
         $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>>button:eq(0)').on("click",function(e){
        	 e.stopPropagation();
          	var requestFollowUserNoDiv=$(this).parent();
          	var requestFollowUserNo=$(this).parent().attr("id");
          	$.ajax({
          		url:'<%=request.getContextPath()%>/view/deleterequestfollowuser',
          		type:'POST',
          		data:{'requestFollowUserNo':requestFollowUserNo},
          		success:function(data){
          			requestFollowUserNoDiv.remove();
          		},
          		error:function(xhr,status){
          			alert(xhr+" : "+status);
          		}
          		
          	});
         });
        $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>>button:eq(1)').on("click",function(e){
        	e.stopPropagation();
         	var requestFollowUserNoDiv=$(this).parent();
         	var requestFollowUserNo=$(this).parent().attr("id");
         	$.ajax({
         		url:'<%=request.getContextPath()%>/view/allowrequestfollowuser',
         		type:'POST',
         		data:{'requestFollowUserNo':requestFollowUserNo},
         		success:function(data){
         			requestFollowUserNoDiv.remove();
         		},
         		error:function(xhr,status){
         			alert(xhr+" : "+status);
         		}
         		
         	});
         });
         $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>>button:eq(0)').css({'margin-right':'8px','background-color':'#FF5A5A'});
         $('#profileContent7>#<%=requestfollowDataArray.get(i).getNo()%>>button:eq(1)').css({'background-color':'#59DA50'}); 
         
       <%}}%> 
       
			 
    </script> 

	<div id="posting_wrap">	
	 <div id="posting_whole" class="posting_whole_normal">
		<header>
			<p>게시글 쓰기</p>
		</header>
		<div>

			<form action="" method="post" name="newspped_write">
				<div id="textArea_wrapper">
					<div contenteditable="true" id="posting_content"
						placeholder="내용을 입력하세요."></div>
				</div>

				<hr style="color: lightgray">
				<div id="media_preview">
					<div class="thumbnail_wrapper">
						<label id="label_input_files" for="input_Files"></label> <input
							type="file" accept="image/*" id="input_Files" multiple
							style="display: none;" onchange="uploadFiles(this);">
						<div id="dummy_img_wrapper" style="width: 100%; height: 42%;">
							<img id="dummy_img"
								style="src: url(../img/postwrite/add_Btn.png); visibility: hidden; width: 100%; height: 100%;">
						</div>
					</div>

				</div>


				<div id="button_area">
					<input type="button" id="button_write" onclick="submitPosting()"
						class="button_bbs" value="글쓰기"> <input type="button"
						onclick="hidePostingWhole();" id="button_cancel"
						class="button_bbs" value="취소">
				</div>
			</form>

		</div>
	</div>
	
	</div>

	<div id="media_edit">
		<header>
			<p>사진편집</p>
		</header>
		<hr>

		<nav id="media_effect_nav">
			<input type="hidden" id="media_index" value="0">
			<ul id="media_effect_tab"
				style="list-style: none; margin: 0; padding: 0; background-color: #efefef;">

				<li class="media_effect_list"><label for="radio_effect_filter">
						<p class="media_effect_list_p">필터</p>
				</label> <input type="radio" checked class="effect_radio"
					id="radio_effect_filter" style="display: none;"
					name="media_effect_select"></li>

				<li class="media_effect_list"><label for="radio_effect_cutting">
						<p class="media_effect_list_p">자르기</p>
				</label> <input type="radio" class="effect_radio" id="radio_effect_cutting"
					style="display: none;" name="media_effect_select"></li>

				<li class="media_effect_list"><label for="radio_effect_tag">
						<p class="media_effect_list_p">태그</p>
				</label> <input type="radio" class="effect_radio" id="radio_effect_tag"
					style="display: none;" name="media_effect_select"></li>

			</ul>
		</nav>

		<nav id="media_filter_nav" class="media_effect_sub_tab">
			<ul id="media_filter_tab"
				style="list-style: none; margin: 0px; padding: 0;">
				<li class="media_filter_list"><label
					class="media_filter_preview_selected" for="radio_effect_normal">

				</label> <input type="radio" checked id="radio_effect_normal"
					class="filter_radio" name="media_filter_select"></li>

				<li class="media_filter_list"><label
					class="media_filter_preview" for="radio_effect_grayscale">

				</label> <input type="radio" id="radio_effect_grayscale"
					class="filter_radio" name="media_filter_select"></li>

				<li class="media_filter_list"><label
					class="media_filter_preview" for="radio_effect_brightness">

				</label> <input type="radio" id="radio_effect_brightness"
					class="filter_radio" name="media_filter_select"></li>

				<li class="media_filter_list"><label
					class="media_filter_preview" for="radio_effect_test"> </label> <input
					type="radio" id="radio_effect_test" class="filter_radio"
					name="media_filter_select"></li>

			</ul>

		</nav>

		<nav id="media_cut_nav" class="media_effect_sub_tab"></nav>

		<nav id="media_tag_nav" class="media_effect_sub_tab"></nav>

		<section>

			<article id="canvas_Wrapper">
				<canvas id="filter_canvas"></canvas>
				<div id="cut_tool" draggable="true">
					<img src="../img/postwrite/edge-icon.png" id="left_top_edge"
						class="edge"> <img src="../img/postwrite/edge-icon.png"
						id="right_top_edge" class="edge"> <img
						src="../img/postwrite/edge-icon.png" id="left_bottom_edge"
						class="edge"> <img src="../img/postwrite/edge-icon.png"
						id="right_bottom_edge" class="edge">
				</div>

				<div id="tag_box">
					<input id="xPoint" type="hidden" value="0"> <input
						id="yPoint" type="hidden" value="0">
				</div>

				<div id="tag_wrapper">
					<div id="search_wrapper">
						<input type="text" id="tag_name" placeholder="이름을 입력하세요.">

						<div id="icon_wrapper">
							<div id="tag_close_icon"></div>
							<div id="tag_search_icon"></div>
						</div>
					</div>

					<div id="list_wrapper"></div>
				</div>
			</article>
		</section>

		<input type="button" class="button_bbs"
			style="position: absolute; right: 125px; bottom: 10px;" value="확인"
			onclick="confirmMediaEdit()"> <input type="button"
			class="button_bbs"
			style="position: absolute; right: 20px; bottom: 10px;" value="닫기"
			onclick="cancelMediaEdit();">


	</div>
	
	
	<script>
	
	//게시글 쓸때 ajax통신하는 ㅁ네소드
        function postFileData() {
      		var formEx = document.createElement('form');
      		formEx.method="post";
      		formEx.enctype="multipart/form-data";
      		document.body.appendChild(formEx);
      		
        	var data = new FormData();
        	var fileArray = new Array();
        	var newspeed;
        
        	
			for (var i = 0; i < allFiles.length; i++) {
				var tagList = $('.tag_list_wrapper:eq(' + i + ')');
				var tagListArray = new Array();
				var fileName = 'file' + i;
				var fileObj;
				
				data.append(fileName, allFiles[i]);
				
				for (var j = 0; j < tagList.children().length; j++) {
					console.log('왜그러세요?' + j);
					var tagObj = {
							"tagIndex":j,
							"mediaIndex":(i + 1),
							"x": $('.tag_list_wrapper:eq' + '(' + i + ') .tag_user_wrapper:eq(' + j + ') .xPoint').val(),
							"y": $('.tag_list_wrapper:eq' + '(' + i + ') .tag_user_wrapper:eq(' + j + ') .yPoint').val(),
							"userNo": $('.tag_list_wrapper:eq' + '(' + i + ') .tag_user_wrapper:eq(' + j + ') .userNo').val()		
					};
					
					tagListArray.push(tagObj);
				}
				
				fileObj = {
						"mediaIndex":(i + 1),
						"fileName":fileName,
						"tagList":tagListArray
				}
				
				fileArray.push(fileObj);
			}
			
			newspeed = {
					"fileList":fileArray,
					"content":$('#posting_content').html()
			};

			data.append('content',$('#posting_content').html());
			data.append('newspeed',JSON.stringify(newspeed));

         	$.ajax({
        		url:'<%=request.getContextPath()%>/view/postwrite',
        		type:"post",
        		data:data,
                processData: false, 
                contentType: false, 
                success: function(data, textStatus, jqXHR)
                {
                	location.reload();	
                }, error: function(jqXHR, textStatus, errorThrown)
                {
                    console.log('ERRORS: ' + textStatus);
                    console.log(errorThrown);
                }
        	});
         	
         	$(formEx).remove();	
         	
         	hidePostingWhole();
        }
        
        function requestFollowList() {
        	var userNo = 0;	

           	$.ajax({
        		url:'<%=request.getContextPath()%>/user/usertag',
        		type:"post",
        		data:{"userNo": <%=request.getSession().getAttribute("userNo").toString()%>, "userName":$('#tag_name').val()}, 
                success: function(data, textStatus, jqXHR)
                {
					console.log(data);
					addFollowList(data);
                },
                error: function(jqXHR, textStatus, errorThrown)
                {
                    console.log('ERRORS: ' + textStatus);
                    console.log(errorThrown);
                }
        	});
        
        }
        
        </script>
        
        <script>
		function showProfilePhotoMenu(){  //프로필 클릭시 메뉴 보여주기
			$('#updatePhoto').fadeIn();
		}
		
		$(document).keyup(function(e) {	//esc누르면 프로필 메뉴 닫기
		    if (e.keyCode == 27) { 
		    	$('#updatePhoto').fadeOut();
		    	$('#userPhotoMenu').fadeOut();
		    	/* $('#reportBgk').fadeOut(); */
		   }
		});
		
		$("#updatePhoto").click(function(){	//배경 클릭시 프로필 메뉴 닫기
			$("#updatePhoto").fadeOut();
		});
		
		 $("#updatePhotoContent>button:nth-child(1)").click(function(){  //프로필사진 바꾸기
	   		 $('#uploadProfilePhoto1').click();  //프로필사진 클릭시 숨겨둔 input file 실행 
	     });
		 $("#updatePhotoContent>button:nth-last-child(2)").click(function(){  //스토리 변경
			 $('#uploadProfileStory').click(); 	//hidden 인풋 실행 
	     });
		 $("#updatePhotoContent>button:nth-last-child(1)").click(function(){  //스토리 보기
			 
			 if($("#storyContent>div>video").prop('muted', false)){
				 $("#storyContent>div>video").prop('muted', true);
				 $("#storyContent>div>img").attr("src","<%=request.getContextPath() %>/img/soundOff.png");
			 }
		 
			 $("#storyContent").fadeIn();
				updateBar=setInterval(update,10);
				if(!$('#storyProgressBar').width()==0){
					myMovie.currentTime=0;
					progressBar.style.width='0px';
				    window.clearInterval(updateBar);
				}
				if(myMovie.paused){
					myMovie.play();
				}
	     });
		 
		 $(document).ready(function(){
			    barSize=480;
			    myMovie=document.getElementById('storyVideo');
			    playButton=document.getElementById('playButton');
			    bar=document.getElementById('storyProgressBarBKG');
			    progressBar=document.getElementById('storyProgressBar');
			    
			    <%if(userStory.getPath()!=""){%> //스토리있으면 테두리 추가
			    	$('#profilePhoto').css({'background-image': 'linear-gradient(white, white), radial-gradient(circle at top left, #FAED7D,#F361DC)','background-origin': 'border-box','background-clip': 'content-box, border-box'});
			    <%}%>
		});
	
		function update(){
		    if(!myMovie.ended){
		        var size=parseInt(myMovie.currentTime*barSize/myMovie.duration);
		        progressBar.style.width=size+'px';
		    }else{
		        progressBar.style.width='0px';
		        window.clearInterval(updateBar);
		        $("#storyContent").fadeOut();
		    }
		 }
		
		$('#storyProgressBarBKG').click(function(e) { //스토리 프로그레스바 클릭시 동영상 거기로 이동
			e.stopPropagation();
		    if(!myMovie.paused && !myMovie.ended){
		        var mouseX = e.pageX-bar.offsetLeft;
		        var newtime = mouseX*myMovie.duration/barSize;
		        myMovie.currentTime = newtime;
		        progressBar.style.width=mouseX+'px';
		    }
		 });
		
		$("#storyContent>div>video").click( function (e){ //스토리영상 클릭시 음소거 토글
			e.stopPropagation();
		    if( $("#storyContent>div>video").prop('muted')){
		          $("#storyContent>div>video").prop('muted', false);
		          $("#storyContent>div>img").attr("src","<%=request.getContextPath() %>/img/soundOn.png");
		    } 
		    else {
		      $("#storyContent>div>video").prop('muted', true);
		      $("#storyContent>div>img").attr("src","<%=request.getContextPath() %>/img/soundOff.png");
		    }
		  });
		 
		$(document).keyup(function(e) {  //esc누르면 스토리 꺼짐
		    if (e.keyCode == 27) { 
		    	$("#storyContent>div>video").prop('muted', true);
		    	$("#storyContent").fadeOut();
		   }
		});
		 
		$("#storyContent").click(function(e){   //스토리밖 클릭시 스토리 꺼짐
			$("#storyContent>div>video").prop('muted', true);
			$("#storyContent").fadeOut();
		});
		
	</script>

</body>
</html>
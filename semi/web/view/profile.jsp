<%@page import="com.gamstar.model.vo.NewspeedMedia"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.gamstar.model.vo.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<script src="http://code.jquery.com/jquery.min.js"></script> 
    <link href="https://fonts.googleapis.com/css?family=Jua|Song+Myung|Stylish|Yeon+Sung|Gothic+A1&amp;subset=korean"
        rel="stylesheet">
       
       <!-- 차단시 컨펌박스 --> 
	<link href="<%=request.getContextPath()%>/css/alertBox.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/alertBox.js"></script>

</head>
<body>

	<% User user=(User)request.getAttribute("userData");
	   ArrayList<User> followerDataArray=(ArrayList<User>)request.getAttribute("followerDataArray");
	   ArrayList<User> followDataArray=(ArrayList<User>)request.getAttribute("followDataArray");
	   ArrayList<User> blockDataArray=(ArrayList<User>)request.getAttribute("blockDataArray");
	   ArrayList<NewspeedMedia> content1DataArray=(ArrayList<NewspeedMedia>)request.getAttribute("content1DataArray");
	   ArrayList<NewspeedMedia> storageContentDataArray=(ArrayList<NewspeedMedia>)request.getAttribute("storageContentDataArray");
	   ArrayList<NewspeedMedia> tagContentDataArray=(ArrayList<NewspeedMedia>)request.getAttribute("tagContentDataArray");
	   boolean isFollowed=(boolean)request.getAttribute("isFollowed");	   
	%>

	 <div class='fullScreen'>
        <div class="profileTop">
           <div id='BackgroundPhotoIconDv' onclick="changeBackgroundPhoto();"><img src='<%=request.getContextPath()%>/img/camera20.png'><label>배경 사진 업데이트</label></div>
           <img src="<%=request.getContextPath()%><%=user.getProfileBackgroundPhoto() %>" alt="" id="profileBackgroundPhoto" onclick="changeBackgroundPhoto();">
            <div class="profileTopPhoto">
                <button id='profilePhotoBt' onclick="changePhoto();">
                    <div id="profilePhotoHover"><img src="<%=request.getContextPath()%>/img/camera20.png" alt="" ><label >업데이트</label></div>
                    <img id='profilePhoto' src="<%=request.getContextPath()%><%=user.getProfilePhoto() %>" alt="사진이 안나와요ㅠㅜ" >
                </button>
            </div>
            <div class="profileTopContent">
                <label id="profileName" onclick="location.href='profile?uu=<%=user.getNo()%>'"><%=user.getName()%></label>
                <button class="profileModify" id='profileModify' onclick="location.href='<%=request.getContextPath()%>/view/profilemodifyStart'"><img src="<%=request.getContextPath()%>/img/modify.png"><label for="profileModify">프로필 편집</label></button>
                <button class="profileModify" id='profileWrite' onclick="location.href='<%=request.getContextPath()%>/view/profilemodifyStart'"><img src="<%=request.getContextPath()%>/img/write.png"><label for="profileWrite">글 작성하기</label></button>
                <button class="profileModify" id='profileBlockBt' onclick="blockClick();"><img src="<%=request.getContextPath()%>/img/blockBtOff.png"><label for="profileBlockBt">차단하기</label></button>
				<button class="profileModify" id='profileFollowBt' onclick="followClick();"><img src="<%=request.getContextPath()%>/img/followOff.png"><label for="profileFollowBt">팔로우</label></button>
			
            </div>
        </div>
 		
 		<form action="<%=request.getContextPath()%>/view/photoModify" method="post" id="modifyPhoto" enctype="multipart/form-data">
	        <input type="file" name="uploadProfilePhoto1" id="uploadProfilePhoto1" accept="image/*" this.select();>
	        <input type="file" name="uploadProfilePhoto2" id="uploadProfilePhoto2" accept="image/*" this.select();>
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
            </ul>
        </div>
       
        <div id="profileContent1"></div>  
        <div id="profileContent2"></div>  
        <div id="profileContent3"></div>  
        <div id="profileContent4"></div> 
        <form action="mypage.jsp" method="post">
        	<div id="profileContent5"></div>
        	<input type="hidden" name="followerId" value="followerId"/>
        </form> 
        <form action="mypage.jsp" method="post">
        	<div id="profileContent6"></div>
        	<input type="hidden" name="followId" value="followId"/>
        </form>  

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
            <% HttpSession se=request.getSession();
             
            if(user.getNo()==6){  //내 페이지
            	System.out.println("아이디 같네");
            	%>
            	 $('#profileFollowBt').css("display","none");
            	 $('#profileBlockBt').css("display","none");
         	<%}
         	else{//다른사람 페이지
         		System.out.println("다른사람 페이지네"); 
         		
         		if(isFollowed){%>
         		$('#profileFollowBt>label').text("팔로우됨");
        		$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOn.png");
        		$('#profileFollowBt').css("background-color","rgb(103,153,255)");  
        		$('#profileFollowBt').css("color","white"); 
        		<%}
         		else{%>
        			$('#profileFollowBt>label').text("팔로우");
            		$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOff.png");
            		$('#profileFollowBt').css("background-color","#F6F6F6"); 
            		$('#profileFollowBt').css("color","black");
            		<%-- <%if(user.getDisclosure()==0){%>
            		$('#profileMenuRbt1,#profileMenuRbt2,#profileMenuRbt3,#profileMenuRbt4,#profileMenuRbt5,#profileMenuRbt6')
            		<%}%> --%>
        		<%}%>
         		
         		$('#profileModify').css("display","none");
         		$('#profileWrite').css("display","none");
         		$('#BackgroundPhotoIconDv').css("display","none");
         		$('#profilePhotoHover').css("display","none");
         		$('#profileBackgroundPhoto').removeAttr("onclick");
         		$('#profileBackgroundPhoto').css("cursor","default");
         		$('#profilePhotoBt').removeAttr("onclick");
         		$('#profilePhotoBt').css("cursor","default");
         		$('#blockMenu').css("display","none");
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
            function changePhoto() {
                $('#uploadProfilePhoto1').click();  //프로필사진 클릭시 숨겨둔 input file 실행                   
            }

            function readURL1(input) { //들어온파일 가져와서 e.taget.result로 이미지 받아서 #modifyScreenMainPhoto 사진속성 바꿔주기
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        console.log(e.target.result);
                        $('#profilePhoto').attr('src', e.target.result);
                    }
                    console.log(input.files[0].name);
                    reader.readAsDataURL(input.files[0]); //url읽어드린다

                    $('#modifyPhoto').submit(); 
                }
            }

            $('#uploadProfilePhoto1').change(function () { //숨겨둔 input file에서 그림 선택하면 실행되는 함수 
                console.log(this.files[0]);
                readURL1(this);
            });

         	//배경사진 바꾸기
            function changeBackgroundPhoto() {
                $('#uploadProfilePhoto2').click();  //프로필사진 클릭시 숨겨둔 input file 실행                   
            }

            function readURL2(input) { //들어온파일 가져와서 e.taget.result로 이미지 받아서 #modifyScreenMainPhoto 사진속성 바꿔주기
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        console.log(e.target.result);
                        $('#profileBackgroundPhoto').attr('src', e.target.result);
                    }
                    console.log(input.files[0].name);
                    reader.readAsDataURL(input.files[0]); //url읽어드린다
        
                     $('#modifyPhoto').submit(); 
                    
                }
            }

            $('#uploadProfilePhoto2').change(function () { //숨겨둔 input file에서 그림 선택하면 실행되는 함수 
                console.log(this.files[0]);
                readURL2(this);
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
		if(($('#profileFollowBt>label').text())==("팔로우")){
    		$('#profileFollowBt>label').text("팔로우됨");
    		$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOn.png");
    		$('#profileFollowBt').css("background-color","rgb(103,153,255)");  
    		$('#profileFollowBt').css("color","white"); 
    	}
    	else{
    		$('#profileFollowBt>label').text("팔로우");
    		$('#profileFollowBt>img').attr("src","<%=request.getContextPath()%>/img/followOff.png");
    		$('#profileFollowBt').css("background-color","#F6F6F6"); 
    		$('#profileFollowBt').css("color","black");
    	}
		
		<%-- location.href='<%=request.getContextPath()%>/view/updatefollowblock?follow='+$('#profileFollowBt>label').text()+'&uu=<%=user.getUserNo()%>'; --%>
		$.ajax({
    		url:'<%=request.getContextPath()%>/view/updatefollowblock',
    		type:"POST",
    		data:{"follow":$('#profileFollowBt>label').text(),"uu":<%=user.getNo()%>}, 
    	});
		
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
				                src: '<%=request.getContextPath()%>/upload/'+profilePhoto
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
                		 style:"background-image: url('<%=request.getContextPath()%>/upload/newspeed/<%=content1DataArray.get(i).getPath()%>')"
               		 <%}
                	 else{%>
                  		  style:"background-image: url('<%=request.getContextPath()%>/upload/newspeed/videoContent.png')"
              	    <%}%>
                }));
            	
            	 $('#profileContent1>:eq(<%=i%>)').on('click',function(){
            		alert($(this).attr("value")); 
            	 });

             <%}%>  

           //3.저장됨 컨텐츠
             <%for(int i=0;i<storageContentDataArray.size();i++){%>
            	 $('#profileContent3').append($('<div/>',{
                 class:'profileContent134Photo',
                 value:'<%=storageContentDataArray.get(i).getNewspeedNo()%>',
                 <%if(storageContentDataArray.get(i).getType()==0){%>
                	 style:"background-image: url('<%=request.getContextPath()%>/upload/newspeed/<%=storageContentDataArray.get(i).getPath()%>')"
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
            	 
            	 $(('#profileContent3>div:nth-child(<%=i+1%>)')+'>button').css("display","none");
            	 $(('#profileContent3>div:nth-child(<%=i+1%>)')+'>button').css("width","20px");
            	 $(('#profileContent3>div:nth-child(<%=i+1%>)')+'>button').css("height","20.5px");
            	 
  				 $(('#profileContent3>div:nth-child(<%=i+1%>)')+'>button').on("click",function(e){
  				 e.stopPropagation(); //부모 이벤트 실행 안되게
  				 var deleteNewspeedDiv=$(this).parent();
  				 var storedNewspeedNo=$(this).parent().attr("value");
  				 $('body').alertBox({
  				        title: '저장된 게시물을 지우시겠습니까?',
  				        lTxt: '아니요',
  				        lCallback: function(){alert(storedNewspeedNo);},
  				        rTxt: '네',
  				        rCallback: function(){
  							 $.ajax({
  								url:'<%=request.getContextPath()%>/view/deleteStoredNewspeed',
  								type:"POST",
  								data:{"storedNewspeedNo":storedNewspeedNo},
  								success:function(){
  									deleteNewspeedDiv.remove();
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
             		alert($(this).attr("value")); 
             	 });
            	 
             <%}%> 
                  
            //4.태그됨 컨텐츠
	         <%for(int i=0;i<tagContentDataArray.size();i++){%>
            	 $('#profileContent4').append($('<div/>',{
                 class:'profileContent134Photo',
                 value:'<%=tagContentDataArray.get(i).getNewspeedNo()%>',
                 <%if(tagContentDataArray.get(i).getType()==0){%>
                 	style:"background-image: url('<%=request.getContextPath()%>/upload/newspeed/<%=tagContentDataArray.get(i).getPath()%>')"
                 <%}
            	 else{%>
            	 	style:"background-image: url('<%=request.getContextPath()%>/upload/newspeed/videoContent.png')"
                 <%}%> 
            	 
             	}));
            	 
            	 $('#profileContent4>:eq(<%=i%>)').on('click',function(){
              		alert($(this).attr("value")); 
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
 					                src: '<%=request.getContextPath()%>'+profilePhoto
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
                src: '<%=request.getContextPath()%>/upload/<%=followerDataArray.get(i).getProfilePhoto()%>'
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
	 					                src: '<%=request.getContextPath()%>'+profilePhoto
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
	               src: '<%=request.getContextPath()%>/upload/<%=followDataArray.get(i).getProfilePhoto()%>'
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
			    src: '<%=request.getContextPath()%><%=blockDataArray.get(i).getProfilePhoto()%>'
			 }));
			
			 $('#profileContent2>#<%=blockDataArray.get(i).getNo()%>').append($('<label/>',{
				text:'<%=blockDataArray.get(i).getName()%>'
			 }));
			 
			 $('#profileContent2>#<%=blockDataArray.get(i).getNo()%>').append($('<button/>',{
				class:'contentCancelBt'
			 }));
			 
			 $('#profileContent2>#<%=blockDataArray.get(i).getNo()%>').on("click",function(){
				 var deleteBlockId=$(this).parent().attr("id");
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
				
        });
			 
    </script> 




</body>
</html>
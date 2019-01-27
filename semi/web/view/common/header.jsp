<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>

		<link href="<%=request.getContextPath()%>/css/header.css" rel="stylesheet" type="Text/css">

</head>
<body>

     <nav id='navi'>
        <div id="naviWrap">
           <div id="gamstarLogo"> <p id="gamstarTitle">GamStar</p> </div>
           <input type="text" id="search" name="search" style="width:200px"></input>
			<div id='searchBody'>
                <input type="search" id="rara" value="" placeholder="search..."/>
                <img src="<%=request.getContextPath()%>/img/newspeeddetailview/searchView.png" id="searchView">
                <img src="<%=request.getContextPath()%>/img/newspeeddetailview/searchDelete.png" id="searchDelete">
            </div>
            <div id="infoBox"> <div id="infoSentence">Search Something :)</div> </div>
            <div id="naviIconWrap">
                <div id="myHomIcon"><img id="myHomIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/myPage.png"></div>
                <div id="alramIcon"></div>
                <div id="slideIcon"><img id="slideIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/setting.png"></div>
                <div id="slideBox">
                        <div id="infoChange"><div class="infoChangeText"><a href="<%=request.getContextPath()%>/view/profilemodifyStart">개인정보설정</a></div></div>
                        <hr class="slideHr">
                        <div id="chatting" onclick="chat()"><div class="chattingText" ><a href="#">채팅</a></div></div>
                        <hr class="slideHr">
                        <div id="contactBoard" onclick="supportBtn()"><div class="contactBoardText"><a href="#">문의하기</a></div></div>
                        <hr class="slideHr">
                        <div id="logout"><div class="logoutText"><a href="#">로그아웃</a></div></div>
                </div>
            </div>
            <div class="iconBack"><img id ="iconBackImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/logoBack.png"></div> 
        </div>
    </nav>

</body>
</html>

<script>
	$('#slideIcon').click(function slideBoxClick(){ //수정아이콘 클릭 이벤트
       $('#slideBox').slideToggle('slow');
   });
   
	$('#myHomIcon').click(function(){
		location.href='<%=request.getContextPath()%>/view/profile';
	});
	
	$('#gamstarLogo,.iconBack').click(function(){
		location.href='<%=request.getContextPath()%>';
	});

	$('#rara').keyup(function(){ //검색창 입력 이벤트
		console.log("여기니?");
	       $('#infoSentence').css('opacity', '0');
	       $('#infoBox').removeClass("infoBox");

	       
	       

	       var searchStr = $('#rara').val();
	       if(searchStr == ""){
	          
	       }
	       else{
	           $.ajax({
	              url:"<%=request.getContextPath()%>/mainsearchservlet",
	              data:{"searchStr":searchStr},
	              success: function(data){
	            	  $('.searchPeople').remove();
	                 /* console.log("서치 통신");
	                 console.log("크기 : "+Object.keys(data[0]).length); */
	                 if(Object.keys(data[0]).length == 0){
	                  
	                    $('#searchNull').remove();
	                    $('#infoBox').append('<div id="searchNull"><p id="noSearchEmoticon">(=*x*=)</p><p id="noSearchSentence">no users found!</p></div>');
	                 }
	                 
	                 for(var i = 0; i<Object.keys(data[0]).length; i++){
	                    $('#searchNull').remove();          
	                    $('#infoBox').append('<div class="searchPeople" id='+data[0]["follow"+i].userNo+'><div class="searchImgWrap"><img class="searchImg" src=<%=request.getContextPath()%>/' + data[0]["follow"+i].profilePhoto + '></div><div class="searchNick"><a class="searchNickLink" href="/">' + data[0]["follow"+i].userName + '</a></div></div>');   
	                    
	                    $('#infoBox>#'+data[0]["follow"+i].userNo).click(function(){
	                    	location.href='<%=request.getContextPath()%>/view/profile?uu='+$(this).attr("id");
	                    });
	                 }
	                 
	                

	              },
	              error: function(){
	                 
	              }
	              
	           })
	       }
	       
			$('#infoBox>.searchPeople').click(function(){
				console.log($(this).attr("value"));
			});
	      
	       
	       $('#infoBox').css({'width':'300px','height':'400px','border-radius':'10px','overflow-Y':'auto'});
	       
	   
	       if($('#rara').val() == ""){

	          $('#searchNull').remove();
	           $('#infoBox').removeClass("removeInfoBox");
	           $('#infoBox').addClass("infoBox");
	           $('#searchDelete').css('display','inline');
	           $('#infoBox').css('border','1px solid gray');
	           $('#rara').attr('placeholder','');
	           $('#infoBox').css('width','400px').css('height','0px');
	           $('#infoSentence').css('opacity', '1');
	           $('#infoBox').css('overflow-Y','hidden');
	       }
	   });
	
</script>
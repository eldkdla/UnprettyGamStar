<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%	
  	String userNo = (String)request.getAttribute("userNo");
	String userName = (String)request.getAttribute("userName");  
%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Gamstar</title>
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script> 
    <intercept-url pattern="/favicon.ico" access="ROLE_ANONYMOUS" />

	<link href="<%=request.getContextPath()%>/css/mainCss.css" rel="stylesheet" type="text/css">
	<style>
	    body,
        html {
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
            background-color: gainsboro;
            overflow-x: hidden;
        }
	</style>
</head>

<body>

    <nav id="topHeader"></nav>

    <div id='fullBody'>
        <div id='feedBody'></div>

        <div id='noteBody'>
            <div id='storyNote'>
                <div id='noteHeader'></div>
                <div id='noteContent'></div>
                <div id='noteResize'> <div id="noteResizeBtn">v</div> </div>
            </div>
        </div>
    </div>



    <nav id='navi'>
        <div id="naviWrap">
        	<div id="gamstarLogo"> <p id="gamstarTitle">GamStar</p> </div>
            <div id='searchBody'>
                <input type="search" id="search" value="" results=5 placeholder="search..."/>
                <img src="<%=request.getContextPath()%>/img/newspeeddetailview/searchView.png" id="searchView">
                <img src="<%=request.getContextPath()%>/img/newspeeddetailview/searchDelete.png" id="searchDelete">
            </div>
            <div id="infoBox"> <div id="infoSentence">Search Something :)</div> </div>
            <div id="naviIconWrap">
                <div id="myHomIcon"><img id="myHomIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/myPage.png"></div>
                <div id="alramIcon"><img id="alramIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/alramBlue.png"></div>
                <div id="slideIcon"><img id="slideIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/setting.png"></div>
                <div id="slideBox">
                        <div id="infoChange"><div class="infoChangeText"><a href="#">개인정보설정</a></div></div>
                        <hr class="slideHr">
                        <div id="chatting"><div class="chattingText"><a href="#">채팅</a></div></div>
                        <hr class="slideHr">
                        <div id="contactBoard"><div class="contactBoardText"><a href="#">문의하기</a></div></div>
                        <hr class="slideHr">
                        <div id="logout"><div class="logoutText"><a href="#">로그아웃</a></div></div>
                </div>
            </div>
            <div class="iconBack"><img id ="iconBackImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/logoBack.png"></div>
            <div class="naviIcon"><img id ="naviIconImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/logo.png"></div>    
        </div>


    </nav>

    <div class="icon"></div>
    <div class="icon"></div>
    <div class="icon"></div>
    <div class="icon"></div>
    <div class="icon"></div>
    <div class="icon"></div>

    <script>

        var commentArray = [{ "NangPP": "고양이 핵기엽 ...." }, { "Im_gangNam": "고양이 분양가능한가요??" }, { "eee=vvv": "호호 고양이가 너무 이쁘네요~~ 어디 종인가요? 그리고 종종 놀러올게요~" }, { "qqasdasdasdasd": "맞팔해요~" }];
        var feedCount = 0;
        var limite = 20;
        var checkLike = 0;
 		
 		$(function beforeAjax(){
 			
 			console.log('아약스 실행~');
    	  	$.ajax({
    	 		url:"<%=request.getContextPath()%>/mainnewspeedservlet.do",
    	 		type:"post",
    	 		data:{"limite":limite},
    	 		success: function(data){
    	 			console.log("통신");

    		        $(function feedSet() {
    		        	
    						console.log('피드셋');
    			            
    			            var feedSetSize = Object.keys(data[0]).length;
    				        
    			            for (; feedCount < feedSetSize; feedCount++) {
    			            	
    			            	var userSet = [data[5]["feedUser"+feedCount].userName, data[5]["feedUser"+feedCount].profilePhoto]; //유저 셋팅
     			            	var contentSet =[data[0]["content"+feedCount].content, data[0]["content"+feedCount].newspeedNo, data[0]["content"+feedCount].userNo];//컨텐트 셋팅  
     			            	
        						var likeSet = [];
        						for(var i=0;i<Object.keys(data[3]).length; i++){ //좋아요 셋팅
        							if(data[0]["content"+feedCount].newspeedNo == data[3]["like"+i].newspeedNo){
        								
        								likeSet.push([data[3]["like"+i].userNo]);
        							}
        						}
        						
        						var mediaSet = [];
        						var mediaType =[];
 
        						for(var i=0; i<Object.keys(data[2]).length; i++){ //미디어 셋팅
        							if(data[0]["content"+feedCount].newspeedNo == data[2]["media"+i].newspeedNo){
        								
        								mediaSet.push({[data[2]["media"+i].mediaIndex]:data[2]["media"+i].mediaPath});
        								mediaType.push([data[2]["media"+i].mediaType]);
        								
        							}
        						}
        						
        						
        						var commentSet = [];
         						for(var i=0; i<Object.keys(data[1]).length; i++){ //코멘트 셋팅
        							if(data[0]["content"+feedCount].newspeedNo == data[1]["comment"+i].newspeedNo){

        								commentSet.push({[data[1]["comment"+i].userName] : data[1]["comment"+i].content});
        							}
        						}
         						
         						var tagSet = [];
         						for(var i=0;i<Object.keys(data[4]).length;i++){  //이미지태그 셋팅 (아직 미구현)
         							if(data[0]["content"+feedCount].newspeedNo == data[4]["tag"+i].newspeedNo){
         							
         							}
         						}
    			               

    			                $('#feedBody').append('<div class="feed"></div>');
    			                $('#feedBody .feed:eq(' + feedCount + ')').append('<a class="link" href="#"></a>');
    			                $('#feedBody .feed:eq(' + feedCount + ') .link').append('<img class="titleImg" src="<%=request.getContextPath()%>/'+userSet[1] + '">');
    			                $('#feedBody .feed:eq(' + feedCount + ') .link').append('<p class="nick">' + userSet[0] + '</p>');

    			                $('#feedBody .feed:eq(' + feedCount + ')').append('<div class="imgBody"></div>');

    			                for(var i=0; i < mediaSet.length; i++){  //미디어 셋팅
	
									
    			                	if(mediaType[i] == "0"){

    			                		 $('#feedBody .feed:eq(' + feedCount + ') .imgBody').append('<div class="imgWrap"><img class ="imgSet" src="<%=request.getContextPath()%>/'+Object.values(mediaSet[i])+'"></div>');
    			                	}
     			                	else{
     
    			                		 $('#feedBody .feed:eq(' + feedCount + ') .imgBody').append('<div class="imgWrap"><video class="videoSet" muted autoplay loop controls="controls" src="<%=request.getContextPath()%>/'+Object.values(mediaSet[i]) + '"></video></div>');
    			                	} 
    			          		};
	
    			            
    			                $('#feedBody .feed:eq(' + feedCount + ') .imgBody').append('<div class="prev"><img class="prevImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_prev_icon.png" ></div><div class="next"><img class="nextImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_next_icon.png" ></div>');
    
    			                $('#feedBody .feed:eq(' +feedCount + ')').append('<div class ="bar"></div>');
    			                $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<img class="like" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_like.png">');
    			                $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<img class="commentImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_comment.png">');
    			                $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<img class="saveImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_store_icon.png">');
    		                    $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<img class="report" src="<%=request.getContextPath()%>/img/newspeeddetailview/report_icon.png">');
    			                $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<p class="showLike">' + likeSet.length + 'Liked</p>');
    			                $('#feedBody .feed:eq(' + feedCount + ')').append('<div class="commentWrap"></div>')
    			                $('#feedBody .feed:eq(' + feedCount + ') .commentWrap').append('<div class = titleBody><div class="titleNick">' + userSet[0] + '</div><div class="title">' + contentSet[0] + '</div></div>');
    			                $('#feedBody .feed:eq(' + feedCount + ') .commentWrap .titleBody').append('<p class=moreView>더보기</p>');
    			                $('#feedBody .feed:eq(' + feedCount + ') .commentWrap').append('<div class="commentBody"></div>');
    			                $('#feedBody .feed:eq(' + feedCount + ') .commentWrap').append('<div class="writeBar"><input type="text" class="writeText" placeholder="    소통하기"/><input type="button" class="textSubmit"></div>');
    			         
    /* 		                        var searchIndex =0;
    		                        // var searchBlank =0;
    		                        for(var i =0; i < title.match(/#/g).length;i++){ //태그 글자 걸러내기

    		                            var tagIndex = title.indexOf('#',searchIndex);
    		                            searchIndex = tagIndex+1;

    		                         
    		                            var endIndex = title.indexOf(' ',searchIndex);
    		                            var tagValue = title.substring(tagIndex, endIndex);

    		                            // title.replace(tagValue,'<p class="tagSpan">'+tagValue+'하하호호하호'+'</p>');
    		                            console.log(tagValue);
    		                            title.replace(tagValue,'***');
    		                        }
    		                        console.log(title);
    		                        $('#feedBody .feed:nth-child(' + feedCount + ') .title').html(title);
    		                    }  */
    		                    
    		                    if(mediaSet.length == 1){
    		                    	 $('#feedBody .feed:eq(' + feedCount + ') .nextImg').css('display','none');
    		                    	 $('#feedBody .feed:eq(' + feedCount + ') .prevImg').css('display','none');
    		                    }else{
    		                    	$('#feedBody .feed:eq(' + feedCount + ') .prevImg').css('display','none');
    		                    }
    		                    

    			                if (!checkLike == 0) { //좋아요 체크 아직 미구현
    			                    $('.feed:eq(' + feedCount + ') .bar .like').attr('src', '' + liked + '');
    			                }

    			                var commentHeight = $('#feedBody .feed:eq(' + feedCount + ') .titleBody').height();
    			                if (commentHeight > 70) {  //댓글 더보기 체크
    			                    $('#feedBody .feed:eq(' + feedCount + ') .titleBody').css('height', '40px');
    			                    $('#feedBody .feed:eq(' + feedCount + ') .moreView').css('display', 'inline');

    			                }

    			                for (var i = 0; i < commentSet.length; i++) { //피드 댓글 셋팅
    			                    $('#feedBody .feed:eq(' + feedCount + ') .commentWrap .commentBody').append('<div class="commentSort"><div class="commentNick">' + Object.keys(commentSet[i]) + '</div><div class="comment">' + Object.values(commentSet[i]) + '</div></div>');
    			                }
    			                
    			            }
    	     			   	var image = new Image();
    	    			  	image.src = "<%=request.getContextPath()%>/"+Object.values(mediaSet[0]);
    	    			  	image.onload = function(){
    	    			 	console.log("처음이미지 온로드");
    	    			      	for(var j =0; j < feedCount; j++){

    	    			        	var imgHeight = $('#feedBody .feed:eq(' + j + ') .imgWrap:eq(0)').css('height');
    	    			            $('#feedBody .feed:eq(' + j + ') .imgBody').css('height', imgHeight);

    	    			        }
    	    				};
    	    				
    	    				$('.videoSet').on('loadedmetadata', function(){
    	    					console.log("처음이미지 비디오로드");
    	    			      	for(var j =0; j < feedCount; j++){

    	    			        	var imgHeight = $('#feedBody .feed:eq(' + j + ') .imgWrap:eq(0)').css('height');
    	    			            $('#feedBody .feed:eq(' + j + ') .imgBody').css('height', imgHeight);
    	    			        }
    	    				});
    	    				


    		        });
    		        
    	 		},
    			error: function(){
    	 			alert("피드 불러오기를 실패하였습니다. 새로고침을 눌러주세요~");
    	 		}
    	     });
		 });
        
    	var scrollCheck = true; //피드 불러오기 콜백 함수 적용
        $(document).scroll(function scroll_feed() { //스크롤 이벤트 

			
          /*   if ($(window).scrollTop() == 0) { //nav 스크롤 반응 작동
                $('#navi').css('height', '85px');
                $('.naviIcon').css('height', '60px').css('width', '60px');
                $('#naviIconImg').css('height', '40px').css('width', '40px');
 

            } else {

                $('#navi').bind('mouseenter', function mouseHover() { //nav hover 기능
                    $(this).css('height', '85px');
                    $('.naviIcon').css('height', '60px').css('width', '60px');
                    $('#naviIconImg').css('height', '40px').css('width', '40px');
                });

                $('#navi').bind('mouseleave', function mouseLeave() { //nav hover 빠져나왔을때
                    $(this).css('height', '55px');
                    $('.naviIcon').css('height', '30px').css('width', '30px');
                    $('#naviIconImg').css('height', '20px').css('width', '20px');
                });

                $('.naviIcon').bind('mouseenter', function mouseHover() { //nav 중앙 아이콘 hover 기능
                    $(this).css('height', '60px').css('width', '60px');
                    $('#naviIconImg').css('height', '40px').css('width', '40px');
                });

                $('#navi').css('height', '55px');
                $('.naviIcon').css('height', '30px').css('width', '30px'); 
                $('#naviIconImg').css('height', '20px').css('width', '20px');
                $('#navi').addClass('naviGradation');
                $('#navi').addClass('naviGradationBack');   //스크롤 nav 그라데이션 뺵
                
                $('#search').css('width','0px').css('height','0px');
                $('#search').css('border','0px solid gray');
                $('#search').css({'left':'50%','transform':'translate(-50%,-50%)'});

                $('.iconBack').css({ 'left': '50%', 'transform': 'translate(-50%, -50%)', 'opacity': '0' });
                $('#naviIconWrap').css({ 'left': '50%', 'transform': 'translate(-50%, -50%)', 'opacity': '0'});
                $('#naviIconWrap').css({ 'width': '0px', 'height': '0px' });
                $('#slideBox').slideUp();
                
            } */

            if ($(window).scrollTop() > $(document).height() - $(window).height() - 10) { //맨밑까지 스크롤시 자동 피드 생성
            	

            	if(scrollCheck == true){
				scrollCheck = false;
				
				if($(window).scrollTop() < 3000){
					console.log("걸렸다!!");
					scrollCheck = true;
					return;
				}
				
				console.log("너 들어왔니?");
                
                limite += 10;
                console.log("체크" + feedCount + " / " + limite);
                $(function afterAjax(){
                	$.ajax({
                		url:"<%=request.getContextPath()%>/mainnewspeedservlet.do",
                		type:"post",
                		data:{"limite":limite},
                		success: function(data){
                			
            		        $(function feedSetAfter() {

            					setTimeout(function(){

            						console.log('피드셋');
            			            
            			            var feedSetSize = Object.keys(data[0]).length;
            			            var videoFeedIndex = [];
            				        
            			            for (; feedCount < feedSetSize; feedCount++) {
            			            	
            			            	var userSet = [data[5]["feedUser"+feedCount].userName, data[5]["feedUser"+feedCount].profilePhoto]; //유저 셋팅
             			            	var contentSet =[data[0]["content"+feedCount].content, data[0]["content"+feedCount].newspeedNo, data[0]["content"+feedCount].userNo];//컨텐트 셋팅  
             			            	
                						var likeSet = [];
                						for(var i=0;i<Object.keys(data[3]).length; i++){ //좋아요 셋팅
                							if(data[0]["content"+feedCount].newspeedNo == data[3]["like"+i].newspeedNo){
                								
                								likeSet.push([data[3]["like"+i].userNo]);
                							}
                						}
                						
                						var mediaSet = [];
                						var mediaType =[];
         
                						for(var i=0; i<Object.keys(data[2]).length; i++){ //미디어 셋팅
                							if(data[0]["content"+feedCount].newspeedNo == data[2]["media"+i].newspeedNo){
                								
                								mediaSet.push({[data[2]["media"+i].mediaIndex]:data[2]["media"+i].mediaPath});
                								mediaType.push([data[2]["media"+i].mediaType]);
    
                							}
                						}
                						
                						
                						var commentSet = [];
                 						for(var i=0; i<Object.keys(data[1]).length; i++){ //코멘트 셋팅
                							if(data[0]["content"+feedCount].newspeedNo == data[1]["comment"+i].newspeedNo){

                								commentSet.push({[data[1]["comment"+i].userName] : data[1]["comment"+i].content});
                							}
                						}
                 						
                 						var tagSet = [];
                 						for(var i=0;i<Object.keys(data[4]).length;i++){  //이미지태그 셋팅 (아직 미구현)
                 							if(data[0]["content"+feedCount].newspeedNo == data[4]["tag"+i].newspeedNo){
                 							
                 							}
                 						}
            			               

            			                $('#feedBody').append('<div class="feed"></div>');
            			                $('#feedBody .feed:eq(' + feedCount + ')').append('<a class="link" href="#"></a>');
            			                $('#feedBody .feed:eq(' + feedCount + ') .link').append('<img class="titleImg" src="<%=request.getContextPath()%>/' + userSet[1] + '">');
            			                $('#feedBody .feed:eq(' + feedCount + ') .link').append('<p class="nick">' + userSet[0] + '</p>');

            			                $('#feedBody .feed:eq(' + feedCount + ')').append('<div class="imgBody"></div>');
     
            			                for(var i=0; i < mediaSet.length; i++){  //미디어 셋팅
            			                	
        									
            			                	if(mediaType[i] == "0"){
 
            			                		 $('#feedBody .feed:eq(' + feedCount + ') .imgBody').append('<div class="imgWrap"><img class ="imgSet" src="<%=request.getContextPath()%>/' + Object.values(mediaSet[i]) + '"></div>');
            			                	}
             			                	else{

            			                		 $('#feedBody .feed:eq(' + feedCount + ') .imgBody').append('<div class="imgWrap"><video class="videoSet" muted autoplay loop controls="controls" src="<%=request.getContextPath()%>/' + Object.values(mediaSet[i]) + '"></video></div>');

                 								videoFeedIndex.push(feedCount);
            			                	} 
            			          		};

            			            
            			                $('#feedBody .feed:eq(' + feedCount + ') .imgBody').append('<div class="prev"><img class="prevImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_prev_icon.png" ></div><div class="next"><img class="nextImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_next_icon.png" ></div>');
            
            			                $('#feedBody .feed:eq(' +feedCount + ')').append('<div class ="bar"></div>');
            			                $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<img class="like" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_like.png">');
            			                $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<img class="commentImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_comment.png">');
            			                $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<img class="saveImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/newspeed_store_icon.png">');
            		                    $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<img class="report" src="<%=request.getContextPath()%>/img/newspeeddetailview/report_icon.png">');
            			                $('#feedBody .feed:eq(' + feedCount + ') .bar').append('<p class="showLike">' + likeSet.length + 'Liked</p>');
            			                $('#feedBody .feed:eq(' + feedCount + ')').append('<div class="commentWrap"></div>')
            			                $('#feedBody .feed:eq(' + feedCount + ') .commentWrap').append('<div class = titleBody><div class="titleNick">' + userSet[0] + '</div><div class="title">' + contentSet[0] + '</div></div>');
            			                $('#feedBody .feed:eq(' + feedCount + ') .commentWrap .titleBody').append('<p class=moreView>더보기</p>');
            			                $('#feedBody .feed:eq(' + feedCount + ') .commentWrap').append('<div class="commentBody"></div>');
            			                $('#feedBody .feed:eq(' + feedCount + ') .commentWrap').append('<div class="writeBar"><input type="text" class="writeText" placeholder="    소통하기"/><input type="button" class="textSubmit"></div>');
            			         

        /*     		                    var title  =  $('#feedBody .feed:nth-child(' + feedCount + ') .title').html();
            		                    if(title.indexOf('#') != -1){
            		                        
            		                        var searchIndex =0;
            		                        // var searchBlank =0;
            		                        for(var i =0; i < title.match(/#/g).length;i++){ //태그 글자 걸러내기

            		                            var tagIndex = title.indexOf('#',searchIndex);
            		                            searchIndex = tagIndex+1;

            		                         
            		                            var endIndex = title.indexOf(' ',searchIndex);
            		                            var tagValue = title.substring(tagIndex, endIndex);

            		                            // title.replace(tagValue,'<p class="tagSpan">'+tagValue+'하하호호하호'+'</p>');
            		                            console.log(tagValue);
            		                            title.replace(tagValue,'***');
            		                        }
            		                        console.log(title);
            		                        $('#feedBody .feed:nth-child(' + feedCount + ') .title').html(title);
            		                        console.log('펑션 완료');
            		                    } */
            		                    
            		                    if(mediaSet.length == 1){
            		                    	 $('#feedBody .feed:eq(' + feedCount + ') .nextImg').css('display','none');
            		                    	 $('#feedBody .feed:eq(' + feedCount + ') .prevImg').css('display','none');
            		                    }else{
            		                    	$('#feedBody .feed:eq(' + feedCount + ') .prevImg').css('display','none');
            		                    }
            		                    

            			                if (!checkLike == 0) { //좋아요 체크
            			                    $('.feed:eq(' + feedCount + ') .bar .like').attr('src', '' + liked + '');
            			                }

            			                var commentHeight = $('#feedBody .feed:eq(' + feedCount + ') .titleBody').height();
            			                if (commentHeight > 70) {  //댓글 더보기 체크
            			                    $('#feedBody .feed:eq(' + feedCount + ') .titleBody').css('height', '40px');
            			                    $('#feedBody .feed:eq(' + feedCount + ') .moreView').css('display', 'inline');
            			                    
            			                }

            			                for (var i = 0; i < commentSet.length; i++) { //피드 댓글 셋팅
            			                    $('#feedBody .feed:eq(' + feedCount + ') .commentWrap .commentBody').append('<div class="commentSort"><div class="commentNick">' + Object.keys(commentSet[i]) + '</div><div class="comment">' + Object.values(commentSet[i]) + '</div></div>');
            			                }
            			                
            			            }
            	    				
             	    				$('.videoSet').on('loadedmetadata', function(){

            	    			      	for(var i=0; i < videoFeedIndex.length; i++){

            	    			        	var imgHeight = $('#feedBody .feed:eq(' + videoFeedIndex[i] + ') .imgWrap:eq(0)').css('height');
            	    			            $('#feedBody .feed:eq(' + videoFeedIndex[i] + ') .imgBody').css('height', imgHeight);

            	    			        }
            	    				}); 
            	    				
             			   			var image = new Image();
             			   			console.log("feedCount : "+feedCount);
            			  			image.src = "<%=request.getContextPath()%>/"+Object.values(mediaSet[0]);
            			  			image.onload = function(){
            			  				console.log("두번째             이미지 온로드");
            			  				
            			 				feedCount -= 10;
                 			   			console.log("feedCount : "+feedCount);
            			      			for(; feedCount < limite;feedCount++){
            			                    var imgHeight = $('#feedBody .feed:eq(' + feedCount + ') .imgWrap:eq(0)').css('height');
            			                    $('#feedBody .feed:eq(' + feedCount + ') .imgBody').css('height', imgHeight);
            			                
            			                }
            			  			} 
            					
            					
            					});
            		        });
            		        scrollCheck = true;  //피드 불러오기 콜백 함수 적용
                		},
                		error: function(){
                			alert("피드 불러오기를 실패하였습니다. 새로고침을 눌러주세요~");
                		}

                	})
                })


            }
        		
        	}
        }); 

        //모바일 환경
        function checkMobile() {

            if (navigator.userAgent.match(/Android|Mobile|iP(hone|od|ad)|BlackBerry|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Blazer|Dolfin|Dolphin|Skyfire|Zune/)) {
                window.location.replace("mobileHompage.html");
                console.log('모바일 환경');
            }
            else {
                console.log('컴퓨터 환경');
            }
        }
        checkMobile();
    </script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/mainJs.js"></script>



</body>

</html>
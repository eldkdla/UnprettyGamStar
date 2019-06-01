	/**
	 * 
	 */
	
	console.log("js 들어옴~~");
/*	
	$(document).on('click','.titleImg', function(){
		
		var feedIndex = ($(this).closest('.feed').prevAll().length);
		var url = $('#feedBody .feed:eq(' + feedIndex + ') .link').text();
		
		location.href="http://localhost:9090/GamStar/view/profile?uu="+url+"";

	});
	*/

	$(document).on('click','.saveImg', function(){
		var feedIndex = ($(this).closest('.feed').prevAll().length);
		
		
        console.log(newspeedNo);
		
		$.ajax({
			url:"./newspeed/newspeedstore",
			data:({"newspeedNo":newspeedNo}),
			success: function(data){
				console.log(data.length)-2;
				var src = "newspeed_store_active_icon_wrapper";
				console.log(src.length);

				if((data.length)-2 == src.length){
					console.log("들어옴");
					$('#feedBody .feed:eq(' + feedIndex + ') .saveImg').attr('src','img/newspeeddetailview/newspeed_store_icon.png');
				}else{
					$('#feedBody .feed:eq(' + feedIndex + ') .saveImg').attr('src','img/newspeeddetailview/newspeed_store_active_icon.png');
				}
			},
			error:function(){
				console.log('에러');
			}
		})
		
	})
	
	
	$('#slideIcon').click(function slideBoxClick(){ //수정아이콘 클릭 이벤트
	    $('#slideBox').slideToggle('slow');
	})
	
	$(document).scroll(function noteScroll(){ //추천친구 따라다니는 메뉴(포지션 재설정)
	
	    var position = $(window).scrollTop();
	    // console.log(position);
	    $('#noteBody').css('top', position);
	})
	
	$('#noteResizeBtn').click(function noteResize(){ //추천친구 리 사이즈 클릭 이벤트
	    if($('#storyNote').css('height') == '250px'){ // 1단계 리 사이즈
	        $('#storyNote').css('height','400px');
	        $('#noteContent').css('height','295px');
	    }
	
	    if($('#storyNote').css('height') == '400px'){ //2단계 리 사이즈
	        $('#storyNote').css('height','550px');
	        $('#noteContent').css('height','445px');
	        $('#noteResizeBtn').html('^');
	    }
	
	    if($('#storyNote').css('height') == '550px'){ //사이즈 다시 원래대로
	        $('#storyNote').css('height','250px');
	        $('#noteContent').css('height','145px');
	        $('#noteResizeBtn').html('v');
	    }
	
	});
	
	$('#search').keyup(function inputKey(){ //검색창 입력 이벤트
	    $('#infoSentence').css('opacity', '0');
	    $('#infoBox').removeClass("infoBox");

	    
	    $('.searchPeople').remove();

	    var searchStr = $('#search').val();
	    if(searchStr == ""){
	    	console.log("빈값~");
	    }
	    else{
	    	 $.ajax({
	 	    	url:"./mainsearchservlet",
	 	    	data:{"searchStr":searchStr},
	 	    	success: function(data){
	 	    		console.log("서치 통신");
	 	    		console.log("크기 : "+Object.keys(data[0]).length);
	 	    		if(Object.keys(data[0]).length == 0){
	 	    			console.log("빈값!~~");
	 	    			$('#searchNull').remove();
	 	    			$('#infoBox').append('<div id="searchNull"><p id="noSearchEmoticon">(=*x*=)</p><p id="noSearchSentence">no users found!</p></div>');
	 	    		}
	 	    		
	 	    		for(var i = 0; i<Object.keys(data[0]).length; i++){
	 	    			$('#searchNull').remove();
	 	    			$('#infoBox').append('<div class="searchPeople"><div class="searchImgWrap"><img class="searchImg" src=' + data[0]["follow"+i].profilePhoto + '><p class="searchNickLink">'+data[0]["follow"+i].userNo+'</p></div><div class="searchNick">' + data[0]["follow"+i].userName + '</div></div>');	
	 	    		}
	 	    		
	 	    		$(document).on('click','.searchPeople', function(){
	 	    			console.log("클릭댐!");
	 	    			var url = $(this).children().children('.searchNickLink').text();
	 	    			location.href="http://localhost:9090/GamStar/view/profile?uu="+url+"";
	 	    			console.log(url);
	 	    		})
	 	    		
//href="http://localhost:9090/GamStar/view/profile?uu='+data[0]["follow"+i].userNo+

	 	    	},
	 	    	error: function(){
	 	    		console.log("서치 에러");
	 	    	}
	 	    	
	 	    })
	    }
	    
	    $('')
	    
	    console.log(searchStr);
	   
	    
	    $('#infoBox').css({'width':'300px','height':'400px','border-radius':'10px','overflow-Y':'auto'});
	    console.log("입력");
	
	    if($('#search').val() == ""){

	    	$('#searchNull').remove();
	        $('#infoBox').removeClass("removeInfoBox");
	        $('#infoBox').addClass("infoBox");
	        $('#searchDelete').css('display','inline');
	        $('#infoBox').css('border','1px solid gray');
	        $('#search').attr('placeholder','');
	        $('#infoBox').css('width','400px').css('height','0px');
	        $('#infoSentence').css('opacity', '1');
	        $('#infoBox').css('overflow-Y','hidden');
	    }
	});
	
	$('#search').focus(function searchFocus(){ //검색하기 포커스 이벤트
	    $('#infoBox').removeClass("removeInfoBox");
	   
	    $('#searchDelete').css('display','inline');
	    $('#infoBox').css('opacity','1');
	    $('#infoBox').css('border','1px solid gray');
	    $('#search').attr('placeholder','');
	    if($('#search').val() == ""){
	        //$('#infoBox').css('display','inline');
	        $('#infoBox').css('width','400px').css('height','0px');
	        $('#infoSentence').css('opacity', '1');
	        $('#infoBox').addClass("infoBox");
	    }
	    console.log("클릭2");
	});
	
	$('#search').blur(function searchOutFocus(){ //검색하기 아웃포커스 이벤트
	
	    if($('#search').val() == ""){
	        $('#infoBox').removeClass("infoBox");
	        $('#infoBox').addClass('removeInfoBox');
	        $('#search').attr('placeholder','search..');
	        $('#infoBox').css('border','0px solid gray');
	        $('#infoSentence').css('opacity', '0');
	        console.log("아웃포커스")
	    }
	});
	
	$('#searchDelete').click(function searchDelete(){ //검색삭제 이모티콘 클릭 이벤트
	    $('#search').val("");
	    $('.searchPeople').remove();
	    $('#search').attr('placeholder','search..');
	    $('#searchDelete').css('display','none');
	    $('#infoBox').addClass('removeInfoBox');
	    $('#infoBox').css('border','0px solid gray');
	    $('#infoSentence').css('opacity', '0');
	    console.log("클릭");
	});
	
	
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
	
	var checkNav = false;//nav가 눌린 상태인지 아닌지
	$('.naviIcon').bind('click', function iconClick() {  //nav 클릭 이벤트
	    if($('.naviIcon').height() == 30){  //스크롤 하면서 nav아이콘 클릭했을때
	    	checkNav = true;
	    	
	        $('#navi').unbind('mouseenter mouseleave');
	        $('.naviIcon').unbind('mouseenter');
	        $('#search').css('width','250px').css('height','35px');
	        $('#search').css('border','2.5px solid gray');       
	        //$('#search').css({'left':'50%','transform':'translate(-50%,50%)'});
	        
	        $('#searchView').css({'left': '-9%', 'top':'50%','transform':'translate(9%, -50%)','opacity':'1'});
	        
	        $('#gamstarLogo').css({'left':'10.5%','top':'60%','transform':'translate(-10.5%,-60%)','width':'140px','height':'50px'});
	        $('#gamstarTitle').css('font-size','32px');
	
	        console.log($('.naviIcon').height());
	        
		    $('#navi').css('background-position','100%');
	        
	        $('#navi').css('height','85px')
	        $('.naviIcon').css({ 'width': '0px', 'height': '0px' });
	        $('#naviIconImg').css('height', '0px').css('width', '0px');
	        $('.iconBack').css({ 'left': '20%', 'transform': 'translate(-20%, -50%)', 'opacity': '1' });
	        $('#naviIconWrap').css({ 'left': '100%', 'transform': 'translate(-100%, -50%)', 'opacity': '1'});
	        $('#naviIconWrap').css({ 'width': '260px', 'height': '70px' });
	        $('#myHomeIcon').css({'width':'50px','height':'50px'});
	        $('#alramIcon').css({'width':'20px','height':'20px'});
	        $('#slideIcon').css({'width':'50px','height':'50px'});
	    }
	    console.log("클릭 들어옴");
	    checkNav = true;
	
	    $('#navi').unbind('mouseenter mouseleave');
	    $('.naviIcon').unbind('mouseenter');
	    $('#search').css('width','250px').css('height','35px');
	    $('#search').css('border','2.5px solid gray');

	    $('#searchView').css({'left': '-9%', 'top':'50%','transform':'translate(9%, -50%)','opacity':'1'});
	    
	    $('#gamstarLogo').css({'left':'10.5%','top':'60%','transform':'translate(-10.5%,-60%)','width':'140px','height':'50px'});
	    $('#gamstarTitle').css('font-size','32px');
	
	    $('#navi').css('background-position','100%');
	
	    $('#naviIconWrap').css({ 'left': '100%', 'transform': 'translate(-100%, -50%)', 'opacity': '1'});
	    $('#naviIconWrap').css({ 'width': '160px', 'height': '70px' });
        $('#myHomIcon').css({'width':'50px','height':'50px'});
        $('#alramIcon').css({'width':'20px','height':'20px'});
        $('#slideIcon').css({'width':'50px','height':'50px'});
	
	    $('.naviIcon').css({ 'width': '0px', 'height': '0px' });
	    $('#naviIconImg').css('height', '0px').css('width', '0px');
	    $('.iconBack').css({ 'left': '0%', 'transform': 'translate(-0%, -50%)', 'opacity': '1' });
	
	
	});
	
	$('.iconBack').bind('click', function icon6Click() { //nav 클릭 되돌리기
		
	    console.log("클릭 나감");
	    checkNav = false;
	
	    $('.naviIcon').css({ 'width': '60px', 'height': '60px' });
	    $('#naviIconImg').css('height', '40px').css('width', '40px');
	    $('#search').css('width','0px').css('height','0px');
	    $('#search').css('border','0px solid gray');
	    $('#searchView').css({'left': '50%', 'top':'50%','transform':'translate(-50%, -50%)','opacity':'0'});
        $('#searchDelete').css('display','none');
        $('#infoBox').css('height','0px');
        $('#infoBox').css('border','0px solid gray');
	    
	    $('#gamstarLogo').css({'left':'50%','top':'60%','transform':'translate(-50%,-60%)','width':'0px','height':'0px'});
	    $('#gamstarTitle').css('font-size','0px');
	    
	    $('#navi').css('background-position','0%');
	
	    $('.iconBack').css({ 'left': '50%', 'transform': 'translate(-50%, -50%)', 'opacity': '0' });
	    $('#naviIconWrap').css({ 'left': '50%', 'transform': 'translate(-50%, -50%)', 'opacity': '0'});
	    $('#naviIconWrap').css({ 'width': '0px', 'height': '0px' });
        $('#myHomIcon').css({'width':'0px','height':'0px'});
        $('#alramIcon').css({'width':'0px','height':'0px'});
        $('#slideIcon').css({'width':'0px','height':'0px'});
        
	    $('#slideBox').slideUp();
	    $('#search').val("");
	    $('.searchPeople').remove();
	});
	
	var clickNext = true;
	$(document).on('click','.next', function next(){ //이미지 넥스트 이벤트
	    if(clickNext == true){
	        clickNext = false;
	        
	        var feedIndex = ($(this).closest('.feed').prevAll().length);
	        $('#feedBody .feed:eq(' + feedIndex + ') .prevImg').css('display','inline');
	        
	        var leftVal = $('#feedBody .feed:eq(' + feedIndex + ') .imgWrap').css('left').replace('px','');
	        var moveVal = $('#feedBody .feed:eq(' + feedIndex + ') .imgWrap').css('width').replace('px','');
			
	        var imgIndex;
	        if(leftVal != "0"){
	            imgIndex = (Number(leftVal)/-Number(moveVal))+1;
	        }
	        else{
	        	imgIndex = 1;
	        }
	
	        var imgHeight = $('#feedBody .feed:eq(' + feedIndex + ') .imgWrap:eq('+imgIndex+')').css('height');//이미지 자동 크기조절 이벤트
	
	        $('#feedBody .feed:eq(' + feedIndex + ') .imgBody').css('height', imgHeight);
	
	        var imgWrapNum = ($('#feedBody .feed:eq(' + feedIndex + ') .imgWrap').length)-1;
	        var nextLimite = -moveVal * imgWrapNum;
	        
	        if(imgIndex == imgWrapNum){
	        	console.log('사라짐');
	        	$(this).children().css('display','none');
	        }else{
	        	$(this).closest('.prev').children('display','inline');
	        }
	
	        if(leftVal != nextLimite){ //오버 넥스트 방지
	
	        	var finalVal = Number(leftVal) - Number(moveVal);
	            $('#feedBody .feed:eq(' + feedIndex + ') .imgWrap').css('left',finalVal+'px');
	        }
	        else{
	            console.log('NextLimite');
	        }
	
	        setTimeout(function(){ //중복클릭 방지 셋타임
	            clickNext = true;
	            console.log('트루 변환');
	        },600);
	    }
	    else{
	        console.log('Block double click');
	    }
	});
	
	var clickPrev = true;
	$(document).on('click','.prev', function prev(){ //이미지 prev 이벤트
	    if(clickPrev == true){
	                        
	        clickPrev = false;
	        var feedIndex = ($(this).closest('.feed').prevAll().length);
	        var leftVal = $('#feedBody .feed:eq(' + feedIndex + ') .imgWrap').css('left').replace('px','');
	        var moveVal = $('#feedBody .feed:eq(' + feedIndex + ') .imgWrap').css('width').replace('px','');
	
	        if(leftVal != 0){ //오버 prev 방지
	            var imgIndex = (Number(leftVal) / -Number(moveVal))-1;
	            var imgHeight = $('#feedBody .feed:eq(' + feedIndex + ') .imgWrap:eq('+imgIndex+')').css('height');//이미지 자동 크기조절 이벤트
	            $('#feedBody .feed:eq(' + feedIndex + ') .imgBody').css('height', imgHeight);
	
	            console.log(leftVal);   
	            var finalVal = Number(leftVal) + Number(moveVal);
	            console.log(finalVal);    
	            $('#feedBody .feed:eq(' + feedIndex + ') .imgWrap').css('left',finalVal+'px');
	            $('#feedBody .feed:eq(' + feedIndex + ') .nextImg').css('display','inline');
	            
	            if(finalVal == 0){
	                $(this).children().css('display','none');
	            }
	        }
	        setTimeout(function(){
	            clickPrev = true;
	            console.log('트루 변환');
	        },600);
	    }
	    else{
	        console.log('Block double click');
	    }
	});
	
	$(document).on('mouseenter','.imgWrap, .next, .prev',function(){  //이미지 슬라이드 버튼 호버 
		$('.nextImg').css('opacity','0.8');
		$('.prevImg').css('opacity','0.8');
	});
	
	$(document).on('mouseleave','.imgWrap, .next, .prev',function(){   //이미지 슬라이드 버튼 호버 나갔을때
		$('.nextImg').css('opacity','0.5');
		$('.prevImg').css('opacity','0.5');
	});
	
	$(document).on('click','.commentImg', function(){ //코멘트 버튼 클릭 이벤트
		var feedIndex = ($(this).closest('.feed').prevAll().length);
		$('#feedBody .feed:eq('+feedIndex+') .writeText').focus();
		
	/*             $('#feedBody .feed:nth-child(' + order + ') .writeText').css('background-color', 'white');
	    $('#feedBody .feed:nth-child(' + order + ') .writeBar').css('background-color', 'white');
	    $('#feedBody .feed:nth-child(' + order + ') .textSubmit').css('opacity','1'); */
	
	})
	
	$(document).on('click', '.like', function () { //좋아요 클릭 이벤트 함수
	    var feedIndex = ($(this).closest('.feed').prevAll().length);
	    var str = $('#feedBody .feed:eq(' + feedIndex + ') .showLike').text().replace(/[^0-9]/g, "");
	    var src = $('#feedBody .feed:eq(' + feedIndex + ') .like').attr('src');
	    
	    var newspeedNo =  $('#feedBody .feed:eq(' + feedIndex + ') .feedLink').text();
	    
        $.ajax({  
            url:"./newspeed/newspeedlike",
            data:{"newspeedNo":newspeedNo},
            success: function(data){
            	
            	console.log(data);
            	var likeCheck = data;
            	console.log(likeCheck);
            	
/*            	if(likeCheck == "newspeed_like_active_icon_wrapper"){
            		console.log("좋아요!");
            		 $('#feedBody .feed:eq(' + feedIndex + ') .like').attr('src', "../img/newspeeddetailview/newspeed_like_active.png");
         	        str++;
         	        $('#feedBody .feed:eq(' + feedIndex + ') .showLike').text(str + "Liked");
            	}
            	if(likeCheck == "newspeed_like_icon_wrapper"){
            		console.log("좋아요 취소!");
            		 $('#feedBody .feed:eq(' + feedIndex + ') .like').attr('src', "../img/newspeeddetailview/newspeed_like.png");
         	        str--;
        	        $('#feedBody .feed:eq(' + feedIndex + ') .showLike').text(str + "Liked");
            	}
*/
           	 
            },
            error: function(){
           	 console.log("좋아요 에러");

            }
        })
	    	
	    if (src == "img/newspeeddetailview/newspeed_like_active.png") { //좋아요 클릭시 좋아요 빼기
	        $('#feedBody .feed:eq(' + feedIndex + ') .like').attr('src', "img/newspeeddetailview/newspeed_like.png");
	        str--;
	        $('#feedBody .feed:eq(' + feedIndex + ') .showLike').text(str + "Liked");
	    }
	    else {  //좋아요 클릭시 좋아요 플러스
	        $('#feedBody .feed:eq(' + feedIndex + ') .like').attr('src', "img/newspeeddetailview/newspeed_like_active.png");
	        str++;
	        $('#feedBody .feed:eq(' + feedIndex + ') .showLike').text(str + "Liked");
	    }
	});
	
	$(document).on('click', '.moreView', function () { //더보기 클릭 이벤트 함수
	    $(this).parent().css('height', 'auto');
	    $(this).css('display', 'none');
	});
	
	$(document).on('click','.writeRootComment',function(){ //답글쓰기 클릭 이벤트 함수
		console.log($(this).css('color'));
		if($(this).css('color') == "rgb(128, 128, 128)"){
			console.log("일치!");
			$(this).parent().parent().append('<div class="writeTextWrap"><input type="text" class="writeRootText" /><input type="submit" class="writeRootSubmit" /></div>');
			$(this).css('color','cornflowerblue');
			$(this).css('font-weight','bold');
		}else{
			console.log("불일치!!");
			$(this).parent().parent().children('.writeTextWrap').remove();
			$(this).css('font-weight','normal');
			$(this).css('color','gray');
		}
		
	});
	
	$(document).on('keypress', '.writeRootText', function(key){  //답덥글 엔터 이벤트
		if(key.which == 13){
			
			   var feedIndex = ($(this).closest('.feed').prevAll().length);

		       var userComment = $(this).val();
		       console.log(userComment);
		        
		       var newspeedNo =  $('#feedBody .feed:eq(' + feedIndex + ') .feedLink').text();
		       var rootNo = $(this).parents('.commentSort').children('.commentRootNo').text();

		       console.log(newspeedNo);
		       console.log(rootNo);

		       $.ajax({
		    	   url:"./newspeed/newspeedrecomment",
		    	   data:{"newspeedNo":newspeedNo,"commentContent":userComment,"rootCommentNo":Number(rootNo)},
		    	   success: function(data2){
		    		   console.log("답덥글 통신");
		    		   
		    		   var data = JSON.parse(data2);

		    		   console.log(data);
		    		   console.log(data[0]["recommentList"]);
			     	    $('#feedBody .feed:eq(' + feedIndex + ') .commentSort').remove();
		 	     	    $('#feedBody .feed:eq(' + feedIndex + ') .rootCommentSort').remove();
		       	 
		            	 
		            	var commentSize = data.length;
							for(var i=commentSize; i>0; i--){ //코멘트 셋팅
								console.log('받아지냐?');
								
								console.log("코맨트 푸쉬---------------------");
											if(data[i-1].isMine){
												$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"><p class="writeRootComment">답글달기</p><p class="delteComment">삭제</p></div> </div>');   										
											}
											else{
												$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"> <p class="writeRootComment">답글달기</p></div> </div>');    										
											}
										

										var rootCommentSize = data[i-1].recommentList.length;
										console.log(rootCommentSize);
										if(!rootCommentSize == "0"){
											console.log("루트 들어옴~");
											for(var j=0; j<data[i-1].recommentList.length; j++){
												console.log("루트 푸쉬");
												if(data[i-1].isMine){
													$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> <div class="commentEditeWrap"><p class="delteComment">삭제</p></div> </div>');   										
												}
												else{
													$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> </div>');    										
												}	
											}
											
										}				
								
							}
		    	   },
		    	   error: function(){
		    		   console.log("에러 통신");
		    	   }
		       })
			
				$(this).val("");
		}

	});
	
	$(document).on('click', '.writeRootSubmit', function(){  //버튼 클릭 이벤트

			   var feedIndex = ($(this).closest('.feed').prevAll().length);

		       var userComment = $(this).val();
		       console.log(userComment);
		        
		       var newspeedNo =  $('#feedBody .feed:eq(' + feedIndex + ') .feedLink').text();
		       var rootNo = $(this).parents('.commentSort').children('.commentRootNo').text();
		       console.log(newspeedNo);
		       console.log(rootNo);

		       $.ajax({
		    	   url:"./mainnewspeedrecommentservlet",
		    	   data:{"newspeedNo":newspeedNo,"commentContent":userComment,"rootCommentNo":rootNo},
		    	   success: function(data){
		    		   console.log("답덥글 통신");

		    		   console.log(data);
		    		   console.log(data[0]["recommentList"]);
			     	    $('#feedBody .feed:eq(' + feedIndex + ') .commentSort').remove();
		 	     	    $('#feedBody .feed:eq(' + feedIndex + ') .rootCommentSort').remove();
		       	 
		            	 
		            	var commentSize = data.length;
							for(var i=commentSize; i>0; i--){ //코멘트 셋팅
								console.log('받아지냐?');

								console.log("코맨트 푸쉬---------------------");
											if(1 == data[i-1].userNo){
												$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"><p class="writeRootComment">답글달기</p><p class="delteComment">삭제</p></div> </div>');   										
											}
											else{
												$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"> <p class="writeRootComment">답글달기</p></div> </div>');    										
											}
										

										var rootCommentSize = data[i-1].recommentList.length;
										console.log(rootCommentSize);
										if(!rootCommentSize == "0"){
											console.log("루트 들어옴~");
											for(var j=0; j<data[i-1].recommentList.length; j++){
												console.log("루트 푸쉬");
												if(1 == data[i-1].recommentList[j]["userNo"]){
													$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> <div class="commentEditeWrap"><p class="delteComment">삭제</p></div> </div>');   										
												}
												else{
													$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> </div>');    										
												}	
											}
											
										}				
								
							}
		    	   },
		    	   error: function(){
		    		   console.log("에러 통신");
		    	   }
		       })
			
				$(this).val("");
	});
	
	
	
	
	$(document).on('click','.delteComment', function(){  //댓글 삭제 버튼 클릭 이벤트 함수

		var feedIndex = ($(this).closest('.feed').prevAll().length);
		var feedNo = $('#feedBody .feed:eq(' + feedIndex + ') .feedLink').text();
		var commentNo = $(this).parent().parent().children('.commentNo').text();
	
		console.log("코멘트 번호 : "+commentNo);
		console.log("피드 번호 : "+feedNo);
		 $.ajax({
			url:"./newspeed/newspeedcommentdelete",
			data:{"newspeedNo":feedNo,"commentNo":commentNo},
			success: function(data2){
	     	     $('#feedBody .feed:eq(' + feedIndex + ') .commentSort').remove();
 	     	     $('#feedBody .feed:eq(' + feedIndex + ') .rootCommentSort').remove();
       	 
            	 var data = JSON.parse(data2);
 	     	     
            	 var commentSize = data.length;
					for(var i=commentSize;i>0;  i--){ //댓글삭제후  셋팅
						
						
						console.log('받아지냐?');
						//console.log(commentSize);
						//console.log(data);
						//console.log(data[0]);
						//console.log(data[1]);
						//console.log(Object.keys(data).length);
						
						

						console.log("코맨트 푸쉬---------------------");
									if(data[i-1].isMine){
										$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"><p class="writeRootComment">답글달기</p><p class="delteComment">삭제</p></div> </div>');   										
									}
									else{
										$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"> <p class="writeRootComment">답글달기</p></div> </div>');    										
									}
								

								var rootCommentSize = data[i-1].recommentList.length;
								console.log(rootCommentSize);
								if(!rootCommentSize == "0"){
									console.log("루트 들어옴~");
									for(var j=0; j<data[i-1].recommentList.length; j++){
										console.log("루트 푸쉬");
										if(data[i-1].isMine){
											$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> <div class="commentEditeWrap"><p class="delteComment">삭제</p></div> </div>');   										
										}
										else{
											$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> </div>');    										
										}	
									}
									
								}				
						
					}
			},
			error: function(){
				console.log("댓글삭제 에러~~");
			}
		 })
		 $(this).closest('.commentSort').remove();
	})
	
	
	$(document).on('click','.textSubmit', function(){ //텍스트입력버튼 클릭 이벤트
	
		  var feedIndex = ($(this).closest('.feed').prevAll().length);

	        var userComment =   $('#feedBody .feed:eq(' + feedIndex + ') .writeText').val();
	        
	        var newspeedNo =  $('#feedBody .feed:eq(' + feedIndex + ') .feedLink').text();

	        
	        $.ajax({  //코멘트 받아오기 
	             url:"./mainnewspeedcommentservlet",
	             data:{"newspeedNo":newspeedNo,"commentContent":userComment},
	             success: function(data){
	            	 console.log("코멘트 통신성공");

	            	 
	 	     	     $('#feedBody .feed:eq(' + feedIndex + ') .commentSort').remove();
	 	     	     $('#feedBody .feed:eq(' + feedIndex + ') .rootCommentSort').remove();
	       	 
	            	 
	            	 var commentSize = data.length;
						for(var i=commentSize; i>0; i--){ //코멘트 셋팅
							console.log('받아지냐?');

							console.log("코맨트 푸쉬---------------------");
										if(data[i-1].isMine){
											$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"><p class="writeRootComment">답글달기</p><p class="delteComment">삭제</p></div> </div>');   										
										}
										else{
											$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"> <p class="writeRootComment">답글달기</p></div> </div>');    										
										}
									

									var rootCommentSize = data[i-1].recommentList.length;
									console.log(rootCommentSize);
									if(!rootCommentSize == "0"){
										console.log("루트 들어옴~");
										for(var j=0; j<data[i-1].recommentList.length; j++){
											console.log("루트 푸쉬");
											if(data[i-1].isMine){
												$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> <div class="commentEditeWrap"><p class="delteComment">삭제</p></div> </div>');   										
											}
											else{
												$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> </div>');    										
											}	
										}
										
									}				
							
						}

	             },
	             error: function(){
	            	 console.log("댓글쓰기 에러ssss");

	             }
	         });
	         console.log("뭐야 이거?");

	        $('#feedBody .feed:eq(' + feedIndex + ') .writeText').val('');
	        $('#feedBody .feed:eq(' + feedIndex + ') .writeText').focus();
	})
	
	
	
	
	
	
	
	$(document).on('keypress', '.writeText', function ClickEnter(key) { //댓글쓰기 엔터 이벤트
	    if (key.which == 13) {
	        var feedIndex = ($(this).closest('.feed').prevAll().length);

	        var userComment = $(this).val();
	        console.log(userComment);
	        
	        var newspeedNo =  $('#feedBody .feed:eq(' + feedIndex + ') .feedLink').text();
	     
	        
	         $.ajax({  //코멘트 받아오기 
	             url:"./newspeed/newspeedcommentwrite",
	             data:{"newspeedNo":newspeedNo,"commentContent":userComment},
	             success: function(data2){
	            	 console.log("코멘트 통신성공");

	            	 
	 	     	     $('#feedBody .feed:eq(' + feedIndex + ') .commentSort').remove();
	 	     	     $('#feedBody .feed:eq(' + feedIndex + ') .rootCommentSort').remove();
						
					var data = JSON.parse(data2);
					console.log(data);
	            	 
	            	 var commentSize = data.length;
						for(var i=commentSize; i>0; i--){ //코멘트 셋팅
							console.log('받아지냐?!!!');
							console.log("코맨트 푸쉬---------------------");
										if(data[i-1].isMine){
											$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"><p class="writeRootComment">답글달기</p><p class="delteComment">삭제</p></div> </div>');   										
										}
										else{
											$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="commentSort"><p class="commentRootNo">'+data[i-1].rootCommentNo+'</p><p class="commentNo">'+data[i-1].commentNo+'</p><div class="commentNick">' + data[i-1].userName + '</div><div class="comment">' + data[i-1].commentContent + '</div> <div class="commentEditeWrap"> <p class="writeRootComment">답글달기</p></div> </div>');    										
										}
									

									var rootCommentSize = data[i-1].recommentList.length; 
									console.log(rootCommentSize);
									if(!rootCommentSize == "0"){
										console.log("루트 들어옴~");
										for(var j=0; j<data[i-1].recommentList.length; j++){
											console.log("루트 푸쉬");
											if(data[i-1].isMine){
												$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentRootNo">'+data[i-1].recommentList[j]["rootCommentNo"]+'</p><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> <div class="commentEditeWrap"><p class="delteComment">삭제</p></div> </div>');   										
											}
											else{
												$('#feedBody .feed:eq(' + feedIndex + ') .commentWrap .commentBody').append('<div class="rootCommentSort"><p class="commentRootNo">'+data[i-1].recommentList[j]["rootCommentNo"]+'</p><p class="commentNo">'+data[i-1].recommentList[j]["commentNo"]+'</p><div class="rootCommentNick">' + data[i-1].recommentList[j]["userName"] + '</div><div class="rootComment">' + data[i-1].recommentList[j]["commentContent"] + '</div> </div>');    										
											}	
										}
										
									}				
							
						}
 
	             },
	             error: function(){
	            	 console.log("댓글쓰기 에러ssss");

	             }
	         });
	        $(this).val('');
	        
	    };
	});
	
	
	$(document).on('focus', '.writeText', function () {  //댓글쓰기 포커스 이벤트
	    var order = ($(this).closest('.feed').prevAll().length);
	    console.log('포커스');
	    // $('.writeBar').off('mouseleave');
	    $('#feedBody .feed:eq(' + order + ') .writeText').css('background-color', 'white');
	    $('#feedBody .feed:eq(' + order + ') .writeBar').css('background-color', 'white');
	    $('#feedBody .feed:eq(' + order + ') .textSubmit').css('opacity','1');
	});
	
	$(document).on('blur', '.writeText', function () {  //댓글쓰기 포커스 아웃 이벤트
	    var order = ($(this).closest('.feed').prevAll().length);
	
	    if(!$(this).val().length == 0){
	        $('#feedBody .feed:eq(' + order + ') .writeText').css('background-color', 'white');
	        $('#feedBody .feed:eq(' + order + ') .writeBar').css('background-color', 'white');
	        $('#feedBody .feed:eq(' + order + ') .textSubmit').css('opacity','1');
	    }
	    else{
	        $('#feedBody .feed:eq(' + order + ') .writeBar').css('background-color', '#2ebf91');
	        $('#feedBody .feed:eq(' + order + ') .writeText').css('background-color', '#2ebf91');
	        $('#feedBody .feed:eq(' + order + ') .textSubmit').css('opacity','0');
	    }
	});
	
	
	
	$(window).resize(function(){    //윈도우창 조절시 피드 크기 맞춤
	    var size = $(window).innerWidth();
	
	
	    for(var j =0; j < feedCount; j++){
	    	
	/*             	var leftVal = $('#feedBody .feed:eq(' + j + ') .imgWrap').css('left').replace('px','');
	    	var imgWidth = $('#feedBody .feed:eq(' + j + ') .imgWrap').css('width').replace('px',''); //이미지 인덱스 번호 구하기
	    	var imgIndex = (leftVal/-imgWidth);
	    	
	        var imgHeight = $('#feedBody .feed:eq(' + j + ') .imgWrap:eq('+imgIndex+')').css('height');
	        $('#feedBody .feed:eq(' + j + ') .imgBody').css('height', imgHeight); */
	        
	        var imgHeight = $('#feedBody .feed:eq(' + j + ') .imgWrap:eq(0)').css('height');   
	        $('#feedBody .feed:eq(' + j + ') .imgBody').css('height', imgHeight); //자동 크기조절
	        $('#feedBody .feed:eq(' + j + ') .imgWrap').css('left', '0px');  //이미지 첫번째로 돌아가기
	        $('#feedBody .feed:eq(' + j + ') .prevImg').css('display', 'none');
	    }   
	})
	
	
	   $(document).scroll(function scroll_feed() { //스크롤 이벤트 
	

	            if ($(window).scrollTop() == 0) { //nav 스크롤 반응 작동
	                $('#navi').css('height', '85px');
	                $('.naviIcon').css('height', '60px').css('width', '60px');
	                $('#naviIconImg').css('height', '40px').css('width', '40px');
	            } else {
	                $('#navi').css('height', '55px');
	                $('.naviIcon').css('height', '30px').css('width', '30px'); 
	                $('#naviIconImg').css('height', '20px').css('width', '20px');
	            	
	            	if(checkNav == true){
	            		console.log("스크롤 nav 되돌리기 실! 행!");
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

		        	    $('#navi').css('background-position','0%');
		                
		                $('#search').css('width','0px').css('height','0px');
		                $('#search').css('border','0px solid gray');
		                $('#search').css({'left':'50%','top':'50%','transform':'translate(-50%,-50%)'});
		        	    $('#searchView').css({'left': '50%', 'top':'50%','transform':'translate(-50%, -50%)','opacity':'0'});
		    	        $('#searchDelete').css('display','none');
		    	        
		    		    $('#infoBox').css('height','0px');
		    		    $('#infoBox').css('opacity','0');
		    		    $('#infoBox').css('border','0px solid gray');
		    		    $('#infoSentence').css('opacity', '0');
		                
		                $('#gamstarLogo').css({'left':'50%','top':'60%','transform':'translate(-50%,-60%)','width':'0px','height':'0px'});
		        	    $('#gamstarTitle').css('font-size','0px');
		
		                $('.iconBack').css({ 'left': '50%', 'transform': 'translate(-50%, -50%)', 'opacity': '0' });
		                $('#naviIconWrap').css({ 'left': '50%', 'transform': 'translate(-50%, -50%)', 'opacity': '0'});
		                $('#naviIconWrap').css({ 'width': '0px', 'height': '0px' });
		                $('#myHomIcon').css({'width':'0px','height':'0px'});
		                $('#alramIcon').css({'width':'0px','height':'0px'});
		                $('#slideIcon').css({'width':'0px','height':'0px'});
		                
		                $('#slideBox').slideUp();
		                $('#search').val("");
		                $('.searchPeople').remove();
		                
	            		checkNav = false;
	            	}
	            }
	            
	   });
	
	
	 window.onresize = function checkResize() {
	    //컴퓨터환경
	    if (!navigator.userAgent.match(/Android|Mobile|iP(hone|od|ad)|BlackBerry|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Blazer|Dolfin|Dolphin|Skyfire|Zune/)) {
	
	        var width = window.innerWidth;
	
	        if (width < 1000 && width > 640) {
	            $('#noteBody').css('opacity', '0');
	            $('#fullBody').css('width', '600px');
	            $('#fullBody').css('margin-left', '-300px');
	        }
	        if (width > 1000) {
	            $('#noteBody').css('opacity', '1');
	            $('#fullBody').css('width', '900px');
	            $('#fullBody').css('margin-left', '-467.5px');
	        }
	
	        if (width < 640) {
	            $('#noteBody').css('opacity', '0');
	            $('#fullBody').css('margin-left', '-50%');
	        }
	        if (width > 640 && width < 1000) {
	            $('#fullBody').css('width', '600px');
	            $('#fullBody').css('margin-left', '-300px');
	        }
	    }
	}
	
	//스크린 사이즈 체크
	window.onload = function checkScreenSize() {
	    var width = window.innerWidth;
	    var fullBody = document.getElementById('fullBody');
	    var noteBody = document.getElementById('noteBody');
	
	    //컴퓨터 환경
	    if (!navigator.userAgent.match(/Android|Mobile|iP(hone|od|ad)|BlackBerry|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Blazer|Dolfin|Dolphin|Skyfire|Zune/)) {
	        if (width < 1000 && width > 640) {
	            noteBody.style.opacity = 0;
	            fullBody.style.width = '600px';
	            fullBody.style.marginLeft = '-300px';
	        }
	
	        if (width < 640) {
	            noteBody.style.opacity = 0;
	            fullBody.style.marginLeft = '-50%';
	        }
	    }
	}
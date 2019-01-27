<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
		<link href="<%=request.getContextPath()%>/css/header.css" rel="stylesheet" type="Text/css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/chat.css">
   		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/userSupport.css">
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/profileAlert.css">
		<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
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
                       <div id="infoChange"><div class="infoChangeText"><a href="#">개인정보설정</a></div></div>
                        <hr class="slideHr">
                        <div id="chatting" onclick="chat()"><div class="chattingText">채팅</div></div>
                        <hr class="slideHr">
                        <div id="contactBoard" onclick="supportBtn()"><div class="contactBoardText">문의하기</div></div>
                        <hr class="slideHr">
                        <div id="contactBoard2" onclick="confirmSupportContents()"><div class="contactBoardText">답변확인</div></div>
                        <hr class="slideHr">
                        <div id="logout"><div class="logoutText"><a href="#">로그아웃</a></div></div>
                </div>
            </div>
            <div class="iconBack"><img id ="iconBackImg" src="<%=request.getContextPath()%>/img/newspeeddetailview/logoBack.png"></div> 
        </div>
    </nav>
<!-- 문의하기 -->
     <div id="supportMainDiv" class="supportMain" onclick="zindexchange(this)">

            <div id="supportMainStatus" onmousedown="startDrag(event, document.getElementById('supportMainDiv'))">
                <span style="color: white; font-size: 14px; position: relative; left: 5px; ">Q&A</span>
                <button id="supportMaincloseBtn" onclick="document.getElementById('supportMainDiv').style.display='none';"> x
                </button>
            </div>
            <h2 style="text-align: center; position: relative; top:17px;">Q&A</h2>
            <form action="" method="post" enctype="multipart/form-data" name="" id="userSupportForm">
            <div id="supportDiv">
                <div id="supportTitle">
                    <span id="supportTitleSpan">제목</span>
                    <input id="supportTitleInput" type="text" name="userSupportTitle"/>
                </div>
                <div id="supportContent">
                    <span id="supportContentSpan">내용</span>
                    <textarea id="supportContentTextArea" name="userSupportContent"></textarea>
                </div>
                <div id="supportFile">
                    <span id="supportFileSpan">첨부파일</span>
                       <input type="file" id="supportFileInput" name="supportFileName[]" multiple="multiple">
                       
                </div>
            </div>
            <div id="supportBottom">
                <button id="supportSubmit">보내기</button>
            </div>
            </form>
        </div>

<!-- 문의답변 -->
		<div id="cofirmSupportDiv" class="supportMain" onclick="zindexchange(this)">

            <div id="confirmSupportMainStatus" onmousedown="startDrag(event, document.getElementById('cofirmSupportDiv'))">
                <span style="color: white; font-size: 14px; position: relative; left: 5px; ">Q&A</span>
                <button id="confirmSupportCloseBtn" onclick="document.getElementById('cofirmSupportDiv').style.display='none';"> x
                </button>
            </div>
            <h2 style="text-align: center; position: relative; top:17px;">Answer</h2>
            <div id="confrimSupportContents">
			<table class='type' id='confirmTable'>


			</table>
			<div id="questionDiv"  style="display:none">
				<table class='type' id='aaatable'>
				<tr>
                   <th id="title" style="width:500px ;border-bottom:2px solid black">흠</th>
               </tr>
					
				<tr>
						<td style="position: relative; top:8px;left:10px;border: none; text-align: left; width:500px" id="qDate">
						<small>Date	: </small> 
						</td>
					</tr>
					<tr>
						<td  style="position: relative; top:8px;left:10px; text-align: left; padding: 5px; width:500px">
						<div id="question" style="width:450px; font-family: 'Gothic A1', sans-serif; font-size: 13px"></div> <br /> <br /> <br /> <br /> <br />
						</td>
					</tr>

					<tr>
						<td style="position: relative; left:10px;top:8px; width:500px" id="aDate"><img src='/GamStar/img/adminImg/answer.png' style='width: 18px; opacity: 0.5;'/> 
						<small>Date : </small>
						</td>
					</tr>
					<tr>
						<td style="position: relative; top:8px;left:15px;">
						<div id="answer" style="width:450px; font-family: 'Gothic A1', sans-serif; font-size: 13px""></div>
						</td>
					</tr>

					
				</table>
				<div style="text-align: center">
				<button id="aSupportIndexBtn"  onclick="supportIndex()">목록</button>
				</div>
			</div>


		</div>
            
        </div>
<!-- chatting -->
	 <div id="chatroom" class="chatroomMain" onclick="zindexchange(this)">

        <div id="chatroomStatus" onmousedown="startDrag(event, document.getElementById('chatroom'))">
            <span style="color: white; font-size: 14px; position: relative; left: 5px;">Messanger</span>

            <button id="alarmset" onclick='alarmOnOff()'></button>
            <button id="chatroomcloseBtn" onclick="removechatlist()"> x
            </button> 
        </div>
        
        <div id="addchatlist">

        </div>
        <div>
        
            <button id="plusfriendBtn" onclick="friendlist()"></button>
       
        </div>
    </div>



    <div id="chatroomfriendroom" class="chatroomfriend" onclick="zindexchange(this)">

        <div id="friendlistStatus" onmousedown="startDrag(event, document.getElementById('chatroomfriendroom'))">
            <span style="color: white; font-size: 11px; position: relative; left: 5px;">대화상대 선택</span>
            <button id="friendlistcloseBtn" onclick="document.getElementById('chatroomfriendroom').style.display='none';"> x </button>
        </div>
        <div id="selectfriend">
            <div id="findfriend">
                <input type="text" onkeyup="findFriend()" id="inputFriendName" class=inputfriend />
               
            </div>
            <div id=friendlistdiv>
           
              
               

            </div>
        </div>
        <div id="selectedfriend">
            <div id="selectedfriendtext">선택한 사람</div>
            <div id="selectedfriendlist">

            </div>
            <div id="selectedfriendbutton">
                <button id="selectedFriendOkBtn" onclick="addchat()">확인</button>
                <button id="selectedFriendCancelBtn" onclick="document.getElementById('chatroomfriendroom').style.display='none';">취소</button>

            </div>
        </div>

    </div>



    <div id="chattingscreen" class="chatscreen" onclick="zindexchange(this)">
        <div id="chattingStatus" onmousedown="startDrag(event, document.getElementById('chattingscreen'))">
        <span id="chattingroomName" style="color:white; position:relative; left:3px; font-size: 14px;">홍길동</span>
            <button id="chattingcloseBtn" onclick='closeChatting()'>
                x
            </button>
        </div>
        <div id="messagecontent">
            
            
        </div>
        
            <div id="chattingbottom">
                
                <div id="chatinput">
                    <textarea id="inputchattext"></textarea>
                    <button id="submitchattextBtn" onclick="inputChatting()">전송</button>
                </div>
            </div>
        
    </div>
    <!-- 채팅방 끝 -->
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

	       
	       $('.searchPeople').remove();

	       var searchStr = $('#rara').val();
	       if(searchStr == ""){
	          
	       }
	       else{
	           $.ajax({
	              url:"<%=request.getContextPath()%>/mainsearchservlet",
	              data:{"searchStr":searchStr},
	              success: function(data){
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
<script>
    function profileAlert(alertMsg){
    	$('#myModal').remove();
    	$('body').append($('<div/>',{
    		id:'myModal',
    		class:'modal'
    	}));
    	$('#myModal').append($('<div/>',{
    		class:'modal-content',
    	}));
    	$('.modal-content').append($('<p/>',{
    		text:alertMsg,
    		style:"font-size:18px;"
    	}));
    	$('.modal-content').append($('<div/>',{
    		onclick:'close_pop()',
    		style:"font-size:15px;height:20px; width:60px",
    		text:'확인'
    	}));
    	
    }
	//모달창 닫기
    function close_pop(){
    	$('#myModal').css("display","none");
    }
    
   var timer;
   var audiosetting=1;
  
   var audio = new Audio('../audio/alarm.mp3');
   var audioflag1;
   var audioflag2;
   var audioflagc1;
   var audioflagc2;
   var audioflagm1;
   var audioflagm2;
   //인덱스 변경
   var count = 2100000000;
   function zindexchange(test) {
   	console.log(count);
       document.getElementById(test.id).style.zIndex = count;
       count++;
   }
    function confirmSupportContents(){
    	//console.log("들어옴?");
    	$('#questionDiv').css('display','none');
    	$('#confirmTable').children().remove();
    	document.getElementById('cofirmSupportDiv').style.zIndex = count;
        count++;
        var confirmState='O';
        $('#confirmTable').append($('<tr/>',{
   			id: 'confrimHeader',
   		}));
        $('#confrimHeader').append($('<th/>',{
   			class:'xxx',
   			style:'width:40px',
   			text:'No'
   		}));
        $('#confrimHeader').append($('<th/>',{
   			class:'xxx',
   			style:'width:260px',
   			text:'Title'
   		}));
        $('#confrimHeader').append($('<th/>',{
   			class:'xxx',
   			style:'width:130px',
   			text:'Date'
   		}));
        $('#confrimHeader').append($('<th/>',{
   			class:'xxx',
   			style:'width:91px;',
   			text:'Answer'
   		}));
		$.ajax({
	    	
	        url: "<%=request.getContextPath()%>/confirmsupport",
	        type: 'post',
	        dataType :'text',
	        contentType: false,
	        cache: false,
	        processData:false,
	        success: function(data){
	        	var json = eval("(" + data + ")");
	        	console.log(json);
	        	for(var i=0;i<json.length;i++){
		        	if(json[i]["state"]==0){
		        		confirmState='X'
		        	}
		        	else{
		        		confirmState='O'
		        	}
		        	$('#confirmTable').append($('<tr/>',{
	           			id: 'confirm'+i,
	           		}));
	           		$('#confirm'+i).append($('<td/>',{
	                    class:'xx',
	                    text: i+1 
	                }));
	           		$('#confirm'+i).append($('<td/>',{
	                    class:'xx',
	                    text: json[i]["title"] ,
	                    id:'cofirmTitle'+i,
	           			value:json[i]["supportNo"],
	                    style:"cursor:pointer"
	                }));
	           		$('#confirm'+i).append($('<td/>',{
	                    class:'xx',
	                    text: json[i]["date"]    
	                }));
	           		$('#confirm'+i).append($('<td/>',{
	                    class:'xx',
	                    text:  confirmState,
	                    id: 'confirmState'+i
	                }));
	           		if(confirmState=='O'){
	           			$('#confirmState'+i).css('color','blue')
	           		}
	           		else{
	           			$('#confirmState'+i).css('color','red')
	           		}
	           		$('#cofirmTitle'+i).off("click").on("click",function(){
	           			answerData(this);
	           			
                       });
	        	}
	        	
	        },error:function(xhr,status){
	            alert(xhr+" : "+status);   
	        }
	        
	    });
    	document.getElementById('cofirmSupportDiv').style.display = 'block';
	    document.getElementById('cofirmSupportDiv').style.left = "500px";
	    var height4=$(document).scrollTop();
	    document.getElementById('cofirmSupportDiv').style.top = (height4+100)+"px";
    }
    function supportIndex(){
    	$('#questionDiv').css('display','none');
    	$('#confirmTable').children().remove();
    	document.getElementById('cofirmSupportDiv').style.zIndex = count;
        count++;
        var confirmState='O';
        $('#confirmTable').append($('<tr/>',{
   			id: 'confrimHeader',
   		}));
        $('#confrimHeader').append($('<th/>',{
   			class:'xxx',
   			style:'width:40px',
   			text:'No'
   		}));
        $('#confrimHeader').append($('<th/>',{
   			class:'xxx',
   			style:'width:260px',
   			text:'Title'
   		}));
        $('#confrimHeader').append($('<th/>',{
   			class:'xxx',
   			style:'width:130px',
   			text:'Date'
   		}));
        $('#confrimHeader').append($('<th/>',{
   			class:'xxx',
   			style:'width:91px;',
   			text:'Answer'
   		}));
		$.ajax({
	    	
	        url: "<%=request.getContextPath()%>/confirmsupport",
	        type: 'post',
	        dataType :'text',
	        contentType: false,
	        cache: false,
	        processData:false,
	        success: function(data){
	        	var json = eval("(" + data + ")");
	        	console.log(json);
	        	for(var i=0;i<json.length;i++){
		        	if(json[i]["state"]==0){
		        		confirmState='X'
		        	}
		        	else{
		        		confirmState='O'
		        	}
		        	$('#confirmTable').append($('<tr/>',{
	           			id: 'confirm'+i,
	           		}));
	           		$('#confirm'+i).append($('<td/>',{
	                    class:'xx',
	                    text: i+1 
	                }));
	           		$('#confirm'+i).append($('<td/>',{
	                    class:'xx',
	                    text: json[i]["title"] ,
	                    id:'cofirmTitle'+i,
	           			value:json[i]["supportNo"],
	                    style:"cursor:pointer"
	                }));
	           		$('#confirm'+i).append($('<td/>',{
	                    class:'xx',
	                    text: json[i]["date"]    
	                }));
	           		$('#confirm'+i).append($('<td/>',{
	                    class:'xx',
	                    text:  confirmState,
	                    id: 'confirmState'+i
	                }));
	           		if(confirmState=='O'){
	           			$('#confirmState'+i).css('color','blue')
	           		}
	           		else{
	           			$('#confirmState'+i).css('color','red')
	           		}
	           		$('#cofirmTitle'+i).off("click").on("click",function(){
	           			answerData(this);
	           			
                       });
	        	}
	        	
	        },error:function(xhr,status){
	            alert(xhr+" : "+status);   
	        }
	        
	    });
    }
    function answerData(obj){
    	//console.log($(obj).attr('value'));
    	var supportNo=$(obj).attr('value');
    	console.log(supportNo)
		$.ajax({
	    	
	        url: "<%=request.getContextPath()%>/answerSupportData",
	        type: 'post',
	        dataType :'text',
	        data: {"supportNo" : supportNo},
	        cache: false,
	        success: function(data){
	        	var json = eval("(" + data + ")");
	        	console.log(json);
	        	$('#confirmTable').children().remove();
	        	$('#title').html(json["title"]);
	        	$('#qDate').html('<small>'+'Date : '+'</small>'+json["questionDate"]);
	        	$('#question').html(json["question"]);
	        	if(json["answer"].length<1){
	        		$('#aDate').html('<small>'+'Date : '+'</small>'+"");
	        		$('#answer').html("아직 답변이 달리지 않았습니다.");
	        	}
	        	else{
	        	$('#aDate').html('<small>'+'Date : '+'</small>'+json["answerDate"]);
	        	$('#answer').html(json["answer"]);}
	        	$('#questionDiv').show();
           		
	        	
	        	
	        },error:function(xhr,status){
	            alert(xhr+" : "+status);   
	        }
	        
	    });
    }
    
   function supportBtn() {
	   document.getElementById('supportMainDiv').style.zIndex = count;
       count++;
	    $('#userSupportForm textArea').val('');
	    $('#supportTitleInput').val('');
	    $('#supportFileInput').val('');
	    document.getElementById('supportMainDiv').style.display = 'block';
	    document.getElementById('supportMainDiv').style.left = "300px";
	    var height4=$(document).scrollTop();
	    document.getElementById('supportMainDiv').style.top = (height4+100)+"px";
	    }
   
   
	var flaggg=true;
	$("#userSupportForm").on('submit', function(e){
	    e.preventDefault();
	    flaggg=true;
		
	    	var files = $('#userSupportForm input[type=file]')[0].files;
			var formData = new FormData();
			var title= $('#supportTitleSpan');
			var content = $('#userSupportForm textArea');
			
			for (var i = 0; i < files.length; i++) {
				console.log(files[i].name);
				pathpoint = files[i].name.lastIndexOf('.');
            	filepoint = files[i].name.substring(pathpoint+1,files[i].length);
            	filetype = filepoint.toLowerCase();
            	
            	if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp'|| filetype=='mp4') {

				formData.append('file' + i, files[i]);
            		
            	} else {
            		profileAlert('이미지나 동영상을 선택해주세요');
            		$('#supportFileInput').val("");
            		flaggg=false;
            	}
			}
			
			formData.append('content', $('#userSupportForm textArea').val());
			formData.append('title', $('#supportTitleInput').val());
			
			if($('#userSupportForm textArea').val().length==0||$('#supportTitleInput').val().length==0){
				profileAlert("빈 칸을 작성하여 주세요.");
			}
			else{ 
				if(flaggg==true){
	    $.ajax({
	    	
	        url: "<%=request.getContextPath()%>/userSupportData",
	        type: 'post',
	        data: formData,
	        contentType: false,
	        cache: false,
	        processData:false,
	        success: function(data){
	        	profileAlert("문의가 완료 되었습니다.");
	        	supportIndex();
	        },error:function(xhr,status){
	            alert(xhr+" : "+status);   
	        }
	        
	    });
	    
	    document.getElementById('supportMainDiv').style.display='none';}}
	
});
    function chat() {
    	document.getElementById('chatroom').style.zIndex = count;
    	count++;
    	$.ajax({
            url:"<%=request.getContextPath()%>/previewlist",
           type:"get",
           dataType :'text',
           cache : false,
           
           success:function(data){
                 $('#addchatlist').children().remove();
                  //console.log(data);
                  //var json = JSON.parse(data);

                  var json = eval("(" + data + ")");
                  //console.log(typeof(json));
                  //console.log(json);
                  	  audioflag1=json[0]["previewMessage"];
                        audioflagc1=json[0]["chatNo"];
                        audioflagm1=json[0]["mili"];
                  
                  for(var i=0;i<json.length;i++){
                  	
                    //console.log(key+":"+json[i][key]);
                    //console.log(json[i]["otherName"]);
                    
                    if(json[i]["otherNo"]==json[i]["previewMessageUserNo"]&&json[i]["readState"]==0)
                    {
                       $('#addchatlist').append($('<div/>',{
                             class:'chatlistcontent',
                             id: 'fchatlistdiv'+i,
                             
                         }));
                       $('#fchatlistdiv'+i).append($('<img/>',{
                             src: '<%=request.getContextPath()%>/'+json[i]["otherProfile"],
                             class:'chatuserimg',
                             id: 'fchatuserimg'+i
                            
                         }));
                       $('#fchatlistdiv'+i).append($('<span/>',{
                               text:json[i]["otherName"],
                             class:'chatusername',
                            
                         }));
                       $('#fchatlistdiv'+i).append($('<div/>',{
                             style:'position:absolute',
                             id: 'fchatuserdiv2'+i,
                            
                         })); 
                       $('#fchatuserdiv2'+i).append($('<p/>',{
                             class:'chatpreview',
                             value: json[i]["chatNo"],
                            text:json[i]["previewMessage"]
                         }));
                       $('#fchatlistdiv'+i).append($('<span/>',{
                               text:json[i]["previewMessageTime"],
                               style:'position:relative; font-size:7px ;top:1px;left:19.5px; color:gray;'
                              
                           }));
                       $('#fchatlistdiv'+i).append($('<img/>',{
                             src: '<%=request.getContextPath()%>/img/alarm.png',
                             class:'alarm',
                             style:'position: relative; width: 15px; height: 15px; top:30px; left: 4px;'
                            
                         }));
                       
                      /*  $('#fchatlistdiv'+i).append($('<button/>',{
                             class:'deletechatBtn',
                             text : 'x'
                            
                         })); */
                       
                       $('#fchatlistdiv'+i).off("click").on("click",function(){
                    	   console.log("눌렷냐");
                          //console.log("클릭 : "+username2);
                            chatting(this);
                          });
                       
                           f++; 
                         }
                    else{
                       $('#addchatlist').append($('<div/>',{
                             class:'chatlistcontent',
                             id: 'fchatlistdiv'+i,
                             
                         }));
                       $('#fchatlistdiv'+i).append($('<img/>',{
                             src: '<%=request.getContextPath()%>/'+json[i]["otherProfile"],
                             class:'chatuserimg',
                             id: 'fchatuserimg'+i
                            
                         }));
                       $('#fchatlistdiv'+i).append($('<span/>',{
                               text:json[i]["otherName"],
                               
                             class:'chatusername',
                            
                         }));
                       $('#fchatlistdiv'+i).append($('<div/>',{
                             style:'position:absolute',
                             id: 'fchatuserdiv2'+i,
                            
                         })); 
                       $('#fchatuserdiv2'+i).append($('<p/>',{
                             class:'chatpreview',
                             value: json[i]["chatNo"],
                            text:json[i]["previewMessage"]
                         }));
                       /* $('#fchatlistdiv'+i).append($('<button/>',{
                             class:'deletechatBtn',
                             text : 'x'
                            
                         })); */
                       
                       
                       $('#fchatlistdiv'+i).off("click").on("click",function(){
                    	   console.log("눌렷냐");
                     chatting(this);
                          
                          }); 
                       $('#fchatlistdiv'+i).append($('<span/>',{
                          text:json[i]["previewMessageTime"],
                               style:'position:relative; font-size:7px; left:12px; top:6.5px; color:gray;'
                              
                           }));
                           f++; 
                    }
                  console.log("audioflag1"+audioflag1);
                	console.log("audioflag2"+audioflag2);
                	console.log("audioflagc1"+audioflagc1);
                	console.log("audioflagc2"+audioflagc2);
                        console.log("audiosetting?"+audiosetting);
                	
                     if(audioflag1!=audioflag2&&json[0]["otherNo"]==json[0]["previewMessageUserNo"]&&json[0]["readState"]==0&&audiosetting==0
                  		  ||audioflagc1!=audioflagc2&&json[0]["otherNo"]==json[0]["previewMessageUserNo"]&&json[0]["readState"]==0&&audiosetting==0
                  		  ||audioflagm1!=audioflagm2&&json[0]["otherNo"]==json[0]["previewMessageUserNo"]&&json[0]["readState"]==0&&audiosetting==0){
                        audio.play();
                        } 
                    audioflag2=json[0]["previewMessage"];
                    audioflagc2=json[0]["chatNo"];
                    audioflagm2=json[0]["mili"];
                  
                  }
                    
                  
           },
                 
              error:function(xhr,status){
              alert(xhr+" : "+status);   
              }
         });
        timer = setInterval( function () {
        $.ajax({
          url:"<%=request.getContextPath()%>/previewlist",
         type:"get",
         dataType :'text',
         cache : false,
         
         success:function(data){
               $('#addchatlist').children().remove();
                //console.log(data);
                //var json = JSON.parse(data);

                var json = eval("(" + data + ")");
                //console.log(typeof(json));
                //console.log(json);
                	  audioflag1=json[0]["previewMessage"];
                      audioflagc1=json[0]["chatNo"];
                      audioflagm1=json[0]["mili"];
                
                for(var i=0;i<json.length;i++){
                	
                  //console.log(key+":"+json[i][key]);
                  //console.log(json[i]["otherName"]);
                  
                  if(json[i]["otherNo"]==json[i]["previewMessageUserNo"]&&json[i]["readState"]==0)
                  {
                     $('#addchatlist').append($('<div/>',{
                           class:'chatlistcontent',
                           id: 'fchatlistdiv'+i,
                           
                       }));
                     $('#fchatlistdiv'+i).append($('<img/>',{
                           src: '<%=request.getContextPath()%>/'+json[i]["otherProfile"],
                           class:'chatuserimg',
                           id: 'fchatuserimg'+i
                          
                       }));
                     $('#fchatlistdiv'+i).append($('<span/>',{
                             text:json[i]["otherName"],
                           class:'chatusername',
                          
                       }));
                     $('#fchatlistdiv'+i).append($('<div/>',{
                           style:'position:absolute',
                           id: 'fchatuserdiv2'+i,
                          
                       })); 
                     $('#fchatuserdiv2'+i).append($('<p/>',{
                           class:'chatpreview',
                           value: json[i]["chatNo"],
                          text:json[i]["previewMessage"]
                       }));
                     $('#fchatlistdiv'+i).append($('<span/>',{
                             text:json[i]["previewMessageTime"],
                             style:'position:relative; font-size:7px ;top:1px;left:19.5px; color:gray;'
                            
                         }));
                     $('#fchatlistdiv'+i).append($('<img/>',{
                           src: '<%=request.getContextPath()%>/img/alarm.png',
                           class:'alarm',
                           style:'position: relative; width: 15px; height: 15px; top:30px; left: 4px;'
                          
                       }));
                     
                    /*  $('#fchatlistdiv'+i).append($('<button/>',{
                           class:'deletechatBtn',
                           text : 'x'
                          
                       })); */
                     
                     $('#fchatlistdiv'+i).off("click").on("click",function(){
                        
                        //console.log("클릭 : "+username2);
                          chatting(this);
                        });
                     
                         f++; 
                       }
                  else{
                     $('#addchatlist').append($('<div/>',{
                           class:'chatlistcontent',
                           id: 'fchatlistdiv'+i,
                           
                       }));
                     $('#fchatlistdiv'+i).append($('<img/>',{
                           src: '<%=request.getContextPath()%>/'+json[i]["otherProfile"],
                           class:'chatuserimg',
                           id: 'fchatuserimg'+i
                          
                       }));
                     $('#fchatlistdiv'+i).append($('<span/>',{
                             text:json[i]["otherName"],
                             
                           class:'chatusername',
                          
                       }));
                     $('#fchatlistdiv'+i).append($('<div/>',{
                           style:'position:absolute',
                           id: 'fchatuserdiv2'+i,
                          
                       })); 
                     $('#fchatuserdiv2'+i).append($('<p/>',{
                           class:'chatpreview',
                           value: json[i]["chatNo"],
                          text:json[i]["previewMessage"]
                       }));
                     /* $('#fchatlistdiv'+i).append($('<button/>',{
                           class:'deletechatBtn',
                           text : 'x'
                          
                       })); */
                     
                     
                     $('#fchatlistdiv'+i).off("click").on("click",function(){
                        
                   chatting(this);
                        
                        }); 
                     $('#fchatlistdiv'+i).append($('<span/>',{
                        text:json[i]["previewMessageTime"],
                             style:'position:relative; font-size:7px; left:12px; top:6.5px; color:gray;'
                            
                         }));
                         f++; 
                  }
                console.log("audioflag1"+audioflag1);
              	console.log("audioflag2"+audioflag2);
              	console.log("audioflagc1"+audioflagc1);
              	console.log("audioflagc2"+audioflagc2);
                      console.log("audiosetting?"+audiosetting);
              	
                   if(audioflag1!=audioflag2&&json[0]["otherNo"]==json[0]["previewMessageUserNo"]&&json[0]["readState"]==0&&audiosetting==0
                		  ||audioflagc1!=audioflagc2&&json[0]["otherNo"]==json[0]["previewMessageUserNo"]&&json[0]["readState"]==0&&audiosetting==0
                		  ||audioflagm1!=audioflagm2&&json[0]["otherNo"]==json[0]["previewMessageUserNo"]&&json[0]["readState"]==0&&audiosetting==0){
                      audio.play();
                      } 
                  audioflag2=json[0]["previewMessage"];
                  audioflagc2=json[0]["chatNo"];
                  audioflagm2=json[0]["mili"];
                
                }
                  
                
         },
               
            error:function(xhr,status){
            alert(xhr+" : "+status);   
            }
       });
        }, 1000);
        
        document.getElementById('chatroom').style.display = 'block';
        document.getElementById('chatroom').style.left = "100px";
        console.log("스크롤탑"+$(document).scrollTop());
        var height=$(document).scrollTop();
        document.getElementById('chatroom').style.top = (height+100)+"px";
    }
   var friendlist;
   //친구창 화면
    function friendlist() {
    	document.getElementById('chatroomfriendroom').style.zIndex = count;
    	count++
      //친구목록 불러오기 사진, 이름, 번호
       $.ajax({
         url:"<%=request.getContextPath()%>/addfriendlist",
         type:"get",
         cache : false,
         async:false,
         success:function(data){
            console.log(data);
            $('#friendlistdiv').children().remove();
            for(var j=0;j<data.length;j++){
            	
                 $('#friendlistdiv').append($('<label/>',{
                     for: "friendcheck"+j,
                     id: "friendlabel"+j
                     }));
                 $('#friendlabel'+j).append($('<div/>',{
                     class: "friend",
                     id: "friend"+j
                     }));
                 $('#friend'+j).append($('<img/>',{
                     class: "frienduserimg",
                     id: "frienduserimg"+j,
                     src :'<%=request.getContextPath()%>/'+data[j]["profile"]
                     }));
                 $('#friend'+j).append($('<input/>',{
                     type:"radio",
                     class: "friendcheck",
                     id: "friendcheck"+j,
                     name : "name",
                     }));
                 $('#friend'+j).append($('<span/>',{
                     class: "friendusername",
                     id: "friendusername"+j,
                     text:data[j]["name"],
                     value:""+data[j]["userNo"]
                     }));
                 $("#friendcheck"+j).off("click").on("click",function(){
                    selectedfriend(this);
                  });    
            }
            //console.log(decodeURI(data['name']));
             for(var i=0;i<data.length;i++){
               for(var key in data[i])
               {
                  console.log(key+":"+data[i][key]);
               }
            } 
         },error:function(xhr,status){
            alert(xhr+" : "+status);   
         }
      });
      
        var user=document.getElementsByName('name');
        for(var i=0;i<user.length;i++){
            user[i].checked=false;
        }
        selectedfriend();
        document.getElementById('chatroomfriendroom').style.display = 'block';
        document.getElementById('chatroomfriendroom').style.left = "500px";
        var height2=$(document).scrollTop();
        document.getElementById('chatroomfriendroom').style.top = (height2+100)+"px";
    }
//채팅하는 화면
var timer2;
var data1;
var data2;

    function chatting(obj) {
    	document.getElementById('chattingscreen').style.zIndex = count;
    	count++
       clearInterval(timer2);
       $("#messagecontent").scrollTop($("#messagecontent")[0].scrollHeight);
       var name = $($(obj).children()[1]).text();
       console.log("name!!!!!"+name);
       $('#chattingroomName').html(name);
       //console.log($($($(obj).children()[2]).children()[0]).attr('value'));
       var img= $($(obj).children()[0]).attr('src');
       var no=$($($(obj).children()[2]).children()[0]).attr('value');
       $('#submitchattextBtn').attr('value',no);
        var div = document.createElement('div');
         div.innerHTML = obj;
         div.id='previewchats';
         console.log(div);
         $.ajax({
             url:"<%=request.getContextPath()%>/callChatLog",
             type:"get",
             data:{"chatNo": no},
             cache : false,
             dataType :'text',
             async:false,
             success:function(data){
                $('#messagecontent').children().remove();
                console.log("채팅 데이터 "+data);
                 var json = eval("(" + data + ")");
						console.log("json[0]메세지"+json[0]["chatMessage"]);
					if(json[0]["chatMessage"].length>0){
                    for(var i=0;i<json.length;i++){
                    	console.log("들어온 json[0]메세지"+json[0]["chatMessage"]);
                       if(json[i]["myNo"]==json[i]["chatUserNo"]){
                          console.log("시간: "+json[i]["chatDate"]);
                          $('#messagecontent').append($('<div/>',{
                                class: "clear",
                                }));
                          $('#messagecontent').append($('<div/>',{
                                class:"from-me",
                                id:"from-me"+i
                                }));
                            $('#from-me'+i).append($('<div/>',{
                                class: "sendDiv",
                                id:"sendDiv"+i
                                }));
                            $('#sendDiv'+i).append($('<p/>',{
                                class: "myMsg",
                                text: json[i]["chatMessage"]
                                }));
                            $('#from-me'+i).append($('<span/>',{
                                class: "sendDate",
                                text: json[i]["chatDate"]
                                }));
                            
                           
                       }
                       else{
                          //console.log("다를 때: "+json[i]["chatMessage"]);
                          $('#messagecontent').append($('<div/>',{
                                class: "clear",
                                }));
                          $('#messagecontent').append($('<div/>',{
                                class:"from-them",
                                id:"from-them"+i
                                }));
                            $('#from-them'+i).append($('<div/>',{
                                class: "receiveUser",
                                id:"receiveUser"+i
                                }));
                            $('#receiveUser'+i).append($('<img/>',{
                                class: "receiveUserImg",
                                src: '<%=request.getContextPath()%>/'+img
                                }));
                            $('#receiveUser'+i).append($('<span/>',{
                                class: "receiveUserName",
                                text:name
                                }));
                            $('#from-them'+i).append($('<div/>',{
                                class: "receiveDiv",
                                id: "receiveDiv"+i
                                }));

                            $('#receiveDiv'+i).append($('<p/>',{
                                class: "receiveMsg",
                                text: json[i]["chatMessage"]
                                }));           
                            $('#from-them'+i).append($('<span/>',{
                                class: "receiveDate",
                                text: json[i]["chatDate"]
                                })); 
                           }
                     
                   $("#messagecontent").scrollTop($("#messagecontent")[0].scrollHeight);
                   //clearInterval(timer2);
                       
                   
                       }}
                    },
                    error:function(xhr,status){
                alert(xhr+" : "+status);   
             }
          });
       timer2 = setInterval( function () {
         $.ajax({
         url:"<%=request.getContextPath()%>/callChatLog",
         type:"get",
         data:{"chatNo": no},
         cache : false,
         dataType :'text',
         async:false,
         success:function(data){
            $('#messagecontent').children().remove();
             var json = eval("(" + data + ")");
                console.log(json);
                data1=json.length;
                console.log("data1"+data1);
                console.log("data2"+data2);
                if(json[0]["chatMessage"].length>0){
                for(var i=0;i<json.length;i++){
                   if(json[i]["myNo"]==json[i]["chatUserNo"]){
                      console.log("시간: "+json[i]["chatDate"]);
                      $('#messagecontent').append($('<div/>',{
                            class: "clear",
                            }));
                      $('#messagecontent').append($('<div/>',{
                            class:"from-me",
                            id:"from-me"+i
                            }));
                        $('#from-me'+i).append($('<div/>',{
                            class: "sendDiv",
                            id:"sendDiv"+i
                            }));
                        $('#sendDiv'+i).append($('<p/>',{
                            class: "myMsg",
                            text: json[i]["chatMessage"]
                            }));
                        $('#from-me'+i).append($('<span/>',{
                            class: "sendDate",
                            text: json[i]["chatDate"]
                            }));
                        
                       
                   }
                   else{
                      //console.log("다를 때: "+json[i]["chatMessage"]);
                      $('#messagecontent').append($('<div/>',{
                            class: "clear",
                            }));
                      $('#messagecontent').append($('<div/>',{
                            class:"from-them",
                            id:"from-them"+i
                            }));
                        $('#from-them'+i).append($('<div/>',{
                            class: "receiveUser",
                            id:"receiveUser"+i
                            }));
                        $('#receiveUser'+i).append($('<img/>',{
                            class: "receiveUserImg",
                            src: img
                            }));
                        $('#receiveUser'+i).append($('<span/>',{
                            class: "receiveUserName",
                            text:name
                            }));
                        $('#from-them'+i).append($('<div/>',{
                            class: "receiveDiv",
                            id: "receiveDiv"+i
                            }));

                        $('#receiveDiv'+i).append($('<p/>',{
                            class: "receiveMsg",
                            text: json[i]["chatMessage"]
                            }));           
                        $('#from-them'+i).append($('<span/>',{
                            class: "receiveDate",
                            text: json[i]["chatDate"]
                            })); 
                       }
                   if(data1!=data2){
               $("#messagecontent").scrollTop($("#messagecontent")[0].scrollHeight);
               //clearInterval(timer2);
                   }
               
                   }
                   data2=json.length;}
                },
                error:function(xhr,status){
            alert(xhr+" : "+status);   
         }
      });
         }, 1000);
         $.ajax({
          url:"<%=request.getContextPath()%>/updatereadstate",
          type:"get",
          data:{"chatNo": no},
          cache : false,
          success:function(data){
                 },
                 error:function(xhr,status){
             alert(xhr+" : "+status);   
          }
       });
       
   

        document.getElementById('chattingscreen').style.display = 'block';
        document.getElementById('chattingscreen').style.left = "800px";
        var height3=$(document).scrollTop();
        document.getElementById('chattingscreen').style.top = (height3+100)+"px";
    }

    function removechat(obj) {
        document.getElementById('addchatlist').removeChild(obj.parentNode);

    }
    function removechatlist(){
       document.getElementById('chatroom').style.display='none';
       clearInterval(timer); 

    }
    function closeChatting(){
       document.getElementById('chattingscreen').style.display='none';
       clearInterval(timer2); 
    }
    var callaudio=1;
    function alarmOnOff(){
    	console.log("callaudio"+callaudio);
    	if(callaudio%2==1){
    	audiosetting=0;
    	callaudio++;
    	$('#alarmset').css('background',' url("<%=request.getContextPath()%>/img/alarmon.png") no-repeat');
    	}
    	else if(callaudio%2==0) {
    	audiosetting=1;
    	callaudio++;
    	$('#alarmset').css('background','url("<%=request.getContextPath()%>/img/alarmoff.png") no-repeat');

    	}
    	
    }
//채팅프리뷰     
var f=0;
    function addchat() {
       var dataUserName=new Array();
       
       var user=document.getElementsByName('name');
        var j=0;
        var i=0;
        var imgnum=0;
        var username=new Array();
        var count=0;
        for(var i=0;i<user.length;i++){
            if(user[i].checked){
                username[j]=$(user[i]).siblings('span').text();
                dataUserName[j]=$(user[i]).siblings('span').attr("value");
                //console.log(typeof($(user[i]).siblings('span').attr("value")));
                //console.log($(user[i]).prevAll('img').first().attr("src"));
                //console.log(username[j]);
                j++
                }
        }
        for(i=0;i<user.length;i++){
            if(user[i].checked){
                count++;
                console.log(count);
                if(count==1){
                    imgnum=i;
                }
            }
        }
        //console.log(imgnum);
        
       $.ajax({
         url:"<%=request.getContextPath()%>/preview",
         type:"get",
         cache : false,
         async:false,
         data:{"name":dataUserName},
         success:function(data){
                console.log(data);
                console.log(typeof(data));
        if(count>=1){
        var arr=username.join(', ');
        $('#addchatlist').append($('<div/>',{
            class:'chatlistcontent',
            id: 'chatlistdiv'+f,
            
        }));
        if(count>=2){
      $('#chatlistdiv'+f).append($('<img/>',{
            src: 'img/groupchatimg.png',
            class:'chatuserimg',
            id: 'chatuserimg'+f
           
        }));}
        else{
           $('#chatlistdiv'+f).append($('<img/>',{
                src: $(user[imgnum]).prevAll('img').first().attr("src"),
                class:'chatuserimg',
                id: 'chatuserimg'+f
               
            }));
        }
      $('#chatlistdiv'+f).append($('<span/>',{
              text:arr,
            class:'chatusername',
           
        }));
      $('#chatlistdiv'+f).append($('<div/>',{
            style:'position:absolute',
            id: 'chatuserdiv2'+f,
           
        })); 
      $('#chatuserdiv2'+f).append($('<p/>',{
            class:'chatpreview',
            value: data,
           text:""
        }));
      /* $('#chatlistdiv'+f).append($('<button/>',{
            class:'deletechatBtn',
            text : 'x'
           
        })); */
      username3=arr;
      userimg3=$(user[imgnum]).prevAll('img').first().attr("src");
      chatno3=data;
      $('#chatlistdiv'+f).off("click").on("click",function(){
         console.log("눌렷냐");
           chatting(this);
        }); 
        f++;    
        }
              
            },
            error:function(xhr,status){
            alert(xhr+" : "+status);   
            }
       });
    }
    //친구찾기
    function findFriend(){
       console.log("키눌림");
       $.ajax({
         url:"<%=request.getContextPath()%>/findFriend",
         type:"get",
         data:{"inputFriendName":$('#inputFriendName').val()},
         cache : false,
         success:function(data){
            console.log(data);
             $('#friendlistdiv').children().remove();
             $('#selectedfriendlist').children().remove();
             for(var j=0;j<data.length;j++){
                 $('#friendlistdiv').append($('<label/>',{
                     for: "friendcheck"+j,
                     id: "friendlabel"+j
                     }));
                 $('#friendlabel'+j).append($('<div/>',{
                     class: "friend",
                     id: "friend"+j
                     }));
                 $('#friend'+j).append($('<img/>',{
                     class: "frienduserimg",
                     id: "frienduserimg"+j,
                     src :data[j]["profile"]
                     }));
                 $('#friend'+j).append($('<input/>',{
                     type:"radio",
                     class: "friendcheck",
                     id: "friendcheck"+j,
                     name : "name",
                     }));
                 $('#friend'+j).append($('<span/>',{
                     class: "friendusername",
                     id: "friendusername"+j,
                     text:data[j]["name"],
                     value:""+data[j]["userNo"]
                     }));
                 $("#friendcheck"+j).off("click").on("click",function(){
                    selectedfriend(this);
                  });    
            }
            //console.log(decodeURI(data['name']));
             for(var i=0;i<data.length;i++){
               for(var key in data[i])
               {
                  console.log(key+":"+data[i][key]);
               }
            } 
         },error:function(xhr,status){
            alert(xhr+" : "+status);   
         }
      });
    }
    
   
    
    
    function selectedfriend(obj){
        var user=document.getElementsByName('name');
        var username;
        document.getElementById('selectedfriendlist').innerHTML="";

        for(var i=0;i<user.length;i++){
            if(user[i].checked){
                username = $(user[i]).siblings('span').text();
                $('#selectedfriendlist').append($('<div/>',{
                    class:'usernamediv',
                    id: username+i,
                    style:"background-color: rgb(163, 162, 162); margin-bottom: 5px; position: relative; left: 17px; top: 10px; border-radius: 8px; color: white; width: 80px; height: 23px;", 
                }));
                $('#'+username+i).append($('<span/>',{
                    class:'usernamespan',
                    id: username+"span"+i,
                    style:"display: block; position: relative; font-size: 12px; top: 2px; left: 6px; width: 50px; height: 15px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;",
                    text: username
                }));
                $('#'+username+i).append($('<button/>',{
                    class:'userbtn',
                    id: i,
                    style:"cursor: pointer; color: white; position: relative; float: right; width: 15px; height: 15px; bottom: 13px; right: 5px; font-size: 13px; background-color: transparent !important; background-image: none !important; border-color: transparent; border: none; font-weight: bold",
                    text: "x",
                }));
            }
                $("#"+i).off("click").on("click",function(){
                    btn(this);
                });
        }
    
}
    function btn(name){
        console.log(name);
        var su=name.id;
        console.log(su);
        var user=document.getElementsByName('name');
        user[su].checked=false;
        (name.parentNode).parentNode.removeChild(name.parentNode);
    }

   function inputChatting(){
      
      if($('#inputchattext').val().length!=0){
         console.log("널아닐대만 들어와라");
      $.ajax({
         url:"<%=request.getContextPath()%>/inputchat",
         type:"get",
         data:{"inputChatText":$('#inputchattext').val(),
         "chatNo" :$('#submitchattextBtn').attr('value')   
         },
         cache : false,
         success:function(data){
            $('#inputchattext').val('');
      $("#messagecontent").scrollTop($("#messagecontent")[0].scrollHeight);
         },error:function(xhr,status){
            alert(xhr+" : "+status);   
         }
      });
      }
   }

</script>
<script type='text/javascript'>
    var img_L = 0;
    var img_T = 0;
    var targetObj;

    function getLeft(o) {
        return parseInt(o.style.left.replace('px', ''));
    }
    function getTop(o) {
        return parseInt(o.style.top.replace('px', ''));
    }

    // 이미지 움직이기
    function moveDrag(e) {
        var e_obj = window.event ? window.event : e;
        var dmvx = parseInt(e_obj.clientX + img_L);
        var dmvy = parseInt(e_obj.clientY + img_T);
        targetObj.style.left = dmvx + "px";
        targetObj.style.top = dmvy + "px";
        return false;
    }
var heightt;
    // 드래그 시작
    function startDrag(e, obj) {
        targetObj = obj;
        var e_obj = window.event ? window.event : e;
        img_L = getLeft(obj) - e_obj.clientX;
        img_T = getTop(obj) - e_obj.clientY;
        console.log(img_T);
        heightt=img_T;
        document.onmousemove = moveDrag;
        document.onmouseup = stopDrag;
        if (e_obj.preventDefault) e_obj.preventDefault();
    }

    // 드래그 멈추기
    function stopDrag() {
        document.onmousemove = null;
        document.onmouseup = null;
    }
    $(function(){
       $('#inputchattext').on('keydown',function(event){
          if(event.keyCode==13)
             if(!event.shiftKey){
                event.preventDefault();
                var button=document.getElementById('submitchattextBtn');
                button.click();
             }
       })
    })

    $(document).scroll(function noteScroll(){
    	var position = $(window).scrollTop();
    	console.log(heightt);
    	 $('#chatroom').css('top', position+100);
    	 $('#supportMainDiv').css('top', position+100);
    	 $('#chatroomfriendroom').css('top', position+100);
    	 $('#chattingscreen').css('top', position+100);
    	 $('#cofirmSupportDiv').css('top', position+100);
    	 
   });

</script>
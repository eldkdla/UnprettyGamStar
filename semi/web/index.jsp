<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>임시메인</title>
<script src="http://code.jquery.com/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/profileAlert.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/chat.css">
<link href="https://fonts.googleapis.com/css?family=Jua|Song+Myung|Stylish|Yeon+Sung|Gothic+A1&amp;subset=korean"
        rel="stylesheet">


</head>	
<body>
<script>

<%-- if(<%=request.getSession().getAttribute("userNo")%>==null)
	{
	alert('로긴해~');
	} --%>
	
</script>

<%
String login="";
if(request.getSession().getAttribute("userNo") == null)
{
	response.sendRedirect(request.getContextPath()+"/view/login.jsp");
}else
{
	login = (int)request.getSession().getAttribute("userNo") + "";
}
%>
 
<a href="<%=request.getContextPath()%>/view/profile">내정보</a>   <!-- /view/myprofile -->

<%-- <a href="<%=request.getContextPath()%>/view/profilemodifyStart">내정보수정</a> --%>
<a href="<%=request.getContextPath()%>/view/login.jsp">로그인</a>
<p><%=login%>가 로그인 한 상태입니다.</p>
<a href="<%=request.getContextPath()%>/logout">로그아웃</a>

<% if(request.getSession().getAttribute("userNo")!=null&&(Integer)request.getSession().getAttribute("userNo")<0) {
	request.getRequestDispatcher("/admin/goAdminMain").forward(request, response);
} %>
    
    
     <!--알림버튼(수정해야함)-->
   <button onclick=" chat()">알림</button>



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
            <span style="color: white; font-size: 11px; position: relative; left: 5px;top:2px;">대화상대 선택</span>
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
    
</body>


<script>


   var timer;
   var audiosetting=1;
  
   var audio = new Audio('audio/alarm.mp3');
   var audioflag1;
   var audioflag2;
   var audioflagc1;
   var audioflagc2;
   var audioflagm1;
   var audioflagm2;

    function chat() {
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
                             src: json[i]["otherProfile"],
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
                             src: 'img/alarm.png',
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
                             src: json[i]["otherProfile"],
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
                           src: json[i]["otherProfile"],
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
                           src: 'img/alarm.png',
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
                           src: json[i]["otherProfile"],
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
        document.getElementById('chatroom').style.top = "50px";
    }
   var friendlist;
   //친구창 화면
    function friendlist() {
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
      
        var user=document.getElementsByName('name');
        for(var i=0;i<user.length;i++){
            user[i].checked=false;
        }
        selectedfriend();
        document.getElementById('chatroomfriendroom').style.display = 'block';
        document.getElementById('chatroomfriendroom').style.left = "500px";
        document.getElementById('chatroomfriendroom').style.top = "50px";
    }
//채팅하는 화면
var timer2;
var data1;
var data2;

    function chatting(obj) {
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
                 var json = eval("(" + data + ")");

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
                     
                   $("#messagecontent").scrollTop($("#messagecontent")[0].scrollHeight);
                   //clearInterval(timer2);
                       
                   
                       }
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
                   data2=json.length;
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
        document.getElementById('chattingscreen').style.top = "400px";
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
    	$('#alarmset').css('background',' url("img/alarmon.png") no-repeat');
    	}
    	else if(callaudio%2==0) {
    	audiosetting=1;
    	callaudio++;
    	$('#alarmset').css('background','url("img/alarmoff.png") no-repeat');

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
    
    //인덱스 변경
    var count = 1000;
    function zindexchange(test) {
        document.getElementById(test.id).style.zIndex = count++;
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

    // 드래그 시작
    function startDrag(e, obj) {
        targetObj = obj;
        var e_obj = window.event ? window.event : e;
        img_L = getLeft(obj) - e_obj.clientX;
        img_T = getTop(obj) - e_obj.clientY;

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

</script>


<script>
	//어드민 모바일 메뉴 열기 위한 함수
	function openAdminMobileMenu(){
	    $('nav').toggle();
	}
</script>
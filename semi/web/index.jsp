<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.min.js"></script>

<style>
	
	#storyContent{
	display:none;
	position:fixed;
	width:100%;
	height: 100%;
	background-color: rgba(0,0,0,0.6);
	overflow:hidden;
	left: 0;
    top: 0;
	z-index:200;
	}
	
	#storyContent>video{
		position:relative;
		margin: 5% 25% 35% 25%; 
		width:500px;
		height: 760px;
		max-width:500px;
		max-height:760px;
		border:none;
		object-fit:fill;
	}
	
	#pBar{
		position:absolute;
	    float:left;
	    width:480px;
	    height:10px;
	    background-color: #A6A6A6;
	    margin-top:60px;
	    left:50%;
		transform:translateX(-50%);
		z-index: 250;
	}
	#pBar2{
		position:absolute;
	    width:0px;
	    height:10px;
	    background-color: #F6F6F6;
	}
	
	#storyContent>img{
	position: absolute;
	left:710px;
	margin-top:75px;
	z-index: 300;
	}

	
</style>

</head>
<body>
<a href="<%=request.getContextPath()%>/view/profile">내정보</a>   <!-- /view/myprofile -->
<a href="<%=request.getContextPath()%>/view/profilemodifyStart">내정보수정</a>

<div id="storyContent">
<div id="pBar" onclick="clickedBar();">
	<div id="pBar2"> </div>
</div>
<img alt="" src="<%=request.getContextPath() %>/img/soundOff.png">
<video src="yyyyy.mp4" muted autoplay loop id="storyVideo"></video>
</div>

<button onclick="openStory();">adfad</button>


</body>

<script>


	$(document).ready(function(){
		    barSize=480;
		    myMovie=document.getElementById('storyVideo');
		    playButton=document.getElementById('playButton');
		    bar=document.getElementById('pBar');
		    progressBar=document.getElementById('pBar2');
	});

	function update(){
	    if(!myMovie.ended){
	        var size=parseInt(myMovie.currentTime*barSize/myMovie.duration);
	        progressBar.style.width=size+'px'
	    }else{
	        progressBar.style.width='0px'
	        window.clearInterval(updateBar);
	    }
	 }
	
	function clickedBar(e) {
		e.stopPropagation();
	    if(!myMovie.paused && !myMovie.ended){
	        var mouseX = e.pageX-bar.offsetLeft;
	        var newtime = mouseX*myMovie.duration/barSize;
	        myMovie.currentTime = newtime;
	        progressBar.style.width=mouseX+'px';
	    }
	 }


	function openStory(){
		$("#storyContent").fadeIn();
		updateBar=setInterval(update,1000);
		if(!$('#pBar2').width()==0){
			myMovie.currentTime=0;
			progressBar.style.width='0px'
		    window.clearInterval(updateBar);
		}
	}
	
	$("#storyContent>video").click( function (e){
		e.stopPropagation();
	    if( $("#storyContent>video").prop('muted')){
	          $("#storyContent>video").prop('muted', false);
	          $("#storyContent>img").attr("src","<%=request.getContextPath() %>/img/soundOn.png");
	    } 
	    else {
	      $("#storyContent>video").prop('muted', true);
	      $("#storyContent>img").attr("src","<%=request.getContextPath() %>/img/soundOff.png");
	    }
	  });
	 
	$(document).keyup(function(e) {
	    if (e.keyCode == 27) { 
	    	$("#storyContent").fadeOut();
	   }
	});
	 
	$("#storyContent").click(function(){
		$("#storyContent").fadeOut();
	});
	
</script> 
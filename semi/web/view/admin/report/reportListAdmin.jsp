<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.gamstar.admin.report.model.vo.ReportBoard, com.gamstar.admin.report.model.vo.ReportBoardMedia" %>
<%
	Map<Integer,List<ReportBoardMedia>> mList=(Map<Integer,List<ReportBoardMedia>>)request.getAttribute("mediaList");
	List<ReportBoard> list=(List)request.getAttribute("list");
	int numPerPage=(int)request.getAttribute("numPerPage");
	String pageBar=(String)request.getAttribute("pageBar");
%>
    <style>
    @media all and (min-width:1067px){
    /* 첨부파일 미리보기 */
        div.showMedia{
            width : 100%;
            height : 30%;
            padding: auto;
            line-height: 30%;
            white-space: nowrap;
            overflow-x:auto;
            overflow-y: hidden;
        }
        div.scrollWrapper{
            text-align: center;
        }
        .supImg{
            width : 30%;
            cursor: pointer;
            vertical-align: middle;
        }
        .supImg:hover{
            width : 35%;
            cursor: pointer;
            vertical-align: middle;
        }
     /* 신고 검색용 창 */
    .reportSearch{
        text-align:center;
    }
    .reportSearch>form{
        display: inline;
    }
    .reportSearch>form>select{
        vertical-align: middle;
    }
    .reportSearch>form>input{
        vertical-align: middle;
    }
    .reportSearch>form>button>img{
        width:18px; 
        opacity: 0.5;
    }

    /* 신고 테이블(게시판) */
    table.type {
        color:rgba(0, 0, 0, 0.7);
        padding:10px;
        border-collapse: collapse;
        text-align: center;
        margin:10px;
    }
    table.type thead th {
        color:rgba(0, 0, 0, 0.7);
        font-family: 'Nanum Gothic', sans-serif;
        line-height: 1.5;
        padding:8px;
        border-top: 1px solid rgba(189,189,189,0.8);
        border-bottom: 1px solid rgba(189,189,189,0.8);
    }
    table.type td {
        width: 350px;
        vertical-align: top;
        line-height:1.6;
        border-bottom: 1px solid rgba(189,189,189,0.4);
        font-family: 'Nanum Gothic', sans-serif;
    }
    table.type td a{
       text-decoration: none; 
       color:rgba(0, 0, 0, 0.7);
    }

    /* 신고 확인 (정지버튼) */
    .stopBtn{
        background-color:rgb(255, 79, 79);
        color:white;
        border:none;
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
    }
    .stopBtn:focus{
        outline: none;
    }
    /* 신고 취소 (삭제버튼) */
    .cancelBtn{
        background-color:white;
        color:rgba(0, 0, 0, 0.7);
        border:1px solid rgba(189,189,189,0.8);
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
    }
    .cancelBtn:focus{
        outline: none;
    }

    /* 이미지레이어(정지 뜨는거) */
    .popupLayer {
        position: absolute;
        display: none;
        background-color: #ffffff;
        border: solid 2px #d0d0d0;
        width:200px;
        height:120px;
        padding: 10px;
        text-align: left;
        line-height: 200%;
    }
    .popupLayer div#layerContent {
        position: absolute;
        top: 5px;
        right: 5px
    }

    div.layerBtn{
        text-align: center; 
        width:100%; 
        display:block;
        position:relative;
    }
    .closeLayer{
        background-color: white;
        border:1px solid rgba(189,189,189,0.8);
        color:rgba(0, 0, 0, 0.7);
        cursor: pointer;
        border-top-right-radius:10px;
        border-bottom-right-radius:10px;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
        float:left;
        margin-left: 25%;
    }
    .closeLayer:focus{
        outline: none;
    }
    .acceptLayer{
        border:1px solid rgba(189,189,189,0.8);
        background-color:rgb(255, 79, 79);
        color:white;
        cursor: pointer;
        border-top-right-radius:10px;
        border-bottom-right-radius:10px;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
        float:right;
        margin-right: 22%;
    } 
    .acceptLayer:focus{
        outline: none;
    }
    form#stopForm{
        display:block;
    }

    .layerContent{
        /* text-align: left; */
        padding:15px;
    }
    .layerContent input[type=number]{
        width:70%;
    }

    form#searchReport{
        margin-right:10px;
    }
    /* 신고기각? 버튼 */
    .deleteBtn{
        background-color:rgba(255, 173, 20, 0.8);
        color:rgba(0, 0, 0, 0.7);
        border:1px solid rgba(189,189,189,0.8);
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
    }
    .deleteBtn:focus{
        outline: none;
    }
}


    @media all and (min-width:601px) and (max-width:1066px){
    /* 첨부파일 미리보기 */
        div.showMedia{
            width : 100%;
            height : 30%;
            padding: auto;
            line-height: 30%;
            white-space: nowrap;
            overflow-x:auto;
            overflow-y: hidden;
        }
        div.scrollWrapper{
            text-align: center;
        }
        .supImg{
            width : 30%;
            cursor: pointer;
            vertical-align: middle;
        }
        .supImg:hover{
            width : 35%;
            cursor: pointer;
            vertical-align: middle;
        }
    /* 신고 검색용 창 */
    .reportSearch{
        text-align:center;
    }
    .reportSearch>form{
        display: inline;
    }
    .reportSearch>form>select{
        vertical-align: middle;
    }
    .reportSearch>form>input{
        vertical-align: middle;
    }
    .reportSearch>form>button>img{
        width:18px; 
        opacity: 0.5;
    }

    /* 신고 테이블(게시판) */
    table.type {
        width:100%;
        color:rgba(0, 0, 0, 0.7);
        border-collapse: collapse;
        text-align: center;
    }
    table.type thead th {
        color:rgba(0, 0, 0, 0.7);
        font-family: 'Nanum Gothic', sans-serif;
        line-height: 1.5;
        border-top: 1px solid rgba(189,189,189,0.8);
        border-bottom: 1px solid rgba(189,189,189,0.8);
    }
    table.type td {
        vertical-align: top;
        border-bottom: 1px solid rgba(189,189,189,0.4);
        font-family: 'Nanum Gothic', sans-serif;
    }
    table.type td a{
       text-decoration: none; 
       color:rgba(0, 0, 0, 0.7);
    }

    /* 신고 확인 (정지버튼) */
    .stopBtn{
        background-color:rgb(255, 79, 79);
        color:white;
        border:none;
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
        margin-bottom: 2px;
    }
    .stopBtn:focus{
        outline: none;
    }
    /* 신고 취소 (삭제버튼) */
    .cancelBtn{
        background-color:white;
        color:rgba(0, 0, 0, 0.7);
        border:1px solid rgba(189,189,189,0.8);
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
    }
    .cancelBtn:focus{
        outline: none;
    }
    /* 신고기각? 버튼 */
    .deleteBtn{
        background-color:rgba(255, 173, 20, 0.8);
        color:rgba(0, 0, 0, 0.7);
        border:1px solid rgba(189,189,189,0.8);
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
    }
    .deleteBtn:focus{
        outline: none;
    }

    /* 이미지레이어(정지 뜨는거) */
    .popupLayer {
        position: absolute;
        display: none;
        background-color: #ffffff;
        border: solid 2px #d0d0d0;
        width:200px;
        height:120px;
        padding: 10px;
        text-align: left;
        line-height: 200%;
    }
    .popupLayer div#layerContent {
        position: absolute;
        top: 5px;
        right: 5px
    }

    div.layerBtn{
        text-align: center; 
        width:100%; 
        display:block;
        position:relative;
    }
    .closeLayer{
        background-color: white;
        border:1px solid rgba(189,189,189,0.8);
        color:rgba(0, 0, 0, 0.7);
        cursor: pointer;
        border-top-right-radius:10px;
        border-bottom-right-radius:10px;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
        float:left;
        margin-left: 25%;
    }
    .closeLayer:focus{
        outline: none;
    }
    .acceptLayer{
        border:1px solid rgba(189,189,189,0.8);
        background-color:rgb(255, 79, 79);
        color:white;
        cursor: pointer;
        border-top-right-radius:10px;
        border-bottom-right-radius:10px;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
        float:right;
        margin-right: 22%;
    } 
    .acceptLayer:focus{
        outline: none;
    }
    form#stopForm{
        display:block;
    }

    .layerContent{
        /* text-align: left; */
        padding:15px;
    }
    .layerContent input[type=number]{
        width:70%;
    }

    form#searchReport{
        margin-right:10px;
    }
}


    @media all and (min-width:150px) and (max-width:600px){
    /* 첨부파일 미리보기 */
        div.showMedia{
            width : 100%;
            height : 30%;
            padding: auto;
            line-height: 30%;
            white-space: nowrap;
            overflow-x:auto;
            overflow-y: hidden;
        }
        div.scrollWrapper{
            text-align: center;
        }
        .supImg{
            width : 30%;
            cursor: pointer;
            vertical-align: middle;
        }
        .supImg:hover{
            width : 35%;
            cursor: pointer;
            vertical-align: middle;
        }
        /* 신고 검색용 창 */
    .reportSearch{
        text-align:center;
    }
    .reportSearch>form{
        display: inline;
    }
    .reportSearch>form>select{
        vertical-align: middle;
    }
    .reportSearch>form>input{
        vertical-align: middle;
    }
    .reportSearch>form>button>img{
        width:18px; 
        opacity: 0.5;
    }

    /* 신고 테이블(게시판) */
    table.type {
        width:100%;
        color:rgba(0, 0, 0, 0.7);
        border-collapse: collapse;
        text-align: center;
    }
    table.type thead th {
        color:rgba(0, 0, 0, 0.7);
        font-family: 'Nanum Gothic', sans-serif;
        border-top: 1px solid rgba(189,189,189,0.8);
        border-bottom: 1px solid rgba(189,189,189,0.8);
    }
    table.type td {
        vertical-align: top;
        border-bottom: 1px solid rgba(189,189,189,0.4);
        font-family: 'Nanum Gothic', sans-serif;
    }
    table.type td a{
       text-decoration: none; 
       color:rgba(0, 0, 0, 0.7);
    }

    /* 신고 확인 (정지버튼) */
    .stopBtn{
        background-color:rgb(255, 79, 79);
        color:white;
        border:none;
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
    }
    .stopBtn:focus{
        outline: none;
    }
    /* 신고 취소 (삭제버튼) */
    .cancelBtn{
        background-color:white;
        color:rgba(0, 0, 0, 0.7);
        border:1px solid rgba(189,189,189,0.8);
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
    }
    .cancelBtn:focus{
        outline: none;
    }
    /* 신고기각? 버튼 */
    .deleteBtn{
        background-color:rgba(255, 173, 20, 0.8);
        color:rgba(0, 0, 0, 0.7);
        border:1px solid rgba(189,189,189,0.8);
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
    }
    .deleteBtn:focus{
        outline: none;
    }

    .popupLayer {
        position: absolute;
        display: none;
        background-color: #ffffff;
        border: solid 2px #d0d0d0;
        padding: 10px;
        text-align: left;
        line-height: 200%;
    }
    .popupLayer div#layerContent {
        position: absolute;
        top: 5px;
        right: 5px
    }

    div.layerBtn{
        text-align: center; 
        width:100%; 
        display:block;
        position:relative;
    }
    .closeLayer{
        background-color: white;
        border:1px solid rgba(189,189,189,0.8);
        color:rgba(0, 0, 0, 0.7);
        cursor: pointer;
        border-top-right-radius:10px;
        border-bottom-right-radius:10px;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
        float:left;
        margin-left: 25%;
    }
    .closeLayer:focus{
        outline: none;
    }
    .acceptLayer{
        border:1px solid rgba(189,189,189,0.8);
        background-color:rgb(255, 79, 79);
        color:white;
        cursor: pointer;
        border-top-right-radius:10px;
        border-bottom-right-radius:10px;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
        float:right;
        margin-right: 22%;
    } 
    .acceptLayer:focus{
        outline: none;
    }
    form#stopForm{
        display:block;
    }

    .layerContent{
        /* text-align: left; */
        padding:15px;
    }
    .layerContent input[type=number]{
        width:70%;
    }

    form#searchReport{
        margin-right:10px;
    }
} 
    </style>
   <%@include file='/view/admin/common/header.jsp' %>
    <script>
        // 전체 체크용 기능
         $(document).ready(function(){
        $('.checkAll').click(function(){
            $('.checks').trigger("click");
        });
    });

        //레이어를 나타낼 창 
        
	    $(function(){
		/* 클릭 클릭시 클릭을 클릭한 위치 근처에 레이어가 나타난다. */
		$('.imgSelect').click(function(e)
		{
			var sWidth = window.innerWidth;
			var sHeight = window.innerHeight;

			var oWidth = $('.popupLayer').width();
			var oHeight = $('.popupLayer').height();

			// 레이어가 나타날 위치를 셋팅한다.
			var divLeft = e.clientX + 10;
			var divTop = e.clientY + 5;

			// 레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
			if( divLeft + oWidth > sWidth ) divLeft -= oWidth;
			if( divTop + oHeight > sHeight ) divTop -= oHeight;

			// 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치하자.
			if( divLeft < 0 ) divLeft = 0;
			if( divTop < 0 ) divTop = 0;

			$('.popupLayer').css({
				"top": divTop,
				"left": divLeft,
				"position": "absolute"
			}).show();
		});

	});
    </script>
        <section>
            <br/>
            <label id='pageName'>신고내역</label>
            <br/>
            <br/>
            <br/>
            <!-- 신고내역리스트 -->
            <table class='type'>
                <thead>
                    <tr>
                        <th id='ck' style="width:3%;"><input type="checkbox" id='rboardCheckAll' class='checkAll'/></th>
                        <th id='reportId' style="width:15%;">Id</th>
                        <th style="width:10%;">Type</th>
                        <th style="width:10%;">link</th>
                        <th style="width:35%;">Content</th>
                        <th style="width:15%;">Date</th>
                        <th style="width:20%;">처리</th>
                    </tr>
                </thead>   
                <tbody>
                <% if(list==null || list.isEmpty()) { %>
                	<tr>
                		<td colspan='8'>
                			신고된 내용이 없습니다
                		</td>
                	</tr>
                <%}
                	else {
                		for(ReportBoard r : list){ 
                       		int type=r.getReportBoardType();
                       		String typeStr="";
                       		String linkStr="";
                       		switch(type)
                       		{
                       		case 0: typeStr="user"; linkStr="window.open('"+request.getContextPath()+"/view/profile?uu="+r.getReportBoardLink()+"');";break;
                       		case 1 : typeStr="newspeed";linkStr="alert('준비중입니다.')";break;
                       		case 2 : typeStr="comment";linkStr="alert('준비중입니다.')";break;
                       		case 3 : typeStr="chat";linkStr="alert('준비중입니다.')";break;	
                        	}
                    %>
                    <tr>
                        <td>
                        	<input type="checkbox" class='checks' onclick='checkTr(this)'/>
                        	<input type='hidden' class='reportedTargetNo' value='<%=r.getReportBoardTargetNo()%>'/>
                        	<input type='hidden' class='reportBoardNo' value='<%=r.getReportBoardNo() %>'/>
                        	<input type='hidden' class='reportedBoardType' value='<%=r.getReportBoardLink() %>'/>
                        </td>
                        <td class='reportedTargetName'><%=r.getReportBoardTargetId() %></td>
                        <td><%=typeStr %></td>
                        <td class='reportedLink' onclick="<%=linkStr %>" ><a href=''>go</a></td>
                        <td>
                        	<a onclick='openNextTr(this);'>
                        		<% if(!mList.isEmpty()&&mList.containsKey(r.getReportBoardNo())) {
                        		
                        			List<ReportBoardMedia> mediaList=mList.get(r.getReportBoardNo());
                					for(ReportBoardMedia m : mediaList) {%>
                					<img src='<%=request.getContextPath() %>/<%=m.getReportBoardMediaPathRe() %>' style="width:10%; display:inline-block;"/>
                        	<%}
                					if(mediaList.size()>3)
                					{%>
                						<br/>
                					<%} 
                				}
                        		if(r.getReportBoardContent()!=null){ %>
	                        	<%if(r.getReportBoardContent().length()>20) { %>
	                        	<%=r.getReportBoardContent().substring(0, 17) %>...
	                        	<%} else { %>
	                        	<%=r.getReportBoardContent() %>
	                        	<%} 
	                        	} else{
	                        	%> '내용없음'
	                        	<%} %>
                        	</a>
                        </td>
                        <td><%=r.getReportBoardDate() %></td>
                        <td>
                       		<%if((Integer)r.getReportEndResult()==null||r.getReportEndResult()==0) {%>
                            <button class='imgSelect stopBtn' onclick='stopTr(this);'>정지</button>
                            <button class='deleteBtn' onclick='deleteTr(this);'>삭제</button>
                            <%}else if(r.getReportEndResult()==-1){%>
                            	&nbsp;
                       		<%}else{%>
                            	정지(<%=r.getReportEndResult() %>)
                            <%} %>
                        </td>
                    </tr>
                    <tr class='movingTr' style='display:none;'>
                    	<td colspan=7 style="background-color:rgba(234, 242, 253, 0.5);">
                    		<div style='width:100%; height:30%; text-align:center;'>
                    			<% if(!mList.isEmpty()&&mList.containsKey(r.getReportBoardNo())) {%>
                    			<div class='showMedia'>
                    				<div class='scrollWrap'>
                    				<%  
                    					List<ReportBoardMedia> mediaList=mList.get(r.getReportBoardNo());
                    					for(ReportBoardMedia m : mediaList) {%>
                    						<img src='<%=request.getContextPath() %>/<%=m.getReportBoardMediaPathRe() %>' style="width:30%; display:inline-block; cursor: pointer;"onclick='showImg(src);'/>
                    					<%} %>
                   					</div>
                    			</div>
                    			<%} %>
                    			<div class='inputContentText'>
                    				<%=r.getReportBoardContent() %>
                    			</div>
                    		</div>
                    	</td>
                    </tr>
                    <%	} 
                	} %>
                </tbody>
            </table>
            <div class='reportSearch'>
                <div style="float:left; margin-left:10px;">
                	<button class='imgSelect stopBtn' onclick='stopAllTr();'>정지</button>
                    <button class='deleteBtn' onclick='deleteAllTr();'>삭제</button>
                    <button class='cancelBtn' onclick='cancelAllTr();'>취소</button>
                </div>
                <form id='searchReport' name='searchReport' style="float:right;" action='<%=request.getContextPath() %>/admin/reportSearch' method='post'>
                    <select name='searchType'>
                        <option value='id'>아이디</option>
                        <option value='type'>내용</option>
                    </select>
                    <input type='text' name='searchKeyword'/>
                    <button class='searchBtn' type='submit'><img src='<%=request.getContextPath() %>/img/adminImg/search.png'/></button>
                </form>
            </div>
            <div class='pageChange' style="clear:both;">
		         <%=pageBar %>
		     </div>
        </section>
    </div>
    <!-- 레이어창 -->
    <div class='popupLayer'>
        <!-- 내용 -->
        <form name='stopForm' id="stopForm" method="POST" action='<%=request.getContextPath() %>/admin/report/userBan' onsubmit='return stopUser_val();'>
            <div class='layerContent'>
                <input type="hidden" id="reportedId" name='reportedId'/>
                <input type="hidden" id="reportedName" name="reportedName"/>
                <input type='hidden' id='reportBoardNo' name='reportBoardNo'/>
                <label>
                    <input type="radio" id='stoptype1' name='stoptype' value='temp'/>
                    <input type='number' id='stoptype0' name='stopdays' min='1' placeholder="정지기간 ex)일수"/>일
                </label>
                <br/>
                <label>
                    <input type='radio' id='stoptype2' name='stoptype' value='forever'/>
                   		 영구정지
                </label>
                <br/>
            </div>
            <div id='layerBtn'>
                <button type='reset' class='closeLayer'>취소</button>
                <button type='submit' class='acceptLayer'>확인</button>
            </div>
        </form>
    </div>
    <form name='reportDeleteFrm' id='reportDeleteFrm' method="POST" style='display: none;' 
    	action='<%=request.getContextPath() %>/admin/deleteReport' onsubmit='return deleteTarget_val();'>
        <input type="hidden" id="reportDeleteType" name='reportDeleteType'/>
        <input type="hidden" id="reportDeleteLink" name='reportDeleteLink'/>
    </form>
    

    <script>
        // 모바일 메뉴 열기 위한 함수
        function openAdminMobileMenu(){
            $('nav').toggle();
        }
        function checkTr(it){
        	tr=it.parentNode.parentNode;
        	tr.style.backgroundColor=(it.checked)?"rgba(234, 242, 253, 0.5)":"white";
        }
        function stopTr(it){
        	//선택 줄 색변화
        	tr=it.parentNode.parentNode;
        	tr.style.backgroundColor="rgba(234, 242, 253, 0.5)";
        	$(tr).siblings().each(function(){
        		$(this).css('background-color','white');
        	});
        	//선택 줄 정보 form에 담기
        	reportedTargetNo=$(tr).children('td:first-of-type').children('.reportedTargetNo').val();
        	$('#reportedId').val(reportedTargetNo);
        	reportedTargetId=$(tr).children('.reportedTargetName').text();
        	$('#reportedName').val(reportedTargetId);
        	reportBoardNo=$(tr).children('td:first-of-type').children('.reportBoardNo').val();
        	$('#reportBoardNo').val(reportBoardNo);
        }
        function stopUser_val(){
        	$('#stoptype0').on("keydown", function(e){
      		  /* e(지수), .(소수점) -(마이너스) 예외처리 */
      		    if(e.keyCode == 69 || e.keyCode == 190 || e.keyCode == 109){
      		        return false;              
      		    } 
      		});
        	if($('#reportedId')!=null)
       		{
       			if($('#stoptype1').is(':checked'))
  				{
  					if($('#stoptype0').val()!="")
					{
						return true;
					}
  				}
       			else if($('#stoptype2').is(':checked'))
   				{
   					return true;
   				}
       		}
        	alert('정지 내역을 입력해주세요!');
        	return false;
        }
        //이미지 새창
        function showImg(i){
            var url=i;
            var title='첨부파일';
            var shape='left=200px, top=100px, width=100%, height=100%';

            var popup=open(i,title,shape);
        }
        
        function openNextTr(it){
        	tr=it.parentNode.parentNode;
        	$(tr).next('tr').toggle();
        	
        }
        function deleteTr(it){
        	//선택 줄 색변화
        	tr=it.parentNode.parentNode;
        	tr.style.backgroundColor="rgba(234, 242, 253, 0.5)";
        	$(tr).siblings().each(function(){
        		$(this).css('background-color','white');
        	});
        	
        	//정보모으기
        	link=$(tr).children('.reportedLink').children().text();
        	$('#reportDeleteLink').val(link);
        	type=$(tr).children('.reportedBoardType').text();
        	$('#reportDeleteType').val(type);
        	
        	$('#reportDeleteFrm').submit();
        	
        }
        
        function deleteTarget_val(){
        	
        	if(type!=0&&type!=2)
       		{   
	        	result=confirm(type+'타입의 '+link+'번을 삭제하시겠습니까?');
       		}
        	else
       		{
        		if(type==0)
       			{
		       		alert(type+'타입은 삭제할 수 없습니다.');		
       			}
       			result=false;
       		}
	        	return result;
        }
        
        function stopAllTr(){
        	
        	$("#stopForm").attr("action", "<%=request.getContextPath() %>/admin/report/selectUserBan");
        	
        	$('.checks:checked').each(function(){
        		tr=$(this).parent().parent();
        		
        		reportedTargetNo=$(tr).children('td:first-of-type').children('.reportedTargetNo').val();
        		tempTargetNo=$('#reportedId').val();
            	$('#reportedId').val(tempTargetNo+reportedTargetNo+' ');
            	
            	reportedTargetId=$(tr).children('.reportedTargetName').text();
            	tempTargetId=$('#reportedName').val();
            	$('#reportedName').val(tempTargetId+reportedTargetId+' ');
            	
            	reportBoardNo=$(tr).children('td:first-of-type').children('.reportBoardNo').val();
            	tempBoardNo=$('#reportBoardNo').val();
            	$('#reportBoardNo').val(tempBoardNo+reportBoardNo+' ');
        	});
        }
        
        function deleteAllTr(){}
		function cancelAllTr(){
		        	
			$("#stopForm").attr("action", "<%=request.getContextPath() %>/admin/reportCancel");
			$("#stopForm").attr("onsubmit", "submit()");
	    	
	    	$('.checks:checked').each(function(){
	    		tr=$(this).parent().parent();
	    		
	    		reportedTargetNo=$(tr).children('td:first-of-type').children('.reportedTargetNo').val();
	    		tempTargetNo=$('#reportedId').val();
	        	$('#reportedId').val(tempTargetNo+reportedTargetNo+' ');
	        	
	        	reportedTargetId=$(tr).children('.reportedTargetName').text();
	        	tempTargetId=$('#reportedName').val();
	        	$('#reportedName').val(tempTargetId+reportedTargetId+' ');
	        	
	        	reportBoardNo=$(tr).children('td:first-of-type').children('.reportBoardNo').val();
	        	tempBoardNo=$('#reportBoardNo').val();
	        	$('#reportBoardNo').val(tempBoardNo+reportBoardNo+' ');
	        	
	        	$('#stopForm').submit();
	    	});
	    }

    </script>
</body>
</html>
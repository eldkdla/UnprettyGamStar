<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gamstar.admin.support.model.vo.SupportBoard, com.gamstar.admin.support.model.vo.SupportBoardMedia, java.util.*" %>
<%
	/* int cPage=(int)request.getAttribute("cPage"); */
	int cPage=Integer.parseInt(request.getParameter("cPage"));
	SupportBoard s=(SupportBoard)request.getAttribute("supportBoard");
	List<SupportBoardMedia> mlist=(List<SupportBoardMedia>)request.getAttribute("supportBoardMedia");
	SupportBoard answer=(SupportBoard)request.getAttribute("supportBoardAnswer");
%>
<style>
/* Support Main Table */
table.type{
            text-align:center; 
            width:100%; 
            padding:10px; 
            margin-top:10px;
            border-collapse: collapse;
        }
        table.type th{
            border-top:1px solid rgba(189,189,189,0.8);
            border-bottom: 1px solid rgba(189,189,189,0.8);
            line-height: 2;
            padding:4px;
            font-family: 'Nanum Gothic', sans-serif;
            color:rgba(0, 0, 0, 0.7);
        }
        table.type td{
            line-height:1.8;
            border-bottom: 1px solid rgba(189,189,189,0.4);
            font-family: 'Nanum Gothic', sans-serif;
            color:rgba(0, 0, 0, 0.7);
        }

/* 답변 작성 위한 innertable(form) */
form#supportAnswer{
    display: inline-block;
    width:100%;
}
table#supportInnerTable{
    width:100%;
    border-collapse: collapse;
}
table#supportInnerTable tr{
    border-top:1px solid rgba(189,189,189,0);
    border-bottom: 1px solid rgba(189,189,189,0);
}
table#supportInnerTable td{
    border-top:1px solid rgba(189,189,189,0);
    border-bottom: 1px solid rgba(189,189,189,0);
}
table#supportInnerTable small{
    text-align: left; 
    vertical-align: top;
}
div#supportMedia{
    width:87%; 
    border:1px solid rgba(189,189,189,0.4);
    display: inline-block;
}

/* 등록 취소 등 버튼 */
div.answeringBtn{
    text-align: left;
}
div.answeringBtn>div>a{
    border:none;
    background-color: white;
    font-family: 'Nanum Gothic', sans-serif;
    color:rgba(0, 0, 0, 0.7);
    text-decoration: none;
    cursor: pointer;
}
div.answeringBtn>div>a img{
    width:10px; 
    opacity: 0.5;
}
textarea#supportAnswer{
    margin: 5px;
    width:98%; 
    resize: none;
}
	/* 첨부파일용 */
        #supportMedia{
            border:1px solid rgba(189,189,189,0.4);
            text-decoration: none;
            font-family: 'Nanum Gothic', sans-serif;
            color:rgba(0, 0, 0, 0.7);
        }
        #supportMedia a{
            text-decoration: none;
            font-family: 'Nanum Gothic', sans-serif;
            color:rgba(0, 0, 0, 0.7);
            cursor: pointer;
        }

        /* 첨부파일 미리보기 */
        div.showMedia{
            border:1px solid rgba(189,189,189,0.4);
            width : 100%;
            height : 350px;
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
            width : 40%;
            cursor: pointer;
            vertical-align: middle;
        }
</style>
<%@include file='/view/admin/common/header.jsp' %>
<section>
        <br/>
        <label id='pageName'>문의사항</label>
        <!-- 문의사항 리스트 -->
        <br/>
        <br/>
        <table class='type'>
               <tr>
                   <th colspan="3"><%=s.getSupportBoardTitle() %></th>
               </tr>
               <tr>
                   <td colspan="3" style="border:none; text-align: left;">
                    <small>Date : </small>
                    <%=s.getSupportBoardDate() %>
                    </td>
               </tr>
               <tr>
                    <td style="text-align: left;" colspan='2'>
                        <small>Name : </small>
                        <%=s.getSupportBoardWriterName() %>
                    </td>
                    <td style="text-align: right;">
                        <small>답변 : </small>
                        <%if(s.getSupportBoardRootNo()==0) { %>
                        	답변중
                        <%} else { %>
                        	답변완료
                        <%} %>
                    </td>
               </tr>
               <tr>
                   <td colspan="3" style="text-align: left; padding: 5px;">
               	<% if(mlist!=null) {%>
                        <div class='showMedia'>
                            <div class='scrollWrapper'>
                            	<% for(SupportBoardMedia m : mlist) { 
                            		if(m.getSupportBoardMediaType()==0){%>
                                	<img class='supImg' src='<%=request.getContextPath() %>/<%=m.getSupportBoardMediaPathRe() %>' onclick='showImg(src);'/>
                                <%}else if(m.getSupportBoardMediaType()==1) {%>
		                                <video width="400" controls height=100%;>
										  <source src="<%=request.getContextPath() %>/<%=m.getSupportBoardMediaPathRe() %>" type="video/mp4">
										  <source src="<%=request.getContextPath() %>/<%=m.getSupportBoardMediaPathRe() %>" type="video/ogg">
										  <source src="<%=request.getContextPath() %>/<%=m.getSupportBoardMediaPathRe() %>" type="video/webm" />
										</video>
                            		<%}
                            		} %>
                            </div>
                        </div>
				<%} %>
                       <p id='supportQuestion'><%=s.getSupportBoardContent() %></p>
                       <br/>
                       <br/>
                       <br/>
               <% if (mlist!=null) { %>
                        <div id='supportMedia'>
                            <label>
                                <small>첨부파일</small>
                            </label>
                            &nbsp;
                            &nbsp;
                            <% for(SupportBoardMedia m : mlist) { %>
                            <br/>
                            <a onclick='showImg("<%=request.getContextPath() %>/<%=m.getSupportBoardMediaPathRe() %>");'>
                            	<%=m.getSupportBoardMediaPathOri().substring(m.getSupportBoardMediaPathOri().lastIndexOf("/")+1) %>
                           	</a>
                           	<%} %>
                        </div>
                <%} %>
                       <br/>
                       <br/>
                   </td>
               </tr>

        <%--    <tr>
               <td colspan="2" style='text-align: left;'>
                    <small>Date : </small>
                    <%=new Date() %>
                </td>
           </tr> --%>
           <tr>
               <td colspan='3'>
                    <form name='supportAnswer' id='supportAnswer' action='<%=request.getContextPath() %>/admin/supportAnswerEnd' 
                    		method='post' enctype="multipart/form-data">
                        <table id='supportInnerTable'>
                            <tr>
                                <th>
                                	<input type='hidden' name='cPage' value='<%=cPage %>'/>
                                    <label>
                                        <small>내용</small>
                                    </label>
                                    <input type='hidden' name='oriSupportBoardNo' id='oriSupportBoardNo' value='<%=s.getSupportBoardNo() %>'/>
                                    <%if(answer!=null) {%><input type='hidden' name='answerSupportBoardNo' value='<%=answer.getSupportBoardNo() %>'/><%} %>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <textarea name='supportContent' id='supportContent' rows="20" cols="60" style="width:90%; resize: none;"><% if(answer!=null) {%><%=answer.getSupportBoardContent()%><%} %></textarea>
                                </th>
                            </tr>
                           <!--  <tr>
                                <th style='border:none;'>
                                    <label>
                                        <small>첨부</small>
                                    </label>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <div id='supportMedia'>
                                        <label>
                                            <input type='file' name='supportMedia' id='supportMedia' multiple accept="image/*,video/*" multiple="multiple" style='cursor: pointer;'/>
                                        </label>
                                    </div>
                                    &nbsp;&nbsp;
                                    &nbsp;
                                </th>
                            </tr> -->
                        </table>
                        	<input type="hidden" name="cPage" value='<%=cPage %>'/>
                    </form>
                </td>
            </tr>
        </table>
        <div class='answeringBtn' style='width:100%;'>
            <div style="float: left;">
            <a href="<%=request.getContextPath()%>/admin/supportList">
                <img src='<%=request.getContextPath() %>/img/adminImg/menu.png'/>
                <small>목록</small>
            </a>
            </div>
            
            <div style='float: right;'>
            <a onclick='sendSupportAnswwer();'>
                <img src='<%=request.getContextPath() %>/img/adminImg/write.png' class='linkEdit' onclick='clickedEdit();'/>
                <small>등록</small>
            </a>
            &nbsp;
            <a href='<%=request.getContextPath()%>/admin/supportView?no=<%=s.getSupportBoardNo() %>&cPage=<%=cPage%>' class='linkEdit' onclick='clickedEdit();'>
                <small>취소</small>
            </a>
            </div>
        </div>
        <br/>
        <br/>
        <br/>
        <div id='show'>

        </div>
    </section>
    
</div>
<script>
    function sendSupportAnswwer(){
       /*  //textArea 안에 있는거 그대로(띄어쓰기, 엔터) html화

        var textBox = $('#supportContent');
        var lines = textBox.val().split("\n");

        var resultString  = "<p>";
        for (var i = 0; i < lines.length; i++) 
        {
            resultString += lines[i] + "<br/>";
        }
        resultString += "</p>";

        console.log(resultString); */

       $('#supportAnswer').submit();
    }
    function openAdminMobileMenu(){
        $('nav').toggle();
    }
    function showImg(i){
        var url=i;
        var title='첨부파일';
        var shape='left=200px, top=100px, width=100%, height=100%';

        var popup=open(i,title,shape);
    }
    function clickedEdit() {
        if (!clicked) {
        
             clicked=true;
             $('.linkEdit').unbind('click');
        } else {
        	 $('.linkEdit').delay( 2000 );
        }
     }
</script>
</body>
</html>
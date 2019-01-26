<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gamstar.admin.support.model.vo.SupportBoard, com.gamstar.admin.support.model.vo.SupportBoardMedia, java.util.*" %>
<%
	int cPage=(int)request.getAttribute("cPage");
	int nextNo=(Integer)request.getAttribute("nextNo");
	int prevNo=(Integer)request.getAttribute("prevNo");
	SupportBoard s=(SupportBoard)request.getAttribute("supportBoard");
	SupportBoard answer=(SupportBoard)request.getAttribute("supportBoardAnswer");
	List<SupportBoardMedia> mlist=(List)request.getAttribute("supportBoardMedia");
	
	
	String backToList = request.getContextPath()+"/admin/supportList?cPage="+cPage;
	
	if(request.getParameter("searchType")!=null)
	{
		String searchType=request.getParameter("searchType");
		String searchKeyword=request.getParameter("searchKeyword");
		backToList = request.getContextPath()+"/admin/supportFind?cPage="+cPage+"&searchType="+searchType+"&searchKeyword="+searchKeyword;
	}
	

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
        table.type tr:nth-of-type(5){
            text-align: left; 
            background-color: rgba(234, 242, 253,0.4);
        }
        table.type tr:nth-of-type(6){
            text-align: left; 
            padding: 5px; 
            background-color: rgba(234, 242, 253,0.4);
        }

        /* 답변 수정 등 버튼 */
        div.answeringBtn>div>a{
            border:none;
            background-color: white;
            font-family: 'Nanum Gothic', sans-serif;
            color:rgba(0, 0, 0, 0.7);
            text-decoration: none;
            cursor: pointer;
        }
        div.answeringBtn>div>a>img{
            width:10px; 
            opacity: 0.5;
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
        <!-- 콘텐츠 -->
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
                        <%=s.getSupportBoardWriterName() %>(<%=s.getSupportBoardWriterId() %>)
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
                       <xmp id='supportQuestion'><%=s.getSupportBoardContent() %></xmp>
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
			<% if(answer!=null) { %>
               <tr>
                   <td colspan="3">
                       <img src='<%=request.getContextPath() %>/img/adminImg/answer.png' style='width:18px; opacity: 0.5;';/>
                       <small>Date : </small>
                       <%=answer.getSupportBoardDate() %>
                    </td>
               </tr>
               <tr>
                   <td colspan="3"> 
                        <xmp id='supportAnswer'><%=answer.getSupportBoardContent() %></xmp>
                   </td>
               </tr>
               <tr>
                   <th colspan="3"></th>
               </tr>
               <%} %>
            </table>
            <div class='answeringBtn'>
                <div style='float:left;'>
                    <a href='<%=request.getContextPath()%>/admin/supportView?no=<%=nextNo%>&cPage=<%=cPage%>'>
		                <img src='<%=request.getContextPath() %>/img/adminImg/back.png'/>
		                <small>다음</small>
		            </a>
		            &nbsp;
		            <a href='<%=request.getContextPath()%>/admin/supportView?no=<%=prevNo%>&cPage=<%=cPage%>'>
		                <small>이전</small>
		                <img src='<%=request.getContextPath() %>/img/adminImg/next.png'/>
		            </a>
                    &nbsp;
                    <a href="<%=backToList%>">
                        <img src='<%=request.getContextPath() %>/img/adminImg/menu.png'/>
                        <small>목록</small>
                    </a>
                </div>
                <div style="float:right;">
                	<a href="<%=request.getContextPath()%>/admin/supportDelete?no=<%=s.getSupportBoardNo() %>&cPage=<%=cPage%>" class='doConfirm'>
                        <img src='<%=request.getContextPath() %>/img/adminImg/del.png'/>
                        <small>문의삭제</small>
                    </a>
				<% if(answer!=null) { %>
                    <a href='<%=request.getContextPath()%>/admin/supportAnswer?no=<%=s.getSupportBoardNo()%>&cPage=<%=cPage%>'>
                        <img src='<%=request.getContextPath() %>/img/adminImg/write.png'/>
                        <small>수정</small>
                    </a>
                    <a href="<%=request.getContextPath()%>/admin/supportDelete?no=<%=answer.getSupportBoardNo() %>&cPage=<%=cPage%>" class='doConfirm'>
                        <img src='<%=request.getContextPath() %>/img/adminImg/del.png'/>
                        <small>답변삭제</small>
                    </a>
                 <%} else{%>
                	 <a href='<%=request.getContextPath()%>/admin/supportAnswer?no=<%=s.getSupportBoardNo()%>&cPage=<%=cPage%>'>
                        <img src='<%=request.getContextPath() %>/img/adminImg/write.png'/>
                        <small>답변하기</small>
                    </a>
                 <%} %>
                 
                </div>
                
            </div>
            <br/>
            <br/>
            <br/>
        </section>
    </div>
    <script>
        function showImg(i){
            var url=i;
            var title='첨부파일';
            var shape='left=200px, top=100px, width=100%, height=100%';

            var popup=open(i,title,shape);
        }
        function openAdminMobileMenu(){
            $('nav').toggle();
        }
        
        $('.doConfirm').on('click', function () {
			return confirm('정말로 삭제하시겠습니까?');
        });
    </script>
</body>
</html>
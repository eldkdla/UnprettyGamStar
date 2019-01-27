<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.gamstar.admin.support.model.vo.SupportBoard" %>
<%
	int cPage=(int)request.getAttribute("cPage");
	List<SupportBoard> list = (List<SupportBoard>)request.getAttribute("list");
	String searchType=request.getParameter("type");
	String searchKeyword=request.getParameter("keyword");
	int numPerPage=(int)request.getAttribute("numPerPage");
	String pageBar=(String)request.getAttribute("pageBar");
%>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Hi+Melody" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/adminSupport.css"/>

<%@include file='/view/admin/common/header.jsp' %>
        <section>
            <br/>
            <label id='pageName'>문의사항</label>
            <br/>
            <!-- 문의사항 리스트 -->
            <br/>
            <div id='isAnswered'>
                    <label> 
                        <small>답변상황</small>
                        <form style='display:inline;' name='showSptFrm' action="<%=request.getContextPath() %>/admin/support/show">
	                        <select name='show' id='showSupport' onchange="submit();">
	                            <option value='all'>모두보기</option>
	                            <option value='ing'>처리중</option>
	                            <option value='end'>답변완료</option>
	                        </select>
                        </form>
                    </label>
            </div>
            <table class='type'>
                <tr>
                    <th style="width:7%;">No</th>
                    <th style="width:52%;">Title</th>
                    <th style="width:14%;">Name</th>
                    <th style="width:13%;">Date</th>
                    <th style="width:13%;">답변여부</th>
                </tr>
                <%if(list==null||list.isEmpty()) {%>
                <tr>
                	<td colspan='5'>
                		'<%=searchKeyword %>'로 검색되는 결과가 없습니다.
                	</td>
                </tr>
                <%} else{ %>
                
                <% for(SupportBoard s:list) {%>
                <tr>
                    <td><%=s.getSupportBoardNo() %></td>
                    <td class="sCon">
                    	<a class='linkView' href="<%=request.getContextPath()%>/admin/supportView?no=<%=s.getSupportBoardNo() %>&cPage=<%=cPage%>&type=<%=searchType%>&keyword=<%=searchKeyword%>"
                    	onclick='clickedView();'>
                    		<%if(s.getSupportBoardTitle().length()>20) {%>
                    			<%=s.getSupportBoardTitle().substring(0, 19) %>...
                    		<%}else{ %>
                    			<%=s.getSupportBoardTitle() %>
                    		<%} %>
                    	</a>
                    </td>
                    <td><%=s.getSupportBoardWriterName() %>(<%=s.getSupportBoardWriterId() %>)</td>
                    <td><%=s.getSupportBoardDate() %></td>
                    <td><% if(s.getSupportBoardRootNo()==0) { %>
                    	답변중
                    	<%}
                    		else {%>
                    	답변완료
                    	<%} %>
                    </td>
                </tr>
                <%} 
                }%>
                
            </table>
            <div class='supportSearch' style='margin-top:5px;'>
	            <form name='searchSupport' action="<%=request.getContextPath() %>/admin/support/search">
	                    <select name='type'>
	                        <option value='id' >글쓴이</option>
	                        <option value='title' >글제목</option>
	                        <option value='content' >글내용</option>
	                        <option value='sAll' >전체</option>
	                    </select>
	                    <input type='text' name='keyword' value='<%=searchKeyword!=null?searchKeyword:"" %>'/>
                    <button class='searchBtn' type='submit'><img src='<%=request.getContextPath() %>/img/adminImg/search.png'/></button>
                </form>
            </div>
            <br/>
            <div class='pageChange'>
                <%=pageBar %>
            </div>
        </section>
    </div>
    <script>
        // 모바일 메뉴 열기 위한 함수
        function openAdminMobileMenu(){
            $('nav').toggle();
        }
        
        //답변상황으로 보기
        $('showSupport').change(function(){
        	$('#showSupport').val()
        });
        
        function clickedView() {
            if (!clicked) {
            
                 clicked=true;
                 $('.linkView').unbind('click');
            } else {
            	 $('.linkView').delay( 2000 );
            }
         }
    </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.gamstar.admin.support.model.vo.SupportBoard" %>
<%
	List<SupportBoard> list = (List)request.getAttribute("list");
	int numPerPage=(int)request.getAttribute("numPerPage");
	String pageBar=(String)request.getAttribute("pageBar");
	String selectType=(String)request.getAttribute("selectType");
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
                        <form style='display:inline;' name='showSptFrm' action="<%=request.getContextPath() %>/admin/support/show.do" >
	                        <select name='showSupport' id='showSupport' onchange="submit();">
	                            <option value='all' <%=selectType.equals("all")?"selected":"" %>)>모두보기</option>
	                            <option value='ing' <%=selectType.equals("ing")?"selected":"" %>>처리중</option>
	                            <option value='end' <%=selectType.equals("end")?"selected":"" %>>답변완료</option>
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
                		문의 내역이 없습니다
                	</td>
                </tr>
                <%} %>
                <% for(SupportBoard s:list) {%>
                <tr>
                    <td><%=s.getSupportBoardNo() %></td>
                    <td class="sCon">
                    	<a href="<%=request.getContextPath()%>/admin/supportView?no=<%=s.getSupportBoardNo() %>">
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
                <%} %>
                
            </table>
            <div class='supportSearch' style='margin-top:5px;'>
                <form name='searchSupport' action="<%=request.getContextPath() %>/admin/supportFind">
                    <select name='searchType'>
                        <option value='searchSupportId'>아이디</option>
                        <option value='searchSupportTitle'>글제목</option>
                        <option value='searchSupportContent'>글내용</option>
                        <option value='searchSupportAll'>전체</option>
                    </select>
                    <input type='text' name='searchKeyword'/>
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
      
    </script>
</body>
</html>
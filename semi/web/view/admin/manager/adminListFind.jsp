<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.gamstar.user.model.vo.User" %>
<%
	List<User> list=(List)request.getAttribute("list");
	String searchType=request.getParameter("searchType");
	String searchKeyword=request.getParameter("searchKeyword");
	int numPerPage=(int)request.getAttribute("numPerPage");
	String pageBar=(String)request.getAttribute("pageBar");
%>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Hi+Melody" rel="stylesheet">
<style>
    @media all and (min-width:1067px){
    /* Member Main Table */
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
    
    div.userSearch{
        text-align: right;
    }
    div.userSearch>form{
        display:inline;
    }
    div.userSearch>form>select{
        vertical-align: middle;
    }
    div.userSearch>form>input[type=text]{
        vertical-align: middle;
    }
    
    /* 정지 버튼 */
    .stopBtn{
        background-color:rgb(255, 79, 79);
        color:white;
        border:none;
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
        margin-right: 3px;
    }
    .stopBtn:focus{
        outline: none;
    }
    /* 정지 취소 버튼 */
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
    
     /* 회원탈퇴 버튼 */
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
    /* Member Main Table */
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
    
    div.userSearch{
        text-align: right;
    }
    div.userSearch>form{
        display:inline;
    }
    div.userSearch>form>select{
        vertical-align: middle;
    }
    div.userSearch>form>input[type=text]{
        vertical-align: middle;
    }
    
    /* 정지 버튼 */
    .stopBtn{
        background-color:rgb(255, 79, 79);
        color:white;
        border:none;
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
        margin-right: 3px;
    }
    .stopBtn:focus{
        outline: none;
    }
    /* 정지 취소 버튼 */
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
    
     /* 회원탈퇴 버튼 */
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


@media all and (min-width:150px) and (max-width:600px){
    /* Member Main Table */
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
    
    div.userSearch{
        text-align: right;
    }
    div.userSearch>form{
        display:inline;
    }
    div.userSearch>form>select{
        vertical-align: middle;
    }
    div.userSearch>form>input[type=text]{
        vertical-align: middle;
    }
    
    /* 정지 버튼 */
    .stopBtn{
        background-color:rgb(255, 79, 79);
        color:white;
        border:none;
        cursor: pointer;
        border-top-right-radius:5px;
        border-bottom-right-radius:5px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
        margin-right: 3px;
    }
    .stopBtn:focus{
        outline: none;
    }
    /* 정지 취소 버튼 */
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
    
     /* 회원탈퇴 버튼 */
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
    </style>
<%@include file='/view/admin/common/header.jsp' %>
<script>
// 전체 체크용 기능
    $(document).ready(function(){
        $('.checkAll').click(function(){
            $('.checks').trigger("click");
        });
    });
</script>
    <!-- 콘텐츠 -->
 <section>
     <br/>
     <label id='pageName'>관리자설정</label>
     <br/>
     <br/>
     <br/>
    <%--  <div id="numPerPage-container" style="text-align: right;">
		<small>정렬</small> 
		<form name="pageTypeFrm" id="pageTypeFrm" 
		action="<%=request.getContextPath()%>/admin/memberListOrderType" style="display: inline-block;">
			<select name="orderType" id="orderType" onchange='submit();'>
				<option value="name-desc">이름↓</option>
				<option value="name-asc" >이름↑</option>
				<option value="id-desc">아이디↓</option>
				<option value="id-asc" >아이디↑</option>
			</select>
		<small>페이지당 회원수 :</small> 
			<select name="numPerPage" id="numPerPage" onchange='submit();'>
				<option value="10" <%=numPerPage==10?"selected":"" %> >10</option>
				<option value="5" <%=numPerPage==5?"selected":"" %> >5</option>
				<option value="3" <%=numPerPage==3?"selected":"" %> >3</option>					
			</select>
		</form>			
	</div>		 --%>
     <!-- 문의사항 리스트 -->
     <table class='type'>
         <tr>
         	 <th style="width:10%;"></th>
             <th style="width:7%;">Id</th>
             <th style="width:14%;">Name</th>
             <th style="width:14%;">Email</th>
             <th style="width:13%;">EnrollDate</th>
             <th style="width:10%;"></th>
         </tr>
         <% if(list==null|| list.isEmpty()) { %>
         <tr>
         	<td colspan='8' align='center'>
         		"<%=searchKeyword %>"로 검색되는 관리자가 없습니다.
        		</td>
        	</tr>
        	<%}
         for(User m:list) { %>
         <tr>
         	 <td>
         	 	<input type='hidden' class='hiddenAdminNo' value='<%=m.getNo() %>'/>
         	 </td>
             <td><%=m.getId() %></td>
             <td><%=m.getName() %></td>
             <td><%=m.getEmail() %></td>
             <td><%=m.getEnrollDate() %></td>
             <td>            	
             	<button class='deleteBtn' onclick='delAdmin(this);'>삭제</button>
             </td>
         </tr>
         <%} %>
     </table>
     <div style='margin-top:2px;'>
     	<div id='adminState-container' style='float:left; margin-top: 3px; '>
	     	<button class='stopBtn' onclick='createAdmin();'>추가</button>
	     	<form id='adminStateFrm' name='adminStateFrm' style="display:none;" 
	     		action='<%=request.getContextPath() %>/admin/manager/deleteAdmin' onsubmit='return adminDel_val();'>
	     		<input type='hidden' name='stateFrmAdminNo' id='stateFrmAdminNo' value=''/>
	     	</form>
	     	<form id='adminCreateFrm' name='adminCreate' style='display:none;'
	     	    action='<%=request.getContextPath() %>/admin/manager/createAdmin' onsubmit='return createAdmin_val();'>
	     	    <input type='hidden' name='userId' id='userId' value=''/>
	     	    <input type='hidden' name='userName' id='userName' value=''/>
	     	    <input type='hidden' name='userPw' id='userPw' value=''/>
	     	    <input type='hidden' name='userEmail' id='userEmail' value=''/>
	     	    <input type='hidden' name='userPhone' id='userPhone' value=''/>
     	    </form>
     	</div>
     	<div class='userSearch' style='float:right;' >
         	<form id='searchUser' name='searchUser' action="<%=request.getContextPath() %>/admin/manager/searchAdmin">    
	             <select name='searchType'>
	                 <option value='searchId'>아이디</option>
	                 <option value='searchName'>이름</option>
	             </select>
	             <input type='text' name='searchKeyword'/>
	             <button class='searchBtn' type='submit'><img src='<%=request.getContextPath() %>/img/adminImg/search.png'/></button>
	         </form>
     	</div>
     </div>
     <br/>
     <div class='pageChange' style='clear:both;'>
         <%=pageBar %>
     </div>
 </section>
</div>
<script>
    // 모바일 메뉴 열기 위한 함수
    function openAdminMobileMenu(){
        $('nav').toggle();
    }
    
    function delAdmin(el){
    	tr=el.parentNode.parentNode;
    	tr.style.backgroundColor="rgb(234, 242, 253)";
    	
    	adminNo=$(tr).children('td:first-of-type').children(".hiddenAdminNo").val();
    	console.log(adminNo);
    	$('#stateFrmAdminNo').val(adminNo);
    	$('#adminStateFrm').submit();
    }
    function adminDel_val(){
    	result=confirm("관리자를 삭제하시겠습니까?");
    	return result;
    }
    
    function createAdmin(){
    	$('#userId').val(prompt('아이디?'));
    	$('#userPw').val(prompt('비밀번호?'));
    	$('#userName').val(prompt('이름?'));
    	$('#userEmail').val(prompt('이메일?'));
    	$('#userPhone').val(prompt('전화번호?'));
    	$('#adminCreateFrm').submit();
    }
    function createAdmin_val(){
    	if($('#userId').val()!=null)
   		{
    		if($('#userPw').val()!=null)
   			{
   			if($('#userName').val()!=null)
  				{
  				if($('#userEmail').val()!=null)
 					{
					if($('#userPhone').val()!=null)
						{
							return true;
						}	
 					}
  				}
   			}
   		}
    	return false;
    }
    //한 페이지당 갯수
</script>
</body>
</html>
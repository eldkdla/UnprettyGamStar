<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.gamstar.user.model.vo.User" %>
<%
	List<User> list=(List)request.getAttribute("list");
	int numPerPage=(int)request.getAttribute("numPerPage");
	String pageBar=(String)request.getAttribute("pageBar");
	String orderType=(String)request.getAttribute("orderType");
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
        background-color:white;
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
        background-color:white;
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
            $('.checks').prop('checked',this.checked);
        });
    });
</script>
    <!-- 콘텐츠 -->
 <section>
     <br/>
     <label id='pageName'>회원관리</label>
     <br/>
     <br/>
     
     <div id="numPerPage-container" style="text-align: right;">
		
		<small>정렬</small> 
		<form name="orderTypeFrm" id="orderTypeFrm" 
		action="<%=request.getContextPath()%>/admin/userOrderedList" style="display: inline-block;">
			<select name="orderType" id="orderType" onchange='submit();'>
				<option value="name-desc"  <%=orderType.equals("name-desc")?"selected":"" %>>이름↓</option>
				<option value="name-asc"  <%=orderType.equals("name-asc")?"selected":"" %>>이름↑</option>
				<option value="id-desc" <%=orderType.equals("id-desc")?"selected":"" %>>아이디↓</option>
				<option value="id-asc"  <%=orderType.equals("id-asc")?"selected":"" %>>아이디↑</option>
				<option value="enroll-desc" <%=orderType.equals("enroll-desc")?"selected":"" %>>가입일↓</option>
				<option value="enroll-asc"  <%=orderType.equals("enroll-asc")?"selected":"" %>>가입일↑</option>	
			</select>
		<small>페이지당 회원수 :</small> 
			<select name="numPerPage" id="numPerPage" onchange='submit();'>
				<option value="10" <%=numPerPage==10?"selected":"" %> >10</option>
				<option value="5" <%=numPerPage==5?"selected":"" %> >5</option>
				<option value="3" <%=numPerPage==3?"selected":"" %> >3</option>					
			</select>
		</form>			
	</div>		
     <!-- 문의사항 리스트 -->
     <table class='type'>
         <tr>
             <th style='width:5%;'>
                 <input type='checkbox' class='checkAll'/>
             </th>
             <th style="width:7%;">Id</th>
             <th style="width:14%;">Name</th>
             <th style="width:7%;">Gender</th>
             <th style="width:14%;">Email</th>
             <th style="width:10%;">Phone</th>
             <th style="width:13%;">EnrollDate</th>
             <th style="width:7%;">State</th>
         </tr>
         <% if(list==null|| list.isEmpty()) { %>
         <tr>
         	<td colspan='8' align='center'>
         		등록된 회원이 없습니다.
        		</td>
        	</tr>
        	<%}
         for(User m:list) { %>
         <tr>
             <td>
                 <input type='checkbox' class='checks' onclick='checkTr(this)'/>
                 <input type='hidden' name='hiddenUserNo' class='hiddenUserNo' value='<%=m.getNo() %>'/>
             </td>
             <td class='userId'><%=m.getId() %></td>
             <td class='userName'><%=m.getName() %></td>
             <td><%=m.getGender()%></td>
             <td><%=m.getEmail() %></td>
             <td><%=m.getPhone() %></td>
             <td><%=m.getEnrollDate() %></td>
             <td>
             	<%if(m.getState()==0){ }
             	  else if(m.getState()==1){%>
             	  정지(<%=m.getRemainingDay() %>)
             	<%} %>
             	<input type='hidden' name='rDay' class='rDay' value='<%=m.getRemainingDay() %>'/>
             </td>
         </tr>
         <%} %>
     </table>
     <div id='memberState-container' style='text-align:left; margin-top: 3px;'>
     	<button class='deleteBtn' onclick='deleteMember();'>탈퇴</button>
     	<button class='stopBtn' onclick='stopMember();'>정지</button>
     	<button class='cancelBtn' onclick='resetStopMember();'>정지취소</button>
     	<div id='memberStateFrm-container' style='display:none;'>
     		<form name='memberStateFrm' id='memberStateFrm' action='<%=request.getContextPath() %>/admin/manageUserState'
       		onsubmit='return stateMember_validate();' method='post'>
	     		<input type='hidden' id='hiddenFrmType' name='stateType' value=''/>
	     		<input type='hidden' id='hiddenFrmNo' name='userNo' value=''/>
	     		<input type='hidden' id='hiddenFrmTime' name='stopTime' value=''/>
	     		<input type='hidden' id='hiddenFrmRDay' name='remainingDay' value=''/>
     		</form>
     	</div>
     </div>
     <div class='userSearch' >
       <form id='searchUser' name='searchUser' action="<%=request.getContextPath() %>/admin/userFindList">    
           <select name='searchType'>
               <option value='searchId'>아이디</option>
               <option value='searchName'>이름</option>
               <option value='searchEmail'>이메일</option>
               <option value='searchPhone'>휴대폰</option>
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

    function deleteMember(){
    	chosenId='';
    	chosenNo='';
    	
    	thisTr=$('input.checks:checked').parent().parent();
    	if(thisTr[0]!=null)
   		{
    		thisTr.each(function(){
        		chosenId+=$(this).children('.userName').text()+'('+$(this).children('.userId').text()+') ';
        		chosenNo+=$(this).children('td:first-of-type').children('.hiddenUserNo').val()+' ';
        	});
    		chosenId.trim();
    		chosenNo.trim();
    		$('#hiddenFrmType').val('delete');
    		$('#hiddenFrmNo').val(chosenNo);
        	$('#memberStateFrm').submit();
   		}
    }
    
    function stopMember(){
    	chosenId='';
    	chosenNo='';
    	chosenTime='';
    	selectTime='';
    	
    	thisTr=$('input.checks:checked').parent().parent();
    	if(thisTr[0]!=null)
   		{
    		thisTr.each(function(){
        		chosenId+=$(this).children('.userName').text()+'('+$(this).children('.userId').text()+') ';
        		chosenNo+=$(this).children('td:first-of-type').children('.hiddenUserNo').val()+' ';
        		chosenTime+=$(this).children('td:last-of-type').children('.rDay').val()+' ';
        	});
    		chosenId.trim();
    		chosenNo.trim();
    		chosenTime.trim();
    		
    		$('#hiddenFrmType').val('stop');
    		$('#hiddenFrmNo').val(chosenNo);
    		$('#hiddenFrmRDay').val(chosenTime);
    		
    		selectTime=prompt("정지 일수를 입력해 주세요(일)");
    		if(selectTime!=null)
    		{
    			$('#hiddenFrmTime').val(selectTime);
            	$('#memberStateFrm').submit();
    		}
    		else
   			{
   				$('#memberStateFrm').reset();
   			}
    		
   		}
    }
    
    function resetStopMember(){
    	chosenId='';
    	chosenNo='';
    	chosenTime='';
    	
    	thisTr=$('input.checks:checked').parent().parent();
    	if(thisTr[0]!=null)
   		{
    		thisTr.each(function(){
        		chosenId+=$(this).children('.userName').text()+'('+$(this).children('.userId').text()+') ';
        		chosenNo+=$(this).children('td:first-of-type').children('.hiddenUserNo').val()+' ';
        		
        		rDay=$(this).children('td:last-of-type').children('.rDay').val();
        		if(rDay==0)
        			{
        				alert("정지되지 않은 회원의 정지를 풀 수 없습니다.");
        				$('#memberStateFrm').reset();
        				return;
        			}
        	});
    		chosenId.trim();
    		chosenNo.trim();
    		$('#hiddenFrmType').val('resetStop');
    		$('#hiddenFrmNo').val(chosenNo);
    		$('#hiddenFrmTime').val('0');
        	$('#memberStateFrm').submit();
   		}
    }
    
    function stateMember_validate(){
    	if($('#hiddenFrmType').val()=='delete')
   		{
   			result=confirm(chosenId+'\n위 회원을 정말로 삭제하시겠습니까?');
   		}
    	else if($('#hiddenFrmType').val()=='stop')
   		{
    		result=confirm(chosenId+'\n'+'[정지'+selectTime+'일]'+'\n위 회원의 상태를 변경하시겠습니까?');
   		}
    	else
   		{
    		result=confirm(chosenId+'\n위 회원의 정지를 해제하시겠습니까?');
   		}
    	return result;
    }
</script>
</body>
</html>
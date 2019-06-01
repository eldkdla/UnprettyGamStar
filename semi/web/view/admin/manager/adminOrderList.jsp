<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.gamstar.user.model.vo.User" %>
<%
	String orderType=(String)request.getAttribute("orderType");
	String orderTypeClass=(String)request.getAttribute("orderTypeClass");
	List<User> list=(List)request.getAttribute("list");
	int numPerPage=(int)request.getAttribute("numPerPage");
	String pageBar=(String)request.getAttribute("pageBar");
%>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Hi+Melody" rel="stylesheet">
<style>
div#adminInsert-Modal{
		display:none;
		position:fixed;
		z-index:1;
		left:0;
		top:0;
		width:100%;
		height:100%;
		overflow:none;
		text-align:center;
		background-color:rgba(0,0,0,0.2);
	}
	div#adminInsert-container{
		background-color:#ffffff;
		margin:15% auto;
		border-radius:5px;
		width:40%;
		text-align:center;
	}
	form#adminInsertFrm label{
		display : block;
		width:100%;
		text-align:center;
		padding:auto;
	}
	 /* 정렬 form 꾸미기 */
	div#orderTypeSelect-container>small>a{
		text-decoration: none;
		color: rgba(0,0,0,0.6);
	}
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
    /* 정렬 form 꾸미기 */
	div#orderTypeSelect-container>small>a{
		text-decoration: none;
		color: rgba(0,0,0,0.6);
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
    /* 정렬 form 꾸미기 */
	div#orderTypeSelect-container>small>a{
		text-decoration: none;
		color: rgba(0,0,0,0.6);
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
	/* 정렬 form 꾸미기 */
	div#orderTypeSelect-container>small>a{
		text-decoration: none;
		color: rgba(0,0,0,0.6);
	}
	.mobileDel{
		display:none;
	}
} 
    </style>
<%@include file='/view/admin/common/header.jsp' %>
    <!-- 콘텐츠 -->
 <section>
     <br/>
     <label id='pageName'>관리자설정</label>
     <br/>
     <br/>
     <br/>
     <div id='orderTypeSelect-container' style="float:left; ">
     	<small>정렬</small> 
   		<small>
   			<!-- 선택된걸 누르면 반대로 가니까, 모양은 desc, 표시는 asc -->
   			<% if(orderTypeClass.equals("enroll")) {%>
	    		<a href='<%=request.getContextPath() %>/admin/manager/orderType?type=enrollAsc&class=enroll' class='enroll' 
	    			<%=orderType.equals("enrollDesc")?"style='color: rgba(0,0,0,0.8);'":"style='display:none;'" %>>
	    		가입일↓</a>
	    		<a href='<%=request.getContextPath() %>/admin/manager/orderType?type=enrollDesc&class=enroll' class='enroll'
	    			<%=orderType.equals("enrollAsc")?"style='color: rgba(0,0,0,0.8);'":"style='display:none;'" %>>
	    		가입일↑</a>
	    	<%} else { %>
	   			<a href='<%=request.getContextPath() %>/admin/manager/orderType?type=enrollDesc&class=enroll' class='enroll'>
	    		가입일&nbsp;&nbsp;</a>
    		<%} %>
    		
    		<% if(orderTypeClass.equals("selId")) {%>
	    		<a href='<%=request.getContextPath() %>/admin/manager/orderType?type=idAsc&class=selId' class='selId'
	    			<%=orderType.equals("idDesc")? "style='color: rgba(0,0,0,0.8);'":"style='display:none;'" %>>
	    		아이디↓</a>
	    		<a href='<%=request.getContextPath() %>/admin/manager/orderType?type=idDesc&class=selId' class='selId'
	    			<%=orderType.equals("idAsc")? "style='color: rgba(0,0,0,0.8);'":"style='display:none;'" %>>
	    		아이디↑</a>
	    	<%} else { %>
	    		<a href='<%=request.getContextPath() %>/admin/manager/orderType?type=idDesc&class=selId' class='selId'>
	    		아이디&nbsp;&nbsp;</a>
	    	<%} %>
   		</small>
     </div>
     <!-- 문의사항 리스트 -->
     <table class='type' style='clear: both;'>
         <tr>
         	 <th style="width:5%;" class='mobileDel'></th>
             <th style="width:7%;">Id</th>
             <th style="width:14%;">Name</th>
             <th style="width:14%;" class='mobileDel'>Email</th>
             <th style="width:14%;">Phone</th>
             <th style="width:13%;" class='mobileDel'>EnrollDate</th>
             <th style="width:5%;" ></th>
         </tr>
         <% if(list==null|| list.isEmpty()) { %>
         <tr>
         	<td colspan='7' align='center'>
         		등록된 관리자가 없습니다.
        		</td>
        	</tr>
        	<%}
         for(User m:list) { %>
         <tr>
         	 <td class='mobileDel'>
         	 	<input type='hidden' class='hiddenAdminNo' value='<%=m.getNo() %>'/>
         	 </td>
             <td><%=m.getId() %></td>
             <td><%=m.getName() %></td>
             <td class='mobileDel'><%=m.getEmail() %></td>
             <td><%=m.getPhone() %></td>
             <td class='mobileDel'><%=m.getEnrollDate() %></td>
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
         	<form id='searchUser' name='searchUser' action="<%=request.getContextPath() %>/admin/manager/search">    
	             <select name='type'>
	                 <option value='id'>아이디</option>
	                 <option value='name'>이름</option>
	             </select>
	             <input type='text' name='keyword'/>
	             <button class='searchBtn' type='submit'><img src='<%=request.getContextPath() %>/img/adminImg/search.png'/></button>
	         </form>
     	</div>
     </div>
     <br/>
     <div class='pageChange' style='clear:both;'>
         <%=pageBar %>
     </div>
      <div id='adminInsert-Modal'>
     	<div id='adminInsert-container'>
     		<br/>
     		<h3>관리자 등록</h3>
     		<br/>
     		<form action="<%=request.getContextPath()%>/admin/manager/createAdmin" method="post">
                <!-- 아이디 -->
                <div class="userRegFrm">
                    <label for="userId">아이디</label><br>
                    <input type="text" class="regControl" id="userId" name="userId" placeholder="  아이디 입력" maxlength='20'
                        autofocus size="49" required>
                    <div class="checkMsg" id="id_check"></div>
                </div>
                <!-- 비밀번호 -->
                <div class="userRegFrm">
                    <label for="userPw">비밀번호</label>
                    <sub></sub><br>
                    <input type="password" class="regControl" id="userPw" name="userPw" placeholder="  비밀번호 " maxlength='20'
                        autofocus size="49" required>
                    <div class="checkMsg" id="pw_check"></div>
                </div>
                <!-- 비밀번호 재확인 -->
                <div class="userRegFrm">
                    <label for="userPw2">비밀번호 확인</label><br>
                    <input type="password" class="regControl" id="userPw2" name="userPw2" placeholder="  비밀번호 확인"
                        maxlength='20' autofocus size="49" required>
                    <div class="checkMsg" id="pw2_check"></div>
                </div>
                <!-- 유저 이름 -->
                <div class="userRegFrm">
                    <label for="userName">이름</label><br>
                    <input type="text" class="regControl" id="userName" name="userName" placeholder="  이름 입력 (한글 2-6자)"
                        maxlength='20' autofocus size="49" required>
                    <div class="checkMsg" id="name_check"></div>
                </div>
                <!-- 본인확인 이메일 -->
                <div class="userRegFrm">
                    <label for="userEmail">이메일</label><br>
                    <input type="text" class="regControl" name="userEmail" id="userEmail" placeholder="  E-mail"
                        autofocus size="49" required>
                    <div class="checkMsg" id="email_check"></div>
                </div>
                <!-- 휴대전화 -->
                <div class="userRegFrm">
                    <label for="userPhone">휴대전화 ('-' 없이 번호만 입력해주세요.)</label><br>
                    <input type="text" class="regControl" id="userPhone" name="userPhone" placeholder="  휴대전화 번호"
                        maxlength='11' autofocus size="49" required>
                    <div class="checkMsg" id="phone_check"></div>
                </div>
				<br/>
                <div class="reg_button">
                    <input class='cancelBtn' type="reset" onclick="pageBack();"value="취소" />&emsp;&emsp;
                    <input class='stopBtn' type="submit" id="reg_submit" value="가입" />
                    <!--  -->
                </div>
            </form>
            <br/>
     	</div>
     </div>
 </section>
</div>
<script>
    
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
    	$('#adminInsert-Modal').show();
    }
</script>
<script>

	//검증파트
	var inval_Arr = new Array(6).fill(false);

	//정규식 파트
    var regExp_emp = /\s/g;                 //빈칸
    var regExp_id = /^[a-z0-9]{1,20}$/;  //a-z,0-9로 시작하는 1~20자리 아이디
    var regExp_pw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9])(?=.*[A-Z]).{8,20}$/ //대소문자 +숫자 8~20 패스 
    var regExp_email = /^[\w\-]+@(?:(?:[\w\-]{2,}\.)+[a-zA-Z]{2,})$/;
    var regExp_phone = /^(01[016789]{1})([0-9]{3,4})([0-9]{4})$/;
    var regExp_name = /^[가-힣]{2,6}$/;//한글 2~6자

    //아이디 정규식
    $('#userId').on('change keyup paste', (function () {
        if (regExp_id.test($(this).val())) {
            console.log('id참참');
            $("#id_check").text('');
            inval_Arr[0] = true;
        }
        else {
            $('#id_check').text('아이디를 확인해주세요');
            $('#id_check').css('color', 'red');
            inval_Arr[0] = false;
        }
    }));

    //비밀번호 정규식
    $('#userPw').on('change keyup paste', (function () {
        if (regExp_pw.test($(this).val())) {
        	//if (1) {
            console.log('pw참참')
            $("#pw_check").text('');
        }
        else {
            //$('#pw_check').text('비밀번호를 확인해주세요');
            $('#pw_check').css('color', 'red');
            $('#pw_check').html('비밀번호를 확인해주세요.<br><sub>- 영어 대,소문자 그리고 숫자, 특수기호를 <br>- 각각 한글자이상 포함하여 8 ~ 20 글자 </sub>');
            
        }

    }));

    //비밀번호 재확인
    $('#userPw2').on('change keyup paste', (function () {
        if ($('#userPw').val() != $(this).val())
        {
            $('#pw2_check').text('입력하신 비밀번호가 서로 다릅니다.');
            $('#pw2_check').css('color', 'red');
            inval_Arr[1] = false;
        }
        else {
            $('#pw2_check').text('');
            inval_Arr[1] = true;
        }
    }));
    
  //유저이름 정규식
    $('#userName').on('change keyup paste', (function () {
        if (regExp_name.test($(this).val())) {
            console.log('name참참');
            $("#name_check").text('');
            inval_Arr[2] = true;
        }
        else {
            $('#name_check').text('이름을 확인해주세요');
            $('#name_check').css('color', 'red');
            inval_Arr[2] = false;
        }
    }));

    //이메일 정규식
    $('#userEmail').on('change keyup paste', (function () {
        if (regExp_email.test($(this).val())) {
            console.log('email참참')
            $("#email_check").text('');
            inval_Arr[3] = true;
        }
        else {
            $('#email_check').text('입력한 이메일 주소를 확인해주세요');
            $('#email_check').css('color', 'red');
            inval_Arr[3] = false;
        }
    }));

    //핸드폰 정규식
    $('#userPhone').on('change keyup paste', (function () {
        if (regExp_phone.test($(this).val())) {
            console.log('phone참참')
            $("#phone_check").text('');
            inval_Arr[4] = true;
        }
        else {
            $('#phone_check').text('입력한 핸드폰 번호를 확인해주세요');
            $('#phone_check').css('color', 'red');
            inval_Arr[4] = false;
        }
    }));
    
    //ajax 아이디 중복여부
    $('#userId').on('change keyup paste',(function(){
        if($('#userId').val()==""){//아이디 빈칸일때
           $('#id_check').html("");
        }else{
           $.ajax({
              url:'<%=request.getContextPath()%>/useridChk',
              type:"POST",
              data:{"inputId":$('#userId').val()},
              success:function(data){
                 if(data=="true"){
                    $('#id_check').html("사용가능한 아이디입니다.").css('color', 'green');
                    inval_Arr[5] = true;
                 }
                 else if(data=="false"){
                	 $('#id_check').html("사용 불가능한 아이디입니다.").css('color', 'red');
                	 inval_Arr[5] = false;
                 }
              },
              error:function(xhr,status){
                 alert(xhr+" : "+status);
      		}
  			
   		});
	}}));
    
    //전송 체크
    $('#reg_submit').click(function(){
    	var validAll = true;
		for(var i = 0; i < inval_Arr.length; i++){
			
			if(inval_Arr[i] == false){
				validAll = false;
				console.log(i+": false래");
			}
		}
		
		if(validAll){ // 유효성 모두 통과
			//profileAlert("");
    
			return true;
			
		} else{
			profileAlert("입력한 정보들을 다시 한번 확인해주세요 :)");
    return false;
		}
	});    
	
  
	//공용 Alert 모달창
    function profileAlert(alertMsg){
       $('#myModal').remove();
       $('body').append($('<div/>',{
          id:'myModal',
          class:'modal'
       }));
       $('#myModal').append($('<div/>',{
          class:'modal-content'
       }));
       $('.modal-content').append($('<p/>',{
          text:alertMsg
       }));
       $('.modal-content').append($('<div/>',{
          onclick:'close_pop()',
          text:'확인'
       }));
       
    }
    //모달창 닫기
    function close_pop(){
       $('#myModal').css("display","none");
    }
    
    function pageBack(){
        location.href="<%=request.getContextPath()%>/admin/manager/adminList";
        history.back();
    }
	
</script>
</body>
</html>
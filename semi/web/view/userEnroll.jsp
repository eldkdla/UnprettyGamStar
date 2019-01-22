<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입페이지다요/</title>
</head>

<style>
.userRegFrm{
width: 600px;
min-width: 300px;
margin: 0 auto;
}
</style>

<body>

<p>닝겐의 가입을 환영한다요</p>
<form action="post">
        <!-- 아이디 -->
        <div class="userRegFrm">
            <label for = "userId">아이디</label>
            <input type="text" class="regControl" id="userId" name="userId" placeholder="아이디 입력" required>
            <div class="checkMsg" id="id_check"></div>
        </div>
        <!-- 비밀번호 -->
			<div class="userRegFrm">
                    <label for="userPw">비밀번호</label>
                        <input type="password" class="regControl" id="userPw" name="userPw" placeholder="비밀번호" required>
                    <div class="checkMsg" id="pw_check"></div>
                </div>
                <!-- 비밀번호 재확인 -->
                <div class="userRegFrm">
                    <label for="userPw2">비밀번호 확인</label>
                        <input type="password" class="regControl" id="userPw2" name="userPw2" placeholder="비밀번호 확인" required>
                    <div class="checkMsg" id="pw2_check"></div>
                </div>
                <!-- 본인확인 이메일 -->
                <div class="userRegFrm">
                    <label for="userEmail">이메일</label>
                        <input type="text" class="regControl" name="userEmail" id="userEmail" placeholder="E-mail" required>
                        <!-- <input type="text" style="margin-top: 5px;"class="email_form" name="email_confirm" id="email_confirm" placeholder="인증번호를 입력해주세요!" required>
                            <button type="button" class="btn btn-outline-danger btn-sm px-3" onclick="confirm_email()">
                                <i class="fa fa-envelope"></i>&nbsp;인증
                            </button>&nbsp;
                            <button type="button" class="btn btn-outline-info btn-sm px-3">
                                <i class="fa fa-envelope"></i>&nbsp;확인
                            </button>&nbsp; -->
                        <div class="checkMsg" id="email_check"></div>
                </div>
                <!-- 휴대전화 -->
                <div class="userRegFrm">
                    <label for="userPhone">휴대전화 ('-' 없이 번호만 입력해주세요)</label>
                    <input type="text" class="regControl" id="userPhone" name="userPhone" placeholder="휴대전화 번호" required>
                    <div class="checkMsg" id="phone_check"></div>
                </div>
                <div class="reg_button">
                    <a class="btn btn-danger px-3" href="${pageContext.request.contextPath}">
                        <i class="fa fa-rotate-right pr-2" aria-hidden="true"></i>취소하기
                    </a>&emsp;&emsp;
                    <button class="btn btn-primary px-3" id="reg_submit">
                        <i class="fa fa-heart pr-2" aria-hidden="true"></i>가입하기
                    </button>
                </div>
            </form>
</body>
</html>
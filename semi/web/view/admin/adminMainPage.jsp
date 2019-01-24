<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
/* 위치/크기 설정용 */
	div{
		border:1px solid red;
	}
</style>
<%@ include file="/view/admin/common/header.jsp" %>
<!-- 여기서부터 본문 -->
<section>
	<div id='adminFunctionBtn-container'>
		4개 기능 차례대로 네모나게
	</div>
	<div id='adminMainContent-container'>
		<div id='reportList-container'>
			최신 신고 5개쯤(신고당한 사람, 링크,시간)
		</div>
		<div id='supportList-container'>
			최신 문의 5개(문의한 사람, 제목,시간)
		</div>
		<div id='adminList-container'>
			admin-최신 활동 순서(5명까지만)
		</div>
	</div>
</section>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<form id="emailFrm" method="post" action="<%=request.getContextPath()%>/emailsss">
<input type="hidden" id="email" name="email" value="">
</form>

</body>
<script>
console.log('시작');
//var a = $('#userEmail',userRegFrm.document).val();
//var b = $("#userEmail", opener.document ).val();

//console.log(a);
//console.log(b);


console.log('끝');
/* $(window).on('load', function() {
	 console.log('All assets are loaded');
	val a = $('#userEmail', opener.document).val();
	 console.log(a);
	 alert('asdasdad');
	 //$('#email').val(a);
	})
 */

/* function call(email){
	$('#email').val(email));
	$('#emailFrm').submit;	
} */

/* $(function(){
$('#email').val( $('#userEmail',opener.document).val() );
console.log("짜증나");
}); */

/* $(function() {
	 // Handler for .ready() called.
	 alert('asdads');
	}); */

/* $( window ).load(function() {
    console.log( "window loaded" );
    alert('asdgyuwegyeywgyu');
}); */



</script>
</html>
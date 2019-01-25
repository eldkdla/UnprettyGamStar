/**
 * 
 */

$(function(){
	
});

function onClickReportBtn(targetUserNo, targetNewspeedNo, url) {
	$('#reportContent button').off('click').on('click', function(e){
		requestReport(targetUserNo, targetNewspeedNo, url);
	}) 
}


function requestReport(targetUserNo, targetNewspeedNo, mapping) {
			var files = $('#reportContent input[type=file]')[0].files;
      		var formData = new FormData();
      		var textArea = $('#reportContent textArea');
      		var texttttt = $(textArea).val();
      		
      		
      		for (var i = 0; i < files.length; i++) {
      			formData.append('file' + i, files[i]);
      		}
      		
      		formData.append('content', $(textArea).val());
      		formData.append('targetUserNo', targetUserNo);
      		formData.append('targetNewspeedNo', targetNewspeedNo);
      		
      		console.log(formData);
      		console.log('반가워요!!!');
      	

         	$.ajax({
        		url:mapping,
        		type:"post",
        		data:formData,
                processData: false, 
                contentType: false, 
                success: function(data, textStatus, jqXHR)
                {
                	
                }, error: function(jqXHR, textStatus, errorThrown)
                {
                    console.log('ERRORS: ' + textStatus);
                    console.log(errorThrown);
                }
        	});


}
/**
 * 
 */

$(function(){
	
});

function onClickReportBtn(targetUserNo, targetNewspeedNo, url) {
	$('#reportBgk').off('click').on('click', function(e){
		e.stopPropagation();
		$('#reportBgk').fadeToggle(700);
	});
	
	$('#reportBgk button').off('click').on('click', function(e){
		e.stopPropagation();
		requestReport(targetUserNo, targetNewspeedNo, url);
	});
	
	$('#reportContent').off('click').on('click', function(e){
		e.stopPropagation();
	});
	
	
	
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
                	$('#reportBgk').fadeOut(700);
                	$(textArea).val('');
                	$('#reportContent input[type=file]').val('');
                }, error: function(jqXHR, textStatus, errorThrown)
                {
                    console.log('ERRORS: ' + textStatus);
                    console.log(errorThrown);
                    $('#reportBgk').fadeOut(700);
                    $(textArea).val('');
                    $('#reportContent input[type=file]').val('');
                }
        	});
}


function checkFileType(inputfiles) {
	
	for (var i = 0; i < inputfiles.length; i++) {
		var fileName = inputfiles[i].name;
		
	}
	
}
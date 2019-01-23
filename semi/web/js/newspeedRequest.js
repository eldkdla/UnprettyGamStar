/**

 * 
 */


var isActiveContainerBtn = false;
    var isActiveImageBtn = false;
    var selectedTab = 1;
    var maxNewspeedLength = 1;
    var selectedNewspeedIndex = 0;
    var selectedTag;

    function showNewspeedview(data) {
        $('#newspeedview_btn_wrapper').toggle();
        $('#newspeedview_list_wrapper').children().remove();

   
        appendNewspeedElement(JSON.parse(data), $('#newspeedview_list_wrapper').children().length, function(){
        	$('#newspeedview_list_wrapper > div').css('left',0);	
        	$('#newspeedview_list_wrapper > div:eq(0) > .newspeedview_media:eq(0)').attr('class','newspeedview_media_select');
        	console.log('안녕하세요!!!');
        	
        });
        
        removeRightBtn();
        removeLeftBtn();
    }
    
    function removeRightBtn() {
    	$('#container-right-btnnnn').css('display','inline-block');
    	
    	console.log('오른쪽 제거시도');
    	
        var nextSel =  $(selectedTag).next();
        
        console.log(nextSel);
        console.log($(nextSel).attr('value'));
        
        if (typeof $(nextSel).attr('value') == "undefined") {
        	$('#container-right-btnnnn').css('display','none');
        	console.log('오른쪽 제거');
        	return;
        }
    }
    
    function removeLeftBtn() {
    	$('#container-left-btnnnn').css('display','inline-block');
    	
        var prevSel =  $(selectedTag).prev();
        if (typeof $(prevSel).attr('value') == "undefined") {
        	$('#container-left-btnnnn').css('display','none');
        	return;
        }
    }
    
    function appendNewspeedElementBefore(data, length5, callback) {
        var fileList = data.fileList;
        var content = data.content;
        var userNo = data.userNo;
        var newspeedNo = data.newspeedNo;
        
        
 
        $('#newspeedview_list_wrapper').prepend($('<div/>', {
            class: 'newspeedview_list'
        }));
        
        var left = Number($('.newspeedview_list_select').css('left').replace("px","")); 
        var width = Number($('#newspeedview_list_wrapper > .newspeedview_list_select').width());
        console.log(left + '여기선 무스닝ㄹ이 일어나나요?');
        console.log(width);
        console.log(newspeedNo);
        console.log(length5);
        
        $('#newspeedview_list_wrapper > div').css('left', (left-width));

        $('.newspeedview_list:eq(' + length5 + ')').append($('<input/>', {
            type: 'hidden',
            class: 'newspeedNo',
            value: newspeedNo
        }));


        $('.newspeedview_list:eq(' + length5 + ')').append($('<div/>', {
            class: 'newspeedview_media_list_wrapper'
        }));

        $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper').append($('<div/>', {
            class: 'medialist_left_btn',

        }));

        $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .medialist_left_btn').css('display', 'none');

        $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper').append($('<div/>', {
            class: 'newspeedview_media_list',
        }));

        var mediaList = [];
        var imageIndex = 0;
        var imageLength = 0;

        for (var k = 0; k < fileList.length; k++) {
            if (fileList[k].mediaType == 0){
                imageLength++;
            }
        }

        var mediaReadIndex = 0;
        var images = [];
        setMedia();

        function setMedia() {
  
            var randomMedia;
            
            console.log(mediaReadIndex + '왜 지랄이세요진짜로?');
            console.log(imageLength + '지랄좀작작하자....');
            
           	if(mediaReadIndex == fileList.length){    
                addElement();
            	if (typeof callback == "function") {
                    callback();
                    return;
                }   
            }
            
            if (fileList[mediaReadIndex].mediaType == 0) {
            	console.log('이건이미지에요!' + mediaReadIndex);
            	
                randomMedia = new Image();
                images[images.length] = randomMedia;
                randomMedia.src = '../' + fileList[mediaReadIndex].fileName;
                
                
                randomMedia.onload = function(e) {
                	console.log('도와줘제발......')
                	
                	console.log(randomMedia.width);
                	console.log(randomMedia.height);
                	
                    
                    if (mediaReadIndex < fileList.length) {
                    	mediaReadIndex++;
                        setMedia();
                    } else if(mediaReadIndex == fileList.length){
             
                        addElement();
                    	if (typeof callback == "function") {
                            callback();
                            return;
                        }   
                    }
               
                }
             
            } else {
            	console.log('이건동영상이에요!' + mediaReadIndex);
            	if (mediaReadIndex < fileList.length) {
            		mediaReadIndex++;
            	} else if(mediaReadIndex == fileList.length){    
                    addElement();
                	if (typeof callback == "function") {
                        callback();
                        return;
                    }   
                }
                setMedia();
            }


        }
        function addElement() {
        	
            for (var i = 0; i < fileList.length; i++) 	{
                var imageNum = 0;

                $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list').append($('<div/>', {
                    class: 'newspeedview_media'
                }));

               

                if (fileList[i].mediaType == 0) {
                	 var maxWidth = $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media').width();
                    
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ')').append($('<img/>', {
                        class: 'newspeedview_media_image',
                        src:'../' + fileList[i].fileName
                    }));

                    if(images[imageNum].width >= maxWidth){
                        $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media_list > div:eq(' + i +  ') .newspeedview_media_image:eq(' + imageNum +')').css('width','100%');
                        $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media_list > div:eq(' + i +  ') .newspeedview_media_image:eq(' + imageNum +')').css('height','auto');
                    } else {
                        var rate = images[imageNum].width/ maxWidth * 100;
                        $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media_list > div:eq(' + i +  ') .newspeedview_media_image:eq(' + imageNum +')').css('width',rate + '%');
                        $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media_list > div:eq(' + i +  ') .newspeedview_media_image:eq(' + imageNum +')').css('height','auto');
                    }
                    imageNum++;
                } else {
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ')').append($('<video/>', {
                        class: 'newspeedview_media_video',
                        src:'../' + fileList[i].fileName
                    }));

                    $('.newspeedview_media_video').attr('controls',true);
                }

                var tagList = fileList[i].tagList;
                
                
                for (var j = 0; j < tagList.length; j++) {
                	console.log(tagList[j]);
                	console.log('요기임당');
                	
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') > img').append($('<div/>', {
                        class: 'balloons',
                        text: tagList[j].userName,
                        left: (300),
                        top: (300)
                    }));

                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') .balloons:eq(' + j + ')').append($('<input>', {
                        class: 'tagUserNo',
                        value: tagList[j].userNo,
                        type: 'hidden'
                    }));

                }

                $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ')');
            }

            $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper').append($('<div/>', {
                class: 'medialist_right_btn',
            }));
            
            if(fileList.length < 2) {
            	$('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .medialist_right_btn').css('display','none');
            }

            $('.newspeedview_list:eq(' + length5 + ')').append($('<div/>', {
                class: 'newspeedview_all_content_wrapper'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_writer_wrapper'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper').append($('<div/>', {
                class: 'newspeedview_writer_content_wrapper'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper').append($('<div/>', {
                class: 'newspeedview_profilephoto',
                background: 'url(../' + data.profilephoto + ')'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper .newspeedview_profilephoto').append($('<input/>', {
                type: 'hidden',
                class: "userNo",
                value: userNo
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper').append($('<div/>', {
                class: 'newspeedview_writername'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper .newspeedview_writername').append($('<h4/>', {
                text: data.userName
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper .newspeedview_writername').append($('<input/>', {
                type: 'hidden',
                class: 'userNo',
                value: userNo
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<hr/>', {
                class: 'newspeed_content_hr'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_content_wrapper'
            }));


            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_content_wrapper').append($('<div/>', {

            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_content_wrapper > div').append($('<h4/>', {
                text: data.userName
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_content_wrapper > div').append('&nbsp;' + data.content);

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_content_wrapper > div').append($('<input/>', {
                type: 'hidden',
                class: 'userNo',
                value: userNo
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<hr/>', {
                class: 'newspeed_content_hr'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_comment_wrapper'
            }));

            for (var j = 0; j < data.commentList.length; j++) {
                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper').append($('<div/>', {
                    html: '<h4>' + data.commentList[j].userName + '</h4>&nbsp;' + data.commentList[j].commentContent
                }));

                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div').append($('<input/>', {
                    type: 'hidden',
                    class: 'userNo',
                    value: data.commentList[j].userNo
                }));
            }
            
            $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(0)').attr('class','newspeedview_media_select');


            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_icon_wrapper'
            }));
            
            var iconClass = 'newspeed_like_icon_wrapper';
            
            console.log(data.isLike);
            
            if (data.isLike) {
            	iconClass = 'newspeed_like_active_icon_wrapper';
            }
            
            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_icon_wrapper').append($('<div/>', {
                class: iconClass
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_icon_wrapper').append($('<div/>', {
                class: 'newspeed_report_icon_wrapper'
            }));

            iconClass = 'newspeed_store_icon_wrapper';
            
            if (data.isStore){
            	iconClass = 'newspeed_store_active_icon_wrapper'
            }

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_icon_wrapper').append($('<div/>', {
                class:iconClass
            }));


            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeed_commentwrite_wrapper'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeed_commentwrite_wrapper').append($('<input/>', {
                type: "text",
                class: 'newspeedview_comment_write'
            }));


            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeed_commentwrite_wrapper').append($('<div/>', {
              
                class: 'newspeedview_comment_write_submit'
            }));

            // "commentList":[{"userNo":1,"userName":"임태완","profilephoto":"basic_profile_photo.png" ,"commentContent":"날씨가 너무 춥네요~", "commentIndex":1},

            if ($('.newspeedview_list_select').length == 0) {
            	console.log('바꿉니당!!!!!!!!!!!!!!!111');
            	$('.newspeedview_list:eq(' + length5 + ')').attr('class', 'newspeedview_list_select');
            } else {
            	console.log('바꿉니당!!!!!!!!!!!!!!!111저두요!!!!!!!!!!!!!!!!');
            	console.log($('.newspeedview_list:eq(' + length5 + ')'));
            	$('.newspeedview_list:eq(' + length5 + ')').attr('class', 'newspeedview_list');

            }


            onBindEvent();
            resizeMedia();
        }
 

    }
 

    function appendNewspeedElement(data, length5, callback) {
        var fileList = data.fileList;
        var content = data.content;
        var userNo = data.userNo;
        var newspeedNo = data.newspeedNo;
        
        console.log(data);
        console.log('이렇게 옵니다');
        
        console.log(newspeedNo);
        console.log(length5);
        
 
        $('#newspeedview_list_wrapper').append($('<div/>', {
            class: 'newspeedview_list'
        }));
        
        console.log('이제 뉴스피드 번호를 더하자!');

        $('.newspeedview_list:eq(' + length5 + ')').append($('<input/>', {
            type: 'hidden',
            class: 'newspeedNo',
            value: newspeedNo
        }));

        console.log('이제 미디어를 감싸야지!');

        $('.newspeedview_list:eq(' + length5 + ')').append($('<div/>', {
            class: 'newspeedview_media_list_wrapper'
        }));

        $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper').append($('<div/>', {
            class: 'medialist_left_btn',

        }));

        $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .medialist_left_btn').css('display', 'none');

        $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper').append($('<div/>', {
            class: 'newspeedview_media_list',
        }));

        var mediaList = [];
        var imageIndex = 0;
        var imageLength = 0;

        for (var k = 0; k < fileList.length; k++) {
        	console.log(fileList[k].mediaType + "은무엇?");
            if (fileList[k].mediaType == 0){
                imageLength++;
            }
        }
        
        console.log(imageLength + '이미지몇개임?');

        var mediaReadIndex = 0;
        var images = [];
        setMedia();

        function setMedia() {
  
            var randomMedia;
            
            console.log(mediaReadIndex + '왜 지랄이세요진짜로?');
            console.log(imageLength + '지랄좀작작하자....');
            
           	if(mediaReadIndex == fileList.length){    
                addElement();
            	if (typeof callback == "function") {
                    callback();
                    return;
                }   
            }
            
            if (fileList[mediaReadIndex].mediaType == 0) {
            	console.log('이건이미지에요!' + mediaReadIndex);
            	
                randomMedia = new Image();
                images[images.length] = randomMedia;
                randomMedia.src = '../' + fileList[mediaReadIndex].fileName;
                
                
                randomMedia.onload = function(e) {
                	console.log('도와줘제발......')
                	
                	console.log(randomMedia.width);
                	console.log(randomMedia.height);
                	
                    
                    if (mediaReadIndex < fileList.length) {
                    	mediaReadIndex++;
                        setMedia();
                    } else if(mediaReadIndex == fileList.length){
             
                        addElement();
                    	if (typeof callback == "function") {
                            callback();
                            return;
                        }   
                    }
               
                }
             
            } else {
            	console.log('이건동영상이에요!' + mediaReadIndex);
            	if (mediaReadIndex < fileList.length) {
            		mediaReadIndex++;
            	} else if(mediaReadIndex == fileList.length){    
                    addElement();
                	if (typeof callback == "function") {
                        callback();
                        return;
                    }   
                }
                setMedia();
            }


        }

        function addElement() {
        	
            for (var i = 0; i < fileList.length; i++) {
                var imageNum = 0;

                $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list').append($('<div/>', {
                    class: 'newspeedview_media'
                }));
                
              

                if (fileList[i].mediaType == 0) {
                    var maxWidth = $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media_list_wrapper').width();
                    console.log(maxWidth + "너왜뭐안나옴?");
                    console.log(images[imageNum].width + "너도왜안나옴?");
                   
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ')').append($('<img/>', {
                        class: 'newspeedview_media_image',
                        src:'../' + fileList[i].fileName
                    }));

                    if(images[imageNum].width >= maxWidth){
                        $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media_list > div:eq(' + i +  ') .newspeedview_media_image:eq(' + imageNum +')').css('width','100%');
                        $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media_list > div:eq(' + i +  ') .newspeedview_media_image:eq(' + imageNum +')').css('height','auto');
                    } else {
                        var rate = images[imageNum].width/ maxWidth * 100;
                        $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media_list > div:eq(' + i +  ') .newspeedview_media_image:eq(' + imageNum +')').css('width',rate + '%');
                        $('.newspeedview_list:eq(' + length5 + ') .newspeedview_media_list > div:eq(' + i +  ') .newspeedview_media_image:eq(' + imageNum +')').css('height','auto');
                    }
                    imageNum++;
                } else {
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ')').append($('<video/>', {
                        class: 'newspeedview_media_video',
                        src:'../' + fileList[i].fileName
                    }));

                    $('.newspeedview_media_video').attr('controls',true);
                }

                var tagList = fileList[i].tagList;
                
                
                for (var j = 0; j < tagList.length; j++) {
                	console.log(tagList[j]);
                	console.log('요기임당');
                	
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') > img').append($('<div/>', {
                        class: 'balloons',
                        text: tagList[j].userName,
                        left: (300),
                        top: (300)
                    }));

                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') .balloons:eq(' + j + ')').append($('<input>', {
                        class: 'tagUserNo',
                        value: tagList[j].userNo,
                        type: 'hidden'
                    }));

                }

                
            }
            
      


            $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper').append($('<div/>', {
                class: 'medialist_right_btn',
            }));
            
            if(fileList.length < 2) {
            	$('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .medialist_right_btn').css('display','none');
            }
            $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(0)').attr('class','newspeedview_media_select');

            $('.newspeedview_list:eq(' + length5 + ')').append($('<div/>', {
                class: 'newspeedview_all_content_wrapper'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_writer_wrapper'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper').append($('<div/>', {
                class: 'newspeedview_writer_content_wrapper'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper').append($('<div/>', {
                class: 'newspeedview_profilephoto',
                background: 'url(../' + data.profilephoto + ')'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper .newspeedview_profilephoto').append($('<input/>', {
                type: 'hidden',
                class: "userNo",
                value: userNo
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper').append($('<div/>', {
                class: 'newspeedview_writername'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper .newspeedview_writername').append($('<h4/>', {
                text: data.userName
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper .newspeedview_writername').append($('<input/>', {
                type: 'hidden',
                class: 'userNo',
                value: userNo
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<hr/>', {
                class: 'newspeed_content_hr'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_content_wrapper'
            }));


            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_content_wrapper').append($('<div/>', {

            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_content_wrapper > div').append($('<h4/>', {
                text: data.userName
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_content_wrapper > div').append('&nbsp;' + data.content);

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_content_wrapper > div').append($('<input/>', {
                type: 'hidden',
                class: 'userNo',
                value: userNo
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<hr/>', {
                class: 'newspeed_content_hr'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_comment_wrapper'
            }));

            for (var j = 0; j < data.commentList.length; j++) {
                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper').append($('<div/>', {
                    html: '<h4>' + data.commentList[j].userName + '</h4>&nbsp;' + data.commentList[j].commentContent
                }));

                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div').append($('<input/>', {
                    type: 'hidden',
                    class: 'userNo',
                    value: data.commentList[j].userNo
                }));
            }

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_icon_wrapper'
            }));

            var iconClass = 'newspeed_like_icon_wrapper';
            
            console.log(data.isLike);
            
            if (data.isLike) {
            	iconClass = 'newspeed_like_active_icon_wrapper';
            }
            
            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_icon_wrapper').append($('<div/>', {
                class: iconClass
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_icon_wrapper').append($('<div/>', {
                class: 'newspeed_report_icon_wrapper'
            }));

            iconClass = 'newspeed_store_icon_wrapper';
            
            if (data.isStore){
            	iconClass = 'newspeed_store_active_icon_wrapper'
            }

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_icon_wrapper').append($('<div/>', {
                class:iconClass
            }));


            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeed_commentwrite_wrapper'
            }));

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeed_commentwrite_wrapper').append($('<input/>', {
                type: "text",
                class: 'newspeedview_comment_write'
            }));


            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeed_commentwrite_wrapper').append($('<div/>', {
              
                class: 'newspeedview_comment_write_submit'
            }));

            // "commentList":[{"userNo":1,"userName":"임태완","profilephoto":"basic_profile_photo.png" ,"commentContent":"날씨가 너무 춥네요~", "commentIndex":1},

            if ($('.newspeedview_list_select').length == 0) {
            	$('.newspeedview_list:eq(' + length5 + ')').attr('class', 'newspeedview_list_select');
            	
            } else {
            	$('.newspeedview_list:eq(' + length5 + ')').attr('class', 'newspeedview_list');

            }
         
           

            onBindEvent();
            resizeMediaList();
            resizeMedia();
        }
    }

    $(function () {
        onBindEvent();
        resizeMedia();
    });

    function onBindEvent() {
        onClickLeftContainer();
        onClickRightContainer();
        onClickLeftMediaBtn();
        onClickRightMediaBtn();
        onClickLike();
        onClickStore();
        onEnterCommentWrite();
        onClickTagUser();
        onClickCommentUser();
        onClickBackground();
        resizeNewspeedview();
    }

    $(window).resize(function () {
        resizeNewspeedview();
    });

    function resizeNewspeedview() {
        resizeMedia();
        resizeMediaList();
        resizeIcon();
        resizeNewspeedList();
    }

    function resizeMedia() {
        $('.newspeedview_profilephoto').css('height', $('.newspeedview_profilephoto').width());

    }

    function resizeNewspeedList() {

    }

    function resizeMediaList() {
        var left = Number($('.newspeedview_list_select .newspeedview_media_list > div').width());
        var length = $('.newspeedview_list_select .newspeedview_media_list > .newspeedview_media_select').prevAll().length;
        
        var left2 = $('#newspeedview_list_wrapper > div').width();
        var length2 =$('#newspeedview_list_wrapper > .newspeedview_list_select').prevAll().length;
        
        
        $('#newspeedview_list_wrapper > div').css('left',-(left2 * length2));
        
        $('.newspeedview_list_select .newspeedview_media_list > div').css('left', -(left * length));
        
     
    }

    function resizeIcon() {
        $('.newspeedview_icon_wrapper > div').css('height', $('.newspeedview_icon_wrapper > div').css('width'));
    }

    function stopVideo() {
        var videos = document.getElementsByTagName('video');
            
            for (var i = 0; i < videos.length; i++) {
                videos[i].pause();
            }
    }

    function onClickLeftContainer() {
        $('#container-left-btnnnn').off('click').on('click', function (e) {
            e.stopPropagation();
            
 
            if (isActiveContainerBtn) {
                return;
            }
  
            var selList = $('.newspeedview_list_select').prevAll().length;
            var prevSel = $(selectedTag).prev();
            

            if (selList != 0) {
            	selectedTag = prevSel;
            	moveLeftNewspeed();
                removeRightBtn();
                removeLeftBtn();
            } else if (typeof $(prevSel).attr('value') != "undefined"){
            	selectedTag = prevSel;
            	requestNewspeedInfoBefore($(selectedTag).attr('value'));
                removeRightBtn();
                removeLeftBtn();
            }

            stopVideo();
 

        });
    }
    
    function moveLeftNewspeed() {
    	console.log('대체왜안움짇ㄱ이는거임???????????????????????/');
    	
    	if (isActiveContainerBtn) {
    		return;
    	}
    	
        var index = $('.newspeedview_list_select').prevAll().length;
        var select = $('.newspeedview_list_select');
        

        $('#newspeedview_list_wrapper > div').animate({
            left: (Number($('.newspeedview_list_select').css('left').replace("px", "")) + Number($('.newspeedview_list_select').width()))
        }, $('#newspeedview_list_wrapper > div').css('width').replace("px"), function (e) {
            isActiveContainerBtn = false;
            resizeMediaList();
        });

        $(select).attr('class', 'newspeedview_list');
        $(select).prev().attr('class', 'newspeedview_list_select');
    }

    function onClickRightContainer() {
  
        $('#container-right-btnnnn').off('click').on('click', function (e) {
            e.stopPropagation();  
            
            if (isActiveContainerBtn) {
                return;
            }

            var selList = $('.newspeedview_list_select').nextAll().length;
            var nextSel = $(selectedTag).next();
  
            if (selList != 0) {
            	selectedTag = nextSel;
            	moveRightNewspeed();
            	removeRightBtn();
            	removeLeftBtn();
            } else if (typeof $(nextSel).attr('value') != "undefined") {
            	selectedTag = nextSel;
            	requestNewspeedInfoAfter($(selectedTag).attr('value'));
            	removeRightBtn();
            	removeLeftBtn();
            	console.log('안녕?');
            } 
            
            console.log('안녕?');
        });     
    }
    
    function moveRightNewspeed() {
    	if (isActiveContainerBtn) {
    		return;
    	}
    	
        stopVideo();
        isActiveContainerBtn = true;

        var index = $('.newspeedview_list_select').prevAll().length;
        var length = $('#newspeedview_list_wrapper').children().length - 2;
        var select = $('.newspeedview_list_select');
 

        $('#newspeedview_list_wrapper > div').animate({
            left: (Number($('.newspeedview_list_select').css('left').replace("px", "")) - $('.newspeedview_list_select').width())
        }, $('#container-right-btnnnn').css('width').replace("px"), function (e) {
            isActiveContainerBtn = false;
            resizeMediaList();
        });

        $(select).attr('class', 'newspeedview_list');
        $(select).next().attr('class', 'newspeedview_list_select');
    }
    
 

    function onClickLeftMediaBtn() {
        $('.medialist_left_btn').off('click').on('click', function (e) {
            e.stopPropagation();
            if (isActiveContainerBtn) {
                return;
            }
            isActiveContainerBtn = true;
            $('.newspeedview_list_select .medialist_right_btn').css('display', 'inline-block');


            $('.newspeedview_list_select .newspeedview_media_list > div').animate({
                left: Number($('.newspeedview_list_select .newspeedview_media_select').css('left').replace("px", "")) + $('.newspeedview_list_select .newspeedview_media_select').width()
            }, 700, function (e) {
                isActiveContainerBtn = false;
                resizeMediaList();
            });

            var select = $('.newspeedview_list_select .newspeedview_media_select').prev();
            $('.newspeedview_list_select .newspeedview_media_list > div').attr('class', 'newspeedview_media');
            $(select).attr('class', 'newspeedview_media_select');

            if ($(select).prevAll().length == 0) {
                $(this).css('display', 'none');
            }
            stopVideo();

            var videoIndex = $(select).prevAll().length;
            var video = document.getElementsByClassName("newspeedview_list_select")[0].childNodes[1].childNodes[1].childNodes[videoIndex].childNodes[0];

            if (video.nodeName == 'VIDEO') {
                video.play();
            }

        
        });
    }

    function onClickRightMediaBtn() {
        $('.medialist_right_btn').off('click').on('click', function (e) {
            e.stopPropagation();
            if (isActiveContainerBtn) {
                return;
            }
            
            isActiveContainerBtn = true;
            $('.newspeedview_list_select .medialist_left_btn').css('display', 'inline-block');

  

            $('.newspeedview_list_select .newspeedview_media_list > div').animate({
                left: Number($('.newspeedview_list_select .newspeedview_media_select').css('left').replace("px", "")) - $('.newspeedview_list_select .newspeedview_media_select').width()
            }, 700, function (e) {
            	
            	
                isActiveContainerBtn = false;
                resizeMediaList();
                
            });

            var select = $('.newspeedview_list_select .newspeedview_media_select').next();
            $('.newspeedview_list_select .newspeedview_media_list > div').attr('class', 'newspeedview_media');
            $(select).attr('class', 'newspeedview_media_select')

            if ($(select).nextAll().length == 0) {
                $(this).css('display', 'none');
            }

           
            stopVideo();

            var videoIndex = $(select).prevAll().length;
            var video = document.getElementsByClassName("newspeedview_list_select")[0].childNodes[1].childNodes[1].childNodes[videoIndex].childNodes[0];

            if (video.nodeName == 'VIDEO') {
                video.play();
            }

        });
       
    }

    function onClickBackground() {
        $('#newspeedview_btn_wrapper').off().on('click', function (e) {
            e.stopPropagation();
            $(this).slideToggle();
        });

    }

    function onClickLike() {
        $('.newspeed_like_icon_wrapper').off('click').on('click', function (e) {
            e.stopPropagation();

            $.ajax({
                url: '../newspeed/newspeedlike',
                type: "POST",
                data: { "newspeedNo": newspeedNo },
                success: function (data) {
                    console.log('성공입니다!!!!');
                },
                error:function(request,status,error){
                	console.log('아직 응답페이지를 안들었어요..');
                    
                }

     
            });
            
        });
    }

    function onClickStore() {
        $('.newspeed_store_icon_wrapper').off('click').on('click', function (e) {
            e.stopPropagation();
            var newspeedNo = $('.newspeedview_list_select .newspeedNo').val();
            console.log(newspeedNo + '번게시물');

            showMiniModal('저장');

        });
    }

    function onClickTagUser() {
        $('.balloons').off().on('click', function (e) {
            e.stopPropagation();
 
        });
    }

    function onClickCommentUser() {
        $('.newspeedview_comment_wrapper > div > h4').off().on('click', function (e) {
            e.stopPropagation();
            console.log($(this).siblings().val());
            console.log($(this));
        });
    }

    function onClickWriterProfilePhoto() {

    }

    function onClickWriterName() {

    }

    function onClickReport() {

    }

    function onClickMedia() {

    }

    function onEnterCommentWrite() {
        $('.newspeedview_comment_write').off().on('keyup', function (e) {
            e.stopPropagation();

            console.log($(this).val());
            if (e.key == 'Enter') {
                if ($(this).text().length < 5) {
                    return;
                }

                console.log('전송데스요');
            }
        })
    }

    function showMiniModal(message, left, top) {
        $('#miniModal').text(message);
        $('#miniModal').css('left', left - 30);
        $('#miniModal').css('top', top - 50);
        $('#miniModal').fadeIn(700, function (e) {
            $('#miniModal').fadeOut(700);
        });
    }


    function readFile(files, cur) {
        var fileReader = new FileReader();
        fileReader.readAsDataURL(files[cur]);

        fileReader.onload = function (e) {
            cur++;
            if (cur < files.length) {
                readFile(files, cur);
            }
        }
    }


    function requestNewspeedInfo(newspeedNo, selectIndex, maxLength, tag) {
        maxNewspeedLength = maxLength;
        selectedNewspeedIndex = selectIndex;
        selectedTag = tag;
    	
        
        console.log(selectedTag);
        console.log('안녕?');
        
        $.ajax({
            url: '../newspeed/newspeedview',
            type: "POST",
            data: { "newspeedNo": newspeedNo },
            success: function (data) {
            console.log(JSON.parse(data));
               showNewspeedview(data);
            },
            error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }

 
        });
    }
    
    function requestNewspeedInfoAfter(newspeedNo) {
        $.ajax({
            url: '../newspeed/newspeedview',
            type: "POST",
            data: { "newspeedNo": newspeedNo },
            success: function (data) {
            	console.log('성공');
            	console.log(JSON.parse(data));
            	appendNewspeedElement(JSON.parse(data),$('#newspeedview_list_wrapper').children().length - 1,function(){
            		moveRightNewspeed();
            	});       	
            }
        });
    }
    
    
    function requestNewspeedInfoBefore(newspeedNo) {
    	console.log('반가워!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');
    	
        $.ajax({
            url: '../newspeed/newspeedview',
            type: "POST",
            data: { "newspeedNo": newspeedNo },
            success: function (data) {
            	console.log(newspeedNo);
            	console.log('성공');
            	console.log(JSON.parse(data));
            	appendNewspeedElementBefore(JSON.parse(data),0,function(){
            		console.log('너는왜안하니이ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ');
            		moveLeftNewspeed();
            	});       	
            }
        });
    }
    
    
    
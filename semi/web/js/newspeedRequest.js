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
        $('#newspeedview_btn_wrapper').fadeToggle(500);
        $('#newspeedview_list_wrapper').children().remove();

        appendNewspeedElement(JSON.parse(data), $('#newspeedview_list_wrapper').children().length, function(){
        	$('#newspeedview_list_wrapper > div').css('left',0);	
        	$('#newspeedview_list_wrapper > div:eq(0) > .newspeedview_media:eq(0)').attr('class','newspeedview_media_select');
        });
        
        removeRightBtn();
        removeLeftBtn();
    }
    
    function removeRightBtn() {
    	$('#container-right-btnnnn').css('display','inline-block');
    	

        var nextSel =  $(selectedTag).next();

        
        if (typeof $(nextSel).attr('value') == "undefined") {
        	$('#container-right-btnnnn').css('display','none');
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
            

           	if(mediaReadIndex == fileList.length){    
                addElement();
            	if (typeof callback == "function") {
                    callback();
                    return;
                }   
            }
            
            if (fileList[mediaReadIndex].mediaType == 0) {
            	
                randomMedia = new Image();
                randomMedia.src = '../' + fileList[mediaReadIndex].fileName;
                images[images.length] = randomMedia;
                
                randomMedia.onload = function(e) {
                	
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
                	 var maxWidth = 603.644;
                	 var imgRate = images[imageNum].width / images[imageNum].height;
                	 var widthRate;
                	 var imgTag = document.createElement('img');
                	 imgTag.src = '../' + fileList[i].fileName;
                	 $(imgTag).addClass('newspeedview_media_image');
            

                	 if(imgTag.width >= maxWidth){
                         widthRate = 100;
                      } else {
               
                         widthRate = imgTag.width/ maxWidth * 100;
                      }    
                    
              
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ')').append($('<div/>', {
                        class: 'newspeedview_media_image_wrapper',
                        width:widthRate + '%'
                        
        
                    }));
                    
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') .newspeedview_media_image_wrapper').append(imgTag);
             
                    
                    imageNum++;
                } else {
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ')').append($('<video/>', {
                        class: 'newspeedview_media_video',
                        src:'../' + fileList[i].fileName
                    }));
                    
                    
                    var video = $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') > video')[0];    
                   
                    video.onloadeddata = function(e) {
                    	var vWidth = $(video).width();
                    	var vHeight = $(video).height();
                    	
                    	if (vWidth > vHeight) {
                       	 $(video).css('width', '100%');
                       	 $(video).css('height', 'auto');
                        } else {
                       	 $(video).css('width', 'auto');
                       	 $(video).css('height', '100%');
                        }
          
                    };

                    $('.newspeedview_media_video').attr('controls',true);
                }

                var tagList = fileList[i].tagList;
                
                
                for (var j = 0; j < tagList.length; j++) {
                	
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') .newspeedview_media_image_wrapper').append($('<div/>', {
                        class: 'balloons',
                        text: tagList[j].userName,

                    }));
                    
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') .newspeedview_media_image_wrapper > .balloons:eq(' + j +')').css({
                    	'left': (tagList[j].X * 100) + '%',
                    	'top': (tagList[j].Y * 100) + '%'
                    });
                    
            

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
               
            }));
            

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper .newspeedview_profilephoto').css('background-image','url(../' + data.profilephoto + ')');
            

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
                    html: '<h4>' + data.commentList[j].userName + '</h4>&nbsp;' + data.commentList[j].commentContent + "  <p class='beforeTime'>" + data.commentList[j].beforeTime + "</p>",
                    class:'.newspeedview_comment'
                }));

                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ')').append($('<input/>', {
                    type: 'hidden',
                    class: 'userNo',
                    value: data.commentList[j].userNo
                }));
                
                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ')').append($('<input/>', {
                    type: 'hidden',
                    class: 'commentNo',
                    value: data.commentList[j].commentNo
                }));
                

                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ')').append($('<div/>', {
                    class:'newspeedview_comment_option_wrapper',
                }));
                
                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .newspeedview_comment_option_wrapper').append($('<div/>', {
                    class:'comment_option_recomment',
                	text:'답글'
                }));
                
                if (data.commentList[j].isMine) {
                    
                	if (false) {
                	$('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .newspeedview_comment_option_wrapper').append($('<div/>', {
                		class:'comment_option_modify',
                		text:'수정'
                	}));
                	}
                
                	$('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .newspeedview_comment_option_wrapper').append($('<div/>', {
                		class:'comment_option_delete',
                		text:'삭제'
                	}));
                }
                
                if (typeof data.commentList[j].recommentList != "undefined" ) {
                	var nummmm = 0;
                for (var p = data.commentList[j].recommentList.length -1; p >= 0 ; p--) {
                	
                    $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ')').append($('<div/>', {
                        class:'recomment_wrapper',
                        html:"<h4>" +  data.commentList[j].recommentList[p].userName + "</h4>&nbsp;" + data.commentList[j].recommentList[p].commentContent + "  <p class='beforeTime'>" + data.commentList[j].recommentList[p].beforeTime + "</p>",
                    }));
                    
                    $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ')').append($('<input/>', {
                        type:'hidden',
                        class:'userNo',
                        value:data.commentList[j].recommentList[p].userNo
                    }));
                    
                    $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ')').append($('<input/>', {
                        type:'hidden',
                        class:'commentNo',
                        value:data.commentList[j].recommentList[p].commentNo
                    }));
                    
                    if (data.commentList[j].recommentList[p].isMine){
                    	
                    	 $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ')').append($('<div/>', {
                             class:'recomment_option_wrapper',
                             style:'display:block;',
                             value:data.commentList[j].recommentList[p].commentNo
                         }));
                   	 
                   	 $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ') .recomment_option_wrapper').css('display','block');
                   	 
                   	 $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ') .recomment_option_wrapper').append($('<div/>', {
                            class:'comment_option_delete',
                            text:'삭제'
                        }));
                   }
                    
                    nummmm = (nummmm + 1);
              	
                	}
                }
                
                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div > div > *').css({
                	'display':'inline-block'
                });
                   
            }
            
            $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(0)').attr('class','newspeedview_media_select');


            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_icon_wrapper'
            }));
            
            var iconClass = 'newspeed_like_icon_wrapper';
        
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
            resizeMedia();
        }
 

    }
 

    function appendNewspeedElement(data, length5, callback) {
        var fileList = data.fileList;
        var content = data.content;
        var userNo = data.userNo;
        var newspeedNo = data.newspeedNo;
        

 
        $('#newspeedview_list_wrapper').append($('<div/>', {
            class: 'newspeedview_list'
        }));
        

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
    
           	if(mediaReadIndex == fileList.length){    
                addElement();
            	if (typeof callback == "function") {
                    callback();
                    return;
                }   
            }
            
            if (fileList[mediaReadIndex].mediaType == 0) {
 
                randomMedia = new Image();
     
                randomMedia.src = '../' + fileList[mediaReadIndex].fileName;
                
                randomMedia.onload = function(e) {
                	images[images.length] = this;
                	images[images.length-1].width = randomMedia.width;
                	images[images.length-1].height = randomMedia.height;

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
               	 var maxWidth = 603.644;
            	 var imgRate = images[imageNum].width / images[imageNum].height;
            	 var widthRate;
            	 var imgTag = document.createElement('img');
            	 imgTag.src = '../' + fileList[i].fileName;
            	 $(imgTag).addClass('newspeedview_media_image');

   
            	 if(imgTag.width >= maxWidth){
                     widthRate = 100;
                  } else {
           
                     widthRate = imgTag.width/ maxWidth * 100;
                  }                      
               
                $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ')').append($('<div/>', {
                    class: 'newspeedview_media_image_wrapper',
                    width:widthRate + '%'
                }));
                
                $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') .newspeedview_media_image_wrapper').append(imgTag);
                
                imageNum++;
                
                } else {
                	
                    $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ')').append($('<video/>', {
                        class: 'newspeedview_media_video',
                        src:'../' + fileList[i].fileName
                    }));
                    
                
                    
                    var video = $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') > video')[0];    
                         
                    video.onloadeddata = function(e) {
                    	var vWidth = $(video).width();
                    	var vHeight = $(video).height();
                    	
                    	if (vWidth > vHeight) {
                       	 $(video).css('width', '100%');
                       	 $(video).css('height', 'auto');
                        } else {
                       	 $(video).css('width', 'auto');
                       	 $(video).css('height', '100%');
                        }
               
                    };
                
                    
                    $('.newspeedview_media_video').attr('controls',true);
                }
          

                var tagList = fileList[i].tagList;
                
                
                for (var j = 0; j < tagList.length; j++) {
                	 $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') .newspeedview_media_image_wrapper').append($('<div/>', {
                         class: 'balloons',
                         text: tagList[j].userName,

                     }));
                     
                     $('.newspeedview_list:eq(' + length5 + ') ' + '.newspeedview_media_list_wrapper .newspeedview_media_list .newspeedview_media:eq(' + i + ') .newspeedview_media_image_wrapper > .balloons:eq(' + j +')').css({
                     	'left': (tagList[j].X * 100) + '%',
                     	'top': (tagList[j].Y * 100) + '%'
                     });

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
                
            }));
            
            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_writer_wrapper .newspeedview_writer_content_wrapper .newspeedview_profilephoto').css('background-image','url(../' + data.profilephoto + ')');
            

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
                    html: '<h4>' + data.commentList[j].userName + '</h4>&nbsp;' + data.commentList[j].commentContent + "  <p class='beforeTime'>" + data.commentList[j].beforeTime + "</p>",
                    class:'.newspeedview_comment'
                }));

                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ')').append($('<input/>', {
                    type: 'hidden',
                    class: 'userNo',
                    value: data.commentList[j].userNo
                }));
                
                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ')').append($('<input/>', {
                    type: 'hidden',
                    class: 'commentNo',
                    value: data.commentList[j].commentNo
                }));
                

                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ')').append($('<div/>', {
                    class:'newspeedview_comment_option_wrapper',
                }));
                
                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .newspeedview_comment_option_wrapper').append($('<div/>', {
                    class:'comment_option_recomment',
                	text:'답글'
                }));
                
                
                if (data.commentList[j].isMine) {
                
                	if (false) {
                	$('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .newspeedview_comment_option_wrapper').append($('<div/>', {
                		class:'comment_option_modify',
                		text:'수정'
                	}));
                	}
                
                	$('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .newspeedview_comment_option_wrapper').append($('<div/>', {
                		class:'comment_option_delete',
                		text:'삭제'
                	}));
                	
                }
                if (typeof data.commentList[j].recommentList != "undefined" ) {
                	var nummmm = 0;
                for (var p = data.commentList[j].recommentList.length -1; p >= 0 ; p--) {
                	
  
                	
                    $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ')').append($('<div/>', {
                        class:'recomment_wrapper',
                        html:"<h4>" +  data.commentList[j].recommentList[p].userName + "</h4>&nbsp;" + data.commentList[j].recommentList[p].commentContent + "  <p class='beforeTime'>" + data.commentList[j].recommentList[p].beforeTime + "</p>",
                    }));
                    
                    $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ')').append($('<input/>', {
                        type:'hidden',
                        class:'userNo',
                        value:data.commentList[j].recommentList[p].userNo
                    }));
                    
                    $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ')').append($('<input/>', {
                        type:'hidden',
                        class:'commentNo',
                        value:data.commentList[j].recommentList[p].commentNo
                    }));
                    
         
                    
                    if (data.commentList[j].recommentList[p].isMine){
                
                    	
                      	 $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ')').append($('<div/>', {
                               class:'recomment_option_wrapper',
                               style:'display:block;',
                               value:data.commentList[j].recommentList[p].commentNo
                           }));
                      	 
                      	 $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ') .recomment_option_wrapper').css('display','block');
                      	 
                      	 $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + j + ') .recomment_wrapper:eq('+ (nummmm) + ') .recomment_option_wrapper').append($('<div/>', {
                               class:'comment_option_delete',
                               text:'삭제'
                           }));
                      }
                    
                    nummmm = (nummmm + 1);
              	
                	}
                }
                
                $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div > div > *').css({
                	'display':'inline-block'
                });
                   
            }

            $('.newspeedview_list:eq(' + length5 + ') .newspeedview_all_content_wrapper').append($('<div/>', {
                class: 'newspeedview_icon_wrapper'
            }));

            var iconClass = 'newspeed_like_icon_wrapper';
         
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
            resizeMedia();
            resizeNewspeedview();
        }
    }

    $(function () {
        onBindEvent();
        resizeMedia();
    });

    function onBindEvent() {
    	onClickBackground();
        onClickLeftContainer();
        onClickRightContainer();
        onClickLeftMediaBtn();
        onClickRightMediaBtn();
        onClickLike();
        onClickStore();
        onEnterCommentWrite();
        onClickCommentWriteSubmit()
        onClickTagUser();
        onClickCommentUser();
        onClickReport();
        onClickRecomment();  
        onClickCommentDelete();
        onClickNewspeedMediaImage();
        onClickWriterProfilePhoto();
        onClickWriterName();
        onClickProfilePhoto();
        onClickRecommentWriterName();
    }

    $(window).resize(function () {
        resizeNewspeedview();
        onClickLike();
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
            } 
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
        $('#newspeedview_btn_wrapper').off('click').on('click', function (e) {
            e.stopPropagation();
            
            $(this).fadeToggle(500);
        });
        
        $('#newspeedview_btn_wrapper *').off('click').on('click', function (e) {
            e.stopPropagation();
            
        });

    }

    function onClickLike() {
        $('.newspeed_like_icon_wrapper').off('click').on('click', function (e) {
            e.stopPropagation();
            var newspeedNo = $('.newspeedview_list_select .newspeedNo').val();
    
            var div = $(this);

            $.ajax({
                url: '../newspeed/newspeedlike',
                type: "POST",
                data: { "newspeedNo": newspeedNo },
                success: function (data) {
                	console.log(data);
                    if (data != "error") {
                    	$(div).removeClass();
                    	$(div).addClass(data);
                    
                    }
                    onClickLike();
                },
                error:function(request,status,error){
               
                }
     
            });
            
        });
        
        $('.newspeed_like_active_icon_wrapper').off('click').on('click', function (e) {
            e.stopPropagation();
            var newspeedNo = $('.newspeedview_list_select .newspeedNo').val();

            var div = $(this);

            $.ajax({
                url: '../newspeed/newspeedlike',
                type: "POST",
                data: { "newspeedNo": newspeedNo },
                success: function (data) {
                	console.log(data);
                    if (data != "error") {
                    	$(div).removeClass();
                    	$(div).addClass(data);
                    
                    }
                    onClickLike();
                },
                error:function(request,status,error){
              
                    
                }
     
            });
            
        });
       
    }

    function onClickStore() {
        $('.newspeed_store_icon_wrapper').off('click').on('click', function (e) {
            e.stopPropagation();
            var newspeedNo = $('.newspeedview_list_select .newspeedNo').val();
            console.log(newspeedNo);
            var div = $(this);

            $.ajax({
                url: '../newspeed/newspeedstore',
                type: "POST",
                data: { "newspeedNo": newspeedNo },
                success: function (data) {
                	console.log(data);
                    if (data != "error") {
                    	$(div).removeClass();
                    	$(div).addClass(data);
                    
                    }
                    onClickStore();
                },
                error:function(request,status,error){
                	
                    
                }
            });

        });
        
        
        $('.newspeed_store_active_icon_wrapper').off('click').on('click', function (e) {
            e.stopPropagation();
            var newspeedNo = $('.newspeedview_list_select .newspeedNo').val();
            console.log(newspeedNo);
            var div = $(this);

            $.ajax({
                url: '../newspeed/newspeedstore',
                type: "POST",
                data: { "newspeedNo": newspeedNo },
                success: function (data) {
                	console.log(data);
                    if (data != "error") {
                    	$(div).removeClass();
                    	$(div).addClass(data);
                    
                    }
                    onClickStore();
                },
                error:function(request,status,error){
             
                    
                }
            });

        });
    }
    

    function onClickTagUser() {
        $('.balloons').off('click').on('click', function (e) {
            e.stopPropagation();
            location.href = '../view/profile?uu=' + $(this).children()[0].value;
        });
    }
    

    function onClickCommentUser() {
        $('.newspeedview_comment_wrapper > div > h4').off('click').on('click', function (e) {
            e.stopPropagation();
            location.href = '../view/profile?uu=' + $(this).siblings().val();
            
        });
    }

    function onClickWriterProfilePhoto() {
    	$('.newspeedview_profile_photo').off('click').on('click',function(e){
    		e.stopPropagation();
    		location.href = '../view/profile?uu=' + $('.newspeedview_list_select .userNo').val();
    	});
    }

    function onClickWriterName() {
    	$('.newspeedview_writername > h4').off('click').on('click',function(e){
    		e.stopPropagation();
    		location.href = '../view/profile?uu=' + $('.newspeedview_list_select .userNo').val();
    	});
    	
    	$('.newspeedview_content_wrapper > div > h4').off('click').on('click',function(e){
    		e.stopPropagation();
    		location.href = '../view/profile?uu=' + $('.newspeedview_list_select .userNo').val();
    	});
    }

    function onClickReport() {
    	
    	$('.newspeed_report_icon_wrapper').off('click').on('click',function(e){
        	var targetUserNo = $('.newspeedview_list_select .newspeedview_writer_content_wrapper .userNo').val();
        	var targetNewspeedNo = $('.newspeedview_list_select .newspeedNo').val();
        	
        	$('#reportBgk').fadeIn(700);
        	
        	onClickReportBtn(targetUserNo, targetNewspeedNo, '../report/reportrequest');
    	});
    
    }


    function onEnterCommentWrite() {
        $('.newspeedview_comment_write').off('keyup').on('keyup', function (e) {
            e.stopPropagation();            
            if (e.key == 'Enter') {
            	var newspeedNo = $('.newspeedview_list_select .newspeedNo').val();
            	var content = $(this).val(); 
            	
            	
            	if(!isRightComment(content)) {
            		return;
            	}
            	
            	
               writeNewspeedComment(newspeedNo, 0, content);
               
               $(this).val('');
          	   $(this).text('');
              
            }
        });
    }
    
    function isRightComment(content) {
    	if(content.trim().length < 5) {
    		profileAlert('띄어쓰기를 제외하고 5글자 이상 입력하세요');
    		return false;
    	} else if(content.length < 7) {
    		profileAlert('띄어쓰기 포함 8글자 이상 입력하세요');
    		return false;
    	} else if (content.length > 166) {
    		profileAlert('띄어쓰기 포함 166글자 이하로 입력하세요.');
    		return false;
    	}
    	
    	return true;
    }
    
    function onClickCommentWriteSubmit() {
    	 $('.newspeedview_comment_write_submit').off('click').on('click', function (e) {
 
             	var newspeedNo = $('.newspeedview_list_select .newspeedNo').val();
             	var content = $('.newspeedview_list_select .newspeedview_comment_write').val();
             	
  
              	if(!isRightComment(content)) {
            		return;
            	}
             	
                writeNewspeedComment(newspeedNo, 0, content);
                
                $('.newspeedview_list_select .newspeedview_comment_write').val('');
                $('.newspeedview_list_select .newspeedview_comment_write').text('');

         });
    }
    
    
    function writeNewspeedComment(newspeedNo, rootCommentNo ,commentContent) {
   
        $.ajax({
            url: '../newspeed/newspeedcommentwrite',
            type: "POST",
            data: { "newspeedNo": newspeedNo,
            	     "rootCommentNo": rootCommentNo,
            	     "commentContent": commentContent},
            success: function (data) {
            	reloadCommentList(JSON.parse(data));
            },
            error:function(request,status,error){
            
            }
        });
    }
    
    function onClickNewspeedMediaImage() {
    	$('.newspeedview_media_image').off('click').on('click', function(e){
    		e.stopPropagation();
    		$('.newspeedview_list_select .newspeedview_media_select .balloons').fadeToggle(500);
    	});
    }
    
    function onClickRecomment() {
    	$('.comment_option_recomment').off('click').on('click', function(e){
    		e.stopPropagation();
    		var parents = $(this).parent();
    		
    		if (typeof $('.input_text_recomment').val() != "undefined") {
    			$('.input_text_recomment').remove();
    			return;
    		}
    		
    		$('.input_text_recomment').remove();
  		
    		$(parents).append($('<input/>',{
    			type:'text',
    			class:'input_text_recomment'
    		}));
    		
    		onEnterRecomment(parents);
    		
    	});
    	
    	function onEnterRecomment(parents) {
    		$('.input_text_recomment').off('keyup').on('keyup',function(e){
    			
    			if (e.key == "Enter") {
    				var content = $(this).val();
    				var newspeedNo = $('.newspeedview_list_select .newspeedNo').val();
    				var rootNo = $(parents).parent().children()[3].value;
    				
    		      	if(!isRightComment(content)) {
                		return;
                	}
 
    				
    				onSubmitRecomment(newspeedNo, rootNo, content);
    				
    			}
    		});
    	}
    	
    	function onSubmitRecomment(newspeedNo, rootCommentNo, commentContent) {
            $.ajax({
                url: '../newspeed/newspeedrecomment',
                type: "POST",
                data: { "newspeedNo": newspeedNo,
                	     "rootCommentNo": rootCommentNo,
                	     "commentContent": commentContent},
                success: function (data) {
                	reloadCommentList(JSON.parse(data));
                },
                error:function(request,status,error){
   
                }
            });
    	}
    }
    
    function onClickProfilePhoto() {
    	$('.newspeedview_profilephoto').off('click').on('click',function(e){
    		location.href = '../view/profile?uu=' + $('.newspeedview_list_select .userNo').val();
    	});
    	
    	
    	
    }
    
    function onClickCommentDelete() {
    	$('.comment_option_delete').off('click').on('click',function(e){
    		var commentNo = $(this).parent().parent().children()[3].value;
    		var newspeedNo = $('.newspeedview_list_select .newspeedNo').val();
    		
    		console.log(commentNo + "버노버노댓글버노");
    		
    		 $('body').alertBox({
 		        title: "정말 삭제하시겠습니까?",
 		        lTxt: '확인',
 		        lCallback: function(){
 		        	requestCommentDelete(commentNo, newspeedNo);
 		        
 		        },
 		        rTxt: '취소',
 		        rCallback: function(){
				      
 		        }
 		      });
    				
    	})
    	
    }
    
	function requestCommentDelete(commentNo, newspeedNo) {
		 $.ajax({
	            url: '../newspeed/newspeedcommentdelete',
	            type: "POST",
	            data: { "commentNo": commentNo,
	            	    "newspeedNo" : newspeedNo},
	            success: function (data) {
	                 reloadCommentList(JSON.parse(data));
	            },
	            error:function(request,status,error){
	                
	            }

	 
	        });
	}
	
	function onClickRecommentWriterName() {
		$('.recomment_wrapper > h4').off('click').on('click', function(e){
			location.href = '../view/profile?uu=' + $(this).parent().children()[1].value;
		});
	}
	

 
    
    
    function reloadCommentList(data) {
    	$('.newspeedview_list_select .newspeedview_comment_wrapper > div').remove();

    	
    	for (var i = 0; i < data.length; i++) {	
    		 $('.newspeedview_list_select .newspeedview_comment_wrapper').append($('<div/>', {
    	            html: '<h4>' + data[i].userName + '</h4>&nbsp;' + data[i].commentContent + "  <p class='beforeTime'>" + data[i].beforeTime + "</p>"
    	        }));

    	        $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i +')').append($('<input/>', {
    	            type: 'hidden',
    	            class: 'userNo',
    	            value: data[i].userNo
    	        }));
    	        
    	        $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i +')').append($('<input/>', {
                    type: 'hidden',
                    class: 'commentNo',
                    value: data[i].commentNo
                }));
                

                $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ')').append($('<div/>', {
                    class:'newspeedview_comment_option_wrapper',
                }));
                
                $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ') .newspeedview_comment_option_wrapper').append($('<div/>', {
                    class:'comment_option_recomment',
                	text:'답글'
                }));
                
                
                if(data[i].isMine) {
                	
                	$('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ') .newspeedview_comment_option_wrapper').append($('<div/>', {
                		class:'comment_option_delete',
                		text:'삭제'
                	}));
                }
                
                if (false) {
                $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ') .newspeedview_comment_option_wrapper').append($('<div/>', {
            		class:'comment_option_modify',
            		text:'수정'
            	}));
                }
                
                
                if (typeof data[i].recommentList != "undefined" ) {
                	var nummmm = 0;
                for (var p = data[i].recommentList.length -1; p >= 0 ; p--) {
                	
                	
                    $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ')').append($('<div/>', {
                        class:'recomment_wrapper',
                        html:"<h4>" +  data[i].recommentList[p].userName + "</h4>&nbsp;" + data[i].recommentList[p].commentContent + "  <p class='beforeTime'>" + data[i].recommentList[p].beforeTime + "</p>",
                    }));
                    
                    $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ') .recomment_wrapper:eq('+ (nummmm) + ')').append($('<input/>', {
                        type:'hidden',
                        class:'userNo',
                        value:data[i].recommentList[p].userNo
                    }));
                    
                    $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ') .recomment_wrapper:eq('+ (nummmm) + ')').append($('<input/>', {
                        type:'hidden',
                        class:'commentNo',
                        value:data[i].recommentList[p].commentNo
                    }));
                    
                    if (data[i].recommentList[p].isMine){
                    	
                    	 $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ') .recomment_wrapper:eq('+ (nummmm) + ')').append($('<div/>', {
                             class:'recomment_option_wrapper',
                             style:'display:block;',
                             value:data[i].recommentList[p].commentNo
                         }));
                    	 
                    	 $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ') .recomment_wrapper:eq('+ (nummmm) + ') .recomment_option_wrapper').css('display','block');
                    	 
                    	 $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div:eq(' + i + ') .recomment_wrapper:eq('+ (nummmm) + ') .recomment_option_wrapper').append($('<div/>', {
                             class:'comment_option_delete',
                             text:'삭제'
                         }));
                    }
              	
              	
                    nummmm = (nummmm + 1);
                	}
                }
                
                $('.newspeedview_list_select .newspeedview_all_content_wrapper .newspeedview_comment_wrapper > div > div > *').css({
                	'display':'inline-block'
                });
    	}
    	onBindEvent();
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
    	
        $.ajax({
            url: '../newspeed/newspeedview',
            type: "POST",
            data: { "newspeedNo": newspeedNo },
            success: function (data) {

               showNewspeedview(data);
            },
            error:function(request,status,error){
                
            }

 
        });
    }
    
    function requestNewspeedInfoAfter(newspeedNo) {
        $.ajax({
            url: '../newspeed/newspeedview',
            type: "POST",
            data: { "newspeedNo": newspeedNo },
            success: function (data) {
            	appendNewspeedElement(JSON.parse(data),$('#newspeedview_list_wrapper').children().length - 1,function(){
            		moveRightNewspeed();
            	});       	
            }
        });
    }
    
    
    function requestNewspeedInfoBefore(newspeedNo) {
    	
        $.ajax({
            url: '../newspeed/newspeedview',
            type: "POST",
            data: { "newspeedNo": newspeedNo },
            success: function (data) {

            	appendNewspeedElementBefore(JSON.parse(data),0,function(){
            		moveLeftNewspeed();
            	});       	
            }
        });
    }
    
    
    
    
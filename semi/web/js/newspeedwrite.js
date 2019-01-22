
            var allFiles = [];
            var allFileNames = [];
            var lastImg;
            var lastIndex;
            var uploadFiless;
            var uploadIndex;
            var fileLength;
            var realFiles;
            
            var maxFileListLength;

            function uploadFiles() {
            	uploadFiless = $('#input_Files')[0].files;
                uploadIndex = 0;
                fileLength = uploadFiless.length;
                
                if (fileLength == 0) {
                	return;
                }

                if(!checkFile()) {
                    alert('올릴 수 없는 파일이 존재합니다. 다시 올려주세요');
                    return;
                }
                
   
                readFiles();     
                
                function readFiles() {
                    var fileReader = new FileReader();

                    fileReader.onload = function(e) {

                        console.log('파일을 체크해요!');
                        var fileExt = getFileExt(uploadFiless[uploadIndex].name);
                        var mediaType = 0;
                        
                        if (isVideo(fileExt)) {
                            mediaType = 1;
                        }
                        
                    	allFiles[allFiles.length] = uploadFiless[uploadIndex];
                        setElement(e, mediaType);
                        
                        uploadIndex++;
                        if (uploadIndex < fileLength) {
               	
                        	readFiles();
                        } else if (uploadIndex == fileLength) {
                        	$('#input_Files').val("");
                        	return;
                        }            
                    }
                    
                    fileReader.readAsDataURL(uploadFiless[uploadIndex]);
                }

                function checkFile() {
                    for (var i = 0; i < uploadFiles.length; i++) {
                        var file = uploadFiles[i];
                        var fileExt = getFileExt(file.name);
                        
                        console.log('파일을 체크해요!');
                        
                        if (!isImage(fileExt) && ! isVideo(fileExt)) {
                            return false;
                        }
                    }

                    return true;
                }

                function getFileExt(fileName) {
                    var extIndex = fileName.lastIndexOf(".");

                    return fileName.substring(extIndex,fileName.length);
                }

                function isImage(ext) {
                    return ext == ".jpg" || ext == ".png" || ext == ".bmp";
                }
                function isVideo(ext) {
                    return ext == ".mp4" || ext == ".avi" || ext == ".mov" || ext == ".mkv" || ext == ".ts";
                }


                function setElement(e, mediaType) {
                	console.log("빠아앙" + e.target + "뿌아앙")
                	
                    var result = e.target.result;
                    var root = document.getElementById('media_preview');
                    var thumbnailWrapper = document.createElement('div');
                    var thumbnailMediaWrapper = document.createElement('div');
                    var thumbnailMedia = document.createElement('img');
                    var thumbnailCloseImgWrapper = document.createElement('div');
                    var thumbnailCloseImg = document.createElement('img');
                    var thumbnailEditImgWrapper = document.createElement('div');
                    var thumbnailEditImg = document.createElement('img');

                    thumbnailEditImg.setAttribute('src', '../img/postwrite/edit-icon.png');
                    thumbnailEditImg.setAttribute('class', 'thumbnail_edit_img');
                    thumbnailEditImgWrapper.setAttribute('class', 'thumbnail_edit_img_wrapper');
                    thumbnailCloseImg.setAttribute('class', 'thumbnail_img_close');
                    thumbnailCloseImg.setAttribute('src', '../img/postwrite/close-icon.png')
                    thumbnailCloseImgWrapper.setAttribute('class', 'thumbnail_img_close_wrapper');
                    thumbnailWrapper.setAttribute('class', 'thumbnail_wrapper');
                    thumbnailMediaWrapper.setAttribute('class', 'thumbnail_media_wrapper');
                    if (mediaType == 0) {
                    	thumbnailMedia.setAttribute('src', result);
                    	$(thumbnailMediaWrapper).css('background-image', "url(" + result + ")");
                    } else {
                    	thumbnailMedia.setAttribute('src', "/PostingEx/img/postwrite/videopreview.png");
                    	$(thumbnailMediaWrapper).css('background-image', "url(/PostingEx/img/postwrite/videopreview.png)");
                    }

                    

                    thumbnailEditImgWrapper.appendChild(thumbnailEditImg);
                    thumbnailCloseImgWrapper.appendChild(thumbnailCloseImg);
                    thumbnailWrapper.appendChild(thumbnailMediaWrapper);
                    thumbnailWrapper.appendChild(thumbnailCloseImgWrapper);
                    thumbnailWrapper.appendChild(thumbnailEditImgWrapper);
                    root.appendChild(thumbnailWrapper);

                    $(thumbnailCloseImg).on('click', function () {
                        var index = $(thumbnailWrapper).prevAll().length;
                        allFiles.splice(index - 1, 1);

                        root.removeChild(thumbnailWrapper);
                        removeTagList(index);
                    });

                    $(thumbnailEditImg).on('click', function () {
                    	if (mediaType == 1) {
                    		alert('동영상 편집은 지원하지 않습니다');
                    		return;
                    	}
                    	
                        showMedaiEdit();
                    });


                    $(thumbnailMediaWrapper).css('display', 'block');
                    
                    resizing();
                    resizeLi();
                    addTagList(function(){
                        if (uploadIndex < fileLength) {
                           	
                        	readFiles();
                        } else if (uploadIndex == fileLength) {
                        	$('#input_Files').val("");
                        	return;
                        }   
                    	
                    });
                }


                function addTagList() {
                    $('#media_tag_nav').append($('<div/>', {
                        class: 'tag_list_wrapper'
                    }));

                }

                function removeTagList(index) {
                    $('.tag_list_wrapper:eq(' + (index - 1) + ')').remove();
                }
            }



            function resizing() {
                var height = $('.thumbnail_wrapper').height();

                $('.thumbnail_wrapper').css('width', height);
            }

            $(function () {
                resizing();
                relocateCloseIcon();
                onCheckEffectRadio();
                onCheckFilterRadio();
                setCanvasCutTool();
                onDragCutTool();
                onClickPostingWhole();
                onClickFilterCanvas();
                onClickSearchList();
                onInputPostingContent();
                onChangeTagText();
                
                $('#posting_wrap').on('scroll touchmove mousewheel',function(e){
                	e.preventDefault();
                	e.stopPropagation();
                })
            });


            function onCheckEffectRadio() {
                $('.effect_radio').on('change', function () {
                    var index = $(this).parent().prevAll().length;
                    var mediaIndex = $('#media_index').val();

                    console.log(index);
                    $('.effect_radio').siblings().children().css('color', 'gray');
                    $(this).siblings().children().css('color', 'black');

                    $('#media_edit nav').css('display', 'none');
                    $('#media_effect_nav').css('display', 'block');
                    $('#media_edit nav:nth-of-type(' + (index + 2) + ')').css('display', 'block');
                    $('#cut_tool').css('display', 'none');
                    $('#tag_box').css('display', 'none');
                    $('#tag_wrapper').css('display', 'none');
                    $('#filter_canvas').css('cursor', 'default');

                    if (index == 1) {
                        $('#cut_tool').css('display', 'inline-block');
                    }

                });
                $('#cut_tool').css('display', 'none');
                $('.effect_radio')[0].checked = "checked";

            }

            function onCheckFilterRadio() {
                $('.filter_radio').on('change', function () {
                    $('.filter_radio').siblings().removeClass();
                    $('.filter_radio').siblings().addClass('media_filter_preview');

                    $(this).siblings().removeClass();
                    $(this).siblings().addClass('media_filter_preview_selected');

                    drawImage(lastImg);
                });
            }

            function onDragCutTool() {
                var leftTop = $('#left_top_edge');
                var leftBottom = $('#left_bottom_edge');
                var rightTop = $('#right_top_edge');
                var rightBottom = $('#right_bottom_edge');
                var parent = $('#cut_tool');
                var parentOffset = $(parent).offset();
                var canvasWrapper = $('#canvas_Wrapper');
                var filterCanvas = $('#filter_canvas');
                var cWidth = $(filterCanvas).css('width').replace("px", "");
                var cHeight = $(filterCanvas).css('height').replace("px", "");
                var cWWidth = $(canvasWrapper).css('width').replace("px", "");
                var leftEmpty;

                var isDragAble = false;
                var isResizing = false;

                var prevX = 0;
                var prevY = 0;
                var x = 0;
                var y = 0;
                var xSub = 0;
                var ySub = 0;

                $(parent).on('dragstart', function (e) {
                    prevX = e.pageX;
                    prevY = e.pageY;
                    cWidth = $(filterCanvas).css('width').replace("px", "");
                    cHeight = (filterCanvas).css('height').replace("px", "");
                    cWWidth = $(canvasWrapper).css('width').replace("px", "");
                    leftEmpty = (cWWidth - cWidth) / 2;
                });

                $(parent).on('drag', function (e) {
                    if (e.pageX > 0)
                        x = e.pageX;
                    if (e.pageY > 0)
                        y = e.pageY;
                    xSub = x - prevX;
                    ySub = y - prevY;
                    prevX = x;
                    prevY = y;

                    if (Math.abs(xSub) > 150 || Math.abs(ySub) > 150) {
                        return;
                    }

                    var width = $(this).width();
                    var height = $(this).height();
                    var top = Number($(parent).css('top').replace("px", "")) + ySub;
                    var left = Number($(parent).css('left').replace("px", "")) + xSub;

                    if (left < leftEmpty) {
                        console.log('넌왜터질려함');
                        left = left - xSub;
                    }

                    if (top < 0) {
                        top = top - ySub;
                    }

                    if (top + height > cHeight) {
                        top = top - ySub;
                    }

                    if (left + width - leftEmpty > cWidth) {
                        left = left - xSub;
                    }

                    $(parent).css('top', top);
                    $(parent).css('left', left);

                });

                $(parent).on('dragend', function (e) {
                    if (e.pageX > 0)
                        x = e.pageX;
                    if (e.pageY > 0)
                        y = e.pageY;
                    xSub = x - prevX;
                    ySub = y - prevY;
                    prevX = x;
                    prevY = y;
                    setTagList();
                });


                $('.edge').on('dragstart', function (e) {
                    e.stopPropagation();
                    prevX = e.pageX;
                    prevY = e.pageY;
                    cWidth = $(filterCanvas).css('width').replace("px", "");
                    cHeight = (filterCanvas).css('height').replace("px", "");
                    cWWidth = $(canvasWrapper).css('width').replace("px", "");
                    leftEmpty = (cWWidth - cWidth) / 2;
                });

                $('.edge').on('drag', function (e) {
                    e.stopPropagation();

                    if (e.pageX > 0)
                        x = e.pageX;
                    if (e.pageY > 0)
                        y = e.pageY;
                    xSub = x - prevX;
                    ySub = y - prevY;
                    prevX = x;
                    prevY = y;
                });

                $('.edge').on('dragend', function (e) {
                    e.stopPropagation();
                    console.log(x + '드래그끝나용..' + y)
                    if (e.pageX > 0)
                        x = e.pageX;
                    if (e.pageY > 0)
                        y = e.pageY;
                    xSub = x - prevX;
                    ySub = y - prevY;
                    prevX = x;
                    prevY = y;
                    setTagList();
                });

                $(leftTop).on('drag', function (e) {
                    e.stopPropagation();
                    if (Math.abs(xSub) < 150 && Math.abs(ySub) < 150) {
                        onDragLeftTop(e);
                    }
                });

                $(leftTop).on('dragend', function (e) {
                    e.stopPropagation();
                    onDragLeftTop(e);
                    isDragAble = false;
                    setTagList();
                });

                $(leftBottom).on('drag', function (e) {
                    e.stopPropagation();
                    if (Math.abs(xSub) < 150 && Math.abs(ySub) < 150) {
                        onDragLeftBottom(e)
                    }
                })

                $(leftBottom).on('dragend', function (e) {
                    isDragAble = false;
                    e.stopPropagation();
                    onDragLeftBottom(e);
                    setTagList();
                });

                $(rightTop).on('drag', function (e) {
                    e.stopPropagation();
                    if (Math.abs(xSub) < 150 && Math.abs(ySub) < 150) {
                        onDragRightTop(e)
                    }
                })

                $(rightTop).on('dragend', function (e) {
                    isDragAble = false;
                    e.stopPropagation();
                    onDragRightTop(e);
                    setTagList();
                });

                $(rightBottom).on('drag', function (e) {
                    e.stopPropagation();
                    if (Math.abs(xSub) < 150 && Math.abs(ySub) < 150) {
                        onDragRightBottom(e)
                    }
                })

                $(rightBottom).on('dragend', function (e) {
                    isDragAble = false;
                    e.stopPropagation();
                    onDragRightBottom(e);
                    setTagList();
                });

                function onDragLeftTop(e) {
                    var top = Number($(parent).css('top').replace("px", "")) + ySub;
                    var left = Number($(parent).css('left').replace("px", "")) + xSub;

                    if (top < 0) {
                        top = top - ySub;
                        ySub = 0;
                    }

                    if (left < leftEmpty) {
                        left = left - xSub;
                        xSub = 0;
                    }

                    $(parent).css('height', $(parent).height() - ySub);
                    $(parent).css('top', top);

                    $(parent).css('width', $(parent).width() - xSub);
                    $(parent).css('left', left);


                    setCanvasCutToolEdge();
                }

                function onDragLeftBottom(e) {
                    var top = Number($(parent).css('top').replace("px", ""));
                    var left = Number($(parent).css('left').replace("px", "")) + xSub;
                    var width = $(parent).width() - xSub;
                    var height = $(parent).height() + ySub;


                    if (top + height > cHeight) {
                        height = height - ySub;
                    }
                    if (left < leftEmpty) {
                        left = left - xSub;
                        width = width + xSub;
                    }

                    $(parent).css('height', height);
                    $(parent).css('top', top);

                    $(parent).css('width', width);
                    $(parent).css('left', left);

                    setCanvasCutToolEdge();
                }

                function onDragRightTop(e) {
                    var top = Number($(parent).css('top').replace("px", "")) + ySub;
                    var left = Number($(parent).css('left').replace("px", ""));
                    var height = $(parent).height() - ySub;
                    var width = $(parent).width() + xSub;

                    if (width + left + leftEmpty > cWWidth) {
                        width = width - xSub;
                    }

                    if (top < 0) {
                        height = height + ySub;
                        top = top - ySub;
                    }

                    $(parent).css('height', height);
                    $(parent).css('top', top);

                    $(parent).css('width', width);
                    $(parent).css('left', left);

                    setCanvasCutToolEdge();
                }

                function onDragRightBottom(e) {
                    var top = Number($(parent).css('top').replace("px", ""));
                    var left = Number($(parent).css('left').replace("px", ""));
                    var height = $(parent).height() + ySub;
                    var width = $(parent).width() + xSub;

                    if (width + left + leftEmpty > cWWidth) {
                        width = width - xSub;
                    }

                    if (top + height > cHeight) {
                        height = height - ySub;
                    }

                    $(parent).css('height', height);
                    $(parent).css('top', top);
                    $(parent).css('width', width);
                    $(parent).css('left', left);

                    setCanvasCutToolEdge();
                }


            }

            function onClickFilterCanvas() {
                var filterCanvas = $('#filter_canvas');
                var radios = $('.effect_radio');

                $(filterCanvas).on('click', function (e) {
                    e.stopPropagation();

                    var cutTool = $('#cut_tool');
                    var canvasWrapper = $('#canvas_Wrapper');
                    var canvas = $('#filter_canvas')[0];
                    var left2 = Number($(cutTool).css('left').replace("px", "")) - (canvasWrapper.width() - canvas.width) / 2;
                    var top = Number($(cutTool).css('top').replace("px", ""));
                    var right = left2 + Number($(cutTool).css('width').replace("px", ""));
                    var bottom = top + Number($(cutTool).css('height').replace("px", ""));
                    var x = e.offsetX;
                    var y = e.offsetY;

                    if (radios[2].checked) {
                        if (x < left2 || y < top || y > bottom || x > right) {

                            $('#tag_box').css('display', 'none');
                            $('#tag_wrapper').css('display', 'none');
                            alert('자르기 영엮 밖에는 태그할 수 없습니다.');
                            return;
                        }
                        $('input[id=xPoint]').attr('value', (x / canvas.width));
                        $('input[id=yPoint]').attr('value', (y / canvas.height));

                        $('#filter_canvas').css('left').replace("px", "")
                        var left = (Number($('#canvas_Wrapper').css('width').replace("px", "")) - Number($('#filter_canvas').css('width').replace("px", ""))) / 2

                        $('#tag_box').css('left', e.offsetX - 37.5 + left);
                        $('#tag_box').css('top', e.offsetY - 37.5);
                        $('#tag_wrapper').css('top', e.offsetY + 42.5);
                        $('#tag_wrapper').css('left', e.offsetX - 92.5 + left);
                        $('#tag_box').css('display', 'block');
                        $('#tag_wrapper').css('display', 'block');

                        console.log(e.offsetX + "offset" + e.offsetY);

                    }
                });

                $(filterCanvas).on('mouseenter', function (e) {
                    console.log('들어감');
                    if (radios[2].checked) {
                        console.log('들어감');
                        $(this).css('cursor', 'Crosshair');
                    } else {
                        $('#filter_canvas').css('cursor', 'default');
                    }
                });


            }

            function onClickPostingWhole() {

                $('#media_edit').on('click', function (e) {
                    console.log(this);
                    $('#tag_box').css('display', 'none');
                    $('#tag_wrapper').css('display', 'none');
                    e.stopPropagation();
                });
            }

            function onClickSearchList() {
                $('.search_list').on('click', function (e) {
                    e.stopPropagation();
                    var searchList = this;

                    console.log(searchList);
                    console.log($('.tag_list_wrapper').length + "안녕?");

                    //     <div class="tag_list_wrapper">     
                    //     <p>임태완완완완완</p>
                    //     <input type="hidden" value="01">
                    //     <input type="hidden" value="32">
                    //     <input type="hidden" value="25">
                    //     <img src="close-icon-white.png" class="tag_list_wrapper_close"></img>
                    // </div>

                    /*
                        <div class="tag_list_wrapper" value="" name="">
                            <div class="tag_user_wrapper">   
                              <p>임태완완완완완</p>
                              <input type="hidden" value="01">
                              <input type="hidden" value="32">
                              <input type="hidden" value="25">
                              <img src="close-icon-white.png" class="tag_list_wrapper_close"></img>
                            </div>
                        </div>
        
                    */
                    var mediaIndex = $('#media_index').val();
                    var name;
                    var tagIndex = $(this).prevAll().length + 1;
                    console.log(tagIndex);
                    console.log(mediaIndex + "왜이러세요?");
                    
                    if (isOverlapTagUser(mediaIndex, $('.search_list:eq(' + (tagIndex-1) + ') input[name=user_no]').val())){
                    	alert('이미 태그한 유저입니다');
                    	return;
                    }


                    if (mediaIndex == 0) {
                        $('#media_tag_nav > .tag_list_wrapper:first-child').append($('<div/>', {
                            class: 'tag_user_wrapper',
                            name: 'new'
                        }));

                        name = $('.search_list:nth-child(' + tagIndex + ') .name_wrapper p').text();
                        console.log(name + "야야야");
                    } else {
                        $('#media_tag_nav > .tag_list_wrapper:eq(' + (mediaIndex) + ')').append($('<div/>', {
                            class: 'tag_user_wrapper',
                            name: 'new'
                        }));

                        name = $('.search_list:nth-child(' + tagIndex + ') .name_wrapper p').text();
                        console.log(name + "왜불러");
                    }


                    $('.tag_list_wrapper:eq(' + (mediaIndex) + ') .tag_user_wrapper:last-child').append($('<p/>', {
                        text: name
                    }));

                    $('.tag_list_wrapper:eq(' + (mediaIndex) + ') .tag_user_wrapper:last-child').append($('<input/>', {
                        type: 'hidden',
                        class: 'xPoint',
                        value: $('input[id=xPoint]').val()
                    }));

                    $('.tag_list_wrapper:eq(' + (mediaIndex) + ') .tag_user_wrapper:last-child').append($('<input/>', {
                        type: 'hidden',
                        class: 'yPoint',
                        value: $('input[id=yPoint]').val()
                    }));


                    $('.tag_list_wrapper:eq(' + (mediaIndex) + ') .tag_user_wrapper:last-child').append($('<input/>', {
                        type: 'hidden',
                        class: 'userNo',
                        value: $('.search_list:nth-child(' + tagIndex + ') input[name=user_no]').val()
                    }));

                    $('.tag_list_wrapper:eq(' + (mediaIndex) + ') .tag_user_wrapper:last-child').append($('<img/>', {
                        src: '../img/postwrite/close-icon-white.png',
                        class: 'tag_user_wrapper_close'
                    }));

                    $('.tag_list_wrapper:eq(' + (mediaIndex) + ') .tag_user_wrapper:last-child .tag_user_wrapper_close').on('click', function (e) {
                        e.stopPropagation();

                        var parent = this.parentElement;
                        $(parent).css('display', 'none');
                        $(parent).attr('name', $(parent).attr('name') + '_cancel');

                    });

                });

                $('#search_wrapper').on('click', function (e) {
                    e.stopPropagation();
                });
            }

            function onInputPostingContent() {
                var isHashtag = false;

                $('#posting_content').on('keyup paste', function (e) {
                    console.log($(this).html());

                });

                $('#posting_content').on('mouseclick', function (e) {
                    console.log($(this).text());

                });
            }
            
            function onChangeTagText() {
            	$('#tag_name').on('change keyup paste', function(e){
            		requestFollowList();
            	})
            	
   
            }



            $(window).resize(function () {
                resizing();
                drawImage(lastImg);
                setCanvasCutTool();
                relocateCloseIcon();
                relocateSubEffectTab();
                resizeLi();
                $('#tag_box').css('display', 'none');
                $('#tag_wrapper').css('display', 'none');
            })

            function relocateCloseIcon() {
                console.log(window.outerWidth);
                var rate = $('#media_edit').css('width').replace("px", "") / 30 - 12;
                $('.tag_user_wrapper_close').css('left', rate + "%");
            }

            function relocateSubEffectTab() {
                var left = $('#media_effect_nav').css('width');
                $('.media_effect_sub_tab').css('left', left);
            }

            function resizeLi() {
                var width = $('#media_filter_nav li').css('width').replace("px", "") / 2.2;
                $('#media_filter_nav li').css('height', width);
            }


            function showPostingWhole() {
                if (!isChrome()) {
                    if (!confirm('HTML5를 지원하지 않는 브라우저에서는 정상적으로 동작하지 않을 수 있습니다. 최신 버전의 구글 크롬을 이용하시면 원활한 사용이 가능합니다. 현재 브라우저에서 계속하시겠습니까?')) {
                        return;
                    }
                }

                var modal = $('#posting_wrap');
                $(modal).fadeIn('slow');
                

                console.log($(modal).parent());
                resizing();
                
            }

            function hidePostingWhole() {
                hideMediaEdit();

                var modal = $('#posting_wrap');
                var mediaPreview = $('#media_preview');
                (modal).parent().css('background-color', 'white');
                $(modal).fadeOut('slow');
                
                removePostingData();
            }
            
            function removePostingData() {
            	allFiles = [];
            	var thumbnailWrapper = $('#media_preview').children();
            	
            	for (var i = 1; i < thumbnailWrapper.length; i++) {
            		console.log(thumbnailWrapper[i] + '뭐하냐??');
            		$(thumbnailWrapper[i]).remove();
            	}
            	
            	$('#media_tag_nav > *').remove();
            	$('#list_wrapper > *').remove();
            	$('#posting_content').text('');
            }

            function showMedaiEdit() {
                if (!isChrome()) {
                    alert('이미지 편집은 크롬 브라우저에서만 가능합니다.');
                    return;
                }

                var canvas = $('#filter_canvas')[0];
                var ctx = canvas.getContext('2d');
                var modal = $('#media_edit');
                var index = $(event.target).parent().parent().prevAll().length - 1;

                $('#media_index').val(index);
                lastIndex = index;

                ctx.clearRect(0, 0, canvas.width, canvas.height);
                $(modal).fadeIn('slow');

                $('.effect_radio').siblings().children().css('color', 'gray');
                $('.filter_radio').siblings().children().css('border', '4px solid #F6F6F6');
                $('.filter_radio:first').siblings().children().css('border', '4px solid black');
                $('.effect_radio:first').siblings().children().css('color', 'black');
                $('.media_effect_sub_tab').css('display', 'none');
                $('#media_filter_nav').css('display', 'block');
                $('#cut_tool').css('display', 'none');
                $('#tag_box').css('display', 'none');
                $('#tag_wrapper').css('display', 'none');
                $('.filter_radio').siblings().removeClass();
                $('.filter_radio').siblings().addClass('media_filter_preview');
                $('.filter_radio:first').siblings().removeClass();
                $('.filter_radio:first').siblings().addClass('media_filter_preview_selected');
                $('.tag_list_wrapper').css('display', 'none');
                $('.tag_user_wrapper').css('display', 'block');


                if (index == 0) {
                    $('.tag_list_wrapper:first').css('display', 'block');
                    console.log("방가워");
                } else {
                    $('.tag_list_wrapper:eq(' + (index) + ')').css('display', 'block');
                    console.log("나두");
                }

                imageRoad(index);

                var filterRadios = $('.filter_radio');
                var effectRadios = $('.effect_radio');

                $(filterRadios[0]).prop('checked', true);
                $(effectRadios[0]).prop('checked', true);

                setCanvasCutTool();
                resizeLi();

            }

            function imageRoad(index) {
                var file = allFiles[index];
                var reader = new FileReader();

                reader.readAsDataURL(file);

                reader.onload = function (e) {
                    var image = new Image();
                    lastImg = image;
                    image.src = e.target.result;

                    image.onload = function (e) {
                        drawImage(image);
                        drawEffectThumbnail(image);
                        drawOriginalSizeCanvas(image);
                        setFilterOriginalSizeCanvas();
                        setCanvasCutTool();
                    }
                }
            }

            function drawImage(image, index) {
                var maxWidth = $('#canvas_Wrapper').width();
                var maxHeight = $('#canvas_Wrapper').height();
                var canvas = $('#filter_canvas')[0];
                var radios = $('.filter_radio');

                if (typeof image != "undefined")
                    setImageRate(image, maxWidth, maxHeight, canvas);

                if (maxHeight < canvas.height) {
                    maxHeight = canvas.height;
                }

                if (maxWidth < canvas.width) {
                    maxWidth = canvas.width;
                }


                if (radios[1].checked) {
                    setGrayscale(canvas.getContext('2d'), maxWidth, maxHeight);
                } else if (radios[2].checked) {
                    setBrightness(canvas.getContext('2d'), maxWidth, maxHeight);
                }
            }

            function setImageRate(image, maxWidth, maxHeight, canvas) {
                var imageWidth = image.width;
                var imageHeight = image.height;
                var ctx = canvas.getContext('2d');
                var rate = maxWidth / maxHeight;
                var imgRate = imageWidth / imageHeight;

                if (imgRate > 1) {
                    if (maxHeight > imageHeight && maxWidth > imageWidth) {
                        maxWidth = imageWidth;
                        maxHeight = imageHeight;
                    }

                    for (var i = maxWidth; rate > imgRate; i--) {
                        rate = i / maxHeight;
                        maxWidth = i;
                    }

                    for (var i = maxHeight; rate < imgRate; i--) {
                        rate = maxWidth / i;
                        maxHeight = i;
                    }

                } else if (imgRate < 1) {
                    if (maxHeight > imageHeight) {
                        maxHeight = imageHeight;
                        maxWidth = imageWidth;
                    }


                    for (var i = maxHeight; rate < imgRate; i--) {
                        rate = maxWidth / i;
                        maxHeight = i;
                    }

                    for (var i = maxWidth; rate > imgRate; i--) {
                        rate = i / maxHeight;
                        maxWidth = i;
                    }
                } else {
                    if (imageHeight > maxHeight) {
                        maxWidth = maxHeight;
                    } else {
                        maxWidth = imageWidth;
                        maxHeight = imageHeight;
                    }
                }

                $(canvas).attr('width', maxWidth);
                $(canvas).attr('height', maxHeight);
                ctx.drawImage(image, 0, 0, maxWidth, maxHeight);
            }

            function setCanvasCutTool() {
                var canvasWrapper = $('#canvas_Wrapper');
                var canvas = $('#filter_canvas')[0];
                var cutTool = $('#cut_tool');
                var left = Number($(canvas).css('left').replace("px", ""));

                console.log(canvasWrapper.width() + "이거왜케큼?" + canvas.width);

                $(cutTool).css('width', canvas.width);
                $(cutTool).css('height', canvas.height);
                $(cutTool).css('left', left + (canvasWrapper.width() - canvas.width) / 2);
                $(cutTool).css('top', $(canvas).css('top'));

                console.log($(cutTool).css('left') + "뭐가문제임??");
                setCanvasCutToolEdge();
            }

            function setCanvasCutToolEdge() {
                var leftTop = $('#left_top_edge');
                var leftBottom = $('#left_bottom_edge');
                var rightTop = $('#right_top_edge');
                var rightBottom = $('#right_bottom_edge');
                var cutTool = $('#cut_tool');
                var edgeHalfSize = leftTop.width() / 2;

                $(leftTop).css('left', -edgeHalfSize);
                $(leftTop).css('top', -edgeHalfSize);
                $(leftBottom).css('left', -edgeHalfSize);
                $(leftBottom).css('top', -edgeHalfSize + $(cutTool).height());
                $(rightTop).css('left', -edgeHalfSize + $(cutTool).width());
                $(rightTop).css('top', -edgeHalfSize);
                $(rightBottom).css('left', -edgeHalfSize + $(cutTool).width());
                $(rightBottom).css('top', -edgeHalfSize + $(cutTool).height());
            }

            function drawEffectThumbnail(image) {
                var normalCanvas = $('#normal')[0];
                var grayscaleCanvas = $('#grayscale')[0];
                var brightnessCanvas = $('#brightness')[0];
                var normalContext = normalCanvas.getContext('2d');
                var grayscaleContext = grayscaleCanvas.getContext('2d');
                var brightnessContext = brightnessCanvas.getContext('2d');
                var pixels;
                var cwidth = image.width;
                var cheight = image.height;

                normalCanvas.width = cwidth;
                normalCanvas.height = cheight;
                grayscaleCanvas.width = cwidth;
                grayscaleCanvas.height = cheight;
                brightnessCanvas.width = cwidth;
                brightnessCanvas.height = cheight;

                normalContext.clearRect(0, 0, normalCanvas.width, normalCanvas.height);
                grayscaleContext.clearRect(0, 0, grayscaleCanvas.width, grayscaleCanvas.height);
                brightnessContext.clearRect(0, 0, brightnessCanvas.width, brightnessCanvas.height);
                normalContext.drawImage(image, 0, 0, cwidth, cheight);
                grayscaleContext.drawImage(image, 0, 0, cwidth, cheight);
                brightnessContext.drawImage(image, 0, 0, cwidth, cheight);

                setGrayscale(grayscaleContext, cwidth, cheight);
                setBrightness(brightnessContext, cwidth, cheight);


                $('#radio_effect_normal').prev().css('background-image', "url(" + normalCanvas.toDataURL() + ")");
                $('#radio_effect_grayscale').prev().css('background-image', "url(" + grayscaleCanvas.toDataURL() + ")");
                $('#radio_effect_brightness').prev().css('background-image', "url(" + brightnessCanvas.toDataURL() + ")");

            }

            function setGrayscale(grayscaleContext, width, height) {
                var pixels = grayscaleContext.getImageData(0, 0, width, height);
                var data = pixels.data;

                for (var i = 0; i < data.length; i += 4) {
                    var r = data[i];
                    var g = data[i + 1];
                    var b = data[i + 2];

                    var sum = 0.2126 * r + 0.7152 * g + 0.0722 * b;
                    data[i] = data[i + 1] = data[i + 2] = sum;
                }

                grayscaleContext.putImageData(pixels, 0, 0);
            }

            function setBrightness(brightnessContext, width, height) {
                var pixels = brightnessContext.getImageData(0, 0, width, height);
                var data = pixels.data;

                for (var i = 0; i < data.length; i += 4) {
                    data[i] += 30;
                    data[i + 1] += 30;
                    data[i + 2] += 30;
                }

                brightnessContext.putImageData(pixels, 0, 0);
            }


            function drawOriginalSizeCanvas(image) {
                var canvas = $('#original_size_canvas')[0];
                var ctx = canvas.getContext('2d');
                var width = image.width;
                var height = image.height;

                canvas.width = width;
                canvas.height = height;

                ctx.drawImage(image, 0, 0, width, height);
            }

            function setFilterOriginalSizeCanvas() {
                var canvas = $('#original_size_canvas')[0];
                var ctx = canvas.getContext('2d');
                var radio = $('.filter_radio');

                if (radio[1].checked) {
                    setGrayscale(ctx, canvas.width, canvas.height);
                } else if (radio[2].checked) {
                    setBrightness(ctx, canvas.width, canvas.height);
                }
            }

            function cutOriginalSizeCanvas() {
                var canvas = $('#original_size_canvas')[0];
                var ctx;
                var filterCanvas = $('#filter_canvas')[0];
                var filterCanvasWrapper = filterCanvas.parentElement;
                var cWidth = lastImg.width;
                var cHeight = lastImg.height;
                var fWidth = $(filterCanvas).css('width').replace("px", "");
                var rate = cWidth / fWidth;

                var wrapperWidth = $('#canvas_Wrapper').css('width').replace("px", "");
                var canvasWidth = $('#filter_canvas').css('width').replace("px", "");
                var left2 = (wrapperWidth - canvasWidth) / 2
                var left = Number($('#cut_tool').css('left').replace("px", "") - left2) * rate;
                var top = Number($('#cut_tool').css('top').replace("px", "")) * rate;
                var width = (Number($('#cut_tool').css('width').replace("px", "")) * rate);
                var height = (Number($('#cut_tool').css('height').replace("px", "")) * rate);

                console.log(cWidth + " 안녕하세용");
                console.log(left + "방가방가2");

                ctx = canvas.getContext('2d');
                canvas.width = width;
                canvas.height = height;
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(lastImg, left, top, width, height, 0, 0, width, height);

                setFilterOriginalSizeCanvas();
                setCanvasCutTool();
                relocateTagPoint();

                function relocateTagPoint() {
                	var x = $('input[class=xPoint]');
                	var y = $('input[class=yPoint]');
                	for (var i = 0; i < x.length; i++) {
                		$(x[i]).val(($(x[i]).val() * cWidth - left) / width);
                		$(y[i]).val(($(y[i]).val() * cHeight - top) / height);
 
                	
                	}
                }
            }

            function getBlobOriginalCanvas() {
                var canvas = $('#original_size_canvas')[0];
                var ctx = canvas.getContext('2d');
                var file;

                file = canvas.toBlob(function (blob) {
                       allFiles[lastIndex] = blob;
                       allFiles[lastIndex].name = "반가워.png";

                       hideMediaEdit();
                }, 'image/png');
                
            }

            function getDataURLOriginalCanvas() {
                var canvas = $('#original_size_canvas')[0];

                return canvas.toDataURL();
            }

            function confirmMediaEdit() {
                cutOriginalSizeCanvas();
                var dataURL = getDataURLOriginalCanvas();
                var thumbnailWrapper = $('.thumbnail_wrapper').siblings()[lastIndex + 1];
                var thumbnailMediaWrapper = thumbnailWrapper.children[0];



                saveTagList($('#media_index').val());

                $(thumbnailMediaWrapper).css('background-image', "url(" + dataURL + ")");
                
                getBlobOriginalCanvas();
            }


            function hideMediaEdit() {
                var modal = $('#media_edit');

                $(modal).fadeOut('slow');

                lastIndex = 0;
            }

            function cancelMediaEdit() {
                var mediaIndex = $('#media_index').val();
                console.log(mediaIndex + "닫는당!!")
                cancelTagList(mediaIndex);

                hideMediaEdit();
            }

            function setTagList() {
                var mediaIndex = $('#media_index').val();
                var cutTool = $('#cut_tool');
                var canvasWrapper = $('#canvas_Wrapper');
                var canvas = $('#filter_canvas')[0];
                var left = Number($(cutTool).css('left').replace("px", "")) - (canvasWrapper.width() - canvas.width) / 2;
                var top = Number($(cutTool).css('top').replace("px", ""));
                var right = left + Number($(cutTool).css('width').replace("px", ""));
                var bottom = top + Number($(cutTool).css('height').replace("px", ""));
                var tagList;

                if (mediaIndex == 0) {
                    tagList = $('#media_tag_nav > .tag_list_wrapper:first-child').children();
                } else {
                    tagList = $('#media_tag_nav > .tag_list_wrapper:nth-of-type(' + (mediaIndex) + ')').children();
                }

                for (var i = 0; i < tagList.length; i++) {
                    var tag = tagList[i];
                    var tagChildren = $(tag).children();
                    var x = tagChildren[1].value * canvas.width;
                    var y = tagChildren[2].value * canvas.height;

                    if (x < left || y < top || y > bottom || x > right) {
                        console.log(tag);
                        var tagValue = $(tag).attr('name');

                        if (tagValue.indexOf('cancel') == -1) {
                            console.log('님??');
                            $(tag).attr('name', tagValue + '_cancel');
                            $(tag).css('display', 'none');

                            console.log($(tag).attr('name'));
                        }
                    }
                }
            }


            function saveTagList(mediaIndex) {
                if (mediaIndex == 0) {
                    $('#media_tag_nav > .tag_list_wrapper:first-child > .tag_user_wrapper[name=new]').attr('name', 'old');
                    $('#media_tag_nav > .tag_list_wrapper:first-child > .tag_user_wrapper[name=new_cancel]').remove();
                    $('#media_tag_nav > .tag_list_wrapper:first-child > .tag_user_wrapper[name=old_cancel]').remove();
                } else {
                    $('#media_tag_nav > div:nth-of-type(' + (mediaIndex) + ') > .tag_user_wrapper[name=new]').attr('name', 'old');
                    $('#media_tag_nav > div:nth-of-type(' + (mediaIndex) + ') > .tag_user_wrapper[name=new_cancel]').remove();
                    $('#media_tag_nav > div:nth-of-type(' + (mediaIndex) + ') > .tag_user_wrapper[name=old_cancel]').remove();
                }
            }

            function cancelTagList(mediaIndex) {
                if (mediaIndex == 0) {
                    $('#media_tag_nav > .tag_list_wrapper:first-child > .tag_user_wrapper[name=old_cancel]').attr('name', 'old');
                    $('#media_tag_nav > .tag_list_wrapper:first-child > .tag_user_wrapper[name=new]').remove();
                    $('#media_tag_nav > .tag_list_wrapper:first-child > .tag_user_wrapper[name=new_cancel]').remove();
                } else {
                    $('#media_tag_nav > div:nth-of-type(' + (mediaIndex) + ') > .tag_user_wrapper[name=old_cancel]').attr('name', 'old')
                    $('#media_tag_nav > div:nth-of-type(' + (mediaIndex) + ') > .tag_user_wrapper[name=new]').remove();
                    $('#media_tag_nav > div:nth-of-type(' + (mediaIndex) + ') > .tag_user_wrapper[name=new_cancel]').remove();

                }
            }

            function isChrome() {
                var agt = navigator.userAgent.toLowerCase();

                return (agt.indexOf("chrome") != -1 && agt.indexOf("edge") == -1);
            }

            function removePostingContentInnerTag() {

            }
            
            function submitPosting() {
            	console.log('?????');
            	
            	if(!checkValidate()) {
            		 console.log('??');
            		return;
            	}
            	
            	postFileData();
            }
            
            function checkValidate() {
            	if (allFiles.length < 1) {
            		alert('사진이나 영상을 하나 이상 업로드하세요.');
            		console.log('실행되나궁금');
            		return false;
            	} else if ($('#posting_content').text().length < 5) {
            		alert('최소 5글자 이상 입력하세요.');
            		return false;
            	}
            	
            	return true;
            }

            function addFollowList(data) {
            	
            	$('#list_wrapper > *').remove();
            	
            	for (var i = 0; i < data.length; i++) {
            		$('#list_wrapper').append($('<div/>', {
            			class:'search_list'
            		}));
            		
            		$('#list_wrapper .search_list:eq(' + i + ')').append($('<div/>', {
            			class:'profile_photo',
            			backgroundImage:'url(../img' + data[i].profilePhoto  + ')'
            		}));
            		
            		$('#list_wrapper .search_list:eq(' + i + ')').append($('<div/>', {
            			class:'name_wrapper'
            		}));
            		
            		$('#list_wrapper .search_list:eq(' + i + ') .name_wrapper').append($('<p/>', {
            			text:data[i].name,
            			html:data[i].name,
            			value:data[i].name,
            			innerHTML:data[i].name
            		}));
            		
            		$('#list_wrapper .search_list:eq(' + i + ')').append($('<input/>', {
            			type:'hidden',
            			name:'user_no',
            			value:data[i].userNo
            		}));
            	}
            	
            	onClickSearchList();

            }
            
            function isOverlapTagUser(mediaIndex, userNo) {
           
            	var tagListWrapper = $('#media_tag_nav .tag_list_wrapper:eq(' + mediaIndex +')');
            	var tagUserWrappers = $('#media_tag_nav .tag_list_wrapper:eq(' + mediaIndex +') .tag_user_wrapper .userNo');
            	
            	console.log(tagUserWrappers);
            	console.log(tagUserWrappers.length);
            	
            	for (var i = 0; i < tagUserWrappers.length; i++) {
            		var tagUserWrapper = $('#media_tag_nav .tag_list_wrapper:eq(' + mediaIndex +') .tag_user_wrapper:eq(' + i + ')');
            		var tagName = $(tagUserWrapper).attr('name');
            		var vall = $(tagUserWrappers[i]).val();
            		
            		console.log(userNo + '버노버노' + vall);
            		console.log(tagUserWrapper)
            		console.log(tagName + "뭐니?");
            		
            		if (vall == userNo && tagName != 'old_cancel' && tagName != 'new_cancel') {
            			return true;
            		}
            	}
            	
            	
            	return false;
            }
            



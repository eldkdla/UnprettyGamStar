/**
 * 
 */

    function requestNewspeedInfo(path, newspeedNo) {
        $.ajax({
            url: path + '/newspeed/newspeedview',
            type: "POST",
            data: { "newspeedNo": newspeedNo },
            success: function () {
               console.log('성공했습니다..')
            }
        });
    }
/**
 * 
 */

    function requestNewspeedInfo(path, newspeedNo) {
        $.ajax({
            url: path + '/newspeed/newspeedview',
            type: "POST",
            data: { "newspeedNo": newspeedNo },
            success: function (data) {
               console.log(data);
            }
        });
    }
var PiuBella = function(conf) {
};

/**
 */
PiuBella.prototype.loadNotifications = function(selector) {
    $.ajax('/notification', {
        type: 'GET',
        data: {
            access_token: gapi.auth.getToken().access_token
        },
        success: function(data){
            notifData = data;
            $(selector).html(
            $("#notifications-options").render(data.data)
        );
        }
    });
};

/**
 */
PiuBella.prototype.putNotifications = function(data) {
    console.log("putNotification : ", data);
    $.ajax('/notification', {
        type: 'POST',
        data: {
            method: 'put',
            access_token: gapi.auth.getToken().access_token,
            json: JSON.stringify(data)
        },
        success: function(data) {
            console.log(data);
            PiuBella.prototype.loadNotifications('#accordion2');
        }
    });
};

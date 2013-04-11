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
            $(selector).html(
            $("#notifications-options").render(data.data)
        );
        }
    });
};

/**
 */
PiuBella.prototype.addNotification = function(street, yellow, blue) {
    console.log("addNotification : " + street + " , " + yellow + " , " + blue);
    $.ajax('/notification', {
        type: 'POST',
        data: {
            access_token: gapi.auth.getToken().access_token,
            json: JSON.stringify({
                street: street,
                yellow: yellow,
                blue: blue
            })
        },
        success: function(data) {
            console.log(data);
        }
    });
};

/**
 */
PiuBella.prototype.deleteNotification = function(id) {
    $.ajax('/notifications', {
        method: 'delete',
        data: JSON.stringify({
            method: 'delete',
            access_token: gapi.auth.getToken().access_token
        }),
        success: function() { /* notif supprimée */ }
    });
};

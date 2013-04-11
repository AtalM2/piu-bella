var PiuBella = function(conf) {
};

/**
 */
PiuBella.prototype.loadNotifications = function(selector) {
    $.getJSON("/notification",function(data){
        $(selector).html(
            $("#notifications-options").render(data.data)
            );
    })
};

/**
 */
PiuBella.prototype.addNotification = function(street, yellow, blue) {
    $.ajax('/notification', {
        type: 'POST',
        data: {
            data: JSON.stringify({
                access_token: gapi.auth.getToken(),
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
            access_token: gapi.auth.getToken()
        }),
        success: function() { /* notif supprimée */ }
    });
};

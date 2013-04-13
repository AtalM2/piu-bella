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
            var notifs = notifData = data.data;
            $(selector).html(
                $("#notifications-options").render(data.data)
                );
            for (var i = 0; i < notifs.length; i++) {
                var notif = notifs[i];
                if (notif.blue.indexOf('EMAIL') !== -1) {
                    $('#notif' + i + 'bg').addClass('active');
                    $('#notif' + i + 'bg').addClass('btn-success');
                }
                if (notif.blue.indexOf('XMPP') !== -1) {
                    $('#notif' + i + 'bx').addClass('active');
                    $('#notif' + i + 'bx').addClass('btn-success');
                }
                if (notif.yellow.indexOf('EMAIL') !== -1) {
                    $('#notif' + i + 'yg').addClass('active');
                    $('#notif' + i + 'yg').addClass('btn-success');
                }
                if (notif.yellow.indexOf('XMPP') !== -1) {
                    $('#notif' + i + 'yx').addClass('active');
                    $('#notif' + i + 'yx').addClass('btn-success');
                }
            }
            $('.accordion-inner .btn-group > .btn').click(function() {
                $(this).toggleClass('active');
                var blue = [],
                yellow = [],
                id = $(this).attr('id').replace(/notif(.*)../, '$1');
                if ($('#notif' + id + 'yg').hasClass('active')) {
                    yellow.push('EMAIL');
                    $('#notif' + id + 'yg').addClass('btn-success');
                }
                else {
                    $('#notif' + id + 'yg').removeClass('btn-success');
                }
                if ($('#notif' + id + 'yx').hasClass('active')) {
                    yellow.push('XMPP');
                    $('#notif' + id + 'yx').addClass('btn-success');
                }
                else {
                    $('#notif' + id + 'yx').removeClass('btn-success');
                }
                if ($('#notif' + id + 'bg').hasClass('active')) {
                    blue.push('EMAIL');
                    $('#notif' + id + 'bg').addClass('btn-success');
                }
                else {
                    $('#notif' + id + 'bg').removeClass('btn-success');
                }
                if ($('#notif' + id + 'bx').hasClass('active')) {
                    blue.push('XMPP');
                    $('#notif' + id + 'bx').addClass('btn-success');
                }
                else {
                    $('#notif' + id + 'bx').removeClass('btn-success');
                }
                notifData[id]['blue'] = blue;
                notifData[id]['yellow'] = yellow;
                PiuBella.prototype.putNotifications(notifData, false);
                
            });
            $('.btn-remove').click( function () {
                console.log("remove click");
                var idToRemove = (this.id).replace("remove","");
                var indexOfNotifToRemove = null;
                var streetToRemove = null;
                console.log("idToRemove : " + idToRemove);
                        for (var i = 0 ; i < notifData.length ; i++) {
                            console.log("notifData[i][id] : " + notifData[i]["id"]);
                            if(notifData[i]["id"]==idToRemove){
                                indexOfNotifToRemove = i;
                                streetToRemove = notifData[i]["street"];
                                break;
                            }
                        };
                bootbox.confirm("Vous ne disposerez plus d'alertes pour vos poubelles "+ streetToRemove +" !", function(result) {
                    if (result) {
                        notifData.pop(indexOfNotifToRemove);
                        PiuBella.prototype.putNotifications(notifData,true);
                    }
                });
            });
        }
    });
};

/**
*/
PiuBella.prototype.putNotifications = function(data, reload) {
    console.log("putNotification : ", data);
    console.log(JSON.stringify(data));
    $.ajax('/notification', {
        type: 'POST',
        data: {
            method: 'put',
            access_token: gapi.auth.getToken().access_token,
            json: JSON.stringify(data)
        },
        success: function(data) {
            console.log(data);
            if (reload) {
                PiuBella.prototype.loadNotifications('#accordion2');
            }
        }
    });
};

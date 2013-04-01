var PiuBella = function(conf) { };

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
  $.ajax('/notifications', {
    method: 'post',
    data: {   },
    success: function() { /* notif ajoutée */ }
  });
};

/**
 */
PiuBella.prototype.deleteNotification = function(id) {
  $.ajax('/notifications', {
    method: 'delete',
    data: {   },
    success: function() { /* notif supprimée */ }
  });
};

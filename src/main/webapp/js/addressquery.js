(function() {
    var url = "/fetchjson";
    var dict = null;
    var addressList = [];

    var street = null;
    
    $(window).load(function() {
        $('#loading-indicator').show();
        $.ajax(url,{
            complete: function() {
                $('#loading-indicator').hide();
            },
            success: function(data) {
                notifData = data.data;
                if(!dict){
                    dict = {};
                    addressList = $.map(notifData,function(item) {

                        dict[item[0]]=item;
                        return item[0];

                    });
                }
                $('#addressquery').typeahead({
                    source: addressList,

                    updater:function (item) {
                        function intToDay(index) {
                            var days = _.map(index, function(day) {
                                switch (day) { //was index
                                    case 0:
                                        return 'lundi';
                                    case 1:
                                        return 'mardi';
                                    case 2:
                                        return 'mercredi';
                                    case 3:
                                        return 'mercredi des semaines paires';
                                    case 4:
                                        return 'mercredi des semaines impaires';
                                    case 5:
                                        return 'jeudi';
                                    case 6:
                                        return 'vendredi';
                                    case 7:
                                        return 'samedi';
                                    case 8:
                                        return 'dimanche';
                                }
                            });
                            return days.join(', ').replace(/, ([^,]+$)/, ' et $1');
                        }

                        $('#address-query-wrapper').popover('destroy');
                        var itemdic = dict[item]

                        street = itemdic[0];
                        var yellow = (itemdic.length > 2) ? itemdic[2] : [];
                        var blue = itemdic[1];

                        yellow = intToDay(yellow);
                        blue = intToDay(blue);

                        var popoverContent =
                        (yellow !== ''
                            ? ('<div class="yellow-blue"><img src="/img/poubelle-jaune.png" id="poubelle-jaune" style="margin-right: 10px; height:40px;"/>Sacs jaunes : ' + yellow
                                + '</div>')
                            : '')
                        + '<div class="yellow-blue"><img src="/img/poubelle-bleue.png" id="poubelle-jaune" style="margin-right: 10px; height:40px;"/>Sacs bleus : '+ blue + '</div>'
                        + '<div align=right>'
                        + '<button type="button" class="btn btn-primary" id="notif-creation" '
                        + 'data-loading-text="Création...">Créer une alerte</button>';

                        

                        $('#address-query-wrapper').popover({
                            title: "Jours de collecte",
                            content: popoverContent,
                            placement: 'right',
                            html: true,
                            trigger: 'manual'
                        });

                        $('#address-query-wrapper').popover('show');
                        return item;
                    }
                });
            },
            dataType: "json",
            cache: true
        });
    });
    var yellowAdd = ["EMAIL"];
    var blueAdd = ["EMAIL"];
    $('body').on('click', '.popover button', function () {
        console.log("click : ");
        var alreadyNotified = false;
        for (var i = 0; i < notifData.length ; i++) {
            console.log("notifData[i][street] : " + notifData[i][street]);
            console.log("street : " + street);
            if(notifData[i]["street"]==street){
                alreadyNotified = true;
                break;
            }
        };
        if(!alreadyNotified){
            notifData[notifData.length] = {
                        'street': street,
                        'blue': blueAdd,
                        'yellow': yellowAdd
                    };

            console.log("notifData apres add" + notifData);
            PiuBella.prototype.putNotifications(notifData, true);

            $('#address-query-wrapper').popover('destroy');
        }
        else bootbox.alert("Vous avez déjà listé cette alerte !");
        $('#address-query-wrapper').popover('destroy');
        
        });
})();

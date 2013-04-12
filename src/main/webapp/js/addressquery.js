(function() {
    var url = "/fetchjson";
    var dict = null;
    var addressList = [];

    var street = "myStreet";
    
    $(window).load(function() {
        $('#loading-indicator').show();
        $.ajax(url,{
            complete: function() {
                $('#loading-indicator').hide();
            },
            success: function(data) {
                if(!dict){
                    dict = {};
                    addressList = $.map(data.data,function(item) {

                        

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
                                        return 'Lundi';
                                    case 1:
                                        return 'Mardi';
                                    case 2:
                                        return 'Mercredi';
                                    case 3:
                                        return 'Mercredi des semaines paires';
                                    case 4:
                                        return 'Mercredi des semaines impaires';
                                    case 5:
                                        return 'Jeudi';
                                    case 6:
                                        return 'Vendredi';
                                    case 7:
                                        return 'Samedi';
                                    case 8:
                                        return 'Dimanche';
                                    default:
                                        return 'lulz';
                                }
                            });
                            return days;
                        };

                        //street = dict[item].LIBELLE;
                        //yellow = dict[item].JAUNE_JOUR_COLLECTE;
                        //blue = dict[item].BLEU_JOUR_COLLECTE;

                        $('#address-query-wrapper').popover('destroy');
                        var itemdic = dict[item]

                        street = itemdic[0];
                        console.log("itemdic : " + itemdic);
                        console.log("street : " + street);
                        var yellow = (itemdic.length > 2) ? itemdic[2] : null;
                        console.log("yellow : " + yellow);                        
                        var blue = itemdic[1];
                        console.log("blue : " + blue);

                        yellow = intToDay(yellow);
                        blue = intToDay(blue);

                        var popoverContent =
                        (yellow !== null
                            ? ('<div class="yellow-blue"><img src="/img/poubelle-jaune.png" id="poubelle-jaune" style="margin-right: 10px; height:40px;"/>Sacs jaunes : ' + yellow
                                + '</div>')
                            : '')
                        + '<div class="yellow-blue"><img src="/img/poubelle-bleue.png" id="poubelle-jaune" style="margin-right: 10px; height:40px;"/>Sacs bleus : '+ blue + '</div>'
                        + '<div align=right>'
                        //+ '<img src="/img/ajax-loader.gif" id="loading-indicator-notif" style="display: none;">'
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
    var yellowAdd = ["XMPP", "EMAIL"];
    var blueAdd = ["XMPP", "EMAIL"];
    $('body').on('click', '.popover button', function () {
        console.log("click : ");
        console.log( street + ", " + yellowAdd + ", " + blueAdd);
            PiuBella.prototype.addNotification(street, yellowAdd, blueAdd);
        });
})();

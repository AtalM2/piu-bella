(function() {
    var url = "/fetchjson";
    var dict = null;
    var addressList = [];

    var street = "myStreet";
    var yellow = "myYellow";
    var blue = "myBlue";
    
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

                        //if(!dict[item.LIBELLE]){
                        //    dict[item.LIBELLE]=item;
                        //    return item.LIBELLE;
                        //}

                        dict[item[0]]=item;
                        return item[0];//+ '<span>' + item.QUARTIER + '</span>';

                    });
                }
                $('#addressquery').typeahead({
                    source: addressList,

                    updater:function (item) {
                        var intToDay = function (index) {
                            switch (index) {
                                case '0':
                                    return 'Lundi';
                                case '1':
                                    return 'Mardi';
                                case '2':
                                    return 'Mercredi';
                                case '3':
                                    return 'Mercredi des semaines paires';
                                case '4':
                                    return 'Mercredi des semaines impaires';
                                case '5':
                                    return 'Jeudi';
                                case '6':
                                    return 'Vendredi';
                                case '7':
                                    return 'Samedi';
                                case '8':
                                    return 'Dimanche';
                            }
                        };

                        //street = dict[item].LIBELLE;
                        //yellow = dict[item].JAUNE_JOUR_COLLECTE;
                        //blue = dict[item].BLEU_JOUR_COLLECTE;

                        $('#address-query-wrapper').popover('destroy');
                        var itemdic = dict[item]
                        var yellow = (itemdic.length > 2) ? itemdic[2] : null;
                        var blue = itemdic[1];

                        var popoverContent =
                        (yellow !== null
                            ? ('<div class="yellow">Sacs jaunes : ' + yellow
                                + '</div>')
                            : '')
                        + '<div class="blue">Sacs bleus : '+ blue + '</div>'
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
    $("#notif-creation").click( function () {
        PiuBella.prototype.addNotification(street,yellow,blue);
    });
})();
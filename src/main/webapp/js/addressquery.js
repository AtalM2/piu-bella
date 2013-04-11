(function() {
    var url = "/fetchjson";
    var dict = null;
    var addressList = [];
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
                        + '<button type="button" class="btn btn-primary" '
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
})();
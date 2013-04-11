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
                        if(!dict[item.LIBELLE]){
                            dict[item.LIBELLE]=item;
                            return item.LIBELLE;
                        }
                    });
                }
                $('#addressquery').typeahead({
                    source: addressList,

                    updater:function (item) {
                        $('#address-query-wrapper').popover('destroy');

                        var street = dict[item].LIBELLE;
                        var yellow = dict[item].JAUNE_JOUR_COLLECTE;
                        var blue = dict[item].BLEU_JOUR_COLLECTE;

                        var popoverContent =
                        '<div class="yellow">Sacs jaunes : ' + yellow
                        + '</div>'
                        + '<div class="blue">Sacs bleus : '+ blue + '</div>'
                        + '<div align=right>'
                        + '<img src="/img/ajax-loader.gif" id="loading-indicator-notif" style="display: none;">'
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
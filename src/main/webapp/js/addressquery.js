$(function addquery(){
var url = "/fetchjson";
var dict = null;
var addressList = [];
$('#addressquery').typeahead({
	source: function(query, process){
		//console.log(query,process);
		adressjson(query,process);
	},

	updater:function (item) {
		$('#address-query-wrapper').popover('destroy');

        var yellow = dict[item].JAUNE_JOUR_COLLECTE;
        var blue = dict[item].BLEU_JOUR_COLLECTE;

        var popoverContent = '<div class="yellow">Sacs jaunes : '+ yellow + '</div>'
        + '<div class="blue">Sacs bleus : '+ blue + '</div>'
        + '<div align=right><button type="button" class="btn btn-primary" data-loading-text="Création...">Créer une alerte</button>';

        $('#address-query-wrapper').popover({
        	title: "Jours de collecte",
        	content: popoverContent,
        	placement: 'right',
        	html: true,
        	trigger: 'manual'
        });

        $('#address-query-wrapper').popover('show');
        //do your stuff.

        //dont forget to return the item to reflect them into input
        return item;
    }
});

var adressjson = function(myAdress, callback){
	$('#loading-indicator').show();
	$.ajax(url,{
		complete: function(noarg){
			$('#loading-indicator').hide();
		},
		success: function(data){
			if(!dict){
				dict = {};
				addressList = $.map(data.data,function(item){
					dict[item.LIBELLE]=item;
					return item.LIBELLE;//+ '<span>' + item.QUARTIER + '</span>';
				});
			}
			callback(addressList);
		},
		dataType: "json",
		crossDomain: true,
		cache: true,
		context: addquery
	});
}
});
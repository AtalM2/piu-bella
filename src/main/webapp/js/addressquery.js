$(function addquery(){
var url = "/fetchjson";
var dict = {};
$('#addressquery').typeahead({
	source: function(query, process){
		//console.log(query,process);
		adressjson(query,process);
	},

	updater:function (item) {

		console.log(dict[item]);
        var yellow = dict[item].JAUNE_JOUR_COLLECTE;
        var blue = dict[item].BLEU_JOUR_COLLECTE;

        var popoverContent = '<div class="yellow">Sacs jaunes : '+ yellow + '</div>'
        + '<div class="blue">Sacs bleus : '+ blue + '</div>'
        + '<div> Mon super bouton de sauvegarde </div>';

        $('#addressquery').popover({
        	title: "Ramassage des poubelles "+item,
        	content: popoverContent,
        	placement: 'right',
        	html: true,
        	trigger: 'manual'
        });

        $('#addressquery').popover('show');
        //do your stuff.

        //dont forget to return the item to reflect them into input
        return item;
    }
});

var adressjson = function(myAdress, callback){
	$.ajax(url,{
		success: function(data){
			console.log(data);
			var addressList = $.map(data.data,function(item){
				dict[item.LIBELLE]=item;
				return item.LIBELLE;//+ '<span>' + item.QUARTIER + '</span>';
			});
			callback(addressList);
		},
		dataType: "json",
		crossDomain: true,
		cache: true,
		context: addquery
	});
}
});
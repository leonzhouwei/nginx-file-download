var prodService = new AdminProductionService();
var fsgService = new AdminFileServiceGroupService();

$(function() {
	prodService.getAll(function(result) {
		initProductionSelect(extractContent(result));
	});
	fsgService.getAll(function(result) {
		initFsgSelect(extractContent(result));
	});
});

function initProductionSelect(data) {
	var len = data.length;
	var select = $('#productionSelect');
	for (var i = 0; i < len; ++i) {
		var e = data[i];
		select.append('<option value="' + e['id'] + '">' + e['name']
				+ '</option>');
	}
}

function initFsgSelect(data) {
	console.log(data);
	const
	len = data.length;
	var select = $('#fsgSelect');
	for (var i = 0; i < len; ++i) {
		var e = data[i];
		select.append('<option value="' + e['id'] + '">' + e['name']
				+ '</option>');
	}
}

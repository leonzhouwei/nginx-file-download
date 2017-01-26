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
	var selectedId = $('#productionIdHidden').val();
	var len = data.length;
	var select = $('#productionSelect');
	for (var i = 0; i < len; ++i) {
		var e = data[i];
		if (e['id'] == selectedId) {
			select.append('<option selected="selected" value="' + e['id']
					+ '">' + e['name'] + '</option>');
		} else {
			select.append('<option value="' + e['id'] + '">' + e['name']
					+ '</option>');
		}
	}
}

function initFsgSelect(data) {
	var selectedId = $('#fsgIdHidden').val();
	var len = data.length;
	var select = $('#fsgSelect');
	for (var i = 0; i < len; ++i) {
		var e = data[i];
		if (e['id'] == selectedId) {
			select.append('<option selected="selected" value="' + e['id'] + '">' + e['name']
				+ '</option>');
		} else {
			select.append('<option value="' + e['id'] + '">' + e['name']
			+ '</option>');
		}
	}
}

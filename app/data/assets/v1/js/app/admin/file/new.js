$(function() {
	$.get("/api/admin/productions", function(result) {
		initProductionSelect(result['content']);
	});
	$.get("/api/admin/fsgroups", function(result) {
		initFsgSelect(result['content']);
	});
});

function initProductionSelect(data) {
	const
	len = data.length;
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

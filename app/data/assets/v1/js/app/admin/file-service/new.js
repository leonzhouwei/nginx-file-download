$(function() {
	$.get("/api/admin/fsgroups", function(result) {
		initSelect(result['content']);
	});
});

function initSelect(data) {
	const
	len = data.length;
	var groupIdSelect = $('#groupIdSelect');
	for (var i = 0; i < len; ++i) {
		var e = data[i];
		groupIdSelect.append('<option value="' + e['id'] + '">' + e['name']
				+ '</option>');
	}
}

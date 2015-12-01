var fsgService = new AdminFileServiceGroupService();

$(function() {
	fsgService.getAll(function(result) {
		initSelect(extractContent(result));
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

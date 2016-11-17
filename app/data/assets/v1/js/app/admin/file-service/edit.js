var fsgService = new AdminFileServiceGroupService();

$(function() {
	fsgService.getAll(function(result) {
		initSelect(extractContent(result));
	});
});

function initSelect(data) {
	var selectedId = $('#fsgIdHidden').val();
	var len = data.length;
	var groupIdSelect = $('#groupIdSelect');
	for (var i = 0; i < len; ++i) {
		var e = data[i];
		if (selectedId == e['id']) {
			groupIdSelect.append('<option selected="selected" value="' + e['id'] + '">' + e['name']
			+ '</option>');
		} else {
			groupIdSelect.append('<option value="' + e['id'] + '">' + e['name']
			+ '</option>');		
		}
	}
}

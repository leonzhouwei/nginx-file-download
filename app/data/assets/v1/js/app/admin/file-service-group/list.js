var service = new AdminFileServiceGroupService();

$(function() {
	init();
});

function init() {
	service.getAll(function(result) {
		initTable(extractContent(result));
	});
}

function initTable(result) {
	$('#tbody').empty();
	var len = result.length;
	for (var i = 0; i < len; ++i) {
		var elem = result[i];
		var buffer = [];
		if (i % 2 == 0) {
			buffer.push('<tr class="active">');
		} else {
			buffer.push('<tr>');
		}

		var id = elem['id'];
		buffer.push('<td>', id, '</td>');
		var enabled = elem['enabled'];
		buffer.push('<td>', enabled, '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['createdAt']), '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['updatedAt']), '</td>');
		buffer.push('<td>', elem['name'], '</td>');
		// ----------
		buffer.push('<td>');
		buffer.push('<a href="#" class="btn btn-primary btn-xs">编辑</a>');
		buffer.push('&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;');
		if (enabled == true) {
			buffer.push('<a href="#" onclick="javascript:disable(' + id,
					');" class="btn btn-warning btn-xs">停用</a>');
		} else {
			buffer.push('<a href=#" onclick="javascript:enable(' + id,
					');" class="btn btn-success btn-xs">启用</a>');
		}
		buffer.push('</td>');

		// ----------
		buffer.push('</tr>');
		var newRow = buffer.join('');
		$('#tbody').append(newRow);
	}
}

function disable(id) {
	service.disable(id, function(data) {
		showAppModelForOk();
		init();
	});
}

function enable(id) {
	service.enable(id, function(data) {
		showAppModelForOk();
		init();
	});
}

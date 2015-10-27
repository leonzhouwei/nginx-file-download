$(function() {
	$.get("/api/files", function(result) {
		initTable(result['content']);
	});
});

function initTable(result) {
	for (var i = 0, len = result.length; i < len; ++i) {
		var elem = result[i];
		var table = $('#table');
		var buffer = [];
		if (i % 2 == 0) {
			buffer.push('<tr class="active">');
		} else {
			buffer.push('<tr>');
		}
		var id = elem['id'];
		buffer.push('<td>', id, '</td>');
		buffer.push('<td>', elem['name'], '</td>');
		buffer.push('<td>', elem['createdAt'], '</td>');
		buffer.push('<td style="text-align: right;">', elem['size'], '</td>');
		buffer.push('<td><a href="/download/?fileId=' + id + '&uuid=', elem['uuid'],
				'" class="btn btn-primary btn-xs">下载</a></td>');
		buffer.push('/<tr>');
		var newRow = buffer.join('');
		$('#table tr:last').after(newRow);
	}
}

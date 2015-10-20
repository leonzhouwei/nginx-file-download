$(function() {
	$.get("/api/dld-tasks", function(result) {
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
		buffer.push('<td>', elem['id'], '</td>');
		buffer.push('<td>');
		buffer.push('<a href="/api/files/' + elem['fileId'] + '" target="_blank">',
				elem['fileId'], '</a>');
		buffer.push('</td>');
		buffer.push('<td style="text-align: right;">', elem['createdAt'],
				'</a>');
		buffer.push('</td>');
		buffer.push('/<tr>');
		var newRow = buffer.join('');
		$('#table tr:last').after(newRow);
	}
}

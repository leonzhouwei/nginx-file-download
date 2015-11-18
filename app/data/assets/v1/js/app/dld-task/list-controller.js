$(function() {
	$.get("/api/i/dld-tasks", function(result) {
		initTable(result['content']);
	});
});

function initTable(result) {
	const len = result.length;
	for (var i = 0; i < len; ++i) {
		var elem = result[i];
		var table = $('#table');
		var buffer = [];
		if (i % 2 == 0) {
			buffer.push('<tr class="active">');
		} else {
			buffer.push('<tr>');
		}
		var hhmmss = toHhmmddHuman(elem['timeCostMillis']);
		buffer.push('<td>', elem['id'], '</td>');
		buffer.push('<td>');
		buffer.push('<a href="/files/' + elem['fileId'] + '" target="_blank">',
				elem['fileId'], '</a>');
		buffer.push('</td>');
		buffer.push('<td>', elem['clientIp'], '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['createdAt']), '</td>');
		buffer.push('<td style="text-align: right;">', hhmmss, '</td>');
		buffer.push('/<tr>');
		var newRow = buffer.join('');
		$('#table tr:last').after(newRow);
	}
}

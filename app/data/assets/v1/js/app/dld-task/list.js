var service = new DldTaskService();

$(function() {
	service.getAll(function(result) {
		initTable(extractContent(result));
	});
});

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

		var hhmmss = toHhmmddHuman(elem['timeCostMillis']);
		buffer.push('<td>', elem['id'], '</td>');
		buffer.push('<td>');
		var fileId = elem['file']['Id'];
		buffer.push('<a href="/files/' + fileId + '" target="_blank">',
				fileId, '</a>');
		buffer.push('</td>');
		buffer.push('<td>', elem['clientIp'], '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['createdAt']), '</td>');
		buffer.push('<td style="text-align: right;">', hhmmss, '</td>');

		buffer.push('</tr>');
		var newRow = buffer.join('');
		$('#table tbody').append(newRow);
	}
}

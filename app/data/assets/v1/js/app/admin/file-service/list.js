$(function() {
	$.get("/api/admin/file-services", function(result) {
		initTable(result['content']);
	});
});

function initTable(result) {
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
		buffer.push('<td>', iso8601ToHuman(elem['createdAt']), '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['updatedAt']), '</td>');
		var enabled = elem['enabled'];
		buffer.push('<td>', enabled, '</td>');
		buffer.push('<td>', elem['groupId'], '</td>');
		buffer.push('<td>', elem['host'], '</td>');

		// ----------
		buffer.push('</tr>');
		var newRow = buffer.join('');
		$('#table tbody').append(newRow);
	}
}

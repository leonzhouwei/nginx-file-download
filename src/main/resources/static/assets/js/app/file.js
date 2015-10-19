$(function() {
	$.get("/api/files", function(result) {
		appendNewRows(result['content']);
	});
});

function appendNewRows(result) {
	for (var i = 0, len = result.length; i < len; ++i) {
		var elem = result[i];
		var table = $('#table');
		var buffer = [];
		buffer.push('<tr>');
		buffer.push('<td>', elem['id'], '</td>');
		buffer.push('<td>', elem['name'], '</td>');
		buffer.push('/<tr>');
		var newRow = buffer.join('');
		$('#table tr:last').after(newRow);
	}
}

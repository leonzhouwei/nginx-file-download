$(function() {
	$.get("/api/files", function(result) {
		for (var i = 0, len = result.length; i < len; ++i) {
			console.log(result[i]);
			var table = $('#table');
			var newRow = '<tr><td>' + result[i]['id'] + '</td><td>'
					+ result[i]['name'] + '</td></tr>';
			$('#table tr:last').after(newRow);
		}
	});
});
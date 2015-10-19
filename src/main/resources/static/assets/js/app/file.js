$(function() {
	$.get("/api/files", function(result) {
		for (var i = 0, len = result.length; i < len; ++i) {
			console.log(result[i]);
		}
	});
});
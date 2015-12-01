var service = new AdminSdCardOrderService();

$(function() {
	service.getAll(function(result) {
		initTable(extractContent(result));
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

		buffer.push('<td>', elem['id'], '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['createdAt']), '</td>');
		buffer.push('<td>', elem['status'], '</td>');
		buffer.push('<td><a href="/files/' + elem['fileId']
				+ '" target="_blank">', elem['fileId'], '</a></td>');
		buffer.push('<td style="text-align: right;">', elem['priceFen'] / 100,
				'</td>');
		buffer.push('<td>', elem['userId'], '</td>');
		buffer.push('<td>', elem['userAddr'], '</td>');
		buffer.push('<td>', elem['userZipCode'], '</td>');
		buffer.push('<td>', elem['userMobile'], '</td>');
		buffer.push('<td>', elem['userEmail'], '</td>');

		buffer.push('</tr>');
		var newRow = buffer.join('');
		$('#tbody').append(newRow);
	}
}

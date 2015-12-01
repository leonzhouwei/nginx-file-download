var service = new FileServiceService();

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

		var id = elem['id'];
		var sizeInMB = parseInt(elem['size'] / 1024 / 1024);
		buffer.push('<td>', id, '</td>');
		buffer.push('<td>', elem['name'], '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['createdAt']), '</td>');
		buffer.push('<td style="text-align: right;">', sizeInMB, '</td>');
		buffer.push('<td>', elem['md'], '</td>');
		buffer.push('<td><a href="/fsgroups/', elem['fileServiceGroupId'],
				'/download/' + elem['name'] + '?fileId=' + id + '&uuid=', UUID
						.generate(),
				'" class="btn btn-primary btn-xs">下载</a></td>');
		buffer.push('<td><a href="/i/sd-card-orders/new?fileId=' + id,
				'" class="btn btn-primary btn-xs">下单</a></td>');

		buffer.push('</tr>');
		var newRow = buffer.join('');
		$('#tbody').append(newRow);
	}
}

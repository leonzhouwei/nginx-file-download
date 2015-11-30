$(function() {
	$.get("/api/i/sd-card-orders", function(result) {
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

		var createdAt = iso8601ToHuman(elem['createdAt']);
		var statusHuman = toStatusHuman(elem['status']);
		buffer.push('<td>', elem['id'], '</td>');
		buffer.push('<td>', createdAt, '</td>');
		buffer.push('<td>', statusHuman, '</td>');
		buffer.push('<td><a href="/files/' + elem['fileId']
				+ '" target="_blank">', elem['fileId'], '</a></td>');
		buffer.push('<td style="text-align: right;">', elem['priceFen'] / 100,
				'</td>');
		buffer.push('<td>', elem['userAddr'], '</td>');
		buffer.push('<td>', elem['userZipCode'], '</td>');
		buffer.push('<td>', elem['userMobile'], '</td>');
		buffer.push('<td>', elem['userEmail'], '</td>');

		buffer.push('</tr>');
		var newRow = buffer.join('');
		$('#tbody').append(newRow);
	}
}

function toStatusHuman(status) {
	var ret = '?';
	switch (status) {
	case 1: {
		ret = "等待接单";
		break;
	}
	case 2: {
		ret = "订单已接";
		break;
	}
	default: {
		ret = '?';
	}
	}
	return ret;
}

var service = new AdminDldTaskService();

$(function() {
	init();
});

function init() {
	service.getAll(function(result) {
		initTable(extractContent(result));
	});
}

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
		buffer.push('<td>', id, '</td>');
		buffer.push('<td>');
		
		var fileId = elem['file']['id'];
		buffer.push('<a href="/files/' + fileId + '" target="_blank">',
				fileId, '</a>');
		buffer.push('</td>');
		
		buffer.push('<td>', elem['user']['id'], '</td>');
		buffer.push('<td>', elem['clientIp'], '</td>');
		var timeCostSeconds = parseInt(elem['timeCostMillis'] / 1000);
		var hours = parseInt(timeCostSeconds / 3600);
		var minutes = parseInt((timeCostSeconds - 3600 * hours) / 60);
		if (minutes < 10) {
			minutes = '0' + minutes;
		}
		var seconds = parseInt(timeCostSeconds - 3600 * hours - 60 * minutes);
		if (seconds < 10) {
			seconds = '0' + seconds;
		}
		buffer.push('<td style="text-align: right;">', hours, 'h ', minutes,
				'm ', seconds, 's', '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['createdAt']), '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['updatedAt']), '</td>');
		var enabled = elem['enabled'];
		buffer.push('<td>');
		if (enabled == true) {
			buffer.push('<a href="#" onclick="javascript:disable(' + id,
					');" class="btn btn-warning btn-xs">停用</a>');
		} else {
			buffer.push('<a href=#" onclick="javascript:enable(' + id,
					');" class="btn btn-success btn-xs">启用</a>');
		}
		buffer.push('</td>');
		buffer.push('</tr>');
		var newRow = buffer.join('');
		$('#tbody').append(newRow);
	}
}

function disable(id) {
	service.disable(id, function(data) {
		showAppModelForOk();
		init();
	});
}

function enable(id) {
	service.enable(id, function(data) {
		showAppModelForOk();
		init();
	});
}

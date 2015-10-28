function iso8601ToHuman(iso8601) {
	var index = iso8601.indexOf('.');
	var ret = iso8601;
	if (index > 0) {
		ret = ret.substring(0, index);
	}
	if (ret.indexOf('T') > 0) {
		ret = ret.replace('T', ' ');
	}
	return ret;
}

function toHhmmddHuman(milliseconds) {
	var buffer = [];
	var timeCostSeconds = milliseconds / 1000;
	var hours = parseInt(timeCostSeconds / 3600);
	buffer.push(hours, 'h');
	var minutes = parseInt((timeCostSeconds - 3600 * hours) / 60);
	if (minutes < 10) {
		minutes = '0' + minutes;
	}
	buffer.push(' ', minutes, 'm');
	var seconds = parseInt(timeCostSeconds - 3600 * hours - 60 * minutes);
	if (seconds < 10) {
		seconds = '0' + seconds;
	}
	buffer.push(' ', seconds, 's');
	return buffer.join('');
}

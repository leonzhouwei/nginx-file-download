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

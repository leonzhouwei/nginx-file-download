function iso8601ToHuman(iso8601) {
	var ret = iso8601.replace('T', ' ');
	return ret;
}

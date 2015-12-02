function FileService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/files';

	this.getAll = function(func) {
		$.get(ROUTE, func);
	}

}

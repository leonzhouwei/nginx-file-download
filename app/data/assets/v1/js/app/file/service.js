function FileService() {

	var ROUTE = '/api/files';

	this.getAll = function(func) {
		$.get(ROUTE, func);
	}

}

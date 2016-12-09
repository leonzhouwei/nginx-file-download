function DldTaskService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/i/dld-tasks';

	this.getAll = function(func) {
		$.get(ROUTE, func).fail(showAppModelForJqError);
	}

}

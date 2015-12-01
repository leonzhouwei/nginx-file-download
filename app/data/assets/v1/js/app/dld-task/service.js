function DldTaskService() {

	var ROUTE = '/api/i/dld-tasks';

	this.getAll = function(func) {
		$.get(ROUTE, func);
	}

}

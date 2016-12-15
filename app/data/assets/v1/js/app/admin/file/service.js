function AdminFileService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/admin/files/';

	this.getAll = function(func) {
		$.get(ROUTE, func).fail(showAppModelForJqError);
	}

	this.disable = function(id, func) {
		$.post(ROUTE + id + '/actions/disable', func).fail(showAppModelForJqError);
	}

	this.enable = function(id, func) {
		$.post(ROUTE + id + '/actions/enable', func).fail(showAppModelForJqError);
	}
	
	this.remove = function(id, func) {
		$.post(ROUTE + id + '/actions/delete', func).fail(showAppModelForJqError);
	}

}

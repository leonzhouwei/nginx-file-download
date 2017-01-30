function AdminFileServiceService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/admin/file-services/';

	this.getAll = function(func) {
		$.get(ROUTE, func).fail(showAppModelForJqError);
	}

	this.disable = function(id, func) {
		$.post(ROUTE + id + '/actions/disable', func).fail(showAppModelForJqError);
	}

	this.enable = function(id, func) {
		$.post(ROUTE + id + '/actions/enable', func).fail(showAppModelForJqError);
	}
	
}

function AdminProductionService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/admin/productions/';

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

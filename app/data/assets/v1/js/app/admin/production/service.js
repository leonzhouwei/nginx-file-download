function AdminProductionService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/admin/productions/';

	this.getAll = function(func) {
		$.get(ROUTE, func);
	}

	this.disable = function(id, func) {
		$.post(ROUTE + id + '/disable', func);
	}

	this.enable = function(id, func) {
		$.post(ROUTE + id + '/enable', func);
	}

}

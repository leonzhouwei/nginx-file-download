function AdminFileServiceGroupService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/admin/fsgroups/';

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

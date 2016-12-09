function AdminAccountService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/admin/accounts/';

	this.getAll = function(func) {
		$.get(ROUTE, func).fail(showAppModelForJqError);
	}

	this.disable = function(id, func) {
		$.post(ROUTE + id + '/disable', func).fail(showAppModelForJqError);
	}

	this.enable = function(id, func) {
		$.post(ROUTE + id + '/enable', func).fail(showAppModelForJqError);
	}

	this.remove = function(id, func) {
		$.post(ROUTE + id + '/delete', func).fail(showAppModelForJqError);
	}

}

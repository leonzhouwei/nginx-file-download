function AdminSdCardOrderService() {

	var ROUTE = '/api/admin/sd-card-orders';

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

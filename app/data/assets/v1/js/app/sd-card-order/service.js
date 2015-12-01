function SdCardOrderService() {

	var ROUTE = '/api/i/sd-card-orders';

	this.getAll = function(func) {
		$.get(ROUTE, func);
	}

}

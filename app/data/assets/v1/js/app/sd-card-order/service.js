function SdCardOrderService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/i/sd-card-orders';

	this.getAll = function(func) {
		$.get(ROUTE, func);
	}

}

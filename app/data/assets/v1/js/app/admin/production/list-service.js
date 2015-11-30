function doGetAll(func) {
	$.get("/api/admin/productions", func);
}

function doDisable(id, func) {
	$.post('/api/admin/productions/' + id + '/disable', func);
}

function doEnable(id, func) {
	$.post('/api/admin/productions/' + id + '/enable', func);
}

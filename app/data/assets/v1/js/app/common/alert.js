jQuery.messager = {
	alert : function(options) {
		if ($("#myModal")) {
			$("#myModal").remove();
		}
		// 默认参数对象
		var defaults = {
			title : '系统提示',
			msg : 'null'
		}
		options = $.extend({}, defaults, options);
		var str = '<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">'
				+ '<div class="modal-dialog" role="document">'
				+ '<div class="modal-content">'
				+ '<div class="modal-header">'
				+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
				+ '<h4 class="modal-title" id="myModalLabel">'
				+ options.title
				+ '</h4>'
				+ '</div>'
				+ '<div class="modal-body">'
				+ '<i class="fa fa-exclamation-triangle fa-3x"></i>'
				+ options.msg
				+ '</div>'
				+ '<div class="modal-footer">'
				+ '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'
				+ '<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>'
				+ '</div>' + '</div>' + '</div>' + '</div>'
		$('body').append(str);
		$('#myModal').modal('show');
	}
}

function alert(message) {
	$.messager.alert({
		msg : message,
		title : '系统提示'
	});
}

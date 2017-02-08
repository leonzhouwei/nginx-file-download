$(function() {
	initI18n();
});

function initI18n() {
	jQuery.i18n.properties({
	    name:'login', 
	    path:'i18n/', 
	    mode:'map',
	    language:'zh-CN',
	    checkAvailableLanguages: true,
	    async: true,
	    callback: function() {
	        alert(jQuery.i18n.prop('label_username'));
	    }
	});
}
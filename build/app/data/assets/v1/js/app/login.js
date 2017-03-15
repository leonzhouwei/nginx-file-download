$(function() {
	initI18n();
});

function initI18n() {
	var props = [
	    {"k": "usernameLabel", "v": "username_label"},
	    {"k": "passwordLabel", "v": "password_label"},
	    {"k": "loginButton", "v": "login_button"}
	];
	i18nInit({
	    name: "login", 
	    props: props,
	});
}
const
API_ROUTE_PREFIX = '/api/v1';

const
CONTENT = 'content';

const
APP_LOGOUT_UL_ID = 'appLogoutUl';
const
APP_LOCALE_HIDDEN_ID = 'appLocaleHidden';
const
APP_MODEL_DIV_ID = 'appModalDiv';
const
APP_MODEL_TITLE_ID = 'appModalTitle';
const
APP_MODEL_BODY_ID = 'appModalBody';

const
APP_LOCALE_HIDDEN_JQ = '#' + APP_LOCALE_HIDDEN_ID;
const
APP_MODEL_TITLE_JQ = '#' + APP_MODEL_TITLE_ID;
const
APP_MODEL_BODY_JQ = '#' + APP_MODEL_BODY_ID;

// ==============================================================================
function isNullOrEmpty(str) {
	return str == undefined || str == null || str == "";
}

// ------------------------------------------------------------------------------
function showElement(id) {
	var str = '#' + id;
	$(str).modal('show');
}

// options { title,body }
function showAppModel(options) {
	$(APP_MODEL_TITLE_JQ).html(options['title']);
	$(APP_MODEL_BODY_JQ).html(options['body']);
	showElement(APP_MODEL_DIV_ID);
}

function showAppModelForOk() {
	showAppModel({
		title : '<font color="green">成功!</font>',
		body : '^_^'
	});
}

function showAppModelForJqError(obj) {
	console.log(obj);
	var error = extractError(obj);
	console.log(error);
	var msg = error['message'];
	showAppModelForNg(msg);
}

function hideAppModel() {
	hideElement(APP_MODEL_DIV_ID);
}

function showAppModelForNg(msg) {
	showAppModel({
		title : '<font color="red">失败!</font>',
		body : msg
	});
}

function hideElement(id) {
	console.log("oops: hide " + id);
	var str = '#' + id;
	$(str).modal('hide');
}

// extract the content field from a JSON string
function extractContent(json) {
	return json[CONTENT];
}

// extract the error from jQuery error object
function extractError(jqErr) {
	var respText = jqErr['responseJSON'];
	var err = respText['error'];
	return err;
}

function apiRoutePrefixNoSlash() {
	return API_ROUTE_PREFIX;
}

// ==============================================================================
// i18n
function i18nInit(options) {
	console.log(options);
	doI18nInit({
	    name: options['name'],
	    language: options['lang'],
	    callback: function() {
	    	var props = options['props'];
	    	for (var i = 0; i < props.length; ++i){
	    		var prop = props[i];
	    		var key = prop['k'];
	    		var value = prop['v'];
	    		console.log("k: " + key + ", v" + value);
	    		$('#' + key).html(jQuery.i18n.prop(value));
	    	}
	    }
	});
}

function doI18nInit(options) {
	var lang = options['lang'];
	if (isNullOrEmpty(lang)) {
		lang = navigator.language || navigator.userLanguage;
		console.log("oops: browser locale is " + lang);
		lang = lang.replace("-", "_");
	}
	if (isNullOrEmpty(lang)) {
		lang = $(APP_LOCALE_HIDDEN_JQ).val();
	}
	if (isNullOrEmpty(lang)) {
		lang = "en";
	}
	console.log("oops: final locale is " + lang);
	jQuery.i18n.properties({
	    name: options['name'], 
	    path: '/assets/v1/i18n/', 
	    mode: 'map',
	    language: lang,
	    checkAvailableLanguages: true,
	    encoding: 'UTF-8',
	    async: true,
	    callback: options['callback']
	});
}

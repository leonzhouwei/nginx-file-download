const
API_ROUTE_PREFIX = '/api/v1';

const
CONTENT = 'content';

const
APP_MODEL_DIV_ID = 'appModalDiv';
const
APP_MODEL_TITLE_ID = 'appModalTitle';
const
APP_MODEL_BODY_ID = 'appModalBody';
const
APP_MODEL_DIV_JQ = '#' + APP_MODEL_DIV_ID;
const
APP_MODEL_TITLE_JQ = '#' + APP_MODEL_TITLE_ID;
const
APP_MODEL_BODY_JQ = '#' + APP_MODEL_BODY_ID;

// -----------------------------------------------------------------------------

// options { title,body }
function showAppModel(options) {
	$(APP_MODEL_TITLE_JQ).html(options['title']);
	$(APP_MODEL_BODY_JQ).html(options['body']);
	$(APP_MODEL_DIV_JQ).modal('show');
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
	var msg = error['message'];
	showAppModelForNg(msg);
}

function showAppModelForNg(msg) {
	showAppModel({
		title : '<font color="red">失败!</font>',
		body : msg
	});
}

function hideAppModel() {
	$(APP_MODEL_DIV_JQ).modal('hide');
}

// extract the content field from a JSON string
function extractContent(json) {
	return json[CONTENT];
}

// extract the error from jQuery error object
function extractError(jqErr) {
	var respText = jqErr['responseJSON'];
	return respText['error'];
}

function apiRoutePrefixNoSlash() {
	return API_ROUTE_PREFIX;
}

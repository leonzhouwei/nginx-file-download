$(function() {
	initI18n();
});

function initI18n() {
	var props = [
	    {"k": "filesHyperlink", "v": "files_hyperlink"},
	    {"k": "downloadTasksHyperlink", "v": "download_tasks_hyperlink"}
	];
	i18nInit({
	    name: "index", 
	    props: props,
	});
}
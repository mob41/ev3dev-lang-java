//
// DocGen JavaScript
// by mob41
//
// A script that reads the versions.json and links the javadoc directly.
// Automatically links tutorials too.
//
// Requires jQuery
//
// License based on GPLv3
//

$(document).ready(function(){
	getVersions();
	
	if (verJson != null){
		putLinks();
	} else {
		alert("DocGen Failed: Cannot find \"versions.json\" as versions definition.\n\n" + "The document is currently corrupted. Contact the document author for this problem.");
	}
});

var verJson = null;

function getVersions(){
	$.ajax({
		async: false,
		url: "versions.json",
		dataType: "json",
		error: function(){
			verJson = null;
		},
		success: function(data){
			verJson = data;
		}
	});
	return verJson;
}

function putLinks(){
	var content = "";
	
	content += "";
	
	$("#docgen-nav").append(content);
}
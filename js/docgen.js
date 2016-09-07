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
			writeError("Cannot relieve \"versions.json\" from directory")
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

function writeError(reason){
	alert("DocGen Failed: Error when using \"versions.json\" as versions definition.\n\n" + "The document is currently corrupted. Contact the document author for this problem.");
	var node = $("#docgen-error");
	
	var reasonstr = "Unknown error";
	
	if (reason != null){
		reasonstr = reason;
	}
	
	if (node != null){
		var content = "";
		content += '<div class="row">';
		content += '	<div class="col-lg-12">';
		content += '		<div class="panel panel-danger">';
		content += '			<div class="panel-heading">';
		content += '				<i class="fa fa-excalmation-circle"></i> DocGen: Error when generating document';
		content += '			</div>';
		content += '			<div class="panel-body">';
		content += '				DocGen failed to generate the document, Reason: <code>' + reasonstr + '</code>';
		content += '			</div>';
		content += '			<div class="panel-footer">';
		content += '				Contact the document author for more details.';
		content += '			</div>';
		content += '		</div>';
		content += '	</div>';
		content += '</div>';
		node.append(content);
	}
}
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
	var node = $("#docgen-status");
	
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
		content += '				<i class="fa fa-exclamation-circle"></i> DocGen: Error when generating document';
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
		node.html(content);
	}
}
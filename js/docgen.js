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
	var jsons;
	
	//TODO Hook tutorials
	
	var json;
	var var_version;
	var var_javadoc;
	if (verJson != null){
		jsons = verJson.versions;
		
		var content;
		for (var i = 0; i < jsons.length; i++){
			json = jsons[i];
			var_version = jsons[i].version;
			var_javadoc = jsons[i].javadoc;
			
			content = "";
			
			content += '                        <li>';
			content += '                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> ' + var_version + '<span class="fa arrow"></span></a>';
			content += '                            <ul class="nav nav-second-level">';
			
			if (var_javadoc != null && var_javadoc == true){
				content += '                                <li>';
				content += '                                    <a href="doc-' + var_version + '" target="_blank">JavaDoc</a>';
				content += '                                </li>';
			}
			
			content += '                            </ul>';
			content += '                        </li>';
			
			$("#docgen-navlist").append(content);
		}
	}
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
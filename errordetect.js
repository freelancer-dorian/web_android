var errors = {}; 

function displayErrors() { 
	// initialize variables 
	var haveErrors = false; 
	// remove the invalid class for all inputs 
	$(":input.invalid").removeClass("invalid"); 
	// iterate through the fields specified in the errors array 
	for (var fieldName in errors) { 
		haveErrors = true; 
		$("input[name='" + fieldName + "']").addClass("invalid"); 
	} // for 
	// if we have errors, then add a message to the errors div 
	$("#errors") 
	.html(haveErrors ? "Errors were found." : "") 
	.css("display", haveErrors ? "block" : "none"); 
}

 // displayErrors 
$(document).ready(function() { 
		$("#taskentry").bind("submit", function() { 
			errors = { 
				"task[name]": ["Task name is required"], 
				"task[due]": ["Due date is invalid"] 
		}; // 
	displayErrors(); 
	return false; 
}); 
});

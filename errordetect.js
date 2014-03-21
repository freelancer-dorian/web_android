var errors = {}; 
errors = { 
			"task[name]": ["Task name is required"], 
			"task[due]": ["Due date is invalid"] 
		}; 

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

function displayFieldErrors1(field) {

	$("#errors").hide();
	// remove the invalid class for all inputs 
	$(":input.invalid").removeClass("invalid");

	// get error msg, to be shown
	var msg = errors[field.name];

	if (msg && (msg.length > 0)) {

		// insert line (Unordered List)
		var errorDetail = $("#errordetail_" + field.id).get(0);
		// if it still does not exist, then create it
		if(!errorDetail) {
			$(field).before("<ul class='errors-inline' id='errordetail_" + field.id + "'></ul>");
			errorDetail = $("#errordetail_" + field.id).get(0);
		}

		// set content
		for (var i = 0; i < msg.length; i++) {
			$(errorDetail).html('').append("<li>" + msg[i] + "</li>");
		}
	}
}

$(document).ready(function(){

	$("#taskentry").validate({ 
		submitHandler: function(form) { 
		// TO BE COMPLETED IN THE NEXT CHAPTER 
		}, 
		showErrors: function(errorMap, errorList) { 
			// initialize an empty errors map 
			errors = {}; 
			// iterate through the jQuery validation error map, and convert to something we can use 
			for (var elementName in errorMap) { 
				if (! errors[elementName]) { 
					errors[elementName] = []; 
				} // if 
				errors[elementName].push(errorMap[elementName]); 
			} // for 
		
			// now display the errors 		
			displayErrors(); 
		} 
	});

	// $(":input").focus(function(evt){
	// 	displayFieldErrors1(this);
	// });

	// $(":input").blur(function(evt){
	// 	$("#errordetail_" + this.id).remove();
	// });
});


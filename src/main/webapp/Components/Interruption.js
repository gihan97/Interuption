$(document).ready(function() {

	$("#alertSuccess").hide();

	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "InterruptionAPI",
			type: type,
			data: $("#formItem").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onItemSaveComplete(response.responseText, status);
			}
		});
});


function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidItemIDSave").val($(this).data("itemid"));
	$("#time").val($(this).closest("tr").find('td:eq(0)').text());
	$("#no_of_crew").val($(this).closest("tr").find('td:eq(1)').text());
	$("#vehicle_no").val($(this).closest("tr").find('td:eq(2)').text());
	$("#phone_no").val($(this).closest("tr").find('td:eq(3)').text());
	$("#province").val($(this).closest("tr").find('td:eq(4)').text());

});





//Delete=============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "InterruptionAPI",
			type: "DELETE",
			data: "intID=" + $(this).data("itemid"),
			dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response.responseText, status);
			}
		});
});



function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}





// CLIENT-MODEL================================================================
function validateItemForm() {
	// TIME
	if ($("#time").val().trim() == "") {
		return "Insert Time.";
	}
	// NO_OF_CREW
	if ($("#no_of_crew").val().trim() == "") {
		return "Insert Number of Crew.";
	}
	// VEHICLE_NO-------------------------------
	if ($("#vehicle_no").val().trim() == "") {
		return "Insert Vehicle Number.";
	}

	// PHONE_NO------------------------
	if ($("#phone_no").val().trim() == "") {
		return "Insert Phone Number.";
	}
		// PROVINCE------------------------
	if ($("#province").val().trim() == "") {
		return "Insert Province.";
	}
	return true;
}

$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 return;
 	}
 	
	// If valid-------------------------
 	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "BillAPI",
 		type : type,
 		data : $("#formItem").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onItemSaveComplete(response.responseText, status);
 		}
 	}); 
 });

function onItemSaveComplete(response, status)
	{
		if (status == "success")
		{
			 var resultSet = JSON.parse(response);
 			 if (resultSet.status.trim() == "success")
			 {
 				$("#alertSuccess").text("Successfully saved.");
 				$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
 			 } 
 			 else if (resultSet.status.trim() == "error")
			 {
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			 }
 		} 
 		else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} 
 		else
 		{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
 		}
		$("#hidItemIDSave").val("");
 		$("#formItem")[0].reset();
}

	// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
		 $("#hidItemIDSave").val($(this).data("billID"));
		 $("#accNo").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#usage1").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#vat").val($(this).closest("tr").find('td:eq(2)').text());
 		 $("#value").val($(this).closest("tr").find('td:eq(3)').text());
 		$("#total").val($(this).closest("tr").find('td:eq(4)').text());
	});
	
	
	
	$(document).on("click", ".btnRemove", function(event)
	{
 		$.ajax(
 		{
 			url : "BillAPI",
 			type : "DELETE",
 			data : "billID=" + $(this).data("billId"),
 			dataType : "text",
 			complete : function(response, status)
 			{
 				onItemDeleteComplete(response.responseText, status);
 			}
 		});
	});


	function onItemDeleteComplete(response, status)
	{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
 			} 
 			else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} 
 		else if (status == "error")
 		{
 				$("#alertError").text("Error while deleting.");
 				$("#alertError").show();
 		} 
 		else
 		{
 				$("#alertError").text("Unknown error while deleting..");
 				$("#alertError").show();
 		}
}
	

	// CLIENT-MODEL================================================================
	function validateItemForm()
	{
		// CODE
		if ($("#accNo").val().trim() == "")
		{
 			return "Insert Account Number.";
 		}

		// NAME
		if ($("#usage1").val().trim() == "")
 		{
 			return "Insert usage.";
 		}
		// NAME
		if ($("#vat").val().trim() == "")
 		{
 			return "Insert vat.";
 		}
		
		// NAME
		if ($("#value").val().trim() == "")
 		{
 			return "Insert total amount.";
 		}

		

		// DESCRIPTION------------------------
		if ($("#total").val().trim() == "")
		{
 			return "Insert total.";
 		}

		return true;
	}
	
	
	
	

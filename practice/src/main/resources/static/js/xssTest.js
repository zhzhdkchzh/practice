
var name = "popup";
var option = "width = 500, height = 500, top = 100, left = 200, location = no";

$(document).ready(function() {
	$("#sendBtn").click(function() {
		alert("Click -> Show Text")
		$("#showBtn").prop("disabled", false);
	})

	$("#showBtn").click(function() {

		$.ajax({
			url: '/sendText',
			method: 'POST',
			data: { sendTxt: $("#writeTxt").val() },
			success: function(data) {
				window.open("/popup", data, option);

			}
		})
	})

});
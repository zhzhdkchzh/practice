

$(document).ready(function(){
	$("#beforeBtn").click(function() {
		$("#formId").attr("action", "/beforeSearch");
		$("#formId").submit();
		})
	$("#resetBtn").click(function() {
		$("#name").val("");
		$("#age").val("");
		$("#date").val("");
		$("#office").val("");
		$("#salary").val("");
		})
	$("#allchk").click(function(){
		if($("#allchk").prop('checked')) {
				$("input[type=checkbox]").prop('checked',true);
		}
		else{
				$("input[type=checkbox]").prop('checked',false);
		}

	})		
})
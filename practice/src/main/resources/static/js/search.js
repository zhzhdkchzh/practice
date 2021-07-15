

$(document).ready(function(){
	$("#searchBtn").click(function(){
	if($("#name").val()==''&$("#age").val()==''&$("#date").val()==''&$("#office").val()==''&$("#salary").val()==''){
		$("#formId").attr("action", "#")
		alert("do not search");
	}
	else{
		$("#formId").attr("action", "/sendCondition");
		$("#formId").submit();
	}
	})
	
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

	$("#searchBtn").click(function(){

	})

})
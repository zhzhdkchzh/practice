

$(document).ready(function(){
	$("#searchBtn").click(function() {
		$.ajax({
			url:"/searchTable",
			data:{name: $("#name").val(), 
			position: $("#position").val(),
			office: $("#office").val(),
			age: $("#age").val(),
			startdate: $("#date").val(),
			salary: $("#salary").val()},
			type:'get',
			async: false,
			success: function(data){
				console.log(data);
			
			}
		})

	})
	$("#beforeBtn").click(function(){
		alert("before search");
	})
	$("#resetBtn").click(function(){
		alert("reset");
	})
	
})
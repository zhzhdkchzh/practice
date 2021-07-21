$(document).ready(function(){
	$("#homeBtn").click(function(){
		window.location.href = '/';
	})
});

$(document).ready(function(){
	$(".sign-btn").click(function(){
		if($("#id").val()==''){
			alert("ID");
			return;
		}
		if($("#pw").val()==''){
			alert("Password");
		return;
		}
		$.ajax({
			url:"/signup",
			type:"post",
			data:{
				id: $("#id").val(), 
				pw:$("#pw").val()
			},
			success: function(data){
				location.href=data;
			}
		})
	})
})
$(document).ready(function(){
	$("#logoutBtn").click(function(){
		$.ajax({
		url: '/logout',
		type: 'POST',
		success: function(data){

			alert('로그아웃이 되었습니다.')
			document.location.href='/';
		}
		})
	})

  });
$(document).ready(function(){
	$("#toggle").click(function(){
		if($("#side-toggle-menu").css('display')!=='none'){
		$("#side-toggle-menu").slideUp();
	 	$("#toggle").attr('class', 'fas fa-angle-down fa-2x')
		}
		else{
		$("#side-toggle-menu").slideDown();
	 	$("#toggle").attr('class', 'fas fa-angle-up fa-2x')
		}
})
})

$(document).ready(function(){
	$(".another-btn").click(function(){
		location.href="/another";
	})
	$("#button-addon2").click(function(){
		$.ajax({
				url: "/another/msg",
				data: JSON.stringify({ contents: $(".cont-val").val() }),
				async: false,
				dataType: "json",
				contentType: 'application/json; charset=utf-8',
				type: "post",
				success: function() {
					

				}
		})
		location.reload()
	})
	$(".del-icon").on('click', function(){
		$.ajax({
			url: "/another/delete",
			type: 'post',
			data: {idx: $(this).attr('id')},
			success: function(){
				location.reload()
			}
		})
		
		
	})
	$(".cont-val").keydown(function(key) {
		if (key.keyCode == 13) {
			$.ajax({
				url: "/another/msg",
				async: false,
				data: JSON.stringify({ contents: $(".cont-val").val() }),
				dataType: "json",
				contentType: 'application/json; charset=utf-8',
				type: "post",
				success: function() {
		

				}
			})
			location.reload()
		}
	});
})
var OnAjax = OnAjax ||{
	type:null,
	url:null,
	data:null
} 


OnAjax.getData = function(url, type, data, callback){

	$.ajax({
		url: url,
        type: type,
        data: data,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
		success: function(result){
			callback(result);		
		}
	})
}
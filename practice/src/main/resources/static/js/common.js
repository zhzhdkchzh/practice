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

var OnAjax = OnAjax ||{
	type:null,
	url:null,
	data:null
} 

OnAjax.setAjax = function(url, type, data){
	this.url=url
	this.data=data
	this.type=type
} 

OnAjax.getData = function(){
	$.ajax({
		url: this.url,
        type: this.type,
        data: this.data,
		success: function(data){
			alert(data);
		}
	})
	
}
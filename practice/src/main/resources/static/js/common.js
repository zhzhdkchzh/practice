$(document).ready(function(){
	$("#logoutBtn").click(function(){
		$.ajax({
		url: '/logout',
		method: 'POST',
		success: function(data){

			alert('로그아웃이 되었습니다.')
			document.location.href='/';
		}
		})
	})

  });


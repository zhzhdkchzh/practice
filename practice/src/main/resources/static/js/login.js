$(document).ready(function(){
	
	$("#loginBtn").click(function(){	//로그인 버튼 클릭 시 발생할 이벤트
		if($("#id").val()==''){			//만약 아이디 입력값이 없을경우
			alert("아이디를 입력하세요")	//alert 호출
			return;						//종료
		}
		if($("#pw").val()==''){			//패스워드 입력값이 없을경우
			alert("패스워드를 입력하세요")	
			return;
		}

		$.ajax({						//비동기방식 데이터전송
			url:'/loginProc',	//url 지정
			method:'POST',		//전송방식
			data:{				//요청데이터 지정
				id: $("#id").val(),	//id입력값
				pw: $("#pw").val(),	//pw입력값	
				rememberId: $('#rememberId').is(':checked')	//id기억하기 체크여부
			},
			success: function (data){	//전송 성공시
				console.log(data)	//응답데이터 출력
				if(data == 1){		//반환받은 데이터가 1일경우 (db조회한 아이디가 있음)
					window.location.href="/viewTable"	//지정한 url로 이동
				}
				else if(data == 0){	//실패시 alert 출력
					alert("잘못된 회원정보입니다.");
				}
			},
			error:function(request,status,error){	//ajax 통신 실패시
    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
}
		})
	})
	$("#doBtn").click(function(){		//Test
		$.ajax({
			url:'/test',
			method:'POST',
			data:{	
				test1: $("#test1").val(),
				test2: $("#test2").val(), 
			},
			success: function(data){
			console.log(data)
			}
		})
	})
  });
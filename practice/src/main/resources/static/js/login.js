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
			method:'POST',		//전송방식	Post와 Get 방식의 차이는 url에 요청데이터가 담겨져있냐없냐의 차이인데 
								//get방식은 url에 정보가 담겨져있기 때문에 보안상 취약하지만 캐싱이 가능함
			data:{				//요청데이터이며 서버로 넘어가는 요청데이터는 json형태로 보내짐 (key: value 형식)
				id: $("#id").val(),	//id입력값
				pw: $("#pw").val(),	//pw입력값	
				rememberId: $('#rememberId').is(':checked')	//id기억하기 체크여부
			},
			success: function (data){	//통신 성공시	여기서부터 controller에 설정된 요청url과 같은 url을 받는 메소드와 매핑되고 응답데이터를 data로 받아옴
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